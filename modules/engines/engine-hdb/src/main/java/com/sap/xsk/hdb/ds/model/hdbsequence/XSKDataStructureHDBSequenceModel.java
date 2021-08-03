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
package com.sap.xsk.hdb.ds.model.hdbsequence;

import com.sap.xsk.hdb.ds.model.XSKDataStructureModel;
import com.sap.xsk.parser.hdbsequence.utils.HDBSequenceConstants;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class XSKDataStructureHDBSequenceModel extends XSKDataStructureModel {

  private Integer increment_by = HDBSequenceConstants.INCREMENT_BY_DEFAULT_VALUE;
  private Integer start_with = HDBSequenceConstants.START_WITH_DEFAULT_VALUE;
  private Integer maxvalue;
  private Boolean nomaxvalue;
  private Integer minvalue = HDBSequenceConstants.MIN_DEFAULT_VALUE;
  private Boolean nominvalue;
  private Boolean cycles;
  private String reset_by;
  private String depends_on_table;
  private String depends_on_view;

  @Getter(AccessLevel.NONE)
  private Boolean publicc = HDBSequenceConstants.PUBLIC_DEFAULT_VALUE;

  public boolean isPublic() {
    return publicc;
  }

  public void setPublic(boolean publicc) {
    this.publicc = publicc;
  }
}
