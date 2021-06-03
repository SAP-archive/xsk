// Generated from com/sap/xsk/parser/hdbschema/core/Hdbschema.g4 by ANTLR 4.3
package com.sap.xsk.parser.hdbschema.core;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link HdbschemaParser}.
 */
public interface HdbschemaListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link HdbschemaParser#schemaProp}.
	 * @param ctx the parse tree
	 */
	void enterSchemaProp(@NotNull HdbschemaParser.SchemaPropContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbschemaParser#schemaProp}.
	 * @param ctx the parse tree
	 */
	void exitSchemaProp(@NotNull HdbschemaParser.SchemaPropContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbschemaParser#hdbschemaDefinition}.
	 * @param ctx the parse tree
	 */
	void enterHdbschemaDefinition(@NotNull HdbschemaParser.HdbschemaDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbschemaParser#hdbschemaDefinition}.
	 * @param ctx the parse tree
	 */
	void exitHdbschemaDefinition(@NotNull HdbschemaParser.HdbschemaDefinitionContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbschemaParser#property}.
	 * @param ctx the parse tree
	 */
	void enterProperty(@NotNull HdbschemaParser.PropertyContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbschemaParser#property}.
	 * @param ctx the parse tree
	 */
	void exitProperty(@NotNull HdbschemaParser.PropertyContext ctx);
}