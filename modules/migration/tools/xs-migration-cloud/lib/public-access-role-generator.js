/**
 * Created by SAP SE.
 */
    
var path = require('path');
    
var generatorUtils = require('./generator-utils');
const DbUtils = require('./db-utils');
const pluginUtils = require('./plugin_handlers/plugin-utils');


const CONFIG = {
    DEFAULT_SCHEMA: {
        FILE_NAME: 'public_access_schema',
        ROLE_NAME: 'PublicAccessSchema',
        PRIVILEGE_TYPE: 'privileges',
        CONTENT: 'SCHEMA'
    },
    DEFAULT_OBJECTS: {
        FILE_NAME: 'public_access_objects',
        ROLE_NAME: 'PublicAccessObjects',
        PRIVILEGE_TYPE: 'privileges',
        CONTENT: 'OBJECTS'
    },
    GRANTABLE_SCHEMA: {
        FILE_NAME: 'public_access_schema_grantable',
        ROLE_NAME: 'PublicAccessSchema#',
        PRIVILEGE_TYPE: 'privileges_with_grant_option',
        CONTENT: 'SCHEMA'
    },
    GRANTABLE_OBJECTS: {
        FILE_NAME: 'public_access_objects_grantable',
        ROLE_NAME: 'PublicAccessObjects#',
        PRIVILEGE_TYPE: 'privileges_with_grant_option',
        CONTENT: 'OBJECTS'
    }
};


const SUFFIX = {
    true: '.hdbrole',
    false: '.hdbrole.txt'
};


class PublicAccessRoleGenerator {
        
    
    static generate(globalContext) {
        
        for(let configKey in CONFIG) {
            let config = CONFIG[configKey];
            let role = PublicAccessRoleGenerator._createRole(config, globalContext);
            
            let active = config.CONTENT == globalContext.activatePublicAccess;
            PublicAccessRoleGenerator._writeRole(config.FILE_NAME, role, globalContext, active);            
        }                
    }
    
    
    static _createRole(config, globalContext) {
        
        var roleName = '';
        if(globalContext.rootHdiNamespace && globalContext.rootHdiNamespace.length > 0) {
            roleName += globalContext.rootHdiNamespace + '::';
        }
        roleName += config.ROLE_NAME;
        
        var roleContent = {
            role: {
                name: roleName                               
            }
        };
        
        if(config.CONTENT === 'SCHEMA') {
            roleContent.role.schema_privileges = [{}];
            roleContent.role.schema_privileges[0][config.PRIVILEGE_TYPE] = ["SELECT", "EXECUTE"];
        }
        
        if(config.CONTENT === 'OBJECTS') {
            roleContent.role.object_privileges = [];
            globalContext.contextObjects.forEach(function (contextObject) {
                if(contextObject.objectType !== 'SCHEMA' && !pluginUtils.isNameWithSlashDelimiter(contextObject.objectName)){
                    let privilegeType = DbUtils.getPrivilegeForObjectType(contextObject.objectType);
                    let privilege = {
                        name: contextObject.objectName,
                        type: contextObject.objectType                        
                    };
                    privilege[config.PRIVILEGE_TYPE] = [privilegeType.toString()];
                    roleContent.role.object_privileges.push(privilege)    
                }                
            })
        }
        
        return roleContent;
    }
    
    
    static _writeRole(filename, roleContent, globalContext, active) {
        
        filename += SUFFIX[active];
        var filepath =  path.join(globalContext.targetDir, globalContext.config.directories['xs2-app'], globalContext.pathMap.db);

        generatorUtils.writeFileSync(filepath, filename, JSON.stringify(roleContent, null, 4));

    }
    
       
    
}
module.exports = PublicAccessRoleGenerator;