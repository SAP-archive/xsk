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
package com.sap.xsk.hdbti.model;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "XSK_IMPORTED_CSV_RECORDS")
public class XSKImportedCSVRecordModel {

  @Id
  @GeneratedValue
  @Column(name = "ID", columnDefinition = "BIGINT", length = 32, nullable = false)
  private Long id;

  @Column(name = "CSV_RECORD_ID", columnDefinition = "VARCHAR", nullable = false)
  private String rowId;

  @Column(name = "TABLE_NAME", columnDefinition = "VARCHAR", nullable = false)
  private String tableName;

  @Column(name = "CSV_LOCATION", columnDefinition = "VARCHAR", nullable = false)
  private String csvLocation;

  @Column(name = "HDBTI_LOCATION", columnDefinition = "VARCHAR", nullable = false)
  private String hdbtiLocation;

  @Column(name = "HASH", columnDefinition = "VARCHAR", nullable = false)
  private String hash;

  @Column(name = "DS_CREATED_AT", columnDefinition = "TIMESTAMP", nullable = false)
  private Timestamp createdAt;


  public XSKImportedCSVRecordModel() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getRowId() {
    return rowId;
  }

  public void setRowId(String rowId) {
    this.rowId = rowId;
  }

  public String getTableName() {
    return tableName;
  }

  public void setTableName(String tableName) {
    this.tableName = tableName;
  }

  public String getHdbtiLocation() {
    return hdbtiLocation;
  }

  public void setHdbtiLocation(String hdbtiLocation) {
    this.hdbtiLocation = hdbtiLocation;
  }

  public String getCsvLocation() {
    return csvLocation;
  }

  public void setCsvLocation(String csvLocation) {
    this.csvLocation = csvLocation;
  }

  public String getHash() {
    return hash;
  }

  public void setHash(String hash) {
    this.hash = hash;
  }

  public Timestamp getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Timestamp createdAt) {
    this.createdAt = createdAt;
  }
}
