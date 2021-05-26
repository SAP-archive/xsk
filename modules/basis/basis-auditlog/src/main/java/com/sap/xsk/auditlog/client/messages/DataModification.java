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
import java.util.ArrayList;
import java.util.List;

public class DataModification extends AuditLogMessage {

  @Expose
  @SerializedName("attributes")
  private final List<Attribute> attributes;

  @Expose
  @SerializedName("data_subject")
  private final AuditedDataSubject subject;

  @Expose
  @SerializedName("object")
  private final AuditedObject object;

  public DataModification(DataModificationDetail modificationDetails, AuditLogDetail logDetails) {
    super(logDetails);
    this.object = modificationDetails.object;
    this.subject = modificationDetails.subject;
    this.attributes = modificationDetails.attributes;
  }

  public List<Attribute> getAttributes() {
    return new ArrayList<>(this.attributes);
  }

  public AuditedDataSubject getSubject() {
    return subject;
  }

  public AuditedObject getObject() {
    return object;
  }
  
  @Override
  public AuditLogCategory getCategory() {
    return AuditLogCategory.DATA_MODIFICATION;
  }

  public static class DataModificationDetail {

    private final AuditedDataSubject subject;
    private final AuditedObject object;
    private final List<Attribute> attributes;

    public DataModificationDetail(AuditedDataSubject subject, AuditedObject object, List<Attribute> attributes) {
      this.subject = subject;
      this.object = object;
      this.attributes = new ArrayList<>(attributes);
    }
  }
}

