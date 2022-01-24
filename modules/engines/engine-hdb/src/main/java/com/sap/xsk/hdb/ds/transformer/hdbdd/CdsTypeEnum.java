/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2022 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.hdb.ds.transformer.hdbdd;

/**
 * These types should respond to globalBuiltInTypeScope in SymbolTable class
 * See also {@link com.sap.xsk.parser.hdbdd.symbols.SymbolTable}.
 *
 * @see <a href="https://help.sap.com/viewer/52715f71adba4aaeb480d946c742d1f6/2.0.03/en-US/cf394efd3fb4400f9c09d10315028515.html">CDS Primitive Data Types</a>
 */
public enum CdsTypeEnum {
  String("NVARCHAR"),
  LargeString("NCLOB"),
  Binary("VARBINARY"),
  LargeBinary("BLOB"),
  Integer("INTEGER"),
  Integer64("BIGINT"),
  Decimal("DECIMAL"),
  BinaryFloat("DOUBLE"),
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
