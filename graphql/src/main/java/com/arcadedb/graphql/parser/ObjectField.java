/* Generated by: JJTree: Do not edit this line. ObjectField.java Version 1.1 */
/* ParserGeneratorCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=true,NODE_PREFIX=,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package com.arcadedb.graphql.parser;

public class ObjectField extends SimpleNode {
  protected Name  name;
  protected Value value;

  public ObjectField(int id) {
    super(id);
  }

  public ObjectField(GraphQLParser p, int id) {
    super(p, id);
  }

  public String getName() {
    return name != null ? name.value : null;
  }

  /**
   * Accept the visitor.
   **/
  public Object jjtAccept(GraphQLParserVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
/* ParserGeneratorCC - OriginalChecksum=9d5773c7ec0ad7d266c683e3b2a33f31 (do not edit this line) */
