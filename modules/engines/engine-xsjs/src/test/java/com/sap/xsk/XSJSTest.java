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
package com.sap.xsk;

import org.eclipse.dirigible.commons.config.Configuration;
import org.eclipse.dirigible.commons.config.StaticObjects;
import org.eclipse.dirigible.core.test.AbstractDirigibleTest;
import org.eclipse.dirigible.repository.api.IEntity;
import org.eclipse.dirigible.repository.api.IRepository;
import org.eclipse.dirigible.repository.local.LocalRepository;
import org.junit.After;
import org.junit.Before;

public abstract class XSJSTest extends AbstractDirigibleTest {

  public XSJSTest() {
    // should be executed before parent @Before method as parent would otherwise initialize the DB in a persistent way
    Configuration.set("DIRIGIBLE_DATABASE_H2_URL", "jdbc:h2:mem:xsk-tests");
  }

  @Before
  public void setUpRepository() {
    String rootFolder = "target/test-classes/META-INF/";
    IRepository repository = new LocalRepository(rootFolder, false);
    StaticObjects.set(StaticObjects.REPOSITORY, repository);
  }

  @After
  public void cleanupRepository() {
    IRepository repository = (IRepository) StaticObjects.get(StaticObjects.REPOSITORY);
    repository.getRoot().getResources().forEach(IEntity::delete);
    repository.getRoot().getCollections().forEach(IEntity::delete);
  }

}
