/*
 * Copyright (c) 2018 - Arcade Analytics LTD (https://arcadeanalytics.com)
 */

/* Generated By:JJTree: Do not edit this line. OCreateVertexStatementEmptyNoTarget.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=true,NODE_PREFIX=O,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package com.arcadedb.sql.parser;

public
class CreateVertexStatementEmptyNoTarget extends CreateVertexStatement {
  public CreateVertexStatementEmptyNoTarget(int id) {
    super(id);
  }

  public CreateVertexStatementEmptyNoTarget(SqlParser p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(SqlParserVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }

  @Override public CreateVertexStatement copy() {
    return super.copy();
  }
}
/* JavaCC - OriginalChecksum=e8507ab0b0c002964e04813d45ee71a0 (do not edit this line) */
