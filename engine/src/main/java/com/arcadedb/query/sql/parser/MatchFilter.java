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

/* Generated By:JJTree: Do not edit this line. OMatchFilter.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=true,NODE_PREFIX=O,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_USERTYPE_VISIBILITY_PUBLIC=true */
package com.arcadedb.query.sql.parser;

import com.arcadedb.query.sql.executor.CommandContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MatchFilter extends SimpleNode {
  // TODO transform in a map
  protected List<MatchFilterItem> items = new ArrayList<MatchFilterItem>();

  public MatchFilter(int id) {
    super(id);
  }

  public MatchFilter(SqlParser p, int id) {
    super(p, id);
  }

  public String getAlias() {
    for (MatchFilterItem item : items) {
      if (item.alias != null) {
        return item.alias.getStringValue();
      }
    }
    return null;
  }

  public void setAlias(String alias) {
    boolean found = false;
    for (MatchFilterItem item : items) {
      if (item.alias != null) {
        item.alias = new Identifier(alias);
        found = true;
        break;
      }
    }
    if (!found) {
      MatchFilterItem newItem = new MatchFilterItem(-1);
      newItem.alias = new Identifier(alias);
      items.add(newItem);
    }
  }

  public WhereClause getFilter() {
    for (MatchFilterItem item : items) {
      if (item.filter != null) {
        return item.filter;
      }
    }
    return null;
  }

  public void setFilter(WhereClause filter) {
    boolean found = false;
    for (MatchFilterItem item : items) {
      if (item.filter != null) {
        item.filter = filter;
        found = true;
        break;
      }
    }
    if (!found) {
      MatchFilterItem newItem = new MatchFilterItem(-1);
      newItem.filter = filter;
      items.add(newItem);
    }
  }

  public WhereClause getWhileCondition() {
    for (MatchFilterItem item : items) {
      if (item.whileCondition != null) {
        return item.whileCondition;
      }
    }
    return null;
  }

  public String getTypeName(CommandContext context) {
    for (MatchFilterItem item : items) {
      if (item.typeName != null) {
        if (item.typeName.value instanceof String)
          return (String) item.typeName.value;
        else if (item.typeName.value instanceof SimpleNode) {
          StringBuilder builder = new StringBuilder();

          ((SimpleNode) item.typeName.value).toString(context == null ? null : context.getInputParameters(), builder);
          return builder.toString();
        } else if (item.typeName.isBaseIdentifier()) {
          return item.typeName.getDefaultAlias().getStringValue();
        } else {
          return item.typeName.toString();
        }
      }
    }
    return null;
  }

  public String getBucketName(CommandContext context) {
    for (MatchFilterItem item : items) {
      if (item.bucketName != null) {
        return item.bucketName.getStringValue();
      } else if (item.bucketId != null) {
        int cid = item.bucketId.value.intValue();
        String bucketName = context.getDatabase().getSchema().getBucketById(cid).getName();
        if (bucketName != null) {
          return bucketName;
        }
      }
    }
    return null;
  }

  public Rid getRid(CommandContext context) {
    for (MatchFilterItem item : items) {
      if (item.rid != null) {
        return item.rid;
      }
    }
    return null;
  }

  public Integer getMaxDepth() {
    for (MatchFilterItem item : items) {
      if (item.maxDepth != null) {
        return item.maxDepth.value.intValue();
      }
    }
    return null;
  }

  public boolean isOptional() {
    for (MatchFilterItem item : items) {
      if (Boolean.TRUE.equals(item.optional)) {
        return true;
      }
    }
    return false;
  }

  public String getDepthAlias() {
    for (MatchFilterItem item : items) {
      if (item.depthAlias != null) {
        return item.depthAlias.getStringValue();
      }
    }
    return null;
  }

  public String getPathAlias() {
    for (MatchFilterItem item : items) {
      if (item.pathAlias != null) {
        return item.pathAlias.getStringValue();
      }
    }
    return null;
  }

  public void toString(Map<Object, Object> params, StringBuilder builder) {
    builder.append("{");
    boolean first = true;
    for (MatchFilterItem item : items) {
      if (!first) {
        builder.append(", ");
      }
      item.toString(params, builder);
      first = false;
    }
    builder.append("}");
  }

  @Override
  public MatchFilter copy() {
    MatchFilter result = new MatchFilter(-1);
    result.items = items == null ? null : items.stream().map(x -> x.copy()).collect(Collectors.toList());
    return result;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;

    MatchFilter that = (MatchFilter) o;

    return items != null ? items.equals(that.items) : that.items == null;
  }

  @Override
  public int hashCode() {
    return items != null ? items.hashCode() : 0;
  }

}
/* JavaCC - OriginalChecksum=6b099371c69e0d0c1c106fc96b3072de (do not edit this line) */