package custom;

import com.sap.xsk.parser.hana.core.HanaBaseListener;
import com.sap.xsk.parser.hana.core.HanaParser.Create_procedure_bodyContext;
import com.sap.xsk.parser.hana.core.HanaParser.From_clauseContext;
import com.sap.xsk.parser.hana.core.HanaParser.Join_clauseContext;
import com.sap.xsk.parser.hana.core.HanaParser.Update_set_clauseContext;
import com.sap.xsk.parser.hana.core.HanaParser.Update_stmtContext;
import com.sap.xsk.parser.hana.core.HanaParser.Where_clauseContext;
import models.FromClauseDefinitionModel;
import models.JoinClauseDefinitionModel;
import models.ProcedureDefinitionModel;
import models.UpdateSetClauseDefinitionModel;
import models.UpdateStatementDefinitionModel;
import models.WhereClauseDefinitionModel;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.misc.Interval;

public class HanaProcedureListener extends HanaBaseListener {
  ProcedureDefinitionModel procedureModel;
  FromClauseDefinitionModel fromClauseModel;
  UpdateStatementDefinitionModel updateStatementModel;
  Update_stmtContext updateStatementContext;

  @Override
  public void enterCreate_procedure_body(Create_procedure_bodyContext ctx) {
    procedureModel = new ProcedureDefinitionModel();
  }

  @Override
 public void enterUpdate_stmt(Update_stmtContext ctx) {
    updateStatementContext = ctx;
    updateStatementModel = new UpdateStatementDefinitionModel();
    String tableName = ctx.general_table_ref().dml_table_expression_clause().tableview_name().getText();
    String tableAlias = ctx.general_table_ref().table_alias() != null ? ctx.general_table_ref().table_alias().getText() : null;
    updateStatementModel.setTableName(tableName);
    updateStatementModel.setTableAliasName(tableAlias);
    procedureModel.addUpdateStatement(updateStatementModel);
  }

  @Override
  public void exitUpdate_stmt(Update_stmtContext ctx) {
    updateStatementContext = null;
  }

  @Override
  public void enterUpdate_set_clause(Update_set_clauseContext ctx) {
    if (hasUpdateStatement()){
      UpdateSetClauseDefinitionModel updateSetClauseModel = new UpdateSetClauseDefinitionModel();
      updateSetClauseModel.setClause(getStringWithSpaces(ctx));
      updateStatementModel.setSetClauseDefinitionModel(updateSetClauseModel);
    }
  }

  @Override
  public void enterJoin_clause(Join_clauseContext ctx) {
    if (hasUpdateStatement()){
      JoinClauseDefinitionModel joinClauseModel = new JoinClauseDefinitionModel();
      joinClauseModel.setInnerJoin(getStringWithSpaces(ctx));
      fromClauseModel.addJoinClauseDefinitionModel(joinClauseModel);
    }
  }

  @Override
  public void enterWhere_clause(Where_clauseContext ctx) {
    if (hasUpdateStatement()){
      WhereClauseDefinitionModel whereClauseModel = new WhereClauseDefinitionModel();
      whereClauseModel.setClause(getStringWithSpaces(ctx));
      updateStatementModel.setWhereClauseDefinitionModel(whereClauseModel);
    }
  }

  @Override
  public void enterFrom_clause(From_clauseContext ctx) {
    if (hasUpdateStatement()){
      fromClauseModel = new FromClauseDefinitionModel();
      String tableName = ctx.table_ref_list().table_ref(0).table_ref_aux().getChild(0).getText();
      String tableAlias = ctx.table_ref_list().table_ref(0).table_ref_aux().table_alias() != null ? ctx.table_ref_list().table_ref(0).table_ref_aux().table_alias().getText() : null;
      fromClauseModel.setTableName(tableName);
      fromClauseModel.setTableAliasName(tableAlias);
      updateStatementModel.setFromClauseDefinitionModel(fromClauseModel);
    }
  }

  private String getStringWithSpaces(ParserRuleContext ctx){
    int startIndex = ctx.start.getStartIndex();
    int stopIndex = ctx.stop.getStopIndex();
    Interval selectedColumnsRuleSqlInterval = new Interval(startIndex, stopIndex);
    return ctx.start.getInputStream().getText(selectedColumnsRuleSqlInterval);
  }

  private Boolean hasUpdateStatement(){
    return updateStatementContext != null;
  }

  public ProcedureDefinitionModel getProcedureModel() {
    return procedureModel;
  }
}
