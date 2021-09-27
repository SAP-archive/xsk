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
package com.sap.xsk.hdb.ds.test.handlers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.doNothing;
import static org.powermock.api.mockito.PowerMockito.spy;
import static org.powermock.api.mockito.PowerMockito.verifyPrivate;

import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableColumnModel;
import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableConstraintPrimaryKeyModel;
import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableConstraintsModel;
import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableModel;
import com.sap.xsk.hdb.ds.processors.table.utils.XSKTableAlterHandler;
import com.sap.xsk.utils.XSKHDBUtils;
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
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest({SqlFactory.class, Configuration.class, ProblemsFacade.class, XSKHDBUtils.class, XSKTableAlterHandler.class})
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
    MockitoAnnotations.initMocks(this);
  }

  @Before
  public void setup() throws Exception {
    PowerMockito.mockStatic(SqlFactory.class, Configuration.class, XSKHDBUtils.class, ProblemsFacade.class);
    when(Configuration.get(IDataStructureModel.DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE, "false")).thenReturn("true");
    when(SqlFactory.getNative(mockConnection)).thenReturn(mockSqlFactory);
    when(SqlFactory.deriveDialect(mockConnection)).thenReturn(new HanaSqlDialect());
  }

  @Test
  public void addColumnsSuccessfully() throws Exception {

    List<XSKDataStructureHDBTableColumnModel> columns = new ArrayList<>();
    columns.add(new XSKDataStructureHDBTableColumnModel("Id", "NVARCHAR", "32", true, false, null, null, null, true));
    columns.add(new XSKDataStructureHDBTableColumnModel("Name", "NVARCHAR", "32", true, false, null, null, null, false));
    tableModel.setColumns(columns);
    tableModel.setConstraints(constraintsModel);
    tableModel.setName("hdb_table::SampleHdbdd");
    tableModel.setSchema("MYSCHEMA");

    XSKTableAlterHandler tableAlterHandler = new XSKTableAlterHandler(mockConnection, tableModel);
    XSKTableAlterHandler handlerSpy = spy(tableAlterHandler);

    when(SqlFactory.getNative(mockConnection).alter()).thenReturn(alter);
    when(SqlFactory.getNative(mockConnection).alter().table(any())).thenReturn(alterTableBuilder);
    when(SqlFactory.getNative(mockConnection).alter().table(any()).add()).thenReturn(alterTableBuilder);
    when(alterTableBuilder.build()).thenReturn("sql");

    handlerSpy.addColumns(mockConnection);
    verifyPrivate(handlerSpy, times(2)).invoke("executeAlterBuilder", mockConnection, alterTableBuilder);
  }

  @Test(expected = SQLException.class)
  public void addColumnsFailedWhenPrimaryKey() throws Exception {
    List<XSKDataStructureHDBTableColumnModel> columns = new ArrayList<>();
    columns.add(new XSKDataStructureHDBTableColumnModel("Age", "INTEGER", "32", true, true, null, null, null, true));
    tableModel.setColumns(columns);
    tableModel.setConstraints(constraintsModel);
    tableModel.setName("hdb_table::SampleHdbdd");
    tableModel.setSchema("MYSCHEMA");

    XSKTableAlterHandler tableAlterHandler = new XSKTableAlterHandler(mockConnection, tableModel);
    XSKTableAlterHandler handlerSpy = spy(tableAlterHandler);

    when(SqlFactory.getNative(mockConnection).alter()).thenReturn(alter);
    when(SqlFactory.getNative(mockConnection).alter().table(any())).thenReturn(alterTableBuilder);
    when(SqlFactory.getNative(mockConnection).alter().table(any()).add()).thenReturn(alterTableBuilder);
    when(alterTableBuilder.build()).thenReturn("sql");
    doNothing().when(ProblemsFacade.class, "save", any(), any(),any(), any(), any(), any(), any(), any(), any(), any());
    handlerSpy.addColumns(mockConnection);
  }


  @Test
  public void removeColumnsSuccessfully() throws Exception {

    List<XSKDataStructureHDBTableColumnModel> columns = new ArrayList<>();
    columns.add(new XSKDataStructureHDBTableColumnModel("Id", "NVARCHAR", "32", true, false, null, null, null, true));
    columns.add(new XSKDataStructureHDBTableColumnModel("Name", "NVARCHAR", "32", true, false, null, null, null, false));
    tableModel.setColumns(columns);
    tableModel.setConstraints(constraintsModel);
    tableModel.setName("hdb_table::SampleHdbdd");
    tableModel.setSchema("MYSCHEMA");

    when(mockConnection.getMetaData()).thenReturn(databaseMetaData);
    when(databaseMetaData.getColumns(any(),any(),any(), any())).thenReturn(resultSet);
    when(resultSet.next()).thenReturn(true).thenReturn(false);
    when(resultSet.getString("COLUMN_NAME")).thenReturn("Test");
    when(resultSet.getString("TYPE_NAME")).thenReturn("NVARCHAR");

    XSKTableAlterHandler tableAlterHandler = new XSKTableAlterHandler(mockConnection, tableModel);
    XSKTableAlterHandler handlerSpy = spy(tableAlterHandler);

    when(SqlFactory.getNative(mockConnection).alter()).thenReturn(alter);
    when(SqlFactory.getNative(mockConnection).alter().table(any())).thenReturn(alterTableBuilder);
    when(SqlFactory.getNative(mockConnection).alter().table(any()).drop()).thenReturn(alterTableBuilder);
    when(alterTableBuilder.build()).thenReturn("sql");

    handlerSpy.removeColumns(mockConnection);
    verifyPrivate(handlerSpy, times(1)).invoke("executeAlterBuilder", mockConnection, alterTableBuilder);
  }

  @Test
  public void updateColumnsSuccessfully() throws Exception {

    List<XSKDataStructureHDBTableColumnModel> columns = new ArrayList<>();
    columns.add(new XSKDataStructureHDBTableColumnModel("Id", "NVARCHAR", "32", true, false, null, null, null, true));
    columns.add(new XSKDataStructureHDBTableColumnModel("Name", "NVARCHAR", "32", true, false, null, null, null, false));
    tableModel.setColumns(columns);
    tableModel.setConstraints(constraintsModel);
    tableModel.setName("hdb_table::SampleHdbdd");
    tableModel.setSchema("MYSCHEMA");

    when(mockConnection.getMetaData()).thenReturn(databaseMetaData);
    when(databaseMetaData.getColumns(any(),any(),any(), any())).thenReturn(resultSet);
    when(resultSet.next()).thenReturn(true).thenReturn(false);
    when(resultSet.getString("COLUMN_NAME")).thenReturn("Name");
    when(resultSet.getString("TYPE_NAME")).thenReturn("NVARCHAR");

    XSKTableAlterHandler tableAlterHandler = new XSKTableAlterHandler(mockConnection, tableModel);
    XSKTableAlterHandler handlerSpy = spy(tableAlterHandler);

    when(SqlFactory.getNative(mockConnection).alter()).thenReturn(alter);
    when(SqlFactory.getNative(mockConnection).alter().table(any())).thenReturn(alterTableBuilder);
    when(SqlFactory.getNative(mockConnection).alter().table(any()).alter()).thenReturn(alterTableBuilder);
    when(alterTableBuilder.build()).thenReturn("sql");

    handlerSpy.updateColumns(mockConnection);
    verifyPrivate(handlerSpy, times(1)).invoke("executeAlterBuilder", mockConnection, alterTableBuilder);

  }

  @Test
  public void rebuildIndecesSuccessfully() throws Exception {

    List<XSKDataStructureHDBTableColumnModel> columns = new ArrayList<>();
    columns.add(new XSKDataStructureHDBTableColumnModel("Id", "NVARCHAR", "32", true, false, null, null, null, true));
    columns.add(new XSKDataStructureHDBTableColumnModel("Name", "NVARCHAR", "32", true, false, null, null, null, false));
    tableModel.setColumns(columns);
    tableModel.setConstraints(constraintsModel);
    tableModel.setName("hdb_table::SampleHdbdd");
    tableModel.setSchema("MYSCHEMA");

    when(databaseMetaData.getColumns(any(),any(),any(), any())).thenReturn(resultSet);
    when(resultSet.next()).thenReturn(true).thenReturn(false);
    when(resultSet.getString("COLUMN_NAME")).thenReturn("Name");
    when(resultSet.getString("TYPE_NAME")).thenReturn("NVARCHAR");
    when(SqlFactory.getNative(mockConnection).alter()).thenReturn(alter);
    when(SqlFactory.getNative(mockConnection).alter().table(any())).thenReturn(alterTableBuilder);
    when(alterTableBuilder.build()).thenReturn("sql");
    when(databaseMetaData.getIndexInfo(null, "MYSCHEMA", "hdb_table::SampleHdbdd", true, false)).thenReturn(resultSet);
    when(resultSet.next()).thenReturn(true).thenReturn(false);

    XSKTableAlterHandler tableAlterHandler = new XSKTableAlterHandler(mockConnection, tableModel);
    XSKTableAlterHandler handlerSpy = spy(tableAlterHandler);

    handlerSpy.rebuildIndeces(mockConnection);
    verifyPrivate(handlerSpy, times(1)).invoke("executeAlterBuilder", mockConnection, alterTableBuilder);
  }

  @Test(expected = SQLException.class)
  public void ensurePrimaryKeyIsUnchangedSuccessfully() throws Exception {

    List<XSKDataStructureHDBTableColumnModel> columns = new ArrayList<>();
    columns.add(new XSKDataStructureHDBTableColumnModel("Id", "NVARCHAR", "32", true, true, null, null, null, true));
    columns.add(new XSKDataStructureHDBTableColumnModel("Name", "NVARCHAR", "32", true, false, null, null, null, false));
    tableModel.setColumns(columns);
    tableModel.setConstraints(constraintsModel);
    primaryKey.setColumns(new String[]{"Id"});
    constraintsModel.setPrimaryKey(primaryKey);
    tableModel.setName("hdb_table::SampleHdbdd");
    tableModel.setSchema("MYSCHEMA");

    when(databaseMetaData.getColumns(any(),any(),any(), any())).thenReturn(resultSet);
    when(resultSet.next()).thenReturn(true).thenReturn(false);
    when(resultSet.getString("COLUMN_NAME")).thenReturn("Id");
    when(resultSet.getString("TYPE_NAME")).thenReturn("NVARCHAR");
    when(SqlFactory.getNative(mockConnection).alter()).thenReturn(alter);
    when(SqlFactory.getNative(mockConnection).alter().table(any())).thenReturn(alterTableBuilder);
    when(alterTableBuilder.build()).thenReturn("sql");
    when(databaseMetaData.getIndexInfo(null, "MYSCHEMA", "hdb_table::SampleHdbdd", true, false)).thenReturn(resultSet);
    when(resultSet.next()).thenReturn(true).thenReturn(false);
    doNothing().when(ProblemsFacade.class, "save", any(), any(),any(), any(), any(), any(), any(), any(), any(), any());
    XSKTableAlterHandler tableAlterHandler = new XSKTableAlterHandler(mockConnection, tableModel);
    XSKTableAlterHandler handlerSpy = spy(tableAlterHandler);

    handlerSpy.ensurePrimaryKeyIsUnchanged(mockConnection);
  }

}
