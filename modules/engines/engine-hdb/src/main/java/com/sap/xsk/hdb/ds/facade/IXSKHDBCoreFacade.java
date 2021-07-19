/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2021 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.hdb.ds.facade;

import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdb.ds.model.XSKDataStructureModel;
import org.eclipse.dirigible.core.scheduler.api.SynchronizationException;
import org.eclipse.dirigible.repository.api.IResource;

public interface IXSKHDBCoreFacade {

  void handleResourceSynchronization(IResource resource) throws SynchronizationException, XSKDataStructuresException;

  void handleResourceSynchronization(String fileExtension, XSKDataStructureModel dataStructureModel)
      throws SynchronizationException, XSKDataStructuresException;

  void updateEntities();

  void cleanup() throws XSKDataStructuresException;

  void clearCache();
}
