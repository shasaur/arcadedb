/*
 * Copyright (c) 2019 - Arcade Analytics LTD (https://arcadeanalytics.com)
 */

package com.arcadedb;

import com.arcadedb.database.MutableDocument;
import com.arcadedb.schema.DocumentType;
import com.arcadedb.schema.Type;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DocumentTest extends BaseTest {
  @Override
  public void beginTest() {
    database.transaction((tx) -> {
      DocumentType type = database.getSchema().createDocumentType("ConversionTest");

      type.createProperty("string", Type.STRING);
      type.createProperty("int", Type.INTEGER);
      type.createProperty("long", Type.LONG);
      type.createProperty("float", Type.FLOAT);
      type.createProperty("double", Type.DOUBLE);
      type.createProperty("decimal", Type.DECIMAL);
      type.createProperty("date", Type.DATE);
      type.createProperty("datetime", Type.DATETIME);
    });
  }

  @Test
  public void testNoConversion() {
    database.transaction((tx) -> {
      final MutableDocument doc = database.newDocument("ConversionTest");

      final Date now = new Date();

      doc.set("string", "test");
      doc.set("int", 33);
      doc.set("long", 33l);
      doc.set("float", 33.33f);
      doc.set("double", 33.33d);
      doc.set("decimal", new BigDecimal("33.33"));
      doc.set("date", now);
      doc.set("datetime", now);

      Assertions.assertEquals(33, doc.get("int"));
      Assertions.assertEquals(33l, doc.get("long"));
      Assertions.assertEquals(33.33f, doc.get("float"));
      Assertions.assertEquals(33.33d, doc.get("double"));
      Assertions.assertEquals(new BigDecimal("33.33"), doc.get("decimal"));
      Assertions.assertEquals(now, doc.get("date"));
      Assertions.assertEquals(now, doc.get("datetime"));
    });
  }

  @Test
  public void testConversionDecimals() {
    database.transaction((tx) -> {
      final MutableDocument doc = database.newDocument("ConversionTest");

      final Date now = new Date();

      doc.set("decimal", "33.33");
      Assertions.assertEquals(new BigDecimal("33.33"), doc.get("decimal"));

      doc.set("decimal", 33.33f);
      Assertions.assertEquals(new BigDecimal("33.33"), doc.get("decimal"));

      doc.set("decimal", 33.33d);
      Assertions.assertEquals(new BigDecimal("33.33"), doc.get("decimal"));
    });
  }

  @Test
  public void testConversionDates() {
    database.transaction((tx) -> {
      final MutableDocument doc = database.newDocument("ConversionTest");

      final Date now = new Date();

      doc.set("date", now.getTime());
      doc.set("datetime", now.getTime());
      Assertions.assertEquals(now, doc.get("date"));
      Assertions.assertEquals(now, doc.get("datetime"));

      doc.set("date", "" + now.getTime());
      doc.set("datetime", "" + now.getTime());
      Assertions.assertEquals(now, doc.get("date"));
      Assertions.assertEquals(now, doc.get("datetime"));

      final SimpleDateFormat df = new SimpleDateFormat(database.getSchema().getDateTimeFormat());

      doc.set("date", df.format(now));
      doc.set("datetime", df.format(now));
      Assertions.assertEquals(df.format(now), df.format(doc.get("date")));
      Assertions.assertEquals(df.format(now), df.format(doc.get("datetime")));
    });
  }
}