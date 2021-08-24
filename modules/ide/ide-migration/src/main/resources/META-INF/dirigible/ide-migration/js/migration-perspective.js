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
var migrationPerspective = angular.module('migration', ['ngResource', 'ideUiCore']);

migrationPerspective.config(["messageHubProvider", function (messageHubProvider) {
    messageHubProvider.evtNamePrefix = 'migration';
}]);

migrationPerspective.factory('$messageHub', [function () {
    var messageHub = new FramesMessageHub();
    var message = function (evtName, data) {
        messageHub.post({ data: data }, evtName);
    };
    var on = function (topic, callback) {
        messageHub.subscribe(callback, topic);
    };
    return {
        message: message,
        on: on
    };
}]);

migrationPerspective.controller('MigrationViewController', ['Layouts', function (Layouts) {
    this.layoutModel = {
        views: ['migration-launch'],
        viewSettings: {
            'migration-launch': { isClosable: false }
        },
        layoutSettings: {
            hasHeaders: false,
            showCloseIcon: false
        }
    };
}]);