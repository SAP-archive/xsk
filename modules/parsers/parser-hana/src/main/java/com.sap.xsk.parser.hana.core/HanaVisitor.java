// Generated from com.sap.xsk.parser.hana.core/Hana.g4 by ANTLR 4.10.1
package com.sap.xsk.parser.hana.core;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link HanaParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface HanaVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link HanaParser#swallow_to_semi}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSwallow_to_semi(HanaParser.Swallow_to_semiContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#compilation_unit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompilation_unit(HanaParser.Compilation_unitContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#sql_script}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSql_script(HanaParser.Sql_scriptContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#unit_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnit_statement(HanaParser.Unit_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#set_schema}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSet_schema(HanaParser.Set_schemaContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#drop_procedure}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDrop_procedure(HanaParser.Drop_procedureContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#create_procedure_body}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreate_procedure_body(HanaParser.Create_procedure_bodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#create_func_body}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreate_func_body(HanaParser.Create_func_bodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#param_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParam_name(HanaParser.Param_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#return_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturn_type(HanaParser.Return_typeContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#return_table_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturn_table_type(HanaParser.Return_table_typeContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#param_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParam_type(HanaParser.Param_typeContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#sql_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSql_type(HanaParser.Sql_typeContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#table_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTable_type(HanaParser.Table_typeContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#table_type_definition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTable_type_definition(HanaParser.Table_type_definitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#column_list_definition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumn_list_definition(HanaParser.Column_list_definitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#column_elem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumn_elem(HanaParser.Column_elemContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#column_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumn_name(HanaParser.Column_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#data_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitData_type(HanaParser.Data_typeContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#parameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameter(HanaParser.ParameterContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#parameter_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameter_clause(HanaParser.Parameter_clauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#lang}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLang(HanaParser.LangContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#security_mode}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSecurity_mode(HanaParser.Security_modeContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#default_schema_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefault_schema_name(HanaParser.Default_schema_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#view_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitView_name(HanaParser.View_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#proc_decl_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProc_decl_list(HanaParser.Proc_decl_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#proc_decl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProc_decl(HanaParser.Proc_declContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#proc_decl_op}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProc_decl_op(HanaParser.Proc_decl_opContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#proc_variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProc_variable(HanaParser.Proc_variableContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#proc_table_variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProc_table_variable(HanaParser.Proc_table_variableContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#variable_name_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable_name_list(HanaParser.Variable_name_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#array_datatype}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArray_datatype(HanaParser.Array_datatypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#array_constructor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArray_constructor(HanaParser.Array_constructorContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#proc_default}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProc_default(HanaParser.Proc_defaultContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#proc_cursor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProc_cursor(HanaParser.Proc_cursorContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#proc_cursor_param_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProc_cursor_param_list(HanaParser.Proc_cursor_param_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#variable_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable_name(HanaParser.Variable_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#cursor_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCursor_name(HanaParser.Cursor_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#proc_cursor_param}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProc_cursor_param(HanaParser.Proc_cursor_paramContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#proc_condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProc_condition(HanaParser.Proc_conditionContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#sql_error_code}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSql_error_code(HanaParser.Sql_error_codeContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#proc_handler_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProc_handler_list(HanaParser.Proc_handler_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#proc_handler}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProc_handler(HanaParser.Proc_handlerContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#proc_condition_value_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProc_condition_value_list(HanaParser.Proc_condition_value_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#proc_condition_value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProc_condition_value(HanaParser.Proc_condition_valueContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#proc_stmt_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProc_stmt_list(HanaParser.Proc_stmt_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#func_stmt_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunc_stmt_list(HanaParser.Func_stmt_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#proc_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProc_stmt(HanaParser.Proc_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#proc_sql}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProc_sql(HanaParser.Proc_sqlContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#update_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUpdate_stmt(HanaParser.Update_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#insert_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInsert_stmt(HanaParser.Insert_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#delete_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDelete_stmt(HanaParser.Delete_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#truncate_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTruncate_stmt(HanaParser.Truncate_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#create_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreate_stmt(HanaParser.Create_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#proc_while}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProc_while(HanaParser.Proc_whileContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#proc_for}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProc_for(HanaParser.Proc_forContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#proc_rollback}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProc_rollback(HanaParser.Proc_rollbackContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#proc_commit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProc_commit(HanaParser.Proc_commitContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#proc_break}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProc_break(HanaParser.Proc_breakContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#proc_signal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProc_signal(HanaParser.Proc_signalContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#proc_resignal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProc_resignal(HanaParser.Proc_resignalContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#signal_value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSignal_value(HanaParser.Signal_valueContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#signal_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSignal_name(HanaParser.Signal_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#set_signal_info}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSet_signal_info(HanaParser.Set_signal_infoContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#message_string}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMessage_string(HanaParser.Message_stringContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#proc_if}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProc_if(HanaParser.Proc_ifContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#proc_elseif_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProc_elseif_list(HanaParser.Proc_elseif_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#proc_else}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProc_else(HanaParser.Proc_elseContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#proc_block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProc_block(HanaParser.Proc_blockContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#proc_block_option}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProc_block_option(HanaParser.Proc_block_optionContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#proc_assign}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProc_assign(HanaParser.Proc_assignContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#proc_single_assign}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProc_single_assign(HanaParser.Proc_single_assignContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#unnest_function}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnnest_function(HanaParser.Unnest_functionContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#table_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTable_name(HanaParser.Table_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#as_col_names}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAs_col_names(HanaParser.As_col_namesContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#column_name_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumn_name_list(HanaParser.Column_name_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#proc_call}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProc_call(HanaParser.Proc_callContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#param_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParam_list(HanaParser.Param_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#proc_param}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProc_param(HanaParser.Proc_paramContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#named_param}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNamed_param(HanaParser.Named_paramContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#procedure_body}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProcedure_body(HanaParser.Procedure_bodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#procedure_body_}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProcedure_body_(HanaParser.Procedure_body_Context ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#func_body}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunc_body(HanaParser.Func_bodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#parameter_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameter_name(HanaParser.Parameter_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#for_each_row}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFor_each_row(HanaParser.For_each_rowContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#alter_attribute_definition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlter_attribute_definition(HanaParser.Alter_attribute_definitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#attribute_definition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttribute_definition(HanaParser.Attribute_definitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#alter_collection_clauses}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlter_collection_clauses(HanaParser.Alter_collection_clausesContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#dependent_handling_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDependent_handling_clause(HanaParser.Dependent_handling_clauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#dependent_exceptions_part}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDependent_exceptions_part(HanaParser.Dependent_exceptions_partContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#type_definition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType_definition(HanaParser.Type_definitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#object_type_def}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObject_type_def(HanaParser.Object_type_defContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#object_as_part}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObject_as_part(HanaParser.Object_as_partContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#object_under_part}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObject_under_part(HanaParser.Object_under_partContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#nested_table_type_def}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNested_table_type_def(HanaParser.Nested_table_type_defContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#sqlj_object_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSqlj_object_type(HanaParser.Sqlj_object_typeContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#type_body}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType_body(HanaParser.Type_bodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#type_body_elements}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType_body_elements(HanaParser.Type_body_elementsContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#map_order_func_declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMap_order_func_declaration(HanaParser.Map_order_func_declarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#subprog_decl_in_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubprog_decl_in_type(HanaParser.Subprog_decl_in_typeContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#proc_decl_in_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProc_decl_in_type(HanaParser.Proc_decl_in_typeContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#func_decl_in_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunc_decl_in_type(HanaParser.Func_decl_in_typeContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#constructor_declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstructor_declaration(HanaParser.Constructor_declarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#modifier_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModifier_clause(HanaParser.Modifier_clauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#object_member_spec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObject_member_spec(HanaParser.Object_member_specContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#sqlj_object_type_attr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSqlj_object_type_attr(HanaParser.Sqlj_object_type_attrContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#element_spec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElement_spec(HanaParser.Element_specContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#element_spec_options}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElement_spec_options(HanaParser.Element_spec_optionsContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#subprogram_spec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubprogram_spec(HanaParser.Subprogram_specContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#type_procedure_spec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType_procedure_spec(HanaParser.Type_procedure_specContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#type_function_spec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType_function_spec(HanaParser.Type_function_specContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#constructor_spec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstructor_spec(HanaParser.Constructor_specContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#map_order_function_spec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMap_order_function_spec(HanaParser.Map_order_function_specContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#pragma_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPragma_clause(HanaParser.Pragma_clauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#pragma_elements}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPragma_elements(HanaParser.Pragma_elementsContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#type_elements_parameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType_elements_parameter(HanaParser.Type_elements_parameterContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#create_sequence}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreate_sequence(HanaParser.Create_sequenceContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#sequence_spec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSequence_spec(HanaParser.Sequence_specContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#sequence_start_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSequence_start_clause(HanaParser.Sequence_start_clauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#invoker_rights_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInvoker_rights_clause(HanaParser.Invoker_rights_clauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#compiler_parameters_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompiler_parameters_clause(HanaParser.Compiler_parameters_clauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#call_spec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCall_spec(HanaParser.Call_specContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#java_spec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJava_spec(HanaParser.Java_specContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#c_spec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitC_spec(HanaParser.C_specContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#c_agent_in_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitC_agent_in_clause(HanaParser.C_agent_in_clauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#c_parameters_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitC_parameters_clause(HanaParser.C_parameters_clauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#default_value_part}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefault_value_part(HanaParser.Default_value_partContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#declare_spec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclare_spec(HanaParser.Declare_specContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#variable_declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable_declaration(HanaParser.Variable_declarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#subtype_declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubtype_declaration(HanaParser.Subtype_declarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#cursor_declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCursor_declaration(HanaParser.Cursor_declarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#parameter_spec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameter_spec(HanaParser.Parameter_specContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#exception_declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitException_declaration(HanaParser.Exception_declarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#pragma_declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPragma_declaration(HanaParser.Pragma_declarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#record_declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRecord_declaration(HanaParser.Record_declarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#record_type_dec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRecord_type_dec(HanaParser.Record_type_decContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#field_spec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitField_spec(HanaParser.Field_specContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#record_var_dec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRecord_var_dec(HanaParser.Record_var_decContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#table_declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTable_declaration(HanaParser.Table_declarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#table_type_dec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTable_type_dec(HanaParser.Table_type_decContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#table_indexed_by_part}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTable_indexed_by_part(HanaParser.Table_indexed_by_partContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#varray_type_def}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarray_type_def(HanaParser.Varray_type_defContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#table_var_dec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTable_var_dec(HanaParser.Table_var_decContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#seq_of_statements}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSeq_of_statements(HanaParser.Seq_of_statementsContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#label_declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLabel_declaration(HanaParser.Label_declarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(HanaParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#assignment_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignment_statement(HanaParser.Assignment_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#continue_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContinue_statement(HanaParser.Continue_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#exit_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExit_statement(HanaParser.Exit_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#goto_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGoto_statement(HanaParser.Goto_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#if_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf_statement(HanaParser.If_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#elsif_part}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElsif_part(HanaParser.Elsif_partContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#else_part}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElse_part(HanaParser.Else_partContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#loop_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoop_statement(HanaParser.Loop_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#cursor_loop_param}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCursor_loop_param(HanaParser.Cursor_loop_paramContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#forall_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForall_statement(HanaParser.Forall_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#bounds_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBounds_clause(HanaParser.Bounds_clauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#between_bound}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBetween_bound(HanaParser.Between_boundContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#lower_bound}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLower_bound(HanaParser.Lower_boundContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#upper_bound}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUpper_bound(HanaParser.Upper_boundContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#null_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNull_statement(HanaParser.Null_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#raise_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRaise_statement(HanaParser.Raise_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#return_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturn_statement(HanaParser.Return_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#function_call}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction_call(HanaParser.Function_callContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#body}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBody(HanaParser.BodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#exception_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitException_clause(HanaParser.Exception_clauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#exception_handler}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitException_handler(HanaParser.Exception_handlerContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#trigger_block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTrigger_block(HanaParser.Trigger_blockContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(HanaParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#sql_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSql_statement(HanaParser.Sql_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#execute_immediate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExecute_immediate(HanaParser.Execute_immediateContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#dynamic_returning_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDynamic_returning_clause(HanaParser.Dynamic_returning_clauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#data_manipulation_language_statements}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitData_manipulation_language_statements(HanaParser.Data_manipulation_language_statementsContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#cursor_manipulation_statements}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCursor_manipulation_statements(HanaParser.Cursor_manipulation_statementsContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#close_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClose_statement(HanaParser.Close_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#open_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOpen_statement(HanaParser.Open_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#fetch_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFetch_statement(HanaParser.Fetch_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#open_for_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOpen_for_statement(HanaParser.Open_for_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#transaction_control_statements}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTransaction_control_statements(HanaParser.Transaction_control_statementsContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#set_transaction_command}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSet_transaction_command(HanaParser.Set_transaction_commandContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#set_constraint_command}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSet_constraint_command(HanaParser.Set_constraint_commandContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#commit_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCommit_statement(HanaParser.Commit_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#write_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWrite_clause(HanaParser.Write_clauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#rollback_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRollback_statement(HanaParser.Rollback_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#savepoint_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSavepoint_statement(HanaParser.Savepoint_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#explain_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExplain_statement(HanaParser.Explain_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#select_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelect_statement(HanaParser.Select_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#subquery_factoring_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubquery_factoring_clause(HanaParser.Subquery_factoring_clauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#factoring_element}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFactoring_element(HanaParser.Factoring_elementContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#search_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSearch_clause(HanaParser.Search_clauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#cycle_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCycle_clause(HanaParser.Cycle_clauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#subquery}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubquery(HanaParser.SubqueryContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#subquery_operation_part}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubquery_operation_part(HanaParser.Subquery_operation_partContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#subquery_basic_elements}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubquery_basic_elements(HanaParser.Subquery_basic_elementsContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#query_block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuery_block(HanaParser.Query_blockContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#selected_element}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelected_element(HanaParser.Selected_elementContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#from_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFrom_clause(HanaParser.From_clauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#select_list_elements}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelect_list_elements(HanaParser.Select_list_elementsContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#table_ref_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTable_ref_list(HanaParser.Table_ref_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#table_ref}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTable_ref(HanaParser.Table_refContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#table_ref_aux}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTable_ref_aux(HanaParser.Table_ref_auxContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#join_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJoin_clause(HanaParser.Join_clauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#join_on_part}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJoin_on_part(HanaParser.Join_on_partContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#join_using_part}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJoin_using_part(HanaParser.Join_using_partContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#outer_join_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOuter_join_type(HanaParser.Outer_join_typeContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#query_partition_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuery_partition_clause(HanaParser.Query_partition_clauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#flashback_query_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFlashback_query_clause(HanaParser.Flashback_query_clauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#pivot_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPivot_clause(HanaParser.Pivot_clauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#pivot_element}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPivot_element(HanaParser.Pivot_elementContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#pivot_for_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPivot_for_clause(HanaParser.Pivot_for_clauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#pivot_in_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPivot_in_clause(HanaParser.Pivot_in_clauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#pivot_in_clause_element}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPivot_in_clause_element(HanaParser.Pivot_in_clause_elementContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#pivot_in_clause_elements}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPivot_in_clause_elements(HanaParser.Pivot_in_clause_elementsContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#unpivot_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnpivot_clause(HanaParser.Unpivot_clauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#unpivot_in_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnpivot_in_clause(HanaParser.Unpivot_in_clauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#unpivot_in_elements}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnpivot_in_elements(HanaParser.Unpivot_in_elementsContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#hierarchical_query_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHierarchical_query_clause(HanaParser.Hierarchical_query_clauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#start_part}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStart_part(HanaParser.Start_partContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#group_by_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGroup_by_clause(HanaParser.Group_by_clauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#group_by_elements}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGroup_by_elements(HanaParser.Group_by_elementsContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#rollup_cube_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRollup_cube_clause(HanaParser.Rollup_cube_clauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#grouping_sets_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGrouping_sets_clause(HanaParser.Grouping_sets_clauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#grouping_sets_elements}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGrouping_sets_elements(HanaParser.Grouping_sets_elementsContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#having_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHaving_clause(HanaParser.Having_clauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#model_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModel_clause(HanaParser.Model_clauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#cell_reference_options}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCell_reference_options(HanaParser.Cell_reference_optionsContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#return_rows_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturn_rows_clause(HanaParser.Return_rows_clauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#reference_model}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReference_model(HanaParser.Reference_modelContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#main_model}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMain_model(HanaParser.Main_modelContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#model_column_clauses}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModel_column_clauses(HanaParser.Model_column_clausesContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#model_column_partition_part}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModel_column_partition_part(HanaParser.Model_column_partition_partContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#model_column_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModel_column_list(HanaParser.Model_column_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#model_column}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModel_column(HanaParser.Model_columnContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#model_rules_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModel_rules_clause(HanaParser.Model_rules_clauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#model_rules_part}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModel_rules_part(HanaParser.Model_rules_partContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#model_rules_element}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModel_rules_element(HanaParser.Model_rules_elementContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#cell_assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCell_assignment(HanaParser.Cell_assignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#model_iterate_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModel_iterate_clause(HanaParser.Model_iterate_clauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#until_part}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUntil_part(HanaParser.Until_partContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#order_by_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrder_by_clause(HanaParser.Order_by_clauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#order_by_elements}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrder_by_elements(HanaParser.Order_by_elementsContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#for_update_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFor_update_clause(HanaParser.For_update_clauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#for_update_of_part}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFor_update_of_part(HanaParser.For_update_of_partContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#for_update_options}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFor_update_options(HanaParser.For_update_optionsContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#update_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUpdate_statement(HanaParser.Update_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#update_set_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUpdate_set_clause(HanaParser.Update_set_clauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#column_based_update_set_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumn_based_update_set_clause(HanaParser.Column_based_update_set_clauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#delete_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDelete_statement(HanaParser.Delete_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#insert_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInsert_statement(HanaParser.Insert_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#declare_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclare_statement(HanaParser.Declare_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#exception_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitException_statement(HanaParser.Exception_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#proc_condition_value_}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProc_condition_value_(HanaParser.Proc_condition_value_Context ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#single_table_insert}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSingle_table_insert(HanaParser.Single_table_insertContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#multi_table_insert}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulti_table_insert(HanaParser.Multi_table_insertContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#multi_table_element}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulti_table_element(HanaParser.Multi_table_elementContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#conditional_insert_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConditional_insert_clause(HanaParser.Conditional_insert_clauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#conditional_insert_when_part}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConditional_insert_when_part(HanaParser.Conditional_insert_when_partContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#conditional_insert_else_part}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConditional_insert_else_part(HanaParser.Conditional_insert_else_partContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#insert_into_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInsert_into_clause(HanaParser.Insert_into_clauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#values_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValues_clause(HanaParser.Values_clauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#merge_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMerge_statement(HanaParser.Merge_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#merge_update_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMerge_update_clause(HanaParser.Merge_update_clauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#merge_element}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMerge_element(HanaParser.Merge_elementContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#merge_update_delete_part}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMerge_update_delete_part(HanaParser.Merge_update_delete_partContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#merge_insert_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMerge_insert_clause(HanaParser.Merge_insert_clauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#selected_tableview}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelected_tableview(HanaParser.Selected_tableviewContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#lock_table_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLock_table_statement(HanaParser.Lock_table_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#wait_nowait_part}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWait_nowait_part(HanaParser.Wait_nowait_partContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#lock_table_element}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLock_table_element(HanaParser.Lock_table_elementContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#lock_mode}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLock_mode(HanaParser.Lock_modeContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#general_table_ref}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGeneral_table_ref(HanaParser.General_table_refContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#static_returning_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatic_returning_clause(HanaParser.Static_returning_clauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#error_logging_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitError_logging_clause(HanaParser.Error_logging_clauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#error_logging_into_part}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitError_logging_into_part(HanaParser.Error_logging_into_partContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#error_logging_reject_part}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitError_logging_reject_part(HanaParser.Error_logging_reject_partContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#dml_table_expression_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDml_table_expression_clause(HanaParser.Dml_table_expression_clauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#table_collection_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTable_collection_expression(HanaParser.Table_collection_expressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#subquery_restriction_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubquery_restriction_clause(HanaParser.Subquery_restriction_clauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#sample_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSample_clause(HanaParser.Sample_clauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#seed_part}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSeed_part(HanaParser.Seed_partContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#cursor_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCursor_expression(HanaParser.Cursor_expressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#expression_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression_list(HanaParser.Expression_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondition(HanaParser.ConditionContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#condition_wrapper}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondition_wrapper(HanaParser.Condition_wrapperContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#condition_}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondition_(HanaParser.Condition_Context ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#predicate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPredicate(HanaParser.PredicateContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#comparison_predicate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparison_predicate(HanaParser.Comparison_predicateContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#relational_operator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelational_operator(HanaParser.Relational_operatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#range_predicate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRange_predicate(HanaParser.Range_predicateContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#in_predicate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIn_predicate(HanaParser.In_predicateContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#exist_predicate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExist_predicate(HanaParser.Exist_predicateContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#like_predicate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLike_predicate(HanaParser.Like_predicateContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#null_predicate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNull_predicate(HanaParser.Null_predicateContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#expression__list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression__list(HanaParser.Expression__listContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#expression_}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression_(HanaParser.Expression_Context ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#correlation_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCorrelation_name(HanaParser.Correlation_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#operator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperator(HanaParser.OperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#case_expression_}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCase_expression_(HanaParser.Case_expression_Context ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#simple_case_expression_}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimple_case_expression_(HanaParser.Simple_case_expression_Context ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#search_case_expression_}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSearch_case_expression_(HanaParser.Search_case_expression_Context ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#function_expression_}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction_expression_(HanaParser.Function_expression_Context ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#aggregate_expression_}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAggregate_expression_(HanaParser.Aggregate_expression_Context ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#agg_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAgg_name(HanaParser.Agg_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#delimiter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDelimiter(HanaParser.DelimiterContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#aggregate_order_by_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAggregate_order_by_clause(HanaParser.Aggregate_order_by_clauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(HanaParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#expression_wrapper}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression_wrapper(HanaParser.Expression_wrapperContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#logical_and_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogical_and_expression(HanaParser.Logical_and_expressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#negated_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNegated_expression(HanaParser.Negated_expressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#equality_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEquality_expression(HanaParser.Equality_expressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#multiset_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiset_expression(HanaParser.Multiset_expressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#multiset_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiset_type(HanaParser.Multiset_typeContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#relational_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelational_expression(HanaParser.Relational_expressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#relational_expression_operator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelational_expression_operator(HanaParser.Relational_expression_operatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#compound_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompound_expression(HanaParser.Compound_expressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#like_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLike_type(HanaParser.Like_typeContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#like_escape_part}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLike_escape_part(HanaParser.Like_escape_partContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#in_elements}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIn_elements(HanaParser.In_elementsContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#between_elements}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBetween_elements(HanaParser.Between_elementsContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#concatenation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConcatenation(HanaParser.ConcatenationContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#concatenation_wrapper}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConcatenation_wrapper(HanaParser.Concatenation_wrapperContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#additive_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdditive_expression(HanaParser.Additive_expressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#multiply_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiply_expression(HanaParser.Multiply_expressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#datetime_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDatetime_expression(HanaParser.Datetime_expressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#interval_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInterval_expression(HanaParser.Interval_expressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#model_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModel_expression(HanaParser.Model_expressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#model_expression_element}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModel_expression_element(HanaParser.Model_expression_elementContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#single_column_for_loop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSingle_column_for_loop(HanaParser.Single_column_for_loopContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#for_like_part}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFor_like_part(HanaParser.For_like_partContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#for_increment_decrement_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFor_increment_decrement_type(HanaParser.For_increment_decrement_typeContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#multi_column_for_loop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulti_column_for_loop(HanaParser.Multi_column_for_loopContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#unary_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnary_expression(HanaParser.Unary_expressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#case_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCase_statement(HanaParser.Case_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#simple_case_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimple_case_statement(HanaParser.Simple_case_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#simple_case_when_part}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimple_case_when_part(HanaParser.Simple_case_when_partContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#searched_case_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSearched_case_statement(HanaParser.Searched_case_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#searched_case_when_part}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSearched_case_when_part(HanaParser.Searched_case_when_partContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#case_else_part}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCase_else_part(HanaParser.Case_else_partContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtom(HanaParser.AtomContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#expression_or_vector}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression_or_vector(HanaParser.Expression_or_vectorContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#vector_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVector_expr(HanaParser.Vector_exprContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#quantified_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuantified_expression(HanaParser.Quantified_expressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#standard_function}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStandard_function(HanaParser.Standard_functionContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#over_clause_keyword}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOver_clause_keyword(HanaParser.Over_clause_keywordContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#within_or_over_clause_keyword}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWithin_or_over_clause_keyword(HanaParser.Within_or_over_clause_keywordContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#standard_prediction_function_keyword}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStandard_prediction_function_keyword(HanaParser.Standard_prediction_function_keywordContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#over_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOver_clause(HanaParser.Over_clauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#windowing_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWindowing_clause(HanaParser.Windowing_clauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#windowing_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWindowing_type(HanaParser.Windowing_typeContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#windowing_elements}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWindowing_elements(HanaParser.Windowing_elementsContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#using_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUsing_clause(HanaParser.Using_clauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#using_element}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUsing_element(HanaParser.Using_elementContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#collect_order_by_part}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCollect_order_by_part(HanaParser.Collect_order_by_partContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#within_or_over_part}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWithin_or_over_part(HanaParser.Within_or_over_partContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#cost_matrix_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCost_matrix_clause(HanaParser.Cost_matrix_clauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#xml_passing_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXml_passing_clause(HanaParser.Xml_passing_clauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#xml_attributes_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXml_attributes_clause(HanaParser.Xml_attributes_clauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#xml_namespaces_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXml_namespaces_clause(HanaParser.Xml_namespaces_clauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#xml_table_column}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXml_table_column(HanaParser.Xml_table_columnContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#xml_general_default_part}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXml_general_default_part(HanaParser.Xml_general_default_partContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#xml_multiuse_expression_element}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXml_multiuse_expression_element(HanaParser.Xml_multiuse_expression_elementContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#xmlroot_param_version_part}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXmlroot_param_version_part(HanaParser.Xmlroot_param_version_partContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#xmlroot_param_standalone_part}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXmlroot_param_standalone_part(HanaParser.Xmlroot_param_standalone_partContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#xmlserialize_param_enconding_part}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXmlserialize_param_enconding_part(HanaParser.Xmlserialize_param_enconding_partContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#xmlserialize_param_version_part}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXmlserialize_param_version_part(HanaParser.Xmlserialize_param_version_partContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#xmlserialize_param_ident_part}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXmlserialize_param_ident_part(HanaParser.Xmlserialize_param_ident_partContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#sql_plus_command}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSql_plus_command(HanaParser.Sql_plus_commandContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#whenever_command}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhenever_command(HanaParser.Whenever_commandContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#set_command}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSet_command(HanaParser.Set_commandContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#exit_command}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExit_command(HanaParser.Exit_commandContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#prompt_command}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrompt_command(HanaParser.Prompt_commandContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#partition_extension_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPartition_extension_clause(HanaParser.Partition_extension_clauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#column_alias}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumn_alias(HanaParser.Column_aliasContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#table_alias}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTable_alias(HanaParser.Table_aliasContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#alias_quoted_string}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlias_quoted_string(HanaParser.Alias_quoted_stringContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#where_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhere_clause(HanaParser.Where_clauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#current_of_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCurrent_of_clause(HanaParser.Current_of_clauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#into_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInto_clause(HanaParser.Into_clauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#xml_column_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXml_column_name(HanaParser.Xml_column_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#cost_class_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCost_class_name(HanaParser.Cost_class_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#attribute_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttribute_name(HanaParser.Attribute_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#savepoint_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSavepoint_name(HanaParser.Savepoint_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#rollback_segment_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRollback_segment_name(HanaParser.Rollback_segment_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#table_var_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTable_var_name(HanaParser.Table_var_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#schema_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSchema_name(HanaParser.Schema_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#routine_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRoutine_name(HanaParser.Routine_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#package_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPackage_name(HanaParser.Package_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#implementation_type_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImplementation_type_name(HanaParser.Implementation_type_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#reference_model_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReference_model_name(HanaParser.Reference_model_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#main_model_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMain_model_name(HanaParser.Main_model_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#aggregate_function_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAggregate_function_name(HanaParser.Aggregate_function_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#query_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuery_name(HanaParser.Query_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#constraint_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstraint_name(HanaParser.Constraint_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#label_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLabel_name(HanaParser.Label_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#type_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType_name(HanaParser.Type_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#sequence_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSequence_name(HanaParser.Sequence_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#exception_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitException_name(HanaParser.Exception_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#function_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction_name(HanaParser.Function_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#proc_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProc_name(HanaParser.Proc_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#trigger_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTrigger_name(HanaParser.Trigger_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#variable_name_old}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable_name_old(HanaParser.Variable_name_oldContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#index_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndex_name(HanaParser.Index_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#cursor_name_old}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCursor_name_old(HanaParser.Cursor_name_oldContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#record_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRecord_name(HanaParser.Record_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#collection_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCollection_name(HanaParser.Collection_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#link_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLink_name(HanaParser.Link_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#column_name_old}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumn_name_old(HanaParser.Column_name_oldContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#tableview_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableview_name(HanaParser.Tableview_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#char_set_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitChar_set_name(HanaParser.Char_set_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#keep_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeep_clause(HanaParser.Keep_clauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#function_argument}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction_argument(HanaParser.Function_argumentContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#function_argument_analytic}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction_argument_analytic(HanaParser.Function_argument_analyticContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#function_argument_modeling}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction_argument_modeling(HanaParser.Function_argument_modelingContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#respect_or_ignore_nulls}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRespect_or_ignore_nulls(HanaParser.Respect_or_ignore_nullsContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#argument}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgument(HanaParser.ArgumentContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#type_spec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType_spec(HanaParser.Type_specContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#datatype}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDatatype(HanaParser.DatatypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#precision_part}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrecision_part(HanaParser.Precision_partContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#native_datatype_element}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNative_datatype_element(HanaParser.Native_datatype_elementContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#bind_variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBind_variable(HanaParser.Bind_variableContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#bind_sql_error_code}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBind_sql_error_code(HanaParser.Bind_sql_error_codeContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#const_sql_error_code}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConst_sql_error_code(HanaParser.Const_sql_error_codeContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#bind_sql_error_message}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBind_sql_error_message(HanaParser.Bind_sql_error_messageContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#const_sql_error_message}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConst_sql_error_message(HanaParser.Const_sql_error_messageContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#general_element}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGeneral_element(HanaParser.General_elementContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#general_element_part}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGeneral_element_part(HanaParser.General_element_partContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#table_element}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTable_element(HanaParser.Table_elementContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstant(HanaParser.ConstantContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#numeric}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumeric(HanaParser.NumericContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#numeric_negative}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumeric_negative(HanaParser.Numeric_negativeContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#quoted_string}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuoted_string(HanaParser.Quoted_stringContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#id}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitId(HanaParser.IdContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#id_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitId_expression(HanaParser.Id_expressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#not_equal_op}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNot_equal_op(HanaParser.Not_equal_opContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#greater_than_or_equals_op}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGreater_than_or_equals_op(HanaParser.Greater_than_or_equals_opContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#less_than_or_equals_op}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLess_than_or_equals_op(HanaParser.Less_than_or_equals_opContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#concatenation_op}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConcatenation_op(HanaParser.Concatenation_opContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#outer_join_sign}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOuter_join_sign(HanaParser.Outer_join_signContext ctx);
	/**
	 * Visit a parse tree produced by {@link HanaParser#regular_id}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRegular_id(HanaParser.Regular_idContext ctx);
}