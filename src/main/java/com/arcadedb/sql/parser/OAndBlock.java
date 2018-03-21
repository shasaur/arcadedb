/* Generated By:JJTree: Do not edit this line. OAndBlock.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=true,NODE_PREFIX=O,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package com.arcadedb.sql.parser;

import com.orientechnologies.orient.core.command.OCommandContext;
import com.orientechnologies.orient.core.db.ODatabaseDocumentInternal;
import com.orientechnologies.orient.core.db.record.OIdentifiable;
import com.orientechnologies.orient.core.metadata.schema.OClass;
import com.orientechnologies.orient.core.sql.executor.OResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class OAndBlock extends OBooleanExpression {
  List<OBooleanExpression> subBlocks = new ArrayList<OBooleanExpression>();

  public OAndBlock(int id) {
    super(id);
  }

  public OAndBlock(OrientSql p, int id) {
    super(p, id);
  }

  @Override
  public boolean evaluate(OIdentifiable currentRecord, OCommandContext ctx) {
    if (getSubBlocks() == null) {
      return true;
    }

    for (OBooleanExpression block : subBlocks) {
      if (!block.evaluate(currentRecord, ctx)) {
        return false;
      }
    }
    return true;
  }

  @Override
  public boolean evaluate(OResult currentRecord, OCommandContext ctx) {
    if (getSubBlocks() == null) {
      return true;
    }

    for (OBooleanExpression block : subBlocks) {
      if (!block.evaluate(currentRecord, ctx)) {
        return false;
      }
    }
    return true;
  }

  public List<OBooleanExpression> getSubBlocks() {
    return subBlocks;
  }

  public void setSubBlocks(List<OBooleanExpression> subBlocks) {
    this.subBlocks = subBlocks;
  }

  public void toString(Map<Object, Object> params, StringBuilder builder) {
    if (subBlocks == null || subBlocks.size() == 0) {
      return;
    }
    // if (subBlocks.size() == 1) {
    // subBlocks.get(0).toString(params, builder);
    // }

    boolean first = true;
    for (OBooleanExpression expr : subBlocks) {
      if (!first) {
        builder.append(" AND ");
      }
      expr.toString(params, builder);
      first = false;
    }
  }

  @Override
  protected boolean supportsBasicCalculation() {
    for (OBooleanExpression expr : subBlocks) {
      if (!expr.supportsBasicCalculation()) {
        return false;
      }
    }
    return true;
  }

  @Override
  protected int getNumberOfExternalCalculations() {
    int result = 0;
    for (OBooleanExpression expr : subBlocks) {
      result += expr.getNumberOfExternalCalculations();
    }
    return result;
  }

  @Override
  protected List<Object> getExternalCalculationConditions() {
    List<Object> result = new ArrayList<Object>();
    for (OBooleanExpression expr : subBlocks) {
      result.addAll(expr.getExternalCalculationConditions());
    }
    return result;
  }

  public List<OBinaryCondition> getIndexedFunctionConditions(OClass iSchemaClass, ODatabaseDocumentInternal database) {
    if (subBlocks == null) {
      return null;
    }
    List<OBinaryCondition> result = new ArrayList<OBinaryCondition>();
    for (OBooleanExpression exp : subBlocks) {
      List<OBinaryCondition> sub = exp.getIndexedFunctionConditions(iSchemaClass, database);
      if (sub != null && sub.size() > 0) {
        result.addAll(sub);
      }
    }
    return result.size() == 0 ? null : result;
  }

  public List<OAndBlock> flatten() {
    List<OAndBlock> result = new ArrayList<OAndBlock>();
    boolean first = true;
    for (OBooleanExpression sub : subBlocks) {
      List<OAndBlock> subFlattened = sub.flatten();
      List<OAndBlock> oldResult = result;
      result = new ArrayList<OAndBlock>();
      for (OAndBlock subAndItem : subFlattened) {
        if (first) {
          result.add(subAndItem);
        } else {
          ;
          for (OAndBlock oldResultItem : oldResult) {
            OAndBlock block = new OAndBlock(-1);
            block.subBlocks.addAll(oldResultItem.subBlocks);
            for (OBooleanExpression resultItem : subAndItem.subBlocks) {
              block.subBlocks.add(resultItem);
            }
            result.add(block);
          }
        }
      }
      first = false;
    }
    return result;
  }

  protected OAndBlock encapsulateInAndBlock(OBooleanExpression item) {
    if (item instanceof OAndBlock) {
      return (OAndBlock) item;
    }
    OAndBlock result = new OAndBlock(-1);
    result.subBlocks.add(item);
    return result;
  }

  @Override
  public boolean needsAliases(Set<String> aliases) {
    for (OBooleanExpression block : subBlocks) {
      if (block.needsAliases(aliases)) {
        return true;
      }
    }
    return false;
  }

  public OAndBlock copy() {
    OAndBlock result = new OAndBlock(-1);
    for (OBooleanExpression exp : subBlocks) {
      result.subBlocks.add(exp.copy());
    }
    return result;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;

    OAndBlock andBlock = (OAndBlock) o;

    if (subBlocks != null ? !subBlocks.equals(andBlock.subBlocks) : andBlock.subBlocks != null)
      return false;

    return true;
  }

  @Override
  public int hashCode() {
    return subBlocks != null ? subBlocks.hashCode() : 0;
  }

  @Override
  public boolean isEmpty() {
    if (subBlocks.isEmpty()) {
      return true;
    }
    for (OBooleanExpression block : subBlocks) {
      if (!block.isEmpty()) {
        return false;
      }
    }
    return true;
  }

  @Override
  public void extractSubQueries(SubQueryCollector collector) {
    for (OBooleanExpression exp : subBlocks) {
      exp.extractSubQueries(collector);
    }
  }

  @Override
  public boolean refersToParent() {
    for (OBooleanExpression exp : subBlocks) {
      if (exp.refersToParent()) {
        return true;
      }
    }
    return false;
  }

  @Override
  public List<String> getMatchPatternInvolvedAliases() {
    List<String> result = new ArrayList<String>();
    for (OBooleanExpression exp : subBlocks) {
      List<String> x = exp.getMatchPatternInvolvedAliases();
      if (x != null) {
        result.addAll(x);
      }
    }
    return result.size() == 0 ? null : result;
  }

  @Override
  public void translateLuceneOperator() {
    subBlocks.forEach(x -> x.translateLuceneOperator());
  }

  @Override
  public boolean isCacheable() {
    for (OBooleanExpression exp : subBlocks) {
      if (!exp.isCacheable()) {
        return false;
      }
    }
    return true;
  }

}
/* JavaCC - OriginalChecksum=cf1f66cc86cfc93d357f9fcdfa4a4604 (do not edit this line) */
