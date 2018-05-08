/*
 * Copyright (c) 2018 - Arcade Analytics LTD (https://arcadeanalytics.com)
 */

/* Generated By:JJTree: Do not edit this line. OFromItem.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=true,NODE_PREFIX=O,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package com.arcadedb.sql.parser;

import com.arcadedb.sql.executor.Result;
import com.arcadedb.sql.executor.ResultInternal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FromItem extends SimpleNode {

  protected List<Rid>            rids;
  protected List<InputParameter> inputParams;
  protected Cluster              cluster;
  protected ClusterList          clusterList;
  protected IndexIdentifier      index;
  protected MetadataIdentifier   metadata;
  protected Statement            statement;
  protected InputParameter       inputParam;
  protected Identifier           identifier;
  protected FunctionCall         functionCall;
  protected Modifier             modifier;

  public FromItem(int id) {
    super(id);
  }

  public FromItem(SqlParser p, int id) {
    super(p, id);
  }

  /**
   * Accept the visitor.
   **/
  public Object jjtAccept(SqlParserVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }

  public void toString(Map<Object, Object> params, StringBuilder builder) {
    if (rids != null && rids.size() > 0) {
      if (rids.size() == 1) {
        rids.get(0).toString(params, builder);
        return;
      } else {
        builder.append("[");
        boolean first = true;
        for (Rid rid : rids) {
          if (!first) {
            builder.append(", ");
          }
          rid.toString(params, builder);
          first = false;
        }
        builder.append("]");
        return;
      }
    } else if (inputParams != null && inputParams.size() > 0) {
      if (inputParams.size() == 1) {
        inputParams.get(0).toString(params, builder);
        return;
      } else {
        builder.append("[");
        boolean first = true;
        for (InputParameter rid : inputParams) {
          if (!first) {
            builder.append(", ");
          }
          rid.toString(params, builder);
          first = false;
        }
        builder.append("]");
        return;
      }
    } else if (cluster != null) {
      cluster.toString(params, builder);
      return;
      // } else if (className != null) {
      // return className.getValue();
    } else if (clusterList != null) {
      clusterList.toString(params, builder);
      return;
    } else if (metadata != null) {
      metadata.toString(params, builder);
      return;
    } else if (statement != null) {
      builder.append("(");
      statement.toString(params, builder);
      builder.append(")");
      return;
    } else if (index != null) {
      index.toString(params, builder);
      return;
    } else if (inputParam != null) {
      inputParam.toString(params, builder);
    } else if (functionCall != null) {
      functionCall.toString(params, builder);
    } else if (identifier != null) {
      identifier.toString(params, builder);
    }
    if (modifier != null) {
      modifier.toString(params, builder);
    }
  }

  public Identifier getIdentifier() {
    return identifier;
  }

  public List<Rid> getRids() {
    return rids;
  }

  public Cluster getCluster() {
    return cluster;
  }

  public ClusterList getClusterList() {
    return clusterList;
  }

  public IndexIdentifier getIndex() {
    return index;
  }

  public MetadataIdentifier getMetadata() {
    return metadata;
  }

  public Statement getStatement() {
    return statement;
  }

  public InputParameter getInputParam() {
    return inputParam;
  }

  public List<InputParameter> getInputParams() {
    return inputParams;
  }

  public FunctionCall getFunctionCall() {
    return functionCall;
  }

  public Modifier getModifier() {
    return modifier;
  }

  public FromItem copy() {
    FromItem result = new FromItem(-1);
    if (rids != null) {
      result.rids = rids.stream().map(r -> r.copy()).collect(Collectors.toList());
    }
    if (inputParams != null) {
      result.inputParams = inputParams.stream().map(r -> r.copy()).collect(Collectors.toList());
    }
    result.cluster = cluster == null ? null : cluster.copy();
    result.clusterList = clusterList == null ? null : clusterList.copy();
    result.index = index == null ? null : index.copy();
    result.metadata = metadata == null ? null : metadata.copy();
    result.statement = statement == null ? null : statement.copy();
    result.inputParam = inputParam == null ? null : inputParam.copy();
    result.identifier = identifier == null ? null : identifier.copy();
    result.functionCall = functionCall == null ? null : functionCall.copy();
    result.modifier = modifier == null ? null : modifier.copy();

    return result;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;

    FromItem oFromItem = (FromItem) o;

    if (rids != null ? !rids.equals(oFromItem.rids) : oFromItem.rids != null)
      return false;
    if (inputParams != null ? !inputParams.equals(oFromItem.inputParams) : oFromItem.inputParams != null)
      return false;
    if (cluster != null ? !cluster.equals(oFromItem.cluster) : oFromItem.cluster != null)
      return false;
    if (clusterList != null ? !clusterList.equals(oFromItem.clusterList) : oFromItem.clusterList != null)
      return false;
    if (index != null ? !index.equals(oFromItem.index) : oFromItem.index != null)
      return false;
    if (metadata != null ? !metadata.equals(oFromItem.metadata) : oFromItem.metadata != null)
      return false;
    if (statement != null ? !statement.equals(oFromItem.statement) : oFromItem.statement != null)
      return false;
    if (inputParam != null ? !inputParam.equals(oFromItem.inputParam) : oFromItem.inputParam != null)
      return false;
    if (identifier != null ? !identifier.equals(oFromItem.identifier) : oFromItem.identifier != null)
      return false;
    if (functionCall != null ? !functionCall.equals(oFromItem.functionCall) : oFromItem.functionCall != null)
      return false;
    if (modifier != null ? !modifier.equals(oFromItem.modifier) : oFromItem.modifier != null)
      return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = rids != null ? rids.hashCode() : 0;
    result = 31 * result + (inputParams != null ? inputParams.hashCode() : 0);
    result = 31 * result + (cluster != null ? cluster.hashCode() : 0);
    result = 31 * result + (clusterList != null ? clusterList.hashCode() : 0);
    result = 31 * result + (index != null ? index.hashCode() : 0);
    result = 31 * result + (metadata != null ? metadata.hashCode() : 0);
    result = 31 * result + (statement != null ? statement.hashCode() : 0);
    result = 31 * result + (inputParam != null ? inputParam.hashCode() : 0);
    result = 31 * result + (identifier != null ? identifier.hashCode() : 0);
    result = 31 * result + (functionCall != null ? functionCall.hashCode() : 0);
    result = 31 * result + (modifier != null ? modifier.hashCode() : 0);
    return result;
  }

  public void setRids(List<Rid> rids) {
    this.rids = rids;
  }

  public void setCluster(Cluster cluster) {
    this.cluster = cluster;
  }

  public void setClusterList(ClusterList clusterList) {
    this.clusterList = clusterList;
  }

  public void setIndex(IndexIdentifier index) {
    this.index = index;
  }

  public void setMetadata(MetadataIdentifier metadata) {
    this.metadata = metadata;
  }

  public void setStatement(Statement statement) {
    this.statement = statement;
  }

  public void setInputParam(InputParameter inputParam) {
    this.inputParam = inputParam;
  }

  public void setIdentifier(Identifier identifier) {
    this.identifier = identifier;
  }

  public void setFunctionCall(FunctionCall functionCall) {
    this.functionCall = functionCall;
  }

  public void setModifier(Modifier modifier) {
    this.modifier = modifier;
  }

  public void setInputParams(List<InputParameter> inputParams) {
    this.inputParams = inputParams;
  }

  public Result serialize() {
    ResultInternal result = new ResultInternal();
    if (rids != null) {
      result.setProperty("rids", rids.stream().map(x -> x.serialize()).collect(Collectors.toList()));
    }
    if (inputParams != null) {
      result.setProperty("inputParams", rids.stream().map(x -> x.serialize()).collect(Collectors.toList()));
    }
    if (cluster != null) {
      result.setProperty("cluster", cluster.serialize());
    }
    if (clusterList != null) {
      result.setProperty("clusterList", clusterList.serialize());
    }
    if (index != null) {
      result.setProperty("index", index.serialize());
    }
    if (metadata != null) {
      result.setProperty("metadata", metadata.serialize());
    }
    if (statement != null) {
      result.setProperty("statement", statement.serialize());
    }
    if (inputParam != null) {
      result.setProperty("inputParam", inputParam.serialize());
    }
    if (identifier != null) {
      result.setProperty("identifier", identifier.serialize());
    }
    if (functionCall != null) {
      result.setProperty("functionCall", functionCall.serialize());
    }
    if (modifier != null) {
      result.setProperty("modifier", modifier.serialize());
    }

    return result;
  }

  public void deserialize(Result fromResult) {
    if (fromResult.getProperty("rids") != null) {
      List<Result> serRids = fromResult.getProperty("rids");
      rids = new ArrayList<>();
      for (Result res : serRids) {
        Rid rid = new Rid(-1);
        rid.deserialize(res);
        rids.add(rid);
      }
    }

    if (fromResult.getProperty("inputParams") != null) {
      List<Result> ser = fromResult.getProperty("inputParams");
      inputParams = new ArrayList<>();
      for (Result res : ser) {
        inputParams.add(InputParameter.deserializeFromOResult(res));
      }
    }

    if (fromResult.getProperty("cluster") != null) {
      cluster = new Cluster(-1);
      cluster.deserialize(fromResult.getProperty("cluster"));
    }
    if (fromResult.getProperty("clusterList") != null) {
      clusterList = new ClusterList(-1);
      clusterList.deserialize(fromResult.getProperty("clusterList"));
    }

    if (fromResult.getProperty("index") != null) {
      index = new IndexIdentifier(-1);
      index.deserialize(fromResult.getProperty("index"));
    }
    if (fromResult.getProperty("metadata") != null) {
      metadata = new MetadataIdentifier(-1);
      metadata.deserialize(fromResult.getProperty("metadata"));
    }
    if (fromResult.getProperty("statement") != null) {
      statement = Statement.deserializeFromOResult(fromResult.getProperty("statement"));
    }
    if (fromResult.getProperty("inputParam") != null) {
      inputParam = InputParameter.deserializeFromOResult(fromResult.getProperty("inputParam"));
    }
    if (fromResult.getProperty("identifier") != null) {
      identifier = new Identifier(-1);
      identifier.deserialize(fromResult.getProperty("identifier"));
    }
    if (fromResult.getProperty("functionCall") != null) {
      functionCall = new FunctionCall(-1);
      functionCall.deserialize(fromResult.getProperty("functionCall"));
    }
    if (fromResult.getProperty("modifier") != null) {
      modifier = new Modifier(-1);
      modifier.deserialize(fromResult.getProperty("modifier"));
    }
  }

  public boolean isCacheable() {
    if (modifier != null) {
      return false;
    }
    if (inputParam != null) {
      return false;
    }
    if (inputParams != null && !inputParams.isEmpty()) {
      return false;
    }
    if (statement != null) {
      return statement.executinPlanCanBeCached();
    }
    if(functionCall!=null){
      return functionCall.isCacheable();
    }

    return true;
  }
}
/* JavaCC - OriginalChecksum=f64e3b4d2a2627a1b5d04a7dcb95fa94 (do not edit this line) */
