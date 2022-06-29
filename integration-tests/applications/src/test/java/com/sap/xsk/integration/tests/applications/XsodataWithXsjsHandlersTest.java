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

import com.google.gson.Gson;
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
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;
import javax.sql.DataSource;
import java.io.IOException;
import java.net.MalformedURLException;
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

public class XsodataWithXsjsHandlersTest {

  private static DataSource dataSource;
  private static HttpClientFactory httpClientFactory = new HttpClientFactory();

  private static final String APPLICATION_NAME = "xsodata-with-xsjs-handlers";
  private static final String APPLICATION_SCHEMA = "TEST_SCHEMA";

  private static final String EMPLOYEE_TABLE = "xsodata-with-xsjs-handlers::Entities.Employee";
  private static final String STATUS_TABLE = "xsodata-with-xsjs-handlers::Entities.Status";
  private static final String SALARY_TABLE = "xsodata-with-xsjs-handlers::Entities.Salary";

  private static final String XSODATA_SERVICE_PATH = "/services/v4/web/xsodata-with-xsjs-handlers/Service.xsodata";

  private static final String XSODATA_EMPLOYEE_PATH = XSODATA_SERVICE_PATH + "/Employee?$format=json";
  private static final String XSODATA_STATUS_PATH = XSODATA_SERVICE_PATH + "/Status?$format=json";
  private static final String XSODATA_SALARY_PATH = XSODATA_SERVICE_PATH + "/Salary?$format=json";

  private static final List<ProjectHttpCheck> HTTP_CHECKS = Arrays.asList(
      new ProjectHttpCheck(XSODATA_SERVICE_PATH + "/Employee", 200),
      new ProjectHttpCheck(XSODATA_SERVICE_PATH + "/Status", 200),
      new ProjectHttpCheck(XSODATA_SERVICE_PATH + "/Salary", 200)
  );

  private static final List<ProjectSqlCheck> SQL_CHECKS = Arrays.asList(
      new ProjectSqlCheck(APPLICATION_SCHEMA, EMPLOYEE_TABLE, true, false),
      new ProjectSqlCheck(APPLICATION_SCHEMA, STATUS_TABLE, true, false),
      new ProjectSqlCheck(APPLICATION_SCHEMA, SALARY_TABLE, true, false)
  );

  public static final ProjectDeploymentRule projectDeploymentRule = new ProjectDeploymentRule(APPLICATION_NAME, CUSTOM);

  public static final ProjectHealthCheckRule projectHealthCheckRule = new ProjectHealthCheckRule(HTTP_CHECKS, SQL_CHECKS);

  @ClassRule
  public static TestRule chain = RuleChain.outerRule(projectDeploymentRule).around(projectHealthCheckRule);

  @BeforeClass
  public static void getDataSource() {
    dataSource = HanaDataSourceFactory.getDataSource();
  }

  class Employee {

    private Integer Id;
    private String Name;
    private Integer Age;
    private String Country;

    public Employee(String name, Integer age, String country) {
      Name = name;
      Age = age;
      Country = country;
    }

    public Employee(Integer id, String name, Integer age, String country) {
      Id = id;
      Name = name;
      Age = age;
      Country = country;
    }
  }

  @Test
  public void testXsodataXsjsEventHandlers() throws IOException, URISyntaxException, ExecutionException, InterruptedException {

    XSKHttpClient xskHttpClient = httpClientFactory.createXSKHttpClient();

    URL xsodataEmployeeGetUrl = new URL(xskHttpClient.getBaseHost() + XSODATA_EMPLOYEE_PATH);
    URL xsodataStatusGetUrl = new URL(xskHttpClient.getBaseHost() + XSODATA_STATUS_PATH);
    URL xsodataSalaryGetUrl = new URL(xskHttpClient.getBaseHost() + XSODATA_SALARY_PATH);

    makeCreateRequest(xskHttpClient);

    assertHandlerResults(xskHttpClient, xsodataStatusGetUrl, "/expected-results/xsodata-with-xsjs-handlers/EmployeeStatusInserted.json");
    assertHandlerResults(xskHttpClient, xsodataEmployeeGetUrl, "/expected-results/xsodata-with-xsjs-handlers/EmployeeInserted.json");
    assertHandlerResults(xskHttpClient, xsodataSalaryGetUrl, "/expected-results/xsodata-with-xsjs-handlers/EmployeeSalaryInserted.json");

    makeUpdateRequest(xskHttpClient);

    assertHandlerResults(xskHttpClient, xsodataStatusGetUrl, "/expected-results/xsodata-with-xsjs-handlers/EmployeeStatusUpdated.json");
    assertHandlerResults(xskHttpClient, xsodataEmployeeGetUrl, "/expected-results/xsodata-with-xsjs-handlers/EmployeeUpdated.json");
    assertHandlerResults(xskHttpClient, xsodataSalaryGetUrl, "/expected-results/xsodata-with-xsjs-handlers/EmployeeSalaryUpdated.json");

    makeDeleteRequest(xskHttpClient);

    assertHandlerResults(xskHttpClient, xsodataStatusGetUrl, "/expected-results/xsodata-with-xsjs-handlers/EmployeeStatusDeleted.json");
    assertHandlerResults(xskHttpClient, xsodataEmployeeGetUrl, "/expected-results/xsodata-with-xsjs-handlers/EmployeeDeleted.json");
    assertHandlerResults(xskHttpClient, xsodataSalaryGetUrl, "/expected-results/xsodata-with-xsjs-handlers/EmployeeSalaryDeleted.json");
  }

  private void makeCreateRequest(XSKHttpClient xskHttpClient)
      throws IOException, URISyntaxException, ExecutionException, InterruptedException {
    URL xsodataEmployeeCreateUrl = new URL(xskHttpClient.getBaseHost() + XSODATA_SERVICE_PATH + "/Employee");

    Employee employee = new Employee(1, "Ben", 22, "Bulgaria");
    Gson gson = new Gson();
    StringEntity body = new StringEntity(gson.toJson(employee), ContentType.APPLICATION_JSON);

    HttpUriRequest xsodataRequest = RequestBuilder.post(xsodataEmployeeCreateUrl.toURI()).setEntity(body).build();
    xskHttpClient.executeRequestAsync(xsodataRequest).get();
  }

  private void makeUpdateRequest(XSKHttpClient xskHttpClient)
      throws MalformedURLException, URISyntaxException, ExecutionException, InterruptedException {
    URL xsodataEmployeeCreateUrl = new URL(xskHttpClient.getBaseHost() + XSODATA_SERVICE_PATH + "/Employee(1)");

    Employee employee = new Employee("John", 30, "USA");
    Gson gson = new Gson();
    StringEntity body = new StringEntity(gson.toJson(employee), ContentType.APPLICATION_JSON);

    HttpUriRequest xsodataRequest = RequestBuilder.put(xsodataEmployeeCreateUrl.toURI()).setEntity(body).build();
    xskHttpClient.executeRequestAsync(xsodataRequest).get();
  }

  private void makeDeleteRequest(XSKHttpClient xskHttpClient)
      throws MalformedURLException, URISyntaxException, ExecutionException, InterruptedException {
    URL xsodataEmployeeCreateUrl = new URL(xskHttpClient.getBaseHost() + XSODATA_SERVICE_PATH + "/Employee(1)");

    HttpUriRequest xsodataRequest = RequestBuilder.delete(xsodataEmployeeCreateUrl.toURI()).build();
    xskHttpClient.executeRequestAsync(xsodataRequest).get();
  }

  private String makeGetRequest(XSKHttpClient xskHttpClient, URL url)
      throws IOException, URISyntaxException, ExecutionException, InterruptedException {
    HttpUriRequest xsodataRequest = RequestBuilder.get(url.toURI()).build();

    HttpResponse xsodataHttpResponse = xskHttpClient.executeRequestAsync(xsodataRequest).get();
    HttpEntity xsodataEntity = xsodataHttpResponse.getEntity();
    return IOUtils.toString(xsodataEntity.getContent(), StandardCharsets.UTF_8);
  }

  private void assertHandlerResults(XSKHttpClient xskHttpClient, URL xsodataStatusGetUrl, String expectedXsodataResultPath)
      throws IOException, URISyntaxException, ExecutionException, InterruptedException {
    String xsodataResult = makeGetRequest(xskHttpClient, xsodataStatusGetUrl);

    String expectedXsodataResult = IOUtils.toString(
        XsodataWithXsjsHandlersTest.class.getResourceAsStream(expectedXsodataResultPath),
        StandardCharsets.UTF_8);

    JsonElement xsodataJson = JsonParser.parseString(xsodataResult);
    JsonElement expectedXsodataJson = JsonParser.parseString(expectedXsodataResult);

    assertEquals("The xsodata request response did not match the expected result!", expectedXsodataJson, xsodataJson);
  }

  @AfterClass
  public static void deleteSchema() throws SQLException {
    try (Connection connection = dataSource.getConnection(); Statement statement = connection.createStatement()) {
      HanaITestUtils.dropSchema(statement, APPLICATION_SCHEMA);
    }
  }
}
