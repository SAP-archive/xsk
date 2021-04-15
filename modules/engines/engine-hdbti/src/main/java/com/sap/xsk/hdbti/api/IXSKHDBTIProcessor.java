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
import com.sap.xsk.hdbti.model.XSKTableImportConfigurationDefinition;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public interface IXSKHDBTIProcessor {
    void process(XSKTableImportConfigurationDefinition tableImportConfigurationDefinition, Connection connection)
        throws IOException, SQLException, XSKDataStructuresException, XSKTableImportException;
}
