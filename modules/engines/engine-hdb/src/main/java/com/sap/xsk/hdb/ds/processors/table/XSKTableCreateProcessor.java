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
package com.sap.xsk.hdb.ds.processors.table;

import com.sap.xsk.hdb.ds.api.IXSKDataStructureModel;
import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableModel;
import com.sap.xsk.hdb.ds.processors.AbstractXSKProcessor;
import com.sap.xsk.hdb.ds.processors.table.utils.XSKTableEscapeService;
import com.sap.xsk.hdb.ds.service.manager.IXSKDataStructureManager;
import com.sap.xsk.utils.XSKConstants;
import com.sap.xsk.utils.XSKHDBUtils;

import org.eclipse.dirigible.commons.config.StaticObjects;
import org.eclipse.dirigible.database.sql.DatabaseArtifactTypes;
import org.eclipse.dirigible.database.sql.ISqlDialect;
import org.eclipse.dirigible.database.sql.SqlFactory;
import org.eclipse.dirigible.database.sql.dialects.hana.HanaSqlDialect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

/**
 * The Table Create Processor.
 */
public class XSKTableCreateProcessor extends AbstractXSKProcessor<XSKDataStructureHDBTableModel> {

  private static final Logger logger = LoggerFactory.getLogger(XSKTableCreateProcessor.class);

    private Map<String, IXSKDataStructureManager> managerServices = (Map<String, IXSKDataStructureManager>) StaticObjects.get("managerServices");

  /**
   * Execute the corresponding statement.
     * The method will create a table and a public synonym in order this table to be accessed from other schemes.
   *
   * @param connection the connection
   * @param tableModel the table model
   * @throws SQLException the SQL exception
   * @see <a href="https://github.com/SAP/xsk/wiki/Parser-hdbtable">hdbtable against postgresql itest</a>
   */
  public void execute(Connection connection, XSKDataStructureHDBTableModel tableModel) throws SQLException {
        logger.info("Processing Create Table: " + tableModel.getName());

    String sql = null;
    String tableNameWithoutSchema = tableModel.getName();
    String tableNameWithSchema = XSKHDBUtils.escapeArtifactName(connection, tableModel.getName(), tableModel.getSchema());

    tableModel.setName(tableNameWithSchema);
    XSKTableEscapeService escapeService = new XSKTableEscapeService(connection, tableModel);

    switch (tableModel.getDBContentType()) {
      case XS_CLASSIC: {
        sql = escapeService.getDatabaseSpecificSQL();
        break;
      }
      case OTHERS: {
        ISqlDialect dialect = SqlFactory.deriveDialect(connection);
        if (dialect.getClass().equals(HanaSqlDialect.class)) {
          sql = XSKConstants.XSK_HDBTABLE_CREATE + tableModel.getRawContent();
          break;
        } else {
          throw new IllegalStateException(String.format("Tables are not supported for %s !", dialect.getDatabaseName(connection)));
        }
      }
    }
    executeSql(sql, connection);

    boolean shouldCreatePublicSynonym = SqlFactory.getNative(connection).exists(connection, tableNameWithSchema, DatabaseArtifactTypes.TABLE);
    if (shouldCreatePublicSynonym) {
        XSKHDBUtils.createPublicSynonymForArtifact(managerServices
                .get(IXSKDataStructureModel.TYPE_HDB_SYNONYM), tableNameWithoutSchema, tableModel.getSchema(), connection);
    }
  }
}
