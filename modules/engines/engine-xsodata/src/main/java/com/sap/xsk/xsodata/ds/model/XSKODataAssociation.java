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
package com.sap.xsk.xsodata.ds.model;

public class XSKODataAssociation {

  private String name;

  private String principal;

  private String principalKey;

  private String principalMultiplicity;

  private String dependent;

  private String dependentProperty;

  private String dependentMultiplicity;

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return the principal
   */
  public String getPrincipal() {
    return principal;
  }

  /**
   * @param principal the principal to set
   */
  public void setPrincipal(String principal) {
    this.principal = principal;
  }

  /**
   * @return the principalKey
   */
  public String getPrincipalKey() {
    return principalKey;
  }

  /**
   * @param principalKey the principalKey to set
   */
  public void setPrincipalKey(String principalKey) {
    this.principalKey = principalKey;
  }

  /**
   * @return the principalMultiplicity
   */
  public String getPrincipalMultiplicity() {
    return principalMultiplicity;
  }

  /**
   * @param principalMultiplicity the principalMultiplicity to set
   */
  public void setPrincipalMultiplicity(String principalMultiplicity) {
    this.principalMultiplicity = principalMultiplicity;
  }

  /**
   * @return the dependent
   */
  public String getDependent() {
    return dependent;
  }

  /**
   * @param dependent the dependent to set
   */
  public void setDependent(String dependent) {
    this.dependent = dependent;
  }

  /**
   * @return the dependentProperty
   */
  public String getDependentProperty() {
    return dependentProperty;
  }

  /**
   * @param dependentProperty the dependentProperty to set
   */
  public void setDependentProperty(String dependentProperty) {
    this.dependentProperty = dependentProperty;
  }

  /**
   * @return the dependentMultiplicity
   */
  public String getDependentMultiplicity() {
    return dependentMultiplicity;
  }

  /**
   * @param dependentMultiplicity the dependentMultiplicity to set
   */
  public void setDependentMultiplicity(String dependentMultiplicity) {
    this.dependentMultiplicity = dependentMultiplicity;
  }


}
