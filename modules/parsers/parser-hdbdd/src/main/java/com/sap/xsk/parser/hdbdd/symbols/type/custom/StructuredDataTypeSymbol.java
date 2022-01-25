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
package com.sap.xsk.parser.hdbdd.symbols.type.custom;

import com.sap.xsk.parser.hdbdd.symbols.Symbol;
import com.sap.xsk.parser.hdbdd.symbols.context.Scope;
import com.sap.xsk.parser.hdbdd.symbols.type.Type;
import com.sap.xsk.parser.hdbdd.symbols.type.field.FieldSymbol;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StructuredDataTypeSymbol extends Symbol implements Type, Scope, CustomDataType {

  private Map<String, Symbol> fields = new LinkedHashMap<>();

  public StructuredDataTypeSymbol(Symbol symbol) {
    super(symbol);
  }

  public StructuredDataTypeSymbol(String name, Scope scope) {
    super(name, scope);
  }

  @Override
  public Scope getEnclosingScope() {
    return this.getScope();
  }

  @Override
  public void define(Symbol sym) {
    fields.put(sym.getName(), sym);
    sym.setScope(this);
  }

  @Override
  public Symbol resolve(String name) {
    return fields.get(name);
  }

  @Override
  public boolean isDuplicateName(String id) {
    return fields.containsKey(id) || getName().equals(id);
  }

  public List<FieldSymbol> getFields() {
    return fields.values().stream().map(f -> (FieldSymbol) f).collect(Collectors.toList());
  }

}
