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
package com.sap.xsk.hdb.ds.test.itest.hdbsequence.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.sap.xsk.hdb.ds.module.XSKHDBModule;
import org.apache.commons.dbcp2.BasicDataSource;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.lifecycle.Startables;
import javax.sql.DataSource;
import java.util.stream.Stream;

public class XSKHDBTestModule extends AbstractModule {

  private static Network network = Network.newNetwork();

  public static PostgreSQLContainer<?> postgresContainer =
      new PostgreSQLContainer<>("postgres:alpine")
          .withNetwork(network)
          .withNetworkAliases("postgres");


  public static void startContainers() {
    Startables.deepStart(Stream.of(
        postgresContainer))
        .join();
  }

  @Override
  protected void configure() {
    install(new XSKHDBModule());
  }

  @Provides
  static DataSource getDataSource() {
    startContainers();
    BasicDataSource basicDataSource = new BasicDataSource();
    basicDataSource.setDriverClassName(postgresContainer.getDriverClassName());
    basicDataSource.setUrl(postgresContainer.getJdbcUrl());
    basicDataSource.setUsername(postgresContainer.getUsername());
    basicDataSource.setPassword(postgresContainer.getPassword());
    basicDataSource.setDefaultAutoCommit(true);
    basicDataSource.setAccessToUnderlyingConnectionAllowed(true);
   // To do: use diff data source so that driver can be set explicitly without new pom dependency --> postgresContainer.getJdbcDriverInstance().
    return basicDataSource;

  }
}
