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
package com.sap.xsk.xsodata.ds.service;

/**
 * The {@link XSKOData2TransformerException} is thrown in situations when there is an issue when transforming odata file
 */
public class XSKOData2TransformerException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new transformer exception.
     */
    public XSKOData2TransformerException() {
        super();
    }

    /**
     * Instantiates a new transformer exception.
     *
     * @param message the message
     */
    public XSKOData2TransformerException(String message) {
        super(message);
    }

    /**
     * Instantiates a new transformer exception.
     *
     * @param ex the ex
     */
    public XSKOData2TransformerException(Throwable ex) {
        super(ex);
    }

    /**
     * Instantiates a new transformer exception.
     *
     * @param message the message
     * @param ex      the ex
     */
    public XSKOData2TransformerException(String message, Throwable ex) {
        super(message, ex);
    }

}
