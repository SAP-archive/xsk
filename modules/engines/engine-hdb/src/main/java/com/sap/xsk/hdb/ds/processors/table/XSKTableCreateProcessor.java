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
import com.sap.xsk.hdb.ds.artefacts.HDBTableSynchronizationArtefactType;
import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableModel;
import com.sap.xsk.hdb.ds.module.XSKHDBModule;
import com.sap.xsk.hdb.ds.processors.AbstractXSKProcessor;
import com.sap.xsk.hdb.ds.processors.table.utils.XSKTableEscapeService;
import com.sap.xsk.hdb.ds.service.manager.IXSKDataStructureManager;
import com.sap.xsk.hdb.ds.synchronizer.XSKDataStructuresSynchronizer;
import com.sap.xsk.utils.XSKCommonsConstants;
import com.sap.xsk.utils.XSKCommonsUtils;
import com.sap.xsk.utils.XSKConstants;
import com.sap.xsk.utils.XSKHDBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import org.eclipse.dirigible.core.scheduler.api.ISynchronizerArtefactType.ArtefactState;
import org.eclipse.dirigible.database.sql.DatabaseArtifactTypes;
import org.eclipse.dirigible.database.sql.ISqlDialect;
import org.eclipse.dirigible.database.sql.SqlFactory;
import org.eclipse.dirigible.database.sql.dialects.hana.HanaSqlDialect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Table Create Processor.
 */
public class XSKTableCreateProcessor extends AbstractXSKProcessor<XSKDataStructureHDBTableModel> {

  private static final Logger logger = LoggerFactory.getLogger(XSKTableCreateProcessor.class);
  private static final HDBTableSynchronizationArtefactType TABLE_ARTEFACT = new HDBTableSynchronizationArtefactType();
  private static final XSKDataStructuresSynchronizer dataStructuresSynchronizer = new XSKDataStructuresSynchronizer();

  private Map<String, IXSKDataStructureManager> managerServices = XSKHDBModule.getManagerServices();

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

    String sql = null;
    String tableNameWithoutSchema = tableModel.getName();
    String tableNameWithSchema = XSKHDBUtils.escapeArtifactName(connection, tableModel.getName(), tableModel.getSchema());

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
          String errorMessage = String.format("Tables are not supported for %s !", dialect.getDatabaseName(connection));
          XSKCommonsUtils.logProcessorErrors(errorMessage, XSKCommonsConstants.PROCESSOR_ERROR, tableModel.getLocation(), XSKCommonsConstants.HDB_TABLE_PARSER);
          dataStructuresSynchronizer.applyArtefactState(tableModel.getName(), tableModel.getLocation(), TABLE_ARTEFACT, ArtefactState.FAILED_CREATE, errorMessage);
          throw new IllegalStateException(errorMessage);
        }
      }
    }
    try {
      executeSql(sql, connection);
      String message = String.format("Create table %s successfully", tableModel.getName());
      dataStructuresSynchronizer.applyArtefactState(tableModel.getName(), tableModel.getLocation(), TABLE_ARTEFACT, ArtefactState.SUCCESSFUL_CREATE, message);
    } catch (SQLException ex) {
      XSKCommonsUtils.logProcessorErrors(ex.getMessage(), XSKCommonsConstants.PROCESSOR_ERROR, tableModel.getLocation(), XSKCommonsConstants.HDB_TABLE_PARSER);
      String message = String.format("Create table [%s] skipped due to an error: %s", tableModel, ex.getMessage());
      dataStructuresSynchronizer.applyArtefactState(tableModel.getName(), tableModel.getLocation(), TABLE_ARTEFACT, ArtefactState.FAILED_CREATE, message);
    }

    boolean shouldCreatePublicSynonym = SqlFactory.getNative(connection)
        .exists(connection, tableNameWithSchema, DatabaseArtifactTypes.TABLE);
    if (shouldCreatePublicSynonym) {
      XSKHDBUtils.createPublicSynonymForArtifact(managerServices
          .get(IXSKDataStructureModel.TYPE_HDB_SYNONYM), tableNameWithoutSchema, tableModel.getSchema(), connection);
    }
  }

  @Override
  public void executeSql(String sql, Connection connection) throws SQLException {
    String[] queries = sql.split(String.format("((?=%1$s))", "CREATE"));
    String createTableQuery = queries[0];

    try (PreparedStatement statement = connection.prepareStatement(createTableQuery)) {
      logger.info(createTableQuery);
      statement.executeUpdate();
    } catch (SQLException e) {
      logger.error(sql);
      logger.error(e.getMessage(), e);
      throw e;
    }

    for(int i=1;i<queries.length;i++){
      try (PreparedStatement statement= connection.prepareStatement(queries[i])) {
        logger.info(queries[i]);
        statement.executeUpdate();
      } catch (SQLException exception) {
        logger.error(sql);
        logger.error(exception.getMessage(), exception);
        throw exception;
      }
    }
  }
}
