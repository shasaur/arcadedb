/*
 * Copyright 2021 Arcade Data Ltd
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

/* Generated By:JJTree: Do not edit this line. OStatement.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=true,NODE_PREFIX=O,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_USERTYPE_VISIBILITY_PUBLIC=true */
package com.arcadedb.query.sql.parser;

import com.arcadedb.database.Database;
import com.arcadedb.exception.CommandExecutionException;
import com.arcadedb.exception.CommandSQLParsingException;
import com.arcadedb.query.sql.executor.CommandContext;
import com.arcadedb.query.sql.executor.InternalExecutionPlan;
import com.arcadedb.query.sql.executor.Result;
import com.arcadedb.query.sql.executor.ResultInternal;
import com.arcadedb.query.sql.executor.ResultSet;

import java.util.*;

public class Statement extends SimpleNode {

  //only for internal use!!! (caching)
  protected String originalStatement;

  public static final String CUSTOM_STRICT_SQL = "strictSql";

  public Statement(int id) {
    super(id);
  }

  public Statement(SqlParser p, int id) {
    super(p, id);
  }

  /**
   * Accept the visitor.
   **/
  public Object jjtAccept(SqlParserVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }

  public void toString(Map<Object, Object> params, StringBuilder builder) {
    throw new UnsupportedOperationException("missing implementation in " + getClass().getSimpleName());
  }

  public void validate() throws CommandSQLParsingException {

  }

  @Override
  public String toString(final String prefix) {
    StringBuilder builder = new StringBuilder();
    toString(null, builder);
    return builder.toString();
  }

  public ResultSet execute(final Database db, final Object[] args) {
    return execute(db, args, true);
  }

  public ResultSet execute(final Database db, final Object[] args, final CommandContext parentContext) {
    return execute(db, args, parentContext, true);
  }

  public ResultSet execute(final Database db, final Map args) {
    return execute(db, args, true);
  }

  public ResultSet execute(final Database db, final Map args, CommandContext parentContext) {
    return execute(db, args, parentContext, true);
  }

  public ResultSet execute(final Database db, final Object[] args, final boolean usePlanCache) {
    return execute(db, args, null, usePlanCache);
  }

  public ResultSet execute(final Database db, final Object[] args, final CommandContext parentContext, boolean usePlanCache) {
    throw new UnsupportedOperationException();
  }

  public ResultSet execute(final Database db, final Map args, final boolean usePlanCache) {
    return execute(db, args, null, usePlanCache);
  }

  public ResultSet execute(final Database db, final Map args, final CommandContext parentContext, final boolean usePlanCache) {
    throw new UnsupportedOperationException();
  }

  /**
   * creates an execution plan for current statement, with profiling disabled
   *
   * @param ctx the context that will be used to execute the statement
   *
   * @return an execution plan
   */
  public InternalExecutionPlan createExecutionPlan(final CommandContext ctx) {
    return createExecutionPlan(ctx, false);
  }

  /**
   * creates an execution plan for current statement
   *
   * @param ctx     the context that will be used to execute the statement
   * @param profile true to enable profiling, false to disable it
   *
   * @return an execution plan
   */
  public InternalExecutionPlan createExecutionPlan(final CommandContext ctx, final boolean profile) {
    throw new UnsupportedOperationException();
  }

  public InternalExecutionPlan createExecutionPlanNoCache(CommandContext ctx, boolean profile) {
    return createExecutionPlan(ctx, profile);
  }

  public Statement copy() {
    throw new UnsupportedOperationException("IMPLEMENT copy() ON " + getClass().getSimpleName());
  }

  public boolean refersToParent() {
    throw new UnsupportedOperationException("Implement " + getClass().getSimpleName() + ".refersToParent()");
  }

  public boolean isIdempotent() {
    return false;
  }

  public static Statement deserializeFromOResult(final Result doc) {
    try {
      Statement result = (Statement) Class.forName(doc.getProperty("__class")).getConstructor(Integer.class).newInstance(-1);
      result.deserialize(doc);
    } catch (Exception e) {
      throw new CommandExecutionException(e);
    }
    return null;
  }

  public Result serialize() {
    ResultInternal result = new ResultInternal();
    result.setProperty("__class", getClass().getName());
    return result;
  }

  public void deserialize(Result fromResult) {
    throw new UnsupportedOperationException();
  }

  public boolean executinPlanCanBeCached() {
    return false;
  }

  public String getOriginalStatement() {
    return originalStatement;
  }

}
/* JavaCC - OriginalChecksum=589c4dcc8287f430e46d8eb12b0412c5 (do not edit this line) */
