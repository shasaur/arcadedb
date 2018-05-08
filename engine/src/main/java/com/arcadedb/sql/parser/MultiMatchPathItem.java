/*
 * Copyright (c) 2018 - Arcade Analytics LTD (https://arcadeanalytics.com)
 */

/* Generated By:JJTree: Do not edit this line. OMultiMatchPathItem.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=true,NODE_PREFIX=O,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package com.arcadedb.sql.parser;

import com.arcadedb.database.Identifiable;
import com.arcadedb.sql.executor.CommandContext;

import java.util.*;
import java.util.stream.Collectors;

public class MultiMatchPathItem extends MatchPathItem {
  protected List<MatchPathItem> items = new ArrayList<MatchPathItem>();

  public MultiMatchPathItem(int id) {
    super(id);
  }

  public MultiMatchPathItem(SqlParser p, int id) {
    super(p, id);
  }

  /**
   * Accept the visitor.
   **/
  public Object jjtAccept(SqlParserVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }

  public boolean isBidirectional() {
    return false;
  }

  public void toString(Map<Object, Object> params, StringBuilder builder) {
    builder.append(".(");
    for (MatchPathItem item : items) {
      item.toString(params, builder);
    }
    builder.append(")");
    if (filter != null) {
      filter.toString(params, builder);
    }
  }

  protected Iterable<Identifiable> traversePatternEdge(MatchStatement.MatchContext matchContext, Identifiable startingPoint,
      CommandContext iCommandContext) {
    Set<Identifiable> result = new HashSet<Identifiable>();
    result.add(startingPoint);
    for (MatchPathItem subItem : items) {
      Set<Identifiable> startingPoints = result;
      result = new HashSet<Identifiable>();
      for (Identifiable sp : startingPoints) {
        Iterable<Identifiable> subResult = subItem.executeTraversal(matchContext, iCommandContext, sp, 0);
        if (subResult instanceof Collection) {
          result.addAll((Collection) subResult);
        } else {
          for (Identifiable id : subResult) {
            result.add(id);
          }
        }
      }
    }
    return result;
  }

  @Override public MultiMatchPathItem copy() {
    MultiMatchPathItem result = (MultiMatchPathItem) super.copy();
    result.items = items == null ? null : items.stream().map(x -> x.copy()).collect(Collectors.toList());
    return result;
  }

  @Override public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    if (!super.equals(o))
      return false;

    MultiMatchPathItem that = (MultiMatchPathItem) o;

    if (items != null ? !items.equals(that.items) : that.items != null)
      return false;

    return true;
  }

  @Override public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + (items != null ? items.hashCode() : 0);
    return result;
  }

  public List<MatchPathItem> getItems() {
    return items;
  }

  public void setItems(List<MatchPathItem> items) {
    this.items = items;
  }
}
/* JavaCC - OriginalChecksum=f18f107768de80b8941f166d7fafb3c0 (do not edit this line) */
