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

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Set;
import java.util.TreeSet;
import javax.inject.Singleton;
import org.eclipse.dirigible.api.v3.platform.RegistryFacade;
import org.eclipse.dirigible.commons.api.scripting.ScriptingException;
import org.eclipse.dirigible.repository.api.RepositoryPath;

@Singleton
public class XSKWriteContainerContentProcessor extends XSKHDIAbstractProcessor {

  protected static String[] enumerateFolders(String[] files) {
    Set<String> folders = new TreeSet<>();
    for (String file : files) {
      RepositoryPath path = new RepositoryPath(file);
      String current = "";
      String[] segments = path.getSegments();
      for (int i = 0; i < segments.length - 1; i++) {
        current += segments[i] + "/";
        if (!folders.contains(current)) {
          folders.add(current);
        }
      }
    }
    return folders.toArray(new String[]{});
  }

  public final void execute(Connection connection, String container, String[] files, String configuration)
      throws SQLException, IOException, ScriptingException {

    String[] folders = enumerateFolders(files);

    executeUpdate(connection, "CREATE LOCAL TEMPORARY COLUMN TABLE #PATHS LIKE _SYS_DI.TT_FILESFOLDERS_CONTENT;");

    String content = RegistryFacade.getText(configuration);

    executeUpdate(connection, "INSERT INTO #PATHS (PATH, CONTENT) VALUES ('.hdiconfig', '" + content + "')");
    for (String folder : folders) {
      executeUpdate(connection, "INSERT INTO #PATHS (PATH, CONTENT) VALUES ('" + folder + "', NULL);");
    }
    for (String file : files) {
      content = RegistryFacade.getText(file);
      executeUpdate(connection, "INSERT INTO #PATHS (PATH, CONTENT) VALUES ('" + file.substring(1) + "', '" + content + "');");
    }
    executeQuery(connection, "CALL " + container + "#DI.WRITE(#PATHS, _SYS_DI.T_NO_PARAMETERS, ?, ?, ?);");
    executeUpdate(connection, "DROP TABLE #PATHS;");
  }

}
