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
package com.sap.xsk.parser.hdbview.custom;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.sap.xsk.parser.hdbview.core.HdbviewLexer;
import com.sap.xsk.parser.hdbview.core.HdbviewParser;
import com.sap.xsk.parser.hdbview.exceptions.XSKHDBViewMissingPropertyException;
import com.sap.xsk.parser.hdbview.models.XSKHDBVIEWDefinitionModel;
import java.nio.charset.StandardCharsets;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Test;

public class XSKHDBVIEWCoreListenerTest {

  @Test
  public void parseHdbviewFileSuccessfully() throws Exception {
    String hdbviewSample = org.apache.commons.io.IOUtils
        .toString(XSKHDBVIEWCoreListenerTest.class.getResourceAsStream("/sample.hdbview"), StandardCharsets.UTF_8);

    ANTLRInputStream inputStream = new ANTLRInputStream(hdbviewSample);
    HdbviewLexer hdbviewLexer = new HdbviewLexer(inputStream);
    CommonTokenStream tokenStream = new CommonTokenStream(hdbviewLexer);
    HdbviewParser hdbviewParser = new HdbviewParser(tokenStream);
    hdbviewParser.setBuildParseTree(true);
    ParseTree parseTree = hdbviewParser.hdbviewDefinition();

    XSKHDBVIEWCoreListener hdbviewCoreListener = new XSKHDBVIEWCoreListener();
    ParseTreeWalker parseTreeWalker = new ParseTreeWalker();
    parseTreeWalker.walk(hdbviewCoreListener, parseTree);

    XSKHDBVIEWDefinitionModel model = hdbviewCoreListener.getModel();
    model.checkForAllMandatoryFieldsPresence();
    assertNotNull(model);
    assertEquals(hdbviewParser.getNumberOfSyntaxErrors(), 0);
    assertTrue(model.isPublic());
    assertEquals("MYSCHEMA", model.getSchema());
    assertEquals(2, model.getDependsOn().size());
    assertEquals("acme.com.test.tables::MY_TABLE1", model.getDependsOn().get(0));
    assertEquals("acme.com.test.views::MY_VIEW1", model.getDependsOn().get(1));
    assertEquals(2, model.getDependsOnTable().size());
    assertEquals("acme.com.test.tables::MY_TABLE1", model.getDependsOnTable().get(0));
    assertEquals("acme.com.test.views::MY_TABLE2", model.getDependsOnTable().get(1));
    assertEquals(2, model.getDependsOnView().size());
    assertEquals("acme.com.test.tables::MY_VIEW1", model.getDependsOnView().get(0));
    assertEquals("acme.com.test.views::MY_VIEW2", model.getDependsOnView().get(1));
    assertEquals(
        "SELECT T1.\"Column2\" FROM \"MYSCHEMA\".\"acme.com.test.tables::MY_TABLE1\" AS T1 LEFT JOIN \"MYSCHEMA\".\"acme.com.test.views::MY_VIEW1\" AS T2 ON T1.\"Column1\" = T2.\"Column1\"",
        model.getQuery());
  }

  @Test(expected = XSKHDBViewMissingPropertyException.class)
  public void parseHdbviewFileWithSyntaxErrorExceptionThrown() throws Exception {
    String hdbviewSample = org.apache.commons.io.IOUtils
        .toString(XSKHDBVIEWCoreListenerTest.class.getResourceAsStream("/sample_with_errors.hdbview"), StandardCharsets.UTF_8);

    ANTLRInputStream inputStream = new ANTLRInputStream(hdbviewSample);
    HdbviewLexer hdbviewLexer = new HdbviewLexer(inputStream);
    CommonTokenStream tokenStream = new CommonTokenStream(hdbviewLexer);
    HdbviewParser hdbviewParser = new HdbviewParser(tokenStream);
    hdbviewParser.setBuildParseTree(true);
    ParseTree parseTree = hdbviewParser.hdbviewDefinition();

    XSKHDBVIEWCoreListener hdbviewCoreListener = new XSKHDBVIEWCoreListener();
    ParseTreeWalker parseTreeWalker = new ParseTreeWalker();
    parseTreeWalker.walk(hdbviewCoreListener, parseTree);

    XSKHDBVIEWDefinitionModel model = hdbviewCoreListener.getModel();
    assertEquals(hdbviewParser.getNumberOfSyntaxErrors(), 2);
    model.checkForAllMandatoryFieldsPresence();
    assertFalse(model.isPublic());
  }
}
