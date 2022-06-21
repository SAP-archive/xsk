package com.sap.xsk.integration.tests.applications.status;

public class XSKProjectSqlCheck {

  private final String schemaName;
  private final String tableName;

  public XSKProjectSqlCheck(String schemaName, String tableName) {
    this.schemaName = schemaName;
    this.tableName = tableName;
  }

  public String getSchemaName() {
    return schemaName;
  }

  public String getTableName() {
    return tableName;
  }
}
