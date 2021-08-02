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
package com.sap.xsk.xsodata.ds.module;

import org.eclipse.dirigible.commons.api.module.AbstractDirigibleModule;
import org.eclipse.dirigible.commons.config.StaticObjects;

import com.sap.xsk.xsodata.ds.dao.XSKODataArtifactDao;
import com.sap.xsk.xsodata.ds.service.XSKODataCoreService;
import com.sap.xsk.xsodata.ds.service.XSKODataParser;

public class XSKODataModule extends AbstractDirigibleModule {

    @Override
    public void configure() {
    	StaticObjects.set("xskXSODataArtifactDao", new XSKODataArtifactDao());
    	StaticObjects.set("xskODataParser", new XSKODataParser());
    	StaticObjects.set("xskODataCoreService", new XSKODataCoreService());
    	
//        bind(IXSKODataArtifactDao.class).annotatedWith(Names.named("xskXSODataArtifactDao")).to(XSKODataArtifactDao.class).in(Scopes.SINGLETON);
//        bind(IXSKODataParser.class).annotatedWith(Names.named("xskODataParser")).to(XSKODataParser.class).in(Scopes.SINGLETON);
//        bind(IXSKODataCoreService.class).annotatedWith(Names.named("xskODataCoreService")).to(XSKODataCoreService.class).in(Scopes.SINGLETON);
    }

    @Override
    public String getName() {
        return "XSK XSODATA Module";
    }
}
