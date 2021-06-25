package com.sap.xsk.migration.delivery.units.service;

public class DeliveryUnit {
  private final String name;
  private final String vendor;

  public DeliveryUnit(String name, String vendor){
    this.name = name;
    this.vendor = vendor;
  }

  public String getName() {return name;}
  public String getVendor() {return vendor;}
}
