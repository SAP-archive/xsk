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
package com.sap.xsk.integration.tests.applications;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.sap.xsk.integration.tests.applications.deployment.ProjectDeploymentRule;
import com.sap.xsk.integration.tests.applications.status.ProjectHealthCheckRule;
import com.sap.xsk.integration.tests.applications.status.ProjectHttpCheck;
import com.sap.xsk.integration.tests.applications.status.ProjectSqlCheck;
import com.sap.xsk.integration.tests.applications.utils.HanaDataSourceFactory;
import com.sap.xsk.integration.tests.applications.utils.HttpClientFactory;
import com.sap.xsk.integration.tests.core.client.http.XSKHttpClient;
import com.sap.xsk.integration.tests.core.hdb.utils.HanaITestUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;
import javax.sql.DataSource;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.sap.xsk.integration.tests.applications.deployment.ProjectType.CUSTOM;
import static org.junit.Assert.assertEquals;

public class XsodataWithCalcViewTest {

  private static DataSource dataSource;
  private static HttpClientFactory httpClientFactory = new HttpClientFactory();

  private static final String APPLICATION_NAME = "xsodata-with-calc-view";
  private static final String APPLICATION_SCHEMA = "TEST_SCHEMA";
  private static final String HDI_CONTAINER_NAME = "XSODATA_WITH_CALC_VIEW_HDI";
  private static final String HDI_CONTAINER_GROUP = "XSODATA_WITH_CALC_VIEW_GROUP";

  private static final String CUSTOMERS_TABLE = "xsodata-with-calc-view::Customers";

  private static final String XSODATA_SERVICE_PATH = "/services/v4/web/xsodata-with-calc-view/Service.xsodata";

  private static final String XSODATA_CUSTOMERS_PATH = XSODATA_SERVICE_PATH + "/Customers?$format=json";

  private static final List<ProjectHttpCheck> HTTP_CHECKS = Arrays.asList(
      new ProjectHttpCheck(XSODATA_SERVICE_PATH + "/Customers", 200)
  );

  private static final List<ProjectSqlCheck> SQL_CHECKS = Arrays.asList(
      new ProjectSqlCheck(APPLICATION_SCHEMA, CUSTOMERS_TABLE, true, true)
  );

  public static final ProjectDeploymentRule projectDeploymentRule = new ProjectDeploymentRule(APPLICATION_NAME, CUSTOM);

  public static final ProjectHealthCheckRule projectHealthCheckRule = new ProjectHealthCheckRule(HTTP_CHECKS, SQL_CHECKS);

  @ClassRule
  public static TestRule chain = RuleChain.outerRule(projectDeploymentRule).around(projectHealthCheckRule);

  @BeforeClass
  public static void getDataSource() {
    dataSource = HanaDataSourceFactory.getDataSource();
  }

  @Test
  public void testXsodataWithCalculationView() throws IOException, URISyntaxException, ExecutionException, InterruptedException {

    XSKHttpClient xskHttpClient = httpClientFactory.createXSKHttpClient();

    URL xsodataUrl = new URL(xskHttpClient.getBaseHost() + XSODATA_CUSTOMERS_PATH);

    HttpUriRequest xsodataRequest = RequestBuilder.get(xsodataUrl.toURI()).build();

    HttpResponse xsodataHttpResponse = xskHttpClient.executeRequestAsync(xsodataRequest).get();
    HttpEntity xsodataEntity = xsodataHttpResponse.getEntity();
    String xsodataResult = IOUtils.toString(xsodataEntity.getContent(), StandardCharsets.UTF_8);

    String expectedXsodataResult = IOUtils.toString(
        XsodataWithCalcViewTest.class.getResourceAsStream("/expected-results/xsodata-with-calc-view/XsodataWithCalcViewResult.json"),
        StandardCharsets.UTF_8);

    JsonElement xsodataJson = JsonParser.parseString(xsodataResult);
    JsonElement expectedXsodataJson = JsonParser.parseString(expectedXsodataResult);

    assertEquals("The xsodata request response did not match the expected result!", expectedXsodataJson, xsodataJson);
  }

  @AfterClass
  public static void dropSchemaAndContainer() throws SQLException {
    try (Connection connection = dataSource.getConnection(); Statement statement = connection.createStatement()) {
      HanaITestUtils.dropSchema(statement, APPLICATION_SCHEMA);
      HanaITestUtils.dropHDIContainer(statement, HDI_CONTAINER_NAME, HDI_CONTAINER_GROUP);
      HanaITestUtils.dropHDIContainerGroup(statement, HDI_CONTAINER_GROUP);
    }
  }
}
