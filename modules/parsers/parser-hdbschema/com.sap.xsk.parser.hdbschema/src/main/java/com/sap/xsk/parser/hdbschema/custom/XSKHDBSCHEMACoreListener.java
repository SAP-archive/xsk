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
package com.sap.xsk.parser.hdbschema.custom;

import com.sap.xsk.parser.hdbschema.core.HdbschemaBaseListener;
import com.sap.xsk.parser.hdbschema.core.HdbschemaParser;
import com.sap.xsk.parser.hdbschema.models.XSKHDBSCHEMADefinitionModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

public class XSKHDBSCHEMACoreListener extends HdbschemaBaseListener {

  private XSKHDBSCHEMADefinitionModel model = new XSKHDBSCHEMADefinitionModel();
  private ParseTreeProperty<Object> tokens = new ParseTreeProperty<>();

  @Override
  public void exitHdbschemaDefinition(HdbschemaParser.HdbschemaDefinitionContext ctx) {
    List<ParseTree> ctxList = ctx.children;
    for (ParseTree tree : ctxList) {
      if (tree.getChild(0) instanceof HdbschemaParser.SchemaPropContext) {
        model.setSchema(handleStringLiteral((String) tokens.get((HdbschemaParser.SchemaPropContext) tree.getChild(0))));
      }

    }
  }

  @Override
  public void exitSchemaProp(HdbschemaParser.SchemaPropContext ctx) {
    if (ctx != null && ctx.STRING() != null) {
      tokens.put(ctx, ctx.STRING().getText());
    }
  }

  private String handleStringLiteral(String value) {
    if (value != null && value.length() > 1) {
      String subStr = value.substring(1, value.length() - 1);
      String escapedQuote = subStr.replace("\\\"", "\"");
      return escapedQuote.replace("\\\\", "\\");
    }
    return null;
  }

  public XSKHDBSCHEMADefinitionModel getModel() {
    return model;
  }
}
