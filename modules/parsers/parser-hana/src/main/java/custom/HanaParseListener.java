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

import com.sap.xsk.parser.hana.core.HanaParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.TerminalNode;

public class HanaParseListener implements ParseTreeListener {

  @Override
  public void visitTerminal(TerminalNode terminalNode) {
    var c = terminalNode;
  }

  @Override
  public void visitErrorNode(ErrorNode errorNode) {
    var c = errorNode;
  }

  @Override
  public void enterEveryRule(ParserRuleContext parserRuleContext) {
//    if(parserRuleContext.getClass() == HanaParser.Proc_stmt_listContext.class)
//    {
//      parserRuleContext.getParent().children.remove(parserRuleContext);
//      var c = parserRuleContext;
//    }
    //var c = parserRuleContext;
  }

  @Override
  public void exitEveryRule(ParserRuleContext parserRuleContext) {
//    if(parserRuleContext.getClass() == HanaParser.Proc_stmt_listContext.class)
//    {
//      var c = parserRuleContext;
//    }
  }
}
