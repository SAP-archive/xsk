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

package module;

import com.sap.xsk.hdb.ds.test.itest.model.JDBCModel;
import org.apache.commons.dbcp2.BasicDataSource;
import org.eclipse.dirigible.commons.api.module.AbstractDirigibleModule;
import org.eclipse.dirigible.commons.config.StaticObjects;

import javax.sql.DataSource;

public class XSKHDBTestModule extends AbstractDirigibleModule {

    private final JDBCModel model;

    public XSKHDBTestModule(JDBCModel model) {
        this.model = model;
    }

    public DataSource getDataSource() {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName(this.model.getDriverClassName());
        basicDataSource.setUrl(this.model.getJdbcUrl());
        basicDataSource.setUsername(this.model.getUsername());
        basicDataSource.setPassword(this.model.getPassword());
        basicDataSource.setDefaultAutoCommit(true);
        basicDataSource.setAccessToUnderlyingConnectionAllowed(true);
        return basicDataSource;
    }

    @Override
    public String getName() {
        return "XSKHDBTestModule";
    }

    @Override
    public void configure() {
        StaticObjects.set(StaticObjects.DATASOURCE, getDataSource());
    }
}
