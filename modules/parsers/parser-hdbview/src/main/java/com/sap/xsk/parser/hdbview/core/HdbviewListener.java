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
// Generated from com/sap/xsk/parser/hdbview/core/Hdbview.g4 by ANTLR 4.3
package com.sap.xsk.parser.hdbview.core;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link HdbviewParser}.
 */
public interface HdbviewListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link HdbviewParser#queryProp}.
	 * @param ctx the parse tree
	 */
	void enterQueryProp(@NotNull HdbviewParser.QueryPropContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbviewParser#queryProp}.
	 * @param ctx the parse tree
	 */
	void exitQueryProp(@NotNull HdbviewParser.QueryPropContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbviewParser#dependsOnProp}.
	 * @param ctx the parse tree
	 */
	void enterDependsOnProp(@NotNull HdbviewParser.DependsOnPropContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbviewParser#dependsOnProp}.
	 * @param ctx the parse tree
	 */
	void exitDependsOnProp(@NotNull HdbviewParser.DependsOnPropContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbviewParser#schemaProp}.
	 * @param ctx the parse tree
	 */
	void enterSchemaProp(@NotNull HdbviewParser.SchemaPropContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbviewParser#schemaProp}.
	 * @param ctx the parse tree
	 */
	void exitSchemaProp(@NotNull HdbviewParser.SchemaPropContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbviewParser#dependsOnView}.
	 * @param ctx the parse tree
	 */
	void enterDependsOnView(@NotNull HdbviewParser.DependsOnViewContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbviewParser#dependsOnView}.
	 * @param ctx the parse tree
	 */
	void exitDependsOnView(@NotNull HdbviewParser.DependsOnViewContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbviewParser#property}.
	 * @param ctx the parse tree
	 */
	void enterProperty(@NotNull HdbviewParser.PropertyContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbviewParser#property}.
	 * @param ctx the parse tree
	 */
	void exitProperty(@NotNull HdbviewParser.PropertyContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbviewParser#hdbviewDefinition}.
	 * @param ctx the parse tree
	 */
	void enterHdbviewDefinition(@NotNull HdbviewParser.HdbviewDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbviewParser#hdbviewDefinition}.
	 * @param ctx the parse tree
	 */
	void exitHdbviewDefinition(@NotNull HdbviewParser.HdbviewDefinitionContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbviewParser#publicProp}.
	 * @param ctx the parse tree
	 */
	void enterPublicProp(@NotNull HdbviewParser.PublicPropContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbviewParser#publicProp}.
	 * @param ctx the parse tree
	 */
	void exitPublicProp(@NotNull HdbviewParser.PublicPropContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbviewParser#dependsOnTable}.
	 * @param ctx the parse tree
	 */
	void enterDependsOnTable(@NotNull HdbviewParser.DependsOnTableContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbviewParser#dependsOnTable}.
	 * @param ctx the parse tree
	 */
	void exitDependsOnTable(@NotNull HdbviewParser.DependsOnTableContext ctx);
}