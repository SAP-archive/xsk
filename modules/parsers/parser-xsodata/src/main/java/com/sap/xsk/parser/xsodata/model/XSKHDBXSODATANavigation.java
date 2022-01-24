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

import java.util.Objects;

/**
 * Representing association property of a service. For example:
 * <pre>{@code
 * service {
 *      "namespace.name::customer" as "Customers"
 *          navigates ("Orders_Customers" as "HisOrders");
 *      "namespace.name::order" as "Orders";
 *      association "Orders_Customers" with referential constraint
 *           dependent  "Customers"("OrderID") multiplicity "*"
 *           principal "Orders"("ID") multiplicity "1";
 *  }
 * }</pre>
 * In the example, the association is "Orders_Customers" and the alias is "HisOrders".
 */

public class XSKHDBXSODATANavigation {

  private String association;
  private String aliasNavigation;
  private XSKHDBXSODATABindingType fromBindingType;

  public String getAssociation() {
    return association;
  }

  public XSKHDBXSODATANavigation setAssociation(String association) {
    this.association = association;
    return this;
  }

  public String getAliasNavigation() {
    return aliasNavigation;
  }

  public XSKHDBXSODATANavigation setAliasNavigation(String aliasNavigation) {
    this.aliasNavigation = aliasNavigation;
    return this;
  }

  public XSKHDBXSODATABindingType getFromBindingType() {
    return fromBindingType;
  }

  public XSKHDBXSODATANavigation setFromBindingType(XSKHDBXSODATABindingType fromBindingType) {
    this.fromBindingType = fromBindingType;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    XSKHDBXSODATANavigation that = (XSKHDBXSODATANavigation) o;
    return Objects.equals(association, that.association) && Objects.equals(aliasNavigation, that.aliasNavigation)
        && fromBindingType == that.fromBindingType;
  }

  @Override
  public int hashCode() {
    return Objects.hash(association, aliasNavigation, fromBindingType);
  }
}
