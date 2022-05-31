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

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class ExpectedContentProvider {

  private static final ExpectedContentProvider contentProvider = new ExpectedContentProvider();
  private static final String deliveryUnitPath = "/migration";
  private static final String getDeliveryUnitName = "MIGR_TOOLS";
  private static final String workspaceName = "workspace";
  private static final String contentPath = deliveryUnitPath + "/" + getDeliveryUnitName;

  private final Map<String, List<ExpectedContent>> expectedContentList;

  private static ExpectedContentProvider instance;

  private ExpectedContentProvider() {
    expectedContentList = getValidationProjectFiles();
  }

  static ExpectedContentProvider getInstance() {
    if (instance == null) {
      instance = new ExpectedContentProvider();
    }
    return instance;
  }

  private Map<String, List<ExpectedContent>> getValidationProjectFiles() {
    var expectedContentList = new HashMap<String, List<ExpectedContent>>();

    try {
      for (var fileName : getResourceFilePaths(contentPath)) {
        var trimmedFileName = fileName.replace(contentPath, "");
        ExpectedContent file = new ExpectedContent(trimmedFileName, getResourceFileContent(fileName));
        var projectName = file.getProject();

        if(!expectedContentList.containsKey(projectName)) {
          var initialProjectFiles = new ArrayList<ExpectedContent>();
          initialProjectFiles.add(file);
          expectedContentList.put(projectName, initialProjectFiles);
        } else {
          var oldProjectFiles = expectedContentList.get(projectName);
          oldProjectFiles.add(file);
        }
      }
    } catch (IOException e) {
      throw new RuntimeException("Could not load expected files", e);
    }
    return expectedContentList;
  }

  private List<String> getResourceFilePaths(String path) throws IOException {
    List<String> filenames = new ArrayList<>();

    try (
        InputStream in = getResourceAsStream(path);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
      String resource;

      while ((resource = reader.readLine()) != null) {
        boolean isDirectory = resource.indexOf('.') == -1;
        if(isDirectory) {
          var childDirResources = getResourceFilePaths(path + "/" + resource);
          filenames.addAll(childDirResources);
        } else {
          filenames.add(path + "/" + resource);
        }
      }
    }

    return filenames;
  }

  private byte[] getResourceFileContent(String path) throws IOException {
    InputStream in = getResourceAsStream(path);
    BufferedInputStream bis = new BufferedInputStream(in);
    return bis.readAllBytes();
  }

  private InputStream getResourceAsStream(String resource) {
    final InputStream in = getContextClassLoader().getResourceAsStream(resource);
    return in == null ? getClass().getResourceAsStream(resource) : in;
  }

  private ClassLoader getContextClassLoader() {
    return Thread.currentThread().getContextClassLoader();
  }

  Map<String, List<ExpectedContent>> getExpectedContentList() {
    return contentProvider.expectedContentList;
  }

  String getExpectedDeliveryUnitName() {
    return getDeliveryUnitName;
  }

  String getExpectedWorkspaceName() {
    return workspaceName;
  }
}
