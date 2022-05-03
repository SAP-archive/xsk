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
package com.sap.xsk.mail;

import com.sap.cloud.sdk.cloudplatform.connectivity.exception.DestinationAccessException;
import com.sap.xsk.api.destination.CloudPlatformDestinationFacade;
import org.eclipse.dirigible.api.v3.mail.api.IMailConfigurationProvider;
import org.eclipse.dirigible.commons.config.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DestMailConfigProvider implements IMailConfigurationProvider {

  private static final String MAIL_USER = "mail.user";
  private static final String MAIL_PASSWORD = "mail.password";
  private static final String MAIL_TRANSPORT_PROTOCOL = "mail.transport.protocol";
  private static final String PROXY_TYPE = "ProxyType";
  private static final String HOST = "host";
  private static final String PORT = "port";
  private static final String AUTH = "auth";
  private static final String SOCKS_HOST = "socks.host";
  private static final String SOCKS_PORT = "socks.port";
  private static final String PROXY_USERNAME = "proxy.user";
  private static final String PROXY_PASSWORD = "proxy.password";
  private static final List<String> PROTOCOL_PROPERTIES = List.of(HOST, PORT, AUTH, SOCKS_HOST, SOCKS_HOST, SOCKS_PORT, PROXY_USERNAME,
      PROXY_PASSWORD);
  private static final List<String> MAIL_PROPERTIES = Stream.concat(
      PROTOCOL_PROPERTIES.stream().map(p -> Arrays.asList("mail.smtp." + p, "mail.smtps." + p))
          .flatMap(List::stream), Stream.of(MAIL_USER, MAIL_PASSWORD, MAIL_TRANSPORT_PROTOCOL, PROXY_TYPE)).collect(Collectors.toList());

  private static final String PROVIDER_NAME = "destination";
  private static final String DESTINATION_NAME = "MAIL_SERVER_DESTINATION_NAME";

  private static final Logger logger = LoggerFactory.getLogger(DestMailConfigProvider.class);

  @Override
  public String getName() {
    return PROVIDER_NAME;
  }

  @Override
  public Properties getProperties() {
    Properties properties = new Properties();
    try {
      String destinationName = Configuration.get(DESTINATION_NAME);
      Properties destinationProperties = CloudPlatformDestinationFacade.getDestination(destinationName).getProperties();
      for (String key : MAIL_PROPERTIES) {
        if (destinationProperties.containsKey(key)) {
          properties.put(key, destinationProperties.get(key));
        }
      }
    } catch (DestinationAccessException e) {
      logger.error(
          "Cannot find destination for mail configuration. Please check if " + DESTINATION_NAME + " is set and the destination exists.");
    }

    if (properties.isEmpty()) {
      logger.error("Destination object does not contain necessary mail settings!");
    }
    return properties;
  }
}
