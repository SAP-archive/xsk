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
package com.sap.xsk.hdbti.processors;

import com.ibm.icu.impl.IllegalIcuArgumentException;
import com.sap.xsk.exceptions.XSKArtifactParserException;
import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdbti.api.IXSKHDBTICoreService;
import com.sap.xsk.hdbti.api.IXSKHDBTIProcessor;
import com.sap.xsk.hdbti.api.XSKTableImportException;
import com.sap.xsk.hdbti.model.XSKImportedCSVRecordModel;
import com.sap.xsk.hdbti.model.XSKTableImportConfigurationDefinition;
import com.sap.xsk.hdbti.service.XSKHDBTICoreService;
import com.sap.xsk.hdbti.utils.XSKHDBTIUtils;
import com.sap.xsk.parser.hdbti.custom.XSKHDBTIParser;
import com.sap.xsk.parser.hdbti.exception.XSKHDBTISyntaxErrorException;
import com.sap.xsk.parser.hdbti.models.XSKHDBTIImportConfigModel;
import com.sap.xsk.parser.hdbti.models.XSKHDBTIImportModel;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.IOUtils;
import org.eclipse.dirigible.commons.config.StaticObjects;
import org.eclipse.dirigible.core.problems.exceptions.ProblemsException;
import org.eclipse.dirigible.database.persistence.model.PersistenceTableModel;
import org.eclipse.dirigible.engine.odata2.transformers.DBMetadataUtil;
import org.eclipse.dirigible.repository.api.IRepository;
import org.eclipse.dirigible.repository.api.IResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

public class XSKHDBTIProcessor implements IXSKHDBTIProcessor {

    private static final Logger logger = LoggerFactory.getLogger(XSKHDBTIProcessor.class);

    private final DBMetadataUtil dbMetadataUtil = new DBMetadataUtil();

    private final IRepository repository = (IRepository) StaticObjects.get(StaticObjects.REPOSITORY);

    private final IXSKHDBTICoreService xskHdbtiCoreService = new XSKHDBTICoreService();

    private final XSKHDBTIParser xskhdbtiParser = new XSKHDBTIParser();

    @Override
    public void process(XSKTableImportConfigurationDefinition tableImportConfigurationDefinition, Connection connection)
            throws XSKDataStructuresException, XSKTableImportException, SQLException {
        IResource resource = repository.getResource(xskHdbtiCoreService.convertToActualFileName(tableImportConfigurationDefinition.getFile()));
        String tableName = xskHdbtiCoreService.convertToActualTableName(tableImportConfigurationDefinition.getTable());
        CSVParser csvParser = getCsvParser(tableImportConfigurationDefinition, resource);
        PersistenceTableModel tableMetadata = getTableMetadata(tableImportConfigurationDefinition);
        if (tableMetadata == null || csvParser == null) {
            return;
        }

        Map<String, XSKImportedCSVRecordModel> allImportedRecordsByCsv = xskHdbtiCoreService
                .getImportedCSVRecordsByTableAndCSVLocation(tableName, tableImportConfigurationDefinition.getFile());

        List<XSKImportedCSVRecordModel> importedCSVRecordsToUpdate = new ArrayList<>();
        List<CSVRecord> recordsToInsert = new ArrayList<>();
        List<CSVRecord> recordsToUpdate = new ArrayList<>();

        for (CSVRecord csvRecord : csvParser) {
            if (csvRecord.size() != tableMetadata.getColumns().size()) {
                throw new XSKTableImportException(
                        String.format("Error while trying to process csv with id %s with location %s."
                                        + "The number of csv records should be equal to the number of columns of a db entity",
                                xskHdbtiCoreService.getPkForCSVRecord(csvRecord, tableName, csvParser.getHeaderNames()),
                                tableImportConfigurationDefinition.getFile()));
            }

            String pkForCSVRecord = xskHdbtiCoreService.getPkForCSVRecord(csvRecord, tableName, csvParser.getHeaderNames());
            String csvRecordHash = xskHdbtiCoreService.getCSVRecordHash(csvRecord);
            if (pkForCSVRecord != null && !allImportedRecordsByCsv.containsKey(pkForCSVRecord)) {
                recordsToInsert.add(csvRecord);
            } else if (pkForCSVRecord != null && !allImportedRecordsByCsv.get(pkForCSVRecord).getHash().equals(csvRecordHash)) {
                recordsToUpdate.add(csvRecord);
                XSKImportedCSVRecordModel importedCSVRecordModelToUpdate = allImportedRecordsByCsv.get(pkForCSVRecord);
                importedCSVRecordModelToUpdate.setHash(csvRecordHash);
                importedCSVRecordsToUpdate.add(importedCSVRecordModelToUpdate);

                allImportedRecordsByCsv.remove(pkForCSVRecord);
            } else if (pkForCSVRecord != null) {
                allImportedRecordsByCsv.remove(pkForCSVRecord);
            }
        }

        xskHdbtiCoreService.insertCsvRecords(recordsToInsert, csvParser.getHeaderNames(), tableImportConfigurationDefinition);
        xskHdbtiCoreService.updateCsvRecords(recordsToUpdate, csvParser.getHeaderNames(), importedCSVRecordsToUpdate,
                tableImportConfigurationDefinition);
        xskHdbtiCoreService.removeCsvRecords(new ArrayList<>(allImportedRecordsByCsv.values()), tableName);
    }

    private PersistenceTableModel getTableMetadata(XSKTableImportConfigurationDefinition tableImportConfigurationDefinition) {
        String tableName = xskHdbtiCoreService.convertToActualTableName(tableImportConfigurationDefinition.getTable());
        try {
            return dbMetadataUtil.getTableMetadata(tableName, tableImportConfigurationDefinition.getSchema());
        } catch (SQLException sqlException) {
            logger.error(String.format("Error occurred while trying to read table metadata for table with name: %s", tableName), sqlException);
        }
        return null;
    }

    private CSVParser getCsvParser(XSKTableImportConfigurationDefinition tableImportConfigurationDefinition, IResource resource)
            throws XSKTableImportException {
        try {
            String contentAsString = IOUtils
                    .toString(new InputStreamReader(new ByteArrayInputStream(resource.getContent()), StandardCharsets.UTF_8));
            CSVFormat csvFormat = createCSVFormat(tableImportConfigurationDefinition);
            return CSVParser.parse(contentAsString, csvFormat);
        } catch (IOException e) {
            logger.error(String.format("Error occurred while trying to parse csv imported from hdbti file: %s",
                    tableImportConfigurationDefinition.getHdbtiFileName()), e);
        }

        return null;
    }

    private CSVFormat createCSVFormat(XSKTableImportConfigurationDefinition tableImportConfigurationDefinition)
            throws XSKTableImportException {
        if (tableImportConfigurationDefinition.getDelimField() != null && (!tableImportConfigurationDefinition.getDelimField().equals(",")
                && !tableImportConfigurationDefinition.getDelimField().equals(";"))) {
            throw new XSKTableImportException("Only ';' or ',' characters are supported as delimiters for csv files.");
        } else if (tableImportConfigurationDefinition.getDelimEnclosing() != null
                && tableImportConfigurationDefinition.getDelimEnclosing().length() > 1) {
            throw new XSKTableImportException("Delim enclosing should only contain one character.");
        }

        char delimiter = Objects.isNull(tableImportConfigurationDefinition.getDelimField()) ? ','
                : tableImportConfigurationDefinition.getDelimField().charAt(0);
        char quote = Objects.isNull(tableImportConfigurationDefinition.getDelimEnclosing()) ? '"'
                : tableImportConfigurationDefinition.getDelimEnclosing().charAt(0);
        CSVFormat csvFormat = CSVFormat.newFormat(delimiter).withQuote(quote).withEscape('\\');

        boolean useHeader = !Objects.isNull(tableImportConfigurationDefinition.getHeader()) && tableImportConfigurationDefinition.getHeader();
        if (useHeader) {
            csvFormat = csvFormat.withFirstRecordAsHeader();
        }

        return csvFormat;
    }

    public List<XSKHDBTIImportConfigModel> parseHdbtiToJSON(String location, byte[] file)
            throws XSKArtifactParserException, IOException, XSKHDBTISyntaxErrorException, ProblemsException {
        if (location == null) location = "undefined";
        XSKHDBTIImportModel parsedFile = xskhdbtiParser.parse(location, new String(file, StandardCharsets.UTF_8));
        parsedFile.getConfigModels().forEach(el -> el.setFileName(XSKHDBTIUtils.convertHDBTIFilePropertyToPath(el.getFileName())));
        return parsedFile.getConfigModels();
    }

    public String parseJSONtoHdbti(ArrayList<XSKHDBTIImportConfigModel> json) {
        json.forEach(el -> {
            el.setFileName(XSKHDBTIUtils.convertPathToHDBTIFileProperty(el.getFileName()));
            if(!XSKHDBTIUtils.isCorrectPropertySyntax(el.getSchemaName())) throw new IllegalIcuArgumentException("Schema property contains unsupported symbols: "+el.getSchemaName());
            if(!XSKHDBTIUtils.isCorrectPropertySyntax(el.getTableName())) throw new IllegalIcuArgumentException("Table property contains unsupported symbols: "+el.getTableName());
            el.getKeys().forEach(key ->{
                if(!XSKHDBTIUtils.isCorrectPropertySyntax(key.getColumn())) throw new IllegalIcuArgumentException("key column property contains unsupported symbols: "+key.getColumn());
            });
        });

        XSKHDBTIImportModel model = new XSKHDBTIImportModel();
        model.setConfigModels(json);
        return model.toString();
    }
}
