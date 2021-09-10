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
package com.sap.xsk.parser.hdbtable.model;

import com.sap.xsk.parser.hdbtable.exceptions.XSKHDBTableMissingPropertyException;
import java.util.Objects;

public class XSKHDBTABLEColumnsModel {

  private String name;
  private String sqlType;
  private boolean nullable;
  private boolean unique;
  private String length;
  private String comment;
  private String defaultValue;
  private String precision;
  private String scale;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSqlType() {
    return sqlType;
  }

  public void setSqlType(String sqlType) {
    this.sqlType = sqlType;
  }

  public boolean isNullable() {
    return nullable;
  }

  public void setNullable(boolean nullable) {
    this.nullable = nullable;
  }

  public boolean isUnique() {
    return unique;
  }

  public void setUnique(boolean unique) {
    this.unique = unique;
  }

  public String getLength() {
    return length;
  }

  public void setLength(String length) {
    this.length = length;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public String getDefaultValue() {
    return defaultValue;
  }

  public void setDefaultValue(String defaultValue) {
    this.defaultValue = defaultValue;
  }

  public String getPrecision() {
    return precision;
  }

  public void setPrecision(String precision) {
    this.precision = precision;
  }

  public String getScale() {
    return scale;
  }

  public void setScale(String scale) {
    this.scale = scale;
  }

  public void checkForAllMandatoryColumnFieldsPresence() {
    checkPresence(name, "name");
    checkPresence(sqlType, "sqlType");
  }

  private <T> void checkPresence(T field, String fieldName) {
    if (Objects.isNull(field)) {
      throw new XSKHDBTableMissingPropertyException(String.format("Missing mandatory field %s!", fieldName));
    }
  }
}
