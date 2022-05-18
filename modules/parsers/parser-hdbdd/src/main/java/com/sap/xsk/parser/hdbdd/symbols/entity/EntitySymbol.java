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

import com.sap.xsk.parser.hdbdd.symbols.Symbol;
import com.sap.xsk.parser.hdbdd.symbols.context.Scope;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EntitySymbol extends Symbol implements Scope {
    private Map<String, Symbol> elements;

  public EntitySymbol(Symbol symbol) {
    super(symbol);
    elements = new LinkedHashMap<>();
  }

  public EntitySymbol(String name, Scope scope) {
        super(name, scope);
        elements = new LinkedHashMap<>();
    }

    @Override
    public Scope getEnclosingScope() {
        return this.getScope();
    }

    @Override
    public void define(Symbol sym) {
        elements.put(sym.getName(), sym);
    }

    @Override
    public Symbol resolve(String name) {
        return elements.get(name);
    }

    @Override
    public boolean isDuplicateName(String id) {
        return elements.containsKey(id);
    }

    public List<EntityElementSymbol> getKeys() {
        return elements.values().stream()
                .filter(e -> e instanceof EntityElementSymbol)
                .map(e -> (EntityElementSymbol) e)
                .filter(EntityElementSymbol::isKey)
                .collect(Collectors.toList());
    }

  public List<EntityElementSymbol> getElements() {
    return elements.values().stream()
        .filter(e -> e instanceof EntityElementSymbol)
        .map(e -> (EntityElementSymbol) e)
        .collect(Collectors.toList());
  }

  public List<AssociationSymbol> getAssociations() {
    return elements.values().stream()
        .filter(e -> e instanceof AssociationSymbol)
        .map(e -> (AssociationSymbol) e)
        .collect(Collectors.toList());
  }
}
