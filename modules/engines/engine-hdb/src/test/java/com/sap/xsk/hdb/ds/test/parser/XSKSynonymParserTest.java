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
import com.sap.xsk.hdb.ds.model.XSKDBContent;
import com.sap.xsk.hdb.ds.model.hdbsynonym.XSKDataStructureHDBSynonymModel;
import java.nio.charset.StandardCharsets;
import org.eclipse.dirigible.core.test.AbstractGuiceTest;
import org.junit.Test;

public class XSKSynonymParserTest extends AbstractGuiceTest {

  @Test
  public void parseHdbsynonymFileWithoutErrorsSuccessfully() throws Exception {
    String hdbsynonymSample = org.apache.commons.io.IOUtils
        .toString(XSKSynonymParserTest.class.getResourceAsStream("/MySynonym.hdbsynonym"), StandardCharsets.UTF_8);
    XSKDataStructureHDBSynonymModel model = XSKDataStructureModelFactory.parseSynonym("hdb_view/MySynonym.hdbsynonym", hdbsynonymSample);
    assertEquals("TONI", model.getSynonymSchema());
    assertEquals("hdb_view::Order", model.getTargetObject());
    assertEquals("PUBLIC", model.getTargetSchema());
    assertEquals("hdb_view::MySynonym", model.getName());
    assertEquals(XSKDBContent.XS_CLASSIC, model.getDBContentVersion());
  }
}