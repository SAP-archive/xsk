// ---------------------------------------------------------------------------
//
// Used object analysis and synonym generator
//
// ---------------------------------------------------------------------------

var path = require('path');
var log = require('./log-util');
var utils = require('./generator-utils');
var underscore = require('underscore');
var TargetProviderType = require('./target-provider-type');
var HdiDefaultPrivileges = require('./hdi-default-privileges');
var messages = require('./messages');
var messageCategories = require('./message-categories');
var informationHandler = require('./information-handler');


const SYNONYM_FILE_EXTENSION = '.hdbsynonym';
const SYNONYMCONFIG_FILE_EXTENSION = '.hdbsynonymconfig';
const GRANTOR_FILE_EXTENSION = '.hdbgrants';
const LOCAL_CALCVIEW_SYNONYMS_FILENAME = 'LocalCalcviewSynonyms';
const LOCAL_PROCEDURE_SYNONYMS_FILENAME = 'LocalProcedureSynonyms';


const NON_COVERABLE_SCHEMAS = ['_SYS_REPO'];
const NON_COVERABLE_OBJECTS = ['SYS.REPOSITORY_REST'];

const NON_COVERABLE_SCHEMAS_BY_DEFAULT = ['_SYS_BIC'];



class SynonymGenerator {

    
    constructor(globalContext) {
        this._hdiDefaultPrivileges = new HdiDefaultPrivileges(globalContext.targetRelease);
    }
    
        
    _generateSynonymsForAllProviders(globalContext){
        
        var configurations = [];
                
        for(let provider of globalContext.synonymTargetProviders.providers) {
            let configuration = this._generateConfigFilesForProvider(provider, globalContext);  
            if(configuration.synonymFiles.length > 0 && !globalContext.hasSynonyms) {
                globalContext.hasSynonyms = true;
            }
            configurations.push(configuration);
        }
        
        if(globalContext.generateSlashSynonyms && globalContext.targetRelease.id >= 22) {
            let localCalcviewSynonyms = this._generateLocalSlashSynonyms(globalContext.calculationViews.values(), LOCAL_CALCVIEW_SYNONYMS_FILENAME, globalContext);
            let localProcedureSynonyms = this._generateLocalSlashSynonyms(globalContext.procedures, LOCAL_PROCEDURE_SYNONYMS_FILENAME, globalContext);
            
            configurations = configurations.concat(localCalcviewSynonyms, localProcedureSynonyms);            
        }

        return configurations;
    }
    
    
    _generateConfigFilesForProvider(provider, globalContext) {

        var allFiles = {};
        
        if(globalContext.noSynonymConfigs && provider.getTargetProviderType() === TargetProviderType.LOGICAL_SCHEMA) {
            allFiles.synonymFiles = this._generateConfigFiles(provider, globalContext, true, SYNONYM_FILE_EXTENSION);
            allFiles.synonymConfigFiles = [];
        } else {
            allFiles.synonymFiles = this._generateConfigFiles(provider, globalContext, false, SYNONYM_FILE_EXTENSION);
            allFiles.synonymConfigFiles = this._generateConfigFiles(provider, globalContext, true, SYNONYMCONFIG_FILE_EXTENSION);
        }
        
        if (provider.getTargetProviderType() === TargetProviderType.GRANTOR_SERVICE && !globalContext.htaMode) {
            allFiles.grantorFile = this._generateGrantor(provider);
        }

        return allFiles;
    }
    
    
    _generateGrantor(provider) {
        
        var grantorConfig = {
            object_owner: {},
            application_user: {}
        };

        if(provider.hasRoles()) {
            var roles = provider.grantorServiceRoles;
            
            
            grantorConfig.object_owner.schema_roles = [{"roles": []}];
            grantorConfig.application_user.schema_roles = [{"roles": []}];
            
            roles.forEach((role) => {
                if(role.endsWith('#')) grantorConfig.object_owner.schema_roles[0].roles.push(role);
                else grantorConfig.application_user.schema_roles[0].roles.push(role);
            });
            
        } else {            
            let grants = this._getObjectGrants(provider);          
            
            grantorConfig.object_owner.object_privileges = grants.objectOwner;
            grantorConfig.application_user.object_privileges = grants.applicationUser;
            
            if(grantorConfig.object_owner.object_privileges.length < 1 && grantorConfig.application_user.object_privileges.length < 1){
                return {};
            }
        }              
        
        var completeConfig = {};
        completeConfig[provider.providerName] = grantorConfig;
        
        var filename = provider.providerName + GRANTOR_FILE_EXTENSION;
        
        return {filename: filename, content: completeConfig};        
    }
    
      
    
    _getObjectGrants(provider) {
        
        var objectOwnerGrants = [];
        var applicationUserGrants = [];
        
        for(let coveredObject of provider.coveredObjects) {
            if(!this._hdiDefaultPrivileges.covers(coveredObject)) {
                let applicationUserPrivilege = {
                    name: coveredObject.object_name,
                    privileges: coveredObject.privileges
                };

                let objectOwnerPrivilege = {
                    name: coveredObject.object_name
                };

                if(provider.isDefaultProvider()){
                    applicationUserPrivilege.schema = coveredObject.schema_name;
                    objectOwnerPrivilege.schema = coveredObject.schema_name;
                }

                if(coveredObject.requiresGrantOption()){
                    objectOwnerPrivilege.privileges_with_grant_option = coveredObject.privileges;
                } else {
                    objectOwnerPrivilege.privileges = coveredObject.privileges;

                }

                objectOwnerGrants.push(objectOwnerPrivilege);
                applicationUserGrants.push(applicationUserPrivilege);
            } 
        }

        return {objectOwner: objectOwnerGrants, applicationUser: applicationUserGrants};
    }
    
    
    _generateConfigFiles(provider, globalContext, includeTarget, fileExtension) {
        var synonymFiles = [];
        
        var coveredObjects = provider.coveredObjects;
        var coveredObjectsGroups = {};
        
        for(let coveredObject of coveredObjects){
            if(!coveredObjectsGroups[coveredObject.synonymPackageId]) coveredObjectsGroups[coveredObject.synonymPackageId] = [];
            coveredObjectsGroups[coveredObject.synonymPackageId].push(coveredObject);
        }
        
        for(let coveredObjectGroupName in coveredObjectsGroups) {
            let coveredObjectGroup = coveredObjectsGroups[coveredObjectGroupName];
            var newSynonymFile = {filename: this._generateFilename(globalContext, coveredObjectGroupName, provider.synonymSubpath, provider.providerName, fileExtension), content : {}};
            
            for(let coveredObject of coveredObjectGroup) {
                let coveredObjectConfig = {};
                if(includeTarget) {
                    coveredObjectConfig.target = {};
                    if(provider.getTargetProviderType() === TargetProviderType.GRANTOR_SERVICE && !provider.isDefaultProvider()) {
                        coveredObjectConfig.target["*.configure"] = provider.providerName;
                    } else if(provider.isDefaultProvider()) {
                        coveredObjectConfig.target["schema"] = coveredObject.schema_name;
                    } else if(provider.getTargetProviderType() === TargetProviderType.LOGICAL_SCHEMA) {
                        coveredObjectConfig.target["logical_schema"] = provider.providerName;

                    } 
                    else {
                        coveredObjectConfig.target["schema"] = provider.providerName;
                    }
                    coveredObjectConfig.target.object = coveredObject.object_name;
                }
                newSynonymFile.content[coveredObject.synonymName] = coveredObjectConfig;
            }
            synonymFiles.push(newSynonymFile);
        }
        return synonymFiles;
    }


    _generateLocalSlashSynonyms(objects, filename, globalContext) {

        var synonymFiles = [];
        var synonymConfigFiles = [];
        
        var objectGroups = new Map();
        for(let object of objects){
            if(!objectGroups.has(object.packageId)){
                objectGroups.set(object.packageId, []);
            }
            objectGroups.get(object.packageId).push(object);
        }
        for(let objectGroupName of objectGroups.keys()) {
            let objectGroup = objectGroups.get(objectGroupName);
            let synonymFile = {filename: this._generateFilename(globalContext, objectGroupName, '', filename, '.hdbsynonym'), content: {}};
            let synonymConfigFile = {filename: this._generateFilename(globalContext, objectGroupName, '', filename, '.hdbsynonymconfig'), content: {}};

            for (let calcView of objectGroup) {
                if(globalContext.noSynonymConfigs) {
                    synonymFile.content[calcView.privilegeObjectName] = {target: {object: calcView.fullName}};
                } else {
                    synonymFile.content[calcView.privilegeObjectName] = {};
                    synonymConfigFile.content[calcView.privilegeObjectName] = {target: {object: calcView.fullName}};    
                }                
            }
            synonymFiles.push(synonymFile);
            
            if(!globalContext.noSynonymConfigs) {
                synonymConfigFiles.push(synonymConfigFile);    
            }            
        }
                
        
        return {synonymFiles: synonymFiles, synonymConfigFiles: synonymConfigFiles};

    }
    
    
    _generateFilename(globalContext, packageId, synonymSubpath, providerName, fileExtension) {
        
        var filename = '';
                
        var subpath = '';
        if(globalContext.rootHdiNamespace.length > 0){
            subpath = packageId.replace(globalContext.rootHdiNamespace, '');
            subpath = subpath.replace(/\./g, '/');
        } else if(synonymSubpath && synonymSubpath.length > 0){
            subpath = '/' + synonymSubpath;
        }

        filename = path.join(filename, subpath, providerName + fileExtension);
        
       
        return filename;
    }
    
    
    _writeAllConfigFiles(configurations, globalContext) {
        
        for(let configuration of configurations) {
            if(configuration.synonymFiles.length > 0) {
                this._writeConfigFiles(configuration.synonymFiles, 'dev', globalContext);
            }
            if(configuration.synonymConfigFiles.length > 0) {
                this._writeConfigFiles(configuration.synonymConfigFiles, 'cfg', globalContext);
            }
            if(!underscore.isEmpty(configuration.grantorFile)) {
                this._writeConfigFiles(configuration.grantorFile, 'cfg', globalContext);
            }
            
        }        
        
    }
    
    
    _writeConfigFiles(configFiles, subDirectory, globalContext){
    
        var pathMapKey = 'db-full-path-' + subDirectory + '-objects';
        var filepath = globalContext.pathMap[pathMapKey];
        
        if(!underscore.isArray(configFiles)){
           this._writeConfigFile(configFiles, filepath);           
        } else {
            for(let configFile of configFiles) {
                this._writeConfigFile(configFile, filepath);
            }
        }
    }
    
    
    _writeConfigFile(configFile, filepath) {
        log.info('Writing ' + filepath +  configFile.filename);
        
        filepath = path.join(filepath, path.dirname(configFile.filename));
        utils.writeFileSync(filepath, path.basename(configFile.filename), JSON.stringify(configFile.content, null, 4));
    }
    
    
    
    _dbContainerExists(context) {
        return context.containersPresent.indexOf('db') > -1;
    }
    
    
    generateSynonyms (context, list, packages, callback) {

        if (!this._dbContainerExists(context)) {
            callback();
        } else {
            log.info('Generating Synonyms for database artifacts');
            var configurations = this._generateSynonymsForAllProviders(context);
            this._writeAllConfigFiles(configurations, context);
            callback();
        }
    };
    
    static needsSynonym (usedObject, globalContext) {
        
        var runtimeObject;
        
        if((runtimeObject = SynonymGenerator._isInContext(usedObject.runtimeObjects, globalContext)) !== false) {
            if(runtimeObject.synonymName){
                usedObject.synonymName = runtimeObject.synonymName;
            }
            return false;
        }
        else {
                       
            let isCoverable = SynonymGenerator._isCoverable(usedObject);
            let isCoverableByDefault = SynonymGenerator._isCoverableByDefault(usedObject);
            
            if(isCoverable) {
                let provider = globalContext.synonymTargetProviders.getProvider(usedObject);
                if(provider.isDefaultProvider() && isCoverableByDefault) {
                    return true;
                } 
                if(!provider.isDefaultProvider()) {
                    return true;
                }
            }
                        
            
            usedObject.isCoverable = false;

            for(let user of usedObject.userNames) {
                let message = messages.getMessage(messageCategories.HDI, 24);
                message.message.push(usedObject.completeName);
                message.file = user;
                informationHandler.logMessage(message);
            }
            
            globalContext.nonCoverableObjects.add(usedObject);
            
            return false;
        }
    };
    
    
    static _isInContext(runtimeObjects, globalContext) {
        for(let contextObject of globalContext.contextObjects){
            for(let runtimeObject of runtimeObjects){
                if(contextObject.equals(runtimeObject)) {
                    return contextObject;
                }   
            }            
        }        
        return false;
    }
    
    
    
    static _isCoverable(usedObject) {
        if(NON_COVERABLE_SCHEMAS.includes(usedObject.schema_name)){
            return false;
        }
        
        if(NON_COVERABLE_OBJECTS.includes(usedObject.completeName)) {
            return false;
        }        
        
        return true;
    }
    
        
    static _isCoverableByDefault(usedObject) {
        
        var isCoverable = SynonymGenerator._isCoverable(usedObject);
        var isCoverableByDefault = !NON_COVERABLE_SCHEMAS_BY_DEFAULT.includes(usedObject.schema_name);
        
        if(isCoverable && isCoverableByDefault) {
            return true;
        }
        
        return false;
    }
    
    
}

module.exports = SynonymGenerator;
