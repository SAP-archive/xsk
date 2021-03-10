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
package com.sap.xsk.parser.hdbsequence.custom;

import com.google.gson.*;
import com.sap.xsk.parser.hdbsequence.models.XSKHDBSEQUENCEModel;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CustomDeserializers {

    public static class XSKHDBSEQUENCEModelAdapter implements JsonDeserializer<XSKHDBSEQUENCEModel> {
        private String handleStringLiteral(String value) {
            if (value != null && value.length() > 1) {
                String subStr = value.substring(1, value.length() - 1);
                String escapedQuote = subStr.replace("\\\"", "\"");
                return escapedQuote.replace("\\\\", "\\");
            }

            return value;
        }

        @Override
        public XSKHDBSEQUENCEModel deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context)
                throws JsonParseException {
            JsonObject parsedObj = jsonElement.getAsJsonObject();
            String schema = handleStringLiteral(parsedObj.get("schema").getAsString());
            Integer increment_by = parsedObj.get("increment_by").getAsInt();
            Integer start_with = parsedObj.get("start_with").getAsInt();
            Integer maxvalue = parsedObj.get("maxvalue").getAsInt();
            Boolean nomaxvalue = parsedObj.get("nomaxvalue").getAsBoolean();
            Integer minvalue = parsedObj.get("minvalue").getAsInt();
            Boolean nominvalue = parsedObj.get("nominvalue").getAsBoolean();
            Boolean cycles = parsedObj.get("cycles").getAsBoolean();
            String reset_by = handleStringLiteral(parsedObj.get("reset_by").getAsString());
            Boolean publicc = parsedObj.get("public").getAsBoolean();

            String depends_on_table = handleStringLiteral(parsedObj.get("depends_on_table").getAsString());
            String depends_on_view = handleStringLiteral(parsedObj.get("depends_on_view").getAsString());
            List<String> dependsOnList = new ArrayList<>();
            JsonArray depends_on = parsedObj.get("depends_on").getAsJsonArray();
            for (JsonElement tableName : depends_on) {
                dependsOnList.add(handleStringLiteral(tableName.getAsString()));
            }
            return new XSKHDBSEQUENCEModel(schema,  increment_by,  start_with,  maxvalue,
                     nomaxvalue,  minvalue,  nominvalue,  cycles,  reset_by,
                     publicc,  depends_on_table,  depends_on_view, dependsOnList);
        }
    }

}
