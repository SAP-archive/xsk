package com.sap.xsk.migration.delivery.units.service;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class JUnitTestClass {

  @Test
  public void testDeliveryUnitExtraction() {
    XSKDeliveryUnitService sq = new XSKDeliveryUnitService();
    List<DeliveryUnit> dUnitList = sq.getDeliveryUnits("C5326377", "Kokopipikokopipi123");
    assertFalse(dUnitList.isEmpty());
  }
}







