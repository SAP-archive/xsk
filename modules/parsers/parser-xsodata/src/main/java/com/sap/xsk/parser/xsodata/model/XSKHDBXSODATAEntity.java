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

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class XSKHDBXSODATAEntity {

    private XSKHDBXSODATARepositoryObject repositoryObject;
    private String alias;

    private List<String> withPropertyProjections = new ArrayList<>();
    private List<String> withoutPropertyProjections = new ArrayList<>();

    private List<String> keyList = new ArrayList<>();
    private String keyGenerated;

    private List<XSKHDBXSODATANavigation> navigates = new ArrayList<>();

    private List<XSKHDBXSODATAAggregation> aggregations = new ArrayList<>();
    private XSKHDBXSODATAAggregationType aggregationType;

    private XSKHDBXSODATAParameterType parameterType;
    private XSKHDBXSODATAParameter parameterEntitySet;

    private List<XSKHDBXSODATAModification> modifications = new ArrayList<>();

    private boolean concurrencyToken;
    private List<String> eTags = new ArrayList<>();
}
