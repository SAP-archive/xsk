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
package com.sap.xsk.parser.xsodata.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class XSKHDBXSODATASetting {

  private boolean enableSupportNull;
  private List<String> contentCacheControl = new ArrayList<>();
  private List<String> metadataCacheControl = new ArrayList<>();
  private List<String> hints = new ArrayList<>();
  private String maxRecords;
  private String maxExpandedRecords;

  public boolean isEnableSupportNull() {
    return enableSupportNull;
  }

  public XSKHDBXSODATASetting setEnableSupportNull(boolean enableSupportNull) {
    this.enableSupportNull = enableSupportNull;
    return this;
  }

  public List<String> getContentCacheControl() {
    return contentCacheControl;
  }

  public XSKHDBXSODATASetting setContentCacheControl(List<String> contentCacheControl) {
    this.contentCacheControl = contentCacheControl;
    return this;
  }

  public List<String> getMetadataCacheControl() {
    return metadataCacheControl;
  }

  public XSKHDBXSODATASetting setMetadataCacheControl(List<String> metadataCacheControl) {
    this.metadataCacheControl = metadataCacheControl;
    return this;
  }

  public List<String> getHints() {
    return hints;
  }

  public XSKHDBXSODATASetting setHints(List<String> hints) {
    this.hints = hints;
    return this;
  }

  public String getMaxRecords() {
    return maxRecords;
  }

  public XSKHDBXSODATASetting setMaxRecords(String maxRecords) {
    this.maxRecords = maxRecords;
    return this;
  }

  public String getMaxExpandedRecords() {
    return maxExpandedRecords;
  }

  public XSKHDBXSODATASetting setMaxExpandedRecords(String maxExpandedRecords) {
    this.maxExpandedRecords = maxExpandedRecords;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    XSKHDBXSODATASetting that = (XSKHDBXSODATASetting) o;
    return enableSupportNull == that.enableSupportNull && Objects.equals(contentCacheControl, that.contentCacheControl) && Objects.equals(
        metadataCacheControl, that.metadataCacheControl) && Objects.equals(hints, that.hints) && Objects.equals(maxRecords, that.maxRecords)
        && Objects.equals(maxExpandedRecords, that.maxExpandedRecords);
  }

  @Override
  public int hashCode() {
    return Objects.hash(enableSupportNull, contentCacheControl, metadataCacheControl, hints, maxRecords, maxExpandedRecords);
  }
}
