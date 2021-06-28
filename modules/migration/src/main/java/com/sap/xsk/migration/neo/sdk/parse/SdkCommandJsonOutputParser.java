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
package com.sap.xsk.migration.neo.sdk.parse;

//import com.google.gson.reflect.TypeToken;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.eclipse.dirigible.commons.api.helpers.GsonHelper;
import java.lang.reflect.Type;

class SdkCommandJsonOutputParser implements SdkCommandOutputParser {

  @Override
  public <TCommandRes> SdkCommandParsedOutput<TCommandRes> parse(String output, TypeToken<SdkCommandParsedOutput<TCommandRes>> commandResultType) {
    SdkCommandParsedOutput<TCommandRes> res = new Gson().fromJson(output, commandResultType.getType());
    return res;
  }
}
