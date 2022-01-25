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
package com.sap.xsk.parser.hdbdd.symbols.annotation;

import com.sap.xsk.parser.hdbdd.symbols.Symbol;
import com.sap.xsk.parser.hdbdd.symbols.context.Scope;
import java.util.HashMap;
import java.util.Map;

public class AnnotationSymbol extends Symbol implements Scope {
    private Map<String, Symbol> elements = new HashMap<>();

    public AnnotationSymbol(String name) {
        super(name);
    }

    public AnnotationSymbol(String name, Scope scope) {
        super(name, scope);
    }

    @Override
    public Scope getEnclosingScope() {
        return null;
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
        return false;
    }
}
