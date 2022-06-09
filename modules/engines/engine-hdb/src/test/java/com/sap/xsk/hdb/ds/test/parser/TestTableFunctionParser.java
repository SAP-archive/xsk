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
package com.sap.xsk.hdb.ds.test.parser;

import com.sap.xsk.exceptions.XSKArtifactParserException;
import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdb.ds.model.XSKDataStructureParametersModel;
import com.sap.xsk.hdb.ds.model.hdbtablefunction.XSKDataStructureHDBTableFunctionModel;
import com.sap.xsk.hdb.ds.parser.XSKDataStructureParser;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class TestTableFunctionParser {
    TestContentProvider testContentProvider = new TestContentProvider();
    XSKDataStructureHDBTableFunctionModel parseTableFunction(@NotNull XSKDataStructureParser<XSKDataStructureHDBTableFunctionModel> parser, String location, String content)
            throws XSKDataStructuresException, XSKArtifactParserException, IOException {
        XSKDataStructureParametersModel parametersModel =
                testContentProvider.getParametersModel(null, location, content, null);
        return parser.parse(parametersModel);
    }
}
