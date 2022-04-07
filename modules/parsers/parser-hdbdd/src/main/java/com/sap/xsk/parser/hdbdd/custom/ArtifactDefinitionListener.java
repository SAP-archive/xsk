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
package com.sap.xsk.parser.hdbdd.custom;

import com.sap.xsk.parser.hdbdd.annotation.metadata.AbstractAnnotationValue;
import com.sap.xsk.parser.hdbdd.annotation.metadata.AnnotationArray;
import com.sap.xsk.parser.hdbdd.annotation.metadata.AnnotationEnum;
import com.sap.xsk.parser.hdbdd.annotation.metadata.AnnotationObj;
import com.sap.xsk.parser.hdbdd.annotation.metadata.AnnotationSimpleValue;
import com.sap.xsk.parser.hdbdd.core.CdsBaseListener;
import com.sap.xsk.parser.hdbdd.core.CdsParser;
import com.sap.xsk.parser.hdbdd.core.CdsParser.AnnMarkerRuleContext;
import com.sap.xsk.parser.hdbdd.core.CdsParser.AnnObjectRuleContext;
import com.sap.xsk.parser.hdbdd.core.CdsParser.AnnPropertyRuleContext;
import com.sap.xsk.parser.hdbdd.core.CdsParser.AnnValueContext;
import com.sap.xsk.parser.hdbdd.core.CdsParser.ArrRuleContext;
import com.sap.xsk.parser.hdbdd.core.CdsParser.AssignBuiltInTypeWithArgsContext;
import com.sap.xsk.parser.hdbdd.core.CdsParser.AssignHanaTypeContext;
import com.sap.xsk.parser.hdbdd.core.CdsParser.AssignHanaTypeWithArgsContext;
import com.sap.xsk.parser.hdbdd.core.CdsParser.AssignTypeContext;
import com.sap.xsk.parser.hdbdd.core.CdsParser.AssociationConstraintsContext;
import com.sap.xsk.parser.hdbdd.core.CdsParser.AssociationContext;
import com.sap.xsk.parser.hdbdd.core.CdsParser.ContextRuleContext;
import com.sap.xsk.parser.hdbdd.core.CdsParser.DataTypeRuleContext;
import com.sap.xsk.parser.hdbdd.core.CdsParser.ElementConstraintsContext;
import com.sap.xsk.parser.hdbdd.core.CdsParser.ElementDeclRuleContext;
import com.sap.xsk.parser.hdbdd.core.CdsParser.EntityRuleContext;
import com.sap.xsk.parser.hdbdd.core.CdsParser.EnumRuleContext;
import com.sap.xsk.parser.hdbdd.core.CdsParser.FieldDeclRuleContext;
import com.sap.xsk.parser.hdbdd.core.CdsParser.IdentifierContext;
import com.sap.xsk.parser.hdbdd.core.CdsParser.JoinFieldsContext;
import com.sap.xsk.parser.hdbdd.core.CdsParser.JoinRuleContext;
import com.sap.xsk.parser.hdbdd.core.CdsParser.MaxCardinalityContext;
import com.sap.xsk.parser.hdbdd.core.CdsParser.MinMaxCardinalityContext;
import com.sap.xsk.parser.hdbdd.core.CdsParser.NamespaceRuleContext;
import com.sap.xsk.parser.hdbdd.core.CdsParser.NoCardinalityContext;
import com.sap.xsk.parser.hdbdd.core.CdsParser.ObjContext;
import com.sap.xsk.parser.hdbdd.core.CdsParser.SelectRuleContext;
import com.sap.xsk.parser.hdbdd.core.CdsParser.SelectedColumnsRuleContext;
import com.sap.xsk.parser.hdbdd.core.CdsParser.StructuredTypeRuleContext;
import com.sap.xsk.parser.hdbdd.core.CdsParser.UsingRuleContext;
import com.sap.xsk.parser.hdbdd.core.CdsParser.ViewRuleContext;
import com.sap.xsk.parser.hdbdd.core.CdsParser.WhereRuleContext;
import com.sap.xsk.parser.hdbdd.exception.CDSRuntimeException;
import com.sap.xsk.parser.hdbdd.factory.SymbolFactory;
import com.sap.xsk.parser.hdbdd.symbols.CDSLiteralEnum;
import com.sap.xsk.parser.hdbdd.symbols.Symbol;
import com.sap.xsk.parser.hdbdd.symbols.SymbolTable;
import com.sap.xsk.parser.hdbdd.symbols.context.CDSFileScope;
import com.sap.xsk.parser.hdbdd.symbols.context.ContextSymbol;
import com.sap.xsk.parser.hdbdd.symbols.context.Scope;
import com.sap.xsk.parser.hdbdd.symbols.entity.AssociationSymbol;
import com.sap.xsk.parser.hdbdd.symbols.entity.CardinalityEnum;
import com.sap.xsk.parser.hdbdd.symbols.entity.EntityElementSymbol;
import com.sap.xsk.parser.hdbdd.symbols.type.BuiltInTypeSymbol;
import com.sap.xsk.parser.hdbdd.symbols.type.field.FieldSymbol;
import com.sap.xsk.parser.hdbdd.symbols.type.field.Typeable;
import com.sap.xsk.parser.hdbdd.symbols.view.JoinSymbol;
import com.sap.xsk.parser.hdbdd.symbols.view.SelectSymbol;
import com.sap.xsk.parser.hdbdd.symbols.view.ViewSymbol;
import com.sap.xsk.parser.hdbdd.util.HdbddUtils;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.misc.Interval;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

public class ArtifactDefinitionListener extends CdsBaseListener {

  private SymbolTable symbolTable;
  private String fileLocation;
  private String schema;
  private CDSFileScope cdsFileScope = new CDSFileScope();
  private Scope currentScope = cdsFileScope;
  private final ParseTreeProperty<EntityElementSymbol> entityElements = new ParseTreeProperty<>();
  private final ParseTreeProperty<Typeable> typeables = new ParseTreeProperty<>();
  private final ParseTreeProperty<Symbol> symbolsByParseTreeContext = new ParseTreeProperty<>();
  private final Deque<String> fullSymbolNames = new ArrayDeque<>();
  private final ParseTreeProperty<AssociationSymbol> associations = new ParseTreeProperty<>();
  private final ParseTreeProperty<AbstractAnnotationValue> values = new ParseTreeProperty<>();
  private final Set<String> packagesUsed = new HashSet<>();
  private final SymbolFactory symbolFactory = new SymbolFactory();
  private String packageId;
  private static final String NOKEY_ANNOTATION = "nokey";

  @Override
  public void enterNamespaceRule(NamespaceRuleContext ctx) {
    String packageName = ctx.members.stream().map(IdentifierContext::getText).map(HdbddUtils::processEscapedSymbolName)
        .collect(Collectors.joining("."));

    if (!packageName.equals(getExpectedNamespace())) {
      throw new CDSRuntimeException(
          String.format("Error at line: %s. Namespace does not match the file directory.", ctx.members.get(0).start.getLine()));
    }
    this.packageId = packageName;
  }

  @Override
  public void enterUsingRule(UsingRuleContext ctx) {
    String packagePath = ctx.pack.stream().map(IdentifierContext::getText).map(HdbddUtils::processEscapedSymbolName)
        .collect(Collectors.joining("."));
    String memberPath = ctx.members.stream().map(IdentifierContext::getText).map(HdbddUtils::processEscapedSymbolName)
        .collect(Collectors.joining("."));
    String fullSymbolName = packagePath + "::" + memberPath;

    Symbol externalSymbol = this.symbolTable.getSymbol(fullSymbolName);
    if (externalSymbol == null) {
      packagesUsed.add(fullSymbolName);
      return;
    }

    if (ctx.alias == null) {
      cdsFileScope.define(externalSymbol);
    } else {
      cdsFileScope.defineWithCustomName(ctx.alias.getText(), externalSymbol);
    }
  }

  @Override
  public void enterContextRule(ContextRuleContext ctx) {
    Symbol newSymbol = this.symbolFactory.getSymbol(ctx, this.currentScope, this.schema);
    symbolsByParseTreeContext.put(ctx, newSymbol);
    registerSymbolToSymbolTable(newSymbol);
    fullSymbolNames.push(newSymbol.getName());
    this.currentScope = (Scope) newSymbol;
  }

  @Override
  public void exitContextRule(ContextRuleContext ctx) {
    this.currentScope = this.currentScope.getEnclosingScope(); // pop com.sap.xsk.parser.hdbdd.symbols.scope
    validateTopLevelSymbol(this.symbolsByParseTreeContext.get(ctx));
    fullSymbolNames.pop();
  }

  @Override
  public void enterEntityRule(EntityRuleContext ctx) {
    Symbol newSymbol = this.symbolFactory.getSymbol(ctx, this.currentScope, this.schema);
    symbolsByParseTreeContext.put(ctx, newSymbol);
    registerSymbolToSymbolTable(newSymbol);
    fullSymbolNames.push(newSymbol.getName());
    this.currentScope = (Scope) newSymbol;
    this.symbolTable.addEntityToGraph(newSymbol.getFullName());
  }

  @Override
  public void exitEntityRule(EntityRuleContext ctx) {
    this.currentScope = this.currentScope.getEnclosingScope(); // pop com.sap.xsk.parser.hdbdd.symbols.scope
    validateTopLevelSymbol(this.symbolsByParseTreeContext.get(ctx));
    fullSymbolNames.pop();
  }

  @Override
  public void enterDataTypeRule(DataTypeRuleContext ctx) {
    Symbol dataTypeSymbol = this.symbolFactory.getDataTypeSymbol(ctx, this.currentScope, this.schema);
    typeables.put(ctx, (Typeable) dataTypeSymbol);
    symbolsByParseTreeContext.put(ctx, dataTypeSymbol);
    registerSymbolToSymbolTable(dataTypeSymbol);
    this.currentScope.define(dataTypeSymbol);
  }

  @Override
  public void exitDataTypeRule(DataTypeRuleContext ctx) {
    validateTopLevelSymbol(this.symbolsByParseTreeContext.get(ctx));
  }

  @Override
  public void enterStructuredTypeRule(StructuredTypeRuleContext ctx) {
    Symbol newSymbol = this.symbolFactory.getSymbol(ctx, this.currentScope, this.schema);
    symbolsByParseTreeContext.put(ctx, newSymbol);
    registerSymbolToSymbolTable(newSymbol);
    fullSymbolNames.push(newSymbol.getName());
    this.currentScope = (Scope) newSymbol;
  }

  @Override
  public void exitStructuredTypeRule(StructuredTypeRuleContext ctx) {
    this.currentScope = this.currentScope.getEnclosingScope(); // pop com.sap.xsk.parser.hdbdd.symbols.scope
    validateTopLevelSymbol(this.symbolsByParseTreeContext.get(ctx));
    fullSymbolNames.pop();
  }

  @Override
  public void enterElementDeclRule(ElementDeclRuleContext ctx) {
    EntityElementSymbol elementSymbol = this.symbolFactory.getEntityElementSymbol(ctx, this.currentScope);
    this.entityElements.put(ctx, elementSymbol);
    this.symbolsByParseTreeContext.put(ctx, elementSymbol);
    this.typeables.put(ctx, elementSymbol);
  }

  @Override
  public void exitElementDeclRule(ElementDeclRuleContext ctx) {
    EntityElementSymbol elementSymbol = this.entityElements.get(ctx);
    this.currentScope.define(elementSymbol);
  }

  @Override
  public void enterElementConstraints(ElementConstraintsContext ctx) {
    EntityElementSymbol elementSymbol = this.entityElements.get(ctx.getParent().getParent());
    boolean isNotNull = !ctx.getText().equals("null");

    if (!isNotNull && elementSymbol.isKey()) {
      throw new CDSRuntimeException(String.format("Error at line: %s col: %s. Element - part of composite key cannot be null.",
          ctx.start.getLine(),
          ctx.start.getCharPositionInLine()));
    }

    elementSymbol.setNotNull(isNotNull);
  }

  @Override
  public void enterAssociationConstraints(AssociationConstraintsContext ctx) {
    AssociationSymbol associationSymbol = this.associations.get(ctx.getParent());
    boolean isNotNull = !ctx.getText().equals("null");

    if (!isNotNull && associationSymbol.isKey()) {
      throw new CDSRuntimeException(String.format("Error at line: %s col: %s. Association - part of composite key cannot be null.",
          ctx.start.getLine(),
          ctx.start.getCharPositionInLine()));
    }

    associationSymbol.setNotNull(isNotNull);
  }

  @Override
  public void enterFieldDeclRule(FieldDeclRuleContext ctx) {
    FieldSymbol fieldSymbol = this.symbolFactory.getFieldSymbol(ctx, currentScope);
    this.typeables.put(ctx, fieldSymbol);
    this.symbolsByParseTreeContext.put(ctx, fieldSymbol);
    this.currentScope.define(fieldSymbol);
  }

  @Override
  public void enterAssociation(AssociationContext ctx) {
    AssociationSymbol associationSymbol = this.symbolFactory.getAssociationSymbol(ctx, currentScope);

    if(ctx.key != null && ctx.key.getText().equalsIgnoreCase("key")) {
      associationSymbol.setKey(true);
    }

    this.associations.put(ctx, associationSymbol);
  }

  @Override
  public void enterMaxCardinality(MaxCardinalityContext ctx) {
    AssociationSymbol associationSymbol = this.associations.get(ctx.getParent());
    associationSymbol.setCardinality(CardinalityEnum.ONE_TO_MANY);
  }

  @Override
  public void enterNoCardinality(NoCardinalityContext ctx) {
    AssociationSymbol associationSymbol = this.associations.get(ctx.getParent());
    associationSymbol.setCardinality(CardinalityEnum.ONE_TO_MANY);
  }

  @Override
  public void enterMinMaxCardinality(MinMaxCardinalityContext ctx) {
    AssociationSymbol associationSymbol = this.associations.get(ctx.getParent());
    int firstDotIndex = ctx.ASSOCIATION_MIN().getText().indexOf(".");
    String minValue = ctx.ASSOCIATION_MIN().getText().substring(0, firstDotIndex);
    int min = Integer.parseInt(minValue);
    boolean isMaxGreaterThanOne = false;
    if (ctx.max != null) {
      int max = Integer.parseInt(ctx.max.getText());
      if (min > max) {
        throw new CDSRuntimeException(
            String.format("Error at line: %s col: %s. Maximum cardinality should be greater than minimum cardinality",
                ctx.max.getLine(), ctx.max.getCharPositionInLine()));
      } else if (max <= 0) {
        throw new CDSRuntimeException(String.format("Error at line: %s col: %s. Maximum cardinality should be a positive number.",
            ctx.max.getLine(), ctx.max.getCharPositionInLine()));
      }

      isMaxGreaterThanOne = max > 1;
    } else if (ctx.many != null) {
      isMaxGreaterThanOne = true;
    }

    if (min < 0) {
      throw new CDSRuntimeException(String.format("Error at line: %s. Minimum cardinality should be equal or greater than zero.",
          ctx.ASSOCIATION_MIN().getSymbol().getLine()));
    }

    if (min == 0 && !isMaxGreaterThanOne) {
      associationSymbol.setCardinality(CardinalityEnum.ONE_TO_ONE);
    } else if (min == 1 && !isMaxGreaterThanOne) {
      associationSymbol.setCardinality(CardinalityEnum.ONE_TO_ONE);
    } else if (isMaxGreaterThanOne) {
      associationSymbol.setCardinality(CardinalityEnum.ONE_TO_MANY);
    }
  }

  @Override
  public void enterAssignBuiltInTypeWithArgs(AssignBuiltInTypeWithArgsContext ctx) {
    Token typeIdToken = ctx.ref.ID().getSymbol();
    String typeId = typeIdToken.getText();
    Symbol resolvedType = resolveReference(typeId, this.symbolsByParseTreeContext.get(ctx.getParent()));

    assignBuiltInType(resolvedType, ctx.args, typeIdToken, ctx);
    Typeable typeable = typeables.get(ctx.getParent());

    typeable.setReference(ctx.ref.getText());
  }

  @Override
  public void enterAssignType(AssignTypeContext ctx) {
    Typeable typeable = typeables.get(ctx.getParent());
    String fullReference = ctx.pathSubMembers.stream().map(IdentifierContext::getText).map(HdbddUtils::processEscapedSymbolName)
        .collect(Collectors.joining("."));

    typeable.setReference(fullReference);
  }

  @Override
  public void enterAssignHanaType(AssignHanaTypeContext ctx) {
    String hanaType = ctx.hanaType.getText();
    BuiltInTypeSymbol builtInHanaType = this.symbolTable.getHanaType(hanaType);

    Typeable typeable = typeables.get(ctx.getParent());
    if (builtInHanaType == null) {
      throw new CDSRuntimeException(String.format("Error at line: %s. No such hana type found.", ctx.hanaType.getLine()));
    } else {
      typeable.setType(builtInHanaType);
    }
  }

  @Override
  public void enterAssignHanaTypeWithArgs(AssignHanaTypeWithArgsContext ctx) {
    Token typeIdToken = ctx.BUILT_IN_HANA_TYPE().getSymbol();
    String typeId = typeIdToken.getText();
    Symbol resolvedType = this.symbolTable.getHanaType(typeId);

    assignBuiltInType(resolvedType, ctx.args, typeIdToken, ctx);
    Typeable typeable = typeables.get(ctx.getParent());

    typeable.setReference(ctx.hanaType.getText());
  }

  @Override
  public void exitAnnObjectRule(AnnObjectRuleContext ctx) {
    String annId = ctx.identifier().getText();
    AnnotationObj expectedValue = this.symbolTable.getAnnotation(annId);
    AbstractAnnotationValue providedValue = this.values.get(ctx.annValue());
    Symbol symbolToBeAssigned = this.symbolsByParseTreeContext.get(ctx.getParent());
    validateAnnotation(ctx.identifier(), symbolToBeAssigned);
    int expectedAnnKeys = expectedValue.getKeysNumber();

    Symbol annotatedSymbol = this.symbolsByParseTreeContext.get(ctx.getParent());
    if (expectedAnnKeys == 0) {
      throw new CDSRuntimeException(String.format("Error at line: %d col: %d. Values cannot be assigned to annotation %s.",
          ctx.identifier().start.getLine(), ctx.identifier().start.getCharPositionInLine(), annId));
    } else if (annotatedSymbol.getAnnotation(annId) != null) {
      throw new CDSRuntimeException(String.format("Error at line: %d col: %d. Annotation %s already assigned for %s.",
          ctx.identifier().start.getLine(), ctx.identifier().start.getCharPositionInLine(), annId, symbolToBeAssigned.getFullName()));
    } else if (providedValue instanceof AnnotationObj) {
      AnnotationObj providedAnnObject = (AnnotationObj) providedValue;
      compareAnnValues(providedValue, expectedValue);
      providedAnnObject.setName(annId);
      annotatedSymbol.addAnnotation(providedAnnObject.getName(), providedAnnObject);
    } else {
      if (expectedAnnKeys != 1) { //Only one field is available and could be directly assigned
        throw new CDSRuntimeException(String.format("Error at line: %d col: %d. Invalid value type provided for annotation %s.",
            ctx.identifier().start.getLine(), ctx.identifier().start.getCharPositionInLine(), annId));
      }

      AbstractAnnotationValue expectedFieldValue = expectedValue.getKeyValuePairs().values().stream().collect(Collectors.toList()).get(0);
      compareAnnValues(providedValue, expectedFieldValue);

      AnnotationObj annotationToBeAssigned = new AnnotationObj();
      annotationToBeAssigned.setName(annId);
      Optional<String> expectedValueKeySet = expectedValue.getKeyValuePairs().keySet().stream().findFirst();

      if (!expectedValueKeySet.isEmpty()) {
        String fieldName = expectedValueKeySet.get();
        annotationToBeAssigned.define(fieldName, providedValue);
      }

      annotatedSymbol.addAnnotation(annotationToBeAssigned.getName(), annotationToBeAssigned);
    }
  }

  @Override
  public void exitAnnPropertyRule(AnnPropertyRuleContext ctx) {
    String annId = ctx.annId.getText();
    String annProperty = ctx.prop.getText();
    Symbol symbolToBeAssigned = this.symbolsByParseTreeContext.get(ctx.getParent());
    AnnotationObj expectedAnnObject = this.symbolTable.getAnnotation(annId);
    validateAnnotation(ctx.annId, symbolToBeAssigned);

    AbstractAnnotationValue expectedFieldValue = expectedAnnObject.getValue(annProperty);
    AnnotationObj assignedAnnotation = symbolToBeAssigned.getAnnotation(annId);
    if (expectedFieldValue == null) {
      throw new CDSRuntimeException(String.format("Error at line: %d col: %d. No field with name: %s exists in annotation %s..",
          ctx.prop.start.getLine(), ctx.prop.start.getCharPositionInLine(), annProperty, annId));
    } else if (assignedAnnotation != null && assignedAnnotation.getValue(annProperty) != null) {
      throw new CDSRuntimeException(String.format("Error at line: %d col: %d. Value for property name: %s has already been provided.",
          ctx.prop.start.getLine(), ctx.prop.start.getCharPositionInLine(), annProperty));
    }

    AbstractAnnotationValue providedValue = this.values.get(ctx.annValue());
    compareAnnValues(providedValue, expectedFieldValue);
    if (assignedAnnotation == null) {
      assignedAnnotation = new AnnotationObj();
      assignedAnnotation.setName(annId);
      assignedAnnotation.define(annProperty, providedValue);
      symbolToBeAssigned.addAnnotation(assignedAnnotation.getName(), assignedAnnotation);
    } else {
      assignedAnnotation.define(annProperty, providedValue);
    }
  }

  @Override
  public void exitAnnMarkerRule(AnnMarkerRuleContext ctx) {
    String annId = ctx.identifier().getText();
    AnnotationObj expectedAnnObject = this.symbolTable.getAnnotation(annId);
    Symbol symbolToBeAssigned = this.symbolsByParseTreeContext.get(ctx.getParent());
    IdentifierContext annIdToken = ctx.identifier();
    validateAnnotation(annIdToken, symbolToBeAssigned);

    if (expectedAnnObject.getKeysNumber() != 0) {
      throw new CDSRuntimeException(String.format("Error at line: %d col: %d.Annotation with name: %s cannot be used as a marker.",
          annIdToken.start.getLine(), annIdToken.start.getCharPositionInLine(), annId));
    }
    if (annId.equals(NOKEY_ANNOTATION)) {
      for (int i = 0; i <= ctx.getParent().children.size(); i++) {
        if (ctx.getParent().getChild(i) instanceof ElementDeclRuleContext && ctx.getParent().getChild(i).getChild(0).getText()
            .equalsIgnoreCase("key")) {
          throw new CDSRuntimeException(
              String.format("Error at line: %d col: %d. Annotation %s has been specified for entity with keys.", annIdToken.start.getLine(),
                  annIdToken.start.getCharPositionInLine(), expectedAnnObject.getName()));
        }
      }
    }

    AnnotationObj annToAssign = new AnnotationObj();
    annToAssign.setName(annId);
    symbolToBeAssigned.addAnnotation(annToAssign.getName(), annToAssign);
  }

  @Override
  public void enterViewRule(ViewRuleContext ctx) {
    Symbol newSymbol = this.symbolFactory.getSymbol(ctx, this.currentScope, this.schema);
    symbolsByParseTreeContext.put(ctx, newSymbol);
    registerSymbolToSymbolTable(newSymbol);
    fullSymbolNames.push(newSymbol.getName());
    this.currentScope = (Scope) newSymbol;
    ((ViewSymbol) newSymbol).setPackageId(this.packageId);
    ((ViewSymbol) newSymbol).setContext(((ContextSymbol) currentScope.getEnclosingScope()).getName());
    this.symbolTable.addViewToGraph(newSymbol.getFullName());
  }

  @Override
  public void exitViewRule(ViewRuleContext ctx) {
    this.currentScope = this.currentScope.getEnclosingScope(); // pop com.sap.xsk.parser.hdbdd.symbols.scope
    fullSymbolNames.pop();
  }

  @Override
  public void enterSelectRule(SelectRuleContext ctx) {
    SelectSymbol selectSymbol = new SelectSymbol();

    SelectedColumnsRuleContext selectedColumnsRuleContext = ctx.selectedColumnsRule();
    if (selectedColumnsRuleContext.children != null) {
      int a = selectedColumnsRuleContext.start.getStartIndex();
      int b = selectedColumnsRuleContext.stop.getStopIndex();
      Interval selectedColumnsRuleSqlInterval = new Interval(a, b);
      selectSymbol.setColumnsSql(ctx.start.getInputStream().getText(selectedColumnsRuleSqlInterval));
    }

    WhereRuleContext whereRuleContext = ctx.whereRule();
    if (whereRuleContext != null) {
      int a = whereRuleContext.start.getStartIndex();
      int b = whereRuleContext.stop.getStopIndex();
      Interval whereRuleSqlInterval = new Interval(a, b);
      selectSymbol.setWhereSql(ctx.start.getInputStream().getText(whereRuleSqlInterval));
    }

    if (ctx.isDistinct != null) {
      selectSymbol.setDistinct(true);
    }

    if (ctx.isUnion != null) {
      selectSymbol.setUnion(true);
    }

    if (ctx.dependsOnTable != null) {
      selectSymbol.setDependsOnTable(handleStringLiteral(ctx.dependsOnTable.getText()));
    }

    if (ctx.dependingTableAlias != null) {
      selectSymbol.setDependingTableAlias(handleStringLiteral(ctx.dependingTableAlias.getText()));
    }

    this.currentScope.define(selectSymbol);
    selectSymbol.setScope(this.currentScope);
    this.currentScope = selectSymbol;
  }

  @Override
  public void exitSelectRule(SelectRuleContext ctx) {
    this.currentScope = this.currentScope.getEnclosingScope(); // pop com.sap.xsk.parser.hdbdd.symbols.scope
  }

  @Override
  public void enterJoinRule(JoinRuleContext ctx) {
    JoinSymbol joinSymbol = new JoinSymbol();

    joinSymbol.setJoinType(ctx.joinType.getText());

    if (ctx.joinArtifactName != null) {
      String joinArtifactName = handleStringLiteral(ctx.joinArtifactName.getText());
      joinSymbol.setJoinArtifactName(joinArtifactName);
      this.symbolTable.addChildToView(((ViewSymbol) this.currentScope.getEnclosingScope()).getFullName(),
          ((ViewSymbol) this.currentScope.getEnclosingScope()).getPackageId() + "::"
              + ((ViewSymbol) this.currentScope.getEnclosingScope()).getContext() + "." + joinArtifactName);
    }

    if (ctx.joinTableAlias != null) {
      joinSymbol.setJoinTableAlias(handleStringLiteral(ctx.joinTableAlias.getText()));
    }

    JoinFieldsContext joinFieldsContext = ctx.joinFields();
    if (joinFieldsContext.children != null) {
      int a = joinFieldsContext.start.getStartIndex();
      int b = joinFieldsContext.stop.getStopIndex();
      Interval joinFieldsSqlInterval = new Interval(a, b);
      joinSymbol.setJoinFields(ctx.start.getInputStream().getText(joinFieldsSqlInterval));
    }

    this.currentScope.define(joinSymbol);
  }

  @Override
  public void exitAnnValue(AnnValueContext ctx) {
    if (ctx.literal != null) {
      AbstractAnnotationValue annotationValue = new AnnotationSimpleValue(ctx.literal.getType());
      annotationValue.setValue(ctx.getText());
      this.values.put(ctx, annotationValue);
    }
  }

  @Override
  public void enterEnumRule(EnumRuleContext ctx) {
    AnnotationEnum annotationEnum = new AnnotationEnum();
    annotationEnum.setValue(ctx.identifier().ID().getText());
    this.values.put(ctx.getParent(), annotationEnum);
  }

  @Override
  public void exitArrRule(ArrRuleContext ctx) {
    AnnotationArray annotationArray = new AnnotationArray(CDSLiteralEnum.ENUM.getLiteralType());
    ctx.annValue().forEach(valueCtx -> annotationArray.addValue(this.values.get(valueCtx)));
    this.values.put(ctx.getParent(), annotationArray);
  }

  @Override
  public void exitObj(ObjContext ctx) {
    AnnotationObj annotationObj = new AnnotationObj();
    for (CdsParser.KeyValueContext keyValueCtx :
        ctx.keyValue()) {
      String key = keyValueCtx.identifier().ID().getText();
      AbstractAnnotationValue value = this.values.get(keyValueCtx.annValue());
      annotationObj.define(key, value);
    }

    this.values.put(ctx.getParent(), annotationObj);
  }

  public Symbol resolveReference(String referencedId, Symbol referencingSymbol) {
    if (symbolTable.getGlobalBuiltInTypeScope().resolve(referencedId) != null) {
      return symbolTable.getGlobalBuiltInTypeScope().resolve(referencedId);
    }

    Symbol resolvedTypeSymbol = referencingSymbol.getScope().resolve(referencedId);

    if (resolvedTypeSymbol == null && referencingSymbol instanceof EntityElementSymbol) {
      resolvedTypeSymbol = referencingSymbol.getScope().getEnclosingScope().resolve(referencedId);
      if (resolvedTypeSymbol == null) {
        throw new CDSRuntimeException(
            String.format("Error at line: %s col: %s - No such type: %s", referencingSymbol.getIdToken().start.getLine(),
                referencingSymbol.getIdToken().start.getLine(), referencedId));
      }

      return resolvedTypeSymbol;
    }

    return resolvedTypeSymbol;
  }

  private void validateAnnotation(IdentifierContext annIdToken, Symbol symbol) {
    String annId = annIdToken.getText();
    AnnotationObj annotationObj = this.symbolTable.getAnnotation(annId);

    if (annotationObj == null) {
      throw new CDSRuntimeException(String
          .format("Error at line: %d col: %d. Annotation with name: %s not supported.", annIdToken.start.getLine(),
              annIdToken.start.getCharPositionInLine(), annId));
    }

    if (annotationObj.getAllowedForSymbols().stream().filter(t -> symbol.getClass().equals(t)).findAny().isEmpty()) {
      throw new CDSRuntimeException(
          String.format("Error at line: %d col: %d. Annotation %s not allowed for %s", annIdToken.start.getLine(),
              annIdToken.start.getCharPositionInLine(), annotationObj.getName(), symbol.getFullName()));
    } else if (annotationObj.isTopLevel() && !(symbol.getScope() instanceof CDSFileScope)) {
      throw new CDSRuntimeException(
          String.format("Error at line: %d col: %d. Annotation %s is only allowed for top level entities.", annIdToken.start.getLine(),
              annIdToken.start.getCharPositionInLine(), annotationObj.getName()));
    }
  }

  private void assignBuiltInType(Symbol builtInType, List<Token> args, Token typeIdToken, ParserRuleContext ctx) {
    String typeId = typeIdToken.getText();
    if (builtInType == null) {
      throw new CDSRuntimeException(String.format("Error at line: %d col: %d. No such type:  %s.",
          typeIdToken.getLine(), typeIdToken.getCharPositionInLine(), typeId));
    } else if (!(builtInType instanceof BuiltInTypeSymbol)) {
      throw new CDSRuntimeException(String.format("Error at line: %d col: %d. Constructor usage is only allowed for some built in types.",
          typeIdToken.getLine(), typeIdToken.getCharPositionInLine()));
    }
    BuiltInTypeSymbol resolvedBuiltInType = (BuiltInTypeSymbol) builtInType;
    BuiltInTypeSymbol builtInTypeToProvide = new BuiltInTypeSymbol(builtInType.getName(), resolvedBuiltInType.getArgsCount(),
        resolvedBuiltInType.getValueType());
    builtInTypeToProvide.setHanaType(resolvedBuiltInType.isHanaType());

    if (resolvedBuiltInType.getArgsCount() != args.size()) {
      throw new CDSRuntimeException(String.format("Error at line: %d col: %d. Invalid number of constructor arguments passed.",
          typeIdToken.getLine(), typeIdToken.getCharPositionInLine()));
    }

    args.forEach(t ->
        builtInTypeToProvide.addArgValue(Integer.parseInt(t.getText())));
    Typeable typeable = typeables.get(ctx.getParent());

    typeable.setType(builtInTypeToProvide);
  }

  private void compareAnnValues(AbstractAnnotationValue providedValue, AbstractAnnotationValue expectedValue) {
    if (providedValue.getClass() != expectedValue.getClass()) {
      throw new CDSRuntimeException("ERROR: Invalid value type provided");
    }

    if (providedValue instanceof AnnotationObj) {
      AnnotationObj providedObj = (AnnotationObj) providedValue;
      AnnotationObj expectedObj = (AnnotationObj) expectedValue;
      Set<String> keys = expectedObj.getKeyValuePairs().keySet();
      keys.forEach(k -> {
        AbstractAnnotationValue providedKeyValue = providedObj.getValue(k);
        AbstractAnnotationValue expectedKeyValue = expectedObj.getValue(k);

        if (providedKeyValue != null) {
          compareAnnValues(providedKeyValue, expectedKeyValue);
        }

      });
    } else if (providedValue instanceof AnnotationArray) {
      AnnotationArray providedArray = (AnnotationArray) providedValue;
      AnnotationArray expectedArray = (AnnotationArray) expectedValue;

      AbstractAnnotationValue expectedArrayValueType = expectedArray.getArrValueType();
      providedArray.getValues().forEach(v -> compareAnnValues(v, expectedArrayValueType));
    } else if (providedValue instanceof AnnotationEnum) {
      AnnotationEnum providedEnumValue = (AnnotationEnum) providedValue;
      AnnotationEnum expectedEnumValue = (AnnotationEnum) expectedValue;
      if (!expectedEnumValue.hasValue(providedEnumValue.getValue())) {
        throw new CDSRuntimeException("ERROR: No such value available for enum!");
      }
    }
  }

  private void registerSymbolToSymbolTable(Symbol symbol) {
    String fullName;
    if (fullSymbolNames.isEmpty()) {
      fullName = symbol.getName();
    } else {
      fullName = fullSymbolNames.stream().collect(Collectors.joining(".")) + "." + symbol.getName();
    }

    symbol.setFullName(this.packageId + "::" + fullName);
    symbolTable.addSymbol(symbol);
  }

  private void validateTopLevelSymbol(Symbol symbol) {
    if (!(symbol.getScope() instanceof CDSFileScope)) {
      return;
    }

    AnnotationObj schemaAnnotation = symbol.getAnnotation("Schema");
    if (schemaAnnotation == null) {
      throw new CDSRuntimeException(String.format("Error at line: %s. Missing '@Schema' annotation for top level symbol definition: %s",
          symbol.getIdToken().start.getLine(), symbol.getName()));
    } else {
      AnnotationSimpleValue nameValue = (AnnotationSimpleValue) schemaAnnotation.getValue("name");
      this.schema = nameValue.getValue();
      if (symbol instanceof ContextSymbol) {
        ((ContextSymbol) symbol).getSymbols().forEach((k, v) ->
            v.setSchema(this.schema)
        );
      } else {
        symbol.setSchema(this.schema);
      }
    }

    if (!symbol.getName().equals(getTopLevelSymbolExpectedName())) {
      throw new CDSRuntimeException(String.format("Error at line: %s. Top level symbol name does not match the filename.",
          symbol.getIdToken().start.getLine()));
    }
  }

  private String getTopLevelSymbolExpectedName() {
    String[] splitFileLocation = this.fileLocation.split("/");
    String fileName = splitFileLocation[splitFileLocation.length - 1];

    return fileName.split("\\.")[0];
  }

  private String getExpectedNamespace() {
    String[] splitFileLocation = this.fileLocation.split("/");
    splitFileLocation = Arrays.stream(splitFileLocation).filter(s -> !s.isEmpty()).toArray(String[]::new);
    splitFileLocation = Arrays.copyOfRange(splitFileLocation, 0, splitFileLocation.length - 1);
    return String.join(".", splitFileLocation);
  }

  public ParseTreeProperty<EntityElementSymbol> getEntityElements() {
    return entityElements;
  }

  public ParseTreeProperty<Typeable> getTypeables() {
    return typeables;
  }

  public CDSFileScope getCdsFileScope() {
    return cdsFileScope;
  }

  public void setCdsFileScope(CDSFileScope cdsFileScope) {
    this.cdsFileScope = cdsFileScope;
  }

  public void setSymbolTable(SymbolTable symbolTable) {
    this.symbolTable = symbolTable;
  }

  public ParseTreeProperty<AssociationSymbol> getAssociations() {
    return associations;
  }

  public Set<String> getPackagesUsed() {
    return this.packagesUsed;
  }

  public void setFileLocation(String fileLocation) {
    this.fileLocation = fileLocation;
  }

  private String handleStringLiteral(String value) {
    if (value.length() > 0 && value.charAt(0) == '"' && value.charAt(value.length() - 1) == '"') {
      String subStr = value.substring(1, value.length() - 1);
      String escapedQuote = subStr.replace("\\\"", "\"");
      return escapedQuote.replace("\\\\", "\\");
    }
    return value;
  }
}