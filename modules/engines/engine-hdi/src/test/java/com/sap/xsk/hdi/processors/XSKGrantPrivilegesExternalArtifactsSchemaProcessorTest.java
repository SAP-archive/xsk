/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2022 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.hdi.processors;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.sap.xsk.hdb.ds.model.hdbsynonym.XSKDataStructureHDBSynonymModel;
import com.sap.xsk.hdb.ds.model.hdbsynonym.XSKHDBSYNONYMDefinitionModel;
import com.sap.xsk.hdb.ds.model.hdbsynonym.XSKHDBSYNONYMDefinitionModel.Target;
import com.sap.xsk.hdb.ds.processors.hdi.XSKGrantPrivilegesExternalArtifactsSchemaProcessor;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
public class XSKGrantPrivilegesExternalArtifactsSchemaProcessorTest {

  @Mock(answer = Answers.RETURNS_DEEP_STUBS)
  private Connection mockConnection;

  @Test
  public void executeGrantPrivilegesExternalArtifactsSchemaSuccessfully() throws SQLException {
    XSKGrantPrivilegesExternalArtifactsSchemaProcessor processorSpy = spy(XSKGrantPrivilegesExternalArtifactsSchemaProcessor.class);

    String mockSQLSelect = "GRANT SELECT ON SCHEMA \"externalSchema1\" TO \"testContainer#OO\" WITH GRANT OPTION;";
    String mockSQLExecute = "GRANT EXECUTE ON SCHEMA \"externalSchema1\" TO \"testContainer#OO\" WITH GRANT OPTION;";
    String container = "testContainer";
    String[] deploys = {"/com/sap/testSynonym.hdbsynonym"};
    XSKDataStructureHDBSynonymModel synonymModel = new XSKDataStructureHDBSynonymModel();
    XSKHDBSYNONYMDefinitionModel synonymDefinitionModel = new XSKHDBSYNONYMDefinitionModel();
    synonymDefinitionModel.setTarget(new Target("externalArtefact1", "externalSchema1"));
    synonymModel.setSynonymDefinitions(Map.of("synonymDef1", synonymDefinitionModel));


    processorSpy.execute(mockConnection, container, deploys, synonymModel);
    verify(processorSpy, times(1)).executeUpdate(mockConnection,mockSQLSelect, new String[]{});
    verify(processorSpy, times(1)).executeUpdate(mockConnection,mockSQLExecute, new String[]{});
  }
}
