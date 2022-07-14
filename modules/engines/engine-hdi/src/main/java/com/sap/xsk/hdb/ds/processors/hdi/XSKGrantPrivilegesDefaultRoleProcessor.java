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

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import com.sap.xsk.hdb.ds.exceptions.XSKGrantPrivilegesSQLException;

public class XSKGrantPrivilegesDefaultRoleProcessor extends XSKHDIAbstractProcessor {

  public void execute(Connection connection, String container, String user, String[] deployPaths) {
    if (user == null) {
      throw new IllegalStateException("xsk_technical_privileges.hdbrole assignment failed. No user provided.");
    }

    Arrays.stream(deployPaths).filter(deployPath -> deployPath.endsWith("xsk_technical_privileges.hdbrole")).forEach(filePath -> {
      try {
        grantPrivileges(connection, container, user);
      } catch (SQLException exception) {
        throw new XSKGrantPrivilegesSQLException("Failed to grant xsk_technical_privileges.hdbrole to user " + user, exception);
      }
    });

  }

  private void grantPrivileges(Connection connection, String container, String user) throws SQLException {
    executeUpdate(connection, "CREATE LOCAL TEMPORARY COLUMN TABLE #ROLES LIKE _SYS_DI.TT_SCHEMA_ROLES;");

    executeUpdate(connection, "INSERT INTO #ROLES ( ROLE_NAME, PRINCIPAL_SCHEMA_NAME, PRINCIPAL_NAME ) VALUES ( 'xsk_technical_privileges', '', \'" + user + "\' );");

    executeQuery(connection, "CALL " + container + "#DI.GRANT_CONTAINER_SCHEMA_ROLES(#ROLES, _SYS_DI.T_NO_PARAMETERS, ?, ?, ?);");
    executeUpdate(connection, "DROP TABLE #ROLES;");

  }

}
