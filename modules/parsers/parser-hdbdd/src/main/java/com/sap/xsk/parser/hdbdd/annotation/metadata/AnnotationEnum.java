/*
 * Copyright (c) 2019-2021 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2019-2021 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.parser.hdbdd.annotation.metadata;

import com.sap.xsk.parser.hdbdd.symbols.CDSLiteralEnum;
import java.util.HashSet;
import java.util.Set;

public class AnnotationEnum extends AbstractAnnotationValue {
    private Set<String> enumValues;
    public AnnotationEnum() {
        super(CDSLiteralEnum.ENUM.getLiteralType());
        enumValues = new HashSet<>();
    }

    public void add(String enumValue) {
        this.enumValues.add(enumValue);
    }

    public boolean hasValue(String enumValue) {
        return this.enumValues.contains(enumValue);
    }
}
