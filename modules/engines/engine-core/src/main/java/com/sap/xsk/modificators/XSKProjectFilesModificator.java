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
package com.sap.xsk.modificators;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.dirigible.core.workspace.api.IFile;
import org.eclipse.dirigible.core.workspace.api.IProject;

public class XSKProjectFilesModificator {

  public void modifyProjectFiles(IProject project, List<IFile> projectFiles) throws TransformerException, IOException {
    for (IFile projectFile : projectFiles) {
      modifyProjectFileIfNecessary(projectFile, projectFiles);
      addNewProjectFileIfNecessary(project);
    }
  }

  private void modifyProjectFileIfNecessary(IFile projectFile, List<IFile> projectFiles) throws TransformerException, IOException {
    String fileExtension = getProjectFileExtension(projectFile);

    switch (fileExtension) {
      case "xsjs":
      case "xsjslib":
      case "hdbprocedure":
      case "hdbtablefunction": {
        replaceSessionUser(projectFile);
        break;
      }
      case "analyticprivilege":
      case "hdbanalyticprivilege": {
        replaceSessionUser(projectFile);
        modifyAnalyticPrivilegeFile(projectFile);
        break;
      }
      case "hdi": {
        modifyHdiFile(projectFile, projectFiles);
        break;
      }
    }
  }

  private void addNewProjectFileIfNecessary(IProject project) {
    // // public IFile createFile(String path, byte[] content) {}
    // // public IFile createFile(String path, byte[] content, boolean isBinary, String contentType) {}

    // project.createFile()
  }

  private void replaceSessionUser(IFile projectFile) {
    byte[] currentContent = projectFile.getContent();
    projectFile.setContent(new String(currentContent).replace("SESSION_USER", "SESSION_CONTEXT(APPLICATIONUSER)").getBytes());
  }

  private void modifyAnalyticPrivilegeFile(IFile analyticPrivilegeFile) throws TransformerException, IOException {
    byte[] currentContent = analyticPrivilegeFile.getContent();

    String xslt = IOUtils.toString(
        XSKProjectFilesModificator.class.getClassLoader().getResourceAsStream("META-INF/xslt/analyticprivilege.xslt"),
        StandardCharsets.UTF_8);

    StringWriter writer = new StringWriter();
    StreamResult result = new StreamResult(writer);

    TransformerFactory factory = TransformerFactory.newInstance();
    Source xsltSource = new StreamSource(new StringReader(xslt));
    Transformer transformer = factory.newTransformer(xsltSource);

    Source contentSource = new StreamSource(new StringReader(new String(currentContent)));
    transformer.transform(contentSource, result);

    String processedAnalyticPrivilege = writer.toString();

    analyticPrivilegeFile.setContent(processedAnalyticPrivilege.getBytes());
  }

  private void modifyHdiFile(IFile hdiFile, List<IFile> projectFiles) {
    byte[] currentContent = hdiFile.getContent();

    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    JsonParser jsonParser = new JsonParser();
    JsonObject hdiContentJson = jsonParser.parse(new String(currentContent)).getAsJsonObject();

    for (IFile projectFile : projectFiles) {
      String fileExtension = getProjectFileExtension(projectFile);

      if (fileExtension.equals("analyticprivilege") || fileExtension.equals("hdbanalyticprivilege")) {
        hdiContentJson.getAsJsonArray("deploy").add(projectFile.getPath());
      }
    }

    hdiFile.setContent(gson.toJson(hdiContentJson).getBytes());
  }

  private String getProjectFileExtension(IFile projectFile) {
    return StringUtils.substringAfterLast(projectFile.getName(), ".");
  }

  public static String processModelUri(String value) {
    int start = 0;

    if (value.indexOf("/") != -1) {
      start = value.lastIndexOf("/") + 1;
    }

    return value.substring(start);
  }

  public static String processWhereSql(String value) {
    return value.replaceAll("\"_SYS_BIC\".\"(.*)\\/(.*)\"", "\"$2\"");
  }
}
