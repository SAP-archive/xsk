var fs = require('fs');
var path = require('path');
var logUtil = require("../lib/log-util");
var manifestGenerator = require("./manifest-generator");
var securityHandler = require("./plugin_handlers/xs-security-handler");
var putils = require('./plugin_handlers/plugin-utils');
var calcviewHandler = require('./plugin_handlers/calculationview-handler');
var hdbflowgraphHandler = require('./plugin_handlers/hdbflowgraph-handler');
var async = require('async');
var DefaultAccessRoleGenerator = require('./default-access-role-generator');
var hdbroleHandler = require('./plugin_handlers/hdbrole-handler');
var SynonymGenerator = require('./synonym-generator');
var PublicAccessRoleGenerator = require('./public-access-role-generator');


function postProcessHandler() {
    var that = this;
    this.reportLib = null;

    this.init = function (reportLib, globalContext) {
        that.reportLib = reportLib;
        that.globalContext = globalContext;
    };

  
    this.generateDescriptors = function () {
        return null;
    };

    // exposes xsjs files ?
    this.checkFileExposes = function (globalContext, fileList, packages) {
        var packagelist = analyzeFolderStructure(globalContext, packages);
        for (var i = 0; i < fileList.length; i++) {
            var fileObj = fileList[i];
            if (fileObj.container == "web" || (fileObj.container == "xsjs" && (fileObj.name.indexOf(".xsjs", fileObj.name.length - ".xsjs".length) !== -1))) {
                var filePath = fileObj.pkgOnlyName;
                var pkgPath = putils.getFilepath(filePath);
                logUtil.trace("filepath" + pkgPath + "!!" + path.normalize(fileObj.name) + "   !!");
                var pkgObj = packagelist[pkgPath];
                
                if (pkgObj && pkgObj.exposed == false) {
                    if (that.reportLib)
                        that.reportLib.logMessage({
                            file: fileObj.name,
                            loc: null,
                            message: "the file in XS Classic package structure is protected from http access and will not be protected in new migrated XS Advanced",
                            type: "warning"
                        });
                    logUtil.warning("Found a exposed property warning: " + fileObj.name);
                }
            }
        }
    };

    function analyzeFolderStructure(globalContext, pkgs) {
        var ret_packages = {};
        var foldersObjArray = globalContext["xs-app"];

        for (var i = 0; i < pkgs.length; i++) {
            var pkgObj = findParentFolder(pkgs[i].packageFile, foldersObjArray);
            pkgObj.name = pkgs[i].packageFile;
            ret_packages[path.normalize(pkgObj.name)] = pkgObj;
        }

        return ret_packages;
    }

    function findParentFolder(pkg, foldersObjArray) {
        var parent = null;
        for (var prop in foldersObjArray) {
            if (path.normalize(pkg).indexOf(path.normalize(prop)) == 0 && foldersObjArray[prop].update_content.exposed != undefined) {

                if (parent == null) {
                    parent = path.normalize(prop);
                } else {

                    if (path.normalize(parent).split(path.sep).length < path.normalize(prop).split(path.sep).length) {
                        parent = path.normalize(prop);
                    }
                }
            }
        }
        return {
            parent: parent,
            exposed: parent ? (foldersObjArray[parent].update_content.exposed == undefined ? false : foldersObjArray[parent].update_content.exposed) : false
        };
    }

    function removeLogFiles(globalContext) {
        var files = [
            [path.join(globalContext.pathMap["db-full-path-dev-objects"]), "MigrationLog.log"],
            [globalContext.pathMap["db-full-path-dev-objects"], "Summary.json"]
        ];
        for (var i = 0; i < files.length; i++) {
            var p = path.join(files[i][0], files[i][1]);
            try {
                fs.accessSync(p);
                fs.renameSync(p, path.join(globalContext.targetDir, globalContext.config.directories['xs2-app'], globalContext.pathMap["db-tmp"], files[i][1]));
            } catch (err) {
                logUtil.trace("File " + p + " not exist or permission denied");
            }
        }

    }
    this.process = function (globalContext, pluginHandler, packages, completeFileList, sourceDb, upper_callback) {

        async.series([
                function (callback) {
                    //generateSecurityFiles
                    logUtil.info('Generating security files');
                    securityHandler.postcalculation(globalContext, pluginHandler, packages, sourceDb, callback);
                },
                function (callback) {
                    hdbroleHandler.postcalculation(globalContext, sourceDb, callback);  
                },
                function (callback) {
                    //migrateflowgraphs
                    hdbflowgraphHandler.postProcessing(globalContext, pluginHandler, sourceDb, callback);
                },
                function (callback) {
                    //migrateCalcViews
                    calcviewHandler.callMigrationProcess(globalContext, pluginHandler, sourceDb, callback);
                },
                function(callback) {
                    let synonymGenerator = new SynonymGenerator(globalContext);
                    synonymGenerator.generateSynonyms(globalContext, completeFileList, packages, callback);
                },
                function (callback) {
                    //generateManifest
                    if(globalContext.generate_manifests){
                        logUtil.info('Generating manifests');
                        manifestGenerator.generateManifest(path.join(that.globalContext.targetDir, that.globalContext.config.directories["xs2-app"]), "manifest.yml", that.reportLib, that.globalContext, callback);                   
                    } else {
                        callback();      
                    }
                },
                function (callback) {
                    //generateDescriptors
                    logUtil.info('Generating descriptors');
                    var genDescMsgs = that.generateDescriptors();
                    callback(null, genDescMsgs);
                },                
                function (callback) {
                    //checkExpose
                    let fileList = globalContext.filePathIndex.toArray();
                    that.checkFileExposes(globalContext, fileList, packages);
                    callback();
                },
                function (callback) {
                    let error;
                    try {
                        DefaultAccessRoleGenerator.generateArtifacts(globalContext);
                    } catch (e){
                        logUtil.error('Error during generation of default_access_role');
                        error = e.message;
                    }
                    callback(error);
                },
                function (callback) {
                    let error;
                    if(!globalContext.htaMode) {
                        try {
                            PublicAccessRoleGenerator.generate(globalContext);
                        } catch (e){
                            logUtil.error('Error during generation of public-access roles');
                            error = e.message;
                        }    
                    }                    
                    callback(error);
                }
            ],
            function (err, results) {
                if(!err){
                    removeLogFiles(globalContext);
                }
                upper_callback(err);
            });


    };
}
module.exports = new postProcessHandler();
