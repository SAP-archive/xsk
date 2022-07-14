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

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class XSKGrantPrivilegesDefaultRoleProcessorTest {

  @Mock(answer = Answers.RETURNS_DEEP_STUBS)
  private Connection mockConnection;

  private String hdbRolePath = "/package/xsk_technical_privileges.hdbrole";
  private String user = "HANA_USER";
  private String container = "testContainer";

  @Test
  public void testDeployedRole() throws SQLException {
    String[] deploys = { hdbRolePath };
    testGrantPrivilege(deploys, user, container);
  }

  @Test
  public void testNoDeployedRole() throws SQLException {
    String[] deploys = {};
    testGrantPrivilege(deploys, user, container);
  }

  @Test
  public void testNoHanaUserProvided() {
    Exception exception = assertThrows(RuntimeException.class, () -> {
      String[] deploys = {};
      testGrantPrivilege(deploys, null, container);
    });

    String expectedMessage = "xsk_technical_privileges.hdbrole assignment failed. No user provided.";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }

  private void testGrantPrivilege(String[] deploys, String user, String container) throws SQLException {
    int expectedInvocations = Arrays.asList(deploys).contains(hdbRolePath) ? 1 : 0;

    XSKGrantPrivilegesDefaultRoleProcessor processorSpy = spy(XSKGrantPrivilegesDefaultRoleProcessor.class);;

    processorSpy.execute(mockConnection, container, user, deploys);

    String mockSQLCreate = "CREATE LOCAL TEMPORARY COLUMN TABLE #ROLES LIKE _SYS_DI.TT_SCHEMA_ROLES;";
    String mockSQLInsert = "INSERT INTO #ROLES ( ROLE_NAME, PRINCIPAL_SCHEMA_NAME, PRINCIPAL_NAME ) VALUES ( 'xsk_technical_privileges', '', \'" + user + "\' );";
    String mockSQLCall = "CALL " + container +"#DI.GRANT_CONTAINER_SCHEMA_ROLES(#ROLES, _SYS_DI.T_NO_PARAMETERS, ?, ?, ?);";
    String mockSQLDrop = "DROP TABLE #ROLES;";

    verify(processorSpy, times(expectedInvocations)).executeUpdate(mockConnection, mockSQLCreate, new String[]{});
    verify(processorSpy, times(expectedInvocations)).executeUpdate(mockConnection, mockSQLInsert, new String[]{});
    verify(processorSpy, times(expectedInvocations)).executeQuery(mockConnection, mockSQLCall, new String[]{});
    verify(processorSpy, times(expectedInvocations)).executeUpdate(mockConnection, mockSQLDrop, new String[]{});

  }

}
