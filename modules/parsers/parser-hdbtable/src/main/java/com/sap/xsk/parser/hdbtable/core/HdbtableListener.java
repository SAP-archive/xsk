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
// Generated from com/sap/xsk/parser/hdbtable/core/Hdbtable.g4 by ANTLR 4.9.3
package com.sap.xsk.parser.hdbtable.core;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link HdbtableParser}.
 */
public interface HdbtableListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link HdbtableParser#hdbtableDefinition}.
	 * @param ctx the parse tree
	 */
	void enterHdbtableDefinition(HdbtableParser.HdbtableDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtableParser#hdbtableDefinition}.
	 * @param ctx the parse tree
	 */
	void exitHdbtableDefinition(HdbtableParser.HdbtableDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbtableParser#hdbtableProperties}.
	 * @param ctx the parse tree
	 */
	void enterHdbtableProperties(HdbtableParser.HdbtablePropertiesContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtableParser#hdbtableProperties}.
	 * @param ctx the parse tree
	 */
	void exitHdbtableProperties(HdbtableParser.HdbtablePropertiesContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbtableParser#schemaNameProp}.
	 * @param ctx the parse tree
	 */
	void enterSchemaNameProp(HdbtableParser.SchemaNamePropContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtableParser#schemaNameProp}.
	 * @param ctx the parse tree
	 */
	void exitSchemaNameProp(HdbtableParser.SchemaNamePropContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbtableParser#temporaryProp}.
	 * @param ctx the parse tree
	 */
	void enterTemporaryProp(HdbtableParser.TemporaryPropContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtableParser#temporaryProp}.
	 * @param ctx the parse tree
	 */
	void exitTemporaryProp(HdbtableParser.TemporaryPropContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbtableParser#tableTypeProp}.
	 * @param ctx the parse tree
	 */
	void enterTableTypeProp(HdbtableParser.TableTypePropContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtableParser#tableTypeProp}.
	 * @param ctx the parse tree
	 */
	void exitTableTypeProp(HdbtableParser.TableTypePropContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbtableParser#publicProp}.
	 * @param ctx the parse tree
	 */
	void enterPublicProp(HdbtableParser.PublicPropContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtableParser#publicProp}.
	 * @param ctx the parse tree
	 */
	void exitPublicProp(HdbtableParser.PublicPropContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbtableParser#loggingTypeProp}.
	 * @param ctx the parse tree
	 */
	void enterLoggingTypeProp(HdbtableParser.LoggingTypePropContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtableParser#loggingTypeProp}.
	 * @param ctx the parse tree
	 */
	void exitLoggingTypeProp(HdbtableParser.LoggingTypePropContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbtableParser#tableColumnsProp}.
	 * @param ctx the parse tree
	 */
	void enterTableColumnsProp(HdbtableParser.TableColumnsPropContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtableParser#tableColumnsProp}.
	 * @param ctx the parse tree
	 */
	void exitTableColumnsProp(HdbtableParser.TableColumnsPropContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbtableParser#tableIndexesProp}.
	 * @param ctx the parse tree
	 */
	void enterTableIndexesProp(HdbtableParser.TableIndexesPropContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtableParser#tableIndexesProp}.
	 * @param ctx the parse tree
	 */
	void exitTableIndexesProp(HdbtableParser.TableIndexesPropContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbtableParser#tablePrimaryKeyProp}.
	 * @param ctx the parse tree
	 */
	void enterTablePrimaryKeyProp(HdbtableParser.TablePrimaryKeyPropContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtableParser#tablePrimaryKeyProp}.
	 * @param ctx the parse tree
	 */
	void exitTablePrimaryKeyProp(HdbtableParser.TablePrimaryKeyPropContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbtableParser#tablePrimaryKeyColumnsProp}.
	 * @param ctx the parse tree
	 */
	void enterTablePrimaryKeyColumnsProp(HdbtableParser.TablePrimaryKeyColumnsPropContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtableParser#tablePrimaryKeyColumnsProp}.
	 * @param ctx the parse tree
	 */
	void exitTablePrimaryKeyColumnsProp(HdbtableParser.TablePrimaryKeyColumnsPropContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbtableParser#tablePrimaryKeyIndexTypeProp}.
	 * @param ctx the parse tree
	 */
	void enterTablePrimaryKeyIndexTypeProp(HdbtableParser.TablePrimaryKeyIndexTypePropContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtableParser#tablePrimaryKeyIndexTypeProp}.
	 * @param ctx the parse tree
	 */
	void exitTablePrimaryKeyIndexTypeProp(HdbtableParser.TablePrimaryKeyIndexTypePropContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbtableParser#descriptionProp}.
	 * @param ctx the parse tree
	 */
	void enterDescriptionProp(HdbtableParser.DescriptionPropContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtableParser#descriptionProp}.
	 * @param ctx the parse tree
	 */
	void exitDescriptionProp(HdbtableParser.DescriptionPropContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbtableParser#columnsObject}.
	 * @param ctx the parse tree
	 */
	void enterColumnsObject(HdbtableParser.ColumnsObjectContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtableParser#columnsObject}.
	 * @param ctx the parse tree
	 */
	void exitColumnsObject(HdbtableParser.ColumnsObjectContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbtableParser#columnsProperties}.
	 * @param ctx the parse tree
	 */
	void enterColumnsProperties(HdbtableParser.ColumnsPropertiesContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtableParser#columnsProperties}.
	 * @param ctx the parse tree
	 */
	void exitColumnsProperties(HdbtableParser.ColumnsPropertiesContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbtableParser#indexesObject}.
	 * @param ctx the parse tree
	 */
	void enterIndexesObject(HdbtableParser.IndexesObjectContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtableParser#indexesObject}.
	 * @param ctx the parse tree
	 */
	void exitIndexesObject(HdbtableParser.IndexesObjectContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbtableParser#indexProperties}.
	 * @param ctx the parse tree
	 */
	void enterIndexProperties(HdbtableParser.IndexPropertiesContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtableParser#indexProperties}.
	 * @param ctx the parse tree
	 */
	void exitIndexProperties(HdbtableParser.IndexPropertiesContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbtableParser#columnAssignName}.
	 * @param ctx the parse tree
	 */
	void enterColumnAssignName(HdbtableParser.ColumnAssignNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtableParser#columnAssignName}.
	 * @param ctx the parse tree
	 */
	void exitColumnAssignName(HdbtableParser.ColumnAssignNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbtableParser#columnAssignSQLType}.
	 * @param ctx the parse tree
	 */
	void enterColumnAssignSQLType(HdbtableParser.ColumnAssignSQLTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtableParser#columnAssignSQLType}.
	 * @param ctx the parse tree
	 */
	void exitColumnAssignSQLType(HdbtableParser.ColumnAssignSQLTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbtableParser#columnAssignNullable}.
	 * @param ctx the parse tree
	 */
	void enterColumnAssignNullable(HdbtableParser.ColumnAssignNullableContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtableParser#columnAssignNullable}.
	 * @param ctx the parse tree
	 */
	void exitColumnAssignNullable(HdbtableParser.ColumnAssignNullableContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbtableParser#columnAssignUnique}.
	 * @param ctx the parse tree
	 */
	void enterColumnAssignUnique(HdbtableParser.ColumnAssignUniqueContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtableParser#columnAssignUnique}.
	 * @param ctx the parse tree
	 */
	void exitColumnAssignUnique(HdbtableParser.ColumnAssignUniqueContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbtableParser#columnAssignLength}.
	 * @param ctx the parse tree
	 */
	void enterColumnAssignLength(HdbtableParser.ColumnAssignLengthContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtableParser#columnAssignLength}.
	 * @param ctx the parse tree
	 */
	void exitColumnAssignLength(HdbtableParser.ColumnAssignLengthContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbtableParser#columnAssignComment}.
	 * @param ctx the parse tree
	 */
	void enterColumnAssignComment(HdbtableParser.ColumnAssignCommentContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtableParser#columnAssignComment}.
	 * @param ctx the parse tree
	 */
	void exitColumnAssignComment(HdbtableParser.ColumnAssignCommentContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbtableParser#columnAssignDefaultValue}.
	 * @param ctx the parse tree
	 */
	void enterColumnAssignDefaultValue(HdbtableParser.ColumnAssignDefaultValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtableParser#columnAssignDefaultValue}.
	 * @param ctx the parse tree
	 */
	void exitColumnAssignDefaultValue(HdbtableParser.ColumnAssignDefaultValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbtableParser#columnAssignPrecision}.
	 * @param ctx the parse tree
	 */
	void enterColumnAssignPrecision(HdbtableParser.ColumnAssignPrecisionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtableParser#columnAssignPrecision}.
	 * @param ctx the parse tree
	 */
	void exitColumnAssignPrecision(HdbtableParser.ColumnAssignPrecisionContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbtableParser#columnAssignScale}.
	 * @param ctx the parse tree
	 */
	void enterColumnAssignScale(HdbtableParser.ColumnAssignScaleContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtableParser#columnAssignScale}.
	 * @param ctx the parse tree
	 */
	void exitColumnAssignScale(HdbtableParser.ColumnAssignScaleContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbtableParser#indexAssignName}.
	 * @param ctx the parse tree
	 */
	void enterIndexAssignName(HdbtableParser.IndexAssignNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtableParser#indexAssignName}.
	 * @param ctx the parse tree
	 */
	void exitIndexAssignName(HdbtableParser.IndexAssignNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbtableParser#indexAssignUnique}.
	 * @param ctx the parse tree
	 */
	void enterIndexAssignUnique(HdbtableParser.IndexAssignUniqueContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtableParser#indexAssignUnique}.
	 * @param ctx the parse tree
	 */
	void exitIndexAssignUnique(HdbtableParser.IndexAssignUniqueContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbtableParser#indexAssignOrder}.
	 * @param ctx the parse tree
	 */
	void enterIndexAssignOrder(HdbtableParser.IndexAssignOrderContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtableParser#indexAssignOrder}.
	 * @param ctx the parse tree
	 */
	void exitIndexAssignOrder(HdbtableParser.IndexAssignOrderContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbtableParser#indexAssignIndexColumns}.
	 * @param ctx the parse tree
	 */
	void enterIndexAssignIndexColumns(HdbtableParser.IndexAssignIndexColumnsContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtableParser#indexAssignIndexColumns}.
	 * @param ctx the parse tree
	 */
	void exitIndexAssignIndexColumns(HdbtableParser.IndexAssignIndexColumnsContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbtableParser#indexAssignIndexType}.
	 * @param ctx the parse tree
	 */
	void enterIndexAssignIndexType(HdbtableParser.IndexAssignIndexTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtableParser#indexAssignIndexType}.
	 * @param ctx the parse tree
	 */
	void exitIndexAssignIndexType(HdbtableParser.IndexAssignIndexTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbtableParser#indexColumnsArray}.
	 * @param ctx the parse tree
	 */
	void enterIndexColumnsArray(HdbtableParser.IndexColumnsArrayContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtableParser#indexColumnsArray}.
	 * @param ctx the parse tree
	 */
	void exitIndexColumnsArray(HdbtableParser.IndexColumnsArrayContext ctx);
}