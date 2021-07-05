// Generated from com/sap/xsk/parser/hdbsynonym/core/Hdbsynonym.g4 by ANTLR 4.3
package com.sap.xsk.parser.hdbsynonym.core;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link HdbsynonymParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface HdbsynonymVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link HdbsynonymParser#synonymElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSynonymElement(@NotNull HdbsynonymParser.SynonymElementContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbsynonymParser#synonymTargetProp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSynonymTargetProp(@NotNull HdbsynonymParser.SynonymTargetPropContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbsynonymParser#synonymBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSynonymBody(@NotNull HdbsynonymParser.SynonymBodyContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbsynonymParser#location}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLocation(@NotNull HdbsynonymParser.LocationContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbsynonymParser#synonymTarget}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSynonymTarget(@NotNull HdbsynonymParser.SynonymTargetContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbsynonymParser#hdbsynonymDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHdbsynonymDefinition(@NotNull HdbsynonymParser.HdbsynonymDefinitionContext ctx);

	/**
	 * Visit a parse tree produced by {@link HdbsynonymParser#synonymSchema}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSynonymSchema(@NotNull HdbsynonymParser.SynonymSchemaContext ctx);
}