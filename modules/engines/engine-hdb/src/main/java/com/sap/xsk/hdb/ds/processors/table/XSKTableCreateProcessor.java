/*
 * Copyright (c) 2019-2021 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2019-2021 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.hdb.ds.processors.table;

import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableModel;
import com.sap.xsk.hdb.ds.processors.AbstractXSKProcessor;
import com.sap.xsk.utils.XSKConstants;
import com.sap.xsk.utils.XSKHDBUtils;
import java.sql.Connection;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Table Create Processor.
 */
public class XSKTableCreateProcessor extends AbstractXSKProcessor<XSKDataStructureHDBTableModel> {

  private static final Logger logger = LoggerFactory.getLogger(XSKTableCreateProcessor.class);

  /**
   * Execute the corresponding statement.
   *
   * @param connection the connection
   * @param tableModel the table model
   * @throws SQLException the SQL exception
   */
  public void execute(Connection connection, XSKDataStructureHDBTableModel tableModel) throws SQLException {
    /**
     * @see <a href="https://github.com/SAP/xsk/wiki/Parser-hdbtable">hdbtable against postgresql itest</a>
     */

    String sql = null;
    String tableName = XSKHDBUtils.escapeArtifactName(connection, tableModel.getName());
    logger.info("Processing Create Table: " + tableName);

    XSKTableEscapeService escapeService = new XSKTableEscapeService(connection, tableModel);

    switch (tableModel.getHanaVersion()) {
      case VERSION_1: {
        sql = escapeService.getDatabaseSpecificSQL();
        break;
      }
      case VERSION_2: {
        sql = XSKConstants.XSK_HDBTABLE_CREATE + tableModel.getRawContent();
        break;
      }
    }
    executeSql(sql, connection);
  }
}
