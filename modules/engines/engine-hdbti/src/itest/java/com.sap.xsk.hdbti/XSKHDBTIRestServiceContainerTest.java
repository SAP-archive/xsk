/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2021 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.hdbti;

import org.glassfish.jersey.jsonb.JsonBindingFeature;
import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.images.builder.ImageFromDockerfile;
import org.testcontainers.utility.DockerImageName;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class XSKHDBTIRestServiceContainerTest {

//    @ClassRule
//   public static GenericContainer container =
//    new GenericContainer(new ImageFromDockerfile()
//            .withFileFromFile("Dockerfile", new File(basePath(), "Dockerfile"))
//            .withFileFromFile("target/library-service.war", new File(basePath(), "target/library-service.war")))
//            .waitingFor(Wait.forHttp("/library-service/api/application.wadl")
//                    .withStartupTimeout(Duration.ofSeconds(90)))
//            .withLogConsumer(new Slf4jLogConsumer(LoggerFactory.getLogger(XSKHDBTIRestServiceContainerTest.class)))
//            .withExposedPorts(8080)
//            .withExtraHost("localhost", "127.0.0.1");
//
//
//
//
//    private WebTarget api;
//    private Client client;
//
//    @Before
//    public void setUp() {
//        client = ClientBuilder.newBuilder()
//                .connectTimeout(5, TimeUnit.SECONDS)
//                .readTimeout(5, TimeUnit.SECONDS)
//                .register(JsonBindingFeature.class)
//                .build();
//
//        String uri = String.format("http://%s:%s/library-service/api",
//                container.getContainerIpAddress(), container.getMappedPort(8080));
//        api = client.target(uri);
//    }
//
//    @After
//    public void tearDown() {
//        client.close();
//    }
//
////    @Test
////    public void getVersion() {
////        Response response = api.path("/xsk").request().get();
////        assertEquals(response.getStatus(), 200);
////    }
//
//    private static String basePath() {
//        URL resource = XSKHDBTIRestServiceContainerTest.class.getResource("/");
//        File file;
//        try {
//            file = new File(resource.toURI()).getParentFile().getParentFile().getParentFile().getParentFile().getParentFile();
//        } catch (URISyntaxException e) {
//            throw new IllegalStateException(e);
//        }
//        return file.getAbsolutePath()+"/releng/server";
//    }

}
