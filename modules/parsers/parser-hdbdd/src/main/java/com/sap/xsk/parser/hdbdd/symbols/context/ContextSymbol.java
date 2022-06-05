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
package com.sap.xsk.parser.hdbdd.symbols.context;

import com.sap.xsk.parser.hdbdd.symbols.Symbol;
import org.jetbrains.annotations.Nullable;
import java.util.LinkedHashMap;
import java.util.Map;

public class ContextSymbol extends Symbol implements Scope {

  private Map<String, Symbol> symbols = new LinkedHashMap<>();

  public ContextSymbol(Symbol symbol) {
    super(symbol);
  }

  public ContextSymbol(String name, Scope scope) {
    super(name, scope);
  }

  @Override
  public Scope getEnclosingScope() {
    return this.getScope();
  }

  @Override
  public void define(Symbol sym) {
    this.symbols.put(sym.getName(), sym);
    sym.setScope(this);
  }

  @Override
  @Nullable
  public Symbol resolve(String name) {
    if (name.contains(".")) {
      String[] identifiers = name.split("\\.");
      Symbol outerContext = findSymbol(identifiers[0]);

      return resolveByFullSymbolName(identifiers, outerContext);
    }

    Symbol symbol = symbols.get(name);
    if (symbol != null) {
      return symbol;
    }

    // if not here, check any enclosing com.sap.xsk.parser.hdbdd.symbols.scope
    if (getEnclosingScope() != null) {
      return getEnclosingScope().resolve(name);
    }

    return null; // not found
  }

  public Map<String, Symbol> getSymbols() {
    return symbols;
  }

  @Override
  public boolean isDuplicateName(String id) {
    return symbols.containsKey(id) || getName().equals(id);
  }

  private Symbol findSymbol(String name) {
    Scope currentScope = this;
    Symbol resolvedSymbol = this.getSymbols().get(name);
    while (resolvedSymbol == null) {
      if (currentScope instanceof CDSFileScope) {
        return null;
      }

      currentScope = currentScope.getEnclosingScope();
      resolvedSymbol = currentScope.resolve(name);
    }

    return resolvedSymbol;
  }

  private Symbol resolveByFullSymbolName(String[] contexts, Symbol outerContext) {
    if (outerContext instanceof ContextSymbol) {
      Symbol resolvedSymbol = outerContext;

      for (int i = 1; i < contexts.length; i++) {
        resolvedSymbol = ((ContextSymbol) resolvedSymbol).getSymbols().get(contexts[i]);
        if (resolvedSymbol == null) {
          return null;
        }
      }

      return resolvedSymbol;
    }

    return null;
  }
}
