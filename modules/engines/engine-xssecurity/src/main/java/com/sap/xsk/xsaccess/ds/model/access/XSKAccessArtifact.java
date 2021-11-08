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
package com.sap.xsk.xsaccess.ds.model.access;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import com.google.gson.internal.LinkedTreeMap;
import org.eclipse.dirigible.commons.api.helpers.GsonHelper;

public class XSKAccessArtifact {

  private boolean exposed;
  private List<String> authorization;
  private List<XSKAuthentication> authentication;

  public XSKAccessArtifact() {
  }

  public static XSKAccessArtifact parse(byte[] json) {
    LinkedTreeMap artifactAsObject = (LinkedTreeMap) GsonHelper.GSON.fromJson(new InputStreamReader(new ByteArrayInputStream(json), StandardCharsets.UTF_8), Object.class);
    if(artifactAsObject.get("authentication") instanceof LinkedTreeMap) {
      XSKAccessArtifact artifact = new XSKAccessArtifact();
      artifact.setExposed(Boolean.TRUE.equals(artifactAsObject.get("exposed")));
      artifact.setAuthorization((ArrayList) artifactAsObject.get("authorization"));
      LinkedTreeMap authenticationAsObject = (LinkedTreeMap) artifactAsObject.get("authentication");
      XSKAuthentication authentication = new XSKAuthentication();
      authentication.setMethod(String.valueOf(authenticationAsObject.get("method")));
      ArrayList<XSKAuthentication> authenticationAsList = new ArrayList(Arrays.asList(authentication));
      artifact.setAuthentication(authenticationAsList);

      return artifact;
    } else {
      return GsonHelper.GSON.fromJson(new InputStreamReader(new ByteArrayInputStream(json), StandardCharsets.UTF_8),
          XSKAccessArtifact.class);
    }
  }

  public static XSKAccessArtifact parse(String json) {
    return GsonHelper.GSON.fromJson(json, XSKAccessArtifact.class);
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

  public List<XSKAuthentication> getAuthentication() {
    return authentication;
  }

  public void setAuthentication(List<XSKAuthentication> authentication) {
    this.authentication = authentication;
  }


  public XSKAccessDefinition toXSKAccessDefinition() {
    XSKAccessDefinition xskAccessDefinition = new XSKAccessDefinition();
    xskAccessDefinition.setAuthorizationRolesAsList(getAuthorization());
    if (getAuthentication() != null) {
      xskAccessDefinition.setAuthenticationMethodsAsList(getAuthentication().stream().map(auth -> auth.getMethod()).collect(Collectors.toList()));
    }
    xskAccessDefinition.setExposed(isExposed());

    return xskAccessDefinition;
  }
}