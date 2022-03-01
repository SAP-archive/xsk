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

import com.sap.xsk.parser.hdbdd.core.CdsParser;
import com.sap.xsk.parser.hdbdd.core.CdsParser.ArtifactRuleContext;
import com.sap.xsk.parser.hdbdd.core.CdsParser.ViewRuleContext;
import com.sap.xsk.parser.hdbdd.exception.CDSRuntimeException;
import com.sap.xsk.parser.hdbdd.symbols.Symbol;
import com.sap.xsk.parser.hdbdd.symbols.SymbolTypeEnum;
import com.sap.xsk.parser.hdbdd.symbols.context.ContextSymbol;
import com.sap.xsk.parser.hdbdd.symbols.context.Scope;
import com.sap.xsk.parser.hdbdd.symbols.entity.AssociationSymbol;
import com.sap.xsk.parser.hdbdd.symbols.entity.CardinalityEnum;
import com.sap.xsk.parser.hdbdd.symbols.entity.EntityElementSymbol;
import com.sap.xsk.parser.hdbdd.symbols.entity.EntitySymbol;
import com.sap.xsk.parser.hdbdd.symbols.type.custom.DataTypeSymbol;
import com.sap.xsk.parser.hdbdd.symbols.type.custom.StructuredDataTypeSymbol;
import com.sap.xsk.parser.hdbdd.symbols.type.field.FieldSymbol;
import com.sap.xsk.parser.hdbdd.symbols.view.ViewSymbol;
import com.sap.xsk.parser.hdbdd.util.HdbddUtils;
import org.antlr.v4.runtime.Token;

public class SymbolFactory {

  public Symbol getSymbol(ArtifactRuleContext artifactRuleContext, Scope currentScope, String schema) {
    return getSymbol(currentScope, schema, artifactRuleContext.artifactName, artifactRuleContext.artifactType);
  }

  public Symbol getSymbol(ViewRuleContext viewRuleContext, Scope currentScope, String schema) {
    return getSymbol(currentScope, schema, viewRuleContext.artifactName, viewRuleContext.artifactType);
  }

  private Symbol getSymbol(Scope currentScope, String schema, Token artifactName, Token artifactType) {
    String symbolId = HdbddUtils.processEscapedSymbolName(artifactName.getText());
    checkForDuplicateName(symbolId, currentScope, artifactName.getLine());
    SymbolTypeEnum symbolTypeEnum = parseToSymbolTypeEnum(artifactType);

    Symbol newSymbol = createSymbol(symbolId, currentScope, schema, artifactName);
    switch (symbolTypeEnum) {
      case context:
        ContextSymbol contextSymbol = new ContextSymbol(newSymbol);
        currentScope.define(contextSymbol);
        return contextSymbol;
      case entity:
        EntitySymbol entitySymbol = new EntitySymbol(newSymbol);
        currentScope.define(entitySymbol);
        return entitySymbol;
      case type:
        StructuredDataTypeSymbol dataTypeSymbol = new StructuredDataTypeSymbol(newSymbol);
        currentScope.define(dataTypeSymbol);
        return dataTypeSymbol;
      case view:
        ViewSymbol viewSymbol = new ViewSymbol(newSymbol);
        currentScope.define(viewSymbol);
        return viewSymbol;
      default:
        return null;
    }
  }

  public DataTypeSymbol getDataTypeSymbol(CdsParser.DataTypeRuleContext ctx, Scope currentScope, String schema) {
    String typeId = HdbddUtils.processEscapedSymbolName(ctx.name.getText());
    checkForDuplicateName(typeId, currentScope, ctx.name.getLine());
    SymbolTypeEnum symbolTypeEnum = parseToSymbolTypeEnum(ctx.type);
    Symbol newSymbol = createSymbol(typeId, currentScope, schema, ctx.name);
    if (symbolTypeEnum.equals(SymbolTypeEnum.type)) {
      return new DataTypeSymbol(newSymbol);
    }

    throw new CDSRuntimeException(String
        .format("Error at line: %s  - Invalid type provided: '%s' Data type syntax is only allowed for keyword 'type'", ctx.name.getLine(),
            ctx.type.getText()));
  }

  public EntityElementSymbol getEntityElementSymbol(CdsParser.ElementDeclRuleContext ctx, Scope currentScope) {
    String elementId = HdbddUtils.processEscapedSymbolName(ctx.name.getText());
    checkForDuplicateName(elementId, currentScope, ctx.name.getLine());

    EntityElementSymbol elementSymbol = new EntityElementSymbol(elementId, currentScope);
    elementSymbol.setIdToken(ctx.name);
    if (ctx.key != null && !ctx.key.getText().equalsIgnoreCase("key")) {
      throw new CDSRuntimeException(String
          .format("Error at line: %s  - Before an entity element declaration only the 'key' keyword is allowed", ctx.key.getLine()));
    }
    boolean isKey = ctx.key != null;
    elementSymbol.setKey(isKey);

    return elementSymbol;
  }

  public EntityElementSymbol getEntityElementSymbol(CdsParser.FieldDeclRuleContext ctx, Scope currentScope) {
    String elementId = HdbddUtils.processEscapedSymbolName(ctx.ID().getText());
    checkForDuplicateName(elementId, currentScope, ctx.ID().getSymbol().getLine());
    EntityElementSymbol elementSymbol = new EntityElementSymbol(elementId, currentScope);
    elementSymbol.setIdToken(ctx.ID().getSymbol());

    return elementSymbol;
  }

  public EntityElementSymbol getEntityElementSymbol(CdsParser.DataTypeRuleContext ctx, Scope currentScope) {
    String elementId = HdbddUtils.processEscapedSymbolName(ctx.name.getText());
    checkForDuplicateName(elementId, currentScope, ctx.name.getLine());
    EntityElementSymbol elementSymbol = new EntityElementSymbol(elementId, currentScope);
    elementSymbol.setIdToken(ctx.name);
    elementSymbol.setKey(true);

    return elementSymbol;
  }

  public FieldSymbol getFieldSymbol(CdsParser.FieldDeclRuleContext ctx, Scope currentScope) {
    String filedId = HdbddUtils.processEscapedSymbolName(ctx.ID().getText());
    checkForDuplicateName(filedId, currentScope, ctx.ID().getSymbol().getLine());
    FieldSymbol fieldSymbol = new FieldSymbol(filedId, currentScope);
    fieldSymbol.setIdToken(ctx.ID().getSymbol());

    return fieldSymbol;
  }

  public AssociationSymbol getAssociationSymbol(CdsParser.AssociationContext ctx, Scope currentScope) {
    String associationId = HdbddUtils.processEscapedSymbolName(ctx.ascId.getText());
    checkForDuplicateName(associationId, currentScope, ctx.ascId.getLine());

    AssociationSymbol associationSymbol = new AssociationSymbol(associationId, currentScope);
    associationSymbol.setIdToken(ctx.ascId);
    currentScope.define(associationSymbol);

    if (ctx.cardinality() == null) {
      associationSymbol.setCardinality(CardinalityEnum.ONE_TO_ONE);
    }

    return associationSymbol;
  }

  private Symbol createSymbol(String symbolId, Scope currentScope, String schema, Token idToken) {
    Symbol symbol = new Symbol(symbolId, currentScope);
    symbol.setIdToken(idToken);
    symbol.setSchema(schema);

    return symbol;
  }

  private void checkForDuplicateName(String contextId, Scope currentScope, int line) {
    if (currentScope.isDuplicateName(contextId)) {
      throw new CDSRuntimeException(String.format("Error at line: %s  - Duplicate name for: %s", line, contextId));
    }
  }

  private SymbolTypeEnum parseToSymbolTypeEnum(Token artifactType) {
    try {
      return SymbolTypeEnum.valueOf(artifactType.getText().toLowerCase());
    } catch (IllegalArgumentException illegalArgumentException) {
      throw new CDSRuntimeException(
          String.format("Error at line: %s  - '%s' is not a valid artifact type.", artifactType.getLine(), artifactType.getText()));
    }
  }
}
