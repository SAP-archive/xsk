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
package com.sap.xsk.hdb.ds.test.processors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.sap.xsk.hdb.ds.model.XSKDataStructureModelFactory;
import com.sap.xsk.hdb.ds.model.hdbschema.XSKDataStructureHDBSchemaModel;
import com.sap.xsk.hdb.ds.processors.hdbschema.HDBSchemaCreateProcessor;
import com.sap.xsk.hdb.ds.processors.hdbschema.HDBSchemaDropProcessor;
import com.sap.xsk.hdb.ds.test.parser.XSKSchemaParserTest;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import org.eclipse.dirigible.commons.config.Configuration;
import org.eclipse.dirigible.core.test.AbstractGuiceTest;
import org.eclipse.dirigible.database.ds.model.IDataStructureModel;
import org.eclipse.dirigible.database.sql.DatabaseArtifactTypes;
import org.eclipse.dirigible.database.sql.SqlFactory;
import org.eclipse.dirigible.database.sql.builders.CreateBranchingBuilder;
import org.eclipse.dirigible.database.sql.builders.DropBranchingBuilder;
import org.eclipse.dirigible.database.sql.builders.schema.CreateSchemaBuilder;
import org.eclipse.dirigible.database.sql.builders.schema.DropSchemaBuilder;
import org.eclipse.dirigible.database.sql.builders.synonym.CreateSynonymBuilder;
import org.eclipse.dirigible.database.sql.dialects.DefaultSqlDialect;
import org.eclipse.dirigible.database.sql.dialects.hana.HanaSqlDialect;
import org.eclipse.dirigible.database.sql.dialects.postgres.PostgresSqlDialect;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest({SqlFactory.class, Configuration.class})
public class XSKSchemaProcessorTest extends AbstractGuiceTest {

  @Mock(answer = Answers.RETURNS_DEEP_STUBS)
  private Connection mockConnection;
  @Mock(answer = Answers.RETURNS_DEEP_STUBS)
  private SqlFactory mockSqlfactory;
  @Mock(answer = Answers.RETURNS_DEEP_STUBS)
  private DefaultSqlDialect mockSqlDialect;
  @Mock
  private PreparedStatement mockStatement;
  @Mock
  private CreateBranchingBuilder create;
  @Mock
  private CreateSchemaBuilder mockCreateSchemaBuilder;
  @Mock
  private DropSchemaBuilder mockDropSchemaBuilder;
  @Mock
  private DropBranchingBuilder drop;

  @Before
  public void openMocks() {
    MockitoAnnotations.initMocks(this);
    Configuration.set(IDataStructureModel.DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE, "true");
  }

  @Test
  public void executeCreateSchemaSuccessfully() throws Exception {
    HDBSchemaCreateProcessor processorSpy = spy(HDBSchemaCreateProcessor.class);
    String hdbschemaSample = org.apache.commons.io.IOUtils
            .toString(XSKSchemaParserTest.class.getResourceAsStream("/Myschema.hdbschema"), StandardCharsets.UTF_8);

    XSKDataStructureHDBSchemaModel model = XSKDataStructureModelFactory.parseSchema("hdb_schema/Myschema.hdbschema", hdbschemaSample);

    //PowerMock do not support deep stub calls
    PowerMockito.mockStatic(SqlFactory.class, Configuration.class);
    String mockSQL = "testSQLScript";
    when(SqlFactory.getNative(mockConnection)).thenReturn(mockSqlfactory);
    when(SqlFactory.deriveDialect(mockConnection)).thenReturn(new HanaSqlDialect());
    when(SqlFactory.getNative(mockConnection).exists(mockConnection, "MySchema", DatabaseArtifactTypes.SCHEMA)).thenReturn(false);
    when(SqlFactory.getNative(mockConnection).create()).thenReturn(create);
    when(SqlFactory.getNative(mockConnection).create().schema(anyString())).thenReturn(mockCreateSchemaBuilder);
    when(SqlFactory.getNative(mockConnection).create().schema(anyString()).build()).thenReturn(mockSQL);

    processorSpy.execute(mockConnection, model);
    verify(processorSpy, times(1)).executeSql(mockSQL, mockConnection);
  }

  @Test(expected = IllegalStateException.class)
  public void executeCreateSchemaFail() throws Exception {
    HDBSchemaCreateProcessor processorSpy = spy(HDBSchemaCreateProcessor.class);
    String hdbschemaSample = org.apache.commons.io.IOUtils
        .toString(XSKSchemaParserTest.class.getResourceAsStream("/Myschema.hdbschema"), StandardCharsets.UTF_8);

    XSKDataStructureHDBSchemaModel model = XSKDataStructureModelFactory.parseSchema("hdb_schema/Myschema.hdbschema", hdbschemaSample);

    //PowerMock do not support deep stub calls
    PowerMockito.mockStatic(SqlFactory.class, Configuration.class);
    when(SqlFactory.getNative(mockConnection)).thenReturn(mockSqlfactory);
    when(SqlFactory.deriveDialect(mockConnection)).thenReturn(new PostgresSqlDialect());
    processorSpy.execute(mockConnection, model);
  }

  @Test
  public void executeDropSchemaSuccessfully() throws Exception {
    HDBSchemaDropProcessor processorSpy = spy(HDBSchemaDropProcessor.class);
    String hdbschemaSample = org.apache.commons.io.IOUtils
            .toString(XSKSchemaParserTest.class.getResourceAsStream("/Myschema.hdbschema"), StandardCharsets.UTF_8);

    XSKDataStructureHDBSchemaModel model = XSKDataStructureModelFactory.parseSchema("hdb_schema/Myschema.hdbschema", hdbschemaSample);
    String mockSQL = "testSQLScript";

    //PowerMock do not support deep stub calls
    PowerMockito.mockStatic(SqlFactory.class, Configuration.class);
    when(SqlFactory.getNative(mockConnection)).thenReturn(mockSqlfactory);
    when(SqlFactory.deriveDialect(mockConnection)).thenReturn(new HanaSqlDialect());
    when(SqlFactory.getNative(mockConnection).exists(mockConnection, "MySchema", DatabaseArtifactTypes.SCHEMA)).thenReturn(true);
    when(SqlFactory.getNative(mockConnection).drop()).thenReturn(drop);
    when(SqlFactory.getNative(mockConnection).drop().schema(anyString())).thenReturn(mockDropSchemaBuilder);
    when(SqlFactory.getNative(mockConnection).drop().schema(anyString()).build()).thenReturn(mockSQL);

    processorSpy.execute(mockConnection, model);
    verify(processorSpy, times(1)).executeSql(mockSQL, mockConnection);
  }

  @Test(expected = IllegalStateException.class)
  public void executeDropSchemaFail() throws Exception {
    //PowerMock do not support deep stub calls
    PowerMockito.mockStatic(SqlFactory.class, Configuration.class);
    when(SqlFactory.getNative(mockConnection)).thenReturn(mockSqlfactory);
    when(SqlFactory.deriveDialect(mockConnection)).thenReturn(new PostgresSqlDialect());

    HDBSchemaDropProcessor processorSpy = spy(HDBSchemaDropProcessor.class);
    String hdbschemaSample = org.apache.commons.io.IOUtils
        .toString(XSKSchemaParserTest.class.getResourceAsStream("/Myschema.hdbschema"), StandardCharsets.UTF_8);

    XSKDataStructureHDBSchemaModel model = XSKDataStructureModelFactory.parseSchema("hdb_schema/Myschema.hdbschema", hdbschemaSample);
    processorSpy.execute(mockConnection, model);
  }
}
