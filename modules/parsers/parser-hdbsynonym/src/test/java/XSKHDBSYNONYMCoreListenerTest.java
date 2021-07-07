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

import com.sap.xsk.parser.hdbsynonym.core.HdbsynonymLexer;
import com.sap.xsk.parser.hdbsynonym.core.HdbsynonymParser;
import com.sap.xsk.parser.hdbsynonym.custom.XSKHDBSYNONYMCoreListener;
import com.sap.xsk.parser.hdbsynonym.exceptions.XSKHDBSYNONYMMissingPropertyException;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Assert;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

public class XSKHDBSYNONYMCoreListenerTest {
    private final XSKHDBSYNONYMCoreListener listener = new XSKHDBSYNONYMCoreListener();

    @Test
    public void parseHdbsynonymModelSuccessfully() throws Exception {
        HdbsynonymParser parser = parseSampleFile("/sample.hdbsynonym");
        Assert.assertNotNull(listener.getModel());
        Assert.assertEquals("No Syntax errors are found", 0, parser.getNumberOfSyntaxErrors());
    }

    @Test
    public void parseHdbsynonymModelWithDiffTargetPositionsSuccessfully() throws Exception {
        HdbsynonymParser parser = parseSampleFile("/sample2.hdbsynonym");
        Assert.assertNotNull(listener.getModel());
        Assert.assertEquals("No Syntax errors are found", 0, parser.getNumberOfSyntaxErrors());
    }

    @Test
    public void parseHdbsynonymModelExceptionThrown() throws Exception {
        HdbsynonymParser parser = parseSampleFile("/sample_with_errors.hdbsynonym");
        Assert.assertEquals("Found 1 Syntax error", 1, parser.getNumberOfSyntaxErrors());
    }

    @Test(expected = XSKHDBSYNONYMMissingPropertyException.class)
    public void parseHdbsynonymModelWithWrongDefinition2() throws Exception {
        parseSampleFile("/sample_with_missing_definitions.hdbsynonym");
        listener.getModel().checkForAllMandatoryFieldsPresence();
    }

    @Test
    public void parseHdbsynonymModelWithWrongDefinition() throws Exception {
        HdbsynonymParser parser = parseSampleFile("/sample_with_wrong_definition.hdbsynonym");
        Assert.assertEquals("Found 1 Syntax error", 1, parser.getNumberOfSyntaxErrors());
    }

    private HdbsynonymParser parseSampleFile(String sampleFileName) throws Exception {
        String sample =
                org.apache.commons.io.IOUtils.toString(
                        XSKHDBSYNONYMCoreListenerTest.class.getResourceAsStream(sampleFileName),
                        StandardCharsets.UTF_8);

        ANTLRInputStream inputStream = new ANTLRInputStream(sample);
        HdbsynonymLexer lexer = new HdbsynonymLexer(inputStream);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        HdbsynonymParser parser = new HdbsynonymParser(tokenStream);
        parser.setBuildParseTree(true);
        ParseTree parseTree = parser.hdbsynonymDefinition();

        ParseTreeWalker parseTreeWalker = new ParseTreeWalker();
        parseTreeWalker.walk(listener, parseTree);

        return parser;
    }
}
