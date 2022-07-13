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
import org.eclipse.dirigible.commons.config.Configuration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class XSKHDIContainerCreateProcessorTest {

  @Mock(answer = Answers.RETURNS_DEEP_STUBS)
  private Connection mockConnection;

  @Test
  public void testExecute() throws SQLException, IOException, XSKDataStructuresException {

    XSKGrantPrivilegesContainerGroupAPIProcessor grantPrivilegesContainerGroupAPIProcessor = Mockito.mock(XSKGrantPrivilegesContainerGroupAPIProcessor.class);
    XSKCreateContainerGroupProcessor createContainerGroupProcessor = Mockito.mock(XSKCreateContainerGroupProcessor.class);
    XSKGrantPrivilegesContainerGroupProcessor grantPrivilegesContainerGroupProcessor = Mockito.mock(XSKGrantPrivilegesContainerGroupProcessor.class);
    XSKCreateContainerProcessor createContainerProcessor = Mockito.mock(XSKCreateContainerProcessor.class);
    XSKGrantPrivilegesContainerAPIProcessor grantPrivilegesContainerAPIProcessor = Mockito.mock(XSKGrantPrivilegesContainerAPIProcessor.class);
    XSKWriteContainerContentProcessor writeContainerContentProcessor = Mockito.mock(XSKWriteContainerContentProcessor.class);
    XSKConfigureLibrariesProcessor configureLibrariesProcessor = Mockito.mock(XSKConfigureLibrariesProcessor.class);
    XSKDeployContainerContentProcessor deployContainerContentProcessor = Mockito.mock(XSKDeployContainerContentProcessor.class);
    XSKGrantPrivilegesContainerSchemaProcessor grantPrivilegesContainerSchemaProcessor = Mockito.mock(XSKGrantPrivilegesContainerSchemaProcessor.class);
    XSKGrantPrivilegesExternalArtifactsSchemaProcessor grantPrivilegesExternalArtifactsSchemaProcessor = Mockito.mock(XSKGrantPrivilegesExternalArtifactsSchemaProcessor.class);
    XSKGrantPrivilegesDefaultRoleProcessor grantPrivilegesDefaultRoleProcessor = Mockito.mock(XSKGrantPrivilegesDefaultRoleProcessor.class);

    XSKHDIContainerCreateProcessor processor = new XSKHDIContainerCreateProcessor(grantPrivilegesContainerGroupAPIProcessor,
        createContainerGroupProcessor,
        grantPrivilegesContainerGroupProcessor,
        createContainerProcessor,
        grantPrivilegesContainerAPIProcessor,
        writeContainerContentProcessor,
        configureLibrariesProcessor,
        deployContainerContentProcessor,
        grantPrivilegesContainerSchemaProcessor,
        grantPrivilegesExternalArtifactsSchemaProcessor,
        grantPrivilegesDefaultRoleProcessor
    );
    XSKDataStructureHDIModel hdiModel = new XSKDataStructureHDIModel();

    String container = "test-container";
    String[] files = new String[]{};
    String[] users = new String[]{};
    String group = "test-group";
    String[] deploy = new String[]{};
    String[] undeploy = new String[]{};
    String name = "test.hdi";
    String config = "test-config";

    hdiModel.setUsers(users);
    hdiModel.setContainer(container);
    hdiModel.setGroup(group);
    hdiModel.setDeploy(deploy);
    hdiModel.setUndeploy(undeploy);
    hdiModel.setConfiguration(config);
    hdiModel.setName(name);

    processor.execute(mockConnection, hdiModel);

    verify(grantPrivilegesContainerGroupAPIProcessor, times(1)).execute(mockConnection, users);
    verify(createContainerGroupProcessor, times(1)).execute(mockConnection, group);
    verify(grantPrivilegesContainerGroupProcessor, times(1)).execute(mockConnection, group, users);
    verify(createContainerProcessor, times(1)).execute(mockConnection, group, container);
    verify(grantPrivilegesContainerAPIProcessor, times(1)).execute(mockConnection, group, container, users);
    verify(writeContainerContentProcessor, times(1)).execute(mockConnection, container, files, config);
    verify(deployContainerContentProcessor, times(1)).execute(mockConnection, container, deploy, undeploy);
    verify(grantPrivilegesContainerSchemaProcessor, times(1)).execute(mockConnection, container, users);
    verify(grantPrivilegesExternalArtifactsSchemaProcessor, times(1)).execute(mockConnection, container, deploy);
    verify(grantPrivilegesDefaultRoleProcessor, times(1)).execute(mockConnection, hdiModel.getContainer(), Configuration.get("HANA_USERNAME"), hdiModel.getDeploy());
  }

}
