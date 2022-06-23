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

import org.eclipse.dirigible.repository.api.ICollection;
import org.eclipse.dirigible.repository.api.IRepository;
import org.eclipse.dirigible.repository.api.IResource;
import org.eclipse.dirigible.repository.local.LocalCollection;
import org.eclipse.dirigible.repository.local.LocalRepository;
import org.eclipse.dirigible.repository.local.LocalResource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class XSJSLibSynchronizerRegistryEntityTest {

  @Test
  public void ResolveExistentResourceWithResourceFirstTest() {
    String testResourcePath = "test/resource.xsjslib";

    IResource mockResource = mock(LocalResource.class);
    when(mockResource.exists()).thenReturn(true);
    IRepository mockRepository = mock(LocalRepository.class);
    when(mockRepository.getResource(testResourcePath)).thenReturn(mockResource);

    XSJSLibSynchronizerRegistryEntity entity = new XSJSLibSynchronizerRegistryEntity(testResourcePath, mockRepository, false);
    assertTrue(entity.isSynchronizable());
    assertFalse(entity.isCollection());
    assertEquals(entity.getEntity(), mockResource);
  }

  @Test
  public void ResolveExistentCollectionWithResourceFirstTest() {
    String testResourcePath = "test/resource";

    IResource mockResource = mock(LocalResource.class);
    when(mockResource.exists()).thenReturn(false);

    ICollection mockCollection = mock(LocalCollection.class);
    when(mockCollection.exists()).thenReturn(true);

    IRepository mockRepository = mock(LocalRepository.class);
    when(mockRepository.getResource(testResourcePath)).thenReturn(mockResource);
    when(mockRepository.getCollection(testResourcePath)).thenReturn(mockCollection);

    XSJSLibSynchronizerRegistryEntity entity = new XSJSLibSynchronizerRegistryEntity(testResourcePath, mockRepository, false);
    assertTrue(entity.isSynchronizable());
    assertTrue(entity.isCollection());
    assertEquals(entity.getEntity(), mockCollection);
  }

  @Test
  public void ResolveNonExistentResourceWithResourceFirstTest() {
    String testResourcePath = "non/existent/resource/";

    IResource mockResource = mock(LocalResource.class);
    when(mockResource.exists()).thenReturn(false);

    ICollection mockCollection = mock(LocalCollection.class);
    when(mockCollection.exists()).thenReturn(false);

    IRepository mockRepository = mock(LocalRepository.class);
    when(mockRepository.getResource(testResourcePath)).thenReturn(mockResource);
    when(mockRepository.getCollection(testResourcePath)).thenReturn(mockCollection);

    XSJSLibSynchronizerRegistryEntity entity = new XSJSLibSynchronizerRegistryEntity(testResourcePath, mockRepository, false);
    assertFalse(entity.isSynchronizable());
    assertFalse(entity.isCollection());
    assertNull(entity.getEntity());
  }

  @Test
  public void ResolveExistentResourceWithCollectionFirstTest() {
    String testResourcePath = "test/resource.xsjslib";

    ICollection mockCollection = mock(LocalCollection.class);
    when(mockCollection.exists()).thenReturn(false);

    IResource mockResource = mock(LocalResource.class);
    when(mockResource.exists()).thenReturn(true);

    IRepository mockRepository = mock(LocalRepository.class);
    when(mockRepository.getCollection(testResourcePath)).thenReturn(mockCollection);
    when(mockRepository.getResource(testResourcePath)).thenReturn(mockResource);

    XSJSLibSynchronizerRegistryEntity entity = new XSJSLibSynchronizerRegistryEntity(testResourcePath, mockRepository, true);
    assertTrue(entity.isSynchronizable());
    assertFalse(entity.isCollection());
    assertEquals(entity.getEntity(), mockResource);
  }

  @Test
  public void ResolveExistentCollectionWithCollectionFirstTest() {
    String testResourcePath = "test/resource.xsjslib";

    ICollection mockCollection = mock(LocalCollection.class);
    when(mockCollection.exists()).thenReturn(true);

    IRepository mockRepository = mock(LocalRepository.class);
    when(mockRepository.getCollection(testResourcePath)).thenReturn(mockCollection);

    XSJSLibSynchronizerRegistryEntity entity = new XSJSLibSynchronizerRegistryEntity(testResourcePath, mockRepository, true);
    assertTrue(entity.isSynchronizable());
    assertTrue(entity.isCollection());
    assertEquals(entity.getEntity(), mockCollection);
  }

  @Test
  public void ResolveNonExistentResourceWithCollectionFirstTest() {
    String testResourcePath = "non/existent/resource.xsjslib";

    ICollection mockCollection = mock(LocalCollection.class);
    when(mockCollection.exists()).thenReturn(false);

    IResource mockResource = mock(LocalResource.class);
    when(mockResource.exists()).thenReturn(false);

    IRepository mockRepository = mock(LocalRepository.class);
    when(mockRepository.getCollection(testResourcePath)).thenReturn(mockCollection);
    when(mockRepository.getResource(testResourcePath)).thenReturn(mockResource);

    XSJSLibSynchronizerRegistryEntity entity = new XSJSLibSynchronizerRegistryEntity(testResourcePath, mockRepository, true);
    assertFalse(entity.isSynchronizable());
    assertFalse(entity.isCollection());
    assertNull(entity.getEntity());
  }
}
