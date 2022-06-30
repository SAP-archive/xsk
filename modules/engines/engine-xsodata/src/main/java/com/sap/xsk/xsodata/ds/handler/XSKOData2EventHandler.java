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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class XSKOData2EventHandler extends ScriptingOData2EventHandler {

  public static final Logger LOGGER = LoggerFactory.getLogger(XSKOData2EventHandler.class);

  private static final String ODATA2_EVENT_HANDLER_NAME = "xsk-odata-event-handler";

  private static final String HANDLER = "handler";

  private ODataCoreService odataCoreService;
  private XSKProcedureOData2EventHandler procedureHandler;
  private XSKScriptingOData2EventHandler scriptingHandler;

  public XSKOData2EventHandler() {
    this(new ODataCoreService(), new XSKProcedureOData2EventHandler(), new XSKScriptingOData2EventHandler());
  }

  public XSKOData2EventHandler(ODataCoreService odataCoreService, XSKProcedureOData2EventHandler procedureHandler, XSKScriptingOData2EventHandler scriptingHandler) {
    this.odataCoreService = odataCoreService;
    this.procedureHandler = procedureHandler;
    this.scriptingHandler = scriptingHandler;
  }

  @Override
  public ODataResponse beforeCreateEntity(PostUriInfo uriInfo, String requestContentType, String contentType, ODataEntry entry,
      Map<Object, Object> context)
      throws ODataException {
    try {
      String namespace = uriInfo.getTargetType().getNamespace();
      String name = uriInfo.getTargetType().getName();
      String method = ODataHandlerMethods.create.name();
      String type = ODataHandlerTypes.before.name();
      List<ODataHandlerDefinition> handlers = odataCoreService.getHandlers(namespace, name, method, type);
      if(handlers.size() > 0) {
        AbstractXSKOData2EventHandler eventHandler = determineEventHandler(handlers.get(0));
        context.put(HANDLER, handlers.get(0));
        return eventHandler.beforeCreateEntity(uriInfo, requestContentType, contentType, entry, context);
      }
    } catch (EdmException e) {
      LOGGER.error(e.getMessage(), e);
    }
    return null;
  }

  @Override
  public ODataResponse afterCreateEntity(PostUriInfo uriInfo, String requestContentType, String contentType, ODataEntry entry,
      Map<Object, Object> context)
      throws ODataException {
    try {
      String namespace = uriInfo.getTargetType().getNamespace();
      String name = uriInfo.getTargetType().getName();
      String method = ODataHandlerMethods.create.name();
      String type = ODataHandlerTypes.after.name();
      List<ODataHandlerDefinition> handlers = odataCoreService.getHandlers(namespace, name, method, type);
      if(handlers.size() > 0) {
        AbstractXSKOData2EventHandler eventHandler = determineEventHandler(handlers.get(0));
        context.put(HANDLER, handlers.get(0));
        return eventHandler.afterCreateEntity(uriInfo, requestContentType, contentType, entry, context);
      }
    } catch (EdmException e) {
      LOGGER.error(e.getMessage(), e);
    }
    return null;
  }

  @Override
  public ODataResponse onCreateEntity(PostUriInfo uriInfo, InputStream content, String requestContentType, String contentType,
      Map<Object, Object> context)
      throws ODataException {
    try {
      String namespace = uriInfo.getTargetType().getNamespace();
      String name = uriInfo.getTargetType().getName();
      String method = ODataHandlerMethods.create.name();
      String type = ODataHandlerTypes.on.name();
      List<ODataHandlerDefinition> handlers = odataCoreService.getHandlers(namespace, name, method, type);
      if(handlers.size() > 0) {
        AbstractXSKOData2EventHandler eventHandler = determineEventHandler(handlers.get(0));
        context.put(HANDLER, handlers.get(0));
        return eventHandler.onCreateEntity(uriInfo, content, requestContentType, contentType, context);
      }
    } catch (EdmException e) {
      LOGGER.error(e.getMessage(), e);
    }
    return null;
  }

  @Override
  public ODataResponse beforeUpdateEntity(PutMergePatchUriInfo uriInfo, String requestContentType, boolean merge, String contentType,
      ODataEntry entry, Map<Object, Object> context) throws ODataException {
    try {
      String namespace = uriInfo.getTargetType().getNamespace();
      String name = uriInfo.getTargetType().getName();
      String method = ODataHandlerMethods.update.name();
      String type = ODataHandlerTypes.before.name();
      List<ODataHandlerDefinition> handlers = odataCoreService.getHandlers(namespace, name, method, type);
      if(handlers.size() > 0) {
        AbstractXSKOData2EventHandler eventHandler = determineEventHandler(handlers.get(0));
        context.put(HANDLER, handlers.get(0));
        return eventHandler.beforeUpdateEntity(uriInfo, requestContentType, merge, contentType, entry, context);
      }
    } catch (EdmException e) {
      LOGGER.error(e.getMessage(), e);
    }
    return null;
  }

  @Override
  public ODataResponse afterUpdateEntity(PutMergePatchUriInfo uriInfo, String requestContentType, boolean merge, String contentType,
      ODataEntry entry, Map<Object, Object> context) throws ODataException {
    try {
      String namespace = uriInfo.getTargetType().getNamespace();
      String name = uriInfo.getTargetType().getName();
      String method = ODataHandlerMethods.update.name();
      String type = ODataHandlerTypes.after.name();
      List<ODataHandlerDefinition> handlers = odataCoreService.getHandlers(namespace, name, method, type);
      if(handlers.size() > 0) {
        AbstractXSKOData2EventHandler eventHandler = determineEventHandler(handlers.get(0));
        context.put(HANDLER, handlers.get(0));
        return eventHandler.afterUpdateEntity(uriInfo, requestContentType, merge, contentType, entry, context);
      }
    } catch (EdmException e) {
      LOGGER.error(e.getMessage(), e);
    }
    return null;
  }

  @Override
  public ODataResponse onUpdateEntity(PutMergePatchUriInfo uriInfo, InputStream content, String requestContentType, boolean merge,
      String contentType, Map<Object, Object> context) throws ODataException {
    try {
      String namespace = uriInfo.getTargetType().getNamespace();
      String name = uriInfo.getTargetType().getName();
      String method = ODataHandlerMethods.update.name();
      String type = ODataHandlerTypes.on.name();
      List<ODataHandlerDefinition> handlers = odataCoreService.getHandlers(namespace, name, method, type);
      if(handlers.size() > 0) {
        AbstractXSKOData2EventHandler eventHandler = determineEventHandler(handlers.get(0));
        context.put(HANDLER, handlers.get(0));
        return eventHandler.onUpdateEntity(uriInfo, content, requestContentType, merge, contentType, context);
      }
    } catch (EdmException e) {
      LOGGER.error(e.getMessage(), e);
    }
    return null;
  }

  @Override
  public ODataResponse beforeDeleteEntity(DeleteUriInfo uriInfo, String contentType, Map<Object, Object> context) throws ODataException {
    try {
      String namespace = uriInfo.getTargetType().getNamespace();
      String name = uriInfo.getTargetType().getName();
      String method = ODataHandlerMethods.delete.name();
      String type = ODataHandlerTypes.before.name();
      List<ODataHandlerDefinition> handlers = odataCoreService.getHandlers(namespace, name, method, type);
      if(handlers.size() > 0) {
        AbstractXSKOData2EventHandler eventHandler = determineEventHandler(handlers.get(0));
        context.put(HANDLER, handlers.get(0));
        return eventHandler.beforeDeleteEntity(uriInfo, contentType, context);
      }
    } catch (EdmException e) {
      LOGGER.error(e.getMessage(), e);
    }
    return null;
  }

  @Override
  public ODataResponse afterDeleteEntity(DeleteUriInfo uriInfo, String contentType, Map<Object, Object> context) throws ODataException {
    try {
      String namespace = uriInfo.getTargetType().getNamespace();
      String name = uriInfo.getTargetType().getName();
      String method = ODataHandlerMethods.delete.name();
      String type = ODataHandlerTypes.after.name();
      List<ODataHandlerDefinition> handlers = odataCoreService.getHandlers(namespace, name, method, type);
      if(handlers.size() > 0) {
        AbstractXSKOData2EventHandler eventHandler = determineEventHandler(handlers.get(0));
        context.put(HANDLER, handlers.get(0));
        return eventHandler.afterDeleteEntity(uriInfo, contentType, context);
      }
    } catch (EdmException e) {
      LOGGER.error(e.getMessage(), e);
    }
    return null;
  }

  @Override
  public ODataResponse onDeleteEntity(DeleteUriInfo uriInfo, String contentType, Map<Object, Object> context) throws ODataException {
    try {
      String namespace = uriInfo.getTargetType().getNamespace();
      String name = uriInfo.getTargetType().getName();
      String method = ODataHandlerMethods.delete.name();
      String type = ODataHandlerTypes.on.name();
      List<ODataHandlerDefinition> handlers = odataCoreService.getHandlers(namespace, name, method, type);
      if(handlers.size() > 0) {
        AbstractXSKOData2EventHandler eventHandler = determineEventHandler(handlers.get(0));
        context.put(HANDLER, handlers.get(0));
        return eventHandler.onDeleteEntity(uriInfo, contentType, context);
      }
    } catch (EdmException e) {
      LOGGER.error(e.getMessage(), e);
    }
    return null;
  }

  @Override
  public String getName() {
    return ODATA2_EVENT_HANDLER_NAME;
  }

  private AbstractXSKOData2EventHandler determineEventHandler(ODataHandlerDefinition handler) {
    if(handler.getHandler().contains(".xsjslib::")) {
      return scriptingHandler;
    } else {
      return procedureHandler;
    }
  }
}
