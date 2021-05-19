// Generated from /Users/C5322976/Desktop/Workspace/Dirigible/xsk/modules/parsers/xsk-modules-parsers-xshttpdest-parent/xsk-modules-parsers-xshttpdest/src/main/antlr4/Xshttpdest.g4 by ANTLR 4.9.1
package com.sap.xsk.parser.xshttpdest.core;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link XshttpdestParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface XshttpdestVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link XshttpdestParser#xshttpdest}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXshttpdest(XshttpdestParser.XshttpdestContext ctx);
	/**
	 * Visit a parse tree produced by {@link XshttpdestParser#property}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProperty(XshttpdestParser.PropertyContext ctx);
	/**
	 * Visit a parse tree produced by {@link XshttpdestParser#host}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHost(XshttpdestParser.HostContext ctx);
	/**
	 * Visit a parse tree produced by {@link XshttpdestParser#port}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPort(XshttpdestParser.PortContext ctx);
	/**
	 * Visit a parse tree produced by {@link XshttpdestParser#description}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDescription(XshttpdestParser.DescriptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link XshttpdestParser#useSSL}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUseSSL(XshttpdestParser.UseSSLContext ctx);
	/**
	 * Visit a parse tree produced by {@link XshttpdestParser#sslAuth}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSslAuth(XshttpdestParser.SslAuthContext ctx);
	/**
	 * Visit a parse tree produced by {@link XshttpdestParser#sslHostCheck}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSslHostCheck(XshttpdestParser.SslHostCheckContext ctx);
	/**
	 * Visit a parse tree produced by {@link XshttpdestParser#pathPrefix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPathPrefix(XshttpdestParser.PathPrefixContext ctx);
	/**
	 * Visit a parse tree produced by {@link XshttpdestParser#authType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAuthType(XshttpdestParser.AuthTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link XshttpdestParser#samlProvider}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSamlProvider(XshttpdestParser.SamlProviderContext ctx);
	/**
	 * Visit a parse tree produced by {@link XshttpdestParser#samlACS}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSamlACS(XshttpdestParser.SamlACSContext ctx);
	/**
	 * Visit a parse tree produced by {@link XshttpdestParser#samlAttributes}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSamlAttributes(XshttpdestParser.SamlAttributesContext ctx);
	/**
	 * Visit a parse tree produced by {@link XshttpdestParser#samlNameId}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSamlNameId(XshttpdestParser.SamlNameIdContext ctx);
	/**
	 * Visit a parse tree produced by {@link XshttpdestParser#proxyType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProxyType(XshttpdestParser.ProxyTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link XshttpdestParser#proxyHost}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProxyHost(XshttpdestParser.ProxyHostContext ctx);
	/**
	 * Visit a parse tree produced by {@link XshttpdestParser#proxyPort}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProxyPort(XshttpdestParser.ProxyPortContext ctx);
	/**
	 * Visit a parse tree produced by {@link XshttpdestParser#timeout}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTimeout(XshttpdestParser.TimeoutContext ctx);
	/**
	 * Visit a parse tree produced by {@link XshttpdestParser#remoteSID}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRemoteSID(XshttpdestParser.RemoteSIDContext ctx);
	/**
	 * Visit a parse tree produced by {@link XshttpdestParser#remoteClient}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRemoteClient(XshttpdestParser.RemoteClientContext ctx);
	/**
	 * Visit a parse tree produced by {@link XshttpdestParser#oAuthAppConfigPackage}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOAuthAppConfigPackage(XshttpdestParser.OAuthAppConfigPackageContext ctx);
	/**
	 * Visit a parse tree produced by {@link XshttpdestParser#oAuthAppConfig}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOAuthAppConfig(XshttpdestParser.OAuthAppConfigContext ctx);
	/**
	 * Visit a parse tree produced by {@link XshttpdestParser#sslAuthValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSslAuthValue(XshttpdestParser.SslAuthValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link XshttpdestParser#samlNameIdList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSamlNameIdList(XshttpdestParser.SamlNameIdListContext ctx);
	/**
	 * Visit a parse tree produced by {@link XshttpdestParser#authTypeValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAuthTypeValue(XshttpdestParser.AuthTypeValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link XshttpdestParser#proxyTypeValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProxyTypeValue(XshttpdestParser.ProxyTypeValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link XshttpdestParser#unsignedInt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnsignedInt(XshttpdestParser.UnsignedIntContext ctx);
}