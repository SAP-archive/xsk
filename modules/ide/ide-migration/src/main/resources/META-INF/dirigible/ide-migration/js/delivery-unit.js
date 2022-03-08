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
migrationLaunchView.controller("DeliveryUnitViewController", [
    "$scope",
    "$http",
    "$messageHub",
    "migrationDataState",
    function ($scope, $http, $messageHub, migrationDataState) {
        $scope.migrationDataState = migrationDataState;
        $scope.showCreateButton = false;
        $scope.showSeparator = false;
        $scope.isVisible = false;
        $scope.duDropdownDisabled = true;
        $scope.duDropdownInitText = "---Please select---";
        $scope.duDropdownText = $scope.duDropdownInitText;
        $scope.workspacesDropdownText = "---Please select---";
        $scope.workspaces = [];
        $scope.workspacesList = $scope.workspaces;
        $scope.deliveryUnits = [];
        $scope.deliveryUnitList = $scope.deliveryUnits;
        $scope.dataLoaded = false;
        $scope.selectAllText = "Select all";
        $scope.duSelectedUItext = [];
        let descriptionList = ["Please wait while we get all delivery unit(s)...", "Select the target workspace and delivery unit(s)"];
        $scope.descriptionText = descriptionList[0];
        let defaultErrorTitle = "Error loading delivery units";
        let defaultErrorDesc = "Please check if the information you provided is correct and try again.";

        $(".multi-selectable").on("click", function (e) {
            e.stopPropagation();
        });

        function getDUData() {
            body = {
                neo: {
                    hostName: migrationDataState.neoHostName,
                    subaccount: migrationDataState.neoSubaccount,
                },
                hana: {
                    databaseSchema: migrationDataState.schemaName,
                    username: migrationDataState.dbUsername,
                    password: migrationDataState.dbPassword,
                },
                userJwtToken: migrationDataState.userJwtToken,
            };

            $http
                .post("/services/v4/js/ide-migration/server/migration/api/migration-rest-api.mjs/start-process", JSON.stringify(body), {
                    headers: { "Content-Type": "application/json" },
                })
                .then(
                    function (response) {
                        migrationDataState.processInstanceId = body.processInstanceId = response.data.processInstanceId;
                        const timer = setInterval(function () {
                            $http
                                .post(
                                    "/services/v4/js/ide-migration/server/migration/api/migration-rest-api.mjs/get-process",
                                    JSON.stringify(body),
                                    { headers: { "Content-Type": "application/json" } }
                                )
                                .then(
                                    function (response) {
                                        if (response.data && response.data.failed) {
                                            clearInterval(timer);
                                            $messageHub.announceAlertError(defaultErrorTitle, defaultErrorDesc);
                                            errorOccurred();
                                        } else if (response.data.workspaces && response.data.deliveryUnits && response.data.connectionId) {
                                            clearInterval(timer);
                                            migrationDataState.connectionId = response.data.connectionId;
                                            $scope.workspaces = response.data.workspaces;
                                            $scope.workspacesList = $scope.workspaces;
                                            $scope.deliveryUnits = response.data.deliveryUnits;
                                            $scope.deliveryUnitList = $scope.deliveryUnits;
                                            $scope.$parent.setBottomNavEnabled(true);
                                            $scope.descriptionText = descriptionList[1];
                                            $scope.dataLoaded = true;
                                        }
                                    },
                                    function (response) {
                                        clearInterval(timer);
                                        $messageHub.announceAlertError(defaultErrorTitle, defaultErrorDesc);
                                        errorOccurred();
                                    }
                                );
                        }, 1000);
                    },
                    function (response) {
                        if (response.data) {
                            if ("error" in response.data) {
                                if ("message" in response.data.error) {
                                    $messageHub.announceAlertError(defaultErrorTitle, response.data.error.message);
                                } else {
                                    $messageHub.announceAlertError(defaultErrorTitle, defaultErrorDesc);
                                }
                                console.error(`HTTP $response.status`, response.data.error);
                            } else {
                                $messageHub.announceAlertError(defaultErrorTitle, defaultErrorDesc);
                            }
                        } else {
                            $messageHub.announceAlertError(defaultErrorTitle, defaultErrorDesc);
                        }
                        errorOccurred();
                    }
                );
        }

        function errorOccurred() {
            $scope.$parent.previousClicked();
            $scope.$parent.setBottomNavEnabled(true);
        }

        $scope.filterDU = function () {
            if ($scope.duSearch) {
                let filtered = [];
                for (const deliveryUnit of $scope.deliveryUnits) {
                    if (deliveryUnit.name.toLowerCase().includes($scope.duSearch.toLowerCase())) {
                        filtered.push(deliveryUnit);
                    }
                }
                $scope.deliveryUnitList = filtered;
            } else {
                $scope.deliveryUnitList = $scope.deliveryUnits;
            }
        };

        $scope.filterWorkspaces = function () {
            if ($scope.workspacesSearch) {
                $scope.showCreateButton = true;
                $scope.showSeparator = true;
                let filtered = [];
                for (const workspace of $scope.workspaces) {
                    if (workspace.toLowerCase().includes($scope.workspacesSearch.toLowerCase())) {
                        filtered.push(workspace);
                    } else {
                        $scope.showSeparator = false;
                    }
                }
                $scope.workspacesList = filtered;
            } else {
                $scope.showSeparator = false;
                $scope.showCreateButton = false;
                $scope.workspacesList = $scope.workspaces;
            }
            $scope.btnBottonText = $scope.workspacesSearch;
        };

        $scope.workspaceSelected = function (workspace) {
            migrationDataState.selectedWorkspace = workspace;
            $scope.workspacesDropdownText = workspace;
            $scope.duDropdownDisabled = false;
        };

        $scope.isDUSelected = (du) => {
            return migrationDataState.selectedDeliveryUnits.includes(du) ? "selected" : "";
        };

        $scope.allDUSelectable = () => {
            return migrationDataState.selectedDeliveryUnits.length < $scope.deliveryUnitList.length ? "selected" : "";
        };

        $scope.toggleSelectAllDU = () => {
            let compare_value = migrationDataState.selectedDeliveryUnits.length != $scope.deliveryUnitList.length;
            for (const deliveryUnit of $scope.deliveryUnitList) {
                if (Boolean($scope.isDUSelected(deliveryUnit)) !== compare_value) $scope.duSelected(deliveryUnit);
            }
        };

        $scope.duSelected = function (deliveryUnit) {
            if (migrationDataState.selectedDeliveryUnits.includes(deliveryUnit)) {
                migrationDataState.selectedDeliveryUnits = migrationDataState.selectedDeliveryUnits.filter((elem) => elem != deliveryUnit);
                $scope.duSelectedUItext = $scope.duSelectedUItext.filter((elem) => elem != deliveryUnit.name);
            } else {
                migrationDataState.selectedDeliveryUnits.push(deliveryUnit);
                $scope.duSelectedUItext.push(deliveryUnit.name);
            }

            $scope.duDropdownText = $scope.duSelectedUItext.length ? $scope.duSelectedUItext.join(", ") : $scope.duDropdownInitText;
            $scope.selectAllText =
                migrationDataState.selectedDeliveryUnits.length == $scope.deliveryUnitList.length ? "Unselect all" : "Select all";
            $scope.$parent.setNextEnabled(true);
        };

        $messageHub.on(
            "migration.delivery-unit",
            function (msg) {
                if ("isVisible" in msg.data) {
                    $scope.$apply(function () {
                        $scope.dataLoaded = false;
                        $scope.duDropdownDisabled = true;
                        $scope.duDropdownText = "---Please select---";
                        $scope.workspacesDropdownText = "---Please select---";
                        $scope.descriptionText = descriptionList[0];
                        $scope.isVisible = msg.data.isVisible;
                        if (msg.data.isVisible) {
                            $scope.$parent.setFullWidthEnabled(false);
                            $scope.$parent.setBottomNavEnabled(false);
                            $scope.$parent.setPreviousVisible(true);
                            $scope.$parent.setPreviousEnabled(true);
                            $scope.$parent.setNextEnabled(false);
                            $scope.$parent.setNextVisible(true);
                        }
                    });
                    if (msg.data.isVisible) {
                        console.log("DU msg", msg);
                        getDUData();
                    }
                }
            }.bind(this)
        );
    },
]);
