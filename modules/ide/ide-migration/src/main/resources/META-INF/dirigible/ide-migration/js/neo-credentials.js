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
migrationLaunchView.controller('NeoCredentialsViewController', ['$scope', '$messageHub', 'migrationDataState', function ($scope, $messageHub, migrationDataState) {
    $scope.migrationDataState = migrationDataState;
    $scope.passwordHintMessage = "If you have enabled 2FA for your account, append your 2FA code after the password";
    $scope.isVisible = true;
    $scope.passwordVisible = false;
    $scope.regionDropdownInitText = "---Please select---";
    $scope.regionDropdownText = $scope.regionDropdownInitText;
    $scope.regions = [
        { name: 'Australia (Sydney) | ap1.hana.ondemand.com', region: 'ap1.hana.ondemand.com' },
        { name: 'Europe (Rot) | eu1.hana.ondemand.com', region: 'eu1.hana.ondemand.com' },
        { name: 'Europe (Frankfurt) | eu2.hana.ondemand.com', region: 'eu2.hana.ondemand.com' },
        { name: 'Europe (Amsterdam) | eu3.hana.ondemand.com', region: 'eu3.hana.ondemand.com' },
        { name: 'Japan (Tokyo) | jp1.hana.ondemand.com', region: 'jp1.hana.ondemand.com' },
        { name: 'US East (Ashburn) | us1.hana.ondemand.com', region: 'us1.hana.ondemand.com' },
        { name: 'US East (Sterling) | us3.hana.ondemand.com', region: 'us3.hana.ondemand.com' }
    ];
    $scope.regionList = $scope.regions;
    $scope.hostName = '';
    
    $scope.userInput = function () {
        if (migrationDataState.neoHostName && migrationDataState.neoSubaccount && migrationDataState.neoUsername && migrationDataState.neoPassword) {
            $scope.$parent.setNextEnabled(true);
        } else {
            $scope.$parent.setNextEnabled(false);
        };
    };

    $scope.showPassword = function () {
        $scope.passwordVisible = !$scope.passwordVisible;
    };

    $scope.isSelected = (regionObject) => {
        return $scope.hostName === regionObject.region ? "selected" : '';
    }

    $scope.regionSelected = function (regionObject) {                            
            
        migrationDataState.neoHostName = regionObject.region;
        $scope.regionDropdownText = regionObject.name;
            
            $scope.$parent.setFinishEnabled(true);
        };

    $scope.filterRegions = function () {
            if ($scope.regionSearch) {
                let filtered = [];
                let alreadyHaveUserEnteredRegion = false;
                for (let i = 0; i < $scope.regions.length; i++) {
                    if ($scope.regions[i].name.toLowerCase().includes($scope.regionSearch.toLowerCase())) {
                        const region = $scope.regions[i];
                        filtered.push(region);

                        if (region.region === $scope.regionSearch) {
                          alreadyHaveUserEnteredRegion = true;
                        }
                    }
                }

                if (!alreadyHaveUserEnteredRegion) {
                  filtered.push({name: 'Use "' + $scope.regionSearch + '" as a region', region: $scope.regionSearch, isUserEnteredRegion: true})
                }

                $scope.regionList = filtered;
            } else {
                $scope.regionList = $scope.regions;
            }
        };

    $messageHub.on('migration.neo-credentials', function (msg) {
        if ("isVisible" in msg.data) {
            $scope.$apply(function () {
                $scope.isVisible = msg.data.isVisible;
                if (msg.data.isVisible) {
                    $scope.userInput();
                    $scope.$parent.setPreviousVisible(false);
                    $scope.$parent.setPreviousEnabled(true);
                    $scope.$parent.setNextVisible(true);
                    $scope.$parent.setFinishVisible(false);
                }
            });
        }
    }.bind(this));
}]);