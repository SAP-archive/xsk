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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XSKProjectFilesModificator {

  private static final Logger logger = LoggerFactory.getLogger(XSKProjectFilesModificator.class);

  private static final String XSLT_RESOURCE_PATH = "META-INF/modificators/xslt/analyticprivilege.xslt";
  private static final List<String> REPLACE_SESSION_USER_FILE_EXTENSIONS = List.of("xsjs", "xsjslib", "hdbprocedure", "hdbtablefunction",
      "analyticprivilege", "hdbanalyticprivilege");
  private static final List<String> ANALYTIC_PRIVILEGE_FILE_EXTENSIONS = List.of("analyticprivilege", "hdbanalyticprivilege");

  public void modifyProjectFiles(List<IFile> projectFiles) throws IOException {
    for (IFile projectFile : projectFiles) {
      modifyProjectFileIfNecessary(projectFile);
    }
  }

  private void modifyProjectFileIfNecessary(IFile projectFile) throws IOException {
    String fileExtension = getProjectFileExtension(projectFile);

    replaceSessionUser(fileExtension, projectFile);
    modifyAnalyticPrivilegeFile(fileExtension, projectFile);
  }

  private void replaceSessionUser(String fileExtension, IFile projectFile) {
    if (REPLACE_SESSION_USER_FILE_EXTENSIONS.contains(fileExtension)) {
      byte[] currentContent = projectFile.getContent();
      projectFile.setContent(new String(currentContent).replace("SESSION_USER", "SESSION_CONTEXT(APPLICATIONUSER)").getBytes());
    }
  }

  private void modifyAnalyticPrivilegeFile(String fileExtension, IFile analyticPrivilegeFile) throws IOException {
    if (ANALYTIC_PRIVILEGE_FILE_EXTENSIONS.contains(fileExtension)) {
      byte[] currentContent = analyticPrivilegeFile.getContent();

      if (currentContent.length != 0) {
        try {
          String xslt = IOUtils.toString(
              XSKProjectFilesModificator.class.getClassLoader().getResourceAsStream(XSLT_RESOURCE_PATH),
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
        } catch (TransformerException exception) {
          String errorMessage = String.format("Analytic privilege [%s] modification has failed due to an error: %s",
              analyticPrivilegeFile.getName(), exception.getMessage());
          logger.error(errorMessage, exception);
        }
      }
    }
  }

  private String getProjectFileExtension(IFile projectFile) {
    return StringUtils.substringAfterLast(projectFile.getName(), ".");
  }

  public static String processModelUri(String value) {
    boolean uriIsNotWithPackageAndFolder = value.indexOf("/") == -1;

    if (uriIsNotWithPackageAndFolder) {
      return value;
    }

    return value.substring(value.lastIndexOf("/") + 1);
  }

  public static String processWhereSql(String value) {
    return value.replaceAll("\"_SYS_BIC\".\"(.*)\\/(.*)\"", "\"$2\"");
  }
}
