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
package com.sap.xsk.utils;

public final class XSKCommonsConstants {
    /**
     * The Unix separator character.
     */
    public static final char UNIX_SEPARATOR = '/';
    /**
     * The Windows separator character.
     */
    public static final char WINDOWS_SEPARATOR = '\\';
    /**
     * Problems facade constants
     */
    public static final String MODULE_PARSERS = "Parsers";
    public static final String MODULE_PROCESSORS = "Processors";
    public static final String SOURCE_PUBLISH_REQUEST = "Publish Request";
    public static final String PROGRAM_XSK = "XSK";
    /**
     * Problems facade error type constants
     */
    public static final String PARSER_ERROR = "PARSER";
    public static final String PROCESSOR_ERROR = "PROCESSOR";
    public static final String LEXER_ERROR = "LEXER";
    public static final String SCHEDULER_ERROR = "SCHEDULER";
    /**
     * Problems facade error message constants
     */
    public static final String EXPECTED_FIELDS = "Missing mandatory fields";
    /**
     * Problems facade parser and processor type constants
     */
    public static final String HDBTI_PARSER = "HDBTI";
    public static final String HDB_TABLE_PARSER = "HDB Table";
    public static final String HDB_TABLE_TYPE_PARSER = "HDB Table Type";
    public static final String HDB_SYNONYM_PARSER = "HDB Synonym";
    public static final String HDBDD_PARSER = "HDBDD";
    public static final String HDB_PROCEDURE_PARSER = "HDB Procedure";
    public static final String HDB_TABLE_FUNCTION_PARSER = "HDB Table Function";
    public static final String HDB_SCALAR_FUNCTION_PARSER = "HDB Scalar Function";
    public static final String HDB_SCHEMA_PARSER = "HDB Schema";
    public static final String HDB_SEQUENCE_PARSER = "HDB Sequence";
    public static final String HDB_VIEW_PARSER = "HDB View";
    public static final String XSK_JOB_PARSER = "XSK Job";
    public static final String XSK_ODATA_PARSER = "XSODATA";
    public static final String HDB_ENTITY_PROCESSOR = "HDB Entity";
    public static final String XSK_ENTITY_PROCESSOR = "XSK Entity";
    public static final String HDI_PROCESSOR = "HDI";
}
