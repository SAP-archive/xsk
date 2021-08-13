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
package com.sap.xsk.parser.hdbdd.symbols.context;

import com.sap.xsk.parser.hdbdd.symbols.Symbol;
import java.util.LinkedHashMap;
import java.util.Map;

public class CDSFileScope implements Scope {
    Scope enclosingScope; // null if global (outermost) com.sap.xsk.parser.hdbdd.symbols.scope
    Map<String, Symbol> symbols = new LinkedHashMap<>();

    public CDSFileScope() {
    }

    public Symbol resolve(String name) {
        return symbols.get(name);
    }

    @Override
    public boolean isDuplicateName(String id) {
        return symbols.containsKey(id);
    }

    public void define(Symbol sym) {
        symbols.put(sym.getName(), sym);
        sym.setScope(this); // track the com.sap.xsk.parser.hdbdd.symbols.scope in each symbol
    }

    public void defineWithCustomName(String customName, Symbol symbol) {
        symbols.put(customName, symbol);
    }

    public Scope getEnclosingScope() {
        return enclosingScope;
    }

    public String toString() {
        return symbols.keySet().toString();
    }
}
