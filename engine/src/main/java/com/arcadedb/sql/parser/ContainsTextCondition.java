/*
 * Copyright (c) 2018 - Arcade Analytics LTD (https://arcadeanalytics.com)
 */

/* Generated By:JJTree: Do not edit this line. OContainsTextCondition.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=true,NODE_PREFIX=O,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package com.arcadedb.sql.parser;

import com.arcadedb.database.Identifiable;
import com.arcadedb.sql.executor.CommandContext;
import com.arcadedb.sql.executor.Result;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ContainsTextCondition extends BooleanExpression {

  protected Expression left;
  protected Expression right;

  public ContainsTextCondition(int id) {
    super(id);
  }

  public ContainsTextCondition(SqlParser p, int id) {
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
    Object leftValue = left.execute(currentRecord, ctx);
    if (leftValue == null || !(leftValue instanceof String)) {
      return false;
    }
    Object rightValue = right.execute(currentRecord, ctx);
    if (rightValue == null || !(rightValue instanceof String)) {
      return false;
    }

    return ((String) leftValue).indexOf((String) rightValue) > -1;
  }

  @Override
  public boolean evaluate(Result currentRecord, CommandContext ctx) {
    Object leftValue = left.execute(currentRecord, ctx);
    if (leftValue == null || !(leftValue instanceof String)) {
      return false;
    }
    Object rightValue = right.execute(currentRecord, ctx);
    if (rightValue == null || !(rightValue instanceof String)) {
      return false;
    }

    return ((String) leftValue).indexOf((String) rightValue) > -1;
  }

  public void toString(Map<Object, Object> params, StringBuilder builder) {
    left.toString(params, builder);
    builder.append(" CONTAINSTEXT ");
    right.toString(params, builder);
  }

  @Override
  public boolean supportsBasicCalculation() {
    return true;
  }

  @Override
  protected int getNumberOfExternalCalculations() {
    int total = 0;
    if (!left.supportsBasicCalculation()) {
      total++;
    }
    if (!right.supportsBasicCalculation()) {
      total++;
    }
    return total;
  }

  @Override
  protected List<Object> getExternalCalculationConditions() {
    List<Object> result = new ArrayList<Object>();
    if (!left.supportsBasicCalculation()) {
      result.add(left);
    }
    if (!right.supportsBasicCalculation()) {
      result.add(right);
    }
    return result;
  }

  @Override
  public boolean needsAliases(Set<String> aliases) {
    if (!left.needsAliases(aliases)) {
      return true;
    }
    if (!right.needsAliases(aliases)) {
      return true;
    }
    return false;
  }

  @Override
  public ContainsTextCondition copy() {
    ContainsTextCondition result = new ContainsTextCondition(-1);
    result.left = left.copy();
    result.right = right.copy();
    return result;
  }

  @Override
  public void extractSubQueries(SubQueryCollector collector) {
    left.extractSubQueries(collector);
    right.extractSubQueries(collector);
  }

  @Override
  public boolean refersToParent() {
    return left.refersToParent() || right.refersToParent();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;

    ContainsTextCondition that = (ContainsTextCondition) o;

    if (left != null ? !left.equals(that.left) : that.left != null)
      return false;
    if (right != null ? !right.equals(that.right) : that.right != null)
      return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = left != null ? left.hashCode() : 0;
    result = 31 * result + (right != null ? right.hashCode() : 0);
    return result;
  }

  @Override
  public List<String> getMatchPatternInvolvedAliases() {
    List<String> leftX = left == null ? null : left.getMatchPatternInvolvedAliases();
    List<String> rightX = right == null ? null : right.getMatchPatternInvolvedAliases();

    List<String> result = new ArrayList<String>();
    if (leftX != null) {
      result.addAll(leftX);
    }
    if (rightX != null) {
      result.addAll(rightX);
    }

    return result.size() == 0 ? null : result;
  }

  @Override
  public boolean isCacheable() {
    if (left != null && !left.isCacheable()) {
      return false;
    }
    if (right != null && !right.isCacheable()) {
      return false;
    }
    return true;
  }
}
/* JavaCC - OriginalChecksum=b588492ba2cbd0f932055f1f64bbbecd (do not edit this line) */
