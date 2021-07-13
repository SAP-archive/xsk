
var path = require('path');
var fs = require('fs');

var logUtil = require('../log-util');
var objectClone = require('../object-clone');
var mc = require('../message-categories');
var msgs = require('../messages');
var TABLE_MESSAGES = require('../table-messages');
var UsedObject = require('../used-object');
var SynonymGenerator = require('../synonym-generator');
var SqlSecurity = require('../sql-security');
var dbUtils = require('../db-utils');

var PUBLIC_SYNONYM_SCHEMA = "PUBLIC";

const READ_ONLY_SCHEMAS = ['SYS', '_SYS_RT', '_SYS_BIC', '_SYS_REPO', '_SYS_XS'];

function PluginUtils() {

    this.getExtension = function(fullPath) {
        return fullPath.substring(fullPath.lastIndexOf('.')+1);
    };

    this.getFilepath = function(fullPath) {
        return path.dirname(fullPath);
    };

    this.getFilename = function(fullPath) {
        var paths = path.normalize(fullPath).split(path.sep);
        return paths[paths.length-1];
    };
    this.typeOf = function(fileOrPath, extensions) {

        var ext = this.getExtension(fileOrPath);
        for (var i=0; i < extensions.length; i++) {
             if (ext === extensions[i]) {
                return true;
             }
         }
         return false;
    };
    /**
     * return name of object without extension
     */
    this.getObjectname = function(fullPath) {
        var fileName = this.getFilename(fullPath);
        return fileName.substring(0, fileName.lastIndexOf('.'));
    };

    this.pushMessage = function(file, message) {
        if (! ('messages' in file)) {
            file.messages = [];
        }
        file.messages.push(message);
    };

    this.pushMessages = function(file, new_messages) {
        if (! ('messages' in file)) {
            file.messages = [];
        }
        file.messages = file.messages.concat(new_messages);
    };


    this.isNameWithSlashDelimiter = function(name) {

        // names starting with a slash do not qualify
        //
        if (name.charAt(0) === '/') {
            return false;
        }

        var i=0;
        while (i < name.length) {
            if (name.charAt(i) === ':'
                && i < name.length -1
                && name.charAt(i+1) === ':') {
                return false;
            }
            if (name.charAt(i) === '/') {
                return true;
            }
            i++;
        }
        return false;
    };

    this.convertSlashName = function(name) {
        var slashIdx = name.indexOf('/');
        var replacedName = name;

        if(slashIdx > 0) {
            replacedName = name.substring(0, slashIdx) + "::" + name.substring(slashIdx+1, name.length);
        }

        return replacedName;
    };

    /**
     * Rename objects with slash delimiter, e.g.
     * <pre>
     * sap.hba.ecc/MfgProcConvertToBaseUnitOfMeasure/tabletype/it_orig to
     * sap.hba.ecc::MfgProcConvertToBaseUnitOfMeasure/tabletype/it_orig
     * </pre>
     */
    this.getDoubleColonName = function (name) {
        if (this.isNameWithSlashDelimiter(name)) {
            name = this.convertSlashName(name);
        }
        return name;
    };

    this.replaceObjectName = function(replaceArray, obj) {

        if (this.isNameWithSlashDelimiter(obj.OBJECT_NAME) && !(obj.converted )) {
            var newName = this.convertSlashName(obj.OBJECT_NAME);
            replaceArray.push({
                removestart: obj.OBJECT_START_POSITION,
                removeend: obj.OBJECT_END_POSITION-2,
                newString: newName,
            });
            return newName;
        }
        return obj.newString;
    };

    this.checkForTableError = function (globalContext, usedObject, file, newFilename) {
        if (usedObject.schema_name in TABLE_MESSAGES) {

            var tbls = TABLE_MESSAGES[usedObject.schema_name];
            var msgObj = tbls[usedObject.object_name];
            var newMsg = objectClone.cloneObject(msgObj);
            if (msgObj !== undefined) {
                // TODO remove
                if (file === undefined) {
                    logUtil.error("Error: cannot report table error because no file given: " + JSON.stringify(usedObject));
                    return null;
                }
                return {
                    obj: newMsg,
                    schema: usedObject.schema_name,
                    objName: usedObject.object_name,
                    message: [newMsg["message"][0], usedObject.schema_name + "." + usedObject.object_name],
                    file: path.join(globalContext.pathMap["db-relative-path"], newFilename)
                };
            }
        }
    };

    this.removeSchema = function(ddlFile, relatedObjects, globalContext, defaultSchema, file, newFilename, sqlSecurity = SqlSecurity.DEFINER, replaceObjectBySchemaAwareSynonym, usedObjectPrivileges) {
        var replaceArray = [];
        if (defaultSchema === undefined) {
            defaultSchema = "";
        }
        var checkErrors = [];

        var originalReplaceObjectBySchemaAwareSynonym = replaceObjectBySchemaAwareSynonym;

        for (var key in relatedObjects) {
            var obj = relatedObjects[key];
            var migratedObjectName = this.getDoubleColonName(obj.OBJECT_NAME);
            var oldObjectName = obj.OBJECT_NAME;
            var fullQualified = (obj.SCHEMA_NAME != null);
            let schemaName;
            let objectName;

            if(obj.OBJECT_PROPERTY === 'COLUMN_REF' && obj.SCHEMA_NAME == null) {
                replaceObjectBySchemaAwareSynonym = false;
            } else {
                replaceObjectBySchemaAwareSynonym = originalReplaceObjectBySchemaAwareSynonym;
            }

            if(fullQualified && obj.SCHEMA_NAME !== PUBLIC_SYNONYM_SCHEMA) {
                schemaName = obj.SCHEMA_NAME;
                objectName = migratedObjectName;
            } else if(obj.OBJECT_NAME in globalContext.publicSynonyms) {
                schemaName = globalContext.publicSynonyms[obj.OBJECT_NAME].OBJECT_SCHEMA;
                objectName = this.getDoubleColonName(globalContext.publicSynonyms[obj.OBJECT_NAME].OBJECT_NAME);
            } else {
                schemaName = defaultSchema;
                objectName = migratedObjectName;
            }

            if(objectName.endsWith('/proc')) {
               let msg = msgs.getMessage(mc.HDI, 19);
               msg.message.push(objectName);
               this.pushMessage(file, msg);
            }

            let privileges = [dbUtils.getPrivilegeForObjectCharacteristic(obj.OBJECT_PROPERTY)];
            let isFunctionOrProcedure = dbUtils.isFunctionOrProcedure(obj.OBJECT_PROPERTY);

            if(usedObjectPrivileges && !isFunctionOrProcedure && !READ_ONLY_SCHEMAS.includes(schemaName)) {
                privileges = privileges.concat(usedObjectPrivileges)
            }

            let usedObject = new UsedObject(schemaName, objectName, privileges, sqlSecurity);

            usedObject.addUserName(file.fullName + '.' + file.suffix);
            usedObject.oldObjectName = oldObjectName;

            var needsSynonym = SynonymGenerator.needsSynonym(usedObject, globalContext);
            if(!usedObject.isCoverable) {
                file.container = 'todo';
            }

            if(replaceObjectBySchemaAwareSynonym && needsSynonym) {
                this.handleUsedObject(usedObject, globalContext);
            }

            var ret = this.checkForTableError(globalContext, usedObject, file, newFilename);
            if(ret){
                checkErrors.push(ret);
            }

            if (obj.SCHEMA_START_POSITION || obj.OBJECT_START_POSITION ) {

                var startRemove;

                if (fullQualified) {
                    startRemove = obj.SCHEMA_START_POSITION - 1;
                } else {
                    startRemove = obj.OBJECT_START_POSITION - 1;
                }

                var r;

                if (replaceObjectBySchemaAwareSynonym && needsSynonym) {
                    r = {
                        namestart: obj.SCHEMA_START_POSITION,
                        nameend: obj.SCHEMA_END_POSITION,
                        removestart: startRemove,
                        removeend: obj.OBJECT_END_POSITION - 1,
                        newString: JSON.stringify(usedObject.synonymName),
                    };
                    obj.converted = r.newString; // avoid duplicate conversion
                } else {
                    r = {
                        namestart: obj.SCHEMA_START_POSITION,
                        nameend: obj.SCHEMA_END_POSITION,
                        removestart: startRemove,
                        removeend: obj.SCHEMA_END_POSITION,
                        newString: "",
                    };
                }

                if ((fullQualified || needsSynonym && replaceObjectBySchemaAwareSynonym)) {
                    // consistency check
                    var actualContent = ddlFile.substring(r.removestart, r.removeend);
                    if (!/TCUR.{1}/.test(obj.OBJECT_NAME) || (actualContent && new RegExp(obj.OBJECT_NAME, 'i').test(actualContent)) ) {
                        replaceArray.push(r);
                    }
                  
                }

                let name = this.replaceObjectName(replaceArray, obj);
                globalContext.handledObjectNames.push(name);


            }
        }

        //re-org the checkErrors arrays
        var tmp_Map = {};
        for(let err_i = 0; err_i < checkErrors.length; err_i ++){
            let tmp_obj = checkErrors[err_i];
            tmp_Map[tmp_obj.schema + "_" + tmp_obj.objName] = objectClone.cloneObject(checkErrors[err_i]);
        }
        checkErrors = [];
        for(var prop in tmp_Map){
            checkErrors.push(tmp_Map[prop]);
        }
        //report issues for errors in table
        for(let err_i = 0; err_i < checkErrors.length; err_i ++){
            let tmp_obj = checkErrors[err_i];
            var newMsg = tmp_obj.obj;
            newMsg.message = tmp_obj.message;
            newMsg.file = tmp_obj.file;
            this.pushMessage(file, newMsg);
        }

        // TODO if ce_conversion is used add changes to replaceArray
        // TODO generate messages


        // remove entries from back as positions need to be preserved
        replaceArray = replaceArray.sort(function(a, b){return b.removestart - a.removestart});

        replaceArray.forEach(function (ri) {
            var pre = ddlFile.substring(0, ri.removestart);
            var post = ddlFile.substring(ri.removeend);
            ddlFile = pre + ri.newString + post;
        });
        return ddlFile;
    };


    this.handleUsedObjects = function(usedObjects, globalContext) {

        for(let usedObject of usedObjects) {
            this.handleUsedObject(usedObject, globalContext);
        }

    };


    this.handleUsedObject = function(usedObject, globalContext) {
        var needsSynonym = SynonymGenerator.needsSynonym(usedObject, globalContext);
        var provider = globalContext.synonymTargetProviders.getProvider(usedObject);
        var isSysRepoObject = globalContext.sysRepoObjects.has(usedObject.completeName);
        var providerHasSynonym = globalContext.synonymTargetProviders.hasSynonymName(usedObject.object_name);

        if(needsSynonym && !providerHasSynonym) {
            usedObject.synonymName = this.calculateSynonymName(usedObject, provider, globalContext.rootHdiNamespace, isSysRepoObject);
            provider.addCoveredObject(usedObject);
        } else {
            usedObject.synonymName = this.convertSlashName(usedObject.synonymName);
        }

        return usedObject;
    };


    this.calculateSynonymName = function(usedObject, provider, rootNamespace, isSysRepoObject){

        var synonymName = rootNamespace;
        if(provider.hasSynonymSubpath()){
            if(rootNamespace.length > 0) synonymName += '.';
            synonymName += provider.synonymSubpath;
        }


        let subnamespace = this.calculateSubnamespace(rootNamespace, usedObject.packageId);
        if (subnamespace.length > 0 && synonymName.length > 0) {
            synonymName += '.' + subnamespace;
        } else {
            synonymName += subnamespace;
        }

        if(synonymName.length > 0) {
            synonymName += '::';
            if(isSysRepoObject || usedObject.object_name.includes('::')) {
                synonymName += usedObject.shortObjectName;
            } else {
                synonymName += usedObject.object_name;
            }
        } else {
            synonymName = usedObject.synonymName;
        }


        return synonymName;

    };


    this.calculateSubnamespace = function(rootNamespace, subNamespace){

        var rootNamespaceSplitted = rootNamespace.split('.');
        var subNamespaceSplitted = subNamespace.split('.');

        var result = [];

        for(let i = 0; i < subNamespaceSplitted.length; i++) {
            if(subNamespaceSplitted[i] !== rootNamespaceSplitted[i]){
                result = subNamespaceSplitted.slice(i);
                break;
            }
        }
        return result.join('.');
    };


    /**
     * Retrieve java executable in case this is an official distribution
     */
     this.getJavaExecutable = function(globalContext) {

        var isWin = /^win/.test(process.platform);
        var defaultJava = isWin ? "java.exe" : "java";
        var javaRoot = globalContext.config.directories['std-java-dir'];
        var javaBin = path.join(path.dirname(module.filename), "..", "..", javaRoot, "bin", defaultJava);
        try {
            var stats = fs.statSync(javaBin);
            if (stats.isFile()) {
                return javaBin;
            } else {
                return defaultJava;
            }
        } catch (e) {
            return defaultJava;
        }
     };

     this.pushSchemaNameError = function(file, context) {

        var msg = msgs.getMessage(mc.HDI, 4);
        msg.file = path.join(context.config.directories['xs1-src'], file.RunLocation)
        this.pushMessage(file, msg);
     };

     this.pushGetDDLError = function(file, context, schema, error) {

        var msg = msgs.getMessage(mc.INFRASTRUCTURE, 2);
        msg.file = path.join(context.pathMap['db-relative-path'], file.RunLocation)
        msg.message.push(schema);
        msg.message.push(error);
        this.pushMessage(file, msg);
     };


     this.scanFileForString = function(contents, searchStrings, filename) {
        var messages = [];
        var nl = /\r?\n/;
        var lines = contents.split(nl);
        for (var i=0; i < lines.length; i++) {
            var line = lines[i];
            searchStrings.forEach(function(searchString) {
                var pos = line.search(searchString.search);
                if (pos !== -1) {
                    var msg = objectClone.cloneObject(searchString.message);
                    msg.loc = [
                        {
                            "start": {
                                "line": i+1,
                                "column": pos
                            },
                            "end": {
                                "line": i+123,
                                "column": pos+searchString.search.length
                            }
                        }
                    ];
                    msg.file = filename;
                    messages.push(msg);
                }
            });
        }
        return messages;
    };


    this.calculateStoragePath = function(container, key, filename, globalContext){
        if(container !== 'db') {
            return path.join(globalContext.pathMap[key], path.dirname(filename));

        }

        var rootNamespacePath = globalContext.rootHdiNamespace.replace(/\./g, path.sep);
        var fullPath = path.normalize(path.dirname(filename));
        var storePath = fullPath.replace(rootNamespacePath, '');

        var completeStorePath = path.join(globalContext.pathMap[key], storePath);

        return completeStorePath;
    };


    this.calculateNamespace = function(rootNamespace, parentNamespace, namespace) {

        if(!namespace && parentNamespace.length > 0) {
            return parentNamespace;
        }

        var namespaceArray = namespace.split('.');

        var rootNamespaceArray = [];
	if(rootNamespace && rootNamespace.length > 0) {
		rootNamespaceArray = rootNamespace.split('.');
        }

        var rootDelta = [];
        var newNamespace = [];


        for(let i = 0; i < namespaceArray.length; i++) {
            if(namespaceArray[i] !== rootNamespaceArray[i]) {
                rootDelta.push(namespaceArray[i]);
            }
        }


        if(rootDelta.join('.') === namespace) {
            newNamespace = rootNamespaceArray.concat(namespaceArray);
        } else if(rootDelta.length === 0) {
            newNamespace = rootNamespaceArray;
        } else {
            newNamespace = rootNamespaceArray.concat(rootDelta);
        }

        return newNamespace.join('.');
    };


    this.getSchemaName = function(completeReference) {

        const SCHEMA_REGEX = /(?:\s*"(.+?)"\s*|\s*(.+?)\s*)\..+/gi;

        let schema = SCHEMA_REGEX.exec(completeReference);
        SCHEMA_REGEX.lastIndex = 0;

        if(schema) {
            if(schema[1]) {
                return schema[1];
            }
            if(schema[2] && schema[2][0] !== '"') {

                return schema[2];
            }
        }

        return null;

    };


    this.getObjectName = function (completeReference) {

        completeReference = completeReference.replace(/ /g, '');
        const OBJECT_WITH_SCHEMA_REGEX = /(?:.+?|".+?")\.(?:"(.+)"|(.+))/gi;
        const OBJECT_REGEX = /(?:"(.+)"|(.+))/gi;

        let object = OBJECT_WITH_SCHEMA_REGEX.exec(completeReference);
        OBJECT_WITH_SCHEMA_REGEX.lastIndex = 0;
        OBJECT_REGEX.lastIndex = 0;

        if(object) {
            if(object[1]) {
                return object[1];
            }
            if(object[2] && object[2][object[2].length-1] !== '"') {
                return object[2];
            } else {
                return object[0].replace(/"/g, "");
            }
        } else {
            object = OBJECT_REGEX.exec(completeReference);
            if(object[1]) {
                return object[1];
            }
            if(object[2]) {
                return object[2];
            }
        }


        return null;
    };



    this.escapeMultilineContent = function (content) {

        var escapedString;

        if(content.match(/\r?\n/g)) {
            let splitted = content.split(/(?=\r?\n)/g);
            let escaped = [];

            for(let i = 0; i < splitted.length -1; i++) {
                let split = splitted[i] + "\\n\\";
                escaped.push(split);
            }
            escaped.push(splitted[splitted.length-1]);

            escapedString = escaped.join('');
        } else {
            escapedString = content;
        }

        return escapedString;
    };


    this.convertPathToObjectname = function (completePath) {

        if(completePath.substring(0, 2) === './' || completePath.substring(0, 2) === '.\\') {
            completePath = completePath.substring(2);
        }

        var folderPath = this.getFilepath(completePath);
        var filename = this.getFilename(completePath);
        filename = filename.substring(0, filename.lastIndexOf('.'));

        folderPath = folderPath.replace(/\\\\/g, '.');
        folderPath = folderPath.replace(/\/\//g, '.');
        folderPath = folderPath.replace(/\//g, ".");
        folderPath = folderPath.replace(/\\/g, ".");

        if(folderPath.slice(0, 1) === '.') {
            folderPath = folderPath.substring(1);
        }

        if(folderPath.slice(-1) === '.') {
            folderPath = folderPath.substring(0, folderPath.length - 1);
        }

        var objectName = folderPath + "::" + filename;

        if(folderPath.length < 1 || folderPath === '.') {
            objectName = filename;
        }

        return objectName;
    }



}

module.exports = new PluginUtils();
