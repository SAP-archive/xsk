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
package com.sap.xsk.parser.hdbdd.util;

public class HdbddUtils {

  public static String processEscapedSymbolName(String symbolName) {
      if (symbolName.charAt(0) == '"') {
        return symbolName.substring(1, symbolName.length() - 1);
      }

      return symbolName;
  }
}
