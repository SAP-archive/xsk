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
 * The column element of the table model.
 */
public class XSKDataStructureHDBTableColumnModel {

  private String name;

  private String sqlType;

  private String length;

  private boolean nullable;

  private boolean primaryKey;

  private String defaultValue;

  private String precision;

  private String scale;

  private boolean unique;

  private String comment;

  private String alias;

  private boolean fuzzySearchIndex;

  /**
   * The default constructor.
   */
  public XSKDataStructureHDBTableColumnModel() {

  }

  /**
   * The constructor from the fields.
   *
   * @param name         the name
   * @param type         the type
   * @param length       the length
   * @param nullable     whether null values are allowed
   * @param primaryKey   whether it is a primary key
   * @param defaultValue the default value
   * @param precision    the precision value for floating point types
   * @param scale        the scale value for floating point types
   * @param unique       the unique
   */
  public XSKDataStructureHDBTableColumnModel(String name, String type, String length, boolean nullable, boolean primaryKey,
      String defaultValue,
      String precision, String scale, boolean unique, String alias) {
    super();
    this.name = name;
    this.sqlType = type;
    this.length = length;
    this.nullable = nullable;
    this.primaryKey = primaryKey;
    this.defaultValue = defaultValue;
    this.precision = precision;
    this.scale = scale;
    this.unique = unique;
    this.alias = alias;
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
   * Check for nullable.
   *
   * @return true if can be null
   */
  public boolean isNullable() {
    return nullable;
  }

  /**
   * Setter for the nullable.
   *
   * @param nullable whether null values are allowed
   */
  public void setNullable(boolean nullable) {
    this.nullable = nullable;
  }

  /**
   * Check for primary key.
   *
   * @return true if primary key
   */
  public boolean isPrimaryKey() {
    return primaryKey;
  }

  /**
   * Setter for the primary key.
   *
   * @param primaryKey whether it is a primary key
   */
  public void setPrimaryKey(boolean primaryKey) {
    this.primaryKey = primaryKey;
  }

  /**
   * Getter for the default value.
   *
   * @return the default value
   */
  public String getDefaultValue() {
    return defaultValue;
  }

  /**
   * Setter for the default value.
   *
   * @param defaultValue the default value
   */
  public void setDefaultValue(String defaultValue) {
    this.defaultValue = defaultValue;
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

  /**
   * Check for unique.
   *
   * @return true if unique
   */
  public boolean isUnique() {
    return unique;
  }

  /**
   * Setter for the unique.
   *
   * @param unique the unique value
   */
  public void setUnique(boolean unique) {
    this.unique = unique;
  }

  /**
   * Setter for the comment.
   *
   * @param comment the comment value
   */
  public void setComment(String comment) {
    this.comment = comment;
  }

  public String getComment() {
    return comment;
  }

  public void setAlias(String alias) {
    this.alias = alias;
  }

  public String getAlias() {
    return alias;
  }

  /**
   * Check for the Fuzzy search index.
   *
   * @return true if enabled
   */
  public boolean isFuzzySearchIndexEnabled() {
    return fuzzySearchIndex;
  }

  /**
   * Setter for the Fuzzy search index.
   *
   * @param fuzzySearchIndex whether Fuzzy search index is enabled
   */
  public void setFuzzySearchIndex(boolean fuzzySearchIndex) {
    this.fuzzySearchIndex = fuzzySearchIndex;
  }
}
