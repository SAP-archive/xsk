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
		T__13=1, T__12=2, T__11=3, T__10=4, T__9=5, T__8=6, T__7=7, T__6=8, T__5=9, 
		T__4=10, T__3=11, T__2=12, T__1=13, T__0=14, STRING=15, INT=16, BOOLEAN=17, 
		TRUE=18, FALSE=19, WS=20, LB=21, RB=22, EQ=23, SC=24, SIGNED_INT=25, LINE_COMMENT=26, 
		COMMENT=27;
	public static final String[] tokenNames = {
		"<INVALID>", "'nominvalue'", "'depends_on_table'", "'depends_on'", "'depends_on_view'", 
		"'public'", "'reset_by'", "'schema'", "'start_with'", "'cycles'", "'maxvalue'", 
		"','", "'increment_by'", "'nomaxvalue'", "'minvalue'", "STRING", "INT", 
		"BOOLEAN", "'true'", "'false'", "WS", "'['", "']'", "'='", "';'", "'-'", 
		"LINE_COMMENT", "COMMENT"
	};
	public static final int
		RULE_hdbsequence = 0, RULE_property = 1, RULE_schema = 2, RULE_increment_by = 3, 
		RULE_start_with = 4, RULE_maxvalue = 5, RULE_nomaxvalue = 6, RULE_minvalue = 7, 
		RULE_nominvalue = 8, RULE_cycles = 9, RULE_reset_by = 10, RULE_publicc = 11, 
		RULE_depends_on_table = 12, RULE_depends_on_view = 13, RULE_depends_on = 14, 
		RULE_depends_on_list = 15;
	public static final String[] ruleNames = {
		"hdbsequence", "property", "schema", "increment_by", "start_with", "maxvalue", 
		"nomaxvalue", "minvalue", "nominvalue", "cycles", "reset_by", "publicc", 
		"depends_on_table", "depends_on_view", "depends_on", "depends_on_list"
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
			setState(33); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(32); property();
				}
				}
				setState(35); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__13) | (1L << T__12) | (1L << T__11) | (1L << T__10) | (1L << T__9) | (1L << T__8) | (1L << T__7) | (1L << T__6) | (1L << T__5) | (1L << T__4) | (1L << T__2) | (1L << T__1) | (1L << T__0))) != 0) );
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
		public Depends_on_viewContext depends_on_view() {
			return getRuleContext(Depends_on_viewContext.class,0);
		}
		public MaxvalueContext maxvalue() {
			return getRuleContext(MaxvalueContext.class,0);
		}
		public Depends_onContext depends_on() {
			return getRuleContext(Depends_onContext.class,0);
		}
		public NomaxvalueContext nomaxvalue() {
			return getRuleContext(NomaxvalueContext.class,0);
		}
		public PubliccContext publicc() {
			return getRuleContext(PubliccContext.class,0);
		}
		public CyclesContext cycles() {
			return getRuleContext(CyclesContext.class,0);
		}
		public Depends_on_tableContext depends_on_table() {
			return getRuleContext(Depends_on_tableContext.class,0);
		}
		public Reset_byContext reset_by() {
			return getRuleContext(Reset_byContext.class,0);
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
			setState(50);
			switch (_input.LA(1)) {
			case T__7:
				enterOuterAlt(_localctx, 1);
				{
				setState(37); schema();
				}
				break;
			case T__2:
				enterOuterAlt(_localctx, 2);
				{
				setState(38); increment_by();
				}
				break;
			case T__6:
				enterOuterAlt(_localctx, 3);
				{
				setState(39); start_with();
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 4);
				{
				setState(40); maxvalue();
				}
				break;
			case T__1:
				enterOuterAlt(_localctx, 5);
				{
				setState(41); nomaxvalue();
				}
				break;
			case T__0:
				enterOuterAlt(_localctx, 6);
				{
				setState(42); minvalue();
				}
				break;
			case T__13:
				enterOuterAlt(_localctx, 7);
				{
				setState(43); nominvalue();
				}
				break;
			case T__5:
				enterOuterAlt(_localctx, 8);
				{
				setState(44); cycles();
				}
				break;
			case T__8:
				enterOuterAlt(_localctx, 9);
				{
				setState(45); reset_by();
				}
				break;
			case T__9:
				enterOuterAlt(_localctx, 10);
				{
				setState(46); publicc();
				}
				break;
			case T__12:
				enterOuterAlt(_localctx, 11);
				{
				setState(47); depends_on_table();
				}
				break;
			case T__10:
				enterOuterAlt(_localctx, 12);
				{
				setState(48); depends_on_view();
				}
				break;
			case T__11:
				enterOuterAlt(_localctx, 13);
				{
				setState(49); depends_on();
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
			setState(52); match(T__7);
			setState(53); match(EQ);
			setState(54); match(STRING);
			setState(55); match(SC);
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
			setState(57); match(T__2);
			setState(58); match(EQ);
			setState(59); match(INT);
			setState(60); match(SC);
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
			setState(62); match(T__6);
			setState(63); match(EQ);
			setState(64); match(INT);
			setState(65); match(SC);
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
			setState(67); match(T__4);
			setState(68); match(EQ);
			setState(69); match(INT);
			setState(70); match(SC);
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
			setState(72); match(T__1);
			setState(73); match(EQ);
			setState(74); match(BOOLEAN);
			setState(75); match(SC);
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
			setState(77); match(T__0);
			setState(78); match(EQ);
			setState(79); match(INT);
			setState(80); match(SC);
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
			setState(82); match(T__13);
			setState(83); match(EQ);
			setState(84); match(BOOLEAN);
			setState(85); match(SC);
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
			setState(87); match(T__5);
			setState(88); match(EQ);
			setState(89); match(BOOLEAN);
			setState(90); match(SC);
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
			setState(92); match(T__8);
			setState(93); match(EQ);
			setState(94); match(STRING);
			setState(95); match(SC);
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
			setState(97); match(T__9);
			setState(98); match(EQ);
			setState(99); match(BOOLEAN);
			setState(100); match(SC);
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

	public static class Depends_on_tableContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(HdbsequenceParser.EQ, 0); }
		public TerminalNode STRING() { return getToken(HdbsequenceParser.STRING, 0); }
		public TerminalNode SC() { return getToken(HdbsequenceParser.SC, 0); }
		public Depends_on_tableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_depends_on_table; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbsequenceListener ) ((HdbsequenceListener)listener).enterDepends_on_table(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbsequenceListener ) ((HdbsequenceListener)listener).exitDepends_on_table(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbsequenceVisitor ) return ((HdbsequenceVisitor<? extends T>)visitor).visitDepends_on_table(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Depends_on_tableContext depends_on_table() throws RecognitionException {
		Depends_on_tableContext _localctx = new Depends_on_tableContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_depends_on_table);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(102); match(T__12);
			setState(103); match(EQ);
			setState(104); match(STRING);
			setState(105); match(SC);
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

	public static class Depends_on_viewContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(HdbsequenceParser.EQ, 0); }
		public TerminalNode STRING() { return getToken(HdbsequenceParser.STRING, 0); }
		public TerminalNode SC() { return getToken(HdbsequenceParser.SC, 0); }
		public Depends_on_viewContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_depends_on_view; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbsequenceListener ) ((HdbsequenceListener)listener).enterDepends_on_view(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbsequenceListener ) ((HdbsequenceListener)listener).exitDepends_on_view(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbsequenceVisitor ) return ((HdbsequenceVisitor<? extends T>)visitor).visitDepends_on_view(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Depends_on_viewContext depends_on_view() throws RecognitionException {
		Depends_on_viewContext _localctx = new Depends_on_viewContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_depends_on_view);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(107); match(T__10);
			setState(108); match(EQ);
			setState(109); match(STRING);
			setState(110); match(SC);
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

	public static class Depends_onContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(HdbsequenceParser.EQ, 0); }
		public TerminalNode SC() { return getToken(HdbsequenceParser.SC, 0); }
		public Depends_on_listContext depends_on_list() {
			return getRuleContext(Depends_on_listContext.class,0);
		}
		public Depends_onContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_depends_on; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbsequenceListener ) ((HdbsequenceListener)listener).enterDepends_on(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbsequenceListener ) ((HdbsequenceListener)listener).exitDepends_on(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbsequenceVisitor ) return ((HdbsequenceVisitor<? extends T>)visitor).visitDepends_on(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Depends_onContext depends_on() throws RecognitionException {
		Depends_onContext _localctx = new Depends_onContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_depends_on);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(112); match(T__11);
			setState(113); match(EQ);
			setState(114); depends_on_list();
			setState(115); match(SC);
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

	public static class Depends_on_listContext extends ParserRuleContext {
		public TerminalNode STRING(int i) {
			return getToken(HdbsequenceParser.STRING, i);
		}
		public List<TerminalNode> STRING() { return getTokens(HdbsequenceParser.STRING); }
		public TerminalNode RB() { return getToken(HdbsequenceParser.RB, 0); }
		public TerminalNode LB() { return getToken(HdbsequenceParser.LB, 0); }
		public Depends_on_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_depends_on_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbsequenceListener ) ((HdbsequenceListener)listener).enterDepends_on_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbsequenceListener ) ((HdbsequenceListener)listener).exitDepends_on_list(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbsequenceVisitor ) return ((HdbsequenceVisitor<? extends T>)visitor).visitDepends_on_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Depends_on_listContext depends_on_list() throws RecognitionException {
		Depends_on_listContext _localctx = new Depends_on_listContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_depends_on_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(117); match(LB);
			setState(126);
			_la = _input.LA(1);
			if (_la==STRING) {
				{
				setState(118); match(STRING);
				setState(123);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__3) {
					{
					{
					setState(119); match(T__3);
					setState(120); match(STRING);
					}
					}
					setState(125);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(128); match(RB);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\35\u0085\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\3\2\6\2"+
		"$\n\2\r\2\16\2%\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5"+
		"\3\65\n\3\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6"+
		"\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3"+
		"\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3"+
		"\r\3\r\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20"+
		"\3\20\3\20\3\21\3\21\3\21\3\21\7\21|\n\21\f\21\16\21\177\13\21\5\21\u0081"+
		"\n\21\3\21\3\21\3\21\2\2\22\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \2"+
		"\2\u0083\2#\3\2\2\2\4\64\3\2\2\2\6\66\3\2\2\2\b;\3\2\2\2\n@\3\2\2\2\f"+
		"E\3\2\2\2\16J\3\2\2\2\20O\3\2\2\2\22T\3\2\2\2\24Y\3\2\2\2\26^\3\2\2\2"+
		"\30c\3\2\2\2\32h\3\2\2\2\34m\3\2\2\2\36r\3\2\2\2 w\3\2\2\2\"$\5\4\3\2"+
		"#\"\3\2\2\2$%\3\2\2\2%#\3\2\2\2%&\3\2\2\2&\3\3\2\2\2\'\65\5\6\4\2(\65"+
		"\5\b\5\2)\65\5\n\6\2*\65\5\f\7\2+\65\5\16\b\2,\65\5\20\t\2-\65\5\22\n"+
		"\2.\65\5\24\13\2/\65\5\26\f\2\60\65\5\30\r\2\61\65\5\32\16\2\62\65\5\34"+
		"\17\2\63\65\5\36\20\2\64\'\3\2\2\2\64(\3\2\2\2\64)\3\2\2\2\64*\3\2\2\2"+
		"\64+\3\2\2\2\64,\3\2\2\2\64-\3\2\2\2\64.\3\2\2\2\64/\3\2\2\2\64\60\3\2"+
		"\2\2\64\61\3\2\2\2\64\62\3\2\2\2\64\63\3\2\2\2\65\5\3\2\2\2\66\67\7\t"+
		"\2\2\678\7\31\2\289\7\21\2\29:\7\32\2\2:\7\3\2\2\2;<\7\16\2\2<=\7\31\2"+
		"\2=>\7\22\2\2>?\7\32\2\2?\t\3\2\2\2@A\7\n\2\2AB\7\31\2\2BC\7\22\2\2CD"+
		"\7\32\2\2D\13\3\2\2\2EF\7\f\2\2FG\7\31\2\2GH\7\22\2\2HI\7\32\2\2I\r\3"+
		"\2\2\2JK\7\17\2\2KL\7\31\2\2LM\7\23\2\2MN\7\32\2\2N\17\3\2\2\2OP\7\20"+
		"\2\2PQ\7\31\2\2QR\7\22\2\2RS\7\32\2\2S\21\3\2\2\2TU\7\3\2\2UV\7\31\2\2"+
		"VW\7\23\2\2WX\7\32\2\2X\23\3\2\2\2YZ\7\13\2\2Z[\7\31\2\2[\\\7\23\2\2\\"+
		"]\7\32\2\2]\25\3\2\2\2^_\7\b\2\2_`\7\31\2\2`a\7\21\2\2ab\7\32\2\2b\27"+
		"\3\2\2\2cd\7\7\2\2de\7\31\2\2ef\7\23\2\2fg\7\32\2\2g\31\3\2\2\2hi\7\4"+
		"\2\2ij\7\31\2\2jk\7\21\2\2kl\7\32\2\2l\33\3\2\2\2mn\7\6\2\2no\7\31\2\2"+
		"op\7\21\2\2pq\7\32\2\2q\35\3\2\2\2rs\7\5\2\2st\7\31\2\2tu\5 \21\2uv\7"+
		"\32\2\2v\37\3\2\2\2w\u0080\7\27\2\2x}\7\21\2\2yz\7\r\2\2z|\7\21\2\2{y"+
		"\3\2\2\2|\177\3\2\2\2}{\3\2\2\2}~\3\2\2\2~\u0081\3\2\2\2\177}\3\2\2\2"+
		"\u0080x\3\2\2\2\u0080\u0081\3\2\2\2\u0081\u0082\3\2\2\2\u0082\u0083\7"+
		"\30\2\2\u0083!\3\2\2\2\6%\64}\u0080";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}