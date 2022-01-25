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
import com.sap.xsk.utils.XSKCommonsConstants;
import com.sap.xsk.utils.XSKCommonsUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.dirigible.core.workspace.api.IFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XSKProjectFilesModificator {

  private static final Logger logger = LoggerFactory.getLogger(XSKProjectFilesModificator.class);

  private static final String CALC_VIEW_REFERENCE_MATCH_PATTERN = "\"_SYS_BIC\".\"(.*)\\/(.*)\"";
  private static final String XSLT_RESOURCE_PATH = "META-INF/modificators/xslt/analyticprivilege.xslt";
  private static final List<String> REPLACE_SESSION_USER_FILE_EXTENSIONS = List.of("xsjs", "xsjslib", "hdbprocedure", "hdbtablefunction",
      "analyticprivilege", "hdbanalyticprivilege");
  private static final List<String> ANALYTIC_PRIVILEGE_FILE_EXTENSIONS = List.of("analyticprivilege", "hdbanalyticprivilege");

  /**
   * Modify a list of delivery unit project files during the migration process.
   *
   * @param projectFiles the list of project files which will be modified
   */
  public void modifyProjectFiles(List<IFile> projectFiles) throws IOException {
    for (IFile projectFile : projectFiles) {
      String fileExtension = getProjectFileExtension(projectFile);
      replaceSessionUser(fileExtension, projectFile);
      modifyAnalyticPrivilegeFile(fileExtension, projectFile);
    }
  }

  /**
   * Replace SESSION_USER in the content of the specified file.
   *
   * @param fileExtension the extension of the file being modified
   * @param projectFile   the file being modified
   */
  private void replaceSessionUser(String fileExtension, IFile projectFile) {
    if (REPLACE_SESSION_USER_FILE_EXTENSIONS.contains(fileExtension)) {
      byte[] currentContent = projectFile.getContent();
      projectFile.setContent(new String(currentContent).replace("SESSION_USER", "SESSION_CONTEXT(APPLICATIONUSER)").getBytes());
    }
  }

  /**
   * Make modifications on the modelUri and whereSql elements of an analytic privilege file.
   *
   * @param fileExtension         the extension of the file being modified
   * @param analyticPrivilegeFile the file being modified
   */
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
          logger.error("Analytic privilege" + analyticPrivilegeFile.getName() + "modification has failed due to an error.", exception);
          XSKCommonsUtils.logCustomErrors(analyticPrivilegeFile.getPath(), XSKCommonsConstants.MIGRATION_ERROR, "", "",
              exception.getMessage(),
              "", XSKCommonsConstants.HDB_ANALYTIC_PRIVILEGE, XSKCommonsConstants.MODULE_PARSERS,
              XSKCommonsConstants.SOURCE_DELIVERY_UNIT_MIGRATION, XSKCommonsConstants.PROGRAM_XSK);
        }
      }
    }
  }

  /**
   * Get the extension of the specified file.
   *
   * @param projectFile the file to get extension for
   * @return the file extension string
   */
  private String getProjectFileExtension(IFile projectFile) {
    return StringUtils.substringAfterLast(projectFile.getName(), ".");
  }

  /**
   * Modify analytic privilege file modelUri element by removing the reference of calculation view for the / syntax.
   *
   * @param value the value of the modelUri element
   * @return the modified value of the modelUri
   */
  public static String processModelUri(String value) {
    boolean uriIsNotWithPackageAndFolder = value.indexOf("/") == -1;

    if (uriIsNotWithPackageAndFolder) {
      return value;
    }

    return value.substring(value.lastIndexOf("/") + 1);
  }

  /**
   * Modify analytic privilege file whereSql element by removing calculation view references.
   *
   * @param value the value of the whereSql element
   * @return the modified value of the whereSql on regex pattern match for the second group
   */
  public static String processWhereSql(String value) {
    return value.replaceAll(CALC_VIEW_REFERENCE_MATCH_PATTERN, "\"$2\"");
  }
}
