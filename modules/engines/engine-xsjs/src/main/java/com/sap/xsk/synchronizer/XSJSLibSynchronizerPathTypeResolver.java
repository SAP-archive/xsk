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

import org.eclipse.dirigible.commons.config.StaticObjects;
import org.eclipse.dirigible.repository.api.ICollection;
import org.eclipse.dirigible.repository.api.IRepository;
import org.eclipse.dirigible.repository.api.IResource;

public class XSJSLibSynchronizerPathTypeResolver {
  private final IRepository repository;

  private static final String XSJSLIB_FILE_EXTENSION = ".xsjslib";

  public enum ResolvedPathType {
    EXISTENT_XSJSLIB_FILE,
    EXISTENT_FOLDER,
    EXISTENT_OTHER_FILE,
    NON_EXISTENT_FILE_OR_FOLDER
  }

  XSJSLibSynchronizerPathTypeResolver() {
    this.repository = (IRepository) StaticObjects.get(StaticObjects.REPOSITORY);
  }

  public ResolvedPathType resolveWithResourceFirst(String registryPath) {
    IResource resource = repository.getResource(registryPath);

    if(resource.exists()) {
      return registryPath.endsWith(XSJSLIB_FILE_EXTENSION) ?
          ResolvedPathType.EXISTENT_XSJSLIB_FILE : ResolvedPathType.EXISTENT_OTHER_FILE;
    }
    else {
      ICollection collection = repository.getCollection(registryPath);

      return collection.exists() ?
          ResolvedPathType.EXISTENT_FOLDER : ResolvedPathType.NON_EXISTENT_FILE_OR_FOLDER;
    }
  }

  public ResolvedPathType resolveWithCollectionFirst(String registryPath) {
    ICollection collection = repository.getCollection(registryPath);

    if(collection.exists()) {
      return ResolvedPathType.EXISTENT_FOLDER;
    }
    else {
        IResource resource = repository.getResource(registryPath);

        if(resource.exists()) {
          return registryPath.endsWith(XSJSLIB_FILE_EXTENSION) ?
              ResolvedPathType.EXISTENT_XSJSLIB_FILE : ResolvedPathType.EXISTENT_OTHER_FILE;
        }
        else {
          return ResolvedPathType.NON_EXISTENT_FILE_OR_FOLDER;
        }
      }
    }

  public boolean isNonSynchronizableType(ResolvedPathType type) {
    return type == ResolvedPathType.EXISTENT_OTHER_FILE
        || type == ResolvedPathType.NON_EXISTENT_FILE_OR_FOLDER;
  }
}
