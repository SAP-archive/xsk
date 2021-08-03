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

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.naming.OperationNotSupportedException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.xsk.hdb.ds.api.IXSKDataStructureModel;
import com.sap.xsk.hdb.ds.api.IXSKHdbProcessor;
import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdb.ds.model.hdbschema.XSKDataStructureHDBSchemaModel;
import com.sap.xsk.hdb.ds.processors.hdbschema.HDBSchemaCreateProcessor;
import com.sap.xsk.hdb.ds.processors.hdbschema.HDBSchemaDropProcessor;

public class IXSKSchemaManagerService extends AbstractDataStructureManagerService<XSKDataStructureHDBSchemaModel> {

  private static final Logger logger = LoggerFactory.getLogger(IXSKSchemaManagerService.class);
  private final Map<String, XSKDataStructureHDBSchemaModel> dataStructureSchemasModels;
  private final List<String> schemasSynchronized;
  private IXSKHdbProcessor hdbSchemaCreateProcessor = new HDBSchemaCreateProcessor();
  private IXSKHdbProcessor hdbSchemaDropProcessor = new HDBSchemaDropProcessor();

  public IXSKSchemaManagerService() {
    dataStructureSchemasModels = new LinkedHashMap<>();
    schemasSynchronized = Collections.synchronizedList(new ArrayList<>());
  }

  @Override
  public void synchronizeRuntimeMetadata(XSKDataStructureHDBSchemaModel schemaModel) throws XSKDataStructuresException {
    // TODO: ommit double calling of finding the hdbProcedure by extracting it in
    // variable
    // String schemaNameConcatProcedureName = hdbProcedure.getSchemaName() + "." +
    // hdbProcedure.getName();
    //logger.info("here");
    if (!getDataStructuresCoreService().existsDataStructure(schemaModel.getLocation(), schemaModel.getType())) {
      getDataStructuresCoreService()
          .createDataStructure(schemaModel.getLocation(), schemaModel.getName(), schemaModel.getHash(), schemaModel.getType());
      dataStructureSchemasModels.put(schemaModel.getName(), schemaModel);
      logger.info("Synchronized a new HDB Schema file [{}] from location: {}", schemaModel.getName(), schemaModel.getLocation());
    } else {
      XSKDataStructureHDBSchemaModel existing = getDataStructuresCoreService()
          .getDataStructure(schemaModel.getLocation(), schemaModel.getType());
      if (!schemaModel.equals(existing)) {
        getDataStructuresCoreService()
            .updateDataStructure(schemaModel.getLocation(), schemaModel.getName(), schemaModel.getHash(), schemaModel.getType());
        dataStructureSchemasModels.put(schemaModel.getName(), schemaModel);
        logger.info("Synchronized a modified HDB Schema file [{}] from location: {}", schemaModel.getName(), schemaModel.getLocation());
      }
    }
    if (!schemasSynchronized.contains(schemaModel.getLocation())) {
      schemasSynchronized.add(schemaModel.getLocation());
    }
  }

  @Override
  public void createDataStructure(Connection connection, XSKDataStructureHDBSchemaModel schemaModel) throws SQLException {
    this.hdbSchemaCreateProcessor.execute(connection, schemaModel);
  }

  @Override
  public void dropDataStructure(Connection connection, XSKDataStructureHDBSchemaModel schemaModel) throws SQLException {
    this.hdbSchemaDropProcessor.execute(connection, schemaModel);
  }

  @Override
  public void updateDataStructure(Connection connection, XSKDataStructureHDBSchemaModel schemaModel)
      throws OperationNotSupportedException {
    throw new OperationNotSupportedException();
  }

  @Override
  public List<String> getDataStructureSynchronized() {
    return Collections.unmodifiableList(this.schemasSynchronized);
  }

  @Override
  public String getDataStructureType() {
    return IXSKDataStructureModel.FILE_EXTENSION_HDBSCHEMA;
  }

  @Override
  public void clearCache() {
    dataStructureSchemasModels.clear();
  }

  @Override
  public Map<String, XSKDataStructureHDBSchemaModel> getDataStructureModels() {
    return Collections.unmodifiableMap(this.dataStructureSchemasModels);
  }
}
