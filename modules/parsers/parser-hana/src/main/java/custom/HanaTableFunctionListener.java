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
package custom;

import com.sap.xsk.parser.hana.core.HanaBaseListener;
import com.sap.xsk.parser.hana.core.HanaParser.Create_func_bodyContext;
import models.TableFunctionDefinitionModel;
import org.apache.commons.lang3.StringUtils;

public class HanaTableFunctionListener extends HanaBaseListener {

  private TableFunctionDefinitionModel model;

  @Override
  public void exitCreate_func_body(Create_func_bodyContext ctx) {
    String strippedSchema = null;
    String strippedName = null;

    if (ctx.proc_name() != null) {
      if (ctx.proc_name().id_expression() != null) {
        String maybeQuotedName = ctx.proc_name().id_expression().getText();
        strippedName = StringUtils.strip(maybeQuotedName, "\"");
      }

      if (ctx.proc_name().schema_name() != null) {
        String maybeQuotedSchema = ctx.proc_name().schema_name().getText();
        strippedSchema = StringUtils.strip(maybeQuotedSchema, "\"");
      }
    }

    model = new TableFunctionDefinitionModel(strippedSchema, strippedName);
  }

  public TableFunctionDefinitionModel getModel() {
    return model;
  }
}
