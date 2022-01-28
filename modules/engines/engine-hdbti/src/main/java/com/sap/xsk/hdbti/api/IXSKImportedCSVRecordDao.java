/*
 * Copyright (c) 2022 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2022 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.hdbti.api;

import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdbti.model.XSKImportedCSVRecordModel;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.eclipse.dirigible.database.persistence.PersistenceManager;

public interface IXSKImportedCSVRecordDao {
    XSKImportedCSVRecordModel save(XSKImportedCSVRecordModel importedRowModel) throws XSKDataStructuresException;

    XSKImportedCSVRecordModel update(XSKImportedCSVRecordModel importedRowModel) throws SQLException;

    void deleteAll(List<XSKImportedCSVRecordModel> importedCSVRecordModels) throws SQLException;

    List<XSKImportedCSVRecordModel> getImportedCSVRecordsByTableAndCSVLocation(String tableName, String csvLocation) throws XSKDataStructuresException;

    List<XSKImportedCSVRecordModel> getImportedCSVsByHdbtiLocation(String hdbtiLocation);

    DataSource getDataSource();

    PersistenceManager<XSKImportedCSVRecordModel> getPersistenceManager();
}
