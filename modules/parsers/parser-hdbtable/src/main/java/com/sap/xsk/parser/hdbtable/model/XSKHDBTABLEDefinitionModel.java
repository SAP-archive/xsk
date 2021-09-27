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

import com.google.gson.annotations.SerializedName;
import com.sap.xsk.parser.hdbtable.constants.HdbtablePropertiesConstants;
import com.sap.xsk.parser.hdbtable.exceptions.XSKHDBTableMissingPropertyException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class XSKHDBTABLEDefinitionModel {
    private String schemaName;
    private String tableType;

    @SerializedName(value = HdbtablePropertiesConstants.HDBTABLE_PROPERTY_PKCOLUMNS)
    private List<String> pkColumns = new ArrayList<>();

    private String indexType;
    private List<XSKHDBTABLEColumnsModel> columns = new ArrayList<>();
    private List<XSKHDBTABLEIndexesModel> indexes = new ArrayList<>();
    private String description;
    private Boolean publicProp;
    private String loggingType;
    private Boolean temporary;

  public String getSchemaName() {
    return schemaName;
  }

  public void setSchemaName(String schemaName) {
    this.schemaName = schemaName;
  }

  public String getTableType() {
    return tableType;
  }

  public void setTableType(String tableType) {
    this.tableType = tableType;
  }

  public List<String> getPkColumns() {
    return pkColumns;
  }

  public void setPkColumns(List<String> pkColumns) {
    this.pkColumns = pkColumns;
  }

  public String getIndexType() {
    return indexType;
  }

  public void setIndexType(String indexType) {
    this.indexType = indexType;
  }

  public List<XSKHDBTABLEColumnsModel> getColumns() {
    return columns;
  }

  public void setColumns(List<XSKHDBTABLEColumnsModel> columns) {
    this.columns = columns;
  }

  public List<XSKHDBTABLEIndexesModel> getIndexes() {
    return indexes;
  }

  public void setIndexes(List<XSKHDBTABLEIndexesModel> indexes) {
    this.indexes = indexes;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Boolean isPublic() {
    return publicProp;
  }

  public void setPublic(Boolean publicProp) {
    this.publicProp = publicProp;
  }

  public String getLoggingType() {
    return loggingType;
  }

  public void setLoggingType(String loggingType) {
    this.loggingType = loggingType;
  }

  public Boolean getTemporary() {
    return temporary;
  }

  public void setTemporary(Boolean temporary) {
    this.temporary = temporary;
  }

  public void checkForAllMandatoryFieldsPresence() {
        checkPresence(schemaName, "schemaName");
        checkPresence(columns, "columns");
    }

    private <T> void checkPresence(T field, String fieldName) {
        if (Objects.isNull(field)) {
            throw new XSKHDBTableMissingPropertyException(String.format("Missing mandatory field %s!", fieldName));
        }
        if ((field instanceof ArrayList) && ((ArrayList) field).isEmpty()) {
            throw new XSKHDBTableMissingPropertyException(String.format("Missing mandatory field %s!", fieldName));
        }
    }
}
