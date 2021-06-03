// Generated from com/sap/xsk/parser/hdbschema/core/Hdbschema.g4 by ANTLR 4.3
package com.sap.xsk.parser.hdbschema.core;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class HdbschemaParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, STRING=2, EQ=3, SEMICOLON=4, COMMA=5, WS=6, ESC=7;
	public static final String[] tokenNames = {
		"<INVALID>", "'schema'", "STRING", "'='", "';'", "','", "WS", "ESC"
	};
	public static final int
		RULE_hdbschemaDefinition = 0, RULE_property = 1, RULE_schemaProp = 2;
	public static final String[] ruleNames = {
		"hdbschemaDefinition", "property", "schemaProp"
	};

	@Override
	public String getGrammarFileName() { return "Hdbschema.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public HdbschemaParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class HdbschemaDefinitionContext extends ParserRuleContext {
		public PropertyContext property(int i) {
			return getRuleContext(PropertyContext.class,i);
		}
		public List<PropertyContext> property() {
			return getRuleContexts(PropertyContext.class);
		}
		public HdbschemaDefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_hdbschemaDefinition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbschemaListener ) ((HdbschemaListener)listener).enterHdbschemaDefinition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbschemaListener ) ((HdbschemaListener)listener).exitHdbschemaDefinition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbschemaVisitor ) return ((HdbschemaVisitor<? extends T>)visitor).visitHdbschemaDefinition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HdbschemaDefinitionContext hdbschemaDefinition() throws RecognitionException {
		HdbschemaDefinitionContext _localctx = new HdbschemaDefinitionContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_hdbschemaDefinition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(7); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(6); property();
				}
				}
				setState(9); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__0 );
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
		public PropertyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_property; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbschemaListener ) ((HdbschemaListener)listener).enterProperty(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbschemaListener ) ((HdbschemaListener)listener).exitProperty(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbschemaVisitor ) return ((HdbschemaVisitor<? extends T>)visitor).visitProperty(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PropertyContext property() throws RecognitionException {
		PropertyContext _localctx = new PropertyContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_property);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(11); schemaProp();
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
		public TerminalNode SEMICOLON() { return getToken(HdbschemaParser.SEMICOLON, 0); }
		public TerminalNode EQ() { return getToken(HdbschemaParser.EQ, 0); }
		public TerminalNode STRING() { return getToken(HdbschemaParser.STRING, 0); }
		public SchemaPropContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_schemaProp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbschemaListener ) ((HdbschemaListener)listener).enterSchemaProp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbschemaListener ) ((HdbschemaListener)listener).exitSchemaProp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbschemaVisitor ) return ((HdbschemaVisitor<? extends T>)visitor).visitSchemaProp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SchemaPropContext schemaProp() throws RecognitionException {
		SchemaPropContext _localctx = new SchemaPropContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_schemaProp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(13); match(T__0);
			setState(14); match(EQ);
			setState(15); match(STRING);
			setState(16); match(SEMICOLON);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\t\25\4\2\t\2\4\3"+
		"\t\3\4\4\t\4\3\2\6\2\n\n\2\r\2\16\2\13\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\2\2\5\2\4\6\2\2\22\2\t\3\2\2\2\4\r\3\2\2\2\6\17\3\2\2\2\b\n\5\4\3\2\t"+
		"\b\3\2\2\2\n\13\3\2\2\2\13\t\3\2\2\2\13\f\3\2\2\2\f\3\3\2\2\2\r\16\5\6"+
		"\4\2\16\5\3\2\2\2\17\20\7\3\2\2\20\21\7\5\2\2\21\22\7\4\2\2\22\23\7\6"+
		"\2\2\23\7\3\2\2\2\3\13";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}