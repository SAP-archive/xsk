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

import com.sap.xsk.integration.tests.applications.deployment.XSKProjectDeployer;
import com.sap.xsk.integration.tests.applications.deployment.XSKProjectDeploymentType;
import com.sap.xsk.integration.tests.core.client.http.XSKHttpClient;
import com.sap.xsk.integration.tests.core.client.http.local.LocalXSKHttpClient;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;

public class SampleTest {

  private final XSKProjectDeployer projectPublisher = new XSKProjectDeployer(XSKProjectDeploymentType.LOCAL);
  ;
  private final String applicationName = "hdb-hdbti-simple";
  private final String projectPath = "/samples/hdb-hdbti-simple";

  @Test
  public void testHdbtiSimple() throws IOException, URISyntaxException, ExecutionException, InterruptedException {
    Path resourcePath = Path.of(projectPath);
    projectPublisher.deploy(applicationName, resourcePath);

    Thread.sleep(30000);

    URL xsjsUrl = new URL("http://localhost:8080/services/v4/xsk/hdb-hdbti-simple/getProductsOrders.xsjs");
    URL xsodataUrl = new URL("http://localhost:8080/services/v4/web/hdb-hdbti-simple/Service.xsodata/ProductsOrders?$format=json");
    URL xskUrl = new URL("http://localhost:8080");

    StringBuilder xsjsResult = new StringBuilder();
    StringBuilder xsodataResult = new StringBuilder();

    XSKHttpClient client = LocalXSKHttpClient.create(xskUrl.toURI());

    HttpUriRequest xsjsRequest = RequestBuilder.get(xsjsUrl.toURI()).build();
    HttpUriRequest xsodataRequest = RequestBuilder.get(xsodataUrl.toURI()).build();

    var xsjsResponse = client.executeRequestAsync(xsjsRequest).get();
    var xsodataResponse = client.executeRequestAsync(xsodataRequest).get();

    HttpEntity xsjsEntity = xsjsResponse.getEntity();
    HttpEntity xsodataEntity = xsodataResponse.getEntity();

    try (BufferedReader reader = new BufferedReader(
        new InputStreamReader(xsjsEntity.getContent()))) {
      for (String line; (line = reader.readLine()) != null; ) {
        xsjsResult.append(line);
      }
    }

    try (BufferedReader reader = new BufferedReader(
        new InputStreamReader(xsodataEntity.getContent()))) {
      for (String line; (line = reader.readLine()) != null; ) {
        xsodataResult.append(line);
      }
    }

    projectPublisher.undeploy(applicationName, applicationName);

    assertEquals(
        "[{\"Id\":\"1\","
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
            + "\"Email\":\"josh.patel@example.com\"}]",
        xsjsResult.toString());

    assertEquals(
        "{\"d\":{\"results\":"
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
            + "\"Email\":\"josh.patel@example.com\"}]}}",
        xsodataResult.toString());
  }
}
