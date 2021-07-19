// Generated from com\sap\xsk\parser\hdbschema\core\Hdbschema.g4 by ANTLR 4.3
package com.sap.xsk.parser.hdbschema.core;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link HdbschemaParser}.
 */
public interface HdbschemaListener extends ParseTreeListener {
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
	 * Enter a parse tree produced by {@link HdbschemaParser#schemaNameProp}.
	 * @param ctx the parse tree
	 */
	void enterSchemaNameProp(@NotNull HdbschemaParser.SchemaNamePropContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbschemaParser#schemaNameProp}.
	 * @param ctx the parse tree
	 */
	void exitSchemaNameProp(@NotNull HdbschemaParser.SchemaNamePropContext ctx);
}