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

import com.sap.xsk.hdb.ds.model.hdbsynonym.XSKDataStructureHDBSynonymModel;
import com.sap.xsk.hdb.ds.model.hdbsynonym.XSKHDBSYNONYMDefinitionModel;
import org.apache.commons.io.FilenameUtils;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map.Entry;


public class XSKGrantPrivilegesExternalArtifactsSchemaProcessor extends XSKHDIAbstractProcessor {

  public final void execute(Connection connection, String container, String[] deploys,
      XSKDataStructureHDBSynonymModel synonymModel) throws SQLException {
    String containerOwner = container + "#OO";
    for (String deploy : deploys) {
      if (FilenameUtils.getExtension(deploy).equalsIgnoreCase("hdbsynonym")) {
        for (Entry<String, XSKHDBSYNONYMDefinitionModel> synonymDefinition : synonymModel.getSynonymDefinitions().entrySet()) {
          String externalArtifactSchema = synonymDefinition.getValue().getTarget().getSchema();
          executeUpdate(connection,
              "GRANT SELECT ON SCHEMA \"" + externalArtifactSchema + "\" TO \"" + containerOwner + "\" WITH GRANT OPTION;");
          executeUpdate(connection,
              "GRANT EXECUTE ON SCHEMA \"" + externalArtifactSchema + "\" TO \"" + containerOwner + "\" WITH GRANT OPTION;");
        }
      }
    }
  }
}