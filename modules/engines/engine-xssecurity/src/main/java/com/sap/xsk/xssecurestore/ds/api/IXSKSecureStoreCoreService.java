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
package com.sap.xsk.xssecurestore.ds.api;

import com.sap.xsk.xssecurestore.ds.model.XSKSecureStore;
import com.sap.xsk.xssecurestore.ds.model.XSKSecureStoreContent;
import java.util.List;

public interface IXSKSecureStoreCoreService {

  boolean existsSecureStore(String location) throws XSKSecureStoreException;

  XSKSecureStore createSecureStore(String location, String content) throws XSKSecureStoreException;

  XSKSecureStore getSecureStore(String location) throws XSKSecureStoreException;

  List<XSKSecureStore> getXSKSecureStores() throws XSKSecureStoreException;

  void removeXSKSecureStore(String location) throws XSKSecureStoreException;

  void createSecureStoreValue(String storeId, String userId, String dataId, String value) throws XSKSecureStoreException;

  void updateSecureStoreValue(XSKSecureStoreContent xskSecureStoreContent) throws XSKSecureStoreException;

  XSKSecureStoreContent findSecureStoreContent(String storeId, String userId, String dataId) throws XSKSecureStoreException;

  void deleteSecureStoreValue(String storeId, String userId, String dataId) throws XSKSecureStoreException;

  void deleteSecureStoreValuesByStoreId(String storeId) throws XSKSecureStoreException;

  boolean existsSecureStoreContent(String storeId, String userId, String dataId) throws XSKSecureStoreException;
}
