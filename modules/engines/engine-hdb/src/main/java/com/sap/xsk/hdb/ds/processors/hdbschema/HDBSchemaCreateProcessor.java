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
package com.sap.xsk.hdb.ds.processors.hdbschema;

import com.sap.xsk.hdb.ds.model.hdbschema.XSKDataStructureHDBSchemaModel;
import com.sap.xsk.hdb.ds.processors.AbstractXSKProcessor;
import java.sql.Connection;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HDBSchemaCreateProcessor extends AbstractXSKProcessor<XSKDataStructureHDBSchemaModel> {

  private static final Logger logger = LoggerFactory.getLogger(HDBSchemaCreateProcessor.class);

  public HDBSchemaCreateProcessor() {
  }

  public void execute(Connection connection, XSKDataStructureHDBSchemaModel hdbSchema) throws SQLException {
    String sql = "CREATE SCHEMA " + hdbSchema.getName();
    executeSql(sql, connection);
  }
}
