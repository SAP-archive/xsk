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
package com.sap.xsk.hdb.ds.parser.hdbsynonym;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.sap.xsk.utils.XSKCommonsConstants;
import com.sap.xsk.utils.XSKCommonsUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sap.xsk.hdb.ds.api.IXSKDataStructureModel;
import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdb.ds.exceptions.XSKHDBSYNONYMMissingPropertyException;
import com.sap.xsk.hdb.ds.model.XSKDBContentType;
import com.sap.xsk.hdb.ds.model.hdbsynonym.XSKDataStructureHDBSynonymModel;
import com.sap.xsk.hdb.ds.model.hdbsynonym.XSKHDBSYNONYMDefinitionModel;
import com.sap.xsk.hdb.ds.parser.XSKDataStructureParser;
import com.sap.xsk.utils.XSKHDBUtils;

public class XSKSynonymParser implements XSKDataStructureParser {
  private static final Logger logger = LoggerFactory.getLogger(XSKSynonymParser.class);

  @Override
  public XSKDataStructureHDBSynonymModel parse(String location, String content) throws XSKDataStructuresException, IOException {
    XSKDataStructureHDBSynonymModel hdbSynonymModel = new XSKDataStructureHDBSynonymModel();
    XSKHDBUtils.populateXSKDataStructureModel(location, content, hdbSynonymModel, IXSKDataStructureModel.TYPE_HDB_SYNONYM, XSKDBContentType.XS_CLASSIC);

    Gson gson = new Gson();
    JsonParser jsonParser = new JsonParser();
    JsonObject jsonObject = jsonParser.parse(content).getAsJsonObject();

    Map<String, XSKHDBSYNONYMDefinitionModel> synonymDefinitions = new HashMap<>();
    for (Entry<String, JsonElement> entry : jsonObject.entrySet()) {
      XSKHDBSYNONYMDefinitionModel definitionModel = gson.fromJson(entry.getValue(),
          XSKHDBSYNONYMDefinitionModel.class);
      try {
        definitionModel.getTarget().checkForAllMandatoryFieldsPresence();
        synonymDefinitions.put(entry.getKey(), definitionModel);
        //aligned with HANA XS Classic, where the synonym name must match the artifact name and multiple synonym definitions are not supported
        //synonymDefinitions.put(hdbSynonymModel.getName(), definitionModel);
      } catch (XSKHDBSYNONYMMissingPropertyException exception) {
        XSKCommonsUtils.logCustomErrors(location, XSKCommonsConstants.PARSER_ERROR, "", "",
            String.format("Missing mandatory field for synonym %s!", entry.getKey()), XSKCommonsConstants.EXPECTED_FIELDS,
            XSKCommonsConstants.HDB_SYNONYM_PARSER,XSKCommonsConstants.MODULE_PARSERS,XSKCommonsConstants.SOURCE_PUBLISH_REQUEST,
            XSKCommonsConstants.PROGRAM_XSK);
      }
    }
    hdbSynonymModel.setSynonymDefinitions(synonymDefinitions);
    return hdbSynonymModel;
  }

  @Override
  public String getType() {
    return IXSKDataStructureModel.TYPE_HDB_SYNONYM;
  }

  @Override
  public Class getDataStructureClass() {
    return XSKDataStructureHDBSynonymModel.class;
  }
}
