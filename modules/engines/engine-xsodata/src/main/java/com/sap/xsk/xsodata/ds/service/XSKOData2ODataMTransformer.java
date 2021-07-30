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
package com.sap.xsk.xsodata.ds.service;

import org.eclipse.dirigible.engine.odata2.definition.ODataDefinition;
import org.eclipse.dirigible.engine.odata2.transformers.OData2ODataMTransformer;

import java.sql.SQLException;

public class XSKOData2ODataMTransformer {

    private OData2ODataMTransformer oData2ODataMTransformer = new OData2ODataMTransformer();

    public String[] transform(ODataDefinition oDataDefinition) throws SQLException {
        return oData2ODataMTransformer.transform(oDataDefinition);
    }
}
