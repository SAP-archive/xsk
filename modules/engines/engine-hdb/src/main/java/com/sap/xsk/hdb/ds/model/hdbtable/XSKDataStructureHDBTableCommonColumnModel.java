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

public class XSKDataStructureHDBTableCommonColumnModel {

  private String length;

  private String precision;

  private String scale;

  private String sqlType;

  public XSKDataStructureHDBTableCommonColumnModel() {
  }

  public XSKDataStructureHDBTableCommonColumnModel(String length, String precision, String scale, String sqlType) {
    super();
    this.length = length;
    this.precision = precision;
    this.scale = scale;
    this.sqlType = sqlType;
  }

  /**
   * Getter for the type.
   *
   * @return the type
   */
  public String getType() {
    return sqlType;
  }

  /**
   * Setter for the type.
   *
   * @param sqlType the type
   */
  public void setType(String sqlType) {
    this.sqlType = sqlType;
  }

  /**
   * Getter for the length.
   *
   * @return the length
   */
  public String getLength() {
    return length;
  }

  /**
   * Setter for the length.
   *
   * @param length the length
   */
  public void setLength(String length) {
    this.length = length;
  }

  /**
   * Getter for the precision value.
   *
   * @return the precision value
   */
  public String getPrecision() {
    return precision;
  }

  /**
   * Setter for the precision value.
   *
   * @param precision the precision value
   */
  public void setPrecision(String precision) {
    this.precision = precision;
  }

  /**
   * Getter for the scale value.
   *
   * @return the scale value
   */
  public String getScale() {
    return scale;
  }

  /**
   * Setter for the scale value.
   *
   * @param scale the scale value
   */
  public void setScale(String scale) {
    this.scale = scale;
  }
}
