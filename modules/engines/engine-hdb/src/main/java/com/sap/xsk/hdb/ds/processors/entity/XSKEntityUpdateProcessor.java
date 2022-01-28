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
package com.sap.xsk.hdb.ds.processors.entity;

import java.sql.Connection;
import java.sql.SQLException;

import org.eclipse.dirigible.database.sql.SqlFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.xsk.hdb.ds.api.IXSKHdbProcessor;
import com.sap.xsk.hdb.ds.model.hdbdd.XSKDataStructureEntityModel;
import com.sap.xsk.hdb.ds.processors.AbstractXSKProcessor;
import com.sap.xsk.utils.XSKHDBUtils;

public class XSKEntityUpdateProcessor extends AbstractXSKProcessor<XSKDataStructureEntityModel> {

  private static final Logger logger = LoggerFactory.getLogger(XSKEntityUpdateProcessor.class);

  private IXSKHdbProcessor<XSKDataStructureEntityModel> xskEntityDropProcessor = new XSKEntityDropProcessor();
  private IXSKHdbProcessor<XSKDataStructureEntityModel> xskEntityCreateProcessor = new XSKEntityCreateProcessor();

  /**
   * Execute the corresponding statement.
   *
   * @param connection  the connection
   * @param entityModel the entity model
   * @throws SQLException the SQL exception
   */
  public void execute(Connection connection, XSKDataStructureEntityModel entityModel)
      throws SQLException {
    String tableName = XSKHDBUtils.escapeArtifactName(connection, XSKHDBUtils.getTableName(entityModel));
    logger.info("Processing Update Entity: {}", tableName);
    if (SqlFactory.getNative(connection).exists(connection, tableName)) {
      if (SqlFactory.getNative(connection).count(connection, tableName) == 0) {
        xskEntityDropProcessor.execute(connection, entityModel);
        xskEntityCreateProcessor.execute(connection, entityModel);
      } else {
        // XSKEntityAlterProcessor.execute(connection, entityModel);
      }
    } else {
      xskEntityCreateProcessor.execute(connection, entityModel);
    }
  }
}
