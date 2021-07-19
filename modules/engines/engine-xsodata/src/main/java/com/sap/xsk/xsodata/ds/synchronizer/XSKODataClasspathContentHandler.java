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
package com.sap.xsk.xsodata.ds.synchronizer;

import com.sap.xsk.xsodata.ds.api.IXSKODataModel;
import org.eclipse.dirigible.commons.api.content.AbstractClasspathContentHandler;
import org.eclipse.dirigible.commons.api.module.StaticInjector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The XSK OData Classpath Content Handler.
 */
public class XSKODataClasspathContentHandler extends AbstractClasspathContentHandler {

  private static final Logger logger = LoggerFactory.getLogger(XSKODataClasspathContentHandler.class);

  private final XSKODataSynchronizer odataSynchronizer = StaticInjector.getInjector().getInstance(XSKODataSynchronizer.class);

  /*
   * (non-Javadoc)
   * @see org.eclipse.dirigible.commons.api.content.AbstractClasspathContentHandler#isValid(java.lang.String)
   */
  @Override
  protected boolean isValid(String path) {

    try {
      if (path.endsWith(IXSKODataModel.FILE_EXTENSION_XSODATA)) {
        odataSynchronizer.registerPredeliveredOData(path);
        return true;
      }
    } catch (Exception e) {
      logger.error("Predelivered Table or View is not valid", e);
    }

    return false;
  }

  /*
   * (non-Javadoc)
   * @see org.eclipse.dirigible.commons.api.content.AbstractClasspathContentHandler#getLogger()
   */
  @Override
  protected Logger getLogger() {
    return logger;
  }

}
