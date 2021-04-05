// Generated from C:/Users/I069495/git/CSCProjects/KNeo/xsk/modules/parsers/parser-hdbview/com.sap.xsk.parser.hdbview/src/main/antlr4/com/sap/xsk/parser/hdbview/core\Hdbview.g4 by ANTLR 4.9.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class HdbviewParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, BOOLEAN=9, 
		STRING=10, EQ=11, SEMICOLON=12, COMMA=13, WS=14, ESC=15, LINE_COMMENT=16, 
		COMMENT=17;
	public static final int
		RULE_hdbviewDefinition = 0, RULE_schemaProp = 1, RULE_publicProp = 2, 
		RULE_queryProp = 3, RULE_dependsOnProp = 4, RULE_dependsOnTable = 5, RULE_dependsOnView = 6;
	private static String[] makeRuleNames() {
		return new String[] {
			"hdbviewDefinition", "schemaProp", "publicProp", "queryProp", "dependsOnProp", 
			"dependsOnTable", "dependsOnView"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'schema'", "'public'", "'query'", "'depends_on'", "'['", "']'", 
			"'depends_on_table'", "'depends_on_view'", null, null, "'='", "';'", 
			"','"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, "BOOLEAN", "STRING", 
			"EQ", "SEMICOLON", "COMMA", "WS", "ESC", "LINE_COMMENT", "COMMENT"
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
	public String getGrammarFileName() { return "Hdbview.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public HdbviewParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class HdbviewDefinitionContext extends ParserRuleContext {
		public SchemaPropContext schemaProp() {
			return getRuleContext(SchemaPropContext.class,0);
		}
		public QueryPropContext queryProp() {
			return getRuleContext(QueryPropContext.class,0);
		}
		public PublicPropContext publicProp() {
			return getRuleContext(PublicPropContext.class,0);
		}
		public DependsOnPropContext dependsOnProp() {
			return getRuleContext(DependsOnPropContext.class,0);
		}
		public DependsOnTableContext dependsOnTable() {
			return getRuleContext(DependsOnTableContext.class,0);
		}
		public DependsOnViewContext dependsOnView() {
			return getRuleContext(DependsOnViewContext.class,0);
		}
		public HdbviewDefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_hdbviewDefinition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbviewListener ) ((HdbviewListener)listener).enterHdbviewDefinition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbviewListener ) ((HdbviewListener)listener).exitHdbviewDefinition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbviewVisitor ) return ((HdbviewVisitor<? extends T>)visitor).visitHdbviewDefinition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HdbviewDefinitionContext hdbviewDefinition() throws RecognitionException {
		HdbviewDefinitionContext _localctx = new HdbviewDefinitionContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_hdbviewDefinition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(14);
			schemaProp();
			setState(16);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__1) {
				{
				setState(15);
				publicProp();
				}
			}

			setState(18);
			queryProp();
			setState(20);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__3) {
				{
				setState(19);
				dependsOnProp();
				}
			}

			setState(23);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__6) {
				{
				setState(22);
				dependsOnTable();
				}
			}

			setState(26);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__7) {
				{
				setState(25);
				dependsOnView();
				}
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

	public static class SchemaPropContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(HdbviewParser.EQ, 0); }
		public TerminalNode STRING() { return getToken(HdbviewParser.STRING, 0); }
		public TerminalNode SEMICOLON() { return getToken(HdbviewParser.SEMICOLON, 0); }
		public SchemaPropContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_schemaProp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbviewListener ) ((HdbviewListener)listener).enterSchemaProp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbviewListener ) ((HdbviewListener)listener).exitSchemaProp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbviewVisitor ) return ((HdbviewVisitor<? extends T>)visitor).visitSchemaProp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SchemaPropContext schemaProp() throws RecognitionException {
		SchemaPropContext _localctx = new SchemaPropContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_schemaProp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(28);
			match(T__0);
			setState(29);
			match(EQ);
			setState(30);
			match(STRING);
			setState(31);
			match(SEMICOLON);
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

	public static class PublicPropContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(HdbviewParser.EQ, 0); }
		public TerminalNode BOOLEAN() { return getToken(HdbviewParser.BOOLEAN, 0); }
		public TerminalNode SEMICOLON() { return getToken(HdbviewParser.SEMICOLON, 0); }
		public PublicPropContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_publicProp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbviewListener ) ((HdbviewListener)listener).enterPublicProp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbviewListener ) ((HdbviewListener)listener).exitPublicProp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbviewVisitor ) return ((HdbviewVisitor<? extends T>)visitor).visitPublicProp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PublicPropContext publicProp() throws RecognitionException {
		PublicPropContext _localctx = new PublicPropContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_publicProp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(33);
			match(T__1);
			setState(34);
			match(EQ);
			setState(35);
			match(BOOLEAN);
			setState(36);
			match(SEMICOLON);
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

	public static class QueryPropContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(HdbviewParser.EQ, 0); }
		public TerminalNode STRING() { return getToken(HdbviewParser.STRING, 0); }
		public TerminalNode SEMICOLON() { return getToken(HdbviewParser.SEMICOLON, 0); }
		public QueryPropContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_queryProp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbviewListener ) ((HdbviewListener)listener).enterQueryProp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbviewListener ) ((HdbviewListener)listener).exitQueryProp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbviewVisitor ) return ((HdbviewVisitor<? extends T>)visitor).visitQueryProp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QueryPropContext queryProp() throws RecognitionException {
		QueryPropContext _localctx = new QueryPropContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_queryProp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(38);
			match(T__2);
			setState(39);
			match(EQ);
			setState(40);
			match(STRING);
			setState(41);
			match(SEMICOLON);
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

	public static class DependsOnPropContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(HdbviewParser.EQ, 0); }
		public TerminalNode SEMICOLON() { return getToken(HdbviewParser.SEMICOLON, 0); }
		public List<TerminalNode> STRING() { return getTokens(HdbviewParser.STRING); }
		public TerminalNode STRING(int i) {
			return getToken(HdbviewParser.STRING, i);
		}
		public List<TerminalNode> COMMA() { return getTokens(HdbviewParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(HdbviewParser.COMMA, i);
		}
		public DependsOnPropContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dependsOnProp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbviewListener ) ((HdbviewListener)listener).enterDependsOnProp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbviewListener ) ((HdbviewListener)listener).exitDependsOnProp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbviewVisitor ) return ((HdbviewVisitor<? extends T>)visitor).visitDependsOnProp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DependsOnPropContext dependsOnProp() throws RecognitionException {
		DependsOnPropContext _localctx = new DependsOnPropContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_dependsOnProp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(43);
			match(T__3);
			setState(44);
			match(EQ);
			setState(45);
			match(T__4);
			setState(54);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==STRING) {
				{
				setState(46);
				match(STRING);
				setState(51);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(47);
					match(COMMA);
					setState(48);
					match(STRING);
					}
					}
					setState(53);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(56);
			match(T__5);
			setState(57);
			match(SEMICOLON);
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

	public static class DependsOnTableContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(HdbviewParser.EQ, 0); }
		public TerminalNode SEMICOLON() { return getToken(HdbviewParser.SEMICOLON, 0); }
		public List<TerminalNode> STRING() { return getTokens(HdbviewParser.STRING); }
		public TerminalNode STRING(int i) {
			return getToken(HdbviewParser.STRING, i);
		}
		public List<TerminalNode> COMMA() { return getTokens(HdbviewParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(HdbviewParser.COMMA, i);
		}
		public DependsOnTableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dependsOnTable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbviewListener ) ((HdbviewListener)listener).enterDependsOnTable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbviewListener ) ((HdbviewListener)listener).exitDependsOnTable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbviewVisitor ) return ((HdbviewVisitor<? extends T>)visitor).visitDependsOnTable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DependsOnTableContext dependsOnTable() throws RecognitionException {
		DependsOnTableContext _localctx = new DependsOnTableContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_dependsOnTable);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(59);
			match(T__6);
			setState(60);
			match(EQ);
			setState(61);
			match(T__4);
			setState(70);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==STRING) {
				{
				setState(62);
				match(STRING);
				setState(67);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(63);
					match(COMMA);
					setState(64);
					match(STRING);
					}
					}
					setState(69);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(72);
			match(T__5);
			setState(73);
			match(SEMICOLON);
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

	public static class DependsOnViewContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(HdbviewParser.EQ, 0); }
		public TerminalNode SEMICOLON() { return getToken(HdbviewParser.SEMICOLON, 0); }
		public List<TerminalNode> STRING() { return getTokens(HdbviewParser.STRING); }
		public TerminalNode STRING(int i) {
			return getToken(HdbviewParser.STRING, i);
		}
		public List<TerminalNode> COMMA() { return getTokens(HdbviewParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(HdbviewParser.COMMA, i);
		}
		public DependsOnViewContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dependsOnView; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbviewListener ) ((HdbviewListener)listener).enterDependsOnView(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbviewListener ) ((HdbviewListener)listener).exitDependsOnView(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbviewVisitor ) return ((HdbviewVisitor<? extends T>)visitor).visitDependsOnView(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DependsOnViewContext dependsOnView() throws RecognitionException {
		DependsOnViewContext _localctx = new DependsOnViewContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_dependsOnView);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(75);
			match(T__7);
			setState(76);
			match(EQ);
			setState(77);
			match(T__4);
			setState(86);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==STRING) {
				{
				setState(78);
				match(STRING);
				setState(83);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(79);
					match(COMMA);
					setState(80);
					match(STRING);
					}
					}
					setState(85);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(88);
			match(T__5);
			setState(89);
			match(SEMICOLON);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\23^\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\3\2\3\2\5\2\23\n\2\3\2\3\2"+
		"\5\2\27\n\2\3\2\5\2\32\n\2\3\2\5\2\35\n\2\3\3\3\3\3\3\3\3\3\3\3\4\3\4"+
		"\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\7\6\64\n\6\f"+
		"\6\16\6\67\13\6\5\69\n\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\7\7D\n\7"+
		"\f\7\16\7G\13\7\5\7I\n\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\7\bT\n\b"+
		"\f\b\16\bW\13\b\5\bY\n\b\3\b\3\b\3\b\3\b\2\2\t\2\4\6\b\n\f\16\2\2\2`\2"+
		"\20\3\2\2\2\4\36\3\2\2\2\6#\3\2\2\2\b(\3\2\2\2\n-\3\2\2\2\f=\3\2\2\2\16"+
		"M\3\2\2\2\20\22\5\4\3\2\21\23\5\6\4\2\22\21\3\2\2\2\22\23\3\2\2\2\23\24"+
		"\3\2\2\2\24\26\5\b\5\2\25\27\5\n\6\2\26\25\3\2\2\2\26\27\3\2\2\2\27\31"+
		"\3\2\2\2\30\32\5\f\7\2\31\30\3\2\2\2\31\32\3\2\2\2\32\34\3\2\2\2\33\35"+
		"\5\16\b\2\34\33\3\2\2\2\34\35\3\2\2\2\35\3\3\2\2\2\36\37\7\3\2\2\37 \7"+
		"\r\2\2 !\7\f\2\2!\"\7\16\2\2\"\5\3\2\2\2#$\7\4\2\2$%\7\r\2\2%&\7\13\2"+
		"\2&\'\7\16\2\2\'\7\3\2\2\2()\7\5\2\2)*\7\r\2\2*+\7\f\2\2+,\7\16\2\2,\t"+
		"\3\2\2\2-.\7\6\2\2./\7\r\2\2/8\7\7\2\2\60\65\7\f\2\2\61\62\7\17\2\2\62"+
		"\64\7\f\2\2\63\61\3\2\2\2\64\67\3\2\2\2\65\63\3\2\2\2\65\66\3\2\2\2\66"+
		"9\3\2\2\2\67\65\3\2\2\28\60\3\2\2\289\3\2\2\29:\3\2\2\2:;\7\b\2\2;<\7"+
		"\16\2\2<\13\3\2\2\2=>\7\t\2\2>?\7\r\2\2?H\7\7\2\2@E\7\f\2\2AB\7\17\2\2"+
		"BD\7\f\2\2CA\3\2\2\2DG\3\2\2\2EC\3\2\2\2EF\3\2\2\2FI\3\2\2\2GE\3\2\2\2"+
		"H@\3\2\2\2HI\3\2\2\2IJ\3\2\2\2JK\7\b\2\2KL\7\16\2\2L\r\3\2\2\2MN\7\n\2"+
		"\2NO\7\r\2\2OX\7\7\2\2PU\7\f\2\2QR\7\17\2\2RT\7\f\2\2SQ\3\2\2\2TW\3\2"+
		"\2\2US\3\2\2\2UV\3\2\2\2VY\3\2\2\2WU\3\2\2\2XP\3\2\2\2XY\3\2\2\2YZ\3\2"+
		"\2\2Z[\7\b\2\2[\\\7\16\2\2\\\17\3\2\2\2\f\22\26\31\34\658EHUX";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}