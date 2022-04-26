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
package com.xsk.integration.tests.applications.deployment.client;

import com.xsk.integration.tests.applications.deployment.DeploymentException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static com.xsk.integration.tests.applications.deployment.ApplicationDeploymentRule.HOST;

public class WorkspaceClient {

    private static final String WORKSPACE_SERVICE_URL = HOST + "/services/v4/ide/workspaces/";
    private static final String TRANSPORT_SERVICE_URL = HOST + "/services/v4/transport/project/";

    private final XSKHttpClient xskHttpClient;

    public WorkspaceClient(XSKHttpClient xskHttpClient) {
        this.xskHttpClient = xskHttpClient;
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
            throw new DeploymentException("Could not zip path: " + projectFolderPath);
        }
        return byteArrayOutputStream.toByteArray();
    }

    public CompletableFuture<HttpResponse> createWorkspace(String workspaceName) {
        try {
            var uri = new URI(WORKSPACE_SERVICE_URL).resolve(workspaceName);
            HttpUriRequest request = RequestBuilder.post(uri).build();
            return xskHttpClient.executeRequestAsync(request);
        } catch (URISyntaxException e) {
            throw new DeploymentException("Creating workspace failed", e);
        }
    }

    public CompletableFuture<HttpResponse> login() {
        try {
            var uri = new URI("http://localhost:8080/services/v4/web/ide/");
            HttpUriRequest request = RequestBuilder.get(uri).build();
            return xskHttpClient.executeRequestAsync(request)
                    .thenCompose(x -> {
                        try {
                            var jsecurityUri = new URI("http://localhost:8080/services/v4/web/ide/j_security_check");
                            HttpUriRequest loginRequest = RequestBuilder
                                    .post(jsecurityUri)
                                    .addParameter("j_username", "dirigible")
                                    .addParameter("j_password", "dirigible")
                                    .build();
                            return xskHttpClient.executeRequestAsync(loginRequest);
                        } catch (URISyntaxException e) {
                            throw new RuntimeException(e);
                        }
                    });
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public CompletableFuture<HttpResponse> importProjectInWorkspace(String workspaceName, String projectName, Path projectFolderPath) {
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
            return xskHttpClient.executeRequestAsync(multipartRequest);

        } catch (URISyntaxException e) {
            throw new DeploymentException("Import project into workspace failed", e);
        }
    }

    public CompletableFuture<HttpResponse> deleteWorkspace(String workspace) {
        try {
            var uri = new URI(WORKSPACE_SERVICE_URL).resolve(workspace);
            HttpUriRequest request = RequestBuilder.delete(uri).build();
            return xskHttpClient.executeRequestAsync(request);
        } catch (URISyntaxException e) {
            throw new DeploymentException("Creating workspace failed", e);
        }
    }

}
