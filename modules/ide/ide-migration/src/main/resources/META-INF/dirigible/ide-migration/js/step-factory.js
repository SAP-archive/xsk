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
const FLOW_TYPE_ZIP = 1;
const FLOW_TYPE_LIVE = 2;

migrationLaunchView.factory("stepFactory", ['migrationViewState', 'migrationFlow', function (migrationViewState, migrationFlow) {
    const steps = [
        {
            id: 1,
            name: "SAP BTP Neo Credentials",
            topicId: "migration.neo-credentials"
        },
        {
            id: 2,
            name: "SAP HANA Credentials",
            topicId: "migration.hana-credentials",
            onLoad: "migration.get-databases"
        },
        { id: 3, name: "Delivery Units", topicId: "migration.delivery-unit", onLoad: "migration.delivery-unit" },
        { id: 4, name: "Changes", topicId: "migration.changes", onLoad: "migration.changes" },
        { id: 5, name: "Migration", topicId: "migration.start-migration", onLoad: "migration.start-migration" },
    ];

    const zipsteps = [
        { id: 1, name: "Upload ZIP file", topicId: "migration.upload-zip-migration" },
        { id: 2, name: "Migration", topicId: "migration.start-zip-migration" },
    ];

    function getStepByIndex(index) {
        const activeFlow = migrationFlow.getActiveFlow();
        return activeFlow === FLOW_TYPE_LIVE ? steps[index - 1] : zipsteps[index - 1]; //TODO: refactor - get by id instead index in array
    }

    function getStepByIndexForFlow(index, flow) {
        return flow === FLOW_TYPE_LIVE ? steps[index - 1] : zipsteps[index - 1];
    }

    function getStepByNameForFlow(name, flow) {
        const result = flow === FLOW_TYPE_LIVE ? steps.filter(s => s.name === name) : zipsteps.filter(s => s.name === name);
        if (result && result.length > 0) {
            return result[0];
        }
        return null;
    }


    function getSteps() {
        const activeFlow = migrationFlow.getActiveFlow();
        return activeFlow === FLOW_TYPE_LIVE ? steps : zipsteps;
    }

    return {
        getStepByIndex,
        getSteps,
        getStepByIndexForFlow,
        getStepByNameForFlow
    }
}])