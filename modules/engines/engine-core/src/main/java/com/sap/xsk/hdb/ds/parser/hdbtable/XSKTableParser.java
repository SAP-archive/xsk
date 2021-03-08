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
package com.sap.xsk.hdb.ds.parser.hdbtable;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import com.google.gson.Gson;
import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableColumnModel;
import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableConstraintModel;
import com.sap.xsk.parser.hdbtable.core.HdbtableLexer;
import com.sap.xsk.parser.hdbtable.core.HdbtableParser;
import com.sap.xsk.parser.hdbtable.custom.XSKHDBTABLECoreVisitor;
import com.sap.xsk.parser.hdbtable.custom.XSKHDBTABLESyntaxErrorListener;
import com.sap.xsk.parser.hdbtable.model.XSKHDBTABLEColumnsModel;
import com.sap.xsk.parser.hdbtable.model.XSKHDBTABLEIndexesModel;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sap.xsk.parser.hdbtable.model.XSKHDBTABLEDefinitionModel;
import com.sap.xsk.hdb.ds.api.IXSKDataStructureModel;
import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableModel;
import com.sap.xsk.hdb.ds.parser.XSKDataStructureParser;

public class XSKTableParser implements XSKDataStructureParser<XSKDataStructureHDBTableModel> {
	
	private static final Logger logger = LoggerFactory.getLogger(XSKTableParser.class);

    @Override
    public String getType() {
        return IXSKDataStructureModel.TYPE_HDB_TABLE;
    }

    @Override
    public Class<XSKDataStructureHDBTableModel> getDataStructureClass() {
        return XSKDataStructureHDBTableModel.class;
    }

    @Override
    public XSKDataStructureHDBTableModel parse(String location, String content) throws XSKDataStructuresException, IOException {
        ByteArrayInputStream is = new ByteArrayInputStream(content.getBytes());
        ANTLRInputStream inputStream = new ANTLRInputStream(is);
        HdbtableLexer hdbtableLexer = new HdbtableLexer(inputStream);
        CommonTokenStream tokenStream = new CommonTokenStream(hdbtableLexer);

        HdbtableParser hdbtableParser = new HdbtableParser(tokenStream);
        hdbtableParser.setBuildParseTree(true);
        hdbtableParser.removeErrorListeners();

        XSKHDBTABLESyntaxErrorListener xskhdbtableSyntaxErrorListener = new XSKHDBTABLESyntaxErrorListener();
        hdbtableParser.addErrorListener(xskhdbtableSyntaxErrorListener);
        ParseTree parseTree = hdbtableParser.hdbtableDefinition();

        if (hdbtableParser.getNumberOfSyntaxErrors() > 0) {
            String errorMessage = xskhdbtableSyntaxErrorListener.getErrorMessage();
            throw new XSKDataStructuresException(errorMessage);
        }

        XSKHDBTABLECoreVisitor xskhdbtableCoreVisitor = new XSKHDBTABLECoreVisitor();

        xskhdbtableCoreVisitor.visit(parseTree);

        Gson gson = new Gson();

        XSKHDBTABLEDefinitionModel hdbtableDefinitionModel = gson.fromJson(xskhdbtableCoreVisitor.getHdbtableDefinitionObject(), XSKHDBTABLEDefinitionModel.class);
        XSKHDBTABLEColumnsModel hdbtableColumnsModel = gson.fromJson(xskhdbtableCoreVisitor.getColumnsObject(), XSKHDBTABLEColumnsModel.class);
        XSKHDBTABLEIndexesModel hdbtableIndexesModel = gson.fromJson(xskhdbtableCoreVisitor.getIndexesObject(), XSKHDBTABLEIndexesModel.class);

        XSKDataStructureHDBTableModel dataStructureHDBTableModel = new XSKDataStructureHDBTableModel();
        XSKDataStructureHDBTableColumnModel xskDataStructureHDBTableColumnModel = new XSKDataStructureHDBTableColumnModel();
        XSKDataStructureHDBTableConstraintModel xskDataStructureHDBTableConstraintModel = new XSKDataStructureHDBTableConstraintModel();

        dataStructureHDBTableModel.setTableType(hdbtableDefinitionModel.getTableType().toString());
        dataStructureHDBTableModel.setSchema(hdbtableDefinitionModel.getSchemaName().toString());
        dataStructureHDBTableModel.setDescription(hdbtableDefinitionModel.getDescription().toString());


        return dataStructureHDBTableModel;
    }
}
