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
exports.getView = function () {
	return {
		"id": "xsjobs",
		"name": "XSJobs",
		"factory": "frame",
		"region": "center-middle",
		"label": "XSJobs",
		"link": "../xsk-ide-xsjob/view/jobs.html"
	};
};