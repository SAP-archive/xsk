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

import com.sap.xsk.XSJSTest;
import com.sap.xsk.synchronizer.XSJSLibSynchronizerPathTypeResolver.ResolvedPathType;
import junitparams.JUnitParamsRunner;
import org.eclipse.dirigible.commons.config.StaticObjects;
import org.eclipse.dirigible.repository.api.IRepository;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.TestCase.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class XSJSLibSynchronizerPathTypeResolverTest extends XSJSTest {

  @Test
  public void resolveWithResourceFirstTest() {
    XSJSLibSynchronizerPathTypeResolver resolver = new XSJSLibSynchronizerPathTypeResolver();
    IRepository repository = (IRepository) StaticObjects.get(StaticObjects.REPOSITORY);
    repository.createCollection("/resolveWithResourceFirstTest");
    repository.createResource("/resolveWithResourceFirstTest/test.xsjslib");
    repository.createResource("/resolveWithResourceFirstTest/test.html");

    // Existent files/folders
    ResolvedPathType type1 = resolver.resolveWithResourceFirst("/resolveWithResourceFirstTest/test.xsjslib");
    ResolvedPathType type2 = resolver.resolveWithResourceFirst("/resolveWithResourceFirstTest/test.html");
    ResolvedPathType type3 = resolver.resolveWithResourceFirst("/resolveWithResourceFirstTest/");
    ResolvedPathType type4 = resolver.resolveWithResourceFirst("/resolveWithResourceFirstTest");

    // Non-Existent File/folder
    ResolvedPathType type5 = resolver.resolveWithResourceFirst("/resolveWithResourceFirstTestNonExistent/");
    ResolvedPathType type6 = resolver.resolveWithResourceFirst("/resolveWithResourceFirstTestNonExistent");
    ResolvedPathType type7 = resolver.resolveWithResourceFirst("/resolveWithResourceFirstTest/test2.html");

    assertEquals("Unexpected ResolvedPathType.", ResolvedPathType.EXISTENT_XSJSLIB_FILE, type1);
    assertEquals("Unexpected ResolvedPathType.", ResolvedPathType.EXISTENT_OTHER_FILE, type2);
    assertEquals("Unexpected ResolvedPathType.", ResolvedPathType.EXISTENT_FOLDER, type3);
    assertEquals("Unexpected ResolvedPathType.", ResolvedPathType.EXISTENT_FOLDER, type4);

    assertEquals("Unexpected ResolvedPathType.", ResolvedPathType.NON_EXISTENT_FILE_OR_FOLDER, type5);
    assertEquals("Unexpected ResolvedPathType.", ResolvedPathType.NON_EXISTENT_FILE_OR_FOLDER, type6);
    assertEquals("Unexpected ResolvedPathType.", ResolvedPathType.NON_EXISTENT_FILE_OR_FOLDER, type7);
  }

  @Test
  public void resolveWithCollectionFirstTest() {
    XSJSLibSynchronizerPathTypeResolver resolver = new XSJSLibSynchronizerPathTypeResolver();
    IRepository repository = (IRepository) StaticObjects.get(StaticObjects.REPOSITORY);
    repository.createCollection("/resolveWithCollectionFirstTest");
    repository.createResource("/resolveWithCollectionFirstTest/test.xsjslib");
    repository.createResource("/resolveWithCollectionFirstTest/test.html");

    // Existent files/folders
    ResolvedPathType type1 = resolver.resolveWithCollectionFirst("/resolveWithCollectionFirstTest/test.xsjslib");
    ResolvedPathType type2 = resolver.resolveWithCollectionFirst("/resolveWithCollectionFirstTest/test.html");
    ResolvedPathType type3 = resolver.resolveWithCollectionFirst("/resolveWithCollectionFirstTest/");
    ResolvedPathType type4 = resolver.resolveWithCollectionFirst("/resolveWithCollectionFirstTest");

    // Non-Existent File/folder
    ResolvedPathType type5 = resolver.resolveWithCollectionFirst("/resolveWithCollectionFirstTestNonExistent/");
    ResolvedPathType type6 = resolver.resolveWithCollectionFirst("/resolveWithCollectionFirstTestNonExistent");
    ResolvedPathType type7 = resolver.resolveWithCollectionFirst("/resolveWithCollectionFirstTest/test2.html");

    assertEquals("Unexpected ResolvedPathType.", ResolvedPathType.EXISTENT_XSJSLIB_FILE, type1);
    assertEquals("Unexpected ResolvedPathType.", ResolvedPathType.EXISTENT_OTHER_FILE, type2);
    assertEquals("Unexpected ResolvedPathType.", ResolvedPathType.EXISTENT_FOLDER, type3);
    assertEquals("Unexpected ResolvedPathType.", ResolvedPathType.EXISTENT_FOLDER, type4);

    assertEquals("Unexpected ResolvedPathType.", ResolvedPathType.NON_EXISTENT_FILE_OR_FOLDER, type5);
    assertEquals("Unexpected ResolvedPathType.", ResolvedPathType.NON_EXISTENT_FILE_OR_FOLDER, type6);
    assertEquals("Unexpected ResolvedPathType.", ResolvedPathType.NON_EXISTENT_FILE_OR_FOLDER, type7);
  }
}
