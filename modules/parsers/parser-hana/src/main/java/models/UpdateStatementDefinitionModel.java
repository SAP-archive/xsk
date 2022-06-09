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

public class UpdateStatementDefinitionModel extends TableReferenceModel{

  private String rawContent;

  private FromClauseDefinitionModel fromClause;
  private WhereClauseDefinitionModel whereClause;
  private UpdateSetClauseDefinitionModel updateSetClause;

  public String getRawContent() {
    return rawContent;
  }

  public void setRawContent(String rawContent) {
    this.rawContent = rawContent;
  }

  public FromClauseDefinitionModel getFromClause() {
    return fromClause;
  }

  public void setFromClause(FromClauseDefinitionModel fromClause) {
    this.fromClause = fromClause;
  }

  public WhereClauseDefinitionModel getWhereClause() {
    return whereClause;
  }

  public void setWhereClause(WhereClauseDefinitionModel whereClause) {
    this.whereClause = whereClause;
  }

  public UpdateSetClauseDefinitionModel getUpdateSetClause() {
    return updateSetClause;
  }

  public void setUpdateSetClause(UpdateSetClauseDefinitionModel updateSetClause) {
    this.updateSetClause = updateSetClause;
  }
}
