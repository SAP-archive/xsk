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
package com.sap.xsk.xsaccess.ds.verifier;

import com.sap.xsk.xsaccess.ds.api.IXSKAccessCoreService;
import com.sap.xsk.xsaccess.ds.api.XSKAccessException;
import com.sap.xsk.xsaccess.ds.model.access.XSKAccessDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XSKAccessVerifier {

  private static final Logger logger = LoggerFactory.getLogger(XSKAccessVerifier.class);

  /**
   * Checks whether the URI is secured via the *.access file or not
   *
   * @param ixskAccessCoreService the security core service
   * @param path                  the path
   * @param method                the method
   * @return all the most specific AccessDefinition entry matching the URI if any
   * @throws XSKAccessException the access exception
   */
  public static XSKAccessDefinition getMatchingAccessDefinitions(IXSKAccessCoreService ixskAccessCoreService, String path, String method)
      throws XSKAccessException {
    XSKAccessDefinition current = null;
    for (XSKAccessDefinition xskAccessDefinition : ixskAccessCoreService.getAccessXSKDefinitions()) {
      if (path.startsWith(xskAccessDefinition.getPath())) {
        logger.debug(String.format("URI [%s] with HTTP method [%s] is secured because of definition: %s", path, method,
            xskAccessDefinition.getPath()));
        if ((current == null) || (xskAccessDefinition.getPath().length() > current.getPath().length())) {
          current = xskAccessDefinition;
        }
      }
    }
    if (current == null) {
      logger.trace(String.format("URI [%s] with HTTP method [%s] is NOT secured", path, method));
    }

    return current;
  }
}
