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
package com.sap.xsk.parser.hdbschema.custom;

import com.sap.xsk.parser.hdbschema.core.HdbschemaBaseListener;
import com.sap.xsk.parser.hdbschema.core.HdbschemaParser;
import com.sap.xsk.parser.hdbschema.models.XSKHDBSCHEMADefinitionModel;

public class XSKHDBSCHEMACoreListener extends HdbschemaBaseListener {

  private XSKHDBSCHEMADefinitionModel model = new XSKHDBSCHEMADefinitionModel();

  @Override
  public void exitHdbschemaDefinition(HdbschemaParser.HdbschemaDefinitionContext ctx) {
    if (ctx.schemaNameProp() != null && ctx.schemaNameProp().STRING() != null) {
      model.setSchemaName(handleStringLiteral(ctx.schemaNameProp().STRING().getText()));
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