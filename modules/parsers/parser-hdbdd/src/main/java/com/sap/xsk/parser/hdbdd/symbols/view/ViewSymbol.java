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
package com.sap.xsk.parser.hdbdd.symbols.view;

import com.sap.xsk.parser.hdbdd.symbols.Symbol;
import com.sap.xsk.parser.hdbdd.symbols.context.Scope;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ViewSymbol extends Symbol implements Scope {

  List<Symbol> selectStatements = new ArrayList<>();
  String packageId;
  String context;

  public ViewSymbol(Symbol symbol) {
    super(symbol);
  }

  public List<Symbol> getSelectStatements() {
    return selectStatements;
  }

  public String getPackageId() {
    return packageId;
  }

  public void setPackageId(String packageId) {
    this.packageId = packageId;
  }

  public String getContext() {
    return context;
  }

  public void setContext(String context) {
    this.context = context;
  }

  @Override
  public Scope getEnclosingScope() {
    return this.getScope();
  }

  @Override
  public void define(Symbol sym) {
      selectStatements.add(sym);
  }

  @Override
  public Symbol resolve(String name) {
    return null;
  }

  @Override
  public boolean isDuplicateName(String id) {
    return false;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    if (!super.equals(o))
      return false;
    ViewSymbol that = (ViewSymbol) o;
    return selectStatements.equals(that.selectStatements) && packageId.equals(that.packageId) && context.equals(that.context);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), selectStatements, packageId, context);
  }
}
