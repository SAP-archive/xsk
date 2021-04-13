// Generated from com\sap\xsk\parser\hdbsequence\core\Hdbsequence.g4 by ANTLR 4.3
package com.sap.xsk.parser.hdbsequence.core;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class HdbsequenceParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__9=1, T__8=2, T__7=3, T__6=4, T__5=5, T__4=6, T__3=7, T__2=8, T__1=9, 
		T__0=10, STRING=11, INT=12, BOOLEAN=13, TRUE=14, FALSE=15, WS=16, LB=17, 
		RB=18, EQ=19, SC=20, SIGNED_INT=21, LINE_COMMENT=22, COMMENT=23;
	public static final String[] tokenNames = {
		"<INVALID>", "'nominvalue'", "'reset_by'", "'schema'", "'start_with'", 
		"'cycles'", "'maxvalue'", "'increment_by'", "'public'", "'nomaxvalue'", 
		"'minvalue'", "STRING", "INT", "BOOLEAN", "'true'", "'false'", "WS", "'['", 
		"']'", "'='", "';'", "'-'", "LINE_COMMENT", "COMMENT"
	};
	public static final int
		RULE_hdbsequence = 0, RULE_property = 1, RULE_schema = 2, RULE_increment_by = 3, 
		RULE_start_with = 4, RULE_maxvalue = 5, RULE_nomaxvalue = 6, RULE_minvalue = 7, 
		RULE_nominvalue = 8, RULE_cycles = 9, RULE_reset_by = 10, RULE_publicc = 11;
	public static final String[] ruleNames = {
		"hdbsequence", "property", "schema", "increment_by", "start_with", "maxvalue", 
		"nomaxvalue", "minvalue", "nominvalue", "cycles", "reset_by", "publicc"
	};

	@Override
	public String getGrammarFileName() { return "Hdbsequence.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public HdbsequenceParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class HdbsequenceContext extends ParserRuleContext {
		public PropertyContext property(int i) {
			return getRuleContext(PropertyContext.class,i);
		}
		public List<PropertyContext> property() {
			return getRuleContexts(PropertyContext.class);
		}
		public HdbsequenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_hdbsequence; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbsequenceListener ) ((HdbsequenceListener)listener).enterHdbsequence(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbsequenceListener ) ((HdbsequenceListener)listener).exitHdbsequence(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbsequenceVisitor ) return ((HdbsequenceVisitor<? extends T>)visitor).visitHdbsequence(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HdbsequenceContext hdbsequence() throws RecognitionException {
		HdbsequenceContext _localctx = new HdbsequenceContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_hdbsequence);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(25); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(24); property();
				}
				}
				setState(27); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__9) | (1L << T__8) | (1L << T__7) | (1L << T__6) | (1L << T__5) | (1L << T__4) | (1L << T__3) | (1L << T__2) | (1L << T__1) | (1L << T__0))) != 0) );
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
		public NominvalueContext nominvalue() {
			return getRuleContext(NominvalueContext.class,0);
		}
		public Start_withContext start_with() {
			return getRuleContext(Start_withContext.class,0);
		}
		public MaxvalueContext maxvalue() {
			return getRuleContext(MaxvalueContext.class,0);
		}
		public Reset_byContext reset_by() {
			return getRuleContext(Reset_byContext.class,0);
		}
		public NomaxvalueContext nomaxvalue() {
			return getRuleContext(NomaxvalueContext.class,0);
		}
		public PubliccContext publicc() {
			return getRuleContext(PubliccContext.class,0);
		}
		public Increment_byContext increment_by() {
			return getRuleContext(Increment_byContext.class,0);
		}
		public MinvalueContext minvalue() {
			return getRuleContext(MinvalueContext.class,0);
		}
		public SchemaContext schema() {
			return getRuleContext(SchemaContext.class,0);
		}
		public CyclesContext cycles() {
			return getRuleContext(CyclesContext.class,0);
		}
		public PropertyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_property; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbsequenceListener ) ((HdbsequenceListener)listener).enterProperty(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbsequenceListener ) ((HdbsequenceListener)listener).exitProperty(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbsequenceVisitor ) return ((HdbsequenceVisitor<? extends T>)visitor).visitProperty(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PropertyContext property() throws RecognitionException {
		PropertyContext _localctx = new PropertyContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_property);
		try {
			setState(39);
			switch (_input.LA(1)) {
			case T__7:
				enterOuterAlt(_localctx, 1);
				{
				setState(29); schema();
				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 2);
				{
				setState(30); increment_by();
				}
				break;
			case T__6:
				enterOuterAlt(_localctx, 3);
				{
				setState(31); start_with();
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 4);
				{
				setState(32); maxvalue();
				}
				break;
			case T__1:
				enterOuterAlt(_localctx, 5);
				{
				setState(33); nomaxvalue();
				}
				break;
			case T__0:
				enterOuterAlt(_localctx, 6);
				{
				setState(34); minvalue();
				}
				break;
			case T__9:
				enterOuterAlt(_localctx, 7);
				{
				setState(35); nominvalue();
				}
				break;
			case T__5:
				enterOuterAlt(_localctx, 8);
				{
				setState(36); cycles();
				}
				break;
			case T__8:
				enterOuterAlt(_localctx, 9);
				{
				setState(37); reset_by();
				}
				break;
			case T__2:
				enterOuterAlt(_localctx, 10);
				{
				setState(38); publicc();
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

	public static class SchemaContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(HdbsequenceParser.EQ, 0); }
		public TerminalNode STRING() { return getToken(HdbsequenceParser.STRING, 0); }
		public TerminalNode SC() { return getToken(HdbsequenceParser.SC, 0); }
		public SchemaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_schema; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbsequenceListener ) ((HdbsequenceListener)listener).enterSchema(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbsequenceListener ) ((HdbsequenceListener)listener).exitSchema(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbsequenceVisitor ) return ((HdbsequenceVisitor<? extends T>)visitor).visitSchema(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SchemaContext schema() throws RecognitionException {
		SchemaContext _localctx = new SchemaContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_schema);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(41); match(T__7);
			setState(42); match(EQ);
			setState(43); match(STRING);
			setState(44); match(SC);
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

	public static class Increment_byContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(HdbsequenceParser.EQ, 0); }
		public TerminalNode INT() { return getToken(HdbsequenceParser.INT, 0); }
		public TerminalNode SC() { return getToken(HdbsequenceParser.SC, 0); }
		public Increment_byContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_increment_by; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbsequenceListener ) ((HdbsequenceListener)listener).enterIncrement_by(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbsequenceListener ) ((HdbsequenceListener)listener).exitIncrement_by(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbsequenceVisitor ) return ((HdbsequenceVisitor<? extends T>)visitor).visitIncrement_by(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Increment_byContext increment_by() throws RecognitionException {
		Increment_byContext _localctx = new Increment_byContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_increment_by);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(46); match(T__3);
			setState(47); match(EQ);
			setState(48); match(INT);
			setState(49); match(SC);
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

	public static class Start_withContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(HdbsequenceParser.EQ, 0); }
		public TerminalNode INT() { return getToken(HdbsequenceParser.INT, 0); }
		public TerminalNode SC() { return getToken(HdbsequenceParser.SC, 0); }
		public Start_withContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start_with; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbsequenceListener ) ((HdbsequenceListener)listener).enterStart_with(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbsequenceListener ) ((HdbsequenceListener)listener).exitStart_with(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbsequenceVisitor ) return ((HdbsequenceVisitor<? extends T>)visitor).visitStart_with(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Start_withContext start_with() throws RecognitionException {
		Start_withContext _localctx = new Start_withContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_start_with);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(51); match(T__6);
			setState(52); match(EQ);
			setState(53); match(INT);
			setState(54); match(SC);
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

	public static class MaxvalueContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(HdbsequenceParser.EQ, 0); }
		public TerminalNode INT() { return getToken(HdbsequenceParser.INT, 0); }
		public TerminalNode SC() { return getToken(HdbsequenceParser.SC, 0); }
		public MaxvalueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_maxvalue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbsequenceListener ) ((HdbsequenceListener)listener).enterMaxvalue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbsequenceListener ) ((HdbsequenceListener)listener).exitMaxvalue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbsequenceVisitor ) return ((HdbsequenceVisitor<? extends T>)visitor).visitMaxvalue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MaxvalueContext maxvalue() throws RecognitionException {
		MaxvalueContext _localctx = new MaxvalueContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_maxvalue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(56); match(T__4);
			setState(57); match(EQ);
			setState(58); match(INT);
			setState(59); match(SC);
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

	public static class NomaxvalueContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(HdbsequenceParser.EQ, 0); }
		public TerminalNode BOOLEAN() { return getToken(HdbsequenceParser.BOOLEAN, 0); }
		public TerminalNode SC() { return getToken(HdbsequenceParser.SC, 0); }
		public NomaxvalueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nomaxvalue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbsequenceListener ) ((HdbsequenceListener)listener).enterNomaxvalue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbsequenceListener ) ((HdbsequenceListener)listener).exitNomaxvalue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbsequenceVisitor ) return ((HdbsequenceVisitor<? extends T>)visitor).visitNomaxvalue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NomaxvalueContext nomaxvalue() throws RecognitionException {
		NomaxvalueContext _localctx = new NomaxvalueContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_nomaxvalue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(61); match(T__1);
			setState(62); match(EQ);
			setState(63); match(BOOLEAN);
			setState(64); match(SC);
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

	public static class MinvalueContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(HdbsequenceParser.EQ, 0); }
		public TerminalNode INT() { return getToken(HdbsequenceParser.INT, 0); }
		public TerminalNode SC() { return getToken(HdbsequenceParser.SC, 0); }
		public MinvalueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_minvalue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbsequenceListener ) ((HdbsequenceListener)listener).enterMinvalue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbsequenceListener ) ((HdbsequenceListener)listener).exitMinvalue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbsequenceVisitor ) return ((HdbsequenceVisitor<? extends T>)visitor).visitMinvalue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MinvalueContext minvalue() throws RecognitionException {
		MinvalueContext _localctx = new MinvalueContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_minvalue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(66); match(T__0);
			setState(67); match(EQ);
			setState(68); match(INT);
			setState(69); match(SC);
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

	public static class NominvalueContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(HdbsequenceParser.EQ, 0); }
		public TerminalNode BOOLEAN() { return getToken(HdbsequenceParser.BOOLEAN, 0); }
		public TerminalNode SC() { return getToken(HdbsequenceParser.SC, 0); }
		public NominvalueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nominvalue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbsequenceListener ) ((HdbsequenceListener)listener).enterNominvalue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbsequenceListener ) ((HdbsequenceListener)listener).exitNominvalue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbsequenceVisitor ) return ((HdbsequenceVisitor<? extends T>)visitor).visitNominvalue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NominvalueContext nominvalue() throws RecognitionException {
		NominvalueContext _localctx = new NominvalueContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_nominvalue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(71); match(T__9);
			setState(72); match(EQ);
			setState(73); match(BOOLEAN);
			setState(74); match(SC);
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

	public static class CyclesContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(HdbsequenceParser.EQ, 0); }
		public TerminalNode BOOLEAN() { return getToken(HdbsequenceParser.BOOLEAN, 0); }
		public TerminalNode SC() { return getToken(HdbsequenceParser.SC, 0); }
		public CyclesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cycles; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbsequenceListener ) ((HdbsequenceListener)listener).enterCycles(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbsequenceListener ) ((HdbsequenceListener)listener).exitCycles(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbsequenceVisitor ) return ((HdbsequenceVisitor<? extends T>)visitor).visitCycles(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CyclesContext cycles() throws RecognitionException {
		CyclesContext _localctx = new CyclesContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_cycles);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(76); match(T__5);
			setState(77); match(EQ);
			setState(78); match(BOOLEAN);
			setState(79); match(SC);
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

	public static class Reset_byContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(HdbsequenceParser.EQ, 0); }
		public TerminalNode STRING() { return getToken(HdbsequenceParser.STRING, 0); }
		public TerminalNode SC() { return getToken(HdbsequenceParser.SC, 0); }
		public Reset_byContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_reset_by; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbsequenceListener ) ((HdbsequenceListener)listener).enterReset_by(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbsequenceListener ) ((HdbsequenceListener)listener).exitReset_by(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbsequenceVisitor ) return ((HdbsequenceVisitor<? extends T>)visitor).visitReset_by(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Reset_byContext reset_by() throws RecognitionException {
		Reset_byContext _localctx = new Reset_byContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_reset_by);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(81); match(T__8);
			setState(82); match(EQ);
			setState(83); match(STRING);
			setState(84); match(SC);
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

	public static class PubliccContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(HdbsequenceParser.EQ, 0); }
		public TerminalNode BOOLEAN() { return getToken(HdbsequenceParser.BOOLEAN, 0); }
		public TerminalNode SC() { return getToken(HdbsequenceParser.SC, 0); }
		public PubliccContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_publicc; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbsequenceListener ) ((HdbsequenceListener)listener).enterPublicc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbsequenceListener ) ((HdbsequenceListener)listener).exitPublicc(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbsequenceVisitor ) return ((HdbsequenceVisitor<? extends T>)visitor).visitPublicc(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PubliccContext publicc() throws RecognitionException {
		PubliccContext _localctx = new PubliccContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_publicc);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(86); match(T__2);
			setState(87); match(EQ);
			setState(88); match(BOOLEAN);
			setState(89); match(SC);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\31^\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4"+
		"\f\t\f\4\r\t\r\3\2\6\2\34\n\2\r\2\16\2\35\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\5\3*\n\3\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6"+
		"\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3"+
		"\t\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3"+
		"\r\3\r\3\r\3\r\3\r\3\r\2\2\16\2\4\6\b\n\f\16\20\22\24\26\30\2\2[\2\33"+
		"\3\2\2\2\4)\3\2\2\2\6+\3\2\2\2\b\60\3\2\2\2\n\65\3\2\2\2\f:\3\2\2\2\16"+
		"?\3\2\2\2\20D\3\2\2\2\22I\3\2\2\2\24N\3\2\2\2\26S\3\2\2\2\30X\3\2\2\2"+
		"\32\34\5\4\3\2\33\32\3\2\2\2\34\35\3\2\2\2\35\33\3\2\2\2\35\36\3\2\2\2"+
		"\36\3\3\2\2\2\37*\5\6\4\2 *\5\b\5\2!*\5\n\6\2\"*\5\f\7\2#*\5\16\b\2$*"+
		"\5\20\t\2%*\5\22\n\2&*\5\24\13\2\'*\5\26\f\2(*\5\30\r\2)\37\3\2\2\2) "+
		"\3\2\2\2)!\3\2\2\2)\"\3\2\2\2)#\3\2\2\2)$\3\2\2\2)%\3\2\2\2)&\3\2\2\2"+
		")\'\3\2\2\2)(\3\2\2\2*\5\3\2\2\2+,\7\5\2\2,-\7\25\2\2-.\7\r\2\2./\7\26"+
		"\2\2/\7\3\2\2\2\60\61\7\t\2\2\61\62\7\25\2\2\62\63\7\16\2\2\63\64\7\26"+
		"\2\2\64\t\3\2\2\2\65\66\7\6\2\2\66\67\7\25\2\2\678\7\16\2\289\7\26\2\2"+
		"9\13\3\2\2\2:;\7\b\2\2;<\7\25\2\2<=\7\16\2\2=>\7\26\2\2>\r\3\2\2\2?@\7"+
		"\13\2\2@A\7\25\2\2AB\7\17\2\2BC\7\26\2\2C\17\3\2\2\2DE\7\f\2\2EF\7\25"+
		"\2\2FG\7\16\2\2GH\7\26\2\2H\21\3\2\2\2IJ\7\3\2\2JK\7\25\2\2KL\7\17\2\2"+
		"LM\7\26\2\2M\23\3\2\2\2NO\7\7\2\2OP\7\25\2\2PQ\7\17\2\2QR\7\26\2\2R\25"+
		"\3\2\2\2ST\7\4\2\2TU\7\25\2\2UV\7\r\2\2VW\7\26\2\2W\27\3\2\2\2XY\7\n\2"+
		"\2YZ\7\25\2\2Z[\7\17\2\2[\\\7\26\2\2\\\31\3\2\2\2\4\35)";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}