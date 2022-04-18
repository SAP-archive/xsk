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

exports.getTemplate = function () {
	return {
		"name": "hdi",
		"label": "HDI File",
		"extension": "hdi",
		"data": JSON.stringify({
			"configuration": "/sample/config.hdiconfig",
			"users": ["XSK_SAMPLE_USER"],
			"group": "XSK_HDI_SAMPLE_GROUP",
			"container": "XSK_HDI_SAMPLE",
			"deploy": [
				"/sample/Sample.hdbsynonym",
				"/sample/DEMO.hdbcalculationview"
			],
			"undeploy": []
		})
	};
};
