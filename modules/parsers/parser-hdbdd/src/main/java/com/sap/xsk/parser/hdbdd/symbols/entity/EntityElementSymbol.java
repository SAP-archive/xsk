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
package com.sap.xsk.parser.hdbdd.symbols.entity;

import com.sap.xsk.parser.hdbdd.symbols.context.Scope;
import com.sap.xsk.parser.hdbdd.symbols.type.field.FieldSymbol;

public class EntityElementSymbol extends FieldSymbol {

  private String defaultValue;
  private boolean isDefaultValueDateTimeFunction;
  private boolean isKey;
  private boolean isNotNull;

  public EntityElementSymbol(String name, Scope scope) {
    super(name, scope);
  }

  public EntityElementSymbol(EntityElementSymbol entityElementSymbol) {
    super(entityElementSymbol.getType(), entityElementSymbol.getReference(), entityElementSymbol.getName(), entityElementSymbol.getScope(),
        entityElementSymbol.getIdToken(), entityElementSymbol.getFullName(), entityElementSymbol.getAnnotations(),
        entityElementSymbol.getSchema());
    this.defaultValue = entityElementSymbol.getDefaultValue();
    this.isDefaultValueDateTimeFunction = entityElementSymbol.isDefaultValueDateTimeFunction();
    this.isKey = entityElementSymbol.isKey();
    this.isNotNull = entityElementSymbol.isNotNull();
  }

  public String getDefaultValue() {
    return defaultValue;
  }

  public void setDefaultValue(String value) {
    this.defaultValue = value;
  }

  public boolean isDefaultValueDateTimeFunction() {
    return isDefaultValueDateTimeFunction;
  }

  public void setDefaultValueDateTimeFunction(boolean defaultValueDateTimeFunction) {
    isDefaultValueDateTimeFunction = defaultValueDateTimeFunction;
  }

  public boolean isNotNull() {
    return isNotNull;
  }

  public void setNotNull(boolean notNull) {
    isNotNull = notNull;
  }

  public boolean isKey() {
    return isKey;
  }

  public void setKey(boolean key) {
    isKey = key;
  }
}