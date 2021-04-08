/*
 * Copyright (c) 2019-2021 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2019-2021 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.xssecurestore.ds.synchronizer;

import com.sap.xsk.xssecurestore.ds.api.IXSKSecureStoreModel;
import org.eclipse.dirigible.commons.api.content.AbstractClasspathContentHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XSKSecureStoreClasspathContentHandler extends AbstractClasspathContentHandler {

  private static final Logger logger = LoggerFactory.getLogger(XSKSecureStoreClasspathContentHandler.class);

  @Override
  protected boolean isValid(String path) {

    if (path.endsWith(IXSKSecureStoreModel.FILE_EXTENSION_XSSECURESTORE)) {
      return true;
    }
    return false;
  }

  @Override
  protected Logger getLogger() {
    return logger;
  }
}
