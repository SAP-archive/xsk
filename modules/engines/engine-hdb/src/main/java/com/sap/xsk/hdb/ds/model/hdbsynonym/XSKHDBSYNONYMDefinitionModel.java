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

import java.util.Objects;

import com.google.gson.annotations.SerializedName;
import com.sap.xsk.hdb.ds.exceptions.XSKHDBSYNONYMMissingPropertyException;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class XSKHDBSYNONYMDefinitionModel {

  private Target target;
  @SerializedName(value = "schema")
  String synonymSchema;

  @Getter
  @Setter
  @NoArgsConstructor
  public static class Target {
    String object;
    String schema;

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
