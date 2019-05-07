/* Generated By:JJTree: Do not edit this line. WhileBlock.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=true,NODE_PREFIX=O,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package com.arcadedb.sql.parser;

import com.arcadedb.database.Database;
import com.arcadedb.sql.executor.BasicCommandContext;
import com.arcadedb.sql.executor.CommandContext;
import com.arcadedb.sql.executor.ForEachExecutionPlan;
import com.arcadedb.sql.executor.ResultSet;
import com.arcadedb.sql.executor.UpdateExecutionPlan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WhileBlock extends Statement {

    protected BooleanExpression condition;
    protected List<Statement> statements = new ArrayList<Statement>();

    public WhileBlock(int id) {
        super(id);
    }

    public WhileBlock(SqlParser p, int id) {
        super(p, id);
    }

    /**
     * Accept the visitor.
     **/
    public Object jjtAccept(SqlParserVisitor visitor, Object data) {
        return visitor.visit(this, data);
    }

    @Override
    public ResultSet execute(Database db, Object[] args, CommandContext parentCtx) {
        BasicCommandContext ctx = new BasicCommandContext();
        if (parentCtx != null) {
            ctx.setParentWithoutOverridingChild(parentCtx);
        }
        ctx.setDatabase(db);
        Map<Object, Object> params = new HashMap<>();
        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                params.put(i, args[i]);
            }
        }
        ctx.setInputParameters(params);

        UpdateExecutionPlan executionPlan;
        executionPlan = createExecutionPlan(ctx, false);

        executionPlan.executeInternal();
        return new LocalResultSet(executionPlan);
    }

    @Override
    public ResultSet execute(Database db, Map params, CommandContext parentCtx) {
        BasicCommandContext ctx = new BasicCommandContext();
        if (parentCtx != null) {
            ctx.setParentWithoutOverridingChild(parentCtx);
        }
        ctx.setDatabase(db);
        ctx.setInputParameters(params);

        UpdateExecutionPlan executionPlan;
        executionPlan = createExecutionPlan(ctx, false);

        executionPlan.executeInternal();
        return new LocalResultSet(executionPlan);
    }

    public UpdateExecutionPlan createExecutionPlan(CommandContext ctx, boolean enableProfiling) {
        ForEachExecutionPlan plan = new ForEachExecutionPlan(ctx);
        plan.chain(new WhileStep(condition, statements, ctx, enableProfiling));
        return plan;
    }

    @Override
    public Statement copy() {
        WhileBlock result = new WhileBlock(-1);
        result.condition = condition.copy();
        result.statements = statements.stream().map(x -> x.copy()).collect(Collectors.toList());
        return result;
    }

    public boolean containsReturn() {
        for (Statement stm : this.statements) {
            if (stm instanceof ReturnStatement) {
                return true;
            }
            if (stm instanceof ForEachBlock && ((ForEachBlock) stm).containsReturn()) {
                return true;
            }
            if (stm instanceof IfStatement && ((IfStatement) stm).containsReturn()) {
                return true;
            }
            if (stm instanceof WhileBlock && ((WhileBlock) stm).containsReturn()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        WhileBlock that = (WhileBlock) o;

        if (condition != null ? !condition.equals(that.condition) : that.condition != null)
            return false;
        return statements != null ? statements.equals(that.statements) : that.statements == null;
    }

    @Override
    public int hashCode() {
        int result = condition != null ? condition.hashCode() : 0;
        result = 31 * result + (statements != null ? statements.hashCode() : 0);
        return result;
    }

    public void toString(Map<Object, Object> params, StringBuilder builder) {
        builder.append("WHILE (");
        condition.toString(params, builder);
        builder.append(") {\n");
        for (Statement stm : statements) {
            stm.toString(params, builder);
            builder.append("\n");
        }
        builder.append("}");

    }
}
/* JavaCC - OriginalChecksum=1b38ee666f89790d0f54cc5823b99286 (do not edit this line) */
