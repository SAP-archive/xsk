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
package com.sap.xsk.hdb.ds.model.hdbtabletype;

import com.sap.xsk.hdb.ds.model.XSKDataStructureModel;
import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableColumnModel;
import java.util.ArrayList;
import java.util.List;

public class XSKDataStructureHDBTableTypeModel extends XSKDataStructureModel {


  private List<XSKDataStructureHDBTableColumnModel> columns = new ArrayList<>();
  private XSKDataStructureHDBTableTypePrimaryKeyModel primaryKey = new XSKDataStructureHDBTableTypePrimaryKeyModel();
  private Boolean publicProp;
  private String tableTypeName;

  public List<XSKDataStructureHDBTableColumnModel> getColumns() {
    return columns;
  }

  public void setColumns(List<XSKDataStructureHDBTableColumnModel> columns) {
    this.columns = columns;
  }

  public XSKDataStructureHDBTableTypePrimaryKeyModel getPrimaryKey() {
    return primaryKey;
  }

  public void setPrimaryKey(XSKDataStructureHDBTableTypePrimaryKeyModel primaryKey) {
    this.primaryKey = primaryKey;
  }

  public Boolean isPublicProp() {
    return publicProp;
  }

  public void setPublicProp(Boolean publicProp) {
    this.publicProp = publicProp;
  }

  public String getTableTypeName() {
    return tableTypeName;
  }

  public void setTableTypeName(String tableTypeName) {
    this.tableTypeName = tableTypeName;
  }
}
