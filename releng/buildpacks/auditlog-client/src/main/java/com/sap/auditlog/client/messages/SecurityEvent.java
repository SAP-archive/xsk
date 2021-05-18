package com.sap.auditlog.client.messages;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

public class SecurityEvent extends AuditLogMessage {

  @JsonProperty(value = "data", required = true)
  private final String data;

  @JsonProperty("ip")
  private final String ip;
  @JsonProperty("attributes")
  private final List<SecurityAttribute> attributes;

  public SecurityEvent(SecurityDetail securityConfig, AuditLogDetail logConfig) {
    super(logConfig);
    this.ip = securityConfig.ip;
    this.data = securityConfig.data;
    this.attributes = securityConfig.attributes;
  }

  public String getIp() {
    return this.ip;
  }

  public String getData() {
    return this.data;
  }

  public List<SecurityAttribute> getAttributes() {
    return new ArrayList<>(this.attributes);
  }

  @Override
  public AuditLogCategory getCategory() {
    return AuditLogCategory.SECURITY_EVENT;
  }

  public static class SecurityDetail {

    private final String data;
    private String ip;
    private List<SecurityAttribute> attributes;

    public SecurityDetail(String data) {
      this.data = data;
      this.attributes = new ArrayList<>();
    }

    public void setIP(String ip) {
      this.ip = ip;
    }

    public void setSecurityAttributes(List<SecurityAttribute> attributes) {
      this.attributes = new ArrayList<>(attributes);
    }


  }
}

