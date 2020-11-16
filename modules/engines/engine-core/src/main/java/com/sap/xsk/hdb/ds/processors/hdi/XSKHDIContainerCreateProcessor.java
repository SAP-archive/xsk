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
package com.sap.xsk.hdb.ds.processors.hdi;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.eclipse.dirigible.commons.api.scripting.ScriptingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.xsk.hdb.ds.model.hdi.XSKDataStructureHDIModel;

public class XSKHDIContainerCreateProcessor {
	
	private static final Logger logger = LoggerFactory.getLogger(XSKHDIContainerCreateProcessor.class);
	
	private static XSKHDIContainerCreateProcessor INSTANCE = new XSKHDIContainerCreateProcessor();
	
	private XSKGrantPrivilegesContainerGroupAPIProcessor grantPrivilegesContainerGroupAPIProcessor = new XSKGrantPrivilegesContainerGroupAPIProcessor();
	private XSKCreateContainerGroupProcessor createContainerGroupProcessor = new XSKCreateContainerGroupProcessor();
	private XSKGrantPrivilegesContainerGroupProcessor grantPrivilegesContainerGroupProcessor = new XSKGrantPrivilegesContainerGroupProcessor();
	private XSKCreateContainerProcessor createContainerProcessor = new XSKCreateContainerProcessor();
	private XSKGrantPrivilegesContainerAPIProcessor grantPrivilegesContainerAPIProcessor = new XSKGrantPrivilegesContainerAPIProcessor();
	private XSKWriteContainerContentProcessor writeContainerContentProcessor = new XSKWriteContainerContentProcessor();
	private XSKConfigureLibrariesProcessor configureLibrariesProcessor = new XSKConfigureLibrariesProcessor();
	private XSKDeployContainerContentProcessor deployContainerContentProcessor = new XSKDeployContainerContentProcessor();
	private XSKGrantPrivilegesContainerSchemaProcessor grantPrivilegesContainerSchemaProcessor = new XSKGrantPrivilegesContainerSchemaProcessor();
	private XSKGrantPrivilegesContainerTargetSchemaProcessor grantPrivilegesContainerTargetSchemaProcessor = new XSKGrantPrivilegesContainerTargetSchemaProcessor();
	
	
	private XSKHDIContainerCreateProcessor() {}

    public static void execute(Connection connection, List<XSKDataStructureHDIModel> hdiModels) {
    	
    	if (hdiModels.isEmpty()) {
    		return;
    	}
    	
    	logger.info("Start processing HDI Containers...");
    	
    	for (XSKDataStructureHDIModel hdiModel : hdiModels) {
    		try {
				logger.info("Start processing HDI Container [{0}] from [{1}] ...", hdiModel.getContainer(), hdiModel.getLocation());
				
				// Grant Privileges to Container Group API
				INSTANCE.grantPrivilegesContainerGroupAPIProcessor.execute(connection, hdiModel.getUsers());
				
				// Create a Container Group
				INSTANCE.createContainerGroupProcessor.execute(connection, hdiModel.getGroup());
				
				// Grant Privileges to the Container Group
				INSTANCE.grantPrivilegesContainerGroupProcessor.execute(connection, hdiModel.getGroup(), hdiModel.getUsers());

				// Create a Container
				INSTANCE.createContainerProcessor.execute(connection, hdiModel.getGroup(), hdiModel.getContainer());
				
				// Grant Privileges to Container API
				INSTANCE.grantPrivilegesContainerAPIProcessor.execute(connection, hdiModel.getGroup(), hdiModel.getContainer(), hdiModel.getUsers());
				
				// Write the files content to the Container
				INSTANCE.writeContainerContentProcessor.execute(connection, hdiModel.getContainer(), hdiModel.getDeploy(), hdiModel.getConfiguration());
				
				// Configure Libraries for the Container
				INSTANCE.configureLibrariesProcessor.execute(connection, hdiModel.getContainer());
				
				// Grant Privileges on the Target Schema
				INSTANCE.grantPrivilegesContainerTargetSchemaProcessor.execute(connection, hdiModel.getContainer(), hdiModel.getUsers());
				
				// Deploy the Content
				INSTANCE.deployContainerContentProcessor.execute(connection, hdiModel.getContainer(), hdiModel.getDeploy(), hdiModel.getUndeploy());
				
				// Grant Privileges to the Container Schema
				INSTANCE.grantPrivilegesContainerSchemaProcessor.execute(connection, hdiModel.getContainer(), hdiModel.getUsers());
				
				logger.info("HDI Container [{0}] from [{1}] finished successfully.", hdiModel.getContainer(), hdiModel.getLocation());
			} catch (SQLException | IOException | ScriptingException e) {
				logger.error("HDI Container [{0}] from [{1}] failed.\", hdiModel.getContainer(), hdiModel.getLocation()");
				logger.error(e.getMessage(), e);
			}
    	}
    	
    	logger.info("Done rocessing HDI Containers.");
    	
    }

}
