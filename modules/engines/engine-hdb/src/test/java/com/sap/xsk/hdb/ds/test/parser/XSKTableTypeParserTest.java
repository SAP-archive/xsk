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

import com.sap.xsk.exceptions.XSKArtifactParserException;
import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdb.ds.model.XSKDBContentType;
import com.sap.xsk.hdb.ds.model.XSKDataStructureModelFactory;
import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableColumnModel;
import com.sap.xsk.hdb.ds.model.hdbtabletype.XSKDataStructureHDBTableTypeModel;
import com.sap.xsk.hdb.ds.parser.hdbtabletype.XSKTableTypeParser;
import org.apache.commons.io.IOUtils;
import org.eclipse.dirigible.core.test.AbstractDirigibleTest;
import org.junit.Test;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class XSKTableTypeParserTest extends AbstractDirigibleTest {

  @Test
  public void parse_WhenStructureDefinitionIsCorrect_ModelShouldBeSet() throws Exception {
    InputStream in = XSKTableTypeParserTest.class.getResourceAsStream("/ParsingHDBStructure.hdbstructure");
    String contents = IOUtils.toString(in, StandardCharsets.UTF_8);
    XSKDataStructureHDBTableTypeModel model = XSKDataStructureModelFactory.parseTableType("/ParsingHDBStructure.hdbstructure", contents);

    assertEquals(4, model.getColumns().size());
    assertEquals("ParsingHDBStructure", model.getName());
    assertEquals("DBADMIN", model.getSchema());
    assertEquals("/ParsingHDBStructure.hdbstructure", model.getLocation());
    assertEquals("HDBTABLETYPE", model.getType());
    assertEquals(false, model.isPublicProp());
    assertNotNull(model.getCreatedBy());
    assertNotNull(model.getCreatedAt());
    assertEquals(XSKDBContentType.XS_CLASSIC, model.getDBContentType());
    assertEquals(contents, model.getRawContent());

    XSKDataStructureHDBTableColumnModel scenarioId = model.getColumns().get(0);
    assertEquals("SCENARIO_ID", scenarioId.getName());
    assertEquals("VARCHAR", scenarioId.getType());
    assertEquals("32", scenarioId.getLength());
    assertFalse(scenarioId.isNullable());
    assertTrue(scenarioId.isPrimaryKey());
  }

  @Test(expected = IllegalStateException.class)
  public void parse_WhenPKIsWrong_shouldThrowException() throws XSKDataStructuresException, XSKArtifactParserException, IOException {
    XSKTableTypeParser parser = new XSKTableTypeParser();

    String content = "table.schemaName = \"DBADMIN\";\n" +
        "table.columns = [\n" +
        "\t{ name = \"SCENARIO_ID\";\tsqlType = VARCHAR;},\n" +
        "\t{ name = \"PERIOD_ID\";\t\tsqlType = DECIMAL;}\n" +
        "];\n" +
        "table.primaryKey.pkcolumns = [\"SCENARIO_ID_WRONG\", \"PERIOD_ID\"];";
    parser.parse("/temp.hdbstructure", content);
  }

  @Test
  public void parse_WhenHanaXSAdvancedContent_ModelShouldBeSet() throws Exception {
    String content = "TYPE \"CUSTOMERS_STRUCTURE\" AS TABLE ( \"CATEGORY_ID\" INTEGER , \"NAME\" VARCHAR (255) , \"TYPES\" VARCHAR (220) NOT NULL PRIMARY KEY)";
    XSKDataStructureHDBTableTypeModel model = XSKDataStructureModelFactory.parseTableType("/test.hdbstructure", content);
    assertEquals(XSKDBContentType.OTHERS, model.getDBContentType());
    assertEquals(content, model.getRawContent());
  }

}
