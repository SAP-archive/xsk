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
package com.sap.xsk.hdb.ds.service.manager;

import com.sap.xsk.hdb.ds.api.IXSKDataStructureModel;
import com.sap.xsk.hdb.ds.api.IXSKHdbProcessor;
import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdb.ds.model.hdbtabletype.XSKDataStructureHDBTableTypeModel;
import com.sap.xsk.hdb.ds.processors.hdbstructure.HDBSynonymRemover;
import com.sap.xsk.hdb.ds.processors.hdbstructure.XSKTableTypeCreateProcessor;
import com.sap.xsk.hdb.ds.processors.hdbstructure.XSKTableTypeDropProcessor;
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

public class XSKTableTypeManagerService extends AbstractDataStructureManagerService<XSKDataStructureHDBTableTypeModel> {

  private static final Logger logger = LoggerFactory.getLogger(XSKTableTypeManagerService.class);

  private final Map<String, XSKDataStructureHDBTableTypeModel> dataStructureHDBTableTypeModels;
  private final List<String> tableTypesSynchronized;

  private final IXSKHdbProcessor xskTableTypeCreateProcessor;
  private final IXSKHdbProcessor xskTableTypeDropProcessor;

  public XSKTableTypeManagerService(HDBSynonymRemover synonymRemover) {
    this.xskTableTypeCreateProcessor = new XSKTableTypeCreateProcessor();
    this.xskTableTypeDropProcessor = new XSKTableTypeDropProcessor(synonymRemover);
    dataStructureHDBTableTypeModels = new LinkedHashMap<>();
    tableTypesSynchronized = Collections.synchronizedList(new ArrayList<>());
  }

  @Override
  public Map<String, XSKDataStructureHDBTableTypeModel> getDataStructureModels() {
    return dataStructureHDBTableTypeModels;
  }

  @Override
  public void synchronizeRuntimeMetadata(XSKDataStructureHDBTableTypeModel tableTypeModel) throws XSKDataStructuresException {

    if (!getDataStructuresCoreService().existsDataStructure(tableTypeModel.getLocation(), tableTypeModel.getType())) {
      getDataStructuresCoreService()
          .createDataStructure(tableTypeModel.getLocation(), tableTypeModel.getName(), tableTypeModel.getHash(), tableTypeModel.getType());
      dataStructureHDBTableTypeModels.put(tableTypeModel.getName(), tableTypeModel);
      logger.info("Synchronized a new Structure file [{}] from location: {}", tableTypeModel.getName(), tableTypeModel.getLocation());
    } else {
      XSKDataStructureHDBTableTypeModel existing = getDataStructuresCoreService()
          .getDataStructure(tableTypeModel.getLocation(), tableTypeModel.getType());
      if (!tableTypeModel.equals(existing)) {
        getDataStructuresCoreService()
            .updateDataStructure(tableTypeModel.getLocation(), tableTypeModel.getName(), tableTypeModel.getHash(),
                tableTypeModel.getType());
        dataStructureHDBTableTypeModels.put(tableTypeModel.getName(), tableTypeModel);
        logger
            .info("Synchronized a modified Structure file [{}] from location: {}", tableTypeModel.getName(), tableTypeModel.getLocation());
      }
    }
    if (!tableTypesSynchronized.contains(tableTypeModel.getLocation())) {
      tableTypesSynchronized.add(tableTypeModel.getLocation());
    }
  }

  @Override
  public boolean createDataStructure(Connection connection, XSKDataStructureHDBTableTypeModel structureModel)
      throws SQLException {
	return this.xskTableTypeCreateProcessor.execute(connection, structureModel);
  }

  @Override
  public boolean dropDataStructure(Connection connection, XSKDataStructureHDBTableTypeModel tableTypeModel)
      throws SQLException {
	return this.xskTableTypeDropProcessor.execute(connection, tableTypeModel);
  }

  @Override
  public boolean updateDataStructure(Connection connection, XSKDataStructureHDBTableTypeModel tableTypeModel)
      throws SQLException, OperationNotSupportedException {
    throw new OperationNotSupportedException();
  }

  @Override
  public List<String> getDataStructureSynchronized() {
    return Collections.unmodifiableList(this.tableTypesSynchronized);
  }

  @Override
  public String getDataStructureType() {
    return IXSKDataStructureModel.FILE_EXTENSION_STRUCTURE;
  }

  @Override
  public void clearCache() {
    dataStructureHDBTableTypeModels.clear();
  }
}
