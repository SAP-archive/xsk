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

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
  public void testModifyXsjsWithSessionUser() throws IOException {
    modifyProjectFilesAssertArrayEqualsWhenModified("testModifySessionUser.xsjs");
  }

  @Test
  public void testModifyXsjsWithoutSessionUser() throws IOException {
    modifyProjectFilesAssertArrayEqualsWhenNoModification("testNoSessionUser.xsjs");
  }

  @Test
  public void testModifyXsjsWithEmptyContent() throws IOException {
    modifyProjectFilesAssertArrayEqualsWhenEmptyContent("testEmpty.xsjs");
  }

  @Test
  public void testModifyXsjslibWithSessionUser() throws IOException {
    modifyProjectFilesAssertArrayEqualsWhenModified("testModifySessionUser.xsjslib");
  }

  @Test
  public void testModifyXsjslibWithoutSessionUser() throws IOException {
    modifyProjectFilesAssertArrayEqualsWhenNoModification("testNoSessionUser.xsjslib");
  }

  @Test
  public void testModifyXsjslibWithEmptyContent() throws IOException {
    modifyProjectFilesAssertArrayEqualsWhenEmptyContent("testEmpty.xsjslib");
  }

  @Test
  public void testModifyHdbprocedureWithSessionUser() throws IOException {
    modifyProjectFilesAssertArrayEqualsWhenModified("testModifySessionUser.hdbprocedure");
  }

  @Test
  public void testModifyHdbprocedureWithoutSessionUser() throws IOException {
    modifyProjectFilesAssertArrayEqualsWhenNoModification("testNoSessionUser.hdbprocedure");
  }

  @Test
  public void testModifyHdbprocedureWithReservedWordRow() throws IOException {
    modifyProjectFilesAssertArrayEqualsWhenModified("testReplaceReservedWordRow.hdbprocedure");
  }

  @Test
  public void testModifyHdbprocedureWithUpdateFromStatement() throws IOException {
    modifyProjectFilesAssertArrayEqualsWhenModified("testModifyUpdateFrom.hdbprocedure");
  }

  @Test
  public void testModifyHdbprocedureWithEmptyContent() throws IOException {
    modifyProjectFilesAssertArrayEqualsWhenEmptyContent("testEmpty.hdbprocedure");
  }

  @Test
  public void testModifyHdbTablefunctionWithSessionUser() throws IOException {
    modifyProjectFilesAssertArrayEqualsWhenModified("testModifySessionUser.hdbtablefunction");
  }

  @Test
  public void testModifyHdbtablefunctionWithoutSessionUser() throws IOException {
    modifyProjectFilesAssertArrayEqualsWhenNoModification("testNoSessionUser.hdbtablefunction");
  }

  @Test
  public void testModifyHdbtablefunctionWithEmptyContent() throws IOException {
    modifyProjectFilesAssertArrayEqualsWhenEmptyContent("testEmpty.hdbtablefunction");
  }

  @Test
  public void testModifyAnalyticprivilegeWithSessionUserModelUriViewPath() throws IOException {
    modifyProjectFilesAssertArrayEqualsWhenModified("testModifyModelUriSessionUserViewPath.analyticprivilege");
  }

  @Test
  public void testModifyAnalyticprivilegeWithoutSessionUser() throws IOException {
    modifyProjectFilesAssertArrayEqualsWhenModified("testNoSessionUser.analyticprivilege");
  }

  @Test
  public void testModifyAnalyticprivilegeWithEmptyContent() throws IOException {
    modifyProjectFilesAssertArrayEqualsWhenEmptyContent("testEmpty.analyticprivilege");
  }

  @Test
  public void testModifyHdbAnalyticprivilegeWithSessionUserModelUriViewPath() throws IOException {
    modifyProjectFilesAssertArrayEqualsWhenModified("testModifyModelUriSessionUserViewPath.hdbanalyticprivilege");
  }

  @Test
  public void testModifyHdbanalyticprivilegeWithoutSessionUser() throws IOException {
    modifyProjectFilesAssertArrayEqualsWhenModified("testNoSessionUser.hdbanalyticprivilege");
  }

  @Test
  public void testModifyHdbanalyticprivilegeWithEmptyContent() throws IOException {
    modifyProjectFilesAssertArrayEqualsWhenEmptyContent("testEmpty.hdbanalyticprivilege");
  }

  @Test
  public void testModifyAnalyticPrivilegeWithWrongFormat() throws IOException {
    modifyProjectFilesAssertArrayEqualsWhenNoModification("testWrongFormat.analyticprivilege");
  }

  @Test
  public void testModifyAnalyticPrivilegeWithoutWhereSql() throws IOException {
    modifyProjectFilesAssertArrayEqualsWhenModified("testNoWhereSql.analyticprivilege");
  }

  @Test
  public void testModifyAnalyticPrivilegeWithEmptyWhereSql() throws IOException {
    modifyProjectFilesAssertArrayEqualsWhenModified("testEmptyWhereSql.analyticprivilege");
  }

  @Test
  public void testModifyAnalyticPrivilegeWithoutModelUri() throws IOException {
    modifyProjectFilesAssertArrayEqualsWhenModified("testNoModelUri.analyticprivilege");
  }

  @Test
  public void testModifyAnalyticPrivilegeWithEmptyModelUri() throws IOException {
    modifyProjectFilesAssertArrayEqualsWhenModified("testEmptyModelUri.analyticprivilege");
  }

  @Test
  public void testModifyAnalyticPrivilegeModelUriRemoveUnnecessaryString() {
    assertEquals("myview", projectFilesModificator.processModelUri("/TEST.VIEWS/calculationviews/myview"));
  }

  @Test
  public void testModifyAnalyticPrivilegeModelUriWhenCorrect() {
    assertEquals("test.tinydb::myview", projectFilesModificator.processModelUri("test.tinydb::myview"));
  }

  @Test
  public void testModifyAnalyticPrivilegeModelUriWhenEmptyString() {
    assertEquals("", projectFilesModificator.processModelUri(""));
  }

  @Test
  public void testModifyAnalyticPrivilegeWhereSql() {
    assertEquals("SELECT \"HANAUserName\" FROM \"CALCVIEW\"",
        projectFilesModificator.processWhereSql("SELECT \"HANAUserName\" FROM \"_SYS_BIC\".\"APP.VIEWS/CALCVIEW\""));
  }

  @Test
  public void testModifyAnalyticPrivilegeWhereSqlWhenCorrect() {
    assertEquals("SELECT \"HANAUserName\" FROM \"CALCVIEW\"",
        projectFilesModificator.processWhereSql("SELECT \"HANAUserName\" FROM \"CALCVIEW\""));
  }

  @Test
  public void testModifyAnalyticPrivilegeWhereSqlWhenEmptyString() {
    assertEquals("", projectFilesModificator.processWhereSql(""));
  }

  @Test
  public void testModifyProjectFilesWithUnsupportedExtension() throws IOException {
    modifyProjectFilesAssertArrayEqualsWhenNoModification("testUnsupported.txt");
  }

  private void modifyProjectFilesAssertArrayEqualsWhenModified(String fileName) throws IOException {
    byte[] expectedModifiedFile = getByteArrayFromResourceName("/META-INF/modificators/expected-results/" + fileName);
    byte[] fileContent = getByteArrayFromResourceName("/META-INF/modificators/files-to-modify/" + fileName);
    List<IFile> projectFiles = getProjectFiles(fileName, fileContent);
    projectFilesModificator.modifyProjectFiles(projectFiles);
    assertArrayEquals(projectFiles.get(0).getContent(), expectedModifiedFile);
  }

  private void modifyProjectFilesAssertArrayEqualsWhenEmptyContent(String fileName) throws IOException {
    List<IFile> projectFiles = getProjectFiles(fileName, new byte[0]);
    projectFilesModificator.modifyProjectFiles(projectFiles);
    assertEquals(projectFiles.get(0).getContent().length, 0);
  }

  private void modifyProjectFilesAssertArrayEqualsWhenNoModification(String fileName) throws IOException {
    byte[] fileContent = getByteArrayFromResourceName("/META-INF/modificators/files-to-modify/" + fileName);
    List<IFile> projectFiles = getProjectFiles(fileName, fileContent);
    projectFilesModificator.modifyProjectFiles(projectFiles);
    assertArrayEquals(projectFiles.get(0).getContent(), fileContent);
  }

  private List<IFile> getProjectFiles(String fileName, byte[] fileContent) {
    List<IFile> projectFiles = new ArrayList<>();
    IFile projectIFile = project.createFile(fileName, fileContent);
    projectFiles.add(projectIFile);
    return projectFiles;
  }

  private byte[] getByteArrayFromResourceName(String resourceName) throws IOException {
    return XSKProjectFilesModificatorTest.class.getResourceAsStream(resourceName).readAllBytes();
  }
}