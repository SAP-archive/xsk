/**
 * Created by SAP SE.
 */
    
var fs = require('fs');
var path = require('path');
var archiver = require('archiver');

var logUtil = require('./log-util');    
    
    
class ZipArchiver {


    static generateZip(globalContext, callback){

        logUtil.info('Generating zip file');
    
        var zipFilePath = path.join(globalContext.pathMap['xs2-app-root'], globalContext.project.name + '.zip');

        if (process.env.ZIP_OUTPUT_URL) {
            fs.appendFileSync(process.env.CATALINA_HOME + "/" + process.env.ZIP_OUTPUT_URL, zipFilePath + "\n");
        }

        var outputStream = fs.createWriteStream(zipFilePath);
        var zipArchive = archiver.create('zip');
    
        outputStream.on('close', function () {
            return callback();
        });
    
        zipArchive.pipe(outputStream);
    
        if(globalContext.htaMode) {
            ZipArchiver._addHtaContent(globalContext, zipArchive);
        } else {
            ZipArchiver._addDefaultContent(globalContext, zipArchive);
        }      
                
    
        zipArchive.finalize(function (error, bytes) {
            if(error){
                return callback(error);
            }
        });

    }
    
    
    static _addDefaultContent(globalContext, zipArchive) {

        var directories = ['web', 'db', 'xsjs', 'migration'];

        for(let directory of directories) {
            let subpath = path.join(globalContext.pathMap['xs2-app-root'], directory);

            try {
                fs.accessSync(subpath);
                zipArchive.directory(subpath, directory);
            } catch(e){
                //folder does not exist, is not added to zip file   
            }
        }

        //zipArchive.append(fs.createReadStream(path.join(globalContext.pathMap['xs2-app-root'], 'mta.yaml')), { name : 'mta.yaml' });
        zipArchive.append(fs.createReadStream(path.join(globalContext.pathMap['xs2-app-root'], 'report.html')), { name: 'report.html' });

        try{
            fs.accessSync(path.join(globalContext.pathMap['xs2-app-root'], 'xs-security.json'));
            zipArchive.append(fs.createReadStream(path.join(globalContext.pathMap['xs2-app-root'], 'xs-security.json')), { name: 'xs-security.json' });
        } catch (e){
            //file does not exist, is not added to zip file
        }

    }
    
    
    static _addHtaContent(globalContext, zipArchive) {
        
        var dbPath = path.join(globalContext.pathMap['xs2-app-root'], 'db');
        
        try {
            fs.accessSync(dbPath);
            zipArchive.directory(dbPath, '');

        } catch(e){
            //folder does not exist, is not added to zip file   
        }
        
        
    }
    

    

}

module.exports = ZipArchiver;