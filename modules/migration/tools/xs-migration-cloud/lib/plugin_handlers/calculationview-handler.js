var logUtil = require('../log-util');
var utils = require('../generator-utils');
var async = require('async');
var path = require('path');
var fs = require('fs');
var fse = require('fs-extra');
var pathIsAbsolute = require('path-is-absolute');
var putils = require('./plugin-utils');
var mc = require('../message-categories');
var msgs = require('../messages');
var infoHandler = require('../information-handler');
var translation = require('../translation');
var exec = require('child_process').exec;
var UsedObject = require('../used-object');
var RuntimeObject = require('../runtime-object');
var mkdirp = require('mkdirp');
var ObjectSet = require('../object-set');
var ActiveTags = require('../active-tags');
var fileUtils = require('../file-utils');
var RepositoryObject = require('../hana-repository/repository-object');

const CALCVIEW_MIGRATION_STAGES = require('../calcview-migration-stages');
const {isErrorInWhiteList} = require("./calcview_handler/calc-view-error-handler");

function startsWith(str, prefix) {
    return str.indexOf(prefix) === 0;
}

class CalculationviewHandler {


    preprocessing(file, globalContext, callback) {

        var fileName = "." + file.RunLocation;

        globalContext.calculationViews.set(fileName, file);

        file.doNotWriteContent();

        if (file.suffix === "analyticprivilege") {
            logUtil.trace('Found analyticprivilege file ' + file.privilegeObjectName);
            globalContext.analyticPrivileges.push(file.privilegeObjectName);
        } else {
            var newObjectName = file.packageId + '::' + file.simpleName;
            var newRuntimeObject = new RuntimeObject('_SYS_BIC', newObjectName, 'VIEW');
            globalContext.contextObjects.add(newRuntimeObject);
        }


        callback(null, file);

    };



    migrateToOneView(filelist, globalContext, sourceDb, callback) {

        var stage = CALCVIEW_MIGRATION_STAGES.ONEVIEW_MIGRATION;

        var calcviewHandlerInstance = this;

        async.waterfall([
            function (callback) {
                calcviewHandlerInstance._runMigrationProcess(globalContext, sourceDb, stage, callback);
            },
            function (newFiles, callback) {
                newFiles.forEach((newFile) => filelist.push(newFile));
                callback();
            }
        ], function (error) {
            return callback(error);
        });

    }



    handleFile(file, globalContext, callback, sourceDb, sqlParser, pluginHandler) {

        if(globalContext.calcviewsHandled) {
            return callback(null, file);
        }

        var calcviewHandlerInstance = this;

        pluginHandler.lazyGenerateContainer('db', globalContext);
        var stage = CALCVIEW_MIGRATION_STAGES.COLLECT_REFERENCES;

        async.waterfall([
            function (callback) {
                calcviewHandlerInstance._runMigrationProcess(globalContext, sourceDb, stage, callback);
            },
            function (requiredObjectsSet, callback) {
                putils.handleUsedObjects(requiredObjectsSet.values(), globalContext);
                callback(null, requiredObjectsSet.values());
            },
            function (handledUsedObjects, callback) {
                calcviewHandlerInstance._collectNonCoverables(handledUsedObjects, globalContext, callback);
            }
        ], function (error) {
            globalContext.calcviewsHandled = true;
            callback(error, file);
        });

    };



    _collectNonCoverables(usedObjects, globalContext, callback) {

        for(let usedObject of usedObjects) {
            if(!usedObject.isCoverable) {
                for(let userName of usedObject.userNames) {
                    globalContext.nonCoverableCalcviews.add(userName);
                }
            }
        }

        return callback();

    }


    callMigrationProcess(context, pluginHandler, sourceDb, callback) {

        if (context.calculationViews.size < 1) {
            logUtil.info("No calculation views to migrate");
            callback(null);
        } else {
            pluginHandler.lazyGenerateContainer('db', context);
            logUtil.info('Starting migration of Calcviews...');

            let stage = CALCVIEW_MIGRATION_STAGES.MIGRATE_TO_HDI;
            var _this = this;


            async.waterfall([
                function(callback){
                    _this._runMigrationProcess(context, sourceDb, stage, callback);
                },
                function (migrationMessages, callback) {
                    _this._hdiMigrationPostprocessing(context, migrationMessages, sourceDb, pluginHandler, callback);
                }
            ], function (error) {

                if (error) {
                    logUtil.error('Error occured during Calcview migration: ' + error);
                    return callback(error);
                }

                logUtil.info('Migration of Calviews finished with success');
                logUtil.logProgress(3);
                return callback();
            });

        }
    };


    _runMigrationProcess(context, sourceDb, stage, callback) {

        var calcviewHandlerInstance = this;

        async.waterfall([
                function (callback) {
                    calcviewHandlerInstance._writeInputFiles(context, sourceDb, stage, callback);
                },
                function (cb) {
                    var migrationCommand = calcviewHandlerInstance._getMigrationToolCommand(context, stage);
                    calcviewHandlerInstance._commandLineCall(context, migrationCommand, cb);
                },
                function(callback) {
                    calcviewHandlerInstance._parseResultFile(stage, context, callback);
                }
            ],
            function (err, result) {
                return callback(err, result);
            }
        );
    };


    _hdiMigrationPostprocessing(globalContext, migrationMessages, sourceDb, pluginHandler, methodCallback) {

        var calcviewHandlerInstance = this;
        var viewFiles;

        async.series([
            function (callback) {
                viewFiles = calcviewHandlerInstance._evaluateResult(globalContext, migrationMessages, sourceDb);
                fileUtils.cleanupEmptyFolders(globalContext);
                callback();
            },
            function(callback) {
                calcviewHandlerInstance._generateTranslationFiles(viewFiles, globalContext, sourceDb, callback);
            },
            function (callback) {
                calcviewHandlerInstance._generateActiveTagsFiles(viewFiles, sourceDb, globalContext, callback);
            },
            function (callback) {
                calcviewHandlerInstance._writePropertiesFiles(viewFiles, pluginHandler, globalContext, callback)
            }
        ], function (error) {
           return methodCallback(error);
        });
    }




    _evaluateResult(context, messages, sourceDb) {
        var successNumber = 0;
        var failureNumber = 0;
        var translations = [];

        for (let msg of messages) {

            if (msg.status.toLowerCase() === "successful") {
                successNumber++;

                var targetFile = path.normalize(msg.targetFiles[0]);
                var oldLocation = msg.targetFiles[0];

                if (startsWith(targetFile, "./") || startsWith(targetFile, "\\.")) {
                    targetFile = targetFile.substring(2);
                }
                var fullTargetPath = path.join(context.pathMap["db-full-path-dev-objects"], targetFile);

                var storagePath = putils.calculateStoragePath('db', 'db-full-path-dev-objects', targetFile, context);

                if(context.nonCoverableCalcviews.has(oldLocation)) {
                    storagePath = putils.calculateStoragePath('todo', 'todo-full-path', targetFile, context);
                }

                var newStoragePath = path.join(storagePath, path.basename(targetFile));

                mkdirp.sync(storagePath);
                fs.renameSync(fullTargetPath, newStoragePath);

                context.filePathIndex.updateIndex(newStoragePath, 'db', context.pathMap);
                var unixPath = msg.sourceFile.replace(/\\/g, '/');
                var file = context.calculationViews.get(unixPath);

                var packageName = file.PackageName.packageName;
                var filename = putils.getObjectname(file.RunLocation);
                var ext = "calculationview";

                translations.push({
                    file: file,
                    packageName: packageName,
                    fileName: filename,
                    ext: ext
                });


                var full = msg.sourceFile;
                full = full.substring(2); // cut off "./"
                var fpath = putils.getFilepath(full);
                var fname = putils.getFilename(full);
                if (fname.indexOf(".calculationview", fname.length - ".calculationview".length) !== -1) {
                    fname = fname.substring(0, fname.length - ".calculationview".length);
                    fpath = fpath.replace(/\//g, ".");
                    fpath = fpath.replace(/\\/g, ".");
                    var objectName = fpath + "::" + fname;

                    var schemaName = '_SYS_BIC';
                    var key = '"' + schemaName + '"."' + objectName + '"';
                    logUtil.debug("Calculation view " + key + " migrated.");

                    var newRuntimeObject = new RuntimeObject(schemaName, objectName, 'VIEW');
                    context.contextObjects.add(newRuntimeObject);

                } else if (fname.indexOf(".analyticprivilege", fname.length - ".analyticprivilege".length) !== -1) {
                    logUtil.debug("Analytics privilege " + fname + " migrated.");
                }

            } else {
                failureNumber++;
                var p = msg.sourceFile.replace(/\\/g, '/');;
                var fileObj = context.calculationViews.get(p);
                if (msg.messages[0].message === "Script based calculation view is not supported") {
                    let msgo = msgs.getMessage(mc.VIEW_MIGRATION, 3);
                    msgo.message.push(p);
                    msgo.file = path.join(context.config.directories['xs1-src'], fileObj.RunLocation);
                    infoHandler.logMessage(msgo);
                } else {
                    // other migration error (different category)
                    let msgo = msgs.getMessage(mc.VIEW_MIGRATION, 4);
                    msgo.message.push(msg.messages[0].message);
                    msgo.file = path.join(context.config.directories['xs1-src'], fileObj.RunLocation);
                    infoHandler.logMessage(msgo);
                }
            }
        }
        logUtil.info("Total calculation view and analytic privilege files: " + (successNumber + failureNumber) + " successful: " + successNumber + " failure: " + failureNumber);

        return translations;
    }



    _commandLineCall(context, cmd, callback) {
        logUtil.debug('executing command line call');

        process.env["HDBALM_HOST"] = context.system.host;
        process.env["HDBALM_PORT"] = context.system.sqlport;
        process.env["HDBALM_USER"] = context.system.user;
        process.env["HDBALM_PASSWD"] = context.system.password;

        var absolute = pathIsAbsolute(context.targetDir);
        var targetDirToolLocFolder = (absolute ? "": "./") + context.targetDir;

        exec(cmd, {
            cwd: targetDirToolLocFolder
        },function (error, stdout, stderr) {
            if (stdout) {
                logUtil.info(cmd + '--stdout: ' + stdout);
                logUtil.info(cmd + '--stdout: ' + stdout);
            }

            if (stderr && !isErrorInWhiteList(stderr.toString())) {
                logUtil.error("command: '" + cmd + "'" + 'report exception via --stderr: ');
                logUtil.error(stderr);
            }

            if (error !== null) {
                logUtil.error({
                    type: mc.ERROR,
                    message: cmd + ' exec error: ' + error,
                    category: mc.MIGRATION_PLUGIN,
                    id: mc.MIGRATION_PLUGIN + '_0',
                    file: ""
                }, {
                    report: true
                });
                return callback("Migration of calculation views failed with exit code: " + error.code);
            } else {

                logUtil.trace("in CMD call back");

                return callback();
            }
        });
    }


    _parseResultFile(stage, context, callback) {

        var resultFile = 'Summary.json';
        var subPath = context.pathMap['db'];

        if(stage === CALCVIEW_MIGRATION_STAGES.COLLECT_REFERENCES) {
            resultFile = 'RequiredObjects.json';
            subPath = context.pathMap['db-tmp'];
        }

        if(stage === CALCVIEW_MIGRATION_STAGES.ONEVIEW_MIGRATION) {
            subPath = context.config.directories['xs1-src'];
        }

        var messages = JSON.parse(fs.readFileSync(path.join(context.targetDir, subPath, resultFile), {
            encoding: 'utf8'
        }));

        if(stage === CALCVIEW_MIGRATION_STAGES.MIGRATE_TO_HDI) {
            return callback(null, messages);
        }

        if(stage === CALCVIEW_MIGRATION_STAGES.ONEVIEW_MIGRATION) {
            return this._processOneviewResult(messages, context, callback);
        }


        this._processRequiredObjects(messages, callback);
    }


    _processRequiredObjects(modelerArtifacts, callback) {
        var thinnedRequiredObjects = new ObjectSet();
        for(let modelerArtifact of modelerArtifacts) {
            if(modelerArtifact.requiredObjects) {
                for(let requiredObject of modelerArtifact.requiredObjects) {
                    if(this._isValidObjectName(requiredObject.name)){
                        let usedObject = new UsedObject(requiredObject.schema, requiredObject.name);
                        usedObject.addUserName(modelerArtifact.targetFiles);
                        usedObject.synonymName = requiredObject.synonym;
                        thinnedRequiredObjects.add(usedObject);
                    }
                }
            }
        }

        return callback(null, thinnedRequiredObjects);
    }


    _isValidObjectName(objectName) {
        if(objectName.includes('::null')) {
            return false;
        }

        return true;
    }


    _getMigrationToolCommand(context, stage) {
        var migrationToolLocFolder = path.resolve(path.join(__dirname, "calcview"));
        var migrationToolFileName = "calc_migration.jar";
        var migrationToolLoc = path.join(migrationToolLocFolder, migrationToolFileName);
        logUtil.debug("Migration tool location is " + migrationToolLoc);

        var absolute = pathIsAbsolute(context.targetDir);
        logUtil.debug("Target directory is absolute " + absolute);

        var javaExe = putils.getJavaExecutable(context);

        var migrationCommand = javaExe + " -jar " + migrationToolLoc +
            " " + (absolute ? "" : "./") + path.join(context.targetDir, context.config.directories['xs1-src']);

        var targetLocation;

        switch(stage) {
            case CALCVIEW_MIGRATION_STAGES.COLLECT_REFERENCES:
                targetLocation = context.pathMap["db-tmp"];
                break;
            case CALCVIEW_MIGRATION_STAGES.ONEVIEW_MIGRATION:
                targetLocation = context.config.directories["xs1-src"];
                break;
            case CALCVIEW_MIGRATION_STAGES.MIGRATE_TO_HDI:
                targetLocation = context.pathMap["db"];
                break;
        }

        migrationCommand += " " + (absolute ? "" : "./") + path.join(context.targetDir, targetLocation);

        migrationCommand += " " + (absolute ? "" : "./") + path.join(context.targetDir, context.pathMap["db-tmp"], "calcfilelist.json");

        if(stage === CALCVIEW_MIGRATION_STAGES.COLLECT_REFERENCES || stage === CALCVIEW_MIGRATION_STAGES.MIGRATE_TO_HDI){
            migrationCommand += " " + (absolute ? "" : "./") + path.join(context.targetDir, context.pathMap["db-tmp"], "schemamapping.json");
            migrationCommand += " " + (absolute ? "" : "./") + path.join(context.targetDir, context.pathMap["db-tmp"], "localschemas.json");
        }

        switch(stage) {
            case CALCVIEW_MIGRATION_STAGES.COLLECT_REFERENCES:
                migrationCommand += ' -DcollectReferences=true';
                break;
            case CALCVIEW_MIGRATION_STAGES.ONEVIEW_MIGRATION:
                migrationCommand += ' ' + (absolute ? "" : "./") + path.join(context.targetDir, context.pathMap["db-tmp"], "Viewmigrationoptions.json");
                migrationCommand += ' -DrunViewMigration=true';
                break;
            case CALCVIEW_MIGRATION_STAGES.MIGRATE_TO_HDI:
                migrationCommand += ' ' + (absolute ? "" : "./") + path.join(context.targetDir, context.pathMap["db-tmp"], "synonym.json");
                migrationCommand += ' ' + (absolute ? "" : "./") + path.join(context.targetDir, context.pathMap["db-tmp"], "Viewmigrationoptions.json");
                break;
        }

        logUtil.debug("final cmd is " + migrationCommand);

        return migrationCommand;
    }


    _writeInputFiles(context, sourceDb, stage, callback){

        let _this = this;

        async.parallel({

            calcfileList: function (callback) {

                logUtil.debug(context.calculationViews.size + " calculation view and analytics privilege files will be migrated");

                var list = Array.from(context.calculationViews.keys());
                var convertedList = _this._localizePaths(list, path.sep);

                var targetPath = path.join(context.targetDir, context.pathMap["db-tmp"]);
                utils.writeFile(targetPath, "calcfilelist.json", JSON.stringify(convertedList, null, 4), callback);

            },
            localschemas: function (callback) {

                var localSchemaList = context.applicationSchemas;
                var targetPath =  path.join(context.targetDir, context.pathMap["db-tmp"]);

                utils.writeFile(targetPath, "localschemas.json", JSON.stringify(localSchemaList), callback);

            },
            schemaMapping: function (callback) {

                sourceDb.getSchemaMapping(function(error, schemaMap){
                    logUtil.debug("schema mapping got:" + schemaMap);
                    if(error){
                        logUtil.error("schema mapping got with error:" + error + "set schema mapping as empty array");
                        schemaMap = {
                            "schemaMapping":[]
                        };
                        return callback(error);
                    }
                    var targetPath = path.join(context.targetDir, context.pathMap["db-tmp"]);

                    utils.writeFile(targetPath, "schemamapping.json", JSON.stringify(schemaMap, null, 4), callback);

                });
            },
            synonymMapping: function (callback) {
                if(stage !== CALCVIEW_MIGRATION_STAGES.MIGRATE_TO_HDI) {
                    return callback();
                }

                var synonymMappingContent = [];

                context.localizedObjects.forEach(function (localizedObject) {
                    synonymMappingContent.push(localizedObject.synonymMappingObject);
                });

                synonymMappingContent = synonymMappingContent.concat(context.synonymTargetProviders.synonymMapping);

                var synonymMapping = {
                    requiredObjects: synonymMappingContent
                };
                var targetPath =  path.join(context.targetDir, context.pathMap["db-tmp"]);
                utils.writeFile(targetPath, "synonym.json", JSON.stringify(synonymMapping), callback);

            },
            viewmigrationOptions: function (callback) {
                if(stage === CALCVIEW_MIGRATION_STAGES.COLLECT_REFERENCES) {
                    return callback();
                }

                var viewmigrationOptions = {
                    "namespaceForPrivilegeObject": context.rootHdiNamespace,
                    "unhideHiddenColumns": context.unhideHiddenColumns
                };

                if(context.joinOrderType !== null) {
                    viewmigrationOptions.joinOrderType = context.joinOrderType;
                }

                var targetPath = path.join(context.targetDir, context.pathMap["db-tmp"]);
                utils.writeFile(targetPath, "Viewmigrationoptions.json", JSON.stringify(viewmigrationOptions), callback);
            }

        }, function (error) {
            callback(error);
        });
    }


    _generateActiveTagsFiles(viewFiles, sourceDb, globalContext, callback) {

        async.map(viewFiles, function(viewFile, processingFinished) {
            ActiveTags.convertToPropertiesFile(sourceDb, viewFile.file, globalContext, processingFinished);

        }, function (error) {
            return callback(error);
        });

    }


     _generateTranslationFiles(translations, context, sourceDb, callback) {

         async.map(translations, function(translationObject, processingFinished) {
             translation.exportCalcviewTranslation(context, sourceDb, translationObject.file, translationObject.packageName, translationObject.fileName, translationObject.ext, processingFinished);

         }, function (error) {
             if(error){
                 logUtil.error('Error during export of Calcview translations');
                 return callback(error);
             }
             return callback();
         });

    }


    _writePropertiesFiles(files, pluginHandler, context, callback) {
        var projectPath = path.join(context.targetDir, context.config.directories['xs2-app']);
        async.each(files, function(file, finished){
            pluginHandler.storeKnownExtFile(projectPath, file.file, 'db', context, finished);
        }, function(error){
            if(error){
                logUtil.error('Error during writing of Calcview translation file');
                return callback(error);
            }
            return callback();
        })
    }


    _processOneviewResult(migrationResults, globalContext, callback) {

        var newFiles = [];

        for(let migrationResult of migrationResults) {

            let sourceFile = migrationResult.sourceFile;

            if(migrationResult.targetFiles) {

                for(let targetFile of migrationResult.targetFiles) {

                    let completeObjectName = putils.convertPathToObjectname(targetFile);
                    let schemaName = '_SYS_BIC';

                    let runtimeObject = new RuntimeObject(schemaName, completeObjectName, 'VIEW');

                    globalContext.contextObjects.add(runtimeObject);

                    let objectName = putils.getObjectname(targetFile);
                    let suffix = putils.getExtension(targetFile);
                    let packageId = completeObjectName.split('::')[0];

                    let newFile = new RepositoryObject(objectName, packageId, suffix);

                    let contentPath = path.join(globalContext.targetDir, globalContext.config.directories["xs1-src"], targetFile);
                    newFile.content = fs.readFileSync(contentPath, 'utf-8');
                    newFile.doNotWriteContent();

                    if(newFile.suffix === 'calculationview') {
                        let relativeSourceFile = sourceFile;

                        if(sourceFile.slice(0, 2) !== '.' + path.sep) {
                            relativeSourceFile = '.' + path.sep + sourceFile;
                        }

                        relativeSourceFile = relativeSourceFile.replace(/\\/g, '/');
                        relativeSourceFile = relativeSourceFile.replace(/\/\//g, '/');

                        globalContext.calculationViews.delete(relativeSourceFile);
                        globalContext.calculationViews.set('.' + newFile.RunLocation, newFile);
                    }
                    newFiles.push(newFile);
                }
            }

        }

        return callback(null, newFiles);
    }


    _localizePaths(paths, newSep) {
        return paths.map(unixPath => unixPath.replace(/\//g, newSep));
    }
}

module.exports = new CalculationviewHandler();
