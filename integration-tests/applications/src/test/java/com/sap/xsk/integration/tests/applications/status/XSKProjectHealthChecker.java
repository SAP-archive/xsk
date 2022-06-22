package com.sap.xsk.integration.tests.applications.status;

import com.sap.xsk.integration.tests.applications.deployment.XSKProjectDeploymentType;
import com.sap.xsk.integration.tests.applications.utils.XSKProjectHanaDataSourceBuilder;
import com.sap.xsk.integration.tests.applications.utils.XSKProjectHttpClientBuilder;
import com.sap.xsk.integration.tests.core.client.http.XSKHttpClient;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;

import javax.sql.DataSource;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.sap.xsk.integration.tests.applications.deployment.XSKProjectDeploymentConstants.PROJECT_BASE_URI;

public class XSKProjectHealthChecker {

  private final List<XSKProjectHttpCheck> xskProjectApplicationHttpChecks;
  private final List<XSKProjectSqlCheck> xskProjectApplicationSqlChecks;
  private final XSKHttpClient xskHttpClient;
  private final DataSource dataSource;

  private final static Integer INITIAL_RETRY_INDEX = 0;
  private final static Integer RETRY_COUNT = 10;
  private final static Integer RETRY_INTERVAL = 5000;

  private final static String TABLE_EXISTS_QUERY = "SELECT TABLE_NAME FROM \"SYS\".\"M_TABLES\" WHERE SCHEMA_NAME = ? AND TABLE_NAME = ?";
  private final static String TABLE_HAS_RECORDS_QUERY = "SELECT RECORD_COUNT FROM \"SYS\".\"M_TABLES\" WHERE SCHEMA_NAME = ? AND TABLE_NAME = ?";

  XSKProjectHealthChecker(List<XSKProjectHttpCheck> xskProjectApplicationHttpChecks,
      List<XSKProjectSqlCheck> xskProjectApplicationSqlChecks, XSKProjectDeploymentType xskProjectDeploymentType) {
    this.xskProjectApplicationHttpChecks = xskProjectApplicationHttpChecks;
    this.xskProjectApplicationSqlChecks = xskProjectApplicationSqlChecks;
    this.xskHttpClient = XSKProjectHttpClientBuilder.createXSKHttpClient(xskProjectDeploymentType);
    this.dataSource = XSKProjectHanaDataSourceBuilder.createXSKHanaDataSource();
  }

  public void performHealthChecks() {
    performHttpHealthChecks();
    performSqlHealthChecks();
  }

  public void performHttpHealthChecks() {
    xskProjectApplicationHttpChecks.forEach(httpCheck -> {
      String endpointToCall = httpCheck.getEndpointToCall();
      Integer expectedStatusCode = httpCheck.getExpectedStatusCode();
      String expectedBodyMessage = httpCheck.getExpectedBodyMessage();

      try {
        URL requestUrl = new URL(PROJECT_BASE_URI + endpointToCall);
        HttpUriRequest httpUriRequest = RequestBuilder.get(requestUrl.toURI()).build();

        boolean httpCheckStatus;
        httpCheckStatus = makeRequestWithRetry(xskHttpClient, httpUriRequest, expectedStatusCode, expectedBodyMessage,
            INITIAL_RETRY_INDEX);

        if (!httpCheckStatus) {
          throw new IOException("Expected status coded or body message did not match!");
        }
      } catch (InterruptedException | URISyntaxException | IOException e) {
        String errorMessage = "Health check for endpoint " + endpointToCall + " failed! " + e.getMessage();
        throw new XSKProjectHealthCheckException(errorMessage, e);
      }
    });
  }

  private boolean makeRequestWithRetry(XSKHttpClient httpAsyncClient, HttpUriRequest httpUriRequest, Integer expectedStatusCode,
      String expectedBodyMessage, Integer currentRetryIndex) throws InterruptedException {

    if (currentRetryIndex > RETRY_COUNT) {
      return false;
    }

    try {
      HttpResponse httpResponse = httpAsyncClient.executeRequestAsync(httpUriRequest).get();
      HttpEntity httpEntity = httpResponse.getEntity();
      String responseBodyMessage = IOUtils.toString(httpEntity.getContent(), StandardCharsets.UTF_8);

      if (!expectedStatusCode.equals(httpResponse.getStatusLine().getStatusCode())) {
        throw new IOException("Expected status code did not match!");
      }

      if (expectedBodyMessage != null && !expectedBodyMessage.equals(responseBodyMessage)) {
        throw new IOException("Expected body message did not match!");
      }

      return true;

    } catch (ExecutionException | IOException e) {
      Thread.sleep(RETRY_INTERVAL);
      return makeRequestWithRetry(httpAsyncClient, httpUriRequest, expectedStatusCode, expectedBodyMessage, currentRetryIndex + 1);
    }
  }

  public void performSqlHealthChecks() {
    xskProjectApplicationSqlChecks.forEach(sqlCheck -> {
      String schemaName = sqlCheck.getSchemaName();
      String tableName = sqlCheck.getTableName();

      try (Connection connection = dataSource.getConnection()) {

        boolean tableExistsStatus;
        tableExistsStatus = checkIfTableExists(connection, schemaName, tableName);

        if (!tableExistsStatus) {
          throw new IOException("Table does not exist!");
        }

        boolean tableHasRecordsStatus;
        tableHasRecordsStatus = checkIfTableHasRecords(connection, schemaName, tableName);

        if (!tableHasRecordsStatus) {
          throw new IOException("Table has no records!");
        }

      } catch (IOException | SQLException | InterruptedException e) {
        String errorMessage = "Health check for table " + tableName + " failed! " + e.getMessage();
        throw new XSKProjectHealthCheckException(errorMessage, e);
      }
    });
  }

  private boolean checkIfTableExists(Connection connection, String schemaName, String tableName) throws InterruptedException, SQLException {
    PreparedStatement statement = connection.prepareStatement(TABLE_EXISTS_QUERY);
    statement.setString(1, schemaName);
    statement.setString(2, tableName);
    return executeQueryWithRetryToCheckIfTableExists(statement, INITIAL_RETRY_INDEX);
  }

  private boolean checkIfTableHasRecords(Connection connection, String schemaName, String tableName)
      throws SQLException, InterruptedException {
    PreparedStatement statement = connection.prepareStatement(TABLE_HAS_RECORDS_QUERY);
    statement.setString(1, schemaName);
    statement.setString(2, tableName);
    return executeQueryWithRetryToCheckIfTableHasRecords(statement, INITIAL_RETRY_INDEX);
  }

  private boolean executeQueryWithRetryToCheckIfTableExists(PreparedStatement statement, Integer currentRetryIndex)
      throws InterruptedException {

    if (currentRetryIndex > RETRY_COUNT) {
      return false;
    }

    try {
      ResultSet queryResult = statement.executeQuery();

      if (!queryResult.next()) {
        throw new IOException("Table does not exist!");
      }

      return true;

    } catch (Exception e) {
      Thread.sleep(RETRY_INTERVAL);
      return executeQueryWithRetryToCheckIfTableHasRecords(statement, currentRetryIndex + 1);
    }
  }

  private boolean executeQueryWithRetryToCheckIfTableHasRecords(PreparedStatement statement, Integer currentRetryIndex)
      throws InterruptedException {

    if (currentRetryIndex > RETRY_COUNT) {
      return false;
    }

    try {
      ResultSet queryResult = statement.executeQuery();

      if (!queryResult.next() || queryResult.getInt("RECORD_COUNT") == 0) {
        throw new IOException("Table has no records!");
      }

      return true;

    } catch (Exception e) {
      Thread.sleep(RETRY_INTERVAL);
      return executeQueryWithRetryToCheckIfTableHasRecords(statement, currentRetryIndex + 1);
    }
  }
}
