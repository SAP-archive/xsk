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
package com.sap.xsk.hdb.ds.processors.synonym;

import com.sap.xsk.hdb.ds.artefacts.HDBSynonymSynchronizationArtefactType;
import com.sap.xsk.hdb.ds.model.hdbsynonym.XSKDataStructureHDBSynonymModel;
import com.sap.xsk.hdb.ds.processors.AbstractXSKProcessor;
import com.sap.xsk.hdb.ds.synchronizer.XSKDataStructuresSynchronizer;
import com.sap.xsk.utils.XSKCommonsConstants;
import com.sap.xsk.utils.XSKCommonsUtils;
import com.sap.xsk.utils.XSKHDBUtils;
import java.sql.Connection;
import java.sql.SQLException;
import org.eclipse.dirigible.core.scheduler.api.ISynchronizerArtefactType.ArtefactState;
import org.eclipse.dirigible.database.sql.DatabaseArtifactTypes;
import org.eclipse.dirigible.database.sql.ISqlDialect;
import org.eclipse.dirigible.database.sql.SqlFactory;
import org.eclipse.dirigible.database.sql.dialects.hana.HanaSqlDialect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class HDBSynonymCreateProcessor extends AbstractXSKProcessor<XSKDataStructureHDBSynonymModel> {

  private static final Logger logger = LoggerFactory.getLogger(HDBSynonymCreateProcessor.class);
  private static final HDBSynonymSynchronizationArtefactType SYNONYM_ARTEFACT = new HDBSynonymSynchronizationArtefactType();
  private static final XSKDataStructuresSynchronizer dataStructuresSynchronizer = new XSKDataStructuresSynchronizer();

  /**
   * Execute :
   * CREATE [PUBLIC] SYNONYM <synonym_name> FOR <synonym_source_object_name>
   * <synonym_name> ::= [<schema_name>.]<identifier>
   * <synonym_source_object_name>:==[<object_schema_name>.]<object_name>
   *
   * @see <a href="https://help.sap.com/viewer/4fe29514fd584807ac9f2a04f6754767/1.0.12/en-US/20d5412b75191014bc7ec7e133ce5bf5.html">CREATE SYNONYM Statement (Data Definition)</a>
   */
  @Override
  public void execute(Connection connection, XSKDataStructureHDBSynonymModel synonymModel) throws SQLException {
    synonymModel.getSynonymDefinitions().forEach((key, value) -> {
      logger.info("Processing Create Synonym: " + key);

      String synonymName = (value.getSynonymSchema() != null) ? (XSKHDBUtils.escapeArtifactName(connection, key, value.getSynonymSchema()))
          : (XSKHDBUtils.escapeArtifactName(connection, key));
      String targetObjectName = XSKHDBUtils
          .escapeArtifactName(connection, value.getTarget().getObject(),
              value.getTarget().getSchema());
      try {
        String synonymSchema = null != value.getSynonymSchema() ? value.getSynonymSchema() : connection.getMetaData().getUserName();
        if (!SqlFactory.getNative(connection).exists(connection, synonymSchema, key, DatabaseArtifactTypes.SYNONYM)) {
          ISqlDialect dialect = SqlFactory.deriveDialect(connection);
          if (!(dialect.getClass().equals(HanaSqlDialect.class))) {
            String errorMessage = String.format("Synonyms are not supported for %s !", dialect.getDatabaseName(connection));
            XSKCommonsUtils.logProcessorErrors(errorMessage, XSKCommonsConstants.PROCESSOR_ERROR, synonymModel.getLocation(), XSKCommonsConstants.HDB_SYNONYM_PARSER);
            dataStructuresSynchronizer.applyArtefactState(synonymName, synonymModel.getLocation(), SYNONYM_ARTEFACT, ArtefactState.FAILED_CREATE, errorMessage);
            throw new IllegalStateException(errorMessage);
          } else {
            String sql = SqlFactory.getNative(connection).create().synonym(synonymName).forSource(targetObjectName).build();
            try {
              executeSql(sql, connection);
              String message = String.format("Create synonym %s successfully", synonymName);
              dataStructuresSynchronizer.applyArtefactState(synonymName, synonymModel.getLocation(), SYNONYM_ARTEFACT, ArtefactState.SUCCESSFUL_CREATE, message);
            } catch (SQLException ex) {
              String errorMessage = String.format("Create synonym [%s] skipped due to an error: %s", synonymName, ex.getMessage());
              XSKCommonsUtils.logProcessorErrors(ex.getMessage(), XSKCommonsConstants.PROCESSOR_ERROR, synonymModel.getLocation(), XSKCommonsConstants.HDB_SYNONYM_PARSER);
              dataStructuresSynchronizer.applyArtefactState(synonymName, synonymModel.getLocation(), SYNONYM_ARTEFACT, ArtefactState.FAILED_CREATE, errorMessage);
            }
          }
        } else {
          String warningMessage = String.format("Synonym [%s] already exists during the create process", synonymName);
          logger.warn(warningMessage);
          dataStructuresSynchronizer.applyArtefactState(synonymName, synonymModel.getLocation(), SYNONYM_ARTEFACT, ArtefactState.FAILED_DELETE, warningMessage);
        }
      } catch (SQLException exception) {
        String errorMessage = String.format("Create synonym [%s] skipped due to an error: %s", synonymName, exception.getMessage());
        logger.error(exception.getMessage(), exception);
        dataStructuresSynchronizer.applyArtefactState(synonymName, synonymModel.getLocation(), SYNONYM_ARTEFACT, ArtefactState.FAILED_CREATE, errorMessage);
      }
    });
  }
}
