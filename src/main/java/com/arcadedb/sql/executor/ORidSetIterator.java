package com.arcadedb.sql.executor;

import com.arcadedb.database.PRID;

import java.util.Iterator;

/**
 * Created by luigidellaquila on 25/10/16.
 */
public class ORidSetIterator implements Iterator<PRID> {

  private ORidSet set;
  int  currentCluster = -1;
  long currentId      = -1;

  ORidSetIterator(ORidSet set) {
    this.set = set;
    fetchNext();
  }

  @Override public boolean hasNext() {
    return currentCluster >= 0;
  }

  @Override public PRID next() {
    if (!hasNext()) {
      throw new IllegalStateException();
    }
    PRID result = new PRID(currentCluster, currentId);
    currentId++;
    fetchNext();
    return result;
  }

  private void fetchNext() {
    if (currentCluster < 0) {
      currentCluster = 0;
      currentId = 0;
    }

    long currentArrayPos = currentId / 63;
    long currentBit = currentId % 63;
    int block = (int) (currentArrayPos / set.maxArraySize);
    int blockPositionByteInt = (int) (currentArrayPos % set.maxArraySize);

    while (currentCluster < set.content.length) {
      while (set.content[currentCluster] != null && block < set.content[currentCluster].length) {
        while (set.content[currentCluster][block] != null && blockPositionByteInt < set.content[currentCluster][block].length) {
          if (currentBit == 0 && set.content[currentCluster][block][blockPositionByteInt] == 0L) {
            blockPositionByteInt++;
            currentArrayPos++;
            continue;
          }
          if (set.contains(new PRID(currentCluster, currentArrayPos * 63 + currentBit))) {
            currentId = currentArrayPos * 63 + currentBit;
            return;
          } else {
            currentBit++;
            if (currentBit > 63) {
              currentBit = 0;
              blockPositionByteInt++;
              currentArrayPos++;
            }
          }
        }
        if (set.content[currentCluster][block] == null && set.content[currentCluster].length >= block) {
          currentArrayPos += set.maxArraySize;
        }
        block++;
        blockPositionByteInt = 0;
        currentBit = 0;
      }
      block = 0;
      currentBit = 0;
      currentArrayPos = 0;
      blockPositionByteInt = 0;
      currentCluster++;
    }

    currentCluster = -1;
  }

}
