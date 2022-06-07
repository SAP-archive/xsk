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

import com.sap.xsk.synchronizer.XSJSLibSynchronizerRegistryEntity;
import com.sap.xsk.synchronizer.cleaners.XSJSLibSynchronizerCleaner;
import com.sap.xsk.synchronizer.cleaners.XSJSLibSynchronizerDBCleaner;
import com.sap.xsk.synchronizer.cleaners.XSJSLibSynchronizerFileCleaner;
import org.eclipse.dirigible.repository.api.IEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class XSJSLibSynchronizerUnpublisherTest {
  @Test
  public void unpublishResourceTest() {
    String testPath = "test/testResource.xsjslib";

    IEntity entityMock = mock(IEntity.class);
    when(entityMock.getPath()).thenReturn(testPath);
    XSJSLibSynchronizerRegistryEntity xsjsEntityMock = mock(XSJSLibSynchronizerRegistryEntity.class);
    when(xsjsEntityMock.getEntity()).thenReturn(entityMock);
    when(xsjsEntityMock.isSynchronizable()).thenReturn(true);
    when(xsjsEntityMock.isCollection()).thenReturn(false);

    XSJSLibSynchronizerCleaner fileCleanerMock = mock(XSJSLibSynchronizerFileCleaner.class);
    XSJSLibSynchronizerCleaner dbCleanerMock = mock(XSJSLibSynchronizerDBCleaner.class);

    XSJSLibSynchronizerUnpublisher unpublisher = new XSJSLibSynchronizerUnpublisher(fileCleanerMock, dbCleanerMock);
    unpublisher.unpublish(xsjsEntityMock);

    verify(fileCleanerMock, times(1)).cleanup(testPath);
    verify(dbCleanerMock, times(1)).cleanup(testPath);
  }

  @Test
  public void unpublishCollectionTest() {
    String testPath = "test/";

    IEntity entityMock = mock(IEntity.class);
    when(entityMock.getPath()).thenReturn(testPath);
    XSJSLibSynchronizerRegistryEntity xsjsEntityMock = mock(XSJSLibSynchronizerRegistryEntity.class);
    when(xsjsEntityMock.getEntity()).thenReturn(entityMock);
    when(xsjsEntityMock.isSynchronizable()).thenReturn(true);
    when(xsjsEntityMock.isCollection()).thenReturn(true);

    XSJSLibSynchronizerCleaner fileCleanerMock = mock(XSJSLibSynchronizerFileCleaner.class);
    XSJSLibSynchronizerCleaner dbCleanerMock = mock(XSJSLibSynchronizerDBCleaner.class);

    XSJSLibSynchronizerUnpublisher unpublisher = new XSJSLibSynchronizerUnpublisher(fileCleanerMock, dbCleanerMock);
    unpublisher.unpublish(xsjsEntityMock);

    verify(fileCleanerMock, times(0)).cleanup(testPath);
    verify(dbCleanerMock, times(1)).cleanup(testPath);
  }

  @Test
  public void unpublishNonExistentResourceTest() {
    String testPath = "non/existent/resource.xsjslib";

    XSJSLibSynchronizerRegistryEntity xsjsEntityMock = mock(XSJSLibSynchronizerRegistryEntity.class);
    when(xsjsEntityMock.isSynchronizable()).thenReturn(false);

    XSJSLibSynchronizerCleaner fileCleanerMock = mock(XSJSLibSynchronizerFileCleaner.class);
    XSJSLibSynchronizerCleaner dbCleanerMock = mock(XSJSLibSynchronizerDBCleaner.class);

    XSJSLibSynchronizerUnpublisher unpublisher = new XSJSLibSynchronizerUnpublisher(fileCleanerMock, dbCleanerMock);
    unpublisher.unpublish(xsjsEntityMock);

    verify(fileCleanerMock, times(0)).cleanup(testPath);
    verify(dbCleanerMock, times(0)).cleanup(testPath);
  }
}
