/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2021 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.parser.hdbti.custom;

import com.sap.xsk.parser.hdbti.core.HdbtiBaseListener;
import com.sap.xsk.parser.hdbti.core.HdbtiParser;
import com.sap.xsk.parser.hdbti.exception.DuplicateFieldNameException;
import com.sap.xsk.parser.hdbti.models.XSKHDBTIImportConfigModel;
import com.sap.xsk.parser.hdbti.models.XSKHDBTIImportModel;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

public class XSKHDBTICoreListener extends HdbtiBaseListener {

  private final ParseTreeProperty<String> tokens = new ParseTreeProperty<>();
  private final XSKHDBTIImportModel importModel = new XSKHDBTIImportModel();
  private final Set<String> usedFields = new HashSet<>();
  private List<XSKHDBTIImportConfigModel.Pair> pairs;
  private XSKHDBTIImportConfigModel configModel;

  @Override
  public void enterObjConfig(HdbtiParser.ObjConfigContext ctx) {
    configModel = new XSKHDBTIImportConfigModel();
  }

  @Override
  public void exitObjConfig(HdbtiParser.ObjConfigContext ctx) {
    configModel.setUseHeaderNames(false);
    configModel.setHeader(false);
    configModel.setDistinguishEmptyFromNull(true);

    for (HdbtiParser.AssignExpressionContext expressionContext :
        ctx.assignExpression()) {
      if (expressionContext.getChild(0) instanceof HdbtiParser.AssignTableContext) {
        String tableName = tokens.get(expressionContext.getChild(0));
        configModel.setTableName(handleStringLiteral(tableName));
      } else if (expressionContext.getChild(0) instanceof HdbtiParser.AssignSchemaContext) {
        String schemaName = tokens.get(expressionContext.getChild(0));
        configModel.setSchemaName(handleStringLiteral(schemaName));
      } else if (expressionContext.getChild(0) instanceof HdbtiParser.AssignFileContext) {
        String fileName = tokens.get(expressionContext.getChild(0));
        configModel.setFileName(handleStringLiteral(fileName));
      } else if (expressionContext.getChild(0) instanceof HdbtiParser.AssignHeaderContext) {
        String header = tokens.get(expressionContext.getChild(0));
        configModel.setHeader(Boolean.parseBoolean(header));
      } else if (expressionContext.getChild(0) instanceof HdbtiParser.AssignUseHeaderNamesContext) {
        String useHeaderNames = tokens.get(expressionContext.getChild(0));
        configModel.setUseHeaderNames(Boolean.parseBoolean(useHeaderNames));
      } else if (expressionContext.getChild(0) instanceof HdbtiParser.AssignDelimFieldContext) {
        String delimField = tokens.get(expressionContext.getChild(0));
        configModel.setDelimField(handleStringLiteral(delimField));
      } else if (expressionContext.getChild(0) instanceof HdbtiParser.AssignDelimEnclosingContext) {
        String delimEnclosing = tokens.get(expressionContext.getChild(0));
        configModel.setDelimEnclosing(handleStringLiteral(delimEnclosing));
      } else if (expressionContext.getChild(0) instanceof HdbtiParser.AssignDistinguishEmptyFromNullContext) {
        String distinguishEmptyFromNull = tokens.get(expressionContext.getChild(0));
        configModel.setDistinguishEmptyFromNull(Boolean.parseBoolean(distinguishEmptyFromNull));
      }
    }

    configModel.setKeys(pairs);
    importModel.getConfigModels().add(configModel);
    usedFields.clear();
  }

  @Override
  public void enterAssignExpression(HdbtiParser.AssignExpressionContext ctx) {
    String currentObjField = ctx.getChild(0).getChild(0).getText();
    if (usedFields.contains(currentObjField)) {
      throw new DuplicateFieldNameException(String.format("Duplicate fields: Field with name: '%s' is already in use.", currentObjField));
    } else {
      usedFields.add(currentObjField);
    }
  }

  @Override
  public void exitAssignTable(HdbtiParser.AssignTableContext ctx) {
    if (ctx != null && ctx.STRING() != null) {
      tokens.put(ctx, ctx.STRING().getText());
    }
  }

  @Override
  public void exitAssignFile(HdbtiParser.AssignFileContext ctx) {
    if (ctx != null && ctx.STRING() != null) {
      tokens.put(ctx, ctx.STRING().getText());
    }
  }

  @Override
  public void exitAssignSchema(HdbtiParser.AssignSchemaContext ctx) {
    if (ctx != null && ctx.STRING() != null) {
      tokens.put(ctx, ctx.STRING().getText());
    }

  }

  @Override
  public void exitPair(HdbtiParser.PairContext ctx) {
    if (ctx.pairKey() != null && ctx.pairValue() != null) {
      XSKHDBTIImportConfigModel.Pair pair = new XSKHDBTIImportConfigModel.Pair(tokens.get(ctx.pairKey()), tokens.get(ctx.pairValue()));
      pairs.add(pair);
    }

  }

  @Override
  public void enterKeyArr(HdbtiParser.KeyArrContext ctx) {
    pairs = new ArrayList<>();
  }

  @Override
  public void exitPairKey(HdbtiParser.PairKeyContext ctx) {
    if (ctx != null && ctx.STRING() != null) {
      tokens.put(ctx, handleStringLiteral(ctx.STRING().getText()));
    }
  }

  @Override
  public void exitPairValue(HdbtiParser.PairValueContext ctx) {
    if (ctx != null && ctx.STRING() != null) {
      tokens.put(ctx, handleStringLiteral(ctx.STRING().getText()));
    }
  }

  @Override
  public void exitAssignHeader(HdbtiParser.AssignHeaderContext ctx) {
    if (ctx != null && ctx.BOOLEAN() != null) {
      tokens.put(ctx, ctx.BOOLEAN().getText());
    }
  }

  @Override
  public void exitAssignUseHeaderNames(HdbtiParser.AssignUseHeaderNamesContext ctx) {
    if (ctx != null && ctx.BOOLEAN() != null) {
      tokens.put(ctx, ctx.BOOLEAN().getText());
    }
  }

  @Override
  public void exitAssignDelimField(HdbtiParser.AssignDelimFieldContext ctx) {
    if (ctx != null && ctx.STRING() != null) {
      tokens.put(ctx, ctx.STRING().getText());
    }
  }

  @Override
  public void exitAssignDelimEnclosing(HdbtiParser.AssignDelimEnclosingContext ctx) {
    if (ctx != null && ctx.STRING() != null) {
      tokens.put(ctx, ctx.STRING().getText());
    }
  }

  @Override
  public void exitAssignDistinguishEmptyFromNull(HdbtiParser.AssignDistinguishEmptyFromNullContext ctx) {
    if (ctx != null && ctx.BOOLEAN() != null) {
      tokens.put(ctx, ctx.BOOLEAN().getText());
    }
  }

  public XSKHDBTIImportModel getImportModel() {

    return importModel;
  }

  private String handleStringLiteral(String value) {
    if (value != null && value.length() > 1) {
      String subStr = value.substring(1, value.length() - 1);
      String escapedQuote = subStr.replace("\\\"", "\"");
      return escapedQuote.replace("\\\\", "\\");
    }

    return value;
  }
}
