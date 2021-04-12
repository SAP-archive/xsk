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
package com.sap.xsk.hdb.ds.parser.hdbsequence;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.sap.xsk.hdb.ds.api.IXSKDataStructureModel;
import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdb.ds.model.XSKDataStructureModel;
import com.sap.xsk.hdb.ds.model.XSKHanaVersion;
import com.sap.xsk.hdb.ds.model.hdbsequence.XSKDataStructureHDBSequenceModel;
import com.sap.xsk.hdb.ds.parser.XSKDataStructureParser;
import com.sap.xsk.parser.hdbsequence.core.HdbsequenceBaseVisitor;
import com.sap.xsk.parser.hdbsequence.core.HdbsequenceLexer;
import com.sap.xsk.parser.hdbsequence.core.HdbsequenceParser;
import com.sap.xsk.parser.hdbsequence.custom.CustomDeserializers;
import com.sap.xsk.parser.hdbsequence.custom.HdbsequenceVisitor;
import com.sap.xsk.parser.hdbsequence.custom.XSKHDBSEQUENCESyntaxErrorListener;
import com.sap.xsk.parser.hdbsequence.models.XSKHDBSEQUENCEModel;
import com.sap.xsk.utils.XSKConstants;
import com.sap.xsk.utils.XSKHDBUtils;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.apache.commons.codec.digest.DigestUtils;
import org.eclipse.dirigible.api.v3.security.UserFacade;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Timestamp;

public class XSKHDBSequenceParser implements XSKDataStructureParser {
    private static final Logger logger = LoggerFactory.getLogger(XSKHDBSequenceParser.class);

    @Override
    public XSKDataStructureModel parse(String location, String content) throws XSKDataStructuresException, IOException {
        String expectedHanaXSAdvancedSyntax = XSKConstants.XSK_HDBSEQUENCE_SYNTAX + "\"" + XSKHDBUtils.getRepositoryBaseObjectName(location) + "\"";
        String receivedSyntax = XSKHDBUtils.extractRepositoryBaseObjectNameFromContent(XSKConstants.XSK_HDBSEQUENCE_SYNTAX, content);
        logger.debug("Determine if the hdbsequence is Hana XS Classic or Hana XS Advanced by Comparing '" + receivedSyntax + "' with '" + expectedHanaXSAdvancedSyntax + "'");

        return (receivedSyntax.equals(expectedHanaXSAdvancedSyntax))
                ? parseHanaXSAdvancedContent(location, content)
                : parseHanaXSClassicContent(location, content);
    }

    @Override
    public String getType() {
        return IXSKDataStructureModel.TYPE_HDB_SEQUENCE;
    }

    @Override
    public Class<XSKDataStructureHDBSequenceModel> getDataStructureClass() {
        return XSKDataStructureHDBSequenceModel.class;
    }

    private XSKDataStructureModel parseHanaXSClassicContent(String location, String content) throws XSKDataStructuresException, IOException {
        ByteArrayInputStream is = new ByteArrayInputStream(content.getBytes());
        ANTLRInputStream inputStream = new ANTLRInputStream(is);
        HdbsequenceLexer lexer = new HdbsequenceLexer(inputStream);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);

        HdbsequenceParser parser = new HdbsequenceParser((tokenStream));
        parser.setBuildParseTree(true);
        parser.removeErrorListeners();
        XSKHDBSEQUENCESyntaxErrorListener errorListener = new XSKHDBSEQUENCESyntaxErrorListener();
        parser.addErrorListener(errorListener);

        ParseTree parseTree = parser.hdbsequence();
        if (parser.getNumberOfSyntaxErrors() > 0) {
            throw new XSKDataStructuresException(errorListener.getErrorMessage());
        }


        HdbsequenceBaseVisitor<JsonElement> visitor = new HdbsequenceVisitor();
        JsonElement parsedResult = visitor.visit(parseTree);
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(XSKHDBSEQUENCEModel.class, new CustomDeserializers.XSKHDBSEQUENCEModelAdapter())
                .create();
        XSKHDBSEQUENCEModel antlr4Model = gson.fromJson(parsedResult, XSKHDBSEQUENCEModel.class);
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
                .setMatchingStrategy(MatchingStrategies.STRICT);

        XSKDataStructureHDBSequenceModel hdbSequenceModel = modelMapper.map(antlr4Model, XSKDataStructureHDBSequenceModel.class);
        setXSKDataStructureHDBSequenceModelTrackingDetails(location, content, XSKHanaVersion.VERSION_1, hdbSequenceModel);

        return hdbSequenceModel;
    }

    private XSKDataStructureModel parseHanaXSAdvancedContent(String location, String content) {
        XSKDataStructureHDBSequenceModel hdbSequenceModel = new XSKDataStructureHDBSequenceModel();
        setXSKDataStructureHDBSequenceModelTrackingDetails(location, content, XSKHanaVersion.VERSION_2, hdbSequenceModel);
        hdbSequenceModel.setRawContent(content);
        return hdbSequenceModel;
    }

    private void setXSKDataStructureHDBSequenceModelTrackingDetails(String location, String content, XSKHanaVersion hanaVersion, XSKDataStructureHDBSequenceModel hdbSequenceModel) {
        hdbSequenceModel.setName(XSKHDBUtils.getRepositoryBaseObjectName(location));
        hdbSequenceModel.setLocation(location);
        hdbSequenceModel.setType(IXSKDataStructureModel.TYPE_HDB_SEQUENCE);
        hdbSequenceModel.setHash(DigestUtils.md5Hex(content));
        hdbSequenceModel.setCreatedBy(UserFacade.getName());
        hdbSequenceModel.setCreatedAt(new Timestamp(new java.util.Date().getTime()));
        hdbSequenceModel.setHanaVersion(hanaVersion);
    }
}
