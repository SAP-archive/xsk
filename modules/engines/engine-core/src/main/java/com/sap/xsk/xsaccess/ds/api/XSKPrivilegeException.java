/*
 * Copyright (c) 2019-2020 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2019-2020 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.xsaccess.ds.api;

public class XSKPrivilegeException extends Exception {

    public XSKPrivilegeException() {
        super();
    }

    public XSKPrivilegeException(String message) {
        super(message);
    }

    public XSKPrivilegeException(Throwable cause) {
        super(cause);
    }
}