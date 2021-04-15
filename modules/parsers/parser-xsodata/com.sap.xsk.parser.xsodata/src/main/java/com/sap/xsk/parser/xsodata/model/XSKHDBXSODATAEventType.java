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
package com.sap.xsk.parser.xsodata.model;

import java.util.Arrays;
import java.util.Optional;

public enum XSKHDBXSODATAEventType {
    BEFORE("before"),
    AFTER("after"),
    PRECOMMIT("precommit"),
    POSTCOMMIT("postcommit");

    private final String text;

    XSKHDBXSODATAEventType(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public static Optional<XSKHDBXSODATAEventType> fromValue(String text) {
        return Arrays.stream(values())
                .filter(bl -> bl.text.equalsIgnoreCase(text))
                .findFirst();
    }

    public String value() {
        return name();
    }

}
