package com.sap.xsk.migration.delivery.units.service;

import java.util.List;

interface DeliveryUnitsService {
  public List<DeliveryUnit> getDeliveryUnits(String userName, String password);
}