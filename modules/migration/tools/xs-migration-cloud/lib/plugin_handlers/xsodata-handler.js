const utils = require('./plugin-utils');
const path = require('path');
const xsjs = require('../xsjs-generator');
const UsedObject = require('../used-object');
const odataParser = require('./xsodata/lib/generated/peg_xsodata_parser');

class XSOdataHandler {

	
	_getUsedObjects(parsedOdata, fileName, globalContext) {
		let usedObjects = [];
		
		for ( let property in parsedOdata) {
			if (typeof parsedOdata[property] === "object") {				
				usedObjects = usedObjects.concat(this._getUsedObjects(parsedOdata[property], fileName, globalContext));
				
			} else if (parsedOdata[property] instanceof Array) {
			    
				for (let object of parsedOdata[property]) {
					usedObjects = usedObjects.concat(this._getUsedObjects(object, fileName, globalContext));
				}
				
			} else if (property == "table") {
			    
			    if(parsedOdata["table"]) {
			        
			        let objectName = parsedOdata["table"];
			        
                    if(parsedOdata["schema"]) {
                        let usedObject = new UsedObject(parsedOdata["schema"], objectName);
                        usedObject.addUserName(fileName);
                        usedObjects.push(usedObject);
                    } else if(this._isDesignTimeObject(objectName)) {
                        
                        let cleanedName = this._removeExtensions(objectName);
                        let newName = utils.getDoubleColonName(cleanedName); 
                            
                        let usedObject = new UsedObject('_SYS_BIC', newName);
                        usedObject.addUserName(fileName);
                        usedObject.oldObjectName = cleanedName;
                        
                        usedObjects.push(usedObject); 
                    } else if(this._isPublicSynonym(objectName, globalContext)) {
                        let usedObject = this._convertPublicSynonymToUsedObject(objectName, globalContext);
                        
                        usedObject.addUserName(fileName);
                        usedObjects.push(usedObject);
                        
                    }   
                }			    
			} 
		}
		return usedObjects;
	}


	
    _isDesignTimeObject(name) {
        let regex = new RegExp("(^.*\\.analyticview$)|(^.*\\.attributeview$)|(^.*\\.calculationview$)", "gm");
        
        return regex.test(name);
    }
    
	
    _removeExtensions(fileContent){
	    
        let regex = new RegExp("(\\.analyticview)|(\\.attributeview)|(\\.calculationview)", "g");
        fileContent = fileContent.replace(regex, "");
        
        return fileContent;
    }
    
    
    _convertPublicSynonymToUsedObject(objectName, globalContext) {
        
        let publicSynonym = globalContext.publicSynonyms[objectName];
        
        if(publicSynonym) {
            return new UsedObject(publicSynonym.OBJECT_SCHEMA, objectName);            
        }
        
    }

    
    _isPublicSynonym(objectName, globalContext) {
        
        if(globalContext.publicSynonyms[objectName]) return true;
        
        return false;
    }
    
    
    
	_removeSchemaName(usedObject, fileContent) {

        let replacement = usedObject.object_name;
        if(usedObject.synonymName && usedObject.synonymName.length > 0) {
            replacement = usedObject.synonymName;
        }
        
        let regexes;
        
        if(usedObject.oldObjectName) {
            regexes = [{
                regex: new RegExp("\"" + usedObject.oldObjectName + "(\.analyticview|\.attributeview|\.calculationview)"  + "(?=\")", "g"),
                replacement: '"' + replacement
            }];
            
        } else {           
            regexes = [
                {
                    regex: new RegExp("(\'*|\"*)" + usedObject.schema_name + "(\'*|\"*)" + "." + "'" + usedObject.object_name + "(?=')", "g"),
                    replacement : "'" + replacement
                },
                {
                    regex: new RegExp("(\'*|\"*)" + usedObject.schema_name + "(\'*|\"*)" + "." + "(\")" + usedObject.object_name + "(?=\")", "g"),
                    replacement: '"' + replacement
                },
                {
                    regex: new RegExp("(\'*|\"*)" + usedObject.schema_name + "(\'*|\"*)" + "." + usedObject.object_name + "(?= |\\()", "g"),
                    replacement: replacement
                }
            ];
        }

        regexes.forEach(regex => {
            fileContent = fileContent.replace(regex.regex, regex.replacement);    
        });
        
        return fileContent;
	}

	
	handleFile(file, globalContext, callback) {
		
        let content = file.content.toString('utf8');
        
        let parsed = odataParser.parse(content);
        let usedObjects = this._getUsedObjects(parsed, file.RunLocation, globalContext);
            
        
        utils.handleUsedObjects(usedObjects, globalContext);

        for (let usedObject of usedObjects) {
                content = this._removeSchemaName(usedObject, content);
        }
        

        file.toBeCreated.push(
            {
                filename : path.join(xsjs.properties.devObjectFolder, file.RunLocation),
                update_content : content,
                file_container : 'xsjs'
            });

        file.doNotWriteContent();


        callback(null, file);
                
        
	};
	
}

module.exports = new XSOdataHandler();
