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
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.doNothing;

import com.sap.xsk.hdb.ds.model.XSKDataStructureModelFactory;
import com.sap.xsk.hdb.ds.model.hdbschema.XSKDataStructureHDBSchemaModel;
import com.sap.xsk.hdb.ds.processors.hdbschema.HDBSchemaCreateProcessor;
import com.sap.xsk.hdb.ds.processors.hdbschema.HDBSchemaDropProcessor;
import com.sap.xsk.hdb.ds.synchronizer.XSKDataStructuresSynchronizer;
import com.sap.xsk.hdb.ds.test.parser.XSKSchemaParserTest;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import org.eclipse.dirigible.api.v3.problems.ProblemsFacade;
import org.eclipse.dirigible.commons.config.Configuration;
import org.eclipse.dirigible.core.scheduler.api.ISynchronizerArtefactType.ArtefactState;
import org.eclipse.dirigible.core.test.AbstractDirigibleTest;
import org.eclipse.dirigible.database.ds.model.IDataStructureModel;
import org.eclipse.dirigible.database.sql.DatabaseArtifactTypes;
import org.eclipse.dirigible.database.sql.SqlFactory;
import org.eclipse.dirigible.database.sql.builders.CreateBranchingBuilder;
import org.eclipse.dirigible.database.sql.builders.DropBranchingBuilder;
import org.eclipse.dirigible.database.sql.builders.schema.CreateSchemaBuilder;
import org.eclipse.dirigible.database.sql.builders.schema.DropSchemaBuilder;
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
import org.powermock.reflect.Whitebox;

@RunWith(PowerMockRunner.class)
@PrepareForTest({SqlFactory.class, Configuration.class, ProblemsFacade.class})
public class XSKSchemaProcessorTest extends AbstractDirigibleTest {

  @Mock(answer = Answers.RETURNS_DEEP_STUBS)
  private Connection mockConnection;
  @Mock(answer = Answers.RETURNS_DEEP_STUBS)
  private SqlFactory mockSqlfactory;
  @Mock
  private CreateBranchingBuilder create;
  @Mock
  private CreateSchemaBuilder mockCreateSchemaBuilder;
  @Mock
  private DropSchemaBuilder mockDropSchemaBuilder;
  @Mock
  private DropBranchingBuilder drop;
  @Mock
  private XSKDataStructuresSynchronizer dataStructuresSynchronizer;

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

    Whitebox.setInternalState(HDBSchemaCreateProcessor.class, dataStructuresSynchronizer);
    doNothing().when(dataStructuresSynchronizer).applyArtefactState(any(), any(), any(),any(), any());

    processorSpy.execute(mockConnection, model);
    verify(processorSpy, times(1)).executeSql(mockSQL, mockConnection);
    verify(dataStructuresSynchronizer, times(1)).applyArtefactState(any(),any(), any(), eq(ArtefactState.SUCCESSFUL_CREATE), any());
  }

  @Test(expected = IllegalStateException.class)
  public void executeCreateSchemaFail() throws Exception {
    HDBSchemaCreateProcessor processorSpy = spy(HDBSchemaCreateProcessor.class);
    String hdbschemaSample = org.apache.commons.io.IOUtils
        .toString(XSKSchemaParserTest.class.getResourceAsStream("/Myschema.hdbschema"), StandardCharsets.UTF_8);

    XSKDataStructureHDBSchemaModel model = XSKDataStructureModelFactory.parseSchema("hdb_schema/Myschema.hdbschema", hdbschemaSample);

    //PowerMock do not support deep stub calls
    PowerMockito.mockStatic(SqlFactory.class, Configuration.class, ProblemsFacade.class);
    when(SqlFactory.getNative(mockConnection)).thenReturn(mockSqlfactory);
    when(SqlFactory.deriveDialect(mockConnection)).thenReturn(new PostgresSqlDialect());
    doNothing().when(ProblemsFacade.class, "save", any(), any(),any(), any(), any(), any(), any(), any(), any(), any());
    Whitebox.setInternalState(HDBSchemaCreateProcessor.class, dataStructuresSynchronizer);
    doNothing().when(dataStructuresSynchronizer).applyArtefactState(any(), any(), any(),any(), any());

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

    Whitebox.setInternalState(HDBSchemaDropProcessor.class, dataStructuresSynchronizer);
    doNothing().when(dataStructuresSynchronizer).applyArtefactState(any(), any(), any(),any(), any());

    processorSpy.execute(mockConnection, model);
    verify(processorSpy, times(1)).executeSql(mockSQL, mockConnection);
    verify(dataStructuresSynchronizer, times(1)).applyArtefactState(any(),any(), any(), eq(ArtefactState.SUCCESSFUL_DELETE), any());

  }

  @Test(expected = IllegalStateException.class)
  public void executeDropSchemaFail() throws Exception {
    //PowerMock do not support deep stub calls
    PowerMockito.mockStatic(SqlFactory.class, Configuration.class, ProblemsFacade.class);
    when(SqlFactory.getNative(mockConnection)).thenReturn(mockSqlfactory);
    when(SqlFactory.deriveDialect(mockConnection)).thenReturn(new PostgresSqlDialect());

    HDBSchemaDropProcessor processorSpy = spy(HDBSchemaDropProcessor.class);
    String hdbschemaSample = org.apache.commons.io.IOUtils
        .toString(XSKSchemaParserTest.class.getResourceAsStream("/Myschema.hdbschema"), StandardCharsets.UTF_8);

    XSKDataStructureHDBSchemaModel model = XSKDataStructureModelFactory.parseSchema("hdb_schema/Myschema.hdbschema", hdbschemaSample);
    doNothing().when(ProblemsFacade.class, "save", any(), any(),any(), any(), any(), any(), any(), any(), any(), any());
    Whitebox.setInternalState(HDBSchemaDropProcessor.class, dataStructuresSynchronizer);
    doNothing().when(dataStructuresSynchronizer).applyArtefactState(any(), any(), any(),any(), any());

    processorSpy.execute(mockConnection, model);
  }
}
