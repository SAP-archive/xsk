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
package com.sap.xsk.parser.xshttpdest.models;

import java.util.List;

public class XshttpdestModel {
  private String host;
  private int port;
  private String description;
  private boolean useSSL;
  private String sslAuth;
  private boolean sslHostCheck;
  private String pathPrefix;
  private String authType;
  private String samlProvider;
  private String samlACS;
  private String samlAttributes;
  private List<String> samlNameId;
  private String proxyType;
  private String proxyHost;
  private int proxyPort;
  private int timeout;
  private String remoteSID;
  private String remoteClient;
  private String oAuthAppConfigPackage;
  private String oAuthAppConfig;

  public String getHost() {
    return host;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public int getPort() {
    return port;
  }

  public void setPort(int port) {
    this.port = port;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public boolean isUseSSL() {
    return useSSL;
  }

  public void setUseSSL(boolean useSSL) {
    this.useSSL = useSSL;
  }

  public String getSslAuth() {
    return sslAuth;
  }

  public void setSslAuth(String sslAuth) {
    this.sslAuth = sslAuth;
  }

  public boolean isSslHostCheck() {
    return sslHostCheck;
  }

  public void setSslHostCheck(boolean sslHostCheck) {
    this.sslHostCheck = sslHostCheck;
  }

  public String getPathPrefix() {
    return pathPrefix;
  }

  public void setPathPrefix(String pathPrefix) {
    this.pathPrefix = pathPrefix;
  }

  public String getAuthType() {
    return authType;
  }

  public void setAuthType(String authType) {
    this.authType = authType;
  }

  public String getSamlProvider() {
    return samlProvider;
  }

  public void setSamlProvider(String samlProvider) {
    this.samlProvider = samlProvider;
  }

  public String getSamlACS() {
    return samlACS;
  }

  public void setSamlACS(String samlACS) {
    this.samlACS = samlACS;
  }

  public String getSamlAttributes() {
    return samlAttributes;
  }

  public void setSamlAttributes(String samlAttributes) {
    this.samlAttributes = samlAttributes;
  }

  public List<String> getSamlNameId() {
    return samlNameId;
  }

  public void setSamlNameId(List<String> samlNameId) {
    this.samlNameId = samlNameId;
  }

  public String getProxyType() {
    return proxyType;
  }

  public void setProxyType(String proxyType) {
    this.proxyType = proxyType;
  }

  public String getProxyHost() {
    return proxyHost;
  }

  public void setProxyHost(String proxyHost) {
    this.proxyHost = proxyHost;
  }

  public int getProxyPort() {
    return proxyPort;
  }

  public void setProxyPort(int proxyPort) {
    this.proxyPort = proxyPort;
  }

  public int getTimeout() {
    return timeout;
  }

  public void setTimeout(int timeout) {
    this.timeout = timeout;
  }

  public String getRemoteSID() {
    return remoteSID;
  }

  public void setRemoteSID(String remoteSID) {
    this.remoteSID = remoteSID;
  }

  public String getRemoteClient() {
    return remoteClient;
  }

  public void setRemoteClient(String remoteClient) {
    this.remoteClient = remoteClient;
  }

  public String getoAuthAppConfigPackage() {
    return oAuthAppConfigPackage;
  }

  public void setoAuthAppConfigPackage(String oAuthAppConfigPackage) {
    this.oAuthAppConfigPackage = oAuthAppConfigPackage;
  }

  public String getoAuthAppConfig() {
    return oAuthAppConfig;
  }

  public void setoAuthAppConfig(String oAuthAppConfig) {
    this.oAuthAppConfig = oAuthAppConfig;
  }

  public String toString() {
    return String.format("host = %s;\n" +
            "port = %d;\n" +
            "description = %s;\n" +
            "useSSL = %b;\n" +
            "sslAuth  = %s;\n" +
            "sslHostCheck = %b;\n" +
            "pathPrefix = %s\";\n" +
            "authType = %b;\n" +
            "samlProvider = %s;\n" +
            "samlACS = %s;\n" +
            "samlAttributes = %s;\n" +
            "samlNameId = %s;\n" +
            "proxyType = %s;\n" +
            "proxyHost = %s\n" +
            "proxyPort = %d;\n" +
            "timeout = %d;\n" +
            "remoteSID = %s;\n" +
            "remoteClient = %s;\n" +
            "oAuthAppConfigPackage = %s;\n" +
            "oAuthAppConfig = %s;",
        host, port, description, useSSL, sslAuth, sslHostCheck, pathPrefix, authType, samlProvider, samlACS,
        samlAttributes, samlNameId, proxyType, proxyHost, proxyPort, timeout, remoteSID, remoteClient,
        oAuthAppConfigPackage, oAuthAppConfig);
  }
}