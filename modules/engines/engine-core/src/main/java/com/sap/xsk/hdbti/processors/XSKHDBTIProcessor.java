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

import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdbti.dao.XSKCSVRecordDao;
import com.sap.xsk.hdbti.dao.XSKCsvToHdbtiRelationDao;
import com.sap.xsk.hdbti.dao.XSKImportedCSVRecordDao;
import com.sap.xsk.hdbti.model.XSKImportedCSVRecordModel;
import com.sap.xsk.hdbti.model.XSKTableImportConfigurationDefinition;
import com.sap.xsk.hdbti.service.XSKTableImportParser;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.IOUtils;
import org.apache.cxf.common.util.StringUtils;
import org.eclipse.dirigible.database.persistence.model.PersistenceTableModel;
import org.eclipse.dirigible.engine.odata2.transformers.DBMetadataUtil;
import org.eclipse.dirigible.repository.api.IRepository;
import org.eclipse.dirigible.repository.api.IResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Singleton
public class XSKHDBTIProcessor implements IXSKHDBTIProcessor {

    private static final Logger logger = LoggerFactory.getLogger(XSKHDBTIProcessor.class);
    @Inject
    private DBMetadataUtil dbMetadataUtil;

    @Inject
    private IRepository repository;

    @Inject
    private XSKTableImportParser xskTableImportParser;

    @Inject
    private XSKCsvToHdbtiRelationDao xskCsvToHdbtiRelationDao;

    @Inject
    private XSKImportedCSVRecordDao xskImportedCSVRecordDao;

    @Inject
    private XSKCSVRecordDao xskcsvRecordDao;

    @Override
    public void process(XSKTableImportConfigurationDefinition tableImportConfigurationDefinition, Connection connection) throws XSKDataStructuresException {
        IResource resource = repository.getResource(xskCsvToHdbtiRelationDao.convertToActualFileName(tableImportConfigurationDefinition.getFile()));
        String tableName = xskTableImportParser.convertToActualTableName(tableImportConfigurationDefinition.getTable());
        CSVParser csvParser = getCsvParser(tableImportConfigurationDefinition, resource);
        PersistenceTableModel tableMetadata = getTableMetadata(tableImportConfigurationDefinition);
        if (tableMetadata == null || csvParser == null) {
            return;
        }

        Map<String, XSKImportedCSVRecordModel> importedCSVRecordsWithPk = xskImportedCSVRecordDao.getImportedCSVRecordsByTableAndCSVLocation(tableName, tableImportConfigurationDefinition.getFile());

        List<XSKImportedCSVRecordModel> importedCSVRecordsToUpdate = new ArrayList<>();
        List<CSVRecord> recordsToInsert = new ArrayList<>();
        List<CSVRecord> recordsToUpdate = new ArrayList<>();

        for (CSVRecord csvRecord : csvParser) {
            if (csvRecord.size() != tableMetadata.getColumns().size()) {
                //TODO: Throw proper exception
                return;
            }
            String pkForCSVRecord = getPkForCSVRecord(csvRecord, tableMetadata);
            String csvRecordHash = getCSVRecordHash(csvRecord);
            if (pkForCSVRecord != null && !importedCSVRecordsWithPk.containsKey(pkForCSVRecord)) {
                recordsToInsert.add(csvRecord);
            } else if (pkForCSVRecord != null && !importedCSVRecordsWithPk.get(pkForCSVRecord).getHash().equals(csvRecordHash)) {
                recordsToUpdate.add(csvRecord);
                XSKImportedCSVRecordModel importedCSVRecordModelToUpdate = importedCSVRecordsWithPk.get(pkForCSVRecord);
                importedCSVRecordModelToUpdate.setHash(csvRecordHash);
                importedCSVRecordsToUpdate.add(importedCSVRecordModelToUpdate);

                importedCSVRecordsWithPk.remove(pkForCSVRecord);
            } else if (pkForCSVRecord != null) {
                importedCSVRecordsWithPk.remove(pkForCSVRecord);
            }
        }

        insertCsvRecords(tableImportConfigurationDefinition, tableName, tableMetadata, recordsToInsert);

        for (CSVRecord csvRecord : recordsToUpdate) {
            try {
                xskcsvRecordDao.update(csvRecord, tableName, getPkForCSVRecord(csvRecord, tableMetadata), tableImportConfigurationDefinition.getDistinguishEmptyFromNull());
                for (XSKImportedCSVRecordModel importedCSVRecordModel : importedCSVRecordsToUpdate) {
                    xskImportedCSVRecordDao.update(importedCSVRecordModel);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        List<String> importedIdsToRemove = importedCSVRecordsWithPk.values().stream().map(XSKImportedCSVRecordModel::getId).map(Object::toString).collect(Collectors.toList());
        List<String> idsToRemove = importedCSVRecordsWithPk.values().stream().map(csv -> csv.getRowId()).collect(Collectors.toList());
        try {
            xskcsvRecordDao.deleteAll(idsToRemove, tableName);
            xskImportedCSVRecordDao.deleteAll(importedIdsToRemove, tableName);
        } catch (SQLException sqlException) {
            logger.error(String.format("An error occurred while trying to delete removed CSVs with ids: %s from table %s", String.join(",", importedIdsToRemove)));
        }


    }

    private void insertCsvRecords(XSKTableImportConfigurationDefinition tableImportConfigurationDefinition, String tableName, PersistenceTableModel tableMetadata, List<CSVRecord> recordsToInsert) {
        for (CSVRecord csvRecord : recordsToInsert) {
            try {
                xskcsvRecordDao.save(csvRecord, tableName, tableImportConfigurationDefinition.getDistinguishEmptyFromNull());

                XSKImportedCSVRecordModel importedCSVRecordModel = new XSKImportedCSVRecordModel();
                importedCSVRecordModel.setCsvLocation(tableImportConfigurationDefinition.getFile());
                importedCSVRecordModel.setHash(getCSVRecordHash(csvRecord));
                importedCSVRecordModel.setHdbtiLocation(tableImportConfigurationDefinition.getHdbtiFileName());
                importedCSVRecordModel.setRowId(getPkForCSVRecord(csvRecord, tableMetadata));
                importedCSVRecordModel.setTableName(tableName);
                xskImportedCSVRecordDao.save(importedCSVRecordModel);
            } catch (SQLException e) {
                logger.error(csvRecord.toString());
                logger.error("Error occurred while inserting the csv values in the table pointed by HDBTI file", e);
                logger.error(format("Error occurred while processing %s for table %s at record %d",
                        tableImportConfigurationDefinition.getFile(), tableImportConfigurationDefinition.getTable(),
                        csvRecord.getRecordNumber()), e);
            } catch (Exception e) {
                logger.error(csvRecord.toString());
                logger.error(format("Error occurred while processing %s for table %s at record %d",
                        tableImportConfigurationDefinition.getFile(), tableImportConfigurationDefinition.getTable(),
                        csvRecord.getRecordNumber()), e);
            }
        }
    }

    private String getPkForCSVRecord(CSVRecord csvRecord, PersistenceTableModel tableMetadata) {
        for (int i = 0; i < csvRecord.size(); i++) {
            boolean isColumnPk = tableMetadata.getColumns().get(i).isPrimaryKey();
            if (isColumnPk && !StringUtils.isEmpty(csvRecord.get(i))) {
                return csvRecord.get(i);
            }
        }

        return null;
    }

    private PersistenceTableModel getTableMetadata(XSKTableImportConfigurationDefinition tableImportConfigurationDefinition) {
        String tableName = xskTableImportParser.convertToActualTableName(tableImportConfigurationDefinition.getTable());
        try {
            return dbMetadataUtil.getTableMetadata(tableName);
        } catch (SQLException sqlException) {
            logger.error(String.format("Error occurred while trying to read table metadata for table with name: %s", tableName), sqlException);
        }

        return null;
    }

    private CSVParser getCsvParser(XSKTableImportConfigurationDefinition tableImportConfigurationDefinition, IResource resource) {
        try {
            String contentAsString = IOUtils
                    .toString(new InputStreamReader(new ByteArrayInputStream(resource.getContent()), StandardCharsets.UTF_8));
            CSVFormat csvFormat = createCSVFormat(tableImportConfigurationDefinition);
            return CSVParser.parse(contentAsString, csvFormat);
        } catch (IOException e) {
            logger.error(String.format("Error occurred while trying to parse csv imported from hdbti file: %s", tableImportConfigurationDefinition.getHdbtiFileName()), e);
        }

        return null;
    }

    private CSVFormat createCSVFormat(XSKTableImportConfigurationDefinition tableImportConfigurationDefinition) {
        char delimiter = Objects.isNull(tableImportConfigurationDefinition.getDelimField()) ? ',' : tableImportConfigurationDefinition.getDelimField().charAt(0);
        return CSVFormat.newFormat(delimiter).withSkipHeaderRecord(tableImportConfigurationDefinition.getHeader()).withHeader();
    }


    private String getCSVRecordHash(CSVRecord csvRecord) {
        StringBuilder joinedValues = new StringBuilder();

        for (int i = 0; i < csvRecord.size(); i++) {
            joinedValues.append(csvRecord.get(i));
            if (i != csvRecord.size() - 1) {
                joinedValues.append(",");
            }
        }

        return DigestUtils.md5Hex(joinedValues.toString());
    }
}
