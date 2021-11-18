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
exports.getTemplate = function () {
	return {
		"name": "xsjob",
		"label": " XS Scheduled Job",
		"extension": "xsjob",
		"data": JSON.stringify(JSON.parse('{\n' +
        '    "description": "Read stock value",\n' +
        '    "action": "yahoo:yahoo.xsjs::readStock",\n' +
        '    "schedules": [\n' +
        '       {\n' +
        '          "description": "Read current stock value",\n' +
        '          "xscron": "* * * * * * 59",\n' +
        '          "parameter": {\n' +
        '             "stock": "SAP.DE"\n' +
        '             }\n' +
        '       }\n' +
        '    ]\n' +
        '}'), null, 2)
	};
};
