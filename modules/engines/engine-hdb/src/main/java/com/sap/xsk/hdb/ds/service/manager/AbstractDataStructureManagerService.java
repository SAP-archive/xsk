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

import java.util.List;
import java.util.stream.Collectors;

import com.sap.xsk.hdb.ds.api.IXSKDataStructuresCoreService;
import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdb.ds.model.XSKDataStructureModel;
import com.sap.xsk.hdb.ds.service.XSKDataStructuresCoreService;

public abstract class AbstractDataStructureManagerService<T extends XSKDataStructureModel> implements IXSKDataStructureManager<T> {

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
}
