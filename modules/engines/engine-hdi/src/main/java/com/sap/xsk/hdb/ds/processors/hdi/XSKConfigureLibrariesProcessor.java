/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2021 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.hdb.ds.processors.hdi;

import java.sql.Connection;
import java.sql.SQLException;
import javax.inject.Singleton;

@Singleton
public class XSKConfigureLibrariesProcessor extends XSKHDIAbstractProcessor {

  public final void execute(Connection connection, String container) throws SQLException {
    executeQuery(connection,
        "CALL " + container + "#DI.CONFIGURE_LIBRARIES(_SYS_DI.T_DEFAULT_LIBRARIES, _SYS_DI.T_NO_PARAMETERS, ?, ?, ?);");
  }

}
