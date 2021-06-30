/*
 * Copyright (c) 2019-2021 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2019-2021 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
// Generated from com/sap/xsk/parser/hdbsynonym/core/Hdbsynonym.g4 by ANTLR 4.3
package com.sap.xsk.parser.hdbsynonym.core;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link HdbsynonymParser}.
 */
public interface HdbsynonymListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link HdbsynonymParser#synonymTargetProp}.
	 * @param ctx the parse tree
	 */
	void enterSynonymTargetProp(@NotNull HdbsynonymParser.SynonymTargetPropContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbsynonymParser#synonymTargetProp}.
	 * @param ctx the parse tree
	 */
	void exitSynonymTargetProp(@NotNull HdbsynonymParser.SynonymTargetPropContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbsynonymParser#synonymBody}.
	 * @param ctx the parse tree
	 */
	void enterSynonymBody(@NotNull HdbsynonymParser.SynonymBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbsynonymParser#synonymBody}.
	 * @param ctx the parse tree
	 */
	void exitSynonymBody(@NotNull HdbsynonymParser.SynonymBodyContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbsynonymParser#location}.
	 * @param ctx the parse tree
	 */
	void enterLocation(@NotNull HdbsynonymParser.LocationContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbsynonymParser#location}.
	 * @param ctx the parse tree
	 */
	void exitLocation(@NotNull HdbsynonymParser.LocationContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbsynonymParser#synonymTarget}.
	 * @param ctx the parse tree
	 */
	void enterSynonymTarget(@NotNull HdbsynonymParser.SynonymTargetContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbsynonymParser#synonymTarget}.
	 * @param ctx the parse tree
	 */
	void exitSynonymTarget(@NotNull HdbsynonymParser.SynonymTargetContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbsynonymParser#hdbsynonymDefinition}.
	 * @param ctx the parse tree
	 */
	void enterHdbsynonymDefinition(@NotNull HdbsynonymParser.HdbsynonymDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbsynonymParser#hdbsynonymDefinition}.
	 * @param ctx the parse tree
	 */
	void exitHdbsynonymDefinition(@NotNull HdbsynonymParser.HdbsynonymDefinitionContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbsynonymParser#synonymSchema}.
	 * @param ctx the parse tree
	 */
	void enterSynonymSchema(@NotNull HdbsynonymParser.SynonymSchemaContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbsynonymParser#synonymSchema}.
	 * @param ctx the parse tree
	 */
	void exitSynonymSchema(@NotNull HdbsynonymParser.SynonymSchemaContext ctx);
}