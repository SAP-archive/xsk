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
var migrationLaunchView = angular.module("migration-launch", ["angularFileUpload"]);


migrationLaunchView.factory("$messageHub", [
    function () {
        var messageHub = new FramesMessageHub();
        var announceAlert = function (title, message, type) {
            messageHub.post(
                {
                    data: {
                        title: title,
                        message: message,
                        type: type,
                    },
                },
                "ide.alert"
            );
        };
        var announceAlertError = function (title, message) {
            announceAlert(title, message, "error");
        };
        var message = function (evtName, data) {
            messageHub.post({ data: data }, evtName);
        };
        var on = function (topic, callback) {
            messageHub.subscribe(callback, topic);
        };
        return {
            announceAlert: announceAlert,
            announceAlertError: announceAlertError,
            message: message,
            on: on,
        };
    },
]);

migrationLaunchView.factory("migrationDataState", migrationDataState);

function migrationDataState() {
    let state = {
        schemaName: null,
        dbUsername: null,
        dbPassword: null,

        neoUsername: null,
        neoPassword: null,
        neoSubaccount: null,
        neoHostName: null,

        selectedDeliveryUnits: [],
        selectedWorkspace: null,

        processInstanceId: null,
        connectionId: null,
    };

    return state;
}

migrationLaunchView.controller("MigrationLaunchViewController", [
    "$scope",
    "$messageHub",
    "migrationViewState",
    "stepFactory",
    "migrationFlow",
    function ($scope, $messageHub, migrationViewState, stepFactory, migrationFlow) {

        $scope.getSteps = function () {
            return stepFactory.getSteps();
        }

        $scope.isVisible = function (partial) {
            return migrationViewState.getVisibleStep() === partial;
        }

        $scope.fullWidthEnabled = function () {
            return migrationViewState.isFullWidthEnabled();
        }
        $scope.onStatisticsPage = function () {
            return migrationViewState.isOnStatisticsPage();
        }

        $scope.isMigrationFromZip = function () {
            return migrationFlow.getActiveFlow() === FLOW_TYPE_ZIP;
        }

        $scope.bottomNavHidden = function () {
            return migrationViewState.isBottomNavHidden();
        }
        $scope.previousDisabled = function () {
            return migrationViewState.isPreviousDisabled();
        }
        $scope.nextDisabled = function () {
            return migrationViewState.isNextDisabled();
        }
        $scope.previousVisible = function () {
            return migrationViewState.isPreviousVisible();
        }
        $scope.nextVisible = function () {
            return migrationViewState.isNextVisible();
        }
        $scope.finishVisible = function () {
            return migrationViewState.isFinishVisible();
        }
        $scope.finishDisabled = function () {
            return migrationViewState.isFinishDisabled();
        }

        $scope.currentStepIndex = function () {
            migrationFlow.getCurrentStepIndex();
        }

        $scope.goForward = function () {
            migrationFlow.goForward();
            const currentStep = stepFactory.getStepByIndex(migrationFlow.getCurrentStepIndex());
            if (currentStep && currentStep["onLoad"]) {
                $messageHub.message(currentStep["onLoad"]);
            }
        }

        $scope.goBack = function () {
            migrationFlow.goBack();
        }

        $scope.selectLiveMigration = function () {
            migrationViewState.setFinishVisible(false);
            $scope.startFlow(FLOW_TYPE_LIVE);
        }

        $scope.startFlow = function (flowType) {
            migrationFlow.setActiveFlow(flowType)
            migrationFlow.goForward();
        }

        $scope.selectZipMigration = function () {
            migrationViewState.setFinishVisible(false);
            migrationViewState.setNextVisible(false);
            $scope.startFlow(FLOW_TYPE_ZIP);
        };

        $scope.setNextEnabled = function (enabled) {
            migrationViewState.setNextDisabled(!enabled);
        };

        $scope.previousClicked = function () {
            migrationFlow.goBack();
        };

        $scope.continueClicked = function (migrationEntry) {
            if (migrationEntry.STATUS === "POPULATING_PROJECTS_EXECUTED") {
                const step = stepFactory.getStepByNameForFlow("Changes", FLOW_TYPE_LIVE);
                migrationFlow.goToStep(step.id, FLOW_TYPE_LIVE, step, { migrationEntry });
            }
        }


        $scope.getStepStatus = function (stepId) {
            if (stepId === migrationFlow.getCurrentStepIndex()) {
                return "active";
            }
            if (stepId < migrationFlow.getCurrentStepIndex()) {
                return "done";
            }
            return "inactive";
        }

        $messageHub.on("migration.launch", function (msg) { }.bind(this));
    },
]);
