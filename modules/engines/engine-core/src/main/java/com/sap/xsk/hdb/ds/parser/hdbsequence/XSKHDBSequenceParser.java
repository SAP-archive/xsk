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
import com.sap.xsk.hdb.ds.model.hdbsequence.XSKDataStructureHDBSequenceModel;
import com.sap.xsk.hdb.ds.parser.XSKDataStructureParser;
import com.sap.xsk.parser.hdbsequence.core.HdbsequenceBaseVisitor;
import com.sap.xsk.parser.hdbsequence.core.HdbsequenceLexer;
import com.sap.xsk.parser.hdbsequence.core.HdbsequenceParser;
import com.sap.xsk.parser.hdbsequence.custom.CustomDeserializers;
import com.sap.xsk.parser.hdbsequence.custom.HdbsequenceVisitor;
import com.sap.xsk.parser.hdbsequence.custom.XSKHDBSEQUENCESyntaxErrorListener;
import com.sap.xsk.parser.hdbsequence.models.XSKHDBSEQUENCEModel;
import com.sap.xsk.utils.XSKUtils;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.apache.commons.codec.digest.DigestUtils;
import org.eclipse.dirigible.api.v3.security.UserFacade;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Timestamp;

public class XSKHDBSequenceParser implements XSKDataStructureParser {

    @Override
    public XSKDataStructureModel parse(String location, String content) throws XSKDataStructuresException, IOException {
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
        hdbSequenceModel.setName(XSKUtils.getRepositoryBaseObjectName(location));
        hdbSequenceModel.setLocation(location);
        hdbSequenceModel.setType(IXSKDataStructureModel.TYPE_HDB_SEQUENCE);
        hdbSequenceModel.setHash(DigestUtils.md5Hex(content));
        hdbSequenceModel.setCreatedBy(UserFacade.getName());
        hdbSequenceModel.setCreatedAt(new Timestamp(new java.util.Date().getTime()));

        return hdbSequenceModel;
    }

    @Override
    public String getType() {
        return IXSKDataStructureModel.TYPE_HDB_SEQUENCE;
    }

    @Override
    public Class<XSKDataStructureHDBSequenceModel> getDataStructureClass() {
        return XSKDataStructureHDBSequenceModel.class;
    }
}
