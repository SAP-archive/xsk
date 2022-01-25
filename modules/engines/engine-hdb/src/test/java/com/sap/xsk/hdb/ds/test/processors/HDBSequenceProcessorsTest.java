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
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.doNothing;

import com.sap.xsk.hdb.ds.model.XSKDBContentType;
import com.sap.xsk.hdb.ds.model.hdbsequence.XSKDataStructureHDBSequenceModel;
import com.sap.xsk.hdb.ds.processors.hdbsequence.XSKHDBSequenceCreateProcessor;
import com.sap.xsk.hdb.ds.processors.hdbsequence.XSKHDBSequenceDropProcessor;
import com.sap.xsk.hdb.ds.processors.hdbsequence.XSKHDBSequenceUpdateProcessor;
import java.sql.Connection;
import java.sql.SQLException;
import org.eclipse.dirigible.api.v3.problems.ProblemsFacade;
import org.eclipse.dirigible.commons.config.Configuration;
import org.eclipse.dirigible.core.test.AbstractDirigibleTest;
import org.eclipse.dirigible.database.ds.model.IDataStructureModel;
import org.eclipse.dirigible.database.sql.DatabaseArtifactTypes;
import org.eclipse.dirigible.database.sql.SqlFactory;
import org.eclipse.dirigible.database.sql.builders.AlterBranchingBuilder;
import org.eclipse.dirigible.database.sql.builders.CreateBranchingBuilder;
import org.eclipse.dirigible.database.sql.builders.DropBranchingBuilder;
import org.eclipse.dirigible.database.sql.builders.sequence.AlterSequenceBuilder;
import org.eclipse.dirigible.database.sql.builders.sequence.CreateSequenceBuilder;
import org.eclipse.dirigible.database.sql.builders.sequence.DropSequenceBuilder;
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
@PrepareForTest({SqlFactory.class, Configuration.class, ProblemsFacade.class})
public class HDBSequenceProcessorsTest extends AbstractDirigibleTest {

  @Mock(answer = Answers.RETURNS_DEEP_STUBS)
  private Connection mockConnection;

  @Mock(answer = Answers.RETURNS_DEEP_STUBS)
  private SqlFactory mockSqlFactory;

  @Mock
  private CreateBranchingBuilder create;

  @Mock
  private AlterBranchingBuilder alter;

  @Mock
  private DropBranchingBuilder drop;

  @Mock
  private CreateSequenceBuilder mockCreateSequenceBuilder;

  @Mock
  private AlterSequenceBuilder mockAlterSequenceBuilder;

  @Mock
  private DropSequenceBuilder mockDropSequenceBuilder;

  @Before
  public void openMocks() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void executeCreateSuccessfully() throws Exception {
    XSKHDBSequenceCreateProcessor spyProccessor = spy(XSKHDBSequenceCreateProcessor.class);

    XSKDataStructureHDBSequenceModel mockModel = mock(XSKDataStructureHDBSequenceModel.class);
    String sql = "TestExecuteCreateSuccessfully";
    PowerMockito.mockStatic(SqlFactory.class, Configuration.class);

    when(SqlFactory.deriveDialect(mockConnection)).thenReturn(new HanaSqlDialect());
    when(mockModel.getName()).thenReturn("\"MYSCHEMA\".\"hdb_sequence::SampleSequence_HanaXSClassic\"");
    when(mockModel.getDBContentType()).thenReturn(XSKDBContentType.XS_CLASSIC);
    when(SqlFactory.getNative(mockConnection)).thenReturn(mockSqlFactory);
    when(SqlFactory.getNative(mockConnection).create()).thenReturn(create);
    when(SqlFactory.getNative(mockConnection).create().sequence(any())).thenReturn(mockCreateSequenceBuilder);
    when(mockModel.getResetBy()).thenReturn("LL");
    when(mockCreateSequenceBuilder.start(anyInt())).thenReturn(mockCreateSequenceBuilder);
    when(mockCreateSequenceBuilder.start(anyInt()).increment(anyInt())).thenReturn(mockCreateSequenceBuilder);
    when(mockCreateSequenceBuilder.start(anyInt()).increment(anyInt()).maxvalue(anyInt())).thenReturn(mockCreateSequenceBuilder);
    when(mockCreateSequenceBuilder.start(anyInt()).increment(anyInt()).maxvalue(anyInt()).nomaxvalue(anyBoolean()))
        .thenReturn(mockCreateSequenceBuilder);
    when(mockCreateSequenceBuilder.start(anyInt()).increment(anyInt()).maxvalue(anyInt()).nomaxvalue(anyBoolean()).minvalue(anyInt()))
        .thenReturn(mockCreateSequenceBuilder);
    when(mockCreateSequenceBuilder.start(anyInt()).increment(anyInt()).maxvalue(anyInt()).nomaxvalue(anyBoolean()).minvalue(anyInt())
        .nominvalue(anyBoolean())).thenReturn(mockCreateSequenceBuilder);
    when(mockCreateSequenceBuilder.start(anyInt()).increment(anyInt()).maxvalue(anyInt()).nomaxvalue(anyBoolean()).minvalue(anyInt())
        .nominvalue(anyBoolean()).cycles(anyBoolean())).thenReturn(mockCreateSequenceBuilder);
    when(mockCreateSequenceBuilder.start(anyInt()).increment(anyInt()).maxvalue(anyInt()).nomaxvalue(anyBoolean()).minvalue(anyInt())
        .nominvalue(anyBoolean()).cycles(anyBoolean()).resetBy(anyString())).thenReturn(mockCreateSequenceBuilder);
    when(mockCreateSequenceBuilder.start(anyInt()).increment(anyInt()).maxvalue(anyInt()).nomaxvalue(anyBoolean()).minvalue(anyInt())
        .nominvalue(anyBoolean()).cycles(anyBoolean()).resetBy(anyString()).build()).thenReturn(sql);
    spyProccessor.execute(mockConnection, mockModel);

    verify(spyProccessor, times(1)).executeSql(sql, mockConnection);
  }

  @Test
  public void executeUpdateSuccessfully() throws SQLException {
    XSKHDBSequenceUpdateProcessor spyProccessor = spy(XSKHDBSequenceUpdateProcessor.class);
    XSKDataStructureHDBSequenceModel mockModel = mock(XSKDataStructureHDBSequenceModel.class);
    String sql = "TestExecuteUpdateSuccessfully";
    PowerMockito.mockStatic(SqlFactory.class, Configuration.class);

    when(SqlFactory.deriveDialect(mockConnection)).thenReturn(new HanaSqlDialect());
    when(mockModel.getName()).thenReturn("\"MYSCHEMA\".\"hdb_sequence::SampleSequence_HanaXSClassic\"");
    when(mockModel.getDBContentType()).thenReturn(XSKDBContentType.XS_CLASSIC);
    when(SqlFactory.getNative(mockConnection)).thenReturn(mockSqlFactory);
    when(SqlFactory.getNative(mockConnection).alter()).thenReturn(alter);
    when(SqlFactory.getNative(mockConnection).alter().sequence(any())).thenReturn(mockAlterSequenceBuilder);
    when(mockModel.getResetBy()).thenReturn("LL");
    when(mockAlterSequenceBuilder.start(anyInt())).thenReturn(mockAlterSequenceBuilder);
    when(mockAlterSequenceBuilder.start(anyInt()).increment(anyInt())).thenReturn(mockAlterSequenceBuilder);
    when(mockAlterSequenceBuilder.start(anyInt()).increment(anyInt()).maxvalue(anyInt())).thenReturn(mockAlterSequenceBuilder);
    when(mockAlterSequenceBuilder.start(anyInt()).increment(anyInt()).maxvalue(anyInt()).nomaxvalue(anyBoolean()))
        .thenReturn(mockAlterSequenceBuilder);
    when(mockAlterSequenceBuilder.start(anyInt()).increment(anyInt()).maxvalue(anyInt()).nomaxvalue(anyBoolean()).minvalue(anyInt()))
        .thenReturn(mockAlterSequenceBuilder);
    when(mockAlterSequenceBuilder.start(anyInt()).increment(anyInt()).maxvalue(anyInt()).nomaxvalue(anyBoolean()).minvalue(anyInt())
        .nominvalue(anyBoolean())).thenReturn(mockAlterSequenceBuilder);
    when(mockAlterSequenceBuilder.start(anyInt()).increment(anyInt()).maxvalue(anyInt()).nomaxvalue(anyBoolean()).minvalue(anyInt())
        .nominvalue(anyBoolean()).cycles(anyBoolean())).thenReturn(mockAlterSequenceBuilder);
    when(mockAlterSequenceBuilder.start(anyInt()).increment(anyInt()).maxvalue(anyInt()).nomaxvalue(anyBoolean()).minvalue(anyInt())
        .nominvalue(anyBoolean()).cycles(anyBoolean()).resetBy(anyString())).thenReturn(mockAlterSequenceBuilder);
    when(mockAlterSequenceBuilder.start(anyInt()).increment(anyInt()).maxvalue(anyInt()).nomaxvalue(anyBoolean()).minvalue(anyInt())
        .nominvalue(anyBoolean()).cycles(anyBoolean()).resetBy(anyString()).build()).thenReturn(sql);
    spyProccessor.execute(mockConnection, mockModel);

    verify(spyProccessor, times(1)).executeSql(sql, mockConnection);
  }

  @Test
  public void executeDropSuccessfully() throws SQLException {
    XSKHDBSequenceDropProcessor spyProccessor = spy(XSKHDBSequenceDropProcessor.class);
    PowerMockito.mockStatic(SqlFactory.class, Configuration.class);
    XSKDataStructureHDBSequenceModel mockModel = mock(XSKDataStructureHDBSequenceModel.class);
    String sql = "TestExecuteDropSuccessfully";
    when(Configuration.get(IDataStructureModel.DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE, "false")).thenReturn("false");
    when(SqlFactory.getNative(mockConnection)).thenReturn(mockSqlFactory);

    when(SqlFactory.deriveDialect(mockConnection)).thenReturn(new HanaSqlDialect());
    when(mockModel.getName()).thenReturn("\"MYSCHEMA\".\"hdb_sequence::SampleSequence_HanaXSClassic\"");
    when(SqlFactory.getNative(mockConnection).exists(mockConnection, mockModel.getName(), DatabaseArtifactTypes.SEQUENCE)).thenReturn(true);
    when(mockModel.getDBContentType()).thenReturn(XSKDBContentType.XS_CLASSIC);
    when(SqlFactory.getNative(mockConnection)).thenReturn(mockSqlFactory);
    when(SqlFactory.getNative(mockConnection).drop()).thenReturn(drop);
    when(SqlFactory.getNative(mockConnection).drop().sequence(any())).thenReturn(mockDropSequenceBuilder);
    when(SqlFactory.getNative(mockConnection).drop().sequence(any()).build()).thenReturn(sql);

    spyProccessor.execute(mockConnection, mockModel);

    verify(spyProccessor, times(1)).executeSql(sql, mockConnection);
  }

  @Test(expected = IllegalStateException.class)
  public void executeCreateFailed() throws Exception {
    XSKHDBSequenceCreateProcessor spyProccessor = spy(XSKHDBSequenceCreateProcessor.class);

    XSKDataStructureHDBSequenceModel mockModel = mock(XSKDataStructureHDBSequenceModel.class);
    PowerMockito.mockStatic(SqlFactory.class, Configuration.class, ProblemsFacade.class);
    when(mockModel.getName()).thenReturn("\"MYSCHEMA\".\"hdb_sequence::SampleSequence_HanaXSClassic\"");
    when(mockModel.getDBContentType()).thenReturn(XSKDBContentType.OTHERS);
    when(SqlFactory.deriveDialect(mockConnection)).thenReturn(new PostgresSqlDialect());
    doNothing().when(ProblemsFacade.class, "save", any(), any(), any(), any(), any(), any(), any(), any(), any(), any());
    spyProccessor.execute(mockConnection, mockModel);
  }

  @Test(expected = IllegalStateException.class)
  public void executeUpdateFailed() throws Exception {
    XSKHDBSequenceUpdateProcessor spyProccessor = spy(XSKHDBSequenceUpdateProcessor.class);
    XSKDataStructureHDBSequenceModel mockModel = mock(XSKDataStructureHDBSequenceModel.class);
    PowerMockito.mockStatic(SqlFactory.class, Configuration.class, ProblemsFacade.class);
    when(mockModel.getName()).thenReturn("\"MYSCHEMA\".\"hdb_sequence::SampleSequence_HanaXSClassic\"");
    when(mockModel.getDBContentType()).thenReturn(XSKDBContentType.OTHERS);
    when(SqlFactory.deriveDialect(mockConnection)).thenReturn(new PostgresSqlDialect());
    doNothing().when(ProblemsFacade.class, "save", any(), any(), any(), any(), any(), any(), any(), any(), any(), any());
    spyProccessor.execute(mockConnection, mockModel);
  }

  @Test(expected = IllegalStateException.class)
  public void executeDropFailed() throws Exception {
    XSKHDBSequenceDropProcessor spyProccessor = spy(XSKHDBSequenceDropProcessor.class);
    PowerMockito.mockStatic(SqlFactory.class, Configuration.class, ProblemsFacade.class);
    XSKDataStructureHDBSequenceModel mockModel = mock(XSKDataStructureHDBSequenceModel.class);
    when(mockModel.getName()).thenReturn("\"MYSCHEMA\".\"hdb_sequence::SampleSequence_HanaXSClassic\"");
    when(Configuration.get(IDataStructureModel.DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE, "false")).thenReturn("false");
    when(SqlFactory.getNative(mockConnection)).thenReturn(mockSqlFactory);
    when(SqlFactory.getNative(mockConnection).exists(mockConnection, mockModel.getName(), DatabaseArtifactTypes.SEQUENCE)).thenReturn(true);
    when(mockModel.getDBContentType()).thenReturn(XSKDBContentType.OTHERS);
    when(SqlFactory.deriveDialect(mockConnection)).thenReturn(new PostgresSqlDialect());
    doNothing().when(ProblemsFacade.class, "save", any(), any(), any(), any(), any(), any(), any(), any(), any(), any());
    spyProccessor.execute(mockConnection, mockModel);
  }

}
