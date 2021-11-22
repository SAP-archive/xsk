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
exports.getEditor = function () {
	return {
		"id": "xsjob",
		"name": "XSJob",
		"factory": "frame",
		"region": "center-top",
		"label": "XSJob",
    "defaultEditor": false,
		"link": "../xsk-ide-xsjob/editor/editor.html",
		"contentTypes": ["application/json+xsjob"]
	};
};