/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2022 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.parser.hdbdd.symbols.type;

import com.sap.xsk.parser.hdbdd.symbols.Symbol;
import java.util.ArrayList;
import java.util.List;

public class BuiltInTypeSymbol extends Symbol implements Type {

  private int argsCount;
  private List<Integer> valueType;
  private boolean isHanaType;
  private List<Integer> argsValues;

  public BuiltInTypeSymbol(String name, int argsCount, List<Integer> valueType) {
    this(name, valueType);
    this.argsCount = argsCount;
  }

  public BuiltInTypeSymbol(String name, int argsCount, List<Integer> valueType, boolean isHanaType) {
    this(name, argsCount, valueType);
    this.isHanaType = isHanaType;
  }

  public BuiltInTypeSymbol(String name, List<Integer> valueType) {
    super(name);
    this.argsValues = new ArrayList<>();
    this.valueType = valueType;
  }

  public void addArgValue(int value) {
    this.argsValues.add(value);
  }

  public int getArgsCount() {
    return argsCount;
  }

  public List<Integer> getValueType() {
    return valueType;
  }

  public boolean isHanaType() {
    return isHanaType;
  }

  public void setHanaType(boolean hanaType) {
    isHanaType = hanaType;
  }

  public List<Integer> getArgsValues() {
    return argsValues;
  }
}
