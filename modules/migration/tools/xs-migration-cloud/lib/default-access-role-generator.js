/**
 * Created by SAP SE on 07.09.16.
 */

const path = require('path');
const fs = require('fs');
const mkdirp = require('mkdirp');

const MODELER_ROLE_NAME = 'migration_all_analytic_priv.hdbrole';
const DEFAULTS_SUBPATH = 'defaults';
const NAMESPACE_NAME = '.hdinamespace';
const NAMESPACE_CONTENT = {"name": "", "subfolder": "append"};
const DEFAULT_ACCESS_ROLE_NAME = 'default_access_role.hdbrole';
const DEFAULT_ACCESS_ROLE_CONTENT = {"role":{ "name":"default_access_role", "schema_roles":[{"names":[]}], "schema_privileges":[{"privileges":["SELECT", "INSERT", "UPDATE", "DELETE", "EXECUTE", "CREATE TEMPORARY TABLE", "SELECT CDS METADATA"]}]}};
const ANALYTIC_PRIVILEGE_NAME = "migration_all_analytic_priv";


class DefaultAccessRoleGenerator{    
    
    
    static generateArtifacts(globalContext) {
        var modelerRoleExists = this._existsModelerRole(globalContext);
        var databaseRolesExists = this._existsDatabaseRoles(globalContext);
        
        if(modelerRoleExists || databaseRolesExists) {
            this._generateHdiNamespace(globalContext);
            this._generateDefaultAccessRole(globalContext, modelerRoleExists, databaseRolesExists);
        }        
    }
    
    
    static _existsModelerRole(globalContext) {
        
        var modelerRolePath = path.join(globalContext.pathMap["db-full-path-dev-objects"], MODELER_ROLE_NAME);
        
        try{
            fs.accessSync(modelerRolePath);
            return true;
        } catch (e) {
            return false;
        }        
    }
    
    
    static _existsDatabaseRoles(globalContext) {
        
        if(!globalContext.roles){
            return false;
        }
        
        var roleNames = Object.keys(globalContext.roles);
        
        if(roleNames.length > 0){
            return true;
        }
        return false;
        
    }
    
    
    
    static _generateHdiNamespace(globalContext) {
        
        var namespaceFolderPath = path.join(globalContext.pathMap["db-full-path-dev-objects"], DEFAULTS_SUBPATH);
        mkdirp.sync(namespaceFolderPath);
        
        var namespacePath = path.join(namespaceFolderPath, NAMESPACE_NAME);        
        fs.writeFileSync(namespacePath, JSON.stringify(NAMESPACE_CONTENT), 'utf-8');
    }
    
    
    static _generateDefaultAccessRole(globalContext, modelerRoleExists, databaseRolesExists) {
        var defaultAccessRoleFolderPath = path.join(globalContext.pathMap["db-full-path-dev-objects"], DEFAULTS_SUBPATH);
        mkdirp.sync(defaultAccessRoleFolderPath);

        var completeDefaultAccessRole = DEFAULT_ACCESS_ROLE_CONTENT;
        completeDefaultAccessRole.role.schema_roles[0].names = [];
        if(modelerRoleExists) {
            let analyticPrivilegeName = ANALYTIC_PRIVILEGE_NAME;
            if(globalContext.rootHdiNamespace) {
                analyticPrivilegeName = globalContext.rootHdiNamespace + '::' + ANALYTIC_PRIVILEGE_NAME;
            }
            completeDefaultAccessRole.role.schema_roles[0].names.push(analyticPrivilegeName);
        }
        if(databaseRolesExists) {
            let databaseRoleNames = Object.keys(globalContext.roles);
            
            databaseRoleNames.forEach(function (databaseRoleName) {
                completeDefaultAccessRole.role.schema_roles[0].names.push(databaseRoleName);
            });
        }
        
        var defaultAccessRolePath = path.join(defaultAccessRoleFolderPath, DEFAULT_ACCESS_ROLE_NAME);
        fs.writeFileSync(defaultAccessRolePath, JSON.stringify(completeDefaultAccessRole), 'utf-8');
    }
    
}

module.exports = DefaultAccessRoleGenerator;
