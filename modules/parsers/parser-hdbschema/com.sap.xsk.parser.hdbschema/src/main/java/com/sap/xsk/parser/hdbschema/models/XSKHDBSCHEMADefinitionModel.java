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
package com.sap.xsk.parser.hdbschema.models;

import com.sap.xsk.parser.hdbschema.exceptions.XSKHDBSchemaMissingPropertyException;
import java.util.List;
import java.util.Objects;

public class XSKHDBSCHEMADefinitionModel {

  private String schema;

  public String getSchema() {
    return schema;
  }

  public void setSchema(String schema) {
    this.schema = schema;
  }

  public void checkForAllMandatoryFieldsPresence() throws Exception {
    checkPresence(schema, "schema");
  }

  private <T> void checkPresence(T field, String fieldName) {
    if (Objects.isNull(field)) {
      throw new XSKHDBSchemaMissingPropertyException(String.format("Missing mandatory field %s!", fieldName));
    }
  }

  @Override
  public String toString() {
    return "HdbschemaDefinitionModel{" +
        "schema='" + schema + '\'' +
        '}';
  }
}
