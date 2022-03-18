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

import org.eclipse.dirigible.core.scheduler.api.AbstractSynchronizer;
import org.eclipse.dirigible.core.scheduler.api.IOrderedSynchronizerContribution;
import org.eclipse.dirigible.core.scheduler.api.SynchronizationException;
import org.eclipse.dirigible.repository.api.IResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XSJSLibSynchronizer extends AbstractSynchronizer implements IOrderedSynchronizerContribution {

  private static final Logger logger = LoggerFactory.getLogger(XSJSLibSynchronizer.class);

  public XSJSLibSynchronizer() {
    logger.debug("INITIALIZING SYNC");
  }

  /**
   * Force synchronization.
   */
  public static void forceSynchronization() {
    XSJSLibSynchronizer synchronizer = new XSJSLibSynchronizer();
    synchronizer.setForcedSynchronization(true);
    try {
      synchronizer.synchronize();
    } finally {
      synchronizer.setForcedSynchronization(false);
    }
  }

  @Override
  protected void synchronizeResource(IResource iResource) throws SynchronizationException {
    logger.trace("Synchronizing XSJSLib Resource...");
  }

  @Override
  public int getPriority() {
    return 666;
  }

  @Override
  public void synchronize() {
    synchronized (XSJSLibSynchronizer.class) {
      if(beforeSynchronizing()) {
        logger.trace("Synchronizing XSJSLibs...");

        logger.trace("Done synchronizing XSJSLibs.");
        afterSynchronizing();
      }
    }
  }
}
