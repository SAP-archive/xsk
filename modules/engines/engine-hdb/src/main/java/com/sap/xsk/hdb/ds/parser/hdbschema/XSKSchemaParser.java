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
package com.sap.xsk.hdb.ds.parser.hdbschema;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.xsk.exceptions.XSKArtifactParserException;
import com.sap.xsk.hdb.ds.api.IXSKDataStructureModel;
import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdb.ds.model.XSKDBContentType;
import com.sap.xsk.hdb.ds.model.hdbschema.XSKDataStructureHDBSchemaModel;
import com.sap.xsk.hdb.ds.parser.XSKDataStructureParser;
import com.sap.xsk.parser.hdbschema.core.HdbschemaLexer;
import com.sap.xsk.parser.hdbschema.core.HdbschemaParser;
import com.sap.xsk.parser.hdbschema.custom.XSKHDBSCHEMACoreListener;
import com.sap.xsk.parser.hdbschema.custom.XSKHDBSCHEMASyntaxErrorListener;
import com.sap.xsk.parser.hdbschema.models.XSKHDBSCHEMADefinitionModel;
import com.sap.xsk.utils.XSKCommonsUtils;
import com.sap.xsk.utils.XSKHDBUtils;

public class XSKSchemaParser implements XSKDataStructureParser<XSKDataStructureHDBSchemaModel> {

    private static final Logger logger = LoggerFactory.getLogger(XSKSchemaParser.class);

    @Override
    public String getType() {
        return IXSKDataStructureModel.TYPE_HDB_SCHEMA;
    }

    @Override
    public Class<XSKDataStructureHDBSchemaModel> getDataStructureClass() {
        return XSKDataStructureHDBSchemaModel.class;
    }

    @Override
    public XSKDataStructureHDBSchemaModel parse(String location, String content) throws XSKDataStructuresException, IOException, XSKArtifactParserException {
        XSKDataStructureHDBSchemaModel hdbSchemaModel = new XSKDataStructureHDBSchemaModel();
        XSKHDBUtils.populateXSKDataStructureModel(location, content, hdbSchemaModel, IXSKDataStructureModel.TYPE_HDB_SCHEMA, XSKDBContentType.XS_CLASSIC);

        ByteArrayInputStream is = new ByteArrayInputStream(content.getBytes());
        ANTLRInputStream inputStream = new ANTLRInputStream(is);
        HdbschemaLexer lexer = new HdbschemaLexer(inputStream);
        XSKHDBSCHEMASyntaxErrorListener lexerErrorListener = new XSKHDBSCHEMASyntaxErrorListener();
        lexer.removeErrorListeners();
        lexer.addErrorListener(lexerErrorListener);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);

        HdbschemaParser hdbschemaParser = new HdbschemaParser(tokenStream);
        hdbschemaParser.setBuildParseTree(true);
        XSKHDBSCHEMASyntaxErrorListener parserErrorListener = new XSKHDBSCHEMASyntaxErrorListener();
        hdbschemaParser.removeErrorListeners();
        hdbschemaParser.addErrorListener(parserErrorListener);

        ParseTree parseTree = hdbschemaParser.hdbschemaDefinition();
        XSKCommonsUtils.logParserErrors(parserErrorListener.getErrorMessages(), location, "HDB Schema", logger);
        XSKCommonsUtils.logParserErrors(lexerErrorListener.getErrorMessages(), location, "HDB Schema", logger);

        XSKHDBSCHEMACoreListener XSKHDBSCHEMACoreListener = new XSKHDBSCHEMACoreListener();
        ParseTreeWalker parseTreeWalker = new ParseTreeWalker();
        parseTreeWalker.walk(XSKHDBSCHEMACoreListener, parseTree);

        XSKHDBSCHEMADefinitionModel antlr4Model = XSKHDBSCHEMACoreListener.getModel();
        hdbSchemaModel.setSchema(antlr4Model.getSchemaName());

        return hdbSchemaModel;
    }
}
