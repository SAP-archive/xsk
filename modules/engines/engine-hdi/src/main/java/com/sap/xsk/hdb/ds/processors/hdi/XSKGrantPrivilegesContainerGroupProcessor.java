/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company and XSK contributors
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

public class XSKGrantPrivilegesContainerGroupProcessor extends XSKHDIAbstractProcessor {

  public final void execute(Connection connection, String group, String[] users) throws SQLException {
    executeUpdate(connection, "CREATE LOCAL TEMPORARY COLUMN TABLE #PRIVILEGES LIKE _SYS_DI.TT_API_PRIVILEGES;");
    for (String user : users) {
      executeUpdate(connection, "INSERT INTO #PRIVILEGES (PRINCIPAL_NAME, PRIVILEGE_NAME, OBJECT_NAME) SELECT ?, PRIVILEGE_NAME, OBJECT_NAME FROM _SYS_DI.T_DEFAULT_CONTAINER_GROUP_ADMIN_PRIVILEGES;", user);
    }
    executeQuery(connection,
        "CALL _SYS_DI.GRANT_CONTAINER_GROUP_API_PRIVILEGES(?, #PRIVILEGES, _SYS_DI.T_NO_PARAMETERS, ?, ?, ?);", group);
    executeUpdate(connection, "DROP TABLE #PRIVILEGES;");
  }

}
