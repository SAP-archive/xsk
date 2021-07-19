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
package com.sap.xsk.auditlog.client.messages;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class DataAccess extends AuditLogMessage {

  @Expose
  @SerializedName("object")
  private final AuditedObject object;

  @Expose
  @SerializedName("data_subject")
  private final AuditedDataSubject subject;

  @Expose
  @SerializedName("attributes")
  private final List<Attribute> attributes;

  @Expose
  @SerializedName("attachments")
  private final List<Attachment> attachments;

  @Expose
  @SerializedName("channel")
  private final String channel;

  public DataAccess(DataAccessDetail accessDetails, AuditLogDetail logDetails) {
    super(logDetails);
    this.object = accessDetails.object;
    this.subject = accessDetails.subject;
    this.attributes = accessDetails.attributes;
    this.attachments = accessDetails.attachments;
    this.channel = accessDetails.channel;
  }

  public String getChannel() {
    return this.channel;
  }

  public List<Attribute> getAttributes() {
    return this.attributes;
  }

  public List<Attachment> getAttachments() {
    return this.attachments;
  }
  
  @Override
  public AuditLogCategory getCategory() {
    return AuditLogCategory.DATA_ACCESS;
  }

  public static class DataAccessDetail {

    private final AuditedObject object;
    private final AuditedDataSubject subject;
    private final List<Attribute> attributes;

    private String channel;
    private List<Attachment> attachments;

    public DataAccessDetail(AuditedObject object, AuditedDataSubject subject, List<Attribute> attributes) {
      this.object = object;
      this.subject = subject;
      this.attributes = new ArrayList<>(attributes);
    }

    public void setChannel(String channel) {
      this.channel = channel;
    }

    public void setAttachments(List<Attachment> attachments) {
      this.attachments = new ArrayList<>(attachments);
    }
  }
}

