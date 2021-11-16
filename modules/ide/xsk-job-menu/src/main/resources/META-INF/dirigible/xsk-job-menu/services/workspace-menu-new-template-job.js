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
		"name": "job",
		"label": " XS Scheduled Job",
		"extension": "xsjob",
		"data": JSON.stringify(JSON.parse('{"expression":"0/10 * * * * ?","group":"dirigible-defined","handler":"myproject/myhandler.js","description":"My Job"}'), null, 2)
	};
};
