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
package com.sap.xsk.parser.hdbsequence.models;

import com.google.gson.annotations.SerializedName;
import com.sap.xsk.parser.hdbsequence.utils.HDBSequenceConstants;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class XSKHDBSEQUENCEModel {

  Set<String> missingProps = new HashSet<>();

  String schema;
  Integer increment_by;
  Integer start_with;
  Integer maxvalue;
  Boolean nomaxvalue;
  Integer minvalue;
  Boolean nominvalue;
  Boolean cycles;
  String reset_by;

  @SerializedName(value = HDBSequenceConstants.PUBLIC_PROPERTY)
  Boolean publicc;

  private <T> void checkPresence(T field, String fieldName) {
    if (Objects.isNull(field)) {
      this.missingProps.add(fieldName);
    }
  }

  public boolean hasMandatoryFieldsMissing() {

    checkPresence(this.schema, HDBSequenceConstants.SCHEMA_PROPERTY);
    checkPresence(this.increment_by, HDBSequenceConstants.INCREMENT_BY_PROPERTY);
    checkPresence(this.start_with, HDBSequenceConstants.START_WITH_PROPERTY);
    checkPresence(this.nomaxvalue, HDBSequenceConstants.NOMAXVALUE_PROPERTY);
    checkPresence(this.nominvalue, HDBSequenceConstants.NOMINVALUE_PROPERTY);
    checkPresence(this.publicc, HDBSequenceConstants.PUBLIC_PROPERTY);
    return (this.missingProps.size() > 0) ? true : false;
  }

  public String getSchema() {
    return schema;
  }

  public void setSchema(String schema) {
    this.schema = schema;
  }

  public Integer getIncrement_by() {
    return increment_by;
  }

  public void setIncrement_by(Integer increment_by) {
    this.increment_by = increment_by;
  }

  public Integer getStart_with() {
    return start_with;
  }

  public void setStart_with(Integer start_with) {
    this.start_with = start_with;
  }

  public Integer getMaxvalue() {
    return maxvalue;
  }

  public void setMaxvalue(Integer maxvalue) {
    this.maxvalue = maxvalue;
  }

  public Boolean getNomaxvalue() {
    return nomaxvalue;
  }

  public void setNomaxvalue(Boolean nomaxvalue) {
    this.nomaxvalue = nomaxvalue;
  }

  public Integer getMinvalue() {
    return minvalue;
  }

  public void setMinvalue(Integer minvalue) {
    this.minvalue = minvalue;
  }

  public Boolean getNominvalue() {
    return nominvalue;
  }

  public void setNominvalue(Boolean nominvalue) {
    this.nominvalue = nominvalue;
  }

  public Boolean getCycles() {
    return cycles;
  }

  public void setCycles(Boolean cycles) {
    this.cycles = cycles;
  }

  public String getReset_by() {
    return reset_by;
  }

  public void setReset_by(String reset_by) {
    this.reset_by = reset_by;
  }

  public Boolean getPublicc() {
    return publicc;
  }

  public void setPublicc(Boolean publicc) {
    this.publicc = publicc;
  }

  public Set<String> getMissingProps() {
    return missingProps;
  }
}
