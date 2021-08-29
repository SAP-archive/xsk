/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2021 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
const RepositoryResponse = require('ide-migration/server/migration/repository/repository-response');
const RepositoryObject = require('ide-migration/server/migration/repository/repository-object');
const RepositoryPackage = require('ide-migration/server/migration/repository/repository-package');

const packageFilter = require('ide-migration/server/migration/repository/package-filter');
const utf8 = org.eclipse.dirigible.api.v3.utils.UTF8Facade;
const Utils = require('ide-migration/server/migration/utils');
const bytesUtils = require("io/v4/bytes");

class HanaRepository{
    
    constructor(hdbClient){
        this.hdbClient = hdbClient;
    }

    getAllDeliveryUnits(callback) {

        let requestMethod = {
            "action": "get",
            "what": "allDeliveryUnits"
        };

        let repositoryRequest = requestMethod;

        let processResponse = function(error, response){
            if(error){
                return callback(error);
            }
            return callback(null, response.content.deliveryUnits);
        };

        this._executeRequest(repositoryRequest, processResponse);
    }

    _getAllPackagesForDu(deliveryUnit, callback){
        let hanaRepositoryInstance = this;

        hanaRepositoryInstance._listPackages(deliveryUnit, function(error, packages){
            if(error){
                return callback(error);
            }
            
            let flatPackages = [].concat.apply([], packages);
            hanaRepositoryInstance._convertPackagesToRepositoryPackages(flatPackages, callback);
        });

    }

    _listPackages(du, callback){

        let requestMethod = {
            action: 'list',
            what: 'packages',
            delivery_unit: du.name
        };

        let repositoryRequest = requestMethod;

        let processResponse = function(error, response) {
            if(error){
                return callback(error);
            }
            callback(null, response.content.packages);
        };

        this._executeRequest(repositoryRequest, processResponse);
    }

    _convertPackagesToRepositoryPackages(packages, callback){
        //This needs to be done because the repo-api names the package-name as "package" while halm refers to it as "packageName"
        let repositoryPackages = [];

        for(let i = 0; i < packages.length; i++) {
            let pkg = packages[i];
            let repositoryPackage = new RepositoryPackage(pkg);
            repositoryPackages.push(repositoryPackage);
        }
        callback(null, repositoryPackages);

    }

    _getAllObjectsForPackages(packages, callback){
        let hanaRepositoryInstance = this;

        let done = 0;
        let result = [];
        for(let i = 0; i < packages.length; i++) {
            const pkg = packages[i];
            hanaRepositoryInstance._listObjects(pkg, function(error, objects){
                
                done++;
                result = result.concat(objects)
                if (done === packages.length) {
                    
                    return callback(null, packages, result);
                }
            });

        }

    }

    _listObjects(pkg, callback){
        let requestMethod = {
            action: 'list',
            what: 'objects',
            'package': pkg.packageName
        };

        let repositoryRequest = requestMethod;
        let hanaRepositoryInstance = this;

        let processResponse = function(error, response) {
            if(error){
                return callback(error);
            }
            
            hanaRepositoryInstance._convertObjectsToRepositoryObjects(response.content.objects, callback);

        };

        this._executeRequest(repositoryRequest, processResponse);
    }

    _convertObjectsToRepositoryObjects(objects, callback){

        let hanaRepositoryInstance = this;

        let repositoryObjects = [];
        for(let i = 0; i < objects.length; i++) {
            const object = objects[i];
            let repositoryObject = new RepositoryObject(object.name, object.package, object.suffix);
            
            hanaRepositoryInstance._addLanguageAndContent(repositoryObject, (err, result) => {
                
                repositoryObjects.push(result);
                
                if (repositoryObjects.length === objects.length) {
                    return callback(null, repositoryObjects);
                }
            });
        }

    }

    _addLanguageAndContent(repositoryObject, callback){
        let hanaRepositoryInstance = this;
        
        hanaRepositoryInstance._getOriginalLanguage(repositoryObject.PackageName.packageName, originalLanguage => {
            hanaRepositoryInstance._getFileContent(repositoryObject, (err, content) => {
                repositoryObject.originalLanguage = originalLanguage;
                repositoryObject.content = content;
                return callback(null, repositoryObject);
            });
        });

    }

    _getOriginalLanguage(packageName, callback){

        let requestMethod = {
            action: 'read',
            what: 'package',
            'package': packageName
        };

        let repositoryRequest = requestMethod;

        let processResponse = function(error, response) {
            if(error){
                return callback(error);
            }
            callback(null, response.content.orig_lang);
        };

        this._executeRequest(repositoryRequest, processResponse);

    }


    _getFileContent(repositoryObject, callback){
        let requestMethod = {
            action: "read",
            what: "object",
            object: {
                'package': repositoryObject.PackageName.packageName,
                name: repositoryObject.simpleName,
                suffix: repositoryObject.suffix
            }
        };

        let repositoryRequest = requestMethod;

        let processResponse = function(error, response) {
            if(error){
                return callback(error);
            }
            let fileContent;

            if(response.attachments[0].length == 0 && response.attachments[1].length > 0){
                fileContent = response.attachments[1];
            } else {
                fileContent = response.attachments[0];
            }

            callback(null, fileContent);
        };

        this._executeRequest(repositoryRequest, processResponse);

    }

    _packagesCollected(packages, fileList, outerCallback) {
        let deduped = new Map();

        fileList.forEach(function (file) {
            deduped.set(file._packageName + '::' + file._name + '::' + file._suffix, file);
        });

        let dedupedList = Array.from(deduped.values());
        outerCallback(null, dedupedList, packages)
    }

    getAllFilesForDu(globalContext, deliveryUnit, callback){

        let hanaRepositoryInstance = this;

        this._getAllPackagesForDu(deliveryUnit, (err, packages) => {
   
            let filteredPackages = packageFilter.filterPackages(globalContext, packages) || [];
     
            hanaRepositoryInstance._getAllObjectsForPackages(filteredPackages, (err, packages, fileList) => {

                
                return this._packagesCollected(packages, fileList, callback);
            });
        });
    }

    _executeRequest(repositoryRequest, outerCallback){
    
        let statement = this.hdbClient.prepareCall("CALL SYS.REPOSITORY_REST(?, ?)");
        let bytes = this._encode(JSON.stringify(repositoryRequest), []);
        statement.setBytes(1, bytesUtils.toJavaBytes(bytes));
        statement.execute();
        let result = bytesUtils.toJavaScriptBytes(statement.getBytes(2), [])

        let response = new RepositoryResponse(result);
        statement.close();
        let error = null;

        if (error === null) {
            if(response.content['error-code'] && response.content['error-code'] != 0){
                return outerCallback('' + response.content['error-code'] + ' ' + response.content['error-msg'] + ' ' + response.content['error-arg']);
            }
            return outerCallback(null, response);
        } else {
            return outerCallback(error);
        }

    }

    _encode(json, files) {
        let finalByteArray = [];
        let byteJSONArray = utf8.encode(json);
        let bytesPointer = 0;
        let repositoryProtocol = utf8.encode("repoV2");
        finalByteArray.push(repositoryProtocol);
        bytesPointer += repositoryProtocol.length;
        let binaryDataLength = null;
        let attachmentSize = 1 + (files.length * 2);
        let byteAttachmentCount = Utils.intToByteArray(attachmentSize);
        finalByteArray.push(byteAttachmentCount);
        bytesPointer += byteAttachmentCount.length;
        let bArrJSONLength = Utils.intToByteArray(byteJSONArray.length);
        finalByteArray.push(bArrJSONLength);
        bytesPointer += bArrJSONLength.length;
        finalByteArray.push(byteJSONArray);
        bytesPointer += byteJSONArray.length;

        // for file in files... {}
        let finalRequestArray = [];
        let byteCount = 0;

        for (let i = 0; i < finalByteArray.length; i++) {
            let byteArray = finalByteArray[i];
            for (let u = 0; u < byteArray.length; u++) {
                finalRequestArray.push(byteArray[u]);
                byteCount += 1;
            }
        }
        return finalRequestArray;

  }

  _decode(respAsByteArr, attachments) {
      
        let json = "";
        let bArrAttachmentCount = [];
        Utils.arrayCopy(respAsByteArr, 6, bArrAttachmentCount, 0, 4);
          
        let attachmentCount = Utils.byteArrayToInt(bArrAttachmentCount);
        
        let bArrJSONLength = [];
        Utils.arrayCopy(respAsByteArr, 10, bArrJSONLength, 0, 4);
        jsonLength = Utils.byteArrayToInt(bArrJSONLength);
        let pointer = 14;

        json = utf8.bytesToString(respAsByteArr, pointer, jsonLength);

        return json;

    }

}

module.exports = HanaRepository;
