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
var base64 = require('utils/v4/base64');
var hex = require('utils/v4/hex');

exports.encodeHex = function(data) {
	if (typeof data === 'string') {
		return hex.encode(data);
	}
	return hex.encode(arrayBufferToString(data));
}

exports.encodeBase64 = function(data) {
	if (typeof data === 'string') {
		return base64.encode(data);
	}
	return base64.encode(arrayBufferToString(data));
}

exports.decodeHex = function(hexData) {
	return hex.decode(hexData);
}

exports.decodeBase64 = function(base64Data) {
	return base64.decode(base64Data);
}

function arrayBufferToString(buf) {
	return String.fromCharCode.apply(null, new Uint16Array(buf));
}