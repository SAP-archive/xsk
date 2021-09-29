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
package com.sap.xsk.hdb.ds.itest.utils;

import com.sap.xsk.utils.XSKConstants;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.sql.DataSource;
import org.eclipse.dirigible.commons.config.Configuration;
import org.eclipse.dirigible.database.sql.ISqlKeywords;

public class HanaITestUtils {

  public static boolean checkExistOfSynonym(Connection connection, String synonymName) throws SQLException {
    DatabaseMetaData metaData = connection.getMetaData();
    ResultSet synonym = metaData.getTables(null, XSKConstants.XSK_SYNONYM_PUBLIC_SCHEMA, synonymName,
        new String[]{ISqlKeywords.KEYWORD_SYNONYM});
    return synonym.next();
  }

  public static boolean checkExistOfTable(Connection connection, String tableName, String tableSchema) throws SQLException {
    DatabaseMetaData metaData = connection.getMetaData();
    ResultSet table = metaData.getTables(null, tableSchema, tableName, new String[]{ISqlKeywords.KEYWORD_TABLE});
    return table.next();

  }

  public static void dropTable(Connection connection, Statement stmt, String tableName, String tableSchema) throws SQLException {
    if (checkExistOfSynonym(connection, tableName)) {
      stmt.executeUpdate(
          String.format("drop SYNONYM \"%s\".\"%s\"", XSKConstants.XSK_SYNONYM_PUBLIC_SCHEMA, tableName));
    }
    if (checkExistOfTable(connection, tableName, tableSchema)) {
      stmt.executeUpdate(String.format("drop TABLE  \"%s\".\"%s\"", tableSchema, tableName));
    }
  }

  public static void dropSchema(Statement stmt, String schemaName) throws SQLException {
    stmt.executeUpdate(String.format("DROP SCHEMA \"%s\" CASCADE", schemaName));
  }

  public static void createSchema(Statement stmt, String schemaName) throws SQLException {
    stmt.executeUpdate(String.format("CREATE SCHEMA \"%s\"", schemaName));
  }

  public static void clearDataFromXSKDataStructure(DataSource datasource, List<String> resources) throws SQLException {
    try (Connection connection = datasource.getConnection();
        Statement stmt = connection.createStatement()) {
      DatabaseMetaData metaData = connection.getMetaData();
      String hanaUserName = Configuration.get("hana.username");
      ResultSet table = metaData.getTables(null, hanaUserName, "XSK_DATA_STRUCTURES", null);
      if (table.next()) {
        stmt.executeUpdate(String
            .format("DELETE FROM \"%s\".\"XSK_DATA_STRUCTURES\" WHERE DS_LOCATION IN " + "(" + String.join(",", resources) + ")",
                hanaUserName));
      }
    }
  }

}
