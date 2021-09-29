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
package com.sap.xsk.hdb.ds.itest.hdbsequence;

import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdb.ds.facade.IXSKHDBCoreFacade;
import com.sap.xsk.hdb.ds.facade.XSKHDBCoreFacade;
import com.sap.xsk.hdb.ds.itest.model.JDBCModel;
import com.sap.xsk.hdb.ds.itest.module.XSKHDBTestModule;
import com.sap.xsk.hdb.ds.itest.utils.HanaITestUtils;
import org.eclipse.dirigible.commons.config.Configuration;
import org.eclipse.dirigible.commons.config.StaticObjects;
import org.eclipse.dirigible.core.problems.exceptions.ProblemsException;
import org.eclipse.dirigible.core.scheduler.api.SynchronizationException;
import org.eclipse.dirigible.database.ds.model.IDataStructureModel;
import org.eclipse.dirigible.repository.local.LocalResource;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

import static com.sap.xsk.hdb.ds.itest.utils.TestConstants.*;
import static org.junit.Assert.assertTrue;

public class XSKHDBSequenceParserHanaITTest {

  private static DataSource datasource;
  private static IXSKHDBCoreFacade facade;
  private static final String testSchema = "TEST_SCHEMA";

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
    HanaITestUtils
        .clearDataFromXSKDataStructure(datasource, Arrays.asList("'/sequence-itest/SampleSequence_HanaXSClassic.hdbsequence'",
            "'/sequence-itest/SampleSequence_HanaXSClassicDiffSchema.hdbsequence'"));
    Configuration.set(IDataStructureModel.DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE, "true");
    facade.clearCache();
  }

  @Test
  public void testHDBSequenceCreateSameSchema()
      throws XSKDataStructuresException, SynchronizationException, IOException, SQLException, ProblemsException {
    try (Connection connection = datasource.getConnection();
        Statement stmt = connection.createStatement()) {

      String userSchema = Configuration.get("hana.username");
      String artifactName = "sequence-itest::SampleSequence_HanaXSClassic";

      String fileContent = String.format("schema= \"%s\";\n"
          + "increment_by=1;\n"
          + "start_with= 10;\n"
          + "maxvalue= 30;\n"
          + "nomaxvalue=false;\n"
          + "nominvalue= false;\n"
          + "minvalue= 10;\n"
          + "cycles=false;\n"
          + "public=false;", userSchema);
      LocalResource resource = XSKHDBTestModule.getResourceFromString("/usr/local/target/dirigible/repository/root",
          "/registry/public/sequence-itest/SampleSequence_HanaXSClassic.hdbsequence",
          fileContent);
      this.facade.handleResourceSynchronization(resource);
      this.facade.updateEntities();
      try {
        assertTrue(HanaITestUtils.checkExistOfSequence(stmt, artifactName, userSchema));
      } finally {
        HanaITestUtils.dropSequence(connection, stmt, artifactName, userSchema);
      }
    }
  }

  @Test
  public void testHDBSequenceCreateDifferentSchema()
      throws XSKDataStructuresException, SynchronizationException, IOException, SQLException, ProblemsException {
    try (Connection connection = datasource.getConnection();
        Statement stmt = connection.createStatement()) {

      HanaITestUtils.createSchema(stmt, testSchema);
      String artifactName = "sequence-itest::SampleSequence_HanaXSClassicDiffSchema";
      LocalResource resource = XSKHDBTestModule.getResources("/usr/local/target/dirigible/repository/root",
          "/registry/public/sequence-itest/SampleSequence_HanaXSClassicDiffSchema.hdbsequence",
          "/sequence-itest/SampleSequence_HanaXSClassicDiffSchema.hdbsequence");

      this.facade.handleResourceSynchronization(resource);
      this.facade.updateEntities();
      try {
        assertTrue(HanaITestUtils.checkExistOfSequence(stmt, artifactName, testSchema));
      } finally {
        HanaITestUtils.dropSequence(connection, stmt, artifactName, testSchema);
        HanaITestUtils.dropSchema(stmt, testSchema);
      }
    }
  }
}
