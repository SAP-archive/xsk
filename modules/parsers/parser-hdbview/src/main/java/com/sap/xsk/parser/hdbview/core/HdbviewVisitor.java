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
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link HdbviewParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface HdbviewVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link HdbviewParser#hdbviewDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHdbviewDefinition(HdbviewParser.HdbviewDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbviewParser#property}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProperty(HdbviewParser.PropertyContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbviewParser#schemaProp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSchemaProp(HdbviewParser.SchemaPropContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbviewParser#publicProp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPublicProp(HdbviewParser.PublicPropContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbviewParser#queryProp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQueryProp(HdbviewParser.QueryPropContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbviewParser#dependsOnProp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDependsOnProp(HdbviewParser.DependsOnPropContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbviewParser#dependsOnTable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDependsOnTable(HdbviewParser.DependsOnTableContext ctx);
	/**
	 * Visit a parse tree produced by {@link HdbviewParser#dependsOnView}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDependsOnView(HdbviewParser.DependsOnViewContext ctx);
}