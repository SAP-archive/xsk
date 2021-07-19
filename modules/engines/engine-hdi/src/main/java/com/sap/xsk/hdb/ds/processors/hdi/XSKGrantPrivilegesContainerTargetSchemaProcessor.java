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
public class XSKGrantPrivilegesContainerTargetSchemaProcessor extends XSKHDIAbstractProcessor {

  public final void execute(Connection connection, String container, String[] users) throws SQLException {
    for (String user : users) {
      executeUpdate(connection, "GRANT SELECT ON SCHEMA " + user + "  TO " + container + "#OO WITH GRANT OPTION;");
      executeUpdate(connection, "GRANT EXECUTE ON SCHEMA " + user + "  TO " + container + "#OO WITH GRANT OPTION;");
    }
  }

}
