var fs = require('fs');
var path = require('path');
var async = require("async");

var yamlParser = require("../lib/yaml-parser");
var logUtil = require("../lib/log-util");
var utils = require('../lib/generator-utils');
var reportLib = require('../lib/information-handler');
// only for pathMap
var postprocess = require('../lib/post-processing');
var pluginUtils = require('./plugin_handlers/plugin-utils');
var Container = require('./plugin_handlers/container');
var Containers = require('./plugin_handlers/containers');
var LazyContainerGenerator = require('./lazy-container-generator');

const CONFIG_PATH = "../config/plugin.yml";


class PluginHandler {
	
    constructor() {
        this.containers = new Containers();
        this.loadConfiguration();
    }
    
    
    loadConfiguration() {
        var yamlContent = fs.readFileSync(require.resolve(CONFIG_PATH), {encoding: 'utf-8'});
        yamlParser.loadyamlContent(yamlContent);
    
        var config = yamlParser.getjsonData();

        for (let containerConfig of config.plugins) {
            let container = new Container(containerConfig);
            this.containers.addContainer(container);
        }
    }
    
    
    lazyGenerateContainer (containerName, globalContext){        
        LazyContainerGenerator.generateContainer(containerName, globalContext);
    }
    

	storeKnownExtFile (p_path, f_Obj, containerName, globalContext, callback) {
        
        var that = this;
        
        if(f_Obj.toBeCreated.length > 0) {
            async.eachSeries(f_Obj.toBeCreated, function (new_file, acb) {
                var key = new_file.file_container + '-full-path';
                that.lazyGenerateContainer(new_file.file_container, globalContext);
                var storePath = pluginUtils.calculateStoragePath(new_file.file_container, key, new_file.filename, globalContext);
                if (new_file.update_content) {
                    if(new_file.tmp != true) {
                        globalContext.filePathIndex.updateIndex(new_file.filename, new_file.file_container, globalContext.pathMap);
                    }
                    async.waterfall([
                        function(callback) {
                            utils.writeFile(storePath, path.basename(new_file.filename), new_file.update_content, callback);
                        },
                        function(callback) {
                            if (new_file.content) {
                                utils.writeFile(storePath, path.basename(new_file.filename) + ".orig", new_file.content, callback);
                            } else {
                                callback();
                            }
                        }
                    ], function(error) {
                        acb(error);
                    });
                } else {
                    if(new_file.tmp != true) {
                        globalContext.filePathIndex.updateIndex(new_file.filename, new_file.file_container, globalContext.pathMap);
                    }
                    utils.writeFile(storePath, path.basename(new_file.filename), new_file.content, acb);
                }
            },
            function(err) {
                callback(err);
            });
            
        } else if(f_Obj.writeContent()) {
            
            logUtil.trace("Storing " + p_path + " " + globalContext.pathMap[containerName] + " dirname of " + f_Obj.RunLocation);
            
            that.lazyGenerateContainer(containerName, globalContext);
            var key = containerName + '-full-path-dev-objects';
            var storePath = pluginUtils.calculateStoragePath(containerName, key, f_Obj.RunLocation, globalContext);
            utils.writeFile(storePath,  path.basename(f_Obj.Name), f_Obj.content, function(error) {
                if (error) {
                    callback(error);
                } else {
                    let key = containerName + "-dev-object-folder";
                    let basepath = path.join(globalContext.pathMap[key], path.normalize(f_Obj.RunLocation));
                    
                    globalContext.filePathIndex.updateIndex(basepath, containerName, globalContext.pathMap);
                    callback(error);
                }
            });
            
        }  else {
            logUtil.trace("No file to be saved for " + f_Obj.RunLocation);
            callback();
        }        
        
	};	
    
   
	process (completeFileList, globalContext, packages, sourceDb, sqlParser, post_callback) {
        
        var fileListForHandlers;
        var that = this;
        
        async.waterfall([
            function(callback) {
                fileListForHandlers = that.splitFiles(completeFileList, globalContext);
                that.handleKnownExtFiles(fileListForHandlers, globalContext, sourceDb, sqlParser, callback);
            },
            function(callback) {
                logUtil.logProgress(6);
                logUtil.info('Processing finished\n Starting Post-Processing...');                
                postprocess.init(reportLib, globalContext);
                postprocess.process(globalContext, that, packages, completeFileList, sourceDb, callback);
            }
        ], function (err) {
            if(err){
                return post_callback(err);

            }
            logUtil.info('Post-Processing finished');
            post_callback();
        });
	};


    handleKnownExtFiles (fileListForHandlers, globalContext, sourceDb, sqlParser, post_callback)	{

        var that = this;
        
        async.eachSeries(fileListForHandlers, function(file, callback) {

            that.handleKnownExtFile(file, globalContext, sourceDb, sqlParser, callback);

        }, function(error) {
            if (error) {
                logUtil.debug("Unable to handle files: " + JSON.stringify(error));
            }
            post_callback(error);
        });

    };    
    

	handleKnownExtFile (fileObj, globalContext, sourceDb, sqlParser, callback) {
        
        logUtil.info("Handling file: " + fileObj.RunLocation);
        var that = this;
        
        async.waterfall([
            function (callback) {
               that.parseFile(fileObj, fileObj.container, globalContext, sourceDb, sqlParser, callback);
            },
            function (file, found, callback) {
                let container = that.containers.getContainerByName(fileObj.container);
                let projectPath = path.join(globalContext.targetDir, globalContext.config.directories['xs2-app']);
                reportLib.updateMessages(globalContext, container, file, found);
                that.storeKnownExtFile(projectPath, file, container.name, globalContext, callback);
            }           
        ], function (error) {
            async.setImmediate(callback.bind(undefined, error));
        });
        
		
	};
	
	
    splitFiles (completeFileList, globalContext) {
		
        var fileListForHandlers = [];
        
        for (let fileObj of completeFileList) {

            let container = this.containers.getContainerForExt(fileObj.suffix);
            fileObj.container = container.name;
            
            if(container.isTodoContainer()) {
                container.addFile(fileObj);
                reportLib.updateToDoList(fileObj, globalContext);
            } else {
                fileListForHandlers.push(fileObj);
            }

		}
        
        return fileListForHandlers;
	};
	
        
	parseFile (file, containerName, context, sourceDb, sqlParser, callback) {
        var container = this.containers.getContainerByName(containerName);
        
        var handler = container.getHandlerForExt(file.suffix);
        if (!handler) {
           return callback(null, file, false); 
        }

        let localCallback = function(error, fileObj) {
            if (error) {
                callback(error);
            } else{
                callback(error, fileObj, true);
            }
        };
        
        handler.handleFile(file, context, localCallback, sourceDb, sqlParser, this);

	};
	
}



module.exports = new PluginHandler();
