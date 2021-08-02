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
package com.sap.xsk.parser.hdbview.models;

import com.sap.xsk.parser.hdbview.exceptions.XSKHDBViewMissingPropertyException;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Accessors(chain = true)
public class XSKHDBVIEWDefinitionModel {

    @Getter(AccessLevel.NONE)
    private boolean publicProp = true;
    private String schema;
    private String query;
    private List<String> dependsOn;
    private List<String> dependsOnTable;
    private List<String> dependsOnView;

    public boolean isPublic() {
        return publicProp;
    }

    public void setPublic(boolean publicProp) {
        this.publicProp = publicProp;
    }

    public void checkForAllMandatoryFieldsPresence() {
        checkPresence(schema, "schema");
        checkPresence(query, "query");
    }

    private <T> void checkPresence(T field, String fieldName) {
        if (Objects.isNull(field)) {
            throw new XSKHDBViewMissingPropertyException(String.format("Missing mandatory field %s!", fieldName));
        }
    }
}
