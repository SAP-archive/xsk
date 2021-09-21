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
package com.sap.xsk.hdb.ds.transformer.hdbdd;

/**
 * These types should respond to hanaBuiltInTypes in SymbolTable class
 * See also {@link com.sap.xsk.parser.hdbdd.symbols.SymbolTable}.
 */
public enum CdsHanaTypeEnum {
  NVARCHAR,
  ALPHANUMERIC,
  SMALLINT,
  TINYINT,
  SMALLDECIMAL,
  CLOB,
  BINARY,
  ST_POINT,
  ST_GEOMETRY
}
