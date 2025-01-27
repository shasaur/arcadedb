/*
 * Copyright © 2021-present Arcade Data Ltd (info@arcadedata.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * SPDX-FileCopyrightText: 2021-present Arcade Data Ltd (info@arcadedata.com)
 * SPDX-License-Identifier: Apache-2.0
 */
/* Generated By:JJTree: Do not edit this line. OSuffixIdentifier.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=true,NODE_PREFIX=O,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_USERTYPE_VISIBILITY_PUBLIC=true */
package com.arcadedb.query.sql.parser;

import com.arcadedb.database.Document;
import com.arcadedb.database.Identifiable;
import com.arcadedb.database.MutableDocument;
import com.arcadedb.database.Record;
import com.arcadedb.exception.CommandExecutionException;
import com.arcadedb.graph.Edge;
import com.arcadedb.graph.Vertex;
import com.arcadedb.query.sql.executor.AggregationContext;
import com.arcadedb.query.sql.executor.CommandContext;
import com.arcadedb.query.sql.executor.Result;
import com.arcadedb.query.sql.executor.ResultInternal;
import com.arcadedb.query.sql.executor.ResultSet;

import java.util.*;

public class SuffixIdentifier extends SimpleNode {
  protected Identifier      identifier;
  protected RecordAttribute recordAttribute;
  protected boolean         star = false;

  public SuffixIdentifier(final int id) {
    super(id);
  }

  public SuffixIdentifier(final Identifier identifier) {
    this.identifier = identifier;
  }

  public SuffixIdentifier(final RecordAttribute attr) {
    this.recordAttribute = attr;
  }

  public void toString(final Map<String, Object> params, final StringBuilder builder) {
    if (identifier != null) {
      identifier.toString(params, builder);
    } else if (recordAttribute != null) {
      recordAttribute.toString(params, builder);
    } else if (star) {
      builder.append("*");
    }
  }

  public Object execute(final Identifiable iCurrentRecord, final CommandContext context) {
    if (star) {
      return iCurrentRecord;
    }
    if (identifier != null) {
      final String varName = identifier.getStringValue();
      if (context != null && context.getVariable(varName) != null) {
        return context.getVariable(varName);
      }

      if (iCurrentRecord != null) {
        return ((Document) iCurrentRecord.getRecord()).get(varName);
      }
      return null;
    }
    if (recordAttribute != null) {
      if ("@rid".equalsIgnoreCase(recordAttribute.name))
        return iCurrentRecord.getIdentity();
      else if ("@type".equalsIgnoreCase(recordAttribute.name))
        return iCurrentRecord.asDocument().getTypeName();
      else if ("@cat".equalsIgnoreCase(recordAttribute.name)) {
        final Document doc = iCurrentRecord.asDocument();
        if (doc instanceof Vertex)
          return "v";
        else if (doc instanceof Edge)
          return "e";
        return "d";
      }

      return ((Document) iCurrentRecord.getRecord()).get(recordAttribute.name);
    }
    return null;
  }

  public Object execute(final Result iCurrentRecord, final CommandContext context) {
    if (star) {
      return iCurrentRecord;
    }
    if (identifier != null) {
      final String varName = identifier.getStringValue();
      if (context != null && varName.equalsIgnoreCase("$parent")) {
        return context.getParent();
      }
      if (context != null && (varName.startsWith("$") || varName.startsWith("_$$$")) && context.getVariable(varName) != null) {
        final Object result = context.getVariable(varName);
        return result;
      }
      if (iCurrentRecord != null) {
        if (iCurrentRecord.hasProperty(varName)) {
          return iCurrentRecord.getProperty(varName);
        }
        if (iCurrentRecord.getMetadataKeys().contains(varName)) {
          return iCurrentRecord.getMetadata(varName);
        }
        if (iCurrentRecord instanceof ResultInternal && ((ResultInternal) iCurrentRecord).getTemporaryProperties().contains(varName)) {
          return ((ResultInternal) iCurrentRecord).getTemporaryProperty(varName);
        }
      }
      return null;
    }

    if (iCurrentRecord != null && recordAttribute != null) {
      return recordAttribute.evaluate(iCurrentRecord, context);
    }

    return null;
  }

  public Object execute(final Map iCurrentRecord, final CommandContext context) {
    if (star) {
      final ResultInternal result = new ResultInternal();
      if (iCurrentRecord != null) {
        for (final Map.Entry<String, Object> x : ((Map<String, Object>) iCurrentRecord).entrySet()) {
          result.setProperty("" + x.getKey(), x.getValue());
        }
        return result;
      }
      return iCurrentRecord;
    }
    if (identifier != null) {
      final String varName = identifier.getStringValue();
      if (context != null && varName.equalsIgnoreCase("$parent")) {
        return context.getParent();
      }
      if (context != null && context.getVariable(varName) != null) {
        return context.getVariable(varName);
      }
      if (iCurrentRecord != null) {
        return iCurrentRecord.get(varName);
      }
      return null;
    }
    if (recordAttribute != null) {
      return iCurrentRecord.get(recordAttribute.name);
    }
    return null;
  }

  public Object execute(final Iterable iterable, final CommandContext context) {
    if (star) {
      return null;
    }
    final List<Object> result = new ArrayList<>();
    for (final Object o : iterable) {
      result.add(execute(o, context));
    }
    return result;
  }

  public Object execute(final Iterator iterator, final CommandContext context) {
    if (star) {
      return null;
    }
    final List<Object> result = new ArrayList<>();
    while (iterator.hasNext()) {
      result.add(execute(iterator.next(), context));
    }
    if (iterator instanceof ResultSet) {
      try {
        ((ResultSet) iterator).reset();
      } catch (final Exception ignore) {
      }
    }
    return result;
  }

  public Object execute(final CommandContext iCurrentRecord) {
    if (star)
      return null;

    if (identifier != null) {
      final String varName = identifier.getStringValue();
      if (iCurrentRecord != null)
        return iCurrentRecord.getVariable(varName);

      return null;
    }
    if (recordAttribute != null && iCurrentRecord != null)
      return iCurrentRecord.getVariable(recordAttribute.name);

    return null;
  }

  public Object execute(final Object currentValue, final CommandContext context) {
    if (currentValue instanceof Result)
      return execute((Result) currentValue, context);

    if (currentValue instanceof Identifiable)
      return execute((Identifiable) currentValue, context);

    if (currentValue instanceof Map)
      return execute((Map) currentValue, context);

    if (currentValue instanceof CommandContext)
      return execute((CommandContext) currentValue);

    if (currentValue instanceof Iterable)
      return execute((Iterable) currentValue, context);

    if (currentValue instanceof Iterator)
      return execute((Iterator) currentValue, context);

    if (currentValue == null)
      return execute((Result) null, context);

    return null;
    // TODO other cases?
  }

  public boolean isBaseIdentifier() {
    return identifier != null;
  }

  public boolean isAggregate(final CommandContext context) {
    return false;
  }

  public boolean isCount() {
    return false;
  }

  public SuffixIdentifier splitForAggregation(final AggregateProjectionSplit aggregateProj) {
    return this;
  }

  public boolean isEarlyCalculated() {
    return identifier != null && identifier.internalAlias;
  }

  public void aggregate(final Object value, final CommandContext context) {
    throw new UnsupportedOperationException("this operation does not support plain aggregation: " + this);
  }

  public AggregationContext getAggregationContext(final CommandContext context) {
    throw new UnsupportedOperationException("this operation does not support plain aggregation: " + this);
  }

  public SuffixIdentifier copy() {
    final SuffixIdentifier result = new SuffixIdentifier(-1);
    result.identifier = identifier == null ? null : identifier.copy();
    result.recordAttribute = recordAttribute == null ? null : recordAttribute.copy();
    result.star = star;
    return result;
  }

  @Override
  protected Object[] getIdentityElements() {
    return new Object[] { identifier, recordAttribute, star };
  }

  public void extractSubQueries(final SubQueryCollector collector) {
    // EMPTY METHOD
  }

  @Override
  public boolean refersToParent() {
    return identifier != null && identifier.getStringValue().equalsIgnoreCase("$parent");
  }

  public void setValue(final Object target, final Object value, final CommandContext context) {
    if (target instanceof Result)
      setValue((Result) target, value, context);
    else if (target instanceof Identifiable)
      setValue((Identifiable) target, value, context);
    else if (target instanceof Map)
      setValue((Map) target, value, context);
  }

  public void setValue(final Identifiable target, final Object value, final CommandContext context) {
    if (target == null)
      return;

    MutableDocument doc = null;
    if (target instanceof MutableDocument)
      doc = (MutableDocument) target;
    else
      doc = target.getRecord().asDocument().modify();

    if (doc != null)
      doc.set(identifier.getStringValue(), value);
    else
      throw new CommandExecutionException("Cannot set record attribute " + recordAttribute + " on existing document");
  }

  public void setValue(final Map target, final Object value, final CommandContext context) {
    if (target == null)
      return;

    if (identifier != null)
      target.put(identifier.getStringValue(), value);
    else if (recordAttribute != null)
      target.put(recordAttribute.getName(), value);
  }

  public void setValue(final Result target, final Object value, final CommandContext context) {
    if (target == null)
      return;

    if (target instanceof ResultInternal) {
      final ResultInternal intTarget = (ResultInternal) target;
      if (identifier != null)
        intTarget.setProperty(identifier.getStringValue(), value);
      else if (recordAttribute != null)
        intTarget.setProperty(recordAttribute.getName(), value);

    } else {
      throw new CommandExecutionException("Cannot set property on unmodifiable target: " + target);
    }
  }

  public void applyRemove(Object currentValue, final CommandContext context) {
    if (currentValue == null)
      return;

    if (identifier != null) {
      if (currentValue instanceof Result && ((Result) currentValue).isElement())
        currentValue = ((Result) currentValue).toElement();

      if (currentValue instanceof ResultInternal) {
        ((ResultInternal) currentValue).removeProperty(identifier.getStringValue());
      } else if (currentValue instanceof Document) {
        final MutableDocument doc = ((Document) currentValue).modify();
        doc.remove(identifier.getStringValue());
      } else if (currentValue instanceof Map) {
        ((Map) currentValue).remove(identifier.getStringValue());
      }
    }
  }

  public boolean isDefinedFor(final Result currentRecord) {
    if (identifier != null)
      return currentRecord.hasProperty(identifier.getStringValue());

    return true;
  }

  public boolean isDefinedFor(final Record currentRecord) {
    if (identifier != null)
      return ((Document) currentRecord.getRecord()).has(identifier.getStringValue());

    return true;
  }

  public boolean isCacheable() {
    return true;
  }
}
/* JavaCC - OriginalChecksum=5d9be0188c7d6e2b67d691fb88a518f8 (do not edit this line) */
