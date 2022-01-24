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

public class XSKHDBXSODATABindingRole {

  private XSKHDBXSODATABindingType bindingType;
  private List<String> keys = new ArrayList<>();

  public XSKHDBXSODATABindingType getBindingType() {
    return bindingType;
  }

  public XSKHDBXSODATABindingRole setBindingType(XSKHDBXSODATABindingType bindingType) {
    this.bindingType = bindingType;
    return this;
  }

  public List<String> getKeys() {
    return keys;
  }

  public XSKHDBXSODATABindingRole setKeys(List<String> keys) {
    this.keys = keys;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    XSKHDBXSODATABindingRole that = (XSKHDBXSODATABindingRole) o;
    return bindingType == that.bindingType && Objects.equals(keys, that.keys);
  }

  @Override
  public int hashCode() {
    return Objects.hash(bindingType, keys);
  }
}
