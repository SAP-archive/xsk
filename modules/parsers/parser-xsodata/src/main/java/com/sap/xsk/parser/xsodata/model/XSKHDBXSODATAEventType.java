/*
 * Copyright (c) 2022 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2022 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.parser.xsodata.model;

import java.util.Arrays;
import java.util.Optional;
/**
 * The text should reflect the values from org.eclipse.dirigible.engine.odata2.definition.ODataHandlerTypes
 */
public enum XSKHDBXSODATAEventType {
    BEFORE("before", "before"),
    AFTER("after", "after"),
    PRECOMMIT("precommit", ""),
    POSTCOMMIT("postcommit", "");

    private final String text;
    private final String odataHandlerType;

    XSKHDBXSODATAEventType(String text, String odataHandlerType) {
        this.text = text;
        this.odataHandlerType = odataHandlerType;
    }

    public String getText() {
        return this.text;
    }

    public String getOdataHandlerType() {
        return odataHandlerType;
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