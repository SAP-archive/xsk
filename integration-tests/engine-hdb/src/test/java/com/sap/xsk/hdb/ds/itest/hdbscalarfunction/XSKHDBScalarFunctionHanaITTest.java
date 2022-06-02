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
package com.sap.xsk.hdb.ds.itest.hdbscalarfunction;

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

public class XSKHDBScalarFunctionHanaITTest extends AbstractXSKHDBITTest {

    @Before
    public void setUpBeforeTest() throws SQLException {
        HanaITestUtils.clearDataFromXSKDataStructure(systemDatasource, Arrays.asList( //
            "'/hdbscalarfunction-itest/SampleHanaScalarFunction.hdbscalarfunction'" //
        ));
        Configuration.set(IDataStructureModel.DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE, "true");
        facade.clearCache();
    }

    @Test
    public void testHDBTableFunctionCreate() throws Exception {
        Connection connection = null;
        Statement stmt = null;
        try {
            connection = datasource.getConnection();
            stmt = connection.createStatement();

            HanaITestUtils.createSchema(stmt, TEST_SCHEMA);
            LocalResource resource = XSKHDBTestModule.getResources( //
                "/usr/local/target/dirigible/repository/root", //
                "/registry/public/hdbscalarfunction-itest/SampleHanaScalarFunction.hdbscalarfunction", //
                "/hdbscalarfunction-itest/SampleHanaScalarFunction.hdbscalarfunction" //
            );

            try {
                facade.handleResourceSynchronization(resource);
                facade.updateEntities();
                assertTrue(HanaITestUtils.checkExistOfFunction(stmt, "hdbscalarfunction-itest::SampleHanaScalarFunction", TEST_SCHEMA));
            } finally {
                HanaITestUtils.dropFunction(stmt, "hdbscalarfunction-itest::SampleHanaScalarFunction", TEST_SCHEMA);
            }
        } finally {
            HanaITestUtils.dropSchema(stmt, TEST_SCHEMA);
            if (stmt != null) {
                stmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}
