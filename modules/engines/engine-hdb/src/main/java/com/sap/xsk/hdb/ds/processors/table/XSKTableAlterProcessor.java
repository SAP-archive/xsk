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

import com.sap.xsk.hdb.ds.artefacts.HDBTableSynchronizationArtefactType;
import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableCalculatedColumnModel;
import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableModel;
import com.sap.xsk.hdb.ds.processors.AbstractXSKProcessor;
import com.sap.xsk.utils.XSKCommonsConstants;
import com.sap.xsk.utils.XSKCommonsUtils;
import com.sap.xsk.utils.XSKHDBUtils;
import org.eclipse.dirigible.core.scheduler.api.ISynchronizerArtefactType.ArtefactState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.Connection;
import java.sql.SQLException;

public class XSKTableAlterProcessor extends AbstractXSKProcessor<XSKDataStructureHDBTableModel> {

  private static final Logger logger = LoggerFactory.getLogger(XSKTableAlterProcessor.class);
  private static final HDBTableSynchronizationArtefactType TABLE_ARTEFACT = new HDBTableSynchronizationArtefactType();

  /**
   * Execute the corresponding statement.
   *
   * @param connection the connection
   * @param tableModel the table model
   * @throws SQLException the SQL exception
   */
  @Override
  public boolean execute(Connection connection, XSKDataStructureHDBTableModel tableModel) throws SQLException {

    for(XSKDataStructureHDBTableCalculatedColumnModel columnModel: tableModel.getCalculatedColumns()) {
      String tableName = XSKHDBUtils.escapeArtifactName(tableModel.getName(), tableModel.getSchema());
      String tableAlterStatement = new TableBuilder().buildAlterCalculatedColumns(tableName, columnModel);
      boolean success = processStatement(connection, tableModel, tableAlterStatement);
      if(!success) {
        return false;
      }
    }

    XSKTableAlterHandler handler = createTableAlterHandler(connection, tableModel);
    handler.addColumns(connection);
    handler.removeColumns(connection);
    handler.updateColumns(connection);
    handler.rebuildIndeces(connection);
    handler.ensurePrimaryKeyIsUnchanged(connection);

    return true;
  }

  public XSKTableAlterHandler createTableAlterHandler(Connection connection, XSKDataStructureHDBTableModel tableModel)
      throws SQLException {
    return new XSKTableAlterHandler(connection, tableModel);
  }

  private boolean processStatement(Connection connection, XSKDataStructureHDBTableModel tableModel, String tableCreateStatement) {
    try {
      executeSql(tableCreateStatement, connection);

      String message = String.format("Altered table %s successfully", tableModel.getName());
      applyArtefactState(tableModel.getName(), tableModel.getLocation(), TABLE_ARTEFACT, ArtefactState.SUCCESSFUL_UPDATE, message);
      return true;
    } catch (SQLException ex) {
      logger.error("Alter of table failed. Used SQL - alter table {}", tableCreateStatement, ex);
      XSKCommonsUtils.logProcessorErrors(ex.getMessage(), XSKCommonsConstants.PROCESSOR_ERROR, tableModel.getLocation(),
          XSKCommonsConstants.HDB_TABLE_PARSER);
      String message = String.format("Alter table [%s] failed due to an error: %s", tableModel, ex.getMessage());
      applyArtefactState(tableModel.getName(), tableModel.getLocation(), TABLE_ARTEFACT, ArtefactState.FAILED_UPDATE, message);
      return false;
    }
  }
}
