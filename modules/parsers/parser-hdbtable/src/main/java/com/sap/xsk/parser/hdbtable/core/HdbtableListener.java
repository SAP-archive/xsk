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
// Generated from com/sap/xsk/parser/hdbtable/core/Hdbtable.g4 by ANTLR 4.3
package com.sap.xsk.parser.hdbtable.core;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link HdbtableParser}.
 */
public interface HdbtableListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link HdbtableParser#tableColumnsProp}.
	 * @param ctx the parse tree
	 */
	void enterTableColumnsProp(@NotNull HdbtableParser.TableColumnsPropContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtableParser#tableColumnsProp}.
	 * @param ctx the parse tree
	 */
	void exitTableColumnsProp(@NotNull HdbtableParser.TableColumnsPropContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbtableParser#indexesObject}.
	 * @param ctx the parse tree
	 */
	void enterIndexesObject(@NotNull HdbtableParser.IndexesObjectContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtableParser#indexesObject}.
	 * @param ctx the parse tree
	 */
	void exitIndexesObject(@NotNull HdbtableParser.IndexesObjectContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbtableParser#hdbtableDefinition}.
	 * @param ctx the parse tree
	 */
	void enterHdbtableDefinition(@NotNull HdbtableParser.HdbtableDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtableParser#hdbtableDefinition}.
	 * @param ctx the parse tree
	 */
	void exitHdbtableDefinition(@NotNull HdbtableParser.HdbtableDefinitionContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbtableParser#columnAssignUnique}.
	 * @param ctx the parse tree
	 */
	void enterColumnAssignUnique(@NotNull HdbtableParser.ColumnAssignUniqueContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtableParser#columnAssignUnique}.
	 * @param ctx the parse tree
	 */
	void exitColumnAssignUnique(@NotNull HdbtableParser.ColumnAssignUniqueContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbtableParser#tableIndexesProp}.
	 * @param ctx the parse tree
	 */
	void enterTableIndexesProp(@NotNull HdbtableParser.TableIndexesPropContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtableParser#tableIndexesProp}.
	 * @param ctx the parse tree
	 */
	void exitTableIndexesProp(@NotNull HdbtableParser.TableIndexesPropContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbtableParser#columnAssignDefaultValue}.
	 * @param ctx the parse tree
	 */
	void enterColumnAssignDefaultValue(@NotNull HdbtableParser.ColumnAssignDefaultValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtableParser#columnAssignDefaultValue}.
	 * @param ctx the parse tree
	 */
	void exitColumnAssignDefaultValue(@NotNull HdbtableParser.ColumnAssignDefaultValueContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbtableParser#indexProperties}.
	 * @param ctx the parse tree
	 */
	void enterIndexProperties(@NotNull HdbtableParser.IndexPropertiesContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtableParser#indexProperties}.
	 * @param ctx the parse tree
	 */
	void exitIndexProperties(@NotNull HdbtableParser.IndexPropertiesContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbtableParser#columnAssignName}.
	 * @param ctx the parse tree
	 */
	void enterColumnAssignName(@NotNull HdbtableParser.ColumnAssignNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtableParser#columnAssignName}.
	 * @param ctx the parse tree
	 */
	void exitColumnAssignName(@NotNull HdbtableParser.ColumnAssignNameContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbtableParser#indexAssignIndexColumns}.
	 * @param ctx the parse tree
	 */
	void enterIndexAssignIndexColumns(@NotNull HdbtableParser.IndexAssignIndexColumnsContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtableParser#indexAssignIndexColumns}.
	 * @param ctx the parse tree
	 */
	void exitIndexAssignIndexColumns(@NotNull HdbtableParser.IndexAssignIndexColumnsContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbtableParser#columnsObject}.
	 * @param ctx the parse tree
	 */
	void enterColumnsObject(@NotNull HdbtableParser.ColumnsObjectContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtableParser#columnsObject}.
	 * @param ctx the parse tree
	 */
	void exitColumnsObject(@NotNull HdbtableParser.ColumnsObjectContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbtableParser#tableTypeProp}.
	 * @param ctx the parse tree
	 */
	void enterTableTypeProp(@NotNull HdbtableParser.TableTypePropContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtableParser#tableTypeProp}.
	 * @param ctx the parse tree
	 */
	void exitTableTypeProp(@NotNull HdbtableParser.TableTypePropContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbtableParser#columnAssignLength}.
	 * @param ctx the parse tree
	 */
	void enterColumnAssignLength(@NotNull HdbtableParser.ColumnAssignLengthContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtableParser#columnAssignLength}.
	 * @param ctx the parse tree
	 */
	void exitColumnAssignLength(@NotNull HdbtableParser.ColumnAssignLengthContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbtableParser#indexColumnsArray}.
	 * @param ctx the parse tree
	 */
	void enterIndexColumnsArray(@NotNull HdbtableParser.IndexColumnsArrayContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtableParser#indexColumnsArray}.
	 * @param ctx the parse tree
	 */
	void exitIndexColumnsArray(@NotNull HdbtableParser.IndexColumnsArrayContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbtableParser#columnAssignScale}.
	 * @param ctx the parse tree
	 */
	void enterColumnAssignScale(@NotNull HdbtableParser.ColumnAssignScaleContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtableParser#columnAssignScale}.
	 * @param ctx the parse tree
	 */
	void exitColumnAssignScale(@NotNull HdbtableParser.ColumnAssignScaleContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbtableParser#tablePrimaryKeyIndexTypeProp}.
	 * @param ctx the parse tree
	 */
	void enterTablePrimaryKeyIndexTypeProp(@NotNull HdbtableParser.TablePrimaryKeyIndexTypePropContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtableParser#tablePrimaryKeyIndexTypeProp}.
	 * @param ctx the parse tree
	 */
	void exitTablePrimaryKeyIndexTypeProp(@NotNull HdbtableParser.TablePrimaryKeyIndexTypePropContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbtableParser#columnAssignSQLType}.
	 * @param ctx the parse tree
	 */
	void enterColumnAssignSQLType(@NotNull HdbtableParser.ColumnAssignSQLTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtableParser#columnAssignSQLType}.
	 * @param ctx the parse tree
	 */
	void exitColumnAssignSQLType(@NotNull HdbtableParser.ColumnAssignSQLTypeContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbtableParser#indexAssignIndexType}.
	 * @param ctx the parse tree
	 */
	void enterIndexAssignIndexType(@NotNull HdbtableParser.IndexAssignIndexTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtableParser#indexAssignIndexType}.
	 * @param ctx the parse tree
	 */
	void exitIndexAssignIndexType(@NotNull HdbtableParser.IndexAssignIndexTypeContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbtableParser#indexAssignUnique}.
	 * @param ctx the parse tree
	 */
	void enterIndexAssignUnique(@NotNull HdbtableParser.IndexAssignUniqueContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtableParser#indexAssignUnique}.
	 * @param ctx the parse tree
	 */
	void exitIndexAssignUnique(@NotNull HdbtableParser.IndexAssignUniqueContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbtableParser#hdbtableProperties}.
	 * @param ctx the parse tree
	 */
	void enterHdbtableProperties(@NotNull HdbtableParser.HdbtablePropertiesContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtableParser#hdbtableProperties}.
	 * @param ctx the parse tree
	 */
	void exitHdbtableProperties(@NotNull HdbtableParser.HdbtablePropertiesContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbtableParser#loggingTypeProp}.
	 * @param ctx the parse tree
	 */
	void enterLoggingTypeProp(@NotNull HdbtableParser.LoggingTypePropContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtableParser#loggingTypeProp}.
	 * @param ctx the parse tree
	 */
	void exitLoggingTypeProp(@NotNull HdbtableParser.LoggingTypePropContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbtableParser#columnAssignNullable}.
	 * @param ctx the parse tree
	 */
	void enterColumnAssignNullable(@NotNull HdbtableParser.ColumnAssignNullableContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtableParser#columnAssignNullable}.
	 * @param ctx the parse tree
	 */
	void exitColumnAssignNullable(@NotNull HdbtableParser.ColumnAssignNullableContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbtableParser#columnsProperties}.
	 * @param ctx the parse tree
	 */
	void enterColumnsProperties(@NotNull HdbtableParser.ColumnsPropertiesContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtableParser#columnsProperties}.
	 * @param ctx the parse tree
	 */
	void exitColumnsProperties(@NotNull HdbtableParser.ColumnsPropertiesContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbtableParser#temporaryProp}.
	 * @param ctx the parse tree
	 */
	void enterTemporaryProp(@NotNull HdbtableParser.TemporaryPropContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtableParser#temporaryProp}.
	 * @param ctx the parse tree
	 */
	void exitTemporaryProp(@NotNull HdbtableParser.TemporaryPropContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbtableParser#publicProp}.
	 * @param ctx the parse tree
	 */
	void enterPublicProp(@NotNull HdbtableParser.PublicPropContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtableParser#publicProp}.
	 * @param ctx the parse tree
	 */
	void exitPublicProp(@NotNull HdbtableParser.PublicPropContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbtableParser#indexAssignOrder}.
	 * @param ctx the parse tree
	 */
	void enterIndexAssignOrder(@NotNull HdbtableParser.IndexAssignOrderContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtableParser#indexAssignOrder}.
	 * @param ctx the parse tree
	 */
	void exitIndexAssignOrder(@NotNull HdbtableParser.IndexAssignOrderContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbtableParser#tablePrimaryKeyProp}.
	 * @param ctx the parse tree
	 */
	void enterTablePrimaryKeyProp(@NotNull HdbtableParser.TablePrimaryKeyPropContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtableParser#tablePrimaryKeyProp}.
	 * @param ctx the parse tree
	 */
	void exitTablePrimaryKeyProp(@NotNull HdbtableParser.TablePrimaryKeyPropContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbtableParser#columnAssignPrecision}.
	 * @param ctx the parse tree
	 */
	void enterColumnAssignPrecision(@NotNull HdbtableParser.ColumnAssignPrecisionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtableParser#columnAssignPrecision}.
	 * @param ctx the parse tree
	 */
	void exitColumnAssignPrecision(@NotNull HdbtableParser.ColumnAssignPrecisionContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbtableParser#schemaNameProp}.
	 * @param ctx the parse tree
	 */
	void enterSchemaNameProp(@NotNull HdbtableParser.SchemaNamePropContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtableParser#schemaNameProp}.
	 * @param ctx the parse tree
	 */
	void exitSchemaNameProp(@NotNull HdbtableParser.SchemaNamePropContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbtableParser#tablePrimaryKeyColumnsProp}.
	 * @param ctx the parse tree
	 */
	void enterTablePrimaryKeyColumnsProp(@NotNull HdbtableParser.TablePrimaryKeyColumnsPropContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtableParser#tablePrimaryKeyColumnsProp}.
	 * @param ctx the parse tree
	 */
	void exitTablePrimaryKeyColumnsProp(@NotNull HdbtableParser.TablePrimaryKeyColumnsPropContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbtableParser#columnAssignComment}.
	 * @param ctx the parse tree
	 */
	void enterColumnAssignComment(@NotNull HdbtableParser.ColumnAssignCommentContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtableParser#columnAssignComment}.
	 * @param ctx the parse tree
	 */
	void exitColumnAssignComment(@NotNull HdbtableParser.ColumnAssignCommentContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbtableParser#descriptionProp}.
	 * @param ctx the parse tree
	 */
	void enterDescriptionProp(@NotNull HdbtableParser.DescriptionPropContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtableParser#descriptionProp}.
	 * @param ctx the parse tree
	 */
	void exitDescriptionProp(@NotNull HdbtableParser.DescriptionPropContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbtableParser#indexAssignName}.
	 * @param ctx the parse tree
	 */
	void enterIndexAssignName(@NotNull HdbtableParser.IndexAssignNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtableParser#indexAssignName}.
	 * @param ctx the parse tree
	 */
	void exitIndexAssignName(@NotNull HdbtableParser.IndexAssignNameContext ctx);
}