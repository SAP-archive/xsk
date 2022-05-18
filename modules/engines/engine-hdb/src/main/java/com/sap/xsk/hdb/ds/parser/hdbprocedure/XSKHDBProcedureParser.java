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
import com.sap.xsk.hdb.ds.model.XSKDataStructureParametersModel;
import com.sap.xsk.hdb.ds.model.hdbprocedure.XSKDataStructureHDBProcedureModel;
import com.sap.xsk.hdb.ds.parser.XSKDataStructureParser;
import com.sap.xsk.hdb.ds.synchronizer.XSKDataStructuresSynchronizer;
import com.sap.xsk.utils.XSKCommonsConstants;
import com.sap.xsk.utils.XSKCommonsUtils;
import com.sap.xsk.utils.XSKHDBUtils;
import custom.HanaProcedureListener;
import models.HDBProcedureDefinitionModel;
import models.HDBProcedureMissingPropertyException;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.apache.commons.codec.digest.DigestUtils;
import org.eclipse.dirigible.api.v3.security.UserFacade;
import org.eclipse.dirigible.core.scheduler.api.ISynchronizerArtefactType;

import java.io.IOException;
import java.sql.Timestamp;

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
            throws IOException, XSKDataStructuresException, XSKArtifactParserException {

        String location = parametersModel.getLocation();

        ParseTree parseTree = XSKHDBUtils.getParsedThree(parametersModel);

        HanaProcedureListener listener = new HanaProcedureListener();

        ParseTreeWalker parseTreeWalker = new ParseTreeWalker();
        parseTreeWalker.walk(listener, parseTree);

        HDBProcedureDefinitionModel antlr4Model = listener.getModel();
        validateAntlrModel(antlr4Model, location);

        return createModel(antlr4Model, parametersModel);
    }

    private XSKDataStructureHDBProcedureModel createModel(HDBProcedureDefinitionModel antlrModel,
                                                          XSKDataStructureParametersModel params) {
        XSKDataStructureHDBProcedureModel hdbProcedureModel = new XSKDataStructureHDBProcedureModel();
        hdbProcedureModel.setSchema(antlrModel.getSchema());
        hdbProcedureModel.setName(antlrModel.getName());
        hdbProcedureModel.setLocation(params.getLocation());
        hdbProcedureModel.setType(getType());
        hdbProcedureModel.setHash(DigestUtils.md5Hex(params.getContent())); //NOSONAR
        hdbProcedureModel.setCreatedBy(UserFacade.getName());
        hdbProcedureModel.setCreatedAt(new Timestamp(new java.util.Date().getTime()));
        hdbProcedureModel.setContent(params.getContent());
        hdbProcedureModel.setRawContent(params.getContent());
        return hdbProcedureModel;
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
