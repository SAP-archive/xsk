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
package com.sap.xsk.hdb.ds.itest.hdbtable;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.name.Names;
import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdb.ds.facade.IXSKHDBCoreFacade;
import com.sap.xsk.hdb.ds.itest.model.JDBCModel;
import com.sap.xsk.hdb.ds.itest.module.XSKHDBTestModule;
import com.sap.xsk.hdb.ds.itest.utils.TestConstants;
import com.sap.xsk.utils.XSKConstants;
import org.eclipse.dirigible.commons.config.Configuration;
import org.eclipse.dirigible.core.scheduler.api.SynchronizationException;
import org.eclipse.dirigible.database.sql.ISqlKeywords;
import org.eclipse.dirigible.repository.local.LocalResource;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;

import static org.junit.Assert.assertTrue;

public class XSKHDBTableParserHanaITTest {

    private static Connection connection;
    private static IXSKHDBCoreFacade facade;

    @BeforeClass
    public static void setUpBeforeClass() throws SQLException {
        JDBCModel model = new JDBCModel(TestConstants.HANA_DRIVER,
                TestConstants.HANA_URL,
                TestConstants.HANA_USERNAME,
                TestConstants.HANA_PASSWORD);
        Injector injector = Guice.createInjector(new XSKHDBTestModule(model));
        connection = injector.getInstance(DataSource.class).getConnection();
        facade = injector.getInstance(Key.get(IXSKHDBCoreFacade.class, Names.named("xskHDBCoreFacade")));
    }

    @Before
    public void setUpBeforeTest() throws SQLException {
        Statement stmt = connection.createStatement();
        DatabaseMetaData metaData = connection.getMetaData();
        String hanaUserName = Configuration.get("hana.username");
        ResultSet table = metaData.getTables(null, hanaUserName, "XSK_DATA_STRUCTURES", null);
        if (table.next()) {
            stmt.executeUpdate(String.format("DELETE FROM \"%s\".\"XSK_DATA_STRUCTURES\" WHERE DS_LOCATION ='/hdbtable-itest/SamplePostgreXSClassicTable.hdbtable'", hanaUserName));
        }
    }

    @Test
    public void testHDBTableCreateOnSameSchema() throws XSKDataStructuresException, SynchronizationException, IOException, SQLException {
        Statement stmt = connection.createStatement();
        String schemaName = "test";
        stmt.executeUpdate(String.format("CREATE SCHEMA \"%s\"", schemaName));

        LocalResource resource = XSKHDBTestModule.getResources("/usr/local/target/dirigible/repository/root",
                "/registry/public/hdbtable-itest/SamplePostgreXSClassicTable.hdbtable",
                "/hdbtable-itest/SamplePostgreXSClassicTable.hdbtable");

        facade.handleResourceSynchronization(resource);
        facade.updateEntities();

        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet table = metaData.getTables(null, schemaName, "hdbtable-itest::SamplePostgreXSClassicTable", new String[]{ISqlKeywords.KEYWORD_TABLE});
        assertTrue(table.next());

        ResultSet synonym = metaData.getTables(null, XSKConstants.XSK_SYNONYM_PUBLIC_SCHEMA, "hdbtable-itest::SamplePostgreXSClassicTable", new String[]{ISqlKeywords.KEYWORD_SYNONYM});
        assertTrue(synonym.next());

        stmt.executeUpdate(String.format("drop SYNONYM \"%s\".\"hdbtable-itest::SamplePostgreXSClassicTable\"", XSKConstants.XSK_SYNONYM_PUBLIC_SCHEMA));
        stmt.executeUpdate(String.format("drop table \"%s\".\"hdbtable-itest::SamplePostgreXSClassicTable\"", schemaName));
        stmt.executeUpdate(String.format("DROP SCHEMA \"%s\"", schemaName));
    }
}
