/*
 * Copyright (c) 2018 - Arcade Analytics LTD (https://arcadeanalytics.com)
 */

package com.arcadedb.database;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class ModifiableDocument extends BaseDocument implements RecordInternal {
  private Map<String, Object> map;

  protected ModifiableDocument(final Database database, final String typeName, final RID rid) {
    super(database, typeName, rid, null);
    this.map = new LinkedHashMap<String, Object>();
  }

  protected ModifiableDocument(final Database database, final String typeName, final RID rid, final Binary buffer) {
    super(database, typeName, rid, buffer);
    buffer.position(buffer.position() + 1); // SKIP RECORD TYPE
  }

  public void merge(final Document other) {
    for (String p : other.getPropertyNames())
      set(p, other.get(p));
  }

  public void fromMap(final Map<String, Object> map) {
    this.map = new HashMap<>(map);
  }

  @Override
  public Map<String, Object> toMap() {
    return new HashMap<>(map);
  }

  @Override
  public JSONObject toJSON() {
    return new JSONObject(map);
  }

  public void set(final String name, final Object value) {
    checkForLazyLoadingProperties();
    map.put(name, value);
  }

  public Object get(final String name) {
    checkForLazyLoadingProperties();
    return map.get(name);
  }

  public Object remove(final String name) {
    checkForLazyLoadingProperties();
    return map.remove(name);
  }

  public void save() {
    if (getIdentity() != null)
      database.updateRecord(this);
    else
      database.createRecord(this);
  }

  public void save(final String bucketName) {
    database.createRecord(this, bucketName);
  }

  @Override
  public void setIdentity(final RID rid) {
    this.rid = rid;
  }

  @Override
  public String toString() {
    final StringBuilder buffer = new StringBuilder(256);
    if (rid != null)
      buffer.append(rid);
    if (typeName != null) {
      buffer.append('@');
      buffer.append(typeName);
    }
    buffer.append('[');
    if (map == null) {
      buffer.append('?');
    } else {
      int i = 0;
      for (Map.Entry<String, Object> entry : map.entrySet()) {
        if (i > 0)
          buffer.append(',');

        buffer.append(entry.getKey());
        buffer.append('=');
        buffer.append(entry.getValue());
        i++;
      }
    }
    buffer.append(']');
    return buffer.toString();
  }

  @Override
  public Set<String> getPropertyNames() {
    checkForLazyLoadingProperties();
    return map.keySet();
  }

  @Override
  public Record modify() {
    return this;
  }

  @Override
  public void reload() {
    map = null;
    super.reload();
  }

  protected void checkForLazyLoadingProperties() {
    if (this.map == null && buffer != null) {
      buffer.position(propertiesStartingPosition);
      this.map = this.database.getSerializer().deserializeProperties(this.database, buffer);
      buffer = null;
    }
  }
}
