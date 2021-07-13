/**
 * Created by SAP SE.
 */
    
var globToRegExp = require('glob-to-regexp');
var TargetProviderType = require('./target-provider-type');    
var ObjectSet = require('./object-set');

var DEFAULT_PROVIDER_SERVICE_NAME = 'synonym-grantor-service';

   
class SynonymTargetProvider {
    
    constructor(grantorServiceMapping, isDefaultProvider = false) {
        this._isDefaultProvider = isDefaultProvider;
        if(grantorServiceMapping) {
            if(grantorServiceMapping.package) {
                this._packageId = globToRegExp(grantorServiceMapping.package.id);
                this._shortPackageId = grantorServiceMapping.package.id.replace('.*', '');
                this._includeTopLevelObjects = grantorServiceMapping.package.includeTopLevelObjects;
            }
            this._schemaName = grantorServiceMapping.schemaName;
            this._synonymSubpath = grantorServiceMapping.synonymSubpath;
            if(grantorServiceMapping.grantorService) {
                this._grantorServiceName = grantorServiceMapping.grantorService.name;
                this._grantorServiceRoles = grantorServiceMapping.grantorService.roles;
            }
            if(isDefaultProvider){
                this._grantorServiceName = DEFAULT_PROVIDER_SERVICE_NAME;
            }
            if(grantorServiceMapping.logicalSchemaName) {
                this._logicalSchemaName = grantorServiceMapping.logicalSchemaName;
            }
            if(grantorServiceMapping.objects) {
                this._objects = grantorServiceMapping.objects;
            }
        }
       
        this._coveredObjects = new ObjectSet();
        this._coveredSynonyms = new Set();
    }  
    
    
    getTargetProviderType() {
        if(this._logicalSchemaName) {
            return TargetProviderType.LOGICAL_SCHEMA;
        }
        return TargetProviderType.GRANTOR_SERVICE;
    }
    
    
    suppliesSchema(schemaName) {
        
        if(this._packageId) {
            return false;
        }
        
        return this._suppliesSchema(schemaName);
    }
    
    
    _suppliesSchema(schemaName) {
        
        if(this._objects) {
            return false;
        }
        
        if(this._schemaName && this._schemaName.toLowerCase() === schemaName.toLowerCase()) {
            return true;
        }
        return false;
    }
    
    
    suppliesPackage(packageId) {
     
        if(this._schemaName) {
            return false;
        }
        
        return this._suppliesPackage(packageId);
        
    }
    
    
    _suppliesPackage(packageId) {
        
        if(!this._packageId) {
            return false;
        }
        if(this._packageId.test(packageId)) {
            return true;
        }
        if(this._includeTopLevelObjects) {
            return this._shortPackageId === packageId;
        }
        return false;
    }
    
    
    suppliesObject(schemaName, objectName) {
        
        if(!this._objects) return false;
        
        for(let object of this._objects) {
            if(object.objectName === objectName && (object.schemaName === schemaName || this._schemaName === schemaName)) {
                return true;
            }
        }
        
        return false;
        
    }    
    
    
    suppliesSchemaAndPackage(schemaName, packageId) {
        if(this._suppliesSchema(schemaName) && this._suppliesPackage(packageId)) {
            return true;
        }
        return false;
    }
    
        
    addCoveredObject(usedObject) {
        if(!this._coveredObjects.has(usedObject) && !this._coveredSynonyms.has(usedObject.synonymName)) {
            this._coveredObjects.add(usedObject);    
            this._coveredSynonyms.add(usedObject.synonymName);
        } else {
            let existingObject = this._coveredObjects.get(usedObject);
            if(existingObject) {
                existingObject.merge(usedObject);    
            }            
        }            
    }
    
    
    hasSynonymSubpath(){
        if(this._synonymSubpath && this.synonymSubpath.length > 0){
            return true;
        } 
        return false;
    }
    
    
    get synonymSubpath() {
        return this._synonymSubpath;
    }
    
    
    get shortPackageId() {
        return this._shortPackageId;
    }
    
    
    isDefaultProvider() {
        return this._isDefaultProvider;
    }
    
    get grantorServiceName() {
        return this._grantorServiceName;
    }
    
    get coveredObjects() {
        return this._coveredObjects.values();
    }
    
    get providerName() {
        if(this._logicalSchemaName) return this._logicalSchemaName;
        if(this._grantorServiceName) return this._grantorServiceName;
    }
    
    
    get grantorServiceRoles() {
        return this._grantorServiceRoles;
    }
    
    
    hasRoles() {
        return this._grantorServiceRoles && this._grantorServiceRoles.length > 0;
    }
    
    
    coversObjects() {
        return this._coveredObjects.size > 0;
    }
    
    
    hasSynonymName(synonymName) {
        return this._coveredSynonyms.has(synonymName);
    }
    
}

module.exports = SynonymTargetProvider;