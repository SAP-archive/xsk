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
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.sap.xsk.parser.hdbtable.core.HdbtableLexer;
import com.sap.xsk.parser.hdbtable.core.HdbtableParser;
import com.sap.xsk.parser.hdbtable.custom.XSKHDBTABLECoreVisitor;
import com.sap.xsk.parser.hdbtable.custom.XSKHDBTABLESyntaxErrorListener;
import com.sap.xsk.parser.hdbtable.model.XSKHDBTABLEDefinitionModel;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;

public class XSKHDBTABLECoreVisitorTest {

  @Test
  public void parseHdbtableSuccessfully() throws Exception {
    String content = org.apache.commons.io.IOUtils
        .toString(XSKHDBTABLECoreVisitorTest.class.getResourceAsStream("/sample.hdbtable"), StandardCharsets.UTF_8);
    XSKHDBTABLEDefinitionModel hdbtableDefinitionModel = passeFileContent(content);

    assertNotNull(hdbtableDefinitionModel);
    assertEquals("MYSCHEMA", hdbtableDefinitionModel.getSchemaName());
  }


  @Test
  public void parseHdbtableWithStringLiteralsWithEscapedQuotes() throws IOException {
    String content = "table.schemaName = \"MYSCHEMA\";\n"
        + "table.tableType = COLUMNSTORE;\n"
        + "table.description = \"Some \\\"escapted text\\\"\";\n"
        + "table.columns = [\n"
        + "    {name = \"ID\"; sqlType = NVARCHAR; length = 256; nullable = false; comment = \"Some \\\"escapted text\\\"\";}\n"
        + "];\n"
        + "table.primaryKey.pkcolumns = [\"ID\"];";
    XSKHDBTABLEDefinitionModel hdbtableDefinitionModel = passeFileContent(content);

    assertNotNull(hdbtableDefinitionModel);
    assertEquals("MYSCHEMA", hdbtableDefinitionModel.getSchemaName());
    assertEquals("Some \"escapted text\"", hdbtableDefinitionModel.getDescription());

  }

  private static XSKHDBTABLEDefinitionModel passeFileContent(String content) throws IOException {
    ByteArrayInputStream is = new ByteArrayInputStream(content.getBytes());
    ANTLRInputStream inputStream = new ANTLRInputStream(is);
    HdbtableLexer lexer = new HdbtableLexer(inputStream);
    XSKHDBTABLESyntaxErrorListener lexerErrorListener = new XSKHDBTABLESyntaxErrorListener();
    lexer.removeErrorListeners();
    lexer.addErrorListener(lexerErrorListener);
    CommonTokenStream tokenStream = new CommonTokenStream(lexer);

    HdbtableParser hdbtableParser = new HdbtableParser(tokenStream);
    hdbtableParser.setBuildParseTree(true);
    hdbtableParser.removeErrorListeners();

    XSKHDBTABLESyntaxErrorListener parserErrorListener = new XSKHDBTABLESyntaxErrorListener();
    hdbtableParser.addErrorListener(parserErrorListener);
    ParseTree parseTree = hdbtableParser.hdbtableDefinition();
    XSKHDBTABLECoreVisitor xskhdbtableCoreVisitor = new XSKHDBTABLECoreVisitor();

    JsonElement parsedResult = xskhdbtableCoreVisitor.visit(parseTree);
    Gson gson = new Gson();
    XSKHDBTABLEDefinitionModel hdbtableDefinitionModel = gson.fromJson(parsedResult, XSKHDBTABLEDefinitionModel.class);
    hdbtableDefinitionModel.checkForAllMandatoryFieldsPresence();

    assertEquals(0, parserErrorListener.getErrors().size());
    assertEquals(0, lexerErrorListener.getErrors().size());

    return hdbtableDefinitionModel;
  }

}