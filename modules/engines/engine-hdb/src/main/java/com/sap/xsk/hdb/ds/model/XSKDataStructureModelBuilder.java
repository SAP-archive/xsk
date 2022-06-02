/*
 * Copyright (c) 2022 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2022 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.hdb.ds.model;

import java.sql.Timestamp;

public class XSKDataStructureModelBuilder {

  private String location;
  private String name;
  private String type;
  private String hash;
  private String createdBy;
  private Timestamp createdAt;
  private String schema;
  private String rawContent;
  private XSKDBContentType dbContentType;


  public XSKDataStructureModelBuilder withName(String name) {
    this.name = name;
    return this;
  }

  public XSKDataStructureModelBuilder withLocation(String location) {
    this.location = location;
    return this;
  }

  public XSKDataStructureModelBuilder withType(String type) {
    this.type = type;
    return this;
  }

  public XSKDataStructureModelBuilder withHash(String hash) {
    this.hash = hash;
    return this;
  }

  public XSKDataStructureModelBuilder createdAt(Timestamp timestamp) {
    this.createdAt = timestamp;
    return this;
  }

  public XSKDataStructureModelBuilder createdBy(String createdBy) {
    this.createdBy = createdBy;
    return this;
  }

  public XSKDataStructureModelBuilder withSchema(String schema) {
    this.schema = schema;
    return this;
  }

  public XSKDataStructureModelBuilder rawContent(String rawContent) {
    this.rawContent = rawContent;
    return this;
  }

  public String getLocation() {
    return location;
  }

  public String getName() {
    return name;
  }

  public String getType() {
    return type;
  }

  public String getHash() {
    return hash;
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public Timestamp getCreatedAt() {
    return createdAt;
  }

  public String getSchema() {
    return schema;
  }

  public String getRawContent() {
    return rawContent;
  }

  public XSKDBContentType getDbContentType() {
    return dbContentType;
  }

  public XSKDataStructureModel build() {
    return new XSKDataStructureModel(this);
  }

  public XSKDataStructureModelBuilder() {
  }
}
