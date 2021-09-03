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
package com.sap.xsk.hdb.ds.facade;

import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdb.ds.itest.model.JDBCModel;
import com.sap.xsk.hdb.ds.itest.module.XSKHDBTestModule;
import com.sap.xsk.utils.XSKConstants;
import org.eclipse.dirigible.commons.config.Configuration;
import org.eclipse.dirigible.commons.config.StaticObjects;
import org.eclipse.dirigible.core.scheduler.api.SynchronizationException;
import org.eclipse.dirigible.database.ds.model.IDataStructureModel;
import org.eclipse.dirigible.database.sql.ISqlKeywords;
import org.eclipse.dirigible.repository.local.LocalResource;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;

import static com.sap.xsk.hdb.ds.itest.utils.TestConstants.*;
import static org.junit.Assert.assertTrue;

public class XSKHDBCoreFacadeHanaITTest {

    private static DataSource datasource;
    private static IXSKHDBCoreFacade facade;

    @BeforeClass
    public static void setUpBeforeClass() throws SQLException {
        JDBCModel model = new JDBCModel(HANA_DRIVER, HANA_URL, HANA_USERNAME,
                HANA_PASSWORD);
        XSKHDBTestModule xskhdbTestModule = new XSKHDBTestModule(model);
        xskhdbTestModule.configure();
        datasource = (DataSource) StaticObjects.get(StaticObjects.DATASOURCE);
        facade = new XSKHDBCoreFacade();
    }

    @Before
    public void setUpBeforeTest() throws SQLException {
        try (Connection connection = datasource.getConnection();
             Statement stmt = connection.createStatement()) {
            DatabaseMetaData metaData = connection.getMetaData();
            String hanaUserName = Configuration.get("hana.username");
            ResultSet table = metaData.getTables(null, hanaUserName, "XSK_DATA_STRUCTURES", null);
            if (table.next()) {
                stmt.executeUpdate(String
                        .format("DELETE FROM \"%s\".\"XSK_DATA_STRUCTURES\" WHERE DS_LOCATION IN ("
                                        + "'/acme/com/test/views/MY_VIEW1.hdbview',"
                                        + "'/acme/com/test/views/MY_VIEW2.hdbview',"
                                        + "'/acme/com/test/tables/W_TABLE1.hdbtable',"
                                        + "'/acme/com/test/tables/W_VIEW_3.hdbview',"
                                        + "'/acme/com/test/tables/MY_TABLE2.hdbtable')",
                                hanaUserName));
            }
        }
      Configuration.set(IDataStructureModel.DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE, "true");
      facade.clearCache();
    }

    @Test
    public void testUpdateEntities() throws XSKDataStructuresException, SynchronizationException, IOException, SQLException {
        try (Connection connection = datasource.getConnection();
             Statement stmt = connection.createStatement()) {
            String schemaName = Configuration.get("hana.username");
            LocalResource view1Resource = XSKHDBTestModule.getResources("/usr/local/target/dirigible/repository/root",
                    "/registry/public/acme/com/test/views/MY_VIEW1.hdbview",
                    "/hdbview-itest/MY_VIEW1.hdbview");
            LocalResource view2Resource = XSKHDBTestModule.getResources("/usr/local/target/dirigible/repository/root",
                    "/registry/public/acme/com/test/views/MY_VIEW2.hdbview",
                    "/hdbview-itest/MY_VIEW2.hdbview");

            LocalResource table1Resource = XSKHDBTestModule.getResources("/usr/local/target/dirigible/repository/root",
                    "/registry/public/acme/com/test/tables/W_TABLE1.hdbtable",
                    "/hdbview-itest/W_TABLE1.hdbtable");
            LocalResource table2Resource = XSKHDBTestModule.getResources("/usr/local/target/dirigible/repository/root",
                    "/registry/public/acme/com/test/tables/MY_TABLE2.hdbtable",
                    "/hdbview-itest/MY_TABLE2.hdbtable");
            LocalResource view3Resource = XSKHDBTestModule.getResources("/usr/local/target/dirigible/repository/root",
                    "/registry/public/acme/com/test/tables/W_VIEW_3.hdbview",
                    "/hdbview-itest/W_VIEW_3.hdbview");

            facade.handleResourceSynchronization(view3Resource);
            facade.handleResourceSynchronization(view2Resource);
            facade.handleResourceSynchronization(view1Resource);
            facade.handleResourceSynchronization(table1Resource);
            facade.handleResourceSynchronization(table2Resource);

            facade.updateEntities();

            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet view = metaData
                    .getTables(null, schemaName, "acme.com.test.views::MY_VIEW1", new String[]{ISqlKeywords.KEYWORD_VIEW});
            assertTrue(view.next());

            ResultSet synonym = metaData.getTables(null, XSKConstants.XSK_SYNONYM_PUBLIC_SCHEMA, "acme.com.test.views::MY_VIEW1",
                    new String[]{ISqlKeywords.KEYWORD_SYNONYM});
            assertTrue(synonym.next());

            stmt.executeUpdate(
                    String.format("drop SYNONYM \"%s\".\"acme.com.test.views::MY_VIEW1\"", XSKConstants.XSK_SYNONYM_PUBLIC_SCHEMA));
            stmt.executeUpdate(String.format("drop VIEW    \"%s\".\"acme.com.test.views::MY_VIEW1\"", schemaName));
            stmt.executeUpdate(
                    String.format("drop SYNONYM \"%s\".\"acme.com.test.views::MY_VIEW2\"", XSKConstants.XSK_SYNONYM_PUBLIC_SCHEMA));
            stmt.executeUpdate(String.format("drop VIEW    \"%s\".\"acme.com.test.views::MY_VIEW2\"", schemaName));
            stmt.executeUpdate(
                    String.format("drop SYNONYM \"%s\".\"acme.com.test.tables::W_TABLE1\"", XSKConstants.XSK_SYNONYM_PUBLIC_SCHEMA));
            stmt.executeUpdate(String.format("drop table \"%s\".\"acme.com.test.tables::W_TABLE1\"", schemaName));
            stmt.executeUpdate(
                    String.format("drop SYNONYM \"%s\".\"acme.com.test.tables::MY_TABLE2\"", XSKConstants.XSK_SYNONYM_PUBLIC_SCHEMA));
            stmt.executeUpdate(String.format("drop table \"%s\".\"acme.com.test.tables::MY_TABLE2\"", schemaName));
        }
    }
}