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
package com.sap.xsk.hdb.ds.api;

import com.sap.xsk.hdb.ds.model.XSKDataStructureModel;
import java.sql.Connection;
import java.sql.SQLException;

public interface IXSKHdbProcessor<T extends XSKDataStructureModel> {

  boolean execute(Connection connection, T entityModel) throws SQLException;
}
