/*
 * Copyright (c) 2019-2020 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2019-2020 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.xsjob.ds.model;

import java.util.Map;

public class XSKJobSchedule {

    private String description;

    private String signature_version;

    private String xscron;

    private Map<String, String> parameter;

    public XSKJobSchedule() {

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSignature_version() {
        return signature_version;
    }

    public void setSignature_version(String signature_version) {
        this.signature_version = signature_version;
    }

    public String getXscron() {
        return xscron;
    }

    public void setXscron(String xscron) {
        this.xscron = xscron;
    }

    public Map<String, String> getParameter() {
        return parameter;
    }

    public void setParameter(Map<String, String> parameter) {
        this.parameter = parameter;
    }
}
