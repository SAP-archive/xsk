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
package com.sap.xsk.integration.tests.core.client;

import com.sap.xsk.integration.tests.core.client.http.XSKHttpClient;
import com.sap.xsk.integration.tests.core.client.http.XSKHttpClientException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class WorkspaceClient {

    private final URI workspaceServiceUri;
    private final URI transportServiceUri;

    private final XSKHttpClient xskHttpClient;

    public WorkspaceClient(XSKHttpClient xskHttpClient) {
        this.xskHttpClient = xskHttpClient;
        this.workspaceServiceUri = xskHttpClient.getBaseHost().resolve("/services/v4/ide/workspaces/");
        this.transportServiceUri = xskHttpClient.getBaseHost().resolve("/services/v4/transport/project/");
    }

    private static byte[] zipProject(String projectName, Path projectFolderPath) {
        var byteArrayOutputStream = new ByteArrayOutputStream();
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(byteArrayOutputStream)) {
            var filePaths = Files.walk(projectFolderPath)
                    .filter(path -> !Files.isDirectory(path))
                    .collect(Collectors.toList());
            for (var filePath : filePaths) {
                Path zipEntryPath = Path.of(projectName, projectFolderPath.relativize(filePath).toString());
                var zipEntry = new ZipEntry((zipEntryPath).toString());
                zipOutputStream.putNextEntry(zipEntry);
                Files.copy(filePath, zipOutputStream);
                zipOutputStream.closeEntry();
            }
        } catch (IOException e) {
            String errorMessage = "Could not zip path: " + projectFolderPath;
            throw new XSKClientException(errorMessage, e);
        }
        return byteArrayOutputStream.toByteArray();
    }

    public CompletableFuture<HttpResponse> createWorkspace(String workspaceName) {
        try {
            var uri = workspaceServiceUri.resolve(workspaceName);
            HttpUriRequest request = RequestBuilder.post(uri).build();
            return xskHttpClient.executeRequestAsync(request);
        } catch (XSKHttpClientException e) {
            String errorMessage = "Error when creating workspace " + workspaceName;
            throw new XSKClientException(errorMessage, e);
        }

    }

    public CompletableFuture<HttpResponse> importProjectInWorkspace(String workspaceName, String projectName, Path projectFolderPath) {
        try {
            byte[] projectZip = zipProject(projectName, projectFolderPath);
            var uri = transportServiceUri.resolve(workspaceName);
            HttpEntity multiPartHttpEntity = MultipartEntityBuilder.create()
                    .setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
                    .addBinaryBody("file", projectZip)
                    .build();

            HttpUriRequest multipartRequest = RequestBuilder.post(uri)
                    .setEntity(multiPartHttpEntity)
                    .build();
            return xskHttpClient.executeRequestAsync(multipartRequest);
        } catch (XSKHttpClientException e) {
            String errorMessage = "Cannot import project " + projectName + " to workspace " + workspaceName;
            throw new XSKClientException(errorMessage, e);
        }

    }

    public CompletableFuture<HttpResponse> deleteWorkspace(String workspace) {
        try {
            var uri = workspaceServiceUri.resolve(workspace);
            HttpUriRequest request = RequestBuilder.delete(uri).build();
            return xskHttpClient.executeRequestAsync(request);
        } catch (XSKHttpClientException e) {
            String errorMessage = "Error deleting workspace " + workspace;
            throw new XSKClientException(errorMessage, e);
        }

    }

}
