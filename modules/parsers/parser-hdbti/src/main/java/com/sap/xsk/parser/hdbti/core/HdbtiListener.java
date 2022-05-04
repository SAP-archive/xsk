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
// Generated from com/sap/xsk/parser/hdbti/core/Hdbti.g4 by ANTLR 4.10.1
package com.sap.xsk.parser.hdbti.core;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link HdbtiParser}.
 */
public interface HdbtiListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link HdbtiParser#importArr}.
	 * @param ctx the parse tree
	 */
	void enterImportArr(HdbtiParser.ImportArrContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtiParser#importArr}.
	 * @param ctx the parse tree
	 */
	void exitImportArr(HdbtiParser.ImportArrContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbtiParser#objConfig}.
	 * @param ctx the parse tree
	 */
	void enterObjConfig(HdbtiParser.ObjConfigContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtiParser#objConfig}.
	 * @param ctx the parse tree
	 */
	void exitObjConfig(HdbtiParser.ObjConfigContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbtiParser#assignExpression}.
	 * @param ctx the parse tree
	 */
	void enterAssignExpression(HdbtiParser.AssignExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtiParser#assignExpression}.
	 * @param ctx the parse tree
	 */
	void exitAssignExpression(HdbtiParser.AssignExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbtiParser#assignTable}.
	 * @param ctx the parse tree
	 */
	void enterAssignTable(HdbtiParser.AssignTableContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtiParser#assignTable}.
	 * @param ctx the parse tree
	 */
	void exitAssignTable(HdbtiParser.AssignTableContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbtiParser#assignSchema}.
	 * @param ctx the parse tree
	 */
	void enterAssignSchema(HdbtiParser.AssignSchemaContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtiParser#assignSchema}.
	 * @param ctx the parse tree
	 */
	void exitAssignSchema(HdbtiParser.AssignSchemaContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbtiParser#assignFile}.
	 * @param ctx the parse tree
	 */
	void enterAssignFile(HdbtiParser.AssignFileContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtiParser#assignFile}.
	 * @param ctx the parse tree
	 */
	void exitAssignFile(HdbtiParser.AssignFileContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbtiParser#assignHeader}.
	 * @param ctx the parse tree
	 */
	void enterAssignHeader(HdbtiParser.AssignHeaderContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtiParser#assignHeader}.
	 * @param ctx the parse tree
	 */
	void exitAssignHeader(HdbtiParser.AssignHeaderContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbtiParser#assignUseHeaderNames}.
	 * @param ctx the parse tree
	 */
	void enterAssignUseHeaderNames(HdbtiParser.AssignUseHeaderNamesContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtiParser#assignUseHeaderNames}.
	 * @param ctx the parse tree
	 */
	void exitAssignUseHeaderNames(HdbtiParser.AssignUseHeaderNamesContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbtiParser#assignDelimField}.
	 * @param ctx the parse tree
	 */
	void enterAssignDelimField(HdbtiParser.AssignDelimFieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtiParser#assignDelimField}.
	 * @param ctx the parse tree
	 */
	void exitAssignDelimField(HdbtiParser.AssignDelimFieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbtiParser#assignDelimEnclosing}.
	 * @param ctx the parse tree
	 */
	void enterAssignDelimEnclosing(HdbtiParser.AssignDelimEnclosingContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtiParser#assignDelimEnclosing}.
	 * @param ctx the parse tree
	 */
	void exitAssignDelimEnclosing(HdbtiParser.AssignDelimEnclosingContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbtiParser#assignDistinguishEmptyFromNull}.
	 * @param ctx the parse tree
	 */
	void enterAssignDistinguishEmptyFromNull(HdbtiParser.AssignDistinguishEmptyFromNullContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtiParser#assignDistinguishEmptyFromNull}.
	 * @param ctx the parse tree
	 */
	void exitAssignDistinguishEmptyFromNull(HdbtiParser.AssignDistinguishEmptyFromNullContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbtiParser#assignKeys}.
	 * @param ctx the parse tree
	 */
	void enterAssignKeys(HdbtiParser.AssignKeysContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtiParser#assignKeys}.
	 * @param ctx the parse tree
	 */
	void exitAssignKeys(HdbtiParser.AssignKeysContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbtiParser#keyArr}.
	 * @param ctx the parse tree
	 */
	void enterKeyArr(HdbtiParser.KeyArrContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtiParser#keyArr}.
	 * @param ctx the parse tree
	 */
	void exitKeyArr(HdbtiParser.KeyArrContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbtiParser#pair}.
	 * @param ctx the parse tree
	 */
	void enterPair(HdbtiParser.PairContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtiParser#pair}.
	 * @param ctx the parse tree
	 */
	void exitPair(HdbtiParser.PairContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbtiParser#pairKey}.
	 * @param ctx the parse tree
	 */
	void enterPairKey(HdbtiParser.PairKeyContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtiParser#pairKey}.
	 * @param ctx the parse tree
	 */
	void exitPairKey(HdbtiParser.PairKeyContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbtiParser#pairValue}.
	 * @param ctx the parse tree
	 */
	void enterPairValue(HdbtiParser.PairValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtiParser#pairValue}.
	 * @param ctx the parse tree
	 */
	void exitPairValue(HdbtiParser.PairValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbtiParser#tableName}.
	 * @param ctx the parse tree
	 */
	void enterTableName(HdbtiParser.TableNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbtiParser#tableName}.
	 * @param ctx the parse tree
	 */
	void exitTableName(HdbtiParser.TableNameContext ctx);
}