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
package com.sap.xsk.hdb.ds.test.parser;

import static org.junit.Assert.assertEquals;

import com.sap.xsk.hdb.ds.model.XSKDataStructureModelFactory;
import com.sap.xsk.hdb.ds.model.hdbschema.XSKDataStructureHDBSchemaModel;
import java.nio.charset.StandardCharsets;
import org.eclipse.dirigible.core.test.AbstractGuiceTest;
import org.junit.Test;

public class XSKSchemaParserTest extends AbstractGuiceTest {

  @Test
  public void parseHdbschemaFileWithoutErrorsSuccessfully() throws Exception {
    String hdbschemaSample = org.apache.commons.io.IOUtils
        .toString(XSKSchemaParserTest.class.getResourceAsStream("/Myschema.hdbschema"), StandardCharsets.UTF_8);
    XSKDataStructureHDBSchemaModel model = XSKDataStructureModelFactory.parseSchema("/Myschema.hdbschema", hdbschemaSample);
    assertEquals("MYSCHEMA", model.getSchema());
  }
}