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

import java.util.Set;

public class XSKDataStructureHDBTableIndexModel {

  private String indexName;
  private String indexType;
  private String order;
  private boolean unique;
  private Set<String> indexColumns;

  public XSKDataStructureHDBTableIndexModel() {
  }

  public XSKDataStructureHDBTableIndexModel(String indexName, String order, Set<String> indexColumns, boolean isUnique) {
    this.indexName = indexName;
    this.order = order;
    this.indexColumns = indexColumns;
    this.unique = isUnique;
  }

  public String getIndexName() {
    return indexName;
  }

  public void setIndexName(String indexName) {
    this.indexName = indexName;
  }

  public String getIndexType() {
    return indexType;
  }

  public void setIndexType(String indexType) {
    this.indexType = indexType;
  }

  public String getOrder() {
    return order;
  }

  public void setOrder(String order) {
    this.order = order;
  }

  public boolean isUnique() {
    return unique;
  }

  public void setUnique(boolean unique) {
    this.unique = unique;
  }

  public Set<String> getIndexColumns() {
    return indexColumns;
  }

  public void setIndexColumns(Set<String> indexColumns) {
    this.indexColumns = indexColumns;
  }
}
