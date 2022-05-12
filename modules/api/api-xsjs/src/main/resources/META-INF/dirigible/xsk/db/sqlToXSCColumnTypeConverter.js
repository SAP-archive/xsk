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

/*
* Converts XSC DB and HDB API Database types (https://help.sap.com/doc/3de842783af24336b6305a3c0223a369/2.0.03/en-US/$.hdb.html#types) to JDBC SQL Types (https://docs.oracle.com/javase/7/docs/api/constant-values.html#java.sql.Types.ARRAY)
*/

const SQL_TO_XSC_TYPE_MAP = new Map();

SQL_TO_XSC_TYPE_MAP.set(-6, 1); //TYNIINT
SQL_TO_XSC_TYPE_MAP.set(5, 2); //SMALLINT
SQL_TO_XSC_TYPE_MAP.set(4, 3); //INTEGER
SQL_TO_XSC_TYPE_MAP.set(-5, 4); //BIGINT
SQL_TO_XSC_TYPE_MAP.set(3, 5); //DECIMAL
SQL_TO_XSC_TYPE_MAP.set(7, 6); //REAL
SQL_TO_XSC_TYPE_MAP.set(8, 7); //DOUBLE
SQL_TO_XSC_TYPE_MAP.set(1, 8); //CHAR
SQL_TO_XSC_TYPE_MAP.set(12, 9); //VARCHAR
SQL_TO_XSC_TYPE_MAP.set(-15, 10); //NCHAR
SQL_TO_XSC_TYPE_MAP.set(-9, 11); //NVARCHAR
SQL_TO_XSC_TYPE_MAP.set(-2, 12); //BINARY
SQL_TO_XSC_TYPE_MAP.set(-3, 13); //VARBINARY
SQL_TO_XSC_TYPE_MAP.set(91, 14); //DATE
SQL_TO_XSC_TYPE_MAP.set(92, 15); //TIME
SQL_TO_XSC_TYPE_MAP.set(93, 16); //TIMESTAMP
SQL_TO_XSC_TYPE_MAP.set(2005, 25); //CLOB
SQL_TO_XSC_TYPE_MAP.set(2011, 26); //NCLOB
SQL_TO_XSC_TYPE_MAP.set(2004, 27); //BLOB

exports.convert = function (sqlColumnType) {
    if (SQL_TO_XSC_TYPE_MAP.has(sqlColumnType)) {
        return SQL_TO_XSC_TYPE_MAP.get(sqlColumnType);
    }

    return sqlColumnType;
}