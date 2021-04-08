/*
 * Copyright (c) 2019-2021 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2019-2021 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.xsaccess.ds.model.access;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.eclipse.dirigible.commons.api.helpers.GsonHelper;

public class XSKAccessArtifact {
    private boolean exposed;
    private List<String> authorization;
    private XSKAuthentication authentication;

    public static XSKAccessArtifact parse(byte[] json) {
        return GsonHelper.GSON.fromJson(new InputStreamReader(new ByteArrayInputStream(json), StandardCharsets.UTF_8), XSKAccessArtifact.class);
    }

    public static XSKAccessArtifact parse(String json) {
        return GsonHelper.GSON.fromJson(json, XSKAccessArtifact.class);
    }

    public XSKAccessArtifact() {
    }

    public boolean isExposed() {
        return exposed;
    }

    public void setExposed(boolean exposed) {
        this.exposed = exposed;
    }

    public List<String> getAuthorization() {
        return authorization;
    }

    public void setAuthorization(List<String> authorization) {
        this.authorization = authorization;
    }

    public XSKAuthentication getAuthentication() {
        return authentication;
    }

    public void setAuthentication(XSKAuthentication authentication) {
        this.authentication = authentication;
    }


    public XSKAccessDefinition toXSKAccessDefinition() {
        XSKAccessDefinition xskAccessDefinition = new XSKAccessDefinition();
        xskAccessDefinition.setAuthorizationRolesAsList(getAuthorization());
        if (getAuthentication() != null) {
        	xskAccessDefinition.setAuthenticationMethod(getAuthentication().getMethod());
        }
        xskAccessDefinition.setExposed(isExposed());

        return xskAccessDefinition;
    }
}