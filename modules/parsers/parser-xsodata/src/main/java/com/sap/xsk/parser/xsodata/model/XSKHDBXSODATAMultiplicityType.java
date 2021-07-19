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

public enum XSKHDBXSODATAMultiplicityType {
    ONE("1"),
    ZERO_TO_ONE("0..1"),
    ONE_TO_MANY("1..*"),
    MANY("*");

    private final String text;

    XSKHDBXSODATAMultiplicityType(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public static Optional<XSKHDBXSODATAMultiplicityType> fromValue(String text) {
        return Arrays.stream(values())
                .filter(bl -> bl.text.equalsIgnoreCase(text))
                .findFirst();
    }

    public String value() {
        return name();
    }

}
