/*
 * Copyright (c) 2018 - Arcade Analytics LTD (https://arcadeanalytics.com)
 */

package com.arcadedb.sql.executor;

import com.arcadedb.exception.TimeoutException;
import com.arcadedb.sql.parser.Projection;

import java.util.Map;
import java.util.Optional;

/**
 * Created by luigidellaquila on 12/07/16.
 */
public class ProjectionCalculationStep extends AbstractExecutionStep {
  protected final Projection projection;

  protected long cost = 0;

  public ProjectionCalculationStep(Projection projection, CommandContext ctx, boolean profilingEnabled) {
    super(ctx, profilingEnabled);
    this.projection = projection;
  }

  @Override
  public ResultSet syncPull(CommandContext ctx, int nRecords) throws TimeoutException {
    if (!prev.isPresent()) {
      throw new IllegalStateException("Cannot calculate projections without a previous source");
    }

    ResultSet parentRs = prev.get().syncPull(ctx, nRecords);
    return new ResultSet() {
      @Override
      public boolean hasNext() {
        return parentRs.hasNext();
      }

      @Override
      public Result next() {
        Result item = parentRs.next();
        Object oldCurrent = ctx.getVariable("$current");
        ctx.setVariable("$current", item);
        Result result = calculateProjections(ctx, item);
        ctx.setVariable("$current", oldCurrent);
        return result;
      }

      @Override
      public void close() {
        parentRs.close();
      }

      @Override
      public Optional<ExecutionPlan> getExecutionPlan() {
        return null;
      }

      @Override
      public Map<String, Long> getQueryStats() {
        return null;
      }
    };
  }

  private Result calculateProjections(CommandContext ctx, Result next) {
    long begin = profilingEnabled ? System.nanoTime() : 0;
    try {
      return this.projection.calculateSingle(ctx, next);
    } finally {
      if (profilingEnabled) {
        cost += (System.nanoTime() - begin);
      }
    }
  }

  @Override
  public String prettyPrint(int depth, int indent) {
    String spaces = ExecutionStepInternal.getIndent(depth, indent);

    String result = spaces + "+ CALCULATE PROJECTIONS";
    if (profilingEnabled) {
      result += " (" + getCostFormatted() + ")";
    }
    result += ("\n" + spaces + "  " + projection.toString() + "");
    return result;
  }

  @Override
  public long getCost() {
    return cost;
  }

  @Override
  public boolean canBeCached() {
    return true;
  }

  @Override
  public ExecutionStep copy(CommandContext ctx) {
    return new ProjectionCalculationStep(projection.copy(), ctx, profilingEnabled);
  }
}
