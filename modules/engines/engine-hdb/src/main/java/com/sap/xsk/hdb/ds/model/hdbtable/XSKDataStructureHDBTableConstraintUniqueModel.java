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

/**
 * The Data Structure Table Constraint Unique Model.
 */
public class XSKDataStructureHDBTableConstraintUniqueModel extends XSKDataStructureHDBTableConstraintModel {

  private String indexName;
  private String indexType;
  private String order;
  public XSKDataStructureHDBTableConstraintUniqueModel(){
  }

  public XSKDataStructureHDBTableConstraintUniqueModel(String indexName, String order, String[] indexColumns){
    if (indexName != null){
      this.indexName = indexName;
    }
    if (order != null){
      this.order = order;
    }
    this.setColumns(indexColumns);
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

}
