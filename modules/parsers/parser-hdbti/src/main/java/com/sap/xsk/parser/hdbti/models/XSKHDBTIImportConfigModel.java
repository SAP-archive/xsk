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
package com.sap.xsk.parser.hdbti.models;

import com.google.gson.annotations.SerializedName;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class XSKHDBTIImportConfigModel {

    @SerializedName("table")
    private String tableName;

    @SerializedName("schema")
    private String schemaName;

    @SerializedName("file")
    private String fileName;

    @SerializedName("header")
    private Boolean header;

    @SerializedName("useHeaderNames")
    private Boolean useHeaderNames;

    @SerializedName("delimField")
    private String delimField;

    @SerializedName("delimEnclosing")
    private String delimEnclosing;

    @SerializedName("distinguishEmptyFromNull")
    private Boolean distinguishEmptyFromNull;

    private List<Pair> keys;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode
    public static class Pair {

        private String column;
        private ArrayList<String> values = new ArrayList<>();
    }
}
