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

exports.import = function (namespace, name) {
  var validPackages;
  var resourceName;
  var moduleName;

  if(arguments.length == 1) {
      validPackages = namespace.split('/');
      moduleName = validPackages.pop().split('.')[0];
      resourceName = namespace;
  } else {
      validPackages = namespace.split('.');
      resourceName = validPackages.join('/') + '/' + name + '.xsjslib';
      moduleName = name;
  }

  console.info("Importing: " + resourceName);

  var module = xskRequire(resourceName);
  addToXSJSApis(this, validPackages, moduleName, module);

  console.info("Imported: " + resourceName);
  return module;
}

var Require = (function (modulePath) {
  var _loadedModules = {};
  var _require = function (path) {
    var moduleInfo, buffered, head = '(function(exports,module,require){ ', code = '', tail = '})', line = null;
    moduleInfo = _loadedModules[path];
    if (moduleInfo) {
      return moduleInfo;
    }
    code = SourceProvider.loadSource(path);

    moduleInfo = {
      loaded: false,
      id: path,
      exports: {},
      require: _requireClosure()
    };
    code = head + code + tail;
    _loadedModules[path] = moduleInfo;
    var compiledWrapper = null;
    try {
      compiledWrapper = load({
        name: path,
        script: code
      });
    } catch (e) {
      throw new Error('Error evaluating module ' + path + ' line #' + e.lineNumber + ' : ' + e.message, path, e.lineNumber);
    }
    var parameters = [moduleInfo.exports, /* exports */
      moduleInfo, /* module */
      moduleInfo.require /* require */
    ];
    try {
      compiledWrapper.apply(moduleInfo.exports, /* this */
          parameters);
    } catch (e) {
      throw new Error('Error executing module ' + path + ' line #' + e.lineNumber + ' : ' + e.message, path, e.lineNumber);
    }
    moduleInfo.loaded = true;
    return moduleInfo;
  };
  var _requireClosure = function () {
    return function (path) {
      var module = _require(path);
      return module.exports;
    };
  };
  return _requireClosure();
});

var xskRequire = Require();

function addToXSJSApis (api, validPackages, name, module) {
  for (var i = 0; i < validPackages.length; i++) {
    api = api[validPackages[i]] = api[validPackages[i]] || {};
  }
  api[name] = module;
};
