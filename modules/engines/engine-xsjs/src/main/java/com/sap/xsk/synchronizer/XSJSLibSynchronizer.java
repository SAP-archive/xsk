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
package com.sap.xsk.synchronizer;

import org.eclipse.dirigible.commons.api.context.ThreadContextFacade;
import org.eclipse.dirigible.commons.api.scripting.ScriptingException;
import org.eclipse.dirigible.core.scheduler.api.AbstractSynchronizer;
import org.eclipse.dirigible.core.scheduler.api.IOrderedSynchronizerContribution;
import org.eclipse.dirigible.core.scheduler.api.SynchronizationException;
import org.eclipse.dirigible.engine.js.graalvm.processor.GraalVMJavascriptEngineExecutor;
import org.eclipse.dirigible.repository.api.IResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XSJSLibSynchronizer extends AbstractSynchronizer implements IOrderedSynchronizerContribution {

  private static final Logger logger = LoggerFactory.getLogger(XSJSLibSynchronizer.class);

  private final GraalVMJavascriptEngineExecutor graalVMJavascriptEngineExecutor;
  private static final String EXPORT_GENERATION_SOURCE = "/*\n"
      + " * Copyright (c) 2022 SAP SE or an SAP affiliate company and XSK contributors\n"
      + " *\n"
      + " * All rights reserved. This program and the accompanying materials\n"
      + " * are made available under the terms of the Apache License, v2.0\n"
      + " * which accompanies this distribution, and is available at\n"
      + " * http://www.apache.org/licenses/LICENSE-2.0\n"
      + " *\n"
      + " * SPDX-FileCopyrightText: 2022 SAP SE or an SAP affiliate company and XSK contributors\n"
      + " * SPDX-License-Identifier: Apache-2.0\n"
      + " */\n"
      + "var acorn = require(\"acornjs/acorn\");\n"
      + "\n"
      + "exports.import = function (namespace, name) {\n"
      + "  var validPackages;\n"
      + "  var resourceName;\n"
      + "  var moduleName;\n"
      + "\n"
      + "  if(arguments.length == 1) {\n"
      + "      validPackages = namespace.split('/');\n"
      + "      moduleName = validPackages.pop().split('.')[0];\n"
      + "      resourceName = namespace;\n"
      + "  } else {\n"
      + "      validPackages = namespace.split('.');\n"
      + "      resourceName = validPackages.join('/') + '/' + name + '.xsjslib';\n"
      + "      moduleName = name;\n"
      + "  }\n"
      + "\n"
      + "  console.info(\"Importing: \" + resourceName);\n"
      + "\n"
      + "  var module = xskRequire(resourceName);\n"
      + "  addToXSJSApis(this, validPackages, moduleName, module);\n"
      + "\n"
      + "  console.info(\"Imported: \" + resourceName);\n"
      + "  return module;\n"
      + "}\n"
      + "\n"
      + "var Require = (function (modulePath) {\n"
      + "  var _loadedModules = {};\n"
      + "  var _require = function (path) {\n"
      + "    var moduleInfo, buffered, head = '(function(exports,module,require){ ', code = '', tail = '})', line = null;\n"
      + "    moduleInfo = _loadedModules[path];\n"
      + "    if (moduleInfo) {\n"
      + "      return moduleInfo;\n"
      + "    }\n"
      + "    code = SourceProvider.loadSource(path);\n"
      + "\n"
      + "    var exports = getExports(code);\n"
      + "    code += \"\\n\\n\";\n"
      + "    exports.forEach(e => code += \"exports.\" + e + \" = \" + e + \";\\n\");\n"
      + "\n"
      + "    moduleInfo = {\n"
      + "      loaded: false,\n"
      + "      id: path,\n"
      + "      exports: {},\n"
      + "      require: _requireClosure()\n"
      + "    };\n"
      + "    code = head + code + tail;\n"
      + "    _loadedModules[path] = moduleInfo;\n"
      + "    var compiledWrapper = null;\n"
      + "    try {\n"
      + "      compiledWrapper = eval(code);\n"
      + "    } catch (e) {\n"
      + "      throw new Error('Error evaluating module ' + path + ' line #' + e.lineNumber + ' : ' + e.message, path, e.lineNumber);\n"
      + "    }\n"
      + "    var parameters = [moduleInfo.exports, /* exports */\n"
      + "      moduleInfo, /* module */\n"
      + "      moduleInfo.require /* require */\n"
      + "    ];\n"
      + "    try {\n"
      + "      compiledWrapper.apply(moduleInfo.exports, /* this */\n"
      + "          parameters);\n"
      + "    } catch (e) {\n"
      + "      throw new Error('Error executing module ' + path + ' line #' + e.lineNumber + ' : ' + e.message, path, e.lineNumber);\n"
      + "    }\n"
      + "    moduleInfo.loaded = true;\n"
      + "    return moduleInfo;\n"
      + "  };\n"
      + "  var _requireClosure = function () {\n"
      + "    return function (path) {\n"
      + "      var module = _require(path);\n"
      + "      return module.exports;\n"
      + "    };\n"
      + "  };\n"
      + "  return _requireClosure();\n"
      + "});\n"
      + "\n"
      + "var xskRequire = Require();\n"
      + "\n"
      + "function getExports(code) {\n"
      + "  var nodes = acorn.parse(code);\n"
      + "  var functionDeclarations = nodes.body.filter(e => e.type === \"FunctionDeclaration\").map(e => e.id.name);\n"
      + "  var variableDeclarations = nodes.body.filter(e => e.type === \"VariableDeclaration\").flatMap(e => e.declarations.filter(d => d.type === \"VariableDeclarator\").flatMap(d => d.id.name));\n"
      + "  var exports = functionDeclarations.concat(variableDeclarations);\n"
      + "  return exports;\n"
      + "}\n"
      + "\n"
      + "function addToXSJSApis (api, validPackages, name, module) {\n"
      + "  for (var i = 0; i < validPackages.length; i++) {\n"
      + "    api = api[validPackages[i]] = api[validPackages[i]] || {};\n"
      + "  }\n"
      + "  api[name] = module;\n"
      + "};";

  public XSJSLibSynchronizer() {
    this.graalVMJavascriptEngineExecutor = new GraalVMJavascriptEngineExecutor();
    logger.debug("INITIALIZING SYNC");
  }

  /**
   * Force synchronization.
   */
  public static void forceSynchronization() {
    XSJSLibSynchronizer synchronizer = new XSJSLibSynchronizer();
    synchronizer.setForcedSynchronization(true);
    try {
      synchronizer.synchronize();
    } finally {
      synchronizer.setForcedSynchronization(false);
    }
  }

  @Override
  protected void synchronizeResource(IResource iResource) throws SynchronizationException {
    logger.trace("Synchronizing XSJSLib Resource...");
  }

  @Override
  public int getPriority() {
    return 666;
  }

  @Override
  public void synchronize() {
    synchronized (XSJSLibSynchronizer.class) {
      if(beforeSynchronizing()) {
        logger.trace("Synchronizing XSJSLibs...");

        try {
          ThreadContextFacade.setUp();
          Object error = graalVMJavascriptEngineExecutor.executeServiceModule(EXPORT_GENERATION_SOURCE, null);
          if (error != null && error.toString() != null) {
            throw new ScriptingException(error.toString());
          }
        } finally {
          ThreadContextFacade.tearDown();
        }

        logger.trace("Done synchronizing XSJSLibs.");
        afterSynchronizing();
      }
    }
  }
}
