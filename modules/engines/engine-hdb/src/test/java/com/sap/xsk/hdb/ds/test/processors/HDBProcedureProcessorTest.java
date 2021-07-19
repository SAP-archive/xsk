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

import com.sap.xsk.hdb.ds.model.hdbprocedure.XSKDataStructureHDBProcedureModel;
import com.sap.xsk.hdb.ds.processors.hdbprocedure.HDBProcedureCreateProcessor;
import com.sap.xsk.hdb.ds.processors.hdbprocedure.HDBProcedureDropProcessor;
import com.sap.xsk.hdb.ds.test.parser.XSKViewParserTest;
import com.sap.xsk.utils.XSKCommonsUtils;
import com.sap.xsk.utils.XSKConstants;
import org.eclipse.dirigible.commons.config.Configuration;
import org.eclipse.dirigible.core.test.AbstractGuiceTest;
import org.eclipse.dirigible.database.ds.model.IDataStructureModel;
import org.eclipse.dirigible.database.sql.DatabaseArtifactTypes;
import org.eclipse.dirigible.database.sql.SqlFactory;
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

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({SqlFactory.class, Configuration.class})
public class HDBProcedureProcessorTest extends AbstractGuiceTest {

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private Connection mockConnection;
    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private SqlFactory mockSqlfactory;
    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private DefaultSqlDialect mockSqlDialect;
    @Mock
    private PreparedStatement mockStatement;


    @Before
    public void openMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void executeCreateProcedureIfDoNotExist() throws IOException, SQLException {
        executeCreateProcedureSuccessfully(false, 1);
    }

    @Test
    public void executeCreateProcedureIfAlreadyExist() throws IOException, SQLException {
        executeCreateProcedureSuccessfully(true, 0);
    }

    public void executeCreateProcedureSuccessfully(boolean doExist, int expectedTimesOfInvocation) throws IOException, SQLException {
        //PowerMock do not support deep stub calls
        PowerMockito.mockStatic(SqlFactory.class, Configuration.class);
        when(SqlFactory.getNative(mockConnection)).thenReturn(mockSqlfactory);
        when(SqlFactory.deriveDialect(mockConnection)).thenReturn(new HanaSqlDialect());
        when(Configuration.get(IDataStructureModel.DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE, "false")).thenReturn("true");

        HDBProcedureCreateProcessor processorSpy = spy(HDBProcedureCreateProcessor.class);
        String hdbprocedureSample = org.apache.commons.io.IOUtils
                .toString(XSKViewParserTest.class.getResourceAsStream("/OrderProcedure.hdbprocedure"), StandardCharsets.UTF_8);

        XSKDataStructureHDBProcedureModel model = new XSKDataStructureHDBProcedureModel();
        model.setContent(hdbprocedureSample);
        model.setName("\"MYSCHEMA\".\"hdb_view::OrderProcedure\"");
        String sql = XSKConstants.XSK_HDBPROCEDURE_CREATE + model.getContent();
        when(SqlFactory.getNative(mockConnection).exists(mockConnection, XSKCommonsUtils.extractArtifactNameWhenSchemaIsProvided(model.getName())[1], DatabaseArtifactTypes.PROCEDURE)).thenReturn(doExist);

        when(mockConnection.prepareStatement(sql)).thenReturn(mockStatement);
        when(mockStatement.executeUpdate(any())).thenReturn(1);
        processorSpy.execute(mockConnection, model);

        verify(processorSpy, times(expectedTimesOfInvocation)).executeSql(sql, mockConnection);
    }

    @Test(expected = IllegalStateException.class)
    public void executeCreateProcedurePostgresFailed() throws IOException, SQLException {
        //PowerMock do not support deep stub calls
        PowerMockito.mockStatic(SqlFactory.class, Configuration.class);
        when(SqlFactory.getNative(mockConnection)).thenReturn(mockSqlfactory);
        when(SqlFactory.deriveDialect(mockConnection)).thenReturn(new PostgresSqlDialect());

        HDBProcedureCreateProcessor processorSpy = spy(HDBProcedureCreateProcessor.class);
        String hdbprocedureSample = org.apache.commons.io.IOUtils
                .toString(XSKViewParserTest.class.getResourceAsStream("/OrderProcedure.hdbprocedure"), StandardCharsets.UTF_8);

        XSKDataStructureHDBProcedureModel model = new XSKDataStructureHDBProcedureModel();
        model.setContent(hdbprocedureSample);
        model.setName("\"MYSCHEMA\".\"hdb_view::OrderProcedure\"");
        when(SqlFactory.getNative(mockConnection).exists(mockConnection, XSKCommonsUtils.extractArtifactNameWhenSchemaIsProvided(model.getName())[1], DatabaseArtifactTypes.PROCEDURE)).thenReturn(false);

        processorSpy.execute(mockConnection, model);
    }

    @Test
    public void executeDropProcedureIfDoNotExist() throws IOException, SQLException {
        executeDropProcedureSuccessfully(false, 0);
    }

    @Test
    public void executeDropProcedureIfAlreadyExist() throws IOException, SQLException {
        executeDropProcedureSuccessfully(true, 1);
    }

    public void executeDropProcedureSuccessfully(boolean doExist, int expectedTimesOfInvocation) throws SQLException {
        //PowerMock do not support deep stub calls
        PowerMockito.mockStatic(SqlFactory.class, Configuration.class);
        when(SqlFactory.getNative(mockConnection)).thenReturn(mockSqlfactory);
        when(SqlFactory.deriveDialect(mockConnection)).thenReturn(new HanaSqlDialect());
        when(Configuration.get(IDataStructureModel.DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE, "false")).thenReturn("true");

        HDBProcedureDropProcessor processorSpy = spy(HDBProcedureDropProcessor.class);

        XSKDataStructureHDBProcedureModel model = new XSKDataStructureHDBProcedureModel();
        model.setName("\"MYSCHEMA\".\"hdb_view::OrderProcedure\"");
        String sql = XSKConstants.XSK_HDBPROCEDURE_DROP + model.getName();
        when(SqlFactory.getNative(mockConnection).exists(mockConnection, XSKCommonsUtils.extractArtifactNameWhenSchemaIsProvided(model.getName())[1], DatabaseArtifactTypes.PROCEDURE)).thenReturn(doExist);

        when(mockConnection.prepareStatement(sql)).thenReturn(mockStatement);
        when(mockStatement.executeUpdate(any())).thenReturn(1);
        processorSpy.execute(mockConnection, model);

        verify(processorSpy, times(expectedTimesOfInvocation)).executeSql(sql, mockConnection);
    }

    @Test(expected = IllegalStateException.class)
    public void executeDropProcedurePostgresFailed() throws SQLException {
        //PowerMock do not support deep stub calls
        PowerMockito.mockStatic(SqlFactory.class, Configuration.class);
        when(SqlFactory.getNative(mockConnection)).thenReturn(mockSqlfactory);
        when(SqlFactory.deriveDialect(mockConnection)).thenReturn(new PostgresSqlDialect());

        HDBProcedureDropProcessor processorSpy = spy(HDBProcedureDropProcessor.class);

        XSKDataStructureHDBProcedureModel model = new XSKDataStructureHDBProcedureModel();
        model.setName("\"MYSCHEMA\".\"hdb_view::OrderProcedure\"");

        when(SqlFactory.getNative(mockConnection).exists(mockConnection, XSKCommonsUtils.extractArtifactNameWhenSchemaIsProvided(model.getName())[1], DatabaseArtifactTypes.PROCEDURE)).thenReturn(true);

        processorSpy.execute(mockConnection, model);
    }
}
