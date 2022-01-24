/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company and XSK contributors
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

import com.sap.xsk.hdbti.utils.XSKCsvRecordMetadata;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.eclipse.dirigible.engine.odata2.transformers.DBMetadataUtil;

public interface IXSKCSVRecordDao {

  void save(XSKCsvRecordMetadata csvRecordMetadata) throws SQLException;

  void update(XSKCsvRecordMetadata csvRecordMetadata) throws SQLException;

  void deleteAll(List<String> ids, String tableName) throws SQLException;

  void delete(String id, String tableName) throws SQLException;

  DataSource getDataSource();

  DBMetadataUtil getDbMetadataUtil();
}
