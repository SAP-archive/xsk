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
package com.sap.xsk.hdbti.processors;

import static java.text.MessageFormat.format;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.dirigible.commons.api.helpers.DateTimeUtils;
import org.eclipse.dirigible.database.ds.model.transfer.TableColumn;
import org.eclipse.dirigible.database.ds.model.transfer.TableMetadataHelper;
import org.eclipse.dirigible.database.persistence.PersistenceException;
import org.eclipse.dirigible.database.sql.SqlFactory;
import org.eclipse.dirigible.database.sql.builders.records.DeleteBuilder;
import org.eclipse.dirigible.database.sql.builders.records.InsertBuilder;
import org.eclipse.dirigible.repository.api.IRepository;
import org.eclipse.dirigible.repository.api.IResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.xsk.hdbti.model.XSKTableImportConfigurationDefinition;
import com.sap.xsk.hdbti.service.XSKCsvToHdbtiRelationService;
import com.sap.xsk.hdbti.service.XSKTableImportCoreService;

@Singleton
public class XSKTableImportProcessor implements IXSKTableImportProcessor{

    private static final Logger logger = LoggerFactory.getLogger(XSKTableImportProcessor.class);

    @Inject
    private IRepository repository;

    @Inject
    private XSKTableImportCoreService xskTableImportCoreService;

    @Inject
    private XSKCsvToHdbtiRelationService xskCsvToHdbtiRelationService;

    @Override
    public void process(XSKTableImportConfigurationDefinition tableImportConfigurationDefinition, Connection connection) throws IOException, SQLException {
        IResource resource = repository.getResource(xskCsvToHdbtiRelationService.convertToActualFileName(tableImportConfigurationDefinition.getFile()));
        String contentAsString = IOUtils
                .toString(new InputStreamReader(new ByteArrayInputStream(resource.getContent()), StandardCharsets.UTF_8));
        CSVFormat csvFormat = createCSVFormat(tableImportConfigurationDefinition);
        CSVParser csvParser = CSVParser.parse(contentAsString, csvFormat);
        deleteFromTable(tableImportConfigurationDefinition, connection);
        for (CSVRecord csvRecord : csvParser) {
            insertTableData(tableImportConfigurationDefinition, csvRecord, connection);
        }
    }

    private CSVFormat createCSVFormat(XSKTableImportConfigurationDefinition tableImportConfigurationDefinition) {
        char delimiter = Objects.isNull(tableImportConfigurationDefinition.getDelimField()) ? ',' : tableImportConfigurationDefinition.getDelimField().charAt(0);
        return CSVFormat.newFormat(delimiter);
    }


    private void deleteFromTable(XSKTableImportConfigurationDefinition tableImportConfigurationDefinition, Connection connection) {
        DeleteBuilder deleteBuilder = new DeleteBuilder(SqlFactory.deriveDialect(connection));
        String tableName = xskTableImportCoreService.convertToActualTableName(tableImportConfigurationDefinition.getTable());
        String sql = deleteBuilder.from(tableName).generate();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.execute();
        } catch (SQLException e) {
            logger.error("Error occurred while clearing the table pointed by HDBTI file before importing new values from csv", e);
        }
    }

    private void insertTableData(XSKTableImportConfigurationDefinition tableImportConfigurationDefinition, CSVRecord csvRecord, Connection connection) throws SQLException {
        String tableName = xskTableImportCoreService.convertToActualTableName(tableImportConfigurationDefinition.getTable());
        List<TableColumn> availableTableColumns = TableMetadataHelper.getColumns(connection, tableName);
		
		for (TableColumn tableColumn : availableTableColumns) {
			logger.debug("    {}: {}", tableColumn.getName(), tableColumn.getType());
		}
		
        InsertBuilder insertBuilder = new InsertBuilder(SqlFactory.deriveDialect(connection));
        insertBuilder.into(tableName);
        for (int i = 0; i < csvRecord.size(); i++) {
            String columnName = availableTableColumns.get(i).getName();
			insertBuilder.column("\"" + columnName + "\"").value("?");
        }
        PreparedStatement statement = connection.prepareStatement(insertBuilder.generate());
        for (int i = 0; i < csvRecord.size(); i++) {
            String value = csvRecord.get(i);
            int columnType = availableTableColumns.get(i).getType();
            try {
				if (!StringUtils.isEmpty(value)) {
					setValue(statement, i+1, columnType, value);
				} else {
					if (tableImportConfigurationDefinition.getDistinguishEmptyFromNull()) {
				        setValue(statement, i+1, columnType, "");
				    } else {
				    	setValue(statement, i+1, columnType, value);
				    }
				}
			} catch (Exception e) {
				logger.error(csvRecord.toString());
				logger.error(format("Error occured while processing {0} for table {1} at record {2}",
						tableImportConfigurationDefinition.getFile(), tableImportConfigurationDefinition.getTable(),
						csvRecord.getRecordNumber()), e);
			}
        }
        
        try {
            statement.execute();
        } catch (SQLException e) {
        	logger.error(csvRecord.toString());
            logger.error("Error occured while inserting the csv values in the table pointed by HDBTI file", e);
            logger.error(format("Error occured while processing {0} for table {1} at record {2}",
					tableImportConfigurationDefinition.getFile(), tableImportConfigurationDefinition.getTable(),
					csvRecord.getRecordNumber()), e);
        } finally {
        	statement.close();
        }
    }
    
    /**
	 * Sets the value.
	 *
	 * @param preparedStatement
	 *            the prepared statement
	 * @param i
	 *            the i
	 * @param dataType
	 *            the data type
	 * @param value
	 *            the value
	 * @throws SQLException
	 *             the SQL exception
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
		}

		else {
			throw new PersistenceException(format("Database type [{0}] not supported", dataType));
		}
	}
	
	private static String sanitize(String value) {
		if (value != null && value.startsWith("\"") && value.endsWith("\"")) {
			value = value.substring(1, value.length() - 1);
		}
		if (value != null && value.startsWith("'") && value.endsWith("'")) {
			value = value.substring(1, value.length() - 1);
		}
		return value.trim();
	}
	
	private String numberize(String value) {
		if (StringUtils.isEmpty(value)) {
			value = "0";
		}
		return value;
	}
}
