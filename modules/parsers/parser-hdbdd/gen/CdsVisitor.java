// Generated from /Users/C5322976/Desktop/Workspace/Dirigible/xsk/modules/parsers/parser-hdbdd/src/main/antlr4/com/sap/xsk/parser/hdbdd/core/Cds.g4 by ANTLR 4.9.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link CdsParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface CdsVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link CdsParser#cdsFile}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCdsFile(CdsParser.CdsFileContext ctx);
	/**
	 * Visit a parse tree produced by {@link CdsParser#namespaceRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNamespaceRule(CdsParser.NamespaceRuleContext ctx);
	/**
	 * Visit a parse tree produced by {@link CdsParser#usingRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUsingRule(CdsParser.UsingRuleContext ctx);
	/**
	 * Visit a parse tree produced by {@link CdsParser#topLevelSymbol}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTopLevelSymbol(CdsParser.TopLevelSymbolContext ctx);
	/**
	 * Visit a parse tree produced by {@link CdsParser#dataTypeRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDataTypeRule(CdsParser.DataTypeRuleContext ctx);
	/**
	 * Visit a parse tree produced by {@link CdsParser#fieldDeclRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFieldDeclRule(CdsParser.FieldDeclRuleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AssignBuiltInTypeWithArgs}
	 * labeled alternative in {@link CdsParser#typeAssignRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignBuiltInTypeWithArgs(CdsParser.AssignBuiltInTypeWithArgsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AssignHanaType}
	 * labeled alternative in {@link CdsParser#typeAssignRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignHanaType(CdsParser.AssignHanaTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AssignHanaTypeWithArgs}
	 * labeled alternative in {@link CdsParser#typeAssignRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignHanaTypeWithArgs(CdsParser.AssignHanaTypeWithArgsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AssignType}
	 * labeled alternative in {@link CdsParser#typeAssignRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignType(CdsParser.AssignTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link CdsParser#elementDeclRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElementDeclRule(CdsParser.ElementDeclRuleContext ctx);
	/**
	 * Visit a parse tree produced by {@link CdsParser#elementDetails}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElementDetails(CdsParser.ElementDetailsContext ctx);
	/**
	 * Visit a parse tree produced by {@link CdsParser#elementConstraints}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElementConstraints(CdsParser.ElementConstraintsContext ctx);
	/**
	 * Visit a parse tree produced by {@link CdsParser#association}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssociation(CdsParser.AssociationContext ctx);
	/**
	 * Visit a parse tree produced by {@link CdsParser#associationTarget}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssociationTarget(CdsParser.AssociationTargetContext ctx);
	/**
	 * Visit a parse tree produced by {@link CdsParser#unmanagedForeignKey}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnmanagedForeignKey(CdsParser.UnmanagedForeignKeyContext ctx);
	/**
	 * Visit a parse tree produced by {@link CdsParser#managedForeignKeys}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitManagedForeignKeys(CdsParser.ManagedForeignKeysContext ctx);
	/**
	 * Visit a parse tree produced by {@link CdsParser#foreignKey}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForeignKey(CdsParser.ForeignKeyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MinMaxCardinality}
	 * labeled alternative in {@link CdsParser#cardinality}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMinMaxCardinality(CdsParser.MinMaxCardinalityContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MaxCardinality}
	 * labeled alternative in {@link CdsParser#cardinality}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMaxCardinality(CdsParser.MaxCardinalityContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NoCardinality}
	 * labeled alternative in {@link CdsParser#cardinality}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNoCardinality(CdsParser.NoCardinalityContext ctx);
	/**
	 * Visit a parse tree produced by {@link CdsParser#defaultValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefaultValue(CdsParser.DefaultValueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AnnObjectRule}
	 * labeled alternative in {@link CdsParser#annotationRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnnObjectRule(CdsParser.AnnObjectRuleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AnnPropertyRule}
	 * labeled alternative in {@link CdsParser#annotationRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnnPropertyRule(CdsParser.AnnPropertyRuleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AnnMarkerRule}
	 * labeled alternative in {@link CdsParser#annotationRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnnMarkerRule(CdsParser.AnnMarkerRuleContext ctx);
	/**
	 * Visit a parse tree produced by {@link CdsParser#annValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnnValue(CdsParser.AnnValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link CdsParser#enumRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnumRule(CdsParser.EnumRuleContext ctx);
	/**
	 * Visit a parse tree produced by {@link CdsParser#arrRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrRule(CdsParser.ArrRuleContext ctx);
	/**
	 * Visit a parse tree produced by {@link CdsParser#obj}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObj(CdsParser.ObjContext ctx);
	/**
	 * Visit a parse tree produced by {@link CdsParser#keyValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyValue(CdsParser.KeyValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link CdsParser#artifactRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArtifactRule(CdsParser.ArtifactRuleContext ctx);
}