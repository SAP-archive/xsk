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
package com.sap.xsk.xssecurestore.ds.model;

import com.sap.xsk.xssecurestore.ds.api.IXSKSecureStoreModel;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = IXSKSecureStoreModel.SECURE_STORE_CONTENT_TABLE_NAME)
public class XSKSecureStoreContent {

  @Id
  @GeneratedValue
  @Column(name = "KEY", columnDefinition = "BIGINT", length = 32, nullable = false)
  private long id;

  @Column(name = "STORE_ID", columnDefinition = "VARCHAR", nullable = false, length = 255)
  private String storeId;

  @Column(name = "USER_ID", columnDefinition = "VARCHAR", nullable = false, length = 255)
  private String userId;

  @Column(name = "DATA_ID", columnDefinition = "VARCHAR", nullable = false, length = 255)
  private String dataId;

  @Column(name = "DATA_VALUE", columnDefinition = "BLOB", nullable = false, length = 5000)
  private byte[] dataValue;

  public XSKSecureStoreContent() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getStoreId() {
    return storeId;
  }

  public void setStoreId(String storeId) {
    this.storeId = storeId;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getDataId() {
    return dataId;
  }

  public void setDataId(String dataId) {
    this.dataId = dataId;
  }

  public byte[] getDataValue() {
    return dataValue;
  }

  public void setDataValue(byte[] dataValue) {
    this.dataValue = dataValue;
  }
}
