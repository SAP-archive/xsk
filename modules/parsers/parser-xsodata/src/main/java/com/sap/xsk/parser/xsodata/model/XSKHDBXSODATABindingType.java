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
package com.sap.xsk.parser.xsodata.model;

import java.util.Arrays;
import java.util.Optional;

public enum XSKHDBXSODATABindingType {
    PRINCIPAL("principal"),
    DEPENDENT("dependent");

    private final String text;

    XSKHDBXSODATABindingType(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public static Optional<XSKHDBXSODATABindingType> fromValue(String text) {
        return Arrays.stream(values())
                .filter(bl -> bl.text.equalsIgnoreCase(text))
                .findFirst();
    }

    public String value() {
        return name();
    }

}
