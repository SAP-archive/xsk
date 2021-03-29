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
package com.sap.xsk.hdb.ds.module;

import com.google.inject.Scopes;
import com.google.inject.multibindings.MapBinder;
import com.google.inject.name.Names;
import com.sap.xsk.hdb.ds.api.IXSKDataStructureModel;
import com.sap.xsk.hdb.ds.api.IXSKDataStructuresCoreService;
import com.sap.xsk.hdb.ds.api.IXSKHdbProcessor;
import com.sap.xsk.hdb.ds.facade.IXSKHDBCoreFacade;
import com.sap.xsk.hdb.ds.facade.XSKHDBCoreFacade;
import com.sap.xsk.hdb.ds.parser.XSKDataStructureParser;
import com.sap.xsk.hdb.ds.parser.hdbdd.XSKEntitiesParser;
import com.sap.xsk.hdb.ds.parser.hdbprocedure.XSKHDBProcedureParser;
import com.sap.xsk.hdb.ds.parser.hdbschema.XSKHDBSchemaParser;
import com.sap.xsk.hdb.ds.parser.hdbsynonym.XSKSynonymParser;
import com.sap.xsk.hdb.ds.parser.hdbsequence.XSKHDBSequenceParser;
import com.sap.xsk.hdb.ds.parser.hdbsequence.XSKHDBSequenceParser;
import com.sap.xsk.hdb.ds.parser.hdbsynonym.XSKSynonymParser;
import com.sap.xsk.hdb.ds.parser.hdbtable.XSKTableParser;
import com.sap.xsk.hdb.ds.parser.hdbtablefunction.XSKHDBTableFunctionParser;
import com.sap.xsk.hdb.ds.parser.hdbview.XSKViewParser;
import com.sap.xsk.hdb.ds.parser.hdi.XSKHDIParser;
import com.sap.xsk.hdb.ds.processors.entity.XSKEntityCreateProcessor;
import com.sap.xsk.hdb.ds.processors.entity.XSKEntityDropProcessor;
import com.sap.xsk.hdb.ds.processors.entity.XSKEntityUpdateProcessor;
import com.sap.xsk.hdb.ds.processors.hdbprocedure.HDBProcedureCreateProcessor;
import com.sap.xsk.hdb.ds.processors.hdbprocedure.HDBProcedureDropProcessor;
import com.sap.xsk.hdb.ds.processors.hdbschema.HDBSchemaCreateProcessor;
import com.sap.xsk.hdb.ds.processors.hdbschema.HDBSchemaDropProcessor;
import com.sap.xsk.hdb.ds.processors.hdbsequence.XSKHDBSequenceCreateProcessor;
import com.sap.xsk.hdb.ds.processors.hdbsequence.XSKHDBSequenceDropProcessor;
import com.sap.xsk.hdb.ds.processors.hdbsequence.XSKHDBSequenceUpdateProcessor;
import com.sap.xsk.hdb.ds.processors.hdbtablefunction.HDBTableFunctionCreateProcessor;
import com.sap.xsk.hdb.ds.processors.hdbtablefunction.HDBTableFunctionDropProcessor;
import com.sap.xsk.hdb.ds.processors.synonym.HDBSynonymCreateProcessor;
import com.sap.xsk.hdb.ds.processors.synonym.HDBSynonymDropProcessor;
import com.sap.xsk.hdb.ds.processors.table.XSKTableCreateProcessor;
import com.sap.xsk.hdb.ds.processors.table.XSKTableDropProcessor;
import com.sap.xsk.hdb.ds.processors.view.XSKViewCreateProcessor;
import com.sap.xsk.hdb.ds.processors.view.XSKViewDropProcessor;
import com.sap.xsk.hdb.ds.service.XSKDataStructuresCoreService;
import com.sap.xsk.hdb.ds.service.manager.*;
import com.sap.xsk.hdb.ds.service.parser.IXSKCoreParserService;
import com.sap.xsk.hdb.ds.service.parser.XSKCoreParserService;
import org.eclipse.dirigible.commons.api.module.AbstractDirigibleModule;

import javax.ws.rs.HEAD;


public class XSKHDBModule extends AbstractDirigibleModule {

    @Override
    protected void configure() {
        bind(IXSKHDBCoreFacade.class).annotatedWith(Names.named("xskHDBCoreFacade")).to(XSKHDBCoreFacade.class).in(Scopes.SINGLETON);
        bind(IXSKDataStructuresCoreService.class).annotatedWith(Names.named("xskDataStructuresCoreService")).to(XSKDataStructuresCoreService.class).in(Scopes.SINGLETON);
        bind(IXSKCoreParserService.class).annotatedWith(Names.named("xskCoreParserService")).to(XSKCoreParserService.class).in(Scopes.SINGLETON);
        bind(IXSKDataStructuresCoreService.class).annotatedWith(Names.named("xskDataStructuresCoreService")).to(XSKDataStructuresCoreService.class).in(Scopes.SINGLETON);

        bindManagerServicesToFileExtensions();
        bindParsersToFileExtension();
        bindProcessors();
    }

    private void bindManagerServicesToFileExtensions() {
        MapBinder<String, IXSKDataStructureManager> mapBinder
                = MapBinder.newMapBinder(binder(), String.class, IXSKDataStructureManager.class);

        mapBinder.addBinding(IXSKDataStructureModel.TYPE_HDB_ENTITIES).to(IXSKEntityManagerService.class).asEagerSingleton();
        mapBinder.addBinding(IXSKDataStructureModel.TYPE_HDB_TABLE).to(IXSKTableManagerService.class).asEagerSingleton();
        mapBinder.addBinding(IXSKDataStructureModel.TYPE_HDB_VIEW).to(IXSKViewManagerService.class).asEagerSingleton();
        mapBinder.addBinding(IXSKDataStructureModel.TYPE_HDB_TABLE_FUNCTION).to(IXSKTableFunctionManagerService.class).asEagerSingleton();
        mapBinder.addBinding(IXSKDataStructureModel.TYPE_HDB_SCHEMA).to(IXSKSchemaManagerService.class).asEagerSingleton();
        mapBinder.addBinding(IXSKDataStructureModel.TYPE_HDB_PROCEDURE).to(IXSKProceduresManagerService.class).asEagerSingleton();
        mapBinder.addBinding(IXSKDataStructureModel.TYPE_HDB_SYNONYM).to(IXSKSynonymManagerService.class).asEagerSingleton();
        mapBinder.addBinding(IXSKDataStructureModel.TYPE_HDB_SEQUENCE).to(IXSKHDBSequenceManagerService.class).asEagerSingleton();
    }

    private void bindParsersToFileExtension() {
        MapBinder<String, XSKDataStructureParser> mapBinder
                = MapBinder.newMapBinder(binder(), String.class, XSKDataStructureParser.class);

        mapBinder.addBinding(IXSKDataStructureModel.FILE_EXTENSION_ENTITIES).to(XSKEntitiesParser.class).asEagerSingleton();
        mapBinder.addBinding(IXSKDataStructureModel.FILE_EXTENSION_TABLE).to(XSKTableParser.class).asEagerSingleton();
        mapBinder.addBinding(IXSKDataStructureModel.FILE_EXTENSION_VIEW).to(XSKViewParser.class).asEagerSingleton();
        mapBinder.addBinding(IXSKDataStructureModel.FILE_EXTENSION_SYNONYM).to(XSKSynonymParser.class).asEagerSingleton();
        mapBinder.addBinding(IXSKDataStructureModel.FILE_EXTENSION_HDBTABLEFUNCTION).to(XSKHDBTableFunctionParser.class).asEagerSingleton();
        mapBinder.addBinding(IXSKDataStructureModel.FILE_EXTENSION_HDBSCHEMA).to(XSKHDBSchemaParser.class).asEagerSingleton();
        mapBinder.addBinding(IXSKDataStructureModel.FILE_EXTENSION_HDBPROCEDURE).to(XSKHDBProcedureParser.class).asEagerSingleton();
        mapBinder.addBinding(IXSKDataStructureModel.FILE_EXTENSION_HDBSEQUENCE).to(XSKHDBSequenceParser.class).asEagerSingleton();

        mapBinder.addBinding(IXSKDataStructureModel.TYPE_HDB_ENTITIES).to(XSKEntitiesParser.class).asEagerSingleton();
        mapBinder.addBinding(IXSKDataStructureModel.TYPE_HDB_TABLE).to(XSKTableParser.class).asEagerSingleton();
        mapBinder.addBinding(IXSKDataStructureModel.TYPE_HDB_VIEW).to(XSKViewParser.class).asEagerSingleton();
        mapBinder.addBinding(IXSKDataStructureModel.TYPE_HDB_SYNONYM).to(XSKSynonymParser.class).asEagerSingleton();
        mapBinder.addBinding(IXSKDataStructureModel.TYPE_HDB_TABLE_FUNCTION).to(XSKHDBTableFunctionParser.class).asEagerSingleton();
        mapBinder.addBinding(IXSKDataStructureModel.TYPE_HDB_SCHEMA).to(XSKHDBSchemaParser.class).asEagerSingleton();
        mapBinder.addBinding(IXSKDataStructureModel.TYPE_HDB_PROCEDURE).to(XSKHDBProcedureParser.class).asEagerSingleton();
        mapBinder.addBinding(IXSKDataStructureModel.TYPE_HDB_SEQUENCE).to(XSKHDBSequenceParser.class).asEagerSingleton();
        mapBinder.addBinding(IXSKDataStructureModel.TYPE_HDI).to(XSKHDIParser.class).asEagerSingleton();
    }

    private void bindProcessors() {
        // Entity processors instantiation
        bind(IXSKHdbProcessor.class).annotatedWith(Names.named("xskEntityUpdateProcessor")).to(XSKEntityUpdateProcessor.class).in(Scopes.SINGLETON);
        bind(IXSKHdbProcessor.class).annotatedWith(Names.named("xskEntityDropProcessor")).to(XSKEntityDropProcessor.class).in(Scopes.SINGLETON);
        bind(IXSKHdbProcessor.class).annotatedWith(Names.named("xskEntityCreateProcessor")).to(XSKEntityCreateProcessor.class).in(Scopes.SINGLETON);

        // Hdb procedure processors instantiation
        bind(IXSKHdbProcessor.class).annotatedWith(Names.named("hdbProcedureDropProcessor")).to(HDBProcedureDropProcessor.class).in(Scopes.SINGLETON);
        bind(IXSKHdbProcessor.class).annotatedWith(Names.named("hdbProcedureCreateProcessor")).to(HDBProcedureCreateProcessor.class).in(Scopes.SINGLETON);

        // Hdb schema processors instantiation
        bind(IXSKHdbProcessor.class).annotatedWith(Names.named("hdbSchemaCreateProcessor")).to(HDBSchemaCreateProcessor.class).in(Scopes.SINGLETON);
        bind(IXSKHdbProcessor.class).annotatedWith(Names.named("hdbSchemaDropProcessor")).to(HDBSchemaDropProcessor.class).in(Scopes.SINGLETON);

        // Hdb table function processors instantiation
        bind(IXSKHdbProcessor.class).annotatedWith(Names.named("hdbTableFunctionCreateProcessor")).to(HDBTableFunctionCreateProcessor.class).in(Scopes.SINGLETON);
        bind(IXSKHdbProcessor.class).annotatedWith(Names.named("hdbTableFunctionDropProcessor")).to(HDBTableFunctionDropProcessor.class).in(Scopes.SINGLETON);

        // Hdb table processors instantiation
        bind(IXSKHdbProcessor.class).annotatedWith(Names.named("xskTableCreateProcessor")).to(XSKTableCreateProcessor.class).in(Scopes.SINGLETON);
        bind(IXSKHdbProcessor.class).annotatedWith(Names.named("xskTableDropProcessor")).to(XSKTableDropProcessor.class).in(Scopes.SINGLETON);

        // Hdb view processors instantiation
        bind(IXSKHdbProcessor.class).annotatedWith(Names.named("xskViewCreateProcessor")).to(XSKViewCreateProcessor.class).in(Scopes.SINGLETON);
        bind(IXSKHdbProcessor.class).annotatedWith(Names.named("xskViewDropProcessor")).to(XSKViewDropProcessor.class).in(Scopes.SINGLETON);


        // Hdb synonym processors instantiation
        bind(IXSKHdbProcessor.class).annotatedWith(Names.named("xskSynonymCreateProcessor")).to(HDBSynonymCreateProcessor.class).in(Scopes.SINGLETON);
        bind(IXSKHdbProcessor.class).annotatedWith(Names.named("xskSynonymDropProcessor")).to(HDBSynonymDropProcessor.class).in(Scopes.SINGLETON);

        // Hdb sequence processors instantiation
        bind(IXSKHdbProcessor.class).annotatedWith(Names.named("xskHdbSequenceUpdateProcessor")).to(XSKHDBSequenceUpdateProcessor.class).in(Scopes.SINGLETON);
        bind(IXSKHdbProcessor.class).annotatedWith(Names.named("xskHdbSequenceDropProcessor")).to(XSKHDBSequenceDropProcessor.class).in(Scopes.SINGLETON);
        bind(IXSKHdbProcessor.class).annotatedWith(Names.named("xskHdbSequenceCreateProcessor")).to(XSKHDBSequenceCreateProcessor.class).in(Scopes.SINGLETON);

    }

    @Override
    public String getName() {
        return "XSK HDB Module";
    }
}
