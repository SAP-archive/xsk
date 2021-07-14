/**
 * Created by SAP SE.
 */
    
var ObjectSet = require('./object-set');    
var StringUtil = require('./string-util');

const configurationBaseName = '../config/hdi-default-privileges-';
const configurationExtension = '.json';

class HdiDefaultPrivileges {
    
    constructor(targetVersion) {
        this._defaultPrivileges = new ObjectSet();
        this._loadConfiguration(targetVersion);
    }
    
    
    covers(usedObject) {
        return this._defaultPrivileges.has({serialId: usedObject.privilegeSerialId});
    }
    
        
    _loadConfiguration(version) {
        var configurationPath = configurationBaseName + version.name + configurationExtension;
        var configuration = require(configurationPath);
     
        
        for (let privilege of configuration) {
            
            var hashcode = this._getPrivilegeHashcode(privilege);
            this._defaultPrivileges.add({serialId: hashcode});
            
            if(privilege.IS_GRANTABLE === 'TRUE') {
                
                let privilegeWithoutGrant = {
                    SCHEMA_NAME: privilege.SCHEMA_NAME,
                    OBJECT_NAME: privilege.OBJECT_NAME,
                    PRIVILEGE: privilege.PRIVILEGE,
                    IS_GRANTABLE: 'FALSE'
                };
                
                hashcode = this._getPrivilegeHashcode(privilegeWithoutGrant);
                this._defaultPrivileges.add({serialId: hashcode});                  
            }
        }        
        
    }
    
    
    _getPrivilegeHashcode(privilege) {
        let privilegeString = '';
        if (privilege.SCHEMA_NAME) privilegeString += privilege.SCHEMA_NAME;
        if (privilege.OBJECT_NAME) privilegeString += privilege.OBJECT_NAME;

        privilegeString += privilege.PRIVILEGE + privilege.IS_GRANTABLE;

        return StringUtil.hashCode(privilegeString);

    }
    
    
    
}

module.exports = HdiDefaultPrivileges;