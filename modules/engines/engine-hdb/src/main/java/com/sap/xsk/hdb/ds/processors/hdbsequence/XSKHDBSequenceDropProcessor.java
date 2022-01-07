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
package com.sap.xsk.hdb.ds.processors.hdbsequence;

import com.sap.xsk.hdb.ds.artefacts.HDBSequenceSynchronizationArtefactType;
import com.sap.xsk.hdb.ds.model.hdbsequence.XSKDataStructureHDBSequenceModel;
import com.sap.xsk.hdb.ds.processors.AbstractXSKProcessor;
import com.sap.xsk.hdb.ds.synchronizer.XSKDataStructuresSynchronizer;
import com.sap.xsk.utils.XSKCommonsConstants;
import com.sap.xsk.utils.XSKCommonsUtils;
import com.sap.xsk.utils.XSKConstants;
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

public class XSKHDBSequenceDropProcessor extends AbstractXSKProcessor<XSKDataStructureHDBSequenceModel> {

  private static final Logger logger = LoggerFactory.getLogger(XSKHDBSequenceDropProcessor.class);
  private static final HDBSequenceSynchronizationArtefactType SEQUENCE_ARTEFACT = new HDBSequenceSynchronizationArtefactType();
  private static final XSKDataStructuresSynchronizer dataStructuresSynchronizer = new XSKDataStructuresSynchronizer();

  @Override
  public void execute(Connection connection, XSKDataStructureHDBSequenceModel hdbSequenceModel) throws SQLException {
    String hdbSequenceName = XSKHDBUtils.escapeArtifactName(connection, hdbSequenceModel.getName(), hdbSequenceModel.getSchema());
    logger.info("Processing Drop HdbSequence: " + hdbSequenceName);

    if (SqlFactory.getNative(connection).exists(connection, hdbSequenceName, DatabaseArtifactTypes.SEQUENCE)) {
      String sql = null;
      switch (hdbSequenceModel.getDBContentType()) {
        case XS_CLASSIC: {
          sql = getDatabaseSpecificSQL(connection, hdbSequenceName);
          break;
        }
        case OTHERS: {
          ISqlDialect dialect = SqlFactory.deriveDialect(connection);
          if (dialect.getClass().equals(HanaSqlDialect.class)) {
            sql = XSKConstants.XSK_HDBSEQUENCE_DROP + hdbSequenceModel.getRawContent();
            break;
          } else {
            String errorMessage = String.format("Sequences are not supported for %s !", dialect.getDatabaseName(connection));
            XSKCommonsUtils.logProcessorErrors(errorMessage, XSKCommonsConstants.PROCESSOR_ERROR, hdbSequenceModel.getLocation(), XSKCommonsConstants.HDB_SEQUENCE_PARSER);
            dataStructuresSynchronizer.applyArtefactState(hdbSequenceModel.getName(), hdbSequenceModel.getLocation(), SEQUENCE_ARTEFACT, ArtefactState.FAILED_DELETE, errorMessage);
            throw new IllegalStateException(errorMessage);
          }
        }
      }
      try {
        executeSql(sql, connection);
        String message = String.format("Drop sequence %s successfully", hdbSequenceModel.getName());
        dataStructuresSynchronizer.applyArtefactState(hdbSequenceModel.getName(), hdbSequenceModel.getLocation(), SEQUENCE_ARTEFACT, ArtefactState.SUCCESSFUL_DELETE, message);
      } catch (SQLException ex) {
        String message = String.format("Drop sequence [%s] skipped due to an error: %s", hdbSequenceModel.getName(), ex.getMessage());
        XSKCommonsUtils.logProcessorErrors(ex.getMessage(), XSKCommonsConstants.PROCESSOR_ERROR, hdbSequenceModel.getLocation(), XSKCommonsConstants.HDB_SEQUENCE_PARSER);
        dataStructuresSynchronizer.applyArtefactState(hdbSequenceModel.getName(), hdbSequenceModel.getLocation(), SEQUENCE_ARTEFACT, ArtefactState.FAILED_DELETE, message);
      }
    } else {
      String warningMessage = String.format("Sequence [%s] does not exists during the drop process", hdbSequenceModel.getName());
      logger.warn(warningMessage);
      dataStructuresSynchronizer.applyArtefactState(hdbSequenceModel.getName(), hdbSequenceModel.getLocation(), SEQUENCE_ARTEFACT, ArtefactState.FAILED_DELETE, warningMessage);
    }
  }

  private String getDatabaseSpecificSQL(Connection connection, String hdbSequenceName) {
    return SqlFactory.getNative(connection).drop().sequence(hdbSequenceName).build();
  }

}