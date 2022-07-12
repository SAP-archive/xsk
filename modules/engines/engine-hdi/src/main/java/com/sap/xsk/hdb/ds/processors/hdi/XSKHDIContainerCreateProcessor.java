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
package com.sap.xsk.hdb.ds.processors.hdi;

import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdb.ds.model.hdi.XSKDataStructureHDIModel;
import com.sap.xsk.utils.XSKCommonsConstants;
import com.sap.xsk.utils.XSKCommonsUtils;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.eclipse.dirigible.commons.api.scripting.ScriptingException;
import org.eclipse.dirigible.commons.config.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XSKHDIContainerCreateProcessor {

  private static final Logger LOGGER = LoggerFactory.getLogger(XSKHDIContainerCreateProcessor.class);

  private final XSKGrantPrivilegesContainerGroupAPIProcessor grantPrivilegesContainerGroupAPIProcessor;
  private final XSKCreateContainerGroupProcessor createContainerGroupProcessor;
  private final XSKGrantPrivilegesContainerGroupProcessor grantPrivilegesContainerGroupProcessor;
  private final XSKCreateContainerProcessor createContainerProcessor;
  private final XSKGrantPrivilegesContainerAPIProcessor grantPrivilegesContainerAPIProcessor;
  private final XSKWriteContainerContentProcessor writeContainerContentProcessor;
  private final XSKConfigureLibrariesProcessor configureLibrariesProcessor;
  private final XSKDeployContainerContentProcessor deployContainerContentProcessor;
  private final XSKGrantPrivilegesContainerSchemaProcessor grantPrivilegesContainerSchemaProcessor;
  private final XSKGrantPrivilegesExternalArtifactsSchemaProcessor grantPrivilegesExternalArtifactsSchemaProcessor;
  private final XSKGrantPrivilegesDefaultRoleProcessor grantPrivilegesDefaultRoleProcessor;

  public XSKHDIContainerCreateProcessor() {
    this(new XSKGrantPrivilegesContainerGroupAPIProcessor(), new XSKCreateContainerGroupProcessor(), new XSKGrantPrivilegesContainerGroupProcessor(), new XSKCreateContainerProcessor(), new XSKGrantPrivilegesContainerAPIProcessor(), new XSKWriteContainerContentProcessor(), new XSKConfigureLibrariesProcessor(), new XSKDeployContainerContentProcessor(), new XSKGrantPrivilegesContainerSchemaProcessor(), new XSKGrantPrivilegesExternalArtifactsSchemaProcessor(), new XSKGrantPrivilegesDefaultRoleProcessor());
  }
  public XSKHDIContainerCreateProcessor(
      XSKGrantPrivilegesContainerGroupAPIProcessor grantPrivilegesContainerGroupAPIProcessor,
      XSKCreateContainerGroupProcessor createContainerGroupProcessor,
      XSKGrantPrivilegesContainerGroupProcessor grantPrivilegesContainerGroupProcessor,
      XSKCreateContainerProcessor createContainerProcessor,
      XSKGrantPrivilegesContainerAPIProcessor grantPrivilegesContainerAPIProcessor,
      XSKWriteContainerContentProcessor xskWriteContainerContentProcessor,
      XSKConfigureLibrariesProcessor configureLibrariesProcessor,
      XSKDeployContainerContentProcessor deployContainerContentProcessor,
      XSKGrantPrivilegesContainerSchemaProcessor grantPrivilegesContainerSchemaProcesso,
      XSKGrantPrivilegesExternalArtifactsSchemaProcessor grantPrivilegesExternalArtifactsSchemaProcessor,
      XSKGrantPrivilegesDefaultRoleProcessor grantPrivilegesDefaultRoleProcessor
  ) { //NOSONAR
    this.grantPrivilegesContainerGroupAPIProcessor = grantPrivilegesContainerGroupAPIProcessor;
    this.createContainerGroupProcessor =createContainerGroupProcessor;
    this.grantPrivilegesContainerGroupProcessor = grantPrivilegesContainerGroupProcessor;
    this.createContainerProcessor = createContainerProcessor;
    this.configureLibrariesProcessor = configureLibrariesProcessor;
    this.deployContainerContentProcessor = deployContainerContentProcessor;
    this.grantPrivilegesContainerSchemaProcessor = grantPrivilegesContainerSchemaProcesso;
    this.grantPrivilegesExternalArtifactsSchemaProcessor = grantPrivilegesExternalArtifactsSchemaProcessor;
    this.grantPrivilegesContainerAPIProcessor = grantPrivilegesContainerAPIProcessor;
    this.grantPrivilegesDefaultRoleProcessor = grantPrivilegesDefaultRoleProcessor;
    this.writeContainerContentProcessor = xskWriteContainerContentProcessor;
  }

  public void execute(Connection connection, XSKDataStructureHDIModel hdiModel) {
    LOGGER.info("Start processing HDI Containers...");
    try {
      LOGGER.info("Start processing HDI Container [{}] from [{}] ...", hdiModel.getContainer(), hdiModel.getLocation());

      // Grant Privileges to Container Group API
      this.grantPrivilegesContainerGroupAPIProcessor.execute(connection, hdiModel.getUsers());

      // Create a Container Group
      this.createContainerGroupProcessor.execute(connection, hdiModel.getGroup());

      List<String> users = new ArrayList<>(Arrays.asList(hdiModel.getUsers()));
      users.add(Configuration.get("HANA_USERNAME"));
      String[] usersAsArray = users.toArray(new String[0]);

      // Grant Privileges to the Container Group
      this.grantPrivilegesContainerGroupProcessor.execute(connection, hdiModel.getGroup(), usersAsArray);

      // Create a Container
      this.createContainerProcessor.execute(connection, hdiModel.getGroup(), hdiModel.getContainer());

      // Grant Privileges to Container API
      this.grantPrivilegesContainerAPIProcessor.execute(connection, hdiModel.getGroup(), hdiModel.getContainer(), usersAsArray);

      // Write the files content to the Container
      this.writeContainerContentProcessor.execute(connection, hdiModel.getContainer(), hdiModel.getDeploy(), hdiModel.getConfiguration());

      // Configure Libraries for the Container
      Set<String> pluginsToBEActivated = new HashSet<>(List.of("com.sap.hana.di.publicsynonym"));
      this.configureLibrariesProcessor.execute(connection, hdiModel.getContainer(), pluginsToBEActivated);

      //Grant Privileges on the external artifacts' schemas
      grantPrivilegesExternalArtifactsSchemaProcessor.execute(connection, hdiModel.getContainer(), hdiModel.getDeploy());

      // Deploy the Content
      this.deployContainerContentProcessor.execute(connection, hdiModel.getContainer(), hdiModel.getDeploy(), hdiModel.getUndeploy());

      // Grant Privileges to the default role
      this.grantPrivilegesDefaultRoleProcessor.execute(connection, hdiModel.getContainer(), Configuration.get("HANA_USERNAME"), hdiModel.getDeploy(),
          hdiModel.getPackageName());

      // Grant Privileges to the Container Schema
      this.grantPrivilegesContainerSchemaProcessor.execute(connection, hdiModel.getContainer(), usersAsArray);

      LOGGER.info("HDI Container [{}] from [{}] finished successfully.", hdiModel.getContainer(), hdiModel.getLocation());
    } catch (SQLException | IOException | ScriptingException | XSKDataStructuresException e) {
      String errorMessage = String.format("HDI Container %s from %s failed.", hdiModel.getContainer(), hdiModel.getLocation());
      XSKCommonsUtils.logProcessorErrors(errorMessage, XSKCommonsConstants.PROCESSOR_ERROR, hdiModel.getLocation(),
          XSKCommonsConstants.HDI_PROCESSOR);
      LOGGER.error(errorMessage, e);
    }

    LOGGER.info("Done processing HDI Containers.");
  }

}
