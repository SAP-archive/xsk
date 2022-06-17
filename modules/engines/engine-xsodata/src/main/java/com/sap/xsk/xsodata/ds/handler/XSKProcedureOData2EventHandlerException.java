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
package com.sap.xsk.xsodata.ds.handler;

/**
 * The XSK Scripting OData2 Event Handler Exception.
 */
public class XSKProcedureOData2EventHandlerException extends RuntimeException {

  private static final long serialVersionUID = 1570571830962118349L;

  /**
   * Instantiates a new XSK scripting OData2 event handler exception.
   *
   * @param message the message
   * @param cause   the cause
   */
  public XSKProcedureOData2EventHandlerException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Instantiates a new XSK scripting OData2 event handler exception.
   *
   * @param cause the cause
   */
  public XSKProcedureOData2EventHandlerException(Throwable cause) {
    super(cause);
  }

}
