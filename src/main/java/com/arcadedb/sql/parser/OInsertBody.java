/* Generated By:JJTree: Do not edit this line. OInsertBody.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=true,NODE_PREFIX=O,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package com.arcadedb.sql.parser;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OInsertBody extends SimpleNode {

  protected List<OIdentifier>          identifierList;
  protected List<List<OExpression>>    valueExpressions;
  protected List<OInsertSetExpression> setExpressions;

  protected OJson            content;


  public OInsertBody(int id) {
    super(id);
  }

  public OInsertBody(OrientSql p, int id) {
    super(p, id);
  }

  /**
   * Accept the visitor.
   **/
  public Object jjtAccept(OrientSqlVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }

  public void toString(Map<Object, Object> params, StringBuilder builder) {

    if (identifierList != null) {
      builder.append("(");
      boolean first = true;
      for (OIdentifier item : identifierList) {
        if (!first) {
          builder.append(", ");
        }
        item.toString(params, builder);
        first = false;
      }
      builder.append(") VALUES ");
      if (valueExpressions != null) {
        boolean firstList = true;
        for (List<OExpression> itemList : valueExpressions) {
          if (firstList) {
            builder.append("(");
          } else {
            builder.append("),(");
          }
          first = true;
          for (OExpression item : itemList) {
            if (!first) {
              builder.append(", ");
            }
            item.toString(params, builder);
            first = false;
          }
          firstList = false;
        }
      }
      builder.append(")");

    }

    if (setExpressions != null) {
      builder.append("SET ");
      boolean first = true;
      for (OInsertSetExpression item : setExpressions) {
        if (!first) {
          builder.append(", ");
        }
        item.toString(params, builder);
        first = false;
      }
    }

    if (content != null) {
      builder.append("CONTENT ");
      content.toString(params, builder);
    }

  }

  public OInsertBody copy() {
    OInsertBody result = new OInsertBody(-1);
    result.identifierList = identifierList == null ? null : identifierList.stream().map(x -> x.copy()).collect(Collectors.toList());
    result.valueExpressions = valueExpressions == null ?
        null :
        valueExpressions.stream().map(sub -> sub.stream().map(x -> x.copy()).collect(Collectors.toList()))
            .collect(Collectors.toList());
    result.setExpressions = setExpressions == null ? null : setExpressions.stream().map(x -> x.copy()).collect(Collectors.toList());
    result.content = content == null ? null : content.copy();
    return result;
  }

  @Override public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;

    OInsertBody that = (OInsertBody) o;

    if (identifierList != null ? !identifierList.equals(that.identifierList) : that.identifierList != null)
      return false;
    if (valueExpressions != null ? !valueExpressions.equals(that.valueExpressions) : that.valueExpressions != null)
      return false;
    if (setExpressions != null ? !setExpressions.equals(that.setExpressions) : that.setExpressions != null)
      return false;
    if (content != null ? !content.equals(that.content) : that.content != null)
      return false;

    return true;
  }

  @Override public int hashCode() {
    int result = identifierList != null ? identifierList.hashCode() : 0;
    result = 31 * result + (valueExpressions != null ? valueExpressions.hashCode() : 0);
    result = 31 * result + (setExpressions != null ? setExpressions.hashCode() : 0);
    result = 31 * result + (content != null ? content.hashCode() : 0);
    return result;
  }

  public List<OIdentifier> getIdentifierList() {
    return identifierList;
  }

  public List<List<OExpression>> getValueExpressions() {
    return valueExpressions;
  }

  public List<OInsertSetExpression> getSetExpressions() {
    return setExpressions;
  }

  public OJson getContent() {
    return content;
  }
}
/* JavaCC - OriginalChecksum=7d2079a41a1fc63a812cb679e729b23a (do not edit this line) */
