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
import com.sap.xsk.hdb.ds.model.hdbprocedure.XSKDataStructureHDBProcedureModel;
import com.sap.xsk.hdb.ds.model.hdbtablefunction.XSKDataStructureHDBTableFunctionModel;
import com.sap.xsk.hdb.ds.parser.XSKDataStructureParser;
import org.apache.commons.io.IOUtils;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class ParserTestContentProvider {

    String getTestContent(String location) throws IOException {
        InputStream in = ParserTestContentProvider.class.getResourceAsStream(location);
        return IOUtils.toString(Objects.requireNonNull(in), StandardCharsets.UTF_8);
    }

    XSKDataStructureHDBProcedureModel parseProcedure(@NotNull XSKDataStructureParser<XSKDataStructureHDBProcedureModel> parser, String location, String content)
            throws XSKDataStructuresException, XSKArtifactParserException, IOException {
        XSKDataStructureParametersModel parametersModel =
                getParametersModel(null, location, content, null);
        return parser.parse(parametersModel);
    }

    XSKDataStructureHDBTableFunctionModel parseTableFunction(@NotNull XSKDataStructureParser<XSKDataStructureHDBTableFunctionModel> parser, String location, String content)
            throws XSKDataStructuresException, XSKArtifactParserException, IOException {
        XSKDataStructureParametersModel parametersModel =
                getParametersModel(null, location, content, null);
        return parser.parse(parametersModel);
    }

    private XSKDataStructureParametersModel getParametersModel(String type, String location, String content, String workspacePath) {
        return new XSKDataStructureParametersModel(type, location, content, workspacePath);
    }
}
