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

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.dirigible.core.workspace.api.IFile;
import org.eclipse.dirigible.core.workspace.api.IProject;

public class XSKProjectFilesModificator {

  public void modifyProjectFiles(IProject project, List<IFile> projectFiles) throws TransformerException, IOException {
    for (IFile projectFile : projectFiles) {
      modifyProjectFileIfNecessary(projectFile, projectFiles);
      addNewProjectFileIfNecessary(project);
      System.out.println(new String(projectFile.getContent()));
    }
  }

  private void modifyProjectFileIfNecessary(IFile projectFile, List<IFile> projectFiles) throws TransformerException, IOException {
    String fileName = projectFile.getName();
    String fileExtension = StringUtils.substringAfterLast(fileName, ".");

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
    //
    // project.createFile()
  }

  private void replaceSessionUser(IFile file) {
    byte[] currentContent = file.getContent();
    file.setContent(new String(currentContent).replace("SESSION_USER", "SESSION_CONTEXT(APPLICATIONUSER)").getBytes());
  }

  private void modifyAnalyticPrivilegeFile(IFile analyticPrivilegeFile) throws TransformerException, IOException {
    byte[] currentContent = analyticPrivilegeFile.getContent();

    String xslt = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\n"
        + "<xsl:stylesheet version=\"2.0\" xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" xmlns:analyticPrivilegeTransformer=\"com.sap.xsk.modificators.XSKProjectFilesModificator\">\n"
        + "\n"
        + "    <xsl:template match=\"node()|@*\">\n"
        + "        <xsl:copy>\n"
        + "          <xsl:apply-templates select=\"node()|@*\"/>\n"
        + "      </xsl:copy>\n"
        + "    </xsl:template>\n"
        + "\n"
        + "    <xsl:template match=\"securedModels/modelUri\">\n"
        + "        <xsl:copy>\n"
        + "         <xsl:value-of select=\"analyticPrivilegeTransformer:processModelUri(string(.))\"/>\n"
        + "      </xsl:copy>\n"
        + "    </xsl:template>\n"
        + "\n"
        + "    <xsl:template match=\"whereSql\">\n"
        + "        <xsl:copy>\n"
        + "         <xsl:value-of select=\"analyticPrivilegeTransformer:processWhereSql(string(.))\"/>\n"
        + "      </xsl:copy>\n"
        + "    </xsl:template>\n"
        + "</xsl:stylesheet>";

    StringWriter writer = new StringWriter();
    StreamResult result = new StreamResult(writer);

    TransformerFactory factory = TransformerFactory.newInstance();
    Source xsltSource = new StreamSource(new StringReader(xslt));
    Transformer transformer = factory.newTransformer(xsltSource);

    Source contentSource = new StreamSource(new StringReader(new String(currentContent)));
    transformer.transform(contentSource, result);

    String xml = writer.toString();

    analyticPrivilegeFile.setContent(xml.getBytes());
  }

  private void modifyHdiFile(IFile hdiFile, List<IFile> files) {
    byte[] currentContent = hdiFile.getContent();

    JsonParser jsonParser = new JsonParser();
    JsonObject hdiContentJson = jsonParser.parse(new String(currentContent)).getAsJsonObject();

    for (IFile file : files) {
      String fileName = file.getName();
      String fileExtension = StringUtils.substringAfterLast(fileName, ".");

      if (fileExtension.equals("analyticprivilege") || fileExtension.equals("hdbanalyticprivilege")) {
        hdiContentJson.getAsJsonArray("deploy").add(file.getPath());
      }
    }

    hdiFile.setContent(hdiContentJson.toString().getBytes());
  }

  public static String processModelUri(String value) {
    int start = 0;

    if (value.indexOf("/") != -1) {
      start = value.lastIndexOf("/") + 1;
    }

    String processedValue = value.substring(start);

    return processedValue;
  }

  public static String processWhereSql(String value) {
    return value.replaceAll("\"_SYS_BIC\".\"(.*)\\/(.*)\"", "\"$2\"");
  }
}
