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

import com.sap.xsk.exceptions.XSKArtifactParserException;
import com.sap.xsk.hdb.ds.api.IXSKDataStructureModel;
import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdb.ds.artefacts.HDBProcedureSynchronizationArtefactType;
import com.sap.xsk.hdb.ds.model.XSKDataStructureModelBuilder;
import com.sap.xsk.hdb.ds.model.XSKDataStructureParametersModel;
import com.sap.xsk.hdb.ds.model.hdbprocedure.XSKDataStructureHDBProcedureModel;
import com.sap.xsk.hdb.ds.parser.XSKDataStructureParser;
import com.sap.xsk.hdb.ds.synchronizer.XSKDataStructuresSynchronizer;
import com.sap.xsk.utils.XSKCommonsConstants;
import com.sap.xsk.utils.XSKCommonsUtils;
import com.sap.xsk.utils.XSKHDBUtils;
import custom.HanaProcedureListener;
import models.ProcedureDefinitionModel;
import exceptions.ProcedureMissingPropertyException;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.apache.commons.codec.digest.DigestUtils;
import org.eclipse.dirigible.api.v3.security.UserFacade;
import org.eclipse.dirigible.core.scheduler.api.ISynchronizerArtefactType;

public class XSKHDBProcedureParser implements XSKDataStructureParser<XSKDataStructureHDBProcedureModel> {

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
            throws XSKDataStructuresException, XSKArtifactParserException {

        String location = parametersModel.getLocation();

        ParseTree parseTree = XSKHDBUtils.getParsedThree(parametersModel);

        HanaProcedureListener listener = new HanaProcedureListener();

        ParseTreeWalker parseTreeWalker = new ParseTreeWalker();
        parseTreeWalker.walk(listener, parseTree);

        ProcedureDefinitionModel antlr4Model = listener.getModel();
        validateAntlrModel(antlr4Model, location);

        return createModel(antlr4Model, parametersModel);
    }

    private XSKDataStructureHDBProcedureModel createModel(ProcedureDefinitionModel antlrModel,
                                                          XSKDataStructureParametersModel params) {

      XSKDataStructureModelBuilder builder = new XSKDataStructureModelBuilder()
                .withName(antlrModel.getName())
                .withHash(DigestUtils.md5Hex(params.getContent()))//NOSONAR
                .createdAt(XSKHDBUtils.getTimestamp())
                .createdBy(UserFacade.getName())
                .withLocation(params.getLocation())
                .withType(getType())
                .rawContent(params.getContent())
                .withSchema(antlrModel.getSchema());

      return new XSKDataStructureHDBProcedureModel(builder);

    }

    private void validateAntlrModel(ProcedureDefinitionModel antlrModel, String location) throws XSKDataStructuresException {
        try {
            antlrModel.checkForAllMandatoryFieldsPresence();
        } catch (ProcedureMissingPropertyException e) {
            procedureLogger.logError(location, XSKCommonsConstants.EXPECTED_FIELDS, e.getMessage());
            dataStructuresSynchronizer.applyArtefactState(XSKCommonsUtils.getRepositoryBaseObjectName(location),
                    location,
                    procedureSynchronizationArtefactType,
                    ISynchronizerArtefactType.ArtefactState.FAILED_CREATE,
                    e.getMessage());

            throw new XSKDataStructuresException("Wrong format of HDB Procedure: " + location + " during parsing. ", e);
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
