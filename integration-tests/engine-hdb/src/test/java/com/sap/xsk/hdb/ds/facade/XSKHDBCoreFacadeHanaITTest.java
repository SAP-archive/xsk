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
package com.sap.xsk.hdb.ds.facade;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

import org.eclipse.dirigible.commons.config.Configuration;
import org.eclipse.dirigible.database.ds.model.IDataStructureModel;
import org.eclipse.dirigible.repository.local.LocalResource;
import org.junit.Before;
import org.junit.Test;

import com.sap.xsk.hdb.ds.AbstractXSKHDBITTest;
import com.sap.xsk.integration.tests.core.hdb.module.XSKHDBTestModule;
import com.sap.xsk.integration.tests.core.hdb.utils.HanaITestUtils;

public class XSKHDBCoreFacadeHanaITTest extends AbstractXSKHDBITTest {

	@Before
	public void setUpBeforeTest() throws SQLException {
		HanaITestUtils.clearDataFromXSKDataStructure(systemDatasource, Arrays.asList( //
				"'/acme/com/test/views/MY_VIEW1.hdbview'", //
				"'/acme/com/test/views/MY_VIEW2.hdbview'", //
				"'/acme/com/test/tables/W_TABLE1.hdbtable'", //
				"'/acme/com/test/tables/W_VIEW_3.hdbview'", //
				"'/acme/com/test/tables/MY_TABLE2.hdbtable'" //
		));
		Configuration.set(IDataStructureModel.DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE, "true");
		facade.clearCache();
	}

	@Test
	public void testUpdateEntities() throws Exception {
		try (Connection connection = datasource.getConnection(); Statement stmt = connection.createStatement()) {

			HanaITestUtils.createSchema(stmt, TEST_SCHEMA);
			LocalResource view1Resource = XSKHDBTestModule.getResources( //
					"/usr/local/target/dirigible/repository/root", //
					"/registry/public/acme/com/test/views/MY_VIEW1.hdbview", //
					"/hdbview-itest/MY_VIEW1.hdbview" //
			);
			LocalResource view2Resource = XSKHDBTestModule.getResources( //
					"/usr/local/target/dirigible/repository/root", //
					"/registry/public/acme/com/test/views/MY_VIEW2.hdbview", //
					"/hdbview-itest/MY_VIEW2.hdbview" //
			);

			LocalResource table1Resource = XSKHDBTestModule.getResources( //
					"/usr/local/target/dirigible/repository/root", //
					"/registry/public/acme/com/test/tables/W_TABLE1.hdbtable", //
					"/hdbview-itest/W_TABLE1.hdbtable" //
			);
			LocalResource table2Resource = XSKHDBTestModule.getResources( //
					"/usr/local/target/dirigible/repository/root", //
					"/registry/public/acme/com/test/tables/MY_TABLE2.hdbtable", //
					"/hdbview-itest/MY_TABLE2.hdbtable" //
			);
			LocalResource view3Resource = XSKHDBTestModule.getResources("/usr/local/target/dirigible/repository/root", //
					"/registry/public/acme/com/test/tables/W_VIEW_3.hdbview", //
					"/hdbview-itest/W_VIEW_3.hdbview" //
			);

			try {
				facade.handleResourceSynchronization(view3Resource);
				facade.handleResourceSynchronization(view2Resource);
				facade.handleResourceSynchronization(view1Resource);
				facade.handleResourceSynchronization(table1Resource);
				facade.handleResourceSynchronization(table2Resource);

				facade.updateEntities();
				assertTrue(HanaITestUtils.checkExistOfView(connection, "acme.com.test.views::MY_VIEW1", TEST_SCHEMA));
				assertTrue(HanaITestUtils.checkExistOfPublicSynonym(connection, "acme.com.test.views::MY_VIEW1"));
			} finally {
				HanaITestUtils.dropView(connection, stmt, "acme.com.test.views::MY_VIEW1", TEST_SCHEMA);
				HanaITestUtils.dropView(connection, stmt, "acme.com.test.views::MY_VIEW2", TEST_SCHEMA);
				HanaITestUtils.dropView(connection, stmt, "acme.com.test.tables::W_TABLE1", TEST_SCHEMA);
				HanaITestUtils.dropView(connection, stmt, "acme.com.test.tables::MY_TABLE2", TEST_SCHEMA);
				HanaITestUtils.dropView(connection, stmt, "acme.com.test.views::MY_VIEW3", TEST_SCHEMA);
				HanaITestUtils.dropSchema(stmt, TEST_SCHEMA);
			}
		}
	}
}