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
package com.sap.xsk.hdb.ds.module;

import java.util.Map;

import org.eclipse.dirigible.commons.api.module.AbstractDirigibleModule;

import com.sap.xsk.hdb.ds.api.IXSKDataStructureModel;
import com.sap.xsk.hdb.ds.parser.XSKDataStructureParser;
import com.sap.xsk.hdb.ds.parser.hdi.XSKHDIParser;


public class XSKHDIModule extends AbstractDirigibleModule {

  @Override
  public void configure() {

    bindParsersToFileExtension();
  }

  private void bindParsersToFileExtension() {
//    MapBinder<String, XSKDataStructureParser> mapBinder
//        = MapBinder.newMapBinder(binder(), String.class, XSKDataStructureParser.class);

	  Map<String, XSKDataStructureParser> parserServices = XSKHDBModule.getParserServices();
      parserServices.put(IXSKDataStructureModel.TYPE_HDI, new XSKHDIParser());
  }


  @Override
  public String getName() {
    return "XSK HDBTI Module";
  }
}
