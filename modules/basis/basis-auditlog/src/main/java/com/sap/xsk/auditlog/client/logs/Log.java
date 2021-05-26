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
package com.sap.xsk.auditlog.client.logs;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Log {

  @Expose
  @SerializedName("message_uuid")
  private String uuid;

  @Expose
  @SerializedName("org_id")
  private String orgID;

  @Expose
  @SerializedName("space_id")
  private String spaceID;

  @Expose
  @SerializedName("app_or_service_id")
  private String appServiceID;

  @Expose
  @SerializedName("als_service_id")
  private String alsServiceID;

  @Expose
  @SerializedName("format_version")
  private String formatVersion;

  @Expose
  @SerializedName("tenant")
  private String tenant;

  @Expose
  @SerializedName("time")
  private String time;

  @Expose
  @SerializedName("user")
  private String user;

  @Expose
  @SerializedName("category")
  private String category;

  @Expose
  @SerializedName("message")
  private String message;


  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  public String getOrgID() {
    return orgID;
  }

  public void setOrgID(String orgID) {
    this.orgID = orgID;
  }

  public String getSpaceID() {
    return spaceID;
  }

  public void setSpaceID(String spaceID) {
    this.spaceID = spaceID;
  }

  public String getAppServiceID() {
    return appServiceID;
  }

  public void setAppServiceID(String appServiceID) {
    this.appServiceID = appServiceID;
  }

  public String getAlsServiceID() {
    return alsServiceID;
  }

  public void setAlsServiceID(String alsServiceID) {
    this.alsServiceID = alsServiceID;
  }

  public String getFormatVersion() {
    return formatVersion;
  }

  public void setFormatVersion(String formatVersion) {
    this.formatVersion = formatVersion;
  }

  public String getTenant() {
    return tenant;
  }

  public void setTenant(String tenant) {
    this.tenant = tenant;
  }

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
