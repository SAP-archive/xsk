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
package com.sap.xsk.auditlog.client;

import com.sap.xsk.auditlog.client.exceptions.ServiceException;
import com.sap.xsk.auditlog.client.logs.Log;
import com.sap.xsk.auditlog.client.messages.AuditLogMessage;
import java.time.Instant;
import java.util.List;

public interface AuditLogClient {

  void log(AuditLogMessage log) throws ServiceException;

  List<Log> getLogs() throws ServiceException;

  List<Log> getLogs(Instant from, Instant to) throws ServiceException;
}