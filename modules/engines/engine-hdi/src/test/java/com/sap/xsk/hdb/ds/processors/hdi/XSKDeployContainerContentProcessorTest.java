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
package com.sap.xsk.hdb.ds.processors.hdi;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.sap.xsk.hdb.ds.util.Message;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

@RunWith(MockitoJUnitRunner.class)
public class XSKDeployContainerContentProcessorTest {

  @Mock(answer = Answers.RETURNS_DEEP_STUBS)
  private Connection mockConnection;
  @Mock
  private ResultSet resultSetMock;
  @Mock
  private ResultSet resultSetFromSelectMock;
  @Mock
  private PreparedStatement preparedStatementMock;
  @Mock
  private CallableStatement callableStatementMock;

  @Test
  public void testExecuteCall() throws SQLException {

    XSKDeployContainerContentProcessor processorSpy = spy(XSKDeployContainerContentProcessor.class);
    ArrayList<Message> messages = new ArrayList<>();
    String container = "testContainer";

    String sqlMakeCall =
        "CALL " + container + "#DI.MAKE(#DEPLOY_PATHS, #UNDEPLOY_PATHS, #PATH_PARAMETERS, _SYS_DI.T_NO_PARAMETERS, ?, ?, ?);";
    String sqlSelectFromDeployPaths = "SELECT * FROM #DEPLOY_PATHS";

    when(mockConnection.prepareCall(sqlMakeCall)).thenReturn(callableStatementMock);
    when(callableStatementMock.executeQuery()).thenReturn(resultSetMock);
    when(mockConnection.prepareStatement(sqlSelectFromDeployPaths)).thenReturn(preparedStatementMock);
    when(preparedStatementMock.executeQuery()).thenReturn(resultSetFromSelectMock);

    String[][] result = { { "25", "1","5","HDI","12","12","/test_Deploy_Container/testCalcView.hdbcalculationview","INFO","7868","Success","0:0","path","5/12/2022" } ,
        { "REQUEST_ID", "ROW_ID", "LEVEL","TYPE","LIBRARY_ID","PLUGIN_ID","PATH","SEVERITY","MESSAGE_CODE","MESSAGE","LOCATION","LOCATION_PATH","TIMESTAMP_UTC" }};
    final AtomicInteger idx = new AtomicInteger(0);
    final MockRow row = new MockRow();
    doAnswer(new Answer<Boolean>() {
      @Override
      public Boolean answer(InvocationOnMock invocation) throws Throwable {
        int index = idx.getAndIncrement();
        if (result.length <= index) {
          return false;
        }
        String[] current = result[index];
        row.setCurrentRowData(current);
        return true;
      };
    }).when(resultSetMock).next();

    doAnswer(new Answer<String>() {
      @Override
      public String answer(InvocationOnMock invocation) throws Throwable {
        Object[] args = invocation.getArguments();
        int idx = ((Integer) args[0]).intValue();
        return row.getColumn(idx);
      };
    }).when(resultSetMock).getString(anyInt());

    when(resultSetMock.next()).thenReturn(true).thenReturn(false);
    when(resultSetFromSelectMock.next()).thenReturn(true).thenReturn(true).thenReturn(false);
    Message message = new Message(resultSetMock);
    messages.add(message);
    when(callableStatementMock.getInt(1)).thenReturn(1);
    when(resultSetFromSelectMock.getString(1)).thenReturn("test_Deploy_Container/testCalcView.hdbcalculationview");
    Mockito.doNothing().when(processorSpy).applyArtefactState(any(), any(), any(), any(), any());

    processorSpy.executeCall(mockConnection, sqlMakeCall);
    verify(processorSpy,times(1)).executeCall(mockConnection, sqlMakeCall);
    verify(processorSpy, times(1)).parseResultSet(resultSetMock);
    verify(processorSpy,times(2)).applyState(any(), any(), any(), any());

  }

  static class MockRow {
    String[] rowData;

    public void setCurrentRowData(String[] rowData) {
      this.rowData = rowData;
    }

    public String getColumn(int idx) {
      return rowData[idx - 1];
    }
  }
}
