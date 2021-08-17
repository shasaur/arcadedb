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

/* Generated By:JJTree: Do not edit this line. OFetchPlanItem.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=true,NODE_PREFIX=O,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_USERTYPE_VISIBILITY_PUBLIC=true */
package com.arcadedb.query.sql.parser;

import com.arcadedb.query.sql.executor.Result;
import com.arcadedb.query.sql.executor.ResultInternal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FetchPlanItem extends SimpleNode {

  protected Boolean star;
  protected PInteger leftDepth;
  protected boolean leftStar = false;
  protected PInteger rightDepth;
  protected List<String> fieldChain = new ArrayList<String>();

  public FetchPlanItem(int id) {
    super(id);
  }

  public FetchPlanItem(SqlParser p, int id) {
    super(p, id);
  }

  /**
   * Accept the visitor.
   **/
  public Object jjtAccept(SqlParserVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }

  public void toString(Map<Object, Object> params, StringBuilder builder) {
    if (Boolean.TRUE.equals(star)) {
      builder.append("*");
    } else {
      if (leftDepth != null) {
        builder.append("[");
        leftDepth.toString(params, builder);
        builder.append("]");
      } else if (leftStar) {
        builder.append("[*]");
      }

      boolean first = true;
      for (String s : fieldChain) {
        if (!first) {
          builder.append(".");
        }
        builder.append(s);
        first = false;
      }

    }
    builder.append(":");
    rightDepth.toString(params, builder);
  }

  public FetchPlanItem copy() {
    FetchPlanItem result = new FetchPlanItem(-1);
    result.star = star;
    result.leftDepth = leftDepth == null ? null : leftDepth.copy();
    result.leftStar = leftStar;
    result.rightDepth = rightDepth == null ? null : rightDepth.copy();
    result.fieldChain.addAll(fieldChain);
    return result;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;

    FetchPlanItem that = (FetchPlanItem) o;

    if (leftStar != that.leftStar)
      return false;
    if (star != null ? !star.equals(that.star) : that.star != null)
      return false;
    if (leftDepth != null ? !leftDepth.equals(that.leftDepth) : that.leftDepth != null)
      return false;
    if (rightDepth != null ? !rightDepth.equals(that.rightDepth) : that.rightDepth != null)
      return false;
    return fieldChain != null ? fieldChain.equals(that.fieldChain) : that.fieldChain == null;
  }

  @Override
  public int hashCode() {
    int result = star != null ? star.hashCode() : 0;
    result = 31 * result + (leftDepth != null ? leftDepth.hashCode() : 0);
    result = 31 * result + (leftStar ? 1 : 0);
    result = 31 * result + (rightDepth != null ? rightDepth.hashCode() : 0);
    result = 31 * result + (fieldChain != null ? fieldChain.hashCode() : 0);
    return result;
  }

  public Result serialize() {
    ResultInternal result = new ResultInternal();
    result.setProperty("star", star);
    if (leftDepth != null) {
      result.setProperty("leftDepth", leftDepth.serialize());
    }
    result.setProperty("leftStar", leftStar);
    if (rightDepth != null) {
      result.setProperty("rightDepth", rightDepth.serialize());
    }
    if (fieldChain != null) {
      result.setProperty("rightDepth", fieldChain.stream().collect(Collectors.toList()));
    }
    return result;
  }

  public void deserialize(Result fromResult) {
    star = fromResult.getProperty("star");
    if (fromResult.getProperty("leftDepth") != null) {
      leftDepth = new PInteger(-1);
      leftDepth.deserialize(fromResult.getProperty("leftDepth"));
    }
    leftStar = fromResult.getProperty("leftStar");
    if (fromResult.getProperty("rightDepth") != null) {
      rightDepth = new PInteger(-1);
      rightDepth.deserialize(fromResult.getProperty("rightDepth"));
    }
    if (fromResult.getProperty("fieldChain") != null) {
      List<String> ser = fromResult.getProperty("fieldChain");
      fieldChain = new ArrayList<>();
      fieldChain.addAll(ser);
    }
  }
}
/* JavaCC - OriginalChecksum=b7f4c9a97a8f2ca3d85020e054a9ad16 (do not edit this line) */