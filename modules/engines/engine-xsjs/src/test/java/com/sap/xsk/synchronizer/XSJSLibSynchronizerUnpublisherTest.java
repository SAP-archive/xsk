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

import com.sap.xsk.synchronizer.XSJSLibSynchronizerPathTypeResolver.ResolvedPathType;
import com.sap.xsk.synchronizer.cleaners.XSJSLibSynchronizerCleaner;
import com.sap.xsk.synchronizer.cleaners.XSJSLibSynchronizerDBCleaner;
import com.sap.xsk.synchronizer.cleaners.XSJSLibSynchronizerFileCleaner;
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

    XSJSLibSynchronizerPathTypeResolver resolverMock = mock(XSJSLibSynchronizerPathTypeResolver.class);
    when(resolverMock.resolveWithCollectionFirst(testPath)).thenReturn(ResolvedPathType.EXISTENT_XSJSLIB_FILE);

    XSJSLibSynchronizerCleaner fileCleanerMock = mock(XSJSLibSynchronizerFileCleaner.class);
    XSJSLibSynchronizerCleaner dbCleanerMock = mock(XSJSLibSynchronizerDBCleaner.class);

    XSJSLibSynchronizerUnpublisher unpublisher = new XSJSLibSynchronizerUnpublisher(resolverMock, fileCleanerMock, dbCleanerMock);
    unpublisher.unpublish(testPath);

    verify(fileCleanerMock, times(1)).cleanup(testPath);
    verify(dbCleanerMock, times(1)).cleanup(testPath);
  }

  @Test
  public void unpublishCollectionTest() {
    String testPath = "test/";

    XSJSLibSynchronizerPathTypeResolver resolverMock = mock(XSJSLibSynchronizerPathTypeResolver.class);
    when(resolverMock.resolveWithCollectionFirst(testPath)).thenReturn(ResolvedPathType.EXISTENT_FOLDER);

    XSJSLibSynchronizerCleaner fileCleanerMock = mock(XSJSLibSynchronizerFileCleaner.class);
    XSJSLibSynchronizerCleaner dbCleanerMock = mock(XSJSLibSynchronizerDBCleaner.class);

    XSJSLibSynchronizerUnpublisher unpublisher = new XSJSLibSynchronizerUnpublisher(resolverMock, fileCleanerMock, dbCleanerMock);
    unpublisher.unpublish(testPath);

    verify(fileCleanerMock, times(0)).cleanup(testPath);
    verify(dbCleanerMock, times(1)).cleanup(testPath);
  }

  @Test
  public void unpublishUnexistentCollectionTest() {
    String testPath = "non/existent/collection";

    XSJSLibSynchronizerPathTypeResolver resolverMock = mock(XSJSLibSynchronizerPathTypeResolver.class);
    when(resolverMock.resolveWithCollectionFirst(testPath)).thenReturn(ResolvedPathType.NON_EXISTENT_FILE_OR_FOLDER);

    XSJSLibSynchronizerCleaner fileCleanerMock = mock(XSJSLibSynchronizerFileCleaner.class);
    XSJSLibSynchronizerCleaner dbCleanerMock = mock(XSJSLibSynchronizerDBCleaner.class);

    XSJSLibSynchronizerUnpublisher unpublisher = new XSJSLibSynchronizerUnpublisher(resolverMock, fileCleanerMock, dbCleanerMock);
    unpublisher.unpublish(testPath);

    verify(fileCleanerMock, times(0)).cleanup(testPath);
    verify(dbCleanerMock, times(0)).cleanup(testPath);
  }

  @Test
  public void unpublishUnexistentResourceTest() {
    String testPath = "non/existent/resource.xsjslib";

    XSJSLibSynchronizerPathTypeResolver resolverMock = mock(XSJSLibSynchronizerPathTypeResolver.class);
    when(resolverMock.resolveWithCollectionFirst(testPath)).thenReturn(ResolvedPathType.NON_EXISTENT_FILE_OR_FOLDER);

    XSJSLibSynchronizerCleaner fileCleanerMock = mock(XSJSLibSynchronizerFileCleaner.class);
    XSJSLibSynchronizerCleaner dbCleanerMock = mock(XSJSLibSynchronizerDBCleaner.class);

    XSJSLibSynchronizerUnpublisher unpublisher = new XSJSLibSynchronizerUnpublisher(resolverMock, fileCleanerMock, dbCleanerMock);
    unpublisher.unpublish(testPath);

    verify(fileCleanerMock, times(0)).cleanup(testPath);
    verify(dbCleanerMock, times(0)).cleanup(testPath);
  }
}
