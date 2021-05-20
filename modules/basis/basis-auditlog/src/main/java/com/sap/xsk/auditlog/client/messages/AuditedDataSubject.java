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
package com.sap.xsk.auditlog.client.messages;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonIgnoreProperties(
    ignoreUnknown = true
)
@JsonInclude(Include.NON_NULL)
public class AuditedDataSubject {

  @JsonProperty("type")
  private final String type;
  @JsonProperty("role")
  private final String role;
  @JsonProperty("id")
  private final Map<String, String> id;

  public AuditedDataSubject(String type, String role, Map<String, String> identifiers) {
    this.type = type;
    this.role = role;
    this.id = new LinkedHashMap<>(identifiers);
  }

  public AuditedDataSubject(String type, Map<String, String> identifiers) {
    this.type = type;
    this.id = new LinkedHashMap<>(identifiers);
    this.role = null;
  }

  @JsonIgnore
  public String getType() {
    return this.type;
  }

  @JsonIgnore
  public String getRole() {
    return this.role;
  }


  @JsonIgnore
  public LinkedHashMap<String, String> getId() {
    return new LinkedHashMap<>(id);
  }
}

