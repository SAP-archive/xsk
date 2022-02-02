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
// Generated from com.sap.xsk.parser.hana.core/Hana.g4 by ANTLR 4.3
package com.sap.xsk.parser.hana.core;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link HanaParser}.
 */
public interface HanaListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link HanaParser#reference_model_name}.
	 * @param ctx the parse tree
	 */
	void enterReference_model_name(@NotNull HanaParser.Reference_model_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#reference_model_name}.
	 * @param ctx the parse tree
	 */
	void exitReference_model_name(@NotNull HanaParser.Reference_model_nameContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#continue_statement}.
	 * @param ctx the parse tree
	 */
	void enterContinue_statement(@NotNull HanaParser.Continue_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#continue_statement}.
	 * @param ctx the parse tree
	 */
	void exitContinue_statement(@NotNull HanaParser.Continue_statementContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#function_call}.
	 * @param ctx the parse tree
	 */
	void enterFunction_call(@NotNull HanaParser.Function_callContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#function_call}.
	 * @param ctx the parse tree
	 */
	void exitFunction_call(@NotNull HanaParser.Function_callContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#model_column}.
	 * @param ctx the parse tree
	 */
	void enterModel_column(@NotNull HanaParser.Model_columnContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#model_column}.
	 * @param ctx the parse tree
	 */
	void exitModel_column(@NotNull HanaParser.Model_columnContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#cursor_declaration}.
	 * @param ctx the parse tree
	 */
	void enterCursor_declaration(@NotNull HanaParser.Cursor_declarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#cursor_declaration}.
	 * @param ctx the parse tree
	 */
	void exitCursor_declaration(@NotNull HanaParser.Cursor_declarationContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#else_part}.
	 * @param ctx the parse tree
	 */
	void enterElse_part(@NotNull HanaParser.Else_partContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#else_part}.
	 * @param ctx the parse tree
	 */
	void exitElse_part(@NotNull HanaParser.Else_partContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#table_var_name}.
	 * @param ctx the parse tree
	 */
	void enterTable_var_name(@NotNull HanaParser.Table_var_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#table_var_name}.
	 * @param ctx the parse tree
	 */
	void exitTable_var_name(@NotNull HanaParser.Table_var_nameContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#searched_case_statement}.
	 * @param ctx the parse tree
	 */
	void enterSearched_case_statement(@NotNull HanaParser.Searched_case_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#searched_case_statement}.
	 * @param ctx the parse tree
	 */
	void exitSearched_case_statement(@NotNull HanaParser.Searched_case_statementContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#alter_collection_clauses}.
	 * @param ctx the parse tree
	 */
	void enterAlter_collection_clauses(@NotNull HanaParser.Alter_collection_clausesContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#alter_collection_clauses}.
	 * @param ctx the parse tree
	 */
	void exitAlter_collection_clauses(@NotNull HanaParser.Alter_collection_clausesContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#grouping_sets_clause}.
	 * @param ctx the parse tree
	 */
	void enterGrouping_sets_clause(@NotNull HanaParser.Grouping_sets_clauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#grouping_sets_clause}.
	 * @param ctx the parse tree
	 */
	void exitGrouping_sets_clause(@NotNull HanaParser.Grouping_sets_clauseContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#conditional_insert_else_part}.
	 * @param ctx the parse tree
	 */
	void enterConditional_insert_else_part(@NotNull HanaParser.Conditional_insert_else_partContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#conditional_insert_else_part}.
	 * @param ctx the parse tree
	 */
	void exitConditional_insert_else_part(@NotNull HanaParser.Conditional_insert_else_partContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#sqlj_object_type}.
	 * @param ctx the parse tree
	 */
	void enterSqlj_object_type(@NotNull HanaParser.Sqlj_object_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#sqlj_object_type}.
	 * @param ctx the parse tree
	 */
	void exitSqlj_object_type(@NotNull HanaParser.Sqlj_object_typeContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#bind_sql_error_code}.
	 * @param ctx the parse tree
	 */
	void enterBind_sql_error_code(@NotNull HanaParser.Bind_sql_error_codeContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#bind_sql_error_code}.
	 * @param ctx the parse tree
	 */
	void exitBind_sql_error_code(@NotNull HanaParser.Bind_sql_error_codeContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#xmlserialize_param_ident_part}.
	 * @param ctx the parse tree
	 */
	void enterXmlserialize_param_ident_part(@NotNull HanaParser.Xmlserialize_param_ident_partContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#xmlserialize_param_ident_part}.
	 * @param ctx the parse tree
	 */
	void exitXmlserialize_param_ident_part(@NotNull HanaParser.Xmlserialize_param_ident_partContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#goto_statement}.
	 * @param ctx the parse tree
	 */
	void enterGoto_statement(@NotNull HanaParser.Goto_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#goto_statement}.
	 * @param ctx the parse tree
	 */
	void exitGoto_statement(@NotNull HanaParser.Goto_statementContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#lock_table_element}.
	 * @param ctx the parse tree
	 */
	void enterLock_table_element(@NotNull HanaParser.Lock_table_elementContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#lock_table_element}.
	 * @param ctx the parse tree
	 */
	void exitLock_table_element(@NotNull HanaParser.Lock_table_elementContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#partition_extension_clause}.
	 * @param ctx the parse tree
	 */
	void enterPartition_extension_clause(@NotNull HanaParser.Partition_extension_clauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#partition_extension_clause}.
	 * @param ctx the parse tree
	 */
	void exitPartition_extension_clause(@NotNull HanaParser.Partition_extension_clauseContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#nested_table_type_def}.
	 * @param ctx the parse tree
	 */
	void enterNested_table_type_def(@NotNull HanaParser.Nested_table_type_defContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#nested_table_type_def}.
	 * @param ctx the parse tree
	 */
	void exitNested_table_type_def(@NotNull HanaParser.Nested_table_type_defContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#sample_clause}.
	 * @param ctx the parse tree
	 */
	void enterSample_clause(@NotNull HanaParser.Sample_clauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#sample_clause}.
	 * @param ctx the parse tree
	 */
	void exitSample_clause(@NotNull HanaParser.Sample_clauseContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#unnest_function}.
	 * @param ctx the parse tree
	 */
	void enterUnnest_function(@NotNull HanaParser.Unnest_functionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#unnest_function}.
	 * @param ctx the parse tree
	 */
	void exitUnnest_function(@NotNull HanaParser.Unnest_functionContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#procedure_body}.
	 * @param ctx the parse tree
	 */
	void enterProcedure_body(@NotNull HanaParser.Procedure_bodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#procedure_body}.
	 * @param ctx the parse tree
	 */
	void exitProcedure_body(@NotNull HanaParser.Procedure_bodyContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#func_body}.
	 * @param ctx the parse tree
	 */
	void enterFunc_body(@NotNull HanaParser.Func_bodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#func_body}.
	 * @param ctx the parse tree
	 */
	void exitFunc_body(@NotNull HanaParser.Func_bodyContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#prompt_command}.
	 * @param ctx the parse tree
	 */
	void enterPrompt_command(@NotNull HanaParser.Prompt_commandContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#prompt_command}.
	 * @param ctx the parse tree
	 */
	void exitPrompt_command(@NotNull HanaParser.Prompt_commandContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#proc_table_variable}.
	 * @param ctx the parse tree
	 */
	void enterProc_table_variable(@NotNull HanaParser.Proc_table_variableContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#proc_table_variable}.
	 * @param ctx the parse tree
	 */
	void exitProc_table_variable(@NotNull HanaParser.Proc_table_variableContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#insert_stmt}.
	 * @param ctx the parse tree
	 */
	void enterInsert_stmt(@NotNull HanaParser.Insert_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#insert_stmt}.
	 * @param ctx the parse tree
	 */
	void exitInsert_stmt(@NotNull HanaParser.Insert_stmtContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#search_clause}.
	 * @param ctx the parse tree
	 */
	void enterSearch_clause(@NotNull HanaParser.Search_clauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#search_clause}.
	 * @param ctx the parse tree
	 */
	void exitSearch_clause(@NotNull HanaParser.Search_clauseContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#c_agent_in_clause}.
	 * @param ctx the parse tree
	 */
	void enterC_agent_in_clause(@NotNull HanaParser.C_agent_in_clauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#c_agent_in_clause}.
	 * @param ctx the parse tree
	 */
	void exitC_agent_in_clause(@NotNull HanaParser.C_agent_in_clauseContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#variable_declaration}.
	 * @param ctx the parse tree
	 */
	void enterVariable_declaration(@NotNull HanaParser.Variable_declarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#variable_declaration}.
	 * @param ctx the parse tree
	 */
	void exitVariable_declaration(@NotNull HanaParser.Variable_declarationContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#cursor_manipulation_statements}.
	 * @param ctx the parse tree
	 */
	void enterCursor_manipulation_statements(@NotNull HanaParser.Cursor_manipulation_statementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#cursor_manipulation_statements}.
	 * @param ctx the parse tree
	 */
	void exitCursor_manipulation_statements(@NotNull HanaParser.Cursor_manipulation_statementsContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#not_equal_op}.
	 * @param ctx the parse tree
	 */
	void enterNot_equal_op(@NotNull HanaParser.Not_equal_opContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#not_equal_op}.
	 * @param ctx the parse tree
	 */
	void exitNot_equal_op(@NotNull HanaParser.Not_equal_opContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#search_case_expression_}.
	 * @param ctx the parse tree
	 */
	void enterSearch_case_expression_(@NotNull HanaParser.Search_case_expression_Context ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#search_case_expression_}.
	 * @param ctx the parse tree
	 */
	void exitSearch_case_expression_(@NotNull HanaParser.Search_case_expression_Context ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#general_element_part}.
	 * @param ctx the parse tree
	 */
	void enterGeneral_element_part(@NotNull HanaParser.General_element_partContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#general_element_part}.
	 * @param ctx the parse tree
	 */
	void exitGeneral_element_part(@NotNull HanaParser.General_element_partContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#sequence_spec}.
	 * @param ctx the parse tree
	 */
	void enterSequence_spec(@NotNull HanaParser.Sequence_specContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#sequence_spec}.
	 * @param ctx the parse tree
	 */
	void exitSequence_spec(@NotNull HanaParser.Sequence_specContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#modifier_clause}.
	 * @param ctx the parse tree
	 */
	void enterModifier_clause(@NotNull HanaParser.Modifier_clauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#modifier_clause}.
	 * @param ctx the parse tree
	 */
	void exitModifier_clause(@NotNull HanaParser.Modifier_clauseContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#outer_join_sign}.
	 * @param ctx the parse tree
	 */
	void enterOuter_join_sign(@NotNull HanaParser.Outer_join_signContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#outer_join_sign}.
	 * @param ctx the parse tree
	 */
	void exitOuter_join_sign(@NotNull HanaParser.Outer_join_signContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#c_spec}.
	 * @param ctx the parse tree
	 */
	void enterC_spec(@NotNull HanaParser.C_specContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#c_spec}.
	 * @param ctx the parse tree
	 */
	void exitC_spec(@NotNull HanaParser.C_specContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#expression_wrapper}.
	 * @param ctx the parse tree
	 */
	void enterExpression_wrapper(@NotNull HanaParser.Expression_wrapperContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#expression_wrapper}.
	 * @param ctx the parse tree
	 */
	void exitExpression_wrapper(@NotNull HanaParser.Expression_wrapperContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#seed_part}.
	 * @param ctx the parse tree
	 */
	void enterSeed_part(@NotNull HanaParser.Seed_partContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#seed_part}.
	 * @param ctx the parse tree
	 */
	void exitSeed_part(@NotNull HanaParser.Seed_partContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#factoring_element}.
	 * @param ctx the parse tree
	 */
	void enterFactoring_element(@NotNull HanaParser.Factoring_elementContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#factoring_element}.
	 * @param ctx the parse tree
	 */
	void exitFactoring_element(@NotNull HanaParser.Factoring_elementContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#over_clause_keyword}.
	 * @param ctx the parse tree
	 */
	void enterOver_clause_keyword(@NotNull HanaParser.Over_clause_keywordContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#over_clause_keyword}.
	 * @param ctx the parse tree
	 */
	void exitOver_clause_keyword(@NotNull HanaParser.Over_clause_keywordContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#varray_type_def}.
	 * @param ctx the parse tree
	 */
	void enterVarray_type_def(@NotNull HanaParser.Varray_type_defContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#varray_type_def}.
	 * @param ctx the parse tree
	 */
	void exitVarray_type_def(@NotNull HanaParser.Varray_type_defContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#alias_quoted_string}.
	 * @param ctx the parse tree
	 */
	void enterAlias_quoted_string(@NotNull HanaParser.Alias_quoted_stringContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#alias_quoted_string}.
	 * @param ctx the parse tree
	 */
	void exitAlias_quoted_string(@NotNull HanaParser.Alias_quoted_stringContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#drop_procedure}.
	 * @param ctx the parse tree
	 */
	void enterDrop_procedure(@NotNull HanaParser.Drop_procedureContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#drop_procedure}.
	 * @param ctx the parse tree
	 */
	void exitDrop_procedure(@NotNull HanaParser.Drop_procedureContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#standard_function}.
	 * @param ctx the parse tree
	 */
	void enterStandard_function(@NotNull HanaParser.Standard_functionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#standard_function}.
	 * @param ctx the parse tree
	 */
	void exitStandard_function(@NotNull HanaParser.Standard_functionContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#selected_element}.
	 * @param ctx the parse tree
	 */
	void enterSelected_element(@NotNull HanaParser.Selected_elementContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#selected_element}.
	 * @param ctx the parse tree
	 */
	void exitSelected_element(@NotNull HanaParser.Selected_elementContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#const_sql_error_code}.
	 * @param ctx the parse tree
	 */
	void enterConst_sql_error_code(@NotNull HanaParser.Const_sql_error_codeContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#const_sql_error_code}.
	 * @param ctx the parse tree
	 */
	void exitConst_sql_error_code(@NotNull HanaParser.Const_sql_error_codeContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#in_elements}.
	 * @param ctx the parse tree
	 */
	void enterIn_elements(@NotNull HanaParser.In_elementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#in_elements}.
	 * @param ctx the parse tree
	 */
	void exitIn_elements(@NotNull HanaParser.In_elementsContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#flashback_query_clause}.
	 * @param ctx the parse tree
	 */
	void enterFlashback_query_clause(@NotNull HanaParser.Flashback_query_clauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#flashback_query_clause}.
	 * @param ctx the parse tree
	 */
	void exitFlashback_query_clause(@NotNull HanaParser.Flashback_query_clauseContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#additive_expression}.
	 * @param ctx the parse tree
	 */
	void enterAdditive_expression(@NotNull HanaParser.Additive_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#additive_expression}.
	 * @param ctx the parse tree
	 */
	void exitAdditive_expression(@NotNull HanaParser.Additive_expressionContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#model_iterate_clause}.
	 * @param ctx the parse tree
	 */
	void enterModel_iterate_clause(@NotNull HanaParser.Model_iterate_clauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#model_iterate_clause}.
	 * @param ctx the parse tree
	 */
	void exitModel_iterate_clause(@NotNull HanaParser.Model_iterate_clauseContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#declare_statement}.
	 * @param ctx the parse tree
	 */
	void enterDeclare_statement(@NotNull HanaParser.Declare_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#declare_statement}.
	 * @param ctx the parse tree
	 */
	void exitDeclare_statement(@NotNull HanaParser.Declare_statementContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#link_name}.
	 * @param ctx the parse tree
	 */
	void enterLink_name(@NotNull HanaParser.Link_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#link_name}.
	 * @param ctx the parse tree
	 */
	void exitLink_name(@NotNull HanaParser.Link_nameContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#return_table_type}.
	 * @param ctx the parse tree
	 */
	void enterReturn_table_type(@NotNull HanaParser.Return_table_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#return_table_type}.
	 * @param ctx the parse tree
	 */
	void exitReturn_table_type(@NotNull HanaParser.Return_table_typeContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#condition_wrapper}.
	 * @param ctx the parse tree
	 */
	void enterCondition_wrapper(@NotNull HanaParser.Condition_wrapperContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#condition_wrapper}.
	 * @param ctx the parse tree
	 */
	void exitCondition_wrapper(@NotNull HanaParser.Condition_wrapperContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#collect_order_by_part}.
	 * @param ctx the parse tree
	 */
	void enterCollect_order_by_part(@NotNull HanaParser.Collect_order_by_partContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#collect_order_by_part}.
	 * @param ctx the parse tree
	 */
	void exitCollect_order_by_part(@NotNull HanaParser.Collect_order_by_partContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#subquery_restriction_clause}.
	 * @param ctx the parse tree
	 */
	void enterSubquery_restriction_clause(@NotNull HanaParser.Subquery_restriction_clauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#subquery_restriction_clause}.
	 * @param ctx the parse tree
	 */
	void exitSubquery_restriction_clause(@NotNull HanaParser.Subquery_restriction_clauseContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#create_sequence}.
	 * @param ctx the parse tree
	 */
	void enterCreate_sequence(@NotNull HanaParser.Create_sequenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#create_sequence}.
	 * @param ctx the parse tree
	 */
	void exitCreate_sequence(@NotNull HanaParser.Create_sequenceContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#datetime_expression}.
	 * @param ctx the parse tree
	 */
	void enterDatetime_expression(@NotNull HanaParser.Datetime_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#datetime_expression}.
	 * @param ctx the parse tree
	 */
	void exitDatetime_expression(@NotNull HanaParser.Datetime_expressionContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#param_type}.
	 * @param ctx the parse tree
	 */
	void enterParam_type(@NotNull HanaParser.Param_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#param_type}.
	 * @param ctx the parse tree
	 */
	void exitParam_type(@NotNull HanaParser.Param_typeContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#update_stmt}.
	 * @param ctx the parse tree
	 */
	void enterUpdate_stmt(@NotNull HanaParser.Update_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#update_stmt}.
	 * @param ctx the parse tree
	 */
	void exitUpdate_stmt(@NotNull HanaParser.Update_stmtContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#variable_name_old}.
	 * @param ctx the parse tree
	 */
	void enterVariable_name_old(@NotNull HanaParser.Variable_name_oldContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#variable_name_old}.
	 * @param ctx the parse tree
	 */
	void exitVariable_name_old(@NotNull HanaParser.Variable_name_oldContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#merge_update_clause}.
	 * @param ctx the parse tree
	 */
	void enterMerge_update_clause(@NotNull HanaParser.Merge_update_clauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#merge_update_clause}.
	 * @param ctx the parse tree
	 */
	void exitMerge_update_clause(@NotNull HanaParser.Merge_update_clauseContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#unpivot_in_clause}.
	 * @param ctx the parse tree
	 */
	void enterUnpivot_in_clause(@NotNull HanaParser.Unpivot_in_clauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#unpivot_in_clause}.
	 * @param ctx the parse tree
	 */
	void exitUnpivot_in_clause(@NotNull HanaParser.Unpivot_in_clauseContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#column_alias}.
	 * @param ctx the parse tree
	 */
	void enterColumn_alias(@NotNull HanaParser.Column_aliasContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#column_alias}.
	 * @param ctx the parse tree
	 */
	void exitColumn_alias(@NotNull HanaParser.Column_aliasContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#lock_mode}.
	 * @param ctx the parse tree
	 */
	void enterLock_mode(@NotNull HanaParser.Lock_modeContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#lock_mode}.
	 * @param ctx the parse tree
	 */
	void exitLock_mode(@NotNull HanaParser.Lock_modeContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#respect_or_ignore_nulls}.
	 * @param ctx the parse tree
	 */
	void enterRespect_or_ignore_nulls(@NotNull HanaParser.Respect_or_ignore_nullsContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#respect_or_ignore_nulls}.
	 * @param ctx the parse tree
	 */
	void exitRespect_or_ignore_nulls(@NotNull HanaParser.Respect_or_ignore_nullsContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#query_partition_clause}.
	 * @param ctx the parse tree
	 */
	void enterQuery_partition_clause(@NotNull HanaParser.Query_partition_clauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#query_partition_clause}.
	 * @param ctx the parse tree
	 */
	void exitQuery_partition_clause(@NotNull HanaParser.Query_partition_clauseContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#proc_cursor_param_list}.
	 * @param ctx the parse tree
	 */
	void enterProc_cursor_param_list(@NotNull HanaParser.Proc_cursor_param_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#proc_cursor_param_list}.
	 * @param ctx the parse tree
	 */
	void exitProc_cursor_param_list(@NotNull HanaParser.Proc_cursor_param_listContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#collection_name}.
	 * @param ctx the parse tree
	 */
	void enterCollection_name(@NotNull HanaParser.Collection_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#collection_name}.
	 * @param ctx the parse tree
	 */
	void exitCollection_name(@NotNull HanaParser.Collection_nameContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#map_order_function_spec}.
	 * @param ctx the parse tree
	 */
	void enterMap_order_function_spec(@NotNull HanaParser.Map_order_function_specContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#map_order_function_spec}.
	 * @param ctx the parse tree
	 */
	void exitMap_order_function_spec(@NotNull HanaParser.Map_order_function_specContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#single_column_for_loop}.
	 * @param ctx the parse tree
	 */
	void enterSingle_column_for_loop(@NotNull HanaParser.Single_column_for_loopContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#single_column_for_loop}.
	 * @param ctx the parse tree
	 */
	void exitSingle_column_for_loop(@NotNull HanaParser.Single_column_for_loopContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#proc_condition_value_list}.
	 * @param ctx the parse tree
	 */
	void enterProc_condition_value_list(@NotNull HanaParser.Proc_condition_value_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#proc_condition_value_list}.
	 * @param ctx the parse tree
	 */
	void exitProc_condition_value_list(@NotNull HanaParser.Proc_condition_value_listContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#aggregate_function_name}.
	 * @param ctx the parse tree
	 */
	void enterAggregate_function_name(@NotNull HanaParser.Aggregate_function_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#aggregate_function_name}.
	 * @param ctx the parse tree
	 */
	void exitAggregate_function_name(@NotNull HanaParser.Aggregate_function_nameContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#proc_elsif_list}.
	 * @param ctx the parse tree
	 */
	void enterProc_elsif_list(@NotNull HanaParser.Proc_elsif_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#proc_elsif_list}.
	 * @param ctx the parse tree
	 */
	void exitProc_elsif_list(@NotNull HanaParser.Proc_elsif_listContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#insert_into_clause}.
	 * @param ctx the parse tree
	 */
	void enterInsert_into_clause(@NotNull HanaParser.Insert_into_clauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#insert_into_clause}.
	 * @param ctx the parse tree
	 */
	void exitInsert_into_clause(@NotNull HanaParser.Insert_into_clauseContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#column_based_update_set_clause}.
	 * @param ctx the parse tree
	 */
	void enterColumn_based_update_set_clause(@NotNull HanaParser.Column_based_update_set_clauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#column_based_update_set_clause}.
	 * @param ctx the parse tree
	 */
	void exitColumn_based_update_set_clause(@NotNull HanaParser.Column_based_update_set_clauseContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#proc_handler}.
	 * @param ctx the parse tree
	 */
	void enterProc_handler(@NotNull HanaParser.Proc_handlerContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#proc_handler}.
	 * @param ctx the parse tree
	 */
	void exitProc_handler(@NotNull HanaParser.Proc_handlerContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#merge_statement}.
	 * @param ctx the parse tree
	 */
	void enterMerge_statement(@NotNull HanaParser.Merge_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#merge_statement}.
	 * @param ctx the parse tree
	 */
	void exitMerge_statement(@NotNull HanaParser.Merge_statementContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#proc_default}.
	 * @param ctx the parse tree
	 */
	void enterProc_default(@NotNull HanaParser.Proc_defaultContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#proc_default}.
	 * @param ctx the parse tree
	 */
	void exitProc_default(@NotNull HanaParser.Proc_defaultContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#like_type}.
	 * @param ctx the parse tree
	 */
	void enterLike_type(@NotNull HanaParser.Like_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#like_type}.
	 * @param ctx the parse tree
	 */
	void exitLike_type(@NotNull HanaParser.Like_typeContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#proc_param}.
	 * @param ctx the parse tree
	 */
	void enterProc_param(@NotNull HanaParser.Proc_paramContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#proc_param}.
	 * @param ctx the parse tree
	 */
	void exitProc_param(@NotNull HanaParser.Proc_paramContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#concatenation_op}.
	 * @param ctx the parse tree
	 */
	void enterConcatenation_op(@NotNull HanaParser.Concatenation_opContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#concatenation_op}.
	 * @param ctx the parse tree
	 */
	void exitConcatenation_op(@NotNull HanaParser.Concatenation_opContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#column_elem}.
	 * @param ctx the parse tree
	 */
	void enterColumn_elem(@NotNull HanaParser.Column_elemContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#column_elem}.
	 * @param ctx the parse tree
	 */
	void exitColumn_elem(@NotNull HanaParser.Column_elemContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#set_constraint_command}.
	 * @param ctx the parse tree
	 */
	void enterSet_constraint_command(@NotNull HanaParser.Set_constraint_commandContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#set_constraint_command}.
	 * @param ctx the parse tree
	 */
	void exitSet_constraint_command(@NotNull HanaParser.Set_constraint_commandContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#exist_predicate}.
	 * @param ctx the parse tree
	 */
	void enterExist_predicate(@NotNull HanaParser.Exist_predicateContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#exist_predicate}.
	 * @param ctx the parse tree
	 */
	void exitExist_predicate(@NotNull HanaParser.Exist_predicateContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#lower_bound}.
	 * @param ctx the parse tree
	 */
	void enterLower_bound(@NotNull HanaParser.Lower_boundContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#lower_bound}.
	 * @param ctx the parse tree
	 */
	void exitLower_bound(@NotNull HanaParser.Lower_boundContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#fetch_statement}.
	 * @param ctx the parse tree
	 */
	void enterFetch_statement(@NotNull HanaParser.Fetch_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#fetch_statement}.
	 * @param ctx the parse tree
	 */
	void exitFetch_statement(@NotNull HanaParser.Fetch_statementContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#declare_spec}.
	 * @param ctx the parse tree
	 */
	void enterDeclare_spec(@NotNull HanaParser.Declare_specContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#declare_spec}.
	 * @param ctx the parse tree
	 */
	void exitDeclare_spec(@NotNull HanaParser.Declare_specContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#function_name}.
	 * @param ctx the parse tree
	 */
	void enterFunction_name(@NotNull HanaParser.Function_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#function_name}.
	 * @param ctx the parse tree
	 */
	void exitFunction_name(@NotNull HanaParser.Function_nameContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#data_type}.
	 * @param ctx the parse tree
	 */
	void enterData_type(@NotNull HanaParser.Data_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#data_type}.
	 * @param ctx the parse tree
	 */
	void exitData_type(@NotNull HanaParser.Data_typeContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#array_datatype}.
	 * @param ctx the parse tree
	 */
	void enterArray_datatype(@NotNull HanaParser.Array_datatypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#array_datatype}.
	 * @param ctx the parse tree
	 */
	void exitArray_datatype(@NotNull HanaParser.Array_datatypeContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#loop_statement}.
	 * @param ctx the parse tree
	 */
	void enterLoop_statement(@NotNull HanaParser.Loop_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#loop_statement}.
	 * @param ctx the parse tree
	 */
	void exitLoop_statement(@NotNull HanaParser.Loop_statementContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#between_bound}.
	 * @param ctx the parse tree
	 */
	void enterBetween_bound(@NotNull HanaParser.Between_boundContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#between_bound}.
	 * @param ctx the parse tree
	 */
	void exitBetween_bound(@NotNull HanaParser.Between_boundContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#open_for_statement}.
	 * @param ctx the parse tree
	 */
	void enterOpen_for_statement(@NotNull HanaParser.Open_for_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#open_for_statement}.
	 * @param ctx the parse tree
	 */
	void exitOpen_for_statement(@NotNull HanaParser.Open_for_statementContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#subtype_declaration}.
	 * @param ctx the parse tree
	 */
	void enterSubtype_declaration(@NotNull HanaParser.Subtype_declarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#subtype_declaration}.
	 * @param ctx the parse tree
	 */
	void exitSubtype_declaration(@NotNull HanaParser.Subtype_declarationContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#alter_attribute_definition}.
	 * @param ctx the parse tree
	 */
	void enterAlter_attribute_definition(@NotNull HanaParser.Alter_attribute_definitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#alter_attribute_definition}.
	 * @param ctx the parse tree
	 */
	void exitAlter_attribute_definition(@NotNull HanaParser.Alter_attribute_definitionContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#create_func_body}.
	 * @param ctx the parse tree
	 */
	void enterCreate_func_body(@NotNull HanaParser.Create_func_bodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#create_func_body}.
	 * @param ctx the parse tree
	 */
	void exitCreate_func_body(@NotNull HanaParser.Create_func_bodyContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#rollup_cube_clause}.
	 * @param ctx the parse tree
	 */
	void enterRollup_cube_clause(@NotNull HanaParser.Rollup_cube_clauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#rollup_cube_clause}.
	 * @param ctx the parse tree
	 */
	void exitRollup_cube_clause(@NotNull HanaParser.Rollup_cube_clauseContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#case_else_part}.
	 * @param ctx the parse tree
	 */
	void enterCase_else_part(@NotNull HanaParser.Case_else_partContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#case_else_part}.
	 * @param ctx the parse tree
	 */
	void exitCase_else_part(@NotNull HanaParser.Case_else_partContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#precision_part}.
	 * @param ctx the parse tree
	 */
	void enterPrecision_part(@NotNull HanaParser.Precision_partContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#precision_part}.
	 * @param ctx the parse tree
	 */
	void exitPrecision_part(@NotNull HanaParser.Precision_partContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#dependent_handling_clause}.
	 * @param ctx the parse tree
	 */
	void enterDependent_handling_clause(@NotNull HanaParser.Dependent_handling_clauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#dependent_handling_clause}.
	 * @param ctx the parse tree
	 */
	void exitDependent_handling_clause(@NotNull HanaParser.Dependent_handling_clauseContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#like_escape_part}.
	 * @param ctx the parse tree
	 */
	void enterLike_escape_part(@NotNull HanaParser.Like_escape_partContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#like_escape_part}.
	 * @param ctx the parse tree
	 */
	void exitLike_escape_part(@NotNull HanaParser.Like_escape_partContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#func_decl_in_type}.
	 * @param ctx the parse tree
	 */
	void enterFunc_decl_in_type(@NotNull HanaParser.Func_decl_in_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#func_decl_in_type}.
	 * @param ctx the parse tree
	 */
	void exitFunc_decl_in_type(@NotNull HanaParser.Func_decl_in_typeContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#for_update_of_part}.
	 * @param ctx the parse tree
	 */
	void enterFor_update_of_part(@NotNull HanaParser.For_update_of_partContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#for_update_of_part}.
	 * @param ctx the parse tree
	 */
	void exitFor_update_of_part(@NotNull HanaParser.For_update_of_partContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#pivot_element}.
	 * @param ctx the parse tree
	 */
	void enterPivot_element(@NotNull HanaParser.Pivot_elementContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#pivot_element}.
	 * @param ctx the parse tree
	 */
	void exitPivot_element(@NotNull HanaParser.Pivot_elementContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#unpivot_clause}.
	 * @param ctx the parse tree
	 */
	void enterUnpivot_clause(@NotNull HanaParser.Unpivot_clauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#unpivot_clause}.
	 * @param ctx the parse tree
	 */
	void exitUnpivot_clause(@NotNull HanaParser.Unpivot_clauseContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(@NotNull HanaParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(@NotNull HanaParser.BlockContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#id}.
	 * @param ctx the parse tree
	 */
	void enterId(@NotNull HanaParser.IdContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#id}.
	 * @param ctx the parse tree
	 */
	void exitId(@NotNull HanaParser.IdContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#object_as_part}.
	 * @param ctx the parse tree
	 */
	void enterObject_as_part(@NotNull HanaParser.Object_as_partContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#object_as_part}.
	 * @param ctx the parse tree
	 */
	void exitObject_as_part(@NotNull HanaParser.Object_as_partContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#parameter_spec}.
	 * @param ctx the parse tree
	 */
	void enterParameter_spec(@NotNull HanaParser.Parameter_specContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#parameter_spec}.
	 * @param ctx the parse tree
	 */
	void exitParameter_spec(@NotNull HanaParser.Parameter_specContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#savepoint_statement}.
	 * @param ctx the parse tree
	 */
	void enterSavepoint_statement(@NotNull HanaParser.Savepoint_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#savepoint_statement}.
	 * @param ctx the parse tree
	 */
	void exitSavepoint_statement(@NotNull HanaParser.Savepoint_statementContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#until_part}.
	 * @param ctx the parse tree
	 */
	void enterUntil_part(@NotNull HanaParser.Until_partContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#until_part}.
	 * @param ctx the parse tree
	 */
	void exitUntil_part(@NotNull HanaParser.Until_partContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#column_list_definition}.
	 * @param ctx the parse tree
	 */
	void enterColumn_list_definition(@NotNull HanaParser.Column_list_definitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#column_list_definition}.
	 * @param ctx the parse tree
	 */
	void exitColumn_list_definition(@NotNull HanaParser.Column_list_definitionContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#multiset_type}.
	 * @param ctx the parse tree
	 */
	void enterMultiset_type(@NotNull HanaParser.Multiset_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#multiset_type}.
	 * @param ctx the parse tree
	 */
	void exitMultiset_type(@NotNull HanaParser.Multiset_typeContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#as_col_names}.
	 * @param ctx the parse tree
	 */
	void enterAs_col_names(@NotNull HanaParser.As_col_namesContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#as_col_names}.
	 * @param ctx the parse tree
	 */
	void exitAs_col_names(@NotNull HanaParser.As_col_namesContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#exception_handler}.
	 * @param ctx the parse tree
	 */
	void enterException_handler(@NotNull HanaParser.Exception_handlerContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#exception_handler}.
	 * @param ctx the parse tree
	 */
	void exitException_handler(@NotNull HanaParser.Exception_handlerContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#null_statement}.
	 * @param ctx the parse tree
	 */
	void enterNull_statement(@NotNull HanaParser.Null_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#null_statement}.
	 * @param ctx the parse tree
	 */
	void exitNull_statement(@NotNull HanaParser.Null_statementContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#exception_statement}.
	 * @param ctx the parse tree
	 */
	void enterException_statement(@NotNull HanaParser.Exception_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#exception_statement}.
	 * @param ctx the parse tree
	 */
	void exitException_statement(@NotNull HanaParser.Exception_statementContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#update_set_clause}.
	 * @param ctx the parse tree
	 */
	void enterUpdate_set_clause(@NotNull HanaParser.Update_set_clauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#update_set_clause}.
	 * @param ctx the parse tree
	 */
	void exitUpdate_set_clause(@NotNull HanaParser.Update_set_clauseContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#group_by_elements}.
	 * @param ctx the parse tree
	 */
	void enterGroup_by_elements(@NotNull HanaParser.Group_by_elementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#group_by_elements}.
	 * @param ctx the parse tree
	 */
	void exitGroup_by_elements(@NotNull HanaParser.Group_by_elementsContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#static_returning_clause}.
	 * @param ctx the parse tree
	 */
	void enterStatic_returning_clause(@NotNull HanaParser.Static_returning_clauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#static_returning_clause}.
	 * @param ctx the parse tree
	 */
	void exitStatic_returning_clause(@NotNull HanaParser.Static_returning_clauseContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#agg_name}.
	 * @param ctx the parse tree
	 */
	void enterAgg_name(@NotNull HanaParser.Agg_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#agg_name}.
	 * @param ctx the parse tree
	 */
	void exitAgg_name(@NotNull HanaParser.Agg_nameContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#pivot_for_clause}.
	 * @param ctx the parse tree
	 */
	void enterPivot_for_clause(@NotNull HanaParser.Pivot_for_clauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#pivot_for_clause}.
	 * @param ctx the parse tree
	 */
	void exitPivot_for_clause(@NotNull HanaParser.Pivot_for_clauseContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#exception_name}.
	 * @param ctx the parse tree
	 */
	void enterException_name(@NotNull HanaParser.Exception_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#exception_name}.
	 * @param ctx the parse tree
	 */
	void exitException_name(@NotNull HanaParser.Exception_nameContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#subquery_basic_elements}.
	 * @param ctx the parse tree
	 */
	void enterSubquery_basic_elements(@NotNull HanaParser.Subquery_basic_elementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#subquery_basic_elements}.
	 * @param ctx the parse tree
	 */
	void exitSubquery_basic_elements(@NotNull HanaParser.Subquery_basic_elementsContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#if_statement}.
	 * @param ctx the parse tree
	 */
	void enterIf_statement(@NotNull HanaParser.If_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#if_statement}.
	 * @param ctx the parse tree
	 */
	void exitIf_statement(@NotNull HanaParser.If_statementContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#model_column_clauses}.
	 * @param ctx the parse tree
	 */
	void enterModel_column_clauses(@NotNull HanaParser.Model_column_clausesContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#model_column_clauses}.
	 * @param ctx the parse tree
	 */
	void exitModel_column_clauses(@NotNull HanaParser.Model_column_clausesContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#function_argument_analytic}.
	 * @param ctx the parse tree
	 */
	void enterFunction_argument_analytic(@NotNull HanaParser.Function_argument_analyticContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#function_argument_analytic}.
	 * @param ctx the parse tree
	 */
	void exitFunction_argument_analytic(@NotNull HanaParser.Function_argument_analyticContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#proc_block_option}.
	 * @param ctx the parse tree
	 */
	void enterProc_block_option(@NotNull HanaParser.Proc_block_optionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#proc_block_option}.
	 * @param ctx the parse tree
	 */
	void exitProc_block_option(@NotNull HanaParser.Proc_block_optionContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#pragma_clause}.
	 * @param ctx the parse tree
	 */
	void enterPragma_clause(@NotNull HanaParser.Pragma_clauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#pragma_clause}.
	 * @param ctx the parse tree
	 */
	void exitPragma_clause(@NotNull HanaParser.Pragma_clauseContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#security_mode}.
	 * @param ctx the parse tree
	 */
	void enterSecurity_mode(@NotNull HanaParser.Security_modeContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#security_mode}.
	 * @param ctx the parse tree
	 */
	void exitSecurity_mode(@NotNull HanaParser.Security_modeContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#set_schema}.
	 * @param ctx the parse tree
	 */
	void enterSet_schema(@NotNull HanaParser.Set_schemaContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#set_schema}.
	 * @param ctx the parse tree
	 */
	void exitSet_schema(@NotNull HanaParser.Set_schemaContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#case_statement}.
	 * @param ctx the parse tree
	 */
	void enterCase_statement(@NotNull HanaParser.Case_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#case_statement}.
	 * @param ctx the parse tree
	 */
	void exitCase_statement(@NotNull HanaParser.Case_statementContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#values_clause}.
	 * @param ctx the parse tree
	 */
	void enterValues_clause(@NotNull HanaParser.Values_clauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#values_clause}.
	 * @param ctx the parse tree
	 */
	void exitValues_clause(@NotNull HanaParser.Values_clauseContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#function_expression_}.
	 * @param ctx the parse tree
	 */
	void enterFunction_expression_(@NotNull HanaParser.Function_expression_Context ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#function_expression_}.
	 * @param ctx the parse tree
	 */
	void exitFunction_expression_(@NotNull HanaParser.Function_expression_Context ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#sequence_name}.
	 * @param ctx the parse tree
	 */
	void enterSequence_name(@NotNull HanaParser.Sequence_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#sequence_name}.
	 * @param ctx the parse tree
	 */
	void exitSequence_name(@NotNull HanaParser.Sequence_nameContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#wait_nowait_part}.
	 * @param ctx the parse tree
	 */
	void enterWait_nowait_part(@NotNull HanaParser.Wait_nowait_partContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#wait_nowait_part}.
	 * @param ctx the parse tree
	 */
	void exitWait_nowait_part(@NotNull HanaParser.Wait_nowait_partContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#cell_assignment}.
	 * @param ctx the parse tree
	 */
	void enterCell_assignment(@NotNull HanaParser.Cell_assignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#cell_assignment}.
	 * @param ctx the parse tree
	 */
	void exitCell_assignment(@NotNull HanaParser.Cell_assignmentContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#create_procedure_body}.
	 * @param ctx the parse tree
	 */
	void enterCreate_procedure_body(@NotNull HanaParser.Create_procedure_bodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#create_procedure_body}.
	 * @param ctx the parse tree
	 */
	void exitCreate_procedure_body(@NotNull HanaParser.Create_procedure_bodyContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#call_spec}.
	 * @param ctx the parse tree
	 */
	void enterCall_spec(@NotNull HanaParser.Call_specContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#call_spec}.
	 * @param ctx the parse tree
	 */
	void exitCall_spec(@NotNull HanaParser.Call_specContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#attribute_name}.
	 * @param ctx the parse tree
	 */
	void enterAttribute_name(@NotNull HanaParser.Attribute_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#attribute_name}.
	 * @param ctx the parse tree
	 */
	void exitAttribute_name(@NotNull HanaParser.Attribute_nameContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#commit_statement}.
	 * @param ctx the parse tree
	 */
	void enterCommit_statement(@NotNull HanaParser.Commit_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#commit_statement}.
	 * @param ctx the parse tree
	 */
	void exitCommit_statement(@NotNull HanaParser.Commit_statementContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#label_name}.
	 * @param ctx the parse tree
	 */
	void enterLabel_name(@NotNull HanaParser.Label_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#label_name}.
	 * @param ctx the parse tree
	 */
	void exitLabel_name(@NotNull HanaParser.Label_nameContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#index_name}.
	 * @param ctx the parse tree
	 */
	void enterIndex_name(@NotNull HanaParser.Index_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#index_name}.
	 * @param ctx the parse tree
	 */
	void exitIndex_name(@NotNull HanaParser.Index_nameContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#pivot_in_clause_element}.
	 * @param ctx the parse tree
	 */
	void enterPivot_in_clause_element(@NotNull HanaParser.Pivot_in_clause_elementContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#pivot_in_clause_element}.
	 * @param ctx the parse tree
	 */
	void exitPivot_in_clause_element(@NotNull HanaParser.Pivot_in_clause_elementContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#dml_table_expression_clause}.
	 * @param ctx the parse tree
	 */
	void enterDml_table_expression_clause(@NotNull HanaParser.Dml_table_expression_clauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#dml_table_expression_clause}.
	 * @param ctx the parse tree
	 */
	void exitDml_table_expression_clause(@NotNull HanaParser.Dml_table_expression_clauseContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#object_under_part}.
	 * @param ctx the parse tree
	 */
	void enterObject_under_part(@NotNull HanaParser.Object_under_partContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#object_under_part}.
	 * @param ctx the parse tree
	 */
	void exitObject_under_part(@NotNull HanaParser.Object_under_partContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#cursor_name_old}.
	 * @param ctx the parse tree
	 */
	void enterCursor_name_old(@NotNull HanaParser.Cursor_name_oldContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#cursor_name_old}.
	 * @param ctx the parse tree
	 */
	void exitCursor_name_old(@NotNull HanaParser.Cursor_name_oldContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#assignment_statement}.
	 * @param ctx the parse tree
	 */
	void enterAssignment_statement(@NotNull HanaParser.Assignment_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#assignment_statement}.
	 * @param ctx the parse tree
	 */
	void exitAssignment_statement(@NotNull HanaParser.Assignment_statementContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#unit_statement}.
	 * @param ctx the parse tree
	 */
	void enterUnit_statement(@NotNull HanaParser.Unit_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#unit_statement}.
	 * @param ctx the parse tree
	 */
	void exitUnit_statement(@NotNull HanaParser.Unit_statementContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#type_body}.
	 * @param ctx the parse tree
	 */
	void enterType_body(@NotNull HanaParser.Type_bodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#type_body}.
	 * @param ctx the parse tree
	 */
	void exitType_body(@NotNull HanaParser.Type_bodyContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#named_param}.
	 * @param ctx the parse tree
	 */
	void enterNamed_param(@NotNull HanaParser.Named_paramContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#named_param}.
	 * @param ctx the parse tree
	 */
	void exitNamed_param(@NotNull HanaParser.Named_paramContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#proc_sql}.
	 * @param ctx the parse tree
	 */
	void enterProc_sql(@NotNull HanaParser.Proc_sqlContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#proc_sql}.
	 * @param ctx the parse tree
	 */
	void exitProc_sql(@NotNull HanaParser.Proc_sqlContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#return_rows_clause}.
	 * @param ctx the parse tree
	 */
	void enterReturn_rows_clause(@NotNull HanaParser.Return_rows_clauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#return_rows_clause}.
	 * @param ctx the parse tree
	 */
	void exitReturn_rows_clause(@NotNull HanaParser.Return_rows_clauseContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#quoted_string}.
	 * @param ctx the parse tree
	 */
	void enterQuoted_string(@NotNull HanaParser.Quoted_stringContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#quoted_string}.
	 * @param ctx the parse tree
	 */
	void exitQuoted_string(@NotNull HanaParser.Quoted_stringContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#variable_name_list}.
	 * @param ctx the parse tree
	 */
	void enterVariable_name_list(@NotNull HanaParser.Variable_name_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#variable_name_list}.
	 * @param ctx the parse tree
	 */
	void exitVariable_name_list(@NotNull HanaParser.Variable_name_listContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#proc_stmt}.
	 * @param ctx the parse tree
	 */
	void enterProc_stmt(@NotNull HanaParser.Proc_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#proc_stmt}.
	 * @param ctx the parse tree
	 */
	void exitProc_stmt(@NotNull HanaParser.Proc_stmtContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#null_predicate}.
	 * @param ctx the parse tree
	 */
	void enterNull_predicate(@NotNull HanaParser.Null_predicateContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#null_predicate}.
	 * @param ctx the parse tree
	 */
	void exitNull_predicate(@NotNull HanaParser.Null_predicateContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#relational_expression}.
	 * @param ctx the parse tree
	 */
	void enterRelational_expression(@NotNull HanaParser.Relational_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#relational_expression}.
	 * @param ctx the parse tree
	 */
	void exitRelational_expression(@NotNull HanaParser.Relational_expressionContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterAtom(@NotNull HanaParser.AtomContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitAtom(@NotNull HanaParser.AtomContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#field_spec}.
	 * @param ctx the parse tree
	 */
	void enterField_spec(@NotNull HanaParser.Field_specContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#field_spec}.
	 * @param ctx the parse tree
	 */
	void exitField_spec(@NotNull HanaParser.Field_specContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#constant}.
	 * @param ctx the parse tree
	 */
	void enterConstant(@NotNull HanaParser.ConstantContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#constant}.
	 * @param ctx the parse tree
	 */
	void exitConstant(@NotNull HanaParser.ConstantContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#sql_statement}.
	 * @param ctx the parse tree
	 */
	void enterSql_statement(@NotNull HanaParser.Sql_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#sql_statement}.
	 * @param ctx the parse tree
	 */
	void exitSql_statement(@NotNull HanaParser.Sql_statementContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#conditional_insert_when_part}.
	 * @param ctx the parse tree
	 */
	void enterConditional_insert_when_part(@NotNull HanaParser.Conditional_insert_when_partContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#conditional_insert_when_part}.
	 * @param ctx the parse tree
	 */
	void exitConditional_insert_when_part(@NotNull HanaParser.Conditional_insert_when_partContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#multiply_expression}.
	 * @param ctx the parse tree
	 */
	void enterMultiply_expression(@NotNull HanaParser.Multiply_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#multiply_expression}.
	 * @param ctx the parse tree
	 */
	void exitMultiply_expression(@NotNull HanaParser.Multiply_expressionContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#within_or_over_clause_keyword}.
	 * @param ctx the parse tree
	 */
	void enterWithin_or_over_clause_keyword(@NotNull HanaParser.Within_or_over_clause_keywordContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#within_or_over_clause_keyword}.
	 * @param ctx the parse tree
	 */
	void exitWithin_or_over_clause_keyword(@NotNull HanaParser.Within_or_over_clause_keywordContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#body}.
	 * @param ctx the parse tree
	 */
	void enterBody(@NotNull HanaParser.BodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#body}.
	 * @param ctx the parse tree
	 */
	void exitBody(@NotNull HanaParser.BodyContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#concatenation_wrapper}.
	 * @param ctx the parse tree
	 */
	void enterConcatenation_wrapper(@NotNull HanaParser.Concatenation_wrapperContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#concatenation_wrapper}.
	 * @param ctx the parse tree
	 */
	void exitConcatenation_wrapper(@NotNull HanaParser.Concatenation_wrapperContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#cycle_clause}.
	 * @param ctx the parse tree
	 */
	void enterCycle_clause(@NotNull HanaParser.Cycle_clauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#cycle_clause}.
	 * @param ctx the parse tree
	 */
	void exitCycle_clause(@NotNull HanaParser.Cycle_clauseContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#proc_decl}.
	 * @param ctx the parse tree
	 */
	void enterProc_decl(@NotNull HanaParser.Proc_declContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#proc_decl}.
	 * @param ctx the parse tree
	 */
	void exitProc_decl(@NotNull HanaParser.Proc_declContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#explain_statement}.
	 * @param ctx the parse tree
	 */
	void enterExplain_statement(@NotNull HanaParser.Explain_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#explain_statement}.
	 * @param ctx the parse tree
	 */
	void exitExplain_statement(@NotNull HanaParser.Explain_statementContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#record_var_dec}.
	 * @param ctx the parse tree
	 */
	void enterRecord_var_dec(@NotNull HanaParser.Record_var_decContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#record_var_dec}.
	 * @param ctx the parse tree
	 */
	void exitRecord_var_dec(@NotNull HanaParser.Record_var_decContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#rollback_segment_name}.
	 * @param ctx the parse tree
	 */
	void enterRollback_segment_name(@NotNull HanaParser.Rollback_segment_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#rollback_segment_name}.
	 * @param ctx the parse tree
	 */
	void exitRollback_segment_name(@NotNull HanaParser.Rollback_segment_nameContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#proc_signal}.
	 * @param ctx the parse tree
	 */
	void enterProc_signal(@NotNull HanaParser.Proc_signalContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#proc_signal}.
	 * @param ctx the parse tree
	 */
	void exitProc_signal(@NotNull HanaParser.Proc_signalContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#xmlserialize_param_version_part}.
	 * @param ctx the parse tree
	 */
	void enterXmlserialize_param_version_part(@NotNull HanaParser.Xmlserialize_param_version_partContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#xmlserialize_param_version_part}.
	 * @param ctx the parse tree
	 */
	void exitXmlserialize_param_version_part(@NotNull HanaParser.Xmlserialize_param_version_partContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#default_schema_name}.
	 * @param ctx the parse tree
	 */
	void enterDefault_schema_name(@NotNull HanaParser.Default_schema_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#default_schema_name}.
	 * @param ctx the parse tree
	 */
	void exitDefault_schema_name(@NotNull HanaParser.Default_schema_nameContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#pivot_clause}.
	 * @param ctx the parse tree
	 */
	void enterPivot_clause(@NotNull HanaParser.Pivot_clauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#pivot_clause}.
	 * @param ctx the parse tree
	 */
	void exitPivot_clause(@NotNull HanaParser.Pivot_clauseContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#negated_expression}.
	 * @param ctx the parse tree
	 */
	void enterNegated_expression(@NotNull HanaParser.Negated_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#negated_expression}.
	 * @param ctx the parse tree
	 */
	void exitNegated_expression(@NotNull HanaParser.Negated_expressionContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#schema_name}.
	 * @param ctx the parse tree
	 */
	void enterSchema_name(@NotNull HanaParser.Schema_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#schema_name}.
	 * @param ctx the parse tree
	 */
	void exitSchema_name(@NotNull HanaParser.Schema_nameContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#const_sql_error_message}.
	 * @param ctx the parse tree
	 */
	void enterConst_sql_error_message(@NotNull HanaParser.Const_sql_error_messageContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#const_sql_error_message}.
	 * @param ctx the parse tree
	 */
	void exitConst_sql_error_message(@NotNull HanaParser.Const_sql_error_messageContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#proc_variable}.
	 * @param ctx the parse tree
	 */
	void enterProc_variable(@NotNull HanaParser.Proc_variableContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#proc_variable}.
	 * @param ctx the parse tree
	 */
	void exitProc_variable(@NotNull HanaParser.Proc_variableContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterCondition(@NotNull HanaParser.ConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitCondition(@NotNull HanaParser.ConditionContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#object_type_def}.
	 * @param ctx the parse tree
	 */
	void enterObject_type_def(@NotNull HanaParser.Object_type_defContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#object_type_def}.
	 * @param ctx the parse tree
	 */
	void exitObject_type_def(@NotNull HanaParser.Object_type_defContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#simple_case_when_part}.
	 * @param ctx the parse tree
	 */
	void enterSimple_case_when_part(@NotNull HanaParser.Simple_case_when_partContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#simple_case_when_part}.
	 * @param ctx the parse tree
	 */
	void exitSimple_case_when_part(@NotNull HanaParser.Simple_case_when_partContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#subprog_decl_in_type}.
	 * @param ctx the parse tree
	 */
	void enterSubprog_decl_in_type(@NotNull HanaParser.Subprog_decl_in_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#subprog_decl_in_type}.
	 * @param ctx the parse tree
	 */
	void exitSubprog_decl_in_type(@NotNull HanaParser.Subprog_decl_in_typeContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#table_indexed_by_part}.
	 * @param ctx the parse tree
	 */
	void enterTable_indexed_by_part(@NotNull HanaParser.Table_indexed_by_partContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#table_indexed_by_part}.
	 * @param ctx the parse tree
	 */
	void exitTable_indexed_by_part(@NotNull HanaParser.Table_indexed_by_partContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#table_alias}.
	 * @param ctx the parse tree
	 */
	void enterTable_alias(@NotNull HanaParser.Table_aliasContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#table_alias}.
	 * @param ctx the parse tree
	 */
	void exitTable_alias(@NotNull HanaParser.Table_aliasContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#equality_expression}.
	 * @param ctx the parse tree
	 */
	void enterEquality_expression(@NotNull HanaParser.Equality_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#equality_expression}.
	 * @param ctx the parse tree
	 */
	void exitEquality_expression(@NotNull HanaParser.Equality_expressionContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#windowing_type}.
	 * @param ctx the parse tree
	 */
	void enterWindowing_type(@NotNull HanaParser.Windowing_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#windowing_type}.
	 * @param ctx the parse tree
	 */
	void exitWindowing_type(@NotNull HanaParser.Windowing_typeContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#xml_general_default_part}.
	 * @param ctx the parse tree
	 */
	void enterXml_general_default_part(@NotNull HanaParser.Xml_general_default_partContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#xml_general_default_part}.
	 * @param ctx the parse tree
	 */
	void exitXml_general_default_part(@NotNull HanaParser.Xml_general_default_partContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#reference_model}.
	 * @param ctx the parse tree
	 */
	void enterReference_model(@NotNull HanaParser.Reference_modelContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#reference_model}.
	 * @param ctx the parse tree
	 */
	void exitReference_model(@NotNull HanaParser.Reference_modelContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#model_rules_clause}.
	 * @param ctx the parse tree
	 */
	void enterModel_rules_clause(@NotNull HanaParser.Model_rules_clauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#model_rules_clause}.
	 * @param ctx the parse tree
	 */
	void exitModel_rules_clause(@NotNull HanaParser.Model_rules_clauseContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#sequence_start_clause}.
	 * @param ctx the parse tree
	 */
	void enterSequence_start_clause(@NotNull HanaParser.Sequence_start_clauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#sequence_start_clause}.
	 * @param ctx the parse tree
	 */
	void exitSequence_start_clause(@NotNull HanaParser.Sequence_start_clauseContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#seq_of_statements}.
	 * @param ctx the parse tree
	 */
	void enterSeq_of_statements(@NotNull HanaParser.Seq_of_statementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#seq_of_statements}.
	 * @param ctx the parse tree
	 */
	void exitSeq_of_statements(@NotNull HanaParser.Seq_of_statementsContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#routine_name}.
	 * @param ctx the parse tree
	 */
	void enterRoutine_name(@NotNull HanaParser.Routine_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#routine_name}.
	 * @param ctx the parse tree
	 */
	void exitRoutine_name(@NotNull HanaParser.Routine_nameContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#model_rules_part}.
	 * @param ctx the parse tree
	 */
	void enterModel_rules_part(@NotNull HanaParser.Model_rules_partContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#model_rules_part}.
	 * @param ctx the parse tree
	 */
	void exitModel_rules_part(@NotNull HanaParser.Model_rules_partContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#simple_case_statement}.
	 * @param ctx the parse tree
	 */
	void enterSimple_case_statement(@NotNull HanaParser.Simple_case_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#simple_case_statement}.
	 * @param ctx the parse tree
	 */
	void exitSimple_case_statement(@NotNull HanaParser.Simple_case_statementContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#close_statement}.
	 * @param ctx the parse tree
	 */
	void enterClose_statement(@NotNull HanaParser.Close_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#close_statement}.
	 * @param ctx the parse tree
	 */
	void exitClose_statement(@NotNull HanaParser.Close_statementContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#table_collection_expression}.
	 * @param ctx the parse tree
	 */
	void enterTable_collection_expression(@NotNull HanaParser.Table_collection_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#table_collection_expression}.
	 * @param ctx the parse tree
	 */
	void exitTable_collection_expression(@NotNull HanaParser.Table_collection_expressionContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#table_ref_aux}.
	 * @param ctx the parse tree
	 */
	void enterTable_ref_aux(@NotNull HanaParser.Table_ref_auxContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#table_ref_aux}.
	 * @param ctx the parse tree
	 */
	void exitTable_ref_aux(@NotNull HanaParser.Table_ref_auxContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#expression_list}.
	 * @param ctx the parse tree
	 */
	void enterExpression_list(@NotNull HanaParser.Expression_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#expression_list}.
	 * @param ctx the parse tree
	 */
	void exitExpression_list(@NotNull HanaParser.Expression_listContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#vector_expr}.
	 * @param ctx the parse tree
	 */
	void enterVector_expr(@NotNull HanaParser.Vector_exprContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#vector_expr}.
	 * @param ctx the parse tree
	 */
	void exitVector_expr(@NotNull HanaParser.Vector_exprContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#signal_name}.
	 * @param ctx the parse tree
	 */
	void enterSignal_name(@NotNull HanaParser.Signal_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#signal_name}.
	 * @param ctx the parse tree
	 */
	void exitSignal_name(@NotNull HanaParser.Signal_nameContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#sql_script}.
	 * @param ctx the parse tree
	 */
	void enterSql_script(@NotNull HanaParser.Sql_scriptContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#sql_script}.
	 * @param ctx the parse tree
	 */
	void exitSql_script(@NotNull HanaParser.Sql_scriptContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#variable_name}.
	 * @param ctx the parse tree
	 */
	void enterVariable_name(@NotNull HanaParser.Variable_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#variable_name}.
	 * @param ctx the parse tree
	 */
	void exitVariable_name(@NotNull HanaParser.Variable_nameContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#lang}.
	 * @param ctx the parse tree
	 */
	void enterLang(@NotNull HanaParser.LangContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#lang}.
	 * @param ctx the parse tree
	 */
	void exitLang(@NotNull HanaParser.LangContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#table_ref}.
	 * @param ctx the parse tree
	 */
	void enterTable_ref(@NotNull HanaParser.Table_refContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#table_ref}.
	 * @param ctx the parse tree
	 */
	void exitTable_ref(@NotNull HanaParser.Table_refContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#view_name}.
	 * @param ctx the parse tree
	 */
	void enterView_name(@NotNull HanaParser.View_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#view_name}.
	 * @param ctx the parse tree
	 */
	void exitView_name(@NotNull HanaParser.View_nameContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#comparison_predicate}.
	 * @param ctx the parse tree
	 */
	void enterComparison_predicate(@NotNull HanaParser.Comparison_predicateContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#comparison_predicate}.
	 * @param ctx the parse tree
	 */
	void exitComparison_predicate(@NotNull HanaParser.Comparison_predicateContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#sqlj_object_type_attr}.
	 * @param ctx the parse tree
	 */
	void enterSqlj_object_type_attr(@NotNull HanaParser.Sqlj_object_type_attrContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#sqlj_object_type_attr}.
	 * @param ctx the parse tree
	 */
	void exitSqlj_object_type_attr(@NotNull HanaParser.Sqlj_object_type_attrContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#multi_table_element}.
	 * @param ctx the parse tree
	 */
	void enterMulti_table_element(@NotNull HanaParser.Multi_table_elementContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#multi_table_element}.
	 * @param ctx the parse tree
	 */
	void exitMulti_table_element(@NotNull HanaParser.Multi_table_elementContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#record_declaration}.
	 * @param ctx the parse tree
	 */
	void enterRecord_declaration(@NotNull HanaParser.Record_declarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#record_declaration}.
	 * @param ctx the parse tree
	 */
	void exitRecord_declaration(@NotNull HanaParser.Record_declarationContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#over_clause}.
	 * @param ctx the parse tree
	 */
	void enterOver_clause(@NotNull HanaParser.Over_clauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#over_clause}.
	 * @param ctx the parse tree
	 */
	void exitOver_clause(@NotNull HanaParser.Over_clauseContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#swallow_to_semi}.
	 * @param ctx the parse tree
	 */
	void enterSwallow_to_semi(@NotNull HanaParser.Swallow_to_semiContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#swallow_to_semi}.
	 * @param ctx the parse tree
	 */
	void exitSwallow_to_semi(@NotNull HanaParser.Swallow_to_semiContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#compound_expression}.
	 * @param ctx the parse tree
	 */
	void enterCompound_expression(@NotNull HanaParser.Compound_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#compound_expression}.
	 * @param ctx the parse tree
	 */
	void exitCompound_expression(@NotNull HanaParser.Compound_expressionContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#merge_insert_clause}.
	 * @param ctx the parse tree
	 */
	void enterMerge_insert_clause(@NotNull HanaParser.Merge_insert_clauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#merge_insert_clause}.
	 * @param ctx the parse tree
	 */
	void exitMerge_insert_clause(@NotNull HanaParser.Merge_insert_clauseContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#keep_clause}.
	 * @param ctx the parse tree
	 */
	void enterKeep_clause(@NotNull HanaParser.Keep_clauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#keep_clause}.
	 * @param ctx the parse tree
	 */
	void exitKeep_clause(@NotNull HanaParser.Keep_clauseContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#proc_assign}.
	 * @param ctx the parse tree
	 */
	void enterProc_assign(@NotNull HanaParser.Proc_assignContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#proc_assign}.
	 * @param ctx the parse tree
	 */
	void exitProc_assign(@NotNull HanaParser.Proc_assignContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#execute_immediate}.
	 * @param ctx the parse tree
	 */
	void enterExecute_immediate(@NotNull HanaParser.Execute_immediateContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#execute_immediate}.
	 * @param ctx the parse tree
	 */
	void exitExecute_immediate(@NotNull HanaParser.Execute_immediateContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#param_name}.
	 * @param ctx the parse tree
	 */
	void enterParam_name(@NotNull HanaParser.Param_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#param_name}.
	 * @param ctx the parse tree
	 */
	void exitParam_name(@NotNull HanaParser.Param_nameContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#exception_declaration}.
	 * @param ctx the parse tree
	 */
	void enterException_declaration(@NotNull HanaParser.Exception_declarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#exception_declaration}.
	 * @param ctx the parse tree
	 */
	void exitException_declaration(@NotNull HanaParser.Exception_declarationContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#type_function_spec}.
	 * @param ctx the parse tree
	 */
	void enterType_function_spec(@NotNull HanaParser.Type_function_specContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#type_function_spec}.
	 * @param ctx the parse tree
	 */
	void exitType_function_spec(@NotNull HanaParser.Type_function_specContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#dynamic_returning_clause}.
	 * @param ctx the parse tree
	 */
	void enterDynamic_returning_clause(@NotNull HanaParser.Dynamic_returning_clauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#dynamic_returning_clause}.
	 * @param ctx the parse tree
	 */
	void exitDynamic_returning_clause(@NotNull HanaParser.Dynamic_returning_clauseContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#tableview_name}.
	 * @param ctx the parse tree
	 */
	void enterTableview_name(@NotNull HanaParser.Tableview_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#tableview_name}.
	 * @param ctx the parse tree
	 */
	void exitTableview_name(@NotNull HanaParser.Tableview_nameContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#proc_single_assign}.
	 * @param ctx the parse tree
	 */
	void enterProc_single_assign(@NotNull HanaParser.Proc_single_assignContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#proc_single_assign}.
	 * @param ctx the parse tree
	 */
	void exitProc_single_assign(@NotNull HanaParser.Proc_single_assignContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#java_spec}.
	 * @param ctx the parse tree
	 */
	void enterJava_spec(@NotNull HanaParser.Java_specContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#java_spec}.
	 * @param ctx the parse tree
	 */
	void exitJava_spec(@NotNull HanaParser.Java_specContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#invoker_rights_clause}.
	 * @param ctx the parse tree
	 */
	void enterInvoker_rights_clause(@NotNull HanaParser.Invoker_rights_clauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#invoker_rights_clause}.
	 * @param ctx the parse tree
	 */
	void exitInvoker_rights_clause(@NotNull HanaParser.Invoker_rights_clauseContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#table_declaration}.
	 * @param ctx the parse tree
	 */
	void enterTable_declaration(@NotNull HanaParser.Table_declarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#table_declaration}.
	 * @param ctx the parse tree
	 */
	void exitTable_declaration(@NotNull HanaParser.Table_declarationContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#table_name}.
	 * @param ctx the parse tree
	 */
	void enterTable_name(@NotNull HanaParser.Table_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#table_name}.
	 * @param ctx the parse tree
	 */
	void exitTable_name(@NotNull HanaParser.Table_nameContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#predicate}.
	 * @param ctx the parse tree
	 */
	void enterPredicate(@NotNull HanaParser.PredicateContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#predicate}.
	 * @param ctx the parse tree
	 */
	void exitPredicate(@NotNull HanaParser.PredicateContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#in_predicate}.
	 * @param ctx the parse tree
	 */
	void enterIn_predicate(@NotNull HanaParser.In_predicateContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#in_predicate}.
	 * @param ctx the parse tree
	 */
	void exitIn_predicate(@NotNull HanaParser.In_predicateContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#query_block}.
	 * @param ctx the parse tree
	 */
	void enterQuery_block(@NotNull HanaParser.Query_blockContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#query_block}.
	 * @param ctx the parse tree
	 */
	void exitQuery_block(@NotNull HanaParser.Query_blockContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#for_update_options}.
	 * @param ctx the parse tree
	 */
	void enterFor_update_options(@NotNull HanaParser.For_update_optionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#for_update_options}.
	 * @param ctx the parse tree
	 */
	void exitFor_update_options(@NotNull HanaParser.For_update_optionsContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#proc_decl_in_type}.
	 * @param ctx the parse tree
	 */
	void enterProc_decl_in_type(@NotNull HanaParser.Proc_decl_in_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#proc_decl_in_type}.
	 * @param ctx the parse tree
	 */
	void exitProc_decl_in_type(@NotNull HanaParser.Proc_decl_in_typeContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#return_statement}.
	 * @param ctx the parse tree
	 */
	void enterReturn_statement(@NotNull HanaParser.Return_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#return_statement}.
	 * @param ctx the parse tree
	 */
	void exitReturn_statement(@NotNull HanaParser.Return_statementContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#type_elements_parameter}.
	 * @param ctx the parse tree
	 */
	void enterType_elements_parameter(@NotNull HanaParser.Type_elements_parameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#type_elements_parameter}.
	 * @param ctx the parse tree
	 */
	void exitType_elements_parameter(@NotNull HanaParser.Type_elements_parameterContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#condition_}.
	 * @param ctx the parse tree
	 */
	void enterCondition_(@NotNull HanaParser.Condition_Context ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#condition_}.
	 * @param ctx the parse tree
	 */
	void exitCondition_(@NotNull HanaParser.Condition_Context ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#windowing_elements}.
	 * @param ctx the parse tree
	 */
	void enterWindowing_elements(@NotNull HanaParser.Windowing_elementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#windowing_elements}.
	 * @param ctx the parse tree
	 */
	void exitWindowing_elements(@NotNull HanaParser.Windowing_elementsContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#trigger_block}.
	 * @param ctx the parse tree
	 */
	void enterTrigger_block(@NotNull HanaParser.Trigger_blockContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#trigger_block}.
	 * @param ctx the parse tree
	 */
	void exitTrigger_block(@NotNull HanaParser.Trigger_blockContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#for_like_part}.
	 * @param ctx the parse tree
	 */
	void enterFor_like_part(@NotNull HanaParser.For_like_partContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#for_like_part}.
	 * @param ctx the parse tree
	 */
	void exitFor_like_part(@NotNull HanaParser.For_like_partContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#error_logging_into_part}.
	 * @param ctx the parse tree
	 */
	void enterError_logging_into_part(@NotNull HanaParser.Error_logging_into_partContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#error_logging_into_part}.
	 * @param ctx the parse tree
	 */
	void exitError_logging_into_part(@NotNull HanaParser.Error_logging_into_partContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#merge_update_delete_part}.
	 * @param ctx the parse tree
	 */
	void enterMerge_update_delete_part(@NotNull HanaParser.Merge_update_delete_partContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#merge_update_delete_part}.
	 * @param ctx the parse tree
	 */
	void exitMerge_update_delete_part(@NotNull HanaParser.Merge_update_delete_partContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#sql_plus_command}.
	 * @param ctx the parse tree
	 */
	void enterSql_plus_command(@NotNull HanaParser.Sql_plus_commandContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#sql_plus_command}.
	 * @param ctx the parse tree
	 */
	void exitSql_plus_command(@NotNull HanaParser.Sql_plus_commandContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#elsif_part}.
	 * @param ctx the parse tree
	 */
	void enterElsif_part(@NotNull HanaParser.Elsif_partContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#elsif_part}.
	 * @param ctx the parse tree
	 */
	void exitElsif_part(@NotNull HanaParser.Elsif_partContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#map_order_func_declaration}.
	 * @param ctx the parse tree
	 */
	void enterMap_order_func_declaration(@NotNull HanaParser.Map_order_func_declarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#map_order_func_declaration}.
	 * @param ctx the parse tree
	 */
	void exitMap_order_func_declaration(@NotNull HanaParser.Map_order_func_declarationContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#proc_stmt_list}.
	 * @param ctx the parse tree
	 */
	void enterProc_stmt_list(@NotNull HanaParser.Proc_stmt_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#proc_stmt_list}.
	 * @param ctx the parse tree
	 */
	void exitProc_stmt_list(@NotNull HanaParser.Proc_stmt_listContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#select_statement}.
	 * @param ctx the parse tree
	 */
	void enterSelect_statement(@NotNull HanaParser.Select_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#select_statement}.
	 * @param ctx the parse tree
	 */
	void exitSelect_statement(@NotNull HanaParser.Select_statementContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#insert_statement}.
	 * @param ctx the parse tree
	 */
	void enterInsert_statement(@NotNull HanaParser.Insert_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#insert_statement}.
	 * @param ctx the parse tree
	 */
	void exitInsert_statement(@NotNull HanaParser.Insert_statementContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#correlation_name}.
	 * @param ctx the parse tree
	 */
	void enterCorrelation_name(@NotNull HanaParser.Correlation_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#correlation_name}.
	 * @param ctx the parse tree
	 */
	void exitCorrelation_name(@NotNull HanaParser.Correlation_nameContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#error_logging_clause}.
	 * @param ctx the parse tree
	 */
	void enterError_logging_clause(@NotNull HanaParser.Error_logging_clauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#error_logging_clause}.
	 * @param ctx the parse tree
	 */
	void exitError_logging_clause(@NotNull HanaParser.Error_logging_clauseContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#rollback_statement}.
	 * @param ctx the parse tree
	 */
	void enterRollback_statement(@NotNull HanaParser.Rollback_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#rollback_statement}.
	 * @param ctx the parse tree
	 */
	void exitRollback_statement(@NotNull HanaParser.Rollback_statementContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#query_name}.
	 * @param ctx the parse tree
	 */
	void enterQuery_name(@NotNull HanaParser.Query_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#query_name}.
	 * @param ctx the parse tree
	 */
	void exitQuery_name(@NotNull HanaParser.Query_nameContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#cursor_expression}.
	 * @param ctx the parse tree
	 */
	void enterCursor_expression(@NotNull HanaParser.Cursor_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#cursor_expression}.
	 * @param ctx the parse tree
	 */
	void exitCursor_expression(@NotNull HanaParser.Cursor_expressionContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#xml_attributes_clause}.
	 * @param ctx the parse tree
	 */
	void enterXml_attributes_clause(@NotNull HanaParser.Xml_attributes_clauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#xml_attributes_clause}.
	 * @param ctx the parse tree
	 */
	void exitXml_attributes_clause(@NotNull HanaParser.Xml_attributes_clauseContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#having_clause}.
	 * @param ctx the parse tree
	 */
	void enterHaving_clause(@NotNull HanaParser.Having_clauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#having_clause}.
	 * @param ctx the parse tree
	 */
	void exitHaving_clause(@NotNull HanaParser.Having_clauseContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#pragma_elements}.
	 * @param ctx the parse tree
	 */
	void enterPragma_elements(@NotNull HanaParser.Pragma_elementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#pragma_elements}.
	 * @param ctx the parse tree
	 */
	void exitPragma_elements(@NotNull HanaParser.Pragma_elementsContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#windowing_clause}.
	 * @param ctx the parse tree
	 */
	void enterWindowing_clause(@NotNull HanaParser.Windowing_clauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#windowing_clause}.
	 * @param ctx the parse tree
	 */
	void exitWindowing_clause(@NotNull HanaParser.Windowing_clauseContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(@NotNull HanaParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(@NotNull HanaParser.ExpressionContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#less_than_or_equals_op}.
	 * @param ctx the parse tree
	 */
	void enterLess_than_or_equals_op(@NotNull HanaParser.Less_than_or_equals_opContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#less_than_or_equals_op}.
	 * @param ctx the parse tree
	 */
	void exitLess_than_or_equals_op(@NotNull HanaParser.Less_than_or_equals_opContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#model_column_partition_part}.
	 * @param ctx the parse tree
	 */
	void enterModel_column_partition_part(@NotNull HanaParser.Model_column_partition_partContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#model_column_partition_part}.
	 * @param ctx the parse tree
	 */
	void exitModel_column_partition_part(@NotNull HanaParser.Model_column_partition_partContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#constructor_declaration}.
	 * @param ctx the parse tree
	 */
	void enterConstructor_declaration(@NotNull HanaParser.Constructor_declarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#constructor_declaration}.
	 * @param ctx the parse tree
	 */
	void exitConstructor_declaration(@NotNull HanaParser.Constructor_declarationContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#type_procedure_spec}.
	 * @param ctx the parse tree
	 */
	void enterType_procedure_spec(@NotNull HanaParser.Type_procedure_specContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#type_procedure_spec}.
	 * @param ctx the parse tree
	 */
	void exitType_procedure_spec(@NotNull HanaParser.Type_procedure_specContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#single_table_insert}.
	 * @param ctx the parse tree
	 */
	void enterSingle_table_insert(@NotNull HanaParser.Single_table_insertContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#single_table_insert}.
	 * @param ctx the parse tree
	 */
	void exitSingle_table_insert(@NotNull HanaParser.Single_table_insertContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#table_type_dec}.
	 * @param ctx the parse tree
	 */
	void enterTable_type_dec(@NotNull HanaParser.Table_type_decContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#table_type_dec}.
	 * @param ctx the parse tree
	 */
	void exitTable_type_dec(@NotNull HanaParser.Table_type_decContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#c_parameters_clause}.
	 * @param ctx the parse tree
	 */
	void enterC_parameters_clause(@NotNull HanaParser.C_parameters_clauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#c_parameters_clause}.
	 * @param ctx the parse tree
	 */
	void exitC_parameters_clause(@NotNull HanaParser.C_parameters_clauseContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#type_name}.
	 * @param ctx the parse tree
	 */
	void enterType_name(@NotNull HanaParser.Type_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#type_name}.
	 * @param ctx the parse tree
	 */
	void exitType_name(@NotNull HanaParser.Type_nameContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#whenever_command}.
	 * @param ctx the parse tree
	 */
	void enterWhenever_command(@NotNull HanaParser.Whenever_commandContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#whenever_command}.
	 * @param ctx the parse tree
	 */
	void exitWhenever_command(@NotNull HanaParser.Whenever_commandContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#column_name_list}.
	 * @param ctx the parse tree
	 */
	void enterColumn_name_list(@NotNull HanaParser.Column_name_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#column_name_list}.
	 * @param ctx the parse tree
	 */
	void exitColumn_name_list(@NotNull HanaParser.Column_name_listContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#sql_type}.
	 * @param ctx the parse tree
	 */
	void enterSql_type(@NotNull HanaParser.Sql_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#sql_type}.
	 * @param ctx the parse tree
	 */
	void exitSql_type(@NotNull HanaParser.Sql_typeContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#element_spec_options}.
	 * @param ctx the parse tree
	 */
	void enterElement_spec_options(@NotNull HanaParser.Element_spec_optionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#element_spec_options}.
	 * @param ctx the parse tree
	 */
	void exitElement_spec_options(@NotNull HanaParser.Element_spec_optionsContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#expression_or_vector}.
	 * @param ctx the parse tree
	 */
	void enterExpression_or_vector(@NotNull HanaParser.Expression_or_vectorContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#expression_or_vector}.
	 * @param ctx the parse tree
	 */
	void exitExpression_or_vector(@NotNull HanaParser.Expression_or_vectorContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#parameter_clause}.
	 * @param ctx the parse tree
	 */
	void enterParameter_clause(@NotNull HanaParser.Parameter_clauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#parameter_clause}.
	 * @param ctx the parse tree
	 */
	void exitParameter_clause(@NotNull HanaParser.Parameter_clauseContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#object_member_spec}.
	 * @param ctx the parse tree
	 */
	void enterObject_member_spec(@NotNull HanaParser.Object_member_specContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#object_member_spec}.
	 * @param ctx the parse tree
	 */
	void exitObject_member_spec(@NotNull HanaParser.Object_member_specContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#func_stmt_list}.
	 * @param ctx the parse tree
	 */
	void enterFunc_stmt_list(@NotNull HanaParser.Func_stmt_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#func_stmt_list}.
	 * @param ctx the parse tree
	 */
	void exitFunc_stmt_list(@NotNull HanaParser.Func_stmt_listContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#operator}.
	 * @param ctx the parse tree
	 */
	void enterOperator(@NotNull HanaParser.OperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#operator}.
	 * @param ctx the parse tree
	 */
	void exitOperator(@NotNull HanaParser.OperatorContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#implementation_type_name}.
	 * @param ctx the parse tree
	 */
	void enterImplementation_type_name(@NotNull HanaParser.Implementation_type_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#implementation_type_name}.
	 * @param ctx the parse tree
	 */
	void exitImplementation_type_name(@NotNull HanaParser.Implementation_type_nameContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#proc_condition_value}.
	 * @param ctx the parse tree
	 */
	void enterProc_condition_value(@NotNull HanaParser.Proc_condition_valueContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#proc_condition_value}.
	 * @param ctx the parse tree
	 */
	void exitProc_condition_value(@NotNull HanaParser.Proc_condition_valueContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#type_body_elements}.
	 * @param ctx the parse tree
	 */
	void enterType_body_elements(@NotNull HanaParser.Type_body_elementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#type_body_elements}.
	 * @param ctx the parse tree
	 */
	void exitType_body_elements(@NotNull HanaParser.Type_body_elementsContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#pivot_in_clause}.
	 * @param ctx the parse tree
	 */
	void enterPivot_in_clause(@NotNull HanaParser.Pivot_in_clauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#pivot_in_clause}.
	 * @param ctx the parse tree
	 */
	void exitPivot_in_clause(@NotNull HanaParser.Pivot_in_clauseContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#raise_statement}.
	 * @param ctx the parse tree
	 */
	void enterRaise_statement(@NotNull HanaParser.Raise_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#raise_statement}.
	 * @param ctx the parse tree
	 */
	void exitRaise_statement(@NotNull HanaParser.Raise_statementContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#delete_statement}.
	 * @param ctx the parse tree
	 */
	void enterDelete_statement(@NotNull HanaParser.Delete_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#delete_statement}.
	 * @param ctx the parse tree
	 */
	void exitDelete_statement(@NotNull HanaParser.Delete_statementContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#for_each_row}.
	 * @param ctx the parse tree
	 */
	void enterFor_each_row(@NotNull HanaParser.For_each_rowContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#for_each_row}.
	 * @param ctx the parse tree
	 */
	void exitFor_each_row(@NotNull HanaParser.For_each_rowContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#table_var_dec}.
	 * @param ctx the parse tree
	 */
	void enterTable_var_dec(@NotNull HanaParser.Table_var_decContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#table_var_dec}.
	 * @param ctx the parse tree
	 */
	void exitTable_var_dec(@NotNull HanaParser.Table_var_decContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#proc_condition}.
	 * @param ctx the parse tree
	 */
	void enterProc_condition(@NotNull HanaParser.Proc_conditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#proc_condition}.
	 * @param ctx the parse tree
	 */
	void exitProc_condition(@NotNull HanaParser.Proc_conditionContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#expression_}.
	 * @param ctx the parse tree
	 */
	void enterExpression_(@NotNull HanaParser.Expression_Context ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#expression_}.
	 * @param ctx the parse tree
	 */
	void exitExpression_(@NotNull HanaParser.Expression_Context ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#bind_sql_error_message}.
	 * @param ctx the parse tree
	 */
	void enterBind_sql_error_message(@NotNull HanaParser.Bind_sql_error_messageContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#bind_sql_error_message}.
	 * @param ctx the parse tree
	 */
	void exitBind_sql_error_message(@NotNull HanaParser.Bind_sql_error_messageContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#proc_cursor_param}.
	 * @param ctx the parse tree
	 */
	void enterProc_cursor_param(@NotNull HanaParser.Proc_cursor_paramContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#proc_cursor_param}.
	 * @param ctx the parse tree
	 */
	void exitProc_cursor_param(@NotNull HanaParser.Proc_cursor_paramContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#conditional_insert_clause}.
	 * @param ctx the parse tree
	 */
	void enterConditional_insert_clause(@NotNull HanaParser.Conditional_insert_clauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#conditional_insert_clause}.
	 * @param ctx the parse tree
	 */
	void exitConditional_insert_clause(@NotNull HanaParser.Conditional_insert_clauseContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#like_predicate}.
	 * @param ctx the parse tree
	 */
	void enterLike_predicate(@NotNull HanaParser.Like_predicateContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#like_predicate}.
	 * @param ctx the parse tree
	 */
	void exitLike_predicate(@NotNull HanaParser.Like_predicateContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#id_expression}.
	 * @param ctx the parse tree
	 */
	void enterId_expression(@NotNull HanaParser.Id_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#id_expression}.
	 * @param ctx the parse tree
	 */
	void exitId_expression(@NotNull HanaParser.Id_expressionContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#order_by_clause}.
	 * @param ctx the parse tree
	 */
	void enterOrder_by_clause(@NotNull HanaParser.Order_by_clauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#order_by_clause}.
	 * @param ctx the parse tree
	 */
	void exitOrder_by_clause(@NotNull HanaParser.Order_by_clauseContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#regular_id}.
	 * @param ctx the parse tree
	 */
	void enterRegular_id(@NotNull HanaParser.Regular_idContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#regular_id}.
	 * @param ctx the parse tree
	 */
	void exitRegular_id(@NotNull HanaParser.Regular_idContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#grouping_sets_elements}.
	 * @param ctx the parse tree
	 */
	void enterGrouping_sets_elements(@NotNull HanaParser.Grouping_sets_elementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#grouping_sets_elements}.
	 * @param ctx the parse tree
	 */
	void exitGrouping_sets_elements(@NotNull HanaParser.Grouping_sets_elementsContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#element_spec}.
	 * @param ctx the parse tree
	 */
	void enterElement_spec(@NotNull HanaParser.Element_specContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#element_spec}.
	 * @param ctx the parse tree
	 */
	void exitElement_spec(@NotNull HanaParser.Element_specContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#savepoint_name}.
	 * @param ctx the parse tree
	 */
	void enterSavepoint_name(@NotNull HanaParser.Savepoint_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#savepoint_name}.
	 * @param ctx the parse tree
	 */
	void exitSavepoint_name(@NotNull HanaParser.Savepoint_nameContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#signal_value}.
	 * @param ctx the parse tree
	 */
	void enterSignal_value(@NotNull HanaParser.Signal_valueContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#signal_value}.
	 * @param ctx the parse tree
	 */
	void exitSignal_value(@NotNull HanaParser.Signal_valueContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#trigger_name}.
	 * @param ctx the parse tree
	 */
	void enterTrigger_name(@NotNull HanaParser.Trigger_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#trigger_name}.
	 * @param ctx the parse tree
	 */
	void exitTrigger_name(@NotNull HanaParser.Trigger_nameContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#quantified_expression}.
	 * @param ctx the parse tree
	 */
	void enterQuantified_expression(@NotNull HanaParser.Quantified_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#quantified_expression}.
	 * @param ctx the parse tree
	 */
	void exitQuantified_expression(@NotNull HanaParser.Quantified_expressionContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#procedure_body_}.
	 * @param ctx the parse tree
	 */
	void enterProcedure_body_(@NotNull HanaParser.Procedure_body_Context ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#procedure_body_}.
	 * @param ctx the parse tree
	 */
	void exitProcedure_body_(@NotNull HanaParser.Procedure_body_Context ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(@NotNull HanaParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(@NotNull HanaParser.StatementContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#update_statement}.
	 * @param ctx the parse tree
	 */
	void enterUpdate_statement(@NotNull HanaParser.Update_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#update_statement}.
	 * @param ctx the parse tree
	 */
	void exitUpdate_statement(@NotNull HanaParser.Update_statementContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#model_rules_element}.
	 * @param ctx the parse tree
	 */
	void enterModel_rules_element(@NotNull HanaParser.Model_rules_elementContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#model_rules_element}.
	 * @param ctx the parse tree
	 */
	void exitModel_rules_element(@NotNull HanaParser.Model_rules_elementContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#char_set_name}.
	 * @param ctx the parse tree
	 */
	void enterChar_set_name(@NotNull HanaParser.Char_set_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#char_set_name}.
	 * @param ctx the parse tree
	 */
	void exitChar_set_name(@NotNull HanaParser.Char_set_nameContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#proc_else}.
	 * @param ctx the parse tree
	 */
	void enterProc_else(@NotNull HanaParser.Proc_elseContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#proc_else}.
	 * @param ctx the parse tree
	 */
	void exitProc_else(@NotNull HanaParser.Proc_elseContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#model_column_list}.
	 * @param ctx the parse tree
	 */
	void enterModel_column_list(@NotNull HanaParser.Model_column_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#model_column_list}.
	 * @param ctx the parse tree
	 */
	void exitModel_column_list(@NotNull HanaParser.Model_column_listContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#within_or_over_part}.
	 * @param ctx the parse tree
	 */
	void enterWithin_or_over_part(@NotNull HanaParser.Within_or_over_partContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#within_or_over_part}.
	 * @param ctx the parse tree
	 */
	void exitWithin_or_over_part(@NotNull HanaParser.Within_or_over_partContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#xml_passing_clause}.
	 * @param ctx the parse tree
	 */
	void enterXml_passing_clause(@NotNull HanaParser.Xml_passing_clauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#xml_passing_clause}.
	 * @param ctx the parse tree
	 */
	void exitXml_passing_clause(@NotNull HanaParser.Xml_passing_clauseContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#parameter_name}.
	 * @param ctx the parse tree
	 */
	void enterParameter_name(@NotNull HanaParser.Parameter_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#parameter_name}.
	 * @param ctx the parse tree
	 */
	void exitParameter_name(@NotNull HanaParser.Parameter_nameContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#pragma_declaration}.
	 * @param ctx the parse tree
	 */
	void enterPragma_declaration(@NotNull HanaParser.Pragma_declarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#pragma_declaration}.
	 * @param ctx the parse tree
	 */
	void exitPragma_declaration(@NotNull HanaParser.Pragma_declarationContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#group_by_clause}.
	 * @param ctx the parse tree
	 */
	void enterGroup_by_clause(@NotNull HanaParser.Group_by_clauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#group_by_clause}.
	 * @param ctx the parse tree
	 */
	void exitGroup_by_clause(@NotNull HanaParser.Group_by_clauseContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#main_model}.
	 * @param ctx the parse tree
	 */
	void enterMain_model(@NotNull HanaParser.Main_modelContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#main_model}.
	 * @param ctx the parse tree
	 */
	void exitMain_model(@NotNull HanaParser.Main_modelContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#outer_join_type}.
	 * @param ctx the parse tree
	 */
	void enterOuter_join_type(@NotNull HanaParser.Outer_join_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#outer_join_type}.
	 * @param ctx the parse tree
	 */
	void exitOuter_join_type(@NotNull HanaParser.Outer_join_typeContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#standard_prediction_function_keyword}.
	 * @param ctx the parse tree
	 */
	void enterStandard_prediction_function_keyword(@NotNull HanaParser.Standard_prediction_function_keywordContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#standard_prediction_function_keyword}.
	 * @param ctx the parse tree
	 */
	void exitStandard_prediction_function_keyword(@NotNull HanaParser.Standard_prediction_function_keywordContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#native_datatype_element}.
	 * @param ctx the parse tree
	 */
	void enterNative_datatype_element(@NotNull HanaParser.Native_datatype_elementContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#native_datatype_element}.
	 * @param ctx the parse tree
	 */
	void exitNative_datatype_element(@NotNull HanaParser.Native_datatype_elementContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#function_argument_modeling}.
	 * @param ctx the parse tree
	 */
	void enterFunction_argument_modeling(@NotNull HanaParser.Function_argument_modelingContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#function_argument_modeling}.
	 * @param ctx the parse tree
	 */
	void exitFunction_argument_modeling(@NotNull HanaParser.Function_argument_modelingContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#package_name}.
	 * @param ctx the parse tree
	 */
	void enterPackage_name(@NotNull HanaParser.Package_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#package_name}.
	 * @param ctx the parse tree
	 */
	void exitPackage_name(@NotNull HanaParser.Package_nameContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#using_clause}.
	 * @param ctx the parse tree
	 */
	void enterUsing_clause(@NotNull HanaParser.Using_clauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#using_clause}.
	 * @param ctx the parse tree
	 */
	void exitUsing_clause(@NotNull HanaParser.Using_clauseContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#proc_decl_list}.
	 * @param ctx the parse tree
	 */
	void enterProc_decl_list(@NotNull HanaParser.Proc_decl_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#proc_decl_list}.
	 * @param ctx the parse tree
	 */
	void exitProc_decl_list(@NotNull HanaParser.Proc_decl_listContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#constructor_spec}.
	 * @param ctx the parse tree
	 */
	void enterConstructor_spec(@NotNull HanaParser.Constructor_specContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#constructor_spec}.
	 * @param ctx the parse tree
	 */
	void exitConstructor_spec(@NotNull HanaParser.Constructor_specContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#proc_handler_list}.
	 * @param ctx the parse tree
	 */
	void enterProc_handler_list(@NotNull HanaParser.Proc_handler_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#proc_handler_list}.
	 * @param ctx the parse tree
	 */
	void exitProc_handler_list(@NotNull HanaParser.Proc_handler_listContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#subquery_operation_part}.
	 * @param ctx the parse tree
	 */
	void enterSubquery_operation_part(@NotNull HanaParser.Subquery_operation_partContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#subquery_operation_part}.
	 * @param ctx the parse tree
	 */
	void exitSubquery_operation_part(@NotNull HanaParser.Subquery_operation_partContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#upper_bound}.
	 * @param ctx the parse tree
	 */
	void enterUpper_bound(@NotNull HanaParser.Upper_boundContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#upper_bound}.
	 * @param ctx the parse tree
	 */
	void exitUpper_bound(@NotNull HanaParser.Upper_boundContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#sql_error_code}.
	 * @param ctx the parse tree
	 */
	void enterSql_error_code(@NotNull HanaParser.Sql_error_codeContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#sql_error_code}.
	 * @param ctx the parse tree
	 */
	void exitSql_error_code(@NotNull HanaParser.Sql_error_codeContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#delimiter}.
	 * @param ctx the parse tree
	 */
	void enterDelimiter(@NotNull HanaParser.DelimiterContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#delimiter}.
	 * @param ctx the parse tree
	 */
	void exitDelimiter(@NotNull HanaParser.DelimiterContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#label_declaration}.
	 * @param ctx the parse tree
	 */
	void enterLabel_declaration(@NotNull HanaParser.Label_declarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#label_declaration}.
	 * @param ctx the parse tree
	 */
	void exitLabel_declaration(@NotNull HanaParser.Label_declarationContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#searched_case_when_part}.
	 * @param ctx the parse tree
	 */
	void enterSearched_case_when_part(@NotNull HanaParser.Searched_case_when_partContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#searched_case_when_part}.
	 * @param ctx the parse tree
	 */
	void exitSearched_case_when_part(@NotNull HanaParser.Searched_case_when_partContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#xml_column_name}.
	 * @param ctx the parse tree
	 */
	void enterXml_column_name(@NotNull HanaParser.Xml_column_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#xml_column_name}.
	 * @param ctx the parse tree
	 */
	void exitXml_column_name(@NotNull HanaParser.Xml_column_nameContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#exception_clause}.
	 * @param ctx the parse tree
	 */
	void enterException_clause(@NotNull HanaParser.Exception_clauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#exception_clause}.
	 * @param ctx the parse tree
	 */
	void exitException_clause(@NotNull HanaParser.Exception_clauseContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#record_type_dec}.
	 * @param ctx the parse tree
	 */
	void enterRecord_type_dec(@NotNull HanaParser.Record_type_decContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#record_type_dec}.
	 * @param ctx the parse tree
	 */
	void exitRecord_type_dec(@NotNull HanaParser.Record_type_decContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#table_element}.
	 * @param ctx the parse tree
	 */
	void enterTable_element(@NotNull HanaParser.Table_elementContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#table_element}.
	 * @param ctx the parse tree
	 */
	void exitTable_element(@NotNull HanaParser.Table_elementContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#interval_expression}.
	 * @param ctx the parse tree
	 */
	void enterInterval_expression(@NotNull HanaParser.Interval_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#interval_expression}.
	 * @param ctx the parse tree
	 */
	void exitInterval_expression(@NotNull HanaParser.Interval_expressionContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#array_constructor}.
	 * @param ctx the parse tree
	 */
	void enterArray_constructor(@NotNull HanaParser.Array_constructorContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#array_constructor}.
	 * @param ctx the parse tree
	 */
	void exitArray_constructor(@NotNull HanaParser.Array_constructorContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#set_transaction_command}.
	 * @param ctx the parse tree
	 */
	void enterSet_transaction_command(@NotNull HanaParser.Set_transaction_commandContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#set_transaction_command}.
	 * @param ctx the parse tree
	 */
	void exitSet_transaction_command(@NotNull HanaParser.Set_transaction_commandContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#proc_if}.
	 * @param ctx the parse tree
	 */
	void enterProc_if(@NotNull HanaParser.Proc_ifContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#proc_if}.
	 * @param ctx the parse tree
	 */
	void exitProc_if(@NotNull HanaParser.Proc_ifContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#subprogram_spec}.
	 * @param ctx the parse tree
	 */
	void enterSubprogram_spec(@NotNull HanaParser.Subprogram_specContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#subprogram_spec}.
	 * @param ctx the parse tree
	 */
	void exitSubprogram_spec(@NotNull HanaParser.Subprogram_specContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#write_clause}.
	 * @param ctx the parse tree
	 */
	void enterWrite_clause(@NotNull HanaParser.Write_clauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#write_clause}.
	 * @param ctx the parse tree
	 */
	void exitWrite_clause(@NotNull HanaParser.Write_clauseContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#multi_column_for_loop}.
	 * @param ctx the parse tree
	 */
	void enterMulti_column_for_loop(@NotNull HanaParser.Multi_column_for_loopContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#multi_column_for_loop}.
	 * @param ctx the parse tree
	 */
	void exitMulti_column_for_loop(@NotNull HanaParser.Multi_column_for_loopContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#xml_multiuse_expression_element}.
	 * @param ctx the parse tree
	 */
	void enterXml_multiuse_expression_element(@NotNull HanaParser.Xml_multiuse_expression_elementContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#xml_multiuse_expression_element}.
	 * @param ctx the parse tree
	 */
	void exitXml_multiuse_expression_element(@NotNull HanaParser.Xml_multiuse_expression_elementContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#order_by_elements}.
	 * @param ctx the parse tree
	 */
	void enterOrder_by_elements(@NotNull HanaParser.Order_by_elementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#order_by_elements}.
	 * @param ctx the parse tree
	 */
	void exitOrder_by_elements(@NotNull HanaParser.Order_by_elementsContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#model_expression}.
	 * @param ctx the parse tree
	 */
	void enterModel_expression(@NotNull HanaParser.Model_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#model_expression}.
	 * @param ctx the parse tree
	 */
	void exitModel_expression(@NotNull HanaParser.Model_expressionContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#current_of_clause}.
	 * @param ctx the parse tree
	 */
	void enterCurrent_of_clause(@NotNull HanaParser.Current_of_clauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#current_of_clause}.
	 * @param ctx the parse tree
	 */
	void exitCurrent_of_clause(@NotNull HanaParser.Current_of_clauseContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#concatenation}.
	 * @param ctx the parse tree
	 */
	void enterConcatenation(@NotNull HanaParser.ConcatenationContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#concatenation}.
	 * @param ctx the parse tree
	 */
	void exitConcatenation(@NotNull HanaParser.ConcatenationContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#xmlserialize_param_enconding_part}.
	 * @param ctx the parse tree
	 */
	void enterXmlserialize_param_enconding_part(@NotNull HanaParser.Xmlserialize_param_enconding_partContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#xmlserialize_param_enconding_part}.
	 * @param ctx the parse tree
	 */
	void exitXmlserialize_param_enconding_part(@NotNull HanaParser.Xmlserialize_param_enconding_partContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#forall_statement}.
	 * @param ctx the parse tree
	 */
	void enterForall_statement(@NotNull HanaParser.Forall_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#forall_statement}.
	 * @param ctx the parse tree
	 */
	void exitForall_statement(@NotNull HanaParser.Forall_statementContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#record_name}.
	 * @param ctx the parse tree
	 */
	void enterRecord_name(@NotNull HanaParser.Record_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#record_name}.
	 * @param ctx the parse tree
	 */
	void exitRecord_name(@NotNull HanaParser.Record_nameContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#subquery_factoring_clause}.
	 * @param ctx the parse tree
	 */
	void enterSubquery_factoring_clause(@NotNull HanaParser.Subquery_factoring_clauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#subquery_factoring_clause}.
	 * @param ctx the parse tree
	 */
	void exitSubquery_factoring_clause(@NotNull HanaParser.Subquery_factoring_clauseContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#aggregate_order_by_clause}.
	 * @param ctx the parse tree
	 */
	void enterAggregate_order_by_clause(@NotNull HanaParser.Aggregate_order_by_clauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#aggregate_order_by_clause}.
	 * @param ctx the parse tree
	 */
	void exitAggregate_order_by_clause(@NotNull HanaParser.Aggregate_order_by_clauseContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#compiler_parameters_clause}.
	 * @param ctx the parse tree
	 */
	void enterCompiler_parameters_clause(@NotNull HanaParser.Compiler_parameters_clauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#compiler_parameters_clause}.
	 * @param ctx the parse tree
	 */
	void exitCompiler_parameters_clause(@NotNull HanaParser.Compiler_parameters_clauseContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#merge_element}.
	 * @param ctx the parse tree
	 */
	void enterMerge_element(@NotNull HanaParser.Merge_elementContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#merge_element}.
	 * @param ctx the parse tree
	 */
	void exitMerge_element(@NotNull HanaParser.Merge_elementContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#aggregate_expression_}.
	 * @param ctx the parse tree
	 */
	void enterAggregate_expression_(@NotNull HanaParser.Aggregate_expression_Context ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#aggregate_expression_}.
	 * @param ctx the parse tree
	 */
	void exitAggregate_expression_(@NotNull HanaParser.Aggregate_expression_Context ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#using_element}.
	 * @param ctx the parse tree
	 */
	void enterUsing_element(@NotNull HanaParser.Using_elementContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#using_element}.
	 * @param ctx the parse tree
	 */
	void exitUsing_element(@NotNull HanaParser.Using_elementContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#cost_class_name}.
	 * @param ctx the parse tree
	 */
	void enterCost_class_name(@NotNull HanaParser.Cost_class_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#cost_class_name}.
	 * @param ctx the parse tree
	 */
	void exitCost_class_name(@NotNull HanaParser.Cost_class_nameContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#cursor_loop_param}.
	 * @param ctx the parse tree
	 */
	void enterCursor_loop_param(@NotNull HanaParser.Cursor_loop_paramContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#cursor_loop_param}.
	 * @param ctx the parse tree
	 */
	void exitCursor_loop_param(@NotNull HanaParser.Cursor_loop_paramContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#compilation_unit}.
	 * @param ctx the parse tree
	 */
	void enterCompilation_unit(@NotNull HanaParser.Compilation_unitContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#compilation_unit}.
	 * @param ctx the parse tree
	 */
	void exitCompilation_unit(@NotNull HanaParser.Compilation_unitContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#type_spec}.
	 * @param ctx the parse tree
	 */
	void enterType_spec(@NotNull HanaParser.Type_specContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#type_spec}.
	 * @param ctx the parse tree
	 */
	void exitType_spec(@NotNull HanaParser.Type_specContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#datatype}.
	 * @param ctx the parse tree
	 */
	void enterDatatype(@NotNull HanaParser.DatatypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#datatype}.
	 * @param ctx the parse tree
	 */
	void exitDatatype(@NotNull HanaParser.DatatypeContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#unpivot_in_elements}.
	 * @param ctx the parse tree
	 */
	void enterUnpivot_in_elements(@NotNull HanaParser.Unpivot_in_elementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#unpivot_in_elements}.
	 * @param ctx the parse tree
	 */
	void exitUnpivot_in_elements(@NotNull HanaParser.Unpivot_in_elementsContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#lock_table_statement}.
	 * @param ctx the parse tree
	 */
	void enterLock_table_statement(@NotNull HanaParser.Lock_table_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#lock_table_statement}.
	 * @param ctx the parse tree
	 */
	void exitLock_table_statement(@NotNull HanaParser.Lock_table_statementContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#table_type}.
	 * @param ctx the parse tree
	 */
	void enterTable_type(@NotNull HanaParser.Table_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#table_type}.
	 * @param ctx the parse tree
	 */
	void exitTable_type(@NotNull HanaParser.Table_typeContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#cost_matrix_clause}.
	 * @param ctx the parse tree
	 */
	void enterCost_matrix_clause(@NotNull HanaParser.Cost_matrix_clauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#cost_matrix_clause}.
	 * @param ctx the parse tree
	 */
	void exitCost_matrix_clause(@NotNull HanaParser.Cost_matrix_clauseContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#type_definition}.
	 * @param ctx the parse tree
	 */
	void enterType_definition(@NotNull HanaParser.Type_definitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#type_definition}.
	 * @param ctx the parse tree
	 */
	void exitType_definition(@NotNull HanaParser.Type_definitionContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#open_statement}.
	 * @param ctx the parse tree
	 */
	void enterOpen_statement(@NotNull HanaParser.Open_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#open_statement}.
	 * @param ctx the parse tree
	 */
	void exitOpen_statement(@NotNull HanaParser.Open_statementContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#message_string}.
	 * @param ctx the parse tree
	 */
	void enterMessage_string(@NotNull HanaParser.Message_stringContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#message_string}.
	 * @param ctx the parse tree
	 */
	void exitMessage_string(@NotNull HanaParser.Message_stringContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#for_increment_decrement_type}.
	 * @param ctx the parse tree
	 */
	void enterFor_increment_decrement_type(@NotNull HanaParser.For_increment_decrement_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#for_increment_decrement_type}.
	 * @param ctx the parse tree
	 */
	void exitFor_increment_decrement_type(@NotNull HanaParser.For_increment_decrement_typeContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#case_expression_}.
	 * @param ctx the parse tree
	 */
	void enterCase_expression_(@NotNull HanaParser.Case_expression_Context ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#case_expression_}.
	 * @param ctx the parse tree
	 */
	void exitCase_expression_(@NotNull HanaParser.Case_expression_Context ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#from_clause}.
	 * @param ctx the parse tree
	 */
	void enterFrom_clause(@NotNull HanaParser.From_clauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#from_clause}.
	 * @param ctx the parse tree
	 */
	void exitFrom_clause(@NotNull HanaParser.From_clauseContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#error_logging_reject_part}.
	 * @param ctx the parse tree
	 */
	void enterError_logging_reject_part(@NotNull HanaParser.Error_logging_reject_partContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#error_logging_reject_part}.
	 * @param ctx the parse tree
	 */
	void exitError_logging_reject_part(@NotNull HanaParser.Error_logging_reject_partContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#return_type}.
	 * @param ctx the parse tree
	 */
	void enterReturn_type(@NotNull HanaParser.Return_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#return_type}.
	 * @param ctx the parse tree
	 */
	void exitReturn_type(@NotNull HanaParser.Return_typeContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#subquery}.
	 * @param ctx the parse tree
	 */
	void enterSubquery(@NotNull HanaParser.SubqueryContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#subquery}.
	 * @param ctx the parse tree
	 */
	void exitSubquery(@NotNull HanaParser.SubqueryContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#where_clause}.
	 * @param ctx the parse tree
	 */
	void enterWhere_clause(@NotNull HanaParser.Where_clauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#where_clause}.
	 * @param ctx the parse tree
	 */
	void exitWhere_clause(@NotNull HanaParser.Where_clauseContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#argument}.
	 * @param ctx the parse tree
	 */
	void enterArgument(@NotNull HanaParser.ArgumentContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#argument}.
	 * @param ctx the parse tree
	 */
	void exitArgument(@NotNull HanaParser.ArgumentContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#set_command}.
	 * @param ctx the parse tree
	 */
	void enterSet_command(@NotNull HanaParser.Set_commandContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#set_command}.
	 * @param ctx the parse tree
	 */
	void exitSet_command(@NotNull HanaParser.Set_commandContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#attribute_definition}.
	 * @param ctx the parse tree
	 */
	void enterAttribute_definition(@NotNull HanaParser.Attribute_definitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#attribute_definition}.
	 * @param ctx the parse tree
	 */
	void exitAttribute_definition(@NotNull HanaParser.Attribute_definitionContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#start_part}.
	 * @param ctx the parse tree
	 */
	void enterStart_part(@NotNull HanaParser.Start_partContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#start_part}.
	 * @param ctx the parse tree
	 */
	void exitStart_part(@NotNull HanaParser.Start_partContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#model_expression_element}.
	 * @param ctx the parse tree
	 */
	void enterModel_expression_element(@NotNull HanaParser.Model_expression_elementContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#model_expression_element}.
	 * @param ctx the parse tree
	 */
	void exitModel_expression_element(@NotNull HanaParser.Model_expression_elementContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#exit_command}.
	 * @param ctx the parse tree
	 */
	void enterExit_command(@NotNull HanaParser.Exit_commandContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#exit_command}.
	 * @param ctx the parse tree
	 */
	void exitExit_command(@NotNull HanaParser.Exit_commandContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#table_ref_list}.
	 * @param ctx the parse tree
	 */
	void enterTable_ref_list(@NotNull HanaParser.Table_ref_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#table_ref_list}.
	 * @param ctx the parse tree
	 */
	void exitTable_ref_list(@NotNull HanaParser.Table_ref_listContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#unary_expression}.
	 * @param ctx the parse tree
	 */
	void enterUnary_expression(@NotNull HanaParser.Unary_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#unary_expression}.
	 * @param ctx the parse tree
	 */
	void exitUnary_expression(@NotNull HanaParser.Unary_expressionContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#general_element}.
	 * @param ctx the parse tree
	 */
	void enterGeneral_element(@NotNull HanaParser.General_elementContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#general_element}.
	 * @param ctx the parse tree
	 */
	void exitGeneral_element(@NotNull HanaParser.General_elementContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#transaction_control_statements}.
	 * @param ctx the parse tree
	 */
	void enterTransaction_control_statements(@NotNull HanaParser.Transaction_control_statementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#transaction_control_statements}.
	 * @param ctx the parse tree
	 */
	void exitTransaction_control_statements(@NotNull HanaParser.Transaction_control_statementsContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#range_predicate}.
	 * @param ctx the parse tree
	 */
	void enterRange_predicate(@NotNull HanaParser.Range_predicateContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#range_predicate}.
	 * @param ctx the parse tree
	 */
	void exitRange_predicate(@NotNull HanaParser.Range_predicateContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#numeric_negative}.
	 * @param ctx the parse tree
	 */
	void enterNumeric_negative(@NotNull HanaParser.Numeric_negativeContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#numeric_negative}.
	 * @param ctx the parse tree
	 */
	void exitNumeric_negative(@NotNull HanaParser.Numeric_negativeContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#pivot_in_clause_elements}.
	 * @param ctx the parse tree
	 */
	void enterPivot_in_clause_elements(@NotNull HanaParser.Pivot_in_clause_elementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#pivot_in_clause_elements}.
	 * @param ctx the parse tree
	 */
	void exitPivot_in_clause_elements(@NotNull HanaParser.Pivot_in_clause_elementsContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#table_type_definition}.
	 * @param ctx the parse tree
	 */
	void enterTable_type_definition(@NotNull HanaParser.Table_type_definitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#table_type_definition}.
	 * @param ctx the parse tree
	 */
	void exitTable_type_definition(@NotNull HanaParser.Table_type_definitionContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#general_table_ref}.
	 * @param ctx the parse tree
	 */
	void enterGeneral_table_ref(@NotNull HanaParser.General_table_refContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#general_table_ref}.
	 * @param ctx the parse tree
	 */
	void exitGeneral_table_ref(@NotNull HanaParser.General_table_refContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#bounds_clause}.
	 * @param ctx the parse tree
	 */
	void enterBounds_clause(@NotNull HanaParser.Bounds_clauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#bounds_clause}.
	 * @param ctx the parse tree
	 */
	void exitBounds_clause(@NotNull HanaParser.Bounds_clauseContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#proc_block}.
	 * @param ctx the parse tree
	 */
	void enterProc_block(@NotNull HanaParser.Proc_blockContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#proc_block}.
	 * @param ctx the parse tree
	 */
	void exitProc_block(@NotNull HanaParser.Proc_blockContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#for_update_clause}.
	 * @param ctx the parse tree
	 */
	void enterFor_update_clause(@NotNull HanaParser.For_update_clauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#for_update_clause}.
	 * @param ctx the parse tree
	 */
	void exitFor_update_clause(@NotNull HanaParser.For_update_clauseContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#cell_reference_options}.
	 * @param ctx the parse tree
	 */
	void enterCell_reference_options(@NotNull HanaParser.Cell_reference_optionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#cell_reference_options}.
	 * @param ctx the parse tree
	 */
	void exitCell_reference_options(@NotNull HanaParser.Cell_reference_optionsContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#constraint_name}.
	 * @param ctx the parse tree
	 */
	void enterConstraint_name(@NotNull HanaParser.Constraint_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#constraint_name}.
	 * @param ctx the parse tree
	 */
	void exitConstraint_name(@NotNull HanaParser.Constraint_nameContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#data_manipulation_language_statements}.
	 * @param ctx the parse tree
	 */
	void enterData_manipulation_language_statements(@NotNull HanaParser.Data_manipulation_language_statementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#data_manipulation_language_statements}.
	 * @param ctx the parse tree
	 */
	void exitData_manipulation_language_statements(@NotNull HanaParser.Data_manipulation_language_statementsContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#multiset_expression}.
	 * @param ctx the parse tree
	 */
	void enterMultiset_expression(@NotNull HanaParser.Multiset_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#multiset_expression}.
	 * @param ctx the parse tree
	 */
	void exitMultiset_expression(@NotNull HanaParser.Multiset_expressionContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#xml_table_column}.
	 * @param ctx the parse tree
	 */
	void enterXml_table_column(@NotNull HanaParser.Xml_table_columnContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#xml_table_column}.
	 * @param ctx the parse tree
	 */
	void exitXml_table_column(@NotNull HanaParser.Xml_table_columnContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#relational_operator}.
	 * @param ctx the parse tree
	 */
	void enterRelational_operator(@NotNull HanaParser.Relational_operatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#relational_operator}.
	 * @param ctx the parse tree
	 */
	void exitRelational_operator(@NotNull HanaParser.Relational_operatorContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#into_clause}.
	 * @param ctx the parse tree
	 */
	void enterInto_clause(@NotNull HanaParser.Into_clauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#into_clause}.
	 * @param ctx the parse tree
	 */
	void exitInto_clause(@NotNull HanaParser.Into_clauseContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#default_value_part}.
	 * @param ctx the parse tree
	 */
	void enterDefault_value_part(@NotNull HanaParser.Default_value_partContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#default_value_part}.
	 * @param ctx the parse tree
	 */
	void exitDefault_value_part(@NotNull HanaParser.Default_value_partContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#column_name_old}.
	 * @param ctx the parse tree
	 */
	void enterColumn_name_old(@NotNull HanaParser.Column_name_oldContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#column_name_old}.
	 * @param ctx the parse tree
	 */
	void exitColumn_name_old(@NotNull HanaParser.Column_name_oldContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#multi_table_insert}.
	 * @param ctx the parse tree
	 */
	void enterMulti_table_insert(@NotNull HanaParser.Multi_table_insertContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#multi_table_insert}.
	 * @param ctx the parse tree
	 */
	void exitMulti_table_insert(@NotNull HanaParser.Multi_table_insertContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#xmlroot_param_version_part}.
	 * @param ctx the parse tree
	 */
	void enterXmlroot_param_version_part(@NotNull HanaParser.Xmlroot_param_version_partContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#xmlroot_param_version_part}.
	 * @param ctx the parse tree
	 */
	void exitXmlroot_param_version_part(@NotNull HanaParser.Xmlroot_param_version_partContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#cursor_name}.
	 * @param ctx the parse tree
	 */
	void enterCursor_name(@NotNull HanaParser.Cursor_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#cursor_name}.
	 * @param ctx the parse tree
	 */
	void exitCursor_name(@NotNull HanaParser.Cursor_nameContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#relational_expression_operator}.
	 * @param ctx the parse tree
	 */
	void enterRelational_expression_operator(@NotNull HanaParser.Relational_expression_operatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#relational_expression_operator}.
	 * @param ctx the parse tree
	 */
	void exitRelational_expression_operator(@NotNull HanaParser.Relational_expression_operatorContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#function_argument}.
	 * @param ctx the parse tree
	 */
	void enterFunction_argument(@NotNull HanaParser.Function_argumentContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#function_argument}.
	 * @param ctx the parse tree
	 */
	void exitFunction_argument(@NotNull HanaParser.Function_argumentContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#join_using_part}.
	 * @param ctx the parse tree
	 */
	void enterJoin_using_part(@NotNull HanaParser.Join_using_partContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#join_using_part}.
	 * @param ctx the parse tree
	 */
	void exitJoin_using_part(@NotNull HanaParser.Join_using_partContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#proc_name}.
	 * @param ctx the parse tree
	 */
	void enterProc_name(@NotNull HanaParser.Proc_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#proc_name}.
	 * @param ctx the parse tree
	 */
	void exitProc_name(@NotNull HanaParser.Proc_nameContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#model_clause}.
	 * @param ctx the parse tree
	 */
	void enterModel_clause(@NotNull HanaParser.Model_clauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#model_clause}.
	 * @param ctx the parse tree
	 */
	void exitModel_clause(@NotNull HanaParser.Model_clauseContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#proc_condition_value_}.
	 * @param ctx the parse tree
	 */
	void enterProc_condition_value_(@NotNull HanaParser.Proc_condition_value_Context ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#proc_condition_value_}.
	 * @param ctx the parse tree
	 */
	void exitProc_condition_value_(@NotNull HanaParser.Proc_condition_value_Context ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#proc_call}.
	 * @param ctx the parse tree
	 */
	void enterProc_call(@NotNull HanaParser.Proc_callContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#proc_call}.
	 * @param ctx the parse tree
	 */
	void exitProc_call(@NotNull HanaParser.Proc_callContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#numeric}.
	 * @param ctx the parse tree
	 */
	void enterNumeric(@NotNull HanaParser.NumericContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#numeric}.
	 * @param ctx the parse tree
	 */
	void exitNumeric(@NotNull HanaParser.NumericContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#selected_tableview}.
	 * @param ctx the parse tree
	 */
	void enterSelected_tableview(@NotNull HanaParser.Selected_tableviewContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#selected_tableview}.
	 * @param ctx the parse tree
	 */
	void exitSelected_tableview(@NotNull HanaParser.Selected_tableviewContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#proc_resignal}.
	 * @param ctx the parse tree
	 */
	void enterProc_resignal(@NotNull HanaParser.Proc_resignalContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#proc_resignal}.
	 * @param ctx the parse tree
	 */
	void exitProc_resignal(@NotNull HanaParser.Proc_resignalContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#expression__list}.
	 * @param ctx the parse tree
	 */
	void enterExpression__list(@NotNull HanaParser.Expression__listContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#expression__list}.
	 * @param ctx the parse tree
	 */
	void exitExpression__list(@NotNull HanaParser.Expression__listContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#proc_cursor}.
	 * @param ctx the parse tree
	 */
	void enterProc_cursor(@NotNull HanaParser.Proc_cursorContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#proc_cursor}.
	 * @param ctx the parse tree
	 */
	void exitProc_cursor(@NotNull HanaParser.Proc_cursorContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#parameter}.
	 * @param ctx the parse tree
	 */
	void enterParameter(@NotNull HanaParser.ParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#parameter}.
	 * @param ctx the parse tree
	 */
	void exitParameter(@NotNull HanaParser.ParameterContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#between_elements}.
	 * @param ctx the parse tree
	 */
	void enterBetween_elements(@NotNull HanaParser.Between_elementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#between_elements}.
	 * @param ctx the parse tree
	 */
	void exitBetween_elements(@NotNull HanaParser.Between_elementsContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#bind_variable}.
	 * @param ctx the parse tree
	 */
	void enterBind_variable(@NotNull HanaParser.Bind_variableContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#bind_variable}.
	 * @param ctx the parse tree
	 */
	void exitBind_variable(@NotNull HanaParser.Bind_variableContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#xmlroot_param_standalone_part}.
	 * @param ctx the parse tree
	 */
	void enterXmlroot_param_standalone_part(@NotNull HanaParser.Xmlroot_param_standalone_partContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#xmlroot_param_standalone_part}.
	 * @param ctx the parse tree
	 */
	void exitXmlroot_param_standalone_part(@NotNull HanaParser.Xmlroot_param_standalone_partContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#dependent_exceptions_part}.
	 * @param ctx the parse tree
	 */
	void enterDependent_exceptions_part(@NotNull HanaParser.Dependent_exceptions_partContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#dependent_exceptions_part}.
	 * @param ctx the parse tree
	 */
	void exitDependent_exceptions_part(@NotNull HanaParser.Dependent_exceptions_partContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#select_list_elements}.
	 * @param ctx the parse tree
	 */
	void enterSelect_list_elements(@NotNull HanaParser.Select_list_elementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#select_list_elements}.
	 * @param ctx the parse tree
	 */
	void exitSelect_list_elements(@NotNull HanaParser.Select_list_elementsContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#greater_than_or_equals_op}.
	 * @param ctx the parse tree
	 */
	void enterGreater_than_or_equals_op(@NotNull HanaParser.Greater_than_or_equals_opContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#greater_than_or_equals_op}.
	 * @param ctx the parse tree
	 */
	void exitGreater_than_or_equals_op(@NotNull HanaParser.Greater_than_or_equals_opContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#column_name}.
	 * @param ctx the parse tree
	 */
	void enterColumn_name(@NotNull HanaParser.Column_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#column_name}.
	 * @param ctx the parse tree
	 */
	void exitColumn_name(@NotNull HanaParser.Column_nameContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#param_list}.
	 * @param ctx the parse tree
	 */
	void enterParam_list(@NotNull HanaParser.Param_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#param_list}.
	 * @param ctx the parse tree
	 */
	void exitParam_list(@NotNull HanaParser.Param_listContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#exit_statement}.
	 * @param ctx the parse tree
	 */
	void enterExit_statement(@NotNull HanaParser.Exit_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#exit_statement}.
	 * @param ctx the parse tree
	 */
	void exitExit_statement(@NotNull HanaParser.Exit_statementContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#simple_case_expression_}.
	 * @param ctx the parse tree
	 */
	void enterSimple_case_expression_(@NotNull HanaParser.Simple_case_expression_Context ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#simple_case_expression_}.
	 * @param ctx the parse tree
	 */
	void exitSimple_case_expression_(@NotNull HanaParser.Simple_case_expression_Context ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#proc_decl_op}.
	 * @param ctx the parse tree
	 */
	void enterProc_decl_op(@NotNull HanaParser.Proc_decl_opContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#proc_decl_op}.
	 * @param ctx the parse tree
	 */
	void exitProc_decl_op(@NotNull HanaParser.Proc_decl_opContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#hierarchical_query_clause}.
	 * @param ctx the parse tree
	 */
	void enterHierarchical_query_clause(@NotNull HanaParser.Hierarchical_query_clauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#hierarchical_query_clause}.
	 * @param ctx the parse tree
	 */
	void exitHierarchical_query_clause(@NotNull HanaParser.Hierarchical_query_clauseContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#join_clause}.
	 * @param ctx the parse tree
	 */
	void enterJoin_clause(@NotNull HanaParser.Join_clauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#join_clause}.
	 * @param ctx the parse tree
	 */
	void exitJoin_clause(@NotNull HanaParser.Join_clauseContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#join_on_part}.
	 * @param ctx the parse tree
	 */
	void enterJoin_on_part(@NotNull HanaParser.Join_on_partContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#join_on_part}.
	 * @param ctx the parse tree
	 */
	void exitJoin_on_part(@NotNull HanaParser.Join_on_partContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#logical_and_expression}.
	 * @param ctx the parse tree
	 */
	void enterLogical_and_expression(@NotNull HanaParser.Logical_and_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#logical_and_expression}.
	 * @param ctx the parse tree
	 */
	void exitLogical_and_expression(@NotNull HanaParser.Logical_and_expressionContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#xml_namespaces_clause}.
	 * @param ctx the parse tree
	 */
	void enterXml_namespaces_clause(@NotNull HanaParser.Xml_namespaces_clauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#xml_namespaces_clause}.
	 * @param ctx the parse tree
	 */
	void exitXml_namespaces_clause(@NotNull HanaParser.Xml_namespaces_clauseContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#main_model_name}.
	 * @param ctx the parse tree
	 */
	void enterMain_model_name(@NotNull HanaParser.Main_model_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#main_model_name}.
	 * @param ctx the parse tree
	 */
	void exitMain_model_name(@NotNull HanaParser.Main_model_nameContext ctx);

	/**
	 * Enter a parse tree produced by {@link HanaParser#set_signal_info}.
	 * @param ctx the parse tree
	 */
	void enterSet_signal_info(@NotNull HanaParser.Set_signal_infoContext ctx);
	/**
	 * Exit a parse tree produced by {@link HanaParser#set_signal_info}.
	 * @param ctx the parse tree
	 */
	void exitSet_signal_info(@NotNull HanaParser.Set_signal_infoContext ctx);
}