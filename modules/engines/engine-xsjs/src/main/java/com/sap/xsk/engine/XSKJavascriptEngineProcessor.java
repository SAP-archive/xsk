/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2021 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.engine;

import java.util.HashMap;
import java.util.Map;
import org.eclipse.dirigible.commons.api.scripting.ScriptingException;
import org.eclipse.dirigible.engine.js.api.IJavascriptEngineProcessor;

/**
 * The HANA XS Classic Javascript Engine Processor.
 */
public class XSKJavascriptEngineProcessor implements IJavascriptEngineProcessor {

  private XSKJavascriptEngineExecutor xskEngineExecutor = new XSKJavascriptEngineExecutor();

  /*
   * (non-Javadoc)
   * @see org.eclipse.dirigible.engine.js.api.IJavascriptEngineProcessor#executeService(java.lang.String)
   */
  @Override
  public void executeService(String module) throws ScriptingException {
    Map<Object, Object> executionContext = new HashMap<Object, Object>();
    xskEngineExecutor.executeServiceModule(module, executionContext);
  }

}
