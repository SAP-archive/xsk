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
migrationLaunchView.factory("migrationFlow", ["$messageHub", function ($messageHub) {
    let currentStepIndex = 0;

    let activeFlow = null;

    function setActiveFlow(type) {
        activeFlow = type;
    }

    function getActiveFlow() {
        return activeFlow;
    }

    function goForward() {
        currentStepIndex++;
    }

    function goBack() {
        currentStepIndex--;
    }

    function goToStep(index, flowType, step, data) {
        currentStepIndex = index;
        activeFlow = flowType;
        $messageHub.message(step.onLoad, data);
    }

    function getCurrentStepIndex() {
        return currentStepIndex;
    }

    return {
        setActiveFlow,
        getActiveFlow,
        goForward,
        goBack,
        goToStep,
        getCurrentStepIndex,
    }

}]);
