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
package com.sap.xsk.hdb.ds.test.handlers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableColumnModel;
import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableConstraintPrimaryKeyModel;
import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableConstraintsModel;
import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableModel;
import com.sap.xsk.hdb.ds.processors.table.XSKTableAlterHandler;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.dirigible.api.v3.problems.ProblemsFacade;
import org.eclipse.dirigible.commons.config.Configuration;
import org.eclipse.dirigible.database.ds.model.IDataStructureModel;
import org.eclipse.dirigible.database.sql.SqlFactory;
import org.eclipse.dirigible.database.sql.builders.AlterBranchingBuilder;
import org.eclipse.dirigible.database.sql.builders.table.AlterTableBuilder;
import org.eclipse.dirigible.database.sql.dialects.hana.HanaSqlDialect;
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

@RunWith(MockitoJUnitRunner.class)
public class HDBTableAlterHandlerTest {

  @Mock(answer = Answers.RETURNS_DEEP_STUBS)
  private Connection mockConnection;

  @Mock(answer = Answers.RETURNS_DEEP_STUBS)
  private SqlFactory mockSqlFactory;

  @Mock
  private AlterBranchingBuilder alter;

  @Mock
  private AlterTableBuilder alterTableBuilder;

  @Mock
  private DatabaseMetaData databaseMetaData;

  @Mock
  private ResultSet resultSet;

  private XSKDataStructureHDBTableConstraintPrimaryKeyModel primaryKey = new XSKDataStructureHDBTableConstraintPrimaryKeyModel();
  private XSKDataStructureHDBTableConstraintsModel constraintsModel = new XSKDataStructureHDBTableConstraintsModel();
  private XSKDataStructureHDBTableModel tableModel = new XSKDataStructureHDBTableModel();

  @Before
  public void openMocks() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void addColumnsSuccessfully() throws Exception {
    try (MockedStatic<SqlFactory> sqlFactory = Mockito.mockStatic(SqlFactory.class);
        MockedStatic<Configuration> configuration = Mockito.mockStatic(Configuration.class)) {
      configuration.when(() -> Configuration.get(IDataStructureModel.DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE, "false")).thenReturn("true");
      sqlFactory.when(() -> SqlFactory.getNative(mockConnection)).thenReturn(mockSqlFactory);
      sqlFactory.when(() -> SqlFactory.deriveDialect(mockConnection)).thenReturn(new HanaSqlDialect());

      List<XSKDataStructureHDBTableColumnModel> columns = new ArrayList<>();
      columns.add(new XSKDataStructureHDBTableColumnModel("Id", "NVARCHAR", "32", true, false, null, false, null, null, true, null));
      columns.add(new XSKDataStructureHDBTableColumnModel("Name", "NVARCHAR", "32", true, false, null, false, null, null, false, null));
      tableModel.setColumns(columns);
      tableModel.setConstraints(constraintsModel);
      tableModel.setName("hdb_table::SampleHdbdd");
      tableModel.setSchema("MYSCHEMA");

      XSKTableAlterHandler tableAlterHandler = new XSKTableAlterHandler(mockConnection, tableModel);
      XSKTableAlterHandler handlerSpy = spy(tableAlterHandler);

      sqlFactory.when(() -> SqlFactory.getNative(mockConnection).alter()).thenReturn(alter);
      sqlFactory.when(() -> SqlFactory.getNative(mockConnection).alter().table(any())).thenReturn(alterTableBuilder);
      sqlFactory.when(() -> SqlFactory.getNative(mockConnection).alter().table(any()).add()).thenReturn(alterTableBuilder);
      when(alterTableBuilder.build()).thenReturn("sql");

      handlerSpy.addColumns(mockConnection);
//			verifyPrivate(handlerSpy, times(2)).invoke("executeAlterBuilder", mockConnection, alterTableBuilder);
    }
  }

  @Test(expected = SQLException.class)
  public void addColumnsFailedWhenPrimaryKey() throws Exception {
    try (MockedStatic<SqlFactory> sqlFactory = Mockito.mockStatic(SqlFactory.class);
        MockedStatic<Configuration> configuration = Mockito.mockStatic(Configuration.class);
        MockedStatic<ProblemsFacade> problemsFacade = Mockito.mockStatic(ProblemsFacade.class)) {
      configuration.when(() -> Configuration.get(IDataStructureModel.DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE, "false")).thenReturn("true");
      sqlFactory.when(() -> SqlFactory.getNative(mockConnection)).thenReturn(mockSqlFactory);
      sqlFactory.when(() -> SqlFactory.deriveDialect(mockConnection)).thenReturn(new HanaSqlDialect());

      List<XSKDataStructureHDBTableColumnModel> columns = new ArrayList<>();
      columns.add(new XSKDataStructureHDBTableColumnModel("Age", "INTEGER", "32", true, true, null, false, null, null, true, null));
      tableModel.setColumns(columns);
      tableModel.setConstraints(constraintsModel);
      tableModel.setName("hdb_table::SampleHdbdd");
      tableModel.setSchema("MYSCHEMA");

      XSKTableAlterHandler tableAlterHandler = new XSKTableAlterHandler(mockConnection, tableModel);
      XSKTableAlterHandler handlerSpy = spy(tableAlterHandler);

      sqlFactory.when(() -> SqlFactory.getNative(mockConnection).alter()).thenReturn(alter);
      sqlFactory.when(() -> SqlFactory.getNative(mockConnection).alter().table(any())).thenReturn(alterTableBuilder);
      sqlFactory.when(() -> SqlFactory.getNative(mockConnection).alter().table(any()).add()).thenReturn(alterTableBuilder);
      problemsFacade.when(() -> ProblemsFacade.save(any(), any(), any(), any(), any(), any(), any(), any(), any(), any()))
          .thenAnswer((Answer<Void>) invocation -> null);
      handlerSpy.addColumns(mockConnection);
    }
  }

  @Test
  public void removeColumnsSuccessfully() throws Exception {

    try (MockedStatic<SqlFactory> sqlFactory = Mockito.mockStatic(SqlFactory.class);
        MockedStatic<Configuration> configuration = Mockito.mockStatic(Configuration.class)) {
      configuration.when(() -> Configuration.get(IDataStructureModel.DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE, "false")).thenReturn("true");
      sqlFactory.when(() -> SqlFactory.getNative(mockConnection)).thenReturn(mockSqlFactory);
      sqlFactory.when(() -> SqlFactory.deriveDialect(mockConnection)).thenReturn(new HanaSqlDialect());

      List<XSKDataStructureHDBTableColumnModel> columns = new ArrayList<>();
      columns.add(new XSKDataStructureHDBTableColumnModel("Id", "NVARCHAR", "32", true, false, null, false, null, null, true, null));
      columns.add(new XSKDataStructureHDBTableColumnModel("Name", "NVARCHAR", "32", true, false, null, false, null, null, false, null));
      tableModel.setColumns(columns);
      tableModel.setConstraints(constraintsModel);
      tableModel.setName("hdb_table::SampleHdbdd");
      tableModel.setSchema("MYSCHEMA");

      when(mockConnection.getMetaData()).thenReturn(databaseMetaData);
      when(databaseMetaData.getColumns(any(), any(), any(), any())).thenReturn(resultSet);
      when(resultSet.next()).thenReturn(true).thenReturn(false);
      when(resultSet.getString("COLUMN_NAME")).thenReturn("Test");
      when(resultSet.getString("TYPE_NAME")).thenReturn("NVARCHAR");

      XSKTableAlterHandler tableAlterHandler = new XSKTableAlterHandler(mockConnection, tableModel);
      XSKTableAlterHandler handlerSpy = spy(tableAlterHandler);

      sqlFactory.when(() -> SqlFactory.getNative(mockConnection).alter()).thenReturn(alter);
      sqlFactory.when(() -> SqlFactory.getNative(mockConnection).alter().table(any())).thenReturn(alterTableBuilder);
      sqlFactory.when(() -> SqlFactory.getNative(mockConnection).alter().table(any()).drop()).thenReturn(alterTableBuilder);
      when(alterTableBuilder.build()).thenReturn("sql");

      handlerSpy.removeColumns(mockConnection);
      // TODO: Refactor -> Mockito doesn't have support for verifyPrivate()
      // verifyPrivate(handlerSpy, times(1)).invoke("executeAlterBuilder", mockConnection, alterTableBuilder);
    }
  }

  @Test
  public void updateColumnsSuccessfully() throws Exception {
    try (MockedStatic<SqlFactory> sqlFactory = Mockito.mockStatic(SqlFactory.class);
        MockedStatic<Configuration> configuration = Mockito.mockStatic(Configuration.class)) {
      configuration.when(() -> Configuration.get(IDataStructureModel.DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE, "false")).thenReturn("true");
      sqlFactory.when(() -> SqlFactory.getNative(mockConnection)).thenReturn(mockSqlFactory);
      sqlFactory.when(() -> SqlFactory.deriveDialect(mockConnection)).thenReturn(new HanaSqlDialect());

      List<XSKDataStructureHDBTableColumnModel> columns = new ArrayList<>();
      columns.add(new XSKDataStructureHDBTableColumnModel("Id", "NVARCHAR", "32", true, false, null, false, null, null, true, null));
      columns.add(new XSKDataStructureHDBTableColumnModel("Name", "NVARCHAR", "32", true, false, null, false, null, null, false, null));
      tableModel.setColumns(columns);
      tableModel.setConstraints(constraintsModel);
      tableModel.setName("hdb_table::SampleHdbdd");
      tableModel.setSchema("MYSCHEMA");

      when(mockConnection.getMetaData()).thenReturn(databaseMetaData);
      when(databaseMetaData.getColumns(any(), any(), any(), any())).thenReturn(resultSet);
      when(resultSet.next()).thenReturn(true).thenReturn(false);
      when(resultSet.getString("COLUMN_NAME")).thenReturn("Name");
      when(resultSet.getString("TYPE_NAME")).thenReturn("NVARCHAR");

      XSKTableAlterHandler tableAlterHandler = new XSKTableAlterHandler(mockConnection, tableModel);
      XSKTableAlterHandler handlerSpy = spy(tableAlterHandler);

      sqlFactory.when(() -> SqlFactory.getNative(mockConnection).alter()).thenReturn(alter);
      sqlFactory.when(() -> SqlFactory.getNative(mockConnection).alter().table(any())).thenReturn(alterTableBuilder);
      sqlFactory.when(() -> SqlFactory.getNative(mockConnection).alter().table(any()).alter()).thenReturn(alterTableBuilder);
      when(alterTableBuilder.build()).thenReturn("sql");

      handlerSpy.updateColumns(mockConnection);
      // TODO: Refactor -> Mockito doesn't have support for verifyPrivate()
      // verifyPrivate(handlerSpy, times(1)).invoke("executeAlterBuilder", mockConnection, alterTableBuilder);
    }
  }

  @Test
  public void rebuildIndecesSuccessfully() throws Exception {
    try (MockedStatic<SqlFactory> sqlFactory = Mockito.mockStatic(SqlFactory.class);
        MockedStatic<Configuration> configuration = Mockito.mockStatic(Configuration.class)) {
      configuration.when(() -> Configuration.get(IDataStructureModel.DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE, "false")).thenReturn("true");
      sqlFactory.when(() -> SqlFactory.getNative(mockConnection)).thenReturn(mockSqlFactory);
      sqlFactory.when(() -> SqlFactory.deriveDialect(mockConnection)).thenReturn(new HanaSqlDialect());
      List<XSKDataStructureHDBTableColumnModel> columns = new ArrayList<>();
      columns.add(new XSKDataStructureHDBTableColumnModel("Id", "NVARCHAR", "32", true, false, null, false, null, null, true, null));
      columns.add(new XSKDataStructureHDBTableColumnModel("Name", "NVARCHAR", "32", true, false, null, false, null, null, false, null));
      tableModel.setColumns(columns);
      tableModel.setConstraints(constraintsModel);
      tableModel.setName("hdb_table::SampleHdbdd");
      tableModel.setSchema("MYSCHEMA");

      sqlFactory.when(() -> SqlFactory.getNative(mockConnection).alter()).thenReturn(alter);
      sqlFactory.when(() -> SqlFactory.getNative(mockConnection).alter().table(any())).thenReturn(alterTableBuilder);
      when(alterTableBuilder.build()).thenReturn("sql");

      XSKTableAlterHandler tableAlterHandler = new XSKTableAlterHandler(mockConnection, tableModel);
      XSKTableAlterHandler handlerSpy = spy(tableAlterHandler);

      handlerSpy.rebuildIndeces(mockConnection);
      // TODO: Refactor -> Mockito doesn't have support for verifyPrivate()
      // verifyPrivate(handlerSpy, times(1)).invoke("executeAlterBuilder", mockConnection, alterTableBuilder);
    }
  }

  @Test(expected = SQLException.class)
  public void ensurePrimaryKeyIsUnchangedSuccessfully() throws Exception {
    try (MockedStatic<SqlFactory> sqlFactory = Mockito.mockStatic(SqlFactory.class);
        MockedStatic<Configuration> configuration = Mockito.mockStatic(Configuration.class);
        MockedStatic<ProblemsFacade> problemsFacade = Mockito.mockStatic(ProblemsFacade.class)) {
      configuration.when(() -> Configuration.get(IDataStructureModel.DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE, "false")).thenReturn("true");
      sqlFactory.when(() -> SqlFactory.getNative(mockConnection)).thenReturn(mockSqlFactory);
      sqlFactory.when(() -> SqlFactory.deriveDialect(mockConnection)).thenReturn(new HanaSqlDialect());

      List<XSKDataStructureHDBTableColumnModel> columns = new ArrayList<>();
      columns.add(new XSKDataStructureHDBTableColumnModel("Id", "NVARCHAR", "32", true, true, null, false, null, null, true, null));
      columns.add(new XSKDataStructureHDBTableColumnModel("Name", "NVARCHAR", "32", true, false, null, false, null, null, false, null));
      tableModel.setColumns(columns);
      tableModel.setConstraints(constraintsModel);
      primaryKey.setColumns(new String[]{"Id"});
      constraintsModel.setPrimaryKey(primaryKey);
      tableModel.setName("hdb_table::SampleHdbdd");
      tableModel.setSchema("MYSCHEMA");

      sqlFactory.when(() -> SqlFactory.getNative(mockConnection).alter()).thenReturn(alter);
      problemsFacade.when(() -> ProblemsFacade.save(any(), any(), any(), any(), any(), any(), any(), any(), any(), any()))
          .thenAnswer((Answer<Void>) invocation -> null);
      XSKTableAlterHandler tableAlterHandler = new XSKTableAlterHandler(mockConnection, tableModel);
      XSKTableAlterHandler handlerSpy = spy(tableAlterHandler);

      handlerSpy.ensurePrimaryKeyIsUnchanged(mockConnection);
    }
  }

}
