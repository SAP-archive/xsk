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
package com.sap.xsk.parser.hdbtable.model;

import com.sap.xsk.parser.hdbtable.exceptions.XSKHDBTableMissingPropertyException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class XSKHDBTABLEIndexesModel {

    @Getter(AccessLevel.NONE)
    private String name;
    private boolean unique;
    private String order;
    private List<String> indexColumns;
    private String indexType;

    public String getIndexName() {
        return name;
    }

    public void setIndexName(String name) {
        this.name = name;
    }

    public void checkForAllIndexMandatoryFieldsPresence() {
        checkPresence(name, "name");
        checkPresence(unique, "unique");
        checkPresence(indexColumns, "indexColumns");
    }

    private <T> void checkPresence(T field, String fieldName) {
        if (Objects.isNull(field)) {
            throw new XSKHDBTableMissingPropertyException(String.format("Missing mandatory field %s!", fieldName));
        }
        if ((field instanceof ArrayList) && ((ArrayList) field).isEmpty()) {
            throw new XSKHDBTableMissingPropertyException(String.format("Missing mandatory field %s!", fieldName));
        }
    }
}
