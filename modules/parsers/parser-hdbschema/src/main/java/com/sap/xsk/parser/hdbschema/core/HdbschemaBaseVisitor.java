// Generated from com\sap\xsk\parser\hdbschema\core\Hdbschema.g4 by ANTLR 4.3
package com.sap.xsk.parser.hdbschema.core;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;

/**
 * This class provides an empty implementation of {@link HdbschemaVisitor},
 * which can be extended to create a visitor which only needs to handle a subset
 * of the available methods.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public class HdbschemaBaseVisitor<T> extends AbstractParseTreeVisitor<T> implements HdbschemaVisitor<T> {
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public T visitHdbschemaDefinition(@NotNull HdbschemaParser.HdbschemaDefinitionContext ctx) { return visitChildren(ctx); }

	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public T visitSchemaNameProp(@NotNull HdbschemaParser.SchemaNamePropContext ctx) { return visitChildren(ctx); }
}