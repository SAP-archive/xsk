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
import java.nio.file.Path;
import java.nio.file.Paths;

class MigrationValidation {

  public static final Path INDEX = Paths.get("src/test/resources/migration/testProject/index.html");
  public static final Path INDEXUI5 = Paths.get("src/test/resources/migration/testProject/indexUI5.html");
  public static final Path XSACCESS = Paths.get("src/test/resources/migration/testProject/.xsaccess");
  public static final Path XSAPP = Paths.get("src/test/resources/migration/testProject/.xsapp");
  public static final Path LOGIC = Paths.get("src/test/resources/migration/testProject/logic.xsjs");
  public static final Path CONTACT_REGULAR = Paths.get("src/test/resources/migration/testProject/images/Contact_regular.png");
  public static final Path CONTACT_HOVER = Paths.get("src/test/resources/migration/testProject/images/Contact_hover.png");
  public static final Path SAPLOGO = Paths.get("src/test/resources/migration/testProject/images/SAPLogo.gif");

  public static final String XPATH_COMMON = "//iframe[@src=\"../ide-monaco/editor.html?file=/workspace/xsk-test-app/";
  public static final String XPATH_INDEX = "index.html&contentType=text/html\"]";
  public static final String XPATH_INDEXUI5 = "indexUI5.html&contentType=text/html\"]";
  public static final String XPATH_XSACCESS = ".xsaccess&contentType=text/plain\"]";
  public static final String XPATH_XSAPP = ".xsapp&contentType=text/plain\"]";
  public static final String XPATH_LOGIC = "logic.xsjs&contentType=application/javascript\"]";

  public static final String IMAGE_URL_COMMON = "http://127.0.0.1:8080/services/v4/ide/workspaces/workspace/xsk-test-app/images/";

  private final String index;
  private final String indexUI5;
  private final String xsaccess;
  private final String xsapp;
  private final String logic;
  private final BufferedImage contactRegular;
  private final BufferedImage contactHover;
  private final BufferedImage sapLogo;

  MigrationValidation() throws IOException {
    index = Files.readString(INDEX);
    indexUI5 = Files.readString(INDEXUI5);
    xsaccess = Files.readString(XSACCESS);
    xsapp = Files.readString(XSAPP);
    logic = Files.readString(LOGIC);
    contactRegular = ImageIO.read(CONTACT_REGULAR.toFile());
    contactHover = ImageIO.read(CONTACT_HOVER.toFile());
    sapLogo = ImageIO.read(SAPLOGO.toFile());
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
