/*
 * Copyright (c) 2022 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2022 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
let uploader = null;

migrationLaunchView.controller("ImportZippedDU", [
    "$scope",
    "$http",
    "FileUploader",
    "$messageHub",
    "migrationViewState",
    "migrationFlow",
    function ($scope, $http, FileUploader, $messageHub, migrationViewState, migrationFlow) {
        $scope.TRANSPORT_PROJECT_URL = "/services/v4/transport/project";
        $scope.WORKSPACES_URL = "/services/v4/ide/workspaces";
        $scope.zipPaths = [];
        $scope.migrationFinished = false;
        $scope.stepIndex = 1;

        $scope.isVisible = function () {
            return migrationFlow.getCurrentStepIndex() === $scope.stepIndex && migrationFlow.getActiveFlow() === FLOW_TYPE_ZIP;
        }

        let url = $scope.WORKSPACES_URL;
        let noProcessErrorTitle = "Error starting migration process";
        let noProcessErrorDescription = "Migration process initiation failed! Process ID is null.";

        $http.get(url).then(function (response) {
            let workspaceNames = response.data;
            $scope.workspaces = workspaceNames;
            if ($scope.workspaces[0]) {
                $scope.selectedWs = $scope.workspaces[0];
            }
        });
        $scope.projectFromZipPath = function (zipname = "") {
            return $scope.selectedWs + "/" + zipname.split(".").slice(0, -1).join(".");
        };

        $scope.workspacePath = function (zipname = "") {
            return $scope.selectedWs + "/";
        }

        // FILE UPLOADER

        $scope.uploader = uploader = new FileUploader({
            filters: [],
            url: $scope.TRANSPORT_PROJECT_URL,
        });

        // UPLOADER FILTERS

        $scope.uploader.filters.push({
            name: "customFilter",
            fn: function (item /*{File|FileLikeObject}*/, options) {
                return this.queue.length < 100;
            },
        });

        // UPLOADER CALLBACKS

        $scope.uploader.onWhenAddingFileFailed = function (item /*{File|FileLikeObject}*/, filter, options) {
            //        console.info('onWhenAddingFileFailed', item, filter, options);
        };
        $scope.uploader.onAfterAddingFile = function (fileItem) { };
        $scope.uploader.onAfterAddingAll = function (addedFileItems) {
            //        console.info('onAfterAddingAll', addedFileItems);
        };
        $scope.uploader.onBeforeUploadItem = function (item) {
            console.info("onBeforeUploadItem", item);
            item.url = $scope.TRANSPORT_PROJECT_URL + "?path=" + encodeURI($scope.workspacePath(item.file.name));
        };
        $scope.uploader.onProgressItem = function (fileItem, progress) {
            //        console.info('onProgressItem', fileItem, progress);
        };
        $scope.uploader.onProgressAll = function (progress) {
            //        console.info('onProgressAll', progress);
        };
        $scope.uploader.onSuccessItem = function (fileItem, response, status, headers) {
            //        console.info('onSuccessItem', fileItem, response, status, headers);
        };
        $scope.uploader.onErrorItem = function (fileItem, response, status, headers) {
            //        console.info('onErrorItem', fileItem, response, status, headers);
            alert(response.err.message);
        };

        $scope.uploader.onCancelItem = function (fileItem, response, status, headers) {
            //        console.info('onCancelItem', fileItem, response, status, headers);
        };
        $scope.uploader.onCompleteItem = function (fileItem, response, status, headers) {
            $scope.zipPaths.push($scope.projectFromZipPath(fileItem.file.name));
            // console.info('onCompleteItem', fileItem, response, status, headers);
        };

        $scope.startZipMigration = function (ws, uploader) {
            if (!uploader.queue || !uploader.queue.length) return false;
            let zipPaths = [];

            for (const uploadedFile of uploader.queue) {
                const fileName = uploadedFile.file.name;
                zipPaths.push($scope.projectFromZipPath(fileName));
            }

            let body = {
                workspace: ws,
                zipPath: zipPaths,
            };

            migrationFlow.goForward();

            $http
                .post("/services/v4/js/ide-migration/server/migration/api/migration-rest-api.mjs/start-process-from-zip", body, {
                    headers: { "Content-Type": "application/json" },
                })
                .then(
                    function (response) {
                        if (response.data.processInstanceId) {
                            $scope.migrationFinished = true;
                            $messageHub.message("migration.start-zip-migration", {
                                migrationFinished: true,
                                workspace: body.workspace,
                                error: false,
                            });
                        } else {
                            $scope.migrationFinished = false;

                            $messageHub.announceAlertError(noProcessErrorTitle, noProcessErrorDescription);
                            migrationFlow.goBack();

                            $scope.removeAll($scope.uploader);
                        }
                    },
                    function (response) {
                        $scope.migrationFinished = false;
                        $messageHub.announceAlertError("Migration failed", response.error.message);
                        migrationFlow.goBack();
                        $scope.removeAll($scope.uploader);
                    }
                );
        };

        $scope.uploader.onCompleteAll = function () {
            $scope.startZipMigration($scope.selectedWs, $scope.uploader);
        };

        $scope.removeAll = function (uploader) {
            uploader.clearQueue();
            $scope.zipPaths = [];
        };
        $scope.uploadAndMigrate = function (uploader) {
            uploader.uploadAll();
        };
    },
]);
