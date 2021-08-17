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
package com.sap.xsk.hdb.ds.parser.hdbview;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sap.xsk.parser.utils.ParserConstants;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.eclipse.dirigible.core.problems.exceptions.ProblemsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.xsk.exceptions.XSKArtifactParserException;
import com.sap.xsk.hdb.ds.api.IXSKDataStructureModel;
import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdb.ds.model.XSKDBContentType;
import com.sap.xsk.hdb.ds.model.hdbview.XSKDataStructureHDBViewModel;
import com.sap.xsk.hdb.ds.parser.XSKDataStructureParser;
import com.sap.xsk.parser.hdbview.core.HdbviewLexer;
import com.sap.xsk.parser.hdbview.core.HdbviewParser;
import com.sap.xsk.parser.hdbview.custom.XSKHDBVIEWCoreListener;
import com.sap.xsk.parser.hdbview.custom.XSKHDBVIEWErrorListener;
import com.sap.xsk.parser.hdbview.models.XSKHDBVIEWDefinitionModel;
import com.sap.xsk.utils.XSKCommonsUtils;
import com.sap.xsk.utils.XSKHDBUtils;

public class XSKViewParser implements XSKDataStructureParser<XSKDataStructureHDBViewModel> {

    private static final Logger logger = LoggerFactory.getLogger(XSKViewParser.class);

    @Override
    public XSKDataStructureHDBViewModel parse(String location, String content)
        throws XSKDataStructuresException, IOException, XSKArtifactParserException, ProblemsException {
        Pattern pattern = Pattern.compile("^(\\t\\n)*(\\s)*VIEW", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(content.trim().toUpperCase(Locale.ROOT));
        boolean matchFound = matcher.find();
        return (matchFound)
                ? parseHanaXSAdvancedContent(location, content)
                : parseHanaXSClassicContent(location, content);
    }

    private XSKDataStructureHDBViewModel parseHanaXSAdvancedContent(String location, String content) {
        logger.debug("Parsing hdbview as Hana XS Advanced format");
        XSKDataStructureHDBViewModel hdbViewModel = new XSKDataStructureHDBViewModel();
        XSKHDBUtils.populateXSKDataStructureModel(location, content, hdbViewModel, IXSKDataStructureModel.TYPE_HDB_VIEW, XSKDBContentType.OTHERS);
        hdbViewModel.setRawContent(content);
        return hdbViewModel;
    }

    private XSKDataStructureHDBViewModel parseHanaXSClassicContent(String location, String content)
        throws XSKDataStructuresException, IOException, XSKArtifactParserException, ProblemsException {
        logger.debug("Parsing hdbview as Hana XS Classic format");
        XSKDataStructureHDBViewModel hdbViewModel = new XSKDataStructureHDBViewModel();
        XSKHDBUtils.populateXSKDataStructureModel(location, content, hdbViewModel, IXSKDataStructureModel.TYPE_HDB_VIEW, XSKDBContentType.XS_CLASSIC);

        ByteArrayInputStream is = new ByteArrayInputStream(content.getBytes());
        ANTLRInputStream inputStream = new ANTLRInputStream(is);

        HdbviewLexer lexer = new HdbviewLexer(inputStream);
        XSKHDBVIEWErrorListener lexerErrorListener = new XSKHDBVIEWErrorListener();
        lexer.removeErrorListeners();//remove the ConsoleErrorListener
        lexer.addErrorListener(lexerErrorListener);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);

        XSKHDBVIEWErrorListener parserErrorListener = new XSKHDBVIEWErrorListener();
        HdbviewParser parser = new HdbviewParser(tokenStream);
        parser.setBuildParseTree(true);
        parser.removeErrorListeners();
        parser.addErrorListener(parserErrorListener);

        ParseTree parseTree = parser.hdbviewDefinition();
        XSKCommonsUtils.logParserErrors(parserErrorListener.getErrors(), ParserConstants.PARSER_ERROR, location, "HDB View", logger);
        XSKCommonsUtils.logParserErrors(lexerErrorListener.getErrors(), ParserConstants.LEXER_ERROR, location, "HDB View", logger);

        XSKHDBVIEWCoreListener XSKHDBVIEWCoreListener = new XSKHDBVIEWCoreListener();
        ParseTreeWalker parseTreeWalker = new ParseTreeWalker();
        parseTreeWalker.walk(XSKHDBVIEWCoreListener, parseTree);

        XSKHDBVIEWDefinitionModel antlr4Model = XSKHDBVIEWCoreListener.getModel();
        try {
            antlr4Model.checkForAllMandatoryFieldsPresence();
        } catch (Exception e) {
            throw new XSKDataStructuresException(String.format("Wrong format of HDB View: [%s] during parsing. [%s]", location, e.getMessage()));
        }
        hdbViewModel.setQuery(antlr4Model.getQuery());
        hdbViewModel.setSchema(antlr4Model.getSchema());
        hdbViewModel.setPublic(antlr4Model.isPublic());
        hdbViewModel.setDependsOn(antlr4Model.getDependsOn());
        hdbViewModel.setDependsOnTable(antlr4Model.getDependsOnTable());
        hdbViewModel.setDependsOnView(antlr4Model.getDependsOnView());

        return hdbViewModel;
    }

    @Override
    public String getType() {
        return IXSKDataStructureModel.TYPE_HDB_VIEW;
    }

    @Override
    public Class<XSKDataStructureHDBViewModel> getDataStructureClass() {
        return XSKDataStructureHDBViewModel.class;
    }

}