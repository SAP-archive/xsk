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

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Attachment {

  @Expose
  @SerializedName("id")
  private final String id;

  @Expose
  @SerializedName("name")
  private final String name;

  @Expose
  @SerializedName("successful")
  private final Boolean successful;

  public Attachment(String id, String name) {
    this.id = id;
    this.name = name;
    this.successful = null;
  }

  public Attachment(String id, String name, boolean successful) {
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

