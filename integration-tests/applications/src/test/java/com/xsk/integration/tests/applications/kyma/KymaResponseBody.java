package com.xsk.integration.tests.applications.kyma;

public class KymaResponseBody {
    private String access_token;

    public KymaResponseBody(String access_token) {
        this.access_token = access_token;
    }

    public String getAccess_token() {
        return access_token;
    }
}
