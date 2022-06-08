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
package com.sap.xsk.parser.parser.custom;

import com.sap.xsk.parser.hana.core.HanaLexer;
import com.sap.xsk.parser.hana.core.HanaParser;
import custom.HanaProcedureListener;
import models.ProcedureDefinitionModel;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class HanaProcedureListenerTest {

  @Test
  public void parseHDBProcedure() throws Exception {
    String hdbProcedureSample = getSample("/sample.hdbprocedure");
    ProcedureDefinitionModel model = parseModel(hdbProcedureSample);
    assertModel(model, "XSK_TEST_APP", "xsk_test_app::usersProcedure");
  }

  @Test
  public void parseHDBProcedureWithComment() throws Exception {
    String hdbProcedureSample = getSample("/sample_with_comment.hdbprocedure");
    ProcedureDefinitionModel model = parseModel(hdbProcedureSample);
    assertModel(model, "XSK_SAMPLES_HDB_PROCEDURE_SIMPLE", "hdb-procedure-simple.db::ItemProcedure");
  }

  private String getSample(String sampleName) throws IOException {
    return org.apache.commons.io.IOUtils
        .toString(Objects.requireNonNull(HanaProcedureListenerTest.class.getResourceAsStream(sampleName)), StandardCharsets.UTF_8);
  }

  private ProcedureDefinitionModel parseModel(String sample) {
    CharStream inputStream = CharStreams.fromString(sample);
    HanaLexer lexer = new HanaLexer(inputStream);
    CommonTokenStream tokenStream = new CommonTokenStream(lexer);

    HanaParser parser = new HanaParser(tokenStream);
    parser.setBuildParseTree(true);
    ParseTree parseTree = parser.sql_script();

    HanaProcedureListener listener = new HanaProcedureListener();
    ParseTreeWalker parseTreeWalker = new ParseTreeWalker();
    parseTreeWalker.walk(listener, parseTree);

    return listener.getModel();
  }

  private void assertModel(ProcedureDefinitionModel model, String expectedSchema, String expectedName) {
    assertNotNull(model);
    model.checkForAllMandatoryFieldsPresence();
    assertEquals("Unexpected schema name. ", expectedSchema, model.getSchema());
    assertEquals("Unexpected procedure name. ", expectedName, model.getName());
  }
}
