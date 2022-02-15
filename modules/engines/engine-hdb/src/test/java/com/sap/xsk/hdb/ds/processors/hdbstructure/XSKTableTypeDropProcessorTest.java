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
package com.sap.xsk.hdb.ds.processors.hdbstructure;

import com.sap.xsk.hdb.ds.model.hdbtabletype.XSKDataStructureHDBTableTypeModel;
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class XSKTableTypeDropProcessorTest {

  private static final String TABLE_TYPE_NAME = "TableType";
  private static final String SCHEMA_NAME = "SchemaName";
  private static final String ESCAPED_TABLE_TYPE_NAME = "ESCAPED_TABLE_TYPE_NAME";
  private static final String SQL_TO_DROP_TABLE_TYPE = "SQL TO DROP TABLE TYPE";
  private XSKTableTypeDropProcessor dropProcessorSpy;

  @Mock
  private HDBSynonymRemover synonymRemoverMock;

  @Mock
  private Connection connectionMock;

  private XSKDataStructureHDBTableTypeModel model;

  @Before
  public void setUp() {
    dropProcessorSpy = Mockito.spy(new XSKTableTypeDropProcessor(synonymRemoverMock));

    model = new XSKDataStructureHDBTableTypeModel();
    model.setName(TABLE_TYPE_NAME);
    model.setSchema(SCHEMA_NAME);
  }

  @Test
  public void testExecuteTableTypeDoesNotExist() throws SQLException {
    Mockito.doReturn(true).when(dropProcessorSpy).tableTypeDoesNotExist(connectionMock, model);

    dropProcessorSpy.execute(connectionMock, model);
    Mockito.verify(dropProcessorSpy).tableTypeDoesNotExist(connectionMock, model);
    Mockito.verify(synonymRemoverMock).removePublicSynonym(connectionMock, model.getSchema(), model.getName());
    Mockito.verifyNoInteractions(connectionMock);
    Mockito.verifyNoMoreInteractions(synonymRemoverMock);
  }

  @Test
  public void testExecuteTableType() throws SQLException {
    Mockito.doReturn(false).when(dropProcessorSpy).tableTypeDoesNotExist(connectionMock, model);
    Mockito.doReturn(ESCAPED_TABLE_TYPE_NAME).when(dropProcessorSpy).escapeTableTypeName(connectionMock, model);
    Mockito.doReturn(SQL_TO_DROP_TABLE_TYPE).when(dropProcessorSpy).getDropTableTypeSQL(connectionMock, ESCAPED_TABLE_TYPE_NAME);
    Mockito.doNothing().when(dropProcessorSpy).executeSql(SQL_TO_DROP_TABLE_TYPE, connectionMock);

    dropProcessorSpy.execute(connectionMock, model);

    Mockito.verify(dropProcessorSpy).tableTypeDoesNotExist(connectionMock, model);
    Mockito.verify(dropProcessorSpy).escapeTableTypeName(connectionMock, model);
    Mockito.verify(dropProcessorSpy).executeSql(SQL_TO_DROP_TABLE_TYPE, connectionMock);
    Mockito.verify(dropProcessorSpy).getDropTableTypeSQL(connectionMock, ESCAPED_TABLE_TYPE_NAME);
    Mockito.verify(synonymRemoverMock).removePublicSynonym(connectionMock, model.getSchema(), model.getName());
    Mockito.verifyNoMoreInteractions(connectionMock);
  }

  @Test
  public void testExecuteNoTableTypeSupportWithSQLException() throws SQLException {
    Mockito.doReturn(false).when(dropProcessorSpy).tableTypeDoesNotExist(connectionMock, model);
    Mockito.doReturn(ESCAPED_TABLE_TYPE_NAME).when(dropProcessorSpy).escapeTableTypeName(connectionMock, model);
    Mockito.doReturn(SQL_TO_DROP_TABLE_TYPE).when(dropProcessorSpy).getDropTableTypeSQL(connectionMock, ESCAPED_TABLE_TYPE_NAME);
    SQLException sqlException = new SQLException();
    Mockito.doThrow(sqlException).when(dropProcessorSpy).executeSql(SQL_TO_DROP_TABLE_TYPE, connectionMock);
    Mockito.doNothing().when(dropProcessorSpy).processException(model, sqlException);

    dropProcessorSpy.execute(connectionMock, model);

    Mockito.verify(dropProcessorSpy).tableTypeDoesNotExist(connectionMock, model);
    Mockito.verify(dropProcessorSpy).escapeTableTypeName(connectionMock, model);
    Mockito.verify(dropProcessorSpy).executeSql(SQL_TO_DROP_TABLE_TYPE, connectionMock);
    Mockito.verify(dropProcessorSpy).getDropTableTypeSQL(connectionMock, ESCAPED_TABLE_TYPE_NAME);
    Mockito.verify(dropProcessorSpy).processException(model, sqlException);
    Mockito.verify(synonymRemoverMock).removePublicSynonym(connectionMock, model.getSchema(), model.getName());
    Mockito.verifyNoMoreInteractions(connectionMock);
  }

  @Test
  public void testExecuteNoTableTypeSupportInDB() throws SQLException {
    Mockito.doReturn(false).when(dropProcessorSpy).tableTypeDoesNotExist(connectionMock, model);
    Mockito.doReturn(ESCAPED_TABLE_TYPE_NAME).when(dropProcessorSpy).escapeTableTypeName(connectionMock, model);

    IllegalStateException exception = new IllegalStateException();
    Mockito.doThrow(exception).when(dropProcessorSpy).getDropTableTypeSQL(connectionMock, ESCAPED_TABLE_TYPE_NAME);
    Mockito.doNothing().when(dropProcessorSpy).processException(model, exception);

    dropProcessorSpy.execute(connectionMock, model);

    Mockito.verify(dropProcessorSpy).tableTypeDoesNotExist(connectionMock, model);
    Mockito.verify(dropProcessorSpy).escapeTableTypeName(connectionMock, model);
    Mockito.verify(dropProcessorSpy, Mockito.times(0)).executeSql(Mockito.any(String.class), Mockito.any(Connection.class));
    Mockito.verify(dropProcessorSpy).getDropTableTypeSQL(connectionMock, ESCAPED_TABLE_TYPE_NAME);
    Mockito.verify(dropProcessorSpy).processException(model, exception);
    Mockito.verify(synonymRemoverMock).removePublicSynonym(connectionMock, model.getSchema(), model.getName());
    Mockito.verifyNoMoreInteractions(connectionMock);
  }
}