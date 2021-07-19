/*
 * Copyright (c) 2019-2021 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2019-2021 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.hdb.ds.processors.synonym;

import com.sap.xsk.hdb.ds.model.hdbsynonym.XSKDataStructureHDBSynonymModel;
import com.sap.xsk.hdb.ds.processors.AbstractXSKProcessor;
import com.sap.xsk.utils.XSKHDBUtils;
import org.eclipse.dirigible.database.sql.DatabaseArtifactTypes;
import org.eclipse.dirigible.database.sql.ISqlDialect;
import org.eclipse.dirigible.database.sql.SqlFactory;
import org.eclipse.dirigible.database.sql.dialects.hana.HanaSqlDialect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

import static java.text.MessageFormat.format;

public class HDBSynonymDropProcessor extends AbstractXSKProcessor<XSKDataStructureHDBSynonymModel> {

  private static final Logger logger = LoggerFactory.getLogger(HDBSynonymDropProcessor.class);

  /**
   * Execute :
   * DROP SYNONYM <synonym_name> [<drop_option>]
   * If <drop_option> is not specified, then a non-cascaded drop is performed which only drops the specified synonym.
   * Dependent objects of the synonym are invalidated but not dropped.
   *
   * @see <a href="https://help.sap.com/viewer/4fe29514fd584807ac9f2a04f6754767/1.0.12/en-US/20d7e172751910148bccb49de92d9859.html">DROP SYNONYM Statement (Data Definition)</a>
   */
  @Override
  public void execute(Connection connection, XSKDataStructureHDBSynonymModel synonymModel) throws SQLException {
    synonymModel.getSynonymDefinitions().forEach((key, value) -> {
      logger.info("Processing Drop Synonym: " + key);

            String synonymName = (value.getSynonymSchema() != null) ? (XSKHDBUtils.escapeArtifactName(connection, key, value.getSynonymSchema())) : (XSKHDBUtils.escapeArtifactName(connection, key));
      try {
                if (SqlFactory.getNative(connection).exists(connection, value.getSynonymSchema(), key, DatabaseArtifactTypes.SYNONYM)) {
          ISqlDialect dialect = SqlFactory.deriveDialect(connection);
          if (!(dialect.getClass().equals(HanaSqlDialect.class))) {
            throw new IllegalStateException(String.format("Synonyms are not supported for %s !", dialect.getDatabaseName(connection)));
          } else {
            String sql = SqlFactory.getNative(connection).drop().synonym(synonymName).build();
            executeSql(sql, connection);
          }
        } else {
                    logger.warn(format("Synonym [{0}] does not exists during the drop process", value.getSynonymSchema() + "." + key));
        }
      } catch (SQLException exception) {
        exception.printStackTrace();
      }
    });
  }
}
