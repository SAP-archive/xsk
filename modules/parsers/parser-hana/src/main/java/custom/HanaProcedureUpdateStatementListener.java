/*
 * Copyright (c) 2022 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2022 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
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
import models.TableReferenceModel;
import models.UpdateSetClauseDefinitionModel;
import models.UpdateStatementDefinitionModel;
import models.WhereClauseDefinitionModel;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.misc.Interval;
import org.apache.commons.lang3.StringUtils;

public class HanaProcedureUpdateStatementListener extends HanaBaseListener {

  ProcedureDefinitionModel procedureModel;
  FromClauseDefinitionModel fromClauseModel;
  UpdateStatementDefinitionModel updateStatementModel;
  boolean isUpdateStatementScope = false;

  @Override
  public void enterCreate_procedure_body(Create_procedure_bodyContext ctx) {
    String strippedSchema = null;
    String strippedName = null;

    if (ctx.proc_name() != null) {
      if (ctx.proc_name().id_expression() != null) {
        String maybeQuotedName = ctx.proc_name().id_expression().getText();
        strippedName = StringUtils.strip(maybeQuotedName, "\"");
      }

      if (ctx.proc_name().schema_name() != null) {
        String maybeQuotedSchema = ctx.proc_name().schema_name().getText();
        strippedSchema = StringUtils.strip(maybeQuotedSchema, "\"");
      }
    }
    procedureModel = new ProcedureDefinitionModel(strippedSchema, strippedName);
  }

  @Override
  public void enterUpdate_stmt(Update_stmtContext ctx) {
    isUpdateStatementScope = true;
    updateStatementModel = new UpdateStatementDefinitionModel();
    String tableName = ctx.general_table_ref().dml_table_expression_clause().tableview_name().getText();
    String tableAlias = ctx.general_table_ref().table_alias() != null ? ctx.general_table_ref().table_alias().getText() : null;
    updateStatementModel.setName(tableName);
    updateStatementModel.setAlias(tableAlias);
    updateStatementModel.setRawContent(getStringWithSpaces(ctx));
    procedureModel.addUpdateStatement(updateStatementModel);
  }

  @Override
  public void exitUpdate_stmt(Update_stmtContext ctx) {
    isUpdateStatementScope = false;
  }

  @Override
  public void enterUpdate_set_clause(Update_set_clauseContext ctx) {
    if (isUpdateStatementScope && ctx.parent instanceof Update_stmtContext) {
      UpdateSetClauseDefinitionModel updateSetClauseModel = new UpdateSetClauseDefinitionModel();
      updateSetClauseModel.setRawContent(getStringWithSpaces(ctx));
      updateStatementModel.setUpdateSetClause(updateSetClauseModel);
    }
  }

  @Override
  public void enterJoin_clause(Join_clauseContext ctx) {
    RuleContext ruleContext = ctx.parent.parent.parent.parent;
    if (isUpdateStatementScope && ruleContext instanceof Update_stmtContext) {
      JoinClauseDefinitionModel joinClauseModel = new JoinClauseDefinitionModel();
      String tableName;

      if (ctx.table_ref_aux().dml_table_expression_clause() != null) {
        tableName = getStringWithSpaces(ctx.table_ref_aux().dml_table_expression_clause());
      }
      else {
       tableName =  ctx.table_ref_aux().getChild(0).getText();
      }

      String tableAlias = ctx.table_ref_aux().table_alias() != null ? ctx.table_ref_aux().table_alias().getText() : null;
      joinClauseModel.setName(tableName);
      joinClauseModel.setAlias(tableAlias);
      joinClauseModel.setOnPart(getStringWithSpaces(ctx.join_on_part(0)));
      joinClauseModel.setRawContent(getStringWithSpaces(ctx));
      fromClauseModel.addJoinClause(joinClauseModel);
    }
  }

  @Override
  public void enterWhere_clause(Where_clauseContext ctx) {
    if (isUpdateStatementScope && ctx.parent instanceof Update_stmtContext) {
      WhereClauseDefinitionModel whereClauseModel = new WhereClauseDefinitionModel();
      whereClauseModel.setRawContent(getStringWithSpaces(ctx));
      updateStatementModel.setWhereClause(whereClauseModel);
    }
  }

  @Override
  public void enterFrom_clause(From_clauseContext ctx) {
    if (isUpdateStatementScope && ctx.parent instanceof Update_stmtContext) {
      fromClauseModel = new FromClauseDefinitionModel();

      ctx.table_ref_list().table_ref().forEach(tableRef -> {
        TableReferenceModel tableReferenceModel = new TableReferenceModel();
        String tableName = tableRef.table_ref_aux().getChild(0).getText();
        String tableAlias = tableRef.table_ref_aux().table_alias() != null ? tableRef.table_ref_aux().table_alias().getText() : null;
        tableReferenceModel.setName(tableName);
        tableReferenceModel.setAlias(tableAlias);
        fromClauseModel.addTableReference(tableReferenceModel);
      });

      updateStatementModel.setFromClause(fromClauseModel);
    }
  }

  private String getStringWithSpaces(ParserRuleContext ctx) {
    int startIndex = ctx.start.getStartIndex();
    int stopIndex = ctx.stop.getStopIndex();
    Interval selectedColumnsRuleSqlInterval = new Interval(startIndex, stopIndex);
    return ctx.start.getInputStream().getText(selectedColumnsRuleSqlInterval);
  }

  public ProcedureDefinitionModel getProcedureModel() {
    return procedureModel;
  }
}
