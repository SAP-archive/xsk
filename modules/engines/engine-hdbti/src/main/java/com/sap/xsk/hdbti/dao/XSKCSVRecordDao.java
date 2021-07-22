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
package com.sap.xsk.hdbti.dao;

import com.sap.xsk.hdbti.api.IXSKCSVRecordDao;
import com.sap.xsk.hdbti.utils.XSKCsvRecordMetadata;
import com.sap.xsk.utils.XSKCommonsDBUtils;
import org.apache.commons.csv.CSVRecord;
import org.apache.cxf.common.util.StringUtils;
import org.eclipse.dirigible.commons.api.helpers.DateTimeUtils;
import org.eclipse.dirigible.database.ds.model.transfer.TableColumn;
import org.eclipse.dirigible.database.ds.model.transfer.TableMetadataHelper;
import org.eclipse.dirigible.database.persistence.PersistenceException;
import org.eclipse.dirigible.database.sql.SqlFactory;
import org.eclipse.dirigible.database.sql.builders.records.DeleteBuilder;
import org.eclipse.dirigible.database.sql.builders.records.InsertBuilder;
import org.eclipse.dirigible.database.sql.builders.records.UpdateBuilder;
import org.eclipse.dirigible.engine.odata2.transformers.DBMetadataUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.*;
import java.util.List;

import static java.text.MessageFormat.format;

public class XSKCSVRecordDao implements IXSKCSVRecordDao {

    private static final Logger logger = LoggerFactory.getLogger(XSKCSVRecordDao.class);

    @Inject
    private DataSource dataSource;

    @Inject
    private DBMetadataUtil dbMetadataUtil;

    @Override
    public void save(XSKCsvRecordMetadata csvRecordMetadata) throws SQLException {
        String tableName = csvRecordMetadata.getTableMetadataModel().getTableName();
        try (Connection connection = dataSource.getConnection()) {
            List<TableColumn> availableTableColumns = TableMetadataHelper.getColumns(connection, tableName, csvRecordMetadata.getTableMetadataModel().getSchemaName());
            for (TableColumn availableTableColumn : availableTableColumns) {
                logger.debug("    {}: {}", availableTableColumn.getName(), availableTableColumn.getType());
            }

            InsertBuilder insertBuilder = new InsertBuilder(SqlFactory.deriveDialect(connection));
            insertBuilder.into(tableName);
            CSVRecord csvRecord = csvRecordMetadata.getCsvRecord();
            for (int i = 0; i < csvRecord.size(); i++) {
                String columnName = availableTableColumns.get(i).getName();
                insertBuilder.column("\"" + columnName + "\"").value("?");
            }

            try (PreparedStatement statement = connection.prepareStatement(insertBuilder.generate())) {
                executeInsertPreparedStatement(csvRecordMetadata, availableTableColumns, statement);
                logger.info(format("Table row with id: %s was CREATED successfully in %s.", csvRecord.get(0), tableName));
            }
        }
    }

    @Override
    public void update(XSKCsvRecordMetadata csvRecordMetadata) throws SQLException {
        String tableName = csvRecordMetadata.getTableMetadataModel().getTableName();
        try (Connection connection = dataSource.getConnection()) {
            List<TableColumn> availableTableColumns = TableMetadataHelper.getColumns(connection, tableName, csvRecordMetadata.getTableMetadataModel().getSchemaName());
            UpdateBuilder updateBuilder = new UpdateBuilder(SqlFactory.deriveDialect(connection));
            updateBuilder.table(tableName);
            for (TableColumn tableColumn : availableTableColumns) {
                logger.debug("    {}: {}", tableColumn.getName(), tableColumn.getType());
            }

            CSVRecord csvRecord = csvRecordMetadata.getCsvRecord();
            for (int i = 0; i < csvRecord.size(); i++) {
                String columnName = availableTableColumns.get(i).getName();
                if (columnName.equals(csvRecordMetadata.getPkColumnName())) {
                    continue;
                }

                updateBuilder.set("\"" + columnName + "\"", "?");
            }

            if (csvRecordMetadata.getHeaderNames().size() > 0) {
                updateBuilder.where(String.format("%s = ?", csvRecordMetadata.getPkColumnName()));
            } else {
                updateBuilder.where(String.format("%s = ?", availableTableColumns.get(0).getName()));
            }

            try (PreparedStatement statement = connection.prepareStatement(updateBuilder.generate())) {
                executeUpdatePreparedStatement(csvRecordMetadata, availableTableColumns, statement);
                logger.info(format("Table row with id: %s was UPDATED successfully in %s.", csvRecord.get(0), tableName));
            }
        }
    }

    @Override
    public void deleteAll(List<String> ids, String tableName) throws SQLException {
        if (ids.isEmpty()) {
            return;
        }

        try (Connection connection = dataSource.getConnection()) {
            String pkColumnName = dbMetadataUtil.getTableMetadata(tableName, XSKCommonsDBUtils.getTableSchema(dataSource, tableName)).getColumns().get(0).getName();
            DeleteBuilder deleteBuilder = new DeleteBuilder(SqlFactory.deriveDialect(connection));
            deleteBuilder.from(tableName).where(String.format("%s IN (%s)", pkColumnName, String.join(",", ids)));
            try (PreparedStatement statement = connection.prepareStatement(deleteBuilder.build())) {
                statement.executeUpdate();
                logger.info(String.format("Entities with Row Ids: %s from table: %s", String.join(", ", ids), tableName));
            }
        }
    }

    @Override
    public void delete(String id, String tableName) throws SQLException {
        if (StringUtils.isEmpty(id) || StringUtils.isEmpty(tableName)) {
            return;
        }

        try (Connection connection = dataSource.getConnection()) {
            String pkColumnName = dbMetadataUtil.getTableMetadata(tableName, XSKCommonsDBUtils.getTableSchema(dataSource, tableName)).getColumns().get(0).getName();
            DeleteBuilder deleteBuilder = new DeleteBuilder(SqlFactory.deriveDialect(connection));
            deleteBuilder.from(tableName).where(String.format("%s='%s'", pkColumnName, id));
            try (PreparedStatement statement = connection.prepareStatement(deleteBuilder.build())) {
                statement.executeUpdate();
                logger.info(String.format("Entity with Row Id: %s from table: %s", id, tableName));
            }
        }
    }

    @Override
    public DataSource getDataSource() {
        return dataSource;
    }

    @Override
    public DBMetadataUtil getDbMetadataUtil() {
        return dbMetadataUtil;
    }

    private void executeInsertPreparedStatement(XSKCsvRecordMetadata csvRecordMetadata, List<TableColumn> tableColumns, PreparedStatement statement) throws SQLException {
        if (csvRecordMetadata.getHeaderNames().size() > 0) {
            insertCsvWithHeader(csvRecordMetadata, tableColumns, statement);
        } else {
            insertCsvWithoutHeader(csvRecordMetadata, tableColumns, statement);
        }

        statement.execute();
    }

    private void insertCsvWithHeader(XSKCsvRecordMetadata csvRecordMetadata, List<TableColumn> tableColumns, PreparedStatement statement) throws SQLException {

        for (int i = 0; i < tableColumns.size(); i++) {
            String columnName = tableColumns.get(i).getName();
            int columnType = tableColumns.get(i).getType();
            String value = csvRecordMetadata.getCsvValueForColumn(columnName);
            setPreparedStatementValue(csvRecordMetadata.isDistinguishEmptyFromNull(), statement, i + 1, value, columnType);
        }
    }

    private void insertCsvWithoutHeader(XSKCsvRecordMetadata csvRecordMetadata, List<TableColumn> tableColumns, PreparedStatement statement) throws SQLException {
        for (int i = 0; i < csvRecordMetadata.getCsvRecord().size(); i++) {
            String value = csvRecordMetadata.getCsvRecord().get(i);
            int columnType = tableColumns.get(i).getType();

            setPreparedStatementValue(csvRecordMetadata.isDistinguishEmptyFromNull(), statement, i + 1, value, columnType);
        }
    }

    private void executeUpdatePreparedStatement(XSKCsvRecordMetadata csvRecordMetadata, List<TableColumn> tableColumns, PreparedStatement statement) throws SQLException {
        if (csvRecordMetadata.getHeaderNames().size() > 0) {
            updateCsvWithHeader(csvRecordMetadata, tableColumns, statement);
        } else {
            updateCsvWithoutHeader(csvRecordMetadata, tableColumns, statement);
        }

        statement.execute();
    }

    private void updateCsvWithHeader(XSKCsvRecordMetadata csvRecordMetadata, List<TableColumn> tableColumns, PreparedStatement statement) throws SQLException {
        CSVRecord csvRecord = csvRecordMetadata.getCsvRecord();

        for (int i = 1; i < tableColumns.size(); i++) {
            String columnName = tableColumns.get(i).getName();
            String value = csvRecordMetadata.getCsvValueForColumn(columnName);

            int columnType = tableColumns.get(i).getType();

            setPreparedStatementValue(csvRecordMetadata.isDistinguishEmptyFromNull(), statement, i, value,
                    columnType);
        }

        int pkColumnType = tableColumns.get(0).getType();
        int lastStatementPlaceholderIndex = csvRecord.size();

        setValue(statement, lastStatementPlaceholderIndex, pkColumnType, csvRecordMetadata.getCsvRecordPkValue());
    }

    private void updateCsvWithoutHeader(XSKCsvRecordMetadata csvRecordMetadata, List<TableColumn> tableColumns, PreparedStatement statement) throws SQLException {
        CSVRecord csvRecord = csvRecordMetadata.getCsvRecord();
        for (int i = 1; i < csvRecord.size(); i++) {
            String value = csvRecord.get(i);
            int columnType = tableColumns.get(i).getType();
            setPreparedStatementValue(csvRecordMetadata.isDistinguishEmptyFromNull(), statement, i, value, columnType);
        }

        int pkColumnType = tableColumns.get(0).getType();
        int lastStatementPlaceholderIndex = csvRecord.size();
        setValue(statement, lastStatementPlaceholderIndex, pkColumnType, csvRecord.get(0));
    }

    private void setPreparedStatementValue(Boolean distinguishEmptyFromNull, PreparedStatement statement, int i, String value, int columnType)
            throws SQLException {
        if (!StringUtils.isEmpty(value)) {
            setValue(statement, i, columnType, value);
        } else {
            if (distinguishEmptyFromNull) {
                setValue(statement, i, columnType, "");
            } else {
                setValue(statement, i, columnType, value);
            }
        }
    }

    /**
     * Sets the value.
     *
     * @param preparedStatement the prepared statement
     * @param i                 the i
     * @param dataType          the data type
     * @param value             the value
     * @throws SQLException the SQL exception
     */
    protected void setValue(PreparedStatement preparedStatement, int i, int dataType, String value)
            throws SQLException {
        logger.trace("setValue -> i: " + i + ", dataType: " + dataType + ", value: " + value);

        if (value == null) {
            preparedStatement.setNull(i, dataType);
        } else if (Types.VARCHAR == dataType) {
            preparedStatement.setString(i, sanitize(value));
        } else if (Types.NVARCHAR == dataType) {
            preparedStatement.setString(i, sanitize(value));
        } else if (Types.CHAR == dataType) {
            preparedStatement.setString(i, sanitize(value));
        } else if (Types.DATE == dataType) {
            preparedStatement.setDate(i, DateTimeUtils.parseDate(value));
        } else if (Types.TIME == dataType) {
            preparedStatement.setTime(i, DateTimeUtils.parseTime(value));
        } else if (Types.TIMESTAMP == dataType) {
            preparedStatement.setTimestamp(i, DateTimeUtils.parseDateTime(value));
        } else if (Types.INTEGER == dataType) {
            value = numberize(value);
            preparedStatement.setInt(i, Integer.parseInt(value));
        } else if (Types.TINYINT == dataType) {
            value = numberize(value);
            preparedStatement.setByte(i, Byte.parseByte(value));
        } else if (Types.SMALLINT == dataType) {
            value = numberize(value);
            preparedStatement.setShort(i, Short.parseShort(value));
        } else if (Types.BIGINT == dataType) {
            value = numberize(value);
            preparedStatement.setLong(i, new BigInteger(value).longValueExact());
        } else if (Types.REAL == dataType) {
            value = numberize(value);
            preparedStatement.setFloat(i, Float.parseFloat(value));
        } else if (Types.DOUBLE == dataType) {
            value = numberize(value);
            preparedStatement.setDouble(i, Double.parseDouble(value));
        } else if (Types.BOOLEAN == dataType) {
            preparedStatement.setBoolean(i, Boolean.parseBoolean(value));
        } else if (Types.DECIMAL == dataType) {
            value = numberize(value);
            preparedStatement.setBigDecimal(i, new BigDecimal(value));
        } else if (Types.NCLOB == dataType) {
            preparedStatement.setString(i, sanitize(value));
        } else {
            throw new PersistenceException(format("Database type [{0}] not supported", JDBCType.valueOf(dataType).getName()));
        }
    }

    private String sanitize(String value) {
        if (value != null && value.startsWith("\"") && value.endsWith("\"")) {
            value = value.substring(1, value.length() - 1);
        }
        if (value != null && value.startsWith("'") && value.endsWith("'")) {
            value = value.substring(1, value.length() - 1);
        }
        return value != null ? value.trim() : null;
    }

    private String numberize(String value) {
        if (StringUtils.isEmpty(value)) {
            value = "0";
        }
        return value;
    }
}
