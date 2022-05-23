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
  private static final IRepository repository = (IRepository) StaticObjects.get(StaticObjects.REPOSITORY);

  public enum ResolvedPathType {
    ExistentXSJSLibFile,
    ExistentFolder,
    ExistentOtherFile,
    NonExistentFileOrFolder
  }

  public ResolvedPathType resolveWithResourceFirst(String registryPath) {
    IResource resource = repository.getResource(registryPath);

    if(resource.exists()) {
      return registryPath.endsWith(".xsjslib") ?
          ResolvedPathType.ExistentXSJSLibFile : ResolvedPathType.ExistentOtherFile;
    }
    else {
      ICollection collection = repository.getCollection(registryPath);

      return collection.exists() ?
          ResolvedPathType.ExistentFolder : ResolvedPathType.NonExistentFileOrFolder;
    }
  }

  public ResolvedPathType resolveWithCollectionFirst(String registryPath) {
    ICollection collection = repository.getCollection(registryPath);

    if(collection.exists()) {
      return ResolvedPathType.ExistentFolder;
    }
    else {
        IResource resource = repository.getResource(registryPath);

        if(resource.exists()) {
          return registryPath.endsWith(".xsjslib") ?
              ResolvedPathType.ExistentXSJSLibFile : ResolvedPathType.ExistentOtherFile;
        }
        else {
          return ResolvedPathType.NonExistentFileOrFolder;
        }
      }
    }

  public boolean isNonSynchronizableType(ResolvedPathType type) {
    return type == ResolvedPathType.ExistentOtherFile
        || type == ResolvedPathType.NonExistentFileOrFolder;
  }
}
