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

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * A standalone JAX-RS client implementation for the
 * library service.
 */
public class LibraryServiceTestClient {

    private static final Logger LOGGER = Logger.getAnonymousLogger();

//    public static void main(String[] args) {
//        // construct a JAX-RS client using the builder
//        Client client = ClientBuilder.newBuilder()
//                .connectTimeout(5, TimeUnit.SECONDS)
//                .readTimeout(5, TimeUnit.SECONDS)
//                .register(JsonBindingFeature.class)
//                .build();
//
//        // construct a web target for the library service
//        WebTarget api = client.target("http://localhost:8080").path("/xsk");
//
//        Response sss = api.path("/books").request().accept(MediaType.APPLICATION_JSON).get();
//
//
//        client.close();
//    }

}
