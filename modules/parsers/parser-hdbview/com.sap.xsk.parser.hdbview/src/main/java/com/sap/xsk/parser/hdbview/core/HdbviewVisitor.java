// Generated from com/sap/xsk/parser/hdbview/core/Hdbview.g4 by ANTLR 4.3
package com.sap.xsk.parser.hdbview.core;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link HdbviewParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface HdbviewVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link HdbviewParser#queryProp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQueryProp(@NotNull HdbviewParser.QueryPropContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbviewParser#dependsOnProp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDependsOnProp(@NotNull HdbviewParser.DependsOnPropContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbviewParser#schemaProp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSchemaProp(@NotNull HdbviewParser.SchemaPropContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbviewParser#dependsOnView}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDependsOnView(@NotNull HdbviewParser.DependsOnViewContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbviewParser#property}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProperty(@NotNull HdbviewParser.PropertyContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbviewParser#hdbviewDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHdbviewDefinition(@NotNull HdbviewParser.HdbviewDefinitionContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbviewParser#publicProp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPublicProp(@NotNull HdbviewParser.PublicPropContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbviewParser#dependsOnTable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDependsOnTable(@NotNull HdbviewParser.DependsOnTableContext ctx);
}