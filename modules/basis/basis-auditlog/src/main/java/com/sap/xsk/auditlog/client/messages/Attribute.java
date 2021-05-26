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

public class Attribute {

  @Expose
  @SerializedName("name")
  private final String name;

  @Expose
  @SerializedName("successful")
  private Boolean successful;

  @Expose
  @SerializedName("old")
  private String oldValue;

  @Expose
  @SerializedName("new")
  private String newValue;

  public Attribute(Builder builder) {
    this.name = builder.name;
    this.oldValue = builder.oldValue;
    this.newValue = builder.newValue;
    this.successful = builder.successful;
  }

  public String getOldValue() {
    return this.oldValue;
  }

  public void setOldValue(String oldValue) {
    this.oldValue = oldValue;
  }

  public String getNewValue() {
    return this.newValue;
  }

  public void setNewValue(String newValue) {
    this.newValue = newValue;
  }

  public String getName() {
    return this.name;
  }

  public Boolean getSuccessful() {
    return this.successful;
  }

  public void setSuccessful(boolean successful) {
    this.successful = successful;
  }

  public static class Builder {

    private final String name;
    private Boolean successful;
    private String oldValue;
    private String newValue;

    public Builder(String name) {
      this.name = name;
    }

    public void successful(Boolean successful) {
      this.successful = successful;
    }

    public void oldValue(String oldValue) {
      this.oldValue = oldValue;
    }

    public void newValue(String newValue) {
      this.newValue = newValue;
    }

    public Attribute build() {
      return new Attribute(this);
    }

  }

}

