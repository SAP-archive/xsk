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

/**
 * The text should reflect the values from org.eclipse.dirigible.engine.odata2.definition.ODataHandlerMethods
 */
public enum XSKHDBXSODATAHandlerMethod {
    CREATE("create", "sap:creatable"),
    UPDATE("update", "sap:updatable"),
    DELETE("delete", "sap:deletable");

    private final String odataHandlerType;

    private final String odataSAPAnnotation;

    XSKHDBXSODATAHandlerMethod(String odataHandlerType, String odataSAPAnnotation) {
        this.odataHandlerType = odataHandlerType;
        this.odataSAPAnnotation = odataSAPAnnotation;
    }

    public String getOdataHandlerType() {
        return odataHandlerType;
    }

    public String getOdataSAPAnnotation() {
        return odataSAPAnnotation;
    }

    public String value() {
        return name();
    }
}