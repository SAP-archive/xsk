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
// Generated from com/sap/xsk/parser/hdbschema/core/Hdbschema.g4 by ANTLR 4.3
package com.sap.xsk.parser.hdbschema.core;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link HdbschemaParser}.
 */
public interface HdbschemaListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link HdbschemaParser#hdbschemaDefinition}.
	 * @param ctx the parse tree
	 */
	void enterHdbschemaDefinition(@NotNull HdbschemaParser.HdbschemaDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbschemaParser#hdbschemaDefinition}.
	 * @param ctx the parse tree
	 */
	void exitHdbschemaDefinition(@NotNull HdbschemaParser.HdbschemaDefinitionContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbschemaParser#schemaNameProp}.
	 * @param ctx the parse tree
	 */
	void enterSchemaNameProp(@NotNull HdbschemaParser.SchemaNamePropContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbschemaParser#schemaNameProp}.
	 * @param ctx the parse tree
	 */
	void exitSchemaNameProp(@NotNull HdbschemaParser.SchemaNamePropContext ctx);
}