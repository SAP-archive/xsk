/*
 * Copyright (c) 2022 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2022 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
// Generated from com/sap/xsk/parser/hdbdd/core/Cds.g4 by ANTLR 4.9.3
package com.sap.xsk.parser.hdbdd.core;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CdsParser}.
 */
public interface CdsListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CdsParser#cdsFile}.
	 * @param ctx the parse tree
	 */
	void enterCdsFile(CdsParser.CdsFileContext ctx);
	/**
	 * Exit a parse tree produced by {@link CdsParser#cdsFile}.
	 * @param ctx the parse tree
	 */
	void exitCdsFile(CdsParser.CdsFileContext ctx);
	/**
	 * Enter a parse tree produced by {@link CdsParser#namespaceRule}.
	 * @param ctx the parse tree
	 */
	void enterNamespaceRule(CdsParser.NamespaceRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link CdsParser#namespaceRule}.
	 * @param ctx the parse tree
	 */
	void exitNamespaceRule(CdsParser.NamespaceRuleContext ctx);
	/**
	 * Enter a parse tree produced by {@link CdsParser#usingRule}.
	 * @param ctx the parse tree
	 */
	void enterUsingRule(CdsParser.UsingRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link CdsParser#usingRule}.
	 * @param ctx the parse tree
	 */
	void exitUsingRule(CdsParser.UsingRuleContext ctx);
	/**
	 * Enter a parse tree produced by {@link CdsParser#topLevelSymbol}.
	 * @param ctx the parse tree
	 */
	void enterTopLevelSymbol(CdsParser.TopLevelSymbolContext ctx);
	/**
	 * Exit a parse tree produced by {@link CdsParser#topLevelSymbol}.
	 * @param ctx the parse tree
	 */
	void exitTopLevelSymbol(CdsParser.TopLevelSymbolContext ctx);
	/**
	 * Enter a parse tree produced by {@link CdsParser#dataTypeRule}.
	 * @param ctx the parse tree
	 */
	void enterDataTypeRule(CdsParser.DataTypeRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link CdsParser#dataTypeRule}.
	 * @param ctx the parse tree
	 */
	void exitDataTypeRule(CdsParser.DataTypeRuleContext ctx);
	/**
	 * Enter a parse tree produced by {@link CdsParser#fieldDeclRule}.
	 * @param ctx the parse tree
	 */
	void enterFieldDeclRule(CdsParser.FieldDeclRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link CdsParser#fieldDeclRule}.
	 * @param ctx the parse tree
	 */
	void exitFieldDeclRule(CdsParser.FieldDeclRuleContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AssignBuiltInTypeWithArgs}
	 * labeled alternative in {@link CdsParser#typeAssignRule}.
	 * @param ctx the parse tree
	 */
	void enterAssignBuiltInTypeWithArgs(CdsParser.AssignBuiltInTypeWithArgsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AssignBuiltInTypeWithArgs}
	 * labeled alternative in {@link CdsParser#typeAssignRule}.
	 * @param ctx the parse tree
	 */
	void exitAssignBuiltInTypeWithArgs(CdsParser.AssignBuiltInTypeWithArgsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AssignHanaType}
	 * labeled alternative in {@link CdsParser#typeAssignRule}.
	 * @param ctx the parse tree
	 */
	void enterAssignHanaType(CdsParser.AssignHanaTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AssignHanaType}
	 * labeled alternative in {@link CdsParser#typeAssignRule}.
	 * @param ctx the parse tree
	 */
	void exitAssignHanaType(CdsParser.AssignHanaTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AssignHanaTypeWithArgs}
	 * labeled alternative in {@link CdsParser#typeAssignRule}.
	 * @param ctx the parse tree
	 */
	void enterAssignHanaTypeWithArgs(CdsParser.AssignHanaTypeWithArgsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AssignHanaTypeWithArgs}
	 * labeled alternative in {@link CdsParser#typeAssignRule}.
	 * @param ctx the parse tree
	 */
	void exitAssignHanaTypeWithArgs(CdsParser.AssignHanaTypeWithArgsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AssignType}
	 * labeled alternative in {@link CdsParser#typeAssignRule}.
	 * @param ctx the parse tree
	 */
	void enterAssignType(CdsParser.AssignTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AssignType}
	 * labeled alternative in {@link CdsParser#typeAssignRule}.
	 * @param ctx the parse tree
	 */
	void exitAssignType(CdsParser.AssignTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link CdsParser#elementDeclRule}.
	 * @param ctx the parse tree
	 */
	void enterElementDeclRule(CdsParser.ElementDeclRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link CdsParser#elementDeclRule}.
	 * @param ctx the parse tree
	 */
	void exitElementDeclRule(CdsParser.ElementDeclRuleContext ctx);
	/**
	 * Enter a parse tree produced by {@link CdsParser#elementDetails}.
	 * @param ctx the parse tree
	 */
	void enterElementDetails(CdsParser.ElementDetailsContext ctx);
	/**
	 * Exit a parse tree produced by {@link CdsParser#elementDetails}.
	 * @param ctx the parse tree
	 */
	void exitElementDetails(CdsParser.ElementDetailsContext ctx);
	/**
	 * Enter a parse tree produced by {@link CdsParser#elementConstraints}.
	 * @param ctx the parse tree
	 */
	void enterElementConstraints(CdsParser.ElementConstraintsContext ctx);
	/**
	 * Exit a parse tree produced by {@link CdsParser#elementConstraints}.
	 * @param ctx the parse tree
	 */
	void exitElementConstraints(CdsParser.ElementConstraintsContext ctx);
	/**
	 * Enter a parse tree produced by {@link CdsParser#association}.
	 * @param ctx the parse tree
	 */
	void enterAssociation(CdsParser.AssociationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CdsParser#association}.
	 * @param ctx the parse tree
	 */
	void exitAssociation(CdsParser.AssociationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CdsParser#associationTarget}.
	 * @param ctx the parse tree
	 */
	void enterAssociationTarget(CdsParser.AssociationTargetContext ctx);
	/**
	 * Exit a parse tree produced by {@link CdsParser#associationTarget}.
	 * @param ctx the parse tree
	 */
	void exitAssociationTarget(CdsParser.AssociationTargetContext ctx);
	/**
	 * Enter a parse tree produced by {@link CdsParser#unmanagedForeignKey}.
	 * @param ctx the parse tree
	 */
	void enterUnmanagedForeignKey(CdsParser.UnmanagedForeignKeyContext ctx);
	/**
	 * Exit a parse tree produced by {@link CdsParser#unmanagedForeignKey}.
	 * @param ctx the parse tree
	 */
	void exitUnmanagedForeignKey(CdsParser.UnmanagedForeignKeyContext ctx);
	/**
	 * Enter a parse tree produced by {@link CdsParser#managedForeignKeys}.
	 * @param ctx the parse tree
	 */
	void enterManagedForeignKeys(CdsParser.ManagedForeignKeysContext ctx);
	/**
	 * Exit a parse tree produced by {@link CdsParser#managedForeignKeys}.
	 * @param ctx the parse tree
	 */
	void exitManagedForeignKeys(CdsParser.ManagedForeignKeysContext ctx);
	/**
	 * Enter a parse tree produced by {@link CdsParser#foreignKey}.
	 * @param ctx the parse tree
	 */
	void enterForeignKey(CdsParser.ForeignKeyContext ctx);
	/**
	 * Exit a parse tree produced by {@link CdsParser#foreignKey}.
	 * @param ctx the parse tree
	 */
	void exitForeignKey(CdsParser.ForeignKeyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MinMaxCardinality}
	 * labeled alternative in {@link CdsParser#cardinality}.
	 * @param ctx the parse tree
	 */
	void enterMinMaxCardinality(CdsParser.MinMaxCardinalityContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MinMaxCardinality}
	 * labeled alternative in {@link CdsParser#cardinality}.
	 * @param ctx the parse tree
	 */
	void exitMinMaxCardinality(CdsParser.MinMaxCardinalityContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MaxCardinality}
	 * labeled alternative in {@link CdsParser#cardinality}.
	 * @param ctx the parse tree
	 */
	void enterMaxCardinality(CdsParser.MaxCardinalityContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MaxCardinality}
	 * labeled alternative in {@link CdsParser#cardinality}.
	 * @param ctx the parse tree
	 */
	void exitMaxCardinality(CdsParser.MaxCardinalityContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NoCardinality}
	 * labeled alternative in {@link CdsParser#cardinality}.
	 * @param ctx the parse tree
	 */
	void enterNoCardinality(CdsParser.NoCardinalityContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NoCardinality}
	 * labeled alternative in {@link CdsParser#cardinality}.
	 * @param ctx the parse tree
	 */
	void exitNoCardinality(CdsParser.NoCardinalityContext ctx);
	/**
	 * Enter a parse tree produced by {@link CdsParser#defaultValue}.
	 * @param ctx the parse tree
	 */
	void enterDefaultValue(CdsParser.DefaultValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link CdsParser#defaultValue}.
	 * @param ctx the parse tree
	 */
	void exitDefaultValue(CdsParser.DefaultValueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AnnObjectRule}
	 * labeled alternative in {@link CdsParser#annotationRule}.
	 * @param ctx the parse tree
	 */
	void enterAnnObjectRule(CdsParser.AnnObjectRuleContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AnnObjectRule}
	 * labeled alternative in {@link CdsParser#annotationRule}.
	 * @param ctx the parse tree
	 */
	void exitAnnObjectRule(CdsParser.AnnObjectRuleContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AnnPropertyRule}
	 * labeled alternative in {@link CdsParser#annotationRule}.
	 * @param ctx the parse tree
	 */
	void enterAnnPropertyRule(CdsParser.AnnPropertyRuleContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AnnPropertyRule}
	 * labeled alternative in {@link CdsParser#annotationRule}.
	 * @param ctx the parse tree
	 */
	void exitAnnPropertyRule(CdsParser.AnnPropertyRuleContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AnnMarkerRule}
	 * labeled alternative in {@link CdsParser#annotationRule}.
	 * @param ctx the parse tree
	 */
	void enterAnnMarkerRule(CdsParser.AnnMarkerRuleContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AnnMarkerRule}
	 * labeled alternative in {@link CdsParser#annotationRule}.
	 * @param ctx the parse tree
	 */
	void exitAnnMarkerRule(CdsParser.AnnMarkerRuleContext ctx);
	/**
	 * Enter a parse tree produced by {@link CdsParser#annValue}.
	 * @param ctx the parse tree
	 */
	void enterAnnValue(CdsParser.AnnValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link CdsParser#annValue}.
	 * @param ctx the parse tree
	 */
	void exitAnnValue(CdsParser.AnnValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link CdsParser#enumRule}.
	 * @param ctx the parse tree
	 */
	void enterEnumRule(CdsParser.EnumRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link CdsParser#enumRule}.
	 * @param ctx the parse tree
	 */
	void exitEnumRule(CdsParser.EnumRuleContext ctx);
	/**
	 * Enter a parse tree produced by {@link CdsParser#arrRule}.
	 * @param ctx the parse tree
	 */
	void enterArrRule(CdsParser.ArrRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link CdsParser#arrRule}.
	 * @param ctx the parse tree
	 */
	void exitArrRule(CdsParser.ArrRuleContext ctx);
	/**
	 * Enter a parse tree produced by {@link CdsParser#obj}.
	 * @param ctx the parse tree
	 */
	void enterObj(CdsParser.ObjContext ctx);
	/**
	 * Exit a parse tree produced by {@link CdsParser#obj}.
	 * @param ctx the parse tree
	 */
	void exitObj(CdsParser.ObjContext ctx);
	/**
	 * Enter a parse tree produced by {@link CdsParser#keyValue}.
	 * @param ctx the parse tree
	 */
	void enterKeyValue(CdsParser.KeyValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link CdsParser#keyValue}.
	 * @param ctx the parse tree
	 */
	void exitKeyValue(CdsParser.KeyValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link CdsParser#artifactRule}.
	 * @param ctx the parse tree
	 */
	void enterArtifactRule(CdsParser.ArtifactRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link CdsParser#artifactRule}.
	 * @param ctx the parse tree
	 */
	void exitArtifactRule(CdsParser.ArtifactRuleContext ctx);
	/**
	 * Enter a parse tree produced by {@link CdsParser#viewRule}.
	 * @param ctx the parse tree
	 */
	void enterViewRule(CdsParser.ViewRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link CdsParser#viewRule}.
	 * @param ctx the parse tree
	 */
	void exitViewRule(CdsParser.ViewRuleContext ctx);
	/**
	 * Enter a parse tree produced by {@link CdsParser#selectRule}.
	 * @param ctx the parse tree
	 */
	void enterSelectRule(CdsParser.SelectRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link CdsParser#selectRule}.
	 * @param ctx the parse tree
	 */
	void exitSelectRule(CdsParser.SelectRuleContext ctx);
	/**
	 * Enter a parse tree produced by {@link CdsParser#joinRule}.
	 * @param ctx the parse tree
	 */
	void enterJoinRule(CdsParser.JoinRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link CdsParser#joinRule}.
	 * @param ctx the parse tree
	 */
	void exitJoinRule(CdsParser.JoinRuleContext ctx);
	/**
	 * Enter a parse tree produced by {@link CdsParser#joinFields}.
	 * @param ctx the parse tree
	 */
	void enterJoinFields(CdsParser.JoinFieldsContext ctx);
	/**
	 * Exit a parse tree produced by {@link CdsParser#joinFields}.
	 * @param ctx the parse tree
	 */
	void exitJoinFields(CdsParser.JoinFieldsContext ctx);
	/**
	 * Enter a parse tree produced by {@link CdsParser#selectedColumnsRule}.
	 * @param ctx the parse tree
	 */
	void enterSelectedColumnsRule(CdsParser.SelectedColumnsRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link CdsParser#selectedColumnsRule}.
	 * @param ctx the parse tree
	 */
	void exitSelectedColumnsRule(CdsParser.SelectedColumnsRuleContext ctx);
	/**
	 * Enter a parse tree produced by {@link CdsParser#whereRule}.
	 * @param ctx the parse tree
	 */
	void enterWhereRule(CdsParser.WhereRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link CdsParser#whereRule}.
	 * @param ctx the parse tree
	 */
	void exitWhereRule(CdsParser.WhereRuleContext ctx);
}