// --------------------------------------------------------------------------
//
// Plugin handler for hdbtextbundle files
//
// --------------------------------------------------------------------------

var path = require('path');

var utils = require('./plugin-utils');
var mc = require('../message-categories');
var msgs = require('../messages');


function HdbtextbundleHandler() {

   
    this.parseTranslationReply = function(entries) {

        var translations = {};
        entries.forEach(function (e) {
            var l = e.lang;
            if (!(l in translations)) {
                translations[l] = {};
            }
            var le = translations[l];
            
            var escapedContent = utils.escapeMultilineContent(e.content);
                        
            le[e.text_id] = {
                type: e.text_type,
                maxLength: e.max_length,
                text: escapedContent
            };            
        });
        return translations;
    };

    
    this.toPropertiesFile = function(entries) {

        var content = "";
        for(var id in entries) {
            var e = entries[id];
            if (e.type) {
                content += "#" + e.type + "," + e.maxLength + "\n";
            }
            content += id + "=" + e.text + "\n";
        }
        return content;
    };
    
    
    this.hasReferences = function (filePath, globalContext) {
        filePath = filePath.substring(1);

        for(let textbundleReference of globalContext.textbundleReferences){
            if(filePath === textbundleReference.textbundleFile){
                return textbundleReference.jsFile;
            }
        }

        return null;
    };
    

    this.handleFile = function (file, globalContext, callback, sourceDb) {

        var that = this;
        var name = file.RunLocation;
        var pkg = file.PackageName.packageName;
        var pathName = utils.getFilepath(name);
        var objectName = utils.getObjectname(name);
        var ext = utils.getExtension(name);
        var translationFilenames = [];
        
        var devObjectFolder;
        var relativeContainerPath;
        var targetContainer;
        var referencingFile;
        
        if((referencingFile = this.hasReferences(file.RunLocation, globalContext)) !== null) {
            devObjectFolder = globalContext.pathMap["web-dev-object-folder"];
            relativeContainerPath = globalContext.pathMap["web-relative-path"];
            targetContainer = 'web';
        } else {
            devObjectFolder = globalContext.pathMap["todo-dev-object-folder"];
            relativeContainerPath = globalContext.pathMap["todo-relative-path"];
            targetContainer = 'todo';
        }        

        
        var originalLanguageFilename = path.join(devObjectFolder, pathName, objectName + ".properties");
        translationFilenames.push(originalLanguageFilename);

        // save original language file as .properties file
        //
        file.toBeCreated.push({
            filename: originalLanguageFilename,
            update_content: file.content,
            file_container: targetContainer
        });

        sourceDb.getTranslationsForTextBundle(pkg, objectName, ext, function(err, entries) {
            if (err) {
                return callback(err);
            } else {
                if (entries.length !== 0) {
                    var parsedTranslation = that.parseTranslationReply(entries);
                    
                    for(var lang in parsedTranslation) {
                        var txName = path.join(devObjectFolder, pathName, objectName + "_" + lang + ".properties");
                        
                        file.toBeCreated.push({
                            filename: txName,
                            update_content: that.toPropertiesFile(parsedTranslation[lang]),
                            file_container: targetContainer
                        });
                        
                        translationFilenames.push(txName);
                    }
                }
            }

            file.doNotWriteContent();
            var filenameString = "";
            translationFilenames.forEach(function(f) {
                filenameString += (filenameString.length === 0) ? "" : " ";
                filenameString += f;
            });
            
            var msg;
            if(targetContainer === 'todo'){
                msg = msgs.getMessage(mc.TRANSLATION, 4);
                msg.message.push(filenameString);

            } else {
                msg = msgs.getMessage(mc.TRANSLATION, 7);
                msg.message.push(filenameString);
                msg.message.push(referencingFile);
            }
            
            msg.file = path.join(relativeContainerPath, pathName, objectName + ".properties");
            utils.pushMessage(file, msg);
            
            callback(null, file);
        });
    }
}

module.exports = new HdbtextbundleHandler();
