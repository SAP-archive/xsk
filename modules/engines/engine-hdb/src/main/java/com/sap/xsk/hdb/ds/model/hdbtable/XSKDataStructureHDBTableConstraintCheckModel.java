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
package com.sap.xsk.hdb.ds.model.hdbtable;

/**
 * The Data Structure Table Constraint Check Model.
 */
public class XSKDataStructureHDBTableConstraintCheckModel extends XSKDataStructureHDBTableConstraintModel {

  private String expression;

  /**
   * Gets the expression.
   *
   * @return the expression
   */
  public String getExpression() {
    return expression;
  }

  /**
   * Sets the expression.
   *
   * @param expression the new expression
   */
  public void setExpression(String expression) {
    this.expression = expression;
  }

}
