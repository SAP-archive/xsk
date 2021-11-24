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

import java.util.ArrayList;
import java.util.List;

import com.sap.xsk.hdb.ds.model.XSKDataStructureModel;
import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableColumnModel;
import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableConstraintsModel;
import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableIndexModel;

/**
 * The table model representation.
 */
public class XSKDataStructureEntityModel extends XSKDataStructureModel {

  private List<XSKDataStructureHDBTableColumnModel> columns = new ArrayList<XSKDataStructureHDBTableColumnModel>();

  private XSKDataStructureHDBTableConstraintsModel constraints = new XSKDataStructureHDBTableConstraintsModel();

  private List<XSKDataStructureHDBTableIndexModel> indexes = new ArrayList<>();

  private String namespace;

  private String context;

  /**
   * Getter for the columns.
   *
   * @return the columns
   */
  public List<XSKDataStructureHDBTableColumnModel> getColumns() {
    return columns;
  }

  /**
   * Gets the constraints.
   *
   * @return the constraints
   */
  public XSKDataStructureHDBTableConstraintsModel getConstraints() {
    return constraints;
  }

  /**
   * @return the context
   */
  public String getContext() {
    return context;
  }

  /**
   * @param context the context to set
   */
  public void setContext(String context) {
    this.context = context;
  }

  /**
   * @return the namespace
   */
  public String getNamespace() {
    return namespace;
  }

  /**
   * @param namespace the namespace to set
   */
  public void setNamespace(String namespace) {
    this.namespace = namespace;
  }

  public List<XSKDataStructureHDBTableIndexModel> getIndexes() {
    return indexes;
  }
}
