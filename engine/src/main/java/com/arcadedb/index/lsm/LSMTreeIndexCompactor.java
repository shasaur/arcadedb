/*
 * Copyright (c) 2018 - Arcade Analytics LTD (https://arcadeanalytics.com)
 */

package com.arcadedb.index.lsm;

import com.arcadedb.GlobalConfiguration;
import com.arcadedb.database.Binary;
import com.arcadedb.database.Database;
import com.arcadedb.database.RID;
import com.arcadedb.database.TrackableBinary;
import com.arcadedb.engine.MutablePage;
import com.arcadedb.schema.SchemaImpl;
import com.arcadedb.serializer.BinaryComparator;
import com.arcadedb.serializer.BinarySerializer;
import com.arcadedb.utility.FileUtils;
import com.arcadedb.utility.LogManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LSMTreeIndexCompactor {
  public LSMTreeIndexCompactor() {
  }

  public static boolean compact(final LSMTreeIndex mainIndex) throws IOException, InterruptedException {
    final LSMTreeIndexMutable index = mainIndex.getMutableIndex();

    final Database database = index.getDatabase();

    final int totalPages = index.getTotalPages();
    LogManager.instance()
        .debug(mainIndex, "Compacting index '%s' (pages=%d pageSize=%d threadId=%d)...", index, totalPages, index.getPageSize(), Thread.currentThread().getId());

    if (totalPages < 2)
      return false;

    final long startTime = System.currentTimeMillis();

    LSMTreeIndexCompacted compactedIndex = index.getSubIndex();
    if (compactedIndex == null) {
      // CREATE A NEW INDEX
      compactedIndex = index.createNewForCompaction();
      ((SchemaImpl) index.getDatabase().getSchema()).registerFile(compactedIndex);
    }

    final byte[] keyTypes = index.getKeyTypes();

    long indexCompactionRAM = database.getConfiguration().getValueAsLong(GlobalConfiguration.INDEX_COMPACTION_RAM_MB) * 1024 * 1024;

    final long maxUsableRAM = Runtime.getRuntime().maxMemory() * 30 / 100;
    if (indexCompactionRAM > maxUsableRAM) {
      LogManager.instance().warn(mainIndex, "Configured RAM for compaction (%dMB) is more than 1/3 of the max heap (%s). Forcing to %s", indexCompactionRAM,
          FileUtils.getSizeAsString(Runtime.getRuntime().maxMemory()), maxUsableRAM);
      indexCompactionRAM = maxUsableRAM;
    }

    long iterations = 1;
    long totalKeys = 0;
    long totalValues = 0;
    long totalMergedKeys = 0;
    long totalMergedValues = 0;

    final Binary keyValueContent = new Binary();

    int pagesToCompact;
    int compactedPages = 0;

    // TODO: NOT LAST PAGE BUT RATHER LAST IMMUTABLE PAGE
    for (int pageIndex = 0; pageIndex < totalPages - 1; ) {

      final long totalRAMNeeded = (totalPages - pageIndex) * (long) index.getPageSize();

      if (totalRAMNeeded > indexCompactionRAM) {
        pagesToCompact = (int) (indexCompactionRAM / index.getPageSize());
        LogManager.instance().info(mainIndex, "- Creating partial index with %d pages by using %s (totalRAMNeeded=%s)", pagesToCompact,
            FileUtils.getSizeAsString(indexCompactionRAM), FileUtils.getSizeAsString(totalRAMNeeded));
      } else
        pagesToCompact = totalPages - pageIndex;

      // CREATE ROOT PAGE
      MutablePage rootPage = compactedIndex.createNewPage(pagesToCompact);
      TrackableBinary rootPageBuffer = rootPage.getTrackable();
      Object[] lastPageMaxKey = null;

      final LSMTreeIndexPageIterator[] iterators = new LSMTreeIndexPageIterator[pagesToCompact];
      for (int i = 0; i < pagesToCompact; ++i)
        iterators[i] = index.newPageIterator(pageIndex + i, 0, true);

      final Object[][] keys = new Object[pagesToCompact][keyTypes.length];

      for (int p = 0; p < pagesToCompact; ++p) {
        if (iterators[p].hasNext()) {
          keys[p] = iterators[p].getKeys();
        } else {
          iterators[p].close();
          iterators[p] = null;
          keys[p] = null;
        }
      }

      final BinarySerializer serializer = database.getSerializer();
      final BinaryComparator comparator = serializer.getComparator();

      MutablePage lastPage = null;
      TrackableBinary currentPageBuffer = null;

      final List<RID> rids = new ArrayList<>();

      boolean moreItems = true;
      for (; moreItems; ++iterations) {
        moreItems = false;

        Object[] minorKey = null;
        final List<Integer> minorKeyIndexes = new ArrayList<>();

        // FIND THE MINOR KEY
        for (int p = 0; p < pagesToCompact; ++p) {
          if (minorKey == null) {
            minorKey = keys[p];
            if (minorKey != null) {
              moreItems = true;
              minorKeyIndexes.add(p);
            }
          } else {
            if (keys[p] != null) {
              moreItems = true;
              final int cmp = LSMTreeIndexMutable.compareKeys(comparator, keyTypes, keys[p], minorKey);
              if (cmp == 0) {
                minorKeyIndexes.add(p);
                ++totalMergedKeys;
              } else if (cmp < 0) {
                minorKey = keys[p];
                if (minorKey != null) {
                  minorKeyIndexes.clear();
                  minorKeyIndexes.add(p);
                }
              }
            }
          }
        }

        rids.clear();
        for (int i = 0; i < minorKeyIndexes.size(); ++i) {
          final LSMTreeIndexPageIterator iter = iterators[minorKeyIndexes.get(i)];
          if (iter == null)
            continue;

          final Object[] value = iter.getValue();
          if (value == null)
            // DELETED
            continue;

          if (i > 0)
            totalMergedValues += rids.size();

          for (int r = 0; r < value.length; ++r)
            rids.add((RID) value[r]);
        }

        if (!rids.isEmpty()) {
          final RID[] ridsArray = new RID[rids.size()];
          rids.toArray(ridsArray);

          final MutablePage newPage = compactedIndex.appendDuringCompaction(keyValueContent, lastPage, currentPageBuffer, pagesToCompact, minorKey, ridsArray);
          if (newPage != lastPage) {
            if (rootPage != null) {
              // NEW PAGE: STORE THE MIN KEY IN THE ROOT PAGE
              final int newPageNum = newPage.getPageId().getPageNumber();

              final MutablePage newRootPage = compactedIndex
                  .appendDuringCompaction(keyValueContent, rootPage, rootPageBuffer, pagesToCompact, minorKey, new RID[] { new RID(database, 0, newPageNum) });

              LogManager.instance().debug(mainIndex, "- Creating a new entry in root page %s->%d", Arrays.toString(minorKey), newPageNum);

              if (newRootPage != rootPage) {
                // TODO: MANAGE A LINKED LIST OF ROOT PAGES INSTEAD
                LogManager.instance()
                    .info(mainIndex, "- End of space in root index page for index '%s' (rootEntries=%d)", index, compactedIndex.getCount(rootPage));
                rootPage = null;
                rootPageBuffer = null;
              }
            }

            currentPageBuffer = newPage.getTrackable();
            lastPage = newPage;
          }

          // UPDATE LAST PAGE'S KEY
          if (minorKey != null)
            lastPageMaxKey = minorKey;

          ++totalKeys;
          totalValues += rids.size();

          if (totalKeys % 1000000 == 0)
            LogManager.instance().info(mainIndex, "- keys %d values %d - iterations %d (entriesInRootPage=%d)", totalKeys, totalValues, iterations,
                compactedIndex.getCount(rootPage));
        }

        for (int i = 0; i < minorKeyIndexes.size(); ++i) {
          final int idx = minorKeyIndexes.get(i);
          final LSMTreeIndexPageIterator it = iterators[idx];
          if (it != null) {
            if (iterators[idx].hasNext()) {
              iterators[idx].next();
              keys[idx] = iterators[idx].getKeys();
            } else {
              iterators[idx].close();
              iterators[idx] = null;
              keys[idx] = null;
            }
          }
        }
      }

      if (rootPage != null && lastPageMaxKey != null) {
        // WRITE THE MAX KEY
        compactedIndex.appendDuringCompaction(keyValueContent, rootPage, rootPageBuffer, pagesToCompact, lastPageMaxKey, new RID[] { new RID(database, 0, 0) });
        LogManager.instance().debug(mainIndex, "- Creating last entry in root page %s (entriesInRootPage=%d)", Arrays.toString(lastPageMaxKey),
            compactedIndex.getCount(rootPage));
      }

      database.getPageManager().updatePage(lastPage, true, false);
      if (rootPage != null)
        database.getPageManager().updatePage(rootPage, true, false);

      compactedPages += pagesToCompact;

      LogManager.instance()
          .debug(mainIndex, "- compacted %d pages, remaining %d pages (totalKeys=%d totalValues=%d totalMergedKeys=%d totalMergedValues=%d)", compactedPages,
              (totalPages - compactedPages), totalKeys, totalValues, totalMergedKeys, totalMergedValues);

      pageIndex += pagesToCompact;
    }

    final LSMTreeIndexMutable newIndex = mainIndex.splitIndex(totalPages - 1, compactedIndex);

    LogManager.instance()
        .info(mainIndex, "Compaction completed for index '%s' in %dms. New index file has %d mutable pages + %d compacted pages (iterations=%d threadId=%d)",
            index, (System.currentTimeMillis() - startTime), newIndex.getTotalPages(), compactedIndex.getTotalPages(), iterations,
            Thread.currentThread().getId());

    return true;
  }
}
