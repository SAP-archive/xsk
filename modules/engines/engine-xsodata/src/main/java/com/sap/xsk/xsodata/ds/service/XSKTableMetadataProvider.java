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
package com.sap.xsk.xsodata.ds.service;

import com.sap.xsk.xsodata.ds.model.XSKDBArtifactModel;
import org.eclipse.dirigible.commons.config.Configuration;
import org.eclipse.dirigible.commons.config.StaticObjects;
import org.eclipse.dirigible.database.persistence.model.PersistenceTableModel;
import org.eclipse.dirigible.database.sql.ISqlKeywords;
import org.eclipse.dirigible.engine.odata2.api.ITableMetadataProvider;
import org.eclipse.dirigible.engine.odata2.definition.ODataEntityDefinition;
import org.eclipse.dirigible.engine.odata2.transformers.DBMetadataUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static com.sap.xsk.utils.XSKCommonsDBUtils.getSynonymTargetObjectMetadata;

public class XSKTableMetadataProvider implements ITableMetadataProvider {

  private DataSource dataSource = (DataSource) StaticObjects.get(StaticObjects.DATASOURCE);

  private static final Logger logger = LoggerFactory.getLogger(XSKTableMetadataProvider.class);

  private DBMetadataUtil dbMetadataUtil = new DBMetadataUtil();

  private static final List<String> METADATA_ENTITY_TYPES = List.of(ISqlKeywords.METADATA_TABLE, ISqlKeywords.METADATA_CALC_VIEW,
      ISqlKeywords.METADATA_VIEW);
  private static final String PUBLIC_SCHEMA = "PUBLIC";

  public PersistenceTableModel getPersistenceTableModel(ODataEntityDefinition oDataEntityDefinition) throws SQLException {

    PersistenceTableModel tableMetadata = dbMetadataUtil.getTableMetadata(oDataEntityDefinition.getName(), Configuration.get("HANA_USERNAME"));

    if(METADATA_ENTITY_TYPES.contains(tableMetadata.getTableType())) {
      return tableMetadata;
    }

    String targetSchema = ISqlKeywords.METADATA_SYNONYM.equals(tableMetadata.getTableType())? tableMetadata.getSchemaName() : PUBLIC_SCHEMA;

    PersistenceTableModel targetObjectMetadata = getSynonymTargetObjectMetadata(dataSource, tableMetadata.getTableName(), targetSchema);

    if (targetObjectMetadata.getTableName() == null) {
      logger.error("We cannot find view/table/synonym with name " + tableMetadata.getTableName() + " in schema" + targetSchema +
          " and there is no public synonym with that name. Make sure that you are using view/table/synonym which is in your user default "
          + "schema or is a public synonym.");
      return null;
    }

    tableMetadata = dbMetadataUtil.getTableMetadata(targetObjectMetadata.getTableName(), targetObjectMetadata.getSchemaName());

    return tableMetadata;
  }
}
