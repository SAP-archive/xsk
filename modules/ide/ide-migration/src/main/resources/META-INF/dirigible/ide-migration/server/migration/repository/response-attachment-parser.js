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
const Utils = require("ide-migration/server/migration/utils");

class ResponseAttachmentParser {
    static parse(responseAttachmentBuffer, numberOfAttachments) {
        let attachments = [];
        let lengthStart = 0;
        let lengthEnd = 4;

        for (let i = 1; i <= numberOfAttachments; i++) {
            let lengthBuffer = responseAttachmentBuffer.slice(lengthStart, lengthEnd);
            let length = Utils.byteArrayToInt(lengthBuffer);

            let contentStart = lengthEnd;
            let contentEnd = contentStart + length;
            let contentBuffer = responseAttachmentBuffer.slice(contentStart, contentEnd);

            attachments.push(contentBuffer);

            lengthStart = contentEnd;
            lengthEnd = lengthStart + 4;
        }
        return attachments;
    }
}

module.exports = ResponseAttachmentParser;
