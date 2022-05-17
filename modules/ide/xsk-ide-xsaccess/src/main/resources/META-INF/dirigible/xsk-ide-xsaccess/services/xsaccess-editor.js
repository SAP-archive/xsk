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
exports.getEditor = function () {
	return {
		"id": "xsaccess",
		"name": "XSAccess",
		"factory": "frame",
		"region": "center-top",
		"label": "XS Access",
		"defaultEditor": true,
		"link": "../xsk-ide-xsaccess/editor/editor.html",
		"contentTypes": ["application/json+xsaccess"]
	};
};