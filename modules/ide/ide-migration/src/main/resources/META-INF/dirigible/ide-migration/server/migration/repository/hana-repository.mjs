import { bytes as bytesUtils } from "@dirigible/io";
import { RepositoryResponse } from "./repository-response";
import { RepositoryObject } from "./repository-object";
import { RepositoryPackage } from "./repository-package";
import { PackageFilter } from "./package-filter";
import { Utils } from "../utils";

const packageFilter = new PackageFilter();
const utf8 = org.eclipse.dirigible.api.v3.utils.UTF8Facade;

export class HanaRepository {
    constructor(hdbClient) {
        this.hdbClient = hdbClient;
    }

    getAllDeliveryUnits() {
        let requestMethod = {
            action: "get",
            what: "allDeliveryUnits",
        };

        const response = this._executeRequest(requestMethod);
        return response.content.deliveryUnits;
    }

    _getAllPackagesForDu(deliveryUnit) {
        const packages = this._listPackages(deliveryUnit);
        let flatPackages = [].concat.apply([], packages);
        return this._convertPackagesToRepositoryPackages(flatPackages);
    }

    _listPackages(du) {
        let requestMethod = {
            action: "list",
            what: "packages",
            delivery_unit: du.name,
        };

        const response = this._executeRequest(requestMethod);
        return response.content.packages;
    }

    _convertPackagesToRepositoryPackages(packages) {
        //This needs to be done because the repo-api names the package-name as "package" while halm refers to it as "packageName"
        let repositoryPackages = [];

        for (const pkg of packages) {
            let repositoryPackage = new RepositoryPackage(pkg);
            repositoryPackages.push(repositoryPackage);
        }

        return repositoryPackages;
    }

    _getAllObjectsForPackages(packages) {
        let done = 0;
        let result = [];
        for (const pkg of packages) {
            const objects = this._listObjects(pkg);

            done++;
            result = result.concat(objects);
            if (done === packages.length) {
                return {
                    packages: packages,
                    fileList: result,
                };
            }
        }
    }

    _listObjects(pkg) {
        let requestMethod = {
            action: "list",
            what: "objects",
            package: pkg.packageName,
        };

        const response = this._executeRequest(requestMethod);
        return this._convertObjectsToRepositoryObjects(response.content.objects);
    }

    _convertObjectsToRepositoryObjects(objects) {
        let hanaRepositoryInstance = this;
        let repositoryObjects = [];
        for (const object of objects) {
            let repositoryObject = new RepositoryObject(object.name, object.package, object.suffix);

            const result = hanaRepositoryInstance._addLanguage(repositoryObject);
            repositoryObjects.push(result);

            if (repositoryObjects.length === objects.length) {
                return repositoryObjects;
            }
        }
    }

    _addLanguage(repositoryObject) {
        const originalLanguage = this._getOriginalLanguage(repositoryObject.PackageName.packageName);
        repositoryObject.originalLanguage = originalLanguage;
        return repositoryObject;
    }

    _getOriginalLanguage(packageName) {
        let requestMethod = {
            action: "read",
            what: "package",
            package: packageName,
        };

        const response = this._executeRequest(requestMethod);
        return response.content.orig_lang;
    }

    _getFileContent(repositoryObject) {
        let requestMethod = {
            action: "read",
            what: "object",
            object: {
                package: repositoryObject.PackageName.packageName,
                name: repositoryObject.simpleName,
                suffix: repositoryObject.suffix,
            },
        };

        const response = this._executeRequest(requestMethod);
        let fileContent;

        if (response.attachments[0].length == 0 && response.attachments[1].length > 0) {
            fileContent = response.attachments[1];
        } else {
            fileContent = response.attachments[0];
        }

        return fileContent;
    }

    _packagesCollected(packages, fileList) {
        let deduped = new Map();

        fileList.forEach(function (file) {
            // TODO: see why we have 'undefined' files in here
            if (file) {
                deduped.set(file._packageName + "::" + file._name + "::" + file._suffix, file);
            }
        });

        let dedupedList = Array.from(deduped.values());

        return {
            files: dedupedList,
            packages: packages,
        };
    }

    getAllFilesForDu(globalContext, deliveryUnit) {
        const packages = this._getAllPackagesForDu(deliveryUnit);
        if (packages.length === 0) {
            return null;
        }
        let filteredPackages = packageFilter.filterPackages(globalContext, packages) || [];
        const packagesAndFilesListObject = this._getAllObjectsForPackages(filteredPackages);
        return this._packagesCollected(packagesAndFilesListObject.packages, packagesAndFilesListObject.fileList);
    }

    getContentForObject(name, packageName, suffix) {
        let object = new RepositoryObject(name, packageName, suffix);
        return this._getFileContent(object);
    }

    _executeRequest(repositoryRequest) {
        let statement = this.hdbClient.prepareCall("CALL SYS.REPOSITORY_REST(?, ?)");
        let bytes = this._encode(JSON.stringify(repositoryRequest), []);
        statement.setBytes(1, bytesUtils.toJavaBytes(bytes));
        statement.execute();
        let result = bytesUtils.toJavaScriptBytes(statement.getBytes(2), []);

        let response = new RepositoryResponse(result);
        statement.close();

        if (response.content["error-code"] && response.content["error-code"] != 0) {
            throw new Error(
                "" + response.content["error-code"] + " " + response.content["error-msg"] + " " + response.content["error-arg"]
            );
        }
        return response;
    }

    _encode(json, files) {
        let finalByteArray = [];
        let byteJSONArray = utf8.encode(json);
        let bytesPointer = 0;
        let repositoryProtocol = utf8.encode("repoV2");
        finalByteArray.push(repositoryProtocol);
        bytesPointer += repositoryProtocol.length;
        let attachmentSize = 1 + files.length * 2;
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

        for (const byteArray of finalByteArray) {
            for (const innerByteArray of byteArray) {
                finalRequestArray.push(innerByteArray);
                byteCount += 1;
            }
        }

        return finalRequestArray;
    }

    _decode(respAsByteArr) {
        let json = "";
        let bArrAttachmentCount = [];
        Utils.arrayCopy(respAsByteArr, 6, bArrAttachmentCount, 0, 4);

        let bArrJSONLength = [];
        Utils.arrayCopy(respAsByteArr, 10, bArrJSONLength, 0, 4);
        let jsonLength = Utils.byteArrayToInt(bArrJSONLength);
        let pointer = 14;

        json = utf8.bytesToString(respAsByteArr, pointer, jsonLength);

        return json;
    }
}
