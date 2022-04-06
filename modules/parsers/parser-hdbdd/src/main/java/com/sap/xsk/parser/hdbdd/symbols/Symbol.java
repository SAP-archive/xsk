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
package com.sap.xsk.parser.hdbdd.symbols;

import com.sap.xsk.parser.hdbdd.annotation.metadata.AnnotationObj;
import com.sap.xsk.parser.hdbdd.core.CdsParser.IdentifierContext;
import com.sap.xsk.parser.hdbdd.symbols.context.Scope;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.antlr.v4.runtime.Token;

/***
 * Excerpted from "Language Implementation Patterns",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/tpdsl for more book information.
 ***/
public class Symbol { // A generic programming language symbol

  private String fullName;
  private String name;      // All com.sap.xsk.parser.hdbdd.symbols at least have a name
  private Scope scope;
  private IdentifierContext idToken;
  private String schema;
  private Map<String, AnnotationObj> annotations;
  String alias;

  public Symbol(Symbol symbol) {
    this(symbol.getName(), symbol.getScope());
    this.idToken = symbol.getIdToken();
    this.schema = symbol.getSchema();
  }

  public Symbol(String name) {
    this.name = name;
    this.annotations = new HashMap<>();
  }

  public Symbol(String name, Scope scope) {
    this(name);
    this.scope = scope;
  }

  public Symbol() {
  }

  public Symbol(String name, Scope scope, IdentifierContext idToken, String fullName, Map<String, AnnotationObj> annotations, String schema) {
    this.name = name;
    this.scope = scope;
    this.idToken = idToken;
    this.fullName = fullName;
    this.annotations = annotations;
    this.schema = schema;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Scope getScope() {
    return scope;
  }

  public void setScope(Scope scope) {
    this.scope = scope;
  }

  public IdentifierContext getIdToken() {
    return idToken;
  }

  public void setIdToken(IdentifierContext idToken) {
    this.idToken = idToken;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getSchema() {
    return schema;
  }

  public void setSchema(String schema) {
    this.schema = schema;
  }

  public Map<String, AnnotationObj> getAnnotations() {
    return annotations;
  }

  public void addAnnotation(String name, AnnotationObj annotationObj) {
    this.annotations.put(name, annotationObj);
  }

  public AnnotationObj getAnnotation(String name) {
    return this.annotations.get(name);
  }

  public String getAlias() {
    return alias;
  }

  public void setAlias(String alias) {
    this.alias = alias;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Symbol symbol = (Symbol) o;
    return name.equals(symbol.name) && scope.equals(symbol.scope);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, scope);
  }
}