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
package com.sap.xsk.hdb.ds.model.hdbtabletype;

public class XSKDataStructureHDBTableTypePrimaryKeyModel {

  private String name;

  private String[] primaryKeyColumns;


  /**
   * Gets the name.
   *
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name.
   *
   * @param name the new name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets the primary key columns.
   *
   * @return the primary key columns
   */
  public String[] getPrimaryKeyColumns() {
    return (primaryKeyColumns != null) ? primaryKeyColumns.clone() : null;
  }

  /**
   * Sets the primary key columns.
   *
   * @param primaryKeyColumns the new primary key columns
   */
  public void setPrimaryKeyColumns(String[] primaryKeyColumns) {
    this.primaryKeyColumns = primaryKeyColumns;
  }

}
