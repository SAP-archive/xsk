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
package com.sap.xsk.parser.hdbdd.custom;

import com.sap.xsk.parser.hdbdd.core.CdsBaseListener;
import com.sap.xsk.parser.hdbdd.core.CdsParser;
import com.sap.xsk.parser.hdbdd.core.CdsParser.ManagedForeignKeysContext;
import com.sap.xsk.parser.hdbdd.core.CdsParser.UnmanagedForeignKeyContext;
import com.sap.xsk.parser.hdbdd.core.CdsParser.UsingRuleContext;
import com.sap.xsk.parser.hdbdd.exception.CDSRuntimeException;
import com.sap.xsk.parser.hdbdd.symbols.Symbol;
import com.sap.xsk.parser.hdbdd.symbols.SymbolTable;
import com.sap.xsk.parser.hdbdd.symbols.context.CDSFileScope;
import com.sap.xsk.parser.hdbdd.symbols.context.Scope;
import com.sap.xsk.parser.hdbdd.symbols.entity.AssociationSymbol;
import com.sap.xsk.parser.hdbdd.symbols.entity.CardinalityEnum;
import com.sap.xsk.parser.hdbdd.symbols.entity.EntityElementSymbol;
import com.sap.xsk.parser.hdbdd.symbols.entity.EntitySymbol;
import com.sap.xsk.parser.hdbdd.symbols.type.BuiltInTypeSymbol;
import com.sap.xsk.parser.hdbdd.symbols.type.Type;
import com.sap.xsk.parser.hdbdd.symbols.type.field.FieldSymbol;
import com.sap.xsk.parser.hdbdd.symbols.type.field.Typeable;
import com.sap.xsk.parser.hdbdd.util.HdbddUtils;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ReferenceResolvingListener extends CdsBaseListener {

  private static final String UNMANAGED_ASSOCIATION_MARKER = "@";

  private SymbolTable symbolTable;
  private CDSFileScope cdsFileScope;
  private ParseTreeProperty<EntityElementSymbol> entityElements;
  private ParseTreeProperty<Typeable> typeables;
  private ParseTreeProperty<AssociationSymbol> associations;

  @Override
  public void enterUsingRule(UsingRuleContext ctx) {
    String packagePath = ctx.pack.stream().map(Token::getText).map(HdbddUtils::processEscapedSymbolName).collect(Collectors.joining("."));
    String memberPath = ctx.members.stream().map(Token::getText).map(HdbddUtils::processEscapedSymbolName).collect(Collectors.joining("."));
    String fullSymbolName = packagePath + "::" + memberPath;
    Symbol externalSymbol = this.symbolTable.getSymbol(fullSymbolName);
    if (externalSymbol == null) {
      throw new CDSRuntimeException(String.format(
          "Error at line: %d col: %d. Non existing entity in package: %s::%s",
          ctx.ID.getLine(), ctx.ID.getCharPositionInLine(), packagePath, fullSymbolName));
    }

    if (ctx.alias == null) {
      cdsFileScope.define(externalSymbol);
    } else {
      cdsFileScope.defineWithCustomName(ctx.alias.getText(), externalSymbol);
    }
  }

  @Override
  public void enterAssignType(CdsParser.AssignTypeContext ctx) {
    Typeable referencingSymbol = typeables.get(ctx.getParent());
    if (referencingSymbol.getType() != null) {
      return;
    }

    Set<Symbol> nonResolvedRefSymbols = new HashSet<>();
    nonResolvedRefSymbols.add((Symbol) referencingSymbol);
    Symbol resolvedTypeSymbol = resolveReferenceChain(referencingSymbol.getReference(), (Symbol) referencingSymbol, nonResolvedRefSymbols);
    setResolvedType(ctx.TYPE_OF() != null, referencingSymbol, resolvedTypeSymbol);
  }

  @Override
  public void enterDefaultValue(CdsParser.DefaultValueContext ctx) {
    int valueType = ctx.value.getType();
    BuiltInTypeSymbol typeOfElement = (BuiltInTypeSymbol) this.entityElements.get(ctx.getParent().getParent()).getType();

    //if the default value is se to null, ignore it
    if (ctx.NULL() == null) {
      if (typeOfElement.getValueType().stream().filter(el -> el.equals(valueType)).count() < 0) {
        throw new CDSRuntimeException(String.format(
            "Error at line: %d col: %d. Incompatible types! Expected %s, Provided %s",
            ctx.value.getLine(), ctx.value.getCharPositionInLine(), typeOfElement.getName(), ctx.value.getText()));
      }

      EntityElementSymbol elementSymbol = this.entityElements.get(ctx.getParent().getParent());
      elementSymbol.setValue(ctx.value.getText());
    }
  }

  @Override
  public void enterAssociationTarget(CdsParser.AssociationTargetContext ctx) {
    AssociationSymbol associationSymbol = this.associations.get(ctx.getParent());

    Set<Symbol> nonResolvedRefSymbols = new HashSet<>();
    nonResolvedRefSymbols.add(associationSymbol);
    String reference = ctx.pathSubMembers.stream().map(Token::getText).map(HdbddUtils::processEscapedSymbolName)
        .collect(Collectors.joining("."));
    associationSymbol.setReference(reference);

    Symbol resolvedSymbol = resolveReferenceChain(reference, associationSymbol, nonResolvedRefSymbols);
    if (!(resolvedSymbol instanceof EntitySymbol)) {
      throw new CDSRuntimeException(String.format(
          "Error at line: %d col: %d. The provided reference must be an Entity!",
          resolvedSymbol.getIdToken().getLine(), resolvedSymbol.getIdToken().getCharPositionInLine()));
    }

    associationSymbol.setTarget((EntitySymbol) resolvedSymbol);
  }

  @Override
  public void exitAssociation(@NotNull CdsParser.AssociationContext ctx) {
    AssociationSymbol associationSymbol = this.associations.get(ctx);

    if (ctx.getChild(ManagedForeignKeysContext.class, 0) == null
        && ctx.getChild(UnmanagedForeignKeyContext.class, 0) == null) {
      List<EntityElementSymbol> associationKeys = associationSymbol.getTarget().getKeys();
      associationSymbol.setForeignKeys(associationKeys);
      associationSymbol.setManaged(true);
    }
  }

  @Override
  public void enterForeignKey(CdsParser.ForeignKeyContext ctx) {
    AssociationSymbol associationSymbol = this.associations.get(ctx.getParent().getParent());
    associationSymbol.setManaged(true);
    String entityName = associationSymbol.getReference();
    String reference = ctx.pathSubMembers.stream().map(Token::getText).map(HdbddUtils::processEscapedSymbolName)
        .collect(Collectors.joining("."));
    String refFullPath = entityName + "." + reference;
    Symbol resolvedSymbol = resolveReferenceChain(refFullPath, associationSymbol, new HashSet<>(Arrays.asList(associationSymbol)));

    if (resolvedSymbol == null) {
      throw new CDSRuntimeException(String.format(
          "Error at line: %s. No such field found in entity: %s.",
          associationSymbol.getIdToken().getLine(), refFullPath));
    } else if (!(resolvedSymbol instanceof EntityElementSymbol)) {
      throw new CDSRuntimeException(String.format(
          "Error at line: %s. Only an entity element could be referenced as a foreign key.",
          resolvedSymbol.getIdToken().getLine()));
    }

    associationSymbol.addForeignKey((EntityElementSymbol) resolvedSymbol);
    EntitySymbol entity = (EntitySymbol) associationSymbol.getScope();
    this.symbolTable.addChildToEntity(entity.getFullName(), ((Symbol) resolvedSymbol.getScope()).getFullName());
  }

  @Override
  public void enterUnmanagedForeignKey(CdsParser.UnmanagedForeignKeyContext ctx) {
    AssociationSymbol associationSymbol = this.associations.get(ctx.getParent());
    associationSymbol.setManaged(false);
    String targetFullName = associationSymbol.getTarget().getName();
    String reference = ctx.pathSubMembers.stream().skip(1).map(Token::getText).map(HdbddUtils::processEscapedSymbolName)
        .collect(Collectors.joining("."));

    String refFullPath = targetFullName + "." + reference;
    Symbol resolvedTargetSymbol = resolveReferenceChain(refFullPath, associationSymbol, new HashSet<>(Arrays.asList(associationSymbol)));
    if (resolvedTargetSymbol == null) {
      throw new CDSRuntimeException(String.format(
          "Error at line: %s. No such field found in entity: %s.",
          associationSymbol.getIdToken().getLine(), refFullPath));
    } else if (!(resolvedTargetSymbol instanceof EntityElementSymbol)) {
      System.out.println("ERROR: Only an entity element could be referenced as a foreign key!");
      throw new CDSRuntimeException(String.format(
          "Error at line: %s. Only an Element of an Entity could be referenced as a foreign key.",
          associationSymbol.getIdToken().getLine()));
    }

    EntitySymbol associationHolder = (EntitySymbol) associationSymbol.getScope();
    EntityElementSymbol resolvedSourcePkElement = (EntityElementSymbol) associationHolder.resolve(ctx.source.getText());
    if (resolvedSourcePkElement == null || !resolvedSourcePkElement.isKey()) {
      throw new CDSRuntimeException(String.format(
          "Error at line: %s. No such field: %s found in: %s or the field is not a primary key.",
          ctx.source.getLine(), ctx.source.getText(), associationSymbol.getTarget().getName()));
    }

    AssociationSymbol targetAssociation = new AssociationSymbol(UNMANAGED_ASSOCIATION_MARKER + reference);
    targetAssociation.setManaged(false);
    targetAssociation.setTarget((EntitySymbol) associationSymbol.getScope());
    targetAssociation.setCardinality(CardinalityEnum.ONE_TO_ONE);
    targetAssociation.setScope(resolvedTargetSymbol.getScope());
    targetAssociation.setIdToken(resolvedTargetSymbol.getIdToken());
    targetAssociation.addForeignKey(resolvedSourcePkElement);

    EntitySymbol targetEntity = associationSymbol.getTarget();
    targetEntity.define(targetAssociation);

    this.symbolTable.addChildToEntity(targetEntity.getFullName(), associationHolder.getFullName());
  }

  private Symbol resolveReferenceChain(String reference, Symbol referencingSymbol, Set<Symbol> nonResolvedRefSymbols) {
    String[] splitReference = reference.split("\\.");

    String firstMember = splitReference[0];
    Symbol resolvedSubMember = resolveReference(firstMember, referencingSymbol);

    for (int i = 1; i < splitReference.length; i++) {
      String member = splitReference[i];

      if (resolvedSubMember instanceof BuiltInTypeSymbol) {
        return null;
      } else if (resolvedSubMember instanceof FieldSymbol) {
        Scope scopeToExplore;
        FieldSymbol resolvedSubMemberField = (FieldSymbol) resolvedSubMember;
        Symbol resolvedSubMemberType = resolveSimpleReference(resolvedSubMember, nonResolvedRefSymbols);
        if (resolvedSubMemberField.getType() == null) {
          setResolvedType(true, resolvedSubMemberField, resolvedSubMemberType);
        }

        if (resolvedSubMemberType instanceof Scope) {
          scopeToExplore = (Scope) resolvedSubMemberType;
        } else {
          return null;
        }

        resolvedSubMember = scopeToExplore.resolve(member);

      } else if (resolvedSubMember instanceof Scope) {
        resolvedSubMember = ((Scope) resolvedSubMember).resolve(member);
      }
    }

    if (resolvedSubMember instanceof FieldSymbol) {
      FieldSymbol resolvedSubMemberField = (FieldSymbol) resolvedSubMember;
      Symbol resolvedMemberType = (Symbol) resolvedSubMemberField.getType();

      if (resolvedMemberType == null) {
        if (!nonResolvedRefSymbols.contains(resolvedSubMember)) {
          nonResolvedRefSymbols.add(resolvedSubMemberField);
          resolvedMemberType = resolveReferenceChain(resolvedSubMemberField.getReference(), resolvedSubMemberField, nonResolvedRefSymbols);
          setResolvedType(true, resolvedSubMemberField, resolvedMemberType);
        } else {
          throw new CDSRuntimeException("Circular dependency");
        }
      }
    }

    return resolvedSubMember;
  }

  private void setResolvedType(boolean isTypeOfUsed, Typeable typeable, Symbol resolvedSymbol) {
    Symbol typeableSymbol = (Symbol) typeable;
    if (resolvedSymbol == null) {
      throw new CDSRuntimeException(String.format("Error at line: %s. No such type existing.", typeableSymbol.getIdToken().getLine()));
    } else if (resolvedSymbol instanceof EntitySymbol) {
      throw new CDSRuntimeException(
          String.format("Error at line: %s. Entities could not be used as a type.", typeableSymbol.getIdToken().getLine()));
    } else if (resolvedSymbol instanceof Type) {
      typeable.setType((Type) resolvedSymbol);
    } else if (resolvedSymbol instanceof Typeable && !isTypeOfUsed) {
      throw new CDSRuntimeException(
          String.format("Error at line: %s. The reference provided is not a type, but a field. Use 'type of' keyword.",
              typeableSymbol.getIdToken().getLine()));
    } else if (resolvedSymbol instanceof Typeable) {
      typeable.setType(((Typeable) resolvedSymbol).getType());
    } else if (resolvedSymbol instanceof AssociationSymbol) {
      throw new CDSRuntimeException(String.format("Error at line: %s. Association field is not allowed as a reference, a type is required.",
          typeableSymbol.getIdToken().getLine()));
    }
  }

  private Symbol resolveSimpleReference(Symbol resolvedMember, Set<Symbol> nonResolvedRefSymbols) {
    FieldSymbol resolvedField = (FieldSymbol) resolvedMember;
    Symbol resolvedFieldType = (Symbol) resolvedField.getType();

    if (resolvedFieldType == null) {
      if (!nonResolvedRefSymbols.contains(resolvedField)) {
        nonResolvedRefSymbols.add(resolvedField);
        return resolveReferenceChain(resolvedField.getReference(), resolvedField, nonResolvedRefSymbols);
      } else {
        throw new CDSRuntimeException(String.format("Circular dependency for field: %s", resolvedField.getName()));
      }
    }

    return resolvedFieldType;
  }

  public Symbol resolveReference(String referencedId, Symbol referencingSymbol) {
    if (symbolTable.getGlobalBuiltInTypeScope().resolve(referencedId) != null) {
      return symbolTable.getGlobalBuiltInTypeScope().resolve(referencedId);
    }

    Symbol resolvedTypeSymbol = referencingSymbol.getScope().resolve(referencedId);

    if (resolvedTypeSymbol == null &&
        (referencingSymbol instanceof FieldSymbol || referencingSymbol instanceof AssociationSymbol)) {
      resolvedTypeSymbol = referencingSymbol.getScope().getEnclosingScope().resolve(referencedId);
      if (resolvedTypeSymbol == null) {
        System.out.println("No such type found: " + referencedId);
        throw new CDSRuntimeException(
            String.format("Error at line: %s. No such type found: %s", referencingSymbol.getIdToken().getLine(), referencedId));
      }

      return resolvedTypeSymbol;
    }

    return resolvedTypeSymbol;
  }

  public void setEntityElements(ParseTreeProperty<EntityElementSymbol> entityElements) {
    this.entityElements = entityElements;
  }

  public void setTypeables(ParseTreeProperty<Typeable> typeables) {
    this.typeables = typeables;
  }

  public void setAssociations(ParseTreeProperty<AssociationSymbol> associations) {
    this.associations = associations;
  }

  public void setSymbolTable(SymbolTable symbolTable) {
    this.symbolTable = symbolTable;
  }

  public void setCdsFileScope(CDSFileScope cdsFileScope) {
    this.cdsFileScope = cdsFileScope;
  }
}
