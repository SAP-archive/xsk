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
package com.sap.xsk.hdb.ds.module;

import com.google.inject.multibindings.MapBinder;
import com.sap.xsk.hdb.ds.api.IXSKDataStructureModel;
import com.sap.xsk.hdb.ds.parser.XSKDataStructureParser;
import com.sap.xsk.hdb.ds.parser.hdi.XSKHDIParser;
import org.eclipse.dirigible.commons.api.module.AbstractDirigibleModule;


public class XSKHDIModule extends AbstractDirigibleModule {

  @Override
  protected void configure() {

    bindParsersToFileExtension();
  }

  private void bindParsersToFileExtension() {
    MapBinder<String, XSKDataStructureParser> mapBinder
        = MapBinder.newMapBinder(binder(), String.class, XSKDataStructureParser.class);

    mapBinder.addBinding(IXSKDataStructureModel.TYPE_HDI).to(XSKHDIParser.class).asEagerSingleton();
  }


  @Override
  public String getName() {
    return "XSK HDBTI Module";
  }
}
