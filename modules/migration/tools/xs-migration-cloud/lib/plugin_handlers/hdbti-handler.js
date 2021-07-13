// --------------------------------------------------------------------------
//
// Plugin handler for hdbti files
//
// --------------------------------------------------------------------------

var path = require('path');
var async = require('async');

var hdbtiParser = require('./hdbti').parser;
var utils = require('./plugin-utils');
var log = require('../log-util');
var mc = require('../message-categories');
var msgs = require('../messages');
var UsedObject = require('../used-object');
var objectClone = require('../object-clone');

const SQL_PRIVILEGE = require('../sql-privilege');

var HDBTABLEDATA_EXT = ".hdbtabledata";

class HdbtiHandler {

    _convertAndSort(columns) {
        var arr = [];
        var temp_Map = {};
        for (var k in columns) {
            temp_Map[columns[k].TABLE_NAME + "." + columns[k].COLUMN_NAME] = columns[k];
        }
        for(var i in temp_Map){
            arr.push(temp_Map[i]);
        }
        
        return arr.sort(function(a,b) {
            return parseInt(a.POSITION) - parseInt(b.POSITION);
        });
    }

    _fixFilename(oldFilename) {
        // from playground.d025616.event2705.data.loads:event.csv 
        // to playground.d025616.event2705.data.loads::event.csv

        var pt = /:/;
        return oldFilename.replace(pt, '::');
    }
    
    
    preprocessing(hdbtiFile, globalContext, callback) {

        var hdbtiObject = hdbtiParser.parse(hdbtiFile.content.toString('utf8'));

        for(let importDefinition of hdbtiObject) {
            
            if(importDefinition.header && !importDefinition.useHeaderNames) {
                let csvFilename = this._fixFilename(importDefinition.file);
                globalContext.referencedCsvFiles.withHeader.push(csvFilename);
            }            
            
        }
        
        callback();
    }
    
    

    generateNewHdbti (globalContext, file, oldHdbti) {

        var newHdbti = {
            format_version: 1,
            imports: []
        };

        for (var i=0; i < oldHdbti.length; i++) {
            var oldImport = oldHdbti[i];
            var newImport = {
                target_table: oldImport.table,
                import_settings: { }
            };

            if ("schema" in oldImport) {
                // check at the end whether we need a synonym
                //
                let usedObject = new UsedObject(oldImport.schema, oldImport.table, [SQL_PRIVILEGE.SELECT]);
                usedObject.addUserName(file.fullName + '.' + file.suffix);
                utils.handleUsedObject(usedObject, globalContext);

            } else if (oldImport.table in globalContext.publicSynonyms) {
                // no schema given: generate a synonym if it is in 
                // the list of public synonyms
                //
                var syn = globalContext.publicSynonyms[oldImport.table];

                let usedObject = new UsedObject(syn.OBJECT_SCHEMA, syn.OBJECT_NAME, [SQL_PRIVILEGE.SELECT]);
                usedObject.addUserName(file.fullName + '.' + file.suffix);
                utils.handleUsedObject(usedObject, globalContext);
            }

            newImport.source_data = {
                data_type: "CSV",
                file_name: this._fixFilename(oldImport.file),
                has_header: ("header" in oldImport) ? oldImport.header : false
            };
            if ("delimiter" in oldImport) {
                newImport.source_data.type_config = {
                    delimiter: oldImport.delimiter
                }
            }
            if("keys" in oldImport) {
                newImport.import_settings.include_filter = this._createIncludeFilters(oldImport.keys);
            }

            if (oldImport.delimEnclosing) {
                if(!newImport.source_data.type_config) {
                    newImport.source_data.type_config = {};
                }
                newImport.source_data.type_config.do_quote = true;
                newImport.source_data.type_config.quote_character = oldImport.delimEnclosing;
            }
            
            
            if (!oldImport.distinguishEmptyFromNull || oldImport.distinguishEmptyFromNull === false) {
                let msg = msgs.getMessage(mc.HDI, 8);
                msg.file = path.join(globalContext.config.directories['xs1-src'], file.RunLocation);
                utils.pushMessage(file, msg);    
            }
            if (! ("columns" in oldImport)) {
                var msg = msgs.getMessage(mc.HDI, 7);
                msg.file = path.join(globalContext.config.directories['xs1-src'], file.RunLocation);
                utils.pushMessage(file, msg);
                // cannot migrate this object
                return undefined;
            } else {
                // always required
                //
                var import_columns = [];
                for (var j=0; j < oldImport.columns.length; j++) {
                    var c = oldImport.columns[j];
                    import_columns.push(c.COLUMN_NAME);
                } 
                newImport.import_settings.import_columns = import_columns;
                if (newImport.source_data.has_header === false) {

                    // need table mapping
                    var cm = {};
                    for (var j=0; j < oldImport.columns.length; j++) {
                        var c = oldImport.columns[j];
                        cm[c.COLUMN_NAME] = j+1;
                    }
                    newImport.column_mappings = cm;
                }
            }
            log.trace("new hdbti: " + JSON.stringify(newImport, null, 4));
            newHdbti.imports.push(newImport);
        }
        return newHdbti;
    }

    _retrieveTableColumns(globalContext, file, origHdbti, sourceDb, callback) {

        var _this = this;
        
        async.eachLimit(origHdbti, 3, 
            function (imp, cb) {
                var schema = ('schema' in imp) ? imp.schema : undefined;
                sourceDb.getTableColumns(schema, imp.table, function(err, res) {
                    if (err) {
                        var msg = msgs.getMessage(mc.INFRASTRUCTURE, 6);
                        msg.message.push(imp.table);
                        msg.message.push(JSON.stringify(err));
                        msg.file = path.join(globalContext.config.directories['xs1-src'], file.RunLocation);
                        utils.pushMessage(file, msg);
                    } else {
                        imp.columns = _this._convertAndSort(res);
                    }
                    cb(err);
                });
            },
            function (err) {
                callback(err);
            });
    }
    
    
    
    _createIncludeFilters (filterKeys) {
        var groups = new Map();
       
        if(filterKeys.length < 1) {
            return [];
        }
        
        for(let filterKey of filterKeys) {
            let filterKeyName = Object.getOwnPropertyNames(filterKey)[0];
            let filterKeyValue = filterKey[filterKeyName];
            
            if(!groups.has(filterKeyName)) {
                groups.set(filterKeyName, [filterKeyValue]);
            } else {
                groups.get(filterKeyName).push(filterKeyValue);
            }
        }
                
        var keysArray = Array.from(groups.keys());
        
        if(keysArray.length == 1) {
            let combined = [];
            let group = groups.get(keysArray[0]);
            
            for (let value of group) {
                let filter = {};
                filter[keysArray[0]] = value;
                combined.push(filter);
            }
            
            return combined;
        }
        
        
        var group1 = groups.get(keysArray[0]);
        var group2 = groups.get(keysArray[1]);
        
        var combined = this._combineTwoGroups(group1, keysArray[0], group2, keysArray[1]);
        
        
        if(keysArray.length > 2) {
            for(let i = 2; i< keysArray.length; i++) {
                let group2 = groups.get(keysArray[i]);
                
                combined = this._combineTwoGroups(combined, undefined, group2, keysArray[i]);
            }   
        }

        return combined;
    };
    
    
    _combineTwoGroups (group1, groupName1, group2, groupName2) {
      
        var combined = [];
        
        for(let groupElement1 of group1) {
            for(let groupElement2 of group2) {

                let combinedElement = {};

                if(!groupName1) {      
                    
                    combinedElement = objectClone.cloneObject(groupElement1);
                    combinedElement[groupName2] = groupElement2;
                    
                } else {
                    
                    combinedElement[groupName1] = groupElement1;
                    combinedElement[groupName2] = groupElement2;
                    
                }

                combined.push(combinedElement);
                
            }
        }
        
        return combined;
       
    };
    
    


    handleFile (file, globalContext, callback, sourceDb) {

        var origHdbti;
        
        var _this = this;
        
        try {
            origHdbti = hdbtiParser.parse(file.content.toString('utf8'));
            file.doNotWriteContent();
            this._retrieveTableColumns(globalContext, file, origHdbti, sourceDb, function(err) {
                if (err) {
                    // ignore, has been handled already
                } else {
                    var newHdbti = _this.generateNewHdbti(globalContext, file, origHdbti);
                    if (newHdbti) {
                        var filename = path.join(
                            globalContext.pathMap["db-dev-object-folder"],
                            utils.getFilepath(file.RunLocation),
                            utils.getObjectname(file.RunLocation) + HDBTABLEDATA_EXT);
                        var newHdbiString = JSON.stringify(newHdbti, null, 4);
                        file.toBeCreated.push({
                                filename: filename,
                                update_content: newHdbiString.replace(/\\\\\\/g, "\\"),
                                file_container: 'db'
                        });
                    }
                }
                callback(null, file);
            });

        }catch (e) {
            var msg = msgs.getMessage(mc.HDI, 5);
            msg.message.push(e.message);
            msg.file = path.join(globalContext.config.directories['xs1-src'], file.RunLocation);
            utils.pushMessage(file, msg);
            callback(null, file);
        }
    }
}

module.exports = new HdbtiHandler();
