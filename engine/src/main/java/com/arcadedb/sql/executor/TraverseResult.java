/*
 * Copyright (c) 2018 - Arcade Analytics LTD (https://arcadeanalytics.com)
 */

package com.arcadedb.sql.executor;

/**
 * Created by luigidellaquila on 02/11/16.
 */
public class TraverseResult extends ResultInternal {
  protected Integer depth;

  @Override public <T> T getProperty(String name) {
    if ("$depth".equalsIgnoreCase(name)) {
      return (T) depth;
    }
    return super.getProperty(name);
  }

  @Override public void setProperty(String name, Object value) {
    if ("$depth".equalsIgnoreCase(name)) {
      if (value instanceof Number) {
        depth = ((Number) value).intValue();
      }
    } else {
      super.setProperty(name, value);
    }
  }
}
