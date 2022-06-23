package com.sap.xsk.integration.tests.applications.status;

import com.sap.xsk.integration.tests.applications.deployment.ProjectDeploymentType;
import com.sap.xsk.integration.tests.applications.utils.HanaDataSourceFactory;
import com.sap.xsk.integration.tests.applications.utils.HttpClientFactory;
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

import static com.sap.xsk.integration.tests.applications.deployment.ProjectDeploymentConstants.PROJECT_BASE_URI;

public class ProjectHealthChecker {

  private final List<ProjectHttpCheck> projectApplicationHttpChecks;
  private final List<ProjectSqlCheck> projectApplicationSqlChecks;
  private final XSKHttpClient xskHttpClient;
  private final DataSource dataSource;

  private final static Integer INITIAL_RETRY_INDEX = 0;
  private final static Integer RETRY_COUNT = 10;
  private final static Integer RETRY_INTERVAL = 5000;

  private final static String TABLE_EXISTS_QUERY = "SELECT TABLE_NAME FROM \"SYS\".\"M_TABLES\" WHERE SCHEMA_NAME = ? AND TABLE_NAME = ?";
  private final static String TABLE_HAS_RECORDS_QUERY = "SELECT RECORD_COUNT FROM \"SYS\".\"M_TABLES\" WHERE SCHEMA_NAME = ? AND TABLE_NAME = ?";

  ProjectHealthChecker(List<ProjectHttpCheck> projectApplicationHttpChecks,
      List<ProjectSqlCheck> projectApplicationSqlChecks, ProjectDeploymentType projectDeploymentType) {
    this.projectApplicationHttpChecks = projectApplicationHttpChecks;
    this.projectApplicationSqlChecks = projectApplicationSqlChecks;
    this.xskHttpClient = HttpClientFactory.createXSKHttpClient(projectDeploymentType);
    this.dataSource = HanaDataSourceFactory.createHanaDataSource();
  }

  public void performHealthChecks() {
    performHttpHealthChecks();
    performSqlHealthChecks();
  }

  private void performHttpHealthChecks() {
    projectApplicationHttpChecks.forEach(httpCheck -> {
      String endpointToCall = httpCheck.getEndpointToCall();
      Integer expectedStatusCode = httpCheck.getExpectedStatusCode();
      String expectedBodyMessage = httpCheck.getExpectedBodyMessage();

      try {
        URL requestUrl = new URL(PROJECT_BASE_URI + endpointToCall);
        HttpUriRequest httpUriRequest = RequestBuilder.get(requestUrl.toURI()).build();

        makeRequestWithRetry(xskHttpClient, httpUriRequest, expectedStatusCode, expectedBodyMessage, INITIAL_RETRY_INDEX);

      } catch (InterruptedException | URISyntaxException | IOException | ExecutionException e) {
        String errorMessage = "HTTP health check for endpoint " + endpointToCall + " failed! " + e.getMessage();
        throw new ProjectHealthCheckException(errorMessage, e);
      }
    });
  }

  private void makeRequestWithRetry(XSKHttpClient httpAsyncClient, HttpUriRequest httpUriRequest, Integer expectedStatusCode,
      String expectedBodyMessage, Integer currentRetryIndex) throws InterruptedException, IOException, ExecutionException {

    checkRetryCount(currentRetryIndex);

    HttpResponse httpResponse = httpAsyncClient.executeRequestAsync(httpUriRequest).get();
    HttpEntity httpEntity = httpResponse.getEntity();
    String responseBodyMessage = IOUtils.toString(httpEntity.getContent(), StandardCharsets.UTF_8);

    if (!expectedStatusCode.equals(httpResponse.getStatusLine().getStatusCode())) {
      waitAndMakeRequest(httpAsyncClient, httpUriRequest, expectedStatusCode, expectedBodyMessage, currentRetryIndex);
    }

    if (expectedBodyMessage != null && !expectedBodyMessage.equals(responseBodyMessage)) {
      waitAndMakeRequest(httpAsyncClient, httpUriRequest, expectedStatusCode, expectedBodyMessage, currentRetryIndex);
    }
  }

  private void waitAndMakeRequest(XSKHttpClient httpAsyncClient, HttpUriRequest httpUriRequest, Integer expectedStatusCode,
      String expectedBodyMessage, Integer currentRetryIndex) throws InterruptedException, IOException, ExecutionException {
    Thread.sleep(RETRY_INTERVAL);
    makeRequestWithRetry(httpAsyncClient, httpUriRequest, expectedStatusCode, expectedBodyMessage, currentRetryIndex + 1);
  }

  private void performSqlHealthChecks() {
    projectApplicationSqlChecks.forEach(sqlCheck -> {
      String schemaName = sqlCheck.getSchemaName();
      String tableName = sqlCheck.getTableName();

      try (Connection connection = dataSource.getConnection()) {

        checkIfTableExists(connection, schemaName, tableName);
        checkIfTableHasRecords(connection, schemaName, tableName);

      } catch (IOException | SQLException | InterruptedException e) {
        String errorMessage = "SQL health check for table " + tableName + " failed! " + e.getMessage();
        throw new ProjectHealthCheckException(errorMessage, e);
      }
    });
  }

  private void checkIfTableExists(Connection connection, String schemaName, String tableName)
      throws InterruptedException, SQLException, IOException {
    PreparedStatement statement = connection.prepareStatement(TABLE_EXISTS_QUERY);
    statement.setString(1, schemaName);
    statement.setString(2, tableName);
    executeQueryWithRetryToCheckIfTableExists(statement, INITIAL_RETRY_INDEX);
  }

  private void checkIfTableHasRecords(Connection connection, String schemaName, String tableName)
      throws SQLException, InterruptedException, IOException {
    PreparedStatement statement = connection.prepareStatement(TABLE_HAS_RECORDS_QUERY);
    statement.setString(1, schemaName);
    statement.setString(2, tableName);
    executeQueryWithRetryToCheckIfTableHasRecords(statement, INITIAL_RETRY_INDEX);
  }

  private void executeQueryWithRetryToCheckIfTableExists(PreparedStatement statement, Integer currentRetryIndex)
      throws InterruptedException, IOException, SQLException {

    checkRetryCount(currentRetryIndex);

    ResultSet queryResult = statement.executeQuery();

    if (!queryResult.next()) {
      Thread.sleep(RETRY_INTERVAL);
      executeQueryWithRetryToCheckIfTableHasRecords(statement, currentRetryIndex + 1);
    }
  }

  private void executeQueryWithRetryToCheckIfTableHasRecords(PreparedStatement statement, Integer currentRetryIndex)
      throws InterruptedException, SQLException, IOException {

    checkRetryCount(currentRetryIndex);

    ResultSet queryResult = statement.executeQuery();

    if (!queryResult.next() || queryResult.getInt("RECORD_COUNT") == 0) {
      Thread.sleep(RETRY_INTERVAL);
      executeQueryWithRetryToCheckIfTableHasRecords(statement, currentRetryIndex + 1);
    }
  }

  private void checkRetryCount(Integer currentRetryIndex) throws IOException {
    if (currentRetryIndex > RETRY_COUNT) {
      throw new IOException("Retry limit reached!");
    }
  }
}
