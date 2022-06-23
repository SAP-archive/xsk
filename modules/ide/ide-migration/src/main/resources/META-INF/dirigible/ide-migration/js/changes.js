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
require.config({ paths: { vs: "/webjars/monaco-editor/0.33.0/min/vs" } });
let editors = [];
migrationLaunchView.controller("ChangesViewController", [
    "$scope",
    "$http",
    "$messageHub",
    "$timeout",
    "migrationDataState",
    "migrationViewState",
    function ($scope, $http, $messageHub, $timeout, migrationDataState, migrationViewState) {
        $scope.migrationDataState = migrationDataState;
        $scope.dataLoaded = function () {
            return !migrationViewState.getIsDataLoading();
        }
        let viewWidth = document.querySelectorAll(".changes-body")[0].clientWidth | 100;
        $scope.isDiffViewSplit = viewWidth > 1100 ? true : false;
        $scope.data = [];

        function getDiffData() {

            $http
                .post(
                    "/services/v4/js/ide-migration/server/migration/api/migration-rest-api.mjs/get-process",
                    JSON.stringify({ processInstanceId: migrationDataState.processInstanceId }),
                    {
                        headers: { "Content-Type": "application/json" },
                    }
                ).then(

                    function (response) {
                        let diffViewData = response.data.diffViewData;
                        handleDiffViewData(diffViewData);
                    },
                    function (response) {
                        handleResponseError(response);
                    }
                );

        }

        function continueMigration() {
            let body = {
                neo: {
                    hostName: migrationDataState.neoHostName,
                    subaccount: migrationDataState.neoSubaccount,
                },
                hana: {
                    databaseSchema: migrationDataState.schemaName,
                    username: migrationDataState.dbUsername,
                    password: migrationDataState.dbPassword,
                },
                connectionId: migrationDataState.connectionId,
                workspace: migrationDataState.selectedWorkspace,
                du: migrationDataState.selectedDeliveryUnits,
                processInstanceId: migrationDataState.processInstanceId
            };

            $http
                .post("/services/v4/js/ide-migration/server/migration/api/migration-rest-api.mjs/continue-process", JSON.stringify(body), {
                    headers: { "Content-Type": "application/json" },
                })
                .then((response) => {
                    const timer = setInterval(function () {
                        $http
                            .post(
                                "/services/v4/js/ide-migration/server/migration/api/migration-rest-api.mjs/get-process",
                                JSON.stringify(body),
                                {
                                    headers: { "Content-Type": "application/json" },
                                }
                            )
                            .then(
                                function (response) {
                                    clearInterval(timer);
                                    let diffViewData = response.data.diffViewData;
                                    handleDiffViewData(diffViewData);
                                    $scope.$apply();
                                },
                                function (response) {
                                    clearInterval(timer);
                                    handleResponseError(response);
                                }
                            );
                    }, 1000);
                });
        }

        function handleDiffViewData(diffViewData) {
            // Add additional keys needed by AngularJS
            for (let i = 0; i < diffViewData.length; i++) {
                const diff = diffViewData[i];
                diff.id = `m-${i}`;
                diff.collapsed = false;
                diff.excluded = false;
            }
            $scope.data = diffViewData;
            migrationViewState.setFullWidthEnabled(true);
            migrationViewState.setDataLoading(false);
        }

        function handleResponseError(response) {
            if (response.data) {
                if ("error" in response.data) {
                    if ("message" in response.data.error) {
                        $messageHub.announceAlertError(defaultErrorTitle, response.data.error.message);
                    } else {
                        $messageHub.announceAlertError(defaultErrorTitle, defaultErrorDesc);
                    }
                } else {
                    $messageHub.announceAlertError(defaultErrorTitle, defaultErrorDesc);
                }
            } else {
                $messageHub.announceAlertError(defaultErrorTitle, defaultErrorDesc);
            }
            errorOccurred();
        }

        $scope.createDiffEditor = function (index) {
            $timeout(function () {
                createDiffView(
                    $scope.data[index].id,
                    $scope.data[index].type,
                    $scope.data[index].original,
                    $scope.data[index].modified,
                    $scope.isDiffViewSplit
                );
            });
        };

        $scope.continueMigration = function () {
            $scope.$parent.goForward();
        };

        $scope.splitDiffView = function () {
            $scope.isDiffViewSplit = true;
            for (let i = 0; i < editors.length; i++) {
                editors[i].updateOptions({ renderSideBySide: true });
            }
        };

        $scope.inlineDiffView = function () {
            $scope.isDiffViewSplit = false;
            for (let i = 0; i < editors.length; i++) {
                editors[i].updateOptions({ renderSideBySide: false });
            }
        };

        $scope.previousClicked = function () {
            $scope.$parent.previousClicked();
        };

        $messageHub.on(
            "migration.changes",
            function (msg) {
                $scope.$apply(function () {
                    migrationViewState.setDataLoading(true);
                    if (msg.data && msg.data.migrationEntry && msg.data.migrationEntry.PROCESS_INSTANCE_ID) {
                        migrationDataState.processInstanceId = msg.data.migrationEntry.PROCESS_INSTANCE_ID;
                        migrationDataState.selectedDeliveryUnits = JSON.parse(msg.data.migrationEntry.DU_STRING);
                        migrationDataState.selectedWorkspace = msg.data.migrationEntry.WORKSPACE_NAME;
                        getDiffData();
                    } else {
                        continueMigration();
                    }

                }
                );
            }.bind(this)
        );

    },
]);

function createDiffView(containerId, filetype, originalTxt, modifiedTxt, renderSideBySide = true) {
    require(["vs/editor/editor.main"], function () {
        let container = document.getElementById(containerId);
        let containerLoading = document.querySelector(`#${containerId} > p:first-of-type`);
        monaco.editor.setTheme(monacoTheme);
        let diffEditor = monaco.editor.createDiffEditor(container, {
            automaticLayout: true,
            readOnly: true,
            scrollBeyondLastLine: false,
            enableSplitViewResizing: false,
            renderSideBySide: renderSideBySide,
        });

        const updateHeight = () => {
            const topBorder = parseInt(getComputedStyle(container).getPropertyValue("border-top-width"), 10);
            const bottomBorder = parseInt(getComputedStyle(container).getPropertyValue("border-bottom-width"), 10);
            const contentHeight = diffEditor.getModifiedEditor().getContentHeight() + topBorder + bottomBorder;
            container.style.height = `${contentHeight}px`;
        };

        diffEditor.getModifiedEditor().onDidContentSizeChange(updateHeight);
        diffEditor.setModel({
            original: monaco.editor.createModel(originalTxt, filetype),
            modified: monaco.editor.createModel(modifiedTxt, filetype),
        });

        editors.push(diffEditor);
        containerLoading.remove();
    });
}
