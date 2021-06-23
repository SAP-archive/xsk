// Generated from com/sap/xsk/parser/hdbsynonym/core/Hdbsynonym.g4 by ANTLR 4.3
package com.sap.xsk.parser.hdbsynonym.core;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class HdbsynonymParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__4=1, T__3=2, T__2=3, T__1=4, T__0=5, STRING=6, COMMA=7, COLON=8, ESC=9, 
		WS=10, LINE_COMMENT=11, COMMENT=12;
	public static final String[] tokenNames = {
		"<INVALID>", "'\"target\"'", "'\"object\"'", "'{'", "'}'", "'\"schema\"'", 
		"STRING", "','", "':'", "ESC", "WS", "LINE_COMMENT", "COMMENT"
	};
	public static final int
		RULE_hdbsynonymDefinition = 0, RULE_location = 1, RULE_synonymBody = 2, 
		RULE_synonymElement = 3, RULE_synonymTarget = 4, RULE_synonymTargetProp = 5, 
		RULE_synonymSchema = 6;
	public static final String[] ruleNames = {
		"hdbsynonymDefinition", "location", "synonymBody", "synonymElement", "synonymTarget", 
		"synonymTargetProp", "synonymSchema"
	};

	@Override
	public String getGrammarFileName() { return "Hdbsynonym.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public HdbsynonymParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class HdbsynonymDefinitionContext extends ParserRuleContext {
		public LocationContext location() {
			return getRuleContext(LocationContext.class,0);
		}
		public TerminalNode COLON() { return getToken(HdbsynonymParser.COLON, 0); }
		public SynonymBodyContext synonymBody() {
			return getRuleContext(SynonymBodyContext.class,0);
		}
		public HdbsynonymDefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_hdbsynonymDefinition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbsynonymListener ) ((HdbsynonymListener)listener).enterHdbsynonymDefinition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbsynonymListener ) ((HdbsynonymListener)listener).exitHdbsynonymDefinition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbsynonymVisitor ) return ((HdbsynonymVisitor<? extends T>)visitor).visitHdbsynonymDefinition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HdbsynonymDefinitionContext hdbsynonymDefinition() throws RecognitionException {
		HdbsynonymDefinitionContext _localctx = new HdbsynonymDefinitionContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_hdbsynonymDefinition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(14); match(T__2);
			setState(15); location();
			setState(16); match(COLON);
			setState(17); synonymBody();
			setState(18); match(T__1);
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

	public static class LocationContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(HdbsynonymParser.STRING, 0); }
		public LocationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_location; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbsynonymListener ) ((HdbsynonymListener)listener).enterLocation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbsynonymListener ) ((HdbsynonymListener)listener).exitLocation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbsynonymVisitor ) return ((HdbsynonymVisitor<? extends T>)visitor).visitLocation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LocationContext location() throws RecognitionException {
		LocationContext _localctx = new LocationContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_location);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(20); match(STRING);
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

	public static class SynonymBodyContext extends ParserRuleContext {
		public TerminalNode COMMA() { return getToken(HdbsynonymParser.COMMA, 0); }
		public List<SynonymElementContext> synonymElement() {
			return getRuleContexts(SynonymElementContext.class);
		}
		public SynonymElementContext synonymElement(int i) {
			return getRuleContext(SynonymElementContext.class,i);
		}
		public SynonymBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_synonymBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbsynonymListener ) ((HdbsynonymListener)listener).enterSynonymBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbsynonymListener ) ((HdbsynonymListener)listener).exitSynonymBody(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbsynonymVisitor ) return ((HdbsynonymVisitor<? extends T>)visitor).visitSynonymBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SynonymBodyContext synonymBody() throws RecognitionException {
		SynonymBodyContext _localctx = new SynonymBodyContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_synonymBody);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(22); match(T__2);
			setState(23); synonymElement();
			setState(24); match(COMMA);
			setState(25); synonymElement();
			setState(26); match(T__1);
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

	public static class SynonymElementContext extends ParserRuleContext {
		public SynonymSchemaContext synonymSchema() {
			return getRuleContext(SynonymSchemaContext.class,0);
		}
		public SynonymTargetContext synonymTarget() {
			return getRuleContext(SynonymTargetContext.class,0);
		}
		public SynonymElementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_synonymElement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbsynonymListener ) ((HdbsynonymListener)listener).enterSynonymElement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbsynonymListener ) ((HdbsynonymListener)listener).exitSynonymElement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbsynonymVisitor ) return ((HdbsynonymVisitor<? extends T>)visitor).visitSynonymElement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SynonymElementContext synonymElement() throws RecognitionException {
		SynonymElementContext _localctx = new SynonymElementContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_synonymElement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(30);
			switch (_input.LA(1)) {
			case T__4:
				{
				setState(28); synonymTarget();
				}
				break;
			case T__0:
				{
				setState(29); synonymSchema();
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class SynonymTargetContext extends ParserRuleContext {
		public TerminalNode COMMA() { return getToken(HdbsynonymParser.COMMA, 0); }
		public TerminalNode COLON() { return getToken(HdbsynonymParser.COLON, 0); }
		public SynonymTargetPropContext synonymTargetProp(int i) {
			return getRuleContext(SynonymTargetPropContext.class,i);
		}
		public List<SynonymTargetPropContext> synonymTargetProp() {
			return getRuleContexts(SynonymTargetPropContext.class);
		}
		public SynonymTargetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_synonymTarget; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbsynonymListener ) ((HdbsynonymListener)listener).enterSynonymTarget(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbsynonymListener ) ((HdbsynonymListener)listener).exitSynonymTarget(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbsynonymVisitor ) return ((HdbsynonymVisitor<? extends T>)visitor).visitSynonymTarget(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SynonymTargetContext synonymTarget() throws RecognitionException {
		SynonymTargetContext _localctx = new SynonymTargetContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_synonymTarget);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(32); match(T__4);
			setState(33); match(COLON);
			setState(34); match(T__2);
			setState(36); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(35); synonymTargetProp();
				}
				}
				setState(38); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__3 || _la==T__0 );
			setState(40); match(T__1);
			setState(42);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				{
				setState(41); match(COMMA);
				}
				break;
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

	public static class SynonymTargetPropContext extends ParserRuleContext {
		public TerminalNode COMMA() { return getToken(HdbsynonymParser.COMMA, 0); }
		public TerminalNode COLON() { return getToken(HdbsynonymParser.COLON, 0); }
		public TerminalNode STRING() { return getToken(HdbsynonymParser.STRING, 0); }
		public SynonymTargetPropContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_synonymTargetProp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbsynonymListener ) ((HdbsynonymListener)listener).enterSynonymTargetProp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbsynonymListener ) ((HdbsynonymListener)listener).exitSynonymTargetProp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbsynonymVisitor ) return ((HdbsynonymVisitor<? extends T>)visitor).visitSynonymTargetProp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SynonymTargetPropContext synonymTargetProp() throws RecognitionException {
		SynonymTargetPropContext _localctx = new SynonymTargetPropContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_synonymTargetProp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(44);
			_la = _input.LA(1);
			if ( !(_la==T__3 || _la==T__0) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			setState(45); match(COLON);
			setState(46); match(STRING);
			setState(48);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(47); match(COMMA);
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

	public static class SynonymSchemaContext extends ParserRuleContext {
		public TerminalNode COMMA() { return getToken(HdbsynonymParser.COMMA, 0); }
		public TerminalNode COLON() { return getToken(HdbsynonymParser.COLON, 0); }
		public TerminalNode STRING() { return getToken(HdbsynonymParser.STRING, 0); }
		public SynonymSchemaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_synonymSchema; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbsynonymListener ) ((HdbsynonymListener)listener).enterSynonymSchema(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbsynonymListener ) ((HdbsynonymListener)listener).exitSynonymSchema(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbsynonymVisitor ) return ((HdbsynonymVisitor<? extends T>)visitor).visitSynonymSchema(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SynonymSchemaContext synonymSchema() throws RecognitionException {
		SynonymSchemaContext _localctx = new SynonymSchemaContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_synonymSchema);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(50); match(T__0);
			setState(51); match(COLON);
			setState(52); match(STRING);
			setState(54);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				{
				setState(53); match(COMMA);
				}
				break;
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

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\16;\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\3\2\3\2\3\2\3\2\3\2\3\2\3\3"+
		"\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\5\5!\n\5\3\6\3\6\3\6\3\6\6\6\'\n"+
		"\6\r\6\16\6(\3\6\3\6\5\6-\n\6\3\7\3\7\3\7\3\7\5\7\63\n\7\3\b\3\b\3\b\3"+
		"\b\5\b9\n\b\3\b\2\2\t\2\4\6\b\n\f\16\2\3\4\2\4\4\7\78\2\20\3\2\2\2\4\26"+
		"\3\2\2\2\6\30\3\2\2\2\b \3\2\2\2\n\"\3\2\2\2\f.\3\2\2\2\16\64\3\2\2\2"+
		"\20\21\7\5\2\2\21\22\5\4\3\2\22\23\7\n\2\2\23\24\5\6\4\2\24\25\7\6\2\2"+
		"\25\3\3\2\2\2\26\27\7\b\2\2\27\5\3\2\2\2\30\31\7\5\2\2\31\32\5\b\5\2\32"+
		"\33\7\t\2\2\33\34\5\b\5\2\34\35\7\6\2\2\35\7\3\2\2\2\36!\5\n\6\2\37!\5"+
		"\16\b\2 \36\3\2\2\2 \37\3\2\2\2!\t\3\2\2\2\"#\7\3\2\2#$\7\n\2\2$&\7\5"+
		"\2\2%\'\5\f\7\2&%\3\2\2\2\'(\3\2\2\2(&\3\2\2\2()\3\2\2\2)*\3\2\2\2*,\7"+
		"\6\2\2+-\7\t\2\2,+\3\2\2\2,-\3\2\2\2-\13\3\2\2\2./\t\2\2\2/\60\7\n\2\2"+
		"\60\62\7\b\2\2\61\63\7\t\2\2\62\61\3\2\2\2\62\63\3\2\2\2\63\r\3\2\2\2"+
		"\64\65\7\7\2\2\65\66\7\n\2\2\668\7\b\2\2\679\7\t\2\28\67\3\2\2\289\3\2"+
		"\2\29\17\3\2\2\2\7 (,\628";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}