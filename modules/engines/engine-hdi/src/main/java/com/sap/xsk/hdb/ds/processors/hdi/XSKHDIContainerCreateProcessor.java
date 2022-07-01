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

  private final XSKGrantPrivilegesContainerGroupAPIProcessor grantPrivilegesContainerGroupAPIProcessor = new XSKGrantPrivilegesContainerGroupAPIProcessor();
  private final XSKCreateContainerGroupProcessor createContainerGroupProcessor = new XSKCreateContainerGroupProcessor();
  private final XSKGrantPrivilegesContainerGroupProcessor grantPrivilegesContainerGroupProcessor = new XSKGrantPrivilegesContainerGroupProcessor();
  private final XSKCreateContainerProcessor createContainerProcessor = new XSKCreateContainerProcessor();
  private final XSKGrantPrivilegesContainerAPIProcessor grantPrivilegesContainerAPIProcessor = new XSKGrantPrivilegesContainerAPIProcessor();
  private final XSKWriteContainerContentProcessor writeContainerContentProcessor = new XSKWriteContainerContentProcessor();
  private final XSKConfigureLibrariesProcessor configureLibrariesProcessor = new XSKConfigureLibrariesProcessor();
  private final XSKDeployContainerContentProcessor deployContainerContentProcessor = new XSKDeployContainerContentProcessor();
  private final XSKGrantPrivilegesContainerSchemaProcessor grantPrivilegesContainerSchemaProcessor = new XSKGrantPrivilegesContainerSchemaProcessor();
  private final XSKGrantPrivilegesExternalArtifactsSchemaProcessor grantPrivilegesExternalArtifactsSchemaProcessor = new XSKGrantPrivilegesExternalArtifactsSchemaProcessor();
  private final XSKGrantPrivilegesDefaultRoleProcessor grantPrivilegesDefaultRoleProcessor = new XSKGrantPrivilegesDefaultRoleProcessor();

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
