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
package com.sap.xsk.parser.hdbdd.symbols;

import java.util.HashMap;
import java.util.Map;

public class Package {
    private String name;
    private Map<String, Symbol> members = new HashMap<>();

    public void addPackageMember(String fullPath, Symbol symbol) {
        members.put(fullPath, symbol);
    }

    public Symbol getPackageMember(String fullPath) {
        return members.get(fullPath);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
