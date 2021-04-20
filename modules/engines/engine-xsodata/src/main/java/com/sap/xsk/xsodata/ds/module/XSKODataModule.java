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
package com.sap.xsk.xsodata.ds.module;

import com.google.inject.Scopes;
import com.google.inject.name.Names;
import com.sap.xsk.xsodata.ds.api.IXSKODataArtifactDao;
import com.sap.xsk.xsodata.ds.api.IXSKODataCoreService;
import com.sap.xsk.xsodata.ds.api.IXSKODataParser;
import com.sap.xsk.xsodata.ds.dao.XSKODataArtifactDao;
import com.sap.xsk.xsodata.ds.service.XSKODataCoreService;
import com.sap.xsk.xsodata.ds.service.XSKODataParser;
import org.eclipse.dirigible.commons.api.module.AbstractDirigibleModule;

public class XSKODataModule extends AbstractDirigibleModule {

    @Override
    protected void configure() {
        bind(IXSKODataArtifactDao.class).annotatedWith(Names.named("xskXSODataArtifactDao")).to(XSKODataArtifactDao.class).in(Scopes.SINGLETON);
        bind(IXSKODataParser.class).annotatedWith(Names.named("xskODataParser")).to(XSKODataParser.class).in(Scopes.SINGLETON);
        bind(IXSKODataCoreService.class).annotatedWith(Names.named("xskODataCoreService")).to(XSKODataCoreService.class).in(Scopes.SINGLETON);
    }

    @Override
    public String getName() {
        return "XSK XSODATA Module";
    }
}
