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
package com.sap.xsk.hdb.ds.parser.hdbschema;

import com.sap.xsk.hdb.ds.api.IXSKDataStructureModel;
import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdb.ds.model.hdbschema.XSKDataStructureHDBSchemaModel;
import com.sap.xsk.hdb.ds.parser.XSKDataStructureParser;
import com.sap.xsk.parser.hdbschema.core.HdbschemaLexer;
import com.sap.xsk.parser.hdbschema.core.HdbschemaParser;
import com.sap.xsk.parser.hdbschema.custom.XSKHDBSCHEMACoreListener;
import com.sap.xsk.parser.hdbschema.custom.XSKHDBSCHEMASyntaxErrorListener;
import com.sap.xsk.parser.hdbschema.models.XSKHDBSCHEMADefinitionModel;
import com.sap.xsk.utils.XSKHDBUtils;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.apache.commons.codec.digest.DigestUtils;
import org.eclipse.dirigible.api.v3.security.UserFacade;

public class XSKSchemaParser implements XSKDataStructureParser<XSKDataStructureHDBSchemaModel> {

  @Override
  public String getType() {
    return IXSKDataStructureModel.TYPE_HDB_SCHEMA;
  }

  @Override
  public Class<XSKDataStructureHDBSchemaModel> getDataStructureClass() {
    return XSKDataStructureHDBSchemaModel.class;
  }

  @Override
  public XSKDataStructureHDBSchemaModel parse(String location, String content) throws XSKDataStructuresException, IOException {
    XSKDataStructureHDBSchemaModel hdbSchemaModel = new XSKDataStructureHDBSchemaModel();
    populateXSKDataStructureHDBSchemaModel(location, content, hdbSchemaModel);

    ByteArrayInputStream is = new ByteArrayInputStream(content.getBytes());
    ANTLRInputStream inputStream = new ANTLRInputStream(is);
    HdbschemaLexer hdbschemaLexer = new HdbschemaLexer(inputStream);
    CommonTokenStream tokenStream = new CommonTokenStream(hdbschemaLexer);

    HdbschemaParser hdbschemaParser = new HdbschemaParser(tokenStream);
    hdbschemaParser.setBuildParseTree(true);
    hdbschemaParser.removeErrorListeners();

    XSKHDBSCHEMASyntaxErrorListener xskhdbschemaSyntaxErrorListener = new XSKHDBSCHEMASyntaxErrorListener();
    hdbschemaParser.addErrorListener(xskhdbschemaSyntaxErrorListener);
    ParseTree parseTree = hdbschemaParser.hdbschemaDefinition();

    if (hdbschemaParser.getNumberOfSyntaxErrors() > 0) {
      String syntaxError = xskhdbschemaSyntaxErrorListener.getErrorMessage();
      throw new XSKDataStructuresException(String.format(
          "Wrong format of HDB Schema: [%s] during parsing. Ensure you are using the correct format for the correct compatibility version. [%s]",
          location, syntaxError));
    }

    XSKHDBSCHEMACoreListener XSKHDBSCHEMACoreListener = new XSKHDBSCHEMACoreListener();
    ParseTreeWalker parseTreeWalker = new ParseTreeWalker();
    parseTreeWalker.walk(XSKHDBSCHEMACoreListener, parseTree);

    XSKHDBSCHEMADefinitionModel antlr4Model = XSKHDBSCHEMACoreListener.getModel();
    hdbSchemaModel.setSchema(antlr4Model.getSchemaName());

    return hdbSchemaModel;
  }

  private void populateXSKDataStructureHDBSchemaModel(String location, String content, XSKDataStructureHDBSchemaModel hdbSchemaModel) {
    hdbSchemaModel.setName(XSKHDBUtils.getRepositoryBaseObjectName(location));
    hdbSchemaModel.setLocation(location);
    hdbSchemaModel.setType(IXSKDataStructureModel.TYPE_HDB_SCHEMA);
    hdbSchemaModel.setHash(DigestUtils.md5Hex(content));
    hdbSchemaModel.setCreatedBy(UserFacade.getName());
    hdbSchemaModel.setCreatedAt(new Timestamp(new java.util.Date().getTime()));
  }
}
