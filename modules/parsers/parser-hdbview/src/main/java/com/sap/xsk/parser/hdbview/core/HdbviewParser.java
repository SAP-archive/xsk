// Generated from com/sap/xsk/parser/hdbview/core/Hdbview.g4 by ANTLR 4.3
package com.sap.xsk.parser.hdbview.core;
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
	static { RuntimeMetaData.checkVersion("4.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__8=1, T__7=2, T__6=3, T__5=4, T__4=5, T__3=6, T__2=7, T__1=8, T__0=9, 
		BOOLEAN=10, STRING=11, EQ=12, SEMICOLON=13, WS=14, ESC=15, LINE_COMMENT=16, 
		COMMENT=17;
	public static final String[] tokenNames = {
		"<INVALID>", "'schema'", "'depends_on_table'", "'['", "'depends_on'", 
		"','", "'depends_on_view'", "']'", "'query'", "'public'", "BOOLEAN", "STRING", 
		"'='", "';'", "WS", "ESC", "LINE_COMMENT", "COMMENT"
	};
	public static final int
		RULE_hdbviewDefinition = 0, RULE_property = 1, RULE_schemaProp = 2, RULE_publicProp = 3, 
		RULE_queryProp = 4, RULE_dependsOnProp = 5, RULE_dependsOnTable = 6, RULE_dependsOnView = 7;
	public static final String[] ruleNames = {
		"hdbviewDefinition", "property", "schemaProp", "publicProp", "queryProp", 
		"dependsOnProp", "dependsOnTable", "dependsOnView"
	};

	@Override
	public String getGrammarFileName() { return "Hdbview.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

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
		public PropertyContext property(int i) {
			return getRuleContext(PropertyContext.class,i);
		}
		public List<PropertyContext> property() {
			return getRuleContexts(PropertyContext.class);
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
			setState(17); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(16); property();
				}
				}
				setState(19); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__8) | (1L << T__7) | (1L << T__5) | (1L << T__3) | (1L << T__1) | (1L << T__0))) != 0) );
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
		public SchemaPropContext schemaProp() {
			return getRuleContext(SchemaPropContext.class,0);
		}
		public PublicPropContext publicProp() {
			return getRuleContext(PublicPropContext.class,0);
		}
		public DependsOnViewContext dependsOnView() {
			return getRuleContext(DependsOnViewContext.class,0);
		}
		public QueryPropContext queryProp() {
			return getRuleContext(QueryPropContext.class,0);
		}
		public DependsOnPropContext dependsOnProp() {
			return getRuleContext(DependsOnPropContext.class,0);
		}
		public DependsOnTableContext dependsOnTable() {
			return getRuleContext(DependsOnTableContext.class,0);
		}
		public PropertyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_property; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbviewListener ) ((HdbviewListener)listener).enterProperty(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbviewListener ) ((HdbviewListener)listener).exitProperty(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbviewVisitor ) return ((HdbviewVisitor<? extends T>)visitor).visitProperty(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PropertyContext property() throws RecognitionException {
		PropertyContext _localctx = new PropertyContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_property);
		try {
			setState(27);
			switch (_input.LA(1)) {
			case T__8:
				enterOuterAlt(_localctx, 1);
				{
				setState(21); schemaProp();
				}
				break;
			case T__0:
				enterOuterAlt(_localctx, 2);
				{
				setState(22); publicProp();
				}
				break;
			case T__1:
				enterOuterAlt(_localctx, 3);
				{
				setState(23); queryProp();
				}
				break;
			case T__5:
				enterOuterAlt(_localctx, 4);
				{
				setState(24); dependsOnProp();
				}
				break;
			case T__7:
				enterOuterAlt(_localctx, 5);
				{
				setState(25); dependsOnTable();
				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 6);
				{
				setState(26); dependsOnView();
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

	public static class SchemaPropContext extends ParserRuleContext {
		public TerminalNode SEMICOLON() { return getToken(HdbviewParser.SEMICOLON, 0); }
		public TerminalNode EQ() { return getToken(HdbviewParser.EQ, 0); }
		public TerminalNode STRING() { return getToken(HdbviewParser.STRING, 0); }
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
		enterRule(_localctx, 4, RULE_schemaProp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(29); match(T__8);
			setState(30); match(EQ);
			setState(31); match(STRING);
			setState(32); match(SEMICOLON);
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
		public TerminalNode SEMICOLON() { return getToken(HdbviewParser.SEMICOLON, 0); }
		public TerminalNode EQ() { return getToken(HdbviewParser.EQ, 0); }
		public TerminalNode BOOLEAN() { return getToken(HdbviewParser.BOOLEAN, 0); }
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
		enterRule(_localctx, 6, RULE_publicProp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(34); match(T__0);
			setState(35); match(EQ);
			setState(36); match(BOOLEAN);
			setState(37); match(SEMICOLON);
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
		public TerminalNode SEMICOLON() { return getToken(HdbviewParser.SEMICOLON, 0); }
		public TerminalNode EQ() { return getToken(HdbviewParser.EQ, 0); }
		public TerminalNode STRING() { return getToken(HdbviewParser.STRING, 0); }
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
		enterRule(_localctx, 8, RULE_queryProp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(39); match(T__1);
			setState(40); match(EQ);
			setState(41); match(STRING);
			setState(42); match(SEMICOLON);
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
		public TerminalNode SEMICOLON() { return getToken(HdbviewParser.SEMICOLON, 0); }
		public TerminalNode STRING(int i) {
			return getToken(HdbviewParser.STRING, i);
		}
		public TerminalNode EQ() { return getToken(HdbviewParser.EQ, 0); }
		public List<TerminalNode> STRING() { return getTokens(HdbviewParser.STRING); }
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
		enterRule(_localctx, 10, RULE_dependsOnProp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(44); match(T__5);
			setState(45); match(EQ);
			setState(46); match(T__6);
			setState(55);
			_la = _input.LA(1);
			if (_la==STRING) {
				{
				setState(47); match(STRING);
				setState(52);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__4) {
					{
					{
					setState(48); match(T__4);
					setState(49); match(STRING);
					}
					}
					setState(54);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(57); match(T__2);
			setState(58); match(SEMICOLON);
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
		public TerminalNode SEMICOLON() { return getToken(HdbviewParser.SEMICOLON, 0); }
		public TerminalNode STRING(int i) {
			return getToken(HdbviewParser.STRING, i);
		}
		public TerminalNode EQ() { return getToken(HdbviewParser.EQ, 0); }
		public List<TerminalNode> STRING() { return getTokens(HdbviewParser.STRING); }
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
		enterRule(_localctx, 12, RULE_dependsOnTable);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(60); match(T__7);
			setState(61); match(EQ);
			setState(62); match(T__6);
			setState(71);
			_la = _input.LA(1);
			if (_la==STRING) {
				{
				setState(63); match(STRING);
				setState(68);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__4) {
					{
					{
					setState(64); match(T__4);
					setState(65); match(STRING);
					}
					}
					setState(70);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(73); match(T__2);
			setState(74); match(SEMICOLON);
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
		public TerminalNode SEMICOLON() { return getToken(HdbviewParser.SEMICOLON, 0); }
		public TerminalNode STRING(int i) {
			return getToken(HdbviewParser.STRING, i);
		}
		public TerminalNode EQ() { return getToken(HdbviewParser.EQ, 0); }
		public List<TerminalNode> STRING() { return getTokens(HdbviewParser.STRING); }
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
		enterRule(_localctx, 14, RULE_dependsOnView);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(76); match(T__3);
			setState(77); match(EQ);
			setState(78); match(T__6);
			setState(87);
			_la = _input.LA(1);
			if (_la==STRING) {
				{
				setState(79); match(STRING);
				setState(84);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__4) {
					{
					{
					setState(80); match(T__4);
					setState(81); match(STRING);
					}
					}
					setState(86);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(89); match(T__2);
			setState(90); match(SEMICOLON);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\23_\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\3\2\6\2\24\n\2\r\2"+
		"\16\2\25\3\3\3\3\3\3\3\3\3\3\3\3\5\3\36\n\3\3\4\3\4\3\4\3\4\3\4\3\5\3"+
		"\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\7\7\65\n\7"+
		"\f\7\16\78\13\7\5\7:\n\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\7\bE\n\b"+
		"\f\b\16\bH\13\b\5\bJ\n\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\7\tU\n\t"+
		"\f\t\16\tX\13\t\5\tZ\n\t\3\t\3\t\3\t\3\t\2\2\n\2\4\6\b\n\f\16\20\2\2b"+
		"\2\23\3\2\2\2\4\35\3\2\2\2\6\37\3\2\2\2\b$\3\2\2\2\n)\3\2\2\2\f.\3\2\2"+
		"\2\16>\3\2\2\2\20N\3\2\2\2\22\24\5\4\3\2\23\22\3\2\2\2\24\25\3\2\2\2\25"+
		"\23\3\2\2\2\25\26\3\2\2\2\26\3\3\2\2\2\27\36\5\6\4\2\30\36\5\b\5\2\31"+
		"\36\5\n\6\2\32\36\5\f\7\2\33\36\5\16\b\2\34\36\5\20\t\2\35\27\3\2\2\2"+
		"\35\30\3\2\2\2\35\31\3\2\2\2\35\32\3\2\2\2\35\33\3\2\2\2\35\34\3\2\2\2"+
		"\36\5\3\2\2\2\37 \7\3\2\2 !\7\16\2\2!\"\7\r\2\2\"#\7\17\2\2#\7\3\2\2\2"+
		"$%\7\13\2\2%&\7\16\2\2&\'\7\f\2\2\'(\7\17\2\2(\t\3\2\2\2)*\7\n\2\2*+\7"+
		"\16\2\2+,\7\r\2\2,-\7\17\2\2-\13\3\2\2\2./\7\6\2\2/\60\7\16\2\2\609\7"+
		"\5\2\2\61\66\7\r\2\2\62\63\7\7\2\2\63\65\7\r\2\2\64\62\3\2\2\2\658\3\2"+
		"\2\2\66\64\3\2\2\2\66\67\3\2\2\2\67:\3\2\2\28\66\3\2\2\29\61\3\2\2\29"+
		":\3\2\2\2:;\3\2\2\2;<\7\t\2\2<=\7\17\2\2=\r\3\2\2\2>?\7\4\2\2?@\7\16\2"+
		"\2@I\7\5\2\2AF\7\r\2\2BC\7\7\2\2CE\7\r\2\2DB\3\2\2\2EH\3\2\2\2FD\3\2\2"+
		"\2FG\3\2\2\2GJ\3\2\2\2HF\3\2\2\2IA\3\2\2\2IJ\3\2\2\2JK\3\2\2\2KL\7\t\2"+
		"\2LM\7\17\2\2M\17\3\2\2\2NO\7\b\2\2OP\7\16\2\2PY\7\5\2\2QV\7\r\2\2RS\7"+
		"\7\2\2SU\7\r\2\2TR\3\2\2\2UX\3\2\2\2VT\3\2\2\2VW\3\2\2\2WZ\3\2\2\2XV\3"+
		"\2\2\2YQ\3\2\2\2YZ\3\2\2\2Z[\3\2\2\2[\\\7\t\2\2\\]\7\17\2\2]\21\3\2\2"+
		"\2\n\25\35\669FIVY";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}