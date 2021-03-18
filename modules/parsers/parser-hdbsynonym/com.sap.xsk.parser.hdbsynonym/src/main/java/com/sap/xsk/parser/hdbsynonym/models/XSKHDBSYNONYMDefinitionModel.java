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
package com.sap.xsk.parser.hdbsynonym.models;

public class XSKHDBSYNONYMDefinitionModel {
    private String location;
    private String targetObject;
    private String targetSchema;
    private String synonymSchema;

    public XSKHDBSYNONYMDefinitionModel(String location) {
        this.location = location;
    }

    public XSKHDBSYNONYMDefinitionModel() {
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTargetObject() {
        return targetObject;
    }

    public void setTargetObject(String targetObject) {
        this.targetObject = targetObject;
    }

    public String getTargetSchema() {
        return targetSchema;
    }

    public void setTargetSchema(String targetSchema) {
        this.targetSchema = targetSchema;
    }

    public String getSynonymSchema() {
        return synonymSchema;
    }

    public void setSynonymSchema(String synonymSchema) {
        this.synonymSchema = synonymSchema;
    }

    @Override
    public String toString() {
        return "XSKHDBSYNONYMDefinitionModel{" +
                "location='" + location + '\'' +
                ", targetObject='" + targetObject + '\'' +
                ", targetSchema='" + targetSchema + '\'' +
                ", synonymSchema='" + synonymSchema + '\'' +
                '}';
    }
}
