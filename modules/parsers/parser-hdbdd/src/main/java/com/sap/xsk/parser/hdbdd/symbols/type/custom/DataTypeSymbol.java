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
package com.sap.xsk.parser.hdbdd.symbols.type.custom;

import com.sap.xsk.parser.hdbdd.symbols.Symbol;
import com.sap.xsk.parser.hdbdd.symbols.context.Scope;
import com.sap.xsk.parser.hdbdd.symbols.type.Type;
import com.sap.xsk.parser.hdbdd.symbols.type.field.Typeable;

public class DataTypeSymbol extends Symbol implements Type, CustomDataType, Typeable {
    private Type type;
    private String reference;
    public DataTypeSymbol(String name, Scope scope) {
        super(name, scope);
    }

    @Override
    public void setType(Type type) {
        this.type = type;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public String getReference() {
        return reference;
    }
}
