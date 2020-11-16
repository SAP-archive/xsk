/*
 * Copyright (c) 2019-2020 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2019-2020 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.hdbti.transformer;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Singleton;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.eclipse.dirigible.commons.api.module.StaticInjector;
import org.eclipse.dirigible.repository.api.IRepository;
import org.eclipse.dirigible.repository.api.IResource;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Injector;
import com.sap.xsk.models.hdbti.HdbtiStandaloneSetup;
import com.sap.xsk.models.hdbti.HdbtiStandaloneSetupGenerated;
import com.sap.xsk.models.hdbti.myHdbti.HdbdtiModel;
import com.sap.xsk.models.hdbti.myHdbti.Import;
import com.sap.xsk.models.hdbti.myHdbti.ImportConfig;
import com.sap.xsk.hdbti.model.XSKTableImportArtifact;
import com.sap.xsk.hdbti.model.XSKTableImportConfigurationDefinition;
import com.sap.xsk.hdbti.model.XSKTableImportToCsvRelation;
import com.sap.xsk.hdbti.service.XSKCsvToHdbtiRelationService;

@Singleton
public class XSKTableImportArtifactFactory {

    private static final Logger logger = LoggerFactory.getLogger(XSKTableImportArtifactFactory.class);

    private IRepository repository = StaticInjector.getInjector().getInstance(IRepository .class);

    private XSKCsvToHdbtiRelationService xskCsvToHdbtiRelationService = StaticInjector.getInjector().getInstance(XSKCsvToHdbtiRelationService .class);

    public XSKTableImportArtifactFactory() {
        setupParser();
    }

    private void setupParser() {
        Injector injector = new HdbtiStandaloneSetup().createInjectorAndDoEMFRegistration();
        injector.injectMembers(this);
    }

    public XSKTableImportArtifact parseTableImport(String content, String location) throws IOException {
        XSKTableImportArtifact tableImportArtifact = new XSKTableImportArtifact();
        List<XSKTableImportConfigurationDefinition> importConfigurationDefinitions = new ArrayList<>();
        List<XSKTableImportToCsvRelation> tableImportToCsvRelations = new ArrayList<>();

        tableImportArtifact.setImportConfigurationDefinition(importConfigurationDefinitions);
        tableImportArtifact.setTableImportToCsvRelations(tableImportToCsvRelations);

        new org.eclipse.emf.mwe.utils.StandaloneSetup().setPlatformUri("../");
        Injector injector = new HdbtiStandaloneSetupGenerated().createInjectorAndDoEMFRegistration();
        XtextResourceSet rs = injector.getInstance(XtextResourceSet.class);
        rs.addLoadOption(XtextResource.OPTION_RESOLVE_ALL, Boolean.TRUE);

        Resource resource = rs.createResource(URI.createURI("dummy:/example.hdbti"));
        InputStream in = new ByteArrayInputStream((content.getBytes()));
        resource.load(in, rs.getLoadOptions());
        HdbdtiModel hdbdtiModel = (HdbdtiModel) resource.getContents().get(0);
        Import importObject = hdbdtiModel.getImportElement();


        for (ImportConfig configuration : importObject.getValues()) {
            addHdbtiToCsvRelation(tableImportArtifact, configuration, location);
            addTableImportConfiguration(tableImportArtifact, configuration);
        }
        return tableImportArtifact;
    }

    private void addTableImportConfiguration(XSKTableImportArtifact tableImportArtifact, ImportConfig configuration) {
        XSKTableImportConfigurationDefinition tableImportConfigurationDefinition = new XSKTableImportConfigurationDefinition();
        tableImportConfigurationDefinition.setTable(configuration.getTableValue());
        tableImportConfigurationDefinition.setSchema(configuration.getSchemaValue());
        tableImportConfigurationDefinition.setFile(configuration.getFileValue());
        tableImportConfigurationDefinition.setHeader(Boolean.parseBoolean(configuration.getHeaderValue()));
        tableImportConfigurationDefinition.setDelimField(configuration.getDelimFieldValue());
        tableImportConfigurationDefinition.setDelimEnclosing(configuration.getDelimEnclosingValue());
        tableImportConfigurationDefinition.setKeysAsMap(new HashMap<>());
        tableImportArtifact.getImportConfigurationDefinition().add(tableImportConfigurationDefinition);
    }

    private void addHdbtiToCsvRelation(XSKTableImportArtifact tableImportArtifact, ImportConfig configuration, String hdbtiLocation) {
        String csvParsedFilePath = xskCsvToHdbtiRelationService.convertToActualFileName(configuration.getFileValue());
        XSKTableImportToCsvRelation tableImportToCsvRelation = new XSKTableImportToCsvRelation();
        IResource csvFile = repository.getResource(csvParsedFilePath);
        tableImportToCsvRelation.setCsv(csvParsedFilePath);
        tableImportToCsvRelation.setHdbti(hdbtiLocation);
        tableImportToCsvRelation.setCsvHash(DigestUtils.md5Hex(getContentFromResource(csvFile)));
        tableImportArtifact.getTableImportToCsvRelations().add(tableImportToCsvRelation);

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
