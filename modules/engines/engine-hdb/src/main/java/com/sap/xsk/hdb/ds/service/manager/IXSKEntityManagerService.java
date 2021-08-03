/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2021 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.hdb.ds.service.manager;

import static java.text.MessageFormat.format;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.naming.OperationNotSupportedException;

import org.eclipse.dirigible.database.sql.SqlFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.xsk.hdb.ds.api.IXSKDataStructureModel;
import com.sap.xsk.hdb.ds.api.IXSKHdbProcessor;
import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdb.ds.model.hdbdd.XSKDataStructureEntitiesModel;
import com.sap.xsk.hdb.ds.model.hdbdd.XSKDataStructureEntityModel;
import com.sap.xsk.hdb.ds.processors.entity.XSKEntityCreateProcessor;
import com.sap.xsk.hdb.ds.processors.entity.XSKEntityDropProcessor;
import com.sap.xsk.hdb.ds.processors.entity.XSKEntityUpdateProcessor;
import com.sap.xsk.utils.XSKHDBUtils;

public class IXSKEntityManagerService extends AbstractDataStructureManagerService<XSKDataStructureEntitiesModel> {

  private static final Logger logger = LoggerFactory.getLogger(IXSKEntityManagerService.class);

  private final Map<String, XSKDataStructureEntitiesModel> dataStructureEntitiesModel;
  private final List<String> entitiesSynchronized;

  private IXSKHdbProcessor xskEntityUpdateProcessor = new XSKEntityUpdateProcessor();

  private IXSKHdbProcessor xskEntityDropProcessor = new XSKEntityDropProcessor();

  private IXSKHdbProcessor xskEntityCreateProcessor = new XSKEntityCreateProcessor();

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
      XSKDataStructureEntitiesModel existing = getDataStructuresCoreService()
          .getDataStructure(entitiesModel.getLocation(), entitiesModel.getType());
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
    if (entitiesModel != null) {
      for (XSKDataStructureEntityModel entityModel : entitiesModel.getContext().getЕntities()) {
        String tableName = XSKHDBUtils.escapeArtifactName(connection, XSKHDBUtils.getTableName(entityModel));
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
    if (entitiesModel != null) {
      for (XSKDataStructureEntityModel entityModel : entitiesModel.getContext().getЕntities()) {
        String tableName = XSKHDBUtils.escapeArtifactName(connection, XSKHDBUtils.getTableName(entityModel));
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
    if (entitiesModel != null) {
      for (XSKDataStructureEntityModel entityModel : entitiesModel.getContext().getЕntities()) {
        String tableName = XSKHDBUtils.escapeArtifactName(connection, XSKHDBUtils.getTableName(entityModel));
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
