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
package com.sap.xsk.xsodata.ds.service;

import static com.sap.xsk.utils.XSKCommonsDBUtils.getSynonymTargetObjectMetadata;

import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.eclipse.dirigible.commons.config.Configuration;
import org.eclipse.dirigible.commons.config.StaticObjects;
import org.eclipse.dirigible.database.persistence.model.PersistenceTableModel;
import org.eclipse.dirigible.database.sql.ISqlKeywords;
import org.eclipse.dirigible.engine.odata2.api.ITableMetadataProvider;
import org.eclipse.dirigible.engine.odata2.definition.ODataEntityDefinition;
import org.eclipse.dirigible.engine.odata2.transformers.DBMetadataUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XSKTableMetadataProvider implements ITableMetadataProvider {

  private final DataSource dataSource = (DataSource) StaticObjects.get(StaticObjects.DATASOURCE);

  private static final Logger logger = LoggerFactory.getLogger(XSKTableMetadataProvider.class);

  private final DBMetadataUtil dbMetadataUtil = new DBMetadataUtil();

  private static final List<String> METADATA_ENTITY_TYPES = List.of(ISqlKeywords.METADATA_TABLE, ISqlKeywords.METADATA_CALC_VIEW,
      ISqlKeywords.METADATA_VIEW);
  private static final String PUBLIC_SCHEMA = "PUBLIC";

  @Override
  public PersistenceTableModel getPersistenceTableModel(ODataEntityDefinition oDataEntityDefinition) throws SQLException {
    return getPersistenceTableModel(oDataEntityDefinition.getTable());
  }

  public PersistenceTableModel getPersistenceTableModel(String artifactName) throws SQLException {
    String currentUserSchema = Configuration.get("HANA_USERNAME");
    PersistenceTableModel tableMetadata = dbMetadataUtil.getTableMetadata(artifactName, currentUserSchema);

    if (null != tableMetadata && METADATA_ENTITY_TYPES.contains(tableMetadata.getTableType())) {
      return tableMetadata;
    }

    PersistenceTableModel targetObjectMetadata = null;
    if (null == tableMetadata) {
      targetObjectMetadata = getSynonymTargetObjectMetadata(dataSource, artifactName, PUBLIC_SCHEMA);
    } else if (ISqlKeywords.METADATA_SYNONYM.equals(tableMetadata.getTableType())) {
      targetObjectMetadata = getSynonymTargetObjectMetadata(dataSource, artifactName, tableMetadata.getSchemaName());
    }

    if (null == targetObjectMetadata || targetObjectMetadata.getTableName() == null) {
      logger.error("We cannot find view/table/synonym with name " + artifactName + " in schema " + currentUserSchema +
          " and there is no public synonym with that name. Make sure that you are using view/table/synonym which is in your user default "
          + "schema or is a public synonym.");
      return null;
    }

    return dbMetadataUtil.getTableMetadata(targetObjectMetadata.getTableName(), targetObjectMetadata.getSchemaName());
  }
}
