// Generated from com/sap/xsk/parser/hdbdd/core/Cds.g4 by ANTLR 4.3
package com.sap.xsk.parser.hdbdd.core;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CdsParser}.
 */
public interface CdsListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CdsParser#topLevelSymbol}.
	 * @param ctx the parse tree
	 */
	void enterTopLevelSymbol(@NotNull CdsParser.TopLevelSymbolContext ctx);
	/**
	 * Exit a parse tree produced by {@link CdsParser#topLevelSymbol}.
	 * @param ctx the parse tree
	 */
	void exitTopLevelSymbol(@NotNull CdsParser.TopLevelSymbolContext ctx);

	/**
	 * Enter a parse tree produced by the {@code AnnPropertyRule}
	 * labeled alternative in {@link CdsParser#annotationRule}.
	 * @param ctx the parse tree
	 */
	void enterAnnPropertyRule(@NotNull CdsParser.AnnPropertyRuleContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AnnPropertyRule}
	 * labeled alternative in {@link CdsParser#annotationRule}.
	 * @param ctx the parse tree
	 */
	void exitAnnPropertyRule(@NotNull CdsParser.AnnPropertyRuleContext ctx);

	/**
	 * Enter a parse tree produced by the {@code MaxCardinality}
	 * labeled alternative in {@link CdsParser#cardinality}.
	 * @param ctx the parse tree
	 */
	void enterMaxCardinality(@NotNull CdsParser.MaxCardinalityContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MaxCardinality}
	 * labeled alternative in {@link CdsParser#cardinality}.
	 * @param ctx the parse tree
	 */
	void exitMaxCardinality(@NotNull CdsParser.MaxCardinalityContext ctx);

	/**
	 * Enter a parse tree produced by the {@code MinMaxCardinality}
	 * labeled alternative in {@link CdsParser#cardinality}.
	 * @param ctx the parse tree
	 */
	void enterMinMaxCardinality(@NotNull CdsParser.MinMaxCardinalityContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MinMaxCardinality}
	 * labeled alternative in {@link CdsParser#cardinality}.
	 * @param ctx the parse tree
	 */
	void exitMinMaxCardinality(@NotNull CdsParser.MinMaxCardinalityContext ctx);

	/**
	 * Enter a parse tree produced by {@link CdsParser#usingRule}.
	 * @param ctx the parse tree
	 */
	void enterUsingRule(@NotNull CdsParser.UsingRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link CdsParser#usingRule}.
	 * @param ctx the parse tree
	 */
	void exitUsingRule(@NotNull CdsParser.UsingRuleContext ctx);

	/**
	 * Enter a parse tree produced by {@link CdsParser#keyValue}.
	 * @param ctx the parse tree
	 */
	void enterKeyValue(@NotNull CdsParser.KeyValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link CdsParser#keyValue}.
	 * @param ctx the parse tree
	 */
	void exitKeyValue(@NotNull CdsParser.KeyValueContext ctx);

	/**
	 * Enter a parse tree produced by the {@code AssignBuiltInTypeWithArgs}
	 * labeled alternative in {@link CdsParser#typeAssignRule}.
	 * @param ctx the parse tree
	 */
	void enterAssignBuiltInTypeWithArgs(@NotNull CdsParser.AssignBuiltInTypeWithArgsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AssignBuiltInTypeWithArgs}
	 * labeled alternative in {@link CdsParser#typeAssignRule}.
	 * @param ctx the parse tree
	 */
	void exitAssignBuiltInTypeWithArgs(@NotNull CdsParser.AssignBuiltInTypeWithArgsContext ctx);

	/**
	 * Enter a parse tree produced by {@link CdsParser#defaultValue}.
	 * @param ctx the parse tree
	 */
	void enterDefaultValue(@NotNull CdsParser.DefaultValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link CdsParser#defaultValue}.
	 * @param ctx the parse tree
	 */
	void exitDefaultValue(@NotNull CdsParser.DefaultValueContext ctx);

	/**
	 * Enter a parse tree produced by {@link CdsParser#association}.
	 * @param ctx the parse tree
	 */
	void enterAssociation(@NotNull CdsParser.AssociationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CdsParser#association}.
	 * @param ctx the parse tree
	 */
	void exitAssociation(@NotNull CdsParser.AssociationContext ctx);

	/**
	 * Enter a parse tree produced by the {@code AssignHanaType}
	 * labeled alternative in {@link CdsParser#typeAssignRule}.
	 * @param ctx the parse tree
	 */
	void enterAssignHanaType(@NotNull CdsParser.AssignHanaTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AssignHanaType}
	 * labeled alternative in {@link CdsParser#typeAssignRule}.
	 * @param ctx the parse tree
	 */
	void exitAssignHanaType(@NotNull CdsParser.AssignHanaTypeContext ctx);

	/**
	 * Enter a parse tree produced by {@link CdsParser#structuredDataTypeRule}.
	 * @param ctx the parse tree
	 */
	void enterStructuredDataTypeRule(@NotNull CdsParser.StructuredDataTypeRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link CdsParser#structuredDataTypeRule}.
	 * @param ctx the parse tree
	 */
	void exitStructuredDataTypeRule(@NotNull CdsParser.StructuredDataTypeRuleContext ctx);

	/**
	 * Enter a parse tree produced by {@link CdsParser#annValue}.
	 * @param ctx the parse tree
	 */
	void enterAnnValue(@NotNull CdsParser.AnnValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link CdsParser#annValue}.
	 * @param ctx the parse tree
	 */
	void exitAnnValue(@NotNull CdsParser.AnnValueContext ctx);

	/**
	 * Enter a parse tree produced by {@link CdsParser#enumRule}.
	 * @param ctx the parse tree
	 */
	void enterEnumRule(@NotNull CdsParser.EnumRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link CdsParser#enumRule}.
	 * @param ctx the parse tree
	 */
	void exitEnumRule(@NotNull CdsParser.EnumRuleContext ctx);

	/**
	 * Enter a parse tree produced by {@link CdsParser#arrRule}.
	 * @param ctx the parse tree
	 */
	void enterArrRule(@NotNull CdsParser.ArrRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link CdsParser#arrRule}.
	 * @param ctx the parse tree
	 */
	void exitArrRule(@NotNull CdsParser.ArrRuleContext ctx);

	/**
	 * Enter a parse tree produced by the {@code NoCardinality}
	 * labeled alternative in {@link CdsParser#cardinality}.
	 * @param ctx the parse tree
	 */
	void enterNoCardinality(@NotNull CdsParser.NoCardinalityContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NoCardinality}
	 * labeled alternative in {@link CdsParser#cardinality}.
	 * @param ctx the parse tree
	 */
	void exitNoCardinality(@NotNull CdsParser.NoCardinalityContext ctx);

	/**
	 * Enter a parse tree produced by {@link CdsParser#fieldDeclRule}.
	 * @param ctx the parse tree
	 */
	void enterFieldDeclRule(@NotNull CdsParser.FieldDeclRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link CdsParser#fieldDeclRule}.
	 * @param ctx the parse tree
	 */
	void exitFieldDeclRule(@NotNull CdsParser.FieldDeclRuleContext ctx);

	/**
	 * Enter a parse tree produced by {@link CdsParser#elementDeclRule}.
	 * @param ctx the parse tree
	 */
	void enterElementDeclRule(@NotNull CdsParser.ElementDeclRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link CdsParser#elementDeclRule}.
	 * @param ctx the parse tree
	 */
	void exitElementDeclRule(@NotNull CdsParser.ElementDeclRuleContext ctx);

	/**
	 * Enter a parse tree produced by {@link CdsParser#contextRule}.
	 * @param ctx the parse tree
	 */
	void enterContextRule(@NotNull CdsParser.ContextRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link CdsParser#contextRule}.
	 * @param ctx the parse tree
	 */
	void exitContextRule(@NotNull CdsParser.ContextRuleContext ctx);

	/**
	 * Enter a parse tree produced by the {@code AssignHanaTypeWithArgs}
	 * labeled alternative in {@link CdsParser#typeAssignRule}.
	 * @param ctx the parse tree
	 */
	void enterAssignHanaTypeWithArgs(@NotNull CdsParser.AssignHanaTypeWithArgsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AssignHanaTypeWithArgs}
	 * labeled alternative in {@link CdsParser#typeAssignRule}.
	 * @param ctx the parse tree
	 */
	void exitAssignHanaTypeWithArgs(@NotNull CdsParser.AssignHanaTypeWithArgsContext ctx);

	/**
	 * Enter a parse tree produced by the {@code AssignType}
	 * labeled alternative in {@link CdsParser#typeAssignRule}.
	 * @param ctx the parse tree
	 */
	void enterAssignType(@NotNull CdsParser.AssignTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AssignType}
	 * labeled alternative in {@link CdsParser#typeAssignRule}.
	 * @param ctx the parse tree
	 */
	void exitAssignType(@NotNull CdsParser.AssignTypeContext ctx);

	/**
	 * Enter a parse tree produced by the {@code AnnMarkerRule}
	 * labeled alternative in {@link CdsParser#annotationRule}.
	 * @param ctx the parse tree
	 */
	void enterAnnMarkerRule(@NotNull CdsParser.AnnMarkerRuleContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AnnMarkerRule}
	 * labeled alternative in {@link CdsParser#annotationRule}.
	 * @param ctx the parse tree
	 */
	void exitAnnMarkerRule(@NotNull CdsParser.AnnMarkerRuleContext ctx);

	/**
	 * Enter a parse tree produced by {@link CdsParser#elementDetails}.
	 * @param ctx the parse tree
	 */
	void enterElementDetails(@NotNull CdsParser.ElementDetailsContext ctx);
	/**
	 * Exit a parse tree produced by {@link CdsParser#elementDetails}.
	 * @param ctx the parse tree
	 */
	void exitElementDetails(@NotNull CdsParser.ElementDetailsContext ctx);

	/**
	 * Enter a parse tree produced by {@link CdsParser#entityRule}.
	 * @param ctx the parse tree
	 */
	void enterEntityRule(@NotNull CdsParser.EntityRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link CdsParser#entityRule}.
	 * @param ctx the parse tree
	 */
	void exitEntityRule(@NotNull CdsParser.EntityRuleContext ctx);

	/**
	 * Enter a parse tree produced by {@link CdsParser#associationTarget}.
	 * @param ctx the parse tree
	 */
	void enterAssociationTarget(@NotNull CdsParser.AssociationTargetContext ctx);
	/**
	 * Exit a parse tree produced by {@link CdsParser#associationTarget}.
	 * @param ctx the parse tree
	 */
	void exitAssociationTarget(@NotNull CdsParser.AssociationTargetContext ctx);

	/**
	 * Enter a parse tree produced by {@link CdsParser#dataTypeRule}.
	 * @param ctx the parse tree
	 */
	void enterDataTypeRule(@NotNull CdsParser.DataTypeRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link CdsParser#dataTypeRule}.
	 * @param ctx the parse tree
	 */
	void exitDataTypeRule(@NotNull CdsParser.DataTypeRuleContext ctx);

	/**
	 * Enter a parse tree produced by {@link CdsParser#cdsFile}.
	 * @param ctx the parse tree
	 */
	void enterCdsFile(@NotNull CdsParser.CdsFileContext ctx);
	/**
	 * Exit a parse tree produced by {@link CdsParser#cdsFile}.
	 * @param ctx the parse tree
	 */
	void exitCdsFile(@NotNull CdsParser.CdsFileContext ctx);

	/**
	 * Enter a parse tree produced by {@link CdsParser#namespaceRule}.
	 * @param ctx the parse tree
	 */
	void enterNamespaceRule(@NotNull CdsParser.NamespaceRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link CdsParser#namespaceRule}.
	 * @param ctx the parse tree
	 */
	void exitNamespaceRule(@NotNull CdsParser.NamespaceRuleContext ctx);

	/**
	 * Enter a parse tree produced by {@link CdsParser#unmanagedForeignKey}.
	 * @param ctx the parse tree
	 */
	void enterUnmanagedForeignKey(@NotNull CdsParser.UnmanagedForeignKeyContext ctx);
	/**
	 * Exit a parse tree produced by {@link CdsParser#unmanagedForeignKey}.
	 * @param ctx the parse tree
	 */
	void exitUnmanagedForeignKey(@NotNull CdsParser.UnmanagedForeignKeyContext ctx);

	/**
	 * Enter a parse tree produced by {@link CdsParser#obj}.
	 * @param ctx the parse tree
	 */
	void enterObj(@NotNull CdsParser.ObjContext ctx);
	/**
	 * Exit a parse tree produced by {@link CdsParser#obj}.
	 * @param ctx the parse tree
	 */
	void exitObj(@NotNull CdsParser.ObjContext ctx);

	/**
	 * Enter a parse tree produced by {@link CdsParser#elementConstraints}.
	 * @param ctx the parse tree
	 */
	void enterElementConstraints(@NotNull CdsParser.ElementConstraintsContext ctx);
	/**
	 * Exit a parse tree produced by {@link CdsParser#elementConstraints}.
	 * @param ctx the parse tree
	 */
	void exitElementConstraints(@NotNull CdsParser.ElementConstraintsContext ctx);

	/**
	 * Enter a parse tree produced by {@link CdsParser#managedForeignKeys}.
	 * @param ctx the parse tree
	 */
	void enterManagedForeignKeys(@NotNull CdsParser.ManagedForeignKeysContext ctx);
	/**
	 * Exit a parse tree produced by {@link CdsParser#managedForeignKeys}.
	 * @param ctx the parse tree
	 */
	void exitManagedForeignKeys(@NotNull CdsParser.ManagedForeignKeysContext ctx);

	/**
	 * Enter a parse tree produced by the {@code AnnObjectRule}
	 * labeled alternative in {@link CdsParser#annotationRule}.
	 * @param ctx the parse tree
	 */
	void enterAnnObjectRule(@NotNull CdsParser.AnnObjectRuleContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AnnObjectRule}
	 * labeled alternative in {@link CdsParser#annotationRule}.
	 * @param ctx the parse tree
	 */
	void exitAnnObjectRule(@NotNull CdsParser.AnnObjectRuleContext ctx);

	/**
	 * Enter a parse tree produced by {@link CdsParser#foreignKey}.
	 * @param ctx the parse tree
	 */
	void enterForeignKey(@NotNull CdsParser.ForeignKeyContext ctx);
	/**
	 * Exit a parse tree produced by {@link CdsParser#foreignKey}.
	 * @param ctx the parse tree
	 */
	void exitForeignKey(@NotNull CdsParser.ForeignKeyContext ctx);
}