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
package com.sap.xsk.xsodata.ds.handler;

import com.google.gson.internal.LinkedTreeMap;
import org.apache.olingo.odata2.api.commons.HttpStatusCodes;
import org.apache.olingo.odata2.api.edm.EdmException;
import org.apache.olingo.odata2.api.edm.EdmType;
import org.apache.olingo.odata2.api.ep.EntityProviderException;
import org.apache.olingo.odata2.api.ep.entry.ODataEntry;
import org.apache.olingo.odata2.api.processor.ODataResponse;
import org.apache.olingo.odata2.api.uri.UriInfo;
import org.apache.olingo.odata2.core.ep.BasicEntityProvider;
import org.eclipse.dirigible.commons.api.helpers.GsonHelper;
import org.eclipse.dirigible.commons.config.StaticObjects;
import org.eclipse.dirigible.engine.odata2.api.ODataException;
import org.eclipse.dirigible.engine.odata2.definition.ODataHandlerDefinition;
import org.eclipse.dirigible.engine.odata2.service.ODataCoreService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.sap.xsk.xsodata.ds.handler.AbstractXSKOData2EventHandler.CONNECTION;
import static com.sap.xsk.xsodata.ds.handler.AbstractXSKOData2EventHandler.AFTER_TABLE_NAME;
import static com.sap.xsk.xsodata.ds.handler.AbstractXSKOData2EventHandler.BEFORE_DELETE_ENTITY_TABLE_NAME;
import static com.sap.xsk.xsodata.ds.handler.AbstractXSKOData2EventHandler.BEFORE_TABLE_NAME;
import static com.sap.xsk.xsodata.ds.handler.AbstractXSKOData2EventHandler.BEFORE_UPDATE_ENTITY_TABLE_NAME;
import static com.sap.xsk.xsodata.ds.handler.AbstractXSKOData2EventHandler.DATASOURCE;
import static com.sap.xsk.xsodata.ds.handler.AbstractXSKOData2EventHandler.ENTRY_MAP;
import static com.sap.xsk.xsodata.ds.handler.AbstractXSKOData2EventHandler.ON_CREATE_ENTITY_TABLE_NAME;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;


@RunWith(MockitoJUnitRunner.class)
public class XSKOData2EventHandlerTest {

  @Mock
  private EdmType edmType;

  @Mock
  private UriInfo uriInfo;

  @Mock
  private ODataCoreService odataCoreService;

  @Mock
  private DataSource dataSource;

  @Mock
  private ODataEntry entry;

  @Mock
  private InputStream inputStream;

  private static MockedStatic<StaticObjects> staticObjects;

  private XSKScriptingOData2EventHandler spyScriptingHandler;
  private XSKProcedureOData2EventHandler spyProcedureHandler;
  private XSKOData2EventHandler xskoData2EventHandler;
  private Map<Object, Object> scriptingHandlerContext;
  private Map<Object, Object> procedureHandlerContext;

  @Before
  public void setup() {
    staticObjects = Mockito.mockStatic(StaticObjects.class);
    staticObjects.when(() -> StaticObjects.get(StaticObjects.DATASOURCE)).thenReturn(dataSource, dataSource);
    spyScriptingHandler = Mockito.spy(new XSKScriptingOData2EventHandler());
    spyProcedureHandler = Mockito.spy(new XSKProcedureOData2EventHandler());
    xskoData2EventHandler = new XSKOData2EventHandler(odataCoreService, spyProcedureHandler, spyScriptingHandler);
    scriptingHandlerContext = new HashMap();
    procedureHandlerContext = new HashMap();
    procedureHandlerContext.put(DATASOURCE, dataSource);
  }

  @After
  public void teardown() {
    staticObjects.close();
  }

  @Test
  public void testBeforeCreateEntity() throws org.apache.olingo.odata2.api.exception.ODataException, ODataException, SQLException {
    // procedure
    mockGetHandlers();
    mockTemporaryTables(spyProcedureHandler);
    mockCallProcedure(spyProcedureHandler, false);
    ODataResponse response = xskoData2EventHandler.beforeCreateEntity(uriInfo, "application/json", "application/json", entry,
        procedureHandlerContext);
    assertResponse(response);

    // .xsjslib
    mockTemporaryTables(spyScriptingHandler);
    Mockito.doNothing().when(spyScriptingHandler).callSuperBeforeCreateEntity(any(), any(), any(), any(), any());
    xskoData2EventHandler.beforeCreateEntity(uriInfo, "application/json", "application/json", entry, scriptingHandlerContext);
    assertTrue(scriptingHandlerContext.containsKey("connection"));
    assertTrue(scriptingHandlerContext.containsKey("afterTableName"));
  }

  @Test
  public void testAfterCreateEntity() throws ODataException, org.apache.olingo.odata2.api.exception.ODataException, SQLException {
    // procedure
    mockGetHandlers();
    mockTemporaryTables(spyProcedureHandler);
    mockCallProcedure(spyProcedureHandler, false);
    ODataResponse response = xskoData2EventHandler.afterCreateEntity(uriInfo, "application/json", "application/json", entry,
        procedureHandlerContext);
    assertResponse(response);

    // .xsjslib
    mockTemporaryTables(spyScriptingHandler);
    Mockito.doNothing().when(spyScriptingHandler).callSuperAfterCreateEntity(any(), any(), any(), any(), any());
    xskoData2EventHandler.afterCreateEntity(uriInfo, "application/json", "application/json", entry, scriptingHandlerContext);
    assertTrue(scriptingHandlerContext.containsKey(CONNECTION));
    assertTrue(scriptingHandlerContext.containsKey(AFTER_TABLE_NAME));
  }

  @Test
  public void testOnCreateEntity() throws ODataException, org.apache.olingo.odata2.api.exception.ODataException, SQLException {
    // procedure
    mockGetHandlers();
    mockTemporaryTables(spyProcedureHandler);
    mockCallProcedure(spyProcedureHandler, false);
    ODataResponse response = xskoData2EventHandler.onCreateEntity(uriInfo, inputStream, "application/json", "application/json",
        procedureHandlerContext);
    assertResponse(response);

    // .xsjslib
    mockTemporaryTables(spyScriptingHandler);
    Mockito.doNothing().when(spyScriptingHandler).callSuperOnCreateEntity(any(), any(), any(), any(), any());
    xskoData2EventHandler.onCreateEntity(uriInfo, inputStream, "application/json", "application/json", scriptingHandlerContext);
    assertTrue(scriptingHandlerContext.containsKey(CONNECTION));
    assertTrue(scriptingHandlerContext.containsKey(AFTER_TABLE_NAME));
    assertTrue(scriptingHandlerContext.containsKey(ON_CREATE_ENTITY_TABLE_NAME));
    assertTrue(scriptingHandlerContext.containsKey(ENTRY_MAP));
  }

  @Test
  public void testBeforeUpdateEntity() throws ODataException, org.apache.olingo.odata2.api.exception.ODataException, SQLException {
    // procedure
    mockGetHandlers();
    mockTemporaryTables(spyProcedureHandler);
    mockCallProcedure(spyProcedureHandler, true);
    ODataResponse response = xskoData2EventHandler.beforeUpdateEntity(uriInfo, "application/json", true, "application/json", entry,
        procedureHandlerContext);
    assertResponse(response);

    // .xsjslib
    mockTemporaryTables(spyScriptingHandler);
    Mockito.doNothing().when(spyScriptingHandler).callSuperBeforeUpdateEntity(any(), any(), anyBoolean(), any(), any(), any());
    xskoData2EventHandler.beforeUpdateEntity(uriInfo, "application/json", true, "application/json", entry, scriptingHandlerContext);
    assertTrue(scriptingHandlerContext.containsKey(CONNECTION));
    assertTrue(scriptingHandlerContext.containsKey(BEFORE_TABLE_NAME));
    assertTrue(scriptingHandlerContext.containsKey(AFTER_TABLE_NAME));
    assertTrue(scriptingHandlerContext.containsKey(BEFORE_UPDATE_ENTITY_TABLE_NAME));
  }

  @Test
  public void testAfterUpdateEntity() throws SQLException, ODataException, org.apache.olingo.odata2.api.exception.ODataException {
    // procedure
    mockGetHandlers();
    mockTemporaryTables(spyProcedureHandler);
    mockCallProcedure(spyProcedureHandler, true);
    ODataResponse response = xskoData2EventHandler.afterUpdateEntity(uriInfo, "application/json", true, "application/json", entry,
        procedureHandlerContext);
    assertResponse(response);

    // .xsjslib
    mockTemporaryTables(spyScriptingHandler);
    Mockito.doNothing().when(spyScriptingHandler).callSuperAfterUpdateEntity(any(), any(), anyBoolean(), any(), any(), any());
    xskoData2EventHandler.afterUpdateEntity(uriInfo, "application/json", true, "application/json", entry, scriptingHandlerContext);
    assertTrue(scriptingHandlerContext.containsKey(CONNECTION));
    assertTrue(scriptingHandlerContext.containsKey(BEFORE_TABLE_NAME));
    assertTrue(scriptingHandlerContext.containsKey(AFTER_TABLE_NAME));
  }

  @Test
  public void testOnUpdateEntity() throws SQLException, ODataException, org.apache.olingo.odata2.api.exception.ODataException {
    // procedure
    mockGetHandlers();
    mockTemporaryTables(spyProcedureHandler);
    mockCallProcedure(spyProcedureHandler, true);
    ODataResponse response = xskoData2EventHandler.onUpdateEntity(uriInfo, inputStream, "application/json", true, "application/json",
        procedureHandlerContext);
    assertResponse(response);

    // .xsjslib
    mockTemporaryTables(spyScriptingHandler);
    Mockito.doNothing().when(spyScriptingHandler).callSuperOnUpdateEntity(any(), any(), any(), anyBoolean(), any(), any());
    xskoData2EventHandler.onUpdateEntity(uriInfo, inputStream, "application/json", true, "application/json", scriptingHandlerContext);
    assertTrue(scriptingHandlerContext.containsKey(CONNECTION));
    assertTrue(scriptingHandlerContext.containsKey(BEFORE_TABLE_NAME));
    assertTrue(scriptingHandlerContext.containsKey(AFTER_TABLE_NAME));
  }

  @Test
  public void testBeforeDeleteEntity() throws SQLException, ODataException, org.apache.olingo.odata2.api.exception.ODataException {
    // procedure
    mockGetHandlers();
    mockTemporaryTables(spyProcedureHandler);
    mockCallProcedure(spyProcedureHandler, false);
    ODataResponse response = xskoData2EventHandler.beforeDeleteEntity(uriInfo, "application/json", procedureHandlerContext);
    assertResponse(response);

    // .xsjslib
    mockTemporaryTables(spyScriptingHandler);
    Mockito.doNothing().when(spyScriptingHandler).callSuperBeforeDeleteEntity(any(), any(), any());
    xskoData2EventHandler.beforeDeleteEntity(uriInfo, "application/json", scriptingHandlerContext);
    assertTrue(scriptingHandlerContext.containsKey(CONNECTION));
    assertTrue(scriptingHandlerContext.containsKey(BEFORE_TABLE_NAME));
    assertTrue(scriptingHandlerContext.containsKey(BEFORE_DELETE_ENTITY_TABLE_NAME));
  }

  @Test
  public void testAfterDeleteEntity() throws SQLException, ODataException, org.apache.olingo.odata2.api.exception.ODataException {
    // procedure
    mockGetHandlers();
    mockTemporaryTables(spyProcedureHandler);
    mockCallProcedure(spyProcedureHandler, false);
    ODataResponse response = xskoData2EventHandler.afterDeleteEntity(uriInfo, "application/json", procedureHandlerContext);
    assertResponse(response);

    // .xsjslib
    mockTemporaryTables(spyScriptingHandler);
    Mockito.doNothing().when(spyScriptingHandler).callSuperAfterDeleteEntity(any(), any(), any());
    xskoData2EventHandler.afterDeleteEntity(uriInfo, "application/json", scriptingHandlerContext);
    assertTrue(scriptingHandlerContext.containsKey(CONNECTION));
    assertTrue(scriptingHandlerContext.containsKey(BEFORE_TABLE_NAME));
  }

  @Test
  public void testOnDeleteEntity() throws SQLException, ODataException, org.apache.olingo.odata2.api.exception.ODataException {
    // procedure
    mockGetHandlers();
    mockTemporaryTables(spyProcedureHandler);
    mockCallProcedure(spyProcedureHandler, false);
    ODataResponse response = xskoData2EventHandler.onDeleteEntity(uriInfo, "application/json", procedureHandlerContext);
    assertResponse(response);

    // .xsjslib
    mockTemporaryTables(spyScriptingHandler);
    Mockito.doNothing().when(spyScriptingHandler).callSuperOnDeleteEntity(any(), any(), any());
    xskoData2EventHandler.onDeleteEntity(uriInfo, "application/json", scriptingHandlerContext);
    assertTrue(scriptingHandlerContext.containsKey(CONNECTION));
    assertTrue(scriptingHandlerContext.containsKey(BEFORE_TABLE_NAME));
  }

  private void mockGetHandlers() throws EdmException, ODataException {
    Mockito.when(uriInfo.getTargetType()).thenReturn(edmType, edmType);
    Mockito.when(edmType.getNamespace()).thenReturn("test-namespace", "test-namespace");
    Mockito.when(edmType.getName()).thenReturn("test-name", "test-name");

    ODataHandlerDefinition procedureHandler = new ODataHandlerDefinition();
    procedureHandler.setHandler("test-namespace::procedure");
    List<ODataHandlerDefinition> procedureHandlers = List.of(procedureHandler);
    ODataHandlerDefinition scriptingHandler = new ODataHandlerDefinition();
    scriptingHandler.setHandler("test-namespace::handler.xsjslib::handlerFunction");
    List<ODataHandlerDefinition> scriptingHandlers = List.of(scriptingHandler);
    Mockito.when(odataCoreService.getHandlers(any(), any(), any(), any()))
        .thenReturn(procedureHandlers, scriptingHandlers);
  }

  private void mockTemporaryTables(AbstractXSKOData2EventHandler handler)
      throws org.apache.olingo.odata2.api.exception.ODataException, SQLException {
    Mockito.doReturn("test-table").when(handler).getSQLInsertBuilderTargetTable(any(), any());

    Mockito.doReturn("test-table").when(handler).getSQLUpdateBuilderTargetTable(any(), any());

    Mockito.doReturn("test-table").when(handler).getSQLDeleteBuilderTargetTable(any(), any());

    Mockito.doNothing().when(handler).createTemporaryTableLikeTable(any(), any(), any());

    Mockito.doNothing().when(handler).createTemporaryTableAsSelect(any(), any(), any(), any());

    Mockito.doNothing().when(handler).insertIntoTemporaryTable(any(), any(), any(), any());

    Mockito.doNothing().when(handler).updateTemporaryTable(any(), any(), any(), any());

    Mockito.doReturn(new HashMap<>()).when(handler).readEntryMap(any(), any());

    Mockito.doNothing().when(handler).closeConnection(any());

    Mockito.doNothing().when(handler).batchDropTemporaryTables(any());

    if (handler instanceof XSKProcedureOData2EventHandler) {
      Mockito.doReturn("TEST_SCHEMA").when((XSKProcedureOData2EventHandler) handler).getODataArtifactTypeSchema("test-table");
    }
  }

  private void mockCallProcedure(XSKProcedureOData2EventHandler handler, boolean isUpdate)
      throws SQLException {
    ResultSet resultSetMock = Mockito.mock(ResultSet.class);
    Mockito.when(resultSetMock.next()).thenReturn(true);
    Mockito.when(resultSetMock.getString(anyString())).thenReturn("400", "INVALID ID");
    Mockito.when(resultSetMock.getString(anyInt())).thenReturn("detail message 1", "detail message 2");

    ResultSetMetaData metaData = Mockito.mock(ResultSetMetaData.class);

    Mockito.when(metaData.getColumnCount()).thenReturn(2);
    Mockito.when(metaData.getColumnName(anyInt())).thenReturn("errorDetail1", "errorDetail2");

    Mockito.when(resultSetMock.getMetaData()).thenReturn(metaData);

    if (isUpdate) {
      Mockito.doReturn(resultSetMock).when(handler).callProcedure(any(), any(), any(), any(), any());
    } else {
      Mockito.doReturn(resultSetMock).when(handler).callProcedure(any(), any(), any(), any());
    }
  }

  private void assertResponse(ODataResponse response) throws EntityProviderException {
    assertEquals(response.getStatus(), HttpStatusCodes.BAD_REQUEST);
    assertEquals("application/json", response.getHeader("Content-Type"));

    Map<String, Object> responseBody = GsonHelper.GSON.fromJson(new BasicEntityProvider().readText(((InputStream) response.getEntity())),
        HashMap.class);

    assertEquals("INVALID ID", ((LinkedTreeMap) ((LinkedTreeMap) responseBody.get("error")).get("message")).get("value"));
    assertEquals("{\"errordetail\":{\"errorDetail2\":\"detail message 2\",\"errorDetail1\":\"detail message 1\"}}",
        (((LinkedTreeMap) responseBody.get("error")).get("innererror")));
  }
}
