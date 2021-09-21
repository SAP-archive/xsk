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
package com.sap.xsk.parser.hdbdd.symbols;

import com.sap.xsk.parser.hdbdd.annotation.metadata.AnnotationArray;
import com.sap.xsk.parser.hdbdd.annotation.metadata.AnnotationEnum;
import com.sap.xsk.parser.hdbdd.annotation.metadata.AnnotationObj;
import com.sap.xsk.parser.hdbdd.annotation.metadata.AnnotationSimpleValue;
import com.sap.xsk.parser.hdbdd.core.CdsLexer;
import com.sap.xsk.parser.hdbdd.exception.CDSRuntimeException;
import com.sap.xsk.parser.hdbdd.symbols.context.ContextSymbol;
import com.sap.xsk.parser.hdbdd.symbols.context.CDSFileScope;
import com.sap.xsk.parser.hdbdd.symbols.entity.EntityElementSymbol;
import com.sap.xsk.parser.hdbdd.symbols.entity.EntitySymbol;
import com.sap.xsk.parser.hdbdd.symbols.type.BuiltInTypeSymbol;
import com.sap.xsk.parser.hdbdd.symbols.type.custom.StructuredDataTypeSymbol;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class SymbolTable {

  private CDSFileScope globalBuiltInTypeScope = new CDSFileScope();
  private Map<String, Symbol> symbolsByFullName = new HashMap<>();
  private Map<String, AnnotationObj> annotations;
  private Map<String, List<String>> entityGraph = new HashMap<>();
  private Map<String, BuiltInTypeSymbol> hanaBuiltInTypes = new HashMap<>();

  public SymbolTable() {
    initTypeSystem();
  }

  protected void initTypeSystem() {
    globalBuiltInTypeScope.define(new BuiltInTypeSymbol("String", 1, Arrays.asList(CdsLexer.STRING)));
    globalBuiltInTypeScope.define(new BuiltInTypeSymbol("LargeString",  Arrays.asList(CdsLexer.STRING)));
    globalBuiltInTypeScope.define(new BuiltInTypeSymbol("Binary", 1,  Arrays.asList(CdsLexer.VARBINARY)));
    globalBuiltInTypeScope.define(new BuiltInTypeSymbol("LargeBinary",  Arrays.asList(CdsLexer.VARBINARY)));

    globalBuiltInTypeScope.define(new BuiltInTypeSymbol("Integer",  Arrays.asList(CdsLexer.INTEGER)));
    globalBuiltInTypeScope.define(new BuiltInTypeSymbol("Integer64",  Arrays.asList(CdsLexer.INTEGER)));

    globalBuiltInTypeScope.define(new BuiltInTypeSymbol("Decimal", 2,  Arrays.asList(CdsLexer.DECIMAL, CdsLexer.INTEGER)));
    globalBuiltInTypeScope.define(new BuiltInTypeSymbol("DecimalFloat",  Arrays.asList(CdsLexer.DECIMAL)));
    globalBuiltInTypeScope.define(new BuiltInTypeSymbol("BinaryFloat",  Arrays.asList(CdsLexer.DECIMAL)));

    globalBuiltInTypeScope.define(new BuiltInTypeSymbol("LocalDate",  Arrays.asList(CdsLexer.LOCAL_DATE)));
    globalBuiltInTypeScope.define(new BuiltInTypeSymbol("LocalTime",  Arrays.asList(CdsLexer.LOCAL_TIME)));
    globalBuiltInTypeScope.define(new BuiltInTypeSymbol("UTCDateTime",  Arrays.asList(CdsLexer.UTC_DATE_TIME)));
    globalBuiltInTypeScope.define(new BuiltInTypeSymbol("UTCTimestamp",  Arrays.asList(CdsLexer.UTC_TIMESTAMP)));
    globalBuiltInTypeScope.define(new BuiltInTypeSymbol("Boolean",  Arrays.asList(CdsLexer.BOOLEAN)));

    hanaBuiltInTypes.put("ALPHANUM", new BuiltInTypeSymbol("ALPHANUMERIC", 0,  Arrays.asList(CdsLexer.STRING), true));
    hanaBuiltInTypes.put("SMALLINT", new BuiltInTypeSymbol("SMALLINT", 0,  Arrays.asList(CdsLexer.INTEGER), true));
    hanaBuiltInTypes.put("TINYINT", new BuiltInTypeSymbol("TINYINT", 0,  Arrays.asList(CdsLexer.INTEGER), true));
    hanaBuiltInTypes.put("REAL", new BuiltInTypeSymbol("REAL", 0,  Arrays.asList(CdsLexer.DECIMAL), true));
    hanaBuiltInTypes.put("SMALLDECIMAL", new BuiltInTypeSymbol("SMALLDECIMAL", 0,  Arrays.asList(CdsLexer.DECIMAL), true));
    hanaBuiltInTypes.put("VARCHAR", new BuiltInTypeSymbol("NVARCHAR", 1,  Arrays.asList(CdsLexer.STRING), true));
    hanaBuiltInTypes.put("CLOB", new BuiltInTypeSymbol("CLOB", 0,  Arrays.asList(CdsLexer.VARBINARY), true));
    hanaBuiltInTypes.put("BINARY", new BuiltInTypeSymbol("BINARY", 0,  Arrays.asList(CdsLexer.VARBINARY), true));
    hanaBuiltInTypes.put("ST_POINT", new BuiltInTypeSymbol("ST_POINT", 0,  Arrays.asList(CdsLexer.STRING), true));
    hanaBuiltInTypes.put("ST_GEOMETRY", new BuiltInTypeSymbol("ST_GEOMETRY", 0,  Arrays.asList(CdsLexer.STRING), true));

    initAnnotations();
  }

  protected void initAnnotations() {
    annotations = new HashMap<>();
    AnnotationObj catalogObj = createCatalogAnn();
    annotations.put(catalogObj.getName(), catalogObj);

    AnnotationObj schemaObj = createSchema();
    annotations.put(schemaObj.getName(), schemaObj);
  }

  public void addEntityToGraph(String fullName) {
    this.entityGraph.put(fullName, null);
  }

  public void addChildToEntity(String entityName, String childName) {
    if (entityGraph.get(entityName) == null) {
      this.entityGraph.put(entityName, new ArrayList<>());
    }

    this.entityGraph.get(entityName).add(childName);
  }

  public void addSymbol(Symbol symbol) {
    symbolsByFullName.put(symbol.getFullName(), symbol);
  }

  public Symbol getSymbol(String symbolFullName) {
    return this.symbolsByFullName.get(symbolFullName);
  }

  public BuiltInTypeSymbol getHanaType(String hanaType) {
    return this.hanaBuiltInTypes.get(hanaType);
  }

  public List<EntitySymbol> getSortedEntities() {
    Set<String> passedEntities = new HashSet<>();
    List<EntitySymbol> orderedEntities = new ArrayList<>();
    entityGraph.keySet().forEach(entityName -> traverseEntityGraph(entityName, orderedEntities, passedEntities));

    return orderedEntities;
  }

  public List<StructuredDataTypeSymbol> getTableTypes() {
    return this.symbolsByFullName.values().stream().filter(s -> s instanceof StructuredDataTypeSymbol)
        .map(dt -> (StructuredDataTypeSymbol) dt)
        .collect(Collectors.toList());
  }

  public void clearSymbolsByFullName() {
    this.symbolsByFullName.clear();
  }

  public void clearEntityGraph() {
    this.entityGraph.clear();
  }

  public CDSFileScope getGlobalBuiltInTypeScope() {
    return globalBuiltInTypeScope;
  }

  public AnnotationObj getAnnotation(String name) {
    return this.annotations.get(name);
  }

  private AnnotationObj createCatalogAnn() {
    AnnotationObj catalogObj = new AnnotationObj();
    catalogObj.setAllowedForSymbols(Collections.singletonList(EntitySymbol.class));
    catalogObj.setTopLevel(false);
    catalogObj.setName("Catalog");

    AnnotationObj index = new AnnotationObj();
    index.define("name", new AnnotationSimpleValue(CDSLiteralEnum.STRING.getLiteralType()));
    index.define("unique", new AnnotationSimpleValue(CDSLiteralEnum.BOOLEAN.getLiteralType()));
    AnnotationArray elementNames = new AnnotationArray(new AnnotationSimpleValue(CDSLiteralEnum.STRING.getLiteralType()));
    index.define("elementNames", elementNames);
    AnnotationEnum order = new AnnotationEnum();
    order.add("ASC");
    order.add("DESC");
    index.define("order", order);
    AnnotationArray catalogIndexArr = new AnnotationArray(index);
    catalogObj.define("index", catalogIndexArr);

    AnnotationEnum annotationEnum = new AnnotationEnum();
    annotationEnum.add("COLUMN");
    annotationEnum.add("ROW");
    catalogObj.define("tableType", annotationEnum);
    return catalogObj;
  }

  private AnnotationObj createSchema() {
    AnnotationObj schemaObj = new AnnotationObj();
    schemaObj.setName("Schema");
    schemaObj.setTopLevel(true);
    schemaObj.setAllowedForSymbols(Arrays.asList(EntitySymbol.class, ContextSymbol.class, StructuredDataTypeSymbol.class));
    AnnotationSimpleValue nameValue = new AnnotationSimpleValue(CDSLiteralEnum.STRING.getLiteralType());
    schemaObj.define("name", nameValue);

    return schemaObj;
  }

  private AnnotationObj createSearchIndex() {
    AnnotationObj searchIndex = new AnnotationObj();
    searchIndex.setAllowedForSymbols(Collections.singletonList(EntityElementSymbol.class));

    AnnotationObj text = new AnnotationObj();
    AnnotationSimpleValue textValue = new AnnotationSimpleValue(CDSLiteralEnum.BOOLEAN.getLiteralType());
    text.define("enabled", textValue);
    searchIndex.define("text", text);

    AnnotationObj fuzzy = new AnnotationObj();
    AnnotationSimpleValue fuzzyValue = new AnnotationSimpleValue(CDSLiteralEnum.BOOLEAN.getLiteralType());
    text.define("enabled", fuzzyValue);
    searchIndex.define("fuzzy", fuzzy);

    return searchIndex;
  }

  private void traverseEntityGraph(String entityName, List<EntitySymbol> orderedSymbol, Set<String> passedEntities) {
    if (passedEntities.contains(entityName)) {
      return;
    }

    passedEntities.add(entityName);
    List<String> children = entityGraph.get(entityName);
    if (children == null) {
      EntitySymbol bottomEntity =  (EntitySymbol) this.symbolsByFullName.get(entityName);
      if (bottomEntity == null) {
        throw new CDSRuntimeException(String.format("No entity with name: %s found in symbol table.", entityName));
      }

      orderedSymbol.add(bottomEntity);
      return;
    }

    children.forEach(child -> traverseEntityGraph(child, orderedSymbol, passedEntities));

    orderedSymbol.add((EntitySymbol) this.symbolsByFullName.get(entityName));
  }
}
