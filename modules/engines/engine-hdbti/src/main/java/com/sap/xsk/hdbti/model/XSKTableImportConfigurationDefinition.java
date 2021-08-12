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
package com.sap.xsk.hdbti.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class XSKTableImportConfigurationDefinition {

    @EqualsAndHashCode.Include
    private Long id;
    @EqualsAndHashCode.Include
    private String table;
    @EqualsAndHashCode.Include
    private String schema;
    @EqualsAndHashCode.Include
    private String file;

    private String hdbtiFileName;
    private Boolean header = Boolean.FALSE;
    private Boolean useHeaderNames = Boolean.FALSE;
    private String delimField;
    private String delimEnclosing;
    private Boolean distinguishEmptyFromNull = Boolean.TRUE;
    private Map<String, ArrayList<String>> keysAsMap;
}
