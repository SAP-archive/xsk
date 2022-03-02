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
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link HdbtableParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface HdbtableVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link HdbtableParser#hdbtableDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHdbtableDefinition(HdbtableParser.HdbtableDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbtableParser#hdbtableProperties}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHdbtableProperties(HdbtableParser.HdbtablePropertiesContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbtableParser#schemaNameProp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSchemaNameProp(HdbtableParser.SchemaNamePropContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbtableParser#temporaryProp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTemporaryProp(HdbtableParser.TemporaryPropContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbtableParser#tableTypeProp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableTypeProp(HdbtableParser.TableTypePropContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbtableParser#publicProp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPublicProp(HdbtableParser.PublicPropContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbtableParser#loggingTypeProp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoggingTypeProp(HdbtableParser.LoggingTypePropContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbtableParser#tableColumnsProp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableColumnsProp(HdbtableParser.TableColumnsPropContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbtableParser#tableIndexesProp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableIndexesProp(HdbtableParser.TableIndexesPropContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbtableParser#tablePrimaryKeyProp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTablePrimaryKeyProp(HdbtableParser.TablePrimaryKeyPropContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbtableParser#tablePrimaryKeyColumnsProp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTablePrimaryKeyColumnsProp(HdbtableParser.TablePrimaryKeyColumnsPropContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbtableParser#tablePrimaryKeyIndexTypeProp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTablePrimaryKeyIndexTypeProp(HdbtableParser.TablePrimaryKeyIndexTypePropContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbtableParser#descriptionProp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDescriptionProp(HdbtableParser.DescriptionPropContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbtableParser#columnsObject}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumnsObject(HdbtableParser.ColumnsObjectContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbtableParser#columnsProperties}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumnsProperties(HdbtableParser.ColumnsPropertiesContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbtableParser#indexesObject}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndexesObject(HdbtableParser.IndexesObjectContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbtableParser#indexProperties}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndexProperties(HdbtableParser.IndexPropertiesContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbtableParser#columnAssignName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumnAssignName(HdbtableParser.ColumnAssignNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbtableParser#columnAssignSQLType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumnAssignSQLType(HdbtableParser.ColumnAssignSQLTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbtableParser#columnAssignNullable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumnAssignNullable(HdbtableParser.ColumnAssignNullableContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbtableParser#columnAssignUnique}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumnAssignUnique(HdbtableParser.ColumnAssignUniqueContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbtableParser#columnAssignLength}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumnAssignLength(HdbtableParser.ColumnAssignLengthContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbtableParser#columnAssignComment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumnAssignComment(HdbtableParser.ColumnAssignCommentContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbtableParser#columnAssignDefaultValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumnAssignDefaultValue(HdbtableParser.ColumnAssignDefaultValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbtableParser#columnAssignPrecision}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumnAssignPrecision(HdbtableParser.ColumnAssignPrecisionContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbtableParser#columnAssignScale}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumnAssignScale(HdbtableParser.ColumnAssignScaleContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbtableParser#indexAssignName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndexAssignName(HdbtableParser.IndexAssignNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbtableParser#indexAssignUnique}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndexAssignUnique(HdbtableParser.IndexAssignUniqueContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbtableParser#indexAssignOrder}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndexAssignOrder(HdbtableParser.IndexAssignOrderContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbtableParser#indexAssignIndexColumns}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndexAssignIndexColumns(HdbtableParser.IndexAssignIndexColumnsContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbtableParser#indexAssignIndexType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndexAssignIndexType(HdbtableParser.IndexAssignIndexTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbtableParser#indexColumnsArray}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndexColumnsArray(HdbtableParser.IndexColumnsArrayContext ctx);
}