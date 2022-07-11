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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

public class XSKGrantPrivilegesDefaultRoleProcessor extends XSKHDIAbstractProcessor {

  private static final Logger LOGGER = LoggerFactory.getLogger(XSKGrantPrivilegesDefaultRoleProcessor.class);

  public void execute(Connection connection, String container, String user, String[] deployPaths, String rootPath) throws SQLException {
    if (Arrays.asList(deployPaths).contains("/" + rootPath +"/xsk_technical_privileges.hdbrole"))  {
        if (user == null) {
          LOGGER.warn("xsk_technical_privileges.hdbrole assignment failed. No user provided.");
        } else {
          grantPrivileges(connection, container, user);
        }

    }
  }

  private void grantPrivileges(Connection connection, String container, String user) throws SQLException {
    executeUpdate(connection, "CREATE LOCAL TEMPORARY COLUMN TABLE #ROLES LIKE _SYS_DI.TT_SCHEMA_ROLES;");

    executeUpdate(connection, String.format("INSERT INTO #ROLES ( ROLE_NAME, PRINCIPAL_SCHEMA_NAME, PRINCIPAL_NAME ) VALUES ( 'xsk_technical_privileges', '', \'%s\' );", user));

    executeQuery(connection, String.format("CALL %s#DI.GRANT_CONTAINER_SCHEMA_ROLES(#ROLES, _SYS_DI.T_NO_PARAMETERS, ?, ?, ?);", container));
    executeUpdate(connection, "DROP TABLE #ROLES;");

  }

}
