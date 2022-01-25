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
package com.sap.xsk.parser.xsodata.model;

import java.util.Objects;

public class XSKHDBXSODATABinding {

  private String entitySetName;
  private XSKHDBXSODATABindingRole bindingRole;
  private XSKHDBXSODATAMultiplicityType multiplicityType;

  public String getEntitySetName() {
    return entitySetName;
  }

  public void setEntitySetName(String entitySetName) {
    this.entitySetName = entitySetName;
  }

  public XSKHDBXSODATABindingRole getBindingRole() {
    return bindingRole;
  }

  public void setBindingRole(XSKHDBXSODATABindingRole bindingRole) {
    this.bindingRole = bindingRole;
  }

  public XSKHDBXSODATAMultiplicityType getMultiplicityType() {
    return multiplicityType;
  }

  public void setMultiplicityType(XSKHDBXSODATAMultiplicityType multiplicityType) {
    this.multiplicityType = multiplicityType;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    XSKHDBXSODATABinding that = (XSKHDBXSODATABinding) o;
    return Objects.equals(entitySetName, that.entitySetName) && Objects.equals(bindingRole, that.bindingRole)
        && multiplicityType == that.multiplicityType;
  }

  @Override
  public int hashCode() {
    return Objects.hash(entitySetName, bindingRole, multiplicityType);
  }
}
