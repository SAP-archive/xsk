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

import com.sap.xsk.synchronizer.XSJSLibSynchronizerPathTypeResolver.ResolvedPathType;
import com.sap.xsk.synchronizer.cleaners.XSJSLibSynchronizerCleaner;
import com.sap.xsk.synchronizer.cleaners.XSJSLibSynchronizerDBCleaner;
import com.sap.xsk.synchronizer.cleaners.XSJSLibSynchronizerFileCleaner;
import org.eclipse.dirigible.commons.config.StaticObjects;
import org.eclipse.dirigible.core.publisher.api.handlers.MetadataPublisherHandler;
import org.eclipse.dirigible.repository.api.IRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.sql.DataSource;

public class XSJSLibSynchronizerPublisherHandler extends MetadataPublisherHandler {
  private static final DataSource dataSource = (DataSource) StaticObjects.get(StaticObjects.SYSTEM_DATASOURCE);

  private static final IRepository repository = (IRepository) StaticObjects.get(StaticObjects.REPOSITORY);

  private static final Logger logger = LoggerFactory.getLogger(XSJSLibSynchronizerPublisherHandler.class);

  @Override
  public void afterPublish(String workspaceLocation, String registryLocation) {
    XSJSLibSynchronizer.forceSynchronization(registryLocation);
  }

  @Override
  public void beforeUnpublish(String location) {
    cleanup(location);
  }

  private void cleanup(String registryPath) {
    XSJSLibSynchronizerCleaner dbCleaner = new XSJSLibSynchronizerDBCleaner(dataSource);
    XSJSLibSynchronizerCleaner fileCleaner = new XSJSLibSynchronizerFileCleaner(repository);
    XSJSLibSynchronizerPathTypeResolver resolver = new XSJSLibSynchronizerPathTypeResolver();
    ResolvedPathType type = resolver.resolveWithCollectionFirst(registryPath);

    switch(type) {
      case EXISTENT_XSJSLIB_FILE: {
        dbCleaner.cleanup(registryPath);
        fileCleaner.cleanup(registryPath);
      } break;

      case EXISTENT_FOLDER: dbCleaner.cleanup(registryPath); break;

      default: logger.info("XSJSLibSynchronizer: Nothing to cleanup."); break;
    }
  }
}
