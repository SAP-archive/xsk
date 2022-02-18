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

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.sap.xsk.hdb.ds.model.hdbsynonym.XSKDataStructureHDBSynonymModel;
import com.sap.xsk.hdb.ds.model.hdbsynonym.XSKHDBSYNONYMDefinitionModel;
import com.sap.xsk.hdb.ds.model.hdbsynonym.XSKHDBSYNONYMDefinitionModel.Target;
import com.sap.xsk.hdb.ds.processors.hdi.XSKGrantPrivilegesExternalArtifactsSchemaProcessor;

@RunWith(MockitoJUnitRunner.class)
public class XSKGrantPrivilegesExternalArtifactsSchemaProcessorTest {

  @Mock(answer = Answers.RETURNS_DEEP_STUBS)
  private Connection mockConnection;

  @Test
  public void testOneSynonymFile() throws XSKDataStructuresException, SQLException, IOException {
    SynonymDefinition definition = new SynonymDefinition("externalArtefact1", "schema");
    HDISynonym synonym = new HDISynonym("/package/test.hdbsynonym", definition);

    testGrantPrivilege(synonym);
  }

  @Test
  public void testOneSynonymFileWithTwoDefinitions() throws XSKDataStructuresException, SQLException, IOException {
    SynonymDefinition definition1 = new SynonymDefinition("externalArtefact1", "schema");
    SynonymDefinition definition2 = new SynonymDefinition("externalArtefact2", "schema2");
    HDISynonym synonym = new HDISynonym("/package/test.hdbsynonym", definition1, definition2);

    testGrantPrivilege(synonym);
  }

  @Test
  public void testTwoSynonymFileWithTwoDefinitions() throws XSKDataStructuresException, SQLException, IOException {
    SynonymDefinition definition1 = new SynonymDefinition("externalArtefact1", "schema");
    SynonymDefinition definition2 = new SynonymDefinition("externalArtefact2", "schema2");
    HDISynonym synonym = new HDISynonym("/package/test.hdbsynonym", definition1, definition2);

    SynonymDefinition definition3 = new SynonymDefinition("externalArtefact3", "schema3");
    SynonymDefinition definition4 = new SynonymDefinition("externalArtefact4", "schema4");
    HDISynonym synonym2 = new HDISynonym("/package/.hdbsynonym", definition3, definition4);

    testGrantPrivilege(synonym, synonym2);
  }

  public void testGrantPrivilege(HDISynonym... synonyms) throws SQLException, IOException, XSKDataStructuresException {
    List<String> deploys = new ArrayList<>();
    List<String> synonymDeploys = Arrays.stream(synonyms).map(HDISynonym::getLocation).collect(Collectors.toList());
    deploys.addAll(synonymDeploys);
    deploys.add("/some-package/calc-view.hdbcalculationview");
    deploys.add("/some-package-2/tablefunction.hdbtablefunction");

    XSKGrantPrivilegesExternalArtifactsSchemaProcessor processorSpy = stubProcessor(synonyms);

    String container = "testContainer";
    String containerOwner = container + "#OO";
    processorSpy.execute(mockConnection, container, deploys.toArray(new String[0]));

    Arrays.stream(synonyms).forEach(bombBox(synonym -> {
      synonym.getModel().getSynonymDefinitions().forEach( bombBox((key, definition) -> {
        String schema = definition.getTarget().getSchema();
        String mockSQLSelect = "GRANT SELECT ON SCHEMA \"" + schema + "\" TO \"" + containerOwner + "\" WITH GRANT OPTION;";
        String mockSQLExecute = "GRANT EXECUTE ON SCHEMA \"" + schema + "\" TO \"" + containerOwner + "\" WITH GRANT OPTION;";
        String mockSQLInsert = "GRANT INSERT ON SCHEMA \"" + schema + "\" TO \"" + containerOwner + "\" WITH GRANT OPTION;";
        String mockSQLUpdate = "GRANT UPDATE ON SCHEMA \"" + schema + "\" TO \"" + containerOwner + "\" WITH GRANT OPTION;";
        String mockSQLDelete = "GRANT DELETE ON SCHEMA \"" + schema + "\" TO \"" + containerOwner + "\" WITH GRANT OPTION;";
          verify(processorSpy, times(1)).executeUpdate(mockConnection, mockSQLExecute, new String[]{});
          verify(processorSpy, times(1)).executeUpdate(mockConnection, mockSQLSelect, new String[]{});
          verify(processorSpy, times(1)).executeUpdate(mockConnection, mockSQLInsert, new String[]{});
          verify(processorSpy, times(1)).executeUpdate(mockConnection, mockSQLUpdate, new String[]{});
          verify(processorSpy, times(1)).executeUpdate(mockConnection, mockSQLDelete, new String[]{});
      }));

      verify(processorSpy, times(1)).getSynonymModel(eq(synonym.getLocation()), anyString());
    }));

  }

  private XSKGrantPrivilegesExternalArtifactsSchemaProcessor stubProcessor(HDISynonym[] synonyms) {
    XSKGrantPrivilegesExternalArtifactsSchemaProcessor processorSpy = spy(XSKGrantPrivilegesExternalArtifactsSchemaProcessor.class);
    Arrays.stream(synonyms).forEach(bombBox(synonym -> {
        doReturn(synonym.getModel()).when(processorSpy).getSynonymModel(eq(synonym.getLocation()), anyString());
        doReturn("blablabla").when(processorSpy).getSynonymContent(synonym.getLocation());
    }));
    return processorSpy;
  }

  static <T> Consumer<T> bombBox(ReThrowingConsumer<T, Exception> throwingConsumer) {
    return i -> {
      try {
        throwingConsumer.accept(i);
      } catch (Exception ex) {
        Assert.fail("Unexpected exception occur! - " + ex.getMessage());
      }
    };
  }

  static <T, X> BiConsumer<T, X> bombBox(ReThrowingBiConsumer<T, X, Exception> throwingConsumer) {
    return (i, y) -> {
      try {
        throwingConsumer.accept(i, y);
      } catch (Exception ex) {
        Assert.fail("Unexpected exception occur! - " + ex.getMessage());
      }
    };
  }
}

@FunctionalInterface
interface ReThrowingConsumer<T, E extends Exception> {
  void accept(T t) throws E;
}

@FunctionalInterface
interface ReThrowingBiConsumer<T, X, E extends Exception> {
  void accept(T t, X x) throws E;
}

class HDISynonym {
  final String location;
  final XSKDataStructureHDBSynonymModel model;

  public HDISynonym(String location, SynonymDefinition... definitions){
    this.location = location;
    this.model = createSynonymModel(definitions);
  }

  public String getLocation() {
    return location;
  }

  public XSKDataStructureHDBSynonymModel getModel() {
    return model;
  }

  private XSKDataStructureHDBSynonymModel createSynonymModel(SynonymDefinition... definitions){
    XSKDataStructureHDBSynonymModel synonymModel = new XSKDataStructureHDBSynonymModel();

    Map<String, XSKHDBSYNONYMDefinitionModel> models = Arrays.stream(definitions)
        .collect(Collectors.toMap(SynonymDefinition::getArtifactName, SynonymDefinition::getModel));
    synonymModel.setSynonymDefinitions(models);

    return synonymModel;
  }
}

class SynonymDefinition {
  final String artifactName;
  final XSKHDBSYNONYMDefinitionModel model;

  public SynonymDefinition(String artifactName, String schemaName){
    this.artifactName = artifactName;
    this.model = createSynonymDefinition(artifactName, schemaName);
  }

  private XSKHDBSYNONYMDefinitionModel createSynonymDefinition(String artifactName, String schemaName){
    XSKHDBSYNONYMDefinitionModel synonymDefinitionModel = new XSKHDBSYNONYMDefinitionModel();
    synonymDefinitionModel.setTarget(new Target(artifactName, schemaName));
    return synonymDefinitionModel;
  }

  public String getArtifactName(){
    return artifactName;
  }

  public XSKHDBSYNONYMDefinitionModel getModel() {
    return model;
  }
}
