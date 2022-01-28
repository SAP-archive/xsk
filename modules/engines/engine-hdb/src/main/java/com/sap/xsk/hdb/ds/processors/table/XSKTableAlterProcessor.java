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
package com.sap.xsk.hdb.ds.processors.table;

import java.sql.Connection;
import java.sql.SQLException;

import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableModel;
import com.sap.xsk.hdb.ds.processors.AbstractXSKProcessor;
import com.sap.xsk.hdb.ds.processors.table.utils.XSKTableAlterHandler;

public class XSKTableAlterProcessor extends AbstractXSKProcessor<XSKDataStructureHDBTableModel> {

  /**
   * Execute the corresponding statement.
   *
   * @param connection the connection
   * @param tableModel the table model
   * @throws SQLException the SQL exception
   */
  @Override
  public void execute(Connection connection, XSKDataStructureHDBTableModel tableModel) throws SQLException {
    XSKTableAlterHandler handler = createTableAlterHandler(connection, tableModel);
    handler.addColumns(connection);
    handler.removeColumns(connection);
    handler.updateColumns(connection);
    handler.rebuildIndeces(connection);
    handler.ensurePrimaryKeyIsUnchanged(connection);
  }

  public XSKTableAlterHandler createTableAlterHandler(Connection connection, XSKDataStructureHDBTableModel tableModel)
      throws SQLException {
    return new XSKTableAlterHandler(connection, tableModel);
  }
}
