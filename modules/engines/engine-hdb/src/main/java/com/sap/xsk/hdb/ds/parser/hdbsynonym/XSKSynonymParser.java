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
package com.sap.xsk.hdb.ds.parser.hdbsynonym;

import com.sap.xsk.hdb.ds.api.IXSKDataStructureModel;
import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdb.ds.model.XSKDBContentType;
import com.sap.xsk.hdb.ds.model.hdbsynonym.XSKDataStructureHDBSynonymModel;
import com.sap.xsk.hdb.ds.parser.XSKDataStructureParser;
import com.sap.xsk.parser.hdbsynonym.core.HdbsynonymLexer;
import com.sap.xsk.parser.hdbsynonym.core.HdbsynonymParser;
import com.sap.xsk.parser.hdbsynonym.custom.XSKHDBSYNONYMCoreListener;
import com.sap.xsk.parser.hdbsynonym.custom.XSKHDBSYNONYMSyntaxErrorListener;
import com.sap.xsk.parser.hdbsynonym.models.XSKHDBSYNONYMDefinitionModel;
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

public class XSKSynonymParser implements XSKDataStructureParser {

  @Override
  public XSKDataStructureHDBSynonymModel parse(String location, String content) throws XSKDataStructuresException, IOException {
    XSKDataStructureHDBSynonymModel hdbSynonymModel = new XSKDataStructureHDBSynonymModel();
    XSKHDBUtils.populateXSKDataStructureModel(location, content, hdbSynonymModel, IXSKDataStructureModel.TYPE_HDB_SYNONYM, XSKDBContentType.XS_CLASSIC);

    ByteArrayInputStream is = new ByteArrayInputStream(content.getBytes());
    ANTLRInputStream inputStream = new ANTLRInputStream(is);
    HdbsynonymLexer lexer = new HdbsynonymLexer(inputStream);
    CommonTokenStream tokenStream = new CommonTokenStream(lexer);

    HdbsynonymParser hdbsynonymParser = new HdbsynonymParser(tokenStream);
    hdbsynonymParser.setBuildParseTree(true);
    hdbsynonymParser.removeErrorListeners();

    XSKHDBSYNONYMSyntaxErrorListener errorListener = new XSKHDBSYNONYMSyntaxErrorListener();
    hdbsynonymParser.addErrorListener(errorListener);
    ParseTree parseTree = hdbsynonymParser.hdbsynonymDefinition();

    if (hdbsynonymParser.getNumberOfSyntaxErrors() > 0) {
      String syntaxError = errorListener.getErrorMessage();
      throw new XSKDataStructuresException(String.format(
          "Wrong format of HDB Synonym: [%s] during parsing. Ensure you are using the correct format for the correct compatibility version. [%s]",
          location, syntaxError));
    }
    XSKHDBSYNONYMCoreListener coreListener = new XSKHDBSYNONYMCoreListener();
    ParseTreeWalker parseTreeWalker = new ParseTreeWalker();
    parseTreeWalker.walk(coreListener, parseTree);

    XSKHDBSYNONYMDefinitionModel antlr4Model = coreListener.getModel();
    hdbSynonymModel.setTargetSchema(antlr4Model.getTargetSchema());
    hdbSynonymModel.setTargetObject(antlr4Model.getTargetObject());
    hdbSynonymModel.setSynonymSchema(antlr4Model.getSynonymSchema());

    return hdbSynonymModel;
  }

  @Override
  public String getType() {
    return IXSKDataStructureModel.TYPE_HDB_SYNONYM;
  }

  @Override
  public Class getDataStructureClass() {
    return XSKDataStructureHDBSynonymModel.class;
  }
}
