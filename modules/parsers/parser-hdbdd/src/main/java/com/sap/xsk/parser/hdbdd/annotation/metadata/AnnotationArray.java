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
import java.util.ArrayList;
import java.util.List;

public class AnnotationArray extends AbstractAnnotationValue {
    private List<AbstractAnnotationValue> values;
    private AbstractAnnotationValue arrValueType;

    public AnnotationArray(AbstractAnnotationValue arrValueType) {
        this(CDSLiteralEnum.ARRAY.getLiteralType());
        this.arrValueType = arrValueType;
    }

    public AnnotationArray(int valueType) {
        super(valueType);
        this.values = new ArrayList<>();
    }

    public List<AbstractAnnotationValue> getValues() {
        return values;
    }

    public void addValue(AbstractAnnotationValue annotationValue) {
        this.values.add(annotationValue);
    }

    public AbstractAnnotationValue getArrValueType() {
        return arrValueType;
    }
}
