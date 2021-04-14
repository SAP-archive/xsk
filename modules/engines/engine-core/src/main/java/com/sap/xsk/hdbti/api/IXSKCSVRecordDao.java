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

import org.apache.commons.csv.CSVRecord;
import org.eclipse.dirigible.engine.odata2.transformers.DBMetadataUtil;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

public interface IXSKCSVRecordDao {
    void save(CSVRecord csvRecord, String tableName, Boolean distinguishEmptyFromNull) throws SQLException;

    void update(CSVRecord csvRecord, String tableName, String pkValue, Boolean distinguishEmptyFromNull) throws SQLException;

    void deleteAll(List<String> ids, String tableName) throws SQLException;

    void delete(String id, String tableName) throws SQLException;

    DataSource getDataSource();

    DBMetadataUtil getDbMetadataUtil();
}
