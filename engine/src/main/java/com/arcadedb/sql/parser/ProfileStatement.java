/*
 * Copyright (c) 2018 - Arcade Analytics LTD (https://arcadeanalytics.com)
 */

/* Generated By:JJTree: Do not edit this line. OExplainStatement.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=true,NODE_PREFIX=O,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package com.arcadedb.sql.parser;

import com.arcadedb.database.Database;
import com.arcadedb.exception.CommandExecutionException;
import com.arcadedb.sql.executor.*;

import java.util.HashMap;
import java.util.Map;

public class ProfileStatement extends Statement {

  protected Statement statement;

  public ProfileStatement(int id) {
    super(id);
  }

  public ProfileStatement(SqlParser p, int id) {
    super(p, id);
  }

  @Override
  public void toString(Map<Object, Object> params, StringBuilder builder) {
    builder.append("EXPLAIN ");
    statement.toString(params, builder);
  }

  @Override
  public ResultSet execute(Database db, Object[] args, CommandContext parentCtx) {
    BasicCommandContext ctx = new BasicCommandContext();
    if (parentCtx != null) {
      ctx.setParentWithoutOverridingChild(parentCtx);
    }
    ctx.setDatabase(db);
    Map<Object, Object> params = new HashMap<>();
    if (args != null) {
      for (int i = 0; i < args.length; i++)
        params.put(i, args[i]);
    }
    ctx.setInputParameters(params);

    ExecutionPlan executionPlan = statement.createExecutionPlan(ctx, true);
    if(executionPlan instanceof UpdateExecutionPlan){
      ((UpdateExecutionPlan) executionPlan).executeInternal();
    }

    LocalResultSet rs = new LocalResultSet((InternalExecutionPlan) executionPlan);

    while (rs.hasNext()) {
      rs.next();
    }

    ExplainResultSet result = new ExplainResultSet(
        rs.getExecutionPlan().orElseThrow(() -> new CommandExecutionException("Cannot profile command: " + statement)));
    rs.close();
    return result;

  }

  @Override
  public ResultSet execute(Database db, Map args, CommandContext parentCtx) {
    BasicCommandContext ctx = new BasicCommandContext();
    if (parentCtx != null) {
      ctx.setParentWithoutOverridingChild(parentCtx);
    }
    ctx.setDatabase(db);
    ctx.setInputParameters(args);

    ExecutionPlan executionPlan = statement.createExecutionPlan(ctx, true);

    LocalResultSet rs = new LocalResultSet((InternalExecutionPlan) executionPlan);

    while (rs.hasNext()) {
      rs.next();
    }

    ExplainResultSet result = new ExplainResultSet(
        rs.getExecutionPlan().orElseThrow(() -> new CommandExecutionException("Cannot profile command: " + statement)));
    rs.close();
    return result;
  }

  @Override
  public InternalExecutionPlan createExecutionPlan(CommandContext ctx, boolean profile) {
    return statement.createExecutionPlan(ctx, profile);
  }

  @Override
  public ProfileStatement copy() {
    ProfileStatement result = new ProfileStatement(-1);
    result.statement = statement == null ? null : statement.copy();
    return result;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;

    ProfileStatement that = (ProfileStatement) o;

    if (statement != null ? !statement.equals(that.statement) : that.statement != null)
      return false;

    return true;
  }

  @Override
  public int hashCode() {
    return statement != null ? statement.hashCode() : 0;
  }

  @Override
  public boolean isIdempotent() {
    return true;
  }
}
/* JavaCC - OriginalChecksum=9fdd24510993cbee32e38a51c838bdb4 (do not edit this line) */
