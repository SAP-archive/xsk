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
package com.sap.xsk.hdb.ds.model.hdbprocedure;

import com.sap.xsk.hdb.ds.model.XSKDataStructureModel;

import java.sql.Timestamp;

public class XSKDataStructureHDBProcedureModel extends XSKDataStructureModel {

    private String content;

    public XSKDataStructureHDBProcedureModel(String location, String name, String type, String hash, String createdBy, Timestamp createdAt, String schema, String content, String rawContent) {
        super(location, name, type, hash, createdBy, createdAt, schema, rawContent);
        this.content = content;
    }

    public XSKDataStructureHDBProcedureModel() {
        super();
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
