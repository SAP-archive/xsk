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
package com.sap.xsk.hdbti.dao;

import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdbti.model.XSKImportedCSVRecordModel;
import com.sap.xsk.hdbti.processors.XSKHDBTIProcessor;
import org.eclipse.dirigible.database.persistence.PersistenceManager;
import org.eclipse.dirigible.database.sql.SqlFactory;
import org.eclipse.dirigible.database.sql.builders.records.DeleteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Singleton
public class XSKImportedCSVRecordDao {
    private static final Logger logger = LoggerFactory.getLogger(XSKHDBTIProcessor.class);

    @Inject
    private DataSource dataSource;

    @Inject
    private PersistenceManager<XSKImportedCSVRecordModel> persistenceManager;

    public XSKImportedCSVRecordModel save(XSKImportedCSVRecordModel importedRowModel) throws XSKDataStructuresException {
        try (Connection connection = dataSource.getConnection()) {
            importedRowModel.setCreatedAt(new Timestamp(new java.util.Date().getTime()));
            persistenceManager.insert(connection, importedRowModel);
            logger.info(format("Entity with rowId: %s and tableName: %s was SAVED successfully in %s.", importedRowModel.getRowId(), importedRowModel.getTableName(), "XSK_IMPORTED_CSV_RECORDS"));
            return importedRowModel;
        } catch (SQLException e) {
            throw new XSKDataStructuresException(e);
        }


    }

    public XSKImportedCSVRecordModel update(XSKImportedCSVRecordModel importedRowModel) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            persistenceManager.update(connection, importedRowModel);
            logger.info(format("Entity with rowId: %s and tableName: %s was UPDATED successfully in %s.", importedRowModel.getRowId(), importedRowModel.getTableName(), "XSK_IMPORTED_CSV_RECORDS"));
            return importedRowModel;
        }
    }

    public void deleteAll(List<String> rowIds, String tableName) throws SQLException {
        if (rowIds.isEmpty()) {
            return;
        }

        try (Connection connection = dataSource.getConnection()) {
            DeleteBuilder deleteBuilder = new DeleteBuilder(SqlFactory.deriveDialect(connection));
            deleteBuilder.from("XSK_IMPORTED_CSV_RECORDS").where(String.format("CSV_RECORD_ID IN (%s) AND TABLE_NAME = \'%s\'", String.join(",", rowIds), tableName));
            PreparedStatement statement = connection.prepareStatement(deleteBuilder.build());

            statement.executeUpdate();
            logger.info(format("Entities with ids: %s were DELETED successfully in %s.", String.join(", ", rowIds), "XSK_IMPORTED_CSV_RECORDS"));
        }
    }


    public void deleteAll(List<XSKImportedCSVRecordModel> importedCSVRecordModels) throws SQLException {
        if (importedCSVRecordModels.isEmpty()) {
            return;
        }

        try (Connection connection = dataSource.getConnection()) {
            importedCSVRecordModels.forEach(record -> {
                persistenceManager.delete(connection, XSKImportedCSVRecordModel.class, record.getId());
            });

            List<String> ids = importedCSVRecordModels.stream().map(record -> record.getId().toString()).collect(Collectors.toList());
            logger.info(format("Entities with ids: %s were DELETED successfully in %s.", String.join(", ", ids), "XSK_IMPORTED_CSV_RECORDS"));
        }

    }

    public Map<String, XSKImportedCSVRecordModel> getImportedCSVRecordsByTableAndCSVLocation(String tableName, String csvLocation) throws XSKDataStructuresException {
        try (Connection connection = dataSource.getConnection()) {
            String sql = SqlFactory.getNative(connection).select().column("*").from("XSK_IMPORTED_CSV_RECORDS")
                    .where("TABLE_NAME = ? AND CSV_LOCATION = ?").toString();
            List<XSKImportedCSVRecordModel> allRowsImportedByHdbtiImportConfig = persistenceManager.query(connection, XSKImportedCSVRecordModel.class, sql, tableName, csvLocation);
            return allRowsImportedByHdbtiImportConfig.stream().collect(Collectors.toMap(XSKImportedCSVRecordModel::getRowId, importModel -> importModel));
        } catch (SQLException e) {
            throw new XSKDataStructuresException(e);
        }
    }

    public List<XSKImportedCSVRecordModel> getImportedCSVsByHdbtiLocation(String hdbtiLocation) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = SqlFactory.getNative(connection).select().column("*").from("XSK_IMPORTED_CSV_RECORDS")
                    .where("HDBTI_LOCATION = ?").toString();
            return persistenceManager.query(connection, XSKImportedCSVRecordModel.class, sql, hdbtiLocation);
        } catch (SQLException e) {
            logger.error("Error occurred while trying to retrieve imported csv records by HDBTI file location");
        }

        return new ArrayList<>();
    }
}
