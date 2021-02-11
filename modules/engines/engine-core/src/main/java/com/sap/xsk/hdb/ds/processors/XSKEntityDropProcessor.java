/*
 * Copyright (c) 2019-2020 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2019-2020 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.hdb.ds.processors;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.dirigible.commons.config.Configuration;
import org.eclipse.dirigible.database.ds.model.IDataStructureModel;
import org.eclipse.dirigible.database.sql.SqlFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.xsk.hdb.ds.model.hdbdd.XSKDataStructureEntityModel;
import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableConstraintForeignKeyModel;
import com.sap.xsk.utils.XSKUtils;

/**
 * The Entity Drop Processor.
 */
public class XSKEntityDropProcessor {

    private static final Logger logger = LoggerFactory.getLogger(XSKEntityDropProcessor.class);

    /**
     * Execute the corresponding statement.
     *
     * @param connection  the connection
     * @param entityModel the table model
     * @throws SQLException the SQL exception
     * @return if delete operation has been performed successfully or the table does not exist
     */
    public void execute(Connection connection, XSKDataStructureEntityModel entityModel) throws SQLException {
    	boolean caseSensitive = Boolean.parseBoolean(Configuration.get(IDataStructureModel.DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE, "false"));
        String tableName = XSKUtils.getTableName(entityModel);
        if (caseSensitive) {
			tableName = "\"" + tableName + "\"";
		}
        logger.info("Processing Drop Table: {}", tableName);
        if (SqlFactory.getNative(connection).exists(connection, tableName)) {
            String sql = SqlFactory.getNative(connection).select().column("COUNT(*)").from(tableName)
                    .build();

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                logger.info(sql);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        int count = resultSet.getInt(1);
                        if (count > 0) {
                            logger.error("Drop operation for the non empty Table {} will not be executed. Delete all the records in the table first.", tableName);
                        }
                    }
                }
            } catch (SQLException e) {
                logger.error(sql);
                logger.error(e.getMessage(), e);
            }

            if (entityModel.getConstraints().getForeignKeys() != null) {
                for (XSKDataStructureHDBTableConstraintForeignKeyModel foreignKey : entityModel.getConstraints().getForeignKeys()) {
                	String foreignKeyName = "FK_" + foreignKey.getName();
					String[] fkColumns = foreignKey.getColumns();
					String referencedTable = XSKUtils.getTableName(entityModel, foreignKey.getReferencedTable());
					String[] referencedColumns = foreignKey.getReferencedColumns();
					if (caseSensitive) {
						foreignKeyName = "\"" + foreignKeyName + "\"";
						for (int i=0;i<fkColumns.length;i++) {
							fkColumns[i] = "\"" + fkColumns[i] + "\"";
						}
						referencedTable = "\"" + referencedTable + "\"";
						for (int i=0;i<referencedColumns.length;i++) {
							referencedColumns[i] = "\"" + referencedColumns[i] + "\"";
						}
					}
                    sql = SqlFactory.getNative(connection).drop().constraint(foreignKeyName).fromTable(tableName).build();
                    executeUpdate(connection, sql);
                }
            }

            sql = SqlFactory.getNative(connection).drop().table(tableName).build();
            executeUpdate(connection, sql);
            return;
        }
        logger.warn("Trying to delete non existing Table: {}", tableName);
    }

    private void executeUpdate(Connection connection, String sql) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            logger.info(sql);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(sql);
            logger.error(e.getMessage(), e);
        }
    }

}
