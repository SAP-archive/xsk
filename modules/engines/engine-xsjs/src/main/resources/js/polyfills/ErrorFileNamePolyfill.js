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
Object.defineProperty(Error.prototype, 'fileName', {
    get() {
        const stack = this.stack
        const regex = /at[^\(]*\(([^:)]*)(?::\d*-*\d*)*\)/;
        if (!stack) {
            return "Unknown";
        }
        const found = stack.match(regex);
        if (!found || !found[1]) {
            return "Unknown";
        }

        const fileName = found[1];
        if (fileName === "<eval>") {
          // <eval> is used for unnamed source executed by GraalVM
          return "Unknown"
        }

        return fileName[0] === "/" ? fileName : "/" + fileName;
    }
});