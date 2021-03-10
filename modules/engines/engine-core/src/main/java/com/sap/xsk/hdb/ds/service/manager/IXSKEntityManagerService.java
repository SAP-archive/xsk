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
import com.sap.xsk.hdb.ds.model.hdbdd.XSKDataStructureEntitiesModel;
import com.sap.xsk.hdb.ds.model.hdbdd.XSKDataStructureEntityModel;
import com.sap.xsk.utils.XSKUtils;
import org.eclipse.dirigible.commons.config.Configuration;
import org.eclipse.dirigible.database.ds.model.IDataStructureModel;
import org.eclipse.dirigible.database.sql.SqlFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.OperationNotSupportedException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

import static java.text.MessageFormat.format;

public class IXSKEntityManagerService extends AbstractDataStructureManagerService<XSKDataStructureEntitiesModel> {
    private static final Logger logger = LoggerFactory.getLogger(IXSKEntityManagerService.class);

    private final Map<String, XSKDataStructureEntitiesModel> dataStructureEntitiesModel;
    private final List<String> entitiesSynchronized;

    @Inject @Named("xskEntityUpdateProcessor")
    private IXSKHdbProcessor xskEntityUpdateProcessor;

    @Inject @Named("xskEntityDropProcessor")
    private IXSKHdbProcessor xskEntityDropProcessor;

    @Inject @Named("xskEntityCreateProcessor")
    private IXSKHdbProcessor xskEntityCreateProcessor;

    public IXSKEntityManagerService() {
        dataStructureEntitiesModel = Collections.synchronizedMap(new LinkedHashMap<>());
        entitiesSynchronized = Collections.synchronizedList(new ArrayList<>());
    }


    @Override
    public void synchronizeRuntimeMetadata(XSKDataStructureEntitiesModel entitiesModel) throws XSKDataStructuresException {
        if (!getDataStructuresCoreService().existsDataStructure(entitiesModel.getLocation(), entitiesModel.getType())) {
            getDataStructuresCoreService()
                    .createDataStructure(entitiesModel.getLocation(), entitiesModel.getName(), entitiesModel.getHash(), entitiesModel.getType());
            dataStructureEntitiesModel.put(entitiesModel.getName(), entitiesModel);
            logger.info("Synchronized a new Entities file [{}] from location: {}", entitiesModel.getName(), entitiesModel.getLocation());
        } else {
            XSKDataStructureEntitiesModel existing = getDataStructuresCoreService().getDataStructure(entitiesModel.getLocation(), entitiesModel.getType());
            if (!entitiesModel.equals(existing)) {
                getDataStructuresCoreService()
                        .updateDataStructure(entitiesModel.getLocation(), entitiesModel.getName(), entitiesModel.getHash(), entitiesModel.getType());
                dataStructureEntitiesModel.put(entitiesModel.getName(), entitiesModel);
                logger.info("Synchronized a modified Entities file [{}] from location: {}", entitiesModel.getName(), entitiesModel.getLocation());
            }
        }
        if (!entitiesSynchronized.contains(entitiesModel.getLocation())) {
            entitiesSynchronized.add(entitiesModel.getLocation());
        }
    }

    @Override
    public void createDataStructure(Connection connection, XSKDataStructureEntitiesModel entitiesModel) throws SQLException {
        boolean caseSensitive = Boolean.parseBoolean(
                Configuration.get(IDataStructureModel.DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE, "false"));
        if (entitiesModel != null) {
            for (XSKDataStructureEntityModel entityModel : entitiesModel.getContext().getЕntities()) {
                String tableName = XSKUtils.getTableName(entityModel);
                if (caseSensitive) {
                    tableName = "\"" + tableName + "\"";
                }
                if (!SqlFactory.getNative(connection).exists(connection, tableName)) {
                    this.xskEntityCreateProcessor.execute(connection, entityModel);
                } else {
                    this.xskEntityUpdateProcessor.execute(connection, entityModel);
                }
            }
        }

    }

    @Override
    public void dropDataStructure(Connection connection, XSKDataStructureEntitiesModel entitiesModel) throws SQLException {
        boolean caseSensitive = Boolean.parseBoolean(
                Configuration.get(IDataStructureModel.DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE, "false"));
        if (entitiesModel != null) {
            for (XSKDataStructureEntityModel entityModel : entitiesModel.getContext().getЕntities()) {
                String tableName = XSKUtils.getTableName(entityModel);
                if (caseSensitive) {
                    tableName = "\"" + tableName + "\"";
                }
                if (SqlFactory.getNative(connection).exists(connection, tableName)) {
                    if (SqlFactory.getNative(connection).count(connection, tableName) == 0) {
                        xskEntityDropProcessor.execute(connection, entityModel);
                    } else {
                        logger.warn(format("Entity [{0}] cannot be deleted during the update process, because it is not empty", entityModel.getName()));
                    }
                }
            }
        }
    }

    @Override
    public void updateDataStructure(Connection connection, XSKDataStructureEntitiesModel entitiesModel)
            throws SQLException, OperationNotSupportedException {
        boolean caseSensitive = Boolean.parseBoolean(
                Configuration.get(IDataStructureModel.DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE, "false"));
        if (entitiesModel != null) {
            for (XSKDataStructureEntityModel entityModel : entitiesModel.getContext().getЕntities()) {
                String tableName = XSKUtils.getTableName(entityModel);
                if (caseSensitive) {
                    tableName = "\"" + tableName + "\"";
                }
                if (SqlFactory.getNative(connection).exists(connection, tableName)) {
                    if (SqlFactory.getNative(connection).count(connection, tableName) != 0) {
                        this.xskEntityUpdateProcessor.execute(connection, entityModel);
                    }
                }
            }
        }
    }

    @Override
    public Map<String, XSKDataStructureEntitiesModel> getDataStructureModels() {
        return Collections.unmodifiableMap(this.dataStructureEntitiesModel);
    }

    @Override
    public List<String> getDataStructureSynchronized() {
        return Collections.unmodifiableList(this.entitiesSynchronized);
    }

    @Override
    public String getDataStructureType() {
        return IXSKDataStructureModel.FILE_EXTENSION_ENTITIES;
    }

    @Override
    public void clearCache() {
        dataStructureEntitiesModel.clear();
    }
}
