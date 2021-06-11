/*
 * Copyright (c) 2019-2021 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2019-2021 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.hdb.ds.model.hdbschema;

import com.sap.xsk.hdb.ds.model.XSKDataStructureModel;

/**
 * The schema model representation.
 */
public class XSKDataStructureHDBSchemaModel extends XSKDataStructureModel {

  private String schemaName;

  /**
   * Getter for the schema_name field.
   *
   * @return the schema_name field
   */
  public String getSchemaName() {
    return schemaName;
  }

  /**
   * Setter for the schema_name field.
   *
   * @param schemaName the schema_name field
   */
  public void setSchemaName(String schemaName) {
    this.schemaName = schemaName;
  }
}
