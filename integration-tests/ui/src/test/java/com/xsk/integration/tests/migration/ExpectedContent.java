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
package com.xsk.integration.tests.migration;

class ExpectedContent {

  private final String filePath;
  private final byte[] content;

  ExpectedContent(String filePath, byte[] content) {
    this.filePath = filePath;
    this.content = content;
  }

  String getFilePath() {
    return filePath;
  }

  byte[] getContent() {
    return content;
  }

  String getProject() { return filePath.split("/")[1]; }
}
