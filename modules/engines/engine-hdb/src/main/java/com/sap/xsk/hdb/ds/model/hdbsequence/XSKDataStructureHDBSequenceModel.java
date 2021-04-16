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
package com.sap.xsk.hdb.ds.model.hdbsequence;

import com.sap.xsk.hdb.ds.model.XSKDataStructureModel;
import java.util.List;

public class XSKDataStructureHDBSequenceModel extends XSKDataStructureModel {

  private Integer increment_by;
  private Integer start_with;
  private Integer maxvalue;
  private Boolean nomaxvalue;
  private Integer minvalue;
  private Boolean nominvalue;
  private Boolean cycles;
  private String reset_by;


  private Boolean publicc;

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
