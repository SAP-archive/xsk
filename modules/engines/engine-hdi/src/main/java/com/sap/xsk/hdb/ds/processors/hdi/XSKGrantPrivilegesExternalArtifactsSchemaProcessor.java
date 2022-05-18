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

import static java.text.MessageFormat.format;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.io.FilenameUtils;
import org.eclipse.dirigible.api.v3.platform.RegistryFacade;
import org.jetbrains.annotations.NotNull;

import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdb.ds.model.XSKDataStructureModelFactory;
import com.sap.xsk.hdb.ds.model.hdbsynonym.XSKDataStructureHDBSynonymModel;
import com.sap.xsk.hdb.ds.model.hdbsynonym.XSKHDBSYNONYMDefinitionModel;


public class XSKGrantPrivilegesExternalArtifactsSchemaProcessor extends XSKHDIAbstractProcessor {

  private static final String HDBSYNONYM_FILE_EXTENSION = "hdbsynonym";

  public final void execute(Connection connection, String container, String[] deploys)
      throws SQLException, IOException, XSKDataStructuresException {
    Set<String> externalArtifactsSchemas = collectExternalArtifactsSchemas(deploys);

    grantPrivileges(connection, container, externalArtifactsSchemas);
  }

  @NotNull
  private Set<String> collectExternalArtifactsSchemas(String[] deploys) throws IOException, XSKDataStructuresException {
    Set<String> externalArtifactsSchemas = new HashSet<>();
    for (String deploy : deploys) {
      if (FilenameUtils.getExtension(deploy).equalsIgnoreCase(HDBSYNONYM_FILE_EXTENSION)) {
        String hdbSynonymContent = getSynonymContent(deploy);
        XSKDataStructureHDBSynonymModel synonymModel = getSynonymModel(deploy, hdbSynonymContent);

        for (Entry<String, XSKHDBSYNONYMDefinitionModel> synonymDefinition : synonymModel.getSynonymDefinitions().entrySet()) {
          String externalArtifactSchema = synonymDefinition.getValue().getTarget().getSchema();
          externalArtifactsSchemas.add(externalArtifactSchema);
        }
      }
    }
    return externalArtifactsSchemas;
  }

  protected String getSynonymContent(String deploy) throws IOException {
    return RegistryFacade.getText(deploy);
  }

  private void grantPrivileges(Connection connection, String container, Set<String> externalArtifactsSchemas) throws SQLException {
    String containerOwner = container + "#OO";
    for (String externalSchema: externalArtifactsSchemas) {
      executeUpdate(connection, format("GRANT SELECT ON SCHEMA \"{0}\" TO \"{1}\" WITH GRANT OPTION;", externalSchema, containerOwner));
      executeUpdate(connection, format("GRANT EXECUTE ON SCHEMA \"{0}\" TO \"{1}\" WITH GRANT OPTION;", externalSchema, containerOwner));
      executeUpdate(connection, format("GRANT INSERT ON SCHEMA \"{0}\" TO \"{1}\" WITH GRANT OPTION;", externalSchema, containerOwner));
      executeUpdate(connection, format("GRANT UPDATE ON SCHEMA \"{0}\" TO \"{1}\" WITH GRANT OPTION;", externalSchema, containerOwner));
      executeUpdate(connection, format("GRANT DELETE ON SCHEMA \"{0}\" TO \"{1}\" WITH GRANT OPTION;", externalSchema, containerOwner));
    }
  }

  protected XSKDataStructureHDBSynonymModel getSynonymModel(String location, String content) throws XSKDataStructuresException, IOException {
    return XSKDataStructureModelFactory.parseSynonym(location, content);
  }
}