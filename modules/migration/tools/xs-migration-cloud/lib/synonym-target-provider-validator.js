/**
 * Created by SAP SE.
 */

var fs = require('fs');
var path = require('path');

var Validator = require('jsonschema').Validator;
    
const WILDCARD_PATTERN = /\*/;


class SynonymTargetProviderValidator{
    
    
    constructor() {
        Validator.prototype.customFormats.packageIdString = this._hasValidPackageId;
        
        this._validator = new Validator();
        this._schemaDefinition = JSON.parse(fs.readFileSync(path.join(__dirname, '..', 'config', 'synonym-target-provider-schema.json'), 'utf-8'));
        
    }
        
    
    validate(config) {               
        var result = this._validator.validate(config, this._schemaDefinition);
        
        if(this._hasObjectsAndPackageId(config)) {            
            result.errors.push({message: 'Configuration does not allow to specify package and objects at the same time.'});
        }

        
        if(this._hasGlobalSchemaNameAndObjectsWithSchemaName(config)) {
            result.errors.push({message: 'Configuration does not allow to specify a schemaName for an object when a global schemaName has been defined.'});
        }
        
        return result;
    }
    
    
    _hasValidPackageId(packageId){
        if(!packageId) return true;
        
        if(packageId && packageId.length > 0) {
            if (!WILDCARD_PATTERN.test(packageId)){
                return true;
            } 
            if (packageId.indexOf('*') === packageId.length - 1) {
                return true;
            }            
        }
        return false;
    }
    
 
    _hasObjectsAndPackageId(config) {
        if(config.objects && config.package) {
            return true;
        }
        
        return false;
    }
    
    
    _hasGlobalSchemaNameAndObjectsWithSchemaName(config) {
        if(config.objects && config.schemaName) {
            
            for(let object of config.objects) {
                if(object.schemaName) {
                    return true;
                }  
            }
        } 
        
        return false;
    }
    
    
    

}

module.exports = new SynonymTargetProviderValidator;