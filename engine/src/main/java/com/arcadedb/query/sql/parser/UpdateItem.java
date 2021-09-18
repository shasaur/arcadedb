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

/* Generated By:JJTree: Do not edit this line. OUpdateItem.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=true,NODE_PREFIX=O,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_USERTYPE_VISIBILITY_PUBLIC=true */
package com.arcadedb.query.sql.parser;

import com.arcadedb.database.Document;
import com.arcadedb.database.Identifiable;
import com.arcadedb.exception.CommandExecutionException;
import com.arcadedb.query.sql.executor.CommandContext;
import com.arcadedb.query.sql.executor.Result;
import com.arcadedb.query.sql.executor.ResultInternal;
import com.arcadedb.schema.DocumentType;
import com.arcadedb.schema.Property;
import com.arcadedb.schema.Type;

import java.util.*;
import java.util.stream.*;

public class UpdateItem extends SimpleNode {
  public static final int OPERATOR_EQ          = 0;
  public static final int OPERATOR_PLUSASSIGN  = 1;
  public static final int OPERATOR_MINUSASSIGN = 2;
  public static final int OPERATOR_STARASSIGN  = 3;
  public static final int OPERATOR_SLASHASSIGN = 4;

  protected Identifier left;
  protected Modifier   leftModifier;
  protected int        operator;
  protected Expression right;

  public UpdateItem(int id) {
    super(id);
  }

  public UpdateItem(SqlParser p, int id) {
    super(p, id);
  }

  /**
   * Accept the visitor.
   **/
  public Object jjtAccept(SqlParserVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }

  public void toString(Map<Object, Object> params, StringBuilder builder) {
    left.toString(params, builder);
    if (leftModifier != null) {
      leftModifier.toString(params, builder);
    }
    switch (operator) {
    case OPERATOR_EQ:
      builder.append(" = ");
      break;
    case OPERATOR_PLUSASSIGN:
      builder.append(" += ");
      break;
    case OPERATOR_MINUSASSIGN:
      builder.append(" -= ");
      break;
    case OPERATOR_STARASSIGN:
      builder.append(" *= ");
      break;
    case OPERATOR_SLASHASSIGN:
      builder.append(" /= ");
      break;

    }
    right.toString(params, builder);
  }

  public UpdateItem copy() {
    UpdateItem result = new UpdateItem(-1);
    result.left = left == null ? null : left.copy();
    result.leftModifier = leftModifier == null ? null : leftModifier.copy();
    result.operator = operator;
    result.right = right == null ? null : right.copy();
    return result;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;

    UpdateItem that = (UpdateItem) o;

    if (operator != that.operator)
      return false;
    if (left != null ? !left.equals(that.left) : that.left != null)
      return false;
    if (leftModifier != null ? !leftModifier.equals(that.leftModifier) : that.leftModifier != null)
      return false;
    return right != null ? right.equals(that.right) : that.right == null;
  }

  @Override
  public int hashCode() {
    int result = left != null ? left.hashCode() : 0;
    result = 31 * result + (leftModifier != null ? leftModifier.hashCode() : 0);
    result = 31 * result + operator;
    result = 31 * result + (right != null ? right.hashCode() : 0);
    return result;
  }

  public void applyUpdate(ResultInternal doc, CommandContext ctx) {
    Object rightValue = right.execute(doc, ctx);
    if (leftModifier == null) {
      applyOperation(doc, left, rightValue, ctx);
    } else {
      Object val = doc.getProperty(left.getStringValue());
      leftModifier.setValue(doc, val, rightValue, ctx);
    }
  }

  public void applyOperation(ResultInternal doc, Identifier attrName, Object rightValue, CommandContext ctx) {

    switch (operator) {
    case OPERATOR_EQ:
      Object newValue = convertResultToDocument(rightValue);
      newValue = convertToPropertyType(doc, attrName, newValue);
      doc.setProperty(attrName.getStringValue(), newValue);
      break;
    case OPERATOR_MINUSASSIGN:
      doc.setProperty(attrName.getStringValue(), calculateNewValue(doc, ctx, MathExpression.Operator.MINUS));
      break;
    case OPERATOR_PLUSASSIGN:
      doc.setProperty(attrName.getStringValue(), calculateNewValue(doc, ctx, MathExpression.Operator.PLUS));
      break;
    case OPERATOR_SLASHASSIGN:
      doc.setProperty(attrName.getStringValue(), calculateNewValue(doc, ctx, MathExpression.Operator.SLASH));
      break;
    case OPERATOR_STARASSIGN:
      doc.setProperty(attrName.getStringValue(), calculateNewValue(doc, ctx, MathExpression.Operator.STAR));
      break;
    }
  }

  private Object convertToPropertyType(final ResultInternal res, final Identifier attrName, Object newValue) {
    final Document doc = res.toElement();
    Optional<DocumentType> optSchema = Optional.ofNullable(doc.getType());
    if (!optSchema.isPresent()) {
      return newValue;
    }

    if (!optSchema.get().existsProperty(attrName.getStringValue()))
      return newValue;

    final Property prop = optSchema.get().getPolymorphicPropertyIfExists(attrName.getStringValue());
    if (prop == null) {
      return newValue;
    }

    if (newValue instanceof Collection) {
      if (prop.getType() == Type.LINK) {
        if (((Collection) newValue).isEmpty()) {
          newValue = null;
        } else if (((Collection) newValue).size() == 1) {
          newValue = ((Collection) newValue).iterator().next();
        } else {
          throw new CommandExecutionException("Cannot assign a collection to a LINK property");
        }
      }
    }
    return newValue;
  }

  private Object convertResultToDocument(Object value) {
    if (value instanceof Result) {
      return ((Result) value).toElement();
    }
    if (value instanceof Identifiable) {
      return value;
    }
    if (value instanceof List && containsOResult((Collection) value)) {
      return ((List) value).stream().map(x -> convertResultToDocument(x)).collect(Collectors.toList());
    }
    if (value instanceof Set && containsOResult((Collection) value)) {
      return ((Set) value).stream().map(x -> convertResultToDocument(x)).collect(Collectors.toSet());
    }
    return value;
  }

  private boolean containsOResult(Collection value) {
    return value.stream().anyMatch(x -> x instanceof Result);
  }

  private Object calculateNewValue(ResultInternal doc, CommandContext ctx, MathExpression.Operator explicitOperator) {
    Expression leftEx = new Expression(left.copy());
    if (leftModifier != null) {
      ((BaseExpression) leftEx.mathExpression).modifier = leftModifier.copy();
    }
    MathExpression mathExp = new MathExpression(-1);
    mathExp.getChildExpressions().add(leftEx.getMathExpression());
    mathExp.getChildExpressions().add(new ParenthesisExpression(right.copy()));
    mathExp.getOperators().add(explicitOperator);
    return mathExp.execute(doc, ctx);
  }

  public Identifier getLeft() {
    return left;
  }

  public void setLeft(Identifier left) {
    this.left = left;
  }

  public Modifier getLeftModifier() {
    return leftModifier;
  }

  public void setLeftModifier(Modifier leftModifier) {
    this.leftModifier = leftModifier;
  }

  public int getOperator() {
    return operator;
  }

  public void setOperator(int operator) {
    this.operator = operator;
  }

  public Expression getRight() {
    return right;
  }

  public void setRight(Expression right) {
    this.right = right;
  }
}
/* JavaCC - OriginalChecksum=df7444be87bba741316df8df0d653600 (do not edit this line) */
