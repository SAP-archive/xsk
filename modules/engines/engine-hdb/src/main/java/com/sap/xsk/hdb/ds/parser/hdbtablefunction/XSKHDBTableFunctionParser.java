/*
 * Copyright (c) 2022 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2022 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.hdb.ds.parser.hdbtablefunction;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Timestamp;

import com.sap.xsk.exceptions.XSKArtifactParserException;
import com.sap.xsk.hdb.ds.artefacts.HDBTableFunctionSynchronizationArtefactType;
import com.sap.xsk.hdb.ds.model.XSKDataStructureParametersModel;
import com.sap.xsk.hdb.ds.synchronizer.XSKDataStructuresSynchronizer;
import com.sap.xsk.parser.hana.core.HanaLexer;
import com.sap.xsk.parser.hana.core.HanaParser;
import com.sap.xsk.utils.XSKCommonsConstants;
import com.sap.xsk.utils.XSKCommonsUtils;
import custom.HanaErrorListener;
import custom.HanaTableFunctionListener;
import models.TableFunctionDefinitionModel;
import models.TableFunctionMissingPropertyException;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.apache.commons.codec.digest.DigestUtils;
import org.eclipse.dirigible.api.v3.security.UserFacade;

import com.sap.xsk.hdb.ds.api.IXSKDataStructureModel;
import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdb.ds.model.hdbtablefunction.XSKDataStructureHDBTableFunctionModel;
import com.sap.xsk.hdb.ds.parser.XSKDataStructureParser;
import org.eclipse.dirigible.core.scheduler.api.ISynchronizerArtefactType.ArtefactState;

public class XSKHDBTableFunctionParser implements XSKDataStructureParser<XSKDataStructureHDBTableFunctionModel> {

  private static final XSKDataStructuresSynchronizer dataStructuresSynchronizer = new XSKDataStructuresSynchronizer();
  private static final HDBTableFunctionSynchronizationArtefactType TABLE_FUNCTION_ARTEFACT = new HDBTableFunctionSynchronizationArtefactType();

  @Override
  public XSKDataStructureHDBTableFunctionModel parse(XSKDataStructureParametersModel parametersModel)
      throws IOException, XSKDataStructuresException, XSKArtifactParserException {

    String location = parametersModel.getLocation();

    CharStream inputStream;
    try (ByteArrayInputStream is = new ByteArrayInputStream(parametersModel.getContent().getBytes())) {
      inputStream = CharStreams.fromStream(is);
    }

    HanaLexer lexer = new HanaLexer(inputStream);
    HanaErrorListener lexerErrorListener = new HanaErrorListener();
    lexer.removeErrorListeners(); // remove the ConsoleErrorListener
    lexer.addErrorListener(lexerErrorListener);
    CommonTokenStream tokenStream = new CommonTokenStream(lexer);

    HanaErrorListener parserErrorListener = new HanaErrorListener();
    HanaParser parser = new HanaParser(tokenStream);
    parser.setBuildParseTree(true);
    parser.removeErrorListeners();
    parser.addErrorListener(parserErrorListener);

    ParseTree parseTree = parser.sql_script();

    HanaTableFunctionListener listener = new HanaTableFunctionListener();
    ParseTreeWalker parseTreeWalker = new ParseTreeWalker();
    parseTreeWalker.walk(listener, parseTree);

    TableFunctionDefinitionModel antlr4Model = listener.getModel();
    validateAntlrModel(antlr4Model, location);

    return createModel(antlr4Model, parametersModel);
  }

  private XSKDataStructureHDBTableFunctionModel createModel(TableFunctionDefinitionModel antlrModel,
      XSKDataStructureParametersModel params) {

    XSKDataStructureHDBTableFunctionModel model = new XSKDataStructureHDBTableFunctionModel();
    model.setSchema(antlrModel.getSchema());
    model.setName(antlrModel.getName());
    model.setLocation(params.getLocation());
    model.setType(getType());
    model.setHash(DigestUtils.md5Hex(params.getContent()));
    model.setCreatedBy(UserFacade.getName());
    model.setCreatedAt(new Timestamp(new java.util.Date().getTime()));
    model.setContent(params.getContent());
    model.setRawContent(params.getContent());

    return model;
  }

  private void validateAntlrModel(TableFunctionDefinitionModel antlrModel, String location) throws XSKDataStructuresException {
    try {
      antlrModel.checkForAllMandatoryFieldsPresence();
    } catch (TableFunctionMissingPropertyException e) {
      XSKCommonsUtils.logCustomErrors(location,
          XSKCommonsConstants.PARSER_ERROR,
          "",
          "",
          e.getMessage(),
          XSKCommonsConstants.EXPECTED_FIELDS,
          XSKCommonsConstants.HDB_VIEW_PARSER,
          XSKCommonsConstants.MODULE_PARSERS,
          XSKCommonsConstants.SOURCE_PUBLISH_REQUEST,
          XSKCommonsConstants.PROGRAM_XSK);

      dataStructuresSynchronizer.applyArtefactState(XSKCommonsUtils.getRepositoryBaseObjectName(location),
          location, TABLE_FUNCTION_ARTEFACT,
          ArtefactState.FAILED_CREATE,
          e.getMessage());

      throw new XSKDataStructuresException("Wrong format of HDB Table Function: " + location + " during parsing. ", e);
    }
  }

  @Override
  public String getType() {
    return IXSKDataStructureModel.TYPE_HDB_TABLE_FUNCTION;
  }

  @Override
  public Class<XSKDataStructureHDBTableFunctionModel> getDataStructureClass() {
    return XSKDataStructureHDBTableFunctionModel.class;
  }
}
