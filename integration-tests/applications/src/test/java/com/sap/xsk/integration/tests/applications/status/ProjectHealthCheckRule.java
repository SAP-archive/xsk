package com.sap.xsk.integration.tests.applications.status;

import com.sap.xsk.integration.tests.applications.deployment.ProjectDeploymentType;
import org.junit.rules.ExternalResource;
import java.util.List;

public class ProjectHealthCheckRule extends ExternalResource {

  private final ProjectHealthChecker projectHealthChecker;

  public ProjectHealthCheckRule(List<ProjectHttpCheck> projectApplicationHttpChecks,
      List<ProjectSqlCheck> projectApplicationSqlChecks, ProjectDeploymentType projectDeploymentType) {
    projectHealthChecker = new ProjectHealthChecker(projectApplicationHttpChecks, projectApplicationSqlChecks,
        projectDeploymentType);
  }

  @Override
  protected void before() throws Throwable {
    super.before();
    projectHealthChecker.performHealthChecks();
  }
}
