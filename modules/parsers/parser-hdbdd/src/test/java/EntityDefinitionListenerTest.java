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

import static org.junit.Assert.assertEquals;

import com.sap.xsk.parser.hdbdd.core.CdsLexer;
import com.sap.xsk.parser.hdbdd.core.CdsParser;
import com.sap.xsk.parser.hdbdd.custom.EntityDefinitionListener;
import com.sap.xsk.parser.hdbdd.custom.ReferenceResolvingListener;
import com.sap.xsk.parser.hdbdd.custom.XSKHdbddErrorListener;
import com.sap.xsk.parser.hdbdd.exception.CDSRuntimeException;
import com.sap.xsk.parser.hdbdd.symbols.SymbolTable;
import com.sap.xsk.parser.hdbdd.symbols.entity.EntitySymbol;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Test;

public class EntityDefinitionListenerTest {

  private final SymbolTable symbolTable = new SymbolTable();

  @Test
  public void parseCaseInsensitiveKeysSuccessfully() throws Exception {
    CdsParser parser = parseSampleFile("/CaseInsensitiveTest.hdbdd", "sap/table/CaseInsensitiveTest.hdbdd");
    assertEquals(0, parser.getNumberOfSyntaxErrors());
  }

  @Test
  public void parseDefaultValuesSuccessfully() throws Exception {
    CdsParser parser = parseSampleFile("/DefaultValues.hdbdd", "sap/table/DefaultValues.hdbdd");
    assertEquals(0, parser.getNumberOfSyntaxErrors());
  }

  @Test
  public void parseEntitySuccessfully() throws Exception {
    CdsParser parser = parseSampleFile("/ParseEntity.hdbdd", "sap/table/ParseEntity.hdbdd");
    List<EntitySymbol> parsedEntities = this.symbolTable.getSortedEntities();

    assertEquals(0, parser.getNumberOfSyntaxErrors());
    assertEquals(1, parsedEntities.size());
    assertEquals("TEST_SCHEMA", parsedEntities.get(0).getSchema());
  }

  @Test
  public void parseContextSuccessfully() throws Exception {
    CdsParser parser = parseSampleFile("/ParseContext.hdbdd", "sap/table/ParseContext.hdbdd");
    List<EntitySymbol> parsedEntities = this.symbolTable.getSortedEntities();//get only Entities

    assertEquals(0, parser.getNumberOfSyntaxErrors());
    assertEquals(10, parsedEntities.size());//-> must be 13 after type is implemented
    parsedEntities.forEach(el -> assertEquals("TEST_SCHEMA", el.getSchema()));
  }

//    @Test
//    public void parseParseStructuredTypeSuccessfully() throws Exception {
//        CdsParser parser = parseSampleFile("/ParseStructuredType.hdbdd", "sap/table/ParseStructuredType.hdbdd");
//        List<EntitySymbol> parsedEntities = this.symbolTable.getSortedEntities();//get only Entities
//
//        assertEquals(0, parser.getNumberOfSyntaxErrors());
//    }

  private CdsParser parseSampleFile(String sampleFileName, String location) throws Exception {
    String content =
        org.apache.commons.io.IOUtils.toString(
            EntityDefinitionListener.class.getResourceAsStream(sampleFileName),
            StandardCharsets.UTF_8);

    ByteArrayInputStream is = new ByteArrayInputStream(content.getBytes());
    ANTLRInputStream inputStream = new ANTLRInputStream(is);
    CdsLexer hdbtiLexer = new CdsLexer(inputStream);
    CommonTokenStream tokenStream = new CommonTokenStream(hdbtiLexer);

    XSKHdbddErrorListener lexerErrorListener = new XSKHdbddErrorListener();
    hdbtiLexer.removeErrorListeners();//remove the ConsoleErrorListener
    hdbtiLexer.addErrorListener(lexerErrorListener);
    XSKHdbddErrorListener parserErrorListener = new XSKHdbddErrorListener();

    CdsParser hdbtiParser = new CdsParser(tokenStream);
    hdbtiParser.setBuildParseTree(true);
    hdbtiParser.removeErrorListeners();
    hdbtiParser.addErrorListener(parserErrorListener);

    ParseTree parseTree = hdbtiParser.cdsFile();

    EntityDefinitionListener entityDefinitionListener = new EntityDefinitionListener();
    entityDefinitionListener.setSymbolTable(symbolTable);
    entityDefinitionListener.setFileLocation(location);

    ParseTreeWalker parseTreeWalker = new ParseTreeWalker();
    parseTreeWalker.walk(entityDefinitionListener, parseTree);

    ReferenceResolvingListener referenceResolvingListener = new ReferenceResolvingListener();
    referenceResolvingListener.setSymbolTable(symbolTable);
    referenceResolvingListener.setEntityElements(entityDefinitionListener.getEntityElements());
    referenceResolvingListener.setTypeables(entityDefinitionListener.getTypeables());
    referenceResolvingListener.setAssociations(entityDefinitionListener.getAssociations());

    try {
      parseTreeWalker.walk(referenceResolvingListener, parseTree);
    } catch (CDSRuntimeException e) {
      throw new CDSRuntimeException(String.format("Failed to parse file: %s. %s", location, e.getMessage()));
    }

    return hdbtiParser;
  }
}