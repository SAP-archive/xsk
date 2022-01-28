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
package com.sap.xsk.parser.hdbdd.symbols.type.field;

import com.sap.xsk.parser.hdbdd.annotation.metadata.AnnotationObj;
import com.sap.xsk.parser.hdbdd.symbols.Symbol;
import com.sap.xsk.parser.hdbdd.symbols.context.Scope;
import com.sap.xsk.parser.hdbdd.symbols.type.Type;
import org.antlr.v4.runtime.Token;
import java.util.Map;
import com.sap.xsk.parser.hdbdd.symbols.type.custom.DataTypeSymbol;

public class FieldSymbol extends Symbol implements Typeable {

  private Type type;
  private String reference;

  public FieldSymbol(String name) {
    super(name);
  }

  public FieldSymbol(String name, Scope scope) {
    super(name, scope);
  }

  public FieldSymbol(Type type, String reference, String name, Scope scope, Token idToken, String fullName,
      Map<String, AnnotationObj> annotations, String schema) {
    super(name, scope, idToken, fullName, annotations, schema);
    this.type = type;
    this.reference = reference;
  }

  @Override
  public Type getType() {
    if (type instanceof DataTypeSymbol){
      return ((DataTypeSymbol) type).getType();
    }else {
      return type;
    }
  }

  @Override
  public void setType(Type type) {
    this.type = type;
  }

  @Override
  public String getReference() {
    return reference;
  }

  @Override
  public void setReference(String token) {
    this.reference = token;
  }
}