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
migrationLaunchView.controller('DeliveryUnitViewController', ['$scope', '$messageHub', function ($scope, $messageHub) {
    $scope.isVisible = false;
    $scope.duDropdownDisabled = true;
    $scope.duDropdownText = "---Please select---";
    $scope.workspacesDropdownText = "---Please select---";
    $scope.workspaces = ["W1", "W2", "W3", "W4", "W5", "W6"];
    $scope.workspacesList = $scope.workspaces;
    $scope.deliveryUnits = ["DU1", "DU2", "DU3", "DU4", "DU5", "DU6", "DU7", "FO1", "LO2"];
    $scope.deliveryUnitList = $scope.deliveryUnits;
    $scope.dataLoaded = false;
    let selectedDeliveyUnit = undefined;
    let descriptionList = [
        "Please wait while we get all delivery units...",
        "Provide the target workspace and delivery unit"
    ];
    $scope.descriptionText = descriptionList[0];

    function sleep(ms) {
        return new Promise(resolve => setTimeout(resolve, ms));
    };

    $scope.filterDU = function () {
        if ($scope.duSearch) {
            let filtered = [];
            for (let i = 0; i < $scope.deliveryUnits.length; i++) {
                if ($scope.deliveryUnits[i].toLowerCase().includes($scope.duSearch.toLowerCase())) {
                    filtered.push($scope.deliveryUnits[i]);
                }
            }
            $scope.deliveryUnitList = filtered;
        } else {
            $scope.deliveryUnitList = $scope.deliveryUnits;
        }
    };

    $scope.filterWorkspaces = function () {
        if ($scope.workspacesSearch) {
            let filtered = [];
            for (let i = 0; i < $scope.workspaces.length; i++) {
                if ($scope.workspaces[i].toLowerCase().includes($scope.workspacesSearch.toLowerCase())) {
                    filtered.push($scope.workspaces[i]);
                }
            }
            $scope.workspacesList = filtered;
        } else {
            $scope.workspacesList = $scope.workspaces;
        }
    };

    $scope.workspaceSelected = function (workspace) {
        selectedWorkspace = workspace;
        $scope.workspacesDropdownText = workspace;
        $scope.duDropdownDisabled = false;
    };

    $scope.duSelected = function (deliveryUnit) {
        selectedDeliveyUnit = deliveryUnit;
        $scope.duDropdownText = deliveryUnit;
        $scope.$parent.setFinishEnabled(true);
    };

    $messageHub.on('migration.delivery-unit', function (msg) {
        if ("isVisible" in msg.data) {
            $scope.$apply(function () {
                $scope.dataLoaded = false;
                $scope.duDropdownDisabled = true;
                $scope.duDropdownText = "---Please select---";
                $scope.workspacesDropdownText = "---Please select---";
                $scope.descriptionText = descriptionList[0];
                $scope.isVisible = msg.data.isVisible;
                if (msg.data.isVisible) {
                    if (selectedDeliveyUnit) {
                        $scope.$parent.setFinishEnabled(true);
                    } else {
                        $scope.$parent.setFinishEnabled(false);
                    }
                    $scope.$parent.setBottomNavEnabled(false);
                    $scope.$parent.setPreviousVisible(true);
                    $scope.$parent.setPreviousEnabled(true);
                    $scope.$parent.setNextVisible(false);
                    $scope.$parent.setFinishVisible(true);
                }
            });
            if (msg.data.isVisible) {
                sleep(4000).then(() => {
                    $scope.$apply(function () {
                        $scope.dataLoaded = true;
                        $scope.descriptionText = descriptionList[1];
                        $scope.$parent.setBottomNavEnabled(true);
                    });
                });
            }
        }
    }.bind(this));
}]);