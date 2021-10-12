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

import com.sap.xsk.exceptions.XSKArtifactParserException;
import com.sap.xsk.parser.hdbti.core.HdbtiLexer;
import com.sap.xsk.parser.hdbti.core.HdbtiParser;
import com.sap.xsk.parser.hdbti.exception.XSKHDBTIMissingPropertyException;
import com.sap.xsk.parser.hdbti.exception.XSKHDBTISyntaxErrorException;
import com.sap.xsk.parser.hdbti.models.XSKHDBTIImportModel;
import com.sap.xsk.parser.utils.ParserConstants;
import com.sap.xsk.utils.XSKCommonsUtils;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XSKHDBTIParser implements IXSKHDBTIParser {

  private static final Logger logger = LoggerFactory.getLogger(XSKHDBTIParser.class);

  public XSKHDBTIImportModel parse(String location, String content)
      throws IOException, XSKHDBTISyntaxErrorException, XSKArtifactParserException {
    ByteArrayInputStream is = new ByteArrayInputStream(content.getBytes());
    ANTLRInputStream inputStream = new ANTLRInputStream(is);
    HdbtiLexer hdbtiLexer = new HdbtiLexer(inputStream);
    XSKHDBTISyntaxErrorListener lexerErrorListener = new XSKHDBTISyntaxErrorListener();
    hdbtiLexer.removeErrorListeners();
    hdbtiLexer.addErrorListener(lexerErrorListener);
    CommonTokenStream tokenStream = new CommonTokenStream(hdbtiLexer);

    HdbtiParser hdbtiParser = new HdbtiParser(tokenStream);
    hdbtiParser.setBuildParseTree(true);
    XSKHDBTISyntaxErrorListener parseErrorListener = new XSKHDBTISyntaxErrorListener();
    hdbtiParser.removeErrorListeners();
    hdbtiParser.addErrorListener(parseErrorListener);

    ParseTree parseTree = hdbtiParser.importArr();
    XSKCommonsUtils.logParserErrors(parseErrorListener.getErrors(), ParserConstants.PARSER_ERROR, location, ParserConstants.HDBTI_PARSER);
    XSKCommonsUtils.logParserErrors(lexerErrorListener.getErrors(), ParserConstants.LEXER_ERROR, location, ParserConstants.HDBTI_PARSER);

    XSKHDBTICoreListener XSKHDBTICoreListener = new XSKHDBTICoreListener();
    ParseTreeWalker parseTreeWalker = new ParseTreeWalker();
    parseTreeWalker.walk(XSKHDBTICoreListener, parseTree);

    XSKHDBTIImportModel importModel = XSKHDBTICoreListener.getImportModel();
    try {
      importModel.checkMandatoryFieldsInAllConfigModels();
    } catch (Exception e) {
      throw new XSKHDBTIMissingPropertyException(String.format("Wrong format of hdbti definition: [%s]. [%s]", location, e.getMessage()));
    }

    return importModel;
  }
}
