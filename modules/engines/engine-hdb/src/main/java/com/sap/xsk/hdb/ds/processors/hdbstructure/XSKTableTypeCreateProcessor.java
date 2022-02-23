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

import static java.text.MessageFormat.format;

import com.sap.xsk.hdb.ds.api.IXSKDataStructureModel;
import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableColumnModel;
import com.sap.xsk.hdb.ds.model.hdbtabletype.XSKDataStructureHDBTableTypeModel;
import com.sap.xsk.hdb.ds.module.XSKHDBModule;
import com.sap.xsk.hdb.ds.processors.AbstractXSKProcessor;
import com.sap.xsk.hdb.ds.service.manager.IXSKDataStructureManager;
import com.sap.xsk.utils.XSKCommonsConstants;
import com.sap.xsk.utils.XSKCommonsUtils;
import com.sap.xsk.utils.XSKConstants;
import com.sap.xsk.utils.XSKHDBUtils;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.eclipse.dirigible.database.sql.DataType;
import org.eclipse.dirigible.database.sql.DatabaseArtifactTypes;
import org.eclipse.dirigible.database.sql.ISqlDialect;
import org.eclipse.dirigible.database.sql.SqlFactory;
import org.eclipse.dirigible.database.sql.builders.tableType.CreateTableTypeBuilder;
import org.eclipse.dirigible.database.sql.dialects.hana.HanaSqlDialect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XSKTableTypeCreateProcessor extends AbstractXSKProcessor<XSKDataStructureHDBTableTypeModel> {

  private static final Logger logger = LoggerFactory.getLogger(XSKTableTypeCreateProcessor.class);
  private Map<String, IXSKDataStructureManager> managerServices = XSKHDBModule.getManagerServices();

  /**
   * Execute the corresponding statement.
   * The method will create a table type and a public synonym in order this table type to be accessed from other schemas.
   *
   * @param connection     the connection
   * @param tableTypeModel the table type model
   * @throws SQLException the SQL exception
   */
  @Override
  public boolean execute(Connection connection, XSKDataStructureHDBTableTypeModel tableTypeModel)
      throws SQLException {
    logger.info("Processing Create Table Type: " + tableTypeModel.getName());
    
    boolean success = false;

    String tableTypeNameWithoutSchema = tableTypeModel.getName();
    String tableTypeNameWithSchema = XSKHDBUtils.escapeArtifactName(tableTypeNameWithoutSchema, tableTypeModel.getSchema());
    List<XSKDataStructureHDBTableColumnModel> columns = tableTypeModel.getColumns();

    if (!SqlFactory.getNative(connection)
        .exists(connection, tableTypeModel.getSchema(), tableTypeNameWithoutSchema, DatabaseArtifactTypes.TABLE_TYPE)) {
      String sql = null;
      CreateTableTypeBuilder createTableTypeBuilder = SqlFactory.getNative(connection).create().tableType(tableTypeNameWithSchema);

      for (XSKDataStructureHDBTableColumnModel columnModel : columns) {
        String name = XSKHDBUtils.escapeArtifactName(columnModel.getName());
        DataType type = DataType.valueOf(columnModel.getType());
        createTableTypeBuilder
            .column(name, type, columnModel.isPrimaryKey(), columnModel.isNullable(), this.getColumnModelArgs(columnModel));
      }

      switch (tableTypeModel.getDBContentType()) {
        case XS_CLASSIC: {
          sql = createTableTypeBuilder.build();
          break;
        }
        case OTHERS: {
          ISqlDialect dialect = SqlFactory.deriveDialect(connection);
          if (dialect.getClass().equals(HanaSqlDialect.class)) {
            sql = XSKConstants.XSK_HDBTABLETYPE_CREATE + tableTypeModel.getRawContent();
            break;
          } else {
            String errorMessage = String.format("Table Types are not supported for %s !", dialect.getDatabaseName(connection));
            XSKCommonsUtils.logProcessorErrors(errorMessage, XSKCommonsConstants.PROCESSOR_ERROR, tableTypeModel.getLocation(),
                XSKCommonsConstants.HDB_TABLE_TYPE_PARSER);
            throw new IllegalStateException(errorMessage);
          }
        }
      }
      
      try {
        executeSql(sql, connection);
        success = true;
      } catch (SQLException ex) {
    	logger.error(format("Table Type [{0}] failed during the create process", tableTypeNameWithoutSchema));
        XSKCommonsUtils.logProcessorErrors(ex.getMessage(), XSKCommonsConstants.PROCESSOR_ERROR, tableTypeModel.getLocation(),
            XSKCommonsConstants.HDB_TABLE_TYPE_PARSER);
      }
    } else {
      logger.warn(format("Table Type [{0}] already exists during the create process", tableTypeNameWithoutSchema));
    }

    //Create public synonym only if the table type exist
    if (SqlFactory.getNative(connection)
        .exists(connection, tableTypeModel.getSchema(), tableTypeNameWithoutSchema, DatabaseArtifactTypes.TABLE_TYPE)) {
      XSKHDBUtils.createPublicSynonymForArtifact(managerServices
          .get(IXSKDataStructureModel.TYPE_HDB_SYNONYM), tableTypeNameWithoutSchema, tableTypeModel.getSchema(), connection);
    }
    return success;
  }

  private String getColumnModelArgs(XSKDataStructureHDBTableColumnModel columnModel) {
    DataType type = DataType.valueOf(columnModel.getType());
    String args = "";
    if (columnModel.getLength() != null) {
      if (type.equals(DataType.VARCHAR) || type.equals(DataType.CHAR)
          || columnModel.getType().equalsIgnoreCase("NVARCHAR")
          || columnModel.getType().equalsIgnoreCase("ALPHANUM")
          || columnModel.getType().equalsIgnoreCase("SHORTTEXT")) {
        args = columnModel.getLength();
      }
    } else if ((columnModel.getPrecision() != null) && (columnModel.getScale() != null)) {
      if (type.equals(DataType.DECIMAL)) {
        args = columnModel.getPrecision() + "," + columnModel.getScale();
      }
    }
    return args;
  }
}
