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
package com.sap.xsk.migration.neo.sdk.command;

import java.util.ArrayList;
import java.util.List;

public class SdkCommandGenericArgs implements SdkCommandArgs {

  private final String account;
  private final String host;
  private final String user;
  private final String password;
  private final boolean shouldOutputJson;

  public SdkCommandGenericArgs(String account, String host, String user, String password) {
    this(account, host, user, password, true);
  }

  public SdkCommandGenericArgs(String account, String host, String user, String password, boolean shouldOutputJson) {
    this.account = account;
    this.host = host;
    this.user = user;
    this.password = password;
    this.shouldOutputJson = shouldOutputJson;
  }

  @Override
  public List<String> commandLineArgs() {
    var commandLineArgs = new ArrayList<String>();
    commandLineArgs.add("--account " + account);
    commandLineArgs.add("--host " + host);
    commandLineArgs.add("--user " + user);
    commandLineArgs.add("--password " + password);

    if (shouldOutputJson) {
      commandLineArgs.add("--output json");
    }

    return commandLineArgs;
  }
}
