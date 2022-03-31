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

import com.sap.xsk.utils.XSKCommonsConstants;
import org.eclipse.dirigible.core.scheduler.api.AbstractSynchronizerJob;
import org.eclipse.dirigible.core.scheduler.api.ISynchronizer;
import java.io.IOException;

public class XSJSLibSynchronizerJob extends AbstractSynchronizerJob {

  private final XSJSLibSynchronizer xsjsLibSynchronizer = new XSJSLibSynchronizer(XSKCommonsConstants.XSK_REGISTRY_PUBLIC);

  public XSJSLibSynchronizerJob() throws IOException {
  }

  @Override
  public String getName() {
    return XSJSLibSynchronizerJobDefinitionProvider.XSK_XSJSLIB_SYNCHRONIZER_JOB;
  }

  @Override
  public ISynchronizer getSynchronizer() {
    return xsjsLibSynchronizer;
  }
}
