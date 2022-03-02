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
package com.sap.xsk.parser.hdbdd.symbols.view;

import com.sap.xsk.parser.hdbdd.symbols.Symbol;

public class SelectSymbol extends Symbol {

  Boolean isUnion = false;
  Boolean isDistinct = false;
  String joinSql = null;
  String columnsSql = null;
  String whereSql = null;
  String dependsOnTable = null;
  String dependingTableAlias = null;

  public Boolean getUnion() {
    return isUnion;
  }

  public void setUnion(Boolean union) {
    isUnion = union;
  }

  public Boolean getDistinct() {
    return isDistinct;
  }

  public void setDistinct(Boolean distinct) {
    isDistinct = distinct;
  }

  public String getJoinSql() {
    return joinSql;
  }

  public void setJoinSql(String joinSql) {
    this.joinSql = joinSql;
  }

  public String getColumnsSql() {
    return columnsSql;
  }

  public void setColumnsSql(String columnsSql) {
    this.columnsSql = columnsSql;
  }

  public String getWhereSql() {
    return whereSql;
  }

  public void setWhereSql(String whereSql) {
    this.whereSql = whereSql;
  }

  public String getDependsOnTable() {
    return dependsOnTable;
  }

  public void setDependsOnTable(String dependsOnTable) {
    this.dependsOnTable = dependsOnTable;
  }

  public String getDependingTableAlias() {
    return dependingTableAlias;
  }

  public void setDependingTableAlias(String dependingTableAlias) {
    this.dependingTableAlias = dependingTableAlias;
  }
}
