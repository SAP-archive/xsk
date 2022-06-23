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

import static com.sap.xsk.integration.tests.applications.deployment.ProjectType.SAMPLE;
import static com.sap.xsk.integration.tests.applications.deployment.ProjectDeploymentType.LOCAL;
import static com.sap.xsk.integration.tests.applications.deployment.ProjectDeploymentConstants.PROJECT_BASE_URI;

import static org.junit.Assert.assertEquals;

public class HdbHdbtiSimpleSampleTest {

  private static DataSource dataSource;

  private static final String APPLICATION_NAME = "hdb-hdbti-simple";
  private static final String APPLICATION_SCHEMA = "XSK_SAMPLES_HDB_HDBTI_SIMPLE";

  private static final String ORDERS_TABLE = "hdb-hdbti-simple::Products.Orders";

  private static final String XSJS_SERVICE_PATH = "/services/v4/xsk/hdb-hdbti-simple/Service.xsjs";
  private static final String XSODATA_SERVICE_PATH = "/services/v4/web/hdb-hdbti-simple/Service.xsodata";

  private static final String XSJS_PRODUCTS_ORDERS_PATH = XSJS_SERVICE_PATH + "/productsOrders";
  private static final String XSODATA_PRODUCTS_ORDERS_PATH = XSODATA_SERVICE_PATH + "/ProductsOrders?$format=json";

  private static final List<ProjectHttpCheck> HTTP_CHECKS = Arrays.asList(
      new ProjectHttpCheck(XSJS_SERVICE_PATH + "/status", 200, "OK"),
      new ProjectHttpCheck(XSODATA_SERVICE_PATH + "/ProductsOrders", 200));

  private static final List<ProjectSqlCheck> SQL_CHECKS = Arrays.asList(new ProjectSqlCheck(APPLICATION_SCHEMA, ORDERS_TABLE));

  public static final ProjectDeploymentRule projectDeploymentRule = new ProjectDeploymentRule(APPLICATION_NAME, SAMPLE, LOCAL);

  public static final ProjectHealthCheckRule projectHealthCheckRule = new ProjectHealthCheckRule(HTTP_CHECKS, SQL_CHECKS, LOCAL);

  @ClassRule
  public static TestRule chain = RuleChain.outerRule(projectDeploymentRule).around(projectHealthCheckRule);

  @BeforeClass
  public static void setDataSource() {
    dataSource = HanaDataSourceFactory.createHanaDataSource();
  }

  @Test
  public void testHdbHdbtiSimpleSampleXsjsService() throws IOException, URISyntaxException, ExecutionException, InterruptedException {

    URL xsjsUrl = new URL(PROJECT_BASE_URI + XSJS_PRODUCTS_ORDERS_PATH);

    XSKHttpClient xskHttpClient = HttpClientFactory.createXSKHttpClient(LOCAL);

    HttpUriRequest xsjsRequest = RequestBuilder.get(xsjsUrl.toURI()).build();

    HttpResponse xsjsHttpResponse = xskHttpClient.executeRequestAsync(xsjsRequest).get();
    HttpEntity xsjsEntity = xsjsHttpResponse.getEntity();
    String xsjsResult = IOUtils.toString(xsjsEntity.getContent(), StandardCharsets.UTF_8);

    final String expectedXsjsResult = "[{\"Id\":\"1\","
        + "\"CustomerName\":\"John\","
        + "\"CustomerSurname\":\"Smith\","
        + "\"Status\":\"In Progress\","
        + "\"CreatedAt\":\"2020-01-01T12:25:10.000Z\","
        + "\"CreatedBy\":\"Mike Baker\","
        + "\"Description\":\"Migration of XS Classic application from Neo to Kubernetes\","
        + "\"Address\":\"Montreux Switzerland\","
        + "\"Phone\":\"-\",\"Email\":\"mike.baker@example.com\"},"
        + "{\"Id\":\"2\","
        + "\"CustomerName\":\"Claudia\","
        + "\"CustomerSurname\":\"Davis\","
        + "\"Status\":\"In Progress\","
        + "\"CreatedAt\":\"2020-01-01T12:27:33.000Z\","
        + "\"CreatedBy\":\"Josh Patel\","
        + "\"Description\":\"Migration of XS Classic application from Neo to Kubernetes\","
        + "\"Address\":\"Waldorf Germany\","
        + "\"Phone\":\"-\","
        + "\"Email\":\"josh.patel@example.com\"}]";

    assertEquals("The xsjs request response did not match the expected result!", expectedXsjsResult, xsjsResult);
  }

  @Test
  public void testHdbHdbtiSimpleSampleXsodataService() throws IOException, URISyntaxException, ExecutionException, InterruptedException {

    URL xsodataUrl = new URL(PROJECT_BASE_URI + XSODATA_PRODUCTS_ORDERS_PATH);

    XSKHttpClient xskHttpClient = HttpClientFactory.createXSKHttpClient(LOCAL);

    HttpUriRequest xsodataRequest = RequestBuilder.get(xsodataUrl.toURI()).build();

    HttpResponse xsodataHttpResponse = xskHttpClient.executeRequestAsync(xsodataRequest).get();
    HttpEntity xsodataEntity = xsodataHttpResponse.getEntity();
    String xsodataResult = IOUtils.toString(xsodataEntity.getContent(), StandardCharsets.UTF_8);

    final String expectedXsodataResult = "{\"d\":{\"results\":"
        + "[{\"__metadata\":{\"id\":\"http://localhost:8080/odata/v2/ProductsOrders('1')\","
        + "\"uri\":\"http://localhost:8080/odata/v2/ProductsOrders('1')\","
        + "\"type\":\"hdb-hdbti-simple.Service.ProductsOrdersType\"},"
        + "\"Id\":\"1\","
        + "\"CustomerName\":\"John\","
        + "\"CustomerSurname\":\"Smith\","
        + "\"Status\":\"In Progress\","
        + "\"CreatedAt\":\"\\/Date(1577881510000)\\/\","
        + "\"CreatedBy\":\"Mike Baker\","
        + "\"Description\":\"Migration of XS Classic application from Neo to Kubernetes\","
        + "\"Address\":\"Montreux Switzerland\","
        + "\"Phone\":\"-\","
        + "\"Email\":\"mike.baker@example.com\"},"
        + "{\"__metadata\":{\"id\":\"http://localhost:8080/odata/v2/ProductsOrders('2')\","
        + "\"uri\":\"http://localhost:8080/odata/v2/ProductsOrders('2')\","
        + "\"type\":\"hdb-hdbti-simple.Service.ProductsOrdersType\"},"
        + "\"Id\":\"2\","
        + "\"CustomerName\":\"Claudia\","
        + "\"CustomerSurname\":\"Davis\","
        + "\"Status\":\"In Progress\","
        + "\"CreatedAt\":\"\\/Date(1577881653000)\\/\","
        + "\"CreatedBy\":\"Josh Patel\","
        + "\"Description\":\"Migration of XS Classic application from Neo to Kubernetes\","
        + "\"Address\":\"Waldorf Germany\","
        + "\"Phone\":\"-\","
        + "\"Email\":\"josh.patel@example.com\"}]}}";

    assertEquals("The xsodata request response did not match the expected result!", expectedXsodataResult, xsodataResult);
  }

  @AfterClass
  public static void deleteSchema() throws SQLException {
    try (Connection connection = dataSource.getConnection(); Statement statement = connection.createStatement()) {
      HanaITestUtils.dropSchema(statement, APPLICATION_SCHEMA);
    }
  }
}
