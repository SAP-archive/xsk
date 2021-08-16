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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class XSKHDBTIImportConfigModel {

    @SerializedName("table")
    @JsonProperty("table")
    private String tableName;

    @SerializedName("schema")
    @JsonProperty("schema")
    private String schemaName;

    @SerializedName("file")
    @JsonProperty("file")
    private String fileName;

    @SerializedName("header")
    @JsonProperty("header")
    private Boolean header;

    @SerializedName("useHeaderNames")
    @JsonProperty("useHeaderNames")
    private Boolean useHeaderNames;

    @SerializedName("delimField")
    @JsonProperty("delimField")
    private String delimField;

    @SerializedName("delimEnclosing")
    @JsonProperty("delimEnclosing")
    private String delimEnclosing;

    @SerializedName("distinguishEmptyFromNull")
    @JsonProperty("distinguishEmptyFromNull")
    private Boolean distinguishEmptyFromNull;

    @SerializedName("keys")
    @JsonProperty("keys")
    private List<Pair> keys = new ArrayList<>();

    @Override
    public String toString() {
        String result = "\n{\n";
        result += delimEnclosing.equals("\"") ? "\tdelimEnclosing=\"\\" + delimEnclosing + "\";\n" : "\tdelimEnclosing=\"" + delimEnclosing + "\";\n";
        result += "\tschema = \"" + schemaName + "\";\n" +
                "\tdistinguishEmptyFromNull = " + distinguishEmptyFromNull + ";\n" +
                "\theader = " + header + ";\n" +
                "\ttable = \"" + tableName + "\";\n" +
                "\tuseHeaderNames = " + useHeaderNames + ";\n" +
                "\tdelimField = \"" + delimField + "\";\n" +
                "\tkeys = " + keys.toString() + ";\n" +
                "\tfile = \"" + fileName + "\";\n" +
                "}";
        return result;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode
    public static class Pair {

        private String column;
        private ArrayList<String> values = new ArrayList<>();

        @Override
        public String toString() {
            StringBuilder result = new StringBuilder();
            for (String value : values) {
                result.append("\"").append(column).append("\":").append("\"").append(value).append("\",");
            }
            return result.substring(0, result.length() - 1);
        }
    }
}
