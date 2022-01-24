/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company and XSK contributors
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
 * The Data Structure Table Constraint Model.
 */
public class XSKDataStructureHDBTableConstraintModel {

  private String name;

  private String[] modifiers;

  private String[] columns;

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
   * Gets the modifiers.
   *
   * @return the modifiers
   */
  public String[] getModifiers() {
    return (modifiers != null) ? modifiers.clone() : null;
  }

  /**
   * Sets the modifiers.
   *
   * @param modifiers the new modifiers
   */
  public void setModifiers(String[] modifiers) {
    this.modifiers = modifiers;
  }

  /**
   * Gets the columns.
   *
   * @return the columns
   */
  public String[] getColumns() {
    return (columns != null) ? columns.clone() : null;
  }

  /**
   * Sets the columns.
   *
   * @param columns the new columns
   */
  public void setColumns(String[] columns) {
    this.columns = columns;
  }

}
