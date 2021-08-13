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
package com.sap.xsk.parser.hdbdd.annotation.metadata;

import com.sap.xsk.parser.hdbdd.symbols.CDSLiteralEnum;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnnotationObj extends AbstractAnnotationValue {
    private Map<String, AbstractAnnotationValue> keyValuePairs;
    private String name;
    private List<Class> allowedForSymbols;
    private boolean isTopLevel;

    public AnnotationObj() {
        super(CDSLiteralEnum.OBJECT.getLiteralType());
        this.keyValuePairs = new HashMap<>();
    }

    public void define(String key, AbstractAnnotationValue value) {
        keyValuePairs.put(key, value);
    }

    public AbstractAnnotationValue getValue(String key) {
        return this.keyValuePairs.get(key);
    }

    public Map<String, AbstractAnnotationValue> getKeyValuePairs() {
        return keyValuePairs;
    }

    public int getKeysNumber() {
        return this.keyValuePairs.size();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Class> getAllowedForSymbols() {
        return allowedForSymbols;
    }

    public void setAllowedForSymbols(List<Class> allowedForSymbols) {
        this.allowedForSymbols = allowedForSymbols;
    }

    public boolean isTopLevel() {
        return isTopLevel;
    }

    public void setTopLevel(boolean topLevel) {
        isTopLevel = topLevel;
    }
}
