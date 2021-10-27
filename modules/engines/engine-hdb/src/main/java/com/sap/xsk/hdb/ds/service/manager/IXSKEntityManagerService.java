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

import com.sap.xsk.hdb.ds.api.IXSKDataStructureModel;
import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdb.ds.model.hdbdd.XSKDataStructureCdsModel;
import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableModel;
import com.sap.xsk.hdb.ds.processors.table.XSKTableAlterProcessor;
import com.sap.xsk.hdb.ds.processors.table.XSKTableCreateProcessor;
import com.sap.xsk.hdb.ds.processors.table.XSKTableDropProcessor;
import com.sap.xsk.utils.XSKHDBUtils;
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

public class IXSKEntityManagerService extends AbstractDataStructureManagerService<XSKDataStructureCdsModel> {

  private static final Logger logger = LoggerFactory.getLogger(IXSKEntityManagerService.class);

  private final Map<String, XSKDataStructureCdsModel> dataStructureEntitiesModel;
  private final List<String> entitiesSynchronized;

  private XSKTableDropProcessor xskTableDropProcessor = new XSKTableDropProcessor();

  private XSKTableAlterProcessor xskTableAlterProcessor = new XSKTableAlterProcessor();

  private XSKTableCreateProcessor xskTableCreateProcessor = new XSKTableCreateProcessor();

  public IXSKEntityManagerService() {
    dataStructureEntitiesModel = Collections.synchronizedMap(new LinkedHashMap<>());
    entitiesSynchronized = Collections.synchronizedList(new ArrayList<>());
  }

  public boolean isParsed(XSKDataStructureCdsModel entitiesModel, boolean parsedByRoot) throws XSKDataStructuresException {
    XSKDataStructureCdsModel existingModel = getDataStructuresCoreService()
        .getDataStructure(entitiesModel.getLocation(), entitiesModel.getType());
    boolean isEmptyExistingModel = existingModel == null;

    if (isEmptyExistingModel && parsedByRoot) {
      getDataStructuresCoreService()
          .createDataStructure(entitiesModel.getLocation(), entitiesModel.getName(), entitiesModel.getHash(), entitiesModel.getType());
      return true;
    }

    entitiesModel.setCreateDataStructure(isEmptyExistingModel);
    return !isEmptyExistingModel && existingModel.equals(entitiesModel);
  }

  @Override
  public void synchronizeRuntimeMetadata(XSKDataStructureCdsModel entitiesModel) throws XSKDataStructuresException {
    if (entitiesModel.isCreateDataStructure()) {
      getDataStructuresCoreService()
          .createDataStructure(entitiesModel.getLocation(), entitiesModel.getName(), entitiesModel.getHash(), entitiesModel.getType());
      dataStructureEntitiesModel.put(entitiesModel.getName(), entitiesModel);
      logger.info("Synchronized a new Entities file [{}] from location: {}", entitiesModel.getName(), entitiesModel.getLocation());
    } else {
      getDataStructuresCoreService()
          .updateDataStructure(entitiesModel.getLocation(), entitiesModel.getName(), entitiesModel.getHash(), entitiesModel.getType());
      dataStructureEntitiesModel.put(entitiesModel.getName(), entitiesModel);
      logger.info("Synchronized a modified Entities file [{}] from location: {}", entitiesModel.getName(), entitiesModel.getLocation());
    }
    if (!entitiesSynchronized.contains(entitiesModel.getLocation())) {
      entitiesSynchronized.add(entitiesModel.getLocation());
    }
  }

  @Override
  public void createDataStructure(Connection connection, XSKDataStructureCdsModel entitiesModel)
      throws SQLException {
    if (entitiesModel != null) {
      for (XSKDataStructureHDBTableModel entityModel : entitiesModel.getTableModels()) {
        String tableName = XSKHDBUtils.escapeArtifactName(connection, entityModel.getName(), entityModel.getSchema());
        if (!SqlFactory.getNative(connection).exists(connection, tableName)) {
          this.xskTableCreateProcessor.execute(connection, entityModel);
        } else {
          this.xskTableAlterProcessor.execute(connection, entityModel);
        }
      }
    }

  }

  @Override
  public void dropDataStructure(Connection connection, XSKDataStructureCdsModel entitiesModel) throws SQLException {

  }

  @Override
  public void updateDataStructure(Connection connection, XSKDataStructureCdsModel entitiesModel)
      throws SQLException, OperationNotSupportedException {
  }

  @Override
  public Map<String, XSKDataStructureCdsModel> getDataStructureModels() {
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
