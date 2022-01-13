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
package com.sap.xsk.test.modificators;

import com.sap.xsk.modificators.XSKProjectFilesModificator;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import javax.xml.transform.TransformerException;
import org.eclipse.dirigible.api.v3.platform.WorkspaceFacade;
import org.eclipse.dirigible.core.test.AbstractDirigibleTest;
import org.eclipse.dirigible.core.workspace.api.IFile;
import org.eclipse.dirigible.core.workspace.api.IProject;
import org.eclipse.dirigible.core.workspace.api.IWorkspace;
import org.junit.Test;

public class XSKProjectFilesModificatorTest extends AbstractDirigibleTest {

  private final XSKProjectFilesModificator projectFilesModificator = new XSKProjectFilesModificator();

  @Test
  public void transformProject() throws IOException, TransformerException {

    IWorkspace workspace = WorkspaceFacade.createWorkspace("test");
    IProject project = workspace.createProject("test");

    List<IFile> projectFiles = new ArrayList<>();

    byte[] xsjsFile = org.apache.commons.io.IOUtils.toString(XSKProjectFilesModificatorTest.class.getResourceAsStream("/test.xsjs"),
        StandardCharsets.UTF_8).getBytes();
    IFile xsjsIFile = project.createFile("test.xsjs", xsjsFile);

    byte[] analyticPrivilegeFile = org.apache.commons.io.IOUtils.toString(
        XSKProjectFilesModificatorTest.class.getResourceAsStream("/test.analyticprivilege"),
        StandardCharsets.UTF_8).getBytes();

    IFile analyticPrivilegeIFile = project.createFile("test.analyticprivilege", analyticPrivilegeFile);

    byte[] hdiFile = org.apache.commons.io.IOUtils.toString(XSKProjectFilesModificatorTest.class.getResourceAsStream("/test.hdi"),
        StandardCharsets.UTF_8).getBytes();

    IFile hdiIFile = project.createFile("test.hdi", hdiFile);

    projectFiles.add(xsjsIFile);
    projectFiles.add(analyticPrivilegeIFile);
    projectFiles.add(hdiIFile);

    projectFilesModificator.modifyProjectFiles(project, projectFiles);

    for (IFile projectFile : projectFiles) {
      System.out.println("------FILE NAME: " + projectFile.getName() + "\n");
      System.out.println(new String(projectFile.getContent()));
      System.out.println("\n-------------------------------------\n");
    }
  }


}
