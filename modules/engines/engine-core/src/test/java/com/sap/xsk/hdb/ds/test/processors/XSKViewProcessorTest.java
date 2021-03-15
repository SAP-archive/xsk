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

import com.sap.xsk.hdb.ds.model.XSKDataStructureModelFactory;
import com.sap.xsk.hdb.ds.model.hdbview.XSKDataStructureHDBViewModel;
import com.sap.xsk.hdb.ds.processors.view.XSKViewCreateProcessor;
import com.sap.xsk.hdb.ds.processors.view.XSKViewDropProcessor;
import com.sap.xsk.hdb.ds.test.parser.XSKViewParserTest;
import com.sap.xsk.utils.XSKConstants;
import org.eclipse.dirigible.core.test.AbstractGuiceTest;
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

import java.nio.charset.StandardCharsets;
import java.sql.Connection;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({SqlFactory.class})
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
        String hdbviewSample = org.apache.commons.io.IOUtils.toString(XSKViewParserTest.class.getResourceAsStream("/ItemsByOrderHANAv1.hdbview"), StandardCharsets.UTF_8);

        XSKDataStructureHDBViewModel model = XSKDataStructureModelFactory.parseView("hdb_view/ItemsByOrderHANAv1.hdbview", hdbviewSample);
        String mockSQL = "testSQLScript";

        //PowerMock do not support deep stub calls
        PowerMockito.mockStatic(SqlFactory.class);
        when(SqlFactory.getNative(mockConnection)).thenReturn(mockSqlfactory);
        when(SqlFactory.getNative(mockConnection).exists(any(), any())).thenReturn(false);
        when(SqlFactory.getNative(mockConnection).create()).thenReturn(create);
        when(SqlFactory.getNative(mockConnection).create().view(any())).thenReturn(mockCreateViewBuilder);
        when(SqlFactory.getNative(mockConnection).create().view(any()).asSelect(any())).thenReturn(mockCreateViewBuilder);
        when(SqlFactory.getNative(mockConnection).create().view(any()).asSelect(any()).build()).thenReturn(mockSQL);

        processorSpy.execute(mockConnection, model);
        verify(processorSpy, times(1)).executeSql(mockSQL, mockConnection);
    }

    @Test
    public void executeCreateViewHANAv2Successfully() throws Exception {
        XSKViewCreateProcessor processorSpy = spy(XSKViewCreateProcessor.class);
        String hdbviewSample = org.apache.commons.io.IOUtils.toString(XSKViewParserTest.class.getResourceAsStream("/ItemsByOrderHANAv2.hdbview"), StandardCharsets.UTF_8);

        XSKDataStructureHDBViewModel model = XSKDataStructureModelFactory.parseView("sap.com.test.views/ItemsByOrderHANAv2.hdbview", hdbviewSample);
        model.setRawContent(hdbviewSample);
        String sql = XSKConstants.XSK_HDBVIEW_CREATE + model.getRawContent();

        PowerMockito.mockStatic(SqlFactory.class);
        when(SqlFactory.getNative(mockConnection)).thenReturn(mockSqlfactory);
        when(SqlFactory.getNative(mockConnection).exists(any(), any())).thenReturn(false);

        processorSpy.execute(mockConnection, model);
        verify(processorSpy, times(1)).executeSql(sql, mockConnection);
    }

    @Test
    public void executeDropViewSuccessfully() throws Exception {
        XSKViewDropProcessor processorSpy = spy(XSKViewDropProcessor.class);
        String hdbviewSample = org.apache.commons.io.IOUtils.toString(XSKViewParserTest.class.getResourceAsStream("/ItemsByOrderHANAv1.hdbview"), StandardCharsets.UTF_8);

        XSKDataStructureHDBViewModel model = XSKDataStructureModelFactory.parseView("hdb_view/ItemsByOrderHANAv1.hdbview", hdbviewSample);
        String mockSQL = XSKConstants.XSK_HDBVIEW_DROP + model.getName();

        PowerMockito.mockStatic(SqlFactory.class);
        when(SqlFactory.getNative(mockConnection)).thenReturn(mockSqlfactory);
        when(SqlFactory.getNative(mockConnection).exists(any(), any())).thenReturn(true);
        when(SqlFactory.getNative(mockConnection).drop()).thenReturn(drop);
        when(SqlFactory.getNative(mockConnection).drop().view(any())).thenReturn(mockDropViewBuilder);
        when(SqlFactory.getNative(mockConnection).drop().view(any()).build()).thenReturn(mockSQL);

        processorSpy.execute(mockConnection, model);
        verify(processorSpy, times(1)).executeSql(mockSQL, mockConnection);
    }
}
