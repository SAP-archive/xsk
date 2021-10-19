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
import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableConstraintForeignKeyModel;
import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableModel;
import com.sap.xsk.hdb.ds.module.XSKHDBModule;
import com.sap.xsk.hdb.ds.processors.AbstractXSKProcessor;
import com.sap.xsk.hdb.ds.service.manager.IXSKDataStructureManager;
import com.sap.xsk.utils.XSKCommonsConstants;
import com.sap.xsk.utils.XSKCommonsUtils;
import com.sap.xsk.utils.XSKConstants;
import com.sap.xsk.utils.XSKHDBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import org.eclipse.dirigible.database.sql.ISqlDialect;
import org.eclipse.dirigible.database.sql.SqlFactory;
import org.eclipse.dirigible.database.sql.dialects.hana.HanaSqlDialect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Table Drop Processor.
 */
public class XSKTableDropProcessor extends AbstractXSKProcessor<XSKDataStructureHDBTableModel> {

  private static final Logger logger = LoggerFactory.getLogger(XSKTableDropProcessor.class);

  private Map<String, IXSKDataStructureManager> managerServices = XSKHDBModule.getManagerServices();

  /**
   * Execute the corresponding statement.
   *
   * @param connection the connection
   * @param tableModel the table model
   * @throws SQLException the SQL exception
   */
  public void execute(Connection connection, XSKDataStructureHDBTableModel tableModel)
      throws SQLException {
    logger.info("Processing Drop Table: " + tableModel.getName());

    String tableName = XSKHDBUtils.escapeArtifactName(connection, tableModel.getName(), tableModel.getSchema());
    String tableNameWithoutSchema = tableModel.getName();

    //Drop public synonym
    XSKHDBUtils.dropPublicSynonymForArtifact(managerServices
        .get(IXSKDataStructureModel.TYPE_HDB_SYNONYM), tableNameWithoutSchema, tableModel.getSchema(), connection);

    if (SqlFactory.getNative(connection).exists(connection, tableName)) {
      String sql = null;
      switch (tableModel.getDBContentType()) {
        case XS_CLASSIC: {
          sql = SqlFactory.getNative(connection).select().column("COUNT(*)").from(tableName)
              .build();
          break;
        }
        case OTHERS: {
          ISqlDialect dialect = SqlFactory.deriveDialect(connection);
          if (dialect.getClass().equals(HanaSqlDialect.class)) {
            sql = XSKConstants.XSK_HDBTABLE_DROP + tableModel.getRawContent();
            //SqlFactory.getNative(connection).drop().table(tableName).build();
            break;
          } else {
            String errorMessage = String.format("Tables are not supported for %s !", dialect.getDatabaseName(connection));
            XSKCommonsUtils.logProcessorErrors(errorMessage, XSKCommonsConstants.PROCESSOR_ERROR, tableModel.getLocation(), XSKCommonsConstants.HDB_TABLE_PARSER);
            throw new IllegalStateException(errorMessage);
          }
        }
      }

      PreparedStatement statement = null;
      try {
        statement = connection.prepareStatement(sql);
        logger.info(sql);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
          int count = resultSet.getInt(1);
          if (count > 0) {
            String errorMessage = String
                .format("Drop operation for the non empty Table %s will not be executed. Delete all the records in the table first.",
                    tableName);
            XSKCommonsUtils.logProcessorErrors(errorMessage, XSKCommonsConstants.PROCESSOR_ERROR, tableModel.getLocation(), XSKCommonsConstants.HDB_TABLE_PARSER);
            logger.error(errorMessage);
            return;
          }
        }
      } catch (SQLException e) {
        XSKCommonsUtils.logProcessorErrors(e.getMessage(), XSKCommonsConstants.PROCESSOR_ERROR, tableModel.getLocation(), XSKCommonsConstants.HDB_TABLE_PARSER);
        logger.error(sql);
        logger.error(e.getMessage(), e);
      } finally {
        if (statement != null) {
          statement.close();
        }
      }

      if (tableModel.getConstraints().getForeignKeys() != null) {
        for (XSKDataStructureHDBTableConstraintForeignKeyModel foreignKeyModel : tableModel.getConstraints().getForeignKeys()) {
          sql = SqlFactory.getNative(connection).drop().constraint(foreignKeyModel.getName()).fromTable(tableName).build();
          executeUpdate(connection, sql, tableModel);
        }
      }

      sql = SqlFactory.getNative(connection).drop().table(tableName).build();
      executeUpdate(connection, sql, tableModel);
    }
  }

  private void executeUpdate(Connection connection, String sql, XSKDataStructureHDBTableModel tableModel)
      throws SQLException {
    PreparedStatement statement = null;
    try {
      statement = connection.prepareStatement(sql);
      logger.info(sql);
      statement.executeUpdate();
    } catch (SQLException e) {
      XSKCommonsUtils.logProcessorErrors(e.getMessage(), XSKCommonsConstants.PROCESSOR_ERROR, tableModel.getLocation(), XSKCommonsConstants.HDB_TABLE_PARSER);
      logger.error(sql);
      logger.error(e.getMessage(), e);
    } finally {
      if (statement != null) {
        statement.close();
      }
    }
  }

}
