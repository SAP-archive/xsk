/*
 * Copyright (c) 2022 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2022 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.hdb.ds.itest.hdbtable;

import static com.sap.xsk.integration.tests.core.hdb.utils.TestConstants.HANA_USERNAME;
import static org.junit.Assert.assertTrue;

import com.sap.xsk.hdb.ds.facade.XSKHDBCoreSynchronizationFacade;
import com.sap.xsk.hdb.ds.AbstractXSKHDBITTest;
import com.sap.xsk.integration.tests.core.hdb.module.XSKHDBTestModule;
import com.sap.xsk.integration.tests.core.hdb.utils.HanaITestUtils;
import com.sap.xsk.hdb.ds.processors.table.XSKTableAlterHandler;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import nl.altindag.log.LogCaptor;
import org.eclipse.dirigible.commons.config.Configuration;
import org.eclipse.dirigible.database.ds.model.IDataStructureModel;
import org.eclipse.dirigible.repository.local.LocalResource;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class XSKHDBTableParserHanaITTest extends AbstractXSKHDBITTest {

    @Before
    public void setUpBeforeTest() throws SQLException {
        HanaITestUtils.clearDataFromXSKDataStructure(systemDatasource, Arrays.asList( //
            "'/hdbtable-itest/SamplePostgreXSClassicTable.hdbtable'", //
            "'/hdbtable-itest/compatible-length-change-hana.hdbtable'", //
            "'/hdbtable-itest/incompatible-nullable-change-hana.hdbtable'", //
            "'/hdbtable-itest/incompatible-unique-change-hana.hdbtable'", //
            "'/hdbtable-itest/incompatible-primary-key-change-hana.hdbtable'", //
            "'/hdbtable-itest/adding-new-column-to-pk-list-hana.hdbtable'", //
            "'/hdbtable-itest/adding-new-not-nullable-column-hana.hdbtable'", //
            "'/hdbtable-itest/incompatible-column-type-change-hana.hdbtable'" //
        ));
        facade.clearCache();
    }

    @Test
    public void testHDBTableCreateOnSameSchema() throws Exception {
        try (Connection connection = datasource.getConnection(); Statement stmt = connection.createStatement()) {

            String userSchema = HANA_USERNAME;

            String fileContent = String.format("table.schemaName = \"%s\";\n" + "table.temporary = true;\n"
                    + "table.tableType = COLUMNSTORE;\n" + "table.loggingType = NOLOGGING;\n" + "table.columns = [\n"
                    + " {name = \"ID\"; sqlType = INTEGER; unique= false; length = 40; nullable = false; comment = \"hello\"; defaultValue = 20; precision = 2; scale = 15;},\n"
                    + " {name = \"NAME\"; sqlType = VARCHAR; length = 20; nullable = false; },\n"
                    + " {name = \"JOB\"; sqlType = VARCHAR; length = 20; nullable = false;},\n"
                    + " {name = \"NUMBER\"; sqlType = INTEGER; length = 20; nullable = false; defaultValue = 444;}];\n"
                    + "table.primaryKey.pkcolumns = [\"ID\"];\n" + "table.description = \"test table test\";",
                userSchema);
            LocalResource resource = XSKHDBTestModule.getResourceFromString( //
                "/usr/local/target/dirigible/repository/root", //
                "/registry/public/hdbtable-itest/SamplePostgreXSClassicTable.hdbtable", //
                fileContent //
            );

            try {
                facade.handleResourceSynchronization(resource);
                facade.updateEntities();
                assertTrue(HanaITestUtils.checkExistOfTable(connection, "hdbtable-itest::SamplePostgreXSClassicTable", userSchema));
                assertTrue(HanaITestUtils.checkExistOfPublicSynonym(connection, "hdbtable-itest::SamplePostgreXSClassicTable"));
            } finally {
                HanaITestUtils.dropTable(connection, stmt, "hdbtable-itest::SamplePostgreXSClassicTable", userSchema);
            }
        }
    }

}