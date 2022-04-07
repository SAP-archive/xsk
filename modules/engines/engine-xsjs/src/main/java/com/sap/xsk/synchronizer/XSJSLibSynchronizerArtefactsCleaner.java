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
package com.sap.xsk.synchronizer;

import com.sap.xsk.exceptions.XSJSLibArtefactCleanerSQLException;
import org.eclipse.dirigible.commons.config.StaticObjects;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class XSJSLibSynchronizerArtefactsCleaner {
  private final DataSource dataSource = (DataSource) StaticObjects.get(StaticObjects.SYSTEM_DATASOURCE);

  public void cleanup(String targetLocation) {
    try {
      PreparedStatement pstmt = dataSource.getConnection()
          .prepareStatement("DELETE FROM \""
              + XSJSLibSynchronizer.XSJSLIB_SYNCHRONIZER_STATE_TABLE_NAME
              + "\" WHERE \"LOCATION\" LIKE ?");

      pstmt.setString(1, targetLocation + "%");
      pstmt.executeUpdate();
    } catch (SQLException e) {
      throw new XSJSLibArtefactCleanerSQLException("Could not cleanup xsjslib synchronizer entries. ", e);
    }
  }
}
