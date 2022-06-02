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
package models;

import java.util.ArrayList;
import java.util.List;

public class FromClauseDefinitionModel {

  private List<JoinClauseDefinitionModel> joinClauses;

  private String tableName;
  private String tableAlias;

  public FromClauseDefinitionModel() {
    this.joinClauses = new ArrayList<>();
  }

  public String getTableName() {
    return tableName;
  }

  public void setTableName(String tableName) {
    this.tableName = tableName;
  }

  public String getTableAlias() {
    return tableAlias;
  }

  public void setTableAlias(String tableAlias) {
    this.tableAlias = tableAlias;
  }

  public List<JoinClauseDefinitionModel> getJoinClauses() {
    return joinClauses;
  }

  public void addJoinClause(JoinClauseDefinitionModel joinClause) {
    this.joinClauses.add(joinClause);
  }
}
