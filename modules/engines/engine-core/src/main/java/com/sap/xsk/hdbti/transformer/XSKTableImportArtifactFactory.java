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
package com.sap.xsk.hdbti.transformer;

import com.sap.xsk.hdbti.model.XSKTableImportArtifact;
import com.sap.xsk.hdbti.model.XSKTableImportConfigurationDefinition;
import com.sap.xsk.hdbti.model.XSKTableImportToCsvRelation;
import com.sap.xsk.hdbti.service.XSKCsvToHdbtiRelationService;
import com.sap.xsk.parser.hdbti.custom.XSKHDBTIParser;
import com.sap.xsk.parser.hdbti.models.XSKHDBTIImportConfigModel;
import com.sap.xsk.parser.hdbti.models.XSKHDBTIImportModel;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.eclipse.dirigible.commons.api.module.StaticInjector;
import org.eclipse.dirigible.repository.api.IRepository;
import org.eclipse.dirigible.repository.api.IResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Singleton
public class XSKTableImportArtifactFactory {

    private static final Logger logger = LoggerFactory.getLogger(XSKTableImportArtifactFactory.class);

    private IRepository repository = StaticInjector.getInjector().getInstance(IRepository .class);

    private XSKCsvToHdbtiRelationService xskCsvToHdbtiRelationService = StaticInjector.getInjector().getInstance(XSKCsvToHdbtiRelationService .class);
    private XSKHDBTIParser xskhdbtiParser;
    public XSKTableImportArtifactFactory() {
        setupParser();
    }

    private void setupParser() {
        xskhdbtiParser = new XSKHDBTIParser();
    }

    public XSKTableImportArtifact parseTableImport(String content, String location) throws IOException {
        XSKTableImportArtifact tableImportArtifact = new XSKTableImportArtifact();
        List<XSKTableImportConfigurationDefinition> importConfigurationDefinitions = new ArrayList<>();
        List<XSKTableImportToCsvRelation> tableImportToCsvRelations = new ArrayList<>();

        tableImportArtifact.setImportConfigurationDefinition(importConfigurationDefinitions);
        tableImportArtifact.setTableImportToCsvRelations(tableImportToCsvRelations);

        XSKHDBTIImportModel importObject = xskhdbtiParser.parse(content);

        for (XSKHDBTIImportConfigModel configuration : importObject.getConfigModels()) {
            addHdbtiToCsvRelation(tableImportArtifact, configuration, location);
            addTableImportConfiguration(tableImportArtifact, configuration);
        }
        return tableImportArtifact;
    }

    private void addTableImportConfiguration(XSKTableImportArtifact tableImportArtifact, XSKHDBTIImportConfigModel configuration) {
        XSKTableImportConfigurationDefinition tableImportConfigurationDefinition = new XSKTableImportConfigurationDefinition();
        tableImportConfigurationDefinition.setTable(configuration.getTableName());
        tableImportConfigurationDefinition.setSchema(configuration.getSchemaName());
        tableImportConfigurationDefinition.setFile(configuration.getFileName());
        tableImportConfigurationDefinition.setHeader(configuration.getHeader());
        tableImportConfigurationDefinition.setDelimField(configuration.getDelimField());
        tableImportConfigurationDefinition.setDelimEnclosing(configuration.getDelimEnclosing());
        tableImportConfigurationDefinition.setKeysAsMap(handleKeyValuePairs(configuration.getKeys()));
        tableImportArtifact.getImportConfigurationDefinition().add(tableImportConfigurationDefinition);
    }

    private void addHdbtiToCsvRelation(XSKTableImportArtifact tableImportArtifact, XSKHDBTIImportConfigModel configuration, String hdbtiLocation) {
        String csvParsedFilePath = xskCsvToHdbtiRelationService.convertToActualFileName(configuration.getFileName());
        XSKTableImportToCsvRelation tableImportToCsvRelation = new XSKTableImportToCsvRelation();
        IResource csvFile = repository.getResource(csvParsedFilePath);
        tableImportToCsvRelation.setCsv(csvParsedFilePath);
        tableImportToCsvRelation.setHdbti(hdbtiLocation);
        tableImportToCsvRelation.setCsvHash(DigestUtils.md5Hex(getContentFromResource(csvFile)));
        tableImportArtifact.getTableImportToCsvRelations().add(tableImportToCsvRelation);

    }

    private Map<String, String> handleKeyValuePairs(List<XSKHDBTIImportConfigModel.Pair> pairs) {
        return pairs.stream().collect(Collectors.toMap(XSKHDBTIImportConfigModel.Pair::getKey, XSKHDBTIImportConfigModel.Pair::getValue));
    }

    private String getContentFromResource(IResource resource) {
        byte[] content = resource.getContent();
        String contentAsString = null;
        try {
            contentAsString = IOUtils
                    .toString(new InputStreamReader(new ByteArrayInputStream(content), StandardCharsets.UTF_8));
        } catch (IOException e) {
            logger.error("Error occured while reading the content from CSV File" ,e);
        }
        return contentAsString;
    }
}
