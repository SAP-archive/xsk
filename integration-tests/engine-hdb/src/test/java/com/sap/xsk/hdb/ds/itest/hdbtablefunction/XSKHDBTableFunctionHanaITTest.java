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
package com.sap.xsk.hdb.ds.itest.hdbtablefunction;

import static org.junit.Assert.assertTrue;

import com.sap.xsk.hdb.ds.itest.AbstractXSKHDBITTest;
import com.sap.xsk.integration.tests.core.hdb.module.XSKHDBTestModule;
import com.sap.xsk.integration.tests.core.hdb.utils.HanaITestUtils;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import org.eclipse.dirigible.commons.config.Configuration;
import org.eclipse.dirigible.database.ds.model.IDataStructureModel;
import org.eclipse.dirigible.repository.local.LocalResource;
import org.junit.Before;
import org.junit.Test;

public class XSKHDBTableFunctionHanaITTest extends AbstractXSKHDBITTest {

    @Before
    public void setUpBeforeTest() throws SQLException {
        HanaITestUtils.clearDataFromXSKDataStructure(systemDatasource, Arrays.asList( //
            "'/hdbtablefunction-itest/SampleHanaTableFunction.hdbtablefunction'" //
        ));
        Configuration.set(IDataStructureModel.DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE, "true");
        facade.clearCache();
    }

    @Test
    public void testHDBTableFunctionCreate() throws Exception {
        try (Connection connection = datasource.getConnection(); Statement stmt = connection.createStatement()) {

            HanaITestUtils.createSchema(stmt, TEST_SCHEMA);
            HanaITestUtils.createEmptyTable(stmt, "hdbtablefunction-itest::SampleHanaTable", TEST_SCHEMA);

            LocalResource resource = XSKHDBTestModule.getResources( //
                "/usr/local/target/dirigible/repository/root", //
                "/registry/public/hdbtablefunction-itest/SampleHanaTableFunction.hdbtablefunction", //
                "/hdbtablefunction-itest/SampleHanaTableFunction.hdbtablefunction" //
            );

            try {
                facade.handleResourceSynchronization(resource);
                facade.updateEntities();
                assertTrue(HanaITestUtils.checkExistOfFunction(stmt, "hdbtablefunction-itest::SampleHanaTableFunction", TEST_SCHEMA));
            } finally {
                HanaITestUtils.dropFunction(stmt, "hdbtablefunction-itest::SampleHanaTableFunction", TEST_SCHEMA);
                HanaITestUtils.dropTable(connection, stmt, "hdbtablefunction-itest::SampleHanaTable", TEST_SCHEMA);
                HanaITestUtils.dropSchema(stmt, TEST_SCHEMA);
            }
        }
    }
}
