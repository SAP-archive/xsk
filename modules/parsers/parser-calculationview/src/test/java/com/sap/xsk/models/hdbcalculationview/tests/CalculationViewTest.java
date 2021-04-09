/*
 * Copyright (c) 2019-2021 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2019-2021 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.models.hdbcalculationview.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import com.sap.ndb.CalculationScenario;
import com.sap.ndb.DataSources;
import com.sap.ndb.DataSources.DataSource;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.junit.Test;

public class CalculationViewTest {

  @Test
  public void serialize() throws JAXBException {

    CalculationScenario calculationScenario = new CalculationScenario();
    calculationScenario.setId("com.sap.xsk.samples::XSK_SIMPLE_CALC_VIEW");
    calculationScenario.setOutputViewType("Projection");
    DataSource dataSource = new DataSource();
    dataSource.setId("XSK_SIMPLE_TABLE");
    dataSource.setResourceUri("XSK_SIMPLE_TABLE");
    DataSources dataSources = new DataSources();
    dataSources.getDataSource().add(dataSource);
    calculationScenario.setDataSources(dataSources);

    StringWriter writer = new StringWriter();
    JAXBContext context = JAXBContext.newInstance(CalculationScenario.class);
    Marshaller m = context.createMarshaller();
    m.marshal(calculationScenario, writer);

    System.out.println(writer.toString());

  }

  @Test
  public void deserialize() throws JAXBException {
    String xml = "";
    try {
      xml = org.apache.commons.io.IOUtils.toString(CalculationViewTest.class.getResourceAsStream("/test.calculationview"));
    } catch (IOException e) {
      fail("Parsing of calculation view failed.");
      e.printStackTrace();
    }

    xml = xml.replace("<Calculation:scenario", "<CalculationScenario");
    xml = xml.replace("</Calculation:scenario>", "</CalculationScenario>");

    JAXBContext context = JAXBContext.newInstance(CalculationScenario.class);
    Unmarshaller um = context.createUnmarshaller();
    CalculationScenario calculationScenario = (CalculationScenario) um.unmarshal(new StringReader(xml));

    assertEquals(calculationScenario.getId(), "PO_HEADER");
  }

}
