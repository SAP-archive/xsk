/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2022 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
let RepositoryHeader = require('ide-migration/server/migration/repository/repository-header');
let ResponseAttachmentParser = require('ide-migration/server/migration/repository/response-attachment-parser');

let utf8 = org.eclipse.dirigible.api.v3.utils.UTF8Facade;

class RepositoryResponse {

    constructor(responseBuffer) {

        let headerBuffer = responseBuffer.slice(0, 14);
        this._header = RepositoryHeader.fromBuffer(headerBuffer);
        let contentEnd = 14 + this._header.contentLength;
        this._contentBuffer = responseBuffer.slice(14, contentEnd);

        this._attachments = [];
        if (this._header.attachmentCount > 1) {
            let attachmentBuffer = responseBuffer.slice(contentEnd, responseBuffer.length);
            this._attachments = ResponseAttachmentParser.parse(attachmentBuffer, this._header.attachmentCount);
        }

    }

    get header() {
        return this._header;
    }

    get content() {
        let contentString = utf8.decode(this._contentBuffer);
        return JSON.parse(contentString);
    }

    get attachments() {
        return this._attachments;
    }

}

module.exports = RepositoryResponse;