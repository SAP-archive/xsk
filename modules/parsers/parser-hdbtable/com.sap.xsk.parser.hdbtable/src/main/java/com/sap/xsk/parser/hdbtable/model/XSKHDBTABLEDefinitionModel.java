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

import com.google.gson.JsonElement;

public class XSKHDBTABLEDefinitionModel {
    private JsonElement schemaName;
    private JsonElement tableType;
    private JsonElement pkcolumns;
    private JsonElement columns;
    private JsonElement indexes;
    private JsonElement description;
    private JsonElement publicProp;
    private JsonElement loggingType;
    private JsonElement temporary;


    public XSKHDBTABLEDefinitionModel() {
    }

    public JsonElement getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(JsonElement schemaName) {
        this.schemaName = schemaName;
    }

    public JsonElement getTableType() {
        return tableType;
    }

    public void setTableType(JsonElement tableType) {
        this.tableType = tableType;
    }

    public JsonElement getPkcolumns() {
        return pkcolumns;
    }

    public void setPkcolumns(JsonElement pkcolumns) {
        this.pkcolumns = pkcolumns;
    }

    public JsonElement getColumns() {
        return columns;
    }

    public void setColumns(JsonElement columns) {
        this.columns = columns;
    }

    public JsonElement getIndexes() {
        return indexes;
    }

    public void setIndexes(JsonElement indexes) {
        this.indexes = indexes;
    }

    public JsonElement getDescription() {
        return description;
    }

    public void setDescription(JsonElement description) {
        this.description = description;
    }

    public JsonElement getPublicProp() {
        return publicProp;
    }

    public void setPublicProp(JsonElement publicProp) {
        this.publicProp = publicProp;
    }

    public JsonElement getLoggingType() {
        return loggingType;
    }

    public void setLoggingType(JsonElement loggingType) {
        this.loggingType = loggingType;
    }

    public JsonElement getTemporary() {
        return temporary;
    }

    public void setTemporary(JsonElement temporary) {
        this.temporary = temporary;
    }
}
