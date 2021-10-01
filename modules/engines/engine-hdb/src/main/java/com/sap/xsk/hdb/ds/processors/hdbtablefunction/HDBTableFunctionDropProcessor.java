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
package com.sap.xsk.hdb.ds.processors.hdbtablefunction;

import static java.text.MessageFormat.format;

import com.sap.xsk.hdb.ds.model.hdbtablefunction.XSKDataStructureHDBTableFunctionModel;
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

public class HDBTableFunctionDropProcessor extends AbstractXSKProcessor<XSKDataStructureHDBTableFunctionModel> {

  private static final Logger logger = LoggerFactory.getLogger(HDBTableFunctionDropProcessor.class);

  public void execute(Connection connection, XSKDataStructureHDBTableFunctionModel hdbTableFunction)
      throws SQLException, ProblemsException {
    logger.info("Processing Drop TableFunction: " + hdbTableFunction.getName());

    String funcNameWithoutSchema = XSKCommonsUtils.extractArtifactNameWhenSchemaIsProvided(hdbTableFunction.getName())[1];
    hdbTableFunction.setSchema(XSKCommonsUtils.extractArtifactNameWhenSchemaIsProvided(hdbTableFunction.getName())[0]);
    if (SqlFactory.getNative(connection)
        .exists(connection, hdbTableFunction.getSchema(), funcNameWithoutSchema, DatabaseArtifactTypes.FUNCTION)) {
      ISqlDialect dialect = SqlFactory.deriveDialect(connection);
      if (!(dialect.getClass().equals(HanaSqlDialect.class))) {
        String errorMessage = String.format("TableFunctions are not supported for %s", dialect.getDatabaseName(connection));
        XSKCommonsUtils.logProcessorErrors(errorMessage, "PROCESSOR", hdbTableFunction.getLocation(), "HDB Table Function");
        throw new IllegalStateException(errorMessage);
      } else {
        String sql = XSKConstants.XSK_HDBTABLEFUNCTION_DROP + hdbTableFunction.getName();
        try {
          executeSql(sql, connection);
        } catch (SQLException ex) {
          XSKCommonsUtils.logProcessorErrors(ex.getMessage(), "PROCESSOR", hdbTableFunction.getLocation(), "HDB Table Function");
        }
      }
    } else {
      logger.warn(format("TableFunction [{0}] does not exists during the drop process", hdbTableFunction.getName()));
    }
  }

}
