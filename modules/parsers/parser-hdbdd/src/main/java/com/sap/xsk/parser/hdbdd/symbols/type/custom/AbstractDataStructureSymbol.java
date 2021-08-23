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

import com.sap.xsk.parser.hdbdd.symbols.Symbol;
import com.sap.xsk.parser.hdbdd.symbols.context.Scope;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class AbstractDataStructureSymbol extends Symbol implements Scope {

  private Map<String, Symbol> fields = new LinkedHashMap<>();

  public AbstractDataStructureSymbol(String name) {
    super(name);
  }

  public AbstractDataStructureSymbol(String name, Scope scope) {
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

  public Map<String, Symbol> getFields() {
    return fields;
  }
}
