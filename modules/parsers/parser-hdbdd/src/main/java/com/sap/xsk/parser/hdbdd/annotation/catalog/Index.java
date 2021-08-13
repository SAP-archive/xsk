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
package com.sap.xsk.parser.hdbdd.annotation.catalog;

import java.util.List;

public class Index {
    private String name;
    private boolean unique;
    private CatalogOrderEnum order;
    private List<String> elementNames;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isUnique() {
        return unique;
    }

    public void setUnique(boolean unique) {
        this.unique = unique;
    }

    public CatalogOrderEnum getOrder() {
        return order;
    }

    public void setOrder(CatalogOrderEnum order) {
        this.order = order;
    }

    public List<String> getElementNames() {
        return elementNames;
    }

    public void setElementNames(List<String> elementNames) {
        this.elementNames = elementNames;
    }
}
