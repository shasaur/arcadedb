/*
 * Copyright (c) 2018 - Arcade Analytics LTD (https://arcadeanalytics.com)
 */

/* Generated By:JJTree: Do not edit this line. OMatchFilterItem.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=true,NODE_PREFIX=O,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package com.arcadedb.sql.parser;

import java.util.Map;

public class MatchFilterItem extends SimpleNode {
  protected Expression         className;
  protected Expression         classNames;
  protected Identifier         clusterName;
  protected PInteger            clusterId;
  protected Rid                rid;
  protected Identifier         alias;
  protected WhereClause        filter;
  protected WhereClause        whileCondition;
  protected ArrayRangeSelector depth;
  protected PInteger            maxDepth;
  protected Boolean            optional;
  protected Identifier         depthAlias;
  protected Identifier         pathAlias;

  public MatchFilterItem(int id) {
    super(id);
  }

  public MatchFilterItem(SqlParser p, int id) {
    super(p, id);
  }

  /**
   * Accept the visitor.
   **/
  public Object jjtAccept(SqlParserVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }

  public void toString(Map<Object, Object> params, StringBuilder builder) {
    if (className != null) {
      builder.append("class: ");
      className.toString(params, builder);
      return;
    }
    if (classNames != null) {
      builder.append("classes: ");
      classNames.toString(params, builder);
      return;
    }
    if (clusterName != null) {
      builder.append("cluster: ");
      clusterName.toString(params, builder);
      return;
    }
    if (clusterId != null) {
      builder.append("cluster: ");
      clusterId.toString(params, builder);
      return;
    }
    if (rid != null) {
      builder.append("rid: ");
      rid.toString(params, builder);
      return;
    }

    if (alias != null) {
      builder.append("as: ");
      alias.toString(params, builder);
      return;
    }

    if (maxDepth != null) {
      builder.append("maxdepth: ");
      maxDepth.toString(params, builder);
      return;
    }

    if (filter != null) {
      builder.append("where: (");
      filter.toString(params, builder);
      builder.append(")");
      return;
    }

    if (whileCondition != null) {
      builder.append("while: (");
      whileCondition.toString(params, builder);
      builder.append(")");
      return;
    }

    if (optional != null) {
      builder.append("optional: ");
      builder.append(optional);
      return;
    }

    if (depthAlias != null) {
      builder.append("depthAlias: ");
      depthAlias.toString(params, builder);
      return;
    }

    if (pathAlias != null) {
      builder.append("pathAlias: ");
      pathAlias.toString(params, builder);
      return;
    }

  }

  @Override
  public MatchFilterItem copy() {
    MatchFilterItem result = new MatchFilterItem(-1);
    result.className = className == null ? null : className.copy();
    result.classNames = classNames == null ? null : classNames.copy();
    result.clusterName = clusterName == null ? null : clusterName.copy();
    result.clusterId = clusterId == null ? null : clusterId.copy();
    result.rid = rid == null ? null : rid.copy();
    result.alias = alias == null ? null : alias.copy();
    result.filter = filter == null ? null : filter.copy();
    result.whileCondition = whileCondition == null ? null : whileCondition.copy();
    result.depth = depth == null ? null : depth.copy();
    result.maxDepth = maxDepth == null ? null : maxDepth.copy();
    result.optional = optional;
    result.depthAlias = depthAlias == null ? null : depthAlias.copy();
    result.pathAlias = pathAlias == null ? null : pathAlias.copy();
    return result;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;

    MatchFilterItem that = (MatchFilterItem) o;

    if (className != null ? !className.equals(that.className) : that.className != null)
      return false;
    if (classNames != null ? !classNames.equals(that.classNames) : that.classNames != null)
      return false;
    if (clusterName != null ? !clusterName.equals(that.clusterName) : that.clusterName != null)
      return false;
    if (clusterId != null ? !clusterId.equals(that.clusterId) : that.clusterId != null)
      return false;
    if (rid != null ? !rid.equals(that.rid) : that.rid != null)
      return false;
    if (alias != null ? !alias.equals(that.alias) : that.alias != null)
      return false;
    if (filter != null ? !filter.equals(that.filter) : that.filter != null)
      return false;
    if (whileCondition != null ? !whileCondition.equals(that.whileCondition) : that.whileCondition != null)
      return false;
    if (depth != null ? !depth.equals(that.depth) : that.depth != null)
      return false;
    if (maxDepth != null ? !maxDepth.equals(that.maxDepth) : that.maxDepth != null)
      return false;
    if (optional != null ? !optional.equals(that.optional) : that.optional != null)
      return false;
    if (depthAlias != null ? !depthAlias.equals(that.depthAlias) : that.depthAlias != null)
      return false;
    return pathAlias != null ? pathAlias.equals(that.pathAlias) : that.pathAlias == null;
  }

  @Override
  public int hashCode() {
    int result = className != null ? className.hashCode() : 0;
    result = 31 * result + (classNames != null ? classNames.hashCode() : 0);
    result = 31 * result + (clusterName != null ? clusterName.hashCode() : 0);
    result = 31 * result + (clusterId != null ? clusterId.hashCode() : 0);
    result = 31 * result + (rid != null ? rid.hashCode() : 0);
    result = 31 * result + (alias != null ? alias.hashCode() : 0);
    result = 31 * result + (filter != null ? filter.hashCode() : 0);
    result = 31 * result + (whileCondition != null ? whileCondition.hashCode() : 0);
    result = 31 * result + (depth != null ? depth.hashCode() : 0);
    result = 31 * result + (maxDepth != null ? maxDepth.hashCode() : 0);
    result = 31 * result + (optional != null ? optional.hashCode() : 0);
    result = 31 * result + (depthAlias != null ? depthAlias.hashCode() : 0);
    result = 31 * result + (pathAlias != null ? pathAlias.hashCode() : 0);
    return result;
  }
}
/* JavaCC - OriginalChecksum=74bf4765509f102180cac29f2295031e (do not edit this line) */
