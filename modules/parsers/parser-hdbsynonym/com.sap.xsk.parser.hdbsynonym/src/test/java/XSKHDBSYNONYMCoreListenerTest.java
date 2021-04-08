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

import static org.junit.Assert.fail;

import com.sap.xsk.parser.hdbsynonym.core.HdbsynonymLexer;
import com.sap.xsk.parser.hdbsynonym.core.HdbsynonymParser;
import com.sap.xsk.parser.hdbsynonym.custom.XSKHDBSYNONYMCoreListener;
import com.sap.xsk.parser.hdbsynonym.models.XSKHDBSYNONYMDefinitionModel;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Assert;
import org.junit.Test;

public class XSKHDBSYNONYMCoreListenerTest {

  private ANTLRInputStream inputStream;

  @Test
  public void parse_hdbsynonymModel_successfully() {
    String sample = "";
    try {
      sample = org.apache.commons.io.IOUtils
          .toString(XSKHDBSYNONYMCoreListenerTest.class.getResourceAsStream("/sample.hdbsynonym"), StandardCharsets.UTF_8);
    } catch (IOException e) {
      fail("Parsing of sample.hdbsynonym failed.");
      e.printStackTrace();
    }
    ANTLRInputStream inputStream = new ANTLRInputStream(sample);
    HdbsynonymLexer HdbsynonymLexer = new HdbsynonymLexer(inputStream);
    CommonTokenStream tokenStream = new CommonTokenStream(HdbsynonymLexer);
    HdbsynonymParser hdbsynonymParser = new HdbsynonymParser(tokenStream);
    hdbsynonymParser.setBuildParseTree(true);
    ParseTree parseTree = hdbsynonymParser.hdbsynonymDefinition();

    XSKHDBSYNONYMCoreListener hdbsynonymCoreListener = new XSKHDBSYNONYMCoreListener();
    ParseTreeWalker parseTreeWalker = new ParseTreeWalker();
    parseTreeWalker.walk(hdbsynonymCoreListener, parseTree);

    XSKHDBSYNONYMDefinitionModel model = hdbsynonymCoreListener.getModel();
    Assert.assertNotNull(model);
    Assert.assertEquals("No Syntax errors are found", 0, hdbsynonymParser.getNumberOfSyntaxErrors());
  }

  @Test
  public void parse_hdbsynonymModel_with_diff_target_positions_successfully() {
    String sample = "";
    try {
      sample = org.apache.commons.io.IOUtils
          .toString(XSKHDBSYNONYMCoreListenerTest.class.getResourceAsStream("/sample2.hdbsynonym"), StandardCharsets.UTF_8);
    } catch (IOException e) {
      fail("Parsing of sample.hdbsynonym failed.");
      e.printStackTrace();
    }
    ANTLRInputStream inputStream = new ANTLRInputStream(sample);
    HdbsynonymLexer HdbsynonymLexer = new HdbsynonymLexer(inputStream);
    CommonTokenStream tokenStream = new CommonTokenStream(HdbsynonymLexer);
    HdbsynonymParser hdbsynonymParser = new HdbsynonymParser(tokenStream);
    hdbsynonymParser.setBuildParseTree(true);
    ParseTree parseTree = hdbsynonymParser.hdbsynonymDefinition();

    XSKHDBSYNONYMCoreListener hdbsynonymCoreListener = new XSKHDBSYNONYMCoreListener();
    ParseTreeWalker parseTreeWalker = new ParseTreeWalker();
    parseTreeWalker.walk(hdbsynonymCoreListener, parseTree);

    XSKHDBSYNONYMDefinitionModel model = hdbsynonymCoreListener.getModel();
    Assert.assertNotNull(model);
    Assert.assertEquals("No Syntax errors are found", 0, hdbsynonymParser.getNumberOfSyntaxErrors());
  }

  @Test
  public void parse_hdbsynonymModel_exceptionThrown() {
    String sample = "";
    try {
      sample = org.apache.commons.io.IOUtils
          .toString(XSKHDBSYNONYMCoreListenerTest.class.getResourceAsStream("/sample_with_errors.hdbsynonym"), StandardCharsets.UTF_8);
    } catch (IOException e) {
      fail("Parsing of sample_with_errors.hdbsynonym failed.");
      e.printStackTrace();
    }
    ANTLRInputStream inputStream = new ANTLRInputStream(sample);
    HdbsynonymLexer HdbsynonymLexer = new HdbsynonymLexer(inputStream);
    CommonTokenStream tokenStream = new CommonTokenStream(HdbsynonymLexer);
    HdbsynonymParser hdbsynonymParser = new HdbsynonymParser(tokenStream);
    hdbsynonymParser.setBuildParseTree(true);
    ParseTree parseTree = hdbsynonymParser.hdbsynonymDefinition();

    XSKHDBSYNONYMCoreListener hdbsynonymCoreListener = new XSKHDBSYNONYMCoreListener();
    ParseTreeWalker parseTreeWalker = new ParseTreeWalker();
    parseTreeWalker.walk(hdbsynonymCoreListener, parseTree);

    XSKHDBSYNONYMDefinitionModel model = hdbsynonymCoreListener.getModel();
    Assert.assertEquals("Found 1 Syntax error", 1, hdbsynonymParser.getNumberOfSyntaxErrors());
  }
}
