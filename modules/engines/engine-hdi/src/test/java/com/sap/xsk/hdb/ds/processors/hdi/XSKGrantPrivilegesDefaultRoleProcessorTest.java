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

import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class XSKGrantPrivilegesDefaultRoleProcessorTest {

  @Mock(answer = Answers.RETURNS_DEEP_STUBS)
  private Connection mockConnection;

  private String hdbRolePath = "/package/xsk_technical_privileges.hdbrole";
  private String packageName = "package";

  @Test
  public void testDeployedRole() throws XSKDataStructuresException, SQLException, IOException {
    String[] deploys = { hdbRolePath };
    testGrantPrivilege(deploys);
  }

  @Test
  public void testNoDeployedRole() throws XSKDataStructuresException, SQLException, IOException {
    String[] deploys = {};
    testGrantPrivilege(deploys);
  }

  private void testGrantPrivilege(String[] deploys) throws SQLException, IOException, XSKDataStructuresException {
    int expectedInvocations = Arrays.asList(deploys).contains(hdbRolePath) ? 1 : 0;

    XSKGrantPrivilegesDefaultRoleProcessor processorSpy = spy(XSKGrantPrivilegesDefaultRoleProcessor.class);;

    String container = "testContainer";
    String user = "HANA_USER";
    processorSpy.execute(mockConnection, container, user, deploys, packageName);

    String mockSQLCreate = "CREATE LOCAL TEMPORARY COLUMN TABLE #ROLES LIKE _SYS_DI.TT_SCHEMA_ROLES;";
    String mockSQLInsert = String.format("INSERT INTO #ROLES ( ROLE_NAME, PRINCIPAL_SCHEMA_NAME, PRINCIPAL_NAME ) VALUES ( 'xsk_technical_privileges', '', \'%s\' );", user);
    String mockSQLCall = String.format("CALL %s#DI.GRANT_CONTAINER_SCHEMA_ROLES(#ROLES, _SYS_DI.T_NO_PARAMETERS, ?, ?, ?);", container);
    String mockSQLDrop = "DROP TABLE #ROLES;";

    verify(processorSpy, times(expectedInvocations)).executeUpdate(mockConnection, mockSQLCreate, new String[]{});
    verify(processorSpy, times(expectedInvocations)).executeUpdate(mockConnection, mockSQLInsert, new String[]{});
    verify(processorSpy, times(expectedInvocations)).executeQuery(mockConnection, mockSQLCall, new String[]{});
    verify(processorSpy, times(expectedInvocations)).executeUpdate(mockConnection, mockSQLDrop, new String[]{});

  }

}
