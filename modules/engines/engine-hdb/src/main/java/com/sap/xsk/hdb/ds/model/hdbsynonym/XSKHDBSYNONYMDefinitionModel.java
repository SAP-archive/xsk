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
package com.sap.xsk.hdb.ds.model.hdbsynonym;

import com.google.gson.annotations.SerializedName;
import com.sap.xsk.hdb.ds.exceptions.XSKHDBSYNONYMMissingPropertyException;
import java.util.Objects;

public class XSKHDBSYNONYMDefinitionModel {

  private Target target;
  @SerializedName(value = "schema")
  String synonymSchema;

  public Target getTarget() {
    return target;
  }

  public void setTarget(Target target) {
    this.target = target;
  }

  public String getSynonymSchema() {
    return synonymSchema;
  }

  public void setSynonymSchema(String synonymSchema) {
    this.synonymSchema = synonymSchema;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    XSKHDBSYNONYMDefinitionModel that = (XSKHDBSYNONYMDefinitionModel) o;
    return Objects.equals(target, that.target) && Objects.equals(synonymSchema, that.synonymSchema);
  }

  @Override
  public int hashCode() {
    return Objects.hash(target, synonymSchema);
  }

  public static class Target {

    String object;
    String schema;

    public String getObject() {
      return object;
    }

    public void setObject(String object) {
      this.object = object;
    }

    public String getSchema() {
      return schema;
    }

    public void setSchema(String schema) {
      this.schema = schema;
    }

    public void checkForAllMandatoryFieldsPresence() {
      checkPresence(object, "object");
      checkPresence(schema, "schema");
    }

    private <T> void checkPresence(T field, String fieldName) {
      if (Objects.isNull(field)) {
        throw new XSKHDBSYNONYMMissingPropertyException(String.format("Missing mandatory field %s!", fieldName));
      }
    }
  }
}
