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
