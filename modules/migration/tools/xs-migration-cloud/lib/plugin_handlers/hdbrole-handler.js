/**
 * Created by SAP SE.
 */
    
var async = require('async');
var path = require('path');


var logUtil = require('../log-util');
var infoHandler = require('../information-handler');
var generatorUtils = require('../generator-utils');
var pluginUtils = require('./plugin-utils');

    
class HdbroleHandler {


    handleFile(hdbroleFile, globalContext, handlerCallback, sourceDb, sqlParser, pluginHandler) {

        logUtil.trace("handleFile file: " + hdbroleFile.RunLocation);

        if(!globalContext.roles){
            globalContext.roles = {};
        }
        
        globalContext.roles[hdbroleFile.fullName] = hdbroleFile;
        
        hdbroleFile.doNotWriteContent();
        
        handlerCallback(null, hdbroleFile);
    }
    
    
    

    postcalculation(globalContext, sourceDb, postcalculationCallback) {
        
        if(!globalContext.roles){
            return postcalculationCallback();
        }
        
        var hdbroleHandlerInstance = this;
        
        async.mapValues(globalContext.roles, function(hdbroleFile, roleName, processingFinished){
           
            hdbroleHandlerInstance.handleRoleFile(hdbroleFile, globalContext, sourceDb, processingFinished);            
            
        }, function(error, results){
            if(error){
                return postcalculationCallback(error);
            }
            
            hdbroleHandlerInstance._writeRoleFiles(globalContext, results, postcalculationCallback);
        });
        
    }
    
    
    handleRoleFile(hdbroleFile, globalContext, sourceDb, handlerCallback) {
    
        var newRole = {
            role: {
                name : hdbroleFile.fullName
            }
        };
        
        var roleHandlerInstance = this;
        
        async.parallel({
            globalAndSchemaRoles: function (callback) {
                sourceDb.getGlobalAndSchemaRoles(hdbroleFile.fullName, globalContext, callback);
            },
            systemPrivileges: function (callback) {
                sourceDb.getSystemPrivileges(hdbroleFile.fullName, callback);
            },
            schemaPrivileges: function (callback) {
                sourceDb.getSchemaPrivileges(hdbroleFile.fullName, globalContext, callback);
            },
            objectPrivileges: function (callback) {
                sourceDb.getObjectPrivileges(hdbroleFile.fullName, globalContext, callback);
            },
            schemaAnalyticPrivileges: function (callback) {
                sourceDb.getSchemaAnalyticPrivileges(hdbroleFile.fullName, globalContext, callback);
            }
        }, function (error, results) {
            if(error){
                return handlerCallback(error);
            }
            
            if(results.globalAndSchemaRoles.global_roles.length > 0){
                newRole.role.global_roles = results.globalAndSchemaRoles.global_roles;
            }
            
            if(results.globalAndSchemaRoles.schema_roles[0].names.length > 0){
                newRole.role.schema_roles = [];
                newRole.role.schema_roles.push(results.globalAndSchemaRoles.schema_roles[0]);
            }
            
            if(results.systemPrivileges.length > 0){
                newRole.role.system_privileges = results.systemPrivileges;
            }
            
            if(results.schemaPrivileges.length > 0){
                newRole.role.schema_privileges = results.schemaPrivileges;
            }
            
            if(results.objectPrivileges.length > 0){
                newRole.role.object_privileges = results.objectPrivileges;
            }
            if(results.schemaAnalyticPrivileges.privileges.privileges.length > 0){
                newRole.role.schema_analytic_privileges = [];
                newRole.role.schema_analytic_privileges.push(results.schemaAnalyticPrivileges.privileges);
            }
            
            results.schemaAnalyticPrivileges.messages.forEach(function (message) {
                message.file = path.join(globalContext.config.directories['xs1-src'], hdbroleFile.RunLocation);
                infoHandler.logMessage(message);
            });
            
            var roleConfig;
            if(newRole.role.schema_privileges) {
                roleConfig = roleHandlerInstance._generateRoleConfig(newRole.role.schema_privileges, newRole.role.name);
            }
            
           
            var newFile = {
                update_content: JSON.stringify(newRole, null, 4),
                filename: hdbroleFile.Name,
                packagePath: hdbroleFile.packagePath,
                roleName: hdbroleFile.fullName,
                simpleName: hdbroleFile.simpleName
            };
            
            var newFiles = {
                roleFile: newFile,
                roleConfig: roleConfig
            };
            
            handlerCallback(null, newFiles);
        });
        
    }
    
    
    _writeRoleFiles(globalContext, roleList, methodCallback){
        
        var hdbroleHandler = this;
        
        async.eachOf(roleList, function (newRoleFile, roleName, writtingFinished) {
            hdbroleHandler._writeRoleAndConfigFile(globalContext, newRoleFile, writtingFinished);
        }, function (error) {
           return methodCallback(error); 
        });        
        
    }
    
    
    _writeRoleAndConfigFile(globalContext, role, methodCallback){
        var filePath = role.roleFile.packagePath + '/.dummy';

        async.waterfall([function (callback) {
            var storagePath = pluginUtils.calculateStoragePath('db', 'db-full-path-dev-objects', filePath, globalContext);
            generatorUtils.writeFile(storagePath, role.roleFile.filename, role.roleFile.update_content, callback);
        }, function (callback) {
            if(role.roleConfig && Object.getOwnPropertyNames(role.roleConfig[role.roleFile.roleName]).length > 0){
                var storagePath = pluginUtils.calculateStoragePath('db', 'db-full-path-cfg-objects', filePath, globalContext);               
                generatorUtils.writeFile(storagePath, role.roleFile.simpleName + '.hdbroleconfig', JSON.stringify(role.roleConfig, null, 4), callback);
            } else {
                callback();
            }
        }], function (error) {
            return methodCallback(error);
        });
        
    }
    
    
    _generateRoleConfig(schemaPrivileges, roleName){
        
        var roleConfig = {};
        roleConfig[roleName] = {};
        
        schemaPrivileges.forEach(function (schemaPrivilege) {
            if(schemaPrivilege.reference) {
                roleConfig[roleName][schemaPrivilege.reference] = {"schema": schemaPrivilege.reference};
            }
        });
        
        return roleConfig;
        
    }
    
    
    
}

module.exports = new HdbroleHandler();