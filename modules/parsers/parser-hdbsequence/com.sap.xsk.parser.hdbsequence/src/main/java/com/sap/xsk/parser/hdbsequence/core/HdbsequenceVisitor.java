// Generated from com\sap\xsk\parser\hdbsequence\core\Hdbsequence.g4 by ANTLR 4.3
package com.sap.xsk.parser.hdbsequence.core;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link HdbsequenceParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface HdbsequenceVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link HdbsequenceParser#schema}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSchema(@NotNull HdbsequenceParser.SchemaContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbsequenceParser#publicc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPublicc(@NotNull HdbsequenceParser.PubliccContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbsequenceParser#maxvalue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMaxvalue(@NotNull HdbsequenceParser.MaxvalueContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbsequenceParser#depends_on}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDepends_on(@NotNull HdbsequenceParser.Depends_onContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbsequenceParser#nomaxvalue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNomaxvalue(@NotNull HdbsequenceParser.NomaxvalueContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbsequenceParser#depends_on_table}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDepends_on_table(@NotNull HdbsequenceParser.Depends_on_tableContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbsequenceParser#nominvalue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNominvalue(@NotNull HdbsequenceParser.NominvalueContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbsequenceParser#cycles}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCycles(@NotNull HdbsequenceParser.CyclesContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbsequenceParser#minvalue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMinvalue(@NotNull HdbsequenceParser.MinvalueContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbsequenceParser#depends_on_view}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDepends_on_view(@NotNull HdbsequenceParser.Depends_on_viewContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbsequenceParser#depends_on_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDepends_on_list(@NotNull HdbsequenceParser.Depends_on_listContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbsequenceParser#reset_by}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReset_by(@NotNull HdbsequenceParser.Reset_byContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbsequenceParser#increment_by}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIncrement_by(@NotNull HdbsequenceParser.Increment_byContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbsequenceParser#hdbsequence}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHdbsequence(@NotNull HdbsequenceParser.HdbsequenceContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbsequenceParser#start_with}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStart_with(@NotNull HdbsequenceParser.Start_withContext ctx);
}