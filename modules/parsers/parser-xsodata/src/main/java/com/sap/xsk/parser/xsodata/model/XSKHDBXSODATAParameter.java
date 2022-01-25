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

public class XSKHDBXSODATAParameter {

  private String parameterEntitySetName;
  private String parameterResultsProperty;

  public String getParameterEntitySetName() {
    return parameterEntitySetName;
  }

  public XSKHDBXSODATAParameter setParameterEntitySetName(String parameterEntitySetName) {
    this.parameterEntitySetName = parameterEntitySetName;
    return this;
  }

  public String getParameterResultsProperty() {
    return parameterResultsProperty;
  }

  public XSKHDBXSODATAParameter setParameterResultsProperty(String parameterResultsProperty) {
    this.parameterResultsProperty = parameterResultsProperty;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    XSKHDBXSODATAParameter that = (XSKHDBXSODATAParameter) o;
    return Objects.equals(parameterEntitySetName, that.parameterEntitySetName) && Objects.equals(parameterResultsProperty,
        that.parameterResultsProperty);
  }

  @Override
  public int hashCode() {
    return Objects.hash(parameterEntitySetName, parameterResultsProperty);
  }
}
