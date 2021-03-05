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

import com.sap.xsk.parser.hdbview.core.HdbviewLexer;
import com.sap.xsk.parser.hdbview.core.HdbviewParser;
import com.sap.xsk.parser.hdbview.models.XSKHDBVIEWDefinitionModel;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.fail;

public class XSKHDBVIEWCoreListenerTest {

    @Test
    public void parse_hdbviewModel_successfully() {
        String hdbviewSample = "";
        try {
            hdbviewSample = org.apache.commons.io.IOUtils.toString(XSKHDBVIEWCoreListenerTest.class.getResourceAsStream("/sample.hdbview"), StandardCharsets.UTF_8);
        } catch (IOException e) {
            fail("Parsing of sample.hdbview failed.");
            e.printStackTrace();
        }
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
        Assert.assertNotNull(model);
        Assert.assertEquals(hdbviewParser.getNumberOfSyntaxErrors(),0);
        Assert.assertTrue(model.isPublic());
    }

    @Test
    public void parse_hdbviewModel_exceptionThrown() {
        String hdbviewSample = "";
        try {
            hdbviewSample = org.apache.commons.io.IOUtils.toString(XSKHDBVIEWCoreListenerTest.class.getResourceAsStream("/sample_with_errors.hdbview"), StandardCharsets.UTF_8);
        } catch (IOException e) {
            fail("Parsing of sample_with_errors.hdbview failed.");
            e.printStackTrace();
        }
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
        Assert.assertEquals(hdbviewParser.getNumberOfSyntaxErrors(),3);
        Assert.assertFalse(model.isPublic());
    }
}
