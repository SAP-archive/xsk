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

import java.util.List;
import java.util.stream.Collectors;

import com.sap.xsk.hdb.ds.api.IXSKDataStructuresCoreService;
import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdb.ds.model.XSKDataStructureModel;
import com.sap.xsk.hdb.ds.model.hdbdd.XSKDataStructureCdsModel;
import com.sap.xsk.hdb.ds.service.XSKDataStructuresCoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractDataStructureManagerService<T extends XSKDataStructureModel> implements IXSKDataStructureManager<T> {

  private static final Logger logger = LoggerFactory.getLogger(AbstractDataStructureManagerService.class);

  private IXSKDataStructuresCoreService xskDataStructuresCoreService = new XSKDataStructuresCoreService();

  @Override
  public void cleanup() throws XSKDataStructuresException {
    List<String> dtLocations = xskDataStructuresCoreService.getDataStructuresByType(getDataStructureType()).stream()
        .map(XSKDataStructureModel::getLocation)
        .filter(location -> !this.getDataStructureSynchronized().contains(location))
        .collect(Collectors.toList());

    for (String dtLocation :
        dtLocations) {
      xskDataStructuresCoreService.removeDataStructure(dtLocation);
    }
  }

  public IXSKDataStructuresCoreService getDataStructuresCoreService() {
    return xskDataStructuresCoreService;
  }

  public void setDataStructuresCoreService(IXSKDataStructuresCoreService dataStructuresCoreService) {
    this.xskDataStructuresCoreService = dataStructuresCoreService;
  }

  public void synchronizeParsedByRootMetadata(T tableModel) throws XSKDataStructuresException {
    if (!getDataStructuresCoreService().existsDataStructure(tableModel.getLocation(), tableModel.getType())) {
      xskDataStructuresCoreService
          .createDataStructure(tableModel.getLocation(), tableModel.getName(), tableModel.getHash(), tableModel.getType());
      logger.info("Root artifact synchronized a new Entities file [{}] from location: {}", tableModel.getName(), tableModel.getLocation());
    }
  }

  public boolean existsArtifactMetadata(T tableModel) throws XSKDataStructuresException {
    return xskDataStructuresCoreService.existsDataStructureByLocationAndHash(tableModel.getLocation(), tableModel.getHash(), tableModel.getType());
  }
}
