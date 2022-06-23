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
package com.sap.xsk.engine;

import org.eclipse.dirigible.commons.api.context.ContextException;
import org.eclipse.dirigible.commons.api.service.AbstractExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.ws.rs.core.Response.Status;

public class XSKContextExceptionHandler extends AbstractExceptionHandler<ContextException> {

  private static final Logger logger = LoggerFactory.getLogger(XSKContextExceptionHandler.class);

  @Override
  public Class<? extends AbstractExceptionHandler<ContextException>> getType() {
    return XSKContextExceptionHandler.class;
  }

  @Override
  protected Logger getLogger() {
    return logger;
  }

  @Override
  protected Status getResponseStatus(ContextException e) {
    return Status.INTERNAL_SERVER_ERROR;
  }

  @Override
  protected String getResponseMessage(ContextException e) {
    return e.getMessage();
  }
}
