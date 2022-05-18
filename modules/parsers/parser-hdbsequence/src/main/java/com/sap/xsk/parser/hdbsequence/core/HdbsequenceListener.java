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
// Generated from com/sap/xsk/parser/hdbsequence/core/Hdbsequence.g4 by ANTLR 4.10.1
package com.sap.xsk.parser.hdbsequence.core;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link HdbsequenceParser}.
 */
public interface HdbsequenceListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link HdbsequenceParser#hdbsequence}.
	 * @param ctx the parse tree
	 */
	void enterHdbsequence(HdbsequenceParser.HdbsequenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbsequenceParser#hdbsequence}.
	 * @param ctx the parse tree
	 */
	void exitHdbsequence(HdbsequenceParser.HdbsequenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbsequenceParser#property}.
	 * @param ctx the parse tree
	 */
	void enterProperty(HdbsequenceParser.PropertyContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbsequenceParser#property}.
	 * @param ctx the parse tree
	 */
	void exitProperty(HdbsequenceParser.PropertyContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbsequenceParser#schema}.
	 * @param ctx the parse tree
	 */
	void enterSchema(HdbsequenceParser.SchemaContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbsequenceParser#schema}.
	 * @param ctx the parse tree
	 */
	void exitSchema(HdbsequenceParser.SchemaContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbsequenceParser#increment_by}.
	 * @param ctx the parse tree
	 */
	void enterIncrement_by(HdbsequenceParser.Increment_byContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbsequenceParser#increment_by}.
	 * @param ctx the parse tree
	 */
	void exitIncrement_by(HdbsequenceParser.Increment_byContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbsequenceParser#start_with}.
	 * @param ctx the parse tree
	 */
	void enterStart_with(HdbsequenceParser.Start_withContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbsequenceParser#start_with}.
	 * @param ctx the parse tree
	 */
	void exitStart_with(HdbsequenceParser.Start_withContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbsequenceParser#maxvalue}.
	 * @param ctx the parse tree
	 */
	void enterMaxvalue(HdbsequenceParser.MaxvalueContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbsequenceParser#maxvalue}.
	 * @param ctx the parse tree
	 */
	void exitMaxvalue(HdbsequenceParser.MaxvalueContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbsequenceParser#nomaxvalue}.
	 * @param ctx the parse tree
	 */
	void enterNomaxvalue(HdbsequenceParser.NomaxvalueContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbsequenceParser#nomaxvalue}.
	 * @param ctx the parse tree
	 */
	void exitNomaxvalue(HdbsequenceParser.NomaxvalueContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbsequenceParser#minvalue}.
	 * @param ctx the parse tree
	 */
	void enterMinvalue(HdbsequenceParser.MinvalueContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbsequenceParser#minvalue}.
	 * @param ctx the parse tree
	 */
	void exitMinvalue(HdbsequenceParser.MinvalueContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbsequenceParser#nominvalue}.
	 * @param ctx the parse tree
	 */
	void enterNominvalue(HdbsequenceParser.NominvalueContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbsequenceParser#nominvalue}.
	 * @param ctx the parse tree
	 */
	void exitNominvalue(HdbsequenceParser.NominvalueContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbsequenceParser#cycles}.
	 * @param ctx the parse tree
	 */
	void enterCycles(HdbsequenceParser.CyclesContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbsequenceParser#cycles}.
	 * @param ctx the parse tree
	 */
	void exitCycles(HdbsequenceParser.CyclesContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbsequenceParser#reset_by}.
	 * @param ctx the parse tree
	 */
	void enterReset_by(HdbsequenceParser.Reset_byContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbsequenceParser#reset_by}.
	 * @param ctx the parse tree
	 */
	void exitReset_by(HdbsequenceParser.Reset_byContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbsequenceParser#publicc}.
	 * @param ctx the parse tree
	 */
	void enterPublicc(HdbsequenceParser.PubliccContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbsequenceParser#publicc}.
	 * @param ctx the parse tree
	 */
	void exitPublicc(HdbsequenceParser.PubliccContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbsequenceParser#dependsOnTable}.
	 * @param ctx the parse tree
	 */
	void enterDependsOnTable(HdbsequenceParser.DependsOnTableContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbsequenceParser#dependsOnTable}.
	 * @param ctx the parse tree
	 */
	void exitDependsOnTable(HdbsequenceParser.DependsOnTableContext ctx);
	/**
	 * Enter a parse tree produced by {@link HdbsequenceParser#dependsOnView}.
	 * @param ctx the parse tree
	 */
	void enterDependsOnView(HdbsequenceParser.DependsOnViewContext ctx);
	/**
	 * Exit a parse tree produced by {@link HdbsequenceParser#dependsOnView}.
	 * @param ctx the parse tree
	 */
	void exitDependsOnView(HdbsequenceParser.DependsOnViewContext ctx);
}