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
package com.sap.xsk.parser.hdbti.custom;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import com.sap.xsk.exceptions.XSKArtifactParserException;
import com.sap.xsk.parser.hdbti.exception.DuplicateFieldNameException;
import com.sap.xsk.parser.hdbti.exception.XSKHDBTISyntaxErrorException;
import com.sap.xsk.parser.hdbti.models.XSKHDBTIImportConfigModel;
import com.sap.xsk.parser.hdbti.models.XSKHDBTIImportModel;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.junit.Test;

public class XSKHDBTIParserTest {

  @Test
  public void testValidInputAllFieldsAssignedProperlyParseSuccessfully()
      throws IOException, XSKHDBTISyntaxErrorException, XSKArtifactParserException {
    String hdbtiSample = org.apache.commons.io.IOUtils
        .toString(XSKHDBTIParserTest.class.getResourceAsStream("/sample.hdbti"), StandardCharsets.UTF_8);

    XSKHDBTIParser xskhdbtiParser = new XSKHDBTIParser();
    XSKHDBTIImportModel importModel = xskhdbtiParser.parse("/test/xsk/com/sap/sample.hdbti", hdbtiSample);
    XSKHDBTIImportConfigModel configModel = importModel.getConfigModels().get(0);

    int expectedConfigsSize = 1;
    assertEquals(expectedConfigsSize, importModel.getConfigModels().size());
    assertEquals(configModel.getTableName(), "myTable");
    assertEquals(configModel.getSchemaName(), "mySchema");
    assertEquals(configModel.getFileName(), "sap.ti2.demo:myData.csv");
    assertEquals(configModel.getHeader(), false);
    assertEquals(configModel.getUseHeaderNames(), false);
    assertEquals(configModel.getDelimField(), ";");
    assertEquals(configModel.getDelimEnclosing(), "\"");
    assertEquals(configModel.getDistinguishEmptyFromNull(), true);
    int expectedKeysSize = 3;
    List<XSKHDBTIImportConfigModel.Pair> keys = configModel.getKeys();
    assertEquals(keys.size(), expectedKeysSize);
    assertEquals(keys.get(0).getKey(), "GROUP_TYPE");
    assertEquals(keys.get(0).getValue(), "BW_CUBE");
    assertEquals(keys.get(1).getKey(), "GROUP_TYPE");
    assertEquals(keys.get(1).getValue(), "BW_DSO");
    assertEquals(keys.get(2).getKey(), "GROUP_TYPE");
    assertEquals(keys.get(2).getValue(), "BW_PSA");
  }

  @Test
  public void testParseContainsInvalidSyntaxShouldThrowException() throws IOException {
    String hdbtiSample = org.apache.commons.io.IOUtils
        .toString(XSKHDBTIParserTest.class.getResourceAsStream("/invalidSyntax.hdbti"), StandardCharsets.UTF_8);
    XSKHDBTIParser xskhdbtiParser = new XSKHDBTIParser();

    try {
      xskhdbtiParser.parse("/test/xsk/com/sap/invalidSyntax.hdbti", hdbtiSample);
    } catch (XSKArtifactParserException parseErrorException) {
      assertTrue(true);
    } catch (Exception e) {
      fail();
    }
  }

  @Test
  public void testParseConfigObjectContainsDuplicateKeysShouldThrowProperException() throws IOException {
    String hdbtiSample = org.apache.commons.io.IOUtils
        .toString(XSKHDBTIParserTest.class.getResourceAsStream("/duplicateKeys.hdbti"), StandardCharsets.UTF_8);
    XSKHDBTIParser xskhdbtiParser = new XSKHDBTIParser();

    try {
      xskhdbtiParser.parse("/test/xsk/com/sap/duplicateKeys.hdbti", hdbtiSample);
    } catch (DuplicateFieldNameException duplicateFieldNameException) {
      assertTrue(true);
    } catch (Exception e) {
      fail();
    }
  }

  @Test
  public void testParseConfigObjectFieldsRandomOrderShouldPass()
      throws IOException, XSKHDBTISyntaxErrorException, XSKArtifactParserException {
    String hdbtiSample = org.apache.commons.io.IOUtils
        .toString(XSKHDBTIParserTest.class.getResourceAsStream("/randomOrder.hdbti"), StandardCharsets.UTF_8);
    XSKHDBTIParser xskhdbtiParser = new XSKHDBTIParser();

    xskhdbtiParser.parse("/test/xsk/com/sap/randomOrder.hdbti", hdbtiSample);
  }

  @Test(expected = XSKArtifactParserException.class)
  public void parseHDBTIContentWithLexerErrorFail() throws Exception {
    String content = org.apache.commons.io.IOUtils
        .toString(XSKHDBTIParserTest.class.getResourceAsStream("/lexerError.hdbti"), StandardCharsets.UTF_8);
    XSKHDBTIParser xskhdbtiParser = new XSKHDBTIParser();
    xskhdbtiParser.parse("/test/xsk/com/sap/lexerError.hdbti", content);
  }

  @Test(expected = XSKArtifactParserException.class)
  public void parseHDBTIContentWithSyntaxErrorFail() throws Exception {
    String content = org.apache.commons.io.IOUtils
        .toString(XSKHDBTIParserTest.class.getResourceAsStream("/parserError.hdbti"), StandardCharsets.UTF_8);
    XSKHDBTIParser xskhdbtiParser = new XSKHDBTIParser();
    xskhdbtiParser.parse("/test/xsk/com/sap/syntaxError.hdbti", content);
  }
}
