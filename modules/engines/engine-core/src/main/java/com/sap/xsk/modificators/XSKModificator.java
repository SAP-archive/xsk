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

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import javax.xml.transform.TransformerException;
import org.eclipse.dirigible.api.v3.platform.WorkspaceFacade;
import org.eclipse.dirigible.core.workspace.api.IFile;
import org.eclipse.dirigible.core.workspace.api.IFolder;

public class XSKModificator {
  List<IFile>filesForProject= new ArrayList<>();

  public byte[] modify(byte[] fileContent) throws TransformerException {
    return new XSLTTransform().removeTypeArtifact(fileContent);
  }

  public void handleXSKProject(String workspace, String project) {
    List<IFolder> folders = WorkspaceFacade.getWorkspace(workspace).getProject(project).getFolders();
    List<IFile> rootFiles = WorkspaceFacade.getWorkspace(workspace).getProject(project).getFiles();
    if(rootFiles!=null || !rootFiles.isEmpty()){
      System.out.println("ADD ROOTFILES");
      filesForProject.addAll(rootFiles);
    }
    System.out.println("List for folders in root");
    for (IFolder folder : folders) {
      listFiles(folder);
    }

    System.out.println("ALL FILES FOR PROJECT");

    for (IFile file : filesForProject) {
      System.out.println("FILENAME " + file.getName() + "FILE EXTENSION" + file.getContentType());
    }
    filesForProject.clear();
    }


  public void listFiles(IFolder folder) {
    filesForProject.addAll(folder.getFiles());
    System.out.println("List in nested folders");
    if(!folder.getFolders().isEmpty() || folder.getFolders() != null ){
      for (int i = 0; i <folder.getFolders().size() ; i++) {
        System.out.println("Recursive call for nested folder" + folder.getFolders().get(i).getName());
        listFiles(folder.getFolders().get(i));
      }
    }
  }
}
