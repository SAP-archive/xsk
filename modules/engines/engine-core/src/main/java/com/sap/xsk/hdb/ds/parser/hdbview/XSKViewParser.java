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
package com.sap.xsk.hdb.ds.parser.hdbview;

import com.sap.xsk.hdb.ds.api.IXSKDataStructureModel;
import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdb.ds.model.hdbview.XSKDataStructureHDBViewModel;
import com.sap.xsk.hdb.ds.parser.XSKDataStructureParser;
import com.sap.xsk.parser.hdbview.core.HdbviewLexer;
import com.sap.xsk.parser.hdbview.core.HdbviewParser;
import com.sap.xsk.parser.hdbview.custom.XSKHDBVIEWCoreListener;
import com.sap.xsk.parser.hdbview.custom.XSKHDBVIEWSyntaxErrorListener;
import com.sap.xsk.parser.hdbview.models.XSKHDBVIEWDefinitionModel;
import com.sap.xsk.utils.XSKUtils;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.apache.commons.codec.digest.DigestUtils;
import org.eclipse.dirigible.api.v3.security.UserFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Timestamp;

public class XSKViewParser implements XSKDataStructureParser {

    private static final Logger logger = LoggerFactory.getLogger(XSKViewParser.class);

    @Override
    public XSKDataStructureHDBViewModel parse(String location, String content) throws XSKDataStructuresException, IOException {
        ByteArrayInputStream is = new ByteArrayInputStream(content.getBytes());
        ANTLRInputStream inputStream = new ANTLRInputStream(is);
        HdbviewLexer hdbviewLexer = new HdbviewLexer(inputStream);
        CommonTokenStream tokenStream = new CommonTokenStream(hdbviewLexer);

        HdbviewParser hdbviewParser = new HdbviewParser(tokenStream);
        hdbviewParser.setBuildParseTree(true);
        hdbviewParser.removeErrorListeners();

        XSKHDBVIEWSyntaxErrorListener xskhdbviewSyntaxErrorListener = new XSKHDBVIEWSyntaxErrorListener();
        hdbviewParser.addErrorListener(xskhdbviewSyntaxErrorListener);
        ParseTree parseTree = hdbviewParser.hdbviewDefinition();

        if (hdbviewParser.getNumberOfSyntaxErrors() > 0) {
            String syntaxError = xskhdbviewSyntaxErrorListener.getErrorMessage();
            throw new XSKDataStructuresException(String.format("Wrong format of HDB View: [%s] during parsing. Ensure you are using the correct format for the correct compatibility version. [%s]", location, syntaxError));
        }
        XSKHDBVIEWCoreListener XSKHDBVIEWCoreListener = new XSKHDBVIEWCoreListener();
        ParseTreeWalker parseTreeWalker = new ParseTreeWalker();
        parseTreeWalker.walk(XSKHDBVIEWCoreListener, parseTree);

        XSKHDBVIEWDefinitionModel antlr4Model = XSKHDBVIEWCoreListener.getModel();
        XSKDataStructureHDBViewModel hdbViewModel = new XSKDataStructureHDBViewModel();

        hdbViewModel.setName(XSKUtils.getTableName(location));
        hdbViewModel.setLocation(location);
        hdbViewModel.setType(IXSKDataStructureModel.TYPE_HDB_VIEW);
        hdbViewModel.setHash(DigestUtils.md5Hex(content));
        hdbViewModel.setCreatedBy(UserFacade.getName());
        hdbViewModel.setCreatedAt(new Timestamp(new java.util.Date().getTime()));
        hdbViewModel.setQuery(antlr4Model.getQuery());
        hdbViewModel.setSchema(antlr4Model.getSchema());

        //TODO: process of antlr4Model.getDependsOnTable() values

        return hdbViewModel;
    }

    @Override
    public String getType() {
        return IXSKDataStructureModel.TYPE_HDB_VIEW;
    }

    @Override
    public Class getDataStructureClass() {
        return XSKDataStructureHDBViewModel.class;
    }
}
