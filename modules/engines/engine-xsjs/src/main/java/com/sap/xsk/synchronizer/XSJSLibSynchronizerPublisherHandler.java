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

import org.eclipse.dirigible.core.publisher.api.handlers.MetadataPublisherHandler;

public class XSJSLibSynchronizerPublisherHandler extends MetadataPublisherHandler {

  @Override
  public void afterPublish(String workspaceLocation, String registryLocation) {
    XSJSLibSynchronizer.forceSynchronization(registryLocation);
  }

  @Override
  public void afterUnpublish(String location) {
    XSJSLibSynchronizerArtefactsCleaner cleaner = new XSJSLibSynchronizerArtefactsCleaner();
    cleaner.cleanup(location);
  }
}
