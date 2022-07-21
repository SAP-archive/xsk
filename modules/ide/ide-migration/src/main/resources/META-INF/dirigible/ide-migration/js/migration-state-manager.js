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
migrationLaunchView.factory("$messageHub", [
    function () {
        const messageHub = new FramesMessageHub();
        const announceAlert = function (title, message, type) {
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
        const announceAlertError = function (title, message) {
            announceAlert(title, message, "error");
        };
        const message = function (evtName, data) {
            messageHub.post({ data: data }, evtName);
        };
        const on = function (topic, callback) {
            messageHub.subscribe(callback, topic);
        };
        return {
            openNext: null,
            openState: null
        };



    },
]);