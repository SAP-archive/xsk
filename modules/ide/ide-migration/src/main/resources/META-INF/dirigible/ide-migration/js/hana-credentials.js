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
migrationLaunchView.controller("HanaCredentialsViewController", [
    "$scope",
    "$http",
    "$messageHub",
    "migrationDataState",
    function ($scope, $http, $messageHub, migrationDataState) {
        $scope.migrationDataState = migrationDataState;
        $scope.isVisible = false;
        $scope.passwordVisible = false;
        $scope.areDatabasesLoaded = false;
        $scope.databasesDropdownText = "---Please select---";
        $scope.databases = [];
        $scope.databasesList = $scope.databases;

        let descriptionList = ["Please wait while we get all available databases...", "Provide the SAP HANA Credentials"];
        $scope.descriptionText = descriptionList[0];
        let defaultErrorTitle = "Error listing databases";
        let noProcessErrorTitle = "Error starting migration process";
        let noProcessErrorDescription = "Migration process initiation failed! Process ID is null.";
        let defaultErrorDesc = "Please check if the information you provided is correct and try again.";

        function getAvailableHanaDatabases() {
            body = {
                neo: {
                    hostName: migrationDataState.neoHostName,
                    subaccount: migrationDataState.neoSubaccount,
                    username: migrationDataState.neoUsername,
                    password: migrationDataState.neoPassword,
                },
            };

            $http
                .post("/services/v4/js/ide-migration/server/migration/api/migration-rest-api.mjs/list-databases", JSON.stringify(body), {
                    headers: { "Content-Type": "application/json" },
                })
                .then(function (response) {
                    if (response.data && response.data.failed) {
                        $messageHub.announceAlertError(defaultErrorTitle, defaultErrorDesc);
                        errorOccurred();
                    } else if (response.data.databases && response.data.userJwtToken) {
                        body.databases = response.data.databases;
                        migrationDataState.userJwtToken = response.data.userJwtToken;

                        $scope.areDatabasesLoaded = true;
                        $scope.descriptionText = descriptionList[1];
                        $scope.userInput();
                        $scope.$parent.setPreviousVisible(true);
                        $scope.$parent.setPreviousEnabled(true);
                        $scope.$parent.setNextVisible(true);
                        $scope.$parent.setNextEnabled(true);
                        $scope.$parent.setFinishVisible(false);

                        $scope.databasesDropdownText = "---Please select---";
                        $scope.databases = response.data.databases;
                        $scope.databasesList = $scope.databases;
                    }
                })
                .catch(function (err) {
                    $messageHub.announceAlertError(defaultErrorTitle, err.message ?? defaultErrorDesc);
                    errorOccurred();
                });
        }

        function errorOccurred() {
            $scope.$parent.previousClicked();
            $scope.$parent.setBottomNavEnabled(true);
        }

        $scope.userInput = function () {
            if (
                migrationDataState.schemaName &&
                migrationDataState.dbUsername &&
                migrationDataState.dbPassword &&
                $scope.areDatabasesLoaded
            ) {
                $scope.$parent.setNextEnabled(true);
            } else {
                $scope.$parent.setNextEnabled(false);
            }
        };

        $scope.showPassword = function () {
            $scope.passwordVisible = !$scope.passwordVisible;
        };

        $scope.filterDatabases = function () {
            if ($scope.databasesSearch) {
                let filtered = [];
                for (const database of $scope.databases) {
                    if (database.toLowerCase().includes($scope.databasesSearch.toLowerCase())) {
                        filtered.push(database);
                    }
                }
                $scope.databasesList = filtered;
            } else {
                $scope.databasesList = $scope.databases;
            }
        };

        $scope.databaseSelected = function (database) {
            migrationDataState.schemaName = database;
            $scope.databasesDropdownText = database;
        };

        $messageHub.on(
            "migration.hana-credentials",
            function (msg) {
                if ("isVisible" in msg.data) {
                    $scope.$apply(function () {
                        $scope.areDatabasesLoaded = false;
                        $scope.isVisible = msg.data.isVisible;
                        $scope.$parent.setPreviousVisible(false);
                        $scope.$parent.setPreviousEnabled(false);
                        $scope.$parent.setNextVisible(false);
                        $scope.$parent.setNextEnabled(false);
                        if (msg.data.isVisible) {
                            getAvailableHanaDatabases();
                        }
                    });
                }
            }.bind(this)
        );
    },
]);
