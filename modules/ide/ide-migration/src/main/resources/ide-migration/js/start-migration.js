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
migrationLaunchView.controller('StartMigrationViewController', ['$scope', '$http', '$messageHub', function ($scope, $http, $messageHub) {
    $scope.isVisible = false;
    $scope.migrationFinished = false;
    $scope.progressBarPercentage = 100;
    let titleList = [
        "Migration in progress",
        "Migration complete"
    ]
    $scope.progressTitle = titleList[0];
    $scope.statusMessage = "Configuration processing...";
    let neoData = undefined;
    let hanaData = undefined;
    let defaultErrorTitle = "Error migrating project";
    let defaultErrorDesc = "Please check if the information you provided is correct and try again.";
    let selectedWorkspace = '';

    function startMigration(duData) {
        selectedWorkspace = duData.workspace;
        body = {
            neo: neoData,
            hana: hanaData,
            "connectionId": duData.connectionId,
            "workspace": duData.workspace,
            "vendor": duData.du.vendor,
            "du": duData.du.name,
        }
        $http.post(
            "/services/v4/migration-operations/execute-migration",
            JSON.stringify(body),
            { headers: { 'Content-Type': 'application/json' } }
        ).then(function (response) {
            $scope.progressTitle = titleList[1];
            $scope.statusMessage = `Project '${response.data.projectName}' was successfully created.`;
            $scope.migrationFinished = true;
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

    $scope.goToWorkspace = function () {
        $messageHub.message(
            "workspace.set",
            {
                workspace: selectedWorkspace
            }
        );
        $messageHub.message(
            "ide-core.openPerspective",
            {
                link: "../ide/index.html"
            }
        );
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