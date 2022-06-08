/*
 * Copyright (c) 2022 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2022 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package models;

import exceptions.ProcedureMissingPropertyException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProcedureDefinitionModel extends DefinitionModel {

  private List<UpdateStatementDefinitionModel> updateStatements;

  public ProcedureDefinitionModel(String schema, String name) {
    super(schema, name);
    this.updateStatements = new ArrayList<>();
  }

  public List<UpdateStatementDefinitionModel> getUpdateStatements() {
    return updateStatements;
  }

  public void addUpdateStatement(UpdateStatementDefinitionModel updateStatement) {
    this.updateStatements.add(updateStatement);
  }

  public void checkForAllMandatoryFieldsPresence() {
    checkPresence(this.getName(), "name");
  }

  private <T> void checkPresence(T field, String fieldName) {
    if (Objects.isNull(field)) {
      throw new ProcedureMissingPropertyException("Missing mandatory field " + fieldName);
    }
  }
}
