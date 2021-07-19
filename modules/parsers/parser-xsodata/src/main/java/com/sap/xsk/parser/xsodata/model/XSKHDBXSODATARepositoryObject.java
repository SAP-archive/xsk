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

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Accessors(chain = true)
public class XSKHDBXSODATARepositoryObject {
    /**
     * Catalog object name
     * The name can represent repoobject or catalogobject.
     * Repoobject is represented as "repopackage '/' reponame '.' repoextension"
     * * <pre>{@code
     *      service {
     *          "teamdemo/MY_REPO_NAME.REPO_NAME" as "MyTable";
     *      }
     * }</pre>
     * Catalogobject is represented as "catalogobjectschema '.' catalogobjectname"
     * <pre>{@code
     *      service {
     *           "MY_SCHEMA"."sample.odata::table" as "MyTable";
     *      }
     * }</pre>
     */
    private String catalogObjectName;
    private String catalogObjectSchema;

}