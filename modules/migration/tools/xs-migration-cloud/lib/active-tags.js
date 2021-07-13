/**
 * Created by SAP SE.
 */
    
const path = require('path');  
    

class ActiveTags {
    
    
    static convertToPropertiesFile(sourceDb, file, globalContext, callback) {     
        
        sourceDb.getActiveTags(file.packageId, file.simpleName, file.suffix, function (error, activeTags) {
            if(error) {
                return callback(error);
            }
            
            if(activeTags.length < 1) {
                return callback();
            }
            
            var activeTagContent = '';
            
            for(let activeTag of activeTags) {                
                activeTagContent += activeTag.TAG + '=' + activeTag.VALUE + '\n';
            }

            var filename = path.join(globalContext.pathMap["db-dev-object-folder"], file.packagePath, file.simpleName + ".hdbcalculationview.tags");

            var activeTagFile = {
                filename: filename,
                update_content: activeTagContent,
                file_container: 'db'
            };
            
            file.toBeCreated.push(activeTagFile);
            
            return callback();
            
        });
        
    }
    
}

module.exports = ActiveTags;