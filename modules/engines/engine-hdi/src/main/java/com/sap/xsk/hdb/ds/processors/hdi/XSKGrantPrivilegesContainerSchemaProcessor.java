/*
 * Copyright (c) 2019-2021 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2019-2021 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.hdb.ds.processors.hdi;

import javax.inject.Singleton;
import java.sql.Connection;
import java.sql.SQLException;

@Singleton
public class XSKGrantPrivilegesContainerSchemaProcessor extends XSKHDIAbstractProcessor {
	
	public final void execute(Connection connection, String container, String[] users) throws SQLException {
    	executeUpdate(connection, "CREATE LOCAL TEMPORARY COLUMN TABLE #PRIVILEGES LIKE _SYS_DI.TT_SCHEMA_PRIVILEGES;");
    	for (String user : users) {
    		executeUpdate(connection, "INSERT INTO #PRIVILEGES(PRIVILEGE_NAME, PRINCIPAL_SCHEMA_NAME, PRINCIPAL_NAME) VALUES ('SELECT', '', '" + user + "');");
    	}
    	executeQuery(connection, "CALL " + container + "#DI.GRANT_CONTAINER_SCHEMA_PRIVILEGES(#PRIVILEGES, _SYS_DI.T_NO_PARAMETERS, ?, ?, ?);");
    	executeUpdate(connection, "DROP TABLE #PRIVILEGES;");
	}

}
