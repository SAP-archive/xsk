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
package com.sap.xsk.hdb.ds.test.processors;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.eclipse.dirigible.api.v3.problems.ProblemsFacade;
import org.eclipse.dirigible.commons.config.Configuration;
import org.eclipse.dirigible.core.scheduler.api.ISynchronizerArtefactType.ArtefactState;
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
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import com.sap.xsk.hdb.ds.model.XSKDBContentType;
import com.sap.xsk.hdb.ds.model.XSKDataStructureModelFactory;
import com.sap.xsk.hdb.ds.model.hdbview.XSKDataStructureHDBViewModel;
import com.sap.xsk.hdb.ds.processors.view.XSKViewCreateProcessor;
import com.sap.xsk.hdb.ds.processors.view.XSKViewDropProcessor;
import com.sap.xsk.hdb.ds.service.manager.IXSKDataStructureManager;
import com.sap.xsk.hdb.ds.synchronizer.XSKDataStructuresSynchronizer;
import com.sap.xsk.hdb.ds.test.parser.XSKViewParserTest;
import com.sap.xsk.utils.XSKConstants;

@RunWith(MockitoJUnitRunner.class)
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

	@Mock
	private XSKDataStructuresSynchronizer dataStructuresSynchronizer;

	@InjectMocks
	private XSKViewCreateProcessor processor = new XSKViewCreateProcessor();

	@Before
	public void openMocks() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void executeCreateViewHANAv1Successfully() throws Exception {
		XSKViewCreateProcessor processorSpy = spy(XSKViewCreateProcessor.class);
		String sample = IOUtils.toString(XSKViewParserTest.class.getResourceAsStream("/ItemsByOrderHANAv1.hdbview"), StandardCharsets.UTF_8);

		XSKDataStructureHDBViewModel model = XSKDataStructureModelFactory.parseView("hdb_view.db.a1/path/ItemsByOrderHANAv1.hdbview", sample);
		String mockSQL = "testSQLScript";
		try (MockedStatic<SqlFactory> sqlFactory = Mockito.mockStatic(SqlFactory.class);
				MockedStatic<Configuration> configuration = Mockito.mockStatic(Configuration.class)) {
			sqlFactory.when(() -> SqlFactory.getNative(mockConnection)).thenReturn(mockSqlFactory);
			sqlFactory.when(() -> SqlFactory.deriveDialect(mockConnection)).thenReturn(new HanaSqlDialect());
			sqlFactory.when(() -> SqlFactory.getNative(mockConnection).create()).thenReturn(create);
			sqlFactory.when(() -> SqlFactory.getNative(mockConnection).create().view(any())).thenReturn(mockCreateViewBuilder);
			sqlFactory.when(() -> SqlFactory.getNative(mockConnection).create().view(any()).asSelect(any())).thenReturn(mockCreateViewBuilder);
			sqlFactory.when(() -> SqlFactory.getNative(mockConnection).create().view(any()).asSelect(any()).build()).thenReturn(mockSQL);
			configuration.when(() -> Configuration.get(IDataStructureModel.DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE, "false")).thenReturn("true");
			
			Mockito.doNothing().when(processorSpy).applyArtefactState(any(), any(), any(), any(), any());
			
			processorSpy.execute(mockConnection, model);
			assertEquals(model.getDBContentType(), XSKDBContentType.XS_CLASSIC);
			verify(processorSpy, times(1)).executeSql(mockSQL, mockConnection);
			verify(processorSpy, times(1)).applyArtefactState(any(), any(), any(), eq(ArtefactState.SUCCESSFUL_CREATE), any());
		}
	}

	@Test
	public void executeCreateViewHANAv2Successfully() throws Exception {
		XSKViewCreateProcessor processorSpy = spy(XSKViewCreateProcessor.class);
		String sample = IOUtils.toString(XSKViewParserTest.class.getResourceAsStream("/ItemsByOrderHANAv2.hdbview"), StandardCharsets.UTF_8);

		XSKDataStructureHDBViewModel model = XSKDataStructureModelFactory.parseView("hdb_view.db/ItemsByOrderHANAv2.hdbview", sample);
		model.setRawContent(sample);
		String sql = XSKConstants.XSK_HDBVIEW_CREATE + model.getRawContent();

		try (MockedStatic<SqlFactory> sqlFactory = Mockito.mockStatic(SqlFactory.class);
				MockedStatic<Configuration> configuration = Mockito.mockStatic(Configuration.class)) {
			sqlFactory.when(() -> SqlFactory.getNative(mockConnection)).thenReturn(mockSqlFactory);
			sqlFactory.when(() -> SqlFactory.deriveDialect(mockConnection)).thenReturn(new HanaSqlDialect());
			sqlFactory.when(() -> SqlFactory.getNative(mockConnection).exists(mockConnection, model.getName(), DatabaseArtifactTypes.VIEW)).thenReturn(false);
			configuration.when(() -> Configuration.get(IDataStructureModel.DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE, "false")).thenReturn("true");

			Mockito.doNothing().when(processorSpy).applyArtefactState(any(), any(), any(), any(), any());
			
			processorSpy.execute(mockConnection, model);
			assertEquals(model.getDBContentType(), XSKDBContentType.OTHERS);
			verify(processorSpy, times(1)).executeSql(sql, mockConnection);
			verify(processorSpy, times(1)).applyArtefactState(any(), any(), any(), eq(ArtefactState.SUCCESSFUL_CREATE), any());
		}
	}

	@Test(expected = IllegalStateException.class)
	public void executeCreateViewPostgreSQLFailed() throws Exception {
		XSKViewCreateProcessor processorSpy = spy(XSKViewCreateProcessor.class);
		String sample = IOUtils.toString(XSKViewParserTest.class.getResourceAsStream("/ItemsByOrderHANAv2.hdbview"), StandardCharsets.UTF_8);

		XSKDataStructureHDBViewModel model = XSKDataStructureModelFactory.parseView("hdb_view.db/ItemsByOrderHANAv2.hdbview", sample);
		model.setRawContent(sample);

		try (MockedStatic<SqlFactory> sqlFactory = Mockito.mockStatic(SqlFactory.class);
				MockedStatic<ProblemsFacade> problemsFacade = Mockito.mockStatic(ProblemsFacade.class)) {
			sqlFactory.when(() -> SqlFactory.getNative(mockConnection)).thenReturn(mockSqlFactory);
			sqlFactory.when(() -> SqlFactory.deriveDialect(mockConnection)).thenReturn(new PostgresSqlDialect());
			problemsFacade.when(() -> ProblemsFacade.save(any(), any(), any(), any(), any(), any(), any(), any(), any(), any())).thenAnswer((Answer<Void>) invocation -> null);
			Mockito.doNothing().when(processorSpy).applyArtefactState(any(), any(), any(), any(), any());
			processorSpy.execute(mockConnection, model);
		}
	}

	@Test
	public void executeDropViewSuccessfully() throws Exception {
		XSKViewDropProcessor processorSpy = spy(XSKViewDropProcessor.class);
		String sample = IOUtils.toString(XSKViewParserTest.class.getResourceAsStream("/ItemsByOrderHANAv1.hdbview"), StandardCharsets.UTF_8);

		XSKDataStructureHDBViewModel model = XSKDataStructureModelFactory.parseView("hdb_view/ItemsByOrderHANAv1.hdbview", sample);
		String mockSQL = XSKConstants.XSK_HDBVIEW_DROP + model.getName();
		try (MockedStatic<SqlFactory> sqlFactory = Mockito.mockStatic(SqlFactory.class);
				MockedStatic<Configuration> configuration = Mockito.mockStatic(Configuration.class)) {
			sqlFactory.when(() -> SqlFactory.getNative(mockConnection)).thenReturn(mockSqlFactory);
			sqlFactory.when(() -> SqlFactory.deriveDialect(mockConnection)).thenReturn(new HanaSqlDialect());
			sqlFactory.when(() -> SqlFactory.getNative(mockConnection).exists(mockConnection, "MYSCHEMA.hdb_view::ItemsByOrderHANAv1", DatabaseArtifactTypes.VIEW)).thenReturn(true);
			sqlFactory.when(() -> SqlFactory.getNative(mockConnection).drop()).thenReturn(drop);
			sqlFactory.when(() -> SqlFactory.getNative(mockConnection).drop().view(any())).thenReturn(mockDropViewBuilder);
			sqlFactory.when(() -> SqlFactory.getNative(mockConnection).drop().view(any()).build()).thenReturn(mockSQL);
			configuration.when(() -> Configuration.get(IDataStructureModel.DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE, "false")).thenReturn("true");
			Mockito.doNothing().when(processorSpy).applyArtefactState(any(), any(), any(), any(), any());
			
			processorSpy.execute(mockConnection, model);
			verify(processorSpy, times(1)).executeSql(mockSQL, mockConnection);
			verify(processorSpy, times(1)).applyArtefactState(any(), any(), any(),eq(ArtefactState.SUCCESSFUL_DELETE), any());
		}
	}
}
