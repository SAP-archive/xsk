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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class Attachment {

  @JsonProperty(value = "id", required = true)
  private final String id;
  @JsonProperty(value = "name", required = true)
  private final String name;
  @JsonProperty("successful")
  private final Boolean successful;

  public Attachment(String id, String name) {
    this.id = id;
    this.name = name;
    this.successful = null;
  }

  public Attachment(String id, String name, Boolean successful) {
    this.id = id;
    this.name = name;
    this.successful = successful;
  }

  public String getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public Boolean getSuccessful() {
    return this.successful;
  }
}

