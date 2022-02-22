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
import com.sap.xsk.utils.XSKCommonsConstants;
import com.sap.xsk.utils.XSKCommonsUtils;
import com.sap.xsk.utils.XSKHDBUtils;
import java.sql.Connection;
import java.sql.SQLException;
import org.eclipse.dirigible.core.scheduler.api.ISynchronizerArtefactType.ArtefactState;
import org.eclipse.dirigible.database.sql.DatabaseArtifactTypes;
import org.eclipse.dirigible.database.sql.SqlFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HDBSynonymDropProcessor extends AbstractXSKProcessor<XSKDataStructureHDBSynonymModel> {

  private static final Logger logger = LoggerFactory.getLogger(HDBSynonymDropProcessor.class);
  private static final HDBSynonymSynchronizationArtefactType SYNONYM_ARTEFACT = new HDBSynonymSynchronizationArtefactType();

  /**
   * Execute :
   * DROP SYNONYM <synonym_name> [<drop_option>]
   * If <drop_option> is not specified, then a non-cascaded drop is performed which only drops the specified synonym.
   * Dependent objects of the synonym are invalidated but not dropped.
   *
   * @see <a href="https://help.sap.com/viewer/4fe29514fd584807ac9f2a04f6754767/1.0.12/en-US/20d7e172751910148bccb49de92d9859.html">DROP SYNONYM Statement (Data Definition)</a>
   */
  @Override
  public void execute(Connection connection, XSKDataStructureHDBSynonymModel synonymModel) {
    synonymModel.getSynonymDefinitions().forEach((key, value) -> {

      String synonymName = (value.getSynonymSchema() != null) ? XSKHDBUtils.escapeArtifactName(key, value.getSynonymSchema())
          : XSKHDBUtils.escapeArtifactName(key);
      try {
        if (SqlFactory.getNative(connection).exists(connection, value.getSynonymSchema(), key, DatabaseArtifactTypes.SYNONYM)) {
          String sql = SqlFactory.getNative(connection).drop().synonym(synonymName).build();
          try {
            executeSql(sql, connection);
            applyArtefactState(synonymName, synonymModel.getLocation(), SYNONYM_ARTEFACT, ArtefactState.SUCCESSFUL_DELETE,
                "Drop synonym [" + synonymName + "] successfully");
          } catch (SQLException ex) {
            String errorMessage = "Drop synonym [" + synonymName + "] skipped due to an error: " + ex.getMessage();
            XSKCommonsUtils.logProcessorErrors(ex.getMessage(), XSKCommonsConstants.PROCESSOR_ERROR, synonymModel.getLocation(),
                XSKCommonsConstants.HDB_SYNONYM_PARSER);
            applyArtefactState(synonymName, synonymModel.getLocation(), SYNONYM_ARTEFACT, ArtefactState.FAILED_DELETE, errorMessage);
          }
        } else {
          String message = "Synonym [" + synonymName + "] does not exists during the drop process";
          applyArtefactState(synonymName, synonymModel.getLocation(), SYNONYM_ARTEFACT, ArtefactState.SUCCESSFUL_DELETE, message);
        }
      } catch (SQLException exception) {
        String errorMessage = "Drop synonym [" + synonymName + "] skipped due to an error: " + exception.getMessage();
        logger.error(errorMessage, exception);
        applyArtefactState(synonymName, synonymModel.getLocation(), SYNONYM_ARTEFACT, ArtefactState.FAILED_DELETE, errorMessage);
      }
    });
  }
}
