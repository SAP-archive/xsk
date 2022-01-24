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
package com.sap.xsk.hdb.ds.artefacts;

import org.eclipse.dirigible.core.scheduler.api.AbstractSynchronizationArtefactType;

public class HDBProcedureSynchronizationArtefactType extends AbstractSynchronizationArtefactType {

  @Override
  protected String getArtefactStateMessage(ArtefactState state) {
    switch (state) {
      case FAILED_CREATE:
        return "Processing for create hdbprocedure has failed";
      case FAILED_CREATE_UPDATE:
        return "Processing for create or update hdbprocedure has failed";
      case FAILED_UPDATE:
        return "Processing for update hdbprocedure has failed";
      case SUCCESSFUL_CREATE:
        return "Processing for create hdbprocedure was successful";
      case SUCCESSFUL_CREATE_UPDATE:
        return "Processing Create or update hdbprocedure was successful";
      case SUCCESSFUL_UPDATE:
        return "Processing for update hdbprocedure was successful";
      default:
        return state.getValue();
    }
  }

  @Override
  public String getId() {
    return "HDBProcedure";
  }
}
