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
package com.sap.xsk.api.destination;

import org.eclipse.dirigible.commons.api.helpers.GsonHelper;
import java.util.Properties;

public class Destination {

  private String host;
  private int port;
  private String pathPrefix;
  private Properties properties = new Properties();

  Destination(String host, int port, String pathPrefix, Properties properties) {
    this.host = host;
    this.port = port;
    this.pathPrefix = pathPrefix;
    this.properties = properties;
  }

  public String getHost() {
    return host;
  }

  public int getPort() {
    return port;
  }

  public String getPathPrefix() { return pathPrefix; }

  public Properties getProperties() {
    return properties;
  }

  public String getPropertiesAsJSON() {
    return GsonHelper.GSON.toJson(getProperties());
  }
}
