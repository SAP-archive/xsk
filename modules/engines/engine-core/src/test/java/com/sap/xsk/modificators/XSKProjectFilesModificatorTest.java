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

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.IOUtils;
import org.eclipse.dirigible.api.v3.platform.WorkspaceFacade;
import org.eclipse.dirigible.core.test.AbstractDirigibleTest;
import org.eclipse.dirigible.core.workspace.api.IFile;
import org.eclipse.dirigible.core.workspace.api.IProject;
import org.eclipse.dirigible.core.workspace.api.IWorkspace;
import org.junit.Before;
import org.junit.Test;

public class XSKProjectFilesModificatorTest extends AbstractDirigibleTest {

  private static IWorkspace workspace;
  private static IProject project;

  private final XSKProjectFilesModificator projectFilesModificator = new XSKProjectFilesModificator();

  @Before
  public void setUp() {
    workspace = WorkspaceFacade.createWorkspace("test");
    project = workspace.createProject("test");
  }

  @Test
  public void modifyProjectFilesSuccessfully() throws IOException {
    List<IFile> projectFiles = new ArrayList<>();

    byte[] xsjsFile = IOUtils.toString(XSKProjectFilesModificatorTest.class.getResourceAsStream("/META-INF/files-to-modify/test.xsjs"),
        StandardCharsets.UTF_8).getBytes();
    IFile xsjsIFile = project.createFile("test.xsjs", xsjsFile);

    byte[] xsjslibFile = IOUtils.toString(
        XSKProjectFilesModificatorTest.class.getResourceAsStream("/META-INF/files-to-modify/test.xsjslib"),
        StandardCharsets.UTF_8).getBytes();
    IFile xsjslibIFile = project.createFile("test.xsjslib", xsjslibFile);

    byte[] hdbprocedureFile = IOUtils.toString(XSKProjectFilesModificatorTest.class.getResourceAsStream(
            "/META-INF/files-to-modify/test.hdbprocedure"),
        StandardCharsets.UTF_8).getBytes();
    IFile hdbprocedureIFile = project.createFile("test.hdbprocedure", hdbprocedureFile);

    byte[] hdbtablefunctionFile = IOUtils.toString(XSKProjectFilesModificatorTest.class.getResourceAsStream(
            "/META-INF/files-to-modify/test.hdbtablefunction"),
        StandardCharsets.UTF_8).getBytes();
    IFile hdbtablefunctionIFile = project.createFile("test.hdbtablefunction", hdbtablefunctionFile);

    byte[] analyticPrivilegeFile = IOUtils.toString(
        XSKProjectFilesModificatorTest.class.getResourceAsStream("/META-INF/files-to-modify/test.analyticprivilege"),
        StandardCharsets.UTF_8).getBytes();
    IFile analyticPrivilegeIFile = project.createFile("test.analyticprivilege", analyticPrivilegeFile);

    byte[] hdbanalyticPrivilegeFile = IOUtils.toString(
        XSKProjectFilesModificatorTest.class.getResourceAsStream("/META-INF/files-to-modify/test.hdbanalyticprivilege"),
        StandardCharsets.UTF_8).getBytes();
    IFile hdbanalyticPrivilegeIFile = project.createFile("test.hdbanalyticprivilege", hdbanalyticPrivilegeFile);

    projectFiles.add(xsjsIFile);
    projectFiles.add(xsjslibIFile);
    projectFiles.add(hdbprocedureIFile);
    projectFiles.add(hdbtablefunctionIFile);
    projectFiles.add(analyticPrivilegeIFile);
    projectFiles.add(hdbanalyticPrivilegeIFile);

    projectFilesModificator.modifyProjectFiles(project, projectFiles);

    byte[] expectedModifiedXsjsFile = XSKProjectFilesModificatorTest.class.getResourceAsStream(
        "/META-INF/expected-results/test.xsjs").readAllBytes();
    byte[] expectedModifiedXsjslibFile = XSKProjectFilesModificatorTest.class.getResourceAsStream(
        "/META-INF/expected-results/test.xsjslib").readAllBytes();
    byte[] expectedModifiedHdbProcedureFile = XSKProjectFilesModificatorTest.class.getResourceAsStream(
        "/META-INF/expected-results/test.hdbprocedure").readAllBytes();
    byte[] expectedModifiedHdbtablefunctionFile = XSKProjectFilesModificatorTest.class.getResourceAsStream(
        "/META-INF/expected-results/test.hdbtablefunction").readAllBytes();
    byte[] expectedModifiedAnalyticprivilegeFile = XSKProjectFilesModificatorTest.class.getResourceAsStream(
        "/META-INF/expected-results/test.analyticprivilege").readAllBytes();
    byte[] expectedModifiedHdbanalyticprivilegeFile = XSKProjectFilesModificatorTest.class.getResourceAsStream(
        "/META-INF/expected-results/test.hdbanalyticprivilege").readAllBytes();

    for (IFile projectFile : projectFiles) {
      switch (projectFile.getName()) {
        case "test.xsjs": {
          assertArrayEquals(projectFile.getContent(), expectedModifiedXsjsFile);
          break;
        }
        case "test.xsjslib": {
          assertArrayEquals(projectFile.getContent(), expectedModifiedXsjslibFile);
          break;
        }
        case "test.hdbprocedure": {
          assertArrayEquals(projectFile.getContent(), expectedModifiedHdbProcedureFile);
          break;
        }
        case "test.hdbtablefunction": {
          assertArrayEquals(projectFile.getContent(), expectedModifiedHdbtablefunctionFile);
          break;
        }
        case "test.analyticprivilege": {
          assertArrayEquals(projectFile.getContent(), expectedModifiedAnalyticprivilegeFile);
          break;
        }
        case "test.hdbanalyticprivilege": {
          assertArrayEquals(projectFile.getContent(), expectedModifiedHdbanalyticprivilegeFile);
          break;
        }
      }
    }
  }

  @Test
  public void modifyProjectFilesWithEmptyContent() {
    List<IFile> projectFiles = new ArrayList<>();

    IFile emptyContentIFile = project.createFile("test.xsjs", new byte[0]);
    projectFiles.add(emptyContentIFile);
    projectFilesModificator.modifyProjectFiles(project, projectFiles);

    assertEquals(projectFiles.get(0).getContent().length, 0);
  }

  @Test
  public void modifyProjectFilesWithWhenAnalyticPrivilegeContentIsEmpty() {
    List<IFile> projectFiles = new ArrayList<>();

    IFile emptyContentAnalyticPrivilegeIFile = project.createFile("test.analyticprivilege", new byte[0]);
    projectFiles.add(emptyContentAnalyticPrivilegeIFile);
    projectFilesModificator.modifyProjectFiles(project, projectFiles);

    assertEquals(projectFiles.get(0).getContent().length, 0);
  }

  @Test
  public void modifyProjectFilesWhenAnalyticPrivilegeIsWrongFormat() {
    List<IFile> projectFiles = new ArrayList<>();

    byte[] analyticPrivilegeFileContent = "Some content.".getBytes();

    IFile analyticPrivilegeIFile = project.createFile("test.analyticprivilege", analyticPrivilegeFileContent);
    projectFiles.add(analyticPrivilegeIFile);
    projectFilesModificator.modifyProjectFiles(project, projectFiles);

    assertArrayEquals(projectFiles.get(0).getContent(), analyticPrivilegeFileContent);
  }

  @Test
  public void modifyProjectFilesWithUnsupportedExtension() {
    List<IFile> projectFiles = new ArrayList<>();

    byte[] textFileContent = "Some content.".getBytes();

    IFile textIFile = project.createFile("test.txt", textFileContent);
    projectFiles.add(textIFile);
    projectFilesModificator.modifyProjectFiles(project, projectFiles);

    assertArrayEquals(projectFiles.get(0).getContent(), textFileContent);
  }

  @Test
  public void modifyProjectFilesWithNoSessionUser() {
    List<IFile> projectFiles = new ArrayList<>();

    byte[] xsjsFileContent = "function sum(a, b) { return a + b; }".getBytes();

    IFile noSessionUserIFile = project.createFile("test.xsjs", xsjsFileContent);
    projectFiles.add(noSessionUserIFile);
    projectFilesModificator.modifyProjectFiles(project, projectFiles);

    assertArrayEquals(projectFiles.get(0).getContent(), xsjsFileContent);
  }

  @Test
  public void modifyProjectAnalyticPrivilegeFileWithoutWhereSql() {
    List<IFile> projectFiles = new ArrayList<>();

    String analyticPrivilegeFileContent =
        "<Privilege:analyticPrivilege xmlns:Privilege=\"http://www.sap.com/ndb/BiModelPrivilege.ecore\" id=\"APGroup\" privilegeType=\"SQL_ANALYTIC_PRIVILEGE\" schemaVersion=\"1.1\">\n"
            + "<descriptions defaultDescription=\"APGroup\"/>\n"
            + "<securedModels>\n"
            + "  <modelUri>test.tinydb::myview</modelUri>\n"
            + "</securedModels>\n"
            + "</Privilege:analyticPrivilege>";

    IFile analyticPrivilegeIFile = project.createFile("test.analyticprivilege", analyticPrivilegeFileContent.getBytes());
    projectFiles.add(analyticPrivilegeIFile);
    projectFilesModificator.modifyProjectFiles(project, projectFiles);

    assertArrayEquals(projectFiles.get(0).getContent(), analyticPrivilegeFileContent.getBytes());
  }

  @Test
  public void modifyAnalyticPrivilegeModelUri() {
    assertEquals("test.tinydb::myview", projectFilesModificator.processModelUri("test.tinydb::myview"));
    assertEquals("myview", projectFilesModificator.processModelUri("/TEST.VIEWS/calculationviews/myview"));
    assertEquals("myview", projectFilesModificator.processModelUri("myview"));
    assertEquals("", projectFilesModificator.processModelUri(""));
  }

  @Test
  public void modifyAnalyticPrivilegeWhereSql() {
    assertEquals("SELECT \"HANAUserName\" FROM \"CALCVIEW\"",
        projectFilesModificator.processWhereSql("SELECT \"HANAUserName\" FROM \"_SYS_BIC\".\"APP.VIEWS/CALCVIEW\""));
    assertEquals("SELECT \"HANAUserName\" FROM \"CALCVIEW\"",
        projectFilesModificator.processWhereSql("SELECT \"HANAUserName\" FROM \"CALCVIEW\""));
    assertEquals("", projectFilesModificator.processWhereSql(""));
  }
}
