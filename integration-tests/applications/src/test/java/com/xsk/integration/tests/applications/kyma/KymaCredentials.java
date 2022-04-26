package com.xsk.integration.tests.applications.kyma;

import org.eclipse.dirigible.commons.config.Configuration;

public class KymaCredentials {
    private String host;
    private String clientId;
    private String clientSecret;
    private String username;
    private String password;
    private String tokenUrl;
    private String grantType;

    public KymaCredentials() {
        host = Configuration.get("KYMA_HOST");
        clientId = Configuration.get("XSUAA_CLIENT_ID");
        clientSecret = Configuration.get("XSUAA_CLIENT_SECRET");
        username = Configuration.get("KYMA_USERNAME");
        password = Configuration.get("KYMA_PASSWORD");
        tokenUrl = Configuration.get("KYMA_TOKEN_URL");
        grantType = Configuration.get("KYMA_GRANT_TYPE");
    }

    public String getGrantType() {
        return grantType;
    }

    public String getHost() {
        return host;
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getTokenUrl() {
        return tokenUrl;
    }
}
