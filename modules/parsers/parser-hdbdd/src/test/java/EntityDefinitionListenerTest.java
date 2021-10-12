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
import static org.junit.Assert.assertNotNull;

import com.sap.xsk.parser.hdbdd.core.CdsLexer;
import com.sap.xsk.parser.hdbdd.core.CdsParser;
import com.sap.xsk.parser.hdbdd.custom.EntityDefinitionListener;
import com.sap.xsk.parser.hdbdd.custom.ReferenceResolvingListener;
import com.sap.xsk.parser.hdbdd.custom.XSKHdbddErrorListener;
import com.sap.xsk.parser.hdbdd.exception.CDSRuntimeException;
import com.sap.xsk.parser.hdbdd.symbols.Symbol;
import com.sap.xsk.parser.hdbdd.symbols.SymbolTable;
import com.sap.xsk.parser.hdbdd.symbols.entity.EntitySymbol;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import com.sap.xsk.parser.hdbdd.symbols.type.custom.DataTypeSymbol;
import com.sap.xsk.parser.hdbdd.symbols.type.field.Typeable;
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

  @Test
  public void parseParseAssociationsSuccessfully() throws Exception {
    CdsParser parser = parseSampleFile("/Products.hdbdd", "sap/db/Products.hdbdd");
    List<EntitySymbol> parsedEntities = this.symbolTable.getSortedEntities();//get only Entities

    assertEquals(0, parser.getNumberOfSyntaxErrors());

    //product.Orders.items
    assertEquals(0, parsedEntities.get(1).getAssociations().get(0).getForeignKeys().size());
    assertEquals(1, parsedEntities.get(1).getAssociations().get(1).getForeignKeys().size());
  }

  //    @Test
//    public void parseParseStructuredTypeSuccessfully() throws Exception {
//        CdsParser parser = parseSampleFile("/ParseStructuredType.hdbdd", "sap/table/ParseStructuredType.hdbdd");
//        List<EntitySymbol> parsedEntities = this.symbolTable.getSortedEntities();//get only Entities
//
//        assertEquals(0, parser.getNumberOfSyntaxErrors());
//    }
  @Test
  public void parseParseStructuredTypeSuccessfully() throws Exception {
    CdsParser parser = parseSampleFile("/ParseStructuredType.hdbdd", "sap/table/ParseStructuredType.hdbdd");
    this.symbolTable.getSortedEntities();//get only Entities

    assertEquals(0, parser.getNumberOfSyntaxErrors());
  }

  @Test
  public void parseUnmanagedAssociationSuccessfully() throws Exception {
    CdsParser parser = parseSampleFile("/ProjectProducts.hdbdd", "sap/db/ProjectProducts.hdbdd");
    this.symbolTable.getSortedEntities();//get only Entities

    assertEquals(0, parser.getNumberOfSyntaxErrors());
  }

  @Test
  public void parseHanaBuiltInTypesSuccessfully() throws Exception {
    CdsParser parser = parseSampleFile("/HanaBuiltInTypesTest.hdbdd", "sap/db/HanaBuiltInTypesTest.hdbdd");
    List<EntitySymbol> parsedEntities = this.symbolTable.getSortedEntities();

    assertEquals(0, parser.getNumberOfSyntaxErrors());
    assertEquals(1, parsedEntities.size());
    EntitySymbol entity = parsedEntities.get(0);
    assertEquals(10, entity.getElements().size());

    assertEquals("ALPHANUMERIC", entity.getElements().get(1).getType().getName());
    assertEquals("NVARCHAR", entity.getElements().get(0).getType().getName());
    assertEquals("SMALLINT", entity.getElements().get(2).getType().getName());
    assertEquals("TINYINT", entity.getElements().get(3).getType().getName());
    assertEquals("REAL", entity.getElements().get(4).getType().getName());
    assertEquals("SMALLDECIMAL", entity.getElements().get(5).getType().getName());
    assertEquals("CLOB", entity.getElements().get(6).getType().getName());
    assertEquals("BINARY", entity.getElements().get(7).getType().getName());
    assertEquals("ST_POINT", entity.getElements().get(8).getType().getName());
    assertEquals("ST_GEOMETRY", entity.getElements().get(9).getType().getName());

  }

  @Test
  public void parseGlobalBuiltInTypesSuccessfully() throws Exception {
    CdsParser parser = parseSampleFile("/GlobalBuiltInTypesTest.hdbdd", "sap/db/GlobalBuiltInTypesTest.hdbdd");
    List<EntitySymbol> parsedEntities = this.symbolTable.getSortedEntities();

    assertEquals(0, parser.getNumberOfSyntaxErrors());
    assertEquals(1, parsedEntities.size());
    EntitySymbol entity = parsedEntities.get(0);
    assertEquals(16, entity.getElements().size());

    assertEquals("String", entity.getElements().get(0).getType().getName());
    assertEquals("LargeString", entity.getElements().get(1).getType().getName());
    assertEquals("String", entity.getElements().get(2).getType().getName());
    assertEquals("Binary", entity.getElements().get(3).getType().getName());
    assertEquals("LargeBinary", entity.getElements().get(4).getType().getName());
    assertEquals("Integer", entity.getElements().get(5).getType().getName());
    assertEquals("Integer64", entity.getElements().get(6).getType().getName());
    assertEquals("Decimal", entity.getElements().get(7).getType().getName());
    assertEquals("DecimalFloat", entity.getElements().get(8).getType().getName());
    assertEquals("BinaryFloat", entity.getElements().get(9).getType().getName());
    assertEquals("LocalDate", entity.getElements().get(10).getType().getName());
    assertEquals("LocalTime", entity.getElements().get(11).getType().getName());
    assertEquals("UTCDateTime", entity.getElements().get(12).getType().getName());
    assertEquals("UTCTimestamp", entity.getElements().get(13).getType().getName());
    assertEquals("Boolean", entity.getElements().get(14).getType().getName());
    assertEquals("Decimal", entity.getElements().get(15).getType().getName());

  }

  @Test
  public void parseArtifactsNamedAsKeywordsSuccessfully() throws Exception {
      parseSampleFile("/Context.hdbdd", "sap/db/Context.hdbdd");
      assertEquals(4, this.symbolTable.getSymbolsByFullName().size());

      List<EntitySymbol> entitiesParsed = this.symbolTable.getSortedEntities();
      assertEquals(2, entitiesParsed.size());

      EntitySymbol parsedEntity = entitiesParsed.get(1);
      assertNotNull(parsedEntity.resolve("key"));
      assertNotNull(parsedEntity.resolve("entity"));
      assertNotNull(parsedEntity.resolve("Type"));

      EntitySymbol to = entitiesParsed.get(0);
      assertNotNull(to.resolve("id"));
      assertNotNull(to.resolve("to"));

      assertNotNull(this.symbolTable.getSymbolsByFullName().get("sap.db::Context.Type"));
    Symbol resolvedType = this.symbolTable.getSymbolsByFullName().get("sap.db::Context.Type");
    assertEquals(DataTypeSymbol.class, resolvedType.getClass());
  }

  @Test
  public void parseInvalidElementDeclarationShouldThrowException() throws Exception {
    try {
      parseSampleFile("/InvalidElementDeclaration.hdbdd", "sap/db/InvalidElementDeclaration.hdbdd");
    } catch (RuntimeException e) {
      assertEquals(CDSRuntimeException.class, e.getClass());
      assertEquals("Error at line: 5 - Element declarations are only allowed for entity scope.", e.getMessage());
    }
  }

  @Test
  public void parseEntitiesWithEscapedNamesShouldResolveCorrectly() throws Exception {
    parseSampleFile("/EscapedNamesExample.hdbdd", "com/sap/EscapedNamesExample.hdbdd");
    List<EntitySymbol> sortedEntities = this.symbolTable.getSortedEntities();

    EntitySymbol school = sortedEntities.get(0);
    EntitySymbol student = sortedEntities.get(1);

    assertEquals("Escaped-Student", student.getName());
    assertNotNull(student.resolve("n@me!"));
    assertNotNull(student.resolve("from"));

    assertEquals("Escaped-School", school.getName());
    assertNotNull(((Typeable) school.resolve("name")).getType());
    assertNotNull(((Typeable) school.resolve("to")).getType());
  }

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