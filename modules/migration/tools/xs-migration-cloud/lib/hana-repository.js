/**
 * Created by SAP SE on 03.08.16.
 */

var async = require('async');
var RepositoryRequest = require('./hana-repository/repository-request');
var RepositoryResponse = require('./hana-repository/repository-response');
var RepositoryObject = require('./hana-repository/repository-object');
var RepositoryPackage = require('./hana-repository/repository-package');
var logUtil = require('./log-util');
var packageFilter = require('./package-filter');
var ObjectTypeFilter = require('./object-type-filter');

class HanaRepository{

    constructor(hdbClient){
        this.hdbClient = hdbClient;
    }


    getAllDeliveryUnits(callback) {

        var requestMethod = {
            "action": "get",
            "what": "allDeliveryUnits"
        };

        var repositoryRequest = new RepositoryRequest(1, requestMethod);

        var processResponse = function(error, response){
            if(error){
                return callback(error);
            }
            return callback(null, response.content.deliveryUnits);
        };

        this._executeRequest(repositoryRequest, processResponse);
    }

    getDeliveryUnits(deliveryUnits, outerCallback){

        var hanaRepositoryInstance = this;

        async.map(deliveryUnits, function(deliveryUnit, processingFinished){

            hanaRepositoryInstance.getDeliveryUnit(deliveryUnit.name, deliveryUnit.vendor, function(error, information){
                if(error){
                    return processingFinished(error);
                }
                return processingFinished(null, information);
            });
        }, function(error, results){
            if(error){
                return outerCallback(error);
            }
            return outerCallback(null, results);
        });
    }


    getDeliveryUnit(name, vendor, outerCallback){
        // the repository api requires the vendor as a mandatory input. To give the user more flexibility, we treat the
        // vendor name as an optional parameter and try to find the DU manually by iterating over all delivery units.
        // if the vendor is set, we use a shortcut to avoid downloading information for all delivery units

        if(vendor){
            return this._getDeliveryUnit(name, vendor, outerCallback);
        }

        var searchForDeliveryUnit = function(error, deliveryUnits){
            if(error){
                return outerCallback(error);
            }

            async.detect(deliveryUnits, function(deliveryUnit, found){
                if(deliveryUnit.name === name){
                    found(null, true);
                } else{
                    found(null, false);
                }
            }, function(error, foundDeliveryUnit){
                if(foundDeliveryUnit === undefined){
                    return outerCallback('Delivery unit ' + name + ' does not exist');
                }
                return outerCallback(error, foundDeliveryUnit);
            });

        };

        return this.getAllDeliveryUnits(searchForDeliveryUnit);
    }


    _getDeliveryUnit(name, vendor, callback){

        var requestMethod = {
            action: "get",
            what: "deliveryUnit",
            name: name,
            vendor: vendor
        };

        var repositoryRequest = new RepositoryRequest(1, requestMethod);

        var processResponse = function(error, response) {
            if(error){
                return callback(error);
            }
            return callback(null, response.content.deliveryUnit);
        };

        this._executeRequest(repositoryRequest, processResponse);
    }


    getAllFilesForDus(globalContext, deliveryUnits, callback){

        var hanaRepositoryInstance = this;

        async.waterfall([
            function (callback) {
                logUtil.info('Collecting packages...');
                if(deliveryUnits.length > 0){
                    hanaRepositoryInstance._getAllPackagesForDus(deliveryUnits, callback);
                } else if(globalContext['packages']) {
                    hanaRepositoryInstance._getAllPackagesForPackageList(globalContext['packages'], callback);
                } else {
                    callback(null, []);
                }
            },
            function (packages, callback) {
                var filteredPackages = packageFilter.filterPackages(globalContext, packages) || [];
                callback(null, filteredPackages);
            },
            function (packages, callback) {
                logUtil.info('Collecting all objects...');
                hanaRepositoryInstance._getAllObjectsForPackages(packages, callback);
            },
            function (packages, fileList, callback) {

                if(globalContext.objects.length < 1) {
                    return callback(null, packages, fileList);
                }


                logUtil.info('Adding objects from list');

                let objects = globalContext.objects.map(function(object) {

                    let [ pkg, fullname ] = object.split('::');
                    let [ name, suffix ] = fullname.split('.');

                    return {
                        name: name,
                        package: pkg,
                        suffix: suffix
                    }
                });

                hanaRepositoryInstance._convertObjectsToRepositoryObjects(objects, function (error, repositoryObjects) {
                    fileList = fileList.concat(repositoryObjects);

                    return callback(null, packages, fileList);
                });
            },
            function (packages, fileList, callback) {
                let deduped = new Map();

                fileList.forEach(function (file) {
                    deduped.set(file._packageName + '::' + file._name + '::' + file._suffix, file);
                });

                let dedupedList = Array.from(deduped.values());

                return callback(null, packages, dedupedList);
            },
            function(packages, fileList, callback) {

                let filteredList = ObjectTypeFilter.filterObjects(globalContext.includedObjectTypes, globalContext.excludedObjectTypes, fileList);

                callback(null, packages, filteredList);
            }
        ], function(error, packages, fileList){
            if(error){
                logUtil.error('Error while collecting files');
                return callback(error);
            }
            logUtil.info(fileList.length + ' Files collected.');
            logUtil.logProgress(2);
            return callback(null, globalContext, fileList, packages);
        });
    }


    _getAllPackagesForDus(deliveryUnits, callback){
        var hanaRepositoryInstance = this;

        async.map(deliveryUnits, function (deliveryUnit, processingFinished) {
            hanaRepositoryInstance._listPackages(deliveryUnit, function(error, packages){
                if(error){
                    return processingFinished(error);
                }
                return processingFinished(null, packages);
            });

        }, function (error, packages) {
            if(error){
                return callback(error);
            }
            var flatPackages = [].concat.apply([], packages);
            hanaRepositoryInstance._convertPackagesToRepositoryPackages(flatPackages, callback);
        });
    }


    _getAllPackagesForPackageList(packageList, callback){
        var hanaRepositoryInstance = this;
        async.map(packageList, function (packageItem, processingFinished) {
            if(packageItem.subpackages && packageItem.subpackages.toString() == 'true') {
                hanaRepositoryInstance._getAllSubpackages(packageItem, processingFinished);
            } else {
                processingFinished(null, packageItem);
            }

            }, function (error, packages) {
                if(error){
                    return callback(error);
                }
                var flatPackages = [].concat.apply([], packages);
                hanaRepositoryInstance._convertPackagesToRepositoryPackages(flatPackages, callback);
        });
    }


    _getAllSubpackages(packageName, callback){

        var hanaRepositoryInstance = this;
        var results = [];

        this._getSubpackages(packageName, function (error, packageList) {
            if(error){
                return callback(error);
            }
            var pending = packageList.length;
            results.push(packageName);

            if(pending < 1) {
                return callback(null, results);
            }

            packageList.forEach(function (packageName) {
                hanaRepositoryInstance._getAllSubpackages(packageName, function (error, res) {
                    if(error){
                        return callback(error);
                    }
                    results = results.concat(res);
                    if(--pending == 0){
                        callback(null, results);
                    }
                });
            });

        });


    }


    _getSubpackages(packageName, callback) {
        var requestMethod = {
            action: 'get',
            what: 'subpackages',
            'package': packageName.package
        };

        var repositoryRequest = new RepositoryRequest(1, requestMethod);

        var processResponse = function (error, response) {
            if(error){
                return callback(error);
            }
            callback(null, response.content.subpackages);
        };

        this._executeRequest(repositoryRequest, processResponse);
    }



    _convertPackagesToRepositoryPackages(packages, callback){
        //This needs to be done because the repo-api names the package-name as "package" while halm refers to it as "packageName"
        var repositoryPackages = [];

        async.each(packages, function(pkg, callback){
            var repositoryPackage = new RepositoryPackage(pkg);
            repositoryPackages.push(repositoryPackage);
            callback();
        }, function(error){
            if(error){
                return callback(error);
            }
            return callback(null, repositoryPackages);
        });
    }


    _listPackages(du, callback){

        var requestMethod = {
            action: 'list',
            what: 'packages',
            delivery_unit: du.name
        };

        var repositoryRequest = new RepositoryRequest(1, requestMethod);

        var processResponse = function(error, response) {
            if(error){
                return callback(error);
            }

            callback(null, response.content.packages);
        };

        this._executeRequest(repositoryRequest, processResponse);
    }


    _getAllObjectsForPackages(packages, callback){
        var hanaRepositoryInstance = this;

        async.map(packages, function (pkg, processingFinished) {
            hanaRepositoryInstance._listObjects(pkg, function(error, objects){
                if(error){
                    return processingFinished(error);
                }
                return processingFinished(null, objects);
            });

        }, function (error, objects) {
            if(error){
                return callback(error);
            }
            var flatObjects = [].concat.apply([], objects);
            return callback(null, packages, flatObjects);
        });
    }


    _listObjects(pkg, callback){
        var requestMethod = {
            action: 'list',
            what: 'objects',
            'package': pkg.packageName
        };

        logUtil.trace('Starting List objects for package: ' + pkg.packageName);

        var repositoryRequest = new RepositoryRequest(1, requestMethod);
        var hanaRepositoryInstance = this;

        var processResponse = function(error, response) {
            logUtil.trace('List objects finished for package: ' + pkg.packageName + ' #objects: ' + response.content.objects.length);
            if(error){
                return callback(error);
            }
            hanaRepositoryInstance._convertObjectsToRepositoryObjects(response.content.objects, callback);

        };

        this._executeRequest(repositoryRequest, processResponse);
    }


    _convertObjectsToRepositoryObjects(objects, callback){

        var hanaRepositoryInstance = this;

        async.map(objects, function(object, processingFinished){
            logUtil.trace('Converting object to repository object: ' + object.package + '::' + object.name + '.' + object.suffix);
            var repositoryObject = new RepositoryObject(object.name, object.package, object.suffix);
            hanaRepositoryInstance._addLanguageAndContent(repositoryObject, processingFinished);

        }, function(error, repositoryObjects){
            if(error){
                return callback(error);
            }
            return callback(null, repositoryObjects);
        });
    }


    _addLanguageAndContent(repositoryObject, callback){
        var hanaRepositoryInstance = this;

        logUtil.trace('Loading language and content for: ' + repositoryObject.fullName);

        async.parallel({
                originalLanguage: function(callback){
                    hanaRepositoryInstance._getOriginalLanguage(repositoryObject.PackageName.packageName, callback);
                },
                content: function(callback){
                    hanaRepositoryInstance._getFileContent(repositoryObject, callback);
                }
            },
        function (error, results) {
            if(error){
                return callback(error);
            }

            logUtil.trace('Language and content loading finished for: ' + repositoryObject.fullName);

            repositoryObject.originalLanguage = results.originalLanguage;
            repositoryObject.content = results.content;

            return callback(null, repositoryObject);
        })
    }


    _getOriginalLanguage(packageName, callback){

        var requestMethod = {
            action: 'read',
            what: 'package',
            'package': packageName
        };

        var repositoryRequest = new RepositoryRequest(1, requestMethod);

        var processResponse = function(error, response) {
            if(error){
                return callback(error);
            }
            callback(null, response.content.orig_lang);
        };

        this._executeRequest(repositoryRequest, processResponse);

    }


    _getFileContent(repositoryObject, callback){
        var requestMethod = {
            action: "read",
            what: "object",
            object: {
                'package': repositoryObject.PackageName.packageName,
                name: repositoryObject.simpleName,
                suffix: repositoryObject.suffix
            }
        };

        var repositoryRequest = new RepositoryRequest(1, requestMethod);

        var processResponse = function(error, response) {
            if(error){
                return callback(error);
            }
            var fileContent;

            if(response.attachments[0].length == 0 && response.attachments[1].length > 0){
                fileContent = response.attachments[1];
            } else {
                fileContent = response.attachments[0];
            }

            callback(null, fileContent);
        };

        this._executeRequest(repositoryRequest, processResponse);

    }


    _executeRequest(repositoryRequest, outerCallback){

        var sql = 'CALL SYS.REPOSITORY_REST(?, ?)';

        var values = {
            IDATA: repositoryRequest.toBuffer()
        };

        var hanaRepositoryInstance = this;

        async.waterfall([
            function (callback) {
                hanaRepositoryInstance.hdbClient.prepare(sql, callback);
            },
            function (statement, callback) {
                statement.exec(values, function onexec(error, responseBuffer) {
                    logUtil.trace('Repository request executed');
                    statement.drop();
                    if (error === null) {
                        callback(null, responseBuffer);
                    } else {
                        callback(error);
                    }
                });
            }

        ], function (error, responseBuffer) {
            if (error === null) {
                let response = new RepositoryResponse(responseBuffer.ODATA);
                if(response.content['error-code'] && response.content['error-code'] != 0){
                    return outerCallback('' + response.content['error-code'] + ' ' + response.content['error-msg'] + ' ' + response.content['error-arg']);
                }
                return outerCallback(null, response);
            } else {
                return outerCallback(error);
            }
        });

    }

}

module.exports = HanaRepository;
