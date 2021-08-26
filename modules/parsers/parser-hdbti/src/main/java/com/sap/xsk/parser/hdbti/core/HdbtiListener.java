// Generated from com\sap\xsk\parser\hdbti\core\Hdbti.g4 by ANTLR 4.3
package com.sap.xsk.parser.hdbti.core;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link HdbtiParser}.
 */
public interface HdbtiListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link HdbtiParser#objConfig}.
	 * @param ctx the parse tree
	 */
	void enterObjConfig(@NotNull HdbtiParser.ObjConfigContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtiParser#objConfig}.
	 * @param ctx the parse tree
	 */
	void exitObjConfig(@NotNull HdbtiParser.ObjConfigContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbtiParser#assignUseHeaderNames}.
	 * @param ctx the parse tree
	 */
	void enterAssignUseHeaderNames(@NotNull HdbtiParser.AssignUseHeaderNamesContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtiParser#assignUseHeaderNames}.
	 * @param ctx the parse tree
	 */
	void exitAssignUseHeaderNames(@NotNull HdbtiParser.AssignUseHeaderNamesContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbtiParser#assignExpression}.
	 * @param ctx the parse tree
	 */
	void enterAssignExpression(@NotNull HdbtiParser.AssignExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtiParser#assignExpression}.
	 * @param ctx the parse tree
	 */
	void exitAssignExpression(@NotNull HdbtiParser.AssignExpressionContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbtiParser#assignHeader}.
	 * @param ctx the parse tree
	 */
	void enterAssignHeader(@NotNull HdbtiParser.AssignHeaderContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtiParser#assignHeader}.
	 * @param ctx the parse tree
	 */
	void exitAssignHeader(@NotNull HdbtiParser.AssignHeaderContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbtiParser#assignDistinguishEmptyFromNull}.
	 * @param ctx the parse tree
	 */
	void enterAssignDistinguishEmptyFromNull(@NotNull HdbtiParser.AssignDistinguishEmptyFromNullContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtiParser#assignDistinguishEmptyFromNull}.
	 * @param ctx the parse tree
	 */
	void exitAssignDistinguishEmptyFromNull(@NotNull HdbtiParser.AssignDistinguishEmptyFromNullContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbtiParser#importArr}.
	 * @param ctx the parse tree
	 */
	void enterImportArr(@NotNull HdbtiParser.ImportArrContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtiParser#importArr}.
	 * @param ctx the parse tree
	 */
	void exitImportArr(@NotNull HdbtiParser.ImportArrContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbtiParser#assignDelimEnclosing}.
	 * @param ctx the parse tree
	 */
	void enterAssignDelimEnclosing(@NotNull HdbtiParser.AssignDelimEnclosingContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtiParser#assignDelimEnclosing}.
	 * @param ctx the parse tree
	 */
	void exitAssignDelimEnclosing(@NotNull HdbtiParser.AssignDelimEnclosingContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbtiParser#pair}.
	 * @param ctx the parse tree
	 */
	void enterPair(@NotNull HdbtiParser.PairContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtiParser#pair}.
	 * @param ctx the parse tree
	 */
	void exitPair(@NotNull HdbtiParser.PairContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbtiParser#tableName}.
	 * @param ctx the parse tree
	 */
	void enterTableName(@NotNull HdbtiParser.TableNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtiParser#tableName}.
	 * @param ctx the parse tree
	 */
	void exitTableName(@NotNull HdbtiParser.TableNameContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbtiParser#assignFile}.
	 * @param ctx the parse tree
	 */
	void enterAssignFile(@NotNull HdbtiParser.AssignFileContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtiParser#assignFile}.
	 * @param ctx the parse tree
	 */
	void exitAssignFile(@NotNull HdbtiParser.AssignFileContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbtiParser#assignDelimField}.
	 * @param ctx the parse tree
	 */
	void enterAssignDelimField(@NotNull HdbtiParser.AssignDelimFieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtiParser#assignDelimField}.
	 * @param ctx the parse tree
	 */
	void exitAssignDelimField(@NotNull HdbtiParser.AssignDelimFieldContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbtiParser#pairValue}.
	 * @param ctx the parse tree
	 */
	void enterPairValue(@NotNull HdbtiParser.PairValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtiParser#pairValue}.
	 * @param ctx the parse tree
	 */
	void exitPairValue(@NotNull HdbtiParser.PairValueContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbtiParser#assignSchema}.
	 * @param ctx the parse tree
	 */
	void enterAssignSchema(@NotNull HdbtiParser.AssignSchemaContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtiParser#assignSchema}.
	 * @param ctx the parse tree
	 */
	void exitAssignSchema(@NotNull HdbtiParser.AssignSchemaContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbtiParser#pairKey}.
	 * @param ctx the parse tree
	 */
	void enterPairKey(@NotNull HdbtiParser.PairKeyContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtiParser#pairKey}.
	 * @param ctx the parse tree
	 */
	void exitPairKey(@NotNull HdbtiParser.PairKeyContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbtiParser#assignTable}.
	 * @param ctx the parse tree
	 */
	void enterAssignTable(@NotNull HdbtiParser.AssignTableContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtiParser#assignTable}.
	 * @param ctx the parse tree
	 */
	void exitAssignTable(@NotNull HdbtiParser.AssignTableContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbtiParser#assignKeys}.
	 * @param ctx the parse tree
	 */
	void enterAssignKeys(@NotNull HdbtiParser.AssignKeysContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtiParser#assignKeys}.
	 * @param ctx the parse tree
	 */
	void exitAssignKeys(@NotNull HdbtiParser.AssignKeysContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbtiParser#keyArr}.
	 * @param ctx the parse tree
	 */
	void enterKeyArr(@NotNull HdbtiParser.KeyArrContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtiParser#keyArr}.
	 * @param ctx the parse tree
	 */
	void exitKeyArr(@NotNull HdbtiParser.KeyArrContext ctx);
}