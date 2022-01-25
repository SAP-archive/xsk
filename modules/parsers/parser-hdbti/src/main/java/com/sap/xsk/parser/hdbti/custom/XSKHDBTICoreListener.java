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
package com.sap.xsk.parser.hdbti.custom;

import com.sap.xsk.parser.hdbti.core.HdbtiBaseListener;
import com.sap.xsk.parser.hdbti.core.HdbtiParser;
import com.sap.xsk.parser.hdbti.core.HdbtiParser.AssignTableContext;
import com.sap.xsk.parser.hdbti.exception.DuplicateFieldNameException;
import com.sap.xsk.parser.hdbti.exception.TablePropertySyntaxException;
import com.sap.xsk.parser.hdbti.models.XSKHDBTIImportConfigModel;
import com.sap.xsk.parser.hdbti.models.XSKHDBTIImportModel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XSKHDBTICoreListener extends HdbtiBaseListener {

  private final XSKHDBTIImportModel importModel = new XSKHDBTIImportModel();
  private final Set<String> usedFields = new HashSet<>();
  private XSKHDBTIImportConfigModel configModel;

  @Override
  public void enterObjConfig(HdbtiParser.ObjConfigContext ctx) {
    configModel = new XSKHDBTIImportConfigModel();
  }

  @Override
  public void exitObjConfig(HdbtiParser.ObjConfigContext ctx) {
    List<XSKHDBTIImportConfigModel.Pair> pairs = new ArrayList<>();

    configModel.setUseHeaderNames(false);
    configModel.setHeader(false);
    configModel.setDistinguishEmptyFromNull(true);

    for (HdbtiParser.AssignExpressionContext expressionContext :
        ctx.assignExpression()) {
      if (expressionContext.assignTable() != null) {
        String tableName = expressionContext.assignTable().STRING().getText();
        configModel.setTableName(handleStringLiteral(tableName));
      } else if (expressionContext.assignSchema() != null) {
        String schemaName = expressionContext.assignSchema().STRING().getText();
        configModel.setSchemaName(handleStringLiteral(schemaName));
      } else if (expressionContext.assignFile() != null) {
        String fileName = expressionContext.assignFile().STRING().getText();
        configModel.setFileName(handleStringLiteral(fileName));
      } else if (expressionContext.assignHeader() != null) {
        String header = expressionContext.assignHeader().BOOLEAN().getText();
        configModel.setHeader(Boolean.parseBoolean(header));
      } else if (expressionContext.assignUseHeaderNames() != null) {
        String useHeaderNames = expressionContext.assignUseHeaderNames().BOOLEAN().getText();
        configModel.setUseHeaderNames(Boolean.parseBoolean(useHeaderNames));
      } else if (expressionContext.assignDelimField() != null) {
        String delimField = expressionContext.assignDelimField().STRING().getText();
        configModel.setDelimField(handleStringLiteral(delimField));
      } else if (expressionContext.assignDelimEnclosing() != null) {
        String delimEnclosing = expressionContext.assignDelimEnclosing().STRING().toString();
        configModel.setDelimEnclosing(handleStringLiteral(delimEnclosing));
      } else if (expressionContext.assignDistinguishEmptyFromNull() != null) {
        String distinguishEmptyFromNull = expressionContext.assignDistinguishEmptyFromNull().BOOLEAN().getText();
        configModel.setDistinguishEmptyFromNull(Boolean.parseBoolean(distinguishEmptyFromNull));
      } else if (expressionContext.assignKeys() != null) {
        List<XSKHDBTIImportConfigModel.Pair> pairsToBeAdd = new ArrayList<>();
        expressionContext.assignKeys().keyArr().pair().forEach(el -> {
          if (pairs.isEmpty()) {
            XSKHDBTIImportConfigModel.Pair newPair = new XSKHDBTIImportConfigModel.Pair(handleStringLiteral(el.pairKey().getText()),
                new ArrayList<>(Collections.singletonList(handleStringLiteral(el.pairValue().getText()))));
            pairs.add(newPair);
          } else {
            for (XSKHDBTIImportConfigModel.Pair pair : pairs) {
              if (pair.getColumn().equals(handleStringLiteral(el.pairKey().getText()))) {
                pair.getValues().add(handleStringLiteral(el.pairValue().getText()));
              } else {
                XSKHDBTIImportConfigModel.Pair newPair = new XSKHDBTIImportConfigModel.Pair(handleStringLiteral(el.pairKey().getText()),
                    new ArrayList<>(Collections.singletonList(handleStringLiteral(el.pairValue().getText()))));
                pairsToBeAdd.add(newPair);
              }
            }
          }
          pairs.addAll(pairsToBeAdd);
        });
      }
    }

    configModel.setKeys(pairs);
    importModel.getConfigModels().add(configModel);
    usedFields.clear();
  }

  @Override
  public void enterAssignExpression(HdbtiParser.AssignExpressionContext ctx) {
    String currentObjField = ctx.getChild(0).getChild(0).getText();

    if (currentObjField.equals("table") || currentObjField.equals("hdbtable") || currentObjField.equals("cdstable")) {
      currentObjField = "table";
    }

    if (usedFields.contains(currentObjField)) {
      if (currentObjField.equals("table")) {
        throw new DuplicateFieldNameException(
            String.format("Invalid combination of table declarations found, you may only use one of [cdstable | hdbtable | table]."));
      } else {
        throw new DuplicateFieldNameException(String.format("Duplicate fields: Field with name: '%s' is already in use.", currentObjField));
      }
    } else {
      usedFields.add(currentObjField);
    }
  }

  public XSKHDBTIImportModel getImportModel() {
    return importModel;
  }

  @Override
  public void enterAssignTable(AssignTableContext ctx) {
    String tableProperty = handleStringLiteral(ctx.STRING().getText());

    if (!isCorrectTablePropertySyntax(tableProperty)) {
      throw new TablePropertySyntaxException(String.format("Table property contains unsupported symbols: %s", tableProperty));
    }
  }

  /**
   * Check if the table property has proper syntax.
   */
  public static boolean isCorrectTablePropertySyntax(String tableProperty) throws IllegalArgumentException {
    String regex = new String();

    if (tableProperty.contains("::")) {
      regex = "^[A-Za-z0-9_\\-$.]+::[A-Za-z0-9_\\-$.]+$";
    } else {
      regex = "^[A-Za-z0-9_\\-$.]+$";
    }

    Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
    Matcher matcher = pattern.matcher(tableProperty.trim());

    return matcher.find();
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
