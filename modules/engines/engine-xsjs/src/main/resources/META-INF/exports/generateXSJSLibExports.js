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

var repository = require('platform/v4/repository');
var acorn = require("acornjs/acorn");
var dao = require("db/v4/dao");
var query = require("db/v4/query");
var digest = require("utils/v4/digest");

var processedArtefactsTable = getOrcreateProcessedArtefactsTable();
var targetRegistryCollection = repository.getCollection(__context.registry_path);

generateExportsRecursively(targetRegistryCollection, processedArtefactsTable);

function generateExportsRecursively(parentCollection, processedTable) {
  if(!parentCollection.exists() || !processedTable) {
    return;
  }

  var resourceNames = toJsArray(parentCollection.getResourcesNames());
  var collectionNames = toJsArray(parentCollection.getCollectionsNames());

  resourceNames
  .filter(resourceName => resourceName.endsWith(".xsjslib"))
  .map(resourceName => parentCollection.getResource(resourceName))
  .forEach(resource => {
    try {
      checkTableAndGenerateExportsIfNeeded(resource, processedTable);
    } catch (e) {
      throw new Error("Cannot check if synchrnoisation is needed. Reason: " + e);
    }
  });

  collectionNames
  .map(collectionName => parentCollection.getCollection(collectionName))
  .forEach(collection => generateExportsRecursively(collection, processedTable));
}

function toJsArray(array) {
  var result = [];
  for(var element of array) {
    result.push(element);
  }
  return result;
}

function checkTableAndGenerateExportsIfNeeded(resource, processedTable) {
  var content = resource.getText();
  var location = resource.getPath();

  var foundEntry = findTableEntryByResourceLocation(location);

  if(!foundEntry) {
    var contentWithExports = writeExportsToResource(resource, content);
    createNewTableEntryForResource(location, contentWithExports, processedTable);
  } 
  else if(foundEntry.HASH !== digest.md5Hex(content)) {
    var contentWithExports = writeExportsToResource(resource, content);
    updateOldTableEntryForResource(foundEntry, location, contentWithExports, processedTable);
  }
}

function writeExportsToResource(resource, content) {
  var contentWithExports = getXSJSLibContentWithExports(content);
  resource.setText(contentWithExports);
  return contentWithExports;
}

function getXSJSLibContentWithExports(content) {
  content +=  "\n\n";
  getExports(content).forEach(e => content += "exports." + e + " = " + e + ";\n");
  return content;
}

function getExports(code) {
  var nodes = acorn.parse(code);

  var functionDeclarations = nodes.body.filter(e => e.type === "FunctionDeclaration")
  .map(e => e.id.name);

  var variableDeclarations = nodes.body.filter(e => e.type === "VariableDeclaration")
  .flatMap(e => e.declarations.filter(d => d.type === "VariableDeclarator")
  .flatMap(d => d.id.name));

  var exports = functionDeclarations.concat(variableDeclarations);

  return exports;
}

function getOrcreateProcessedArtefactsTable() {
  try {
    var table = dao.create({
        table: "PROCESSED_XSJSLIB_ARTEFATCTS",
        properties: [{
            name: "id",
            column: "ID",
            type: "BIGINT",
            id: true
        }, {
            name: "location",
            column: "LOCATION",
            type: "VARCHAR",
            required: true,
            unique: true
        }, {
            name: "hash",
            column: "HASH",
            type: "VARCHAR",
            required: true
        }]
    },
    null,
    "SystemDB",
    "local"
    );

    if (!table.existsTable()) {
      table.createTable();
    }

    return table;
  } catch(e) {
    throw new Error("Cannot check if synchrnoisation is needed. Reason: " + e);
  }
}

function findTableEntryByResourceLocation(location) {
  var sql = "SELECT * FROM \"PUBLIC\".\"PROCESSED_XSJSLIB_ARTEFATCTS\" WHERE LOCATION = ?";
  return query.execute(sql, [location], null, "SystemDB", "local").shift();
}

function createNewTableEntryForResource(location, content, processedTable){
  processedTable.insert({
    location: location,
    hash: digest.md5Hex(content)
  });
}

function updateOldTableEntryForResource(oldEntry, location, content, processedTable) {
  oldEntry.LOCATION = location;
  oldEntry.HASH = digest.md5Hex(content);
  processedTable.update(oldEntry);
}

// TODO: create a class XSJSLibContentModifier {
//        getXSJSLibContentWithExports() {}
//        getExports() {}
// }
//
// TODO: create class ExportGenerationStateTable {
//    processedTable;
//    constructor(tableName = "PROCESSED_XSJSLIB_ARTEFATCTS", tableSchema = "PUBLIC", tableLocation = {"local", "SystemDB"}) {
//      this.tableName = tableName;
//      this.tableSchema = tableSchema;
//      this.tableLocation = tableLocation;
//      this.processedTable = _getOrcreateProcessedArtefactsTable();
//    }
//    findTableEntryByResourceLocation() {}
//    createNewEntryForResource() {}
//    updateOldEntryForResource() {}
// }
//
// TODO: create new class ExportGenerator {
//    constructor(targetCollectionRegistryPath) {
//      this.contentModifier = new XSJSLibContentModifier();
//      this.stateTable = new ExportGenerationStateTable(...);
//      _generateExportsRecursively(targetCollectionRegistryPath);
//    }
//
//    function _generateExportsRecurisvely() {}
//    
//    _toJsArray() {}
//    _checkTableAndGenerateExportsIfNeeded()
// }
// TODO: call new ExportGenerator(_context.registry_path);
// TODO: cleanup - onForcePublish for some projectPath, firstly delete all entries for that projectPath from the table. on Regular synchronization do not do that.