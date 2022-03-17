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
migrationLaunchView.controller("MigrationStatisticsController", [
    "$scope",
    "$http",
    function ($scope, $http) {
        let body = { migrations: "empty" };
        let defaultErrorTitle = "Error loading migrations information.";
        $http
            .post("/services/v4/js/ide-migration/server/migration/api/migration-rest-api.mjs/migrationsTrack", JSON.stringify(body), {
                headers: { "Content-Type": "application/json" },
            })
            .then(
                function (response) {
                    $scope.migrations = JSON.parse(JSON.stringify(response.data));
                    $scope.hideTable = $scope.migrations === "empty";
                },
                function (response) {
                    $messageHub.announceAlertError(defaultErrorTitle, response.data.error.message);
                    console.error(response);
                }
            );
    },
]);
