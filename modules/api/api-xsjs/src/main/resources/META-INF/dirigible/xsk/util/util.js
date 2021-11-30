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
var uuid = require("utils/v4/uuid");
var bytes = require("io/v4/bytes");

exports.codec = require("xsk/util/codec/codec");

exports.createUuid = function () {
  return uuid.random();
}

exports.stringify = function (arrayBuffer) {
  return bytes.byteArrayToText(arrayBuffer);
}

exports.Zip = function (zipParams) {
  zipParams = zipParams || {};
  var source = zipParams.source || null;
  var index = zipParams.index || null;
  var settings = zipParams.settings || {};

  var password = settings.password || null;
  var maxUncompressedSizeInBytes = settings.maxUncompressedSizeInBytes || null;

  // TODO: Check source type. Currently implemented only for byte array.
  // TODO: Index must be provided when source is a ResultSet.
  // TODO: Apply settings if provided.

  if (source) {
    var zipContent = JSON.parse(bytes.byteArrayToText(source));
    for (var file in zipContent) {
      this[file] = zipContent[file];
    }
  }

  this._password_; // TODO: Should always return undefined.
  this._metadata_; // TODO: Should return data about the zip.

  this.asArrayBuffer = function () {
    var {_password_, _metadata_, asArrayBuffer, ...zipContent} = this;
    var content = JSON.stringify(zipContent);

    return bytes.textToByteArray(content);
    ;
  };
}
