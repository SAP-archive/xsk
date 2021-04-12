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


import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.sap.xsk.parser.hdbsequence.models.XSKHDBSEQUENCEModel;
import com.sap.xsk.parser.hdbsequence.utils.HDBSequenceConstants;
import java.lang.reflect.Type;

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
      String schema = (!parsedObj.get(HDBSequenceConstants.SCHEMA_PROPERTY).isJsonNull())
          ? handleStringLiteral(parsedObj.get(HDBSequenceConstants.SCHEMA_PROPERTY).getAsString())
          : null;
      Integer increment_by = (!parsedObj.get(HDBSequenceConstants.INCREMENT_BY_PROPERTY).isJsonNull())
          ? parsedObj.get(HDBSequenceConstants.INCREMENT_BY_PROPERTY).getAsInt()
          : null;
      Integer start_with = (!parsedObj.get(HDBSequenceConstants.START_WITH_PROPERTY).isJsonNull())
          ? parsedObj.get(HDBSequenceConstants.START_WITH_PROPERTY).getAsInt()
          : null;
      Integer maxvalue = (!parsedObj.get(HDBSequenceConstants.MAXVALUE_PROPERTY).isJsonNull())
          ? parsedObj.get(HDBSequenceConstants.MAXVALUE_PROPERTY).getAsInt()
          : null;
      Boolean nomaxvalue = (!parsedObj.get(HDBSequenceConstants.NOMAXVALUE_PROPERTY).isJsonNull())
          ? parsedObj.get(HDBSequenceConstants.NOMAXVALUE_PROPERTY).getAsBoolean()
          : null;
      Integer minvalue = (!parsedObj.get(HDBSequenceConstants.MINVALUE_PROPERTY).isJsonNull())
          ? parsedObj.get(HDBSequenceConstants.MINVALUE_PROPERTY).getAsInt()
          : null;
      Boolean nominvalue = (!parsedObj.get(HDBSequenceConstants.NOMINVALUE_PROPERTY).isJsonNull())
          ? parsedObj.get(HDBSequenceConstants.NOMINVALUE_PROPERTY).getAsBoolean()
          : null;
      Boolean cycles = (!parsedObj.get(HDBSequenceConstants.CYCLES_PROPERTY).isJsonNull())
          ? parsedObj.get(HDBSequenceConstants.CYCLES_PROPERTY).getAsBoolean()
          : null;
      String reset_by = (!parsedObj.get(HDBSequenceConstants.RESET_BY_PROPERTY).isJsonNull())
          ? handleStringLiteral(parsedObj.get(HDBSequenceConstants.RESET_BY_PROPERTY).getAsString())
          : null;
      Boolean publicc = (!parsedObj.get(HDBSequenceConstants.PUBLIC_PROPERTY).isJsonNull())
          ? parsedObj.get(HDBSequenceConstants.PUBLIC_PROPERTY).getAsBoolean()
          : null;



      return new XSKHDBSEQUENCEModel(schema, increment_by, start_with, maxvalue,
          nomaxvalue, minvalue, nominvalue, cycles, reset_by,
          publicc);
    }
  }

}
