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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XSJSLibSynchronizerUnpublisher {
  private static final Logger logger = LoggerFactory.getLogger(XSJSLibSynchronizerUnpublisher.class);

  private final XSJSLibSynchronizerCleaner fileCleaner;

  private final XSJSLibSynchronizerCleaner dbCleaner;

  XSJSLibSynchronizerUnpublisher(
      XSJSLibSynchronizerCleaner fileCleaner,
      XSJSLibSynchronizerCleaner dbCleaner
  ) {
    this.fileCleaner = fileCleaner;
    this.dbCleaner = dbCleaner;
  }

  public void unpublish(XSJSLibSynchronizerRegistryEntity entity) {
    if(entity.isSynchronizable()) {
      String registryPath = entity.getEntity().getPath();
      if(entity.isCollection()) {
        dbCleaner.cleanup(registryPath);
      }
      else {
        dbCleaner.cleanup(registryPath);
        fileCleaner.cleanup(registryPath);
      }
    }
    else {
      logger.info("XSJSLibSynchronizer: Nothing to cleanup.");
    }
  }
}
