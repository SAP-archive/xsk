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

import com.sap.xsk.integration.tests.applications.deployment.XSKProjectApplicationType;
import com.sap.xsk.integration.tests.applications.deployment.XSKProjectDeploymentRule;
import com.sap.xsk.integration.tests.applications.deployment.XSKProjectDeploymentType;
import com.sap.xsk.integration.tests.applications.hdb.AbstractXSKHDBITTest;
import com.sap.xsk.integration.tests.core.client.http.XSKHttpClient;
import com.sap.xsk.integration.tests.core.client.http.local.LocalXSKHttpClient;
import com.sap.xsk.integration.tests.core.hdb.utils.HanaITestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;

import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.eclipse.dirigible.commons.config.Configuration;
import org.eclipse.dirigible.database.ds.model.IDataStructureModel;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

import static com.sap.xsk.integration.tests.applications.deployment.XSKProjectDeploymentConstants.PROJECT_BASE_URI;
import static org.junit.Assert.assertTrue;

public class XSKHdbHdbtiSimpleSampleTest extends AbstractXSKHDBITTest {

  private static Integer MAX_RETRY_INDEX = 10;
  private static Integer DURATION_BEFORE_RETRY = 5000;

  private static String XSJS_URI_PATH = "/services/v4/xsk/hdb-hdbti-simple/getProductsOrders.xsjs";
  private static String XSODATA_URI_PATH = "/services/v4/web/hdb-hdbti-simple/Service.xsodata/ProductsOrders?$format=json";

  @ClassRule
  public static XSKProjectDeploymentRule xskProjectDeploymentRule = new XSKProjectDeploymentRule("hdb-hdbti-simple",
      XSKProjectApplicationType.SAMPLE, XSKProjectDeploymentType.LOCAL);

  @Before
  public void setUpBeforeTest() throws SQLException {
    HanaITestUtils.clearDataFromXSKDataStructure(systemDatasource, Arrays.asList(
        "'/hdb-hdbti-simple/Products.hdbdd'",
        "'/hdb-hdbti-simple/Products.hdbti'",
        "'/hdb-hdbti-simple/XSK_SAMPLES_HDB_HDBTI_SIMPLE.hdbschema'"
    ));
    Configuration.set(IDataStructureModel.DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE, "true");
    facade.clearCache();
  }

//  @Test
//  public void testHdbtiSimple() throws IOException, URISyntaxException, SQLException {
//    try (Connection connection = datasource.getConnection(); Statement stmt = connection.createStatement()) {
//      try {
//        URL xsjsUrl = new URL(PROJECT_BASE_URI + "/services/v4/xsk/hdb-hdbti-simple/getProductsOrders.xsjs");
//        URL xsodataUrl = new URL(PROJECT_BASE_URI + "/services/v4/web/hdb-hdbti-simple/Service.xsodata/ProductsOrders?$format=json");
//        URL xskUrl = new URL(PROJECT_BASE_URI);
//
//        StringBuilder xsjsResult = new StringBuilder();
//        StringBuilder xsodataResult = new StringBuilder();
//
//        XSKHttpClient client = LocalXSKHttpClient.create(xskUrl.toURI());
//
//        HttpUriRequest xsjsRequest = RequestBuilder.get(xsjsUrl.toURI()).build();
//        HttpUriRequest xsodataRequest = RequestBuilder.get(xsodataUrl.toURI()).build();
//
//        CloseableHttpResponse xsjsResponse = client.executeRequest(xsjsRequest);
//        CloseableHttpResponse xsodataResponse = client.executeRequest(xsodataRequest);
//
//        HttpEntity xsjsEntity = xsjsResponse.getEntity();
//        HttpEntity xsodataEntity = xsodataResponse.getEntity();
//
//        try (BufferedReader reader = new BufferedReader(
//            new InputStreamReader(xsjsEntity.getContent()))) {
//          for (String line; (line = reader.readLine()) != null; ) {
//            xsjsResult.append(line);
//          }
//        }
//
//        xsjsResponse.close();
//
//        try (BufferedReader reader = new BufferedReader(
//            new InputStreamReader(xsodataEntity.getContent()))) {
//          for (String line; (line = reader.readLine()) != null; ) {
//            xsodataResult.append(line);
//          }
//        }
//
//        xsodataResponse.close();
//
//        assertEquals(
//            "[{\"Id\":\"1\","
//                + "\"CustomerName\":\"John\","
//                + "\"CustomerSurname\":\"Smith\","
//                + "\"Status\":\"In Progress\","
//                + "\"CreatedAt\":\"2020-01-01T12:25:10.000Z\","
//                + "\"CreatedBy\":\"Mike Baker\","
//                + "\"Description\":\"Migration of XS Classic application from Neo to Kubernetes\","
//                + "\"Address\":\"Montreux Switzerland\","
//                + "\"Phone\":\"-\",\"Email\":\"mike.baker@example.com\"},"
//                + "{\"Id\":\"2\","
//                + "\"CustomerName\":\"Claudia\","
//                + "\"CustomerSurname\":\"Davis\","
//                + "\"Status\":\"In Progress\","
//                + "\"CreatedAt\":\"2020-01-01T12:27:33.000Z\","
//                + "\"CreatedBy\":\"Josh Patel\","
//                + "\"Description\":\"Migration of XS Classic application from Neo to Kubernetes\","
//                + "\"Address\":\"Waldorf Germany\","
//                + "\"Phone\":\"-\","
//                + "\"Email\":\"josh.patel@example.com\"}]",
//            xsjsResult.toString());
//
//        assertEquals(
//            "{\"d\":{\"results\":"
//                + "[{\"__metadata\":{\"id\":\"http://localhost:8080/odata/v2/ProductsOrders('1')\","
//                + "\"uri\":\"http://localhost:8080/odata/v2/ProductsOrders('1')\","
//                + "\"type\":\"hdb-hdbti-simple.Service.ProductsOrdersType\"},"
//                + "\"Id\":\"1\","
//                + "\"CustomerName\":\"John\","
//                + "\"CustomerSurname\":\"Smith\","
//                + "\"Status\":\"In Progress\","
//                + "\"CreatedAt\":\"\\/Date(1577881510000)\\/\","
//                + "\"CreatedBy\":\"Mike Baker\","
//                + "\"Description\":\"Migration of XS Classic application from Neo to Kubernetes\","
//                + "\"Address\":\"Montreux Switzerland\","
//                + "\"Phone\":\"-\","
//                + "\"Email\":\"mike.baker@example.com\"},"
//                + "{\"__metadata\":{\"id\":\"http://localhost:8080/odata/v2/ProductsOrders('2')\","
//                + "\"uri\":\"http://localhost:8080/odata/v2/ProductsOrders('2')\","
//                + "\"type\":\"hdb-hdbti-simple.Service.ProductsOrdersType\"},"
//                + "\"Id\":\"2\","
//                + "\"CustomerName\":\"Claudia\","
//                + "\"CustomerSurname\":\"Davis\","
//                + "\"Status\":\"In Progress\","
//                + "\"CreatedAt\":\"\\/Date(1577881653000)\\/\","
//                + "\"CreatedBy\":\"Josh Patel\","
//                + "\"Description\":\"Migration of XS Classic application from Neo to Kubernetes\","
//                + "\"Address\":\"Waldorf Germany\","
//                + "\"Phone\":\"-\","
//                + "\"Email\":\"josh.patel@example.com\"}]}}",
//            xsodataResult.toString());
//      } finally {
//        HanaITestUtils.dropSchema(stmt, "XSK_SAMPLES_HDB_HDBTI_SIMPLE");
//      }
//    }
//  }

  @Test
  public void testHdbtiSimple() throws IOException, URISyntaxException, SQLException, InterruptedException {
    try (Connection connection = datasource.getConnection(); Statement stmt = connection.createStatement()) {
      try {
        URL xsjsUrl = new URL(PROJECT_BASE_URI + XSJS_URI_PATH);
        URL xsodataUrl = new URL(PROJECT_BASE_URI + XSODATA_URI_PATH);
        URL xskUrl = new URL(PROJECT_BASE_URI);

        XSKHttpClient client = LocalXSKHttpClient.create(xskUrl.toURI());

        HttpUriRequest xsjsRequest = RequestBuilder.get(xsjsUrl.toURI()).build();
        HttpUriRequest xsodataRequest = RequestBuilder.get(xsodataUrl.toURI()).build();

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

        assertTrue("The xsjs request response did not match the expected result!",
            requestWithRetry(client, xsjsRequest, 1, expectedXsjsResult));
        assertTrue("The xsodata request response did not match the expected result!",
            requestWithRetry(client, xsodataRequest, 1, expectedXsodataResult));

      } finally {
        HanaITestUtils.dropSchema(stmt, "XSK_SAMPLES_HDB_HDBTI_SIMPLE");
      }
    }
  }

  private boolean requestWithRetry(XSKHttpClient httpAsyncClient, HttpUriRequest httpUriRequest, Integer currentRetryIndex,
      String expectedResult) throws InterruptedException {

    if (currentRetryIndex > MAX_RETRY_INDEX) {
      return false;
    }

    try {
      HttpResponse closeableHttpResponse = httpAsyncClient.executeRequestAsync(httpUriRequest).get();
      HttpEntity entity = closeableHttpResponse.getEntity();
      StringBuilder result = new StringBuilder();

      BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent()));

      for (String line; (line = reader.readLine()) != null; ) {
        result.append(line);
      }

      if (!expectedResult.equals(result.toString())) {
        throw new IOException();
      } else {
        return true;
      }
    } catch (ExecutionException | IOException e) {
      Thread.sleep(DURATION_BEFORE_RETRY);

      return requestWithRetry(httpAsyncClient, httpUriRequest, currentRetryIndex + 1, expectedResult);
    }
  }
}
