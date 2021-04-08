/*
 * Copyright (c) 2019-2021 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2019-2021 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.hdb.ds.model.hdbview;

import com.sap.xsk.hdb.ds.model.XSKDataStructureModel;
import java.util.List;

/**
 * The view model representation.
 */
public class XSKDataStructureHDBViewModel extends XSKDataStructureModel {

  private String query;
  private boolean publicProp;
  private List<String> dependsOn;
  private List<String> dependsOnTable;
  private List<String> dependsOnView;

  /**
   * Getter for the query field.
   *
   * @return the SQL query
   */
  public String getQuery() {
    return query;
  }

  /**
   * Setter for the query field.
   *
   * @param query the SQL query
   */
  public void setQuery(String query) {
    this.query = query;
  }

  /**
   * Getter for the 'public' field.
   *
   * @return the 'public' field
   */
  public boolean isPublic() {
    return publicProp;
  }

  /**
   * Setter for the 'public' field.
   *
   * @param publicProp the 'public' field
   */
  public void setPublic(boolean publicProp) {
    this.publicProp = publicProp;
  }

  /**
   * Getter for the 'depends_on' field.
   *
   * @return the 'depends_on' field
   */
  public List<String> getDependsOn() {
    return dependsOn;
  }

  /**
   * Setter for the 'depends_on' field.
   *
   * @param dependsOn the list of 'depends_on'
   */
  public void setDependsOn(List<String> dependsOn) {
    this.dependsOn = dependsOn;
  }

  /**
   * Getter for the 'depends_on_table' field.
   *
   * @return the 'depends_on_table' field
   */
  public List<String> getDependsOnTable() {
    return dependsOnTable;
  }

  /**
   * Setter for the 'depends_on_table' field.
   *
   * @param dependsOnTable the list of 'depends_on_table'
   */
  public void setDependsOnTable(List<String> dependsOnTable) {
    this.dependsOnTable = dependsOnTable;
  }

  /**
   * Getter for the 'depends_on_view' field.
   *
   * @return the 'depends_on_view' field
   */
  public List<String> getDependsOnView() {
    return dependsOnView;
  }

  /**
   * Setter for the 'depends_on_view' field.
   *
   * @param dependsOnView the 'depends_on_view'
   */
  public void setDependsOnView(List<String> dependsOnView) {
    this.dependsOnView = dependsOnView;
  }
}
