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
package com.sap.xsk.xssecurestore.ds.api;

public interface IXSKSecureStoreModel {

  String FILE_EXTENSION_XSSECURESTORE = ".xssecurestore";

  String VALUE_APP_USER = "APP";

  String SECURE_STORE_CONTENT_TABLE_NAME = "XSK_SECURE_STORE";

  String SECURE_STORE_VALUE_FIND_STATEMENT = "SELECT * FROM " + SECURE_STORE_CONTENT_TABLE_NAME + "\n" +
      "WHERE STORE_ID=?\n" +
      "AND USER_ID=?\n" +
      "AND DATA_ID=?;";

  String SECURE_STORE_VALUE_DELETE_STATEMENT = "DELETE FROM " + SECURE_STORE_CONTENT_TABLE_NAME + "\n" +
      "WHERE STORE_ID=?\n" +
      "AND USER_ID=?\n" +
      "AND DATA_ID=?;";

  String SECURE_STORE_DELETE_BY_STORE_ID = "DELETE FROM " + SECURE_STORE_CONTENT_TABLE_NAME + "\n" +
      "WHERE STORE_ID=?;";
}
