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
// Generated from /Users/C5322976/Desktop/Workspace/Dirigible/xsk/modules/parsers/parser-xshttpdest/com.sap.xsk.parser.xshttpdest/src/main/antlr4/Xshttpdest.g4 by ANTLR 4.9.1
package com.sap.xsk.parser.xshttpdest.core;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link XshttpdestParser}.
 */
public interface XshttpdestListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link XshttpdestParser#xshttpdest}.
	 * @param ctx the parse tree
	 */
	void enterXshttpdest(XshttpdestParser.XshttpdestContext ctx);
	/**
	 * Exit a parse tree produced by {@link XshttpdestParser#xshttpdest}.
	 * @param ctx the parse tree
	 */
	void exitXshttpdest(XshttpdestParser.XshttpdestContext ctx);
	/**
	 * Enter a parse tree produced by {@link XshttpdestParser#property}.
	 * @param ctx the parse tree
	 */
	void enterProperty(XshttpdestParser.PropertyContext ctx);
	/**
	 * Exit a parse tree produced by {@link XshttpdestParser#property}.
	 * @param ctx the parse tree
	 */
	void exitProperty(XshttpdestParser.PropertyContext ctx);
	/**
	 * Enter a parse tree produced by {@link XshttpdestParser#host}.
	 * @param ctx the parse tree
	 */
	void enterHost(XshttpdestParser.HostContext ctx);
	/**
	 * Exit a parse tree produced by {@link XshttpdestParser#host}.
	 * @param ctx the parse tree
	 */
	void exitHost(XshttpdestParser.HostContext ctx);
	/**
	 * Enter a parse tree produced by {@link XshttpdestParser#port}.
	 * @param ctx the parse tree
	 */
	void enterPort(XshttpdestParser.PortContext ctx);
	/**
	 * Exit a parse tree produced by {@link XshttpdestParser#port}.
	 * @param ctx the parse tree
	 */
	void exitPort(XshttpdestParser.PortContext ctx);
	/**
	 * Enter a parse tree produced by {@link XshttpdestParser#description}.
	 * @param ctx the parse tree
	 */
	void enterDescription(XshttpdestParser.DescriptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link XshttpdestParser#description}.
	 * @param ctx the parse tree
	 */
	void exitDescription(XshttpdestParser.DescriptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link XshttpdestParser#useSSL}.
	 * @param ctx the parse tree
	 */
	void enterUseSSL(XshttpdestParser.UseSSLContext ctx);
	/**
	 * Exit a parse tree produced by {@link XshttpdestParser#useSSL}.
	 * @param ctx the parse tree
	 */
	void exitUseSSL(XshttpdestParser.UseSSLContext ctx);
	/**
	 * Enter a parse tree produced by {@link XshttpdestParser#sslAuth}.
	 * @param ctx the parse tree
	 */
	void enterSslAuth(XshttpdestParser.SslAuthContext ctx);
	/**
	 * Exit a parse tree produced by {@link XshttpdestParser#sslAuth}.
	 * @param ctx the parse tree
	 */
	void exitSslAuth(XshttpdestParser.SslAuthContext ctx);
	/**
	 * Enter a parse tree produced by {@link XshttpdestParser#sslHostCheck}.
	 * @param ctx the parse tree
	 */
	void enterSslHostCheck(XshttpdestParser.SslHostCheckContext ctx);
	/**
	 * Exit a parse tree produced by {@link XshttpdestParser#sslHostCheck}.
	 * @param ctx the parse tree
	 */
	void exitSslHostCheck(XshttpdestParser.SslHostCheckContext ctx);
	/**
	 * Enter a parse tree produced by {@link XshttpdestParser#pathPrefix}.
	 * @param ctx the parse tree
	 */
	void enterPathPrefix(XshttpdestParser.PathPrefixContext ctx);
	/**
	 * Exit a parse tree produced by {@link XshttpdestParser#pathPrefix}.
	 * @param ctx the parse tree
	 */
	void exitPathPrefix(XshttpdestParser.PathPrefixContext ctx);
	/**
	 * Enter a parse tree produced by {@link XshttpdestParser#authType}.
	 * @param ctx the parse tree
	 */
	void enterAuthType(XshttpdestParser.AuthTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link XshttpdestParser#authType}.
	 * @param ctx the parse tree
	 */
	void exitAuthType(XshttpdestParser.AuthTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link XshttpdestParser#samlProvider}.
	 * @param ctx the parse tree
	 */
	void enterSamlProvider(XshttpdestParser.SamlProviderContext ctx);
	/**
	 * Exit a parse tree produced by {@link XshttpdestParser#samlProvider}.
	 * @param ctx the parse tree
	 */
	void exitSamlProvider(XshttpdestParser.SamlProviderContext ctx);
	/**
	 * Enter a parse tree produced by {@link XshttpdestParser#samlACS}.
	 * @param ctx the parse tree
	 */
	void enterSamlACS(XshttpdestParser.SamlACSContext ctx);
	/**
	 * Exit a parse tree produced by {@link XshttpdestParser#samlACS}.
	 * @param ctx the parse tree
	 */
	void exitSamlACS(XshttpdestParser.SamlACSContext ctx);
	/**
	 * Enter a parse tree produced by {@link XshttpdestParser#samlAttributes}.
	 * @param ctx the parse tree
	 */
	void enterSamlAttributes(XshttpdestParser.SamlAttributesContext ctx);
	/**
	 * Exit a parse tree produced by {@link XshttpdestParser#samlAttributes}.
	 * @param ctx the parse tree
	 */
	void exitSamlAttributes(XshttpdestParser.SamlAttributesContext ctx);
	/**
	 * Enter a parse tree produced by {@link XshttpdestParser#samlNameId}.
	 * @param ctx the parse tree
	 */
	void enterSamlNameId(XshttpdestParser.SamlNameIdContext ctx);
	/**
	 * Exit a parse tree produced by {@link XshttpdestParser#samlNameId}.
	 * @param ctx the parse tree
	 */
	void exitSamlNameId(XshttpdestParser.SamlNameIdContext ctx);
	/**
	 * Enter a parse tree produced by {@link XshttpdestParser#proxyType}.
	 * @param ctx the parse tree
	 */
	void enterProxyType(XshttpdestParser.ProxyTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link XshttpdestParser#proxyType}.
	 * @param ctx the parse tree
	 */
	void exitProxyType(XshttpdestParser.ProxyTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link XshttpdestParser#proxyHost}.
	 * @param ctx the parse tree
	 */
	void enterProxyHost(XshttpdestParser.ProxyHostContext ctx);
	/**
	 * Exit a parse tree produced by {@link XshttpdestParser#proxyHost}.
	 * @param ctx the parse tree
	 */
	void exitProxyHost(XshttpdestParser.ProxyHostContext ctx);
	/**
	 * Enter a parse tree produced by {@link XshttpdestParser#proxyPort}.
	 * @param ctx the parse tree
	 */
	void enterProxyPort(XshttpdestParser.ProxyPortContext ctx);
	/**
	 * Exit a parse tree produced by {@link XshttpdestParser#proxyPort}.
	 * @param ctx the parse tree
	 */
	void exitProxyPort(XshttpdestParser.ProxyPortContext ctx);
	/**
	 * Enter a parse tree produced by {@link XshttpdestParser#timeout}.
	 * @param ctx the parse tree
	 */
	void enterTimeout(XshttpdestParser.TimeoutContext ctx);
	/**
	 * Exit a parse tree produced by {@link XshttpdestParser#timeout}.
	 * @param ctx the parse tree
	 */
	void exitTimeout(XshttpdestParser.TimeoutContext ctx);
	/**
	 * Enter a parse tree produced by {@link XshttpdestParser#remoteSID}.
	 * @param ctx the parse tree
	 */
	void enterRemoteSID(XshttpdestParser.RemoteSIDContext ctx);
	/**
	 * Exit a parse tree produced by {@link XshttpdestParser#remoteSID}.
	 * @param ctx the parse tree
	 */
	void exitRemoteSID(XshttpdestParser.RemoteSIDContext ctx);
	/**
	 * Enter a parse tree produced by {@link XshttpdestParser#remoteClient}.
	 * @param ctx the parse tree
	 */
	void enterRemoteClient(XshttpdestParser.RemoteClientContext ctx);
	/**
	 * Exit a parse tree produced by {@link XshttpdestParser#remoteClient}.
	 * @param ctx the parse tree
	 */
	void exitRemoteClient(XshttpdestParser.RemoteClientContext ctx);
	/**
	 * Enter a parse tree produced by {@link XshttpdestParser#oAuthAppConfigPackage}.
	 * @param ctx the parse tree
	 */
	void enterOAuthAppConfigPackage(XshttpdestParser.OAuthAppConfigPackageContext ctx);
	/**
	 * Exit a parse tree produced by {@link XshttpdestParser#oAuthAppConfigPackage}.
	 * @param ctx the parse tree
	 */
	void exitOAuthAppConfigPackage(XshttpdestParser.OAuthAppConfigPackageContext ctx);
	/**
	 * Enter a parse tree produced by {@link XshttpdestParser#oAuthAppConfig}.
	 * @param ctx the parse tree
	 */
	void enterOAuthAppConfig(XshttpdestParser.OAuthAppConfigContext ctx);
	/**
	 * Exit a parse tree produced by {@link XshttpdestParser#oAuthAppConfig}.
	 * @param ctx the parse tree
	 */
	void exitOAuthAppConfig(XshttpdestParser.OAuthAppConfigContext ctx);
	/**
	 * Enter a parse tree produced by {@link XshttpdestParser#sslAuthValue}.
	 * @param ctx the parse tree
	 */
	void enterSslAuthValue(XshttpdestParser.SslAuthValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link XshttpdestParser#sslAuthValue}.
	 * @param ctx the parse tree
	 */
	void exitSslAuthValue(XshttpdestParser.SslAuthValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link XshttpdestParser#samlNameIdList}.
	 * @param ctx the parse tree
	 */
	void enterSamlNameIdList(XshttpdestParser.SamlNameIdListContext ctx);
	/**
	 * Exit a parse tree produced by {@link XshttpdestParser#samlNameIdList}.
	 * @param ctx the parse tree
	 */
	void exitSamlNameIdList(XshttpdestParser.SamlNameIdListContext ctx);
	/**
	 * Enter a parse tree produced by {@link XshttpdestParser#authTypeValue}.
	 * @param ctx the parse tree
	 */
	void enterAuthTypeValue(XshttpdestParser.AuthTypeValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link XshttpdestParser#authTypeValue}.
	 * @param ctx the parse tree
	 */
	void exitAuthTypeValue(XshttpdestParser.AuthTypeValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link XshttpdestParser#proxyTypeValue}.
	 * @param ctx the parse tree
	 */
	void enterProxyTypeValue(XshttpdestParser.ProxyTypeValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link XshttpdestParser#proxyTypeValue}.
	 * @param ctx the parse tree
	 */
	void exitProxyTypeValue(XshttpdestParser.ProxyTypeValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link XshttpdestParser#unsignedInt}.
	 * @param ctx the parse tree
	 */
	void enterUnsignedInt(XshttpdestParser.UnsignedIntContext ctx);
	/**
	 * Exit a parse tree produced by {@link XshttpdestParser#unsignedInt}.
	 * @param ctx the parse tree
	 */
	void exitUnsignedInt(XshttpdestParser.UnsignedIntContext ctx);
}