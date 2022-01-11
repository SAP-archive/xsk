package com.sap.xsk.modificators;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.dirigible.core.workspace.api.IFile;
import org.eclipse.dirigible.core.workspace.api.IProject;
import java.util.List;

public class XSKProjectFilesModificator {

  public void modifyProjectFiles(IProject project, List<IFile> projectFiles) {
    for (IFile projectFile : projectFiles) {
      modifyProjectFileIfNecessary(projectFile);
      addNewProjectFileIfNecessary(project);
    }
  }

  private void modifyProjectFileIfNecessary(IFile projectFile) {
    String fileName = projectFile.getName();
    String fileExtension = StringUtils.substringAfterLast(fileName, ".");

    switch (fileExtension) {
      case "calculationview":
      case "hdbcalculationview": {
        modifyCalculationViewFile(projectFile);
        break;
      }
      case "analyticprivilege":
      case "hdbanalyticprivilege": {
        modifyAnalyticPrivilegeFile(projectFile);
        break;
      }
    }
  }

  private void modifyCalculationViewFile(IFile calculationViewFile) {
    byte[] currentContent = calculationViewFile.getContent();
    // modify
    calculationViewFile.setContent(currentContent);
  }

  private void modifyAnalyticPrivilegeFile(IFile analyticPrivilegeFile) {
    byte[] currentContent = analyticPrivilegeFile.getContent();
    analyticPrivilegeFile.setContent(currentContent);
  }

  private void addNewProjectFileIfNecessary(IProject project) {
    // // public IFile createFile(String path, byte[] content) {}
    // // public IFile createFile(String path, byte[] content, boolean isBinary, String contentType) {}
    //
    // project.createFile()
  }
}
