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
package com.sap.xsk.parser.hdbsequence.models;

import com.google.gson.annotations.SerializedName;
import com.sap.xsk.parser.hdbsequence.utils.HDBSequenceConstants;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class XSKHDBSEQUENCEModel {

  private Set<String> missingProps = new HashSet<>();

  @SerializedName(value = HDBSequenceConstants.SCHEMA_PROPERTY)
  private String schema;

  @SerializedName(value = HDBSequenceConstants.INCREMENT_BY_PROPERTY)
  private Integer incrementBy = HDBSequenceConstants.INCREMENT_BY_DEFAULT_VALUE;

  @SerializedName(value = HDBSequenceConstants.START_WITH_PROPERTY)
  private Integer startWith = HDBSequenceConstants.START_WITH_DEFAULT_VALUE;

  @SerializedName(value = HDBSequenceConstants.MAXVALUE_PROPERTY)
  private Integer maxValue;

  @SerializedName(value = HDBSequenceConstants.NOMAXVALUE_PROPERTY)
  private Boolean noMaxValue;

  @SerializedName(value = HDBSequenceConstants.MINVALUE_PROPERTY)
  private Integer minValue = HDBSequenceConstants.MIN_DEFAULT_VALUE;

  @SerializedName(value = HDBSequenceConstants.NOMINVALUE_PROPERTY)
  private Boolean noMinValue;

  @SerializedName(value = HDBSequenceConstants.CYCLES_PROPERTY)
  private Boolean cycles;

  @SerializedName(value = HDBSequenceConstants.RESET_BY_PROPERTY)
  private String resetBy;

  @SerializedName(value = HDBSequenceConstants.DEPENDS_ON_TABLE_PROPERTY)
  private String dependsOnTable;

  @SerializedName(value = HDBSequenceConstants.DEPENDS_ON_VIEW_PROPERTY)
  private String dependsOnView;

  @SerializedName(value = HDBSequenceConstants.PUBLIC_PROPERTY)
  private Boolean publicProp = HDBSequenceConstants.PUBLIC_DEFAULT_VALUE;

  public Set<String> getMissingProps() {
    return missingProps;
  }

  public void setMissingProps(Set<String> missingProps) {
    this.missingProps = missingProps;
  }

  public String getSchema() {
    return schema;
  }

  public void setSchema(String schema) {
    this.schema = schema;
  }

  public Integer getIncrementBy() {
    return incrementBy;
  }

  public void setIncrementBy(Integer incrementBy) {
    this.incrementBy = incrementBy;
  }

  public Integer getStartWith() {
    return startWith;
  }

  public void setStartWith(Integer startWith) {
    this.startWith = startWith;
  }

  public Integer getMaxValue() {
    return maxValue;
  }

  public void setMaxValue(Integer maxValue) {
    this.maxValue = maxValue;
  }

  public Boolean getNoMaxValue() {
    return noMaxValue;
  }

  public void setNoMaxValue(Boolean noMaxValue) {
    this.noMaxValue = noMaxValue;
  }

  public Integer getMinValue() {
    return minValue;
  }

  public void setMinValue(Integer minValue) {
    this.minValue = minValue;
  }

  public Boolean getNoMinValue() {
    return noMinValue;
  }

  public void setNoMinValue(Boolean noMinValue) {
    this.noMinValue = noMinValue;
  }

  public Boolean getCycles() {
    return cycles;
  }

  public void setCycles(Boolean cycles) {
    this.cycles = cycles;
  }

  public String getResetBy() {
    return resetBy;
  }

  public void setResetBy(String resetBy) {
    this.resetBy = resetBy;
  }

  public String getDependsOnTable() {
    return dependsOnTable;
  }

  public void setDependsOnTable(String dependsOnTable) {
    this.dependsOnTable = dependsOnTable;
  }

  public String getDependsOnView() {
    return dependsOnView;
  }

  public void setDependsOnView(String dependsOnView) {
    this.dependsOnView = dependsOnView;
  }

  public void setPublic(Boolean publicProp) {
    this.publicProp = publicProp;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    XSKHDBSEQUENCEModel that = (XSKHDBSEQUENCEModel) o;
    return Objects.equals(missingProps, that.missingProps) && Objects.equals(schema, that.schema) && Objects.equals(incrementBy,
        that.incrementBy) && Objects.equals(startWith, that.startWith) && Objects.equals(maxValue, that.maxValue) && Objects.equals(
        noMaxValue, that.noMaxValue) && Objects.equals(minValue, that.minValue) && Objects.equals(noMinValue, that.noMinValue)
        && Objects.equals(cycles, that.cycles) && Objects.equals(resetBy, that.resetBy) && Objects.equals(dependsOnTable,
        that.dependsOnTable) && Objects.equals(dependsOnView, that.dependsOnView) && Objects.equals(publicProp, that.publicProp);
  }

  @Override
  public int hashCode() {
    return Objects.hash(missingProps, schema, incrementBy, startWith, maxValue, noMaxValue, minValue, noMinValue, cycles, resetBy,
        dependsOnTable, dependsOnView, publicProp);
  }

  private <T> void checkPresence(T field, String fieldName) {
    if (Objects.isNull(field)) {
      this.missingProps.add(fieldName);
    }
  }

  public boolean hasMandatoryFieldsMissing() {
    checkPresence(this.schema, HDBSequenceConstants.SCHEMA_PROPERTY);
    return (this.missingProps.size() > 0) ? true : false;
  }
}
