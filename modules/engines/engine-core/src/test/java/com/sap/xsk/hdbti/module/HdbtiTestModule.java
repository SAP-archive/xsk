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
package com.sap.xsk.hdbti.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.sap.xsk.hdbti.module.XSKHdbtiModule;
import com.sap.xsk.hdbti.repository.TestRepository;
import org.apache.commons.dbcp2.BasicDataSource;
import org.eclipse.dirigible.engine.odata2.transformers.DBMetadataUtil;
import org.eclipse.dirigible.repository.api.IRepository;

import javax.sql.DataSource;

public class HdbtiTestModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(IRepository.class).to(TestRepository.class);
        bind(DBMetadataUtil.class);
        install(new XSKHdbtiModule());
    }

    @Provides
    static DataSource getDataSource() {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName("org.h2.Driver");
        basicDataSource.setUrl("jdbc:h2:mem:dirigible;DB_CLOSE_DELAY=-1");
        basicDataSource.setUsername("sa");
        basicDataSource.setPassword("sa");
        basicDataSource.setDefaultAutoCommit(true);
        basicDataSource.setAccessToUnderlyingConnectionAllowed(true);

        return basicDataSource;
    }
}
