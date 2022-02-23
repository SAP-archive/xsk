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
package com.sap.xsk.hdb.ds.processors.hdbstructure;

import com.sap.xsk.hdb.ds.model.hdbtabletype.XSKDataStructureHDBTableTypeModel;
import com.sap.xsk.hdb.ds.processors.AbstractXSKProcessor;
import com.sap.xsk.utils.XSKCommonsConstants;
import com.sap.xsk.utils.XSKCommonsUtils;
import com.sap.xsk.utils.XSKHDBUtils;
import java.sql.Connection;
import java.sql.SQLException;
import org.eclipse.dirigible.database.sql.DatabaseArtifactTypes;
import org.eclipse.dirigible.database.sql.SqlFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XSKTableTypeDropProcessor extends AbstractXSKProcessor<XSKDataStructureHDBTableTypeModel> {

  private static final Logger logger = LoggerFactory.getLogger(XSKTableTypeDropProcessor.class);

  private final HDBSynonymRemover synonymRemover;

  public XSKTableTypeDropProcessor(HDBSynonymRemover synonymRemover) {
    this.synonymRemover = synonymRemover;
  }

  @Override
  public boolean execute(Connection connection, XSKDataStructureHDBTableTypeModel tableTypeModel) throws SQLException {
    synonymRemover.removePublicSynonym(connection, tableTypeModel.getSchema(), tableTypeModel.getName());

    if (tableTypeDoesNotExist(connection, tableTypeModel)) {
      logger.debug("Table Type [{}] in schema [{}] does not exists during the drop process", tableTypeModel.getName(),
          tableTypeModel.getSchema());
      return true;
    }

    String tableTypeName = escapeTableTypeName(connection, tableTypeModel);
    try {
      String sql = getDropTableTypeSQL(connection, tableTypeName);
      executeSql(sql, connection);
      return true;
    } catch (SQLException | IllegalStateException ex) {
      processException(tableTypeModel, ex);
      return false;
    }
  }

  void processException(XSKDataStructureHDBTableTypeModel tableTypeModel, Exception ex) {
    logger.error("Failed to drop table type [{}] in schema [{}]", tableTypeModel.getName(), tableTypeModel.getSchema(), ex);
    XSKCommonsUtils.logProcessorErrors(ex.getMessage(), XSKCommonsConstants.PROCESSOR_ERROR, tableTypeModel.getLocation(),
        XSKCommonsConstants.HDB_TABLE_TYPE_PARSER);
  }

  String escapeTableTypeName(Connection connection, XSKDataStructureHDBTableTypeModel tableTypeModel) {
    return XSKHDBUtils.escapeArtifactName(tableTypeModel.getName(), tableTypeModel.getSchema());
  }

  String getDropTableTypeSQL(Connection connection, String tableTypeName) throws IllegalStateException {
    return SqlFactory.getNative(connection).drop().tableType(tableTypeName).build();
  }

  boolean tableTypeDoesNotExist(Connection connection, XSKDataStructureHDBTableTypeModel tableTypeModel) throws SQLException {
    return !SqlFactory.getNative(connection)
        .exists(connection, tableTypeModel.getSchema(), tableTypeModel.getName(), DatabaseArtifactTypes.TABLE_TYPE);
  }
}
