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
package com.sap.xsk.hdbti.api;

import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdbti.model.XSKImportedCSVRecordModel;
import com.sap.xsk.hdbti.model.XSKTableImportArtifact;
import com.sap.xsk.hdbti.model.XSKTableImportConfigurationDefinition;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.apache.commons.csv.CSVRecord;

public interface IXSKHDBTICoreService {

  void insertCsvRecords(List<CSVRecord> recordsToInsert, List<String> headerNames,
      XSKTableImportConfigurationDefinition tableImportConfigurationDefinition)
      throws SQLException;

  Map<String, XSKImportedCSVRecordModel> getImportedCSVRecordsByTableAndCSVLocation(String tableName, String csvLocation)
      throws XSKDataStructuresException;

  void cleanUpHdbtiRelatedData() throws XSKTableImportException;

  void updateCsvRecords(List<CSVRecord> csvRecords, List<String> headerNames,
      List<XSKImportedCSVRecordModel> importedCsvRecordsToUpdate,
      XSKTableImportConfigurationDefinition tableImportConfigurationDefinition) throws SQLException;

  void removeCsvRecords(List<XSKImportedCSVRecordModel> importedCSVRecordModels, String tableName);

  void refreshCsvRelations(XSKTableImportArtifact tableImportArtifact);

  String getPkForCSVRecord(CSVRecord csvRecord, String tableName, List<String> headerNames);

  String getCSVRecordHash(CSVRecord csvRecord);

  String convertToActualTableName(String tableName);

  String convertToActualFileName(String fileNamePath);
}
