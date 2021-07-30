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
package com.sap.xsk.hdb.ds.module;

import com.sap.xsk.hdb.ds.api.IXSKDataStructureModel;
import com.sap.xsk.hdb.ds.api.IXSKDataStructuresCoreService;
import com.sap.xsk.hdb.ds.api.IXSKHdbProcessor;
import com.sap.xsk.hdb.ds.facade.IXSKHDBCoreFacade;
import com.sap.xsk.hdb.ds.facade.XSKHDBCoreFacade;
import com.sap.xsk.hdb.ds.parser.XSKDataStructureParser;
//import com.sap.xsk.hdb.ds.parser.hdbdd.XSKEntitiesParser;
import com.sap.xsk.hdb.ds.parser.hdbprocedure.XSKHDBProcedureParser;
import com.sap.xsk.hdb.ds.parser.hdbschema.XSKSchemaParser;
import com.sap.xsk.hdb.ds.parser.hdbsequence.XSKHDBSequenceParser;
import com.sap.xsk.hdb.ds.parser.hdbsynonym.XSKSynonymParser;
import com.sap.xsk.hdb.ds.parser.hdbtable.XSKTableParser;
import com.sap.xsk.hdb.ds.parser.hdbtablefunction.XSKHDBTableFunctionParser;
import com.sap.xsk.hdb.ds.parser.hdbview.XSKViewParser;
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
import com.sap.xsk.hdb.ds.processors.table.XSKTableAlterProcessor;
import com.sap.xsk.hdb.ds.processors.table.XSKTableCreateProcessor;
import com.sap.xsk.hdb.ds.processors.table.XSKTableDropProcessor;
import com.sap.xsk.hdb.ds.processors.view.XSKViewCreateProcessor;
import com.sap.xsk.hdb.ds.processors.view.XSKViewDropProcessor;
import com.sap.xsk.hdb.ds.service.XSKDataStructuresCoreService;
import com.sap.xsk.hdb.ds.service.manager.IXSKDataStructureManager;
import com.sap.xsk.hdb.ds.service.manager.IXSKEntityManagerService;
import com.sap.xsk.hdb.ds.service.manager.IXSKHDBSequenceManagerService;
import com.sap.xsk.hdb.ds.service.manager.IXSKProceduresManagerService;
import com.sap.xsk.hdb.ds.service.manager.IXSKSchemaManagerService;
import com.sap.xsk.hdb.ds.service.manager.IXSKSynonymManagerService;
import com.sap.xsk.hdb.ds.service.manager.IXSKTableFunctionManagerService;
import com.sap.xsk.hdb.ds.service.manager.IXSKTableManagerService;
import com.sap.xsk.hdb.ds.service.manager.IXSKViewManagerService;
import com.sap.xsk.hdb.ds.service.parser.IXSKCoreParserService;
import com.sap.xsk.hdb.ds.service.parser.XSKCoreParserService;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.dirigible.commons.api.module.AbstractDirigibleModule;

import org.eclipse.dirigible.commons.config.StaticObjects;

public class XSKHDBModule extends AbstractDirigibleModule {

	@Override
	public void configure() {
		StaticObjects.set("xskHDBCoreFacade", new XSKHDBCoreFacade());
		StaticObjects.set("xskDataStructuresCoreService", new XSKHDBCoreFacade());
		StaticObjects.set("xskCoreParserService", new XSKCoreParserService());
		StaticObjects.set("xskDataStructuresCoreService", new XSKDataStructuresCoreService());

//    bind(IXSKHDBCoreFacade.class).annotatedWith(Names.named("xskHDBCoreFacade")).to(XSKHDBCoreFacade.class).in(Scopes.SINGLETON);
//    bind(IXSKDataStructuresCoreService.class).annotatedWith(Names.named("xskDataStructuresCoreService"))
//        .to(XSKDataStructuresCoreService.class).in(Scopes.SINGLETON);
//    bind(IXSKCoreParserService.class).annotatedWith(Names.named("xskCoreParserService")).to(XSKCoreParserService.class)
//        .in(Scopes.SINGLETON);
//    bind(IXSKDataStructuresCoreService.class).annotatedWith(Names.named("xskDataStructuresCoreService"))
//        .to(XSKDataStructuresCoreService.class).in(Scopes.SINGLETON);

		Map<String, IXSKDataStructureManager> managerServices = new HashMap<String, IXSKDataStructureManager>();
		StaticObjects.set("managerServices", managerServices);
		bindManagerServicesToFileExtensions(managerServices);
		Map<String, XSKDataStructureParser> parserServices = new HashMap<String, XSKDataStructureParser>();
		StaticObjects.set("parserServices", parserServices);
		
		bindParsersToFileExtension(parserServices);
		bindProcessors();
	}

	private void bindManagerServicesToFileExtensions(Map<String, IXSKDataStructureManager> managerServices) {
//    MapBinder<String, IXSKDataStructureManager> mapBinder
//        = MapBinder.newMapBinder(binder(), String.class, IXSKDataStructureManager.class);
		
//		  managerServices.put(IXSKDataStructureModel.TYPE_HDB_ENTITIES, new XSKEntityManagerService());
			managerServices.put(IXSKDataStructureModel.TYPE_HDB_TABLE, new IXSKTableManagerService());
			managerServices.put(IXSKDataStructureModel.TYPE_HDB_VIEW, new IXSKViewManagerService());
			managerServices.put(IXSKDataStructureModel.TYPE_HDB_SYNONYM, new IXSKSynonymManagerService());
			managerServices.put(IXSKDataStructureModel.TYPE_HDB_TABLE_FUNCTION, new IXSKTableFunctionManagerService());
			managerServices.put(IXSKDataStructureModel.TYPE_HDB_SCHEMA, new IXSKSchemaManagerService());
			managerServices.put(IXSKDataStructureModel.TYPE_HDB_PROCEDURE, new IXSKProceduresManagerService());
			managerServices.put(IXSKDataStructureModel.TYPE_HDB_SEQUENCE, new IXSKHDBSequenceManagerService());

//		mapBinder.addBinding(IXSKDataStructureModel.TYPE_HDB_ENTITIES).to(IXSKEntityManagerService.class).asEagerSingleton();
//		mapBinder.addBinding(IXSKDataStructureModel.TYPE_HDB_TABLE).to(IXSKTableManagerService.class).asEagerSingleton();
//		mapBinder.addBinding(IXSKDataStructureModel.TYPE_HDB_VIEW).to(IXSKViewManagerService.class).asEagerSingleton();
//		mapBinder.addBinding(IXSKDataStructureModel.TYPE_HDB_TABLE_FUNCTION).to(IXSKTableFunctionManagerService.class).asEagerSingleton();
//		mapBinder.addBinding(IXSKDataStructureModel.TYPE_HDB_SCHEMA).to(IXSKSchemaManagerService.class).asEagerSingleton();
//		mapBinder.addBinding(IXSKDataStructureModel.TYPE_HDB_PROCEDURE).to(IXSKProceduresManagerService.class).asEagerSingleton();
//		mapBinder.addBinding(IXSKDataStructureModel.TYPE_HDB_SYNONYM).to(IXSKSynonymManagerService.class).asEagerSingleton();
//		mapBinder.addBinding(IXSKDataStructureModel.TYPE_HDB_SEQUENCE).to(IXSKHDBSequenceManagerService.class).asEagerSingleton();
	}

	private void bindParsersToFileExtension(Map<String, XSKDataStructureParser> parserServices) {

//    MapBinder<String, XSKDataStructureParser> mapBinder
//        = MapBinder.newMapBinder(binder(), String.class, XSKDataStructureParser.class);

//	  managerServices.put(IXSKDataStructureModel.FILE_EXTENSION_ENTITIES, new XSKEntitiesParser());
		parserServices.put(IXSKDataStructureModel.FILE_EXTENSION_TABLE, new XSKTableParser());
		parserServices.put(IXSKDataStructureModel.FILE_EXTENSION_VIEW, new XSKViewParser());
		parserServices.put(IXSKDataStructureModel.FILE_EXTENSION_SYNONYM, new XSKSynonymParser());
		parserServices.put(IXSKDataStructureModel.FILE_EXTENSION_HDBTABLEFUNCTION, new XSKHDBTableFunctionParser());
		parserServices.put(IXSKDataStructureModel.FILE_EXTENSION_HDBSCHEMA, new XSKSchemaParser());
		parserServices.put(IXSKDataStructureModel.FILE_EXTENSION_HDBPROCEDURE, new XSKHDBProcedureParser());
		parserServices.put(IXSKDataStructureModel.FILE_EXTENSION_HDBSEQUENCE, new XSKHDBSequenceParser());

//    mapBinder.addBinding(IXSKDataStructureModel.FILE_EXTENSION_ENTITIES).to(XSKEntitiesParser.class).asEagerSingleton();
//    mapBinder.addBinding(IXSKDataStructureModel.FILE_EXTENSION_TABLE).to(XSKTableParser.class).asEagerSingleton();
//    mapBinder.addBinding(IXSKDataStructureModel.FILE_EXTENSION_VIEW).to(XSKViewParser.class).asEagerSingleton();
//    mapBinder.addBinding(IXSKDataStructureModel.FILE_EXTENSION_SYNONYM).to(XSKSynonymParser.class).asEagerSingleton();
//    mapBinder.addBinding(IXSKDataStructureModel.FILE_EXTENSION_HDBTABLEFUNCTION).to(XSKHDBTableFunctionParser.class).asEagerSingleton();
//    mapBinder.addBinding(IXSKDataStructureModel.FILE_EXTENSION_HDBSCHEMA).to(XSKSchemaParser.class).asEagerSingleton();
//    mapBinder.addBinding(IXSKDataStructureModel.FILE_EXTENSION_HDBPROCEDURE).to(XSKHDBProcedureParser.class).asEagerSingleton();
//    mapBinder.addBinding(IXSKDataStructureModel.FILE_EXTENSION_HDBSEQUENCE).to(XSKHDBSequenceParser.class).asEagerSingleton();

//	  managerServices.put(IXSKDataStructureModel.TYPE_HDB_ENTITIES, new XSKEntitiesParser());
		parserServices.put(IXSKDataStructureModel.TYPE_HDB_TABLE, new XSKTableParser());
		parserServices.put(IXSKDataStructureModel.TYPE_HDB_VIEW, new XSKViewParser());
		parserServices.put(IXSKDataStructureModel.TYPE_HDB_SYNONYM, new XSKSynonymParser());
		parserServices.put(IXSKDataStructureModel.TYPE_HDB_TABLE_FUNCTION, new XSKHDBTableFunctionParser());
		parserServices.put(IXSKDataStructureModel.TYPE_HDB_SCHEMA, new XSKSchemaParser());
		parserServices.put(IXSKDataStructureModel.TYPE_HDB_PROCEDURE, new XSKHDBProcedureParser());
		parserServices.put(IXSKDataStructureModel.TYPE_HDB_SEQUENCE, new XSKHDBSequenceParser());

//    mapBinder.addBinding(IXSKDataStructureModel.TYPE_HDB_ENTITIES).to(XSKEntitiesParser.class).asEagerSingleton();
//    mapBinder.addBinding(IXSKDataStructureModel.TYPE_HDB_TABLE).to(XSKTableParser.class).asEagerSingleton();
//    mapBinder.addBinding(IXSKDataStructureModel.TYPE_HDB_VIEW).to(XSKViewParser.class).asEagerSingleton();
//    mapBinder.addBinding(IXSKDataStructureModel.TYPE_HDB_SYNONYM).to(XSKSynonymParser.class).asEagerSingleton();
//    mapBinder.addBinding(IXSKDataStructureModel.TYPE_HDB_TABLE_FUNCTION).to(XSKHDBTableFunctionParser.class).asEagerSingleton();
//    mapBinder.addBinding(IXSKDataStructureModel.TYPE_HDB_SCHEMA).to(XSKSchemaParser.class).asEagerSingleton();
//    mapBinder.addBinding(IXSKDataStructureModel.TYPE_HDB_PROCEDURE).to(XSKHDBProcedureParser.class).asEagerSingleton();
//    mapBinder.addBinding(IXSKDataStructureModel.TYPE_HDB_SEQUENCE).to(XSKHDBSequenceParser.class).asEagerSingleton();
	}

	private void bindProcessors() {
		// Entity processors instantiation

		StaticObjects.set("xskEntityUpdateProcessor", new XSKEntityUpdateProcessor());
		StaticObjects.set("xskEntityDropProcessor", new XSKEntityDropProcessor());
		StaticObjects.set("xskEntityCreateProcessor", new XSKEntityCreateProcessor());
//    bind(IXSKHdbProcessor.class).annotatedWith(Names.named("xskEntityUpdateProcessor")).to(XSKEntityUpdateProcessor.class).in(Scopes.SINGLETON);
//    bind(IXSKHdbProcessor.class).annotatedWith(Names.named("xskEntityDropProcessor")).to(XSKEntityDropProcessor.class).in(Scopes.SINGLETON);
//    bind(IXSKHdbProcessor.class).annotatedWith(Names.named("xskEntityCreateProcessor")).to(XSKEntityCreateProcessor.class).in(Scopes.SINGLETON);

		// Hdb procedure processors instantiation
		StaticObjects.set("hdbProcedureDropProcessor", new HDBProcedureDropProcessor());
		StaticObjects.set("hdbProcedureCreateProcessor", new HDBProcedureCreateProcessor());
//    bind(IXSKHdbProcessor.class).annotatedWith(Names.named("hdbProcedureDropProcessor")).to(HDBProcedureDropProcessor.class).in(Scopes.SINGLETON);
//    bind(IXSKHdbProcessor.class).annotatedWith(Names.named("hdbProcedureCreateProcessor")).to(HDBProcedureCreateProcessor.class).in(Scopes.SINGLETON);

		// Hdb schema processors instantiation
		StaticObjects.set("hdbSchemaCreateProcessor", new HDBSchemaCreateProcessor());
		StaticObjects.set("hdbSchemaDropProcessor", new HDBSchemaDropProcessor());
//    bind(IXSKHdbProcessor.class).annotatedWith(Names.named("hdbSchemaCreateProcessor")).to(HDBSchemaCreateProcessor.class).in(Scopes.SINGLETON);
//    bind(IXSKHdbProcessor.class).annotatedWith(Names.named("hdbSchemaDropProcessor")).to(HDBSchemaDropProcessor.class).in(Scopes.SINGLETON);

		// Hdb table function processors instantiation
		StaticObjects.set("hdbTableFunctionCreateProcessor", new HDBTableFunctionCreateProcessor());
		StaticObjects.set("hdbTableFunctionDropProcessor", new HDBTableFunctionDropProcessor());
//    bind(IXSKHdbProcessor.class).annotatedWith(Names.named("hdbTableFunctionCreateProcessor")).to(HDBTableFunctionCreateProcessor.class).in(Scopes.SINGLETON);
//    bind(IXSKHdbProcessor.class).annotatedWith(Names.named("hdbTableFunctionDropProcessor")).to(HDBTableFunctionDropProcessor.class).in(Scopes.SINGLETON);

		// Hdb table processors instantiation
		StaticObjects.set("xskTableCreateProcessor", new XSKTableCreateProcessor());
		StaticObjects.set("xskTableDropProcessor", new XSKTableDropProcessor());
		StaticObjects.set("xskTableAlterProcessor", new XSKTableAlterProcessor());
//    bind(IXSKHdbProcessor.class).annotatedWith(Names.named("xskTableCreateProcessor")).to(XSKTableCreateProcessor.class).in(Scopes.SINGLETON);
//    bind(IXSKHdbProcessor.class).annotatedWith(Names.named("xskTableDropProcessor")).to(XSKTableDropProcessor.class).in(Scopes.SINGLETON);
//    bind(IXSKHdbProcessor.class).annotatedWith(Names.named("xskTableAlterProcessor")).to(XSKTableAlterProcessor.class).in(Scopes.SINGLETON);

		// Hdb view processors instantiation
		StaticObjects.set("xskViewCreateProcessor", new XSKViewCreateProcessor());
		StaticObjects.set("xskViewDropProcessor", new XSKViewDropProcessor());
//    bind(IXSKHdbProcessor.class).annotatedWith(Names.named("xskViewCreateProcessor")).to(XSKViewCreateProcessor.class).in(Scopes.SINGLETON);
//    bind(IXSKHdbProcessor.class).annotatedWith(Names.named("xskViewDropProcessor")).to(XSKViewDropProcessor.class).in(Scopes.SINGLETON);

		// Hdb synonym processors instantiation
		StaticObjects.set("xskSynonymCreateProcessor", new HDBSynonymCreateProcessor());
		StaticObjects.set("xskSynonymDropProcessor", new HDBSynonymDropProcessor());
//    bind(IXSKHdbProcessor.class).annotatedWith(Names.named("xskSynonymCreateProcessor")).to(HDBSynonymCreateProcessor.class).in(Scopes.SINGLETON);
//    bind(IXSKHdbProcessor.class).annotatedWith(Names.named("xskSynonymDropProcessor")).to(HDBSynonymDropProcessor.class).in(Scopes.SINGLETON);

		// Hdb sequence processors instantiation
		StaticObjects.set("xskHdbSequenceUpdateProcessor", new XSKHDBSequenceUpdateProcessor());
		StaticObjects.set("xskHdbSequenceDropProcessor", new XSKHDBSequenceDropProcessor());
		StaticObjects.set("xskHdbSequenceCreateProcessor", new XSKHDBSequenceCreateProcessor());
//    bind(IXSKHdbProcessor.class).annotatedWith(Names.named("xskHdbSequenceUpdateProcessor")).to(XSKHDBSequenceUpdateProcessor.class).in(Scopes.SINGLETON);
//    bind(IXSKHdbProcessor.class).annotatedWith(Names.named("xskHdbSequenceDropProcessor")).to(XSKHDBSequenceDropProcessor.class).in(Scopes.SINGLETON);
//    bind(IXSKHdbProcessor.class).annotatedWith(Names.named("xskHdbSequenceCreateProcessor")).to(XSKHDBSequenceCreateProcessor.class).in(Scopes.SINGLETON);

	}

	@Override
	public String getName() {
		return "XSK HDB Module";
	}
}
