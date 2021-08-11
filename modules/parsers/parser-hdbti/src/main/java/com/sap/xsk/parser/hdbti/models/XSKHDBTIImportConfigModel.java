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

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class XSKHDBTIImportConfigModel {

  @JsonProperty("table")
  private String tableName;

  private String schemaName;
  private String fileName;
  private Boolean header;
  private Boolean useHeaderNames;
  private String delimField;
  private String delimEnclosing;
  private Boolean distinguishEmptyFromNull;
  private List<Pair> keys;

  public static class Pair {

    private String key;
    private String value;

    public Pair(String key, String value) {
      this.key = key;
      this.value = value;
    }

    public String getKey() {
      return key;
    }

    public String getValue() {
      return value;
    }

    @JsonProperty("key")
    public String getJsonkeys(){return "sss";}
  }
}
