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
package com.sap.xsk.hdbti.synchronizer;

import com.sap.xsk.hdbti.model.XSKImportedCSVRecordModel;
import com.sap.xsk.hdbti.model.XSKTableImportToCsvRelation;
import org.eclipse.dirigible.core.publisher.api.PublisherHandler;
import org.eclipse.dirigible.core.scheduler.api.SchedulerException;
import org.eclipse.dirigible.database.persistence.PersistenceManager;

public class XSKTableImportSynchronizerPublisherHandler extends PublisherHandler {

  @Override
  public void beforePublish(String location) {

  }

  @Override
  public void afterPublish(String location) {

  }

  @Override
  public void beforeUnpublish(String location) {

  }

  @Override
  public void afterUnpublish(String location) throws SchedulerException {
    String locationQueryParam = getLocationQueryParam(location, true);

    removeMetadata(new PersistenceManager<XSKImportedCSVRecordModel>(), "XSK_IMPORTED_CSV_RECORDS", "HDBTI_LOCATION", locationQueryParam);
    removeMetadata(new PersistenceManager<XSKTableImportToCsvRelation>(), "XSK_TABLE_IMPORT_TO_CSV", "HDBTI_LOCATION", locationQueryParam);
  }
}
