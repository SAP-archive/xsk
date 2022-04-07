/*
 * Copyright (c) 2022 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2022 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.mail.test;

import com.sap.xsk.api.destination.CloudPlatformDestinationFacade;
import com.sap.xsk.mail.DestMailConfigProvider;
import org.eclipse.dirigible.commons.config.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import java.util.Properties;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class DestMailConfigProviderTest {

  private static final String MAIL_SERVER_DESTINATION_NAME = "MAIL_SERVER_DESTINATION_NAME";
  private static final String DESTINATION_NAME = "test-destination";

  private MockedStatic<CloudPlatformDestinationFacade> cloudPlatformDestinationFacadeMock;
  private MockedStatic<Configuration>configurationMock;

  @Before
  public void setup() {
    mock();
  }

  @After
  public void close() {
    configurationMock.close();
    cloudPlatformDestinationFacadeMock.close();
  }

  public void mock() {
    Properties props = new Properties();
    props.setProperty("mail.user", "user@xsk.io");

    configurationMock = Mockito.mockStatic(Configuration.class);
    configurationMock.when(() -> Configuration.get(MAIL_SERVER_DESTINATION_NAME))
        .thenReturn(DESTINATION_NAME);
    com.sap.xsk.api.destination.Destination mockedDestination = Mockito.mock(com.sap.xsk.api.destination.Destination.class);
    when(mockedDestination.getProperties()).thenReturn(props);

    cloudPlatformDestinationFacadeMock = Mockito.mockStatic(CloudPlatformDestinationFacade.class);
    cloudPlatformDestinationFacadeMock.when(() -> CloudPlatformDestinationFacade.getDestination(DESTINATION_NAME))
        .thenReturn(mockedDestination);
  }

  @Test
  public void getPropertiesTest() {
    DestMailConfigProvider provider = new DestMailConfigProvider();
    Properties properties = provider.getProperties();
    assertEquals(1, properties.size());
  }

}
