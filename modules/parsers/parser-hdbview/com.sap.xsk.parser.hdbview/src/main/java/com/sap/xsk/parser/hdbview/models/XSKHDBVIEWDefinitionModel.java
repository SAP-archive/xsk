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
package com.sap.xsk.parser.hdbview.models;

import com.sap.xsk.parser.hdbview.exceptions.XSKHDBViewMissingPropertyException;
import java.util.List;
import java.util.Objects;

public class XSKHDBVIEWDefinitionModel {

  private String schema;
  private String query;
  private boolean publicProp = true;
  private List<String> dependsOn;
  private List<String> dependsOnTable;
  private List<String> dependsOnView;

  public String getSchema() {
    return schema;
  }

  public void setSchema(String schema) {
    this.schema = schema;
  }

  public String getQuery() {
    return query;
  }

  public void setQuery(String query) {
    this.query = query;
  }

  public boolean isPublic() {
    return publicProp;
  }

  public void setPublic(boolean publicProp) {
    this.publicProp = publicProp;
  }

  public List<String> getDependsOn() {
    return dependsOn;
  }

  public void setDependsOn(List<String> dependsOn) {
    this.dependsOn = dependsOn;
  }

  public List<String> getDependsOnTable() {
    return dependsOnTable;
  }

  public void setDependsOnTable(List<String> dependsOnTable) {
    this.dependsOnTable = dependsOnTable;
  }

  public List<String> getDependsOnView() {
    return dependsOnView;
  }

  public void setDependsOnView(List<String> dependsOnView) {
    this.dependsOnView = dependsOnView;
  }

  public void checkForAllMandatoryFieldsPresence() throws Exception {
    checkPresence(schema, "schema");
    checkPresence(query, "query");
  }

  private <T> void checkPresence(T field, String fieldName) {
    if (Objects.isNull(field)) {
      throw new XSKHDBViewMissingPropertyException(String.format("Missing mandatory field %s!", fieldName));
    }
  }

  @Override
  public String toString() {
    return "HdbviewDefinitionModel{" +
        "schema='" + schema + '\'' +
        ", query='" + query + '\'' +
        ", publicProp=" + publicProp +
        ", dependsOn=" + dependsOn +
        ", dependsOnTable=" + dependsOnTable +
        ", dependsOnView=" + dependsOnView +
        '}';
  }
}
