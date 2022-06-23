package com.sap.xsk.integration.tests.applications.utils;

import com.sap.xsk.integration.tests.applications.deployment.ProjectDeploymentType;
import com.sap.xsk.integration.tests.core.client.http.XSKHttpClient;
import com.sap.xsk.integration.tests.core.client.http.kyma.KymaXSKHttpClient;
import com.sap.xsk.integration.tests.core.client.http.local.LocalXSKHttpClient;
import org.eclipse.dirigible.commons.config.Configuration;
import java.net.URI;

import static com.sap.xsk.integration.tests.applications.deployment.ProjectDeploymentConstants.KYMA_HOST;
import static com.sap.xsk.integration.tests.applications.deployment.ProjectDeploymentConstants.PROJECT_BASE_URI;

public class HttpClientFactory {

  public static XSKHttpClient createXSKHttpClient(ProjectDeploymentType projectDeploymentType) {
    XSKHttpClient xskHttpClient = null;

    switch (projectDeploymentType) {
      case LOCAL:
        URI localUri = URI.create(PROJECT_BASE_URI);
        xskHttpClient = LocalXSKHttpClient.create(localUri);
        break;
      case KYMA:
        String host = Configuration.get(KYMA_HOST);
        URI kymaUri = URI.create(host);
        xskHttpClient = KymaXSKHttpClient.create(kymaUri);
        break;
    }

    return xskHttpClient;
  }
}
