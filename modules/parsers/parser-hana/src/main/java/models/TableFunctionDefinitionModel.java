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
package models;

import java.util.Objects;

public class TableFunctionDefinitionModel {

  private final String schema;
  private final String name;

  public TableFunctionDefinitionModel(String schema, String name) {
    this.schema = schema;
    this.name = name;
  }

  public String getSchema() {
    return schema;
  }

  public String getName() {
    return name;
  }

  public void checkForAllMandatoryFieldsPresence() {
    checkPresence(name, "name");
  }

  private <T> void checkPresence(T field, String fieldName) {
    if (Objects.isNull(field)) {
      throw new TableFunctionMissingPropertyException(String.format("Missing mandatory field %s!", fieldName));
    }
  }
}
