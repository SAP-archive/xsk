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
package com.sap.xsk.hdb.ds.itest.module;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import javax.sql.DataSource;

import org.eclipse.dirigible.commons.api.module.AbstractDirigibleModule;
import org.eclipse.dirigible.commons.config.Configuration;
import org.eclipse.dirigible.commons.config.StaticObjects;
import org.eclipse.dirigible.database.api.IDatabase;
import org.eclipse.dirigible.database.custom.CustomDatabase;
import org.eclipse.dirigible.database.h2.H2Database;
import org.eclipse.dirigible.repository.api.RepositoryPath;
import org.eclipse.dirigible.repository.fs.FileSystemRepository;
import org.eclipse.dirigible.repository.local.LocalRepository;
import org.eclipse.dirigible.repository.local.LocalResource;

import com.sap.xsk.hdb.ds.api.IXSKDataStructureModel;
import com.sap.xsk.hdb.ds.itest.model.JDBCModel;
import com.sap.xsk.hdb.ds.itest.repository.TestRepository;
import com.sap.xsk.hdb.ds.module.XSKHDBModule;
import com.sap.xsk.hdb.ds.service.manager.IXSKDataStructureManager;
import com.sap.xsk.hdb.ds.service.manager.XSKEntityManagerService;
import com.sap.xsk.hdb.ds.service.manager.XSKHDBSequenceManagerService;
import com.sap.xsk.hdb.ds.service.manager.XSKProceduresManagerService;
import com.sap.xsk.hdb.ds.service.manager.XSKScalarFunctionManagerService;
import com.sap.xsk.hdb.ds.service.manager.XSKSchemaManagerService;
import com.sap.xsk.hdb.ds.service.manager.IXSKSynonymManagerService;
import com.sap.xsk.hdb.ds.service.manager.XSKTableFunctionManagerService;
import com.sap.xsk.hdb.ds.service.manager.XSKTableManagerService;
import com.sap.xsk.hdb.ds.service.manager.XSKTableTypeManagerService;
import com.sap.xsk.hdb.ds.service.manager.XSKViewManagerService;

public class XSKHDBTestModule extends AbstractDirigibleModule {

	private JDBCModel model;

	public XSKHDBTestModule(JDBCModel model) {
		this.model = model;
	}

	public DataSource getDataSource() {
		Configuration.set(IDatabase.DIRIGIBLE_DATABASE_PROVIDER, "custom");
		Configuration.set(IDatabase.DIRIGIBLE_DATABASE_CUSTOM_DATASOURCES, "HANA");
		Configuration.set(IDatabase.DIRIGIBLE_DATABASE_DATASOURCE_NAME_DEFAULT, "HANA");
		Configuration.set(IDatabase.DIRIGIBLE_DATABASE_DEFAULT_MAX_CONNECTIONS_COUNT, "32");
		Configuration.set("DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE", "true");
		Configuration.set(IDatabase.DIRIGIBLE_DATABASE_DEFAULT_SET_AUTO_COMMIT, "true");


		Configuration.set("HANA_URL", model.getJdbcUrl());
		Configuration.set("HANA_DRIVER", model.getDriverClassName());
		Configuration.set("HANA_USERNAME", model.getUsername());
		Configuration.set("HANA_PASSWORD", model.getPassword());

		CustomDatabase customDatabase = new CustomDatabase();
		return customDatabase.getDataSource();
	}

	public DataSource getSystemDataSource() {
		H2Database h2Database = new H2Database();
		return h2Database.getDataSource();
	}

	public static LocalResource getResources(String rootFolder, String repoPath, String relativeResourcePath) throws IOException {
		FileSystemRepository fileRepo = new LocalRepository(rootFolder);
		RepositoryPath path = new RepositoryPath(repoPath);
		byte[] content = XSKHDBTestModule.class.getResourceAsStream(relativeResourcePath).readAllBytes();

		LocalResource resource = new LocalResource(fileRepo, path);
		resource.setContent(content);
		return resource;
	}

	public static LocalResource getResourceFromString(String rootFolder, String repoPath, String fileContent) throws IOException {
		FileSystemRepository fileRepo = new LocalRepository(rootFolder);
		RepositoryPath path = new RepositoryPath(repoPath);
		byte[] content = fileContent.getBytes(StandardCharsets.UTF_8);
		LocalResource resource = new LocalResource(fileRepo, path);
		resource.setContent(content);
		return resource;
	}

	@Override
	public String getName() {
		return "XSKHDBTestModule";
	}

	@Override
	public void configure() {
		StaticObjects.set(StaticObjects.DATASOURCE, getDataSource());
		StaticObjects.set(StaticObjects.SYSTEM_DATASOURCE, getSystemDataSource());
		StaticObjects.set(StaticObjects.REPOSITORY, new TestRepository());

		// when we run all integration tests at once, the first run test will determine
		// the datasource of XSKDataStructuresCoreService.
		// this can lead to issue, for example running MYSQL test which is accessing the
		// HANA db, and leading to inconsistent test results
		// that is why we are clearing and reentering the managerServices
		// see: XSKHDBModule.bindManagerServicesToFileExtensions()
		Map<String, IXSKDataStructureManager> managerServices = XSKHDBModule.getManagerServices();
		managerServices.clear();
		managerServices.put(IXSKDataStructureModel.TYPE_HDBDD, new XSKEntityManagerService());
		managerServices.put(IXSKDataStructureModel.TYPE_HDB_TABLE, new XSKTableManagerService());
		managerServices.put(IXSKDataStructureModel.TYPE_HDB_VIEW, new XSKViewManagerService());
		managerServices.put(IXSKDataStructureModel.TYPE_HDB_SYNONYM, new IXSKSynonymManagerService());
		managerServices.put(IXSKDataStructureModel.TYPE_HDB_TABLE_FUNCTION, new XSKTableFunctionManagerService());
		managerServices.put(IXSKDataStructureModel.TYPE_HDB_SCHEMA, new XSKSchemaManagerService());
		managerServices.put(IXSKDataStructureModel.TYPE_HDB_PROCEDURE, new XSKProceduresManagerService());
		managerServices.put(IXSKDataStructureModel.TYPE_HDB_SEQUENCE, new XSKHDBSequenceManagerService());
		managerServices.put(IXSKDataStructureModel.TYPE_HDB_SCALAR_FUNCTION, new XSKScalarFunctionManagerService());
		managerServices.put(IXSKDataStructureModel.TYPE_HDB_TABLE_TYPE, new XSKTableTypeManagerService());
	}
}
