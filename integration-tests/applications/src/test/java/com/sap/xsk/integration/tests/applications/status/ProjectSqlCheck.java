package com.sap.xsk.integration.tests.applications.status;

public class ProjectSqlCheck {

  private final String schemaName;
  private final String tableName;
  private final boolean tableExistsCheck;
  private final boolean tableHasRecordsCheck;

  public ProjectSqlCheck(String schemaName, String tableName, boolean tableExistsCheck, boolean tableHasRecordsCheck) {
    this.schemaName = schemaName;
    this.tableName = tableName;
    this.tableExistsCheck = tableExistsCheck;
    this.tableHasRecordsCheck = tableHasRecordsCheck;
  }

  public String getSchemaName() {
    return schemaName;
  }

  public String getTableName() {
    return tableName;
  }

  public boolean tableExistsCheck() {
    return tableExistsCheck;
  }

  public boolean tableHasRecordsCheck() {
    return tableHasRecordsCheck;
  }
}