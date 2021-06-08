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
package com.sap.xsk.hdb.ds.processors.hdbprocedure;

import static java.text.MessageFormat.format;

import com.sap.xsk.hdb.ds.model.hdbprocedure.XSKDataStructureHDBProcedureModel;
import com.sap.xsk.hdb.ds.processors.AbstractXSKProcessor;
import com.sap.xsk.utils.XSKConstants;
import com.sap.xsk.utils.XSKHDBUtils;
import java.sql.Connection;
import java.sql.SQLException;
import org.eclipse.dirigible.database.sql.DatabaseArtifactTypes;
import org.eclipse.dirigible.database.sql.ISqlDialect;
import org.eclipse.dirigible.database.sql.SqlFactory;
import org.eclipse.dirigible.database.sql.dialects.hana.HanaSqlDialect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HDBProcedureDropProcessor extends AbstractXSKProcessor<XSKDataStructureHDBProcedureModel> {

  private static final Logger logger = LoggerFactory.getLogger(HDBProcedureDropProcessor.class);

  public void execute(Connection connection, XSKDataStructureHDBProcedureModel hdbProcedure) throws SQLException {
    logger.info("Processing Drop Procedure: " + hdbProcedure.getName());

    String procedureName = XSKHDBUtils.escapeArtifactName(connection, hdbProcedure.getName());
    if (SqlFactory.getNative(connection).exists(connection, procedureName, DatabaseArtifactTypes.PROCEDURE)) {
      ISqlDialect dialect = SqlFactory.deriveDialect(connection);
      if (!(dialect.getClass().equals(HanaSqlDialect.class))) {
        throw new IllegalStateException(String.format("%s does not support Procedures", dialect.getDatabaseName(connection)));
      } else {
        String sql = XSKConstants.XSK_HDBPROCEDURE_DROP + hdbProcedure.getName();
        executeSql(sql, connection);
      }
    } else {
      logger.warn(format("Procedure [{0}] already exists during the drop process", hdbProcedure.getName()));
    }
  }
}
