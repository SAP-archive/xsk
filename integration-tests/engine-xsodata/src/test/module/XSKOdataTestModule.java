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
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.eclipse.dirigible.commons.api.module.AbstractDirigibleModule;
import org.eclipse.dirigible.commons.config.StaticObjects;

public class XSKOdataTestModule extends AbstractDirigibleModule {

  private JDBCModel model;

  public XSKOdataTestModule(JDBCModel model) {
    this.model = model;
  }

  public XSKOdataTestModule() {
  }

  private static DataSource createDataSource(String url) {
    BasicDataSource basicDataSource = new BasicDataSource();
    basicDataSource.setDriverClassName("org.h2.Driver");
    basicDataSource.setUrl(url);
    basicDataSource.setUsername("sa");
    basicDataSource.setPassword("sa");
    basicDataSource.setDefaultAutoCommit(true);
    basicDataSource.setAccessToUnderlyingConnectionAllowed(true);
    return basicDataSource;
  }

  private static DataSource getDataSource() {
    return createDataSource("jdbc:h2:mem:xsk-datasource;DB_CLOSE_DELAY=-1");
  }

  private static DataSource getSystemDataSource() {
    return createDataSource("jdbc:h2:mem:xsk-system-datasource;DB_CLOSE_DELAY=-1");
  }

  @Override
  public String getName() {
    return "XSKOdataTestModule";
  }

  @Override
  public int getPriority() {
    return super.getPriority();
  }

  @Override
  public void configure() {
    StaticObjects.set(StaticObjects.DATASOURCE, getDataSource());
    StaticObjects.set(StaticObjects.SYSTEM_DATASOURCE, getDataSource());
  }
}
