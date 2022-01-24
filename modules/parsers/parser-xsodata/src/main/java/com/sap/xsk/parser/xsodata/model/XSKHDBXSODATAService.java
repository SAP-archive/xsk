/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2022 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.parser.xsodata.model;

import java.util.ArrayList;
import java.util.Objects;

public class XSKHDBXSODATAService {

  private String namespace;
  private boolean enableOData4SAPAnnotations;
  private XSKHDBXSODATASetting setting;
  private ArrayList<XSKHDBXSODATAEntity> entities = new ArrayList<>();
  private ArrayList<XSKHDBXSODATAAssociation> associations = new ArrayList<>();

  public String getNamespace() {
    return namespace;
  }

  public void setNamespace(String namespace) {
    this.namespace = namespace;
  }

  public boolean isEnableOData4SAPAnnotations() {
    return enableOData4SAPAnnotations;
  }

  public void setEnableOData4SAPAnnotations(boolean enableOData4SAPAnnotations) {
    this.enableOData4SAPAnnotations = enableOData4SAPAnnotations;
  }

  public XSKHDBXSODATASetting getSetting() {
    return setting;
  }

  public void setSetting(XSKHDBXSODATASetting setting) {
    this.setting = setting;
  }

  public ArrayList<XSKHDBXSODATAEntity> getEntities() {
    return entities;
  }

  public void setEntities(ArrayList<XSKHDBXSODATAEntity> entities) {
    this.entities = entities;
  }

  public ArrayList<XSKHDBXSODATAAssociation> getAssociations() {
    return associations;
  }

  public void setAssociations(ArrayList<XSKHDBXSODATAAssociation> associations) {
    this.associations = associations;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    XSKHDBXSODATAService that = (XSKHDBXSODATAService) o;
    return enableOData4SAPAnnotations == that.enableOData4SAPAnnotations && Objects.equals(namespace, that.namespace) && Objects.equals(
        setting, that.setting) && Objects.equals(entities, that.entities) && Objects.equals(associations, that.associations);
  }

  @Override
  public int hashCode() {
    return Objects.hash(namespace, enableOData4SAPAnnotations, setting, entities, associations);
  }
}
