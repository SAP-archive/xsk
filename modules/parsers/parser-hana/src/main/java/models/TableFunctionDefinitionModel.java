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

import exceptions.TableFunctionMissingPropertyException;
import java.util.Objects;

public class TableFunctionDefinitionModel {

  private String schema;
  private String name;

  public String getSchema() {
    return schema;
  }

  public void setSchema(String schema) {
    this.schema = schema;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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
