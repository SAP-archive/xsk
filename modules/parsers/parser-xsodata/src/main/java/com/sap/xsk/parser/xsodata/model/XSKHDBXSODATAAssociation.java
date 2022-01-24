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
import java.util.List;
import java.util.Objects;

public class XSKHDBXSODATAAssociation {

  private String name;
  private boolean withReferentialConstraint;
  private XSKHDBXSODATABinding principal;
  private XSKHDBXSODATABinding dependent;
  private XSKHDBXSODATAAssociationTable associationTable;
  private XSKHDBXSODATAStorage storage;
  private List<XSKHDBXSODATAModification> modifications = new ArrayList<>();

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean isWithReferentialConstraint() {
    return withReferentialConstraint;
  }

  public void setWithReferentialConstraint(boolean withReferentialConstraint) {
    this.withReferentialConstraint = withReferentialConstraint;
  }

  public XSKHDBXSODATABinding getPrincipal() {
    return principal;
  }

  public void setPrincipal(XSKHDBXSODATABinding principal) {
    this.principal = principal;
  }

  public XSKHDBXSODATABinding getDependent() {
    return dependent;
  }

  public void setDependent(XSKHDBXSODATABinding dependent) {
    this.dependent = dependent;
  }

  public XSKHDBXSODATAAssociationTable getAssociationTable() {
    return associationTable;
  }

  public void setAssociationTable(XSKHDBXSODATAAssociationTable associationTable) {
    this.associationTable = associationTable;
  }

  public XSKHDBXSODATAStorage getStorage() {
    return storage;
  }

  public void setStorage(XSKHDBXSODATAStorage storage) {
    this.storage = storage;
  }

  public List<XSKHDBXSODATAModification> getModifications() {
    return modifications;
  }

  public void setModifications(List<XSKHDBXSODATAModification> modifications) {
    this.modifications = modifications;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    XSKHDBXSODATAAssociation that = (XSKHDBXSODATAAssociation) o;
    return withReferentialConstraint == that.withReferentialConstraint && Objects.equals(name, that.name) && Objects.equals(principal,
        that.principal) && Objects.equals(dependent, that.dependent) && Objects.equals(associationTable, that.associationTable)
        && Objects.equals(storage, that.storage) && Objects.equals(modifications, that.modifications);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, withReferentialConstraint, principal, dependent, associationTable, storage, modifications);
  }
}
