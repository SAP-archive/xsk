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
package com.sap.xsk.xsodata.ds.model;

public class XSKDBArtifactModel {

  private final String name;
  private final String type;
  private final String schema;

  public XSKDBArtifactModel(String name, String type, String schema) {
    this.name = name;
    this.type = type;
    this.schema = schema;
  }

  public String getName() {
    return name;
  }

  public String getType() {
    return type;
  }

  public String getSchema() {
    return schema;
  }

  @Override
  public String toString() {
    return "DBArtifact{" +
        "name='" + name + '\'' +
        ", type='" + type + '\'' +
        ", schema='" + schema + '\'' +
        '}';
  }
}
