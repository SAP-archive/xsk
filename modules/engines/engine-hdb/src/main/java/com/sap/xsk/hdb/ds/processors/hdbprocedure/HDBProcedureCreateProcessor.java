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
package com.sap.xsk.hdb.ds.processors.hdbprocedure;

import static java.text.MessageFormat.format;

import com.sap.xsk.hdb.ds.model.hdbprocedure.XSKDataStructureHDBProcedureModel;
import com.sap.xsk.hdb.ds.processors.AbstractXSKProcessor;
import com.sap.xsk.utils.XSKCommonsUtils;
import com.sap.xsk.utils.XSKConstants;
import java.sql.Connection;
import java.sql.SQLException;
import org.eclipse.dirigible.core.problems.exceptions.ProblemsException;
import org.eclipse.dirigible.database.sql.DatabaseArtifactTypes;
import org.eclipse.dirigible.database.sql.ISqlDialect;
import org.eclipse.dirigible.database.sql.SqlFactory;
import org.eclipse.dirigible.database.sql.dialects.hana.HanaSqlDialect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HDBProcedureCreateProcessor extends AbstractXSKProcessor<XSKDataStructureHDBProcedureModel> {

  private static final Logger logger = LoggerFactory.getLogger(HDBProcedureCreateProcessor.class);

  public void execute(Connection connection, XSKDataStructureHDBProcedureModel hdbProcedure)
      throws SQLException, ProblemsException {
    logger.info("Processing Create Procedure: " + hdbProcedure.getName());
    String procedureNameWithoutSchema = XSKCommonsUtils.extractArtifactNameWhenSchemaIsProvided(hdbProcedure.getName())[1];
    hdbProcedure.setSchema(XSKCommonsUtils.extractArtifactNameWhenSchemaIsProvided(hdbProcedure.getName())[0]);

    if (!SqlFactory.getNative(connection).exists(connection, procedureNameWithoutSchema, DatabaseArtifactTypes.PROCEDURE)) {
      ISqlDialect dialect = SqlFactory.deriveDialect(connection);
      if (!(dialect.getClass().equals(HanaSqlDialect.class))) {
        String errorMessage = String.format("Procedures are not supported for %s", dialect.getDatabaseName(connection));
        XSKCommonsUtils.logProcessorErrors(errorMessage, "PROCESSOR", hdbProcedure.getLocation(), "HDB Procedure");
        throw new IllegalStateException(errorMessage);
      } else {
        String sql = XSKConstants.XSK_HDBPROCEDURE_CREATE + hdbProcedure.getContent();
        try {
          executeSql(sql, connection);
        } catch (SQLException ex) {
          XSKCommonsUtils.logProcessorErrors(ex.getMessage(), "PROCESSOR", hdbProcedure.getLocation(), "HDB Procedure");
        }
      }
    } else {
      logger.warn(format("Procedure [{0}] already exists during the create process", hdbProcedure.getName()));
    }
  }
}
