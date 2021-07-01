/*
 * Copyright (c) 2019-2021 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2019-2021 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
migrationLaunchView.controller('StartMigrationViewController', ['$scope', '$http', '$messageHub', function ($scope, $http, $messageHub) {
    $scope.isVisible = false;
    $scope.migrationFinished = false;
    $scope.progressBarPercentage = 100;
    let titleList = [
        "Configuration processing, starting the migration...",
        "Migration complete"
    ]
    $scope.progressTitle = titleList[0];
    let neoData = undefined;
    let hanaData = undefined;
    let defaultErrorTitle = "Error migrating project";
    let defaultErrorDesc = "Please check if the information you provided is correct and try again.";

    function startMigration(duData) {
        body = {
            neo: neoData,
            hana: hanaData,
            "connectionId": duData.connectionId,
            "workspace": duData.workspace,
            "vendor": duData.du.vendor,
            "du": duData.du.name,
        }
        $http.post(
            "/public/v4/migration-operations/execute-migration",
            JSON.stringify(body),
            { headers: { 'Content-Type': 'application/json' } }
        ).then(function (response) {
            $scope.migrationFinished = true;
            $scope.progressTitle = titleList[1];
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
    };

    function errorOccurred() {
        $scope.$parent.previousClicked();
    }

    $scope.migrationDone = function () {
        $scope.migrationFinished = true;
    };

    $messageHub.on('migration.start-migration', function (msg) {
        if ("isVisible" in msg.data) {
            $scope.$apply(function () {
                $scope.isVisible = msg.data.isVisible;
            });
            if (msg.data.isVisible) {
                $messageHub.message('migration.neo-credentials', { controller: "migration.start-migration", getData: "all" });
                $messageHub.message('migration.hana-credentials', { controller: "migration.start-migration", getData: "all" });
                $messageHub.message('migration.delivery-unit', { controller: "migration.start-migration", getData: "all" });
            }
        }
        if ("neoData" in msg.data) {
            neoData = msg.data.neoData;
        }
        if ("hanaData" in msg.data) {
            hanaData = msg.data.hanaData;
        }
        if ("duData" in msg.data) {
            startMigration(msg.data.duData);
        }
    }.bind(this));
}]);