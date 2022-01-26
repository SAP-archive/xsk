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
package com.sap.xsk.mail;

import org.eclipse.dirigible.api.v3.mail.api.IMailConfigurationProvider;
import java.util.Properties;

public class DestMailConfigProvider implements IMailConfigurationProvider {

  private static final String PROVIDER_NAME = "destination";

  @Override
  public String getName() {
    return PROVIDER_NAME;
  }

  @Override
  public Properties getProperties() {
    Properties properties = new Properties();
    properties.put("PLACEHOLDER", "PLACEHOLDER");
    return properties;
  }
}
