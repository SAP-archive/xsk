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
package com.sap.xsk.synchronizer.cleaners;

import com.sap.db.jdbcext.wrapper.WrappedPreparedStatement;
import com.sap.xsk.synchronizer.XSJSLibSynchronizer;
import org.eclipse.dirigible.database.api.wrappers.WrappedDataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class XSJSSynchronizerDBCleanerTest {

  @Test
  public void cleanDBEntryForResourceTest() throws SQLException {
    PreparedStatement preparedStatementMock = createPreparedStatementMock();
    DataSource dataSourceMock = createDataSourceMock(preparedStatementMock);

    XSJSLibSynchronizerDBCleaner cleaner = new XSJSLibSynchronizerDBCleaner(dataSourceMock);
    cleaner.cleanup("testFolder/abc.xsjslib");

    verify(preparedStatementMock, times(1)).setString(1, "testFolder/abc.xsjslib%");
    verify(preparedStatementMock, times(1)).executeUpdate();
  }

  @Test
  public void cleanDBEntryForCollectionTest() throws SQLException {
    PreparedStatement preparedStatementMock = createPreparedStatementMock();
    DataSource dataSourceMock = createDataSourceMock(preparedStatementMock);

    XSJSLibSynchronizerDBCleaner cleaner = new XSJSLibSynchronizerDBCleaner(dataSourceMock);
    cleaner.cleanup("testFolder/");

    verify(preparedStatementMock, times(1)).setString(1, "testFolder/%");
    verify(preparedStatementMock, times(1)).executeUpdate();
  }

  private PreparedStatement createPreparedStatementMock() {
    return mock(WrappedPreparedStatement.class);
  }

  private DataSource createDataSourceMock(PreparedStatement preparedStatementMock) throws SQLException {
    Connection connectionMock = mock(Connection.class);
    when(connectionMock.prepareStatement("DELETE FROM \""
        + XSJSLibSynchronizer.XSJSLIB_SYNCHRONIZER_STATE_TABLE_NAME
        + "\" WHERE \"LOCATION\" LIKE ?")).thenReturn(preparedStatementMock);

    DataSource dataSourceMock = mock(WrappedDataSource.class);
    when(dataSourceMock.getConnection()).thenReturn(connectionMock);

    return dataSourceMock;
  }
}
