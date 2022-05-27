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
import com.sap.xsk.synchronizer.XSJSLibSynchronizerPathTypeResolver.ResolvedPathType;
import org.eclipse.dirigible.commons.config.StaticObjects;
import org.eclipse.dirigible.repository.api.IRepository;
import org.eclipse.dirigible.repository.api.IResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class XSJSLibSynchronizerArtefactsCleaner {
  private static final Logger logger = LoggerFactory.getLogger(XSJSLibSynchronizerArtefactsCleaner.class);

  private static final IRepository repository = (IRepository) StaticObjects.get(StaticObjects.REPOSITORY);

  private static final DataSource dataSource = (DataSource) StaticObjects.get(StaticObjects.SYSTEM_DATASOURCE);

  private final XSJSLibSynchronizerPathTypeResolver resolver = new XSJSLibSynchronizerPathTypeResolver();

  public void cleanup(String registryPath) {
    ResolvedPathType type = resolver.resolveWithCollectionFirst(registryPath);
    switch(type) {
      case EXISTENT_XSJSLIB_FILE: cleanupResourcePath(registryPath); break;
      case EXISTENT_FOLDER: cleanupCollectionPath(registryPath); break;
      default: logger.info("XSJSLibSynchronizer: Nothing to cleanup."); break;
    }
  }

  private void cleanupResourcePath(String resourcePath) {
      cleanDBEntry(resourcePath);
      cleanExportsFile(resourcePath);
  }

  private void cleanupCollectionPath(String collectionPath) {
    cleanDBEntry(collectionPath);
  }

  private void cleanDBEntry(String registryPath) {
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

  private void cleanExportsFile(String registryPath) {
    IResource resource = repository.getResource(registryPath + ".generated_exports");
    if(resource.exists()) {
      resource.delete();
    }
  }
}
