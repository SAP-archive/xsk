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

import com.sap.xsk.parser.hdbdd.annotation.metadata.AbstractAnnotationValue;
import com.sap.xsk.parser.hdbdd.annotation.metadata.AnnotationArray;
import com.sap.xsk.parser.hdbdd.annotation.metadata.AnnotationEnum;
import com.sap.xsk.parser.hdbdd.annotation.metadata.AnnotationObj;
import com.sap.xsk.parser.hdbdd.annotation.metadata.AnnotationSimpleValue;
import com.sap.xsk.parser.hdbdd.core.CdsBaseListener;
import com.sap.xsk.parser.hdbdd.core.CdsParser;
import com.sap.xsk.parser.hdbdd.exception.CDSRuntimeException;
import com.sap.xsk.parser.hdbdd.symbols.CDSLiteralEnum;
import com.sap.xsk.parser.hdbdd.symbols.Package;
import com.sap.xsk.parser.hdbdd.symbols.Symbol;
import com.sap.xsk.parser.hdbdd.symbols.SymbolTable;
import com.sap.xsk.parser.hdbdd.symbols.context.ContextSymbol;
import com.sap.xsk.parser.hdbdd.symbols.context.CDSFileScope;
import com.sap.xsk.parser.hdbdd.symbols.context.Scope;
import com.sap.xsk.parser.hdbdd.symbols.entity.AssociationSymbol;
import com.sap.xsk.parser.hdbdd.symbols.entity.CardinalityEnum;
import com.sap.xsk.parser.hdbdd.symbols.entity.EntityElementSymbol;
import com.sap.xsk.parser.hdbdd.symbols.entity.EntitySymbol;
import com.sap.xsk.parser.hdbdd.symbols.type.BuiltInTypeSymbol;
import com.sap.xsk.parser.hdbdd.symbols.type.custom.CustomDataType;
import com.sap.xsk.parser.hdbdd.symbols.type.custom.DataTypeSymbol;
import com.sap.xsk.parser.hdbdd.symbols.type.custom.StructuredDataTypeSymbol;
import com.sap.xsk.parser.hdbdd.symbols.type.field.FieldSymbol;
import com.sap.xsk.parser.hdbdd.symbols.type.field.Typeable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

public class EntityDefinitionListener extends CdsBaseListener {
    private SymbolTable symbolTable;
    private CDSFileScope cdsFileScope = new CDSFileScope();
    private Scope currentScope = cdsFileScope;
    private final ParseTreeProperty<EntityElementSymbol> entityElements = new ParseTreeProperty<>();
    private final ParseTreeProperty<Typeable> typeables = new ParseTreeProperty<>();
    private final ParseTreeProperty<Symbol> symbolsByParseTreeContext = new ParseTreeProperty<>();
    private final List<CustomDataType> dataTypes = new ArrayList<>();
    private final List<EntitySymbol> entities = new ArrayList<>();
    private final Stack<String> fullSymbolName = new Stack<>();
    private final ParseTreeProperty<AssociationSymbol> associations = new ParseTreeProperty<>();
    private final ParseTreeProperty<AbstractAnnotationValue> values = new ParseTreeProperty<>();
    private final Set<String> packagesUsed = new HashSet<>();
    private String packageId;

    @Override
    public void enterNamespaceRule(CdsParser.NamespaceRuleContext ctx) {
        String packageName = ctx.members.stream().map(Token::getText).collect(Collectors.joining("."));
        this.packageId = packageName;
    }

    @Override
    public void enterUsingRule(CdsParser.UsingRuleContext ctx) {
        String fullSymbolName = ctx.members.stream().map(Token::getText).collect(Collectors.joining("."));

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
    public void enterContextRule(CdsParser.ContextRuleContext ctx) {
        String contextId = ctx.ID().getText();
        if (checkForDuplicateName(contextId, ctx.ID().getSymbol().getLine())) return;

        ContextSymbol contextSymbol = new ContextSymbol(contextId, currentScope);
        currentScope.define(contextSymbol);
        symbolsByParseTreeContext.put(ctx, contextSymbol);
        registerSymbolToSymbolTable(contextSymbol);
        fullSymbolName.push(contextSymbol.getName());
        this.currentScope = contextSymbol; //push com.sap.xsk.parser.hdbdd.symbols.scope
    }

    @Override
    public void exitContextRule(CdsParser.ContextRuleContext ctx) {
        this.currentScope = this.currentScope.getEnclosingScope(); // pop com.sap.xsk.parser.hdbdd.symbols.scope
        fullSymbolName.pop();
    }

    @Override
    public void enterStructuredDataTypeRule(CdsParser.StructuredDataTypeRuleContext ctx) {
        String typeId = ctx.ID().getText();
        if (checkForDuplicateName(typeId, ctx.ID().getSymbol().getLine())) return;

        StructuredDataTypeSymbol dataTypeSymbol = new StructuredDataTypeSymbol(typeId, this.currentScope);
        this.dataTypes.add(dataTypeSymbol);
        this.currentScope.define(dataTypeSymbol);
        this.symbolsByParseTreeContext.put(ctx, dataTypeSymbol);
        registerSymbolToSymbolTable(dataTypeSymbol);
        this.currentScope = dataTypeSymbol;
    }

    @Override
    public void exitStructuredDataTypeRule(CdsParser.StructuredDataTypeRuleContext ctx) {
        this.currentScope = this.currentScope.getEnclosingScope();
    }

    @Override
    public void enterDataTypeRule(CdsParser.DataTypeRuleContext ctx) {
        String typeId = ctx.ID().getText();
        if (this.currentScope.isDuplicateName(typeId)) {
            System.out.println("Duplicate name for: " + typeId);
        }

        DataTypeSymbol dataTypeSymbol = new DataTypeSymbol(typeId, this.currentScope);

        typeables.put(ctx, dataTypeSymbol);
        symbolsByParseTreeContext.put(ctx, dataTypeSymbol);
        registerSymbolToSymbolTable(dataTypeSymbol);
        this.currentScope.define(dataTypeSymbol);
    }

    @Override
    public void enterEntityRule(CdsParser.EntityRuleContext ctx) {
        String typeId = ctx.ID().getText();
        if (checkForDuplicateName(typeId, ctx.ID().getSymbol().getLine())) return;

        EntitySymbol entitySymbol = new EntitySymbol(typeId, this.currentScope);
        this.currentScope.define(entitySymbol);
        this.symbolsByParseTreeContext.put(ctx, entitySymbol);
        this.currentScope = entitySymbol;
        registerSymbolToSymbolTable(entitySymbol);

        this.fullSymbolName.push(entitySymbol.getName());
        this.entities.add(entitySymbol);
        this.symbolTable.addEntityToGraph(entitySymbol.getFullName());
    }

    @Override
    public void exitEntityRule(CdsParser.EntityRuleContext ctx) {
        this.currentScope = this.currentScope.getEnclosingScope();
        this.fullSymbolName.pop();
    }

    @Override
    public void enterElementDeclRule(CdsParser.ElementDeclRuleContext ctx) {
        String elementId = ctx.ID().getText();
        if (checkForDuplicateName(elementId, ctx.ID().getSymbol().getLine())) {
            return;
        }

        EntityElementSymbol elementSymbol = new EntityElementSymbol(elementId, this.currentScope);
        boolean isKey = ctx.key != null;
        elementSymbol.setKey(isKey);
        this.entityElements.put(ctx, elementSymbol);
        this.symbolsByParseTreeContext.put(ctx, elementSymbol);
        this.typeables.put(ctx, elementSymbol);
    }

    @Override
    public void exitElementDeclRule(CdsParser.ElementDeclRuleContext ctx) {
        EntityElementSymbol elementSymbol = this.entityElements.get(ctx);

        this.currentScope.define(elementSymbol);
    }

    @Override
    public void enterElementConstraints(CdsParser.ElementConstraintsContext ctx) {
        EntityElementSymbol elementSymbol = this.entityElements.get(ctx.getParent());
        boolean isNotNull = ctx.NULL() == null;
        if (!isNotNull && elementSymbol.isKey()) {
            System.out.println("Element part of composite key cannot be null.");
        }

        elementSymbol.setNotNull(isNotNull);
    }

    @Override
    public void enterFieldDeclRule(CdsParser.FieldDeclRuleContext ctx) {
        String filedId = ctx.ID().getText();
        if (this.currentScope.isDuplicateName(filedId)) {
            System.out.println("Duplicate name for: " + filedId);
        }

        FieldSymbol fieldSymbol = new FieldSymbol(filedId, this.currentScope);
        this.typeables.put(ctx, fieldSymbol);
        this.symbolsByParseTreeContext.put(ctx, fieldSymbol);
        this.currentScope.define(fieldSymbol);
    }

    @Override
    public void enterAssociation(CdsParser.AssociationContext ctx) {
        String associationId = ctx.ID().getText();
        if (this.currentScope.isDuplicateName(associationId)) {
            System.out.println("Duplicate name for: " + associationId);
        }

        AssociationSymbol associationSymbol = new AssociationSymbol(associationId, this.currentScope);
        if (ctx.cardinality() == null) {
            associationSymbol.setCardinality(CardinalityEnum.ONE_TO_ONE);
        }

        this.associations.put(ctx, associationSymbol);
        this.currentScope.define(associationSymbol);
    }

    @Override
    public void enterMaxCardinality(CdsParser.MaxCardinalityContext ctx) {
        AssociationSymbol associationSymbol = this.associations.get(ctx.getParent());
        associationSymbol.setCardinality(CardinalityEnum.ONE_TO_MANY);
    }

    @Override
    public void enterNoCardinality(CdsParser.NoCardinalityContext ctx) {
        AssociationSymbol associationSymbol = this.associations.get(ctx.getParent());
        associationSymbol.setCardinality(CardinalityEnum.ONE_TO_MANY);
    }

    @Override
    public void enterMinMaxCardinality(CdsParser.MinMaxCardinalityContext ctx) {
        AssociationSymbol associationSymbol = this.associations.get(ctx.getParent());
        int firstDotIndex = ctx.ASSOCIATION_MIN().getText().indexOf(".");
        String minValue = ctx.ASSOCIATION_MIN().getText().substring(0, firstDotIndex);
        int min = Integer.parseInt(minValue);
        boolean isMaxGreaterThanOne = false;
        if (ctx.max != null) {
            int max = Integer.parseInt(ctx.max.getText());
            if (min > max) {
                System.out.println("Maximum cardinality should be greater than minimum cardinality");
                return;
            } else if (max <= 0) {
                System.out.println("Maximum cardinality should be a positive number.");
                return;
            }

            isMaxGreaterThanOne = max > 1;
        } else if (ctx.many != null) {
            isMaxGreaterThanOne = true;
        }

        if (min < 0) {
            System.out.println("Minimum cardinality should be equal or greater than zero.");
            return;
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
    public void enterAssignBuiltInTypeWithArgs(CdsParser.AssignBuiltInTypeWithArgsContext ctx) {
        Token typeIdToken = ctx.ID().getSymbol();
        String typeId = typeIdToken.getText();
        Symbol resolvedType = resolveReference(typeId, this.symbolsByParseTreeContext.get(ctx.getParent()));
        if (resolvedType == null) {
            throw new CDSRuntimeException(String.format("Error at line: %d col: %d. No such type:  %s.",
                    typeIdToken.getLine(), typeIdToken.getCharPositionInLine(), typeId));
        } else if (!(resolvedType instanceof BuiltInTypeSymbol)) {
            throw new CDSRuntimeException(String.format("Error at line: %d col: %d. Constructor usage is only allowed for some built in types.",
                    typeIdToken.getLine(), typeIdToken.getCharPositionInLine()));
        }
        BuiltInTypeSymbol resolvedBuiltInType = (BuiltInTypeSymbol) resolvedType;
        BuiltInTypeSymbol builtInTypeToProvide = new BuiltInTypeSymbol(typeId, resolvedBuiltInType.getArgsCount(), resolvedBuiltInType.getValueType());
        if (resolvedBuiltInType.getArgsCount() != ctx.args.size()) {
            throw new CDSRuntimeException(String.format("Error at line: %d col: %d. Invalid number of constructor arguments passed.",
                    typeIdToken.getLine(), typeIdToken.getCharPositionInLine()));
        }

        ctx.args.forEach(t ->
                builtInTypeToProvide.addArgValue(Integer.parseInt(t.getText())));
        Typeable typeable = typeables.get(ctx.getParent());

        typeable.setType(builtInTypeToProvide);
        typeable.setReference(ctx.ref.getText());
    }

    @Override
    public void enterAssignType(CdsParser.AssignTypeContext ctx) {
        Typeable typeable = typeables.get(ctx.getParent());
        String fullReference = ctx.pathSubMembers.stream().map(Token::getText).collect(Collectors.joining("."));
        if (ctx.pathSubMembers.size() == 1) {
            Symbol resolvedType = resolveReference(fullReference, (Symbol) typeable);
            if (resolvedType instanceof BuiltInTypeSymbol) {
                typeable.setType((BuiltInTypeSymbol) resolvedType);
            }
        }

        typeable.setReference(fullReference);
    }

    @Override
    public void exitAnnObjectRule(CdsParser.AnnObjectRuleContext ctx) {
        String annId = ctx.ID().getText();
        AnnotationObj expectedValue = this.symbolTable.getAnnotation(annId);
        AbstractAnnotationValue providedValue = this.values.get(ctx.annValue());
        Symbol symbolToBeAssigned = this.symbolsByParseTreeContext.get(ctx.getParent());
        validateAnnotation(ctx.ID().getSymbol(), symbolToBeAssigned);
        int expectedAnnKeys = expectedValue.getKeysNumber();

        Symbol annotatedSymbol = this.symbolsByParseTreeContext.get(ctx.getParent());
        if (expectedAnnKeys == 0) {
            throw new CDSRuntimeException(String.format("Error at line: %d col: %d. Values cannot be assigned to annotation %s.",
                    ctx.ID().getSymbol().getLine(), ctx.ID().getSymbol().getCharPositionInLine(), annId));
        } else if (annotatedSymbol.getAnnotation(annId) != null) {
            throw new CDSRuntimeException(String.format("Error at line: %d col: %d. Annotation %s already assigned for %s.",
                    ctx.ID().getSymbol().getLine(), ctx.ID().getSymbol().getCharPositionInLine(), annId, symbolToBeAssigned.getFullName()));
        } else if (providedValue instanceof AnnotationObj) {
            AnnotationObj providedAnnObject = (AnnotationObj) providedValue;
            compareAnnValues(providedValue, expectedValue);
            providedAnnObject.setName(annId);
            annotatedSymbol.addAnnotation(providedAnnObject.getName(), providedAnnObject);
        } else {
            if (expectedAnnKeys != 1) { //Only one field is available and could be directly assigned
                throw new CDSRuntimeException(String.format("Error at line: %d col: %d. Invalid value type provided for annotation %s.",
                        ctx.ID().getSymbol().getLine(), ctx.ID().getSymbol().getCharPositionInLine(), annId));
            }

            AbstractAnnotationValue expectedFieldValue = expectedValue.getKeyValuePairs().values().stream().collect(Collectors.toList()).get(0);
            compareAnnValues(providedValue, expectedFieldValue);

            AnnotationObj annotationToBeAssigned = new AnnotationObj();
            annotationToBeAssigned.setName(annId);
            String fieldName = expectedValue.getKeyValuePairs().keySet().stream().findFirst().get();
            annotationToBeAssigned.define(fieldName, providedValue);
            annotatedSymbol.addAnnotation(annotationToBeAssigned.getName(), annotationToBeAssigned);
        }
    }

    @Override
    public void exitAnnPropertyRule(CdsParser.AnnPropertyRuleContext ctx) {
        String annId = ctx.annId.getText();
        String annProperty = ctx.prop.getText();
        Symbol symbolToBeAssigned = this.symbolsByParseTreeContext.get(ctx.getParent());
        AnnotationObj expectedAnnObject = this.symbolTable.getAnnotation(annId);
        validateAnnotation(ctx.annId, symbolToBeAssigned);

        AbstractAnnotationValue expectedFieldValue = expectedAnnObject.getValue(annProperty);
        AnnotationObj assignedAnnotation = symbolToBeAssigned.getAnnotation(annId);
        if (expectedFieldValue == null) {
            throw new CDSRuntimeException(String.format("Error at line: %d col: %d. No field with name: %s exists in annotation %s..",
                    ctx.prop.getLine(), ctx.prop.getCharPositionInLine(), annProperty, annId));
        } else if (assignedAnnotation != null && assignedAnnotation.getValue(annProperty) != null) {
            throw new CDSRuntimeException(String.format("Error at line: %d col: %d. Value for property name: %s has already been provided.",
                     ctx.prop.getLine(), ctx.prop.getCharPositionInLine(), annProperty));
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
    public void exitAnnMarkerRule(CdsParser.AnnMarkerRuleContext ctx) {
        String annId = ctx.ID().getText();
        AnnotationObj expectedAnnObject = this.symbolTable.getAnnotation(annId);
        Symbol symbolToBeAssigned = this.symbolsByParseTreeContext.get(ctx.getParent());
        validateAnnotation(ctx.ID().getSymbol(), symbolToBeAssigned);

        if (expectedAnnObject.getKeysNumber() != 0) {
            throw new CDSRuntimeException(String.format("Error at line: %d col: %d.Annotation with name: %s cannot be used as a marker.",
                    ctx.ID().getSymbol().getLine(), ctx.ID().getSymbol().getCharPositionInLine(), annId));
        }

        AnnotationObj annToAssign = new AnnotationObj();
        annToAssign.setName(annId);
        symbolToBeAssigned.addAnnotation(annToAssign.getName(), annToAssign);
    }

    private void validateAnnotation(Token annIdToken, Symbol symbol) {
        String annId = annIdToken.getText();
        AnnotationObj annotationObj = this.symbolTable.getAnnotation(annId);
        Optional<Class> allowedSymbols = annotationObj.getAllowedForSymbols().stream().filter(t -> symbol.getClass().equals(t)).findAny();

        if (annotationObj == null) {
            throw new CDSRuntimeException(String.format("Error at line: %d col: %d. Annotation with name: %s not supported.", annIdToken.getLine(), annIdToken.getCharPositionInLine(), annId));
        } else if (allowedSymbols.isEmpty()) {
            throw new CDSRuntimeException(
                    String.format("Error at line: %d col: %d. Annotation %s not allowed for %s", annIdToken.getLine(), annIdToken.getCharPositionInLine(), annotationObj.getName(), symbol.getFullName()));
        } else if (annotationObj.isTopLevel() && !(symbol.getScope() instanceof CDSFileScope)) {
            throw new CDSRuntimeException(
                    String.format("Error at line: %d col: %d. Annotation %s is only allowed for top level entities.", annIdToken.getLine(), annIdToken.getCharPositionInLine(), annotationObj.getName()));
        }
    }

    private void compareAnnValues(AbstractAnnotationValue providedValue, AbstractAnnotationValue expectedValue) {
        if (providedValue.getClass() != expectedValue.getClass()) {
            System.out.println("ERROR: Invalid value type provided");
            return;
        }

        if (providedValue instanceof AnnotationObj) {
            AnnotationObj providedObj = (AnnotationObj) providedValue;
            AnnotationObj expectedObj = (AnnotationObj) expectedValue;
            Set<String> keys = expectedObj.getKeyValuePairs().keySet();
            keys.forEach(k -> {
                AbstractAnnotationValue providedKeyValue = providedObj.getValue(k);
                AbstractAnnotationValue expectedKeyValue = expectedObj.getValue(k);
                if (providedKeyValue == null) {
                    System.out.println("ERROR: Missing mandatory field declaration!");
                    return;
                }

                compareAnnValues(providedKeyValue, expectedKeyValue);
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
                System.out.println("ERROR: No such value available for enum!");
            }
        }
    }

    @Override
    public void exitAnnValue(CdsParser.AnnValueContext ctx) {
        if (ctx.literal != null) {
            AbstractAnnotationValue annotationValue = new AnnotationSimpleValue(ctx.literal.getType());
            annotationValue.setValue(ctx.getText());
            this.values.put(ctx, annotationValue);
        }
    }

    @Override
    public void enterEnumRule(CdsParser.EnumRuleContext ctx) {
        AnnotationEnum annotationEnum = new AnnotationEnum();
        annotationEnum.setValue(ctx.getText());
        this.values.put(ctx.getParent(), annotationEnum);
    }

    @Override
    public void exitArrRule(CdsParser.ArrRuleContext ctx) {
        AnnotationArray annotationArray = new AnnotationArray(CDSLiteralEnum.ENUM.getLiteralType());
        ctx.annValue().forEach(valueCtx -> annotationArray.addValue(this.values.get(valueCtx)));
        this.values.put(ctx.getParent(), annotationArray);
    }

    @Override
    public void exitObj(CdsParser.ObjContext ctx) {
        AnnotationObj annotationObj = new AnnotationObj();
        for (CdsParser.KeyValueContext keyValueCtx :
                ctx.keyValue()) {
            String key = keyValueCtx.ID().getText();
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

        if (resolvedTypeSymbol == null && referencingSymbol instanceof FieldSymbol) {
            resolvedTypeSymbol = referencingSymbol.getScope().getEnclosingScope().resolve(referencedId);
            if (resolvedTypeSymbol == null) {
                System.out.println("No such type: " + referencedId);
            }

            return resolvedTypeSymbol;
        }

        return resolvedTypeSymbol;
    }

    public void registerSymbolToSymbolTable(Symbol symbol) {
        String fullName;
        if (fullSymbolName.isEmpty()) {
            fullName = symbol.getName();
        } else {
            fullName = fullSymbolName.stream().collect(Collectors.joining(".")) + "." + symbol.getName();
        }

        symbol.setFullName(this.packageId + "::" + fullName);
        symbolTable.addSymbol(symbol);
    }

    public ParseTreeProperty<Symbol> getSymbolsByParseTreeContext() {
        return this.symbolsByParseTreeContext;
    }

    public Scope getCurrentScope() {
        return currentScope;
    }

    public ParseTreeProperty<EntityElementSymbol> getEntityElements() {
        return entityElements;
    }

    public ParseTreeProperty<Typeable> getTypeables() {
        return typeables;
    }

    private boolean checkForDuplicateName(String contextId, int line) {
        if (this.currentScope.isDuplicateName(contextId)) {
            System.out.println("Duplicate name for: " + contextId + " At line: " + line);
            return true;
        }

        return false;
    }

    public void setSymbolTable(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
    }

    public ParseTreeProperty<AssociationSymbol> getAssociations() {
        return associations;
    }

  public Set<String> getPackagesUsed() {
    return packagesUsed;
  }
}
