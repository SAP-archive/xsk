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
package com.sap.xsk.xssecurestore.ds.synchronizer;

import static java.text.MessageFormat.format;

import com.sap.xsk.xssecurestore.ds.api.IXSKSecureStoreModel;
import com.sap.xsk.xssecurestore.ds.api.XSKSecureStoreException;
import com.sap.xsk.xssecurestore.ds.model.XSKSecureStore;
import com.sap.xsk.xssecurestore.ds.service.XSKSecureStoreCoreService;
import java.util.List;
import org.eclipse.dirigible.core.scheduler.api.AbstractSynchronizer;
import org.eclipse.dirigible.core.scheduler.api.SchedulerException;
import org.eclipse.dirigible.core.scheduler.api.SynchronizationException;
import org.eclipse.dirigible.repository.api.IRepository;
import org.eclipse.dirigible.repository.api.IResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XSKSecureStoreSynchronizer extends AbstractSynchronizer {

  private static final Logger logger = LoggerFactory.getLogger(XSKSecureStoreSynchronizer.class);
  private final String SYNCHRONIZER_NAME = this.getClass().getCanonicalName();
  private XSKSecureStoreCoreService xskSecureStoreCoreService = new XSKSecureStoreCoreService();

  /**
   * Force synchronization.
   */
  public static final void forceSynchronization() {
    XSKSecureStoreSynchronizer synchronizer = new XSKSecureStoreSynchronizer();
    synchronizer.setForcedSynchronization(true);
    try {
      synchronizer.synchronize();
    } finally {
      synchronizer.setForcedSynchronization(false);
    }
  }

  @Override
  public void synchronize() {
    synchronized (XSKSecureStoreSynchronizer.class) {
      if (beforeSynchronizing()) {
        logger.trace("Synchronizing Secure Stores ...");
        try {
          startSynchronization(SYNCHRONIZER_NAME);
          synchronizeRegistry();
          cleanup();
          successfulSynchronization(SYNCHRONIZER_NAME, format("Passed successfully."));
        } catch (Exception e) {
          logger.error("Synchronizing process for Secure Stores failed.", e);
          try {
            failedSynchronization(SYNCHRONIZER_NAME, e.getMessage());
          } catch (SchedulerException e1) {
            logger.error("Synchronizing process for Secure Stores files failed in registering the state log.", e);
          }
        }
        logger.trace("Done synchronizing Secure Stores.");
        afterSynchronizing();
      }
    }
  }

  @Override
  protected void synchronizeResource(IResource resource) throws SynchronizationException {
    String resourceName = resource.getName();
    String location = resource.getPath();
    String content = new String(resource.getContent());

    try {
      if (resourceName.endsWith(IXSKSecureStoreModel.FILE_EXTENSION_XSSECURESTORE) &&
          !xskSecureStoreCoreService.existsSecureStore(location)) {
        xskSecureStoreCoreService.createSecureStore(location, content);
      }
    } catch (XSKSecureStoreException e) {
      throw new SynchronizationException(e);
    }
  }

  @Override
  protected void cleanup() throws SynchronizationException {
    logger.trace("Cleaning up Secure Stores");
    IRepository repository = getRepository();

    try {
      List<XSKSecureStore> xskSecureStores = xskSecureStoreCoreService.getXSKSecureStores();
      for (XSKSecureStore xskSecureStore : xskSecureStores) {
        String xskSecureStoreLocation = xskSecureStore.getLocation();
        if (!repository.hasResource(xskSecureStoreLocation)) {
          xskSecureStoreCoreService.removeXSKSecureStore(xskSecureStoreLocation);
          xskSecureStoreCoreService.deleteSecureStoreValuesByStoreId(xskSecureStoreLocation);
          logger.warn("Cleaned up Secure Stores from location: {}", xskSecureStoreLocation);
        }
      }

    } catch (XSKSecureStoreException e) {
      throw new SynchronizationException(e);
    }
  }

  @Override
  protected void synchronizeRegistry() throws SynchronizationException {
    logger.trace("Synchronizing Secure Store from Registry...");

    super.synchronizeRegistry();

    logger.trace("Done synchronizing Secure Store from Registry.");
  }
}
