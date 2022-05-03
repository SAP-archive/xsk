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
// Generated from com/sap/xsk/parser/hdbview/core/Hdbview.g4 by ANTLR 4.10.1
package com.sap.xsk.parser.hdbview.core;
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
	 * Enter a parse tree produced by {@link HdbviewParser#property}.
	 * @param ctx the parse tree
	 */
	void enterProperty(HdbviewParser.PropertyContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbviewParser#property}.
	 * @param ctx the parse tree
	 */
	void exitProperty(HdbviewParser.PropertyContext ctx);
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