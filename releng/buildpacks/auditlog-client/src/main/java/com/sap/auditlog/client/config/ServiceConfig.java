package com.sap.auditlog.client.config;

public abstract class ServiceConfig {

  private final String serviceURL;
  private final String oauthURL;
  private final String clientID;
  private final String clientSecret;

  public ServiceConfig(String serviceURL, String oauthURL, String clientID, String clientSecret) {
    this.serviceURL = serviceURL;
    this.oauthURL = oauthURL;
    this.clientID = clientID;
    this.clientSecret = clientSecret;
  }

  public String getServiceURL() {
    return serviceURL;
  }

  public String getOauthURL() {
    return oauthURL;
  }

  public String getClientID() {
    return clientID;
  }

  public String getClientSecret() {
    return clientSecret;
  }
}
