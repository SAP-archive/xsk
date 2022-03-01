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

public class ViewSymbol extends Symbol implements Scope {

  private String joinSql;
  private String columnsSql;


  public String getJoinSql() {
    return joinSql;
  }

  public void setJoinSql(String joinSql) {
    this.joinSql = joinSql;
  }

  public String getColumnsSql() {
    return columnsSql;
  }

  public void setColumnsSql(String columnsSql) {
    this.columnsSql = columnsSql;
  }

  public ViewSymbol(Symbol symbol) {
    super(symbol);
  }

  @Override
  public Scope getEnclosingScope() {
    return this.getScope();
  }

  @Override
  public void define(Symbol sym) {

  }

  @Override
  public Symbol resolve(String name) {
    return null;
  }

  @Override
  public boolean isDuplicateName(String id) {
    return false;
  }
}
