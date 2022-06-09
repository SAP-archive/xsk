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

public class JoinClauseDefinitionModel {

  private String tableName;
  private String tableAlias;
  private String onPart;
  private String rawContent;

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

  public String getOnPart() {
    return onPart;
  }

  public void setOnPart(String onPart) {
    this.onPart = onPart;
  }

  public String getRawContent() {
    return rawContent;
  }

  public void setRawContent(String rawContent) {
    this.rawContent = rawContent;
  }
}
