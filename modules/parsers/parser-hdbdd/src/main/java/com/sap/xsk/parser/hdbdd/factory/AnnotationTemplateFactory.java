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
package com.sap.xsk.parser.hdbdd.factory;

import com.sap.xsk.parser.hdbdd.annotation.metadata.AnnotationArray;
import com.sap.xsk.parser.hdbdd.annotation.metadata.AnnotationEnum;
import com.sap.xsk.parser.hdbdd.annotation.metadata.AnnotationObj;
import com.sap.xsk.parser.hdbdd.annotation.metadata.AnnotationSimpleValue;
import com.sap.xsk.parser.hdbdd.symbols.CDSLiteralEnum;
import com.sap.xsk.parser.hdbdd.symbols.context.ContextSymbol;
import com.sap.xsk.parser.hdbdd.symbols.entity.EntityElementSymbol;
import com.sap.xsk.parser.hdbdd.symbols.entity.EntitySymbol;
import com.sap.xsk.parser.hdbdd.symbols.type.custom.StructuredDataTypeSymbol;
import java.util.Arrays;
import java.util.Collections;

public class AnnotationTemplateFactory {

  public AnnotationObj buildTemplateForCatalogAnnotation() {
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

  public AnnotationObj buildTemplateForSchemaAnnotation() {
    AnnotationObj schemaObj = new AnnotationObj();
    schemaObj.setName("Schema");
    schemaObj.setTopLevel(true);
    schemaObj.setAllowedForSymbols(Arrays.asList(EntitySymbol.class, ContextSymbol.class, StructuredDataTypeSymbol.class));
    AnnotationSimpleValue nameValue = new AnnotationSimpleValue(CDSLiteralEnum.STRING.getLiteralType());
    schemaObj.define("name", nameValue);

    return schemaObj;
  }

  public AnnotationObj buildTemplateForNoKeyAnnotation() {
    AnnotationObj noKeyObj = new AnnotationObj();
    noKeyObj.setName("nokey");
    noKeyObj.setTopLevel(false);
    noKeyObj.setAllowedForSymbols(Collections.singletonList(EntitySymbol.class));

    return noKeyObj;
  }

  public AnnotationObj buildTemplateForGenerateTableTypeAnnotation() {
    AnnotationObj generateTableTypeObj = new AnnotationObj();
    generateTableTypeObj.setName("GenerateTableType");
    generateTableTypeObj.setTopLevel(false);
    generateTableTypeObj.setAllowedForSymbols(Collections.singletonList(StructuredDataTypeSymbol.class));
    AnnotationSimpleValue booleanValue = new AnnotationSimpleValue(CDSLiteralEnum.BOOLEAN.getLiteralType());
    generateTableTypeObj.define("booleanValue", booleanValue);

    return generateTableTypeObj;
  }

  public AnnotationObj buildTemplateForSearchIndexAnnotation() {
    AnnotationObj searchIndexObj = new AnnotationObj();
    searchIndexObj.setName("SearchIndex");
    searchIndexObj.setTopLevel(false);
    searchIndexObj.setAllowedForSymbols(Collections.singletonList(EntityElementSymbol.class));
    AnnotationSimpleValue booleanValue = new AnnotationSimpleValue(CDSLiteralEnum.BOOLEAN.getLiteralType());
    searchIndexObj.define("enabled", booleanValue);
    searchIndexObj.define("fuzzy", new AnnotationObj());
    searchIndexObj.define("text", new AnnotationObj());

    return searchIndexObj;
  }
}
