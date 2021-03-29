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
            String schema = (!parsedObj.get("schema").isJsonNull()) ? handleStringLiteral(parsedObj.get("schema").getAsString()) : null;
            Integer increment_by = (!parsedObj.get("increment_by").isJsonNull()) ? parsedObj.get("increment_by").getAsInt() : null;
            Integer start_with = (!parsedObj.get("start_with").isJsonNull()) ? parsedObj.get("start_with").getAsInt() : null;
            Integer maxvalue = (!parsedObj.get("maxvalue").isJsonNull()) ? parsedObj.get("maxvalue").getAsInt() : null;
            Boolean nomaxvalue = (!parsedObj.get("nomaxvalue").isJsonNull()) ? parsedObj.get("nomaxvalue").getAsBoolean() : null;
            Integer minvalue = (!parsedObj.get("minvalue").isJsonNull()) ? parsedObj.get("minvalue").getAsInt() : null;
            Boolean nominvalue = (!parsedObj.get("nominvalue").isJsonNull()) ? parsedObj.get("nominvalue").getAsBoolean() : null;
            Boolean cycles = (!parsedObj.get("cycles").isJsonNull()) ? parsedObj.get("cycles").getAsBoolean() : null;
            String reset_by = (!parsedObj.get("reset_by").isJsonNull()) ? handleStringLiteral(parsedObj.get("reset_by").getAsString()) : null;
            Boolean publicc = (!parsedObj.get("public").isJsonNull()) ? parsedObj.get("public").getAsBoolean() : null;

            String depends_on_table = (!parsedObj.get("depends_on_table").isJsonNull()) ? handleStringLiteral(parsedObj.get("depends_on_table").getAsString()) : null;
            String depends_on_view = (!parsedObj.get("depends_on_view").isJsonNull()) ? handleStringLiteral(parsedObj.get("depends_on_view").getAsString()) : null;
            List<String> dependsOnList = new ArrayList<>();
            JsonArray depends_on = (!parsedObj.get("depends_on").isJsonNull()) ? parsedObj.get("depends_on").getAsJsonArray() : null;
            if(depends_on!=null){
                for (JsonElement tableName : depends_on) {
                    dependsOnList.add(handleStringLiteral(tableName.getAsString()));
                }
            }

            return new XSKHDBSEQUENCEModel(schema,  increment_by,  start_with,  maxvalue,
                    nomaxvalue,  minvalue,  nominvalue,  cycles,  reset_by,
                    publicc,  depends_on_table,  depends_on_view, dependsOnList);
        }
    }

}
