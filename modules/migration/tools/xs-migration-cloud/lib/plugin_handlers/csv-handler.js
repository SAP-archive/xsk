/**
 * Created by SAP SE.
 */

class CsvHandler {
    
 
    static handleFile(csvFile, globalContext, callback) {
        
        var completeCsvFilename = csvFile.fullName + '.csv';
        
        if(globalContext.referencedCsvFiles.withHeader.includes(completeCsvFilename)){
         
            let originalContent = csvFile.content.toString('utf-8');
            let originalContentSplitted = originalContent.split(/\n((.|[\r\n])+)/);            
            
            let newContent = '';
            
            if(originalContentSplitted.length > 0) {
                
                let headerLine = originalContentSplitted[0];
                headerLine = headerLine.replace(/\s/g, '');
                
                newContent += headerLine;
            }
            
            if(originalContentSplitted.length > 1) {
                newContent += '\n' + originalContentSplitted[1];
            }
            
            csvFile.content = newContent;
            
        }
        
        callback(null, csvFile);
        
    }
    
    
}

module.exports = CsvHandler;