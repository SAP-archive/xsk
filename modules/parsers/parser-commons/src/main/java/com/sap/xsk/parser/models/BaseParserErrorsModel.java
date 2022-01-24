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
package com.sap.xsk.parser.models;

public class BaseParserErrorsModel {

  private int line;
  private int charPositionInLine;
  private String offendingSymbol;
  private String msg;

  public BaseParserErrorsModel(int line, int charPositionInLine, String offendingSymbol, String msg) {
    this.line = line;
    this.charPositionInLine = charPositionInLine;
    this.offendingSymbol = offendingSymbol;
    this.msg = msg;
  }

  public int getLine() {
    return line;
  }

  public void setLine(int line) {
    this.line = line;
  }

  public int getCharPositionInLine() {
    return charPositionInLine;
  }

  public String getOffendingSymbol() {
    return offendingSymbol;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }
}
