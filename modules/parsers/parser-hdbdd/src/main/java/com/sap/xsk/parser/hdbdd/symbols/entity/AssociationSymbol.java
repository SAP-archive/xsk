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
package com.sap.xsk.parser.hdbdd.symbols.entity;

import com.sap.xsk.parser.hdbdd.symbols.context.Scope;
import com.sap.xsk.parser.hdbdd.symbols.type.field.FieldSymbol;
import java.util.ArrayList;
import java.util.List;

public class AssociationSymbol extends FieldSymbol {

  private CardinalityEnum cardinality;
  private EntitySymbol target;
  private List<EntityElementSymbol> foreignKeys = new ArrayList<>();
  private boolean isManaged;
  private boolean isKey;
  private boolean isNotNull;

  public AssociationSymbol(String name) {
    super(name);
  }

  public AssociationSymbol(String name, Scope scope) {
    super(name, scope);
  }

  public CardinalityEnum getCardinality() {
    return cardinality;
  }

  public void setCardinality(CardinalityEnum cardinality) {
    this.cardinality = cardinality;
  }

  public List<EntityElementSymbol> getForeignKeys() {
    return foreignKeys;
  }

  public void setForeignKeys(List<EntityElementSymbol> foreignKeys) {
    this.foreignKeys = foreignKeys;
  }

  public EntitySymbol getTarget() {
    return target;
  }

  public void setTarget(EntitySymbol target) {
    this.target = target;
  }

  public void addForeignKey(EntityElementSymbol elementSymbol) {
    this.foreignKeys.add(elementSymbol);
  }

  public boolean isManaged() {
    return isManaged;
  }

  public void setManaged(boolean managed) {
    isManaged = managed;
  }

  public boolean isNotNull() {
    return isNotNull;
  }

  public void setNotNull(boolean notNull) {
    isNotNull = notNull;
  }

  public boolean isKey() {
    return isKey;
  }

  public void setKey(boolean key) {
    isKey = key;
  }
}