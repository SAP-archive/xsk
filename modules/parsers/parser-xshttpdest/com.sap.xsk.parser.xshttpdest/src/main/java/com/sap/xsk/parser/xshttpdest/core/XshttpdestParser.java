// Generated from /Users/C5322976/Desktop/Workspace/Dirigible/xsk/modules/parsers/xsk-modules-parsers-xshttpdest-parent/xsk-modules-parsers-xshttpdest/src/main/antlr4/Xshttpdest.g4 by ANTLR 4.9.1
package com.sap.xsk.parser.xshttpdest.core;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class XshttpdestParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, STRING=34, INT=35, BOOL=36, EQ=37, SC=38, WS=39, LINE_COMMENT=40;
	public static final int
		RULE_xshttpdest = 0, RULE_property = 1, RULE_host = 2, RULE_port = 3, 
		RULE_description = 4, RULE_useSSL = 5, RULE_sslAuth = 6, RULE_sslHostCheck = 7, 
		RULE_pathPrefix = 8, RULE_authType = 9, RULE_samlProvider = 10, RULE_samlACS = 11, 
		RULE_samlAttributes = 12, RULE_samlNameId = 13, RULE_proxyType = 14, RULE_proxyHost = 15, 
		RULE_proxyPort = 16, RULE_timeout = 17, RULE_remoteSID = 18, RULE_remoteClient = 19, 
		RULE_oAuthAppConfigPackage = 20, RULE_oAuthAppConfig = 21, RULE_sslAuthValue = 22, 
		RULE_samlNameIdList = 23, RULE_authTypeValue = 24, RULE_proxyTypeValue = 25, 
		RULE_unsignedInt = 26;
	private static String[] makeRuleNames() {
		return new String[] {
			"xshttpdest", "property", "host", "port", "description", "useSSL", "sslAuth", 
			"sslHostCheck", "pathPrefix", "authType", "samlProvider", "samlACS", 
			"samlAttributes", "samlNameId", "proxyType", "proxyHost", "proxyPort", 
			"timeout", "remoteSID", "remoteClient", "oAuthAppConfigPackage", "oAuthAppConfig", 
			"sslAuthValue", "samlNameIdList", "authTypeValue", "proxyTypeValue", 
			"unsignedInt"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'host'", "'port'", "'description'", "'useSSL'", "'sslAuth'", "'sslHostCheck'", 
			"'pathPrefix'", "'authType'", "'samlProvider'", "'samlACS'", "'samlAttributes'", 
			"'samlNameId'", "'proxyType'", "'proxyHost'", "'proxyPort'", "'timeout'", 
			"'remoteSID'", "'remoteClient'", "'oAuthAppConfigPackage'", "'oAuthAppConfig'", 
			"'client'", "'anonymous'", "'['", "','", "']'", "'none'", "'basic'", 
			"'AssertionTicket'", "'SamlAssertion'", "'SamlAssertionPropagation'", 
			"'http'", "'socks'", "'-'", null, null, null, "'='", "';'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, "STRING", 
			"INT", "BOOL", "EQ", "SC", "WS", "LINE_COMMENT"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Xshttpdest.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public XshttpdestParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class XshttpdestContext extends ParserRuleContext {
		public List<PropertyContext> property() {
			return getRuleContexts(PropertyContext.class);
		}
		public PropertyContext property(int i) {
			return getRuleContext(PropertyContext.class,i);
		}
		public XshttpdestContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_xshttpdest; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XshttpdestListener ) ((XshttpdestListener)listener).enterXshttpdest(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XshttpdestListener ) ((XshttpdestListener)listener).exitXshttpdest(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XshttpdestVisitor ) return ((XshttpdestVisitor<? extends T>)visitor).visitXshttpdest(this);
			else return visitor.visitChildren(this);
		}
	}

	public final XshttpdestContext xshttpdest() throws RecognitionException {
		XshttpdestContext _localctx = new XshttpdestContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_xshttpdest);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(55); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(54);
				property();
				}
				}
				setState(57); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PropertyContext extends ParserRuleContext {
		public HostContext host() {
			return getRuleContext(HostContext.class,0);
		}
		public PortContext port() {
			return getRuleContext(PortContext.class,0);
		}
		public DescriptionContext description() {
			return getRuleContext(DescriptionContext.class,0);
		}
		public UseSSLContext useSSL() {
			return getRuleContext(UseSSLContext.class,0);
		}
		public SslAuthContext sslAuth() {
			return getRuleContext(SslAuthContext.class,0);
		}
		public SslHostCheckContext sslHostCheck() {
			return getRuleContext(SslHostCheckContext.class,0);
		}
		public PathPrefixContext pathPrefix() {
			return getRuleContext(PathPrefixContext.class,0);
		}
		public AuthTypeContext authType() {
			return getRuleContext(AuthTypeContext.class,0);
		}
		public SamlProviderContext samlProvider() {
			return getRuleContext(SamlProviderContext.class,0);
		}
		public SamlACSContext samlACS() {
			return getRuleContext(SamlACSContext.class,0);
		}
		public SamlAttributesContext samlAttributes() {
			return getRuleContext(SamlAttributesContext.class,0);
		}
		public SamlNameIdContext samlNameId() {
			return getRuleContext(SamlNameIdContext.class,0);
		}
		public ProxyTypeContext proxyType() {
			return getRuleContext(ProxyTypeContext.class,0);
		}
		public ProxyHostContext proxyHost() {
			return getRuleContext(ProxyHostContext.class,0);
		}
		public ProxyPortContext proxyPort() {
			return getRuleContext(ProxyPortContext.class,0);
		}
		public TimeoutContext timeout() {
			return getRuleContext(TimeoutContext.class,0);
		}
		public RemoteSIDContext remoteSID() {
			return getRuleContext(RemoteSIDContext.class,0);
		}
		public RemoteClientContext remoteClient() {
			return getRuleContext(RemoteClientContext.class,0);
		}
		public OAuthAppConfigPackageContext oAuthAppConfigPackage() {
			return getRuleContext(OAuthAppConfigPackageContext.class,0);
		}
		public OAuthAppConfigContext oAuthAppConfig() {
			return getRuleContext(OAuthAppConfigContext.class,0);
		}
		public PropertyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_property; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XshttpdestListener ) ((XshttpdestListener)listener).enterProperty(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XshttpdestListener ) ((XshttpdestListener)listener).exitProperty(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XshttpdestVisitor ) return ((XshttpdestVisitor<? extends T>)visitor).visitProperty(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PropertyContext property() throws RecognitionException {
		PropertyContext _localctx = new PropertyContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_property);
		try {
			setState(79);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
				enterOuterAlt(_localctx, 1);
				{
				setState(59);
				host();
				}
				break;
			case T__1:
				enterOuterAlt(_localctx, 2);
				{
				setState(60);
				port();
				}
				break;
			case T__2:
				enterOuterAlt(_localctx, 3);
				{
				setState(61);
				description();
				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 4);
				{
				setState(62);
				useSSL();
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 5);
				{
				setState(63);
				sslAuth();
				}
				break;
			case T__5:
				enterOuterAlt(_localctx, 6);
				{
				setState(64);
				sslHostCheck();
				}
				break;
			case T__6:
				enterOuterAlt(_localctx, 7);
				{
				setState(65);
				pathPrefix();
				}
				break;
			case T__7:
				enterOuterAlt(_localctx, 8);
				{
				setState(66);
				authType();
				}
				break;
			case T__8:
				enterOuterAlt(_localctx, 9);
				{
				setState(67);
				samlProvider();
				}
				break;
			case T__9:
				enterOuterAlt(_localctx, 10);
				{
				setState(68);
				samlACS();
				}
				break;
			case T__10:
				enterOuterAlt(_localctx, 11);
				{
				setState(69);
				samlAttributes();
				}
				break;
			case T__11:
				enterOuterAlt(_localctx, 12);
				{
				setState(70);
				samlNameId();
				}
				break;
			case T__12:
				enterOuterAlt(_localctx, 13);
				{
				setState(71);
				proxyType();
				}
				break;
			case T__13:
				enterOuterAlt(_localctx, 14);
				{
				setState(72);
				proxyHost();
				}
				break;
			case T__14:
				enterOuterAlt(_localctx, 15);
				{
				setState(73);
				proxyPort();
				}
				break;
			case T__15:
				enterOuterAlt(_localctx, 16);
				{
				setState(74);
				timeout();
				}
				break;
			case T__16:
				enterOuterAlt(_localctx, 17);
				{
				setState(75);
				remoteSID();
				}
				break;
			case T__17:
				enterOuterAlt(_localctx, 18);
				{
				setState(76);
				remoteClient();
				}
				break;
			case T__18:
				enterOuterAlt(_localctx, 19);
				{
				setState(77);
				oAuthAppConfigPackage();
				}
				break;
			case T__19:
				enterOuterAlt(_localctx, 20);
				{
				setState(78);
				oAuthAppConfig();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class HostContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(XshttpdestParser.EQ, 0); }
		public TerminalNode STRING() { return getToken(XshttpdestParser.STRING, 0); }
		public TerminalNode SC() { return getToken(XshttpdestParser.SC, 0); }
		public HostContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_host; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XshttpdestListener ) ((XshttpdestListener)listener).enterHost(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XshttpdestListener ) ((XshttpdestListener)listener).exitHost(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XshttpdestVisitor ) return ((XshttpdestVisitor<? extends T>)visitor).visitHost(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HostContext host() throws RecognitionException {
		HostContext _localctx = new HostContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_host);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(81);
			match(T__0);
			setState(82);
			match(EQ);
			setState(83);
			match(STRING);
			setState(84);
			match(SC);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PortContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(XshttpdestParser.EQ, 0); }
		public TerminalNode INT() { return getToken(XshttpdestParser.INT, 0); }
		public TerminalNode SC() { return getToken(XshttpdestParser.SC, 0); }
		public PortContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_port; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XshttpdestListener ) ((XshttpdestListener)listener).enterPort(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XshttpdestListener ) ((XshttpdestListener)listener).exitPort(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XshttpdestVisitor ) return ((XshttpdestVisitor<? extends T>)visitor).visitPort(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PortContext port() throws RecognitionException {
		PortContext _localctx = new PortContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_port);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(86);
			match(T__1);
			setState(87);
			match(EQ);
			setState(88);
			match(INT);
			setState(89);
			match(SC);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DescriptionContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(XshttpdestParser.EQ, 0); }
		public TerminalNode STRING() { return getToken(XshttpdestParser.STRING, 0); }
		public TerminalNode SC() { return getToken(XshttpdestParser.SC, 0); }
		public DescriptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_description; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XshttpdestListener ) ((XshttpdestListener)listener).enterDescription(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XshttpdestListener ) ((XshttpdestListener)listener).exitDescription(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XshttpdestVisitor ) return ((XshttpdestVisitor<? extends T>)visitor).visitDescription(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DescriptionContext description() throws RecognitionException {
		DescriptionContext _localctx = new DescriptionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_description);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(91);
			match(T__2);
			setState(92);
			match(EQ);
			setState(93);
			match(STRING);
			setState(94);
			match(SC);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UseSSLContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(XshttpdestParser.EQ, 0); }
		public TerminalNode BOOL() { return getToken(XshttpdestParser.BOOL, 0); }
		public TerminalNode SC() { return getToken(XshttpdestParser.SC, 0); }
		public UseSSLContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_useSSL; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XshttpdestListener ) ((XshttpdestListener)listener).enterUseSSL(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XshttpdestListener ) ((XshttpdestListener)listener).exitUseSSL(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XshttpdestVisitor ) return ((XshttpdestVisitor<? extends T>)visitor).visitUseSSL(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UseSSLContext useSSL() throws RecognitionException {
		UseSSLContext _localctx = new UseSSLContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_useSSL);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(96);
			match(T__3);
			setState(97);
			match(EQ);
			setState(98);
			match(BOOL);
			setState(99);
			match(SC);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SslAuthContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(XshttpdestParser.EQ, 0); }
		public SslAuthValueContext sslAuthValue() {
			return getRuleContext(SslAuthValueContext.class,0);
		}
		public TerminalNode SC() { return getToken(XshttpdestParser.SC, 0); }
		public SslAuthContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sslAuth; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XshttpdestListener ) ((XshttpdestListener)listener).enterSslAuth(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XshttpdestListener ) ((XshttpdestListener)listener).exitSslAuth(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XshttpdestVisitor ) return ((XshttpdestVisitor<? extends T>)visitor).visitSslAuth(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SslAuthContext sslAuth() throws RecognitionException {
		SslAuthContext _localctx = new SslAuthContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_sslAuth);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(101);
			match(T__4);
			setState(102);
			match(EQ);
			setState(103);
			sslAuthValue();
			setState(104);
			match(SC);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SslHostCheckContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(XshttpdestParser.EQ, 0); }
		public TerminalNode BOOL() { return getToken(XshttpdestParser.BOOL, 0); }
		public TerminalNode SC() { return getToken(XshttpdestParser.SC, 0); }
		public SslHostCheckContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sslHostCheck; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XshttpdestListener ) ((XshttpdestListener)listener).enterSslHostCheck(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XshttpdestListener ) ((XshttpdestListener)listener).exitSslHostCheck(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XshttpdestVisitor ) return ((XshttpdestVisitor<? extends T>)visitor).visitSslHostCheck(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SslHostCheckContext sslHostCheck() throws RecognitionException {
		SslHostCheckContext _localctx = new SslHostCheckContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_sslHostCheck);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(106);
			match(T__5);
			setState(107);
			match(EQ);
			setState(108);
			match(BOOL);
			setState(109);
			match(SC);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PathPrefixContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(XshttpdestParser.EQ, 0); }
		public TerminalNode STRING() { return getToken(XshttpdestParser.STRING, 0); }
		public TerminalNode SC() { return getToken(XshttpdestParser.SC, 0); }
		public PathPrefixContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pathPrefix; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XshttpdestListener ) ((XshttpdestListener)listener).enterPathPrefix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XshttpdestListener ) ((XshttpdestListener)listener).exitPathPrefix(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XshttpdestVisitor ) return ((XshttpdestVisitor<? extends T>)visitor).visitPathPrefix(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PathPrefixContext pathPrefix() throws RecognitionException {
		PathPrefixContext _localctx = new PathPrefixContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_pathPrefix);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(111);
			match(T__6);
			setState(112);
			match(EQ);
			setState(113);
			match(STRING);
			setState(114);
			match(SC);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AuthTypeContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(XshttpdestParser.EQ, 0); }
		public AuthTypeValueContext authTypeValue() {
			return getRuleContext(AuthTypeValueContext.class,0);
		}
		public TerminalNode SC() { return getToken(XshttpdestParser.SC, 0); }
		public AuthTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_authType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XshttpdestListener ) ((XshttpdestListener)listener).enterAuthType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XshttpdestListener ) ((XshttpdestListener)listener).exitAuthType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XshttpdestVisitor ) return ((XshttpdestVisitor<? extends T>)visitor).visitAuthType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AuthTypeContext authType() throws RecognitionException {
		AuthTypeContext _localctx = new AuthTypeContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_authType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(116);
			match(T__7);
			setState(117);
			match(EQ);
			setState(118);
			authTypeValue();
			setState(119);
			match(SC);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SamlProviderContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(XshttpdestParser.EQ, 0); }
		public TerminalNode STRING() { return getToken(XshttpdestParser.STRING, 0); }
		public TerminalNode SC() { return getToken(XshttpdestParser.SC, 0); }
		public SamlProviderContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_samlProvider; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XshttpdestListener ) ((XshttpdestListener)listener).enterSamlProvider(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XshttpdestListener ) ((XshttpdestListener)listener).exitSamlProvider(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XshttpdestVisitor ) return ((XshttpdestVisitor<? extends T>)visitor).visitSamlProvider(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SamlProviderContext samlProvider() throws RecognitionException {
		SamlProviderContext _localctx = new SamlProviderContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_samlProvider);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(121);
			match(T__8);
			setState(122);
			match(EQ);
			setState(123);
			match(STRING);
			setState(124);
			match(SC);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SamlACSContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(XshttpdestParser.EQ, 0); }
		public TerminalNode STRING() { return getToken(XshttpdestParser.STRING, 0); }
		public TerminalNode SC() { return getToken(XshttpdestParser.SC, 0); }
		public SamlACSContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_samlACS; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XshttpdestListener ) ((XshttpdestListener)listener).enterSamlACS(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XshttpdestListener ) ((XshttpdestListener)listener).exitSamlACS(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XshttpdestVisitor ) return ((XshttpdestVisitor<? extends T>)visitor).visitSamlACS(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SamlACSContext samlACS() throws RecognitionException {
		SamlACSContext _localctx = new SamlACSContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_samlACS);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(126);
			match(T__9);
			setState(127);
			match(EQ);
			setState(128);
			match(STRING);
			setState(129);
			match(SC);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SamlAttributesContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(XshttpdestParser.EQ, 0); }
		public TerminalNode STRING() { return getToken(XshttpdestParser.STRING, 0); }
		public TerminalNode SC() { return getToken(XshttpdestParser.SC, 0); }
		public SamlAttributesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_samlAttributes; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XshttpdestListener ) ((XshttpdestListener)listener).enterSamlAttributes(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XshttpdestListener ) ((XshttpdestListener)listener).exitSamlAttributes(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XshttpdestVisitor ) return ((XshttpdestVisitor<? extends T>)visitor).visitSamlAttributes(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SamlAttributesContext samlAttributes() throws RecognitionException {
		SamlAttributesContext _localctx = new SamlAttributesContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_samlAttributes);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(131);
			match(T__10);
			setState(132);
			match(EQ);
			setState(133);
			match(STRING);
			setState(134);
			match(SC);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SamlNameIdContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(XshttpdestParser.EQ, 0); }
		public SamlNameIdListContext samlNameIdList() {
			return getRuleContext(SamlNameIdListContext.class,0);
		}
		public TerminalNode SC() { return getToken(XshttpdestParser.SC, 0); }
		public SamlNameIdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_samlNameId; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XshttpdestListener ) ((XshttpdestListener)listener).enterSamlNameId(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XshttpdestListener ) ((XshttpdestListener)listener).exitSamlNameId(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XshttpdestVisitor ) return ((XshttpdestVisitor<? extends T>)visitor).visitSamlNameId(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SamlNameIdContext samlNameId() throws RecognitionException {
		SamlNameIdContext _localctx = new SamlNameIdContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_samlNameId);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(136);
			match(T__11);
			setState(137);
			match(EQ);
			setState(138);
			samlNameIdList();
			setState(139);
			match(SC);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ProxyTypeContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(XshttpdestParser.EQ, 0); }
		public ProxyTypeValueContext proxyTypeValue() {
			return getRuleContext(ProxyTypeValueContext.class,0);
		}
		public TerminalNode SC() { return getToken(XshttpdestParser.SC, 0); }
		public ProxyTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_proxyType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XshttpdestListener ) ((XshttpdestListener)listener).enterProxyType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XshttpdestListener ) ((XshttpdestListener)listener).exitProxyType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XshttpdestVisitor ) return ((XshttpdestVisitor<? extends T>)visitor).visitProxyType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProxyTypeContext proxyType() throws RecognitionException {
		ProxyTypeContext _localctx = new ProxyTypeContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_proxyType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(141);
			match(T__12);
			setState(142);
			match(EQ);
			setState(143);
			proxyTypeValue();
			setState(144);
			match(SC);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ProxyHostContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(XshttpdestParser.EQ, 0); }
		public TerminalNode STRING() { return getToken(XshttpdestParser.STRING, 0); }
		public TerminalNode SC() { return getToken(XshttpdestParser.SC, 0); }
		public ProxyHostContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_proxyHost; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XshttpdestListener ) ((XshttpdestListener)listener).enterProxyHost(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XshttpdestListener ) ((XshttpdestListener)listener).exitProxyHost(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XshttpdestVisitor ) return ((XshttpdestVisitor<? extends T>)visitor).visitProxyHost(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProxyHostContext proxyHost() throws RecognitionException {
		ProxyHostContext _localctx = new ProxyHostContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_proxyHost);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(146);
			match(T__13);
			setState(147);
			match(EQ);
			setState(148);
			match(STRING);
			setState(149);
			match(SC);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ProxyPortContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(XshttpdestParser.EQ, 0); }
		public TerminalNode INT() { return getToken(XshttpdestParser.INT, 0); }
		public TerminalNode SC() { return getToken(XshttpdestParser.SC, 0); }
		public ProxyPortContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_proxyPort; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XshttpdestListener ) ((XshttpdestListener)listener).enterProxyPort(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XshttpdestListener ) ((XshttpdestListener)listener).exitProxyPort(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XshttpdestVisitor ) return ((XshttpdestVisitor<? extends T>)visitor).visitProxyPort(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProxyPortContext proxyPort() throws RecognitionException {
		ProxyPortContext _localctx = new ProxyPortContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_proxyPort);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(151);
			match(T__14);
			setState(152);
			match(EQ);
			setState(153);
			match(INT);
			setState(154);
			match(SC);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TimeoutContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(XshttpdestParser.EQ, 0); }
		public UnsignedIntContext unsignedInt() {
			return getRuleContext(UnsignedIntContext.class,0);
		}
		public TerminalNode SC() { return getToken(XshttpdestParser.SC, 0); }
		public TimeoutContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_timeout; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XshttpdestListener ) ((XshttpdestListener)listener).enterTimeout(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XshttpdestListener ) ((XshttpdestListener)listener).exitTimeout(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XshttpdestVisitor ) return ((XshttpdestVisitor<? extends T>)visitor).visitTimeout(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TimeoutContext timeout() throws RecognitionException {
		TimeoutContext _localctx = new TimeoutContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_timeout);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(156);
			match(T__15);
			setState(157);
			match(EQ);
			setState(158);
			unsignedInt();
			setState(159);
			match(SC);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RemoteSIDContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(XshttpdestParser.EQ, 0); }
		public TerminalNode STRING() { return getToken(XshttpdestParser.STRING, 0); }
		public TerminalNode SC() { return getToken(XshttpdestParser.SC, 0); }
		public RemoteSIDContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_remoteSID; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XshttpdestListener ) ((XshttpdestListener)listener).enterRemoteSID(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XshttpdestListener ) ((XshttpdestListener)listener).exitRemoteSID(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XshttpdestVisitor ) return ((XshttpdestVisitor<? extends T>)visitor).visitRemoteSID(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RemoteSIDContext remoteSID() throws RecognitionException {
		RemoteSIDContext _localctx = new RemoteSIDContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_remoteSID);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(161);
			match(T__16);
			setState(162);
			match(EQ);
			setState(163);
			match(STRING);
			setState(164);
			match(SC);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RemoteClientContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(XshttpdestParser.EQ, 0); }
		public TerminalNode STRING() { return getToken(XshttpdestParser.STRING, 0); }
		public TerminalNode SC() { return getToken(XshttpdestParser.SC, 0); }
		public RemoteClientContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_remoteClient; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XshttpdestListener ) ((XshttpdestListener)listener).enterRemoteClient(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XshttpdestListener ) ((XshttpdestListener)listener).exitRemoteClient(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XshttpdestVisitor ) return ((XshttpdestVisitor<? extends T>)visitor).visitRemoteClient(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RemoteClientContext remoteClient() throws RecognitionException {
		RemoteClientContext _localctx = new RemoteClientContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_remoteClient);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(166);
			match(T__17);
			setState(167);
			match(EQ);
			setState(168);
			match(STRING);
			setState(169);
			match(SC);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OAuthAppConfigPackageContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(XshttpdestParser.EQ, 0); }
		public TerminalNode STRING() { return getToken(XshttpdestParser.STRING, 0); }
		public TerminalNode SC() { return getToken(XshttpdestParser.SC, 0); }
		public OAuthAppConfigPackageContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_oAuthAppConfigPackage; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XshttpdestListener ) ((XshttpdestListener)listener).enterOAuthAppConfigPackage(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XshttpdestListener ) ((XshttpdestListener)listener).exitOAuthAppConfigPackage(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XshttpdestVisitor ) return ((XshttpdestVisitor<? extends T>)visitor).visitOAuthAppConfigPackage(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OAuthAppConfigPackageContext oAuthAppConfigPackage() throws RecognitionException {
		OAuthAppConfigPackageContext _localctx = new OAuthAppConfigPackageContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_oAuthAppConfigPackage);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(171);
			match(T__18);
			setState(172);
			match(EQ);
			setState(173);
			match(STRING);
			setState(174);
			match(SC);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OAuthAppConfigContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(XshttpdestParser.EQ, 0); }
		public TerminalNode STRING() { return getToken(XshttpdestParser.STRING, 0); }
		public TerminalNode SC() { return getToken(XshttpdestParser.SC, 0); }
		public OAuthAppConfigContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_oAuthAppConfig; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XshttpdestListener ) ((XshttpdestListener)listener).enterOAuthAppConfig(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XshttpdestListener ) ((XshttpdestListener)listener).exitOAuthAppConfig(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XshttpdestVisitor ) return ((XshttpdestVisitor<? extends T>)visitor).visitOAuthAppConfig(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OAuthAppConfigContext oAuthAppConfig() throws RecognitionException {
		OAuthAppConfigContext _localctx = new OAuthAppConfigContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_oAuthAppConfig);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(176);
			match(T__19);
			setState(177);
			match(EQ);
			setState(178);
			match(STRING);
			setState(179);
			match(SC);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SslAuthValueContext extends ParserRuleContext {
		public SslAuthValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sslAuthValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XshttpdestListener ) ((XshttpdestListener)listener).enterSslAuthValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XshttpdestListener ) ((XshttpdestListener)listener).exitSslAuthValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XshttpdestVisitor ) return ((XshttpdestVisitor<? extends T>)visitor).visitSslAuthValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SslAuthValueContext sslAuthValue() throws RecognitionException {
		SslAuthValueContext _localctx = new SslAuthValueContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_sslAuthValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(181);
			_la = _input.LA(1);
			if ( !(_la==T__20 || _la==T__21) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SamlNameIdListContext extends ParserRuleContext {
		public List<TerminalNode> STRING() { return getTokens(XshttpdestParser.STRING); }
		public TerminalNode STRING(int i) {
			return getToken(XshttpdestParser.STRING, i);
		}
		public SamlNameIdListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_samlNameIdList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XshttpdestListener ) ((XshttpdestListener)listener).enterSamlNameIdList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XshttpdestListener ) ((XshttpdestListener)listener).exitSamlNameIdList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XshttpdestVisitor ) return ((XshttpdestVisitor<? extends T>)visitor).visitSamlNameIdList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SamlNameIdListContext samlNameIdList() throws RecognitionException {
		SamlNameIdListContext _localctx = new SamlNameIdListContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_samlNameIdList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(183);
			match(T__22);
			setState(184);
			match(STRING);
			setState(189);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__23) {
				{
				{
				setState(185);
				match(T__23);
				setState(186);
				match(STRING);
				}
				}
				setState(191);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(192);
			match(T__24);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AuthTypeValueContext extends ParserRuleContext {
		public AuthTypeValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_authTypeValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XshttpdestListener ) ((XshttpdestListener)listener).enterAuthTypeValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XshttpdestListener ) ((XshttpdestListener)listener).exitAuthTypeValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XshttpdestVisitor ) return ((XshttpdestVisitor<? extends T>)visitor).visitAuthTypeValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AuthTypeValueContext authTypeValue() throws RecognitionException {
		AuthTypeValueContext _localctx = new AuthTypeValueContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_authTypeValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(194);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__25) | (1L << T__26) | (1L << T__27) | (1L << T__28) | (1L << T__29))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ProxyTypeValueContext extends ParserRuleContext {
		public ProxyTypeValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_proxyTypeValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XshttpdestListener ) ((XshttpdestListener)listener).enterProxyTypeValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XshttpdestListener ) ((XshttpdestListener)listener).exitProxyTypeValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XshttpdestVisitor ) return ((XshttpdestVisitor<? extends T>)visitor).visitProxyTypeValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProxyTypeValueContext proxyTypeValue() throws RecognitionException {
		ProxyTypeValueContext _localctx = new ProxyTypeValueContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_proxyTypeValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(196);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__25) | (1L << T__30) | (1L << T__31))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UnsignedIntContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(XshttpdestParser.INT, 0); }
		public UnsignedIntContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unsignedInt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XshttpdestListener ) ((XshttpdestListener)listener).enterUnsignedInt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XshttpdestListener ) ((XshttpdestListener)listener).exitUnsignedInt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XshttpdestVisitor ) return ((XshttpdestVisitor<? extends T>)visitor).visitUnsignedInt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnsignedIntContext unsignedInt() throws RecognitionException {
		UnsignedIntContext _localctx = new UnsignedIntContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_unsignedInt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(199);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__32) {
				{
				setState(198);
				match(T__32);
				}
			}

			setState(201);
			match(INT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3*\u00ce\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\3\2\6\2:\n\2\r\2\16\2;\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3R"+
		"\n\3\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\7\3"+
		"\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n"+
		"\3\n\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r"+
		"\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20"+
		"\3\20\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23"+
		"\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\26\3\26"+
		"\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\31\3\31\3\31\3\31"+
		"\7\31\u00be\n\31\f\31\16\31\u00c1\13\31\3\31\3\31\3\32\3\32\3\33\3\33"+
		"\3\34\5\34\u00ca\n\34\3\34\3\34\3\34\2\2\35\2\4\6\b\n\f\16\20\22\24\26"+
		"\30\32\34\36 \"$&(*,.\60\62\64\66\2\5\3\2\27\30\3\2\34 \4\2\34\34!\"\2"+
		"\u00c8\29\3\2\2\2\4Q\3\2\2\2\6S\3\2\2\2\bX\3\2\2\2\n]\3\2\2\2\fb\3\2\2"+
		"\2\16g\3\2\2\2\20l\3\2\2\2\22q\3\2\2\2\24v\3\2\2\2\26{\3\2\2\2\30\u0080"+
		"\3\2\2\2\32\u0085\3\2\2\2\34\u008a\3\2\2\2\36\u008f\3\2\2\2 \u0094\3\2"+
		"\2\2\"\u0099\3\2\2\2$\u009e\3\2\2\2&\u00a3\3\2\2\2(\u00a8\3\2\2\2*\u00ad"+
		"\3\2\2\2,\u00b2\3\2\2\2.\u00b7\3\2\2\2\60\u00b9\3\2\2\2\62\u00c4\3\2\2"+
		"\2\64\u00c6\3\2\2\2\66\u00c9\3\2\2\28:\5\4\3\298\3\2\2\2:;\3\2\2\2;9\3"+
		"\2\2\2;<\3\2\2\2<\3\3\2\2\2=R\5\6\4\2>R\5\b\5\2?R\5\n\6\2@R\5\f\7\2AR"+
		"\5\16\b\2BR\5\20\t\2CR\5\22\n\2DR\5\24\13\2ER\5\26\f\2FR\5\30\r\2GR\5"+
		"\32\16\2HR\5\34\17\2IR\5\36\20\2JR\5 \21\2KR\5\"\22\2LR\5$\23\2MR\5&\24"+
		"\2NR\5(\25\2OR\5*\26\2PR\5,\27\2Q=\3\2\2\2Q>\3\2\2\2Q?\3\2\2\2Q@\3\2\2"+
		"\2QA\3\2\2\2QB\3\2\2\2QC\3\2\2\2QD\3\2\2\2QE\3\2\2\2QF\3\2\2\2QG\3\2\2"+
		"\2QH\3\2\2\2QI\3\2\2\2QJ\3\2\2\2QK\3\2\2\2QL\3\2\2\2QM\3\2\2\2QN\3\2\2"+
		"\2QO\3\2\2\2QP\3\2\2\2R\5\3\2\2\2ST\7\3\2\2TU\7\'\2\2UV\7$\2\2VW\7(\2"+
		"\2W\7\3\2\2\2XY\7\4\2\2YZ\7\'\2\2Z[\7%\2\2[\\\7(\2\2\\\t\3\2\2\2]^\7\5"+
		"\2\2^_\7\'\2\2_`\7$\2\2`a\7(\2\2a\13\3\2\2\2bc\7\6\2\2cd\7\'\2\2de\7&"+
		"\2\2ef\7(\2\2f\r\3\2\2\2gh\7\7\2\2hi\7\'\2\2ij\5.\30\2jk\7(\2\2k\17\3"+
		"\2\2\2lm\7\b\2\2mn\7\'\2\2no\7&\2\2op\7(\2\2p\21\3\2\2\2qr\7\t\2\2rs\7"+
		"\'\2\2st\7$\2\2tu\7(\2\2u\23\3\2\2\2vw\7\n\2\2wx\7\'\2\2xy\5\62\32\2y"+
		"z\7(\2\2z\25\3\2\2\2{|\7\13\2\2|}\7\'\2\2}~\7$\2\2~\177\7(\2\2\177\27"+
		"\3\2\2\2\u0080\u0081\7\f\2\2\u0081\u0082\7\'\2\2\u0082\u0083\7$\2\2\u0083"+
		"\u0084\7(\2\2\u0084\31\3\2\2\2\u0085\u0086\7\r\2\2\u0086\u0087\7\'\2\2"+
		"\u0087\u0088\7$\2\2\u0088\u0089\7(\2\2\u0089\33\3\2\2\2\u008a\u008b\7"+
		"\16\2\2\u008b\u008c\7\'\2\2\u008c\u008d\5\60\31\2\u008d\u008e\7(\2\2\u008e"+
		"\35\3\2\2\2\u008f\u0090\7\17\2\2\u0090\u0091\7\'\2\2\u0091\u0092\5\64"+
		"\33\2\u0092\u0093\7(\2\2\u0093\37\3\2\2\2\u0094\u0095\7\20\2\2\u0095\u0096"+
		"\7\'\2\2\u0096\u0097\7$\2\2\u0097\u0098\7(\2\2\u0098!\3\2\2\2\u0099\u009a"+
		"\7\21\2\2\u009a\u009b\7\'\2\2\u009b\u009c\7%\2\2\u009c\u009d\7(\2\2\u009d"+
		"#\3\2\2\2\u009e\u009f\7\22\2\2\u009f\u00a0\7\'\2\2\u00a0\u00a1\5\66\34"+
		"\2\u00a1\u00a2\7(\2\2\u00a2%\3\2\2\2\u00a3\u00a4\7\23\2\2\u00a4\u00a5"+
		"\7\'\2\2\u00a5\u00a6\7$\2\2\u00a6\u00a7\7(\2\2\u00a7\'\3\2\2\2\u00a8\u00a9"+
		"\7\24\2\2\u00a9\u00aa\7\'\2\2\u00aa\u00ab\7$\2\2\u00ab\u00ac\7(\2\2\u00ac"+
		")\3\2\2\2\u00ad\u00ae\7\25\2\2\u00ae\u00af\7\'\2\2\u00af\u00b0\7$\2\2"+
		"\u00b0\u00b1\7(\2\2\u00b1+\3\2\2\2\u00b2\u00b3\7\26\2\2\u00b3\u00b4\7"+
		"\'\2\2\u00b4\u00b5\7$\2\2\u00b5\u00b6\7(\2\2\u00b6-\3\2\2\2\u00b7\u00b8"+
		"\t\2\2\2\u00b8/\3\2\2\2\u00b9\u00ba\7\31\2\2\u00ba\u00bf\7$\2\2\u00bb"+
		"\u00bc\7\32\2\2\u00bc\u00be\7$\2\2\u00bd\u00bb\3\2\2\2\u00be\u00c1\3\2"+
		"\2\2\u00bf\u00bd\3\2\2\2\u00bf\u00c0\3\2\2\2\u00c0\u00c2\3\2\2\2\u00c1"+
		"\u00bf\3\2\2\2\u00c2\u00c3\7\33\2\2\u00c3\61\3\2\2\2\u00c4\u00c5\t\3\2"+
		"\2\u00c5\63\3\2\2\2\u00c6\u00c7\t\4\2\2\u00c7\65\3\2\2\2\u00c8\u00ca\7"+
		"#\2\2\u00c9\u00c8\3\2\2\2\u00c9\u00ca\3\2\2\2\u00ca\u00cb\3\2\2\2\u00cb"+
		"\u00cc\7%\2\2\u00cc\67\3\2\2\2\6;Q\u00bf\u00c9";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}