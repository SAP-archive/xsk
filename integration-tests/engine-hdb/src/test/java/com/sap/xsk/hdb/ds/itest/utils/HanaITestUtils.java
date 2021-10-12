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

import static com.sap.xsk.hdb.ds.itest.utils.TestConstants.HANA_USERNAME;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import org.eclipse.dirigible.database.sql.ISqlKeywords;

import com.sap.xsk.utils.XSKConstants;
import com.sap.xsk.utils.XSKHDBUtils;

public class HanaITestUtils {

  public static boolean checkExistOfPublicSynonym(Connection connection, String synonymName) throws SQLException {
    DatabaseMetaData metaData = connection.getMetaData();
    ResultSet rs = metaData.getTables(null, XSKConstants.XSK_SYNONYM_PUBLIC_SCHEMA, synonymName,
        new String[]{ISqlKeywords.KEYWORD_SYNONYM});
    return rs.next();
  }

  public static boolean checkExistOfSynonym(Connection connection, String synonymName, String synonymSchema) throws SQLException {
    DatabaseMetaData metaData = connection.getMetaData();
    ResultSet rs = metaData.getTables(null, synonymSchema, synonymName,
        new String[]{ISqlKeywords.KEYWORD_SYNONYM});
    return rs.next();
  }

  public static boolean checkExistOfTable(Connection connection, String tableName, String tableSchema) throws SQLException {
    DatabaseMetaData metaData = connection.getMetaData();
    ResultSet rs = metaData.getTables(null, tableSchema, tableName, new String[]{ISqlKeywords.KEYWORD_TABLE});
    return rs.next();
  }

  public static boolean checkExistOfView(Connection connection, String tableName, String tableSchema) throws SQLException {
    DatabaseMetaData metaData = connection.getMetaData();
    ResultSet rs = metaData.getTables(null, tableSchema, tableName, new String[]{ISqlKeywords.KEYWORD_VIEW});
    return rs.next();
  }

  public static boolean checkExistOfSchema(Connection connection, String schemaName) throws SQLException {
    DatabaseMetaData metaData = connection.getMetaData();
    ResultSet rs = metaData.getSchemas(null, schemaName);
    return rs.next();
  }

  public static boolean checkExistOfProcedure(Connection connection, String tableName, String tableSchema) throws SQLException {
    DatabaseMetaData metaData = connection.getMetaData();
    ResultSet rs = metaData.getProcedures(null, tableSchema, tableName);
    return rs.next();
  }

  public static boolean checkExistOfFunction(Statement stmt, String funcName, String funcSchema) throws SQLException {
    ResultSet rs = stmt.executeQuery(
        "SELECT COUNT(*) as rawsCount FROM SYS.OBJECTS WHERE OBJECT_NAME IN ('" + funcName
            + "') AND OBJECT_TYPE = 'FUNCTION' AND SCHEMA_NAME ='" + funcSchema + "'");
    rs.next();
    return rs.getInt("rawsCount") == 1;
  }

  public static boolean checkExistOfSequence(Statement stmt, String seqName, String seqSchema) throws SQLException {
    ResultSet rs = stmt.executeQuery(String
        .format("SELECT COUNT(*) as rawsCount from Sequences WHERE SEQUENCE_NAME = '%s' AND SCHEMA_NAME = '%s'",
            seqName, seqSchema));
    rs.next();
    return rs.getInt("rawsCount") == 1;
  }

  public static boolean checkExistOfTableType(Statement stmt, String tableTypeName, String schemaName) throws SQLException {
    ResultSet tableType = stmt
        .executeQuery(
            String.format(
                "SELECT IS_USER_DEFINED_TYPE FROM SYS.\"TABLES\" WHERE TABLE_NAME like '%s' AND IS_USER_DEFINED_TYPE like 'TRUE' AND SCHEMA_NAME LIKE '%s'",
                tableTypeName, schemaName));
    return tableType.next();
  }

  public static void dropTable(Connection connection, Statement stmt, String tableName, String tableSchema) throws SQLException {
    if (checkExistOfPublicSynonym(connection, tableName)) {
      stmt.executeUpdate(
          String.format("drop SYNONYM \"%s\".\"%s\"", XSKConstants.XSK_SYNONYM_PUBLIC_SCHEMA, tableName));
    }
    if (checkExistOfTable(connection, tableName, tableSchema)) {
      stmt.executeUpdate(String.format("drop TABLE  \"%s\".\"%s\"", tableSchema, tableName));
    }
  }

  public static void dropView(Connection connection, Statement stmt, String tableName, String tableSchema) throws SQLException {
    if (checkExistOfPublicSynonym(connection, tableName)) {
      stmt.executeUpdate(
          String.format("drop SYNONYM \"%s\".\"%s\"", XSKConstants.XSK_SYNONYM_PUBLIC_SCHEMA, tableName));
    }
    if (checkExistOfView(connection, tableName, tableSchema)) {
      stmt.executeUpdate(String.format("drop VIEW  \"%s\".\"%s\"", tableSchema, tableName));
    }
  }

  public static void dropTableType(Connection connection, Statement stmt, String tableName, String tableSchema) throws SQLException {
    if (checkExistOfPublicSynonym(connection, tableName)) {
      stmt.executeUpdate(
          String.format("drop SYNONYM \"%s\".\"%s\"", XSKConstants.XSK_SYNONYM_PUBLIC_SCHEMA, tableName));
    }
    if (checkExistOfTable(connection, tableName, tableSchema)) {
      stmt.executeUpdate(String.format("drop TYPE  \"%s\".\"%s\"", tableSchema, tableName));
    }
  }

  public static void dropProcedure(Connection connection, Statement stmt, String procName, String procSchema) throws SQLException {
    stmt.executeUpdate(String.format("DROP PROCEDURE %s", XSKHDBUtils
        .escapeArtifactName(connection, procName, procSchema)));
  }

  public static void dropFunction(Connection connection, Statement stmt, String funcName, String funcSchema) throws SQLException {
    stmt.executeUpdate(String.format("DROP FUNCTION %s", XSKHDBUtils
        .escapeArtifactName(connection, funcName, funcSchema)));
  }

  public static void dropSequence(Connection connection, Statement stmt, String seqName, String seqSchema) throws SQLException {
    stmt.executeUpdate(String.format("DROP SEQUENCE %s", XSKHDBUtils
        .escapeArtifactName(connection, seqName, seqSchema)));
  }

  public static void dropSchema(Statement stmt, String schemaName) throws SQLException {
    stmt.executeUpdate(String.format("DROP SCHEMA \"%s\" CASCADE", schemaName));
  }

  public static void createSchema(Statement stmt, String schemaName) throws SQLException {
    stmt.executeUpdate(String.format("CREATE SCHEMA \"%s\"", schemaName));
  }

  public static void clearDataFromXSKDataStructure(DataSource datasource, List<String> resources) throws SQLException {
    try (Connection connection = datasource.getConnection(); Statement stmt = connection.createStatement()) {
      DatabaseMetaData metaData = connection.getMetaData();
      ResultSet table = metaData.getTables(null, null, "XSK_DATA_STRUCTURES", null);
      if (table.next()) {
        stmt.executeUpdate("DELETE FROM \"XSK_DATA_STRUCTURES\" WHERE DS_LOCATION IN " + "(" + String.join(",", resources) + ")");
      }
    }
  }

  public static void createEmptyTable(Statement stmt, String tableName, String tableSchema) throws SQLException {
    stmt.executeUpdate(String.format("create table \"%s\".\"%s\"(Id INTEGER,Name NVARCHAR)",
        tableSchema, tableName));
  }
}
