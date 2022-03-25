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
package com.sap.xsk.engine;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;
import org.apache.commons.io.IOUtils;
import org.eclipse.dirigible.commons.api.scripting.ScriptingException;
import org.eclipse.dirigible.engine.api.script.IScriptEngineExecutor;
import org.eclipse.dirigible.engine.js.api.IJavascriptModuleSourceProvider;
import org.eclipse.dirigible.engine.js.graalvm.processor.GraalVMJavascriptEngineExecutor;
import org.eclipse.dirigible.repository.api.IRepositoryStructure;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The HANA XS Classic Javascript Engine Executor.
 */
public class XSKJavascriptEngineExecutor extends GraalVMJavascriptEngineExecutor implements IScriptEngineExecutor {

  public static final String ENGINE_NAME = "HANA XS Classic JavaScript Engine";
  private static final String ENGINE_JAVA_SCRIPT = "js";
  private static final String XSK_API_LOCATION = "/xsk/api.js";
  private static final Charset DEFAULT_CHARSET = Charset.defaultCharset();
  private static String XSK_API_CONTENT = null;
  private final XSKRepositoryModuleSourceProvider sourceProvider = new XSKRepositoryModuleSourceProvider(this,
      IRepositoryStructure.PATH_REGISTRY_PUBLIC);

  @Override
  protected void beforeEval(Context context) throws IOException {
    String xskApi = getXskApi();
    context.getBindings(ENGINE_JAVA_SCRIPT).putMember("XSK_API", xskApi);
    Value loadScriptStringResult = context.eval(
        Source
            .newBuilder(ENGINE_JAVA_SCRIPT, "mainModule.loadScriptString(XSK_API)", "internal-module-load-string-code.js")
            .build()
    );
    context.getBindings(ENGINE_JAVA_SCRIPT).putMember("$", loadScriptStringResult);
    context.eval(getJSErrorFileNamePolyfillSource());
    super.beforeEval(context);
  }

  private static Source getJSErrorFileNamePolyfillSource() throws IOException {
    String errorFileNamePolyfillName = "/ErrorFileNamePolyfill.js";
    InputStream errorFileNamePolyfillInputStream = XSKJavascriptEngineExecutor.class
        .getResourceAsStream("/js/polyfills" + errorFileNamePolyfillName);
    String errorFileNamePolyfillCode = IOUtils.toString(Objects.requireNonNull(errorFileNamePolyfillInputStream), StandardCharsets.UTF_8);
    return Source
        .newBuilder(ENGINE_JAVA_SCRIPT, errorFileNamePolyfillCode, errorFileNamePolyfillName)
        .internal(true)
        .build();

  }

  @Override
  protected String loadSource(String module) throws IOException, URISyntaxException {
    return sourceProvider.loadSource(module);
  }

  private String getXskApi() throws IOException {
    if (XSK_API_CONTENT == null) {
      XSK_API_CONTENT = IOUtils
          .toString(XSKJavascriptEngineExecutor.class.getResourceAsStream("/META-INF/dirigible" + XSK_API_LOCATION), DEFAULT_CHARSET);
    }
    return XSK_API_CONTENT;
  }

  @Override
  public Object executeServiceModule(String module, Map<Object, Object> executionContext) throws ScriptingException {
    return super.executeServiceModule(module, executionContext);
  }

  @Override
  public Object executeServiceCode(String code, Map<Object, Object> executionContext) throws ScriptingException {
    return super.executeServiceCode(code, executionContext);
  }

  @Override
  public Object evalCode(String code, Map<Object, Object> executionContext) throws ScriptingException {
    return super.evalCode(code, executionContext);
  }

  @Override
  public Object evalModule(String module, Map<Object, Object> executionContext) throws ScriptingException {
    return super.evalModule(module, executionContext);
  }

  @Override
  public Object executeService(String moduleOrCode, Map<Object, Object> executionContext, boolean isModule, boolean commonJSModule)
      throws ScriptingException {
    return super.executeService(moduleOrCode, executionContext, isModule, commonJSModule);
  }

  // TODO: Handle XSK Job calls -> callXSKJobFunction(...)
  //	try {
  //	ModuleSource moduleSource = (isModule ? sourceProvider.loadSource(moduleOrCode, null, null) : null);
  //	try {
  //		// load the HANA XS Classic Bridge
  //		String api = IOUtils.toString(XSKJavascriptEngineExecutor.class.getResourceAsStream("/xsk/api.js"), Charset.defaultCharset());
  //		context.evaluateString(topLevelScope, api, "$", -1, null);
  //
  //		if (moduleSource != null) {
  //			result = context.evaluateReader(topLevelScope, moduleSource.getReader(), moduleOrCode, -1, null);
  //			callXSKJobFunction(topLevelScope, context, executionContext);
  //		} else {
  //			result = context.evaluateString(topLevelScope, moduleOrCode, "dynamic", -1, null);
  //		}
  //		forceFlush();
  //	} catch (EcmaError e) {
  //		logger.error(e.getMessage());
  //		if ((e.getMessage() != null) && e.getMessage().contains("\"exports\" is not defined")) {
  //			String message = "Requested endpoint is not a service, but rather a library.";
  //			// throw new ScriptingDependencyException(message);
  //			logger.warn(message);
  //			return message;
  //		}
  //		throw new ScriptingException(e);
  //	}

  //	private void callXSKJobFunction(Scriptable topLevelScope, Context context, Map<Object, Object> executionContext) {
  //		if (executionContext == null) {
  //			return;
  //		}
  //
  //		String functionName = (String) executionContext.get(IXSKJobCoreService.XSK_JOB_FUNCTION);
  //		Object[] functionParameters = (Object[]) executionContext.get(IXSKJobCoreService.XSK_JOB_PARAMETERS);
  //		if (functionName == null || functionParameters == null) {
  //			return;
  //		}
  //
  //
  //		Function fct = (Function)topLevelScope.get(functionName, topLevelScope);
  //		fct.call(context, topLevelScope, topLevelScope, functionParameters);
  //	}

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.dirigible.engine.api.script.IEngineExecutor#getType()
   */
  @Override
  public String getType() {
    return "xskjavascript";
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.dirigible.engine.api.script.IEngineExecutor#getName()
   */
  @Override
  public String getName() {
    return ENGINE_NAME;
  }

  @Override
  public IJavascriptModuleSourceProvider getSourceProvider() {
    return sourceProvider;
  }
}
