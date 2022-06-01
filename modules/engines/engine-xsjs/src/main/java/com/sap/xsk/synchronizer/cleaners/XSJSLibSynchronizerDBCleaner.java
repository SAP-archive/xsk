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
package com.sap.xsk.synchronizer.cleaners;

import com.sap.xsk.exceptions.XSJSLibArtefactCleanerSQLException;
import com.sap.xsk.synchronizer.XSJSLibSynchronizer;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class XSJSLibSynchronizerDBCleaner implements XSJSLibSynchronizerCleaner {
  private final DataSource dataSource;

  public XSJSLibSynchronizerDBCleaner(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  public void cleanup(String registryPath) {
    try (PreparedStatement deleteStatement =
        dataSource.getConnection().prepareStatement(
            "DELETE FROM \""
                + XSJSLibSynchronizer.XSJSLIB_SYNCHRONIZER_STATE_TABLE_NAME
                + "\" WHERE \"LOCATION\" LIKE ?")
    ) {
      deleteStatement.setString(1, registryPath + "%");
      deleteStatement.executeUpdate();
    } catch (SQLException e) {
      throw new XSJSLibArtefactCleanerSQLException("Could not cleanup xsjslib synchronizer entries. ", e);
    }
  }
}
