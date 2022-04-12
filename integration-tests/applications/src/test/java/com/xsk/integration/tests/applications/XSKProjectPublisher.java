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
package com.xsk.integration.tests.applications;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class XSKProjectPublisher {

  private static final String TRANSPORT_SERVICE_URL = "http://localhost:8080/services/v4/transport/project/";
  private static final String WORKSPACE_SERVICE_URL = "http://localhost:8080/services/v4/ide/workspaces/";
  private static final String PUBLISHER_SERVICE_URL = "http://localhost:8080/services/v4/ide/publisher/request/";
  private final CloseableHttpAsyncClient httpClient;

  public XSKProjectPublisher() {
    httpClient = HttpAsyncClients.createDefault();
    httpClient.start();
  }

  public void publish(String applicationName, Path applicationFolderPath) throws PublishException {
    try {
      publishAsync(applicationName, applicationFolderPath).get();
    } catch (InterruptedException | ExecutionException e) {
      throw new PublishException("Publish request failed", e);
    }
  }

  public CompletableFuture<HttpResponse> publishAsync(String projectName, Path projectFolderPath) throws PublishException {
    return createWorkspace(projectName)
        .thenCompose(x -> importProjectInWorkspace(projectName, projectName, projectFolderPath))
        .thenCompose(x -> publishProjectInWorkspace(projectName, projectName));
  }

  private CompletableFuture<HttpResponse> createWorkspace(String workspaceName) {
    try {
      var uri = new URI(WORKSPACE_SERVICE_URL).resolve(workspaceName);
      HttpUriRequest request = RequestBuilder.post(uri).build();
      return executeRequestAsync(request);
    } catch (URISyntaxException e) {
      throw new PublishException("Publish request failed", e);
    }
  }

  private CompletableFuture<HttpResponse> importProjectInWorkspace(String workspaceName, String projectName, Path projectFolderPath) {
    try {
      byte[] projectZip = zipProject(projectName, projectFolderPath);
      var uri = new URI(TRANSPORT_SERVICE_URL).resolve(workspaceName);
      HttpEntity multiPartHttpEntity = MultipartEntityBuilder.create()
          .setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
          .addBinaryBody("file", projectZip)
          .build();

      HttpUriRequest multipartRequest = RequestBuilder.post(uri)
          .setEntity(multiPartHttpEntity)
          .build();

      return executeRequestAsync(multipartRequest);

    } catch (URISyntaxException e) {
      throw new PublishException("Publish request failed", e);
    }
  }

  private CompletableFuture<HttpResponse> publishProjectInWorkspace(String workspace, String projectName) {
    try {
      var uri = new URI(PUBLISHER_SERVICE_URL).resolve(workspace + "/").resolve(projectName);
      HttpUriRequest request = RequestBuilder.post(uri).build();
      return executeRequestAsync(request);
    } catch (URISyntaxException e) {
      throw new PublishException("Publish request failed", e);
    }
  }

  private static byte[] zipProject(String projectName, Path projectFolderPath) {
    var byteArrayOutputStream = new ByteArrayOutputStream();
    try (ZipOutputStream zipOutputStream = new ZipOutputStream(byteArrayOutputStream)) {
      var filePaths = Files.walk(projectFolderPath)
          .filter(path -> !Files.isDirectory(path))
          .collect(Collectors.toList());

      for (var filePath : filePaths) {
        var zipEntry = new ZipEntry(Path.of(projectName, projectFolderPath.relativize(filePath).toString()).toString());
        zipOutputStream.putNextEntry(zipEntry);
        Files.copy(filePath, zipOutputStream);
        zipOutputStream.closeEntry();
      }
    } catch (IOException e) {
      throw new PublishException("Could not zip path: " + projectFolderPath);
    }
    return byteArrayOutputStream.toByteArray();
  }

  private CompletableFuture<HttpResponse> executeRequestAsync(HttpUriRequest request) {
    var future = new CompletableHttpResponseFuture();
    httpClient.execute(request, future);
    return future;
  }


  public void unpublish(String projectName) {

  }

  static class CompletableHttpResponseFuture extends CompletableFuture<HttpResponse> implements FutureCallback<HttpResponse> {

    @Override
    public void completed(HttpResponse httpResponse) {
      int statusCode = httpResponse.getStatusLine().getStatusCode();
      if (statusCode > 299 || statusCode < 200) {
        completeExceptionally(new PublishException("Unexpected status code: " + statusCode));
      }
      complete(httpResponse);
    }

    @Override
    public void failed(Exception e) {
      completeExceptionally(e);
    }

    @Override
    public void cancelled() {
      completeExceptionally(new CancellationException());
    }
  }

}
