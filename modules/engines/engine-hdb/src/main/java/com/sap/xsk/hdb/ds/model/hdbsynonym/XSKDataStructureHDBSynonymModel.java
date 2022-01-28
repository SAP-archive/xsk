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
package com.sap.xsk.hdb.ds.model.hdbsynonym;

import com.sap.xsk.hdb.ds.model.XSKDataStructureModel;
import java.util.HashMap;
import java.util.Map;

/**
 * The synonym model representation.
 */
public class XSKDataStructureHDBSynonymModel extends XSKDataStructureModel {
  Map<String, XSKHDBSYNONYMDefinitionModel> synonymDefinitions = new HashMap<>();

  public Map<String, XSKHDBSYNONYMDefinitionModel> getSynonymDefinitions() {
    return synonymDefinitions;
  }

  public void setSynonymDefinitions(Map<String, XSKHDBSYNONYMDefinitionModel> synonymDefinitions) {
    this.synonymDefinitions = synonymDefinitions;
  }
}
