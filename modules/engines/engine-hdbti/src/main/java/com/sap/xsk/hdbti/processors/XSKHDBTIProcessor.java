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
import com.sap.xsk.parser.hdbti.models.XSKHDBTIImportConfigModel.Pair;
import com.sap.xsk.parser.hdbti.models.XSKHDBTIImportModel;
import com.sap.xsk.utils.XSKCommonsConstants;
import com.sap.xsk.utils.XSKCommonsUtils;
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
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.IOUtils;
import org.eclipse.dirigible.commons.config.StaticObjects;
import org.eclipse.dirigible.database.persistence.model.PersistenceTableModel;
import org.eclipse.dirigible.engine.odata2.transformers.DBMetadataUtil;
import org.eclipse.dirigible.repository.api.IRepository;
import org.eclipse.dirigible.repository.api.IResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        String errorMessage = String.format("Error while trying to process csv with id %s with location %s."
                + "The number of csv records should be equal to the number of columns of a db entity",
            xskHdbtiCoreService.getPkForCSVRecord(csvRecord, tableName, csvParser.getHeaderNames()),
            tableImportConfigurationDefinition.getFile());
        XSKCommonsUtils.logProcessorErrors(errorMessage, XSKCommonsConstants.PROCESSOR_ERROR,
            tableImportConfigurationDefinition.getHdbtiFileName(), XSKCommonsConstants.HDBTI_PARSER);
        throw new XSKTableImportException(errorMessage);
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
      String errorMessage = String.format("Error occurred while trying to read table metadata for table with name: %s", tableName);
      XSKCommonsUtils.logProcessorErrors(errorMessage, XSKCommonsConstants.PROCESSOR_ERROR,
          tableImportConfigurationDefinition.getHdbtiFileName(), XSKCommonsConstants.HDBTI_PARSER);
      logger.error(errorMessage, sqlException);
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
      String errorMessage = String.format("Error occurred while trying to parse csv imported from hdbti file: %s",
          tableImportConfigurationDefinition.getHdbtiFileName());
      XSKCommonsUtils.logProcessorErrors(errorMessage, XSKCommonsConstants.PROCESSOR_ERROR,
          tableImportConfigurationDefinition.getHdbtiFileName(), XSKCommonsConstants.HDBTI_PARSER);
      logger.error(errorMessage, e);
    }

    return null;
  }

  private CSVFormat createCSVFormat(XSKTableImportConfigurationDefinition tableImportConfigurationDefinition)
      throws XSKTableImportException {
    if (tableImportConfigurationDefinition.getDelimField() != null && (!tableImportConfigurationDefinition.getDelimField().equals(",")
        && !tableImportConfigurationDefinition.getDelimField().equals(";"))) {
      String errorMessage = "Only ';' or ',' characters are supported as delimiters for csv files.";
      XSKCommonsUtils.logProcessorErrors(errorMessage, XSKCommonsConstants.PROCESSOR_ERROR,
          tableImportConfigurationDefinition.getHdbtiFileName(), XSKCommonsConstants.HDBTI_PARSER);
      throw new XSKTableImportException(errorMessage);
    } else if (tableImportConfigurationDefinition.getDelimEnclosing() != null
        && tableImportConfigurationDefinition.getDelimEnclosing().length() > 1) {
      String errorMessage = "Delim enclosing should only contain one character.";
      XSKCommonsUtils.logProcessorErrors(errorMessage, XSKCommonsConstants.PROCESSOR_ERROR,
          tableImportConfigurationDefinition.getHdbtiFileName(), XSKCommonsConstants.HDBTI_PARSER);
      throw new XSKTableImportException(errorMessage);
    }

    char delimiter = Objects.isNull(tableImportConfigurationDefinition.getDelimField()) ? ','
        : tableImportConfigurationDefinition.getDelimField().charAt(0);
    char quote = Objects.isNull(tableImportConfigurationDefinition.getDelimEnclosing()) ? '"'
        : tableImportConfigurationDefinition.getDelimEnclosing().charAt(0);
    CSVFormat csvFormat = CSVFormat.newFormat(delimiter).withIgnoreEmptyLines().withQuote(quote).withEscape('\\');

    boolean useHeader = !Objects.isNull(tableImportConfigurationDefinition.getHeader()) && tableImportConfigurationDefinition.getHeader();
    if (useHeader) {
      csvFormat = csvFormat.withFirstRecordAsHeader();
    }

    return csvFormat;
  }

  public List<XSKHDBTIImportConfigModel> parseHdbtiToJSON(String location, byte[] file)
      throws XSKArtifactParserException, IOException, XSKHDBTISyntaxErrorException {
    if (location == null) {
      location = "undefined";
    }
    XSKHDBTIImportModel parsedFile = xskhdbtiParser.parse(location, new String(file, StandardCharsets.UTF_8));
    parsedFile.getConfigModels().forEach(el -> el.setFileName(XSKHDBTIUtils.convertHDBTIFilePropertyToPath(el.getFileName())));
    return parsedFile.getConfigModels();
  }

  public String parseJSONtoHdbti(ArrayList<XSKHDBTIImportConfigModel> json) {
    for (XSKHDBTIImportConfigModel el : json) {
      try {
        el.setFileName(XSKHDBTIUtils.convertPathToHDBTIFileProperty(el.getFileName()));
      } catch (IllegalArgumentException ex) {
        XSKCommonsUtils.logProcessorErrors(ex.getMessage(), XSKCommonsConstants.PROCESSOR_ERROR, el.getFileName(),
            XSKCommonsConstants.HDBTI_PARSER);
        throw ex;
      }
      if (!XSKHDBTIUtils.isCorrectPropertySyntax(el.getSchemaName())) {
        String errMsg = "Schema property contains unsupported symbols: " + el.getSchemaName();
        XSKCommonsUtils.logProcessorErrors(errMsg, XSKCommonsConstants.PROCESSOR_ERROR, el.getFileName(), XSKCommonsConstants.HDBTI_PARSER);
        throw new IllegalIcuArgumentException(errMsg);
      }
      if (!XSKHDBTIUtils.isCorrectTablePropertySyntax(el.getTableName())) {
        String errMsg = "Table property contains unsupported symbols: " + el.getTableName();
        XSKCommonsUtils.logProcessorErrors(errMsg, XSKCommonsConstants.PROCESSOR_ERROR, el.getFileName(), XSKCommonsConstants.HDBTI_PARSER);
        throw new IllegalIcuArgumentException(errMsg);
      }
      for (Pair key : el.getKeys()) {
        if (!XSKHDBTIUtils.isCorrectPropertySyntax(key.getColumn())) {
          String errMsg = "key column property contains unsupported symbols: " + key.getColumn();
          XSKCommonsUtils.logProcessorErrors(errMsg, XSKCommonsConstants.PROCESSOR_ERROR, el.getFileName(),
              XSKCommonsConstants.HDBTI_PARSER);
          throw new IllegalIcuArgumentException(errMsg);
        }
      }
    }

    XSKHDBTIImportModel model = new XSKHDBTIImportModel();
    model.setConfigModels(json);
    return model.toString();
  }
}
