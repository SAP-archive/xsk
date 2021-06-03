// Generated from com/sap/xsk/parser/hdbschema/core/Hdbschema.g4 by ANTLR 4.3
package com.sap.xsk.parser.hdbschema.core;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link HdbschemaParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface HdbschemaVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link HdbschemaParser#schemaProp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSchemaProp(@NotNull HdbschemaParser.SchemaPropContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbschemaParser#hdbschemaDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHdbschemaDefinition(@NotNull HdbschemaParser.HdbschemaDefinitionContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbschemaParser#property}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProperty(@NotNull HdbschemaParser.PropertyContext ctx);
}