var logUtil = require('../log-util');
var utils = require('../generator-utils');
var async = require('async');
var path = require('path');
var putils = require('./plugin-utils');
var mc = require('../message-categories');
var msgs = require('../messages');
var infoHandler = require('../information-handler');
var UsedObject = require('../used-object');
var xml2js = require('xml2js');
const SQL_PRIVILEGE = require('../sql-privilege');

function HdbflowgraphHandler() {

	var flowgraphHandlerInstance = this;
    this.flowgraphContext = new Map();

    function getSynonymName(schema, object, privileges, context, fileSummary) {
        let usedObject;
        if (privileges) {
            usedObject = new UsedObject(schema, object, privileges);
        } else {
            usedObject = new UsedObject(schema, object);
        }
        fileSummary.synonymCount++;
        return putils.handleUsedObject(usedObject, context).synonymName;    
    }

    function hasAnnotation(node, annotationKey) {
        let result = false;
        node.annotations.forEach(function(annotation){
            if (annotation.$.key === annotationKey) {
                result = true;
            }
        });
        return result;    
    }

    function getAnnotation(node, annotationKey) {
        let result;
        node.annotations.forEach(function(annotation){
            if (annotation.$.key === annotationKey) {
                result = annotation;
            }
        });
        return result;    
    }

    function setAnnotation(node, annotationKey, annotationValue) {
        node.annotations.forEach(function(annotation){
            if (annotation.$.key === annotationKey) {
                annotation.$.value = annotationValue;
                return;
            }
        }); 
    }

    function deleteAnnotation(node, annotationKey) {
        node.annotations.forEach(function(annotation, index, array){
            if (annotation.$.key === annotationKey) {
                array.splice(index, 1);
                return;
            }
        });
    }

    function handleLookupNode(node, globalContext, fileSummary) {
        let synonymName = getSynonymName(node.$.lookupTableSchemaName, node.$.lookupTableName, [SQL_PRIVILEGE.SELECT], globalContext, fileSummary);
        delete node.$.lookupTableSchemaName;
        node.$.lookupTableName = synonymName;
        fileSummary.lookupExists = true;
        fileSummary.nodeMessages.push({nodeName: node.$.name, nodeType: node.$["xsi:type"], messages: ["Please check the lookup condition. The table names used should be replaced with synonym names"]});
    }

    function handleTableComparisonNode(node, globalContext, fileSummary) {
        let synonymName = getSynonymName(node.$.compareTableSchema, node.$.compareTableName, [SQL_PRIVILEGE.SELECT], globalContext, fileSummary);
        delete node.$.compareTableSchema;
        node.$.compareTableName = synonymName;
        fileSummary.tableComparisonExists = true;
        fileSummary.nodeMessages.push({nodeName: node.$.name, nodeType: node.$["xsi:type"], messages: ["Please check the filter condition. The table names used should be replaced with synonym names"]});
    }

    function handleProcedureNode(node, globalContext, fileSummary) {
        let synonymName = getSynonymName(node.$.authoringSchemaName, node.$.procedureName, [SQL_PRIVILEGE.EXECUTE, SQL_PRIVILEGE.DEBUG], globalContext, fileSummary);
        delete node.$.authoringSchemaName;
        node.$.procedureName = synonymName;
        if (node.inputs && Array.isArray(node.inputs.length)) {
            node.inputs.forEach(function(input) {
                if (input.$.kind === "SCALAR" && input.attributes && input.attributes.length === 1 && input.attributes[0].$.mappedName) {
                    if (!node.tables) {
                        node.tables = [];
                    }
                    let tableAttributes = {
                        "xmi:id": createUUID_32(),
                        type: input.$["xmi:id"],
                        active: true
                    };
                    let tableRows = {
                        $: {"xmi:id": createUUID_32()},
                        values: input.attributes[0].$.mappedName
                    };
                    node.tables.push({
                        $: tableAttributes,
                        rows: tableRows
                    });
                    delete input.attributes[0].$.mappedName;
                }
            });
        }
    }

    function createUUID_32() {
        let S4 = function() {
            return (((1+Math.random())*0x10000)|0).toString(16).substring(1);
        };
        return (S4()+S4()+S4()+S4()+S4()+S4()+S4()+S4());
    }

    function handleUnionNode(node, globalContext, fileSummary) {
        //create the tablemapping inside union if it does not exist
        if (!node.tableMappings) {
            node.tableMappings = [];
            let tableAttributeMappings = [];
            let firstInput = node.inputs[0];
            let output = node.outputs[0];
            let tableMapping = {
                "xmi:id": createUUID_32(),
                source: firstInput.$["xmi:id"],
                target: output.$["xmi:id"]
            };
            if (firstInput.attributes.length === output.attributes.length) {
                for (let i = 0; i < firstInput.attributes.length; i++) {
                    let attributeMapping = {
                        "xmi:id": createUUID_32(),
                        source: firstInput.attributes[i].$["xmi:id"],
                        target: output.attributes[i].$["xmi:id"]
                    };
                    tableAttributeMappings.push({$: attributeMapping});
                }
            }
            node.tableMappings.push({$: tableMapping, attributeMappings: tableAttributeMappings});
        }
    }

    function getAttributeFromId(attributes, id) {
        return attributes.find(function(attribute) {
            return id === attribute.$["xmi:id"];
        });
    }

    function handleFilterNode(node, globalContext, fileSummary) {
        let idNameMapping = [];
        if (node.tableMappings && node.tableMappings.length > 0) {
            let sourceId = node.tableMappings[0].$.source;
            let targetId = node.tableMappings[0].$.target;
            let source = node.inputs[0].attributes, 
                target = node.outputs[0].attributes;
            if (node.tableMappings[0].attributeMappings) {
                node.tableMappings[0].attributeMappings.forEach(function(attributeMapping) {
                    let sourceAttr = getAttributeFromId(source, attributeMapping.$.source);
                    target.forEach(function(attribute) {
                        if (attribute.$["xmi:id"] === attributeMapping.$.target) {
                            attribute.$.expression = sourceAttr.$.name;
                        }
                    });
                });
            }
        }
        delete node.tableMappings;
    }

    function handleDataSource(node, globalContext, fileSummary) {
        let synonymName = getSynonymName(node.$.authoringSchemaName, node.$.catalogObjectName, [SQL_PRIVILEGE.SELECT], globalContext, fileSummary);
        delete node.$.authoringSchemaName;
        node.$.catalogObjectName = synonymName;
    }

    function handleDataTarget(node, globalContext, fileSummary) {
        if (node.$.type === "DATABASE_TABLE" || node.$.type === "TEMPLATE_TABLE" || node.$.type === "VIRTUAL_TABLE") {
            if (hasAnnotation(node, "sap.im.sequenceName")) {
                let sequenceName = getAnnotation(node, "sap.im.sequenceName").$.value;
                let sequenceSchema = getAnnotation(node, "sap.im.sequenceSchema").$.value;
                let synonymName = getSynonymName(sequenceSchema, sequenceName, [SQL_PRIVILEGE.SELECT], globalContext, fileSummary);
                setAnnotation(node, "sap.im.sequenceName", synonymName);
                deleteAnnotation(node, "sap.im.sequenceSchema");
            }
            let historyTableAnnotation = getAnnotation(node, "sap.im.historyTable");
            let privileges = [SQL_PRIVILEGE.SELECT, SQL_PRIVILEGE.INSERT, SQL_PRIVILEGE.UPDATE, SQL_PRIVILEGE.DELETE];            
            if (historyTableAnnotation && historyTableAnnotation.annotations) {
                let name = historyTableAnnotation.annotations[1].$.value;
                let schema = historyTableAnnotation.annotations[0].$.value;
                let type = historyTableAnnotation.annotations[2].$.value;
                if (type === "DATABASE_TABLE") {
                    let synonymName = getSynonymName(schema, name, privileges, globalContext, fileSummary);
                    historyTableAnnotation.annotations[1].$.value = synonymName;
                }
            } else if (historyTableAnnotation && !historyTableAnnotation.annotations && historyTableAnnotation.$ && historyTableAnnotation.$.value === "") {
                deleteAnnotation(node, "sap.im.historyTable");
            }
            if (node.$.type === "DATABASE_TABLE" || node.$.type === "VIRTUAL_TABLE") {
                let synonymName = getSynonymName(node.$.authoringSchemaName, node.$.catalogObjectName, privileges, globalContext, fileSummary);
                delete node.$.authoringSchemaName;
                node.$.catalogObjectName = synonymName;
            }
            if (node.$.type === "TEMPLATE_TABLE") {
                delete node.$.authoringSchemaName;
            }
            return {};
        }        
    }

    function handleDataNode(node, globalContext, fileSummary) {
        if (node.outputs) {
            handleDataSource(node, globalContext, fileSummary);
        } else if (node.inputs) {
            handleDataTarget(node, globalContext, fileSummary);
        }
        if (node.$.type === "VIRTUAL_TABLE") {
            fileSummary.nodeMessages.push({nodeName: node.$.name, nodeType: node.$["xsi:type"], messages: ['Synonyms are created for existing virtual table catalog objects as hdbvirtualtable generation is not yet supported by the migration assistant.']});
            fileSummary.virtualTables.push(node.$.catalogObjectName);
        }
    }

    function initSummaryFile(file) {
        return {
            name: file._name, 
            packageName: file._packageName,
            virtualTables: [],
            synonymCount: 0,
            nodeMessages: [],
            flowgraphMessages: []
        };
    }

    function writeToOutput(result, file, globalContext, fileSummary) {
        var builder = new xml2js.Builder();
        var newContent = builder.buildObject(result);
        var newFilename = path.join(
                                globalContext.pathMap["db-dev-object-folder"],
                                putils.getFilepath(file.RunLocation),
                                putils.getObjectname(file.RunLocation) + ".hdbflowgraph");                    
        file.toBeCreated.push({
            filename: newFilename,
            update_content: newContent,
            file_container: 'db'
        });
        if (fileSummary.virtualTables.length > 0) {
            let msgo = msgs.getMessage(mc.HDI, 20);
            msgo.message.push(newFilename);
            msgo.message.push(fileSummary.virtualTables.toString());
            msgo.file = path.join(globalContext.config.directories['xs1-src'], file.RunLocation);
            infoHandler.logMessage(msgo); 
            fileSummary.flowgraphMessages.push("Flowgraph " + newFilename + " uses virtual table(s) for which hdbsynonyms are created. hdbvirtualtable generation is not yet supported.");
        } else{
            delete fileSummary.virtualTables;
        }
        if (fileSummary.realtime) {
            let msgo = msgs.getMessage(mc.HDI, 21);
            msgo.message.push(newFilename);
            msgo.file = path.join(globalContext.config.directories['xs1-src'], file.RunLocation);
            infoHandler.logMessage(msgo);
            fileSummary.flowgraphMessages.push("Flowgraph " + newFilename + " is marked for real-time. User needs to grant necessary privileges on remote source to the HDI user.");
        }
        if (fileSummary.lookupExists) {
            let msgo = msgs.getMessage(mc.HDI, 22);
            msgo.message.push(newFilename);
            msgo.file = path.join(globalContext.config.directories['xs1-src'], file.RunLocation);
            infoHandler.logMessage(msgo);
        }
        if (fileSummary.tableComparisonExists) {
            let msgo = msgs.getMessage(mc.HDI, 23);
            msgo.message.push(newFilename);
            msgo.file = path.join(globalContext.config.directories['xs1-src'], file.RunLocation);
            infoHandler.logMessage(msgo);
        }
    }

    this.handleFile = function (file, globalContext, callback, sourceDb, sqlParser, pluginHandler) {
        var fileName = "." + file.RunLocation;
        var fileSummary = initSummaryFile(file);
        flowgraphHandlerInstance.flowgraphContext.set(fileName, fileSummary);
        var oldContent = file.content.toString('utf8');
        file.doNotWriteContent();
        xml2js.parseString(oldContent, function(err, result) {
            if ("flowgraph:ContainerNode" in result) {
                var containerNode = result["flowgraph:ContainerNode"];
                delete containerNode.$.targetSchema;
                if (containerNode.nodes) {
                    containerNode.nodes.forEach(function(node) {
                        if (node.$["xsi:type"] === "flowgraph:DataNode" && node.$.authoringSchemaName) {
                            handleDataNode(node, globalContext, fileSummary);
                        } else if (node.$["xsi:type"] === "flowgraph:LookupNode" && node.$.lookupTableSchemaName) {
                            handleLookupNode(node, globalContext, fileSummary);
                        } else if (node.$["xsi:type"] === "flowgraph:TableComparisonNode" && node.$.compareTableSchema) {
                            handleTableComparisonNode(node, globalContext, fileSummary);
                        } else if (node.$["xsi:type"] === "flowgraph:ProcedureNode" && node.$.authoringSchemaName) {
                            handleProcedureNode(node, globalContext, fileSummary);
                        } else if (node.$["xsi:type"] === "flowgraph:UnionNode") {
                            handleUnionNode(node, globalContext, fileSummary);
                        } else if (node.$["xsi:type"] === "flowgraph:FilterNode") {
                            handleFilterNode(node, globalContext, fileSummary);
                        }
                    });
                    if (containerNode.$.runtimeBehavior === "REALTIME_TASK") {
                        fileSummary.realtime = true;
                    }
                    writeToOutput(result, file, globalContext, fileSummary);
                }
            }
        });                
        callback(null, file);
        
    };

    this.postProcessing = function(context, pluginHandler, sourceDb, callback) {
        async.parallel({           
            fgfilelist: function (callback) {
                var list = Array.from(flowgraphHandlerInstance.flowgraphContext.keys());
                if (list.length < 1) {
                    logUtil.info("No flowgraphs to migrate");
                    callback(null);
                } else {
                    logUtil.info(list.length + " flowgraph files have been successfully migrated");
                    var targetPath = path.join(context.targetDir, context.config.directories['xs2-app'], context.pathMap["db-tmp"]);               
                    utils.writeFile(targetPath, "fgfilelist.json", JSON.stringify(list, null, 4), callback);
                }            
            },
            fgfilesummary: function (callback) {
                var values = Array.from(flowgraphHandlerInstance.flowgraphContext.values());
                if (values.length < 1) {
                    logUtil.info("No flowgraphs to migrate");
                    callback(null);
                } else {
                    var targetPath = path.join(context.targetDir, context.config.directories['xs2-app'], context.pathMap["db-tmp"]);               
                    utils.writeFile(targetPath, "fgfilesummary.json", JSON.stringify(values, null, 4), callback);
                }            
            }
        }, function (error) {
            callback(error);
        });        
    }
    
}

module.exports = new HdbflowgraphHandler();
