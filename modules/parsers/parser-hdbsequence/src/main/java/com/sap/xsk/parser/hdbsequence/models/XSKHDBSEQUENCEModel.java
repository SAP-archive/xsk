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
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
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
  List<String> depends_on;
  List<String> depends_on_table;
  List<String> depends_on_view;

  @SerializedName(value = HDBSequenceConstants.PUBLIC_PROPERTY)
  Boolean publicc;

  private <T> void checkPresence(T field, String fieldName) {
    if (Objects.isNull(field)) {
      this.missingProps.add(fieldName);
    }
  }

  public boolean hasMandatoryFieldsMissing() {

    checkPresence(this.schema, HDBSequenceConstants.SCHEMA_PROPERTY);
//    Removed for backwards compatibility
//    checkPresence(this.increment_by, HDBSequenceConstants.INCREMENT_BY_PROPERTY);
//    checkPresence(this.start_with, HDBSequenceConstants.START_WITH_PROPERTY);
//    checkPresence(this.nomaxvalue, HDBSequenceConstants.NOMAXVALUE_PROPERTY);
//    checkPresence(this.nominvalue, HDBSequenceConstants.NOMINVALUE_PROPERTY);
//    checkPresence(this.publicc, HDBSequenceConstants.PUBLIC_PROPERTY);
    return (this.missingProps.size() > 0) ? true : false;
  }
}
