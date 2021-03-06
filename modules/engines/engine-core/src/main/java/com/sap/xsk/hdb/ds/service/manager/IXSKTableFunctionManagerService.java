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
import com.sap.xsk.hdb.ds.model.hdbtablefunction.XSKDataStructureHDBTableFunctionModel;
import com.sap.xsk.hdb.ds.service.XSKDataStructuresCoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.OperationNotSupportedException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

public class IXSKTableFunctionManagerService extends AbstractDataStructureManagerService<XSKDataStructureHDBTableFunctionModel> {
    private static final Logger logger = LoggerFactory.getLogger(IXSKTableFunctionManagerService.class);

    private final Map<String, XSKDataStructureHDBTableFunctionModel> dataStructureTableFunctionsModels;
    private final List<String> tableFunctionsSynchronized;

    @Inject
    @Named("hdbTableFunctionCreateProcessor")
    private IXSKHdbProcessor hdbTableFunctionCreateProcessor;
    @Inject
    @Named("hdbTableFunctionDropProcessor")
    private IXSKHdbProcessor hdbTableFunctionDropProcessor;

    public IXSKTableFunctionManagerService() {
        dataStructureTableFunctionsModels = new LinkedHashMap<>();
        tableFunctionsSynchronized = Collections.synchronizedList(new ArrayList<>());
    }

    @Override
    public void synchronizeRuntimeMetadata(XSKDataStructureHDBTableFunctionModel hdbTableFunctionModel) throws XSKDataStructuresException {
        // TODO: ommit double calling of finding the hdbTableFunction by extracting it in
        // variable
        // String schemaNameConcatTableFunctionName = hdbTableName.getSchemaName() + "." +
        // hdbProcedure.getName();
        if (!getDataStructuresCoreService().existsDataStructure(hdbTableFunctionModel.getLocation(), hdbTableFunctionModel.getType())) {
            getDataStructuresCoreService()
                    .createDataStructure(hdbTableFunctionModel.getLocation(), hdbTableFunctionModel.getName(), hdbTableFunctionModel.getHash(), hdbTableFunctionModel.getType());
            dataStructureTableFunctionsModels.put(hdbTableFunctionModel.getName(), hdbTableFunctionModel);
            logger.info("Synchronized a new HDB Table Function file [{}] from location: {}", hdbTableFunctionModel.getName(), hdbTableFunctionModel.getLocation());
        } else {
            XSKDataStructureHDBTableFunctionModel existing = getDataStructuresCoreService().getDataStructure(hdbTableFunctionModel.getLocation(), hdbTableFunctionModel.getType());
            if (!hdbTableFunctionModel.equals(existing)) {
                getDataStructuresCoreService()
                        .updateDataStructure(hdbTableFunctionModel.getLocation(), hdbTableFunctionModel.getName(), hdbTableFunctionModel.getHash(), hdbTableFunctionModel.getType());
                dataStructureTableFunctionsModels.put(hdbTableFunctionModel.getName(), hdbTableFunctionModel);
                logger.info("Synchronized a modified HDB Table Function file [{}] from location: {}", hdbTableFunctionModel.getName(), hdbTableFunctionModel.getLocation());
            }
        }
        if (!tableFunctionsSynchronized.contains(hdbTableFunctionModel.getLocation())) {
            tableFunctionsSynchronized.add(hdbTableFunctionModel.getLocation());
        }
    }

    @Override
    public void createDataStructure(Connection connection, XSKDataStructureHDBTableFunctionModel hdbTableFunctionModel) throws SQLException {
        this.hdbTableFunctionCreateProcessor.execute(connection, hdbTableFunctionModel);
    }

    @Override
    public void dropDataStructure(Connection connection, XSKDataStructureHDBTableFunctionModel hdbTableFunctionModel) throws SQLException {
        this.hdbTableFunctionDropProcessor.execute(connection, hdbTableFunctionModel);
    }

    @Override
    public void updateDataStructure(Connection connection, XSKDataStructureHDBTableFunctionModel hdbTableFunctionModel) throws SQLException, OperationNotSupportedException {
        throw new OperationNotSupportedException();
    }

    @Override
    public List<String> getDataStructureSynchronized() {
        return Collections.unmodifiableList(this.tableFunctionsSynchronized);
    }

    @Override
    public String getDataStructureType() {
        return IXSKDataStructureModel.FILE_EXTENSION_HDBTABLEFUNCTION;
    }

    @Override
    public void clearCache() {
        dataStructureTableFunctionsModels.clear();
    }

    @Override
    public Map<String, XSKDataStructureHDBTableFunctionModel> getDataStructureModels() {
        return Collections.unmodifiableMap(this.dataStructureTableFunctionsModels);
    }
}
