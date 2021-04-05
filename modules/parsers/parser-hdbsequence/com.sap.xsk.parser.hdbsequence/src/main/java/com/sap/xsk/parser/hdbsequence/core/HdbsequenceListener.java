// Generated from com\sap\xsk\parser\hdbsequence\core\Hdbsequence.g4 by ANTLR 4.3
package com.sap.xsk.parser.hdbsequence.core;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link HdbsequenceParser}.
 */
public interface HdbsequenceListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link HdbsequenceParser#schema}.
	 * @param ctx the parse tree
	 */
	void enterSchema(@NotNull HdbsequenceParser.SchemaContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbsequenceParser#schema}.
	 * @param ctx the parse tree
	 */
	void exitSchema(@NotNull HdbsequenceParser.SchemaContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbsequenceParser#publicc}.
	 * @param ctx the parse tree
	 */
	void enterPublicc(@NotNull HdbsequenceParser.PubliccContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbsequenceParser#publicc}.
	 * @param ctx the parse tree
	 */
	void exitPublicc(@NotNull HdbsequenceParser.PubliccContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbsequenceParser#maxvalue}.
	 * @param ctx the parse tree
	 */
	void enterMaxvalue(@NotNull HdbsequenceParser.MaxvalueContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbsequenceParser#maxvalue}.
	 * @param ctx the parse tree
	 */
	void exitMaxvalue(@NotNull HdbsequenceParser.MaxvalueContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbsequenceParser#depends_on}.
	 * @param ctx the parse tree
	 */
	void enterDepends_on(@NotNull HdbsequenceParser.Depends_onContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbsequenceParser#depends_on}.
	 * @param ctx the parse tree
	 */
	void exitDepends_on(@NotNull HdbsequenceParser.Depends_onContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbsequenceParser#nomaxvalue}.
	 * @param ctx the parse tree
	 */
	void enterNomaxvalue(@NotNull HdbsequenceParser.NomaxvalueContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbsequenceParser#nomaxvalue}.
	 * @param ctx the parse tree
	 */
	void exitNomaxvalue(@NotNull HdbsequenceParser.NomaxvalueContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbsequenceParser#depends_on_table}.
	 * @param ctx the parse tree
	 */
	void enterDepends_on_table(@NotNull HdbsequenceParser.Depends_on_tableContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbsequenceParser#depends_on_table}.
	 * @param ctx the parse tree
	 */
	void exitDepends_on_table(@NotNull HdbsequenceParser.Depends_on_tableContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbsequenceParser#nominvalue}.
	 * @param ctx the parse tree
	 */
	void enterNominvalue(@NotNull HdbsequenceParser.NominvalueContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbsequenceParser#nominvalue}.
	 * @param ctx the parse tree
	 */
	void exitNominvalue(@NotNull HdbsequenceParser.NominvalueContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbsequenceParser#cycles}.
	 * @param ctx the parse tree
	 */
	void enterCycles(@NotNull HdbsequenceParser.CyclesContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbsequenceParser#cycles}.
	 * @param ctx the parse tree
	 */
	void exitCycles(@NotNull HdbsequenceParser.CyclesContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbsequenceParser#minvalue}.
	 * @param ctx the parse tree
	 */
	void enterMinvalue(@NotNull HdbsequenceParser.MinvalueContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbsequenceParser#minvalue}.
	 * @param ctx the parse tree
	 */
	void exitMinvalue(@NotNull HdbsequenceParser.MinvalueContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbsequenceParser#depends_on_view}.
	 * @param ctx the parse tree
	 */
	void enterDepends_on_view(@NotNull HdbsequenceParser.Depends_on_viewContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbsequenceParser#depends_on_view}.
	 * @param ctx the parse tree
	 */
	void exitDepends_on_view(@NotNull HdbsequenceParser.Depends_on_viewContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbsequenceParser#depends_on_list}.
	 * @param ctx the parse tree
	 */
	void enterDepends_on_list(@NotNull HdbsequenceParser.Depends_on_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbsequenceParser#depends_on_list}.
	 * @param ctx the parse tree
	 */
	void exitDepends_on_list(@NotNull HdbsequenceParser.Depends_on_listContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbsequenceParser#property}.
	 * @param ctx the parse tree
	 */
	void enterProperty(@NotNull HdbsequenceParser.PropertyContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbsequenceParser#property}.
	 * @param ctx the parse tree
	 */
	void exitProperty(@NotNull HdbsequenceParser.PropertyContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbsequenceParser#reset_by}.
	 * @param ctx the parse tree
	 */
	void enterReset_by(@NotNull HdbsequenceParser.Reset_byContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbsequenceParser#reset_by}.
	 * @param ctx the parse tree
	 */
	void exitReset_by(@NotNull HdbsequenceParser.Reset_byContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbsequenceParser#increment_by}.
	 * @param ctx the parse tree
	 */
	void enterIncrement_by(@NotNull HdbsequenceParser.Increment_byContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbsequenceParser#increment_by}.
	 * @param ctx the parse tree
	 */
	void exitIncrement_by(@NotNull HdbsequenceParser.Increment_byContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbsequenceParser#hdbsequence}.
	 * @param ctx the parse tree
	 */
	void enterHdbsequence(@NotNull HdbsequenceParser.HdbsequenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbsequenceParser#hdbsequence}.
	 * @param ctx the parse tree
	 */
	void exitHdbsequence(@NotNull HdbsequenceParser.HdbsequenceContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbsequenceParser#start_with}.
	 * @param ctx the parse tree
	 */
	void enterStart_with(@NotNull HdbsequenceParser.Start_withContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbsequenceParser#start_with}.
	 * @param ctx the parse tree
	 */
	void exitStart_with(@NotNull HdbsequenceParser.Start_withContext ctx);
}