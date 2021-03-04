// Generated from com\sap\xsk\parser\hdbview\core\Hdbview.g4 by ANTLR 4.3
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
		T__7=1, T__6=2, T__5=3, T__4=4, T__3=5, T__2=6, T__1=7, T__0=8, BOOLEAN=9, 
		STRING=10, EQ=11, SEMICOLON=12, COMMA=13, WS=14, ESC=15;
	public static final String[] tokenNames = {
		"<INVALID>", "'schema'", "'depends_on_table'", "'['", "'depends_on'", 
		"'depends_on_view'", "']'", "'query'", "'public'", "BOOLEAN", "STRING", 
		"'='", "';'", "','", "WS", "ESC"
	};
	public static final int
		RULE_hdbviewDefinition = 0, RULE_schemaProp = 1, RULE_publicProp = 2, 
		RULE_queryProp = 3, RULE_dependsOnProp = 4, RULE_dependsOnTable = 5, RULE_dependsOnView = 6;
	public static final String[] ruleNames = {
		"hdbviewDefinition", "schemaProp", "publicProp", "queryProp", "dependsOnProp", 
		"dependsOnTable", "dependsOnView"
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
			setState(14); schemaProp();
			setState(15); publicProp();
			setState(16); queryProp();
			setState(18);
			_la = _input.LA(1);
			if (_la==T__4) {
				{
				setState(17); dependsOnProp();
				}
			}

			setState(21);
			_la = _input.LA(1);
			if (_la==T__6) {
				{
				setState(20); dependsOnTable();
				}
			}

			setState(24);
			_la = _input.LA(1);
			if (_la==T__3) {
				{
				setState(23); dependsOnView();
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
		enterRule(_localctx, 2, RULE_schemaProp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(26); match(T__7);
			setState(27); match(EQ);
			setState(28); match(STRING);
			setState(29); match(SEMICOLON);
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
		enterRule(_localctx, 4, RULE_publicProp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(31); match(T__0);
			setState(32); match(EQ);
			setState(33); match(BOOLEAN);
			setState(34); match(SEMICOLON);
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
		enterRule(_localctx, 6, RULE_queryProp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(36); match(T__1);
			setState(37); match(EQ);
			setState(38); match(STRING);
			setState(39); match(SEMICOLON);
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
		enterRule(_localctx, 8, RULE_dependsOnProp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(41); match(T__4);
			setState(42); match(EQ);
			setState(43); match(T__5);
			setState(52);
			_la = _input.LA(1);
			if (_la==STRING) {
				{
				setState(44); match(STRING);
				setState(49);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(45); match(COMMA);
					setState(46); match(STRING);
					}
					}
					setState(51);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(54); match(T__2);
			setState(55); match(SEMICOLON);
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
		enterRule(_localctx, 10, RULE_dependsOnTable);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(57); match(T__6);
			setState(58); match(EQ);
			setState(59); match(T__5);
			setState(68);
			_la = _input.LA(1);
			if (_la==STRING) {
				{
				setState(60); match(STRING);
				setState(65);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(61); match(COMMA);
					setState(62); match(STRING);
					}
					}
					setState(67);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(70); match(T__2);
			setState(71); match(SEMICOLON);
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
		enterRule(_localctx, 12, RULE_dependsOnView);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(73); match(T__3);
			setState(74); match(EQ);
			setState(75); match(T__5);
			setState(84);
			_la = _input.LA(1);
			if (_la==STRING) {
				{
				setState(76); match(STRING);
				setState(81);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(77); match(COMMA);
					setState(78); match(STRING);
					}
					}
					setState(83);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(86); match(T__2);
			setState(87); match(SEMICOLON);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\21\\\4\2\t\2\4\3"+
		"\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\3\2\3\2\3\2\3\2\5\2\25\n"+
		"\2\3\2\5\2\30\n\2\3\2\5\2\33\n\2\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3"+
		"\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\7\6\62\n\6\f\6\16\6\65"+
		"\13\6\5\6\67\n\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\7\7B\n\7\f\7\16\7"+
		"E\13\7\5\7G\n\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\7\bR\n\b\f\b\16\b"+
		"U\13\b\5\bW\n\b\3\b\3\b\3\b\3\b\2\2\t\2\4\6\b\n\f\16\2\2]\2\20\3\2\2\2"+
		"\4\34\3\2\2\2\6!\3\2\2\2\b&\3\2\2\2\n+\3\2\2\2\f;\3\2\2\2\16K\3\2\2\2"+
		"\20\21\5\4\3\2\21\22\5\6\4\2\22\24\5\b\5\2\23\25\5\n\6\2\24\23\3\2\2\2"+
		"\24\25\3\2\2\2\25\27\3\2\2\2\26\30\5\f\7\2\27\26\3\2\2\2\27\30\3\2\2\2"+
		"\30\32\3\2\2\2\31\33\5\16\b\2\32\31\3\2\2\2\32\33\3\2\2\2\33\3\3\2\2\2"+
		"\34\35\7\3\2\2\35\36\7\r\2\2\36\37\7\f\2\2\37 \7\16\2\2 \5\3\2\2\2!\""+
		"\7\n\2\2\"#\7\r\2\2#$\7\13\2\2$%\7\16\2\2%\7\3\2\2\2&\'\7\t\2\2\'(\7\r"+
		"\2\2()\7\f\2\2)*\7\16\2\2*\t\3\2\2\2+,\7\6\2\2,-\7\r\2\2-\66\7\5\2\2."+
		"\63\7\f\2\2/\60\7\17\2\2\60\62\7\f\2\2\61/\3\2\2\2\62\65\3\2\2\2\63\61"+
		"\3\2\2\2\63\64\3\2\2\2\64\67\3\2\2\2\65\63\3\2\2\2\66.\3\2\2\2\66\67\3"+
		"\2\2\2\678\3\2\2\289\7\b\2\29:\7\16\2\2:\13\3\2\2\2;<\7\4\2\2<=\7\r\2"+
		"\2=F\7\5\2\2>C\7\f\2\2?@\7\17\2\2@B\7\f\2\2A?\3\2\2\2BE\3\2\2\2CA\3\2"+
		"\2\2CD\3\2\2\2DG\3\2\2\2EC\3\2\2\2F>\3\2\2\2FG\3\2\2\2GH\3\2\2\2HI\7\b"+
		"\2\2IJ\7\16\2\2J\r\3\2\2\2KL\7\7\2\2LM\7\r\2\2MV\7\5\2\2NS\7\f\2\2OP\7"+
		"\17\2\2PR\7\f\2\2QO\3\2\2\2RU\3\2\2\2SQ\3\2\2\2ST\3\2\2\2TW\3\2\2\2US"+
		"\3\2\2\2VN\3\2\2\2VW\3\2\2\2WX\3\2\2\2XY\7\b\2\2YZ\7\16\2\2Z\17\3\2\2"+
		"\2\13\24\27\32\63\66CFSV";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}