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
package com.sap.xsk.parser.hdbdd.symbols;

import com.sap.xsk.parser.hdbdd.core.CdsLexer;

public enum CDSLiteralEnum {
    INTEGER(CdsLexer.INTEGER),
    DECIMAL(CdsLexer.DECIMAL),
    STRING(CdsLexer.STRING),
    BOOLEAN(CdsLexer.BOOLEAN),
    OBJECT(4),
    ARRAY(5),
    ENUM(6);

    private int literalType;

    CDSLiteralEnum(int literalType) {
        this.literalType = literalType;
    }

    public int getLiteralType() {
        return literalType;
    }
}
