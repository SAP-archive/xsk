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
package com.sap.xsk.hdb.ds.model.hdbsynonym;

import com.google.gson.annotations.SerializedName;
import com.sap.xsk.hdb.ds.exceptions.XSKHDBSYNONYMMissingPropertyException;
import lombok.*;
import java.util.Objects;

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
