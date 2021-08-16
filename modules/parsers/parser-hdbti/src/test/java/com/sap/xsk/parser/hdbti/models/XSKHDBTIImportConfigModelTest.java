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
package com.sap.xsk.parser.hdbti.models;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class XSKHDBTIImportConfigModelTest {

    @Test
    public void testToStringSuccessfully() {
        XSKHDBTIImportConfigModel impModel = new XSKHDBTIImportConfigModel();
        impModel.setFileName("/sap/ti2/demo/myData.csv");
        impModel.setDelimEnclosing("\\\"");
        impModel.setDelimField(";");
        impModel.setHeader(false);
        impModel.setSchemaName("mySchema");
        impModel.setDistinguishEmptyFromNull(true);
        impModel.setTableName("myTable");
        impModel.setUseHeaderNames(false);
        XSKHDBTIImportConfigModel.Pair pair = new XSKHDBTIImportConfigModel.Pair("GROUP_TYPE", new ArrayList<>((Collections.singletonList("BW_CUBE"))));
        impModel.getKeys().add(pair);
        pair = new XSKHDBTIImportConfigModel.Pair("MAIN", new ArrayList<>((Collections.singletonList("BW_PSA"))));
        impModel.getKeys().add(pair);
        XSKHDBTIImportModel model = new XSKHDBTIImportModel();
        model.setConfigModels(Collections.singletonList(impModel));

        String expectedString = "import = [\n" +
                "{\n" +
                "\tdelimEnclosing=\"\\\"\";\n" +
                "\tschema = \"mySchema\";\n" +
                "\tdistinguishEmptyFromNull = true;\n" +
                "\theader = false;\n" +
                "\ttable = \"myTable\";\n" +
                "\tuseHeaderNames = false;\n" +
                "\tdelimField = \";\";\n" +
                "\tkeys = [\"GROUP_TYPE\":\"BW_CUBE\", \"MAIN\":\"BW_PSA\"];\n" +
                "\tfile = \"/sap/ti2/demo/myData.csv\";\n" +
                "}];";
        assertEquals(expectedString, model.toString());
    }

}
