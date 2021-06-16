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
import com.sap.xsk.hdb.ds.model.XSKDBContentType;
import com.sap.xsk.hdb.ds.model.hdbview.XSKDataStructureHDBViewModel;
import java.nio.charset.StandardCharsets;
import org.eclipse.dirigible.core.test.AbstractGuiceTest;
import org.junit.Test;

public class XSKViewParserTest extends AbstractGuiceTest {

  @Test
  public void parseHdbviewFileWithoutErrorsSuccessfully() throws Exception {
    String hdbviewSample = org.apache.commons.io.IOUtils
        .toString(XSKViewParserTest.class.getResourceAsStream("/ItemsByOrderHANAv1.hdbview"), StandardCharsets.UTF_8);
    XSKDataStructureHDBViewModel model = XSKDataStructureModelFactory.parseView("/ItemsByOrderHANAv1.hdbview", hdbviewSample);
    assertEquals("MYSCHEMA", model.getSchema());
    assertEquals(2, model.getDependsOn().size());
    assertEquals("acme.com.test.tables::MY_TABLE1", model.getDependsOn().get(0));
    assertEquals("acme.com.test.views::MY_VIEW1", model.getDependsOn().get(1));
    assertEquals(2, model.getDependsOnTable().size());
    assertEquals("acme.com.test.tables::MY_TABLE1", model.getDependsOnTable().get(0));
    assertEquals("acme.com.test.views::MY_TABLE2", model.getDependsOnTable().get(1));
    assertEquals(2, model.getDependsOnView().size());
    assertEquals("acme.com.test.tables::MY_VIEW1", model.getDependsOnView().get(0));
    assertEquals("acme.com.test.views::MY_VIEW2", model.getDependsOnView().get(1));
    assertEquals(
        "SELECT T1.\"Column2\" FROM \"MYSCHEMA\".\"acme.com.test.tables::MY_TABLE1\" AS T1 LEFT JOIN \"MYSCHEMA\".\"acme.com.test.views::MY_VIEW1\" AS T2 ON T1.\"Column1\" = T2.\"Column1\"",
        model.getQuery());
    assertEquals(XSKDBContentType.XS_CLASSIC, model.getDBContentType());
  }

  @Test
  public void parseHdbviewFileHANAv2Successfully() throws Exception {
    String hdbviewSample = org.apache.commons.io.IOUtils
        .toString(XSKViewParserTest.class.getResourceAsStream("/ItemsByOrderHANAv2.hdbview"), StandardCharsets.UTF_8);
    XSKDataStructureHDBViewModel model = XSKDataStructureModelFactory.parseView("hdb_view/db/ItemsByOrderHANAv2.hdbview", hdbviewSample);
    assertEquals(XSKDBContentType.OTHERS, model.getDBContentType());
    assertEquals(hdbviewSample, model.getRawContent());
  }
}