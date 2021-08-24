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
package com.sap.xsk.hdb.ds.test.parser;

import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdb.ds.model.XSKDataStructureModelFactory;
import org.eclipse.dirigible.core.test.AbstractDirigibleTest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class XSKHdbddParserTest extends AbstractDirigibleTest {
    @Test
    public void parseHanaXSClassicContentWithSyntaxErrorFail() {
        String content = "namespace sap.slh.dcs.gstr2.table;\n" +
                "@Schema : 'GSTR2DB'\n" +
                "@Catalog.tableType : #COLUMN\n" +
                "entity ITC_EXPIRED_CONFIG {\n" +
                "\tKeY1 FY_YEAR             : hana.VARCHAR(10);\n" + //-> incorrect key
                "\tITC_EXPIRED_DATE        : LocalDate;\n" +
                "}\n";
        XSKDataStructuresException exception = assertThrows(
                XSKDataStructuresException.class,
                () -> XSKDataStructureModelFactory.parseHdbdd("sap/slh/dcs/gstr2/table/ITC_EXPIRED_CONFIG.hdbdd", content)
        );
        assertEquals("Wrong format of HDB HDBDD: [sap/slh/dcs/gstr2/table/ITC_EXPIRED_CONFIG.hdbdd] during parsing. Ensure you are using the correct format for the correct compatibility version.", exception.getMessage());
    }

    @Test
    public void parseHanaXSClassicContentWithLexerErrorFail() {
        String content = "namespace/ sap.slh.dcs.gstr2.table;\n" +
                "@Schema : 'GSTR2DB'\n" +
                "@Catalog.tableType : #COLUMN\n" +
                "entity ITC_EXPIRED_CONFIG {\n" +
                "\tkey FY_YEAR             : hana.VARCHAR(10);\n" +
                "\tITC_EXPIRED_DATE        : LocalDate;\n" +
                "};";
        XSKDataStructuresException exception = assertThrows(
                XSKDataStructuresException.class,
                () -> XSKDataStructureModelFactory.parseHdbdd("sap/slh/dcs/gstr2/table/ITC_EXPIRED_CONFIG.hdbdd", content)
        );
        assertEquals("Wrong format of HDB HDBDD: [sap/slh/dcs/gstr2/table/ITC_EXPIRED_CONFIG.hdbdd] during parsing. Ensure you are using the correct format for the correct compatibility version.", exception.getMessage());
    }
}