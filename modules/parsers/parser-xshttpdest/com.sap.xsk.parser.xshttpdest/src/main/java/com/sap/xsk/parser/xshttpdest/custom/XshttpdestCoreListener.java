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
package com.sap.xsk.parser.xshttpdest.custom;

import com.sap.xsk.parser.xshttpdest.core.XshttpdestBaseListener;
import com.sap.xsk.parser.xshttpdest.core.XshttpdestParser;
import com.sap.xsk.parser.xshttpdest.models.XshttpdestModel;

import java.util.Arrays;
import java.util.List;

public class XshttpdestCoreListener extends XshttpdestBaseListener {
  private XshttpdestModel model = new XshttpdestModel();

  @Override
  public void exitHost(XshttpdestParser.HostContext ctx) {
    model.setHost(ctx.STRING().getText());
  }

  @Override
  public void exitPort(XshttpdestParser.PortContext ctx) {
    int port = Integer.parseInt(ctx.INT().getText());
    model.setPort(port);
  }

  @Override
  public void exitDescription(XshttpdestParser.DescriptionContext ctx) {
    model.setDescription(ctx.STRING().getText());
  }

  @Override
  public void exitUseSSL(XshttpdestParser.UseSSLContext ctx) {
    boolean useSSL = Boolean.valueOf(ctx.BOOL().getText());
    model.setUseSSL(useSSL);
  }

  @Override
  public void exitSslAuth(XshttpdestParser.SslAuthContext ctx) {
    model.setSslAuth(ctx.sslAuthValue().getText());
  }

  @Override
  public void exitSslHostCheck(XshttpdestParser.SslHostCheckContext ctx) {
    boolean sslHostCheck = Boolean.valueOf(ctx.BOOL().getText());
    model.setSslHostCheck(sslHostCheck);
  }

  @Override
  public void exitPathPrefix(XshttpdestParser.PathPrefixContext ctx) {
    model.setPathPrefix(ctx.STRING().getText());
  }

  @Override
  public void exitAuthType(XshttpdestParser.AuthTypeContext ctx) {
    model.setAuthType(ctx.authTypeValue().getText());
  }

  @Override
  public void exitSamlProvider(XshttpdestParser.SamlProviderContext ctx) {
    model.setSamlProvider(ctx.STRING().getText());
  }

  @Override
  public void exitSamlACS(XshttpdestParser.SamlACSContext ctx) {
    model.setSamlACS(ctx.STRING().getText());
  }

  @Override
  public void exitSamlAttributes(XshttpdestParser.SamlAttributesContext ctx) {
    model.setSamlAttributes(ctx.STRING().getText());
  }

  @Override
  public void exitSamlNameId(XshttpdestParser.SamlNameIdContext ctx) {
    String stringList = ctx.samlNameIdList().getText();
    List<String> parsedList = Arrays.asList(stringList.substring( 1, stringList.length() - 1 ).split(" , "));
    model.setSamlNameId(parsedList);
  }

  @Override
  public void exitProxyType(XshttpdestParser.ProxyTypeContext ctx) {
    model.setProxyType(ctx.proxyTypeValue().getText());
  }

  @Override
  public void exitProxyHost(XshttpdestParser.ProxyHostContext ctx) {
    model.setProxyHost(ctx.STRING().getText());
  }

  @Override
  public void exitProxyPort(XshttpdestParser.ProxyPortContext ctx) {
    model.setProxyPort(Integer.parseInt(ctx.INT().getText()));
  }

  @Override
  public void exitTimeout(XshttpdestParser.TimeoutContext ctx) {
    model.setTimeout(Integer.parseUnsignedInt(ctx.unsignedInt().getText()));
  }

  @Override
  public void exitRemoteSID(XshttpdestParser.RemoteSIDContext ctx) {
    model.setRemoteSID(ctx.STRING().getText());
  }

  @Override
  public void exitRemoteClient(XshttpdestParser.RemoteClientContext ctx) {
    model.setRemoteClient(ctx.STRING().getText());
  }

  @Override
  public void exitOAuthAppConfigPackage(XshttpdestParser.OAuthAppConfigPackageContext ctx) {
    model.setoAuthAppConfigPackage(ctx.STRING().getText());
  }

  @Override
  public void exitOAuthAppConfig(XshttpdestParser.OAuthAppConfigContext ctx) {
    model.setoAuthAppConfig(ctx.STRING().getText());
  }

  public XshttpdestModel getModel() {
    return model;
  }
}