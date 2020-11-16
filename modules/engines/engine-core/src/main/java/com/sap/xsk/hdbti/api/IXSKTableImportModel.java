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
package com.sap.xsk.hdbti.api;

public interface IXSKTableImportModel {

    /**
     * File extension for *.hdbti files
     */
    public static final String FILE_EXTENSION_TABLE_IMPORT = ".hdbti";

    public static final String FILE_EXTENSION_CSV = ".csv";

    public static final String TYPE_CSV = "CSV";


    public static final String TYPE_HDBTI = "HDBTI";
}
