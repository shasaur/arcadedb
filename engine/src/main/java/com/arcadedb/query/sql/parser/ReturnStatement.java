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

/* Generated By:JJTree: Do not edit this line. OReturnStatement.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=true,NODE_PREFIX=O,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_USERTYPE_VISIBILITY_PUBLIC=true */
package com.arcadedb.query.sql.parser;

import com.arcadedb.database.Document;
import com.arcadedb.database.Identifiable;
import com.arcadedb.query.sql.executor.CommandContext;
import com.arcadedb.query.sql.executor.InternalResultSet;
import com.arcadedb.query.sql.executor.Result;
import com.arcadedb.query.sql.executor.ResultInternal;
import com.arcadedb.query.sql.executor.ResultSet;

import java.util.*;

public class ReturnStatement extends SimpleExecStatement {
  protected Expression expression;

  public ReturnStatement(int id) {
    super(id);
  }

  public ReturnStatement(SqlParser p, int id) {
    super(p, id);
  }

  @Override
  public ResultSet executeSimple(CommandContext ctx) {
    InternalResultSet rs = new InternalResultSet();

    Object result = expression == null ? null : expression.execute((Result) null, ctx);
    if (result instanceof Result) {
      rs.add((Result) result);
    } else if (result instanceof Identifiable) {
      ResultInternal res = new ResultInternal();
      res.setElement((Document) ((Identifiable) result).getRecord());
      rs.add(res);
    } else if (result instanceof ResultSet) {
      if (!((ResultSet) result).hasNext()) {
        try {
          ((ResultSet) result).reset();
        } catch (UnsupportedOperationException ignore) {
          // just try to reset the RS, in case it was already used during the script execution
          // already
          // You can have two cases here:
          // - a result stored in a LET, that is always resettable, as it's copied
          // - a result from a direct query (eg. RETURN SELECT...), that is new or just empty, so
          // this operation does not hurt
        }
      }
      return (ResultSet) result;
    } else {
      ResultInternal res = new ResultInternal();
      res.setProperty("value", result);
      rs.add(res);
    }
    return rs;
  }

  @Override
  public void toString(Map<Object, Object> params, StringBuilder builder) {
    builder.append("RETURN");
    if (expression != null) {
      builder.append(" ");
      expression.toString(params, builder);
    }
  }

  @Override
  public ReturnStatement copy() {
    ReturnStatement result = new ReturnStatement(-1);
    result.expression = expression == null ? null : expression.copy();
    return result;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;

    ReturnStatement that = (ReturnStatement) o;

    return expression != null ? expression.equals(that.expression) : that.expression == null;
  }

  @Override
  public int hashCode() {
    return expression != null ? expression.hashCode() : 0;
  }
}
/* JavaCC - OriginalChecksum=c72ec860d1fa92cbf52e42ae1c2935c0 (do not edit this line) */
