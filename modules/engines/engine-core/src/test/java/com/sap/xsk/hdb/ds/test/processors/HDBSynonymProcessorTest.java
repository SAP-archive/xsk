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
import com.sap.xsk.hdb.ds.model.hdbsynonym.XSKDataStructureHDBSynonymModel;
import com.sap.xsk.hdb.ds.processors.synonym.HDBSynonymCreateProcessor;
import com.sap.xsk.hdb.ds.processors.synonym.HDBSynonymDropProcessor;
import com.sap.xsk.hdb.ds.test.parser.XSKSynonymParserTest;
import com.sap.xsk.hdb.ds.test.parser.XSKViewParserTest;
import org.eclipse.dirigible.core.test.AbstractGuiceTest;
import org.eclipse.dirigible.database.sql.SqlFactory;
import org.eclipse.dirigible.database.sql.builders.CreateBranchingBuilder;
import org.eclipse.dirigible.database.sql.builders.DropBranchingBuilder;
import org.eclipse.dirigible.database.sql.builders.synonym.CreateSynonymBuilder;
import org.eclipse.dirigible.database.sql.builders.synonym.DropSynonymBuilder;
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
public class HDBSynonymProcessorTest extends AbstractGuiceTest {
    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private Connection mockConnection;
    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private SqlFactory mockSqlfactory;
    @Mock
    private DefaultSqlDialect mockDefaultSqlDialect;
    @Mock
    private CreateBranchingBuilder create;
    @Mock
    private CreateSynonymBuilder mockCreateSynonymBuilder;
    @Mock
    private DropBranchingBuilder drop;
    @Mock
    private DropSynonymBuilder mockDropSynonymBuilder;

    @Before
    public void openMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void executeCreateSynonymHANAv1Successfully() throws Exception {
        HDBSynonymCreateProcessor processorSpy = spy(HDBSynonymCreateProcessor.class);
        String hdbsynonymSample = org.apache.commons.io.IOUtils.toString(HDBSynonymProcessorTest.class.getResourceAsStream("/MySynonym.hdbsynonym"), StandardCharsets.UTF_8);

        XSKDataStructureHDBSynonymModel model = XSKDataStructureModelFactory.parseSynonym("hdb_view/MySynonym.hdbsynonym", hdbsynonymSample);
        String mockSQL = "testSQLScript";

        //PowerMock do not support deep stub calls
        PowerMockito.mockStatic(SqlFactory.class);
        when(SqlFactory.getNative(mockConnection)).thenReturn(mockSqlfactory);
        when(SqlFactory.getNative(mockConnection).exists(any(), any())).thenReturn(false);
        when(SqlFactory.getNative(mockConnection).create()).thenReturn(create);
        when(SqlFactory.getNative(mockConnection).create().synonym(any())).thenReturn(mockCreateSynonymBuilder);
        when(SqlFactory.getNative(mockConnection).create().synonym(any()).forSource(any())).thenReturn(mockCreateSynonymBuilder);
        when(SqlFactory.getNative(mockConnection).create().synonym(any()).forSource(any()).build()).thenReturn(mockSQL);

        processorSpy.execute(mockConnection, model);
        verify(processorSpy, times(1)).executeSql(mockSQL, mockConnection);
    }

    @Test
    public void executeDropSynonymSuccessfully() throws Exception {
        HDBSynonymDropProcessor processorSpy = spy(HDBSynonymDropProcessor.class);
        String hdsynonymSample = org.apache.commons.io.IOUtils.toString(HDBSynonymProcessorTest.class.getResourceAsStream("/MySynonym.hdbsynonym"), StandardCharsets.UTF_8);

        XSKDataStructureHDBSynonymModel model = XSKDataStructureModelFactory.parseSynonym("hdb_view/MySynonym.hdbsynonym", hdsynonymSample);
        String mockSQL = "testSQLScript";

        PowerMockito.mockStatic(SqlFactory.class);
        when(SqlFactory.getNative(mockConnection)).thenReturn(mockSqlfactory);
        when(SqlFactory.getNative(mockConnection).exists(any(), any())).thenReturn(true);
        when(SqlFactory.getNative(mockConnection).drop()).thenReturn(drop);
        when(SqlFactory.getNative(mockConnection).drop().synonym(any())).thenReturn(mockDropSynonymBuilder);
        when(SqlFactory.getNative(mockConnection).drop().synonym(any()).build()).thenReturn(mockSQL);

        processorSpy.execute(mockConnection, model);
        verify(processorSpy, times(1)).executeSql(mockSQL, mockConnection);
    }
}
