/**
 * Created by SAP SE.
 */

var SynonymTargetProvider = require('./synonym-target-provider');   
var TargetProviderType = require('./target-provider-type');
var SynonymTargetProviderFactory = require('./synonym-target-provider-factory');


class SynonymTargetProviders {
    
    constructor(generateProviders = false, providerType = TargetProviderType.GRANTOR_SERVICE) {
        this._providers = [];    
        this._defaultProvider = new SynonymTargetProvider({}, true);
        this._generateProviders = generateProviders;
        this._generatedProviderType = providerType;
    }
    
    
    addProvider(provider) {
        this._providers.push(provider);
    }
    
    
    getProvider(usedObject) {
        if(this._providers.length === 0 && !this._generateProviders){
            return this._defaultProvider;
        }


        var matchingProvider = this._providerForObject(usedObject.schemaName, usedObject.object_name);
        if(matchingProvider) {
            return matchingProvider;
        }
        
        matchingProvider = this._providerForSchemaAndPackage(usedObject.schema_name, usedObject.packageId);
        if(matchingProvider) {
            return matchingProvider;
        }

        
        matchingProvider = this._providerForSchemaOrPackage(usedObject.schema_name, usedObject.packageId);
        if(matchingProvider) {
            return matchingProvider;
        }       
        
        
        if(!this._generateProviders)
            return this._defaultProvider;
        
        var newProvider = SynonymTargetProviderFactory.createProvider(usedObject, this._generatedProviderType);
        this.addProvider(newProvider);
        
        return newProvider;
    }
    
    
    _providerForSchemaAndPackage(schemaName, packageId) {
        for(let provider of this._providers) {
           if(provider.suppliesSchemaAndPackage(schemaName, packageId)){
               return provider;
           } 
        }
    }
    
    
    _providerForSchemaOrPackage(schemaName, packageId) {
        for(let provider of this._providers){
            if(provider.suppliesPackage(packageId)) {
                return provider;
            }
            if(provider.suppliesSchema(schemaName)){
                return provider;
            }            
        }
    }
    
    
    _providerForObject(schemaName, objectName) {
        for(let provider of this._providers) {
            if(provider.suppliesObject(schemaName, objectName)) {
                return provider;
            }
        }
    }
    
    
    get grantorServiceNames(){
        var serviceNames = [];         
        
        this.providers.forEach(function (provider) {
            if(provider.getTargetProviderType() === TargetProviderType.GRANTOR_SERVICE && provider.coversObjects()){
                serviceNames.push(provider.grantorServiceName);
            }
        });
            
        return serviceNames;
    }
    
    
    get providers() {
        return this._providers.concat(this._defaultProvider);
    }
    
    
    get synonymMapping() {
        
        var synonymMapping = [];
        
        for(let provider of this.providers) {
            for(let coveredObject of provider.coveredObjects) {
                synonymMapping.push({
                    schemaName: coveredObject.schema_name,
                    objectName: coveredObject.object_name,
                    synonymName: coveredObject.synonymName
                });
            }            
        }
        
        return synonymMapping;
    }


    hasSynonymName(synonymName) {
        for(let provider of this.providers) {
            if(provider.hasSynonymName(synonymName)) {
                return true;
            }    
        }
        
        return false;
    }
    
}


module.exports = SynonymTargetProviders;