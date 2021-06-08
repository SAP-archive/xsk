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
package com.sap.xsk.hdb.ds.parser.hdi.deserializer;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.sap.xsk.hdb.ds.model.hdi.XSKDataStructureHDIModel;
import java.lang.reflect.Type;


public class XSKHDIModelAdapter implements JsonDeserializer<XSKDataStructureHDIModel> {


  @Override
  public XSKDataStructureHDIModel deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context)
      throws JsonParseException {
    XSKDataStructureHDIModel hdiModel = new Gson().fromJson(jsonElement, type);
    if (hdiModel.isMandatoryFieldMissing()) {
      throw new JsonParseException("Mandatory field among configuration/users/group/container is missing!");
    }
    if (!hdiModel.hasDeploymentFile()) {
      throw new JsonParseException("Both deploy and undeploy cannot be empty arrays!");
    }

    if (hdiModel.hasMisusedDeploymentFile()) {
      throw new JsonParseException("Both deploy and undeploy cannot have to same file!");
    }
    return hdiModel;
  }
}