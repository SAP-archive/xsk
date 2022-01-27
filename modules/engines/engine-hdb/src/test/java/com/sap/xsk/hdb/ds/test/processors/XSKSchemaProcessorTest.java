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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.nio.charset.StandardCharsets;
import java.sql.Connection;

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
import org.eclipse.dirigible.database.sql.builders.schema.CreateSchemaBuilder;
import org.eclipse.dirigible.database.sql.builders.schema.DropSchemaBuilder;
import org.eclipse.dirigible.database.sql.dialects.hana.HanaSqlDialect;
import org.eclipse.dirigible.database.sql.dialects.postgres.PostgresSqlDialect;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import com.sap.xsk.hdb.ds.model.XSKDataStructureModelFactory;
import com.sap.xsk.hdb.ds.model.hdbschema.XSKDataStructureHDBSchemaModel;
import com.sap.xsk.hdb.ds.processors.hdbschema.HDBSchemaCreateProcessor;
import com.sap.xsk.hdb.ds.processors.hdbschema.HDBSchemaDropProcessor;
import com.sap.xsk.hdb.ds.synchronizer.XSKDataStructuresSynchronizer;
import com.sap.xsk.hdb.ds.test.parser.XSKSchemaParserTest;

@RunWith(MockitoJUnitRunner.class)
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
		MockitoAnnotations.openMocks(this);
		Configuration.set(IDataStructureModel.DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE, "true");
	}

	@Test
	public void executeCreateSchemaSuccessfully() throws Exception {
		HDBSchemaCreateProcessor processorSpy = spy(HDBSchemaCreateProcessor.class);
		String hdbschemaSample = IOUtils.toString(XSKSchemaParserTest.class.getResourceAsStream("/Myschema.hdbschema"), StandardCharsets.UTF_8);

		XSKDataStructureHDBSchemaModel model = XSKDataStructureModelFactory.parseSchema("hdb_schema/Myschema.hdbschema", hdbschemaSample);

		try (MockedStatic<SqlFactory> sqlFactory = Mockito.mockStatic(SqlFactory.class)) {
			String mockSQL = "testSQLScript";
			sqlFactory.when(() -> SqlFactory.getNative(mockConnection)).thenReturn(mockSqlfactory);
			sqlFactory.when(() -> SqlFactory.deriveDialect(mockConnection)).thenReturn(new HanaSqlDialect());
			sqlFactory.when(() -> SqlFactory.getNative(mockConnection).exists(mockConnection, "MySchema", DatabaseArtifactTypes.SCHEMA)).thenReturn(false);
			sqlFactory.when(() -> SqlFactory.getNative(mockConnection).create()).thenReturn(create);
			sqlFactory.when(() -> SqlFactory.getNative(mockConnection).create().schema(anyString())).thenReturn(mockCreateSchemaBuilder);
			sqlFactory.when(() -> SqlFactory.getNative(mockConnection).create().schema(anyString()).build()).thenReturn(mockSQL);

			Mockito.doNothing().when(processorSpy).applyArtefactState(any(), any(), any(), any(), any());
			
			processorSpy.execute(mockConnection, model);
			verify(processorSpy, times(1)).executeSql(mockSQL, mockConnection);
			verify(processorSpy, times(1)).applyArtefactState(any(), any(), any(), eq(ArtefactState.SUCCESSFUL_CREATE), any());
		}
	}

	@Test(expected = IllegalStateException.class)
	public void executeCreateSchemaFail() throws Exception {
		HDBSchemaCreateProcessor processorSpy = spy(HDBSchemaCreateProcessor.class);
		String hdbschemaSample = IOUtils.toString(XSKSchemaParserTest.class.getResourceAsStream("/Myschema.hdbschema"), StandardCharsets.UTF_8);

		XSKDataStructureHDBSchemaModel model = XSKDataStructureModelFactory.parseSchema("hdb_schema/Myschema.hdbschema", hdbschemaSample);

		try (MockedStatic<SqlFactory> sqlFactory = Mockito.mockStatic(SqlFactory.class);
				MockedStatic<ProblemsFacade> problemsFacade = Mockito.mockStatic(ProblemsFacade.class)) {
			sqlFactory.when(() -> SqlFactory.getNative(mockConnection)).thenReturn(mockSqlfactory);
			sqlFactory.when(() -> SqlFactory.deriveDialect(mockConnection)).thenReturn(new PostgresSqlDialect());
			problemsFacade.when(() -> ProblemsFacade.save(any(), any(), any(), any(), any(), any(), any(), any(), any(), any())).thenAnswer((Answer<Void>) invocation -> null);
			Mockito.doNothing().when(processorSpy).applyArtefactState(any(), any(), any(), any(), any());

			processorSpy.execute(mockConnection, model);
		}
	}

	@Test
	public void executeDropSchemaSuccessfully() throws Exception {
		HDBSchemaDropProcessor processorSpy = spy(HDBSchemaDropProcessor.class);
		String hdbschemaSample = IOUtils.toString(XSKSchemaParserTest.class.getResourceAsStream("/Myschema.hdbschema"), StandardCharsets.UTF_8);

		XSKDataStructureHDBSchemaModel model = XSKDataStructureModelFactory.parseSchema("hdb_schema/Myschema.hdbschema", hdbschemaSample);
		String mockSQL = "testSQLScript";

		try (MockedStatic<SqlFactory> sqlFactory = Mockito.mockStatic(SqlFactory.class)) {
			sqlFactory.when(() -> SqlFactory.getNative(mockConnection)).thenReturn(mockSqlfactory);
			sqlFactory.when(() -> SqlFactory.deriveDialect(mockConnection)).thenReturn(new HanaSqlDialect());
			sqlFactory.when(() -> SqlFactory.getNative(mockConnection).exists(mockConnection, "MySchema", DatabaseArtifactTypes.SCHEMA)).thenReturn(true);
			sqlFactory.when(() -> SqlFactory.getNative(mockConnection).drop()).thenReturn(drop);
			sqlFactory.when(() -> SqlFactory.getNative(mockConnection).drop().schema(anyString())).thenReturn(mockDropSchemaBuilder);
			sqlFactory.when(() -> SqlFactory.getNative(mockConnection).drop().schema(anyString()).build()).thenReturn(mockSQL);
			
			Mockito.doNothing().when(processorSpy).applyArtefactState(any(), any(), any(), any(), any());
			
			processorSpy.execute(mockConnection, model);
			verify(processorSpy, times(1)).executeSql(mockSQL, mockConnection);
			verify(processorSpy, times(1)).applyArtefactState(any(), any(), any(), eq(ArtefactState.SUCCESSFUL_DELETE), any());
		}
	}

	@Test(expected = IllegalStateException.class)
	public void executeDropSchemaFail() throws Exception {
		try (MockedStatic<SqlFactory> sqlFactory = Mockito.mockStatic(SqlFactory.class);
				MockedStatic<ProblemsFacade> problemsFacade = Mockito.mockStatic(ProblemsFacade.class)) {
			sqlFactory.when(() -> SqlFactory.getNative(mockConnection)).thenReturn(mockSqlfactory);
			sqlFactory.when(() -> SqlFactory.deriveDialect(mockConnection)).thenReturn(new PostgresSqlDialect());
			
			HDBSchemaDropProcessor processorSpy = spy(HDBSchemaDropProcessor.class);
			String hdbschemaSample = IOUtils.toString(XSKSchemaParserTest.class.getResourceAsStream("/Myschema.hdbschema"), StandardCharsets.UTF_8);
			
			XSKDataStructureHDBSchemaModel model = XSKDataStructureModelFactory.parseSchema("hdb_schema/Myschema.hdbschema", hdbschemaSample);
			problemsFacade.when(() -> ProblemsFacade.save(any(), any(), any(), any(), any(), any(), any(), any(), any(), any())).thenAnswer((Answer<Void>) invocation -> null);
			Mockito.doNothing().when(processorSpy).applyArtefactState(any(), any(), any(), any(), any());
			
			processorSpy.execute(mockConnection, model);
		}
	}
}
