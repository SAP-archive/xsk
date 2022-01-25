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
package com.sap.xsk.hdb.ds.model;

import java.util.Objects;

/**
 * Dependency element of the DataStructureModel.
 */
public class XSKDataStructureDependencyModel {

  private String name;

  private String type;

  /**
   * The constructor from fields.
   *
   * @param name the name
   * @param type the type
   */
  public XSKDataStructureDependencyModel(String name, String type) {
    super();
    this.name = name;
    this.type = type;
  }

  /**
   * Getter for the name.
   *
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * Setter for the name.
   *
   * @param name the name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Getter for the type.
   *
   * @return the type
   */
  public String getType() {
    return type;
  }

  /**
   * Setter for the type.
   *
   * @param type the type
   */
  public void setType(String type) {
    this.type = type;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    XSKDataStructureDependencyModel that = (XSKDataStructureDependencyModel) o;
    return Objects.equals(name, that.name) && Objects.equals(type, that.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, type);
  }
}
