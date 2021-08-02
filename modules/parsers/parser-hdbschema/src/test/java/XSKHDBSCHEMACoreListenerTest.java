/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2021 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.sap.xsk.parser.hdbschema.core.HdbschemaLexer;
import com.sap.xsk.parser.hdbschema.core.HdbschemaParser;
import com.sap.xsk.parser.hdbschema.custom.XSKHDBSCHEMACoreListener;
import com.sap.xsk.parser.hdbschema.models.XSKHDBSCHEMADefinitionModel;
import java.nio.charset.StandardCharsets;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Test;

public class XSKHDBSCHEMACoreListenerTest {

  @Test
  public void parseHdbschemaFileWithoutErrorsSuccessfully() throws Exception {
    String hdbschemaSample = org.apache.commons.io.IOUtils
        .toString(XSKHDBSCHEMACoreListenerTest.class.getResourceAsStream("/sample.hdbschema"), StandardCharsets.UTF_8);

    ANTLRInputStream inputStream = new ANTLRInputStream(hdbschemaSample);
    HdbschemaLexer hdbschemaLexer = new HdbschemaLexer(inputStream);
    CommonTokenStream tokenStream = new CommonTokenStream(hdbschemaLexer);
    HdbschemaParser hdbschemaParser = new HdbschemaParser(tokenStream);
    hdbschemaParser.setBuildParseTree(true);
    ParseTree parseTree = hdbschemaParser.hdbschemaDefinition();

    XSKHDBSCHEMACoreListener hdbschemaCoreListener = new XSKHDBSCHEMACoreListener();
    ParseTreeWalker parseTreeWalker = new ParseTreeWalker();
    parseTreeWalker.walk(hdbschemaCoreListener, parseTree);

    XSKHDBSCHEMADefinitionModel model = hdbschemaCoreListener.getModel();
    assertNotNull(model);
    assertEquals(hdbschemaParser.getNumberOfSyntaxErrors(), 0);
    assertEquals("MYSCHEMA", model.getSchemaName());
  }

  @Test
  public void parseHdbschemaFileWithSyntaxErrorExceptionThrown() throws Exception {
    String hdbschemaSample = org.apache.commons.io.IOUtils
        .toString(XSKHDBSCHEMACoreListenerTest.class.getResourceAsStream("/sample_with_errors.hdbschema"), StandardCharsets.UTF_8);

    ANTLRInputStream inputStream = new ANTLRInputStream(hdbschemaSample);
    HdbschemaLexer hdbschemaLexer = new HdbschemaLexer(inputStream);
    CommonTokenStream tokenStream = new CommonTokenStream(hdbschemaLexer);
    HdbschemaParser hdbschemaParser = new HdbschemaParser(tokenStream);
    hdbschemaParser.setBuildParseTree(true);
    ParseTree parseTree = hdbschemaParser.hdbschemaDefinition();

    XSKHDBSCHEMACoreListener hdbschemaCoreListener = new XSKHDBSCHEMACoreListener();
    ParseTreeWalker parseTreeWalker = new ParseTreeWalker();
    parseTreeWalker.walk(hdbschemaCoreListener, parseTree);

    System.out.println(hdbschemaParser.getNumberOfSyntaxErrors());
    assertEquals(hdbschemaParser.getNumberOfSyntaxErrors(), 1);
  }
}