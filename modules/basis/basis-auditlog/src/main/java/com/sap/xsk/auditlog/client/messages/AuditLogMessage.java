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
import java.time.Instant;
import java.util.Objects;

public abstract class AuditLogMessage {

  private static final String SUBSCRIBER_TENANT = "$SUBSCRIBER";
  private static final String PROVIDER_TENANT = "$PROVIDER";

  @Expose
  @SerializedName("user")
  private final String user = "$USER";

  @Expose
  @SerializedName("uuid")
  private final String uuid;

  @Expose
  @SerializedName("tenant")
  private final String tenant;

  @Expose
  @SerializedName("time")
  private final Instant time;
  
  @Expose(serialize = false, deserialize = false)
  private final String subscriberTokenIssuer;

  public AuditLogMessage(AuditLogDetail config) {
    this.uuid = config.uuid;
    this.time = config.time;
    this.subscriberTokenIssuer = config.subscriberTokenIssuer;
    if (Objects.isNull(this.subscriberTokenIssuer)) {
      this.tenant = PROVIDER_TENANT;
    } else {
      this.tenant = SUBSCRIBER_TENANT;
    }
  }

  public String getUuid() {
    return this.uuid;
  }

  public String getTenant() {
    return this.tenant;
  }

  public String getSubscriberTokenIssuer() {
    return this.subscriberTokenIssuer;
  }

  public Instant getTime() {
    return this.time;
  }

  public abstract AuditLogCategory getCategory();

  public static class AuditLogDetail {

    private final String uuid;
    private final Instant time;
    private final String subscriberTokenIssuer;

    public AuditLogDetail(String uuid, Instant time, String subscriberTokenIssuer) {
      this.uuid = uuid;
      this.time = time;
      this.subscriberTokenIssuer = subscriberTokenIssuer;
    }

    public AuditLogDetail(String uuid, Instant time) {
      this.uuid = uuid;
      this.time = time;
      this.subscriberTokenIssuer = null;
    }
  }
}

