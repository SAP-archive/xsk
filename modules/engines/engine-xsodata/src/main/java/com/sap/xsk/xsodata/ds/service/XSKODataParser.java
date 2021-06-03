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
package com.sap.xsk.xsodata.ds.service;

import com.ibm.icu.impl.IllegalIcuArgumentException;
import com.sap.xsk.parser.xsodata.core.HdbxsodataLexer;
import com.sap.xsk.parser.xsodata.core.HdbxsodataParser;
import com.sap.xsk.parser.xsodata.custom.XSKHDBXSODATACoreListener;
import com.sap.xsk.parser.xsodata.custom.XSKHDBXSODATASyntaxErrorListener;
import com.sap.xsk.parser.xsodata.model.XSKHDBXSODATAService;
import com.sap.xsk.xsodata.ds.api.IXSKODataParser;
import com.sap.xsk.xsodata.ds.model.XSKODataModel;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.apache.commons.codec.digest.DigestUtils;
import org.eclipse.dirigible.api.v3.security.UserFacade;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;

/**
 * The factory for creation of the data structure models from source content.
 */
public class XSKODataParser implements IXSKODataParser {
    /**
     * Creates a odata model from the raw content.
     *
     * @param content the odata definition
     * @return the odata model instance
     * @throws IOException exception during parsing
     */
    public XSKODataModel parseXSODataArtifact(String location, String content) throws IOException {

        XSKODataModel odataModel = new XSKODataModel();
        odataModel.setName(new File(location).getName());
        odataModel.setLocation(location);
        odataModel.setHash(DigestUtils.md5Hex(content));
        odataModel.setCreatedBy(UserFacade.getName());
        odataModel.setCreatedAt(new Timestamp(new java.util.Date().getTime()));

        ByteArrayInputStream is = new ByteArrayInputStream(content.getBytes());
        ANTLRInputStream inputStream = new ANTLRInputStream(is);
        HdbxsodataLexer lexer = new HdbxsodataLexer(inputStream);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);

        HdbxsodataParser parser = new HdbxsodataParser(tokenStream);
        parser.setBuildParseTree(true);
        parser.removeErrorListeners();

        XSKHDBXSODATASyntaxErrorListener errorListener = new XSKHDBXSODATASyntaxErrorListener();
        parser.addErrorListener(errorListener);
        ParseTree parseTree = parser.xsodataDefinition();

        if (parser.getNumberOfSyntaxErrors() > 0) {
            String syntaxError = errorListener.getErrorMessage();
            throw new IllegalIcuArgumentException(String.format(
                    "Wrong format of XSODATA: [%s] during parsing. Ensure you are using the correct format for the correct compatibility version. [%s]",
                    location, syntaxError));
        }
        XSKHDBXSODATACoreListener coreListener = new XSKHDBXSODATACoreListener();
        ParseTreeWalker parseTreeWalker = new ParseTreeWalker();
        parseTreeWalker.walk(coreListener, parseTree);

        XSKHDBXSODATAService antlr4Model = coreListener.getServiceModel();
        odataModel.setService(antlr4Model);
        return odataModel;
    }

}
