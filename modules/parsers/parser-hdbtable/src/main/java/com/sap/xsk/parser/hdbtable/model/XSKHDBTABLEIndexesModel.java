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
package com.sap.xsk.parser.hdbtable.model;

import com.google.gson.annotations.SerializedName;
import com.sap.xsk.parser.hdbtable.constants.HdbtablePropertiesConstants;
import com.sap.xsk.parser.hdbtable.exceptions.XSKHDBTableMissingPropertyException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;

public class XSKHDBTABLEIndexesModel {

  @SerializedName(value = HdbtablePropertiesConstants.INDEX_PROPERTY_NAME)
  private String indexName;

  private boolean unique;
  private String order;
  private Set<String> indexColumns;
  private String indexType;

  public String getIndexName() {
    return indexName;
  }

  public void setIndexName(String name) {
    this.indexName = indexName;
  }

  public boolean isUnique() {
    return unique;
  }

  public void setUnique(boolean unique) {
    this.unique = unique;
  }

  public String getOrder() {
    return order;
  }

  public void setOrder(String order) {
    this.order = order;
  }

  public Set<String> getIndexColumns() {
    return indexColumns;
  }

  public void setIndexColumns(Set<String> indexColumns) {
    this.indexColumns = indexColumns;
  }

  public String getIndexType() {
    return indexType;
  }

  public void setIndexType(String indexType) {
    this.indexType = indexType;
  }

  public void checkForAllIndexMandatoryFieldsPresence() {
    checkPresence(indexName, "name");
    checkPresence(unique, "unique");
    checkPresence(indexColumns, "indexColumns");
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
