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

/* Generated By:JJTree: Do not edit this line. OMatchStatement.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=true,NODE_PREFIX=O,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_USERTYPE_VISIBILITY_PUBLIC=true */
package com.arcadedb.query.sql.parser;

import com.arcadedb.database.Database;
import com.arcadedb.database.Identifiable;
import com.arcadedb.database.Record;
import com.arcadedb.exception.CommandExecutionException;
import com.arcadedb.schema.DocumentType;
import com.arcadedb.schema.Schema;
import com.arcadedb.query.sql.executor.*;

import java.util.*;
import java.util.stream.Collectors;

public class MatchStatement extends Statement {

  static final String DEFAULT_ALIAS_PREFIX = "$ARCADEDB_DEFAULT_ALIAS_";

  long threshold = 20;
  private int      limitFromProtocol = -1;
  private Database database;

  public List<NestedProjection> getReturnNestedProjections() {
    return returnNestedProjections;
  }

  public void setReturnNestedProjections(List<NestedProjection> returnNestedProjections) {
    this.returnNestedProjections = returnNestedProjections;
  }

  public class MatchContext {
    int currentEdgeNumber = 0;

    Map<String, Iterable>     candidates   = new LinkedHashMap<String, Iterable>();
    Map<String, Identifiable> matched      = new LinkedHashMap<String, Identifiable>();
    Map<PatternEdge, Boolean> matchedEdges = new IdentityHashMap<PatternEdge, Boolean>();

    public MatchContext copy(String alias, Identifiable value) {
      MatchContext result = new MatchContext();

      result.candidates.putAll(candidates);
      result.candidates.remove(alias);

      result.matched.putAll(matched);
      result.matched.put(alias, value);

      result.matchedEdges.putAll(matchedEdges);
      result.currentEdgeNumber = currentEdgeNumber;
      return result;
    }

    public Record toDoc() {
      throw new UnsupportedOperationException();
    }

  }

  public static class EdgeTraversal {
    boolean     out = true;
    PatternEdge edge;

    public EdgeTraversal(PatternEdge edge, boolean out) {
      this.edge = edge;
      this.out = out;
    }
  }

  public static class MatchExecutionPlan {
    public List<EdgeTraversal> sortedEdges;
    public Map<String, Long>   preFetchedAliases = new HashMap<String, Long>();
    public String              rootAlias;
  }

  public static final String                 KEYWORD_MATCH           = "MATCH";
  // parsed data
  protected           List<MatchExpression>  matchExpressions        = new ArrayList<>();
  protected           List<MatchExpression>  notMatchExpressions     = new ArrayList<>();
  protected           List<Expression>       returnItems             = new ArrayList<>();
  protected           List<Identifier>       returnAliases           = new ArrayList<>();
  protected           List<NestedProjection> returnNestedProjections = new ArrayList<>();
  protected           boolean                returnDistinct          = false;
  protected           GroupBy                groupBy;
  protected           OrderBy                orderBy;
  protected           Unwind                 unwind;
  protected           Skip                   skip;
  protected           Limit                  limit;

  // post-parsing generated data
  protected Pattern pattern;

  private Map<String, WhereClause> aliasFilters;
  private Map<String, String>      aliasUserTypes;

  // execution data
  private CommandContext context;
//  private OProgressListener progressListener;

  public MatchStatement() {
    super(-1);
  }

  public MatchStatement(int id) {
    super(id);
  }

  public MatchStatement(SqlParser p, int id) {
    super(p, id);
  }

  @Override
  public ResultSet execute(Database db, Object[] args, CommandContext parentCtx, boolean usePlanCache) {
    this.database = db;
    BasicCommandContext ctx = new BasicCommandContext();
    if (parentCtx != null) {
      ctx.setParentWithoutOverridingChild(parentCtx);
    }
    ctx.setDatabase(db);
    Map<Object, Object> params = new HashMap<>();
    if (args != null) {
      for (int i = 0; i < args.length; i++) {
        params.put(i, args[i]);
      }
    }
    ctx.setInputParameters(params);
    InternalExecutionPlan executionPlan = createExecutionPlan(ctx, false);

    return new LocalResultSet(executionPlan);
  }

  @Override
  public ResultSet execute(Database db, Map params, CommandContext parentCtx, boolean usePlanCache) {
    this.database = db;
    BasicCommandContext ctx = new BasicCommandContext();
    if (parentCtx != null) {
      ctx.setParentWithoutOverridingChild(parentCtx);
    }
    ctx.setDatabase(db);
    ctx.setInputParameters(params);
    InternalExecutionPlan executionPlan = createExecutionPlan(ctx, false);

    return new LocalResultSet(executionPlan);
  }

  public InternalExecutionPlan createExecutionPlan(CommandContext ctx, boolean enableProfiling) {
    OMatchExecutionPlanner planner = new OMatchExecutionPlanner(this);
    return planner.createExecutionPlan(ctx, enableProfiling);
  }

  /**
   * Accept the visitor. *
   */
  public Object jjtAccept(SqlParserVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }

  protected void buildPatterns() {
    assignDefaultAliases(this.matchExpressions);
    pattern = new Pattern();
    for (MatchExpression expr : this.matchExpressions) {
      pattern.addExpression(expr);
    }

    Map<String, WhereClause> aliasFilters = new LinkedHashMap<String, WhereClause>();
    Map<String, String> aliasUserTypes = new LinkedHashMap<String, String>();
    for (MatchExpression expr : this.matchExpressions) {
      addAliases(database, expr, aliasFilters, aliasUserTypes, context);
    }

    this.aliasFilters = aliasFilters;
    this.aliasUserTypes = aliasUserTypes;

    rebindFilters(aliasFilters);
  }

  /**
   * rebinds filter (where) conditions to alias nodes after optimization
   *
   * @param aliasFilters
   */
  private void rebindFilters(Map<String, WhereClause> aliasFilters) {
    for (MatchExpression expression : matchExpressions) {
      WhereClause newFilter = aliasFilters.get(expression.origin.getAlias());
      expression.origin.setFilter(newFilter);

      for (MatchPathItem item : expression.items) {
        newFilter = aliasFilters.get(item.filter.getAlias());
        item.filter.setFilter(newFilter);
      }
    }
  }

  /**
   * assigns default aliases to pattern nodes that do not have an explicit alias
   *
   * @param matchExpressions
   */
  private void assignDefaultAliases(List<MatchExpression> matchExpressions) {
    int counter = 0;
    for (MatchExpression expression : matchExpressions) {
      if (expression.origin.getAlias() == null) {
        expression.origin.setAlias(DEFAULT_ALIAS_PREFIX + (counter++));
      }

      for (MatchPathItem item : expression.items) {
        if (item.filter == null) {
          item.filter = new MatchFilter(-1);
        }
        if (item.filter.getAlias() == null) {
          item.filter.setAlias(DEFAULT_ALIAS_PREFIX + (counter++));
        }
      }
    }
  }

  public boolean returnsPathElements() {
    for (Expression item : returnItems) {
      if (item.toString().equalsIgnoreCase("$pathElements")) {
        return true;
      }
    }
    return false;
  }

  public boolean returnsElements() {
    for (Expression item : returnItems) {
      if (item.toString().equalsIgnoreCase("$elements")) {
        return true;
      }
    }
    return false;
  }

  public boolean returnsPatterns() {
    for (Expression item : returnItems) {
      if (item.toString().equalsIgnoreCase("$patterns")) {
        return true;
      }
      if (item.toString().equalsIgnoreCase("$matches")) {
        return true;
      }
    }
    return false;
  }

  public boolean returnsPaths() {
    for (Expression item : returnItems) {
      if (item.toString().equalsIgnoreCase("$paths")) {
        return true;
      }
    }
    return false;
  }

  private void addAliases(final Database database, final MatchExpression expr, Map<String, WhereClause> aliasFilters, Map<String, String> aliasUserTypes,
      CommandContext context) {
    addAliases(database, expr.origin, aliasFilters, aliasUserTypes, context);
    for (MatchPathItem item : expr.items) {
      if (item.filter != null) {
        addAliases(database, item.filter, aliasFilters, aliasUserTypes, context);
      }
    }
  }

  private void addAliases(final Database database, final MatchFilter matchFilter, Map<String, WhereClause> aliasFilters, Map<String, String> aliasUserTypes,
      CommandContext context) {
    String alias = matchFilter.getAlias();
    WhereClause filter = matchFilter.getFilter();
    if (alias != null) {
      if (filter != null && filter.baseExpression != null) {
        WhereClause previousFilter = aliasFilters.get(alias);
        if (previousFilter == null) {
          previousFilter = new WhereClause(-1);
          previousFilter.baseExpression = new AndBlock(-1);
          aliasFilters.put(alias, previousFilter);
        }
        AndBlock filterBlock = (AndBlock) previousFilter.baseExpression;
        if (filter != null && filter.baseExpression != null) {
          filterBlock.subBlocks.add(filter.baseExpression);
        }
      }

      String typez = matchFilter.getTypeName(context);
      if (typez != null) {
        String previousClass = aliasUserTypes.get(alias);
        if (previousClass == null) {
          aliasUserTypes.put(alias, typez);
        } else {
          String lower = getLowerSubclass(database, typez, previousClass);
          if (lower == null) {
            throw new CommandExecutionException("classes defined for alias " + alias + " (" + typez + ", " + previousClass + ") are not in the same hierarchy");
          }
          aliasUserTypes.put(alias, lower);
        }
      }
    }
  }

  private String getLowerSubclass(final Database database, final String className1, final String className2) {
    final Schema schema = database.getSchema();
    final DocumentType class1 = schema.getType(className1);
    final DocumentType class2 = schema.getType(className2);
    if (class1 == null) {
      throw new CommandExecutionException("Type " + className1 + " not found in the schema");
    }
    if (class2 == null) {
      throw new CommandExecutionException("Type " + className2 + " not found in the schema");
    }
    if (class1.isSubTypeOf(className2)) {
      return class1.getName();
    }
    if (class2.isSubTypeOf(className1)) {
      return class2.getName();
    }
    return null;
  }

  @Override
  public boolean isIdempotent() {
    return true;
  }

  public void toString(Map<Object, Object> params, StringBuilder builder) {
    builder.append(KEYWORD_MATCH);
    builder.append(" ");
    boolean first = true;
    for (MatchExpression expr : this.matchExpressions) {
      if (!first) {
        builder.append(", ");
      }
      expr.toString(params, builder);
      first = false;
    }
    builder.append(" RETURN ");
    if (returnDistinct) {
      builder.append("DISTINCT ");
    }
    first = true;
    int i = 0;
    for (Expression expr : this.returnItems) {
      if (!first) {
        builder.append(", ");
      }
      expr.toString(params, builder);
      if (returnNestedProjections != null && i < returnNestedProjections.size() && returnNestedProjections.get(i) != null) {
        returnNestedProjections.get(i).toString(params, builder);
      }
      if (returnAliases != null && i < returnAliases.size() && returnAliases.get(i) != null) {
        builder.append(" AS ");
        returnAliases.get(i).toString(params, builder);
      }
      i++;
      first = false;
    }
    if (groupBy != null) {
      builder.append(" ");
      groupBy.toString(params, builder);
    }
    if (orderBy != null) {
      builder.append(" ");
      orderBy.toString(params, builder);
    }
    if (unwind != null) {
      builder.append(" ");
      unwind.toString(params, builder);
    }
    if (skip != null) {
      builder.append(" ");
      skip.toString(params, builder);
    }
    if (limit != null) {
      builder.append(" ");
      limit.toString(params, builder);
    }
  }

  @Override
  public MatchStatement copy() {
    MatchStatement result = new MatchStatement(-1);
    result.database = database;
    result.matchExpressions = matchExpressions == null ? null : matchExpressions.stream().map(x -> x.copy()).collect(Collectors.toList());
    result.notMatchExpressions =
        notMatchExpressions == null ? null : notMatchExpressions.stream().map(x -> x == null ? null : x.copy()).collect(Collectors.toList());
    result.returnItems = returnItems == null ? null : returnItems.stream().map(x -> x.copy()).collect(Collectors.toList());
    result.returnAliases = returnAliases == null ? null : returnAliases.stream().map(x -> x.copy()).collect(Collectors.toList());
    result.returnNestedProjections = returnNestedProjections == null ? null : returnNestedProjections.stream().map(x -> x.copy()).collect(Collectors.toList());
    result.groupBy = groupBy == null ? null : groupBy.copy();
    result.orderBy = orderBy == null ? null : orderBy.copy();
    result.unwind = unwind == null ? null : unwind.copy();
    result.skip = skip == null ? null : skip.copy();
    result.limit = limit == null ? null : limit.copy();
    result.returnDistinct = this.returnDistinct;
    result.buildPatterns();
    return result;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;

    MatchStatement that = (MatchStatement) o;

    if (matchExpressions != null ? !matchExpressions.equals(that.matchExpressions) : that.matchExpressions != null)
      return false;
    if (notMatchExpressions != null ? !notMatchExpressions.equals(that.notMatchExpressions) : that.notMatchExpressions != null)
      return false;
    if (returnItems != null ? !returnItems.equals(that.returnItems) : that.returnItems != null)
      return false;
    if (returnAliases != null ? !returnAliases.equals(that.returnAliases) : that.returnAliases != null)
      return false;
    if (returnNestedProjections != null ? !returnNestedProjections.equals(that.returnNestedProjections) : that.returnNestedProjections != null)
      return false;
    if (groupBy != null ? !groupBy.equals(that.groupBy) : that.groupBy != null)
      return false;
    if (orderBy != null ? !orderBy.equals(that.orderBy) : that.orderBy != null)
      return false;
    if (unwind != null ? !unwind.equals(that.unwind) : that.unwind != null)
      return false;
    if (skip != null ? !skip.equals(that.skip) : that.skip != null)
      return false;
    if (limit != null ? !limit.equals(that.limit) : that.limit != null)
      return false;

    return returnDistinct == that.returnDistinct;
  }

  @Override
  public int hashCode() {
    int result = matchExpressions != null ? matchExpressions.hashCode() : 0;
    result = 31 * result + (notMatchExpressions != null ? notMatchExpressions.hashCode() : 0);
    result = 31 * result + (returnItems != null ? returnItems.hashCode() : 0);
    result = 31 * result + (returnAliases != null ? returnAliases.hashCode() : 0);
    result = 31 * result + (returnNestedProjections != null ? returnNestedProjections.hashCode() : 0);
    result = 31 * result + (groupBy != null ? groupBy.hashCode() : 0);
    result = 31 * result + (orderBy != null ? orderBy.hashCode() : 0);
    result = 31 * result + (unwind != null ? unwind.hashCode() : 0);
    result = 31 * result + (skip != null ? skip.hashCode() : 0);
    result = 31 * result + (limit != null ? limit.hashCode() : 0);
    return result;
  }

  public Limit getLimit() {
    return limit;
  }

  public void setLimit(Limit limit) {
    this.limit = limit;
  }

  public List<Identifier> getReturnAliases() {
    return returnAliases;
  }

  public void setReturnAliases(List<Identifier> returnAliases) {
    this.returnAliases = returnAliases;
  }

  public List<Expression> getReturnItems() {
    return returnItems;
  }

  public void setReturnItems(List<Expression> returnItems) {
    this.returnItems = returnItems;
  }

  public List<MatchExpression> getMatchExpressions() {
    return matchExpressions;
  }

  public void setMatchExpressions(List<MatchExpression> matchExpressions) {
    this.matchExpressions = matchExpressions;
  }

  public List<MatchExpression> getNotMatchExpressions() {
    return notMatchExpressions;
  }

  public void setNotMatchExpressions(List<MatchExpression> notMatchExpressions) {
    this.notMatchExpressions = notMatchExpressions;
  }

  public boolean isReturnDistinct() {
    return returnDistinct;
  }

  public void setReturnDistinct(boolean returnDistinct) {
    this.returnDistinct = returnDistinct;
  }

  public OrderBy getOrderBy() {
    return orderBy;
  }

  public void setOrderBy(OrderBy orderBy) {
    this.orderBy = orderBy;
  }

  public GroupBy getGroupBy() {
    return groupBy;
  }

  public void setGroupBy(GroupBy groupBy) {
    this.groupBy = groupBy;
  }

  public Unwind getUnwind() {
    return unwind;
  }

  public void setUnwind(Unwind unwind) {
    this.unwind = unwind;
  }

  public Skip getSkip() {
    return skip;
  }

  public void setSkip(Skip skip) {
    this.skip = skip;
  }
}
/* JavaCC - OriginalChecksum=6ff0afbe9d31f08b72159fcf24070c9f (do not edit this line) */