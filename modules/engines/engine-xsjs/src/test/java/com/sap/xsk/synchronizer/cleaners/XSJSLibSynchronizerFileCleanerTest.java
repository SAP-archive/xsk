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
import org.eclipse.dirigible.repository.local.LocalRepository;
import org.eclipse.dirigible.repository.local.LocalResource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class XSJSLibSynchronizerFileCleanerTest {
  @Test
  public void cleanupGeneratedExportsFileTest() {
    String testResourcePath = "/cleanupFileTest/test.xsjslib";
    String generatedExportsExtension = ".generated_exports";

    IResource resourceMock = mock(LocalResource.class);
    when(resourceMock.exists()).thenReturn(true);

    IRepository repositoryMock = mock(LocalRepository.class);
    when(repositoryMock.getResource(testResourcePath + generatedExportsExtension)).thenReturn(resourceMock);

    XSJSLibSynchronizerFileCleaner cleaner = new XSJSLibSynchronizerFileCleaner(repositoryMock);
    cleaner.cleanup(testResourcePath);

    Mockito.verify(repositoryMock, Mockito.times(1)).getResource(testResourcePath + generatedExportsExtension);
    Mockito.verify(resourceMock, Mockito.times(1)).exists();
    Mockito.verify(resourceMock, Mockito.times(1)).delete();
  }
}
