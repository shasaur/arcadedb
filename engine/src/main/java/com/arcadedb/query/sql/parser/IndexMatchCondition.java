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

/* Generated By:JJTree: Do not edit this line. OIndexMatchCondition.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=true,NODE_PREFIX=O,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_USERTYPE_VISIBILITY_PUBLIC=true */
package com.arcadedb.query.sql.parser;

import com.arcadedb.database.Identifiable;
import com.arcadedb.query.sql.executor.CommandContext;
import com.arcadedb.query.sql.executor.Result;

import java.util.*;
import java.util.stream.*;

public class IndexMatchCondition extends BooleanExpression {

  protected BinaryCompareOperator operator;
  protected Boolean               between;

  protected List<Expression> leftExpressions;
  protected List<Expression> rightExpressions;

  public IndexMatchCondition(int id) {
    super(id);
  }

  public IndexMatchCondition(SqlParser p, int id) {
    super(p, id);
  }

  /**
   * Accept the visitor.
   **/
  public Object jjtAccept(SqlParserVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }

  @Override
  public boolean evaluate(Identifiable currentRecord, CommandContext ctx) {
    throw new UnsupportedOperationException("TODO Implement IndexMatch!!!");//TODO
  }

  @Override
  public boolean evaluate(Result currentRecord, CommandContext ctx) {
    throw new UnsupportedOperationException("TODO Implement IndexMatch!!!");//TODO
  }

  public void toString(Map<Object, Object> params, StringBuilder builder) {
    builder.append("KEY ");
    if (operator != null) {
      builder.append(operator);
      builder.append(" [");
      boolean first = true;
      for (Expression x : leftExpressions) {
        if (!first) {
          builder.append(", ");
        }
        x.toString(params, builder);
        first = false;
      }
      builder.append("]");
    } else if (Boolean.TRUE.equals(between)) {
      builder.append(" BETWEEN [");
      boolean first = true;
      for (Expression x : leftExpressions) {
        if (!first) {
          builder.append(", ");
        }
        x.toString(params, builder);
        first = false;
      }
      builder.append("] AND [");
      first = true;
      for (Expression x : rightExpressions) {
        if (!first) {
          builder.append(", ");
        }
        x.toString(params, builder);
        first = false;
      }
      builder.append("]");
    }
  }

  @Override
  public boolean supportsBasicCalculation() {
    return false;
  }

  @Override
  protected int getNumberOfExternalCalculations() {
    return 1;
  }

  @Override
  protected List<Object> getExternalCalculationConditions() {
    List<Object> result = new ArrayList<Object>();
    result.add(this);
    return result;
  }

  @Override
  public boolean needsAliases(Set<String> aliases) {
    if (leftExpressions != null) {
      for (Expression exp : leftExpressions) {
        if (exp.needsAliases(aliases)) {
          return true;
        }
      }
    }
    if (rightExpressions != null) {
      for (Expression exp : rightExpressions) {
        if (exp.needsAliases(aliases)) {
          return true;
        }
      }
    }
    return false;
  }

  @Override
  public IndexMatchCondition copy() {
    IndexMatchCondition result = new IndexMatchCondition(-1);
    result.operator = operator == null ? null : operator.copy();
    result.between = between;

    result.leftExpressions =
        leftExpressions == null ? null : leftExpressions.stream().map(x -> x.copy()).collect(Collectors.toList());
    result.rightExpressions =
        rightExpressions == null ? null : rightExpressions.stream().map(x -> x.copy()).collect(Collectors.toList());

    return result;
  }

  @Override
  public void extractSubQueries(SubQueryCollector collector) {
    if (leftExpressions != null) {
      for (Expression exp : leftExpressions) {
        exp.extractSubQueries(collector);
      }
    }
    if (rightExpressions != null) {
      for (Expression exp : rightExpressions) {
        exp.extractSubQueries(collector);
      }
    }
  }

  @Override
  public boolean refersToParent() {
    if (leftExpressions != null) {
      for (Expression exp : leftExpressions) {
        if (exp != null && exp.refersToParent()) {
          return true;
        }
      }
    }
    if (rightExpressions != null) {
      for (Expression exp : rightExpressions) {
        if (exp != null && exp.refersToParent()) {
          return true;
        }
      }
    }
    return false;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;

    IndexMatchCondition that = (IndexMatchCondition) o;

    if (operator != null ? !operator.equals(that.operator) : that.operator != null)
      return false;
    if (between != null ? !between.equals(that.between) : that.between != null)
      return false;
    if (leftExpressions != null ? !leftExpressions.equals(that.leftExpressions) : that.leftExpressions != null)
      return false;
    return rightExpressions != null ? rightExpressions.equals(that.rightExpressions) : that.rightExpressions == null;
  }

  @Override
  public int hashCode() {
    int result = operator != null ? operator.hashCode() : 0;
    result = 31 * result + (between != null ? between.hashCode() : 0);
    result = 31 * result + (leftExpressions != null ? leftExpressions.hashCode() : 0);
    result = 31 * result + (rightExpressions != null ? rightExpressions.hashCode() : 0);
    return result;
  }

  @Override
  public List<String> getMatchPatternInvolvedAliases() {
    return null;
  }

  @Override
  public boolean isCacheable() {

    if (leftExpressions != null) {
      for (Expression exp : leftExpressions) {
        if (!exp.isCacheable()) {
          return false;
        }
      }
    }
    if (rightExpressions != null) {
      for (Expression exp : rightExpressions) {
        if (!exp.isCacheable()) {
          return false;
        }
      }
    }
    return true;
  }

}
/* JavaCC - OriginalChecksum=702e9ab959e87b043b519844a7d31224 (do not edit this line) */
