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
package com.sap.xsk.hdb.ds.parser.hdbprocedure;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Timestamp;

import com.sap.xsk.exceptions.XSKArtifactParserException;
import com.sap.xsk.hdb.ds.artefacts.HDBProcedureSynchronizationArtefactType;
import com.sap.xsk.hdb.ds.model.XSKDataStructureParametersModel;
import com.sap.xsk.hdb.ds.model.hdbprocedure.XSKDataStructureHDBProcedureModel;
import com.sap.xsk.hdb.ds.synchronizer.XSKDataStructuresSynchronizer;
import com.sap.xsk.parser.hana.core.HanaLexer;
import com.sap.xsk.parser.hana.core.HanaParser;
import com.sap.xsk.utils.XSKCommonsConstants;
import com.sap.xsk.utils.XSKCommonsUtils;
import custom.HanaProcedureListener;
import custom.HanaTableFunctionListener;
import models.HDBProcedureDefinitionModel;
import models.HDBProcedureMissingPropertyException;
import models.TableFunctionDefinitionModel;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.apache.commons.codec.digest.DigestUtils;
import org.eclipse.dirigible.api.v3.security.UserFacade;

import com.sap.xsk.hdb.ds.api.IXSKDataStructureModel;
import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdb.ds.parser.XSKDataStructureParser;
import org.eclipse.dirigible.core.scheduler.api.ISynchronizerArtefactType;

public class XSKHDBProcedureParser implements XSKDataStructureParser<XSKDataStructureHDBProcedureModel>   {

  private final XSKDataStructuresSynchronizer dataStructuresSynchronizer;
  private final HDBProcedureSynchronizationArtefactType procedureSynchronizationArtefactType;
  private final XSKHDBProcedureLogger procedureLogger;


  public XSKHDBProcedureParser(XSKDataStructuresSynchronizer dataStructuresSynchronizer,
                               HDBProcedureSynchronizationArtefactType procedureSynchronizationArtefactType,
                               XSKHDBProcedureLogger procedureLogger) {
    this.dataStructuresSynchronizer = dataStructuresSynchronizer;
    this.procedureSynchronizationArtefactType = procedureSynchronizationArtefactType;
    this.procedureLogger = procedureLogger;
  }

  @Override
  public XSKDataStructureHDBProcedureModel parse(XSKDataStructureParametersModel parametersModel)
          throws IOException, XSKDataStructuresException, XSKArtifactParserException {

    String location = parametersModel.getLocation();

    CharStream inputStream;
    try (ByteArrayInputStream is = new ByteArrayInputStream(parametersModel.getContent().getBytes())) {
      inputStream = CharStreams.fromStream(is);
    }

    HanaLexer lexer = new HanaLexer(inputStream);
    lexer.removeErrorListeners();
    CommonTokenStream tokenStream = new CommonTokenStream(lexer);

    HanaParser parser = new HanaParser(tokenStream);
    parser.setBuildParseTree(true);
    parser.removeErrorListeners();

    ParseTree parseTree = parser.sql_script();

    HanaProcedureListener listener = new HanaProcedureListener();

    ParseTreeWalker parseTreeWalker = new ParseTreeWalker();
    parseTreeWalker.walk(listener, parseTree);

    HDBProcedureDefinitionModel antlr4Model = listener.getModel();
    validateAntlrModel(antlr4Model, location);

    return createModel(antlr4Model, parametersModel);
  }

  private XSKDataStructureHDBProcedureModel createModel(HDBProcedureDefinitionModel antlrModel,
                                                            XSKDataStructureParametersModel params) {

    XSKDataStructureHDBProcedureModel model = new XSKDataStructureHDBProcedureModel();
    model.setSchema(antlrModel.getSchema());
    model.setName(antlrModel.getName());
    model.setLocation(params.getLocation());
    model.setType(getType());
    model.setHash(DigestUtils.md5Hex(params.getContent())); //NOSONAR
    model.setCreatedBy(UserFacade.getName());
    model.setCreatedAt(new Timestamp(new java.util.Date().getTime()));
    model.setContent(params.getContent());
    model.setRawContent(params.getContent());

    return model;
  }

  private void validateAntlrModel(HDBProcedureDefinitionModel antlrModel, String location) throws XSKDataStructuresException {
    try {
      antlrModel.checkForAllMandatoryFieldsPresence();
    } catch (HDBProcedureMissingPropertyException e) {
      procedureLogger.logError(location, XSKCommonsConstants.EXPECTED_FIELDS, e.getMessage());
      dataStructuresSynchronizer.applyArtefactState(XSKCommonsUtils.getRepositoryBaseObjectName(location),
              location,
              procedureSynchronizationArtefactType,
              ISynchronizerArtefactType.ArtefactState.FAILED_CREATE,
              e.getMessage());

      throw new XSKDataStructuresException("Wrong format of HDB Table Function: " + location + " during parsing. ", e);
    }
  }

  @Override
  public String getType() {
    return IXSKDataStructureModel.TYPE_HDB_PROCEDURE;
  }

  @Override
  public Class<XSKDataStructureHDBProcedureModel> getDataStructureClass() {
    return XSKDataStructureHDBProcedureModel.class;
  }
}
