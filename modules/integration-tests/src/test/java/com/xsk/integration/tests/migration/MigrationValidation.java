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

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MigrationValidation {
  private final String index;
  private final String indexUI5;
  private final String xsaccess;
  private final String xsapp;
  private final String logic;
  private final String contactRegular;
  private final String contactHover;
  private final String sapLogo;

  public MigrationValidation() throws IOException {
    index = Files.readString(Paths.get("src/test/resources/migration/testProject/index.html"));
    indexUI5 = Files.readString(Paths.get("src/test/resources/migration/testProject/indexUI5.html"));
    xsaccess = Files.readString(Paths.get("src/test/resources/migration/testProject/.xsaccess"));
    xsapp = Files.readString(Paths.get("src/test/resources/migration/testProject/.xsapp"));
    logic = Files.readString(Paths.get("src/test/resources/migration/testProject/logic.xsjs"));
    contactRegular = readFileUTF8(Paths.get("src/test/resources/migration/testProject/images/Contact_regular.png"));
    contactHover = readFileUTF8(Paths.get("src/test/resources/migration/testProject/images/Contact_hover.png"));
    sapLogo = readFileUTF8(Paths.get("src/test/resources/migration/testProject/images/SAPLogo.gif"));
  }

  @SuppressWarnings("ReadWriteStringCanBeUsed")
  private String readFileUTF8(Path path) throws IOException {
    return new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
  }

  public String getIndex() {
    return index;
  }

  public String getIndexUI5() {
    return indexUI5;
  }

  public String getXsaccess() {
    return xsaccess;
  }

  public String getXsapp() {
    return xsapp;
  }

  public String getLogic() {
    return logic;
  }

  public String getContactRegular() {
    return contactRegular;
  }

  public String getContactHover() {
    return contactHover;
  }

  public String getSapLogo() {
    return sapLogo;
  }
}
