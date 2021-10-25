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
import com.sap.xsk.hdb.ds.api.IXSKHdbProcessor;
import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdb.ds.model.hdbsynonym.XSKDataStructureHDBSynonymModel;
import com.sap.xsk.hdb.ds.processors.synonym.HDBSynonymCreateProcessor;
import com.sap.xsk.hdb.ds.processors.synonym.HDBSynonymDropProcessor;
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

public class IXSKSynonymManagerService extends AbstractDataStructureManagerService<XSKDataStructureHDBSynonymModel> {

  private static final Logger logger = LoggerFactory.getLogger(IXSKSynonymManagerService.class);
  private final Map<String, XSKDataStructureHDBSynonymModel> dataStructureSynonymModels = new LinkedHashMap<>();
  private final List<String> synonymsSynchronized = Collections.synchronizedList(new ArrayList<>());
  private IXSKHdbProcessor xskSynonymCreateProcessor = new HDBSynonymCreateProcessor();
  private IXSKHdbProcessor xskSynonymDropProcessor = new HDBSynonymDropProcessor();

  @Override
  public void synchronizeRuntimeMetadata(XSKDataStructureHDBSynonymModel synonymModel) throws XSKDataStructuresException {
    if (!getDataStructuresCoreService().existsDataStructure(synonymModel.getLocation(), synonymModel.getType())) {
      getDataStructuresCoreService()
          .createDataStructure(synonymModel.getLocation(), synonymModel.getName(), synonymModel.getHash(), synonymModel.getType());
      dataStructureSynonymModels.put(synonymModel.getName(), synonymModel);
      logger.info("Synchronized a new Synonym file [{}] from location: {}", synonymModel.getName(), synonymModel.getLocation());
    } else {
      XSKDataStructureHDBSynonymModel existing = getDataStructuresCoreService()
          .getDataStructure(synonymModel.getLocation(), synonymModel.getType());
      if (!synonymModel.equals(existing)) {
        getDataStructuresCoreService()
            .updateDataStructure(synonymModel.getLocation(), synonymModel.getName(), synonymModel.getHash(), synonymModel.getType());
        dataStructureSynonymModels.put(synonymModel.getName(), synonymModel);
        logger.info("Synchronized a modified Synonym file [{}] from location: {}", synonymModel.getName(), synonymModel.getLocation());
      }
    }
    if (!synonymsSynchronized.contains(synonymModel.getLocation())) {
      synonymsSynchronized.add(synonymModel.getLocation());
    }
  }

  @Override
  public void createDataStructure(Connection connection, XSKDataStructureHDBSynonymModel synonymModel)
      throws SQLException {
    this.xskSynonymCreateProcessor.execute(connection, synonymModel);
  }

  @Override
  public void dropDataStructure(Connection connection, XSKDataStructureHDBSynonymModel synonymModel)
      throws SQLException {
    this.xskSynonymDropProcessor.execute(connection, synonymModel);
  }

  @Override
  public void updateDataStructure(Connection connection, XSKDataStructureHDBSynonymModel synonymModel)
      throws SQLException, OperationNotSupportedException {
    throw new OperationNotSupportedException();
  }

  @Override
  public Map<String, XSKDataStructureHDBSynonymModel> getDataStructureModels() {
    return Collections.unmodifiableMap(this.dataStructureSynonymModels);
  }

  @Override
  public List<String> getDataStructureSynchronized() {
    return Collections.unmodifiableList(this.synonymsSynchronized);
  }

  @Override
  public String getDataStructureType() {
    return IXSKDataStructureModel.FILE_EXTENSION_SYNONYM;
  }

  @Override
  public void clearCache() {
    dataStructureSynonymModels.clear();
  }

  @Override
  public boolean skipParse(XSKDataStructureHDBSynonymModel tableModel, boolean parsedByRoot) throws XSKDataStructuresException {
    return false;
  }
}
