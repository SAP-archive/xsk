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
package com.sap.xsk.parser.hdbti.models;

import java.util.List;

public class XSKHDBTIImportConfigModel {

  private String tableName;
  private String schemaName;
  private String fileName;
  private Boolean header;
  private Boolean useHeaderNames;
  private String delimField;
  private String delimEnclosing;
  private Boolean distinguishEmptyFromNull;
  private List<Pair> keys;

  public String getTableName() {
    return tableName;
  }

  public void setTableName(String tableName) {
    this.tableName = tableName;
  }

  public String getSchemaName() {
    return schemaName;
  }

  public void setSchemaName(String schemaName) {
    this.schemaName = schemaName;
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public Boolean getHeader() {
    return header;
  }

  public void setHeader(Boolean header) {
    this.header = header;
  }

  public Boolean getUseHeaderNames() {
    return useHeaderNames;
  }

  public void setUseHeaderNames(Boolean useHeaderNames) {
    this.useHeaderNames = useHeaderNames;
  }

  public String getDelimField() {
    return delimField;
  }

  public void setDelimField(String delimField) {
    this.delimField = delimField;
  }

  public String getDelimEnclosing() {
    return delimEnclosing;
  }

  public void setDelimEnclosing(String delimEnclosing) {
    this.delimEnclosing = delimEnclosing;
  }

  public Boolean getDistinguishEmptyFromNull() {
    return distinguishEmptyFromNull;
  }

  public void setDistinguishEmptyFromNull(Boolean distinguishEmptyFromNull) {
    this.distinguishEmptyFromNull = distinguishEmptyFromNull;
  }

  public List<Pair> getKeys() {
    return keys;
  }

  public void setKeys(List<Pair> keys) {
    this.keys = keys;
  }

  public static class Pair {

    private String key;
    private String value;

    public Pair(String key, String value) {
      this.key = key;
      this.value = value;
    }

    public String getKey() {
      return key;
    }

    public String getValue() {
      return value;
    }
  }
}
