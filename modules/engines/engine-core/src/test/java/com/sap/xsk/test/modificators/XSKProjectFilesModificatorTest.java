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

    List<IFile> files = new ArrayList<>();

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

    files.add(xsjsIFile);
    files.add(analyticPrivilegeIFile);
    files.add(hdiIFile);

    projectFilesModificator.modifyProjectFiles(project, files);
  }


}
