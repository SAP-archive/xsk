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
package com.sap.xsk.hdb.ds.service.manager;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.sap.xsk.hdb.ds.api.IXSKDataStructureModel;
import com.sap.xsk.hdb.ds.api.IXSKDataStructuresCoreService;
import com.sap.xsk.hdb.ds.api.IXSKHdbProcessor;
import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdb.ds.model.hdbprocedure.XSKDataStructureHDBProcedureModel;
import com.sap.xsk.hdb.ds.service.XSKDataStructuresCoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.OperationNotSupportedException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

public class IXSKProceduresManagerService extends AbstractDataStructureManagerService<XSKDataStructureHDBProcedureModel> {

    private static final Logger logger = LoggerFactory.getLogger(IXSKProceduresManagerService.class);


    private final Map<String, XSKDataStructureHDBProcedureModel> dataStructureProceduresModels;
    private final List<String> proceduresSynchronized;

    @Inject @Named("hdbProcedureDropProcessor")
    private IXSKHdbProcessor hdbProcedureDropProcessor;
    @Inject @Named("hdbProcedureCreateProcessor")
    private IXSKHdbProcessor hdbProcedureCreateProcessor;

    public IXSKProceduresManagerService() {
        dataStructureProceduresModels = new LinkedHashMap<>();
        proceduresSynchronized = Collections.synchronizedList(new ArrayList<>());
    }

    @Override
    public void synchronizeRuntimeMetadata(XSKDataStructureHDBProcedureModel hdbProcedureModel) throws XSKDataStructuresException {
        // TODO: ommit double calling of finding the hdbProcedure by extracting it in
        // variable
        // String schemaNameConcatProcedureName = hdbProcedure.getSchemaName() + "." +
        // hdbProcedure.getName();
        if (!getDataStructuresCoreService().existsDataStructure(hdbProcedureModel.getLocation(), hdbProcedureModel.getType())) {
            getDataStructuresCoreService().createDataStructure(hdbProcedureModel.getLocation(), hdbProcedureModel.getName(), hdbProcedureModel.getHash(), hdbProcedureModel.getType());
            dataStructureProceduresModels.put(hdbProcedureModel.getName(), hdbProcedureModel);
            logger.info("Synchronized a new HDB Procedure file [{}] from location: {}", hdbProcedureModel.getName(), hdbProcedureModel.getLocation());
        } else {
            XSKDataStructureHDBProcedureModel existing = getDataStructuresCoreService().getDataStructure(hdbProcedureModel.getLocation(), hdbProcedureModel.getType());
            if (!hdbProcedureModel.equals(existing)) {
                getDataStructuresCoreService()
                        .updateDataStructure(hdbProcedureModel.getLocation(), hdbProcedureModel.getName(), hdbProcedureModel.getHash(), hdbProcedureModel.getType());
                dataStructureProceduresModels.put(hdbProcedureModel.getName(), hdbProcedureModel);
                logger.info("Synchronized a modified HDB Procedure file [{}] from location: {}", hdbProcedureModel.getName(), hdbProcedureModel.getLocation());
            }
        }
        if (!proceduresSynchronized.contains(hdbProcedureModel.getLocation())) {
            proceduresSynchronized.add(hdbProcedureModel.getLocation());
        }
    }

    @Override
    public void createDataStructure(Connection connection, XSKDataStructureHDBProcedureModel viewModel) throws SQLException {
        this.hdbProcedureCreateProcessor.execute(connection, viewModel);
    }

    @Override
    public void dropDataStructure(Connection connection, XSKDataStructureHDBProcedureModel viewModel) throws SQLException {
        this.hdbProcedureDropProcessor.execute(connection, viewModel);
    }

    @Override
    public void updateDataStructure(Connection connection, XSKDataStructureHDBProcedureModel viewModel) throws OperationNotSupportedException {
        throw new OperationNotSupportedException();
    }

    @Override
    public List<String> getDataStructureSynchronized() {
        return Collections.unmodifiableList(this.proceduresSynchronized);
    }

    @Override
    public String getDataStructureType() {
        return IXSKDataStructureModel.FILE_EXTENSION_HDBPROCEDURE;
    }

    @Override
    public void clearCache() {
        dataStructureProceduresModels.clear();
    }

    @Override
    public Map<String, XSKDataStructureHDBProcedureModel> getDataStructureModels() {
        return Collections.unmodifiableMap(this.dataStructureProceduresModels);
    }
}
