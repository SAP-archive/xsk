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
package com.sap.xsk.parser.xsodata.custom;

import com.sap.xsk.parser.models.BaseParserErrorsModel;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

import java.util.ArrayList;

public class XSKHDBXSODATASyntaxErrorListener extends BaseErrorListener {

  private final ArrayList<BaseParserErrorsModel> errors = new ArrayList<>();

  @Override
  public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg,
                          RecognitionException e) {

    this.errors.add(new BaseParserErrorsModel(line, charPositionInLine, offendingSymbol == null? "" : offendingSymbol.toString(), msg));
  }

  public ArrayList<BaseParserErrorsModel> getErrors() {
    return errors;
  }
}
