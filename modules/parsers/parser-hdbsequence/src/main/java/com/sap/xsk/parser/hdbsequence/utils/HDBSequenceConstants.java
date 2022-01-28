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
package com.sap.xsk.parser.hdbsequence.utils;

public final class HDBSequenceConstants {

  public static final String SCHEMA_PROPERTY = "schema";
  public static final String INCREMENT_BY_PROPERTY = "increment_by";
  public static final String START_WITH_PROPERTY = "start_with";
  public static final String MAXVALUE_PROPERTY = "maxvalue";
  public static final String NOMAXVALUE_PROPERTY = "nomaxvalue";
  public static final String MINVALUE_PROPERTY = "minvalue";
  public static final String NOMINVALUE_PROPERTY = "nominvalue";
  public static final String CYCLES_PROPERTY = "cycles";
  public static final String RESET_BY_PROPERTY = "reset_by";
  public static final String PUBLIC_PROPERTY = "public";
  public static final String DEPENDS_ON_TABLE_PROPERTY = "depends_on_table";
  public static final String DEPENDS_ON_VIEW_PROPERTY = "depends_on_view";
  public static final String DEPENDS_ON_PROPERTY = "depends_on";
  public static final int INCREMENT_BY_DEFAULT_VALUE = 1;
  public static final int START_WITH_DEFAULT_VALUE = 1;
  public static final int MIN_DEFAULT_VALUE = 1;
  public static final boolean NOMAXVALUE_DEFAULT_VALUE = false;
  public static final boolean NOMINVALUE_DEFAULT_VALUE = false;
  public static final boolean PUBLIC_DEFAULT_VALUE = false;
  private HDBSequenceConstants() {
  }

}
