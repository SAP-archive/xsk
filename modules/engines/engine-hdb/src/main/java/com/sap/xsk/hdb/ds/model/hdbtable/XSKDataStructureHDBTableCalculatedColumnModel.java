package com.sap.xsk.hdb.ds.model.hdbtable;

public class XSKDataStructureHDBTableCalculatedColumnModel extends XSKDataStructureHDBTableCommonColumnModel {

  private String columnName;

  private String statement;

  public XSKDataStructureHDBTableCalculatedColumnModel() {
  }

  public XSKDataStructureHDBTableCalculatedColumnModel(String columnName, String statement) {
    this.columnName = columnName;
    this.statement = statement;
  }

  public String getColumnName() {
    return columnName;
  }

  public void setColumnName(String columnName) {
    this.columnName = columnName;
  }

  public String getStatement() {
    return statement;
  }

  public void setStatement(String statement) {
    this.statement = statement;
  }
}
