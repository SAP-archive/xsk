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
package com.sap.xsk.synchronizer.cleaners;

import org.eclipse.dirigible.repository.api.IRepository;
import org.eclipse.dirigible.repository.api.IResource;

public class XSJSLibSynchronizerFileCleaner implements XSJSLibSynchronizerCleaner {
  private final IRepository repository;

  public XSJSLibSynchronizerFileCleaner(IRepository repository) {
    this.repository = repository;
  }

  public void cleanup(String registryPath) {

    IResource resource = repository.getResource(registryPath + ".generated_exports");
    if(resource.exists()) {
      resource.delete();
    }
  }
}
