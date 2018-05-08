/*
 * Copyright (c) 2018 - Arcade Analytics LTD (https://arcadeanalytics.com)
 */

package com.arcadedb.sql.executor;

import com.arcadedb.database.Database;
import com.arcadedb.exception.CommandExecutionException;
import com.arcadedb.exception.TimeoutException;
import com.arcadedb.schema.PDocumentType;
import com.arcadedb.schema.PSchema;

/**
 * <p>
 * This step is used just as a gate check for classes (eg. for CREATE VERTEX to make sure that the passed class is a vertex class).
 * </p>
 * <p>
 * It accepts two values: a target class and a parent class. If the two classes are the same or if the parent class is indeed
 * a parent class of the target class, then the syncPool() returns an empty result set, otherwise it throws an PCommandExecutionException
 * </p>
 *
 * @author Luigi Dell'Aquila (luigi.dellaquila - at - orientdb.com)
 */
public class CheckClassTypeStep extends AbstractExecutionStep {

  private final String targetClass;
  private final String parentClass;

  private long cost = 0;

  boolean found = false;

  /**
   * @param targetClass      a class to be checked
   * @param parentClass      a class that is supposed to be the same or a parent class of the target class
   * @param ctx              execuiton context
   * @param profilingEnabled true to collect execution stats
   */
  public CheckClassTypeStep(String targetClass, String parentClass, CommandContext ctx, boolean profilingEnabled) {
    super(ctx, profilingEnabled);
    this.targetClass = targetClass;
    this.parentClass = parentClass;
  }

  @Override
  public ResultSet syncPull(CommandContext ctx, int nRecords) throws TimeoutException {
    getPrev().ifPresent(x -> x.syncPull(ctx, nRecords));
    long begin = profilingEnabled ? System.nanoTime() : 0;
    try {
      if (found) {
        return new InternalResultSet();
      }
      if (this.targetClass.equals(this.parentClass)) {
        return new InternalResultSet();
      }

      if (this.parentClass != null) {
        if (!this.parentClass.equals("V") && this.parentClass.equals("E")) {
          Database db = ctx.getDatabase();

          PSchema schema = db.getSchema();
          PDocumentType parentClazz = schema.getType(this.parentClass);

          PDocumentType targetClazz = schema.getType(this.targetClass);
          if (targetClazz == null) {
            throw new CommandExecutionException("Class not found: " + this.targetClass);
          }

          if (parentClazz.equals(targetClazz)) {
            found = true;
          } else {
            for (PDocumentType sublcass : parentClazz.getSubTypes()) {
              if (sublcass.equals(targetClazz)) {
                this.found = true;
                break;
              }
            }
          }
          if (!found) {
            throw new CommandExecutionException("Class  " + this.targetClass + " is not a subclass of " + this.parentClass);
          }
        }
      }
      return new InternalResultSet();
    } finally {
      if (profilingEnabled) {
        cost += (System.nanoTime() - begin);
      }
    }
  }

  @Override
  public String prettyPrint(int depth, int indent) {
    String spaces = ExecutionStepInternal.getIndent(depth, indent);
    StringBuilder result = new StringBuilder();
    result.append(spaces);
    result.append("+ CHECK CLASS HIERARCHY");
    if (profilingEnabled) {
      result.append(" (" + getCostFormatted() + ")");
    }
    result.append("\n");
    result.append("  " + this.parentClass);
    return result.toString();
  }

  @Override
  public long getCost() {
    return cost;
  }
}
