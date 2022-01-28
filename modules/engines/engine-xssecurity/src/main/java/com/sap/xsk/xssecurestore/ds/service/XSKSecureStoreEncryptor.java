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
package com.sap.xsk.xssecurestore.ds.service;

import com.sap.xsk.xssecurestore.ds.api.IXSKSecureStoreEncryptor;
import org.eclipse.dirigible.api.v3.utils.Base64Facade;

public class XSKSecureStoreEncryptor implements IXSKSecureStoreEncryptor {

  @Override
  public byte[] encode(byte[] input) {
    return Base64Facade.encodeNative(input);
  }

  @Override
  public byte[] decode(byte[] input) {
    return Base64Facade.decodeNative(input);
  }
}
