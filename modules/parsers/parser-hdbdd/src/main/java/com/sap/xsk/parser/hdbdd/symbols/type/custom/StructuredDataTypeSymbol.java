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
package com.sap.xsk.parser.hdbdd.symbols.type.custom;

import com.sap.xsk.parser.hdbdd.symbols.context.Scope;
import com.sap.xsk.parser.hdbdd.symbols.type.Type;

public class StructuredDataTypeSymbol extends AbstractDataStructureSymbol implements Type, CustomDataType {

  public StructuredDataTypeSymbol(String name) {
    super(name);
  }

  public StructuredDataTypeSymbol(String name, Scope scope) {
    super(name, scope);
  }

  @Override
  public Scope getEnclosingScope() {
    return this.getScope();
  }
}
