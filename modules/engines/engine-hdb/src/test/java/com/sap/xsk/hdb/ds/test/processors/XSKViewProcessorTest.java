/*
 * Copyright (c) 2019-2021 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2019-2021 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.hdb.ds.test.processors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.sap.xsk.hdb.ds.model.XSKDataStructureModelFactory;
import com.sap.xsk.hdb.ds.model.hdbview.XSKDataStructureHDBViewModel;
import com.sap.xsk.hdb.ds.processors.view.XSKViewCreateProcessor;
import com.sap.xsk.hdb.ds.processors.view.XSKViewDropProcessor;
import com.sap.xsk.hdb.ds.test.parser.XSKViewParserTest;
import com.sap.xsk.utils.XSKConstants;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import org.eclipse.dirigible.commons.config.Configuration;
import org.eclipse.dirigible.core.test.AbstractGuiceTest;
import org.eclipse.dirigible.database.ds.model.IDataStructureModel;
import org.eclipse.dirigible.database.sql.DatabaseArtifactTypes;
import org.eclipse.dirigible.database.sql.SqlFactory;
import org.eclipse.dirigible.database.sql.builders.CreateBranchingBuilder;
import org.eclipse.dirigible.database.sql.builders.DropBranchingBuilder;
import org.eclipse.dirigible.database.sql.builders.view.CreateViewBuilder;
import org.eclipse.dirigible.database.sql.builders.view.DropViewBuilder;
import org.eclipse.dirigible.database.sql.dialects.DefaultSqlDialect;
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
public class XSKViewProcessorTest extends AbstractGuiceTest {

  @Mock(answer = Answers.RETURNS_DEEP_STUBS)
  private Connection mockConnection;
  @Mock(answer = Answers.RETURNS_DEEP_STUBS)
  private SqlFactory mockSqlfactory;
  @Mock
  private DefaultSqlDialect mockDefaultSqlDialect;
  @Mock
  private CreateBranchingBuilder create;
  @Mock
  private CreateViewBuilder mockCreateViewBuilder;
  @Mock
  private DropBranchingBuilder drop;
  @Mock
  private DropViewBuilder mockDropViewBuilder;

  @Before
  public void openMocks() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void executeCreateViewHANAv1Successfully() throws Exception {
    XSKViewCreateProcessor processorSpy = spy(XSKViewCreateProcessor.class);
    String hdbviewSample = org.apache.commons.io.IOUtils
        .toString(XSKViewParserTest.class.getResourceAsStream("/ItemsByOrderHANAv1.hdbview"), StandardCharsets.UTF_8);

    XSKDataStructureHDBViewModel model = XSKDataStructureModelFactory.parseView("hdb_view.db/ItemsByOrderHANAv1.hdbview", hdbviewSample);
    model.setName("\"MYSCHEMA\".\"hdb_view::ItemsByOrderHANAv1\"");
    String mockSQL = "testSQLScript";

    //PowerMock do not support deep stub calls
    PowerMockito.mockStatic(SqlFactory.class, Configuration.class);
    when(SqlFactory.getNative(mockConnection)).thenReturn(mockSqlfactory);
    when(SqlFactory.getNative(mockConnection).exists(mockConnection, model.getName(), DatabaseArtifactTypes.VIEW)).thenReturn(false);
    when(SqlFactory.getNative(mockConnection).create()).thenReturn(create);
    when(SqlFactory.getNative(mockConnection).create().view(any())).thenReturn(mockCreateViewBuilder);
    when(SqlFactory.getNative(mockConnection).create().view(any()).asSelect(any())).thenReturn(mockCreateViewBuilder);
    when(SqlFactory.getNative(mockConnection).create().view(any()).asSelect(any()).build()).thenReturn(mockSQL);
    when(Configuration.get(IDataStructureModel.DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE, "false")).thenReturn("true");

    processorSpy.execute(mockConnection, model);
    verify(processorSpy, times(1)).executeSql(mockSQL, mockConnection);
  }

  @Test
  public void executeCreateViewHANAv2Successfully() throws Exception {
    XSKViewCreateProcessor processorSpy = spy(XSKViewCreateProcessor.class);
    String hdbviewSample = org.apache.commons.io.IOUtils
        .toString(XSKViewParserTest.class.getResourceAsStream("/ItemsByOrderHANAv2.hdbview"), StandardCharsets.UTF_8);

    XSKDataStructureHDBViewModel model = XSKDataStructureModelFactory.parseView("hdb_view.db/ItemsByOrderHANAv2.hdbview", hdbviewSample);
    model.setRawContent(hdbviewSample);
    model.setName("\"MYSCHEMA\".\"hdb_view::ItemsByOrderHANAv2\"");
    String sql = XSKConstants.XSK_HDBVIEW_CREATE + model.getRawContent();

    PowerMockito.mockStatic(SqlFactory.class, Configuration.class);
    when(SqlFactory.getNative(mockConnection)).thenReturn(mockSqlfactory);
    when(SqlFactory.getNative(mockConnection).exists(mockConnection, model.getName(), DatabaseArtifactTypes.VIEW)).thenReturn(false);
    when(Configuration.get(IDataStructureModel.DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE, "false")).thenReturn("true");

    processorSpy.execute(mockConnection, model);
    verify(processorSpy, times(1)).executeSql(sql, mockConnection);
  }

  @Test
  public void executeDropViewSuccessfully() throws Exception {
    XSKViewDropProcessor processorSpy = spy(XSKViewDropProcessor.class);
    String hdbviewSample = org.apache.commons.io.IOUtils
        .toString(XSKViewParserTest.class.getResourceAsStream("/ItemsByOrderHANAv1.hdbview"), StandardCharsets.UTF_8);

    XSKDataStructureHDBViewModel model = XSKDataStructureModelFactory.parseView("hdb_view/ItemsByOrderHANAv1.hdbview", hdbviewSample);
    model.setName("\"MYSCHEMA\".\"hdb_view::ItemsByOrderHANAv1\"");
    String mockSQL = XSKConstants.XSK_HDBVIEW_DROP + model.getName();

    PowerMockito.mockStatic(SqlFactory.class, Configuration.class);
    when(SqlFactory.getNative(mockConnection)).thenReturn(mockSqlfactory);
    when(SqlFactory.getNative(mockConnection).exists(mockConnection, model.getName(), DatabaseArtifactTypes.VIEW)).thenReturn(true);
    when(SqlFactory.getNative(mockConnection).drop()).thenReturn(drop);
    when(SqlFactory.getNative(mockConnection).drop().view(any())).thenReturn(mockDropViewBuilder);
    when(SqlFactory.getNative(mockConnection).drop().view(any()).build()).thenReturn(mockSQL);
    when(Configuration.get(IDataStructureModel.DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE, "false")).thenReturn("true");

    processorSpy.execute(mockConnection, model);
    verify(processorSpy, times(1)).executeSql(mockSQL, mockConnection);
  }
}
