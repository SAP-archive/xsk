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

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.sap.xsk.hdb.ds.api.IXSKHdbProcessor;
import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableModel;
import com.sap.xsk.hdb.ds.processors.AbstractXSKProcessor;
import com.sap.xsk.hdb.ds.processors.table.utils.XSKTableAlterColumnHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.Connection;
import java.sql.SQLException;

public class XSKTableAlterProcessor extends AbstractXSKProcessor<XSKDataStructureHDBTableModel> {

  private static final Logger logger = LoggerFactory.getLogger(XSKTableAlterProcessor.class);

  private static final String INCOMPATIBLE_CHANGE_OF_TABLE = "Incompatible change of table [%s] by adding a column [%s] which is [%s]"; //$NON-NLS-1$

  @Inject
  @Named("xskTableCreateProcessor")
  private IXSKHdbProcessor xskTableCreateProcessor;

  /**
   * Execute the corresponding statement.
   *
   * @param connection the connection
   * @param tableModel the table model
   * @throws SQLException the SQL exception
   */
  @Override
  public void execute(Connection connection, XSKDataStructureHDBTableModel tableModel) throws SQLException {
    XSKTableAlterColumnHandler handler = new XSKTableAlterColumnHandler(connection, tableModel);
    handler.addColumns(connection, tableModel);
    handler.removeColumns(connection);

  }


}
