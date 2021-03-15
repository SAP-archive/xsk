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

import java.util.ArrayList;
import java.util.List;

public class XSKHDBTABLEDefinitionModel {
    private String schemaName;
    private String tableType;
    private List<String> pkcolumns;
    private String indexType;
    private List<XSKHDBTABLEColumnsModel> columns;
    private List<XSKHDBTABLEIndexesModel> indexes;
    private String description;
    private Boolean publicProp;
    private String loggingType;
    private Boolean temporary;


    public XSKHDBTABLEDefinitionModel() {
    }

    public String getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    public String getTableType() {
        return tableType;
    }

    public void setTableType(String tableType) {
        this.tableType = tableType;
    }

    public List<String> getPkcolumns() {
        return pkcolumns;
    }

    public void setPkcolumns(List<String> pkcolumns) {
        this.pkcolumns = pkcolumns;
    }

    public String getIndexType() {
        return indexType;
    }

    public void setIndexType(String indexType) {
        this.indexType = indexType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getPublicProp() {
        return publicProp;
    }

    public void setPublicProp(Boolean publicProp) {
        this.publicProp = publicProp;
    }

    public String getLoggingType() {
        return loggingType;
    }

    public void setLoggingType(String loggingType) {
        this.loggingType = loggingType;
    }

    public Boolean getTemporary() {
        return temporary;
    }

    public void setTemporary(Boolean temporary) {
        this.temporary = temporary;
    }

    public void setColumns(List<XSKHDBTABLEColumnsModel> columns) {
        this.columns = columns;
    }

    public void setIndexes(List<XSKHDBTABLEIndexesModel> indexes) {
        this.indexes = indexes;
    }

    public List<XSKHDBTABLEColumnsModel> getColumns() {
        return columns;
    }

    public List<XSKHDBTABLEIndexesModel> getIndexes() {
        return indexes;
    }
}
