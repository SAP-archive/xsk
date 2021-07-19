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
package com.sap.xsk.parser.xsodata.model;

import lombok.*;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Accessors(chain = true)
public class XSKHDBXSODATASetting {

    private boolean enableSupportNull;
    private List<String> contentCacheControl = new ArrayList<>();
    private List<String> metadataCacheControl = new ArrayList<>();
    private List<String> hints = new ArrayList<>();
    private String maxRecords;
    private String maxExpandedRecords;

}
