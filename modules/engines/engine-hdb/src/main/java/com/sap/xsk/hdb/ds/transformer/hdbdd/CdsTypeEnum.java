package com.sap.xsk.hdb.ds.transformer.hdbdd;

public enum CdsTypeEnum {
  String("NVARCHAR"),
  Integer("INTEGER"),
  Integer64("BIGINT"),
  Decimal("DECIMAL"),
  DecimalFloat("DECIMAL"),
  LocalDate("DATE"),
  LocalTime("TIME"),
  UTCDateTime("SECONDDATE"),
  UTCTimestamp("TIMESTAMP"),
  Boolean("BOOLEAN");

  CdsTypeEnum(java.lang.String sqlType) {
    this.sqlType = sqlType;
  }

  private String sqlType;

  public java.lang.String getSqlType() {
    return sqlType;
  }
}
