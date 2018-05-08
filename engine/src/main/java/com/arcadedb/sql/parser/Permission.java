/*
 * Copyright (c) 2018 - Arcade Analytics LTD (https://arcadeanalytics.com)
 */

/* Generated By:JJTree: Do not edit this line. OPermission.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=true,NODE_PREFIX=O,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package com.arcadedb.sql.parser;

import java.util.Map;

public class Permission extends SimpleNode {
  protected String permission;

  public Permission(int id) {
    super(id);
  }

  public Permission(SqlParser p, int id) {
    super(p, id);
  }

  @Override public void toString(Map<Object, Object> params, StringBuilder builder) {
    builder.append(permission);
  }

  @Override public Permission copy() {
    Permission result = new Permission(-1);
    result.permission = permission;
    return result;
  }

  @Override public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;

    Permission that = (Permission) o;

    if (permission != null ? !permission.equals(that.permission) : that.permission != null)
      return false;

    return true;
  }

  @Override public int hashCode() {
    return permission != null ? permission.hashCode() : 0;
  }
}
/* JavaCC - OriginalChecksum=576b31633bf93fdbc597f7448fc3c3b3 (do not edit this line) */
