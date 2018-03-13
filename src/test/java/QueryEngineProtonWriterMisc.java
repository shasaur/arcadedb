import com.arcadedb.database.*;
import com.arcadedb.engine.PFile;
import com.arcadedb.schema.PType;
import com.arcadedb.utility.PFileUtils;
import org.junit.Assert;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class QueryEngineProtonWriterMisc {
  private static final int    TOT        = 10000000;
  private static final String CLASS_NAME = "Person";

  public static void main(String[] args) throws Exception {
    new QueryEngineProtonWriterMisc().run();
  }

  private void run() throws IOException {
    PFileUtils.deleteRecursively(new File("/temp/proton/geodb"));

    final int parallel = 3;

    PDatabase database = new PDatabaseFactory("/temp/proton/geodb", PFile.MODE.READ_WRITE).acquire();
    try {
      if (!database.getSchema().existsType(CLASS_NAME)) {
        database.begin();

        final PType type = database.getSchema().createType(CLASS_NAME, parallel);

        type.createProperty("id", Long.class);
        type.createProperty("name", String.class);
        type.createProperty("surname", String.class);
        type.createProperty("locali", Integer.class);

        database.getSchema().createClassIndexes(CLASS_NAME, "id");
        database.commit();
      }
    } finally {
      database.close();
    }

    database = new PDatabaseFactory("/temp/proton/geodb", PFile.MODE.READ_WRITE).useParallel(true).acquire();

    long begin = System.currentTimeMillis();

    try {

      if (database instanceof PDatabaseParallel) {
        ((PDatabaseParallel) database).setCommitEvery(5000);
        ((PDatabaseParallel) database).setParallelLevel(parallel);
      }

      database.begin();

      long row = 0;
      for (; row < TOT; ++row) {
        final PModifiableDocument record = database.newDocument();
        record.setType(CLASS_NAME);

        record.set("id", row);
        record.set("name", "Luca" + row);
        record.set("surname", "Skywalker" + row);
        record.set("locali", 10);

        record.save();

        if (row % 100000 == 0)
          System.out.println("Written " + row + " elements in " + (System.currentTimeMillis() - begin) + "ms");
      }
      database.commit();

      System.out.println("Inserted " + row + " elements in " + (System.currentTimeMillis() - begin) + "ms");

    } finally {
      database.close();
      System.out.println("Insertion finished in " + (System.currentTimeMillis() - begin) + "ms");
    }

    begin = System.currentTimeMillis();
    database = new PDatabaseFactory("/temp/proton/geodb", PFile.MODE.READ_ONLY).acquire();
    try {
      System.out.println("Lookup all the keys...");
      for (long id = 0; id < TOT; ++id) {
        final List<PBaseDocument> records = (List<PBaseDocument>) database
            .lookupByKey(CLASS_NAME, new String[] { "id" }, new Object[] { id });
        Assert.assertNotNull(records);
        Assert.assertEquals("Wrong result for lookup of key " + id, 1, records.size());

        final PBaseDocument record = records.get(0);
        Assert.assertEquals(id, record.get("id"));

        if (id % 100000 == 0)
          System.out.println("Checked " + id + " lookups in " + (System.currentTimeMillis() - begin) + "ms");
      }
    } finally {
      database.close();
      System.out.println("Lookup finished in " + (System.currentTimeMillis() - begin) + "ms");
    }

  }
}