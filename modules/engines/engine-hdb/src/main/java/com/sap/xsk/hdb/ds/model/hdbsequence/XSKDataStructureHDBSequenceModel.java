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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class XSKDataStructureHDBSequenceModel extends XSKDataStructureModel {

  private Integer increment_by;
  private Integer start_with;
  private Integer maxvalue;
  private Boolean nomaxvalue;
  private Integer minvalue;
  private Boolean nominvalue;
  private Boolean cycles;
  private String reset_by;
  private List<String> depends_on;
  private List<String> depends_on_table;
  private List<String> depends_on_view;

  private Boolean publicc;
}
