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
package com.sap.xsk.utils;

import org.eclipse.dirigible.database.persistence.model.PersistenceTableModel;
import org.eclipse.dirigible.database.sql.ISqlKeywords;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class XSKCommonsDBUtils {

    public static String getTableSchema(DataSource dataSource, String tableName) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            ResultSet rs = databaseMetaData.getTables(connection.getCatalog(), null, tableName, new String[]{ISqlKeywords.KEYWORD_TABLE});
            if (rs.next()) {
                return rs.getString("TABLE_SCHEM");
            }
            return null;
        }
    }

  public static PersistenceTableModel getSynonymTargetObjectMetadata(DataSource dataSource, String synonymName, String schemaName) throws SQLException {
    PersistenceTableModel tableMetadata = new PersistenceTableModel();

    try (Connection connection = dataSource.getConnection()) {
      PreparedStatement prepareStatement = connection.prepareStatement("SELECT * FROM \"SYS\".\"SYNONYMS\" WHERE \"SYNONYM_NAME\" = ? AND \"SCHEMA_NAME\" = ?");
      prepareStatement.setString(1, synonymName);
      prepareStatement.setString(2, schemaName);

      try (ResultSet resultSet = prepareStatement.executeQuery()) {
        if (resultSet.next()) {
          tableMetadata.setTableName(resultSet.getString("OBJECT_NAME"));
          tableMetadata.setSchemaName(resultSet.getString("OBJECT_SCHEMA"));
          tableMetadata.setTableType(resultSet.getString("OBJECT_TYPE"));
        }
      }
    }

    return tableMetadata;
  }
}

