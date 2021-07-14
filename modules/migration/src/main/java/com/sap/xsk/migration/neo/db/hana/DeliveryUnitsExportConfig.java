package com.sap.xsk.migration.neo.db.hana;

public class DeliveryUnitsExportConfig {

  private final String workspace;
  private final String deliveryUnitName;
  private final String deliveryUnitVendor;
  private final String dbHost;
  private final String dbPort;
  private final String dbUser;
  private final String dbPassword;

  private DeliveryUnitsExportConfig(Builder builder) {
    this.workspace = builder.getWorkspace();
    this.deliveryUnitName = builder.getDeliveryUnitName();
    this.deliveryUnitVendor = builder.getDeliveryUnitVendor();
    this.dbHost = builder.getDbHost();
    this.dbPort = builder.getDbPort();
    this.dbUser = builder.getDbUser();
    this.dbPassword = builder.getDbPassword();
  }

  public String getDeliveryUnitName() {
    return deliveryUnitName;
  }

  public String getDeliveryUnitVendor() {
    return deliveryUnitVendor;
  }

  public String getDbHost() {
    return dbHost;
  }

  public String getDbPort() {
    return dbPort;
  }

  public String getDbUser() {
    return dbUser;
  }

  public String getDbPassword() {
    return dbPassword;
  }

  public String getWorkspace() {
    return workspace;
  }

  public static class Builder {

    private String workspace;
    private String deliveryUnitName;
    private String deliveryUnitVendor;
    private String dbHost;
    private String dbPort;
    private String dbUser;
    private String dbPassword;

    public Builder withWorkspace(String workspace) {
      this.workspace = workspace;
      return this;
    }

    public Builder withDeliveryUnitName(String deliveryUnitName) {
      this.deliveryUnitName = deliveryUnitName;
      return this;
    }

    public Builder withDeliveryUnitVendor(String deliveryUnitVendor) {
      this.deliveryUnitVendor = deliveryUnitVendor;
      return this;
    }

    public Builder withDbHost(String dbHost) {
      this.dbHost = dbHost;
      return this;
    }

    public Builder withDbPort(String dbPort) {
      this.dbPort = dbPort;
      return this;
    }

    public Builder withDbUser(String dbUser) {
      this.dbUser = dbUser;
      return this;
    }

    public Builder withDbPassword(String dbPassword) {
      this.dbPassword = dbPassword;
      return this;
    }

    public DeliveryUnitsExportConfig build() {
      return new DeliveryUnitsExportConfig(this);
    }

    String getWorkspace() {
      return workspace;
    }

    String getDeliveryUnitName() {
      return deliveryUnitName;
    }

    String getDeliveryUnitVendor() {
      return deliveryUnitVendor;
    }

    String getDbHost() {
      return dbHost;
    }

    String getDbPort() {
      return dbPort;
    }

    String getDbUser() {
      return dbUser;
    }

    String getDbPassword() {
      return dbPassword;
    }
  }
}