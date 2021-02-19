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
package com.sap.xsk.parser.hdbti.custom;

import com.sap.xsk.parser.hdbti.core.HdbtiLexer;
import com.sap.xsk.parser.hdbti.core.HdbtiParser;
import com.sap.xsk.parser.hdbti.models.XSKHDBTIImportModel;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class XSKHDBTIParser {

    public XSKHDBTIImportModel parse(String content) throws IOException {
        ByteArrayInputStream is = new ByteArrayInputStream(content.getBytes());
        ANTLRInputStream inputStream = new ANTLRInputStream(is);
        HdbtiLexer hdbtiLexer = new HdbtiLexer(inputStream);
        CommonTokenStream tokenStream = new CommonTokenStream(hdbtiLexer);
        HdbtiParser hdbtiParser = new HdbtiParser(tokenStream);
        hdbtiParser.setBuildParseTree(true);
        ParseTree parseTree = hdbtiParser.importArr();

        XSKHDBTICoreListener XSKHDBTICoreListener = new XSKHDBTICoreListener();
        ParseTreeWalker parseTreeWalker = new ParseTreeWalker();
        parseTreeWalker.walk(XSKHDBTICoreListener, parseTree);

        return XSKHDBTICoreListener.getImportModel();
    }
}
