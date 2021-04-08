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
package com.sap.xsk.hdb.ds.model.hdbsynonym;

import com.sap.xsk.hdb.ds.model.XSKDataStructureModel;

/**
 * The synonym model representation.
 */
public class XSKDataStructureHDBSynonymModel extends XSKDataStructureModel {
    private String targetObject;
    private String targetSchema;
    private String synonymSchema;

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
}
