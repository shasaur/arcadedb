/*
 * Copyright (c) 2018 - Arcade Analytics LTD (https://arcadeanalytics.com)
 */

/* Generated By:JJTree: Do not edit this line. OBatch.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=true,NODE_PREFIX=O,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package com.arcadedb.sql.parser;

import com.arcadedb.exception.CommandExecutionException;
import com.arcadedb.sql.executor.CommandContext;

import java.util.Map;

public class Batch extends SimpleNode {

  protected PInteger num;

  protected InputParameter inputParam;

  public Batch(int id) {
    super(id);
  }

  public Batch(SqlParser p, int id) {
    super(p, id);
  }

  /**
   * Accept the visitor.
   **/
  public Object jjtAccept(SqlParserVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }

  public java.lang.Integer evaluate(CommandContext ctx) {
    if (this.num != null) {
      return num.getValue().intValue();
    } else if (inputParam != null) {
      Object obj = inputParam.getValue(ctx.getInputParameters());
      if (obj == null || !(obj instanceof Number)) {
        throw new CommandExecutionException("" + obj + " is not a number (BATCH)");
      }
      return ((Number) obj).intValue();
    }
    return -1;
  }

  public void toString(Map<Object, Object> params, StringBuilder builder) {
    if (num == null && inputParam == null) {
      return;
    }

    builder.append(" BATCH ");
    if (num != null) {
      num.toString(params, builder);
    } else {
      inputParam.toString(params, builder);
    }
  }

  public Batch copy() {
    Batch result = new Batch(-1);
    result.inputParam = inputParam == null ? null : inputParam.copy();
    result.num = num == null ? null : num.copy();
    return result;
  }

  @Override public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;

    Batch oBatch = (Batch) o;

    if (num != null ? !num.equals(oBatch.num) : oBatch.num != null)
      return false;
    if (inputParam != null ? !inputParam.equals(oBatch.inputParam) : oBatch.inputParam != null)
      return false;

    return true;
  }

  @Override public int hashCode() {
    int result = num != null ? num.hashCode() : 0;
    result = 31 * result + (inputParam != null ? inputParam.hashCode() : 0);
    return result;
  }
}
/* JavaCC - OriginalChecksum=b1587460e08cbf21086d8c8fcca192e0 (do not edit this line) */
