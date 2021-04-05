// Generated from C:/Users/I069495/git/CSCProjects/KNeo/xsk/modules/parsers/parser-hdbview/com.sap.xsk.parser.hdbview/src/main/antlr4/com/sap/xsk/parser/hdbview/core\Hdbview.g4 by ANTLR 4.9.1
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
	 * Visit a parse tree produced by {@link HdbviewParser#hdbviewDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHdbviewDefinition(HdbviewParser.HdbviewDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbviewParser#schemaProp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSchemaProp(HdbviewParser.SchemaPropContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbviewParser#publicProp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPublicProp(HdbviewParser.PublicPropContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbviewParser#queryProp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQueryProp(HdbviewParser.QueryPropContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbviewParser#dependsOnProp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDependsOnProp(HdbviewParser.DependsOnPropContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbviewParser#dependsOnTable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDependsOnTable(HdbviewParser.DependsOnTableContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbviewParser#dependsOnView}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDependsOnView(HdbviewParser.DependsOnViewContext ctx);
}