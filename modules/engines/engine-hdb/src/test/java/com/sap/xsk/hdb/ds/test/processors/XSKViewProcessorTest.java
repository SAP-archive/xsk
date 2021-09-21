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

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.doNothing;

import com.sap.xsk.hdb.ds.model.XSKDBContentType;
import com.sap.xsk.hdb.ds.model.XSKDataStructureModelFactory;
import com.sap.xsk.hdb.ds.model.hdbview.XSKDataStructureHDBViewModel;
import com.sap.xsk.hdb.ds.processors.view.XSKViewCreateProcessor;
import com.sap.xsk.hdb.ds.processors.view.XSKViewDropProcessor;
import com.sap.xsk.hdb.ds.service.manager.IXSKDataStructureManager;
import com.sap.xsk.hdb.ds.test.parser.XSKViewParserTest;
import com.sap.xsk.utils.XSKConstants;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.util.Map;
import org.eclipse.dirigible.api.v3.problems.ProblemsFacade;
import org.eclipse.dirigible.commons.config.Configuration;
import org.eclipse.dirigible.core.test.AbstractDirigibleTest;
import org.eclipse.dirigible.database.ds.model.IDataStructureModel;
import org.eclipse.dirigible.database.sql.DatabaseArtifactTypes;
import org.eclipse.dirigible.database.sql.SqlFactory;
import org.eclipse.dirigible.database.sql.builders.CreateBranchingBuilder;
import org.eclipse.dirigible.database.sql.builders.DropBranchingBuilder;
import org.eclipse.dirigible.database.sql.builders.view.CreateViewBuilder;
import org.eclipse.dirigible.database.sql.builders.view.DropViewBuilder;
import org.eclipse.dirigible.database.sql.dialects.hana.HanaSqlDialect;
import org.eclipse.dirigible.database.sql.dialects.postgres.PostgresSqlDialect;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest({SqlFactory.class, Configuration.class, ProblemsFacade.class})
public class XSKViewProcessorTest extends AbstractDirigibleTest {

  @Mock(answer = Answers.RETURNS_DEEP_STUBS)
  private Connection mockConnection;
  @Mock(answer = Answers.RETURNS_DEEP_STUBS)
  private SqlFactory mockSqlFactory;
  @Mock
  private CreateBranchingBuilder create;
  @Mock
  private CreateViewBuilder mockCreateViewBuilder;
  @Mock
  private DropBranchingBuilder drop;
  @Mock
  private DropViewBuilder mockDropViewBuilder;

  @Mock
  private Map<String, IXSKDataStructureManager> managerServices;

  @InjectMocks
  private XSKViewCreateProcessor processor = new XSKViewCreateProcessor();

  @Before
  public void openMocks() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void executeCreateViewHANAv1Successfully() throws Exception {
    XSKViewCreateProcessor processorSpy = spy(XSKViewCreateProcessor.class);
    String sample = org.apache.commons.io.IOUtils
        .toString(XSKViewParserTest.class.getResourceAsStream("/ItemsByOrderHANAv1.hdbview"), StandardCharsets.UTF_8);

    XSKDataStructureHDBViewModel model = XSKDataStructureModelFactory.parseView("hdb_view.db.a1/path/ItemsByOrderHANAv1.hdbview", sample);
    String mockSQL = "testSQLScript";
    PowerMockito.mockStatic(SqlFactory.class, Configuration.class);
    when(SqlFactory.getNative(mockConnection)).thenReturn(mockSqlFactory);
    when(SqlFactory.deriveDialect(mockConnection)).thenReturn(new HanaSqlDialect());
    when(SqlFactory.getNative(mockConnection)
        .exists(mockConnection, XSKConstants.XSK_SYNONYM_PUBLIC_SCHEMA, "MYSCHEMA.hdb_view::ItemsByOrderHANAv1",
            DatabaseArtifactTypes.VIEW)).thenReturn(false);
    when(SqlFactory.getNative(mockConnection).create()).thenReturn(create);
    when(SqlFactory.getNative(mockConnection).create().view(any())).thenReturn(mockCreateViewBuilder);
    when(SqlFactory.getNative(mockConnection).create().view(any()).asSelect(any())).thenReturn(mockCreateViewBuilder);
    when(SqlFactory.getNative(mockConnection).create().view(any()).asSelect(any()).build()).thenReturn(mockSQL);
    when(Configuration.get(IDataStructureModel.DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE, "false")).thenReturn("true");

    processorSpy.execute(mockConnection, model);
    assertEquals(model.getDBContentType(), XSKDBContentType.XS_CLASSIC);
    verify(processorSpy, times(1)).executeSql(mockSQL, mockConnection);
  }

  @Test
  public void executeCreateViewHANAv2Successfully() throws Exception {
    XSKViewCreateProcessor processorSpy = spy(XSKViewCreateProcessor.class);
    String sample = org.apache.commons.io.IOUtils
        .toString(XSKViewParserTest.class.getResourceAsStream("/ItemsByOrderHANAv2.hdbview"), StandardCharsets.UTF_8);

    XSKDataStructureHDBViewModel model = XSKDataStructureModelFactory.parseView("hdb_view.db/ItemsByOrderHANAv2.hdbview", sample);
    model.setRawContent(sample);
    String sql = XSKConstants.XSK_HDBVIEW_CREATE + model.getRawContent();

    PowerMockito.mockStatic(SqlFactory.class, Configuration.class);
    when(SqlFactory.getNative(mockConnection)).thenReturn(mockSqlFactory);
    when(SqlFactory.deriveDialect(mockConnection)).thenReturn(new HanaSqlDialect());
    when(SqlFactory.getNative(mockConnection).exists(mockConnection, model.getName(), DatabaseArtifactTypes.VIEW)).thenReturn(false);
    when(Configuration.get(IDataStructureModel.DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE, "false")).thenReturn("true");

    processorSpy.execute(mockConnection, model);
    assertEquals(model.getDBContentType(), XSKDBContentType.OTHERS);
    verify(processorSpy, times(1)).executeSql(sql, mockConnection);
  }

  @Test(expected = IllegalStateException.class)
  public void executeCreateViewPostgreSQLFailed() throws Exception {
    XSKViewCreateProcessor processorSpy = spy(XSKViewCreateProcessor.class);
    String sample = org.apache.commons.io.IOUtils
        .toString(XSKViewParserTest.class.getResourceAsStream("/ItemsByOrderHANAv2.hdbview"), StandardCharsets.UTF_8);

    XSKDataStructureHDBViewModel model = XSKDataStructureModelFactory.parseView("hdb_view.db/ItemsByOrderHANAv2.hdbview", sample);
    model.setRawContent(sample);

    PowerMockito.mockStatic(SqlFactory.class, Configuration.class, ProblemsFacade.class);
    when(SqlFactory.getNative(mockConnection)).thenReturn(mockSqlFactory);
    when(SqlFactory.deriveDialect(mockConnection)).thenReturn(new PostgresSqlDialect());
    doNothing().when(ProblemsFacade.class, "save", any(), any(),any(), any(), any(), any(), any(), any(), any(), any());

    processorSpy.execute(mockConnection, model);
  }

  @Test
  public void executeDropViewSuccessfully() throws Exception {
    XSKViewDropProcessor processorSpy = spy(XSKViewDropProcessor.class);
    String sample = org.apache.commons.io.IOUtils
        .toString(XSKViewParserTest.class.getResourceAsStream("/ItemsByOrderHANAv1.hdbview"), StandardCharsets.UTF_8);

    XSKDataStructureHDBViewModel model = XSKDataStructureModelFactory.parseView("hdb_view/ItemsByOrderHANAv1.hdbview", sample);
    String mockSQL = XSKConstants.XSK_HDBVIEW_DROP + model.getName();
    PowerMockito.mockStatic(SqlFactory.class, Configuration.class);
    when(SqlFactory.getNative(mockConnection)).thenReturn(mockSqlFactory);
    when(SqlFactory.deriveDialect(mockConnection)).thenReturn(new HanaSqlDialect());
    when(SqlFactory.getNative(mockConnection)
        .exists(mockConnection, XSKConstants.XSK_SYNONYM_PUBLIC_SCHEMA, "MYSCHEMA.hdb_view::ItemsByOrderHANAv1",
            DatabaseArtifactTypes.VIEW)).thenReturn(true);
    when(SqlFactory.getNative(mockConnection).drop()).thenReturn(drop);
    when(SqlFactory.getNative(mockConnection).drop().view(any())).thenReturn(mockDropViewBuilder);
    when(SqlFactory.getNative(mockConnection).drop().view(any()).build()).thenReturn(mockSQL);
    when(Configuration.get(IDataStructureModel.DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE, "false")).thenReturn("true");

    processorSpy.execute(mockConnection, model);
    verify(processorSpy, times(1)).executeSql(mockSQL, mockConnection);
  }
}
