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
// Generated from com/sap/xsk/parser/hdbsequence/core/Hdbsequence.g4 by ANTLR 4.3
package com.sap.xsk.parser.hdbsequence.core;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link HdbsequenceParser}.
 */
public interface HdbsequenceListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link HdbsequenceParser#schema}.
	 * @param ctx the parse tree
	 */
	void enterSchema(@NotNull HdbsequenceParser.SchemaContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbsequenceParser#schema}.
	 * @param ctx the parse tree
	 */
	void exitSchema(@NotNull HdbsequenceParser.SchemaContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbsequenceParser#publicc}.
	 * @param ctx the parse tree
	 */
	void enterPublicc(@NotNull HdbsequenceParser.PubliccContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbsequenceParser#publicc}.
	 * @param ctx the parse tree
	 */
	void exitPublicc(@NotNull HdbsequenceParser.PubliccContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbsequenceParser#maxvalue}.
	 * @param ctx the parse tree
	 */
	void enterMaxvalue(@NotNull HdbsequenceParser.MaxvalueContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbsequenceParser#maxvalue}.
	 * @param ctx the parse tree
	 */
	void exitMaxvalue(@NotNull HdbsequenceParser.MaxvalueContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbsequenceParser#nomaxvalue}.
	 * @param ctx the parse tree
	 */
	void enterNomaxvalue(@NotNull HdbsequenceParser.NomaxvalueContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbsequenceParser#nomaxvalue}.
	 * @param ctx the parse tree
	 */
	void exitNomaxvalue(@NotNull HdbsequenceParser.NomaxvalueContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbsequenceParser#nominvalue}.
	 * @param ctx the parse tree
	 */
	void enterNominvalue(@NotNull HdbsequenceParser.NominvalueContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbsequenceParser#nominvalue}.
	 * @param ctx the parse tree
	 */
	void exitNominvalue(@NotNull HdbsequenceParser.NominvalueContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbsequenceParser#cycles}.
	 * @param ctx the parse tree
	 */
	void enterCycles(@NotNull HdbsequenceParser.CyclesContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbsequenceParser#cycles}.
	 * @param ctx the parse tree
	 */
	void exitCycles(@NotNull HdbsequenceParser.CyclesContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbsequenceParser#dependsOnTable}.
	 * @param ctx the parse tree
	 */
	void enterDependsOnTable(@NotNull HdbsequenceParser.DependsOnTableContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbsequenceParser#dependsOnTable}.
	 * @param ctx the parse tree
	 */
	void exitDependsOnTable(@NotNull HdbsequenceParser.DependsOnTableContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbsequenceParser#minvalue}.
	 * @param ctx the parse tree
	 */
	void enterMinvalue(@NotNull HdbsequenceParser.MinvalueContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbsequenceParser#minvalue}.
	 * @param ctx the parse tree
	 */
	void exitMinvalue(@NotNull HdbsequenceParser.MinvalueContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbsequenceParser#dependsOnView}.
	 * @param ctx the parse tree
	 */
	void enterDependsOnView(@NotNull HdbsequenceParser.DependsOnViewContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbsequenceParser#dependsOnView}.
	 * @param ctx the parse tree
	 */
	void exitDependsOnView(@NotNull HdbsequenceParser.DependsOnViewContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbsequenceParser#property}.
	 * @param ctx the parse tree
	 */
	void enterProperty(@NotNull HdbsequenceParser.PropertyContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbsequenceParser#property}.
	 * @param ctx the parse tree
	 */
	void exitProperty(@NotNull HdbsequenceParser.PropertyContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbsequenceParser#reset_by}.
	 * @param ctx the parse tree
	 */
	void enterReset_by(@NotNull HdbsequenceParser.Reset_byContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbsequenceParser#reset_by}.
	 * @param ctx the parse tree
	 */
	void exitReset_by(@NotNull HdbsequenceParser.Reset_byContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbsequenceParser#increment_by}.
	 * @param ctx the parse tree
	 */
	void enterIncrement_by(@NotNull HdbsequenceParser.Increment_byContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbsequenceParser#increment_by}.
	 * @param ctx the parse tree
	 */
	void exitIncrement_by(@NotNull HdbsequenceParser.Increment_byContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbsequenceParser#hdbsequence}.
	 * @param ctx the parse tree
	 */
	void enterHdbsequence(@NotNull HdbsequenceParser.HdbsequenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbsequenceParser#hdbsequence}.
	 * @param ctx the parse tree
	 */
	void exitHdbsequence(@NotNull HdbsequenceParser.HdbsequenceContext ctx);

	/**
	 * Enter a parse tree produced by {@link HdbsequenceParser#start_with}.
	 * @param ctx the parse tree
	 */
	void enterStart_with(@NotNull HdbsequenceParser.Start_withContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbsequenceParser#start_with}.
	 * @param ctx the parse tree
	 */
	void exitStart_with(@NotNull HdbsequenceParser.Start_withContext ctx);
}