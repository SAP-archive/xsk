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
package com.sap.xsk.parser.hdbdd.custom;

import com.sap.xsk.parser.hdbdd.core.CdsBaseListener;
import com.sap.xsk.parser.hdbdd.core.CdsParser;
import com.sap.xsk.parser.hdbdd.core.CdsParser.UsingRuleContext;
import com.sap.xsk.parser.hdbdd.exception.CDSRuntimeException;
import com.sap.xsk.parser.hdbdd.symbols.Package;
import com.sap.xsk.parser.hdbdd.symbols.Symbol;
import com.sap.xsk.parser.hdbdd.symbols.SymbolTable;
import com.sap.xsk.parser.hdbdd.symbols.context.CDSFileScope;
import com.sap.xsk.parser.hdbdd.symbols.context.Scope;
import com.sap.xsk.parser.hdbdd.symbols.entity.AssociationSymbol;
import com.sap.xsk.parser.hdbdd.symbols.entity.EntityElementSymbol;
import com.sap.xsk.parser.hdbdd.symbols.entity.EntitySymbol;
import com.sap.xsk.parser.hdbdd.symbols.type.BuiltInTypeSymbol;
import com.sap.xsk.parser.hdbdd.symbols.type.Type;
import com.sap.xsk.parser.hdbdd.symbols.type.field.FieldSymbol;
import com.sap.xsk.parser.hdbdd.symbols.type.field.Typeable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

public class ReferenceResolvingListener extends CdsBaseListener {

  private SymbolTable symbolTable;
  private CDSFileScope cdsFileScope;
  private ParseTreeProperty<Symbol> symbolsByParseTreeContext;
  private ParseTreeProperty<EntityElementSymbol> entityElements;
  private ParseTreeProperty<Typeable> typeables;
  private ParseTreeProperty<AssociationSymbol> associations;

  @Override
  public void enterUsingRule(UsingRuleContext ctx) {
    String packageName = ctx.pack.stream().map(Token::getText).collect(Collectors.joining("."));
    String fullSymbolName = ctx.members.stream().map(Token::getText).collect(Collectors.joining("."));

    Symbol externalSymbol = this.symbolTable.getSymbol(fullSymbolName);
    if (externalSymbol == null) {
      throw new CDSRuntimeException(String.format(
          "Error at line: %d col: %d. Non existing entity in package: %s::%s",
          ctx.ID.getLine(), ctx.ID.getCharPositionInLine(), packageName, fullSymbolName));
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
    setResolvedType(true, (Typeable) referencingSymbol, resolvedTypeSymbol);
  }

  @Override
  public void enterDefaultValue(CdsParser.DefaultValueContext ctx) {
    int valueType = ctx.value.getType();
    BuiltInTypeSymbol typeOfElement = (BuiltInTypeSymbol) this.entityElements.get(ctx.getParent()).getType();

    if (valueType != typeOfElement.getValueType()) {
      System.out.println("Incompatible types! Expected " + typeOfElement.getName() + ". Provided " + ctx.value.getText());
      return;
    }

    EntityElementSymbol elementSymbol = this.entityElements.get(ctx.getParent());
    elementSymbol.setValue(ctx.value.getText());
    //TODO: Extract value
  }

  @Override
  public void enterAssociationTarget(CdsParser.AssociationTargetContext ctx) {
    AssociationSymbol associationSymbol = this.associations.get(ctx.getParent());

    Set<Symbol> nonResolvedRefSymbols = new HashSet<>();
    nonResolvedRefSymbols.add(associationSymbol);
    String reference = ctx.pathSubMembers.stream().map(Token::getText).collect(Collectors.joining("."));
    Symbol resolvedSymbol = resolveReferenceChain(reference, associationSymbol, nonResolvedRefSymbols);
    if (!(resolvedSymbol instanceof EntitySymbol)) {
      System.out.println("The provided reference is not valid. Entity should be proviced!");
      return;
    }

    associationSymbol.setTarget((EntitySymbol) resolvedSymbol);
    EntitySymbol entity = (EntitySymbol) associationSymbol.getScope();
    this.symbolTable.addChildToEntity(entity.getFullName(), resolvedSymbol.getFullName());
  }

  @Override
  public void enterForeignKey(CdsParser.ForeignKeyContext ctx) {
    AssociationSymbol associationSymbol = this.associations.get(ctx.getParent().getParent());
    String entityName = associationSymbol.getTarget().getFullName();
    String reference = ctx.pathSubMembers.stream().map(Token::getText).collect(Collectors.joining("."));
    String refFullPath = entityName + "." + reference;
    Symbol resolvedSymbol = resolveReferenceChain(refFullPath, associationSymbol, new HashSet<>(Arrays.asList(associationSymbol)));

    if (resolvedSymbol == null) {
      System.out.println(String.format("ERROR: No such field found in entity: %s", refFullPath));
    } else if (!(resolvedSymbol instanceof EntityElementSymbol)) {
      System.out.println("ERROR: Only an entity element could be referenced as a foreign key!");
      return;
    }

    associationSymbol.addForeignKey((EntityElementSymbol) resolvedSymbol);
  }

  @Override
  public void enterUnmanagedForeignKey(CdsParser.UnmanagedForeignKeyContext ctx) {
    AssociationSymbol associationSymbol = this.associations.get(ctx.getParent());
    String targetFullName = associationSymbol.getTarget().getFullName();
    String reference = ctx.pathSubMembers.stream().map(Token::getText).collect(Collectors.joining("."));
    String refFullPath = targetFullName + "." + reference;
    Symbol resolvedTargetSymbol = resolveReferenceChain(refFullPath, associationSymbol, new HashSet<>(Arrays.asList(associationSymbol)));
    if (resolvedTargetSymbol == null) {
      System.out.println(String.format("ERROR: No such field found in entity: %s", refFullPath));
    } else if (!(resolvedTargetSymbol instanceof EntityElementSymbol)) {
      System.out.println("ERROR: Only an entity element could be referenced as a foreign key!");
      return;
    }

    EntitySymbol associationHolder = (EntitySymbol) associationSymbol.getScope();
    Symbol resolvedSourceSymbol = associationHolder.resolve(ctx.source.getText());
    if (resolvedSourceSymbol == null) {
      System.out.println(String.format("ERROR: No such field: %s found in: %s", ctx.source.getText(), associationHolder.getName()));
    }
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
          scopeToExplore = (Scope) resolvedSubMemberField;
        } else {
          //TODO: Should throw meaningful error
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
          throw new RuntimeException("Circular dependency");
        }
      }
    }

    return resolvedSubMember;
  }

  private void setResolvedType(boolean isTypeOfUsed, Typeable typeable, Symbol resolvedSymbol) {
    if (resolvedSymbol == null) {
      System.out.println("Invalid type found");
    } else if (resolvedSymbol instanceof EntitySymbol) {
      System.out.println("Entities could not be used as a type.");
    } else if (resolvedSymbol instanceof Typeable && !isTypeOfUsed) {
      System.out.println("The reference provided is not a type, but a field!");
    } else if (resolvedSymbol instanceof Type) {
      typeable.setType((Type) resolvedSymbol);
    } else if (resolvedSymbol instanceof Typeable && isTypeOfUsed) {
      typeable.setType(((Typeable) resolvedSymbol).getType());
    } else if (resolvedSymbol instanceof AssociationSymbol) {
      System.out.println("Association field is not allowed as a reference. Here a type is required!");
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
        throw new RuntimeException("Circular dependency");
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
        System.out.println("No such type: " + referencedId);
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

  public void setSymbolsByParseTreeContext(ParseTreeProperty<Symbol> symbolsByParseTreeContext) {
    this.symbolsByParseTreeContext = symbolsByParseTreeContext;
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
