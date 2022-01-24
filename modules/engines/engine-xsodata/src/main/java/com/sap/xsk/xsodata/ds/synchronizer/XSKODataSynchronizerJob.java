/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2022 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.xsodata.ds.synchronizer;

import org.eclipse.dirigible.core.scheduler.api.AbstractSynchronizerJob;
import org.eclipse.dirigible.core.scheduler.api.ISynchronizer;

/**
 * The XSK Data Structures Synchronizer Job.
 */
public class XSKODataSynchronizerJob extends AbstractSynchronizerJob {

  private final XSKODataSynchronizer odataSynchronizer = new XSKODataSynchronizer();

  /*
   * (non-Javadoc)
   * @see org.eclipse.dirigible.core.scheduler.api.AbstractSynchronizerJob#getSynchronizer()
   */
  @Override
  public ISynchronizer getSynchronizer() {
    return odataSynchronizer;
  }

  /*
   * (non-Javadoc)
   * @see org.eclipse.dirigible.core.scheduler.api.AbstractSynchronizerJob#getName()
   */
  @Override
  public String getName() {
    return XSKODataSynchronizerJobDefinitionProvider.XSK_ODATA_SYNCHRONIZER_JOB;
  }

}
