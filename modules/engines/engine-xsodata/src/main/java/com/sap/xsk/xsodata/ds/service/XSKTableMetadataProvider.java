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
import org.eclipse.dirigible.engine.odata2.definition.ODataEntityDefinition;
import org.eclipse.dirigible.engine.odata2.transformers.DBMetadataUtil;
import org.eclipse.dirigible.engine.odata2.transformers.TableMetadataProvider;
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

public class XSKTableMetadataProvider extends TableMetadataProvider {

  private DataSource dataSource = (DataSource) StaticObjects.get(StaticObjects.DATASOURCE);

  private static final Logger logger = LoggerFactory.getLogger(XSKTableMetadataProvider.class);

  private DBMetadataUtil dbMetadataUtil = new DBMetadataUtil();

  private static final List<String> METADATA_ENTITY_TYPES = List.of(ISqlKeywords.METADATA_TABLE, ISqlKeywords.METADATA_CALC_VIEW,
      ISqlKeywords.METADATA_VIEW);
  private static final String PUBLIC_SCHEMA = "PUBLIC";

  public PersistenceTableModel getPersistenceTableModel(ODataEntityDefinition oDataEntityDefinition, StringBuilder buff) throws SQLException {

    PersistenceTableModel tableMetadata = dbMetadataUtil.getTableMetadata(oDataEntityDefinition.getName(), Configuration.get("HANA_USERNAME"));

    if(METADATA_ENTITY_TYPES.contains(tableMetadata.getTableType())) {
      return tableMetadata;
    }

    String targetSchema = ISqlKeywords.METADATA_SYNONYM.equals(tableMetadata.getTableType())? tableMetadata.getSchemaName() : PUBLIC_SCHEMA;

    HashMap<String, String> targetObjectMetadata = dbMetadataUtil.getSynonymTargetObjectMetadata(tableMetadata.getTableName(), targetSchema);

    if (targetObjectMetadata.isEmpty()) {
      logger.error("Failed to get details for synonym - " + tableMetadata.getTableName());
      return null;
    }

    if (!METADATA_ENTITY_TYPES.contains(targetObjectMetadata.get(ISqlKeywords.KEYWORD_TABLE_TYPE))) {
      logger.error("Unsupported object type for {}", targetObjectMetadata.get(ISqlKeywords.KEYWORD_TABLE));
      return null;
    }

    tableMetadata = dbMetadataUtil.getTableMetadata(targetObjectMetadata.get(ISqlKeywords.KEYWORD_TABLE), targetObjectMetadata.get(ISqlKeywords.KEYWORD_SCHEMA));

    return tableMetadata;
  }
}
