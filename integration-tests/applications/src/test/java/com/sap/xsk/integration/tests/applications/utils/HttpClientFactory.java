package com.sap.xsk.integration.tests.applications.utils;

import com.sap.xsk.integration.tests.core.client.http.XSKHttpClient;
import com.sap.xsk.integration.tests.core.client.http.kyma.KymaXSKHttpClient;
import com.sap.xsk.integration.tests.core.client.http.local.LocalXSKHttpClient;
import org.eclipse.dirigible.commons.config.Configuration;
import java.net.URI;

import static com.sap.xsk.integration.tests.applications.deployment.ProjectDeploymentConstants.KYMA_HOST;
import static com.sap.xsk.integration.tests.applications.deployment.ProjectDeploymentConstants.LOCAL_BASE_HOST;

public class HttpClientFactory {

  public XSKHttpClient createXSKHttpClient() {
    String kymaHost = Configuration.get(KYMA_HOST);

    if (kymaHost != null && !kymaHost.isEmpty()) {
      URI kymaUri = URI.create(kymaHost);
      return KymaXSKHttpClient.create(kymaUri);
    } else {
      URI localUri = URI.create(LOCAL_BASE_HOST);
      return LocalXSKHttpClient.create(localUri);
    }
  }
}
