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
package com.sap.xsk.hdb.ds.service.parser;

import java.io.IOException;
import java.util.Map;

import com.sap.xsk.exceptions.XSKArtifactParserException;
import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdb.ds.model.XSKDataStructureModel;
import com.sap.xsk.hdb.ds.module.XSKHDBModule;
import com.sap.xsk.hdb.ds.parser.XSKDataStructureParser;

public class XSKCoreParserService implements IXSKCoreParserService {

  private Map<String, XSKDataStructureParser> parserServices = XSKHDBModule.getParserServices();

  @Override
  public XSKDataStructureModel parseDataStructure(String type, String location, String content)
          throws XSKDataStructuresException, IOException, XSKArtifactParserException {
    if (!parserServices.containsKey(type)) {
      return null;
    }

    XSKDataStructureParser<?> parser = parserServices.get(type);
    return parser.parse(location, content);
  }

  @Override
  public Class<XSKDataStructureModel> getDataStructureClass(String type) {
    return (Class<XSKDataStructureModel>) parserServices.get(type).getDataStructureClass();
  }
}
