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
package com.sap.xsk.parser.hdbdd.symbols.annotation;

import com.sap.xsk.parser.hdbdd.symbols.Symbol;
import com.sap.xsk.parser.hdbdd.symbols.context.Scope;
import java.util.ArrayList;
import java.util.List;

public class EnumSymbol extends Symbol {
    private List<String> members = new ArrayList<>();

    public EnumSymbol(String name) {
        super(name);
    }

    public EnumSymbol(String name, Scope scope) {
        super(name, scope);
    }

    public void addMember(String member) {
        this.members.add(member);
    }

    public List<String> getMembers() {
        return members;
    }
}
