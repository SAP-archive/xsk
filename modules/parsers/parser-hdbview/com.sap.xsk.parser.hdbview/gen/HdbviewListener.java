// Generated from C:/Users/I069495/git/CSCProjects/KNeo/xsk/modules/parsers/parser-hdbview/com.sap.xsk.parser.hdbview/src/main/antlr4/com/sap/xsk/parser/hdbview/core\Hdbview.g4 by ANTLR 4.9.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link HdbviewParser}.
 */
public interface HdbviewListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link HdbviewParser#hdbviewDefinition}.
	 * @param ctx the parse tree
	 */
	void enterHdbviewDefinition(HdbviewParser.HdbviewDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbviewParser#hdbviewDefinition}.
	 * @param ctx the parse tree
	 */
	void exitHdbviewDefinition(HdbviewParser.HdbviewDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbviewParser#schemaProp}.
	 * @param ctx the parse tree
	 */
	void enterSchemaProp(HdbviewParser.SchemaPropContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbviewParser#schemaProp}.
	 * @param ctx the parse tree
	 */
	void exitSchemaProp(HdbviewParser.SchemaPropContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbviewParser#publicProp}.
	 * @param ctx the parse tree
	 */
	void enterPublicProp(HdbviewParser.PublicPropContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbviewParser#publicProp}.
	 * @param ctx the parse tree
	 */
	void exitPublicProp(HdbviewParser.PublicPropContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbviewParser#queryProp}.
	 * @param ctx the parse tree
	 */
	void enterQueryProp(HdbviewParser.QueryPropContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbviewParser#queryProp}.
	 * @param ctx the parse tree
	 */
	void exitQueryProp(HdbviewParser.QueryPropContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbviewParser#dependsOnProp}.
	 * @param ctx the parse tree
	 */
	void enterDependsOnProp(HdbviewParser.DependsOnPropContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbviewParser#dependsOnProp}.
	 * @param ctx the parse tree
	 */
	void exitDependsOnProp(HdbviewParser.DependsOnPropContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbviewParser#dependsOnTable}.
	 * @param ctx the parse tree
	 */
	void enterDependsOnTable(HdbviewParser.DependsOnTableContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbviewParser#dependsOnTable}.
	 * @param ctx the parse tree
	 */
	void exitDependsOnTable(HdbviewParser.DependsOnTableContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbviewParser#dependsOnView}.
	 * @param ctx the parse tree
	 */
	void enterDependsOnView(HdbviewParser.DependsOnViewContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbviewParser#dependsOnView}.
	 * @param ctx the parse tree
	 */
	void exitDependsOnView(HdbviewParser.DependsOnViewContext ctx);
}