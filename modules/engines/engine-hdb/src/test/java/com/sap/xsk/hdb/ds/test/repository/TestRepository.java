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
package com.sap.xsk.hdb.ds.test.repository;

import java.io.IOException;
import org.eclipse.dirigible.repository.api.IResource;
import org.eclipse.dirigible.repository.api.RepositoryReadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestRepository extends AbstractTestRepository {

  private static final Logger logger = LoggerFactory.getLogger(TestRepository.class);

  @Override
    public IResource getResource(String s) {
        try {
            byte[] content = TestRepository.class.getResourceAsStream(s).readAllBytes();
            TestResource resource = new TestResource();
            resource.setContent(content);
            return resource;
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }

        return null;
    }

  @Override
  public boolean hasResource(String s) throws RepositoryReadException {
    return false;
  }
}
