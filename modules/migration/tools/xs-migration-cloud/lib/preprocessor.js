/**
 * Created by SAP SE.
 */

var async = require('async');
var path = require('path');
var fs = require('fs');

var hdbschemaHandler = require('./plugin_handlers/hdbschema-handler');
var javascriptHandler = require('./plugin_handlers/js-handler');
var logUtil = require('./log-util');
var webfileHandler = require('./plugin_handlers/webfile-handler');
var calcviewHandler = require('./plugin_handlers/calculationview-handler');
var yamlParser = require('./yaml-parser');
var ParserError = require('./parser-error');
var hdbtiHandler = require('./plugin_handlers/hdbti-handler');
var TableObject = require('./table-object');

var PLUGIN_PATH = path.join(__dirname, '..', 'config', 'plugin.yml');

const CALCVIEW_ARTIFACTS = ['calculationview', 'analyticview', 'attributeview', 'analyticprivilege'];

class PreProcessor{
    
    constructor(){
        var pluginContentYaml = fs.readFileSync(PLUGIN_PATH, 'utf-8');
        yamlParser.loadyamlContent(pluginContentYaml);
        var pluginContentJson = yamlParser.getjsonData();
        
        for(let plugin of pluginContentJson.plugins){
            if(plugin.name === 'db'){
                this._dbExtensions = plugin.extensions;
            }            
        }        
        this._objects = [];
        this._cdsArtifacts = [];
    }
    
    
    process(fileList, globalContext, sourceDb, callback){
        
        logUtil.info('Starting preprocessing');
        this._sourceDb = sourceDb;
        
        var preProcessorInstance = this;
                
        async.series([
            function (callback) {
                preProcessorInstance._handleFiles(fileList, globalContext, callback);
            },           
            function (callback) {
                
                if(preProcessorInstance._objects.length > 0) {
                    preProcessorInstance._loadRuntimeInformation(globalContext, function (error) {
                        return callback(error);
                    });
                } else {
                    return callback();    
                }
            },
            function (callback) {
                logUtil.info('Starting oneview migration');
                calcviewHandler.migrateToOneView(fileList, globalContext, sourceDb, callback);
            }
        ], function (error) {
            return callback(error);
        });
          
        
    }   
    
    
    _handleFiles(fileList, globalContext, methodCallback) {

        var preProcessorInstance = this;
        var parserErrors = [];

        async.each(fileList, function (file, finished) {

            preProcessorInstance._handleFile(file, globalContext, function (error) {
                if(error instanceof ParserError) {
                    parserErrors.push(error);
                    return finished();
                }
                return finished(error);
            });

        }, function (error) {
            if(error){
                return methodCallback(error);
            }
            
            if(parserErrors.length > 0) {
                return methodCallback(parserErrors);
            }
            
            preProcessorInstance._overwriteRootNamespace(globalContext);

            if(globalContext.htaMode) {
                preProcessorInstance._alignDbPaths(globalContext);
            }

            logUtil.info('Root HDI namespace is ' + globalContext.rootHdiNamespace);

            if(parserErrors.length > 0) {
                return methodCallback(parserErrors);
            }
            
            return methodCallback();
        });    
        
    }
    
    
    _handleFile(file, globalContext, methodCallback) {
        
        var preProcessorInstance = this;

        async.parallel({
            addObjectToContext: function (callback) {
                preProcessorInstance._objects.push({'packageId': file.packageId, 'simpleName': file.simpleName, 'suffix': file.suffix});
                callback();
            },
            calculateRootHdiNamespace: function (callback) {
                if(preProcessorInstance._isDbContainerObject(file)) {
                    preProcessorInstance._calculateRootHdiNamespace(file.packageId, globalContext, callback);
                } else {
                    callback();
                }
            },
            handleSchema: function (callback) {
                if(file.suffix == 'hdbschema' || file.suffix == 'schema'){
                    hdbschemaHandler.preprocessing(file, globalContext, function (error) {
                        return callback(error);
                    });
                } else {
                    return callback();
                }
            },
            handleJs: function (callback) {
                if(file.suffix == 'js') {
                    javascriptHandler.preprocessing(file, globalContext, callback);
                } else {
                    return callback();
                }
            },
            handleTextbundle: function (callback) {
                if(file.suffix == 'hdbtextbundle'){
                    let textbundleFilePath = file.RunLocation.substring(1);
                    globalContext.textbundleFiles.push(textbundleFilePath);
                }
                return callback();
            },
            handleHtml: function (callback) {
                if(file.suffix == 'html' || file.suffix == 'htm') {
                    webfileHandler.preprocessing(file, globalContext, callback);
                } else {
                    return callback();
                }
            },
            handleCalcviewArtifacts: function (callback) {
                if(CALCVIEW_ARTIFACTS.includes(file.suffix)) {
                    calcviewHandler.preprocessing(file, globalContext, callback);
                } else {
                    return callback();
                }  
            },
            handleTableImport: function (callback) {
                if(file.suffix == 'hdbti') {
                    hdbtiHandler.preprocessing(file, globalContext, callback);
                } else {
                    return callback();
                }
            },
            handleCdsArtifacts: function (callback) {
                if(file.suffix == 'hdbdd') {
                    preProcessorInstance._cdsArtifacts.push(file.fullName);
                }
                return callback();
            },
            handleXsjob: function (callback) {
                if(file.suffix == 'xsjob') {
                    globalContext.hasJobs = true;
                }
                return callback();
            },
            handleProcedure: function (callback) {
                if(file.suffix =='procedure') {
                    globalContext.procedures.push(file);
                }
                return callback();
            }
        }, function (error, results) {
            return methodCallback(error);
        });
        
    }
    
    
    _loadRuntimeInformation(globalContext, callback) {
        
        var _this = this;
        
        async.series({
            contextObjects: function (callback) {
                _this._addObjectsToContext(globalContext, callback);
            },
            sysRepoObjects: function (callback) {
                _this._addSysRepoObjectsToContext(globalContext, callback);
            },
            cdsObjects: function (callback) {
                _this._addCdsRuntimeObjectsToContext(globalContext, callback);
            }
        }, function (error) {
            callback(error);
        });
        
        
    }
    
        
    _addObjectsToContext(globalContext, callback) {
        this._sourceDb.getRuntimeObjects(this._objects, function (error, runtimeObjects) {
            if(error) {
                return callback(error);
            }
            
            globalContext.contextObjects.addAll(runtimeObjects);
            callback();
        });       
        
    }
    
    
    _addSysRepoObjectsToContext(globalContext, callback) {
        
        this._sourceDb.getSysRepoObjects(function (error, sysRepoObjects) {
            if(error) {
                return callback(error);
            }
            
            globalContext.sysRepoObjects = sysRepoObjects;
            callback();
        });
    }
    
    
    _addCdsRuntimeObjectsToContext(globalContext, callback) {
        
        this._sourceDb.getCdsRuntimeObjects(this._cdsArtifacts, function (error, cdsRuntimeObjects) {
           
            if(error) {
                return callback(error);
            }
            
            globalContext.contextObjects.addAll(cdsRuntimeObjects);
            
            for(let cdsRuntimeObject of cdsRuntimeObjects) {
                if(cdsRuntimeObject.objectType === 'Entity') {
                    let tableObject = new TableObject(cdsRuntimeObject.schemaName, cdsRuntimeObject.objectName);
                    globalContext.tableObjects.push(tableObject);
                }
            }
            
            callback();
        });
        
    }
    
    
    
    _calculateRootHdiNamespace(packageId, globalContext, callback) {
        if(globalContext.rootHdiNamespace === null) {
            globalContext.rootHdiNamespace = packageId;
            
            return callback();
        }
        
        var intersection = [];
        
        var rootNamespaceSeparated = globalContext.rootHdiNamespace.split('.');
        var packageIdSeparated = packageId.split('.');
        
        rootNamespaceSeparated.forEach(function (rootNamespacePart, i) {
            if(rootNamespacePart === packageIdSeparated[i]){
                intersection.push(rootNamespacePart);
            }
            
        });
        
        globalContext.rootHdiNamespace = intersection.join('.');
        
        return callback();
    }
    
    
    _isDbContainerObject(file) {
        if(this._dbExtensions.indexOf(file.suffix) > -1) {
            return true;
        }
        return false;
    }
    
    
    _alignDbPaths(globalContext) {
        if(!globalContext.rootHdiNamespace) return;
        
        globalContext.pathMap["db-relative-path"] = path.join(globalContext.pathMap['db-relative-path'], globalContext.rootHdiNamespace);
        globalContext.pathMap["db-full-path-dev-objects"] = path.join(globalContext.pathMap["db-full-path-dev-objects"], globalContext.rootHdiNamespace);
        globalContext.pathMap["db-full-path-cfg-objects"] = path.join(globalContext.pathMap["db-full-path-cfg-objects"], globalContext.rootHdiNamespace);         
        globalContext.pathMap.db = path.join(globalContext.pathMap.db, globalContext.rootHdiNamespace);
        globalContext.pathMap["db-dev-object-folder"] = path.join(globalContext.pathMap["db-dev-object-folder"], globalContext.rootHdiNamespace);
    }
 
    
    _overwriteRootNamespace(globalContext) {
        
        if(!globalContext.overwriteRootNamespace) {
            return;
        }
        
        let match = globalContext.rootHdiNamespace.match(globalContext.overwriteRootNamespace);
        if(match && match.index === 0) {
            globalContext.rootHdiNamespace = globalContext.overwriteRootNamespace;
        }
                
    }
    
    
}

module.exports = new PreProcessor();