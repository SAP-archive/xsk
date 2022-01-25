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
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import org.eclipse.dirigible.engine.api.script.IScriptEngineExecutor;
import org.eclipse.dirigible.engine.js.api.IJavascriptModuleSourceProvider;

/**
 * The GraalVM Repository Module Source Provider.
 */
public class XSKRepositoryModuleSourceProvider implements IJavascriptModuleSourceProvider {

  private static final String XSJS_EXTENSION = ".xsjs"; //$NON-NLS-1$
  private static final String XSJSLIB_EXTENSION = ".xsjslib"; //$NON-NLS-1$
  private static final String JS_EXTENSION = ".js"; //$NON-NLS-1$

  private IScriptEngineExecutor executor;

  private String root;

  /**
   * Instantiates a new GraalVM repository module source provider.
   *
   * @param executor the executor
   * @param root     the root
   */
  public XSKRepositoryModuleSourceProvider(IScriptEngineExecutor executor, String root) {
    this.executor = executor;
    this.root = root;
  }

  /**
   * Load source.
   *
   * @param module the module
   * @return the string
   * @throws IOException        Signals that an I/O exception has occurred.
   * @throws URISyntaxException the URI syntax exception
   */
  @Override
  public String loadSource(String module) throws IOException, URISyntaxException {

    if (module == null) {
      throw new IOException("Module location cannot be null");
    }

    byte[] sourceCode = null;
    if (module.endsWith(XSJS_EXTENSION)) {
      sourceCode = executor.retrieveModule(root, module).getContent();
    } else if (module.endsWith(XSJSLIB_EXTENSION)) {
      sourceCode = executor.retrieveModule(root, module).getContent();
    } else if (module.endsWith(JS_EXTENSION)) {
      sourceCode = executor.retrieveModule(root, module).getContent();
    } else if (module.indexOf(XSJS_EXTENSION + "/") > 0) {
      module = module.substring(0, module.indexOf(XSJS_EXTENSION + "/") + 5);
      sourceCode = executor.retrieveModule(root, module).getContent();
    } else if (module.indexOf(XSJSLIB_EXTENSION + "/") > 0) {
      module = module.substring(0, module.indexOf(XSJSLIB_EXTENSION + "/") + 8);
      sourceCode = executor.retrieveModule(root, module).getContent();
    } else if (module.indexOf(JS_EXTENSION + "/") > 0) {
      module = module.substring(0, module.indexOf(XSJS_EXTENSION + "/") + 3);
      sourceCode = executor.retrieveModule(root, module).getContent();
    } else {
      if (executor.existsModule(root, module, XSJS_EXTENSION)) {
        sourceCode = executor.retrieveModule(root, module, XSJS_EXTENSION).getContent();
        if (sourceCode == null || sourceCode.length == 0) {
          sourceCode = executor.retrieveModule(root, module, JS_EXTENSION).getContent();
        }
      } else if (executor.existsModule(root, module, XSJSLIB_EXTENSION)) {
        sourceCode = executor.retrieveModule(root, module, XSJSLIB_EXTENSION).getContent();
        if (sourceCode == null || sourceCode.length == 0) {
          sourceCode = executor.retrieveModule(root, module, XSJSLIB_EXTENSION).getContent();
        }
      } else if (executor.existsModule(root, module, JS_EXTENSION)) {
        sourceCode = executor.retrieveModule(root, module, JS_EXTENSION).getContent();
        if (sourceCode == null || sourceCode.length == 0) {
          sourceCode = executor.retrieveModule(root, module, JS_EXTENSION).getContent();
        }
      } else {
        throw new IOException("Module: " + module + " does not exist.");
      }
    }

    return new String(sourceCode, StandardCharsets.UTF_8);
  }

}
