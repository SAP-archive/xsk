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
package com.sap.xsk.hdb.ds.synchronizer;

import com.sap.xsk.hdb.ds.model.XSKDataStructureModel;
import org.eclipse.dirigible.core.publisher.api.handlers.MetadataPublisherHandler;
import org.eclipse.dirigible.core.scheduler.api.SchedulerException;
import org.eclipse.dirigible.database.persistence.PersistenceManager;

public class XSKDataStructuresSynchronizerPublisherHandler extends MetadataPublisherHandler {

  @Override
  public void beforePublish(String location) {

  }

  @Override
  public void afterPublish(String workspaceLocation, String registryLocation) {

  }

  @Override
  public void beforeUnpublish(String location) {

  }

  @Override
  public void afterUnpublish(String location) throws SchedulerException {
    removeMetadata(new PersistenceManager<XSKDataStructureModel>(), "XSK_DATA_STRUCTURES", "DS_LOCATION", location, true);
  }
}
