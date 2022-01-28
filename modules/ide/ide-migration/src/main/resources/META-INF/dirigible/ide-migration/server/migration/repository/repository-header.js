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
const Utils = require('ide-migration/server/migration/utils');

class RepositoryHeader {

    constructor(attachmentCount, contentLength) {
        this._protocol = "repoV2";
        this._attachmentCount = attachmentCount;
        this._contentLength = contentLength;
    }


    get protocol() {
        return this._protocol;
    }


    get attachmentCount() {
        return this._attachmentCount;
    }


    get contentLength() {
        return this._contentLength;
    }

    static fromBuffer(buffer) {
        let attachmentCountBuffer = buffer.slice(6, 10);

        let attachmentCount = Utils.byteArrayToInt(attachmentCountBuffer);

        let contentLengthBuffer = buffer.slice(10, 14);
        let contentLength = Utils.byteArrayToInt(contentLengthBuffer);
        let actualAttachmentCount = Math.round((attachmentCount) / 2);

        return new RepositoryHeader(actualAttachmentCount, contentLength);

    }

}

module.exports = RepositoryHeader;
