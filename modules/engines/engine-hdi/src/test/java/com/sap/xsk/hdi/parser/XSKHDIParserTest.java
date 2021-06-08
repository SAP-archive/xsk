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
package com.sap.xsk.hdi.parser;

import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdb.ds.model.hdi.XSKDataStructureHDIModel;
import com.sap.xsk.hdb.ds.parser.hdi.XSKHDIParser;
import org.junit.Test;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.*;

public class XSKHDIParserTest {

  @Test
  public void parseValidContent() throws IOException, XSKDataStructuresException {
    String location = "/ValidHDIContent.hdi";
    String content = org.apache.commons.io.IOUtils
        .toString(XSKHDIParserTest.class.getResourceAsStream(location), StandardCharsets.UTF_8);
    XSKDataStructureHDIModel model = new XSKHDIParser().parse(location, content);
    assertEquals("/hdi-ext/config.hdiconfig", model.getConfiguration());
    assertEquals(new String[]{"DBADMIN"}, model.getUsers());
    assertEquals("/hdi-ext/config.hdiconfig", model.getConfiguration());
    assertEquals("XSK_HDI_EXT_GROUP", model.getGroup());
    assertEquals("XSK_HDI_EXT", model.getContainer());
    assertEquals(new String[]{"/hdi-ext/Customers.hdbsynonym", "/hdi-ext/CustomersCalcView.hdbcalculationview"}, model.getDeploy());
    assertTrue(model.getUndeploy().length == 0);

  }

  @Test
  public void parseNonStringProperties() throws IOException {
    String location = "/NonStringProperties.hdi";
    String content = org.apache.commons.io.IOUtils
        .toString(XSKHDIParserTest.class.getResourceAsStream(location), StandardCharsets.UTF_8);
    assertThrows(JsonSyntaxException.class, () -> new XSKHDIParser().parse(location, content));
  }

  @Test
  public void parseMissingMandatoryProperties() throws IOException {
    String location = "/MissingMandatoryProperty.hdi";
    String content = org.apache.commons.io.IOUtils
        .toString(XSKHDIParserTest.class.getResourceAsStream(location), StandardCharsets.UTF_8);
    assertThrows(JsonParseException.class, () -> new XSKHDIParser().parse(location, content));
  }

  @Test
  public void parseSameDeploymentFile() throws IOException {
    String location = "/SameDeploymentFile.hdi";
    String content = org.apache.commons.io.IOUtils
        .toString(XSKHDIParserTest.class.getResourceAsStream(location), StandardCharsets.UTF_8);
    assertThrows(JsonParseException.class, () -> new XSKHDIParser().parse(location, content));
  }

  @Test
  public void parseMissingDeploymentFile() throws IOException {
    String location = "/NoDeploymentFiles.hdi";
    String content = org.apache.commons.io.IOUtils
        .toString(XSKHDIParserTest.class.getResourceAsStream(location), StandardCharsets.UTF_8);
    assertThrows(JsonParseException.class, () -> new XSKHDIParser().parse(location, content));
  }
}
