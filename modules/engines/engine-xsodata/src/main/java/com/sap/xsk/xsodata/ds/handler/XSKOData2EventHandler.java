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

import org.apache.olingo.odata2.api.edm.EdmException;
import org.apache.olingo.odata2.api.ep.entry.ODataEntry;
import org.apache.olingo.odata2.api.processor.ODataResponse;
import org.apache.olingo.odata2.api.uri.info.DeleteUriInfo;
import org.apache.olingo.odata2.api.uri.info.PostUriInfo;
import org.apache.olingo.odata2.api.uri.info.PutMergePatchUriInfo;
import org.eclipse.dirigible.engine.odata2.api.ODataException;
import org.eclipse.dirigible.engine.odata2.definition.ODataHandlerDefinition;
import org.eclipse.dirigible.engine.odata2.definition.ODataHandlerMethods;
import org.eclipse.dirigible.engine.odata2.definition.ODataHandlerTypes;
import org.eclipse.dirigible.engine.odata2.handler.ScriptingOData2EventHandler;
import org.eclipse.dirigible.engine.odata2.service.ODataCoreService;
import org.eclipse.dirigible.engine.odata2.sql.api.OData2EventHandler;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class XSKOData2EventHandler extends ScriptingOData2EventHandler {

  private static final String ODATA2_EVENT_HANDLER_NAME = "xsk-odata-event-handler";

  private static final String HANDLER = "handler";

  private final ODataCoreService odataCoreService = new ODataCoreService();
  private final XSKScriptingOData2EventHandler xskScriptingOData2EventHandler = new XSKScriptingOData2EventHandler();
  private final XSKProcedureOData2EventHandler xskProcedureOData2EventHandler = new XSKProcedureOData2EventHandler();

  @Override
  public void beforeCreateEntity(PostUriInfo uriInfo, String requestContentType, String contentType, ODataEntry entry,
      Map<Object, Object> context)
      throws ODataException {
    try {
      String namespace = uriInfo.getTargetType().getNamespace();
      String name = uriInfo.getTargetType().getName();
      String method = ODataHandlerMethods.create.name();
      String type = ODataHandlerTypes.before.name();
      List<ODataHandlerDefinition> handlers = odataCoreService.getHandlers(namespace, name, method, type);
      if(handlers.size() > 0) {
        OData2EventHandler eventHandler = determineEventHandler(handlers.get(0));
        context.put(HANDLER, handlers.get(0));
        eventHandler.beforeCreateEntity(uriInfo, requestContentType, contentType, entry, context);
      }
    } catch (EdmException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void afterCreateEntity(PostUriInfo uriInfo, String requestContentType, String contentType, ODataEntry entry,
      Map<Object, Object> context)
      throws ODataException {
    try {
      String namespace = uriInfo.getTargetType().getNamespace();
      String name = uriInfo.getTargetType().getName();
      String method = ODataHandlerMethods.create.name();
      String type = ODataHandlerTypes.after.name();
      List<ODataHandlerDefinition> handlers = odataCoreService.getHandlers(namespace, name, method, type);
      if(handlers.size() > 0) {
        OData2EventHandler eventHandler = determineEventHandler(handlers.get(0));
        context.put(HANDLER, handlers.get(0));
        eventHandler.beforeCreateEntity(uriInfo, requestContentType, contentType, entry, context);
      }
    } catch (EdmException e) {
      e.printStackTrace();
    }
  }

  @Override
  public ODataResponse onCreateEntity(PostUriInfo uriInfo, InputStream content, String requestContentType, String contentType,
      Map<Object, Object> context)
      throws ODataException {
    ODataResponse response = null;
    try {
      String namespace = uriInfo.getTargetType().getNamespace();
      String name = uriInfo.getTargetType().getName();
      String method = ODataHandlerMethods.create.name();
      String type = ODataHandlerTypes.on.name();
      List<ODataHandlerDefinition> handlers = odataCoreService.getHandlers(namespace, name, method, type);
      if(handlers.size() > 0) {
        OData2EventHandler eventHandler = determineEventHandler(handlers.get(0));
        context.put(HANDLER, handlers.get(0));
        response = eventHandler.onCreateEntity(uriInfo, content, requestContentType, contentType, context);
      }
    } catch (EdmException e) {
      e.printStackTrace();
    }
    return response;
  }

  @Override
  public void beforeUpdateEntity(PutMergePatchUriInfo uriInfo, String requestContentType, boolean merge, String contentType,
      ODataEntry entry, Map<Object, Object> context) throws ODataException {
    try {
      String namespace = uriInfo.getTargetType().getNamespace();
      String name = uriInfo.getTargetType().getName();
      String method = ODataHandlerMethods.update.name();
      String type = ODataHandlerTypes.before.name();
      List<ODataHandlerDefinition> handlers = odataCoreService.getHandlers(namespace, name, method, type);
      if(handlers.size() > 0) {
        OData2EventHandler eventHandler = determineEventHandler(handlers.get(0));
        context.put(HANDLER, handlers.get(0));
        eventHandler.beforeUpdateEntity(uriInfo, requestContentType, merge, contentType, entry, context);
      }
    } catch (EdmException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void afterUpdateEntity(PutMergePatchUriInfo uriInfo, String requestContentType, boolean merge, String contentType,
      ODataEntry entry, Map<Object, Object> context) throws ODataException {
    try {
      String namespace = uriInfo.getTargetType().getNamespace();
      String name = uriInfo.getTargetType().getName();
      String method = ODataHandlerMethods.update.name();
      String type = ODataHandlerTypes.after.name();
      List<ODataHandlerDefinition> handlers = odataCoreService.getHandlers(namespace, name, method, type);
      if(handlers.size() > 0) {
        OData2EventHandler eventHandler = determineEventHandler(handlers.get(0));
        context.put(HANDLER, handlers.get(0));
        eventHandler.afterUpdateEntity(uriInfo, requestContentType, merge, contentType, entry, context);
      }
    } catch (EdmException e) {
      e.printStackTrace();
    }
  }

  @Override
  public ODataResponse onUpdateEntity(PutMergePatchUriInfo uriInfo, InputStream content, String requestContentType, boolean merge,
      String contentType, Map<Object, Object> context) throws ODataException {
    ODataResponse response = null;
    try {
      String namespace = uriInfo.getTargetType().getNamespace();
      String name = uriInfo.getTargetType().getName();
      String method = ODataHandlerMethods.update.name();
      String type = ODataHandlerTypes.on.name();
      List<ODataHandlerDefinition> handlers = odataCoreService.getHandlers(namespace, name, method, type);
      if(handlers.size() > 0) {
        OData2EventHandler eventHandler = determineEventHandler(handlers.get(0));
        context.put(HANDLER, handlers.get(0));
        response = eventHandler.onUpdateEntity(uriInfo, content, requestContentType, merge, contentType, context);
      }
    } catch (EdmException e) {
      e.printStackTrace();
    }
    return response;
  }

  @Override
  public void beforeDeleteEntity(DeleteUriInfo uriInfo, String contentType, Map<Object, Object> context) throws ODataException {
    try {
      String namespace = uriInfo.getTargetType().getNamespace();
      String name = uriInfo.getTargetType().getName();
      String method = ODataHandlerMethods.delete.name();
      String type = ODataHandlerTypes.before.name();
      List<ODataHandlerDefinition> handlers = odataCoreService.getHandlers(namespace, name, method, type);
      if(handlers.size() > 0) {
        OData2EventHandler eventHandler = determineEventHandler(handlers.get(0));
        context.put(HANDLER, handlers.get(0));
        eventHandler.beforeDeleteEntity(uriInfo, contentType, context);
      }
    } catch (EdmException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void afterDeleteEntity(DeleteUriInfo uriInfo, String contentType, Map<Object, Object> context) throws ODataException {
    try {
      String namespace = uriInfo.getTargetType().getNamespace();
      String name = uriInfo.getTargetType().getName();
      String method = ODataHandlerMethods.delete.name();
      String type = ODataHandlerTypes.after.name();
      List<ODataHandlerDefinition> handlers = odataCoreService.getHandlers(namespace, name, method, type);
      if(handlers.size() > 0) {
        OData2EventHandler eventHandler = determineEventHandler(handlers.get(0));
        context.put(HANDLER, handlers.get(0));
        eventHandler.afterDeleteEntity(uriInfo, contentType, context);
      }
    } catch (EdmException e) {
      e.printStackTrace();
    }
  }

  @Override
  public ODataResponse onDeleteEntity(DeleteUriInfo uriInfo, String contentType, Map<Object, Object> context) throws ODataException {
    ODataResponse response = null;
    try {
      String namespace = uriInfo.getTargetType().getNamespace();
      String name = uriInfo.getTargetType().getName();
      String method = ODataHandlerMethods.delete.name();
      String type = ODataHandlerTypes.on.name();
      List<ODataHandlerDefinition> handlers = odataCoreService.getHandlers(namespace, name, method, type);
      if(handlers.size() > 0) {
        OData2EventHandler eventHandler = determineEventHandler(handlers.get(0));
        context.put(HANDLER, handlers.get(0));
        response = eventHandler.onDeleteEntity(uriInfo, contentType, context);
      }
    } catch (EdmException e) {
      e.printStackTrace();
    }
    return response;
  }

  @Override
  public String getName() {
    return ODATA2_EVENT_HANDLER_NAME;
  }

  private OData2EventHandler determineEventHandler(ODataHandlerDefinition handler) {
    if(handler.getHandler().contains(".xsjslib::")) {
      return xskScriptingOData2EventHandler;
    } else {
      return xskProcedureOData2EventHandler;
    }
  }
}
