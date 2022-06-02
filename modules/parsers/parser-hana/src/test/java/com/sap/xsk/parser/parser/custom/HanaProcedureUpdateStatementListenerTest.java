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
import custom.HanaProcedureUpdateStatementListener;
import models.ProcedureDefinitionModel;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Test;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;

public class HanaProcedureUpdateStatementListenerTest {

  @Test
  public void parseHdbProcedureUpdateStatements() throws Exception {
    String tableFunctionSample = getSample("/sample.hdbprocedure");
    ProcedureDefinitionModel model = parseHDBPProcedureModel(tableFunctionSample);

    assertEquals(5, model.getUpdateStatements().size());
  }

  private String getSample(String sampleName) throws IOException {
    return org.apache.commons.io.IOUtils
        .toString(HanaTableFunctionListenerTest.class.getResourceAsStream(sampleName), StandardCharsets.UTF_8);
  }

  private ProcedureDefinitionModel parseHDBPProcedureModel(String sample) {
    CharStream inputStream = CharStreams.fromString(sample);
    HanaLexer lexer = new HanaLexer(inputStream);
    CommonTokenStream tokenStream = new CommonTokenStream(lexer);

    HanaParser parser = new HanaParser(tokenStream);
    parser.setBuildParseTree(true);
    ParseTree parseTree = parser.sql_script();

    HanaProcedureUpdateStatementListener listener = new HanaProcedureUpdateStatementListener();
    ParseTreeWalker parseTreeWalker = new ParseTreeWalker();
    parseTreeWalker.walk(listener, parseTree);

    return listener.getProcedureModel();
  }
}


