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
package com.sap.xsk.parser.xsodata.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class XSKHDBXSODATAStorage {

  private XSKHDBXSODATAStorageType storageType;
  private List<XSKHDBXSODATAModification> modifications = new ArrayList<>();

  public XSKHDBXSODATAStorageType getStorageType() {
    return storageType;
  }

  public XSKHDBXSODATAStorage setStorageType(XSKHDBXSODATAStorageType storageType) {
    this.storageType = storageType;
    return this;
  }

  public List<XSKHDBXSODATAModification> getModifications() {
    return modifications;
  }

  public XSKHDBXSODATAStorage setModifications(List<XSKHDBXSODATAModification> modifications) {
    this.modifications = modifications;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    XSKHDBXSODATAStorage that = (XSKHDBXSODATAStorage) o;
    return storageType == that.storageType && Objects.equals(modifications, that.modifications);
  }

  @Override
  public int hashCode() {
    return Objects.hash(storageType, modifications);
  }
}
