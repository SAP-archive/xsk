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
package com.sap.xsk.hdb.ds.processors.hdbstructure;

import com.sap.xsk.hdb.ds.service.manager.IXSKDataStructureManager;
import com.sap.xsk.hdb.ds.service.manager.XSKSynonymManagerService;
import com.sap.xsk.utils.XSKHDBUtils;
import java.sql.Connection;
import java.sql.SQLException;

public class HDBSynonymRemover {

  IXSKDataStructureManager synonymManagerService;

  public HDBSynonymRemover(XSKSynonymManagerService synonymManagerService) {
    this.synonymManagerService = synonymManagerService;
  }

  public void removePublicSynonym(Connection connection, String targetObjectSchema, String targetObjectName) throws SQLException {
    XSKHDBUtils.dropPublicSynonymForArtifact(synonymManagerService, targetObjectSchema, targetObjectName,
        connection);
  }

}
