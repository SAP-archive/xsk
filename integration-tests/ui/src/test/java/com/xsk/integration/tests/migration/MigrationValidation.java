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

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

class MigrationValidation {

  private final String index;
  private final String indexUI5;
  private final String xsaccess;
  private final String xsapp;
  private final String logic;
  private final BufferedImage contactRegular;
  private final BufferedImage contactHover;
  private final BufferedImage sapLogo;

  MigrationValidation() throws IOException {
    index = Files.readString(Paths.get("src/test/resources/migration/testProject/index.html"));
    indexUI5 = Files.readString(Paths.get("src/test/resources/migration/testProject/indexUI5.html"));
    xsaccess = Files.readString(Paths.get("src/test/resources/migration/testProject/.xsaccess"));
    xsapp = Files.readString(Paths.get("src/test/resources/migration/testProject/.xsapp"));
    logic = Files.readString(Paths.get("src/test/resources/migration/testProject/logic.xsjs"));
    contactRegular = ImageIO.read(Paths.get("src/test/resources/migration/testProject/images/Contact_regular.png").toFile());
    contactHover = ImageIO.read(Paths.get("src/test/resources/migration/testProject/images/Contact_hover.png").toFile());
    sapLogo = ImageIO.read(Paths.get("src/test/resources/migration/testProject/images/SAPLogo.gif").toFile());
  }

  String getIndex() {
    return index;
  }

  String getIndexUI5() {
    return indexUI5;
  }

  String getXsaccess() {
    return xsaccess;
  }

  String getXsapp() {
    return xsapp;
  }

  String getLogic() {
    return logic;
  }

  BufferedImage getContactRegular() {
    return contactRegular;
  }

  BufferedImage getContactHover() {
    return contactHover;
  }

  BufferedImage getSapLogo() {
    return sapLogo;
  }
}
