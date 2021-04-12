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
import com.sap.xsk.parser.hdbsequence.exceptions.XSKHDBSequenceMissingPropertyException;
import com.sap.xsk.parser.hdbsequence.utils.HDBSequenceConstants;
import java.util.List;
import java.util.Objects;

public class XSKHDBSEQUENCEModel {

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

  public XSKHDBSEQUENCEModel() {
  }

  public XSKHDBSEQUENCEModel(String schema, Integer increment_by, Integer start_with, Integer maxvalue,
      Boolean nomaxvalue, Integer minvalue, Boolean nominvalue, Boolean cycles, String reset_by,
      Boolean publicc)
      throws XSKHDBSequenceMissingPropertyException {
    checkMandatoryFieldsPresence(schema, increment_by, start_with, nomaxvalue, nominvalue, publicc);
    this.schema = schema;
    this.increment_by = increment_by;
    this.start_with = start_with;
    this.maxvalue = maxvalue;
    this.nomaxvalue = nomaxvalue;
    this.minvalue = minvalue;
    this.nominvalue = nominvalue;
    this.cycles = cycles;
    this.reset_by = reset_by;
    this.publicc = publicc;
  }

  private void checkMandatoryFieldsPresence(String schema, Integer increment_by, Integer start_with,
      Boolean nomaxvalue, Boolean nominvalue, Boolean publicc) throws XSKHDBSequenceMissingPropertyException {
    checkPresence(schema, HDBSequenceConstants.SCHEMA_PROPERTY);
    checkPresence(increment_by, HDBSequenceConstants.INCREMENT_BY_PROPERTY);
    checkPresence(start_with, HDBSequenceConstants.START_WITH_PROPERTY);
    checkPresence(nomaxvalue, HDBSequenceConstants.NOMAXVALUE_PROPERTY);
    checkPresence(nominvalue, HDBSequenceConstants.NOMINVALUE_PROPERTY);
    checkPresence(publicc, HDBSequenceConstants.PUBLIC_PROPERTY);

  }

  private <T> void checkPresence(T field, String fieldName) throws XSKHDBSequenceMissingPropertyException {
    if (Objects.isNull(field)) {
      throw new XSKHDBSequenceMissingPropertyException(String.format("Missing mandatory field %s!", fieldName));

    }
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
}
