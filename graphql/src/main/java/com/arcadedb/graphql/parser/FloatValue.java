/* Generated by: JJTree: Do not edit this line. FloatValue.java Version 1.1 */
/* ParserGeneratorCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=true,NODE_PREFIX=,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package com.arcadedb.graphql.parser;

public class FloatValue extends AbstractValue {
  protected String stringValue;

  public FloatValue(int id) {
    super(id);
  }

  public FloatValue(GraphQLParser p, int id) {
    super(p, id);
  }

  @Override
  public Object getValue() {
    return stringValue;
  }

  /**
   * Accept the visitor.
   **/
  public Object jjtAccept(GraphQLParserVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }

  @Override
  public String toString() {
    return "FloatValue{" + stringValue + '}';
  }
}
/* ParserGeneratorCC - OriginalChecksum=6629a12b125a5d138e3a3f27eae0b061 (do not edit this line) */
