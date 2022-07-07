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

import com.sap.xsk.synchronizer.cleaners.XSJSLibSynchronizerCleaner;
import com.sap.xsk.synchronizer.cleaners.XSJSLibSynchronizerDBCleaner;
import com.sap.xsk.synchronizer.cleaners.XSJSLibSynchronizerFileCleaner;
import org.eclipse.dirigible.commons.config.StaticObjects;
import org.eclipse.dirigible.core.publisher.api.handlers.MetadataPublisherHandler;
import org.eclipse.dirigible.repository.api.IRepository;
import javax.sql.DataSource;

public class XSJSLibSynchronizerPublisherHandler extends MetadataPublisherHandler {
  private static final DataSource dataSource = (DataSource) StaticObjects.get(StaticObjects.SYSTEM_DATASOURCE);
  private static final IRepository repository = (IRepository) StaticObjects.get(StaticObjects.REPOSITORY);

  private final XSJSLibSynchronizerCleaner dbCleaner = new XSJSLibSynchronizerDBCleaner(dataSource);
  private final XSJSLibSynchronizerCleaner fileCleaner = new XSJSLibSynchronizerFileCleaner(repository);
  private final XSJSLibSynchronizerUnpublisher unpublisher = new XSJSLibSynchronizerUnpublisher(fileCleaner, dbCleaner);

  @Override
  public void afterPublish(String workspaceLocation, String registryLocation) {
    XSJSLibSynchronizer.forceSynchronization(registryLocation);
  }

  @Override
  public void beforeUnpublish(String location) {
    XSJSLibSynchronizerRegistryEntity entity = new XSJSLibSynchronizerRegistryEntity(location, repository, true);
    unpublisher.unpublish(entity);
  }
}
