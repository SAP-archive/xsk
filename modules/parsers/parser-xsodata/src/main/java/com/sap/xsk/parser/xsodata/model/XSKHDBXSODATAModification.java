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

import java.util.Objects;

public class XSKHDBXSODATAModification {

  private XSKHDBXSODATAHandlerMethod method;
  private XSKHDBXSODATAModificationSpec specification;

  public XSKHDBXSODATAHandlerMethod getMethod() {
    return method;
  }

  public XSKHDBXSODATAModification setMethod(XSKHDBXSODATAHandlerMethod method) {
    this.method = method;
    return this;
  }

  public XSKHDBXSODATAModificationSpec getSpecification() {
    return specification;
  }

  public XSKHDBXSODATAModification setSpecification(XSKHDBXSODATAModificationSpec specification) {
    this.specification = specification;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    XSKHDBXSODATAModification that = (XSKHDBXSODATAModification) o;
    return method == that.method && Objects.equals(specification, that.specification);
  }

  @Override
  public int hashCode() {
    return Objects.hash(method, specification);
  }
}