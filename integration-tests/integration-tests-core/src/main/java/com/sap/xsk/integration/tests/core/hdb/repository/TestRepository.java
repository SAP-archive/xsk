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
package com.sap.xsk.integration.tests.core.hdb.repository;

import java.io.IOException;
import org.eclipse.dirigible.repository.api.IResource;
import org.eclipse.dirigible.repository.api.RepositoryReadException;

public class TestRepository extends AbstractTestRepository {
    @Override
    public IResource getResource(String s) {
        try {
            byte[] content = TestRepository.class.getResourceAsStream(s).readAllBytes();
            TestResource resource = new TestResource();
            resource.setContent(content);
            return resource;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

  @Override
  public boolean hasResource(String s) throws RepositoryReadException {
    return false;
  }
}
