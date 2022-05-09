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
exports.getFileTypes = function () {
    return {
        ".xsjs": "javascript",
        ".xsjslib": "javascript",
        ".hdbsynonym": "json",
        ".hdbpublicsynonym": "json",
        ".hdi": "json",
        ".hdiconfig": "json",
        ".hdbcalculationview": "xml",
        ".hdbtable": "sql",
        ".hdbview": "sql",
        ".hdbprocedure": "sql",
        ".hdbtablefunction": "sql",
        ".hdbschema": "sql",
        ".hdbdd": "sql",
        ".hdbflowgraph": "xml",
        ".xsodata": "sql",
        ".xsaccess": "json",
        ".xsprivileges": "json",
        ".csvim": "json",
        ".calculationview": "xml"
    }
};