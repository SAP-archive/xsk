/**
 * Created by SAP SE.
 */
    
var SynonymTargetProvider = require('./synonym-target-provider');    
var TargetProviderType = require('./target-provider-type');

class SynonymTargetProviderFactory {
    
    static createProvider(usedObject, providerType){
        
        var providerConfig = {
            schemaName: usedObject.schema_name,
            synonymSubpath: usedObject.schema_name.toLowerCase()            
        };
        
        if(providerType === TargetProviderType.LOGICAL_SCHEMA){
            providerConfig.logicalSchemaName = usedObject.schema_name; 
        } else {
            providerConfig.grantorService = {
                name: usedObject.schema_name.toLowerCase() + '-grantor'
            }
        }
        
        return new SynonymTargetProvider(providerConfig);
    }    
    
}

module.exports = SynonymTargetProviderFactory;