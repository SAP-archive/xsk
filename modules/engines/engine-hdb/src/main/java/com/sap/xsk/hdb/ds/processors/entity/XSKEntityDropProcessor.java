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
package com.sap.xsk.hdb.ds.processors.entity;

import com.sap.xsk.hdb.ds.model.hdbdd.XSKDataStructureEntityModel;
import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableConstraintForeignKeyModel;
import com.sap.xsk.hdb.ds.processors.AbstractXSKProcessor;
import com.sap.xsk.utils.XSKHDBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.eclipse.dirigible.database.sql.SqlFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.sap.xsk.utils.XSKConstants.SHOULD_ADD_ESCAPE_SYMBOL_DEFAULT_VALUE;

/**
 * The Entity Drop Processor.
 */
public class XSKEntityDropProcessor extends AbstractXSKProcessor<XSKDataStructureEntityModel> {

  private static final Logger logger = LoggerFactory.getLogger(XSKEntityDropProcessor.class);

  /**
   * Execute the corresponding statement.
   *
   * @param connection  the connection
   * @param entityModel the table model
   * @return if delete operation has been performed successfully or the table does not exist
   * @throws SQLException the SQL exception
   */
  public void execute(Connection connection, XSKDataStructureEntityModel entityModel) throws SQLException {
    String tableName = XSKHDBUtils.escapeArtifactName(connection, XSKHDBUtils.getTableName(entityModel),
        SHOULD_ADD_ESCAPE_SYMBOL_DEFAULT_VALUE);
    logger.info("Processing Drop Table: {}", tableName);
    if (SqlFactory.getNative(connection).exists(connection, tableName)) {
      String sql = SqlFactory.getNative(connection).select().column("COUNT(*)").from(tableName)
          .build();

      try (PreparedStatement statement = connection.prepareStatement(sql)) {
        logger.info(sql);
        try (ResultSet resultSet = statement.executeQuery()) {
          if (resultSet.next()) {
            int count = resultSet.getInt(1);
            if (count > 0) {
              logger.error("Drop operation for the non empty Table {} will not be executed. Delete all the records in the table first.",
                  tableName);
            }
          }
        }
      } catch (SQLException e) {
        logger.error(sql);
        logger.error(e.getMessage(), e);
      }

      if (entityModel.getConstraints().getForeignKeys() != null) {
        for (XSKDataStructureHDBTableConstraintForeignKeyModel foreignKey : entityModel.getConstraints().getForeignKeys()) {
          String foreignKeyName = "FK_" + foreignKey.getName();
          String[] fkColumns = foreignKey.getColumns();
          String[] referencedColumns = foreignKey.getReferencedColumns();
          foreignKeyName = XSKHDBUtils.escapeArtifactName(connection, foreignKeyName, SHOULD_ADD_ESCAPE_SYMBOL_DEFAULT_VALUE);
          for (int i = 0; i < fkColumns.length; i++) {
            fkColumns[i] = XSKHDBUtils.escapeArtifactName(connection, fkColumns[i], SHOULD_ADD_ESCAPE_SYMBOL_DEFAULT_VALUE);
          }
          for (int i = 0; i < referencedColumns.length; i++) {
            referencedColumns[i] = XSKHDBUtils.escapeArtifactName(connection, referencedColumns[i], SHOULD_ADD_ESCAPE_SYMBOL_DEFAULT_VALUE);
          }
          sql = SqlFactory.getNative(connection).drop().constraint(foreignKeyName).fromTable(tableName).build();
          executeSql(sql, connection);
        }
      }

      sql = SqlFactory.getNative(connection).drop().table(tableName).build();
      executeSql(sql, connection);
      return;
    }
    logger.warn("Trying to delete non existing Table: {}", tableName);
  }
}
