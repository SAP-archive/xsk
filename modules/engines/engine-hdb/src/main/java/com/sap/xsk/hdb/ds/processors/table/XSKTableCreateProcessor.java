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

import com.sap.xsk.hdb.ds.api.IXSKDataStructureModel;
import com.sap.xsk.hdb.ds.artefacts.HDBTableSynchronizationArtefactType;
import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableModel;
import com.sap.xsk.hdb.ds.module.XSKHDBModule;
import com.sap.xsk.hdb.ds.processors.AbstractXSKProcessor;
import com.sap.xsk.hdb.ds.service.manager.IXSKDataStructureManager;
import com.sap.xsk.utils.XSKCommonsConstants;
import com.sap.xsk.utils.XSKCommonsUtils;
import com.sap.xsk.utils.XSKConstants;
import com.sap.xsk.utils.XSKHDBUtils;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import org.eclipse.dirigible.core.scheduler.api.ISynchronizerArtefactType.ArtefactState;
import org.eclipse.dirigible.database.sql.DatabaseArtifactTypes;
import org.eclipse.dirigible.database.sql.SqlFactory;
import org.eclipse.dirigible.database.sql.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Table Create Processor.
 */
public class XSKTableCreateProcessor extends AbstractXSKProcessor<XSKDataStructureHDBTableModel> {

  private static final Logger logger = LoggerFactory.getLogger(XSKTableCreateProcessor.class);
  private static final HDBTableSynchronizationArtefactType TABLE_ARTEFACT = new HDBTableSynchronizationArtefactType();

  private final Map<String, IXSKDataStructureManager> managerServices = XSKHDBModule.getManagerServices();

  /**
   * Execute the corresponding statement.
   * The method will create a table and a public synonym in order this table to be accessed from other schemas.
   *
   * @param connection the connection
   * @param tableModel the table model
   * @throws SQLException the SQL exception
   * @see <a href="https://github.com/SAP/xsk/wiki/Parser-hdbtable">hdbtable against postgresql itest</a>
   */
  public void execute(Connection connection, XSKDataStructureHDBTableModel tableModel)
      throws SQLException {
    logger.info("Processing Create Table: " + tableModel.getName());

    Collection<String> indicesStatements = new ArrayList<>();
    String tableCreateStatement;
    String tableNameWithoutSchema = tableModel.getName();
    String tableNameWithSchema = XSKHDBUtils.escapeArtifactName(tableModel.getName(), tableModel.getSchema());

    switch (tableModel.getDBContentType()) {
      case XS_CLASSIC: {
        Table table = new TableBuilder().build(tableModel);
        tableCreateStatement = table.getCreateTableStatement();
        indicesStatements.addAll(table.getCreateIndicesStatements());
        break;
      }
      case OTHERS: {
        tableCreateStatement = XSKConstants.XSK_HDBTABLE_CREATE + tableModel.getRawContent();
        break;
      }
      default:
        throw new IllegalStateException("Unsupported content type: " + tableModel.getDBContentType());
    }

    processStatements(connection, tableModel, indicesStatements, tableCreateStatement);
    processSynonym(connection, tableModel, tableNameWithoutSchema, tableNameWithSchema);
  }

  private void processSynonym(Connection connection, XSKDataStructureHDBTableModel tableModel, String tableNameWithoutSchema,
      String tableNameWithSchema) throws SQLException {
    boolean shouldCreatePublicSynonym = SqlFactory.getNative(connection)
        .exists(connection, tableNameWithSchema, DatabaseArtifactTypes.TABLE);
    if (shouldCreatePublicSynonym) {
      XSKHDBUtils.createPublicSynonymForArtifact(managerServices
          .get(IXSKDataStructureModel.TYPE_HDB_SYNONYM), tableNameWithoutSchema, tableModel.getSchema(), connection);
    }
  }

  private void processStatements(Connection connection, XSKDataStructureHDBTableModel tableModel, Collection<String> indicesStatements,
      String tableCreateStatement) {
    try {
      executeSql(tableCreateStatement, connection);

      if (!indicesStatements.isEmpty()) {
        executeBatch(indicesStatements, connection);
      }

      String message = String.format("Create table %s successfully", tableModel.getName());
      applyArtefactState(tableModel.getName(), tableModel.getLocation(), TABLE_ARTEFACT, ArtefactState.SUCCESSFUL_CREATE, message);
    } catch (SQLException ex) {
      logger.error("Creation of table failed. Used SQL - create table {}, indices {}", tableCreateStatement,
          String.join("; ", indicesStatements), ex);
      XSKCommonsUtils.logProcessorErrors(ex.getMessage(), XSKCommonsConstants.PROCESSOR_ERROR, tableModel.getLocation(),
          XSKCommonsConstants.HDB_TABLE_PARSER);
      String message = String.format("Create table [%s] failed due to an error: %s", tableModel, ex.getMessage());
      applyArtefactState(tableModel.getName(), tableModel.getLocation(), TABLE_ARTEFACT, ArtefactState.FAILED_CREATE, message);
    }
  }

  private void executeBatch(Collection<String> createStatements, Connection connection) throws SQLException {
    try (Statement statement = connection.createStatement()) {
      for (String createSQL : createStatements) {
        logger.debug("Adding SQL statement to the batch - {}", createSQL);
        statement.addBatch(createSQL);
      }
      statement.executeBatch();
    }
  }
}
