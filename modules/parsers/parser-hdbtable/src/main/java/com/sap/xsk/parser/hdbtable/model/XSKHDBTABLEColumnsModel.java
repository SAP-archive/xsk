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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class XSKHDBTABLEColumnsModel {

    private String name;
    private String sqlType;
    private boolean nullable;
    private boolean unique;
    private String length;
    private String comment;
    private String defaultValue;
    private String precision;
    private String scale;

    public void checkForAllMandatoryColumnFieldsPresence() {
        checkPresence(name, "name");
        checkPresence(sqlType, "sqlType");
    }

    private <T> void checkPresence(T field, String fieldName) {
        if (Objects.isNull(field)) {
            throw new XSKHDBTableMissingPropertyException(String.format("Missing mandatory field %s!", fieldName));
        }
    }
}
