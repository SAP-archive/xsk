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
package com.sap.xsk.hdb.ds.test.itest.hdbview;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.name.Names;
import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdb.ds.facade.IXSKHDBCoreFacade;
import com.sap.xsk.hdb.ds.test.itest.model.JDBCModel;
import com.sap.xsk.hdb.ds.test.itest.module.XSKHDBTestModule;
import com.sap.xsk.utils.XSKHDBUtils;
import org.eclipse.dirigible.commons.config.Configuration;
import org.eclipse.dirigible.core.scheduler.api.SynchronizationException;
import org.eclipse.dirigible.repository.local.LocalResource;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.sap.xsk.hdb.ds.test.itest.utils.TestConstants.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class XSKHDBViewParserHanaCase {

  private static Connection connection;
  private static IXSKHDBCoreFacade facade;

  @BeforeClass
  public static void setUpBeforeClass() throws SQLException, IOException {
    JDBCModel model = new JDBCModel(HANA_DRIVER, HANA_URL, HANA_USERNAME,
        HANA_PASSWORD);
    Injector injector = Guice.createInjector(new XSKHDBTestModule(model));
    connection = injector.getInstance(DataSource.class).getConnection();
    facade = injector.getInstance(Key.get(IXSKHDBCoreFacade.class, Names.named("xskHDBCoreFacade")));
  }

  @Before
  public void setUpBeforeTest() throws SQLException {
    Statement stmt = connection.createStatement();
    stmt.executeUpdate(String.format("drop table \"%s\".\"XSK_DATA_STRUCTURES\"", Configuration.get("hana.username")));
  }

  @Test
  public void testHDBViewCreate() throws XSKDataStructuresException, SynchronizationException, IOException, SQLException {
    Statement stmt = connection.createStatement();
    stmt.executeUpdate(String.format("create table \"%s\".\"acme.com.test.tables::MY_TABLE1\"(COLUMN1 integer,COLUMN2 integer)",
        Configuration.get("hana.username")));
    stmt.executeUpdate(String.format("create table \"%s\".\"acme.com.test.views::MY_VIEW1\"(COLUMN1 integer,COLUMN2 integer)",
        Configuration.get("hana.username")));
    LocalResource resource = XSKHDBTestModule.getResources("/usr/local/target/dirigible/repository/root",
        "/registry/public/hdbview-itest/SampleHANAXSClassicView.hdbview",
        "/registry.public.hdbview-itest/SampleHANAXSClassicView.hdbview");

    this.facade.handleResourceSynchronization(resource);
    this.facade.updateEntities();

    ResultSet rs = stmt.executeQuery(
        String.format("SELECT COUNT(*) as rawsCount FROM %s",
            XSKHDBUtils.escapeArtifactName(connection, "hdbview-itest::SampleHANAXSClassicView")));
    assertTrue(rs.next());
    assertEquals(0, rs.getInt("rawsCount"));
    stmt.executeUpdate(String.format("DROP VIEW %s", XSKHDBUtils.escapeArtifactName(connection, "hdbview-itest::SampleHANAXSClassicView")));
    stmt.executeUpdate(String.format("drop table \"%s\".\"acme.com.test.tables::MY_TABLE1\"", Configuration.get("hana.username")));
    stmt.executeUpdate(String.format("drop table \"%s\".\"acme.com.test.views::MY_VIEW1\"", Configuration.get("hana.username")));
  }
}