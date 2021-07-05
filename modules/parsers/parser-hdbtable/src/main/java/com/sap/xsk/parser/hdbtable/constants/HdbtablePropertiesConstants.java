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
package com.sap.xsk.parser.hdbtable.constants;

public final class HdbtablePropertiesConstants {
    private HdbtablePropertiesConstants() {}

    public static final String HDBTABLE_PROPERTY_SCHEMA_NAME = "schemaName";
    public static final String HDBTABLE_PROPERTY_TABLE_TYPE = "tableType";
    public static final String HDBTABLE_PROPERTY_COLUMNS = "columns";
    public static final String HDBTABLE_PROPERTY_INDEXES = "indexes";
    public static final String HDBTABLE_PROPERTY_PKCOLUMNS = "pkcolumns";
    public static final String HDBTABLE_PROPERTY_DESCRIPTION = "description";
    public static final String HDBTABLE_PROPERTY_TEMPORARY = "temporary";
    public static final String HDBTABLE_PROPERTY_PUBLIC = "publicProp";
    public static final String HDBTABLE_PROPERTY_LOGGING_TYPE = "loggingType";
    public static final String HDBTABLE_PROPERTY_INDEX_TYPE = "indexType";

    public static final String COLUMN_PROPERTY_NAME ="name";
    public static final String COLUMN_PROPERTY_SQL_TYPE = "sqlType";
    public static final String COLUMN_PROPERTY_NULLABLE = "nullable";
    public static final String COLUMN_PROPERTY_UNIQUE = "unique";
    public static final String COLUMN_PROPERTY_LENGTH = "length";
    public static final String COLUMN_PROPERTY_PRECISION = "precision";
    public static final String COLUMN_PROPERTY_COMMENT = "comment";
    public static final String COLUMN_PROPERTY_SCALE = "scale";
    public static final String COLUMN_PROPERTY_DEFAULT_VALUE = "defaultValue";

    public static final String INDEX_PROPERTY_NAME ="name";
    public static final String INDEX_PROPERTY_UNIQUE ="unique";
    public static final String INDEX_PROPERTY_ORDER ="order";
    public static final String INDEX_PROPERTY_INDEX_TYPE ="indexType";
    public static final String INDEX_PROPERTY_INDEX_COLUMNS ="indexColumns";

}
