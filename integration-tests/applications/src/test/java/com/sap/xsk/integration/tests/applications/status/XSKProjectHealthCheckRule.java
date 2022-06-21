package com.sap.xsk.integration.tests.applications.status;

import com.sap.xsk.integration.tests.applications.deployment.XSKProjectDeploymentType;
import org.junit.rules.ExternalResource;
import javax.sql.DataSource;
import java.util.List;

public class XSKProjectHealthCheckRule extends ExternalResource {

  private final XSKProjectHealthChecker projectHealthChecker;

  public XSKProjectHealthCheckRule(List<XSKProjectHttpCheck> xskProjectApplicationHttpChecks,
      List<XSKProjectSqlCheck> xskProjectApplicationSqlChecks, XSKProjectDeploymentType xskProjectDeploymentType) {
    projectHealthChecker = new XSKProjectHealthChecker(xskProjectApplicationHttpChecks, xskProjectApplicationSqlChecks,
        xskProjectDeploymentType);
  }

  @Override
  protected void before() throws Throwable {
    super.before();
    projectHealthChecker.performHealthChecks();
  }
}
