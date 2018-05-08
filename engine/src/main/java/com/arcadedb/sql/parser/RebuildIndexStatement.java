/*
 * Copyright (c) 2018 - Arcade Analytics LTD (https://arcadeanalytics.com)
 */

/* Generated By:JJTree: Do not edit this line. ORebuildIndexStatement.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=true,NODE_PREFIX=O,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package com.arcadedb.sql.parser;

import com.arcadedb.sql.executor.CommandContext;
import com.arcadedb.sql.executor.ResultInternal;
import com.arcadedb.sql.executor.ResultSet;

import java.util.Map;

public class RebuildIndexStatement extends SimpleExecStatement {

  protected boolean all = false;
  protected IndexName name;

  public RebuildIndexStatement(int id) {
    super(id);
  }

  public RebuildIndexStatement(SqlParser p, int id) {
    super(p, id);
  }

  @Override public ResultSet executeSimple(CommandContext ctx) {
    ResultInternal result = new ResultInternal();
    result.setProperty("operation", "rebuild index");

    throw new UnsupportedOperationException();

//    final PDatabase database = getDatabase();
//    if (all) {
//      long totalIndexed = 0;
//      for (PIndex idx : database.getSchema().getIndexes()) {
////        if (idx.isAutomatic())
////          totalIndexed += idx.rebuild();
//      }
//
//      result.setProperty("totalIndexed", totalIndexed);
//    } else {
//      final OIndex<?> idx = database.getMetadata().getIndexManager().getIndex(name.getValue());
//      if (idx == null)
//        throw new PCommandExecutionException("Index '" + name + "' not found");
//
//      if (!idx.isAutomatic())
//        throw new PCommandExecutionException(
//            "Cannot rebuild index '" + name + "' because it's manual and there aren't indications of what to index");
//
//      long val = idx.rebuild();
//      result.setProperty("totalIndexed", val);
//
//    }
//    OInternalResultSet rs = new OInternalResultSet();
//    rs.add(result);
//    return rs;

  }

  @Override public void toString(Map<Object, Object> params, StringBuilder builder) {
    builder.append("REBUILD INDEX ");
    if (all) {
      builder.append("*");
    } else {
      name.toString(params, builder);
    }
  }

  @Override public RebuildIndexStatement copy() {
    RebuildIndexStatement result = new RebuildIndexStatement(-1);
    result.all = all;
    result.name = name == null ? null : name.copy();
    return result;
  }

  @Override public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;

    RebuildIndexStatement that = (RebuildIndexStatement) o;

    if (all != that.all)
      return false;
    if (name != null ? !name.equals(that.name) : that.name != null)
      return false;

    return true;
  }

  @Override public int hashCode() {
    int result = (all ? 1 : 0);
    result = 31 * result + (name != null ? name.hashCode() : 0);
    return result;
  }
}
/* JavaCC - OriginalChecksum=baca3c54112f1c08700ebdb691fa85bd (do not edit this line) */
