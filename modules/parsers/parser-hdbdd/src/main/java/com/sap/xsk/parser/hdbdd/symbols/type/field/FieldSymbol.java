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
package com.sap.xsk.parser.hdbdd.symbols.type.field;

import com.sap.xsk.parser.hdbdd.symbols.Symbol;
import com.sap.xsk.parser.hdbdd.symbols.context.Scope;
import com.sap.xsk.parser.hdbdd.symbols.type.Type;

public class FieldSymbol extends Symbol implements Typeable {

  private Type type;
  private String reference;

  public FieldSymbol(String name) {
    super(name);
  }

  public FieldSymbol(Symbol symbol) {
    super(symbol);
  }

  public FieldSymbol(String name, Scope scope) {
    super(name, scope);
  }

  @Override
  public Type getType() {
    return type;
  }

  @Override
  public void setType(Type type) {
    this.type = type;
  }

  @Override
  public String getReference() {
    return reference;
  }

  @Override
  public void setReference(String token) {
    this.reference = token;
  }
}