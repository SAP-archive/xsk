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
package com.sap.xsk.hdbti.model;

import com.sap.xsk.hdb.ds.model.XSKDataStructureModel;
import java.util.List;

public class XSKTableImportArtifact extends XSKDataStructureModel {

  private List<XSKTableImportConfigurationDefinition> importConfigurationDefinition;

  private List<XSKTableImportToCsvRelation> tableImportToCsvRelations;

  public List<XSKTableImportToCsvRelation> getTableImportToCsvRelations() {
    return tableImportToCsvRelations;
  }

  public void setTableImportToCsvRelations(List<XSKTableImportToCsvRelation> tableImportToCsvRelations) {
    this.tableImportToCsvRelations = tableImportToCsvRelations;
  }

  public List<XSKTableImportConfigurationDefinition> getImportConfigurationDefinition() {
    return importConfigurationDefinition;
  }

  public void setImportConfigurationDefinition(List<XSKTableImportConfigurationDefinition> importConfigurationDefinition) {
    this.importConfigurationDefinition = importConfigurationDefinition;
  }
}
