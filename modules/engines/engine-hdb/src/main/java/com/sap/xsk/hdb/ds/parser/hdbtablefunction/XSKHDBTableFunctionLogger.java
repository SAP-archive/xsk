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
package com.sap.xsk.hdb.ds.parser.hdbtablefunction;

import com.sap.xsk.utils.XSKCommonsConstants;
import com.sap.xsk.utils.XSKCommonsUtils;

public class XSKHDBTableFunctionLogger {

  public void logError(String parsedFileLocation, String problemsFacadeMessage, String exceptionMessage) {
    XSKCommonsUtils.logCustomErrors(parsedFileLocation,
        XSKCommonsConstants.PARSER_ERROR,
        "",
        "",
        exceptionMessage,
        problemsFacadeMessage,
        XSKCommonsConstants.HDB_TABLE_FUNCTION_PARSER,
        XSKCommonsConstants.MODULE_PARSERS,
        XSKCommonsConstants.SOURCE_PUBLISH_REQUEST,
        XSKCommonsConstants.PROGRAM_XSK);
  }
}
