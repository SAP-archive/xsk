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
package com.sap.xsk.parser.hdbtable.model;

import com.sap.xsk.parser.hdbtable.exceptions.XSKHDBTableMissingPropertyException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class XSKHDBTABLEDefinitionModel {
    private String schemaName;
    private String tableType;
    private List<String> pkcolumns = new ArrayList<>();
    private String indexType;
    private List<XSKHDBTABLEColumnsModel> columns = new ArrayList<>();
    private List<XSKHDBTABLEIndexesModel> indexes = new ArrayList<>();
    private String description;
    private Boolean publicProp;
    private String loggingType;
    private Boolean temporary;

    public void checkForAllMandatoryFieldsPresence() {
        checkPresence(schemaName, "schemaName");
        checkPresence(columns, "columns");
    }

    private <T> void checkPresence(T field, String fieldName) {
        if (Objects.isNull(field)) {
            throw new XSKHDBTableMissingPropertyException(String.format("Missing mandatory field %s!", fieldName));
        }
        if ((field instanceof ArrayList) && ((ArrayList) field).isEmpty()) {
            throw new XSKHDBTableMissingPropertyException(String.format("Missing mandatory field %s!", fieldName));
        }
    }
}
