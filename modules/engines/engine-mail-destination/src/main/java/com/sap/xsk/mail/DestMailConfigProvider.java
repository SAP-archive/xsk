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
import com.sap.cloud.sdk.cloudplatform.connectivity.Destination;
import org.eclipse.dirigible.commons.config.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Properties;

public class DestMailConfigProvider implements IMailConfigurationProvider {
  private static final String MAIL_USER = "mail.user";
  private static final String MAIL_PASSWORD = "mail.password";
  private static final String MAIL_TRANSPORT_PROTOCOL = "mail.transport.protocol";
  private static final String MAIL_SMTPS_HOST = "mail.smtps.host";
  private static final String MAIL_SMTPS_PORT = "mail.smtps.port";
  private static final String MAIL_SMTPS_AUTH = "mail.smtps.auth";
  private static final String MAIL_SMTP_HOST = "mail.smtp.host";
  private static final String MAIL_SMTP_PORT = "mail.smtp.port";
  private static final String MAIL_SMTP_AUTH = "mail.smtp.auth";
  private static List<String> MAIL_PROPERTIES = List.of(MAIL_USER, MAIL_PASSWORD, MAIL_TRANSPORT_PROTOCOL, MAIL_SMTPS_HOST, MAIL_SMTPS_PORT,
      MAIL_SMTPS_AUTH, MAIL_SMTP_HOST, MAIL_SMTP_PORT, MAIL_SMTP_AUTH);

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
      Destination destination = CloudPlatformDestinationFacade.getDestination(destinationName);
      for (String key : MAIL_PROPERTIES) {
        if(!destination.get(key).isEmpty()) {
          properties.put(key, destination.get(key).get());
        }
      }
    } catch (DestinationAccessException e) {
      logger.error("No Destination object with name " + DESTINATION_NAME + " found!");
    }

    if(properties.isEmpty()) {
      logger.error("Destination object does not contain necessary mail settings!");
    }
    return properties;
  }
}
