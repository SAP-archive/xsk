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
class Utils {
    static arrayCopy(src, srcIndex, dest, destIndex, length) {
        dest.splice(destIndex, length, ...src.slice(srcIndex, srcIndex + length));
    }

    static intToByteArray(int) {
        return org.eclipse.dirigible.api.v3.io.BytesFacade.intToByteArray(int, "LITTLE_ENDIAN");
    }

    static byteArrayToInt(arr) {
        return org.eclipse.dirigible.api.v3.io.BytesFacade.byteArrayToInt(arr, "LITTLE_ENDIAN");
    }
}

module.exports = Utils;
