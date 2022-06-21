package com.sap.xsk.integration.tests.applications.utils;

import com.sap.xsk.integration.tests.applications.deployment.XSKProjectDeploymentType;
import com.sap.xsk.integration.tests.core.client.http.XSKHttpClient;
import com.sap.xsk.integration.tests.core.client.http.kyma.KymaXSKHttpClient;
import com.sap.xsk.integration.tests.core.client.http.local.LocalXSKHttpClient;
import org.eclipse.dirigible.commons.config.Configuration;
import java.net.URI;

import static com.sap.xsk.integration.tests.applications.deployment.XSKProjectDeploymentConstants.KYMA_HOST;
import static com.sap.xsk.integration.tests.applications.deployment.XSKProjectDeploymentConstants.PROJECT_BASE_URI;

public class XSKProjectHttpClientBuilder {

  public static XSKHttpClient createXSKHttpClient(XSKProjectDeploymentType xskProjectDeploymentType) {
    XSKHttpClient xskHttpClient = null;

    switch (xskProjectDeploymentType) {
      case LOCAL:
        var localUri = URI.create(PROJECT_BASE_URI);
        xskHttpClient = LocalXSKHttpClient.create(localUri);
        break;
      case KYMA:
        String host = Configuration.get(KYMA_HOST);
        var kymaUri = URI.create(host);
        xskHttpClient = KymaXSKHttpClient.create(kymaUri);
        break;
    }

    return xskHttpClient;
  }
}
