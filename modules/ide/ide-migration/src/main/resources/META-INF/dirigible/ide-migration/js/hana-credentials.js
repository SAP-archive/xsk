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
migrationLaunchView.controller('HanaCredentialsViewController', ['$scope', '$http', '$messageHub', function ($scope, $http, $messageHub) {
    $scope.isVisible = false;
    $scope.passwordVisible = false;
    $scope.areDatabasesLoaded = false;
    $scope.databasesDropdownText = "---Please select---";
    $scope.databases = [];
    $scope.databasesList = $scope.databases;
    let processId = undefined;

    let descriptionList = [
        "Please wait while we get all available databases...",
        "Provide the SAP HANA Credentials"
    ];
    $scope.descriptionText = descriptionList[0];
    let neoData = undefined;

    function getAvailableHanaDatabases() {
        body = {
            neo: neoData
        }

        $http.post(
            "/services/v4/js/ide-migration/server/migration/api/migration-rest-api.js/start-process",
            JSON.stringify(body),
            { headers: { 'Content-Type': 'application/json' } }
        ).then(function (response) {
            processId = body.processInstanceId = response.data.processInstanceId;
            const timer = setInterval(function () {
                $http.post(
                    "/services/v4/js/ide-migration/server/migration/api/migration-rest-api.js/get-process",
                    JSON.stringify(body),
                    { headers: { 'Content-Type': 'application/json' } }
                ).then(function (response) {
                    if (response.data.databases) {
                        clearInterval(timer);
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
                }, function (response) { })
            }, 1000);

        }, function (response) {
            if (response.data) {
                if ("error" in response.data) {
                    if ("message" in response.data.error) {
                        $messageHub.announceAlertError(
                            defaultErrorTitle,
                            response.data.error.message
                        );
                    } else {
                        $messageHub.announceAlertError(
                            defaultErrorTitle,
                            defaultErrorDesc
                        );
                    }
                    console.error(`HTTP $response.status`, response.data.error);
                } else {
                    $messageHub.announceAlertError(
                        defaultErrorTitle,
                        defaultErrorDesc
                    );
                }
            } else {
                $messageHub.announceAlertError(
                    defaultErrorTitle,
                    defaultErrorDesc
                );
            }
            errorOccurred();
        });
    }

    $scope.userInput = function () {
        if ($scope.schemaName && $scope.username && $scope.password && $scope.areDatabasesLoaded) {
            $scope.$parent.setNextEnabled(true);
        } else {
            $scope.$parent.setNextEnabled(false);
        };
    };

    $scope.showPassword = function () {
        $scope.passwordVisible = !$scope.passwordVisible;
    };

    $scope.filterDatabases = function () {
        if ($scope.databasesSearch) {
            let filtered = [];
            for (let i = 0; i < $scope.databases.length; i++) {
                if ($scope.databases[i].toLowerCase().includes($scope.databasesSearch.toLowerCase())) {
                    filtered.push($scope.databases[i]);
                }
            }
            $scope.databasesList = filtered;
        } else {
            $scope.databasesList = $scope.databases;
        }
    };

    $scope.databaseSelected = function (database) {
        $scope.schemaName = database;
        $scope.databasesDropdownText = database;
    };

    $messageHub.on('migration.hana-credentials', function (msg) {
        if ("isVisible" in msg.data) {
            $scope.$apply(function () {
                $scope.areDatabasesLoaded = false;
                $scope.isVisible = msg.data.isVisible;
                $scope.$parent.setPreviousVisible(false);
                $scope.$parent.setPreviousEnabled(false);
                $scope.$parent.setNextVisible(false);
                $scope.$parent.setNextEnabled(false);
                if (msg.data.isVisible) {
                    $messageHub.message('migration.neo-credentials', { controller: "migration.hana-credentials", getData: "all" });
                }
            });
        }
        if ("neoData" in msg.data) {
            neoData = msg.data.neoData;
            getAvailableHanaDatabases();
        }
        if ("getData" in msg.data) {
            if (msg.data.getData === "all") {
                $messageHub.message(msg.data.controller, {
                    hanaData: {
                        "databaseSchema": $scope.schemaName,
                        "username": $scope.username,
                        "password": $scope.password,
                        "processId": processId
                    }
                });
            }
        }
    }.bind(this));
}]);