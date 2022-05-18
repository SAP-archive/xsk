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
package com.sap.xsk.hdb.ds.parser.hdbtabletype;

import com.sap.xsk.exceptions.XSKArtifactParserException;
import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdb.ds.model.XSKDataStructureParametersModel;
import com.sap.xsk.hdb.ds.model.hdbtabletype.XSKDataStructureHDBTableTypeModel;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(value = Parameterized.class)
public class XSKTableTypeParserTest {

    private final XSKTableTypeParser xskTableTypeParser = new XSKTableTypeParser();
    private final boolean isTableTypeWithComments;

    public XSKTableTypeParserTest(boolean isTableTypeWithComments) {
        this.isTableTypeWithComments = isTableTypeWithComments;
    }

    @Parameters(name = "isTableTypeWithComments")
    public static Collection<Object> isTableTypeWithComments() {
        return List.of(true, false);
    }

    private static String getTableTypeContentFromResources(String tableTypeFile) {
        InputStream tableTypeFileInputStream = XSKTableTypeParserTest.class.getResourceAsStream("/table-types/" + tableTypeFile);
        if (tableTypeFileInputStream == null) {
            throw tableTypeFileCouldNotBeRead(tableTypeFile);
        }

        try {
            return IOUtils.toString(tableTypeFileInputStream, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw tableTypeFileCouldNotBeRead(tableTypeFile);
        }
    }

    private static RuntimeException tableTypeFileCouldNotBeRead(String tableTypeFile) {
        return new RuntimeException("Table type file '" + tableTypeFile + "' could not be found.");
    }

    @Test
    public void testParsedXSAdvancedTableTypeSchemaAndNameAreExtracted()
            throws XSKDataStructuresException, XSKArtifactParserException, IOException {
        String tableTypeFile = (isTableTypeWithComments) ? "tableTypeWithCommentsAndSchemaAndName.hdbtabletype" : "tableTypeWithSchemaAndName.hdbtabletype";
        String tableTypeContent = getTableTypeContentFromResources(tableTypeFile);
        XSKDataStructureParametersModel parametersModel = new XSKDataStructureParametersModel(null, tableTypeFile, tableTypeContent, null);

        XSKDataStructureHDBTableTypeModel parsedModel = xskTableTypeParser.parse(parametersModel);
        assertEquals("Unexpected schema found", "XSK_SAMPLES_HDI_CUBE", parsedModel.getSchema());
        assertEquals("Unexpected name found", "customer_sample::publishers", parsedModel.getName());
    }

    @Test
    public void testParsedXSAdvancedTableTypeNameIsExtractedWhenNoSchemaIsSet()
            throws XSKDataStructuresException, XSKArtifactParserException, IOException {
        String tableTypeFile =
                (isTableTypeWithComments) ? "tableTypeWithCommentsAndNoSchema.hdbtabletype" : "tableTypeWithNoSchema.hdbtabletype";
        String tableTypeContent = getTableTypeContentFromResources(tableTypeFile);
        XSKDataStructureParametersModel parametersModel = new XSKDataStructureParametersModel(null, tableTypeFile, tableTypeContent, null);

        XSKDataStructureHDBTableTypeModel parsedModel = xskTableTypeParser.parse(parametersModel);

        assertNull("Unexpected schema found", parsedModel.getSchema());
        assertEquals("Unexpected name found", "customer_sample::publishers", parsedModel.getName());
    }


}
