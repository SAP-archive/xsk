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
import com.sap.xsk.hdb.ds.api.IXSKHdbProcessor;
import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdb.ds.model.hdbsequence.XSKDataStructureHDBSequenceModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.OperationNotSupportedException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

public class IXSKHDBSequenceManagerService extends AbstractDataStructureManagerService<XSKDataStructureHDBSequenceModel>{
    private static final Logger logger = LoggerFactory.getLogger(IXSKHDBSequenceManagerService.class);

    private final Map<String, XSKDataStructureHDBSequenceModel> dataStructureSequenceModels;
    private final List<String> sequencesSynchronized;

    @Inject
    @Named("xskHdbSequenceDropProcessor")
    private IXSKHdbProcessor xskHdbSequenceDropProcessor;
    @Inject @Named("xskHdbSequenceCreateProcessor")
    private IXSKHdbProcessor xskHdbSequenceCreateProcessor;
    @Inject @Named("xskHdbSequenceUpdateProcessor")
    private IXSKHdbProcessor xskHdbSequenceUpdateProcessor;

    public IXSKHDBSequenceManagerService() {
        dataStructureSequenceModels = new LinkedHashMap<>();
        sequencesSynchronized = Collections.synchronizedList(new ArrayList<>());
    }

    @Override
    public Map<String, XSKDataStructureHDBSequenceModel> getDataStructureModels() {
        return Collections.unmodifiableMap(this.dataStructureSequenceModels);
    }

    @Override
    public void synchronizeRuntimeMetadata(XSKDataStructureHDBSequenceModel hdbSequenceModel) throws XSKDataStructuresException {
        if (!getDataStructuresCoreService().existsDataStructure(hdbSequenceModel.getLocation(), hdbSequenceModel.getType())) {
            getDataStructuresCoreService().createDataStructure(hdbSequenceModel.getLocation(), hdbSequenceModel.getName(), hdbSequenceModel.getHash(), hdbSequenceModel.getType());
            dataStructureSequenceModels.put(hdbSequenceModel.getName(), hdbSequenceModel);
            logger.info("Synchronized a new Hdbsequence file [{}] from location: {}", hdbSequenceModel.getName(), hdbSequenceModel.getLocation());
        } else {
            XSKDataStructureHDBSequenceModel existing = getDataStructuresCoreService().getDataStructure(hdbSequenceModel.getLocation(), hdbSequenceModel.getType());
            if (!hdbSequenceModel.equals(existing)) {
                getDataStructuresCoreService().updateDataStructure(hdbSequenceModel.getLocation(), hdbSequenceModel.getName(), hdbSequenceModel.getHash(), hdbSequenceModel.getType());
                dataStructureSequenceModels.put(hdbSequenceModel.getName(), hdbSequenceModel);
                logger.info("Synchronized a modified Hdbsequence file [{}] from location: {}", hdbSequenceModel.getName(), hdbSequenceModel.getLocation());
            }
        }
        if (!sequencesSynchronized.contains(hdbSequenceModel.getLocation())) {
            sequencesSynchronized.add(hdbSequenceModel.getLocation());
        }
    }

    @Override
    public void createDataStructure(Connection connection, XSKDataStructureHDBSequenceModel hdbSequenceModel) throws SQLException {
        this.xskHdbSequenceCreateProcessor.execute(connection,hdbSequenceModel);
    }

    @Override
    public void dropDataStructure(Connection connection, XSKDataStructureHDBSequenceModel hdbSequenceModel) throws SQLException {
        this.xskHdbSequenceDropProcessor.execute(connection,hdbSequenceModel);
    }

    @Override
    public void updateDataStructure(Connection connection, XSKDataStructureHDBSequenceModel hdbSequenceModel) throws SQLException, OperationNotSupportedException {
        this.xskHdbSequenceUpdateProcessor.execute(connection, hdbSequenceModel);
    }

    @Override
    public List<String> getDataStructureSynchronized() {
        return Collections.unmodifiableList(this.sequencesSynchronized);
    }

    @Override
    public String getDataStructureType() {
        return IXSKDataStructureModel.FILE_EXTENSION_HDBSEQUENCE;
    }

    @Override
    public void clearCache() {
        dataStructureSequenceModels.clear();
    }
}
