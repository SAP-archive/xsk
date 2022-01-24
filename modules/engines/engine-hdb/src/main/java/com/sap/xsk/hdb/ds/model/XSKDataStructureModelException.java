/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2022 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.hdb.ds.model;

/**
 * Specialized exception for the data models.
 */
public class XSKDataStructureModelException extends Exception {

  private static final long serialVersionUID = 8008932847050301958L;

  /**
   * The default constructor.
   */
  public XSKDataStructureModelException() {
    super();
  }

  /**
   * Overloaded constructor.
   *
   * @param message            the message
   * @param cause              the cause
   * @param enableSuppression  whether to enable suppression
   * @param writableStackTrace whether to enable writable stack trace
   */
  public XSKDataStructureModelException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

  /**
   * Overloaded constructor.
   *
   * @param message the message
   * @param cause   the cause
   */
  public XSKDataStructureModelException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Overloaded constructor.
   *
   * @param message the message
   */
  public XSKDataStructureModelException(String message) {
    super(message);
  }

  /**
   * Overloaded constructor.
   *
   * @param cause the cause
   */
  public XSKDataStructureModelException(Throwable cause) {
    super(cause);
  }

}
