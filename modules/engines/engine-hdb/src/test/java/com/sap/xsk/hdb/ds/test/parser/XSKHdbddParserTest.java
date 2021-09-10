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
import com.sap.xsk.hdb.ds.test.module.HdbTestModule;
import org.eclipse.dirigible.core.test.AbstractDirigibleTest;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class XSKHdbddParserTest extends AbstractDirigibleTest {

  @Before
  public void setUp() {
    HdbTestModule testModule = new HdbTestModule();
    testModule.configure();
  }
    @Test
    public void parseHanaXSClassicContentWithSyntaxErrorFail() {
        XSKDataStructuresException exception = assertThrows(
                XSKDataStructuresException.class,
                () -> XSKDataStructureModelFactory.parseHdbdd("gstr2/ITC_EXPIRED_CONFIG.hdbdd", "")
        );
        assertEquals("Wrong format of HDB HDBDD: [gstr2/ITC_EXPIRED_CONFIG.hdbdd] during parsing. Ensure you are using the correct format for the correct compatibility version.", exception.getMessage());
    }

    @Test
    public void parseHanaXSClassicContentWithLexerErrorFail() {
        XSKDataStructuresException exception = assertThrows(
                XSKDataStructuresException.class,
                () -> XSKDataStructureModelFactory.parseHdbdd("gstr2/ITC_EXPIRED_CONFIG1.hdbdd", "")
        );
        assertEquals("Wrong format of HDB HDBDD: [gstr2/ITC_EXPIRED_CONFIG1.hdbdd] during parsing. Ensure you are using the correct format for the correct compatibility version.", exception.getMessage());
    }
}