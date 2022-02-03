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
package com.sap.xsk.hdb.ds.processors.hdi;

import com.sap.xsk.hdb.ds.model.hdi.XSKDataStructureHDIModel;
import java.sql.Connection;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XSKHDIContainerDropProcessor {

  private static final Logger logger = LoggerFactory.getLogger(XSKHDIContainerDropProcessor.class);

  private XSKHDIContainerDropProcessor() {
  }

  public static void execute(Connection connection, List<XSKDataStructureHDIModel> hdiModels) {
  }

}
