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

import com.google.gson.annotations.SerializedName;
import com.sap.xsk.parser.hdbti.exception.XSKHDBTIMissingPropertyException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class XSKHDBTIImportConfigModel {

  @SerializedName("table")
  private String tableName;

  @SerializedName("schema")
  private String schemaName;

  @SerializedName("file")
  private String fileName;

  @SerializedName("header")
  private Boolean header;

  @SerializedName("useHeaderNames")
  private Boolean useHeaderNames;

  @SerializedName("delimField")
  private String delimField;

  @SerializedName("delimEnclosing")
  private String delimEnclosing;

  @SerializedName("distinguishEmptyFromNull")
  private Boolean distinguishEmptyFromNull;

  @SerializedName("keys")
  private List<Pair> keys = new ArrayList<>();

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

  @Override
  public String toString() {
    StringBuilder result = new StringBuilder();
    result.append("\n{\n");
    if (delimEnclosing != null) {
      result.append(delimEnclosing.equals("\"") ? "\tdelimEnclosing=\"\\" + delimEnclosing + "\";\n"
          : "\tdelimEnclosing=\"" + delimEnclosing + "\";\n");
    }
    if (schemaName != null) {
      result.append("\tschema = \"" + schemaName + "\";\n");
    }
    if (distinguishEmptyFromNull != null) {
      result.append("\tdistinguishEmptyFromNull = " + distinguishEmptyFromNull + ";\n");
    }
    if (header != null) {
      result.append("\theader = " + header + ";\n");
    }
    if (tableName != null) {
      result.append("\ttable = \"" + tableName + "\";\n");
    }
    if (useHeaderNames != null) {
      result.append("\tuseHeaderNames = " + useHeaderNames + ";\n");
    }
    if (delimField != null) {
      result.append("\tdelimField = \"" + delimField + "\";\n");
    }
    if (keys.size() > 0) {
      result.append("\tkeys = " + keys.toString() + ";\n");
    }
    if (fileName != null) {
      result.append("\tfile = \"" + fileName + "\";\n");
    }
    result.append("}");
    return result.toString();
  }

  public static class Pair {

    private String column;
    private ArrayList<String> values = new ArrayList<>();

    public Pair(String column, ArrayList<String> values) {
      this.column = column;
      this.values = values;
    }

    public String getColumn() {
      return column;
    }

    public void setColumn(String column) {
      this.column = column;
    }

    public ArrayList<String> getValues() {
      return values;
    }

    public void setValues(ArrayList<String> values) {
      this.values = values;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o)
        return true;
      if (o == null || getClass() != o.getClass())
        return false;
      Pair pair = (Pair) o;
      return Objects.equals(column, pair.column) && Objects.equals(values, pair.values);
    }

    @Override
    public int hashCode() {
      return Objects.hash(column, values);
    }

    @Override
    public String toString() {
      StringBuilder result = new StringBuilder();
      for (String value : values) {
        result.append("\"").append(column).append("\":").append("\"").append(value).append("\",");
      }
      return result.substring(0, result.length() - 1);
    }
  }

  public void checkForAllMandatoryFieldsPresence() {
    if (tableName != null && !tableName.contains("::")) {
      checkPresence(schemaName, "schemaName");
    }
  }

  private <T> void checkPresence(T field, String fieldName) {
    if (Objects.isNull(field)) {
      throw new XSKHDBTIMissingPropertyException(String.format("Missing mandatory field %s!", fieldName));
    }
    if ((field instanceof ArrayList) && ((ArrayList) field).isEmpty()) {
      throw new XSKHDBTIMissingPropertyException(String.format("Missing mandatory field %s!", fieldName));
    }
  }
}
