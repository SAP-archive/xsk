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
package com.sap.xsk.utils;

public final class XSKConstants {

  public static final String XSK_HDBPROCEDURE_CREATE = "CREATE ";
  public static final String XSK_HDBPROCEDURE_DROP = "DROP PROCEDURE ";
  public static final String XSK_HDBTABLEFUNCTION_CREATE = "CREATE ";
  public static final String XSK_HDBTABLEFUNCTION_DROP = "DROP FUNCTION ";
  public static final String XSK_HDBVIEW_SYNTAX = "VIEW ";
  public static final String XSK_HDBVIEW_CREATE = "CREATE ";
  public static final String XSK_HDBVIEW_DROP = "DROP VIEW ";
  public static final String XSK_HDBSEQUENCE_SYNTAX = "SEQUENCE ";
  public static final String XSK_HDBSEQUENCE_CREATE = "CREATE ";
  public static final String XSK_HDBSEQUENCE_ALTER = "ALTER ";
  public static final String XSK_HDBSEQUENCE_DROP = "DROP  ";
  public static final String XSK_HDBTABLE_SYNTAX = "COLUMN TABLE ";
  public static final String XSK_HDBTABLE_CREATE = "CREATE ";
  public static final String XSK_HDBTABLE_DROP = "DROP ";
  /**
   * The Unix separator character.
   */
  public static final char UNIX_SEPARATOR = '/';
  /**
   * The Windows separator character.
   */
  public static final char WINDOWS_SEPARATOR = '\\';
  public static final boolean SHOULD_ADD_ESCAPE_SYMBOL_DEFAULT_VALUE = true;

  private XSKConstants() {
  }

}
