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
package com.sap.xsk.hdb.ds.model.hdbdd;

import com.sap.xsk.hdb.ds.model.XSKDataStructureModel;
import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableModel;
import java.util.List;

public class XSKDataStructureCdsModel extends XSKDataStructureModel {
  private boolean forceUpdate;

  private List<XSKDataStructureHDBTableModel> tableModels;

  public List<XSKDataStructureHDBTableModel> getTableModels() {
    return tableModels;
  }

  public void setTableModels(List<XSKDataStructureHDBTableModel> tableModels) {
    this.tableModels = tableModels;
  }

  public boolean isForceUpdate() {
    return forceUpdate;
  }

  public void setForceUpdate(boolean forceUpdate) {
    this.forceUpdate = forceUpdate;
  }
}
