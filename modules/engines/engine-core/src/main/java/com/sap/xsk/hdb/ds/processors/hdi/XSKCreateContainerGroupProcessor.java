/*
 * Copyright (c) 2019-2020 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2019-2020 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.hdb.ds.processors.hdi;

import java.sql.Connection;
import java.sql.SQLException;

public class XSKCreateContainerGroupProcessor extends XSKHDIAbstractProcessor {
	
	public final void execute(Connection connection, String group) throws SQLException {
		executeQuery(connection, "CALL _SYS_DI.CREATE_CONTAINER_GROUP('" + group + "', _SYS_DI.T_NO_PARAMETERS, ?, ?, ?);");
	}
}
