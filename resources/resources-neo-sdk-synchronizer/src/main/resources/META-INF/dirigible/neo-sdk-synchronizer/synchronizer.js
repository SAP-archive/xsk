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
let zip = require("io/v4/zip");
let files = require("io/v4/files");
let config = require("core/v4/configurations");

const NEO_SDK_FILE_PERMISSION = "rwxr-x---";

exports.synchronize = function() {
    let startDate = new Date();
    let targetLocation = config.get("user.dir") + "/target/dirigible/";
    console.log(`Start coping Neo SDK.`);

    copyNeoSdk(targetLocation);

    let time = (new Date().getTime() - startDate.getTime()) / 1000;
    console.log(`Coping Neo SDK finished for ${time} seconds.`);
};

function copyNeoSdk(targetLocation) {
    let inputStream = null;
    try {
        let neoSdkResourcesPath = getNeoSdkResourcesPath();
        inputStream = files.createInputStream(neoSdkResourcesPath);
        if (inputStream.isValid()) {
            let zipInputStream = null;
            try {
                zipInputStream = zip.createZipInputStream(inputStream);
                let zipEntry = zipInputStream.getNextEntry();
                while (zipEntry.isValid()) {
                    let filePath = targetLocation + getNeoSdkFilePath(zipEntry);
                    if (isNeoSdkFile(zipEntry) && !files.exists(filePath)) {
                        let fileDirectory = filePath.substring(0, filePath.lastIndexOf("/"));
                        try {
                            files.createDirectory(fileDirectory);
                            files.createFile(filePath);
                            files.writeBytesNative(filePath, zipInputStream.readNative());
                            files.setPermissions(filePath, NEO_SDK_FILE_PERMISSION);
                        } catch (e) {
                            // Do nothing
                            // console.error(`Error occured while coping Neo SDK, for file [${filePath}]: ${e}`);
                        }
                    }
                    zipEntry = zipInputStream.getNextEntry();
                }
            } finally {
                if (zipInputStream) {
                    zipInputStream.close();
                }
            }
        } else {
            console.error('Neo SDK Resource content is missing.');
        }
    } finally {
        if (inputStream) {
            inputStream.close();
        }
    }
}

function getNeoSdkResourcesPath() {
    let catalinaHome = config.get("CATALINA_HOME");
    if (!catalinaHome) {
        let userDir = config.get("user.dir");
        catalinaHome = userDir.endsWith("/bin") ? userDir.substring(0, userDir.lastIndexOf("/")) : userDir;
    }
    let libPath = catalinaHome + "/webapps/ROOT/WEB-INF/lib/";
    let neoSdkResourcesPath = files.list(libPath).filter(e => e.contains("xsk-resources-neo-sdk") && !e.contains("xsk-resources-neo-sdk-synchronizer"))[0];
    return neoSdkResourcesPath;
}

function getNeoSdkFilePath(zipEntry) {
    return zipEntry.getName().substring("META-INF/dirigible/".length);
}

function isNeoSdkFile(zipEntry) {
    let filePath = getNeoSdkFilePath(zipEntry);
    return !zipEntry.isDirectory() && filePath != "" && filePath != "F" && filePath != "MANIFEST.MF" && !filePath.startsWith("maven/") && !filePath.startsWith("sap.xsk/");
}
