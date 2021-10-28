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
package com.xsk.integration.tests.migration;

import org.eclipse.dirigible.commons.config.Configuration;

class MigrationCredentials {

  private final String region;
  private final String subaccount;
  private final String username;
  private final String password;
  private final String schema;
  private final String hanaUsername;
  private final String hanaPassword;

  MigrationCredentials(boolean isHana2) {
    region = Configuration.get("ITESTS_SELENIUM_NEO_REGION");
    subaccount = Configuration.get("ITESTS_SELENIUM_NEO_SUBACCOUNT");
    username = Configuration.get("ITESTS_SELENIUM_NEO_USERNAME");
    password = Configuration.get("ITESTS_SELENIUM_NEO_PASSWORD");

    if(isHana2) {
      schema = Configuration.get("ITESTS_SELENIUM_HANA_DB_SCHEMA_2");
    } else {
      schema = Configuration.get("ITESTS_SELENIUM_HANA_DB_SCHEMA");
    }

    hanaUsername = Configuration.get("ITESTS_SELENIUM_HANA_DB_USERNAME");
    hanaPassword = Configuration.get("ITESTS_SELENIUM_HANA_DB_PASSWORD");
  }

  String getRegion() {
    return region;
  }

  String getSubaccount() {
    return subaccount;
  }

  String getUsername() {
    return username;
  }

  String getPassword() {
    return password;
  }

  String getSchema() {
    return schema;
  }

  String getHanaUsername() {
    return hanaUsername;
  }

  public String getHanaPassword() {
    return hanaPassword;
  }

  public String removePasswordsFromString(String input) {
    input = input.replaceAll(getHanaPassword(), "HIDDEN_PASSWORD");
    input = input.replaceAll(getPassword(), "HIDDEN_PASSWORD");
    return input;
  }

}
