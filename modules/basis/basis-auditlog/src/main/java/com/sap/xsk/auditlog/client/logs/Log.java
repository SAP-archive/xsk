package com.sap.xsk.auditlog.client.logs;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Log {

  @JsonProperty("message_uuid")
  private String uuid;

  @JsonProperty("org_id")
  private String orgID;

  @JsonProperty("space_id")
  private String spaceID;

  @JsonProperty("app_or_service_id")
  private String appServiceID;

  @JsonProperty("als_service_id")
  private String alsServiceID;

  @JsonProperty("format_version")
  private String formatVersion;

  @JsonProperty("tenant")
  private String tenant;

  @JsonProperty("time")
  private String time;

  @JsonProperty("user")
  private String user;

  @JsonProperty("category")
  private String category;

  @JsonProperty("message")
  private String message;


  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  public String getOrgID() {
    return orgID;
  }

  public void setOrgID(String orgID) {
    this.orgID = orgID;
  }

  public String getSpaceID() {
    return spaceID;
  }

  public void setSpaceID(String spaceID) {
    this.spaceID = spaceID;
  }

  public String getAppServiceID() {
    return appServiceID;
  }

  public void setAppServiceID(String appServiceID) {
    this.appServiceID = appServiceID;
  }

  public String getAlsServiceID() {
    return alsServiceID;
  }

  public void setAlsServiceID(String alsServiceID) {
    this.alsServiceID = alsServiceID;
  }

  public String getFormatVersion() {
    return formatVersion;
  }

  public void setFormatVersion(String formatVersion) {
    this.formatVersion = formatVersion;
  }

  public String getTenant() {
    return tenant;
  }

  public void setTenant(String tenant) {
    this.tenant = tenant;
  }

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
