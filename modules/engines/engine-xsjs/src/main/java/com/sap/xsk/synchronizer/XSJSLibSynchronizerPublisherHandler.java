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

import com.sap.xsk.utils.XSKCommonsConstants;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.dirigible.core.publisher.api.handlers.MetadataPublisherHandler;

public class XSJSLibSynchronizerPublisherHandler extends MetadataPublisherHandler {
  XSJSLibSynchronizerArtefactsCleaner cleaner = new XSJSLibSynchronizerArtefactsCleaner();

  @Override
  public void afterPublish(String location) {
      String registryLocation = XSKCommonsConstants.XSK_REGISTRY_PUBLIC + location.substring(
          StringUtils.lastIndexOf(location, "workspace/") + 10);
      XSJSLibSynchronizer.forceSynchronization(registryLocation);
  }

  @Override
  public void afterUnpublish(String location) {
    cleaner.cleanup(location);
  }
}
