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
import com.sap.xsk.hdb.ds.model.hdbtablefunction.XSKDataStructureHDBTableFunctionModel;
import com.sap.xsk.hdb.ds.processors.hdbtablefunction.HDBTableFunctionCreateProcessor;
import com.sap.xsk.hdb.ds.processors.hdbtablefunction.HDBTableFunctionDropProcessor;
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

public class XSKTableFunctionManagerService extends AbstractDataStructureManagerService<XSKDataStructureHDBTableFunctionModel> {

  private static final Logger logger = LoggerFactory.getLogger(XSKTableFunctionManagerService.class);

  private final Map<String, XSKDataStructureHDBTableFunctionModel> dataStructureTableFunctionsModels;
  private final List<String> tableFunctionsSynchronized;

  private IXSKHdbProcessor hdbTableFunctionCreateProcessor = new HDBTableFunctionCreateProcessor();
  private IXSKHdbProcessor hdbTableFunctionDropProcessor = new HDBTableFunctionDropProcessor();

  public XSKTableFunctionManagerService() {
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
          .createDataStructure(hdbTableFunctionModel.getLocation(), hdbTableFunctionModel.getName(), hdbTableFunctionModel.getHash(),
              hdbTableFunctionModel.getType());
      dataStructureTableFunctionsModels.put(hdbTableFunctionModel.getName(), hdbTableFunctionModel);
      logger.info("Synchronized a new HDB Table Function file [{}] from location: {}", hdbTableFunctionModel.getName(),
          hdbTableFunctionModel.getLocation());
    } else {
      XSKDataStructureHDBTableFunctionModel existing = getDataStructuresCoreService()
          .getDataStructure(hdbTableFunctionModel.getLocation(), hdbTableFunctionModel.getType());
      if (!hdbTableFunctionModel.equals(existing)) {
        getDataStructuresCoreService()
            .updateDataStructure(hdbTableFunctionModel.getLocation(), hdbTableFunctionModel.getName(), hdbTableFunctionModel.getHash(),
                hdbTableFunctionModel.getType());
        dataStructureTableFunctionsModels.put(hdbTableFunctionModel.getName(), hdbTableFunctionModel);
        logger.info("Synchronized a modified HDB Table Function file [{}] from location: {}", hdbTableFunctionModel.getName(),
            hdbTableFunctionModel.getLocation());
      }
    }
    if (!tableFunctionsSynchronized.contains(hdbTableFunctionModel.getLocation())) {
      tableFunctionsSynchronized.add(hdbTableFunctionModel.getLocation());
    }
  }

  @Override
  public boolean createDataStructure(Connection connection, XSKDataStructureHDBTableFunctionModel hdbTableFunctionModel)
      throws SQLException {
	return this.hdbTableFunctionCreateProcessor.execute(connection, hdbTableFunctionModel);
  }

  @Override
  public boolean dropDataStructure(Connection connection, XSKDataStructureHDBTableFunctionModel hdbTableFunctionModel)
      throws SQLException {
	return this.hdbTableFunctionDropProcessor.execute(connection, hdbTableFunctionModel);
  }

  @Override
  public boolean updateDataStructure(Connection connection, XSKDataStructureHDBTableFunctionModel hdbTableFunctionModel)
      throws SQLException, OperationNotSupportedException {
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
